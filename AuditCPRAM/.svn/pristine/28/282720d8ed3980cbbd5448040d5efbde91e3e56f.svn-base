package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jsoup.internal.StringUtil;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemKey;
import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.dao.MenuDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dao.SystemConfigurationDAO;
import th.co.gosoft.audit.cpram.dao.SystemLogDAO;
import th.co.gosoft.audit.cpram.dao.UserDAO;
import th.co.gosoft.audit.cpram.dao.UserGroupDAO;
import th.co.gosoft.audit.cpram.dto.MenuDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SystemConfigurationDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.ldap.LDAPConnector;
import th.co.gosoft.audit.cpram.ldap.LDAPDataObject;
import th.co.gosoft.audit.cpram.mail.BodyEmailDTO;
import th.co.gosoft.audit.cpram.mail.MailConnector;
import th.co.gosoft.audit.cpram.mail.MailReceiver;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.SupplierUserMappingModel;
import th.co.gosoft.audit.cpram.model.SystemConfigurationModel;
import th.co.gosoft.audit.cpram.model.SystemLogModel;
import th.co.gosoft.audit.cpram.model.UserGroupModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.relational.UserRelational;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.EncryptUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.PasswordGenerator;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class UserController {
	private final static Logger logger = Logger.getLogger(UserController.class);

	@SuppressWarnings("null")
	public String authen(HttpServletRequest request, String objUser){
		Connection connection = null;
		UserDAO userDAO = null;
		UserGroupDAO userGroupDAO = null;
		MenuDAO menuDAO = null;
		SystemConfigurationDAO systemConfigurationDAO = null;
		UserModel resultUserModel = null;
		Gson gson = null;
		SystemLogDAO systemLogDAO = null;
		try{
			ConfigurationSystemManager.getInstance();
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			userGroupDAO = new UserGroupDAO(connection);
			systemConfigurationDAO = new SystemConfigurationDAO(connection);
			menuDAO = new MenuDAO(connection);
			resultUserModel = new UserModel();
			systemLogDAO = new SystemLogDAO(connection);
			gson = new Gson();
			UserModel objUserReceive = gson.fromJson(objUser, UserModel.class);
			UserDTO userTmpDTO = userDAO.getUserDeatilByAuthenTypeIsAdmin(TransformModel.transUserModel(objUserReceive),true);
			if(userTmpDTO != null) {
				UserGroupDTO userGroupDTO = userGroupDAO.getAuthenTypeByUserName(TransformModel.transUserModel(objUserReceive));
				UserDTO userDTO = null;
				
				if(userGroupDTO != null) {
					// ===================== Search User In DataBase ================================================
					if(userGroupDTO.getAuthenType().equals(Config.AUTHEN_TYPE_AD)) {
						userDTO = userDAO.getUserDeatilByAuthenTypeIsAdmin(TransformModel.transUserModel(objUserReceive),false);					
					}else if(userGroupDTO.getAuthenType().equals(Config.AUTHEN_TYPE_USER)) {
						userDTO = userDAO.getUserDeatilByAuthenTypeIsUser(TransformModel.transUserModel(objUserReceive));					
					}
					// ===================== Search User In DataBase ================================================
					if(userDTO != null && userDTO.getUserId() > 0) {
						try {
							boolean resultAuthenAdminResult = false;
							//============= Query Connect To LDAP =====================
							if(userGroupDTO.getAuthenType().equals(Config.AUTHEN_TYPE_AD)) {
								LDAPDataObject ldapDataObject = null;
								LDAPConnector ldapConnector = new LDAPConnector();
								//resultAuthenAdminResult = true;
								ldapDataObject = ldapConnector.LdapConnection(objUserReceive.getUsername(), objUserReceive.getPassword());
								System.out.println(new Gson().toJson(ldapDataObject));
								if(ldapDataObject != null) {
									resultAuthenAdminResult = true;
								}
							}
							//============= Query Connect To LDAP =====================
							else if(userGroupDTO.getAuthenType().equals(Config.AUTHEN_TYPE_USER)) {
								resultAuthenAdminResult = true;
							}
							if(resultAuthenAdminResult) {
								
								if((userDTO.getUserGroupId().getUserGroupId() == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN 
										|| userDTO.getUserGroupId().getUserGroupId() == Config.AUTHEN_USERTYPE_SUPPLIER_QA
										|| userDTO.getUserGroupId().getUserGroupId() == Config.AUTHEN_USERTYPE_SUPPLIER_SALE)  
										&& (userDTO.getlasttimer() == null || userDTO.getlasttimer().toString() == "")) {									
									DatabaseUtils.commit(connection);
									resultUserModel.setUserId(Integer.toString(userDTO.getUserId()));
									resultUserModel.setReturnCode("0002");
									resultUserModel.setReturnMessage("กรุณาเปลี่ยน Password");
								} else {
									//================== Gen Menu by Role Of User ========================
									MenuDTO menuDTO = new MenuDTO();
									menuDTO.setUserGroupId(new ArrayList<>());	
									menuDTO.getUserGroupId().add(userDTO.getUserGroupId());
									StringBuilder menuListHTML = new StringBuilder();
									List<MenuDTO> menuDTOs = menuDAO.getMenuByGroupId(menuDTO);
									
									for(MenuDTO menu : menuDTOs) {
										if(menu.getCoutSubMenu() > 0) {
											
											menuListHTML.append("<li class=\"nav-item  \">");
											menuListHTML.append("	<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
											menuListHTML.append("		<i class=\""+menu.getMenuIcon()+"\"></i>");
											menuListHTML.append("        <span class=\"title\">"+menu.getMenuName()+"</span>");
											menuListHTML.append("        <span class=\"arrow\"></span>");
											menuListHTML.append("	</a>");
											menuListHTML.append("	<ul class=\"sub-menu\">");
											
											List<MenuDTO> subMenuList = menuDAO.getSubMenuByMenuId(menu);
						                     for (MenuDTO subMenu : subMenuList){
						                    	menuListHTML.append("<li class=\"nav-item  \">");
						                    	menuListHTML.append("		<a href=\""+subMenu.getMenuUrl()+"\" class=\"nav-link \">");
						                    	menuListHTML.append("		<i class=\""+subMenu.getMenuIcon()+"\"></i>");
						                    	menuListHTML.append("			<span class=\"title\">"+subMenu.getMenuName()+"</span>");
						                    	menuListHTML.append("     </a>");
						                    	menuListHTML.append("</li>");
											}
						                    menuListHTML.append("		</ul>");
						                    menuListHTML.append("</li>");  
											
																				
										}//if(userGroupMenu.getCoutSubMenu() > 0)
										else {
											menuListHTML.append("<li class=\"nav-item\">");
											menuListHTML.append("		<a href=\""+menu.getMenuUrl()+"\" class=\"nav-link nav-toggle\">");
											menuListHTML.append("		<i class=\""+menu.getMenuIcon()+"\"></i>");
											menuListHTML.append("			<span class=\"title\">"+menu.getMenuName()+"</span>");
											menuListHTML.append("		</a>");
											menuListHTML.append("</li>");
										}
									}//for(UserGroupMenuDTO userGroupMenu : userGroupMenuDTOs)
									
									UserModel userObjectSession = new UserModel();
									userObjectSession.setEmployeeId(NullUtils.cvStr(userDTO.getEmployeeId()));
									userObjectSession.setFullname(userDTO.getFullname());
									userObjectSession.setUserMenu(menuListHTML.toString());
									userObjectSession.setUserId(NullUtils.cvStr(userDTO.getUserId()));
									userObjectSession.setAcceptPdpaStatus(NullUtils.cvStr(userDTO.getAccept_pdpa_status()));
									userObjectSession.setAcceptPdpaDate(NullUtils.cvStr(userDTO.getAccept_pdpa_date()));
									
									UserGroupModel userGroupModel = new UserGroupModel();
									userGroupModel.setUserGroupId(NullUtils.cvStr(userDTO.getUserGroupId().getUserGroupId()));
									userGroupModel.setUserGroupName(userDTO.getUserGroupId().getUserGroupName());
									userObjectSession.setUserGroupId(userGroupModel);
									
									List<SystemConfigurationDTO> systemConfigurationDTOs = systemConfigurationDAO.getSystemConfigurationList("");
									List<SystemConfigurationModel> systemConfigurationModels = new ArrayList<>();
		
									for(SystemConfigurationDTO systemConfiguration : systemConfigurationDTOs) {
										systemConfigurationModels.add(TransformDTO.transSystemConfigurationDTO(systemConfiguration));
										
										if(systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemEmerFooterAdminMail)) {
											userObjectSession.setEmerEmail(systemConfiguration.getSystemValue().trim());
										}
										else if(systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemEmerFooterTelephone)) {
											userObjectSession.setEmerTelephone(systemConfiguration.getSystemValue().trim());
										}
										else if(systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemEmerFooterUrlWeb)) {
											userObjectSession.setEmerUrl(systemConfiguration.getSystemValue().trim());
										}
										else if(systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemAcceptPdpaHtml)) {
											userObjectSession.setAcceptPdpaHtml(systemConfiguration.getSystemValue().trim());
										}
										else if(systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemAcceptPdpaNo)) {
											userObjectSession.setAcceptPdpaNo(systemConfiguration.getSystemValue().trim());
										}
										else if(systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemAcceptPdpaDocument)) {
											userObjectSession.setPdpalink(systemConfiguration.getSystemValue().trim());
										}
									}							
									
									HttpSession session;
									session = request.getSession(true);
									session.setAttribute(StaticVariableUtils.Session_Key, userObjectSession);
									
									String ipAddress = request.getHeader("X-FORWARDED-FOR");  
									if (ipAddress == null) {  
									    ipAddress = request.getRemoteAddr();  
									}
									
								
									resultUserModel = userObjectSession;
									resultUserModel.setReturnCode("0000");
									resultUserModel.setReturnMessage("Success");							
										
									boolean statusProcess = userDAO.updateCountandTimeDf(userDTO);
									if(statusProcess) {
										SystemLogModel systemLogModel = new SystemLogModel();
										systemLogModel.setAccess("login");
										systemLogModel.setActivity("login");
										systemLogModel.setDetail("เข้าใช้งานระบบ");
										systemLogModel.setCreateBy(NullUtils.cvStr(userDTO.getUserId()));
										systemLogModel.setNote(ipAddress);
										systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));
										DatabaseUtils.commit(connection);
									}else {
										DatabaseUtils.rollback(connection);
									}
								}							
																
							}//if(resultAuthenAdminResult)
							else {
								userDTO = userDAO.getUserDeatilByAuthenTypeIsAdmin(TransformModel.transUserModel(objUserReceive),true);
								userDAO.updatelasttime(userDTO);
								DatabaseUtils.commit(connection);
								resultUserModel.setReturnCode("0001");
								resultUserModel.setReturnMessage("Username หรือ Password ไม่ถูกต้อง");
							}
						}catch (Exception e) {
							logger.error("UserController.authen() Exception : "+ExceptionUtils.stackTraceException(e));
							userDTO = userDAO.getUserDeatilByAuthenTypeIsAdmin(TransformModel.transUserModel(objUserReceive),true);
							userDAO.updatelasttime(userDTO);
							DatabaseUtils.commit(connection);
							resultUserModel.setReturnCode("0001");
							resultUserModel.setReturnMessage("Username หรือ Password ไม่ถูกต้อง");
						}
						
					}//if(userDTO != null && userDTO.getUserId() > 0)
					else {
						userDTO = userDAO.getUserDeatilByAuthenTypeIsAdmin(TransformModel.transUserModel(objUserReceive),true);
						
						if(!((userDTO.getUserGroupId().getUserGroupId() == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN 
								|| userDTO.getUserGroupId().getUserGroupId() == Config.AUTHEN_USERTYPE_SUPPLIER_QA)  
								&& (userDTO.getlasttimer() == null || userDTO.getlasttimer().toString() == ""))) {
							userDAO.updatelasttime(userDTO);
						}						
						DatabaseUtils.commit(connection);
						resultUserModel.setReturnCode("0001");
						resultUserModel.setReturnMessage("Username หรือ Password ไม่ถูกต้อง");
					}	
				}// if(userGroupDTO != null)
				else {
					resultUserModel.setReturnCode("0001");
					resultUserModel.setReturnMessage("Username หรือ Password ไม่ถูกต้อง");
				}
			}else {
				UserDTO userDTO = null;
				userDTO = userDAO.getUserDeatilByAuthenTypeIsAdmin(TransformModel.transUserModel(objUserReceive),false);
				if(userDTO != null) {
					resultUserModel.setReturnCode("0001");
					resultUserModel.setReturnMessage("กรุณารออีก 5 นาทีเพื่อเข้าระบบอีกครั้ง");
				}else {
					resultUserModel.setReturnCode("0001");
					resultUserModel.setReturnMessage("Username หรือ Password ไม่ถูกต้อง");
				}
			}
		}
		catch (Exception e) {
			logger.error("UserController.authen() Exception : "+ExceptionUtils.stackTraceException(e));
			resultUserModel.setReturnCode("0001");
			resultUserModel.setReturnMessage("Username หรือ Password ไม่ถูกต้อง");
		}finally{
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
		String resultJSONUserModel = gson.toJson(resultUserModel);
        return resultJSONUserModel;
		
	}
	
	
	public DataTableModel<UserModel> dataTableGetUserList (HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel){
		Connection connection = null;
		UserDAO userDAO = null;
		SessionUtils sessionUtils = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			
			UserModel userModelSession = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			
			DataTableModel<UserModel> dataTableUserModel = new DataTableModel<>();
			dataTableUserModel.setData(new ArrayList<>());
			
			StringBuilder queryWhereClause = new StringBuilder();
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("employeeId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.employee_id").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
					else if (col.getName().equals("username") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.username").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("password") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.password").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("fullname") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.fullname").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("description") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.description").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("email") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.email").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("telephone") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.telephone").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("enable") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.enable").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("userGroupId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.user_group_id").append(" = ").append(col.getSearch().getValue()).append(" ");
					}
					else if (col.getName().equals("userGroupName") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("ug.user_group_name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
				}
			}
			
			if(NullUtils.cvInt(userModelSession.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN 
					|| NullUtils.cvInt(userModelSession.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA
					|| NullUtils.cvInt(userModelSession.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_SALE) {				
				List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userModelSession));				
				queryWhereClause.append(" AND ").append("sup_map.supplier_id = '").append(supplierDTOs.get(0).getSupplierId()).append("' ");
			}
			
			if(NullUtils.cvInt(userModelSession.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_REGISTER) {					
				queryWhereClause.append(" AND u.user_group_id <> 1 ");
			}
			
			if (NullUtils.cvInt(userModelSession.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_PURCHASING_OFFICER) {					
				queryWhereClause.append(" AND u.user_group_id in (3, 8) ");
			}
			
			List<UserDTO> userDTOs = userDAO.getAllUserList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			
			for(UserDTO userDTO : userDTOs) {
				dataTableUserModel.getData().add(TransformDTO.transUserDTO(userDTO));
			}
			int countUserAll = userDAO.countAllUser(queryWhereClause.toString());
			dataTableUserModel.setRecordsFiltered(countUserAll);
			dataTableUserModel.setRecordsTotal(countUserAll);
			dataTableUserModel.setDraw(dataTablePostParamModel.getDraw());
			
			return dataTableUserModel;
		}catch(Exception e) {
			logger.error("UserController.dataTableGetUserList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
		
	public DataTableModel<UserModel> datatableGetUserAuditorType(DataTablePostParamModel dataTablePostParamModel){
		UserDAO userDAO = null;
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			
			DataTableModel<UserModel> dataTableUserModel = new DataTableModel<>();
			dataTableUserModel.setData(new ArrayList<>());
			
			StringBuilder queryWhereClause = new StringBuilder();
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("employeeId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.employee_id").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}else if (col.getName().equals("fullname") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.fullname").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("description") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.description").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("email") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.email").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("telephone") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.telephone").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("enable") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.enable").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
				}
			}
			
			queryWhereClause.append(" AND ");
			queryWhereClause.append("u.user_group_id").append(" = ").append(Config.AUTHEN_USERTYPE_AUDITOR).append(" ");
			
			List<UserDTO> userDTOs = userDAO.getAllUserList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			
			for(UserDTO userDTO : userDTOs) {
				dataTableUserModel.getData().add(TransformDTO.transUserDTO(userDTO));
			}
			int countUserAll = userDAO.countAllUser(queryWhereClause.toString());
			dataTableUserModel.setRecordsFiltered(countUserAll);
			dataTableUserModel.setRecordsTotal(countUserAll);
			dataTableUserModel.setDraw(dataTablePostParamModel.getDraw());
			
			return dataTableUserModel;
			
		}catch(Exception e) {
			logger.error("UserController.datatableGetUserAuditorType() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public DataTableModel<UserModel> datatableGetEntourgeList(DataTablePostParamModel dataTablePostParamModel){
		Connection connection = null;
		UserDAO userDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			DataTableModel<UserModel> dataTableUserModel = new DataTableModel<>();
			dataTableUserModel.setData(new ArrayList<>());
			
			StringBuilder queryWhereClause = new StringBuilder();
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("employeeId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.employee_id").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}else if (col.getName().equals("fullname") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.fullname").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("description") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.description").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("email") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.email").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("telephone") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.telephone").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("enable") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.enable").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("userGroupName") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("ug.user_group_name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
				}
			}
			
			queryWhereClause.append(" AND ");
			queryWhereClause.append("( u.user_group_id").append(" = ").append(Config.AUTHEN_USERTYPE_AUDITOR).append(" ");
			queryWhereClause.append(" OR ");
			queryWhereClause.append("u.user_group_id").append(" = ").append(Config.AUTHEN_USERTYPE_PURCHASING_OFFICER).append(" ) ");
			
			
			List<UserDTO> userDTOs = userDAO.getAllUserList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			
			for(UserDTO userDTO : userDTOs) {
				dataTableUserModel.getData().add(TransformDTO.transUserDTO(userDTO));
			}
			int countUserAll = userDAO.countAllUser(queryWhereClause.toString());
			dataTableUserModel.setRecordsFiltered(countUserAll);
			dataTableUserModel.setRecordsTotal(countUserAll);
			dataTableUserModel.setDraw(dataTablePostParamModel.getDraw());
			
			
			return dataTableUserModel;
		}catch(Exception e) {
			logger.error("UserController.datatableGetEntourgeList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public Map<String, Object> insertUser(HttpServletRequest httpServletRequest, String objUser) {
		Connection connection = null;
		UserDAO userDAO = null;
		SystemLogDAO systemLogDAO = null;
		Gson gson = null;
		PasswordGenerator passwordGenerator = null;
		SessionUtils sessionUtils = null;		
		LDAPConnector ldapConnector = null;
		Boolean resultProcess = false, exitsUserLDAP = true, dupplicateUsername = false, dupplicateSupplierAdmin = false;	
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		int resultUserId = 0;
		
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			gson = new Gson();
			passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
					.useDigits(true)
					.useLower(true)
					.useUpper(true)
					.build();
			sessionUtils = new SessionUtils(httpServletRequest);
			ldapConnector = new LDAPConnector();
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			systemLogDAO = new SystemLogDAO(connection);
			
			UserModel userRequest = gson.fromJson(objUser, UserModel.class);
			String password = passwordGenerator.generate(new Random().nextInt((5 - 4) + 1) + 4);	
			userRequest.setPassword(password.concat(EncryptUtils.EncryptSHA256()));	
			int userSessionId = sessionUtils.getUserIdSession("sessionAuthen");			
			userRequest.setCreateBy(NullUtils.cvStr(userSessionId));
			userRequest.setUpdateBy(NullUtils.cvStr(userSessionId));
			
			dupplicateUsername = userDAO.checkDupplicateUserName(TransformModel.transUserModel(userRequest));
			
			if(!dupplicateUsername) {
				if(userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_ADMINISTRATOR))) {
					userRequest.setDescription(userRequest.getDescription());
					resultUserId = userDAO.insertUser(TransformModel.transUserModel(userRequest));
					if(resultUserId > 0){
						resultProcess = true;
					}
				}else if(userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_AUDITOR)) || userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_PURCHASING_OFFICER))
						|| userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_APPROVE_AUDIT_RESULT)) || userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_REGISTER))){ 
					userRequest.setPassword("");
					userRequest.setDescription(userRequest.getDescription());
					exitsUserLDAP = ldapConnector.checkUserExitsInLDAP(userRequest.getUsername());
					if(exitsUserLDAP) {
						resultUserId = userDAO.insertUser(TransformModel.transUserModel(userRequest));
						if(resultUserId > 0) {
							resultProcess = true;
						}							
					}else {
						exitsUserLDAP = false;
						resultProcess = false;
					}
					
				}else if(userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN)) 
						|| userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_QA))
						|| userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_SALE))) {
					
					SupplierUserMappingModel supplierUserMappingModel = new SupplierUserMappingModel();
					supplierUserMappingModel.setCreateBy(userRequest.getCreateBy());
					supplierUserMappingModel.setEnable("Y");
					supplierUserMappingModel.setUpdateBy(userRequest.getUpdateBy());
					for(SupplierModel supplier : userRequest.getSupplierId()) {
						supplierUserMappingModel.setSupplierId(supplier.getSupplierId());
					}
					
					if(userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN))) {							
						dupplicateSupplierAdmin = supplierUserMappingDAO.checkDupplicateSupplierAdmin(TransformModel.transSupplierUserMappingModel(supplierUserMappingModel));
					}
					
					if(!dupplicateSupplierAdmin) {
						userRequest.setDescription(userRequest.getDescription());
						resultUserId = userDAO.insertUser(TransformModel.transUserModel(userRequest));
						if(resultUserId > 0) {
							resultProcess = true;
							userRequest.setUserId(NullUtils.cvStr(resultUserId));
							supplierUserMappingModel.setUserId(userRequest.getUserId());
						}							
						if(resultProcess) {														
							resultProcess = supplierUserMappingDAO.insert(TransformModel.transSupplierUserMappingModel(supplierUserMappingModel));
						}else {
							resultProcess = false;
						}
					}							
					
				}
				
				if(resultProcess) {
					List<String> receiver = new ArrayList<>();
					BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();	
					if(userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_AUDITOR)) || userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_PURCHASING_OFFICER))
							|| userRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_APPROVE_AUDIT_RESULT))) {
						bodyEmailDTO.setPasswordReceiver("Password Lan");
					}else {
						bodyEmailDTO.setPasswordReceiver(password);
					}
					bodyEmailDTO.setFullnameReceiver(userRequest.getFullname());
					bodyEmailDTO.setUsernameReceiver(userRequest.getUsername());
					receiver.add(userRequest.getEmail());
					MailReceiver mailReceiver = new MailReceiver();
					mailReceiver.setMailReceiver(receiver);
					resultProcess = MailConnector.sendMailRegisterUser(mailReceiver, bodyEmailDTO, "Authentification CPRam Audit Supplier");
					
				}//if(resultProcess)
				
			}//if(!DupplicateUsername)
				
			if(resultProcess) {
				SystemLogModel systemLogModel = new SystemLogModel();
				systemLogModel.setAccess("user");
				systemLogModel.setActivity("create");
				systemLogModel.setDetail("สร้างผู้ใช้งาน");
				systemLogModel.setCreateBy(NullUtils.cvStr(userRequest.getUpdateBy()));
				systemLogModel.setRefId(NullUtils.cvStr(resultUserId));
				systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));
				DatabaseUtils.commit(connection);
			}
				
			else 
				DatabaseUtils.rollback(connection);
			
			Map<String, Object> resultOfProcess = new HashMap<>();
			resultOfProcess.put("resultProcess", resultProcess);
			resultOfProcess.put("DupplicateUsername", dupplicateUsername);
			resultOfProcess.put("ExistingUserLDAP", exitsUserLDAP);
			resultOfProcess.put("DupplicateSupplierAdmin", dupplicateSupplierAdmin);
			return resultOfProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("UserController.insertUser() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.rollback(connection);
		}
	}
	
	public Map<String, Boolean> userChangePassword(String objUser) {
		Connection connection = null;
		UserDAO userDAO = null;
		Gson gson = null;
		PasswordGenerator passwordGenerator = null;
		boolean statusProcess = false, checkIsAdmin = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			gson = new Gson();
			passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
					.useDigits(true)
					.useLower(true)
					.useLower(true)
					.useUpper(true)
					.build();
			
			String newPasswordString = passwordGenerator.generate(new Random().nextInt((5 - 4) + 1) + 4);
			UserModel userRequest = gson.fromJson(objUser, UserModel.class);
			userRequest.setPassword(newPasswordString.concat(EncryptUtils.EncryptSHA256()));
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND ").append("u.email = '").append(userRequest.getEmail()).append("' ");
			queryWhereClause.append(" AND ").append("u.username = '").append(userRequest.getUsername()).append("' ");
			
			List<UserDTO> userDTOs =  userDAO.getAllUserList(0, userDAO.countAllUser(queryWhereClause.toString()), queryWhereClause.toString());
			if(userDTOs != null && !userDTOs.isEmpty()) {
				if(userDTOs.get(0).getUserGroupId().getUserGroupId() == Config.AUTHEN_USERTYPE_ADMINISTRATOR) {
					statusProcess = false;
					checkIsAdmin = true;
				}else {
					userRequest.setUserId(NullUtils.cvStr(userDTOs.get(0).getUserId()));
					statusProcess = userDAO.userChangePassword(TransformModel.transUserModel(userRequest));
					
					if(statusProcess) {
						List<String> receiver = new ArrayList<>();
						BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();	
						bodyEmailDTO.setPasswordReceiver(newPasswordString);
						bodyEmailDTO.setUsernameReceiver(userRequest.getUsername());
						bodyEmailDTO.setFullnameReceiver(userDTOs.get(0).getFullname());
						
						receiver.add(userRequest.getEmail());
						MailReceiver mailReceiver = new MailReceiver();
						mailReceiver.setMailReceiver(receiver);
						
						statusProcess = MailConnector.sendMailChangePasswordUser(mailReceiver, bodyEmailDTO,"แจ้งการสร้างรหัสผ่านใหม่");
						/*MailConfigDTO mailConfigDTO = Config.getObjectConfigEmail();
						mailConfigDTO.setEmailSubject("แจ้งการสร้างรหัสผ่านใหม่");
						List<String> receiverList = new ArrayList<>();				
						receiverList.add(userRequest.getEmail());
						mailConfigDTO.setEmailReceiver(receiverList);
						mailConfigDTO.setEmailBody(TemplateMail.getTemplateMail(CreateMailBody.getBodyChangePasswordUser(bodyEmailDTO)));
						
						mail = new Mail(mailConfigDTO);
						statusProcess = mail.sendMailBodyHTML();*/
					}
				}
				
			}
			
			if(statusProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			Map<String, Boolean> resultProcess = new HashMap<>();
			resultProcess.put("statusProcess", statusProcess);
			resultProcess.put("checkIsAdmin", checkIsAdmin);
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("UserController.userChangePassword() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public Map<String, Boolean> userChangePasswordManual(String objUser) {
		Connection connection = null;
		UserDAO userDAO = null;
		Gson gson = null;
		boolean statusProcess = false, checkIsAdmin = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			gson = new Gson();
			
			UserModel userRequest = gson.fromJson(objUser, UserModel.class);
			userRequest.setPassword(userRequest.getPassword().concat(EncryptUtils.EncryptSHA256()));
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND ").append("u.user_id = '").append(userRequest.getUserId()).append("' ");
			
			List<UserDTO> userDTOs =  userDAO.getAllUserList(0, userDAO.countAllUser(queryWhereClause.toString()), queryWhereClause.toString());

			userRequest.setUserId(NullUtils.cvStr(userDTOs.get(0).getUserId()));
			statusProcess = userDAO.userChangePassword(TransformModel.transUserModel(userRequest));
			
			statusProcess = userDAO.updateCountandTimeDf(userDTOs.get(0));
			
			if(statusProcess) {
				DatabaseUtils.commit(connection);
			}				
			else
				DatabaseUtils.rollback(connection);
			
			Map<String, Boolean> resultProcess = new HashMap<>();
			resultProcess.put("statusProcess", statusProcess);
			resultProcess.put("checkIsAdmin", checkIsAdmin);
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("UserController.userChangePasswordManual() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public Map<String, Object> updateUser(HttpServletRequest httpServletRequest, String objUser) {
		Connection connection = null;
		UserDAO userDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		SystemLogDAO systemLogDAO = null;
		LDAPConnector ldapConnector = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		Boolean resultProcess = false, exitsUseranmeUser = false, exitsUserLDAP = false, exitsSupplierAdmin = false;		
		UserDTO dataBeforeUpdate = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			userDAO = new UserDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			systemLogDAO = new SystemLogDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			ldapConnector = new LDAPConnector();
			
			UserModel userModelRequest = gson.fromJson(objUser, UserModel.class);
			userModelRequest.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
			
			exitsUseranmeUser = userDAO.checkDupplicateUsernameNotInUserId(TransformModel.transUserModel(userModelRequest));
			if(!exitsUseranmeUser) {
				
				if(!StringUtils.isEmptyOrWhitespaceOnly(userModelRequest.getPassword())) {
					String password = userModelRequest.getPassword().concat(EncryptUtils.EncryptSHA256());						
					userModelRequest.setPassword(String.format(" password = MD5('%s'), ",password));
				}			
				
				dataBeforeUpdate = userDAO.getUserByUserId(userModelRequest.getUserId());
				
				if(userModelRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_AUDITOR)) 
						|| userModelRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_PURCHASING_OFFICER))
						|| userModelRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_APPROVE_AUDIT_RESULT)) 
						|| userModelRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_REGISTER))) {
//						exitsUserLDAP = ldapConnector.checkUserExitsInLDAP(userModelRequest.getUsername().trim());
					
//						if(exitsUserLDAP) {
						resultProcess = userDAO.updateUser(TransformModel.transUserModel(userModelRequest));							
//						}else {
//							resultProcess = false;
//						}
					
				}else if(userModelRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN)) 
						|| userModelRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_QA))
						|| userModelRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_SALE))) {
					exitsUserLDAP = true;

					if(userModelRequest.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN))){
						SupplierUserMappingModel supplierUserMappingModel = new SupplierUserMappingModel();
						supplierUserMappingModel.setUserId(userModelRequest.getUserId());
						supplierUserMappingModel.setEnable(userModelRequest.getEnable());
						supplierUserMappingModel.setCreateBy(userModelRequest.getUpdateBy());
						supplierUserMappingModel.setUpdateBy(userModelRequest.getUpdateBy());
						for(SupplierModel supplier : userModelRequest.getSupplierId()) {
							supplierUserMappingModel.setSupplierId(supplier.getSupplierId());
						}							
						exitsSupplierAdmin = supplierUserMappingDAO.checkDupplicateSupplierAdmin(TransformModel.transSupplierUserMappingModel(supplierUserMappingModel));
					}						
					
					if(!exitsSupplierAdmin) {
						resultProcess = userDAO.updateUser(TransformModel.transUserModel(userModelRequest));	
						if(resultProcess) {
							if(userModelRequest.getSupplierId() != null) {
								if(!userModelRequest.getSupplierId().isEmpty()) {
									resultProcess = supplierUserMappingDAO.deleteByUserId(TransformModel.transUserModel(userModelRequest));
									if(resultProcess) {
										for(SupplierModel supplier : userModelRequest.getSupplierId()) {
											if(!StringUtils.isNullOrEmpty(supplier.getSupplierId())) {
												
												SupplierUserMappingModel supplierUserMappingModel = new SupplierUserMappingModel();
												supplierUserMappingModel.setSupplierId(supplier.getSupplierId());
												supplierUserMappingModel.setUserId(userModelRequest.getUserId());
												supplierUserMappingModel.setEnable(userModelRequest.getEnable());
												supplierUserMappingModel.setCreateBy(userModelRequest.getUpdateBy());
												supplierUserMappingModel.setUpdateBy(userModelRequest.getUpdateBy());
												resultProcess = supplierUserMappingDAO.insert(TransformModel.transSupplierUserMappingModel(supplierUserMappingModel));
												if(!resultProcess) {
													break;
												}
											}
										}
									}
								}
							}
						}
					}					
					
				}else {
					exitsUserLDAP = true;
					resultProcess = userDAO.updateUser(TransformModel.transUserModel(userModelRequest));
				}					
				
			}
			
			if(resultProcess) {
				SystemLogModel systemLogModel = new SystemLogModel();
				systemLogModel.setAccess("user");
				
				if(!dataBeforeUpdate.getEnable().toString().equalsIgnoreCase(userModelRequest.getEnable())) {
					if(userModelRequest.getEnable().equalsIgnoreCase("Y")) {
						systemLogModel.setActivity("unlock");
						systemLogModel.setDetail("ยกเลิกระงับใช้งาน");
					} else if(userModelRequest.getEnable().equalsIgnoreCase("N")){
						systemLogModel.setActivity("lock");
						systemLogModel.setDetail("ระงับใช้งาน");
					}
				} else {
					systemLogModel.setActivity("edit");
					systemLogModel.setDetail("แก้ไขข้อมูลผู้ใช้งาน");
				}			
				systemLogModel.setCreateBy(NullUtils.cvStr(userModelRequest.getUpdateBy()));
				systemLogModel.setRefId(NullUtils.cvStr(userModelRequest.getUserId()));
				systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));
				DatabaseUtils.commit(connection);
			}				
			else
				DatabaseUtils.rollback(connection);
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("exitsUserLDAP", exitsUserLDAP);
			resultMap.put("resultProcess",resultProcess);
			resultMap.put("exitsUseranmeUser", exitsUseranmeUser);
			resultMap.put("exitsSupplierAdmin", exitsSupplierAdmin);
			return resultMap;
			
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("UserController.updateUser() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	
	public String getUserDeatilById(String objUser) {
		Connection connection = null;
		UserDAO userDAO = null;
		Gson gson = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			gson = new Gson();
			
			UserModel userRequest = gson.fromJson(objUser, UserModel.class);
			StringBuilder queryWhereClause = new StringBuilder(); 
			queryWhereClause.append(" AND u.user_id = ").append(userRequest.getUserId());
			
			List<UserDTO> userDTOs = userDAO.getAllUserList(0, userDAO.countAllUser(queryWhereClause.toString()), queryWhereClause.toString());
			List<UserModel> userModels = new ArrayList<>();
			for(UserDTO user : userDTOs) {
				List<SupplierDTO> supplierList = supplierUserMappingDAO.getSupplierInUser(user);
				user.setSupplierId(supplierList);
				userModels.add(TransformDTO.transUserDTO(user));
			}
			
			String resultUserModel = gson.toJson(userModels);
			return resultUserModel;			
		}catch(Exception e) {
			logger.error("UserController.getUserDeatilById() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public boolean deleteUser(HttpServletRequest httpServletRequest,String objUser) {
		Connection connection = null;
		UserDAO userDAO = null;
		SystemLogDAO systemLogDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		Boolean resultProcess = false;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		UserDTO dataBeforeDelete = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			systemLogDAO = new SystemLogDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			gson = new Gson();
						
			UserModel userModelRequest = gson.fromJson(objUser, UserModel.class);
			
			sessionUtils = new SessionUtils(httpServletRequest);
			userModelRequest.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
			
			dataBeforeDelete = userDAO.getUserByUserId(userModelRequest.getUserId());
			
			
			if(!UserRelational.checkUserRelational(connection, TransformModel.transUserModel(userModelRequest))) {
				resultProcess = supplierUserMappingDAO.deleteByUserId(TransformModel.transUserModel(userModelRequest));
				if(resultProcess) {
					resultProcess = userDAO.deleteUser(TransformModel.transUserModel(userModelRequest));
					
				}
			} else {
				resultProcess = userDAO.flagDeleteByUserId(TransformModel.transUserModel(userModelRequest));
			}
			
			if(resultProcess) {
				SystemLogModel systemLogModel = new SystemLogModel();
				systemLogModel.setAccess("user");
				systemLogModel.setActivity("delete");
				systemLogModel.setDetail("ลบข้อมูลผู้ใช้งาน");
				systemLogModel.setCreateBy(NullUtils.cvStr(userModelRequest.getUpdateBy()));
				systemLogModel.setRefId(NullUtils.cvStr(userModelRequest.getUserId()));
				systemLogModel.setNote(dataBeforeDelete.getEmployeeId() + " - " + dataBeforeDelete.getFullname() + " - " + dataBeforeDelete.getEmail());
				systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));
				DatabaseUtils.commit(connection);
			}
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("UserController.deleteUser() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	
	public boolean updatePdpaStatus(HttpServletRequest httpServletRequest, int UserID) {
		Connection connection = null;
		UserDAO userDAO = null;
		UserModel userModel = null;
		SystemConfigurationDAO systemConfigurationDAO = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			
			userDAO = new UserDAO(connection);
			systemConfigurationDAO = new SystemConfigurationDAO(connection);
			userModel = new UserModel();
			
			List<SystemConfigurationDTO> systemConfigurationDTOs = systemConfigurationDAO.getSystemConfigurationList("");
			List<SystemConfigurationModel> systemConfigurationModels = new ArrayList<>();

			for(SystemConfigurationDTO systemConfiguration : systemConfigurationDTOs) {
				systemConfigurationModels.add(TransformDTO.transSystemConfigurationDTO(systemConfiguration));
				
				if(systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemAcceptPdpaNo)) {
					userModel.setAcceptPdpaNo(systemConfiguration.getSystemValue().trim());
				}
			}
			
			
			resultProcess = userDAO.updatePdpaStatus( UserID , userModel.getAcceptPdpaNo(),"Y" );
			
			if(resultProcess == true) {
				logger.error("Controller is Fine");
					DatabaseUtils.commit(connection);
					/*
					SessionUtils sessionUtils = new SessionUtils(httpServletRequest);
					UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
					userSessionModel.setAcceptPdpaStatus("Y");
					
					HttpSession session;
					session = httpServletRequest.getSession(true);
					session.setAttribute(StaticVariableUtils.Session_Key, userSessionModel);
					*/
				
			}else {
				logger.error("Controller broken");
					DatabaseUtils.rollback(connection);
			}
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("UserController.updatePdpaStatus() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
		
		
	}
	
	public boolean updateCancelPdpaStatus(HttpServletRequest httpServletRequest, int UserID) {
		Connection connection = null;
		UserDAO userDAO = null;
		UserModel userModel = null;
		SystemConfigurationDAO systemConfigurationDAO = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			
			userDAO = new UserDAO(connection);
			systemConfigurationDAO = new SystemConfigurationDAO(connection);
			userModel = new UserModel();
			
			List<SystemConfigurationDTO> systemConfigurationDTOs = systemConfigurationDAO.getSystemConfigurationList("");
			List<SystemConfigurationModel> systemConfigurationModels = new ArrayList<>();

			for(SystemConfigurationDTO systemConfiguration : systemConfigurationDTOs) {
				systemConfigurationModels.add(TransformDTO.transSystemConfigurationDTO(systemConfiguration));
				
				if(systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemAcceptPdpaNo)) {
					userModel.setAcceptPdpaNo("2");
				}
			}
			
			
			resultProcess = userDAO.updatePdpaStatus( UserID , "2","N" );
			
			if(resultProcess == true) {
				logger.error("Controller is Fine");
					DatabaseUtils.commit(connection);
					/*
					SessionUtils sessionUtils = new SessionUtils(httpServletRequest);
					UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
					userSessionModel.setAcceptPdpaStatus("Y");
					
					HttpSession session;
					session = httpServletRequest.getSession(true);
					session.setAttribute(StaticVariableUtils.Session_Key, userSessionModel);
					*/
				
			}else {
				logger.error("Controller broken");
					DatabaseUtils.rollback(connection);
			}
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("UserController.updatePdpaStatus() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
		
		
	}
	
	
	//UserDTO userDTO = userDAO.authen(username, password);
	/*if (userDTO!=null && userDTO.getUserId()>0){
		
		//String authenType = userDTO.getAuthenType();
		String authenType = "";
		try{
			boolean authenADResult = true;
			
			if (authenType.equals(Config.AUTHEN_TYPE_AD)){
				authenADResult = false;
				LDAPDataObject lData = null;
				LDAPConnector ldapConnect = new LDAPConnector();
				lData = ldapConnect.LdapConnection(username, password);
				if (lData == null) {
					authenADResult = true;
				}
			}
			if (authenADResult){
				
				// Get Menu
				List<UserGroupMenuDTO> userGroupMenuList = userDAO.getUserMenuByGroupId(userDTO.getUserGroupId().getUserGroupId());
				StringBuilder sb = new StringBuilder();
				for (UserGroupMenuDTO userGroupMenuDTO : userGroupMenuList){
					if (userGroupMenuDTO.getCountSubMenu()>0){
					if(true) {
	                     sb.append("<li class=\"nav-item  \">");
	                     sb.append("	<a href=\"javascript:;\" class=\"nav-link nav-toggle\">");
	                     sb.append("		<i class=\""+userGroupMenuDTO.getMenuIcon()+"\"></i>");
	                     sb.append("        <span class=\"title\">"+userGroupMenuDTO.getMenuName()+"</span>");
	                     sb.append("        <span class=\"arrow\"></span>");
	                     sb.append("	</a>");
	                     sb.append("	<ul class=\"sub-menu\">");
	                     List<UserGroupMenuDTO> subMenu = userDAO.getUserSubMenuByMenuId(userGroupMenuDTO.getUserGroupMenuId());
	                     for (UserGroupMenuDTO sub : subMenu){
							sb.append("<li class=\"nav-item  \">");
		                    sb.append("		<a href=\""+sub.getMenuUrl()+"\" class=\"nav-link \">");
		                    sb.append("			<span class=\"title\">"+sub.getMenuName()+"</span>");
		                    sb.append("     </a>");
		                    sb.append("</li>");
						}
						sb.append("		</ul>");
	             	    sb.append("</li>");                    
						
					}else{
						sb.append("<li class=\"nav-item\">");
						sb.append("		<a href=\""+userGroupMenuDTO.getMenuUrl()+"\" class=\"nav-link nav-toggle\">");
	                    sb.append("		<i class=\""+userGroupMenuDTO.getMenuIcon()+"\"></i>");
						sb.append("			<span class=\"title\">"+userGroupMenuDTO.getMenuName()+"</span>");
						sb.append("		</a>");
						sb.append("</li>");
					}
					
				}

				
				UserModel sessionUser = new UserModel();
				sessionUser.setEmployeeId(NullUtils.cvStr(userDTO.getEmployeeId()));
				//sessionUser.setFullName(NullUtils.cvStr(userDTO.getFullName()));
				
				//sessionUser.setUserMenu(sb.toString());
				sessionUser.setUserId(NullUtils.cvStr(userDTO.getUserId()));
				
				UserGroupModel userGroupModel = new UserGroupModel();
				userGroupModel.setUserGroupId(NullUtils.cvStr(userDTO.getUserGroupId().getUserGroupId()));
				sessionUser.setUserGroupId(userGroupModel);
				
				userModel.setReturnCode("0000");
				userModel.setReturnMessage("Success");
				
				HttpSession session;
				session = request.getSession(true);
				session.setAttribute("sesionAuthen", sessionUser);
				
			} else {
				userModel.setReturnCode("0001");
				userModel.setReturnMessage("ไม่พบ User ที่ LDAP");
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.out.println(e.toString());
			userModel.setReturnCode("0001");
			userModel.setReturnMessage("ไม่พบ User ที่ LDAP");
		}
		
	}
	else{
		userModel.setReturnCode("0001");
		userModel.setReturnMessage("ไม่พบ User ที่ LDAP");
	}*/

		

	/*public Map<String, Object> submitUser(HttpServletRequest servletRequest, UserModel userModel) {
		UserDAO userDAO = null;
		SupplierDAO supplierDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		try {    		
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			supplierDAO = new SupplierDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			Map<String, Object> mapResult = new HashMap<>();
			boolean res = false;
			boolean resultDuplicateUserName = false;
			int userId = NullUtils.cvInt(userModel.getUserId());	
			UserModel userSession = (UserModel)servletRequest.getSession().getAttribute(StaticVariableUtils.Session_Key);			
			
			UserDTO userDTONew = new UserDTO();
			userDTONew.setUserId(userId);
			userDTONew.setEmployeeId(userModel.getEmployeeId());
			//userDTONew.setFullName(NullUtils.cvStr(userModel.getFullName()));
			userDTONew.setUsername(NullUtils.cvStr(userModel.getUsername()));
			userDTONew.setPassword(NullUtils.cvStr(userModel.getPassword()));
			userDTONew.setEmail(NullUtils.cvStr(userModel.getEmail()));
			userDTONew.setTelephone(NullUtils.cvStr(userModel.getTelephone()));
			//userDTONew.setStatusId(NullUtils.cvInt(userModel.getStatusId()));
			userDTONew.setDescription(NullUtils.cvStr(userModel.getDescription()));
			
			UserGroupDTO userGroupDTO = new UserGroupDTO();
			userGroupDTO.setUserGroupId(NullUtils.cvInt(userModel.getUserGroupId().getUserGroupId()));
			userGroupDTO.setUserGroupName(NullUtils.cvStr(userModel.getUserGroupId().getUserGroupName()));
			userDTONew.setUserGroupId(userGroupDTO);
			
			SupplierDTO supplier = new SupplierDTO();
			if(userModel.getSupplier() != null) {
			if(true) {
				//supplier.setSupplierId(NullUtils.cvInt(userModel.getSupplier().getSupplierId()));
				//int userParent = supplierDAO.getUserId(NullUtils.cvInt(userModel.getSupplier().getSupplierId()));
				//userDTONew.setUserParent(userParent);
			}else {
				supplier.setSupplierId(0);
			}
			//userDTONew.setSupplier(supplier);
			
			if (userId>0){
				res = userDAO.update(userDTONew);
			}else{
				resultDuplicateUserName = userDAO.getUserName(userDTONew.getUsername());
				if(!resultDuplicateUserName) {
					int user_id = userDAO.insert(userDTONew);
					if(user_id > 0) {
						SupplierUserMappingDTO supplierUserMappingDTO = new SupplierUserMappingDTO();
						//supplierUserMappingDTO.setSupplierId(NullUtils.cvInt(userModel.getSupplier().getSupplierId()));
						supplierUserMappingDTO.setUserId(NullUtils.cvInt(user_id));
						//supplierUserMappingDTO.setStatusId(NullUtils.cvInt(userModel.getSupplier().getStatusId()));
						supplierUserMappingDTO.setCreateBy(NullUtils.cvInt(userSession.getUserId()));
						supplierUserMappingDTO.setUpdateBy(NullUtils.cvInt(userSession.getUserId()));
						supplierUserMappingDAO.insert(supplierUserMappingDTO);
					}else {
						res = false;
					}
				}				
			}
			if(!res)
				DatabaseUtils.rollback(connection);
			mapResult.put("resultDuplicateUserName", resultDuplicateUserName);
			mapResult.put("resultProcess", res);
			return mapResult;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (userDAO != null) {
				userDAO.closeConnection();
			}
			if(supplierDAO != null) {
				supplierDAO.closeConnection();
			}
		}

	}
*/	
	

	
	
	
}
