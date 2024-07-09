package th.co.gosoft.audit.cpram.controller;

import java.lang.reflect.Type;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.conts.ConstEvidenceType;
import th.co.gosoft.audit.cpram.dao.CarDetailDAO;
import th.co.gosoft.audit.cpram.dao.EvidenceDAO;
import th.co.gosoft.audit.cpram.dao.SystemLogDAO;
import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.CarDetailDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvidenceDTO;
import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.model.EvidenceModel;
import th.co.gosoft.audit.cpram.model.SystemLogModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class EvidenceController {
	
	private final static Logger logger = Logger.getLogger(EvidenceController.class);
	
	public String getEvidence(HttpServletRequest httpServletRequest, String evidenceObj) {
		Connection connection = null;
		Gson gson = null;
		EvidenceDAO evidenceDAO = null;
		String evidenceListResponse = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			evidenceDAO = new EvidenceDAO(connection);
			
			EvidenceModel evidenceModelRequest = gson.fromJson(evidenceObj, EvidenceModel.class);
			if(evidenceModelRequest != null) {
				StringBuilder queryWhereClause = new StringBuilder();
				
				if(evidenceModelRequest.getAuditResultId() != null) {					
					
					
					if(evidenceModelRequest.getAuditResultId().getChecklistPlanId() != null && !StringUtils.isNullOrEmpty(evidenceModelRequest.getAuditResultId().getChecklistPlanId().getChecklistPlanId())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("e.checklist_plan_id ").append(" = ").append(evidenceModelRequest.getAuditResultId().getChecklistPlanId().getChecklistPlanId());
					}
					
					if(evidenceModelRequest.getAuditResultId().getEvalPlanId() != null && !StringUtils.isNullOrEmpty(evidenceModelRequest.getAuditResultId().getEvalPlanId().getEvalPlanId())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("e.eval_plan_id ").append(" = ").append(evidenceModelRequest.getAuditResultId().getEvalPlanId().getEvalPlanId());
					}
					
				}	
				
				if(evidenceModelRequest.getEvidenceTypeId() != null) {
					if(!StringUtils.isNullOrEmpty(evidenceModelRequest.getEvidenceTypeId().getEvidenceTypeId())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("et.evidence_type_id ").append(" = ").append(evidenceModelRequest.getEvidenceTypeId().getEvidenceTypeId());
					}
				}
				
				if(!StringUtils.isNullOrEmpty(evidenceModelRequest.getActionType())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("e.action_type ").append(" = '").append(evidenceModelRequest.getActionType()).append("' ");
				}
				
				
				
				List<EvidenceDTO> evidenceDTOs = evidenceDAO.getEvidenceList(queryWhereClause.toString());
				List<EvidenceModel> evidenceModels = new ArrayList<EvidenceModel>();
				
				for(EvidenceDTO evidenceDTO : evidenceDTOs) {
					EvidenceModel evidenceModel = TransformDTO.transEvidenceDTO(evidenceDTO);
					if(evidenceDTO.getEvidenceTypeId().getEvidenceTypeId() == ConstEvidenceType.PICTURE) {
						evidenceModel.setData(String.format("%s%s%s", ConfigurationSystemManager.getInstance().getHttpWebserverName(), ConfigurationSystemManager.getInstance().getHttpAliasAuditResult(), evidenceModel.getData()));
					}
					evidenceModels.add(evidenceModel);
					
				}
				
				evidenceListResponse = gson.toJson(evidenceModels);
			}	
			return evidenceListResponse;
		}catch (Exception e) {
			logger.error("EvidenceController.getEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean insertEvidence(HttpServletRequest httpServletRequest, String evidenceListObj) {
		Connection connection = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		FileProcessing fileProcessing = null;
		EvidenceDAO evidenceDAO = null;
		CarDetailDAO carDetailDAO = null;
		SystemLogDAO systemLogDAO = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();			
			Type listType = new TypeToken<ArrayList<EvidenceModel>>(){}.getType();
			sessionUtils = new SessionUtils(httpServletRequest);			
			fileProcessing = new FileProcessing();
			evidenceDAO = new EvidenceDAO(connection);
			carDetailDAO = new CarDetailDAO(connection);
			systemLogDAO = new SystemLogDAO(connection);
			List<EvidenceModel> listEvidenceRequestModel = gson.fromJson(evidenceListObj, listType);
			int orderEvidenceMedia = 1;
			
			if(!listEvidenceRequestModel.isEmpty()) {
				String pathEvidence = String.format("p%s_u%s", listEvidenceRequestModel.get(0).getAuditResultId().getChecklistPlanId().getChecklistPlanId(), sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key));
				String subPathEvidence = String.format("d%s_%s", DateUtils.getCurrentDateTime("yyyyMMddHHmmssSSS"), pathEvidence);
				
				for(EvidenceModel evidence : listEvidenceRequestModel) {
					evidence.setCreateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
					evidence.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
					
					if(NullUtils.cvInt(evidence.getEvidenceTypeId().getEvidenceTypeId()) == ConstEvidenceType.TEXT) {
						
						resultProcess = evidenceDAO.insertEvidence(TransformModel.transEvidenceModel(evidence));
						if(!resultProcess)
							break;
						
					}else if(NullUtils.cvInt(evidence.getEvidenceTypeId().getEvidenceTypeId()) == ConstEvidenceType.PICTURE || NullUtils.cvInt(evidence.getEvidenceTypeId().getEvidenceTypeId()) == ConstEvidenceType.DOCUMENT) {
						
						String assemblyPath = Paths.get(pathEvidence, subPathEvidence,"evidence_"+evidence.getAuditResultId().getEvalPlanId().getEvalPlanId()+"_"+NullUtils.cvStr(orderEvidenceMedia)+"."+evidence.getData().split("\\.")[evidence.getData().split("\\.").length-1]).toString();			
						String fullPath = Paths.get(FileUploadConst.PATH_UPLOAD_EVIDENCE, assemblyPath).toString();	
						resultProcess = fileProcessing.CopyFile(Paths.get(FileUploadConst.PATH_Temp, evidence.getData()).toString(), fullPath);
						if(resultProcess) {
							fileProcessing.DeleteFile(Paths.get(FileUploadConst.PATH_Temp, evidence.getData()).toString());
							evidence.setData(assemblyPath.replace('\\', '/'));
							resultProcess = evidenceDAO.insertEvidence(TransformModel.transEvidenceModel(evidence));
							if(!resultProcess)
								break;
							else								
								orderEvidenceMedia += 1;
						}else {
							break;
						}						
					}
				}
				
				CarDetailDTO carDetailDTO = new CarDetailDTO();
				carDetailDTO.setCompleted(null);
				carDetailDTO.setRemark("");
				carDetailDTO.setUpdateBy(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key));
				carDetailDTO.setAuditResultId(new AuditResultDTO());
				carDetailDTO.getAuditResultId().setChecklistPlanId(new ChecklistPlanDTO());
				carDetailDTO.getAuditResultId().getChecklistPlanId().setChecklistPlanId(NullUtils.cvInt(listEvidenceRequestModel.get(0).getAuditResultId().getChecklistPlanId().getChecklistPlanId()));
				carDetailDTO.getAuditResultId().setEvalPlanId(new EvalPlanDTO());
				carDetailDTO.getAuditResultId().getEvalPlanId().setEvalPlanId(NullUtils.cvInt(listEvidenceRequestModel.get(0).getAuditResultId().getEvalPlanId().getEvalPlanId()));
				
				
				resultProcess = carDetailDAO.updateByChecklistPlanAndEvalPlan(carDetailDTO);
				
			}
			
			if(resultProcess) {
				SystemLogModel systemLogModel = new SystemLogModel();
				systemLogModel.setAccess("final_audit_result");
				systemLogModel.setActivity("supplier_edit");
				systemLogModel.setDetail("ส่งหลักฐานการแก้ไข");
				systemLogModel.setCreateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
				systemLogModel.setRefId(NullUtils.cvStr(listEvidenceRequestModel.get(0).getAuditResultId().getChecklistPlanId().getChecklistPlanId()));
				systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));	
				DatabaseUtils.commit(connection);
			}				
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("EvidenceController.insertEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean updateEvidence(HttpServletRequest httpServletRequest, String evidenceListObj) {
		Connection connection = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		EvidenceDAO evidenceDAO = null;
		CarDetailDAO carDetailDAO = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			Type listType = new TypeToken<ArrayList<EvidenceModel>>(){}.getType();
			sessionUtils = new SessionUtils(httpServletRequest);
			evidenceDAO = new EvidenceDAO(connection);
			carDetailDAO = new CarDetailDAO(connection);
			List<EvidenceModel> listEvidenceRequestModel = gson.fromJson(evidenceListObj, listType);
			
			
			if(!listEvidenceRequestModel.isEmpty()) {
				for(EvidenceModel evidence : listEvidenceRequestModel) {
					evidence.setCreateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
					evidence.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
					if(NullUtils.cvInt(evidence.getEvidenceTypeId().getEvidenceTypeId()) == ConstEvidenceType.TEXT) {
						
						resultProcess = evidenceDAO.updateEvidence(TransformModel.transEvidenceModel(evidence));
						if(!resultProcess)
							break;
						
					}
				}
				
				CarDetailDTO carDetailDTO = new CarDetailDTO();
				carDetailDTO.setCompleted(null);
				carDetailDTO.setRemark("");
				carDetailDTO.setUpdateBy(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key));
				carDetailDTO.setAuditResultId(new AuditResultDTO());
				carDetailDTO.getAuditResultId().setChecklistPlanId(new ChecklistPlanDTO());
				carDetailDTO.getAuditResultId().getChecklistPlanId().setChecklistPlanId(NullUtils.cvInt(listEvidenceRequestModel.get(0).getAuditResultId().getChecklistPlanId().getChecklistPlanId()));
				carDetailDTO.getAuditResultId().setEvalPlanId(new EvalPlanDTO());
				carDetailDTO.getAuditResultId().getEvalPlanId().setEvalPlanId(NullUtils.cvInt(listEvidenceRequestModel.get(0).getAuditResultId().getEvalPlanId().getEvalPlanId()));
				
				resultProcess = carDetailDAO.updateByChecklistPlanAndEvalPlan(carDetailDTO);
				
			}
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("EvidenceController.updateEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getEvidenceSolveCar(String evidenceObj) {
		Connection connection = null;
		EvidenceDAO evidenceDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			evidenceDAO = new EvidenceDAO(connection);
			gson = new Gson();
			EvidenceModel evidenceRequestModel = gson.fromJson(evidenceObj, EvidenceModel.class);
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND e.checklist_plan_id = '").append(evidenceRequestModel.getAuditResultId().getChecklistPlanId().getChecklistPlanId()).append("' ");
			queryWhereClause.append(" AND e.auditor_id = '").append("0").append("' ");
			queryWhereClause.append(" AND e.eval_plan_id = '").append(evidenceRequestModel.getAuditResultId().getEvalPlanId().getEvalPlanId()).append("' ");
			queryWhereClause.append(" AND e.enable = 'Y' AND e.action_type = 'S' ");
			
			List<EvidenceDTO> evidenceDTOs = evidenceDAO.getEvidenceList(queryWhereClause.toString());
			List<EvidenceModel> evidenceModels = new ArrayList<>();
			for(EvidenceDTO evidence : evidenceDTOs) {
				if(evidence.getEvidenceTypeId().getEvidenceTypeId() == ConstEvidenceType.PICTURE || evidence.getEvidenceTypeId().getEvidenceTypeId() == ConstEvidenceType.DOCUMENT) {
					evidence.setData(ConfigurationSystemManager.getInstance().getHttpWebserverName().concat(ConfigurationSystemManager.getInstance().getHttpAliasAuditResult()).concat(evidence.getData()));
				}
				evidenceModels.add(TransformDTO.transEvidenceDTO(evidence));
			}
			return gson.toJson(evidenceModels);
		}catch(Exception e) {
			logger.error("EvidenceController.updateEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean deleteEvidence(String evidenceObj) {
		Connection connection = null;
		EvidenceDAO evidenceDAO = null;
		Gson gson = null;
		FileProcessing fileProcessing = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			evidenceDAO = new EvidenceDAO(connection);
			gson = new Gson();			
			fileProcessing = new FileProcessing();
			
			StringBuilder queryWhereClause = new StringBuilder();
			EvidenceModel evidenceRequestModel = gson.fromJson(evidenceObj, EvidenceModel.class);
			
			if(!StringUtils.isNullOrEmpty(evidenceRequestModel.getEvidenceId())) {
				queryWhereClause.append(" AND e.evidence_id ='").append(evidenceRequestModel.getEvidenceId()).append("' ");
			}
			
			List<EvidenceDTO> evidenceDTOs = evidenceDAO.getEvidenceList(queryWhereClause.toString());
			for(EvidenceDTO evidenceDTO : evidenceDTOs) {
				if(evidenceDTO.getEvidenceTypeId().getEvidenceTypeId() == ConstEvidenceType.PICTURE || evidenceDTO.getEvidenceTypeId().getEvidenceTypeId() == ConstEvidenceType.DOCUMENT) {
					resultProcess = fileProcessing.DeleteFile(Paths.get(ConfigurationSystemManager.getInstance().getMobileServicePathFileUpload(), evidenceDTO.getData()).toString());
					if(resultProcess) {
						resultProcess = evidenceDAO.deleteEvidence(evidenceDTO);
						if(!resultProcess) {
							break;
						}
					}else {
						break;
					}
				}
			}
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("EvidenceController.deleteEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
}
