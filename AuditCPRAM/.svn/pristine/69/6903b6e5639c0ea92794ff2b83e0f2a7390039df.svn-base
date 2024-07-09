package th.co.gosoft.audit.cpram.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import java.util.regex.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.UserController;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;

@Path("/user")
public class UserAPI {
	private final static Logger logger = Logger.getLogger(AuthenAPI.class);
    @Context private HttpServletRequest servletRequest;

	@POST
	@Path("/authen")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response authen(String objUser) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try{
			UserController userController = new UserController();
			String resultJSONUserModel = userController.authen(servletRequest, objUser);		
			if(!StringUtils.isNullOrEmpty(resultJSONUserModel)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSONUserModel);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		} catch (Exception e) {
			logger.error("UserAPI.authen() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/datatable/get_user_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<UserModel> dataTableGetUserList(DataTablePostParamModel dataTablePostParamModel){
		try {
			UserController userController = new UserController();
			return userController.dataTableGetUserList(servletRequest, dataTablePostParamModel);			
		}catch(Exception e) {
			logger.error("UserAPI.dataTableGetUserList() Exception : "+ExceptionUtils.stackTraceException(e));		
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/datatable/get_user_auditor_type")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<UserModel> datatableGetUserAuditorType(DataTablePostParamModel dataTablePostParamModel){
		try {
			
			UserController userController = new UserController();
			return userController.datatableGetUserAuditorType(dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("UserAPI.datatableGetUserAuditorType() Exception : "+ExceptionUtils.stackTraceException(e));			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/datatable/entourge_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<UserModel> datatableGetEntourgeList(DataTablePostParamModel dataTablePostParamModel){
		try {
			
			UserController userController = new UserController();
			return userController.datatableGetEntourgeList(dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("UserAPI.datatableGetEntourgeList() Exception : "+ExceptionUtils.stackTraceException(e));			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	
	@POST
	@Path("/insert_user")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertUser(String objUser) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		Pattern Pattern_check = Pattern.compile("^[ก-๏a-zA-Z0-9\\s()&/._%+-]+$");
		Gson gson = new Gson();
		try {
			UserController userController = new UserController();
			UserModel userModelRequest = gson.fromJson(objUser, UserModel.class);
			String fullname = userModelRequest.getFullname();
			if(!Pattern_check.matcher(fullname).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			String username = userModelRequest.getUsername();
			if(!Pattern_check.matcher(username).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			Map<String, Object> result = userController.insertUser(servletRequest, objUser);
			
			if(!(Boolean)result.get("DupplicateUsername")) {
				if(!(Boolean)result.get("DupplicateSupplierAdmin")) {				
					if((Boolean) result.get("ExistingUserLDAP")) {
						if((Boolean) result.get("resultProcess")) {
							responseMessageModel.setResult(true);
							responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
						}else {
							responseMessageModel.setResult(false);
							responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
						}
					}else {
						responseMessageModel.setResult(false);
						responseMessageModel.setMessage(ResponseMessage.UserName_LDAP_Non_Existing);
					}
				}else {
					responseMessageModel.setResult(false);
					responseMessageModel.setMessage(ResponseMessage.SupplierAdmin_Existing);
				}			
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.UserName_Existing);
			}
			
			return Response.status(Status.OK).entity(responseMessageModel).build();
		}catch(Exception e) {
			logger.error("UserAPI.insertUser() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_user")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateUser(String objUser) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		Pattern Pattern_check = Pattern.compile("^[ก-๏a-zA-Z0-9\\s()&/._%+-]+$");
		Gson gson = new Gson();
		try {
			
			UserController userController = new UserController();
			UserModel userModelRequest = gson.fromJson(objUser, UserModel.class);
			String fullname = userModelRequest.getFullname();
			if(!Pattern_check.matcher(fullname).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			Map<String, Object> result = userController.updateUser(servletRequest, objUser);
			
			if((Boolean)result.get("exitsUserLDAP")) {
				if(!(Boolean)result.get("exitsUseranmeUser")) {
					if(!(Boolean)result.get("exitsSupplierAdmin")) {
						if((Boolean)result.get("resultProcess")) {
							responseMessageModel.setResult(true);
							responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
							return Response.status(Status.OK).entity(responseMessageModel).build();
						}else {
							responseMessageModel.setResult(false);
							responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
							return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
						}
					}else {
						responseMessageModel.setResult(false);
						responseMessageModel.setMessage(ResponseMessage.SupplierAdmin_Existing);
						return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
					}
				}else {
					responseMessageModel.setResult(false);
					responseMessageModel.setMessage(ResponseMessage.UserName_Existing);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.UserName_LDAP_Non_Existing);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}				
		}catch(Exception e) {
			logger.error("UserAPI.updateUser() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@POST
	@Path("/user_change_password")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response userChangePassword(String objUser) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			UserController userController = new UserController();
			Map<String, Boolean> result = userController.userChangePassword(objUser);
			
			if(!result.get("checkIsAdmin")) {
				if(result.get("statusProcess")) {
					responseMessageModel.setResult(true);
					responseMessageModel.setMessage(ResponseMessage.Change_Password_Success);
					return Response.status(Status.OK).entity(responseMessageModel).build();
				}else {
					responseMessageModel.setResult(false);
					responseMessageModel.setMessage(ResponseMessage.Change_Password_Unsuccess);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Change_Password_Admin);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();				
			}
			
			
			
		}catch(Exception e) {
			logger.error("UserAPI.userChangePassword() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/user_change_password_manual")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response userChangePasswordManual(String objUser) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			UserController userController = new UserController();
			Map<String, Boolean> result = userController.userChangePasswordManual(objUser);
			
			if(result.get("statusProcess")) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(ResponseMessage.Change_Password_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Change_Password_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("UserAPI.userChangePasswordManual() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET 
	@Path("/get_session_user")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSessionUser() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {			
			UserModel userSession = (UserModel)servletRequest.getSession().getAttribute(StaticVariableUtils.Session_Key);
			String resultJSONUserModel = new Gson().toJson(userSession);
			if(!StringUtils.isNullOrEmpty(resultJSONUserModel)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSONUserModel);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
			
		}catch(Exception e) {
			logger.error("UserAPI.getSessionUser() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/get_permission_menu")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getPermissionMenu(String web) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			UserModel userSession = (UserModel)servletRequest.getSession().getAttribute(StaticVariableUtils.Session_Key);
			String resultJSONUserModel = new Gson().toJson(userSession);
			if(userSession.getUserMenu().contains(web)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSONUserModel);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}		
		}catch(Exception e) {
			logger.error("UserAPI.getPermissionMenu() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.OK).entity(responseMessageModel).build();
		}
	}

		
	@GET
	@Path("/user_deatil_by_id/{userObj}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getUserDeatilById(@PathParam("userObj") String objUser) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			UserController userController = new UserController();
			String resultJSONUserModel = userController.getUserDeatilById(objUser);
			if(!StringUtils.isNullOrEmpty(resultJSONUserModel)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSONUserModel);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("UserAPI.getUserDeatilById() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@DELETE
	@Path("/user_delete")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response deleteUser(String objUser) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			UserController userController = new UserController();
			boolean result = userController.deleteUser(servletRequest,objUser);
			responseMessageModel.setResult(result);
			
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("UserAPI.deleteUser() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/updatePdpaStatus")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updatePdpaStatus(int UserID) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		
		logger.info("Update :" +UserID);
		
		try {
			
			UserController userController = new UserController();
			boolean resultProcess = userController.updatePdpaStatus(servletRequest,UserID);
			
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}	
			
			
		}catch (Exception e) {
			logger.error("UserAPI.updatePdpaStatus() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
			
		
			
	}
	
	@POST
	@Path("/updateCancelPdpaStatus")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateCancelPdpaStatus(int UserID) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		
		logger.info("Update :" +UserID);
		
		try {
			
			UserController userController = new UserController();
			boolean resultProcess = userController.updateCancelPdpaStatus(servletRequest,UserID);
			
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}	
			
			
		}catch (Exception e) {
			logger.error("UserAPI.updatePdpaStatus() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
			
		
			
	}
	
	
}
