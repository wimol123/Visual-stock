package th.co.gosoft.audit.cpram.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.controller.ChecklistPlanController;
import th.co.gosoft.audit.cpram.model.ChecklistPlanModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/checklist_plan")
public class ChecklistPlanAPI {
	
	private final static Logger logger = Logger.getLogger(ChecklistPlanAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@POST
	@Path("/datatabale/checklist_plan_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<ChecklistPlanModel> dataTableChecklistPlanList(DataTablePostParamModel dataTablePostParamModel) {
		try {
			
			ChecklistPlanController checklistPlanController = new ChecklistPlanController();
			return checklistPlanController.dataTableChecklistPlanList(servletRequest, dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("ChecklistPlanAPI.dataTableChecklistPlanList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/insert_checklist_plan")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertChecklistPlan(String objChecklistPlan) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			ChecklistPlanController checklistPlanController = new ChecklistPlanController();
			Map<String, Boolean> result = checklistPlanController.insertChecklistPlan(servletRequest, objChecklistPlan);
			
			
			if(result.get("resultSearchChecklist")) {
				if(result.get("resultOfProcess")) {
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
				responseMessageModel.setMessage(ResponseMessage.Checklist_Not_Exitsting);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch(Exception e) {
			logger.error("ChecklistPlanAPI.insertChecklistPlan() Exception : "+ExceptionUtils.stackTraceException(e));		
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/cancel_checklist_plan")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response cancelChecklistPlan(String checklistPlanObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			ChecklistPlanController checklistPlanController = new ChecklistPlanController();
			boolean result = checklistPlanController.cancelChecklistPlan(servletRequest, checklistPlanObj);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch(Exception e) {
			logger.error("ChecklistPlanAPI.cancelChecklistPlan() Exception : "+ExceptionUtils.stackTraceException(e));			
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
//	@POST
//	@Path("/update_plandate_checklist_plan")
//	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
//	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
//	public Response updatePlanDateOnCheckListPlan(String checklistPlanObj) {
//		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
//		try {
//			ChecklistPlanController checklistPlanController = new ChecklistPlanController();
//			boolean result = checklistPlanController.updatePlanDateChecklistPlan(checklistPlanObj);
//			if(result) {
//				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
//				return Response.status(Status.OK).entity(responseMessageModel).build();
//			} else {
//				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
//				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
//			}
//		} catch (Exception e) {
//			logger.error("ChecklistPlanAPI.updatePlanDateCheckListPlan() Exception : "+ExceptionUtils.stackTraceException(e));	
//			responseMessageModel.setResult(false);
//			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
//			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
//		}
//	}
	
}
