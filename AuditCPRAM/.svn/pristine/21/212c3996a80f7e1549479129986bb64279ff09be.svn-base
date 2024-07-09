package th.co.gosoft.audit.cpram.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.AppointController;
import th.co.gosoft.audit.cpram.controller.ChecklistPlanController;
import th.co.gosoft.audit.cpram.model.AppointModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/appoint")
public class AppointAPI {
	private final static Logger logger = Logger.getLogger(AppointAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@POST
	@Path("/datatable/appoint_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<AppointModel> datatableGetAppointList(DataTablePostParamModel dataTablePostParamModel){
		try {
			
			AppointController appointController = new AppointController();
			return appointController.datatableGetAppointList(servletRequest, dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("AppointAPI.datatableGetAppointList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@POST
	@Path("/insert_appoint")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertAppoint(String appointObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			AppointController appointController = new AppointController();
			Map<String, Boolean> result = appointController.insertAppoint(servletRequest, appointObj);
			if(result.get("checkAppointDate")) {
				if(result.get("resultProcess")) {
					responseMessageModel.setResult(true);
					responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
					return Response.status(Status.OK).entity(responseMessageModel).build();
				}else {
					responseMessageModel.setResult(false);
					responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}		
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.AppointDateTime_invalid);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
				
		}catch(Exception e) {
			logger.error("AppointAPI.insertAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_appoint")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateAppoint(String objApoint) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			AppointController appointController = new AppointController();
			Map<String, Boolean> result = appointController.updateAppoint(servletRequest, objApoint);
			
			if(result.get("checkAppointDate")) {
				responseMessageModel.setResult(result.get("resultProcess"));
				if(result.get("resultProcess")) {				
					responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
					return Response.status(Status.OK).entity(responseMessageModel).build();
				}else {
					responseMessageModel.setResult(false);
					responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.AppointDateTime_invalid);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
			
			
		}catch(Exception e) {
			logger.error("AppointAPI.updateAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_appoint_detail/{objAppoint}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAppointDetail(@PathParam("objAppoint") String objAppoint) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			AppointController appointController = new AppointController();
			String resultJson = appointController.getAppointDetail(servletRequest, objAppoint);
			if(!StringUtils.isNullOrEmpty(resultJson)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJson);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("AppointAPI.getAppointDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@DELETE
	@Path("/delete_appoint")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response deleteAppoint(String objAppoint) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			AppointController appointController = new AppointController();
			boolean result = appointController.deleteAppoint(objAppoint);
			
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
			
		}catch(Exception e) {
			logger.error("AppointAPI.deleteAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_plandate_checklist_plan")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updatePlanDateOnCheckListPlan(String checklistPlanObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			ChecklistPlanController checklistPlanController = new ChecklistPlanController();
			boolean result = checklistPlanController.updatePlanDateChecklistPlan(checklistPlanObj);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			} else {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		} catch (Exception e) {
			logger.error("ChecklistPlanAPI.updatePlanDateCheckListPlan() Exception : "+ExceptionUtils.stackTraceException(e));	
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
}
