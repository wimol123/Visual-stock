package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.conts.ConstAppointStatus;
import th.co.gosoft.audit.cpram.dao.AppointDAO;
import th.co.gosoft.audit.cpram.dao.AppointEntourageDAO;
import th.co.gosoft.audit.cpram.dao.AppointHistoryDAO;
import th.co.gosoft.audit.cpram.dao.AppointUserMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dao.UserDAO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.mail.BodyEmailDTO;
import th.co.gosoft.audit.cpram.mail.BodyEmailNewAppointDTO;
import th.co.gosoft.audit.cpram.mail.MailConnector;
import th.co.gosoft.audit.cpram.mail.MailReceiver;
import th.co.gosoft.audit.cpram.model.AppointHistoryModel;
import th.co.gosoft.audit.cpram.model.AppointIdModel;
import th.co.gosoft.audit.cpram.model.AppointModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.APICallerUtils;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;
public class AppointController {
	private final static Logger logger = Logger.getLogger(AppointController.class);
	
	public DataTableModel<AppointModel> datatableGetAppointList(HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel){
		AppointDAO appointDAO = null;
		Connection connection = null;
		SessionUtils sessionUtils = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			DataTableModel<AppointModel> dataTableModel = new DataTableModel<>();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			StringBuilder queryWhereClause = new StringBuilder();
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					if (col.getName().equals("appointDate") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.appoint_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
					else if (col.getName().equals("appointCreateBy") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.appoint_create_by").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("appointCreateBy.fullname") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.fullname").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}					
					else if (col.getName().equals("title") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("detail") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.detail").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("appointStatusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						if(col.getSearch().getValue().trim().equals("processing-group")) {
							queryWhereClause.append("( ");
							queryWhereClause.append("a.appoint_status_id").append(" = '").append(ConstAppointStatus.NEW).append("' ");
							queryWhereClause.append("OR a.appoint_status_id").append(" = '").append(ConstAppointStatus.POSTPONE_APPOINTMENT).append("' ");
							queryWhereClause.append("OR a.appoint_status_id").append(" = '").append(ConstAppointStatus.NEW_APPOINTMENT).append("' ");
							queryWhereClause.append("OR a.appoint_status_id").append(" = '").append(ConstAppointStatus.ACCEPT_APPOINTMENT).append("' ");
							queryWhereClause.append(" ) ");
						}else if(col.getSearch().getValue().trim().equals("finish-group")) {
							queryWhereClause.append("( ");
							queryWhereClause.append("a.appoint_status_id").append(" = '").append(ConstAppointStatus.COMPLETE_APPOINTMENT).append("' ");
							queryWhereClause.append("OR a.appoint_status_id").append(" = '").append(ConstAppointStatus.CANCEL_APPOINTMENT).append("' ");
							queryWhereClause.append(" ) ");
						}else {							
							queryWhereClause.append("a.appoint_status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
						}
						
					}
					else if (col.getName().equals("supplierId.supplierCompany") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("sup.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
				}
			}
			
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
				List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel));
				queryWhereClause.append(" AND ").append("sup.supplier_id = '").append(supplierDTOs.get(0).getSupplierId()).append("' ");
			}
			
			int countAppointId = appointDAO.countAppointList(queryWhereClause.toString());
			dataTableModel.setData(new ArrayList<>());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countAppointId);
			dataTableModel.setRecordsTotal(countAppointId);
			
			List<AppointDTO> appointDTOs = appointDAO.getAppointList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			for(AppointDTO appoint : appointDTOs) {
				dataTableModel.getData().add(TransformDTO.transAppointDTO(appoint));
			}
			
			return dataTableModel;
		}catch(Exception e) {
			logger.error("AppointController.datatableGetAppointList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public Map<String, Boolean> insertAppoint(HttpServletRequest httpServletRequest, String objAppoint) {
		Connection connection = null;
		AppointDAO appointDAO = null;		
		AppointUserMappingDAO appointUserMappingDAO = null;
		AppointEntourageDAO appointEntourageDAO = null;
		AppointHistoryDAO appointHistoryDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		UserDAO userDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = false, checkAppointDate = true;
		Date dateAppoint = null;
		Date dateCurrent = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			appointEntourageDAO = new AppointEntourageDAO(connection);
			appointHistoryDAO = new AppointHistoryDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			userDAO = new UserDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			AppointIdModel appointIdReq = new AppointIdModel();
			Gson gSon = new GsonBuilder().setPrettyPrinting().create();
			APICallerUtils apiCallerUtils = new APICallerUtils();
			
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			AppointModel appointModelRequest = gson.fromJson(objAppoint, AppointModel.class);
			
			if(!StringUtils.isNullOrEmpty(appointModelRequest.getAppointCreateBy().getUserId())) {
				appointModelRequest.setCreateBy(NullUtils.cvStr(userSessionId));
				appointModelRequest.setUpdateBy(NullUtils.cvStr(userSessionId));
				
				SimpleDateFormat formatDate =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
				String dateTimeAppointString = appointModelRequest.getAppointDate();
				String dateTimeCurrentString = DateUtils.getCurrentDateTime("dd/MM/yyyy HH:mm:ss");;
				
				dateAppoint = formatDate.parse(dateTimeAppointString);
				dateCurrent = formatDate.parse(dateTimeCurrentString);
				
				long diff = dateAppoint.getTime() - dateCurrent.getTime();
				
				long diffDays = diff / (24 * 60 * 60 * 1000);

				if(diffDays > 0) {
					int primaryKeyAppoint = appointDAO.insertAppoint(TransformModel.transAppointModel(appointModelRequest));
					System.out.println(primaryKeyAppoint);
					if(primaryKeyAppoint != 0) {
						
						appointModelRequest.setAppointId(NullUtils.cvStr(primaryKeyAppoint));
						for (UserModel auditor : appointModelRequest.getAuditorId()) {
							resultProcess = appointUserMappingDAO.insertAppointUserMapping(TransformModel.transAppointModel(appointModelRequest), TransformModel.transUserModel(auditor));
							if(!resultProcess) {
								resultProcess = false;
								break;
							}
						}
						
						if(resultProcess) {
							if(appointModelRequest.getEntourageId() != null && !appointModelRequest.getEntourageId().isEmpty()) {
								for(UserModel entourage : appointModelRequest.getEntourageId()) {
									resultProcess = appointEntourageDAO.insertAppointEntourage(TransformModel.transAppointModel(appointModelRequest), TransformModel.transUserModel(entourage));
									if(!resultProcess) {
										resultProcess = false;
										break;
									}
								}
							}
							
							if(resultProcess) {
								
								if(appointModelRequest.getAppointHistoryId() != null && !appointModelRequest.getAppointHistoryId().isEmpty()) {
									for(AppointHistoryModel appointHistory : appointModelRequest.getAppointHistoryId()) {
										
										appointHistory.setAppointId(new AppointModel());
										appointHistory.getAppointId().setAppointId(NullUtils.cvStr(primaryKeyAppoint));
										appointHistory.setCreateBy(NullUtils.cvStr(userSessionId));
										appointHistory.setUpdateBy(NullUtils.cvStr(userSessionId));
										int primaryKeyAppointHistory = appointHistoryDAO.insertAppointHistory(TransformModel.transAppointHistoryModel(appointHistory));
										if(primaryKeyAppointHistory != 0) {
											resultProcess = true;									
										}else {
											resultProcess = false;
										}
									}
								}
								
								if(resultProcess) {
									
									List<String> receiver = new ArrayList<>();
									for(UserModel auditList : appointModelRequest.getAuditorId()) {
										StringBuilder queryWhereClause = new StringBuilder();
										queryWhereClause.append(" AND ").append("u.user_id ='").append(auditList.getUserId()).append("' ");
										List<UserDTO> auditDetailList = userDAO.getAllUserList(0, userDAO.countAllUser(queryWhereClause.toString()), queryWhereClause.toString());
										if(auditDetailList != null && !auditDetailList.isEmpty()) {									
											receiver.add(auditDetailList.get(0).getEmail());
										}								
									}
									
									
									if(appointModelRequest.getEntourageId() != null && !appointModelRequest.getEntourageId().isEmpty()) {
										for(UserModel entourage : appointModelRequest.getEntourageId()) {
											StringBuilder queryWhereClause = new StringBuilder();
											queryWhereClause.append(" AND ").append("u.user_id = '").append(entourage.getUserId()).append("' ");
											List<UserDTO> entourageDetailList = userDAO.getAllUserList(0, userDAO.countAllUser(queryWhereClause.toString()), queryWhereClause.toString());
											if(entourageDetailList != null && !entourageDetailList.isEmpty()) {
												receiver.add(entourageDetailList.get(0).getEmail());
											}								
										}
									}
									
									
									StringBuilder queryWhereClause = new StringBuilder();
									queryWhereClause.append(" AND ").append("s.supplier_id = '").append(appointModelRequest.getSupplierId().getSupplierId()).append("' ");
									List<UserDTO> supplierDetailList = supplierUserMappingDAO.getUserContractSupplier(0, supplierUserMappingDAO.countUserContractSupplier(queryWhereClause.toString()), queryWhereClause.toString());
									for(UserDTO supplierDetail : supplierDetailList) {
										receiver.add(supplierDetail.getEmail());
									}
									
									queryWhereClause.setLength(0);
									queryWhereClause = new StringBuilder();
									queryWhereClause.append(" AND ").append("sup_map.id = '").append(appointModelRequest.getLocationId().getId()).append("' ");
									List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierProduceListMappingProductTypeBySupplier(0, supplierProductTypeAddressMappingDAO.countSupplierProduceListMappingProductTypeBySupplier(queryWhereClause.toString()), queryWhereClause.toString());
									
									StringBuilder location = new StringBuilder();
									location.append(supplierProductAddressMappingDTOs.get(0).getLocationName()).append(" ");
									location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getAddress()).append(" ");
									location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getSubDistrictId().getName()).append(" ");
									location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getDistrictId().getName()).append(" ");
									location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getProvinceId().getName()).append(" ");
									location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getPostcode());
									
									BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();
									BodyEmailNewAppointDTO bodyEmailNewAppoint = new BodyEmailNewAppointDTO();
									bodyEmailNewAppoint.setEmailAdmin(bodyEmailDTO.getEmailAdmin());
									bodyEmailNewAppoint.setTelephoneAdmin(bodyEmailDTO.getTelephoneAdmin());
									bodyEmailNewAppoint.setUrl(bodyEmailDTO.getUrl());
									
									bodyEmailNewAppoint.setDateAppoint(appointModelRequest.getAppointDate().split(" ")[0].trim());
									bodyEmailNewAppoint.setTimeAppoint(appointModelRequest.getAppointDate().split(" ")[1].trim());
									//bodyEmailNewAppoint.setDetail(String.format("%s %s", appointModelRequest.getTitle(),"[รายการใหม่]"));
									bodyEmailNewAppoint.setDetail(String.format("ตรวจประเมิน %s %s %s", appointModelRequest.getTitle(), appointModelRequest.getSupplierId().getSupplierCompany(),"[รายการใหม่]"));
									bodyEmailNewAppoint.setLocation(location.toString());
									bodyEmailNewAppoint.setSupplierName(appointModelRequest.getSupplierId().getSupplierCompany());
									
									MailReceiver mailReceiver = new MailReceiver();
									mailReceiver.setMailReceiver(receiver);
									
									
//									resultProcess = MailConnector.sendMailNewAppoint(mailReceiver, bodyEmailNewAppoint, String.format("ตรวจประเมิน %s %s %s", appointModelRequest.getTitle(), appointModelRequest.getSupplierId().getSupplierCompany(),"[รายการใหม่]"));
//									resultProcess = MailConnector.sendMailNewAppoint(mailReceiver, bodyEmailNewAppoint, String.format("%s %s", appointModelRequest.getTitle(),"[รายการใหม่]"))
									
									appointIdReq.setAppointId(primaryKeyAppoint);;
															
									if(!resultProcess) {
										resultProcess = false;
									}
								}
							}					
						}else {
							resultProcess = false;
						}				
					}else {
						resultProcess = false;
					}
				}else {
					checkAppointDate = false;
				}
				
				if(resultProcess && checkAppointDate) {
					DatabaseUtils.commit(connection);
					
					// Call Runjob API					
					String strJson = gSon.toJson(appointIdReq);
					
					// PROD
					String jsonRes = APICallerUtils.generateCheckListPlan(strJson, "https://app.cpram.co.th/auditsupplierscheduler/api/run_appoint/run_job");
					
					// DEV
//					 String jsonRes = APICallerUtils.generateCheckListPlan(strJson, "http://10.182.236.158/auditsupplierscheduler/api/run_appoint/run_job");
					
					// LOCAL
					// String jsonRes = APICallerUtils.generateCheckListPlan(strJson, "http://localhost:8081/auditsupplierscheduler/api/run_appoint/run_job");
					
					System.out.println("#### API RUN JOB SCHEDULE BEGIN ####");
					System.out.println("SEND");
					System.out.println(strJson);
					System.out.println("RESPONSE");
					System.out.println(jsonRes);
					System.out.println("#### API RUN JOB SCHEDULE END ####");
				}else {
					DatabaseUtils.rollback(connection);
				}
			}else {
				logger.error("Cannot Load Data Of ApprointCreateBy (UserId)");
			}
			
			Map<String, Boolean> result = new HashMap<>();
			result.put("resultProcess", resultProcess);
			result.put("checkAppointDate", checkAppointDate);
			return result;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("AppointController.insertAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public Map<String, Boolean> updateAppoint(HttpServletRequest httpServletRequest, String objAppoint) {
		Connection connection = null;
		AppointDAO appointDAO = null;
		AppointUserMappingDAO appointUserMappingDAO = null;
		AppointEntourageDAO appointEntourageDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		UserDAO userDAO = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		AppointHistoryDAO appointHistoryDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		Date dateAppoint = null;
		Date dateCurrent = null;
		boolean resultProcess = false, checkAppointDate = true;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			appointEntourageDAO = new AppointEntourageDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			userDAO = new UserDAO(connection);
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			appointHistoryDAO = new AppointHistoryDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			AppointModel appointModelRequest = gson.fromJson(objAppoint, AppointModel.class);
			appointModelRequest.setUpdateBy(NullUtils.cvStr(userSessionId));
			appointModelRequest.getAppointHistoryId().get(0).setUpdateBy(NullUtils.cvStr(userSessionId));
			
			SimpleDateFormat formatDate =  new SimpleDateFormat("dd/MM/yyyy HH:mm:ss"); 
			String dateTimeAppointString = appointModelRequest.getAppointDate();
			String dateTimeCurrentString = DateUtils.getCurrentDateTime("dd/MM/yyyy HH:mm:ss");;
			
			dateAppoint = formatDate.parse(dateTimeAppointString);
			dateCurrent = formatDate.parse(dateTimeCurrentString);
			
			long diff = dateAppoint.getTime() - dateCurrent.getTime();
			
			long diffDays = diff / (24 * 60 * 60 * 1000);
			
			if((NullUtils.cvInt(appointModelRequest.getAppointStatusId().getAppointStatusId()) == ConstAppointStatus.NEW || NullUtils.cvInt(appointModelRequest.getAppointStatusId().getAppointStatusId()) == ConstAppointStatus.NEW_APPOINTMENT )) {
				if(diffDays <= 0)
					checkAppointDate = false;
			}
			
			if(checkAppointDate) {
				resultProcess = appointDAO.updateAppoint(TransformModel.transAppointModel(appointModelRequest));
				if(resultProcess) {
					resultProcess = appointUserMappingDAO.deleteAppointUserMappingWithAppoint(TransformModel.transAppointModel(appointModelRequest));
					
					if(resultProcess) {
						
						for(UserModel audit : appointModelRequest.getAuditorId()) {
							resultProcess = appointUserMappingDAO.insertAppointUserMapping(TransformModel.transAppointModel(appointModelRequest), TransformModel.transUserModel(audit));
							if(!resultProcess) {
								resultProcess = false;
								break;
							}
						}
						
						if(resultProcess) {
							resultProcess = appointEntourageDAO.deleteAppointEntourageWithAppoint(TransformModel.transAppointModel(appointModelRequest));
							if(resultProcess) {
								
								for(UserModel entourage : appointModelRequest.getEntourageId()) {
									resultProcess = appointEntourageDAO.insertAppointEntourage(TransformModel.transAppointModel(appointModelRequest), TransformModel.transUserModel(entourage));
									if(!resultProcess) {
										resultProcess = false;
										break;
									}
								}
								
								if(resultProcess) {
									if(appointModelRequest.getAppointHistoryId() != null && !appointModelRequest.getAppointHistoryId().isEmpty()) {
										for(AppointHistoryModel appointHistory : appointModelRequest.getAppointHistoryId()) {
											appointHistory.setAppointId(new AppointModel());
											appointHistory.getAppointId().setAppointId(NullUtils.cvStr(appointModelRequest.getAppointId()));
											appointHistory.setCreateBy(NullUtils.cvStr(userSessionId));
											appointHistory.setUpdateBy(NullUtils.cvStr(userSessionId));
											int primaryKeyAppointHistory = appointHistoryDAO.insertAppointHistory(TransformModel.transAppointHistoryModel(appointHistory));
											if(primaryKeyAppointHistory != 0) {
												resultProcess = true;									
											}else {
												resultProcess = false;
											}
										}
									}
								}
								
							}else {
								resultProcess = false;
							}
							
						}else {
							resultProcess = false;
						}
						
					}else {
						resultProcess = false;
					}
				}
				
				if(resultProcess) {
					StringBuilder queryWhereClause = new StringBuilder();
					String statusAppointName = "";
					queryWhereClause.append(" AND ").append("a.appoint_id = '").append(appointModelRequest.getAppointId()).append("' ");
					List<AppointDTO> appointDTOs = appointDAO.getAppointList(0, appointDAO.countAppointList(queryWhereClause.toString()), queryWhereClause.toString());
					for(AppointDTO appoint : appointDTOs) {
						statusAppointName = appoint.getAppointStatusId().getAppointStatusName();
					}
					
					
					List<String> receiver = new ArrayList<>();
					for(UserModel auditList : appointModelRequest.getAuditorId()) {
						queryWhereClause.setLength(0);
						queryWhereClause = new StringBuilder();
						queryWhereClause.append(" AND ").append("u.user_id ='").append(auditList.getUserId()).append("' ");
						List<UserDTO> auditDetailList = userDAO.getAllUserList(0, userDAO.countAllUser(queryWhereClause.toString()), queryWhereClause.toString());
						if(auditDetailList != null && !auditDetailList.isEmpty()) {									
							receiver.add(auditDetailList.get(0).getEmail());
						}								
					}
					
					if(appointModelRequest.getEntourageId() != null && !appointModelRequest.getEntourageId().isEmpty()) {
						for(UserModel entourage : appointModelRequest.getEntourageId()) {
							queryWhereClause.setLength(0);
							queryWhereClause = new StringBuilder();
							queryWhereClause.append(" AND ").append("u.user_id = '").append(entourage.getUserId()).append("' ");
							List<UserDTO> entourageDetailList = userDAO.getAllUserList(0, userDAO.countAllUser(queryWhereClause.toString()), queryWhereClause.toString());
							if(entourageDetailList != null && !entourageDetailList.isEmpty()) {
								receiver.add(entourageDetailList.get(0).getEmail());
							}								
						}
					}
					
					queryWhereClause.setLength(0);
					queryWhereClause = new StringBuilder();
					queryWhereClause.append(" AND ").append("s.supplier_id = '").append(appointModelRequest.getSupplierId().getSupplierId()).append("' ");
					List<UserDTO> supplierDetailList = supplierUserMappingDAO.getUserContractSupplier(0, supplierUserMappingDAO.countUserContractSupplier(queryWhereClause.toString()), queryWhereClause.toString());
					for(UserDTO supplierDetail : supplierDetailList) {					
						receiver.add(supplierDetail.getEmail());
					}
					
					queryWhereClause.setLength(0);
					queryWhereClause = new StringBuilder();
					queryWhereClause.append(" AND ").append("sup_map.id = '").append(appointModelRequest.getLocationId().getId()).append("' ");
					List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierProduceListMappingProductTypeBySupplier(0, supplierProductTypeAddressMappingDAO.countSupplierProduceListMappingProductTypeBySupplier(queryWhereClause.toString()), queryWhereClause.toString());
					
					StringBuilder location = new StringBuilder();
					location.append(supplierProductAddressMappingDTOs.get(0).getLocationName()).append(" ");
					location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getAddress()).append(" ");
					location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getSubDistrictId().getName()).append(" ");
					location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getDistrictId().getName()).append(" ");
					location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getProvinceId().getName()).append(" ");
					location.append(supplierProductAddressMappingDTOs.get(0).getAddressId().getPostcode());
															
					BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();
					BodyEmailNewAppointDTO bodyEmailNewAppoint = new BodyEmailNewAppointDTO();
					bodyEmailNewAppoint.setEmailAdmin(bodyEmailDTO.getEmailAdmin());
					bodyEmailNewAppoint.setTelephoneAdmin(bodyEmailDTO.getTelephoneAdmin());
					bodyEmailNewAppoint.setUrl(bodyEmailDTO.getUrl());				
					bodyEmailNewAppoint.setDateAppoint(appointModelRequest.getAppointDate().split(" ")[0].trim());
					bodyEmailNewAppoint.setTimeAppoint(appointModelRequest.getAppointDate().split(" ")[1].trim());
					
					bodyEmailNewAppoint.setLocation(location.toString());
					bodyEmailNewAppoint.setSupplierName(appointModelRequest.getSupplierId().getSupplierCompany());
					
					if(appointModelRequest.getAppointStatusId().getAppointStatusId().equals("2")) {
						JsonElement jsonElement = new JsonParser().parse(appointModelRequest.getAppointHistoryId().get(0).getDetail());
						JsonObject  jobject = jsonElement.getAsJsonObject();
						bodyEmailNewAppoint.setDetail(jobject.get("Detail").getAsString());
					}else {
						bodyEmailNewAppoint.setDetail(appointModelRequest.getDetail());						
						//bodyEmailNewAppoint.setDetail(String.format("%s [%s]", appointModelRequest.getTitle(),statusAppointName));
					}
					
					MailReceiver mailReceiver = new MailReceiver();
					mailReceiver.setMailReceiver(receiver);
					
					UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
					/*if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_USER) {
						
						if(appointModelRequest.getAppointStatusId().getAppointStatusId().equals("2")) {
							resultProcess = MailConnector.sendMailChangeAppointForSupplier(mailReceiver, bodyEmailNewAppoint, String.format("%s [%s]", appointModelRequest.getTitle(),statusAppointName));
						}else if(appointModelRequest.getAppointStatusId().getAppointStatusId().equals("4") || appointModelRequest.getAppointStatusId().getAppointStatusId().equals("5")){
							resultProcess = MailConnector.sendMailChangeAppointForSupplier(mailReceiver, bodyEmailNewAppoint, String.format("%s [%s]", appointModelRequest.getTitle(),statusAppointName));
						}
					}else {
						resultProcess = MailConnector.sendMailNewAppoint(mailReceiver, bodyEmailNewAppoint, String.format("ตรวจประเมิน %s %s [%s]", appointModelRequest.getTitle(), appointModelRequest.getSupplierId().getSupplierCompany(), statusAppointName));
						//resultProcess = MailConnector.sendMailNewAppoint(mailReceiver, bodyEmailNewAppoint, String.format("%s [%s]", appointModelRequest.getTitle(),statusAppointName));
					}*/
					
					if(!resultProcess) {
						resultProcess = false;
					}
					
				}else {
					resultProcess = false;
				}
			}
			
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			Map<String, Boolean> result = new HashMap<>();
			result.put("resultProcess", resultProcess);
			result.put("checkAppointDate", checkAppointDate);
			
			return result;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("AppointController.updateAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {			
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean updateAppointAfterFinal(int appointId) {
		Connection connection = null;
		AppointDAO appointDAO = null;
		boolean result = true;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			result = appointDAO.updateAppointAfterFinalResult(appointId);
			if(result) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
		} catch (Exception e) {
			result = false;
			DatabaseUtils.rollback(connection);
			logger.error("AppointController.updateAppointAfterFinal() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
		return result;
	}
	
	public String getAppointDetail(HttpServletRequest httpServletRequest, String objAppoint) {
		Connection connection = null;
		AppointDAO appointDAO = null;
		AppointUserMappingDAO appointUserMappingDAO = null;
		AppointEntourageDAO appointEntourageDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			appointEntourageDAO = new AppointEntourageDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			StringBuilder queryWhereClause = new StringBuilder();
			
			if(!StringUtils.isNullOrEmpty(objAppoint) && objAppoint != "") {
				
				AppointModel appointModelRequest = gson.fromJson(objAppoint, AppointModel.class);
				
				if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointDate())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.appoint_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(appointModelRequest.getAppointDate())).append("%'  ");
				}
				else if(!StringUtils.isNullOrEmpty(appointModelRequest.getAppointId())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.appoint_id").append(" = ").append(" '").append((appointModelRequest.getAppointId())).append("'  ");
				}
				else if(appointModelRequest.getAppointCreateBy() != null) {
					if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointCreateBy().getUserId())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.appoint_create_by").append(" = '").append(appointModelRequest.getAppointCreateBy().getUserId()).append("' ");
					}else if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointCreateBy().getFullname())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.fullname").append(" like ").append(" '%").append(appointModelRequest.getAppointCreateBy().getFullname()).append("%'  ");
					}	
				}				
								
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getTitle())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.title").append(" like ").append(" '%").append(appointModelRequest.getTitle()).append("%'  ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getDetail())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.detail").append(" like ").append(" '%").append(appointModelRequest.getDetail()).append("%'  ");
				}
				else if (appointModelRequest.getAppointStatusId() != null) {
					if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointStatusId().getAppointStatusId())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.appoint_status_id").append(" = '").append(appointModelRequest.getAppointStatusId().getAppointStatusId()).append("' ");
					}
				}
				else if (appointModelRequest.getSupplierId() != null) {
					if (!StringUtils.isNullOrEmpty(appointModelRequest.getSupplierId().getSupplierCompany())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("sup.supplier_company").append(" like ").append(" '%").append(appointModelRequest.getSupplierId().getSupplierCompany()).append("%'  ");
					}	
					else if (!StringUtils.isNullOrEmpty(appointModelRequest.getSupplierId().getSupplierId())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.supplier_id").append(" = ").append(" '").append(appointModelRequest.getSupplierId().getSupplierId()).append("'  ");
					}
				}
			}
			
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
				List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel));
				queryWhereClause.append(" AND ").append("a.supplier_id = '").append(supplierDTOs.get(0).getSupplierId()).append("' ");
			}
			
			List<AppointDTO> appointDTOs = appointDAO.getAppointList(0, appointDAO.countAppointList(queryWhereClause.toString()), queryWhereClause.toString());
			List<AppointModel> appointModels = new ArrayList<>();
			for(AppointDTO appoint : appointDTOs) {
				
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND ").append("app.appoint_id = '").append(appoint.getAppointId()).append("' ");
				List<AppointDTO> auditorList = appointUserMappingDAO.getAppointUserMappingList(0, appointUserMappingDAO.countAppointUserMappingList(queryWhereClause.toString()), queryWhereClause.toString());
				appoint.setAuditorId(new ArrayList<>());
				
				if(auditorList != null && !auditorList.isEmpty()) {
					for(AppointDTO auditor : auditorList) {
						appoint.getAuditorId().add(auditor.getAuditorId().get(0));
					}
				}
				
				appoint.setEntourageId(new ArrayList<>());
				List<AppointDTO> entourageList = appointEntourageDAO.getAppointEntourageList(0, appointEntourageDAO.countAppointEntourageList(queryWhereClause.toString()), queryWhereClause.toString());
				if(entourageList != null && !entourageList.isEmpty()) {
					for(AppointDTO entourage : entourageList) {
						//appoint.getEntourageId().add(entourage.getEntourageId().get(0));
					}
				}
				
				appointModels.add(TransformDTO.transAppointDTO(appoint));
			}
			
			return gson.toJson(appointModels);
		}catch(Exception e) {
			logger.error("AppointController.getAppointDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean deleteAppoint(String objAppoint) {
		Connection connection = null;
		AppointDAO appointDAO = null;
		AppointEntourageDAO appointEntourageDAO = null;
		AppointUserMappingDAO appointUserMappingDAO = null;
		AppointHistoryDAO appointHistoryDAO = null;
		Gson gson = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointEntourageDAO = new AppointEntourageDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			appointHistoryDAO = new AppointHistoryDAO(connection);
			gson = new Gson();
			
			AppointModel appointModelRequest = gson.fromJson(objAppoint, AppointModel.class);
			resultProcess = appointEntourageDAO.deleteAppointEntourageWithAppoint(TransformModel.transAppointModel(appointModelRequest));
			if(resultProcess) {
				resultProcess = appointUserMappingDAO.deleteAppointUserMappingWithAppoint(TransformModel.transAppointModel(appointModelRequest));
				if(resultProcess) {
					
					resultProcess = appointHistoryDAO.deleteAppointHistoryByAppoint(TransformModel.transAppointModel(appointModelRequest));
					if(resultProcess) {
						resultProcess = appointDAO.deleteAppoint(TransformModel.transAppointModel(appointModelRequest));
					}else {
						resultProcess = false;
					}					
				}else {
					resultProcess = false;
				}
			}
			
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("AppointController.deleteAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
		  sb.append((char) cp);
		}
		return sb.toString();
	}
	
	/*public DataTableModel<AppointModel> getAppointList(DataTablePostParamModel dataTablePostParamModel) {
		AppointDAO appointDAO = null;
		AppointUserMappingDAO appointUserMappingDAO = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			DataTableModel<AppointModel> dataTableModel = new DataTableModel<>();
			dataTableModel.setData(new ArrayList<>());
			StringBuilder queryWhereClause = new StringBuilder();
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("appointDate") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.appoint_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
					else if (col.getName().equals("appointCreateBy") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.appoint_create_by").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("appointCreateBy.fullname") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.fullname").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}					
					else if (col.getName().equals("title") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("detail") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.detail").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("statusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("a.status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("supplierId.supplierCompany") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("s.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("appointStatusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("aps.appoint_status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					
				}
			}
			
			
			List<AppointDTO> appointList = appointDAO.getAppointList(dataTablePostParamModel.getStart(),dataTablePostParamModel.getLength(),queryWhereClause.toString());
			
			
			for(AppointDTO appoint : appointList) {
				//dataTableModel.getData().add(transformDTO(appoint));
				List<UserDTO> auditList = appointUserMappingDAO.getUserListMappingAppoint(appoint);
				appoint.setUser(new ArrayList<>());
				for(UserDTO audit : auditList) {
					appoint.getUser().add(audit);
				}
				dataTableModel.getData().add(TransformDTO.transAppointDTO(appoint));
			}
			int countRecords = appointDAO.countAppointList(queryWhereClause.toString());
			dataTableModel.setRecordsFiltered(countRecords);
			dataTableModel.setRecordsTotal(countRecords);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			return dataTableModel;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(appointDAO != null)
				appointDAO.closeConnection();
		}
	}
	
	
	public List<AppointModel> getAppointList() {
		AppointDAO appointDAO = null;
		AppointUserMappingDAO appointUserMappingDAO = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			List<AppointDTO> appointDTOs = appointDAO.getAppointList();
			List<AppointModel> appointModels = new ArrayList<>();
			
			for(AppointDTO appointDTO : appointDTOs) {
				//AppointModel appointModel = transformDTO(appointDTO);
				List<UserDTO> userDTOs = appointUserMappingDAO.getUserListMappingAppoint(appointDTO);
				appointDTO.setUser(new ArrayList<>());
				for(UserDTO user : userDTOs) {
					appointDTO.getUser().add(user);
				}
				AppointModel appointModel = TransformDTO.transAppointDTO(appointDTO);
				appointModel.setAppointDate(String.format("%s %s", NullUtils.cvStr(appointDTO.getAppointDate()),NullUtils.cvStr(appointDTO.getAppointTime())));
				appointModels.add(appointModel);
			}
			
			return appointModels;			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(appointDAO != null)
				appointDAO.closeConnection();
			if(appointUserMappingDAO != null)
				appointUserMappingDAO.closeConnection();
		}
	}
	
	
	public AppointModel getAppointHistory(String appointId) {
		AppointDAO appointDAO = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			AppointDTO appointDTO = appointDAO.getAppointHistory(appointId);
			//AppointModel appointModel = transformDTO(appointDTO);
			AppointModel appointModel = TransformDTO.transAppointDTO(appointDTO);
			return appointModel;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(appointDAO != null)
				appointDAO.closeConnection();
		}
	}
	
	
	public boolean changeAppointStatus(String appointObject) {
		AppointDAO appointDAO = null;
		AppointHistoryDAO appointHistoryDAO = null;
		Gson _gson = null;
		boolean statusProcess = false;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointHistoryDAO = new AppointHistoryDAO(connection);
			_gson = new Gson();
			AppointModel appointModelJson = _gson.fromJson(appointObject, AppointModel.class);
			AppointDTO appointDTO = TransformModel.transAppointModel(appointModelJson);
			statusProcess = appointDAO.changeAppointStatus(appointDTO);
			if(statusProcess) {
				AppointHistoryDTO appointHistoryDTO = new AppointHistoryDTO();
				AppointStatusDTO appointStatusId = new AppointStatusDTO();
				appointStatusId.setAppointStatusId(appointDTO.getAppointStatusId().getAppointStatusId());
				
				appointHistoryDTO.setAppointId(appointDTO.getAppointId());				
				appointHistoryDTO.setAppointStatusId(appointStatusId);
				appointHistoryDTO.setTitle(appointDTO.getTitle());
				appointHistoryDTO.setDetail(appointDTO.getDetail());
				appointHistoryDTO.setStatusId(appointDTO.getStatusId());
				appointHistoryDTO.setCreateBy(appointDTO.getUpdateBy());
				appointHistoryDTO.setUpdateBy(appointDTO.getUpdateBy());
				statusProcess = appointHistoryDAO.insert(appointHistoryDTO);
				if(!statusProcess)
					DatabaseUtils.rollback(connection);
				
			}else {
				DatabaseUtils.rollback(connection);
			}
			return statusProcess;
			
			String dateTemp[] = appointModelJson.getAppointDate().split(" ");
			dateTemp[0] = DateUtils.formatWebDateToSQLDateString(NullUtils.cvStr(dateTemp[0]));
			appointModelJson.setAppointDate(dateTemp[0]+" "+dateTemp[1]);
			
			//boolean statusProcess = appointDAO.changeAppointStatus(appointModelJson);
			
			
		}catch(Exception e) {			
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(appointDAO != null)
				appointDAO.closeConnection();
		}
	}
	
	
	public boolean insertAppoint(HttpServletRequest httpServletRequest, String objAppoint) {
		AppointDAO appointDAO = null;
		Gson _gson = null;
		boolean insertStatus = false;
		AppointUserMappingDAO appointUserMappingDAO = null;
		AppointHistoryDAO appointHistoryDAO = null;
		SessionUtils sessionUtils = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			appointHistoryDAO = new AppointHistoryDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			_gson = new Gson();
			
			int userSessionId = sessionUtils.getUserIdSession("sesionAuthen");
			AppointModel appointModel = _gson.fromJson(objAppoint, AppointModel.class);	
			appointModel.setUpdateBy(NullUtils.cvStr(userSessionId));
			appointModel.setCreateBy(NullUtils.cvStr(userSessionId));
			appointModel.setAppointTypeId(NullUtils.cvStr(1));			
			AppointStatusModel appointStatusModel = new AppointStatusModel();
			appointStatusModel.setAppointStatusId(NullUtils.cvStr(1));
			appointModel.setAppointStatusId(appointStatusModel);
			
			
			String dateTemp[] = appointModel.getAppointDate().split(" ");
			//dateTemp[0] = DateUtils.formatWebDateToSQLDateString(NullUtils.cvStr(dateTemp[0]));			
			appointModel.setAppointDate(dateTemp[0]+" "+dateTemp[1]);
			AppointDTO appointDTO = TransformModel.transAppointModel(appointModel);
			int appointId = appointDAO.insertAppoint(appointDTO);
			if(appointId > 0) {			
				appointDTO.setAppointId(appointId);
				if(appointDTO.getUser() != null) {
					if(!appointDTO.getUser().isEmpty()) {
						for(UserDTO audit : appointDTO.getUser()) {
							insertStatus = appointUserMappingDAO.insert(appointDTO, audit);
							if(!insertStatus)
								break;
						}
						
						if(insertStatus) {
							
							AppointHistoryDTO appointHistoryDTO = new AppointHistoryDTO();
							appointHistoryDTO.setAppointId(appointId);
							AppointStatusDTO appointStatusDTO = new AppointStatusDTO();
							appointStatusDTO.setAppointStatusId(appointDTO.getAppointStatusId().getAppointStatusId());
							appointHistoryDTO.setAppointStatusId(appointStatusDTO);
							appointHistoryDTO.setCreateBy(userSessionId);
							appointHistoryDTO.setDetail(appointDTO.getDetail());
							appointHistoryDTO.setStatusId(appointDTO.getStatusId());
							appointHistoryDTO.setTitle(appointDTO.getTitle());
							appointHistoryDTO.setUpdateBy(userSessionId);
							
							insertStatus = appointHistoryDAO.insert(appointHistoryDTO);
							if(!insertStatus)
								DatabaseUtils.rollback(connection);
							
						}else {
							DatabaseUtils.rollback(connection);
						}						
					}
				}
			}else {
				insertStatus = 	false;
				DatabaseUtils.rollback(connection);
			}
			
			return insertStatus;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(appointDAO != null)
				appointDAO.closeConnection();
		}
		
	}
	
	public void getAppointDateAll() {
		AppointDAO appointDAO = null;
		try {
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			
			List<AppointDTO> appointDTOList = appointDAO.getAppointDate();
			List<AppointModel> appointModels = new ArrayList<>();
			
			for(AppointDTO appointDTO : appointDTOList) {
				
				AppointModel appointModel = new AppointModel();
				appointModel.setAppointId(NullUtils.cvStr(appointDTO.getAppointId()));
				appointModel.setAppointDate(NullUtils.cvStr(appointDTO.getAppointDate()+" "+appointDTO.getAppointTime()));
				
				SupplierModel supplierModel = new SupplierModel();
				supplierModel.setSupplierId(NullUtils.cvStr(appointDTO.getSupplierId().getSupplierId()));
				supplierModel.setSupplierCompany(NullUtils.cvStr(appointDTO.getSupplierId().getSupplierCompany()));
				appointModel.setSupplierId(supplierModel);
				
				appointModels.add(appointModel);
			}
			
			return appointModels;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(appointDAO != null)
				appointDAO.closeConnection();
		}
	}
	
	
	public boolean deleteAppointByAppointId(String objAppoint) {
		AppointDAO appointDAO = null;	
		AppointHistoryDAO appointHistoryDAO = null;
		AppointUserMappingDAO appointUserMappingDAO = null;
		boolean statusProcess = false;
		
		Gson gson = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointHistoryDAO = new AppointHistoryDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			gson = new Gson();
			AppointHistoryModel appointHistoryModel = new AppointHistoryModel();
			
			AppointModel appointModel = gson.fromJson(objAppoint, AppointModel.class);
			appointHistoryModel.setAppointId(appointModel.getAppointId().trim());
			statusProcess = appointHistoryDAO.deleteByAppointId(TransformModel.transAppointHistoryModel(appointHistoryModel));
			if(statusProcess) {
				
				statusProcess = appointUserMappingDAO.deleteByAppointId(TransformModel.transAppointModel(appointModel));
				if(statusProcess) {
					statusProcess = appointDAO.deleteAppointByAppointId(TransformModel.transAppointModel(appointModel));
				}else {
					DatabaseUtils.rollback(connection);
				}
				
			}else {
				DatabaseUtils.rollback(connection);
			}
			//statusProcess = appointDAO.deleteAppointByAppointId(objAppoint);
			return statusProcess;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(appointDAO != null)
				appointDAO.closeConnection();
		}
	}
	
	
	public boolean appointDetailProcess(String appointObjString) {
		AppointDAO appointDAO = null;
		AppointUserMappingDAO appointUserMappingDAO = null;
		AppointHistoryDAO appointHistoryDAO = null;
		Gson _gson = null;
		boolean statusProcess = false;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointDAO = new AppointDAO(connection);
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			appointHistoryDAO = new AppointHistoryDAO(connection);
			_gson = new Gson();
			AppointModel appointJsonModel = _gson.fromJson(appointObjString, AppointModel.class);	
			AppointDTO appointDTO = TransformModel.transAppointModel(appointJsonModel);
						
			if(appointDTO.getAppointStatusId().getAppointStatusId() == 1 || appointDTO.getAppointStatusId().getAppointStatusId() == 3) {
				statusProcess = appointUserMappingDAO.deleteByAppointId(appointDTO);
				if(statusProcess) {
					for(UserDTO audit : appointDTO.getUser()) {					
						statusProcess = appointUserMappingDAO.insert(appointDTO, audit);
						if(!statusProcess) {
							DatabaseUtils.rollback(connection);
							break;
						}
					}
					if(statusProcess) {
						statusProcess = appointDAO.updateAppoint(appointDTO);
						if(statusProcess) {
							AppointHistoryDTO appointHistoryDTO = new AppointHistoryDTO();
							AppointStatusDTO appointStatusDTO = new AppointStatusDTO();
							appointHistoryDTO.setAppointId(appointDTO.getAppointId());
							appointStatusDTO.setAppointStatusId(appointDTO.getAppointStatusId().getAppointStatusId());
							appointHistoryDTO.setAppointStatusId(appointStatusDTO);
							appointHistoryDTO.setTitle(appointDTO.getTitle());
							appointHistoryDTO.setDetail(appointDTO.getDetail());
							appointHistoryDTO.setStatusId(appointDTO.getStatusId());
							appointHistoryDTO.setCreateBy(appointDTO.getUpdateBy());
							appointHistoryDTO.setUpdateBy(appointDTO.getUpdateBy());
							statusProcess = appointHistoryDAO.insert(appointHistoryDTO);
							if(!statusProcess)
								DatabaseUtils.rollback(connection);
						}else {
							DatabaseUtils.rollback(connection);
						}
					}
				}else {
					DatabaseUtils.rollback(connection);
				}
				
			}else if(appointDTO.getAppointStatusId().getAppointStatusId() == 2) {
				statusProcess = appointUserMappingDAO.deleteByAppointId(appointDTO);
				if(statusProcess) {
					for(UserDTO audit : appointDTO.getUser()) {
						statusProcess = appointUserMappingDAO.insert(appointDTO, audit);
						if(!statusProcess) {
							DatabaseUtils.rollback(connection);
							break;
						}
					}
					if(statusProcess) {
						AppointDTO appointDTOAppointDate = appointDAO.getAppointDateByAppointId(appointDTO);
						String dateTimeOld = String.format("%s %s", DateUtils.parseSQLDateStringToWebDate(appointDTOAppointDate.getAppointDate()),DateUtils.parseSQLTimeStringToWebTime(appointDTOAppointDate.getAppointTime()));
						String dateTimeNew = String.format("%s %s", DateUtils.parseSQLDateStringToWebDate(appointDTO.getAppointDate()),DateUtils.parseSQLTimeStringToWebTime(appointDTO.getAppointTime()));
						if(dateTimeOld.equals(dateTimeNew)) {
							appointDTO.setAppointDate(appointDTOAppointDate.getAppointDate());
							appointDTO.setAppointTime(appointDTOAppointDate.getAppointTime());
						}else {
							AppointStatusDTO appointStatusDTO = new AppointStatusDTO();
							appointStatusDTO.setAppointStatusId(3);
							appointDTO.setAppointStatusId(appointStatusDTO);
						}
						statusProcess = appointDAO.updateAppoint(appointDTO);
						if(statusProcess) {
							AppointHistoryDTO appointHistoryDTO = new AppointHistoryDTO();
							AppointStatusDTO appointStatusDTO = new AppointStatusDTO();
							appointHistoryDTO.setAppointId(appointDTO.getAppointId());
							appointStatusDTO.setAppointStatusId(appointDTO.getAppointStatusId().getAppointStatusId());
							appointHistoryDTO.setAppointStatusId(appointStatusDTO);
							appointHistoryDTO.setTitle(appointDTO.getTitle());
							appointHistoryDTO.setDetail(appointDTO.getDetail());
							appointHistoryDTO.setStatusId(appointDTO.getStatusId());
							appointHistoryDTO.setCreateBy(appointDTO.getUpdateBy());
							appointHistoryDTO.setUpdateBy(appointDTO.getUpdateBy());
							statusProcess = appointHistoryDAO.insert(appointHistoryDTO);
							if(!statusProcess)
								DatabaseUtils.rollback(connection);
						}else {
							DatabaseUtils.rollback(connection);
						}
					}
				}else {
					DatabaseUtils.rollback(connection);
				}
			}
			
			int statusAppoint = NullUtils.cvInt(appointJsonModel.getAppointStatusId().getAppointStatusId().toString());
						
			if(statusAppoint == 1 || statusAppoint == 3) {			
				// Edit Data Only
				
				statusProcess = appointDAO.InsertAppointMappingUser(appointJsonModel.getUser(), appointJsonModel.getAppointId());
				if(!statusProcess) {
					DatabaseUtils.rollback(connection);
					return false;
				}
				
				String dateTimeTemp[] = appointJsonModel.getAppointDate().split(" ");				
				//dateTimeTemp[0] = DateUtils.formatWebDateToSQLDateString(NullUtils.cvStr(dateTimeTemp[0]));
				appointJsonModel.setAppointDate(dateTimeTemp[0]+" "+dateTimeTemp[1]);				
				
				statusProcess = appointDAO.updateAppoint(appointJsonModel);
				if(!statusProcess) {
					DatabaseUtils.rollback(connection);
					return false;
				}				
				
				statusProcess = appointDAO.insertAppointHistory(appointJsonModel);
				if(!statusProcess) {
					DatabaseUtils.rollback(connection);
					return false;
				}
				
			}else if(statusAppoint == 2) {
				
				// Edit Data And Change Status To 3
				
				statusProcess = appointDAO.InsertAppointMappingUser(appointJsonModel.getUser(), appointJsonModel.getAppointId());
				if(!statusProcess) {
					DatabaseUtils.rollback(connection);
					return false;
				}
				//String dateTimeTemp[] = appointJsonModel.getAppointDate().split("-");
				AppointDTO appointDTO = appointDAO.getAppointDateByAppointId(appointJsonModel.getAppointId().trim());
				String dateTimeOld = DateUtils.parseSQLDateStringToWebDate(appointDTO.getAppointDate())+" "+DateUtils.parseSQLTimeStringToWebTime(appointDTO.getAppointTime());
				String dateTimeNew = appointJsonModel.getAppointDate();	
				
				if(dateTimeOld.equals(dateTimeNew)) {
					String dateTime[] = dateTimeOld.split(" ");					
					//dateTime[0] = DateUtils.formatWebDateToSQLDateString(NullUtils.cvStr(dateTime[0]));
					appointJsonModel.setAppointDate(dateTime[0]+" "+dateTime[1]);
				}else {
					String dateTime[] = dateTimeNew.split(" ");
										
					//dateTime[0] = DateUtils.formatWebDateToSQLDateString(NullUtils.cvStr(dateTime[0]));
					appointJsonModel.setAppointDate(dateTime[0]+" "+dateTime[1]);
					AppointStatusModel appointStatusModel = new AppointStatusModel();
					appointStatusModel.setAppointStatusId("3");
					appointJsonModel.setAppointStatusId(appointStatusModel);
				}				
				
				statusProcess = appointDAO.updateAppoint(appointJsonModel);
				if(!statusProcess) {
					DatabaseUtils.rollback(connection);
					return false;
				}				
				
				statusProcess = appointDAO.insertAppointHistory(appointJsonModel);
				if(!statusProcess) {
					DatabaseUtils.rollback(connection);
					return false;
				}
			}	
			
			
			return statusProcess;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(appointDAO != null)
				appointDAO.closeConnection();
			if(appointUserMappingDAO != null)
				appointUserMappingDAO.closeConnection();
			if(appointHistoryDAO != null)
				appointHistoryDAO.closeConnection();
		}
	}	
	
	
	
	
	private AppointModel transformDTO(AppointDTO appointDTO) {
		
		AppointModel appointModel = new AppointModel();
		appointModel.setUser(new ArrayList<>());
		appointModel.setAppointHistory(new ArrayList<>());
		//appointModel.setAppointCreateBy(NullUtils.cvStr(appointDTO.getAppointCreateBy()));
		if(appointDTO.getAppointCreateBy() != null) {
			UserModel userModel = new UserModel();
			userModel.setUserId(NullUtils.cvStr(appointDTO.getAppointCreateBy().getUserId()));
			//userModel.setFullName(NullUtils.cvStr(appointDTO.getAppointCreateBy().getFullName()));
			appointModel.setAppointCreateBy(userModel);
		}
		
		
		//appointModel.setAppointDate(NullUtils.cvStr(DateUtil.formatDate(appointDTO.getAppointDate(), "dd/MM/yyyy")));
		appointModel.setAppointDate(NullUtils.cvStr(String.format("%s %s", DateUtils.parseSQLDateStringToWebDate(appointDTO.getAppointDate()),DateUtils.parseSQLTimeStringToWebTime(appointDTO.getAppointTime()))));
		appointModel.setAppointId(NullUtils.cvStr(appointDTO.getAppointId()));
		//appointModel.setAppointType(NullUtils.cvStr(appointDTO.getAppointType()));		
		appointModel.setCreateBy(NullUtils.cvStr(appointDTO.getCreateBy()));
		appointModel.setCreateDate(NullUtils.cvStr(appointDTO.getCreateDate()));
		appointModel.setDetail(NullUtils.cvStr(appointDTO.getDetail()));
		appointModel.setTitle(NullUtils.cvStr(appointDTO.getTitle()));
		appointModel.setUpdateBy(NullUtils.cvStr(appointDTO.getUpdateBy()));
		appointModel.setUpdateDate(NullUtils.cvStr(appointDTO.getUpdateDate()));
		
		
		//appointModel.setStatusId(NullUtils.cvStr(appointDTO.getStatusId()));
		if(appointDTO.getStatusId() != null) {
			AppointStatusModel statusAppointModel = new AppointStatusModel();
			statusAppointModel.setStatusId(NullUtils.cvStr(appointDTO.getStatusId().getStatusId()));
			statusAppointModel.setStatusName(NullUtils.cvStr(appointDTO.getStatusId().getStatusName()));
			statusAppointModel.setStatusColor(NullUtils.cvStr(appointDTO.getStatusId().getStatusColor()));
			appointModel.setStatusId(statusAppointModel);
		}
		
		
		//appointModel.setSupplierId(NullUtils.cvStr(appointDTO.getSupplierId()));
		if(appointDTO.getSupplierId() != null) {
			SupplierModel supplierModel = new SupplierModel();
			supplierModel.setSupplierId(NullUtils.cvStr(appointDTO.getSupplierId().getSupplierId()));
			supplierModel.setSupplierCompany(NullUtils.cvStr(appointDTO.getSupplierId().getSupplierCompany()));
			appointModel.setSupplierId(supplierModel);
		}
		
		
		if(appointDTO.getUser() != null) {
			for(UserDTO auditorDetail : appointDTO.getUser()) {
				UserModel user = new UserModel();				
				user.setUserId(NullUtils.cvStr( auditorDetail.getUserId()));
				//user.setFullName(NullUtils.cvStr(auditorDetail.getFullName()));
				
				UserGroupModel userGroupModel = new UserGroupModel();
				userGroupModel.setUserGroupName(NullUtils.cvStr(auditorDetail.getUserGroupId().getUserGroupName()));
				userGroupModel.setUserGroupId(NullUtils.cvStr(auditorDetail.getUserGroupId().getUserGroupId()));
				//userGroupModel.setStatusId(NullUtils.cvStr(auditorDetail.getUserGroupId().getStatusId()));
				user.setUserGroupId(userGroupModel);
				
				appointModel.getUser().add(user);
			}
		}	
		
		if(appointDTO.getAppointHistory() != null) {
			
			for(AppointHistoryDTO appointHistoryDTO : appointDTO.getAppointHistory()) {
				AppointHistoryModel appointHistoryModel = new AppointHistoryModel();
				appointHistoryModel.setAppointHistoryId(NullUtils.cvStr(appointHistoryDTO.getAppointHistoryId()));
				appointHistoryModel.setAppointId(NullUtils.cvStr(appointHistoryDTO.getAppointId()));
				appointHistoryModel.setCreateBy(NullUtils.cvStr(appointHistoryDTO.getCreateBy()));
				appointHistoryModel.setCreateDate(NullUtils.cvStr(String.format("%s %s", DateUtils.parseSQLDateStringToWebDate(appointHistoryDTO.getCreateDate()),DateUtils.parseSQLTimeStringToWebTime(appointHistoryDTO.getCreateTime()))));				
				appointHistoryModel.setTitle(NullUtils.cvStr(appointHistoryDTO.getTitle()));
				appointHistoryModel.setDetail(NullUtils.cvStr(appointHistoryDTO.getDetail()));
				appointHistoryModel.setStatusId(NullUtils.cvStr(appointHistoryDTO.getStatusId()));
				appointModel.getAppointHistory().add(appointHistoryModel);
			}
		}
		
		
		return appointModel;
	}*/
	
}