package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.conts.ConstCar;
import th.co.gosoft.audit.cpram.conts.ConstEvalType;
import th.co.gosoft.audit.cpram.conts.ConstEvidenceType;
import th.co.gosoft.audit.cpram.dao.CarDAO;
import th.co.gosoft.audit.cpram.dao.CarDetailDAO;
import th.co.gosoft.audit.cpram.dao.EvalPlanDAO;
import th.co.gosoft.audit.cpram.dao.EvidenceDAO;
import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.CarDetailDTO;
import th.co.gosoft.audit.cpram.dto.CarStatusDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvidenceDTO;
import th.co.gosoft.audit.cpram.model.CarDetailModel;
import th.co.gosoft.audit.cpram.model.EvalPlanModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class CarDetailController {
	
	private final static Logger logger = Logger.getLogger(CarDetailController.class);
	
	
	public boolean updateStateCarDetail(HttpServletRequest httpServletRequest, String carDetailObj) {
		Connection connection = null;
		Gson gson = null;
		boolean resultProcess = false;
		CarDetailDAO carDetailDAO = null;
		SessionUtils sessionUtils = null;
		CarDAO carDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			carDetailDAO = new CarDetailDAO(connection);
			carDAO = new CarDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			
			CarDetailModel carDetailRequestModel = gson.fromJson(carDetailObj, CarDetailModel.class);
			carDetailRequestModel.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
			if(!StringUtils.isNullOrEmpty(carDetailRequestModel.getCompleted())) {
				if(carDetailRequestModel.getCompleted().equals("Y")) {
					carDetailRequestModel.setCompleteDate(DateUtils.getCurrentDateTime("dd/MM/yyyy HH:mm:ss"));					
				}
			}
			
			
			resultProcess = carDetailDAO.updateCarDetail(TransformModel.transCarDetailModel(carDetailRequestModel));
			
			if(!StringUtils.isNullOrEmpty(carDetailRequestModel.getEnable()) && resultProcess == true) {
				if(carDetailRequestModel.getEnable().equals("N")) {
					StringBuilder queryWhereClause = new StringBuilder();
					queryWhereClause.append(" AND c_detail.checklist_plan_id = '").append(carDetailRequestModel.getAuditResultId().getChecklistPlanId().getChecklistPlanId()).append("' ");
					queryWhereClause.append(" AND ad_result.accepted = 'Y' ");
					List<CarDetailDTO> carDetailDTOs = carDetailDAO.getCarDetailList(0, carDetailDAO.countCarDetail(queryWhereClause.toString()), queryWhereClause.toString());
					int countCancelCar = 0;
					for(CarDetailDTO carDetailDTO : carDetailDTOs) {
						if(carDetailDTO.getEnable().equals('N')) {
							countCancelCar ++;
						}
					}
					
					if(countCancelCar == carDetailDTOs.size()) {
						CarDTO carDTO = new CarDTO();
						carDTO.setCarId(NullUtils.cvInt(carDetailRequestModel.getCarId().getCarId()));
						carDTO.setCarStatusId(new CarStatusDTO());
						carDTO.getCarStatusId().setCarStatusId(ConstCar.CANCEL);
						carDTO.setEnable('Y');
						carDTO.setUpdateBy(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key));
						resultProcess = carDAO.updateCar(carDTO);
					}
					
				}
			}
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			return resultProcess;
		}catch (Exception e) {
			logger.error("CarDetailController.updateStateCarDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean updateDueDateCarDetail(HttpServletRequest httpServletRequest, String carDetailObj) {
		Connection connection = null;
		CarDetailDAO carDetailDAO = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		try {
			
			gson = new Gson();
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			carDetailDAO = new CarDetailDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			
			CarDetailModel carDetailRequestModel = gson.fromJson(carDetailObj, CarDetailModel.class);
			carDetailRequestModel.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
			carDetailRequestModel.setDueDate(String.format("%s %s", carDetailRequestModel.getDueDate().trim(), "00:00:00"));
			
			boolean resultProcess = carDetailDAO.updateDueDateCarDetail(TransformModel.transCarDetailModel(carDetailRequestModel));
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			return resultProcess;
		}catch (Exception e) {
			logger.error("CarDetailController.updateDueDateCarDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public String getCarDetailList(String carDetailObj,String actionType) {
		Connection connection = null;
		Gson gson = null;
		CarDAO carDAO = null;
		CarDetailDAO carDetailDAO = null;
		String resutJson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			carDAO = new CarDAO(connection);
			carDetailDAO = new CarDetailDAO(connection);
			
			CarDetailModel carDetailRequestModel = gson.fromJson(carDetailObj, CarDetailModel.class);
			
			StringBuilder queryWhereClause = new StringBuilder();
			if(carDetailRequestModel.getAuditResultId() != null) {
				if(carDetailRequestModel.getAuditResultId().getChecklistPlanId() != null) {
					if(!StringUtils.isNullOrEmpty(carDetailRequestModel.getAuditResultId().getChecklistPlanId().getChecklistPlanId())) {
						queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(carDetailRequestModel.getAuditResultId().getChecklistPlanId().getChecklistPlanId()).append("' ");
					}
				}
			}
			
			if(carDetailRequestModel.getCarId() != null) {
				if(!StringUtils.isNullOrEmpty(carDetailRequestModel.getCarId().getCarId())) {
					queryWhereClause.append(" AND c.car_id = '").append(carDetailRequestModel.getCarId().getCarId()).append("' ");
				}
			}
			
			
			List<CarDTO> carDTOs = carDAO.getCarList(0, carDAO.countCarList(queryWhereClause.toString()), queryWhereClause.toString());
			
			if(carDTOs != null && !carDTOs.isEmpty()) {
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND ad_result.accepted = 'Y' ");
				queryWhereClause.append(" AND c_detail.car_id = '").append(carDTOs.get(0).getCarId()).append("' ");
				List<CarDetailDTO> carDetailDTOs = carDetailDAO.getCarDetailList(0, carDetailDAO.countCarDetail(queryWhereClause.toString()), queryWhereClause.toString());
				
				List<CarDetailModel> carDetailModels = new ArrayList<>();
				for(CarDetailDTO carDetailDTO : carDetailDTOs) {
					carDetailDTO.setCarId(carDTOs.get(0));
					carDetailDTO.getAuditResultId().setEvalPlanId(TransformModel.transEvalPlanModel(getTopicEval(connection, TransformDTO.transEvalPlanDTO(carDetailDTO.getAuditResultId().getEvalPlanId()))));
					carDetailDTO.setEvidenceId(getEvidenceList(connection, carDetailDTO, actionType));
					carDetailModels.add(TransformDTO.transCarDetailDTO(carDetailDTO));
				}
				resutJson = gson.toJson(carDetailModels);
				return resutJson;
			}else {
				return resutJson;
			}
			
		}catch (Exception e) {
			logger.error("CarDetailController.getCarDetailList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	
	private EvalPlanModel getTopicEval(Connection connection, EvalPlanModel evalPlanModel) {
		EvalPlanDAO evalPlanDAO = null;
		try {
			
			evalPlanDAO = new EvalPlanDAO(connection);		
			String evalPlanId = evalPlanModel.getEvalPlanId();
			boolean flagStop = false;	
			
			while(!flagStop) {
				StringBuilder queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND eplan.eval_plan_id = '").append(evalPlanId).append("' ");
				
				EvalPlanDTO evalPlanDTO = evalPlanDAO.getEvalPlanList(0, evalPlanDAO.countEvalPlanList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
				
				if(evalPlanDTO.getEvalTypeId().getEvalTypeId() == ConstEvalType.QUESTION) {
					evalPlanModel.setDetail(evalPlanDTO.getDetail());
					evalPlanId = NullUtils.cvStr(evalPlanDTO.getParentId());
				}else if(evalPlanDTO.getEvalTypeId().getEvalTypeId() == ConstEvalType.TOPIC) {
					//evalPlanModel.setTitle(evalPlanDTO.getTitle()+" "+evalPlanModel.getDetail().split(" ")[0].trim());
					evalPlanModel.setTitle(evalPlanDTO.getTitle()+" "+evalPlanModel.getDetail().trim());
					flagStop = true;
					evalPlanId = NullUtils.cvStr(evalPlanDTO.getParentId());
				}else {
					evalPlanId = NullUtils.cvStr(evalPlanDTO.getParentId());
				}
			
			}
			
			return evalPlanModel;
			
		}catch (Exception e) {
			logger.error("CarDetailController.getTopicEval() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}				
	}
	
	private List<EvidenceDTO> getEvidenceList(Connection connection, CarDetailDTO carDetailDTO, String actionType) {
		EvidenceDAO evidenceDAO = null;
		try {
			
			evidenceDAO = new EvidenceDAO(connection);
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND e.checklist_plan_id = '").append(carDetailDTO.getAuditResultId().getChecklistPlanId().getChecklistPlanId()).append("' ");
			queryWhereClause.append(" AND e.eval_plan_id = '").append(carDetailDTO.getAuditResultId().getEvalPlanId().getEvalPlanId()).append("' ");
			queryWhereClause.append(" AND e.enable = 'Y' ");
			if(!actionType.equals("PRINT")) {
				queryWhereClause.append(" AND e.auditor_id = '").append(carDetailDTO.getAuditResultId().getAuditorId().getUserId()).append("' ");
				queryWhereClause.append(" AND e.action_type = 'A' ");
			}
			
			List<EvidenceDTO> evidenceDTOs = evidenceDAO.getEvidenceList(queryWhereClause.toString(),actionType);
			for(EvidenceDTO evidence : evidenceDTOs) {
				if(evidence.getEvidenceTypeId().getEvidenceTypeId() == ConstEvidenceType.PICTURE) {
					System.out.println(ConfigurationSystemManager.getInstance().getHttpWebserverName());
					System.out.println(ConfigurationSystemManager.getInstance().getHttpAliasAuditResult());
					evidence.setData(String.format("%s%s%s", ConfigurationSystemManager.getInstance().getHttpWebserverName(), ConfigurationSystemManager.getInstance().getHttpAliasAuditResult(), evidence.getData()));
				}
			}
			return evidenceDTOs;
		}catch (Exception e) {
			logger.error("CarDetailController.getEvidenceList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}			
	}
	
	public boolean editCarDetail(HttpServletRequest httpServletRequest, String carDetailObj) {
		Connection connection = null;
		Gson gson = null;
		boolean resultProcess = false;
		CarDetailDAO carDetailDAO = null;
		SessionUtils sessionUtils = null;
		CarDAO carDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			carDetailDAO = new CarDetailDAO(connection);
			carDAO = new CarDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			
			CarDetailModel carDetailRequestModel = gson.fromJson(carDetailObj, CarDetailModel.class);
			carDetailRequestModel.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
			resultProcess = carDetailDAO.editCarDetail(TransformModel.transCarDetailModel(carDetailRequestModel));
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			return resultProcess;
		}catch (Exception e) {
			logger.error("CarDetailController.updateStateCarDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
}
