package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.conts.ConstCar;
import th.co.gosoft.audit.cpram.conts.ConstChecklistPlanStatus;
import th.co.gosoft.audit.cpram.dao.CarDAO;
import th.co.gosoft.audit.cpram.dao.CarDetailDAO;
import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.CarDetailDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.model.CarModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;


public class CarController {
	
	private static Logger logger = Logger.getLogger(CarController.class);

	public DataTableModel<CarModel> dataTableGetCarList(HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel){
		Connection connection = null;
		CarDAO carDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			carDAO = new CarDAO(connection);
			StringBuilder queryWhereClause = new StringBuilder();
			DataTableModel<CarModel> dataTableModel = new DataTableModel<>();	
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
										
					
					if (col.getName().equals("carNo") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("c.car_no").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("planDate") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("check_plan.plan_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
					else if (col.getName().equals("dueDate") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("c.due_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
					else if (col.getName().equals("carStatusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("car_state.car_status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("checklistTitle") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("check_plan.checklist_title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("checklistPlanNo") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("check_plan.checklist_plan_no").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("supplierCompany") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("check_plan.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
										
				}
			}
						
			queryWhereClause.append(" AND ").append("check_plan.checklist_plan_status_id >= '").append(ConstChecklistPlanStatus.ACCEPT_RESULT_AUDIT).append("' ");
			int countCarId = carDAO.countCarList(queryWhereClause.toString());
			dataTableModel.setData(new ArrayList<>());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countCarId);
			dataTableModel.setRecordsTotal(countCarId);
			
			List<CarDTO> carDTOs = carDAO.getCarList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			for(CarDTO carDTO : carDTOs) {
				dataTableModel.getData().add(TransformDTO.transCarDTO(carDTO));
			}
			
			return dataTableModel;
		}catch (Exception e) {
			logger.error("CarController.dataTableGetCarList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally{
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean updateStateCar(HttpServletRequest httpServletRequest, String carObj) {
		Connection connection = null;
		Gson gson = null;
		boolean resultProcess = false;
		SessionUtils sessionUtils = null;
		CarDAO carDAO = null;
		CarDetailDAO carDetailDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			carDAO = new CarDAO(connection);
			carDetailDAO = new CarDetailDAO(connection);
			
			CarModel carRequestModel = gson.fromJson(carObj, CarModel.class); 
			UserDTO userSessionDTO = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserDTO.class);
			carRequestModel.setUpdateBy(NullUtils.cvStr(userSessionDTO.getUserId()));
			
			if(carRequestModel.getCarStatusId().getCarStatusId().equals(NullUtils.cvStr(ConstCar.CANCEL))) {
				StringBuilder queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND c_detail.car_id = '").append(carRequestModel.getCarId()).append("' ");
				List<CarDetailDTO> carDetailDTOs = carDetailDAO.getCarDetailList(0, carDetailDAO.countCarDetail(queryWhereClause.toString()), queryWhereClause.toString());
				if(!carDetailDTOs.isEmpty()) {
					for(CarDetailDTO carDetailDTO : carDetailDTOs) {
						carDetailDTO.setCompleted(null);
						carDetailDTO.setCompleteDate(null);
						carDetailDTO.setCompleteTime(null);
						carDetailDTO.setRemark(carRequestModel.getCarDetail().get(0).getRemark());
						carDetailDTO.setEnable('N');
						resultProcess = carDetailDAO.updateCarDetail(carDetailDTO);
						if(!resultProcess) {
							break;
						}
					}
				}else {
					resultProcess = true;
				}
				
				if(resultProcess) {
					resultProcess = carDAO.updateCar(TransformModel.transCarModel(carRequestModel));
				}
			}
			
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}		
			
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("CarController.updateStateCar() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally{
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getCarList(String carObj) {
		Connection connection = null;
		CarDAO carDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			carDAO = new CarDAO(connection);
			gson = new Gson();
			StringBuilder queryWhereClause = new  StringBuilder();
			
			
			CarModel carModel = gson.fromJson(carObj, CarModel.class);
			if(carModel.getCarId() != null) {
				if(!StringUtils.isNullOrEmpty(carModel.getCarId())) {
					queryWhereClause.append(" AND c.car_id = '").append(carModel.getCarId()).append("' ");
				}
			}
			
			List<CarDTO> carDTOs = carDAO.getCarList(0, carDAO.countCarList(queryWhereClause.toString()), queryWhereClause.toString());
			List<CarModel> carModels = new ArrayList<>();
			for(CarDTO carDTO : carDTOs) {
				carModels.add(TransformDTO.transCarDTO(carDTO));
			}
			return gson.toJson(carModels);
			
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("CarController.getCarList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally{
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
}
