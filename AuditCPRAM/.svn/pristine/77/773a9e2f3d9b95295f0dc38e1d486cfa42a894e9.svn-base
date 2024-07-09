package th.co.gosoft.audit.cpram.controller;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.picketbox.util.StringUtil;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.conts.ConstAuditType;
import th.co.gosoft.audit.cpram.conts.ConstCar;
import th.co.gosoft.audit.cpram.conts.ConstChecklistPlanStatus;
import th.co.gosoft.audit.cpram.conts.ConstEvalType;
import th.co.gosoft.audit.cpram.conts.ConstEvidenceType;
import th.co.gosoft.audit.cpram.conts.ConstFinalAuditResultStatus;
import th.co.gosoft.audit.cpram.conts.ConstSystemProperties;
import th.co.gosoft.audit.cpram.cryptography.AsymmetricCryptography;
import th.co.gosoft.audit.cpram.dao.AuditResultDAO;
import th.co.gosoft.audit.cpram.dao.CarDAO;
import th.co.gosoft.audit.cpram.dao.CarDetailDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistPlanDAO;
import th.co.gosoft.audit.cpram.dao.EvalPlanDAO;
import th.co.gosoft.audit.cpram.dao.EvidenceDAO;
import th.co.gosoft.audit.cpram.dao.FinalAuditResultDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dao.SystemLogDAO;
import th.co.gosoft.audit.cpram.dao.UserDAO;
import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.CarDetailDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvidenceDTO;
import th.co.gosoft.audit.cpram.dto.FinalAuditResultDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.mail.BodyEmailConfirmEditFinalAuditResultForSupplier;
import th.co.gosoft.audit.cpram.mail.BodyEmailConfirmFinalAuditResultForApprover;
import th.co.gosoft.audit.cpram.mail.BodyEmailConfirmFinalAuditResultForSupplier;
import th.co.gosoft.audit.cpram.mail.MailConnector;
import th.co.gosoft.audit.cpram.mail.MailReceiver;
import th.co.gosoft.audit.cpram.model.AuditorModel;
import th.co.gosoft.audit.cpram.model.CarDetailModel;
import th.co.gosoft.audit.cpram.model.CarModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.EvalPlanModel;
import th.co.gosoft.audit.cpram.model.FinalAuditResultModel;
import th.co.gosoft.audit.cpram.model.PrintFinalAuditResultPdfModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.SystemLogModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.PdfUtils;
import th.co.gosoft.audit.cpram.utils.PropertiesUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class FinalAuditResultController {
	private final static Logger logger = Logger.getLogger(FinalAuditResultController.class);
	public DataTableModel<FinalAuditResultModel> datatableGetFinalAuditResultList(HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel){
		FinalAuditResultDAO finalAuditResultDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		Connection connection = null;		
		SessionUtils sessionUtils = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			finalAuditResultDAO = new FinalAuditResultDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			DataTableModel<FinalAuditResultModel> dataTableModel = new DataTableModel<>();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);	
			
			
			StringBuilder queryWhereClause = new StringBuilder();
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					if (col.getName().equals("checklistPlanNo")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						queryWhereClause.append(" AND ");
						queryWhereClause.append("final_result.checklist_plan_no").append(" like '%").append(col.getSearch().getValue()).append("%'  ");
					}else if (col.getName().equals("planDate")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("final_result.plan_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");					
					}else if (col.getName().equals("finalAuditResultStatusId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("final_result_state.final_audit_result_status_id").append(" = '").append(col.getSearch().getValue()).append("' ");						
					}else if (col.getName().equals("supplierData") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("supplier.supplier_company").append(" like '%").append(col.getSearch().getValue()).append("%'  ");
					}
				}
			}
			
			
			
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
				List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel));
				queryWhereClause.append(" AND ").append("final_result.supplier_id = '").append(supplierDTOs.get(0).getSupplierId()).append("' ");
				queryWhereClause.append(" AND ").append("final_result.final_audit_result_status_id != '").append(ConstFinalAuditResultStatus.WAIT_NOTIFY_SUPPLIER).append("' ");
			}
						
			
			int countFinalAuditResult = finalAuditResultDAO.countFinalAuditResultList(queryWhereClause.toString());
			dataTableModel.setData(new ArrayList<>());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countFinalAuditResult);
			dataTableModel.setRecordsTotal(countFinalAuditResult);
			
			List<FinalAuditResultDTO> finalAuditResultDTOs = finalAuditResultDAO.getFinalAuditResultList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			
			for(FinalAuditResultDTO finalAuditResult : finalAuditResultDTOs) {
				dataTableModel.getData().add(TransformDTO.transFinalAuditResultDTO(finalAuditResult));
			}
			return dataTableModel;
		}catch (Exception e) {
			logger.error("FinalAuditResultController.datatableGetFinalAuditResultList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public boolean updateGrade(HttpServletRequest httpServletRequest, String objFinalAuditResult) {
		Connection connection = null;
		FinalAuditResultDAO finalAuditResultDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			finalAuditResultDAO = new FinalAuditResultDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);			
			FinalAuditResultModel finalAuditResultModel = gson.fromJson(objFinalAuditResult, FinalAuditResultModel.class);
			
			finalAuditResultModel.setUpdateBy(userSessionModel.getUserId());
			int rowAffect = finalAuditResultDAO.updateGrade(TransformModel.transFinalAuditResultModel(finalAuditResultModel));
			if(rowAffect == 1) {
				resultProcess = true;
				DatabaseUtils.commit(connection);
			}else {
				resultProcess = false;
				DatabaseUtils.rollback(connection);
			}
			return resultProcess;
		}catch (Exception e) {
			logger.error("FinalAuditResultController.updateGrade() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	
	/**
	 * @param httpServletRequest
	 * @param objFinalAuditResult
	 * @return
	 */
	public boolean updateFinalAuditResult(HttpServletRequest httpServletRequest, String objFinalAuditResult) {
		Connection connection = null;
		FinalAuditResultDAO finalAuditResultDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		UserDAO userDAO = null;
		AsymmetricCryptography asymmetricCryptography = null;
		CarDAO carDAO = null;
		CarDetailDAO carDetailDAO = null;
		EvidenceDAO evidenceDAO = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		AuditResultDAO auditResultDAO = null;
		SystemLogDAO systemLogDAO = null;
		boolean resultProcess = false;
		String activityLog = "";
		String detailLog = "";
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			finalAuditResultDAO = new FinalAuditResultDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			userDAO = new UserDAO(connection);
			carDAO = new CarDAO(connection);
			carDetailDAO = new CarDetailDAO(connection);
			evidenceDAO = new EvidenceDAO(connection);
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			auditResultDAO = new AuditResultDAO(connection);
			systemLogDAO = new SystemLogDAO(connection);
			asymmetricCryptography = new AsymmetricCryptography("RSA");
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			FinalAuditResultModel finalAuditResultRequestModel = gson.fromJson(objFinalAuditResult, FinalAuditResultModel.class);
			finalAuditResultRequestModel.setUpdateBy(userSessionModel.getUserId());
			
			if(NullUtils.cvInt(finalAuditResultRequestModel.getFinalAuditResultStatusId().getFinalAuditResultStatusId()) == ConstFinalAuditResultStatus.WAIT_NOTIFY_SUPPLIER && !StringUtils.isNullOrEmpty(finalAuditResultRequestModel.getAuditType())) {
				finalAuditResultRequestModel.getFinalAuditResultStatusId().setFinalAuditResultStatusId(NullUtils.cvStr(ConstFinalAuditResultStatus.WAIT_SUPPLIER_CONFIRM));
				activityLog = "audit_sent_result";
				detailLog = "ส่งสรุปผลการตรวจให้กับซัพพลายเออร์";
				
				
				int rowAffect = finalAuditResultDAO.updateAuditType(TransformModel.transFinalAuditResultModel(finalAuditResultRequestModel));
				
				if(rowAffect == 1) {
										
					/*finalAuditResultRequestModel.getChecklistPlanId().setChecklistPlanStatusId(new ChecklistPlanStatusModel());
					finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanStatusId().setChecklistPlanStatusId(NullUtils.cvStr(ConstChecklistPlanStatus.PROMOTE_RESULT_AUDIT));
					rowAffect = checklistPlanDAO.updateStatusChecklistPlan(TransformModel.transChecklistPlanModel(finalAuditResultRequestModel.getChecklistPlanId()));*/
					
					
					List<UserDTO> userDTOs = supplierUserMappingDAO.getUserInSupplier(TransformModel.transSupplierModel(finalAuditResultRequestModel.getSupplierId()));
					List<String> reciecverEmail = new ArrayList<>();
					String audit = finalAuditResultRequestModel.getAuditor().replaceAll("\\\\", "");
//					String editAudit = audit.substring(2, audit.length()-2);
//					audit = "["+editAudit+"]";
					
					audit = audit.replaceAll("\"","");					
					JSONArray json = new JSONArray(audit);
					audit = json.toString();
					
					AuditorModel[] auditor = gson.fromJson(audit, AuditorModel[].class);
					for(AuditorModel each : auditor) {
						UserDTO users = userDAO.getUserByUserId(each.userId+"");
						reciecverEmail.add(users.getEmail());
					}
					for(UserDTO user : userDTOs) {
						if(StringUtil.isNotNull(user.getEmail())) {
							reciecverEmail.add(user.getEmail());
						}						
					}
					MailReceiver mailReceiver = new MailReceiver();
					mailReceiver.setMailReceiver(reciecverEmail);
					
					StringBuilder queryWhereClause = new StringBuilder();	
					CarDTO carDTO = null;
					if(!finalAuditResultRequestModel.getCarId().getCarId().equals("0")) {
						queryWhereClause.append(" AND c.car_id = '").append(finalAuditResultRequestModel.getCarId().getCarId()).append("' ");
						carDTO = carDAO.getCarList(0, carDAO.countCarList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);	
					}else {
						carDTO = new CarDTO();
						carDTO.setDueDate(null);
						carDTO.setDueTime(null);
					}
					
					JsonElement jsonElement = new JsonParser().parse(finalAuditResultRequestModel.getSupplierData());
					JsonObject  jobject = jsonElement.getAsJsonObject();
					//String resultEncoder = EncryptUtils.encoderURL(String.format("checklistPlanId=%s", finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()));
					String resultEncoder = asymmetricCryptography.encryptText(String.format("%s", finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()), asymmetricCryptography.getPrivateKey(PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PRIVATE_KEY_RSA)));
					BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier = new BodyEmailConfirmFinalAuditResultForSupplier();
					bodyEmailConfirmFinalAuditResultForSupplier.setSupplierName(jobject.get("supplierCompany").getAsString());					
					bodyEmailConfirmFinalAuditResultForSupplier.setUrlForConfirm(ConfigurationSystemManager.getInstance().getMailFooterUrlWeb()+"/connector_final_audit_result.jsp?checklistPlanId="+resultEncoder);
					bodyEmailConfirmFinalAuditResultForSupplier.setEmailAdmin(ConfigurationSystemManager.getInstance().getMailFooterAdminMail());
					bodyEmailConfirmFinalAuditResultForSupplier.setTelephoneAdmin(ConfigurationSystemManager.getInstance().getMailFooterTelephone());
					bodyEmailConfirmFinalAuditResultForSupplier.setDateAccept(TransformDTO.transCarDTO(carDTO).getDueDate().trim());
					
					resultProcess = MailConnector.sendMailToSupplierForConfirmFinalAuditResult(mailReceiver, bodyEmailConfirmFinalAuditResultForSupplier, "แจ้งผลการเข้าตรวจ");	
							
					
									
					
				}
			}else if(NullUtils.cvInt(finalAuditResultRequestModel.getFinalAuditResultStatusId().getFinalAuditResultStatusId()) == ConstFinalAuditResultStatus.WAIT_SUPPLIER_CONFIRM) {
				finalAuditResultRequestModel.getFinalAuditResultStatusId().setFinalAuditResultStatusId(NullUtils.cvStr(ConstFinalAuditResultStatus.SUPPLIER_PROCESS));
				activityLog = "supplier_accept_result";
				detailLog = "รับทราบสรุปผลการตรวจ";
				
				int rowAffect = finalAuditResultDAO.updateAuditType(TransformModel.transFinalAuditResultModel(finalAuditResultRequestModel));
				if(rowAffect == 1) {
					resultProcess = true;
				}
			}else if(NullUtils.cvInt(finalAuditResultRequestModel.getFinalAuditResultStatusId().getFinalAuditResultStatusId()) == ConstFinalAuditResultStatus.SUPPLIER_PROCESS && !StringUtils.isNullOrEmpty(finalAuditResultRequestModel.getPass())) {
				activityLog = "audit_submit_result";
				detailLog = "บันทึกสรุปผลการเข้าตรวจ";	
				
				Map<String, String> objApproval = new HashMap<>();
				objApproval.put("userId", userSessionModel.getUserId());
				objApproval.put("fullname", userSessionModel.getFullname());
				StringBuilder queryWhereClause = new StringBuilder();								
				
				queryWhereClause.append(" AND c.car_id = '").append(finalAuditResultRequestModel.getCarId().getCarId()).append("' ");
				List<CarDTO> carDTOs = carDAO.getCarList(0, carDAO.countCarList(queryWhereClause.toString()), queryWhereClause.toString());
				
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND ad_result.checklist_plan_id = '").append(finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()).append("' ");
				queryWhereClause.append(" AND ad_result.accepted = 'Y' ");
				List<AuditResultDTO> auditResultDTOs = auditResultDAO.getAuditResultList(queryWhereClause.toString());
				
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND c_detail.enable = 'Y' ");
				queryWhereClause.append(" AND c_detail.car_id = '").append(finalAuditResultRequestModel.getCarId().getCarId()).append("' ");
				List<CarDetailDTO> carDetailDTOs = carDetailDAO.getCarDetailList(0, carDetailDAO.countCarDetail(queryWhereClause.toString()), queryWhereClause.toString());
				List<CarDetailModel> carDetailModels = new ArrayList<>();
				for(CarDetailDTO carDetailDTO : carDetailDTOs) {
					queryWhereClause.setLength(0);
					queryWhereClause = new StringBuilder();
					queryWhereClause.append(" AND e.eval_plan_id = '").append(carDetailDTO.getAuditResultId().getEvalPlanId().getEvalPlanId()).append("' ");
					queryWhereClause.append(" AND e.enable = 'Y' ");
					List<EvidenceDTO> evidenceDTOs = evidenceDAO.getEvidenceList(queryWhereClause.toString());
					for(EvidenceDTO evidence : evidenceDTOs) {
						if(evidence.getEvidenceTypeId().getEvidenceTypeId() == ConstEvidenceType.PICTURE) {
							evidence.setData(String.format("%s%s%s", ConfigurationSystemManager.getInstance().getHttpWebserverName(), ConfigurationSystemManager.getInstance().getHttpAliasAuditResult(), evidence.getData()));
						}
					}
					carDetailDTO.setEvidenceId(evidenceDTOs);
					
					queryWhereClause.setLength(0);
					queryWhereClause = new StringBuilder();
					
					carDetailDTO.getAuditResultId().setEvalPlanId(TransformModel.transEvalPlanModel(getTopicEval(connection, TransformDTO.transEvalPlanDTO(carDetailDTO.getAuditResultId().getEvalPlanId()))));
					carDetailDTO.setCarId(carDTOs.get(0));
					
					for(AuditResultDTO auditResultDTO : auditResultDTOs) {
						
						if(auditResultDTO.getChecklistPlanId().getChecklistPlanId() == carDetailDTO.getAuditResultId().getChecklistPlanId().getChecklistPlanId() && auditResultDTO.getAuditorId().getUserId() == carDetailDTO.getAuditResultId().getAuditorId().getUserId() && auditResultDTO.getEvalPlanId().getEvalPlanId() == carDetailDTO.getAuditResultId().getEvalPlanId().getEvalPlanId()) {
							carDetailModels.add(TransformDTO.transCarDetailDTO(carDetailDTO));
							
							if(carDetailDTO.getCompleted().equals(null) || carDetailDTO.getCompleted().equals(Character.MIN_VALUE)) {
								if(finalAuditResultRequestModel.getPass().equals("N")) {
									CarDetailDTO carDetailDTOTmp = carDetailDTO;
									carDetailDTOTmp.setCompleted('N');
									carDetailDTOTmp.setCompleteDate(null);
									carDetailDTOTmp.setCompleteTime(null);
									carDetailDTOTmp.setRemark("ไม่ผ่าน เนื่องจากการดำเนินการจากการบันทึกการสรุปผล ไม่ผ่าน");
									carDetailDTOTmp.setEnable('Y');
									carDetailDTOTmp.setUpdateBy(NullUtils.cvInt(userSessionModel.getUserId()));
									resultProcess = carDetailDAO.updateCarDetail(carDetailDTOTmp);
									if(!resultProcess) {
										break;
									}
								}
							}
							
						}else {
							resultProcess = true;
						}
						
					}
					
										
					
				}
				if(!carDetailModels.isEmpty()) {
					finalAuditResultRequestModel.setCarDetailData(gson.toJson(carDetailModels).trim());
				}else {
					finalAuditResultRequestModel.setCarDetailData("");
				}				
				finalAuditResultRequestModel.getFinalAuditResultStatusId().setFinalAuditResultStatusId(NullUtils.cvStr(ConstFinalAuditResultStatus.WAIT_APPROVE));
				finalAuditResultRequestModel.setApprovalName(gson.toJson(objApproval).trim());
				
				for(CarDTO car : carDTOs) {
					if(car.getCarStatusId().getCarStatusId() == ConstCar.RECEIVE) {
						if(finalAuditResultRequestModel.getPass().equals("Y")) {
							car.getCarStatusId().setCarStatusId(ConstCar.PASS);
						}else if(finalAuditResultRequestModel.getPass().equals("N")){
							car.getCarStatusId().setCarStatusId(ConstCar.NOT_PASS);
						}
						car.setUpdateBy(NullUtils.cvInt(userSessionModel.getUserId()));
						resultProcess = carDAO.updateCar(car);
						if(!resultProcess)
							break;
					}else if(car.getCarStatusId().getCarStatusId() == ConstCar.CANCEL) {
						resultProcess = true;
					}
				}
				
				if(carDTOs == null || carDTOs.isEmpty()) {
					resultProcess = true;
				}
				
				
				
				if(resultProcess) {
					resultProcess = finalAuditResultDAO.updateFinalProcess(TransformModel.transFinalAuditResultModel(finalAuditResultRequestModel));
							
					if(resultProcess) {
						queryWhereClause.setLength(0);
						queryWhereClause = new StringBuilder();
						queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()).append("' ");
						ChecklistPlanDTO checklistPlan = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
						
						List<UserDTO> userDTOs = userDAO.getUserApproverList();
						List<String> reciecverEmail = new ArrayList<>();
						String audit = finalAuditResultRequestModel.getAuditor().replaceAll("\\\\", "");
						
//						String editAudit = audit.substring(2, audit.length()-2);
//						audit = "["+editAudit+"]";
						
						audit = audit.replaceAll("\"","");					
						JSONArray json = new JSONArray(audit);
						audit = json.toString();
						
						AuditorModel[] auditor = gson.fromJson(audit, AuditorModel[].class);
						for(AuditorModel each : auditor) {
							UserDTO users = userDAO.getUserByUserId(each.userId+"");
							reciecverEmail.add(users.getEmail());
						}
						for(UserDTO user : userDTOs) {
							reciecverEmail.add(user.getEmail());
						}
						MailReceiver mailReceiver = new MailReceiver();
						mailReceiver.setMailReceiver(reciecverEmail);
						JsonElement jsonElement = new JsonParser().parse(finalAuditResultRequestModel.getSupplierData());
						JsonObject  jobject = jsonElement.getAsJsonObject();
						//String resultEncoder = EncryptUtils.encoderURL(String.format("checklistPlanId=%s", finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()));
						String resultEncoder = asymmetricCryptography.encryptText(String.format("%s", finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()), asymmetricCryptography.getPrivateKey(PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PRIVATE_KEY_RSA)));
						BodyEmailConfirmFinalAuditResultForApprover bodyEmailConfirmFinalAuditResultForApprover = new BodyEmailConfirmFinalAuditResultForApprover();
						bodyEmailConfirmFinalAuditResultForApprover.setSupplierName(jobject.get("supplierCompany").getAsString());					
						bodyEmailConfirmFinalAuditResultForApprover.setUrlForConfirm(ConfigurationSystemManager.getInstance().getMailFooterUrlWeb()+"/connector_final_audit_result.jsp?checklistPlanId="+resultEncoder);
						bodyEmailConfirmFinalAuditResultForApprover.setEmailAdmin(ConfigurationSystemManager.getInstance().getMailFooterAdminMail());
						bodyEmailConfirmFinalAuditResultForApprover.setTelephoneAdmin(ConfigurationSystemManager.getInstance().getMailFooterTelephone());
						bodyEmailConfirmFinalAuditResultForApprover.setGrade(finalAuditResultRequestModel.getGrade());
						if(finalAuditResultRequestModel.getPass().equals("Y")) {
							bodyEmailConfirmFinalAuditResultForApprover.setPass("ผ่าน");
						}else if(finalAuditResultRequestModel.getPass().equals("N")){
							bodyEmailConfirmFinalAuditResultForApprover.setPass("ไม่ผ่าน");
						}
						Map<String, Integer> mapConclude = gson.fromJson(finalAuditResultRequestModel.getConclude().trim(), new TypeToken<HashMap<String, Integer>>(){}.getType());
						String conclude = "";
						if(mapConclude.get("Critical") > 0) {
							conclude += mapConclude.get("Critical") + " Critical";
						}if(mapConclude.get("Major") > 0) {
							if(!conclude.equals("")) {
								conclude += ", ";
							}
							conclude += mapConclude.get("Major") + " Major";
						}if(mapConclude.get("Minor") > 0) {
							if(!conclude.equals("")) {
								conclude += ", ";
							}
							conclude += mapConclude.get("Minor") + " Minor";
						}if(mapConclude.get("Observe") > 0) {
							if(!conclude.equals("")) {
								conclude += ", ";
							}
							conclude += mapConclude.get("Observe") + " Observe";
						}
						bodyEmailConfirmFinalAuditResultForApprover.setConclude(conclude);
						String auditType = "";
						if(finalAuditResultRequestModel.getAuditType().equals(String.valueOf(ConstAuditType.AUDIT_YEARLY))) {
							auditType = "ตรวจประเมินประจำปี";
						}else if(finalAuditResultRequestModel.getAuditType().equals(String.valueOf(ConstAuditType.AUDIT_NEW_SUPPLIER))) {
							auditType = "ตรวจประเมิน RM/Supplier รายใหม่";
						}else if(finalAuditResultRequestModel.getAuditType().equals(String.valueOf(ConstAuditType.AUDIT_TRACK_PROBLEM))) {
							auditType = "ตรวจติดตามปัญหา";
						}
						String subject = "แจ้ง Apporve ผลการตรวจประเมิน (Audit Result "+auditType+") บริษัท "+jobject.get("supplierCompany").getAsString();
						resultProcess = MailConnector.sendMailToApproveForConfirmFinalAuditResult(mailReceiver, bodyEmailConfirmFinalAuditResultForApprover, subject);	
					}
				}
								
			}else if(NullUtils.cvInt(finalAuditResultRequestModel.getFinalAuditResultStatusId().getFinalAuditResultStatusId()) == ConstFinalAuditResultStatus.WAIT_APPROVE) {
				if(finalAuditResultRequestModel.getPass().equals("Y")) {
					finalAuditResultRequestModel.getFinalAuditResultStatusId().setFinalAuditResultStatusId(NullUtils.cvStr(ConstFinalAuditResultStatus.SUCCESS));
					
					int rowAffect = finalAuditResultDAO.updateAuditType(TransformModel.transFinalAuditResultModel(finalAuditResultRequestModel));
					if(rowAffect == 1) {
						StringBuilder queryWhereClause = new StringBuilder();
						queryWhereClause.setLength(0);
						queryWhereClause = new StringBuilder();
						queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()).append("' ");
						ChecklistPlanDTO checklistPlan = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
						//AuditorModel[] auditor = gson.fromJson(finalAuditResultRequestModel.getAuditor(), AuditorModel[].class);
						
						List<UserDTO> userDTOs = supplierUserMappingDAO.getUserInSupplier(TransformModel.transSupplierModel(finalAuditResultRequestModel.getSupplierId()));
						List<String> reciecverEmail = new ArrayList<>();
						String audit = finalAuditResultRequestModel.getAuditor().replaceAll("\\\\", "");
						
//						String editAudit = audit.substring(2, audit.length()-2);
//						audit = "["+editAudit+"]";
						
						audit = audit.replaceAll("\"","");					
						JSONArray json = new JSONArray(audit);
						audit = json.toString();
						
						AuditorModel[] auditor = gson.fromJson(audit, AuditorModel[].class);
						List<String> Auditor_string = new ArrayList<String>();
						for(AuditorModel each : auditor) {
							UserDTO users = userDAO.getUserByUserId(each.userId+"");
							reciecverEmail.add(users.getEmail());
							Auditor_string.add(each.fullname);
						}
						for(UserDTO user : userDTOs) {
							//reciecverEmail.add(user.getEmail());
						}
						MailReceiver mailReceiver = new MailReceiver();
						mailReceiver.setMailReceiver(reciecverEmail);
						//CC Mail หาผู้บริหารระดับสูงด้วย ดึงข้อมูลจาก System Configuration
						
						JsonElement jsonElement = new JsonParser().parse(finalAuditResultRequestModel.getSupplierData());
						JsonObject  jobject = jsonElement.getAsJsonObject();
						//String resultEncoder = EncryptUtils.encoderURL(String.format("checklistPlanId=%s", finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()));
						String resultEncoder = asymmetricCryptography.encryptText(String.format("%s", finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()), asymmetricCryptography.getPrivateKey(PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PRIVATE_KEY_RSA)));
						BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier = new BodyEmailConfirmFinalAuditResultForSupplier();
						bodyEmailConfirmFinalAuditResultForSupplier.setSupplierName(String.join(",", Auditor_string));	
						bodyEmailConfirmFinalAuditResultForSupplier.setChecklistPlanNo(checklistPlan.getChecklistPlanNo());
						if(finalAuditResultRequestModel.getPass().equals("Y")) {
							bodyEmailConfirmFinalAuditResultForSupplier.setAuditResult("ผ่าน");
						}else {
							bodyEmailConfirmFinalAuditResultForSupplier.setAuditResult("ไม่ผ่าน");
						}
						bodyEmailConfirmFinalAuditResultForSupplier.setUrlForConfirm(ConfigurationSystemManager.getInstance().getMailFooterUrlWeb()+"/connector_final_audit_result.jsp?checklistPlanId="+resultEncoder);
						bodyEmailConfirmFinalAuditResultForSupplier.setEmailAdmin(ConfigurationSystemManager.getInstance().getMailFooterAdminMail());
						bodyEmailConfirmFinalAuditResultForSupplier.setTelephoneAdmin(ConfigurationSystemManager.getInstance().getMailFooterTelephone());
						resultProcess = MailConnector.sendMailToSupplierForAlertFinalAuditResult(mailReceiver, bodyEmailConfirmFinalAuditResultForSupplier, "แจ้งสรุปผลการเข้าตรวจ");		
					}
				}else {
					finalAuditResultRequestModel.getFinalAuditResultStatusId().setFinalAuditResultStatusId(NullUtils.cvStr(ConstFinalAuditResultStatus.SUPPLIER_PROCESS));
					
					int rowAffect = finalAuditResultDAO.updateAuditType(TransformModel.transFinalAuditResultModel(finalAuditResultRequestModel));
					if(rowAffect == 1) {
						StringBuilder queryWhereClause = new StringBuilder();
						queryWhereClause.setLength(0);
						queryWhereClause = new StringBuilder();
						queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()).append("' ");
						ChecklistPlanDTO checklistPlan = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
						//AuditorModel[] auditor = gson.fromJson(objFinalAuditResult, AuditorModel[].class);
						List<UserDTO> userDTOs = supplierUserMappingDAO.getUserInSupplier(TransformModel.transSupplierModel(finalAuditResultRequestModel.getSupplierId()));
						List<String> reciecverEmail = new ArrayList<>();
						String audit = finalAuditResultRequestModel.getAuditor().replaceAll("\\\\", "");
						
//						String editAudit = audit.substring(2, audit.length()-2);
//						audit = "["+editAudit+"]";
						
						audit = audit.replaceAll("\"","");					
						JSONArray json = new JSONArray(audit);
						audit = json.toString();
						
						AuditorModel[] auditor = gson.fromJson(audit, AuditorModel[].class);
						List<String> Auditor_string = new ArrayList<String>();
						for(AuditorModel each : auditor) {
							UserDTO users = userDAO.getUserByUserId(each.userId+"");
							reciecverEmail.add(users.getEmail());
							Auditor_string.add(each.fullname);
						}
						for(UserDTO user : userDTOs) {
							//reciecverEmail.add(user.getEmail());
						}
						MailReceiver mailReceiver = new MailReceiver();
						mailReceiver.setMailReceiver(reciecverEmail);
						//CC Mail หาผู้บริหารระดับสูงด้วย ดึงข้อมูลจาก System Configuration
						
						JsonElement jsonElement = new JsonParser().parse(finalAuditResultRequestModel.getSupplierData());
						JsonObject  jobject = jsonElement.getAsJsonObject();
						//String resultEncoder = EncryptUtils.encoderURL(String.format("checklistPlanId=%s", finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()));
						String resultEncoder = asymmetricCryptography.encryptText(String.format("%s", finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()), asymmetricCryptography.getPrivateKey(PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PRIVATE_KEY_RSA)));
						BodyEmailConfirmEditFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier = new BodyEmailConfirmEditFinalAuditResultForSupplier();
						//bodyEmailConfirmFinalAuditResultForSupplier.setSupplierName(jobject.get("supplierCompany").getAsString());	
						bodyEmailConfirmFinalAuditResultForSupplier.setSupplierName(String.join(",", Auditor_string));	
						bodyEmailConfirmFinalAuditResultForSupplier.setChecklistPlanNo(checklistPlan.getChecklistPlanNo());
						bodyEmailConfirmFinalAuditResultForSupplier.setReason(finalAuditResultRequestModel.getReason());
						if(finalAuditResultRequestModel.getPass().equals("Y")) {
							bodyEmailConfirmFinalAuditResultForSupplier.setAuditResult("ผ่าน");
						}else if(finalAuditResultRequestModel.getPass().equals("N")) {
							bodyEmailConfirmFinalAuditResultForSupplier.setAuditResult("ไม่ผ่าน");
						}else if(finalAuditResultRequestModel.getPass().equals("E")) {
							bodyEmailConfirmFinalAuditResultForSupplier.setAuditResult("แก้ไข");
						}
						bodyEmailConfirmFinalAuditResultForSupplier.setUrlForConfirm(ConfigurationSystemManager.getInstance().getMailFooterUrlWeb()+"/connector_final_audit_result.jsp?checklistPlanId="+resultEncoder);
						bodyEmailConfirmFinalAuditResultForSupplier.setEmailAdmin(ConfigurationSystemManager.getInstance().getMailFooterAdminMail());
						bodyEmailConfirmFinalAuditResultForSupplier.setTelephoneAdmin(ConfigurationSystemManager.getInstance().getMailFooterTelephone());
						resultProcess = MailConnector.sendMailToSupplierForAlertEditFinalAuditResult(mailReceiver, bodyEmailConfirmFinalAuditResultForSupplier, "แจ้งสรุปผลการเข้าตรวจ");		
					}
				}
			}		
			
			if(resultProcess) {
				if(activityLog != "" && detailLog != "") {
					SystemLogModel systemLogModel = new SystemLogModel();
					systemLogModel.setAccess("final_audit_result");
					systemLogModel.setActivity(activityLog);
					systemLogModel.setDetail(detailLog);
					systemLogModel.setCreateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
					systemLogModel.setRefId(NullUtils.cvStr(finalAuditResultRequestModel.getChecklistPlanId().getChecklistPlanId()));
					systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));
				}				
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("FinalAuditResultController.updateFinalAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public String getFinalAuditResultDetail(String objFinalAuditResult) {
		Connection connection = null;
		Gson gson = null;
		FinalAuditResultDAO finalAuditResultDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			finalAuditResultDAO = new FinalAuditResultDAO(connection);
			
			FinalAuditResultModel finalAuditResultModel = gson.fromJson(objFinalAuditResult, FinalAuditResultModel.class);
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND final_result.checklist_plan_id = '").append(finalAuditResultModel.getChecklistPlanId().getChecklistPlanId()).append("' ");
			List<FinalAuditResultDTO> finalAuditResultDTOs = finalAuditResultDAO.getFinalAuditResultList(0, finalAuditResultDAO.countFinalAuditResultList(queryWhereClause.toString()), queryWhereClause.toString());
			List<FinalAuditResultModel> finalAuditResultModels = new ArrayList<>();
			
			for(FinalAuditResultDTO finalAuditResult : finalAuditResultDTOs) {
				finalAuditResultModels.add(TransformDTO.transFinalAuditResultDTO(finalAuditResult));
			}
			
			return gson.toJson(finalAuditResultModels);
		}catch (Exception e) {
			logger.error("FinalAuditResultController.getFinalAuditResultDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	
	public String checkAccessFinalAuditResult(String objCheckAccess) {
		Connection connection = null;
		FinalAuditResultDAO finalAuditResultDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			finalAuditResultDAO = new FinalAuditResultDAO(connection);
			gson = new Gson();
			
			JsonElement jsonElement = new JsonParser().parse(objCheckAccess);
			JsonObject  jobject = jsonElement.getAsJsonObject();
			
			FinalAuditResultDTO finalAuditResultDTO = new FinalAuditResultDTO();
			finalAuditResultDTO.setChecklistPlanId(new ChecklistPlanDTO());
			finalAuditResultDTO.getChecklistPlanId().setChecklistPlanId(NullUtils.cvInt(jobject.get("checklistPlanId").getAsString()));
			
			UserDTO userDTO = new UserDTO();
			userDTO.setUserId(NullUtils.cvInt(jobject.get("userId").getAsString()));
			
			List<FinalAuditResultDTO> finalAuditResultDTOs = finalAuditResultDAO.checkAccessFinalAuditResult(finalAuditResultDTO, userDTO);
			List<FinalAuditResultModel> finalAuditResultModels = new ArrayList<>();
			for(FinalAuditResultDTO finalAuditResult : finalAuditResultDTOs) {
				finalAuditResultModels.add(TransformDTO.transFinalAuditResultDTO(finalAuditResult));
			}
			return gson.toJson(finalAuditResultModels);
		}catch (Exception e) {
			logger.error("FinalAuditResultController.checkAccessFinalAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
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
					evalPlanModel.setTitle(evalPlanDTO.getTitle()+" "+evalPlanModel.getDetail().split(" ")[0].trim());
					flagStop = true;
					evalPlanId = NullUtils.cvStr(evalPlanDTO.getParentId());
				}else {
					evalPlanId = NullUtils.cvStr(evalPlanDTO.getParentId());
				}
			
			}
			
			return evalPlanModel;
			
		}catch (Exception e) {
			logger.error("FinalAuditResultController.getTopicEval() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}				
	}
	
	
	public File printFinalAuditResult(HttpServletRequest httpServletRequest, String finalAuditResultRequestModel) {
		Connection connection = null;
		File fileOutputStreamPdf = null;
		EvalPlanDAO evalPlanDAO = null;
		AuditResultDAO auditResultDAO = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		AsymmetricCryptography asymmetricCryptography = null;
		try {
			
			Gson gson = new Gson();
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			FinalAuditResultDAO finalAuditResultDAO = new FinalAuditResultDAO(connection);
			evalPlanDAO = new EvalPlanDAO(connection);
			auditResultDAO = new AuditResultDAO(connection);
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			StringBuilder queryWhereClause = new StringBuilder();
			CarDetailController carDetailController = new CarDetailController();
			asymmetricCryptography = new AsymmetricCryptography("RSA");
			SessionUtils sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			if(userSessionModel != null && NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_AUDITOR) {
				if(!StringUtils.isNullOrEmpty(finalAuditResultRequestModel.trim())) {
					
					String resultDecode = asymmetricCryptography.decryptText(finalAuditResultRequestModel.trim(), asymmetricCryptography.getPublicKey(PropertiesUtils.getValuePropertiesByKey(ConstSystemProperties.PUBLIC_KEY_RSA)));
					FinalAuditResultModel finalAuditResultModelRequest = gson.fromJson(resultDecode.trim(), FinalAuditResultModel.class);
					
					queryWhereClause.append(" AND ");
					queryWhereClause.append("final_result.checklist_plan_no").append(" ='").append(finalAuditResultModelRequest.getChecklistPlanNo()).append("' ");
					List<FinalAuditResultDTO> finalAuditResultFromDBList = finalAuditResultDAO.getFinalAuditResultList(0, finalAuditResultDAO.countFinalAuditResultList(queryWhereClause.toString()), queryWhereClause.toString());
					
					if(finalAuditResultFromDBList != null && !finalAuditResultFromDBList.isEmpty()) {
						FinalAuditResultDTO finalAuditResultFromDB = finalAuditResultFromDBList.get(0);
						FinalAuditResultModel finalAuditResultModelFromDB = TransformDTO.transFinalAuditResultDTO(finalAuditResultFromDB);
						
						EvalPlanModel evalPlanModelRequest = new EvalPlanModel();
						evalPlanModelRequest.setChecklistPlanId(finalAuditResultModelFromDB.getChecklistPlanId());
						queryWhereClause = new StringBuilder();
						
						queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(evalPlanModelRequest.getChecklistPlanId().getChecklistPlanId()).append("' ");
						List<ChecklistPlanDTO> checklistPlanDTO = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString());
						
						queryWhereClause.setLength(0);
						queryWhereClause = new StringBuilder();
						if(evalPlanModelRequest.getChecklistPlanId() != null) {
							if(!StringUtils.isNullOrEmpty(evalPlanModelRequest.getChecklistPlanId().getChecklistPlanId())) {					
								queryWhereClause.append(" AND eplan.checklist_plan_id = '").append(evalPlanModelRequest.getChecklistPlanId().getChecklistPlanId()).append("' ");
							}
						}
						
						List<EvalPlanDTO> evalPlanDTOs = evalPlanDAO.getEvalPlanList(0, evalPlanDAO.countEvalPlanList(queryWhereClause.toString()), queryWhereClause.toString());
						
						List<EvalPlanModel> evalPlanModels = new ArrayList<>();
						for(EvalPlanDTO evalPlan : evalPlanDTOs) {
							if(evalPlan.getEvalTypeId().getEvalTypeId() == NullUtils.cvInt(StaticVariableUtils.evalTypeQuestion)) {
								if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) != Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN && NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) != Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
									List<AuditResultDTO> auditResultDTOs = new ArrayList<>();
									if(checklistPlanDTO.get(0).getChecklistPlanStatusId().getChecklistPlanStatusId() == ConstChecklistPlanStatus.ACCEPT_RESULT_AUDIT) {
										auditResultDTOs = auditResultDAO.getAuditResultByChecklistPlanAndEvalPlan(evalPlan, " AND ad_result.accepted = 'Y' ");
									}else {
										auditResultDTOs = auditResultDAO.getAuditResultByChecklistPlanAndEvalPlan(evalPlan, "");
									}
									
									evalPlan.setAuditResultId(auditResultDTOs);
								}	
							}else {
								if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) != Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN && NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) != Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
									List<AuditResultDTO> auditResultDTOs = auditResultDAO.getAuditResultByChecklistPlanAndEvalPlan(evalPlan, "");
									evalPlan.setAuditResultId(auditResultDTOs);						
								}
							}
							evalPlanModels.add(TransformDTO.transEvalPlanDTO(evalPlan));
						}
						String pathFileReport = ConfigurationSystemManager.getInstance().getPathFileReport().trim();
//						String pathFileReport = "D:\\Project Gosoft\\FileCpram\\";
						String filenameFinalAuditResult = String.format("FinalAuditResult_%s_%s.pdf", finalAuditResultModelFromDB.getChecklistPlanNo().replaceAll("/", "_"), finalAuditResultModelFromDB.getFinalAuditResultStatusId().getFinalAuditResultStatusId());
						
						//String pathFilenameOfFinalAuditResult = String.format("%s%s","D:\\Users\\thitipapre\\Desktop\\2.2.0V2\\data\\" , filenameFinalAuditResult.trim());
						String pathToPDF = String.format("%s",pathFileReport.trim());
						String pathFilenameOfFinalAuditResult = String.format("%s%s",pathFileReport.trim(), filenameFinalAuditResult.trim());
						
						//if(!new File(pathToPDF.trim()).exists()) {
							
							
							String planDate = finalAuditResultModelFromDB.getPlanDate().split(" ")[0];
							int auditType = NullUtils.cvInt(finalAuditResultModelFromDB.getAuditType().trim());
							String checklistPlanNo = finalAuditResultModelFromDB.getChecklistPlanNo().trim();
							
							SupplierModel supplierModelFromDB = gson.fromJson(finalAuditResultModelFromDB.getSupplierData().trim(), SupplierModel.class);
							
							Map<String, Object> mapLocationSupplier = gson.fromJson(finalAuditResultModelFromDB.getSupplierProductAddressMappingData().trim(), new TypeToken<HashMap<String, Object>>(){}.getType());
							String locationName = NullUtils.cvStr(mapLocationSupplier.get("locationName").toString()).trim();
							//int addressId = NullUtils.cvInt(mapLocationSupplier.get("addressId"));
							String address = NullUtils.cvStr(mapLocationSupplier.get("address").toString()).trim();
							String subDistrict = NullUtils.cvStr(mapLocationSupplier.get("subDistrict").toString()).trim();
							String district = NullUtils.cvStr(mapLocationSupplier.get("district").toString()).trim();
							String province = NullUtils.cvStr(mapLocationSupplier.get("province").toString()).trim();
							String postcode = NullUtils.cvStr(mapLocationSupplier.get("postcode").toString()).trim();
							String locationSupplier = String.format("%s %s ต.%s อ.%s จ.%s %s", locationName, address, subDistrict, district, province, postcode);
							
							
							Map<String, Integer> mapConclude = gson.fromJson(finalAuditResultModelFromDB.getConclude().trim(), new TypeToken<HashMap<String, Integer>>(){}.getType());
							
							Integer critical = mapConclude.get("Critical"), 
									major = mapConclude.get("Major"), 
									minor = mapConclude.get("Minor"), 
									observe = mapConclude.get("Observe");
							
							String grade = NullUtils.cvStr(finalAuditResultModelFromDB.getGrade());
							String auditResult = NullUtils.cvStr(finalAuditResultModelFromDB.getPass());
							
							int acceptDay = NullUtils.cvInt(finalAuditResultModelFromDB.getChecklistPlanId().getNoOfCarAcceptDay().trim());					
							//List<UserDTO> userAuditorList = gson.fromJson(finalAuditResultModelFromDB.getAuditor().trim(), new TypeToken<List<UserDTO>>(){}.getType());
							List<String> userAuditor = gson.fromJson(finalAuditResultModelFromDB.getAuditor().trim(), new TypeToken<List<String>>(){}.getType());
							String userAuditString = "";
							for(int i=0;i<userAuditor.size();i++) {
								if(i>0) {
									userAuditString += ",";
								}
								UserModel userAuditTmp = gson.fromJson(userAuditor.get(i).trim(), UserModel.class);
								userAuditString += userAuditTmp.getFullname().trim();
							}
							UserModel userApprove = gson.fromJson(finalAuditResultModelFromDB.getApprovalName().trim(), UserModel.class);
							
							String dateApproval = "-";
							if(!StringUtils.isNullOrEmpty(finalAuditResultModelFromDB.getCompleteDate().trim())) {
								dateApproval = finalAuditResultModelFromDB.getCompleteDate().trim().split(" ")[0].trim();
							}
							
							List<CarDetailModel> carDetailModel = null;
							if(finalAuditResultModelFromDB.getFinalAuditResultStatusId().getFinalAuditResultStatusId().trim().equals(NullUtils.cvStr(ConstFinalAuditResultStatus.SUCCESS)) || finalAuditResultModelFromDB.getFinalAuditResultStatusId().getFinalAuditResultStatusId().trim().equals(NullUtils.cvStr(ConstFinalAuditResultStatus.CANCEL)) || finalAuditResultModelFromDB.getFinalAuditResultStatusId().getFinalAuditResultStatusId().trim().equals(NullUtils.cvStr(ConstFinalAuditResultStatus.WAIT_APPROVE)) ) {
								String resultCarDetail = finalAuditResultModelFromDB.getCarDetailData().trim();
								carDetailModel = gson.fromJson(resultCarDetail.trim(), new TypeToken<List<CarDetailModel>>(){}.getType());
							}else {
								CarDetailModel carDetailModelRequest = new CarDetailModel();
								carDetailModelRequest.setCarId(new CarModel());
								carDetailModelRequest.getCarId().setCarId(finalAuditResultModelFromDB.getCarId().getCarId().trim());
								String resultCarDetail = carDetailController.getCarDetailList(gson.toJson(carDetailModelRequest),"PRINT");		
								carDetailModel = gson.fromJson(resultCarDetail.trim(), new TypeToken<List<CarDetailModel>>(){}.getType());
							}
							
							PrintFinalAuditResultPdfModel printFinalAuditResultPdfModel = new PrintFinalAuditResultPdfModel();
							printFinalAuditResultPdfModel.setChecklistPlanNo(checklistPlanNo);
							printFinalAuditResultPdfModel.setSupplierName(supplierModelFromDB.getSupplierCompany().trim());
							printFinalAuditResultPdfModel.setPlanDate(planDate.trim());
							printFinalAuditResultPdfModel.setAuditType(NullUtils.cvStr(auditType));
							printFinalAuditResultPdfModel.setSupplierLocation(locationSupplier.trim());
							printFinalAuditResultPdfModel.setCritical(NullUtils.cvStr(critical));
							printFinalAuditResultPdfModel.setMajor(NullUtils.cvStr(major));
							printFinalAuditResultPdfModel.setMinor(NullUtils.cvStr(minor));
							printFinalAuditResultPdfModel.setObserve(NullUtils.cvStr(observe));
							printFinalAuditResultPdfModel.setAuditResult(auditResult);
							printFinalAuditResultPdfModel.setGrade(grade);
							printFinalAuditResultPdfModel.setAcceptDay(NullUtils.cvStr(acceptDay));
							printFinalAuditResultPdfModel.setFax(ConfigurationSystemManager.getInstance().getMailFooterTelephone());
							printFinalAuditResultPdfModel.setEmail(ConfigurationSystemManager.getInstance().getMailFooterAdminMail());
							printFinalAuditResultPdfModel.setAuditor(userAuditString);
							printFinalAuditResultPdfModel.setAuditorList(userAuditor);
							printFinalAuditResultPdfModel.setCompleteDate(dateApproval);
							printFinalAuditResultPdfModel.setSignatureOfSupplier(finalAuditResultModelFromDB.getSignatureOfSupplier().trim());
							printFinalAuditResultPdfModel.setPrintName(userSessionModel.getFullname().trim());
							printFinalAuditResultPdfModel.setPrintDate(DateUtils.getCurrentDate("dd/MM/yyyy HH:mm:ss"));
							printFinalAuditResultPdfModel.setCarDetailModelList(carDetailModel);
							printFinalAuditResultPdfModel.setEvalPlanModelList(evalPlanModels);
							if(userApprove != null) {
								printFinalAuditResultPdfModel.setApprovalName(userApprove.getFullname().trim());
							}else {
								printFinalAuditResultPdfModel.setApprovalName("-");
							}
													
							fileOutputStreamPdf = PdfUtils.createFinalAuditResultPdf(pathFilenameOfFinalAuditResult, printFinalAuditResultPdfModel);
							
							
							
						//}else {
							fileOutputStreamPdf = new File(pathFilenameOfFinalAuditResult.trim());
						//}
						
						
						
						
					}else {
						logger.warn("finalAuditResultFromDB Is Null Or Empty");
					}
					
				}else {
					logger.warn("finalAuditResultRequestModel Is Null Or Empty");
				}
				
			}else {
				logger.error("User Authentication : Is Not Role Auditor Can not Access This File");
			}		
			return fileOutputStreamPdf;
		}catch (Exception e) {
			logger.error("FinalAuditResultController.printFinalAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public DataTableModel<FinalAuditResultModel> datatableGetFinalAuditResultLogList(HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel){
		FinalAuditResultDAO finalAuditResultDAO = null;
		Connection connection = null;		
		SessionUtils sessionUtils = null;
		SystemLogDAO systemLogDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			
			finalAuditResultDAO = new FinalAuditResultDAO(connection);
			
			systemLogDAO = new SystemLogDAO(connection);
			
			DataTableModel<FinalAuditResultModel> dataTableModel = new DataTableModel<>();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);	
			
			
			StringBuilder queryWhereClause = new StringBuilder();
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					 if (col.getName().equals("checklistPlanId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append(" s.ref_id ").append(" = '").append(col.getSearch().getValue()).append("' ");						
					}
				}
			}
			
			queryWhereClause.append(" AND s.access in ('checklist_plan','final_audit_result')");						
			
			int countFinalAuditResult = finalAuditResultDAO.countFinalAuditResultList(queryWhereClause.toString());
			dataTableModel.setData(new ArrayList<>());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countFinalAuditResult);
			dataTableModel.setRecordsTotal(countFinalAuditResult);
			
			List<FinalAuditResultDTO> finalAuditResultDTOs = finalAuditResultDAO.getFinalAuditResultList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			
			for(FinalAuditResultDTO finalAuditResult : finalAuditResultDTOs) {
				dataTableModel.getData().add(TransformDTO.transFinalAuditResultDTO(finalAuditResult));
			}
			return dataTableModel;
		}catch (Exception e) {
			logger.error("FinalAuditResultController.datatableGetFinalAuditResultList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
}
