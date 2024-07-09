package th.co.gosoft.audit.cpram.api;

import java.util.Map;
import java.util.regex.Pattern;

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

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.ChecklistController;
import th.co.gosoft.audit.cpram.model.ChecklistModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;


@Path("/checklist")
public class ChecklistAPI {
	
	private final static Logger logger = Logger.getLogger(ChecklistAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
		
	@POST
	@Path("/datatabale/checklist_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<ChecklistModel> dataTableGetChecklistList(DataTablePostParamModel dataTablePostParamModel){
		try {
						
			ChecklistController checklistController = new ChecklistController();
			return checklistController.dataTableGetChecklistList(dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("ChecklistAPI.dataTableGetChecklistList() Exception : "+ExceptionUtils.stackTraceException(e));		
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/datatable/checklist_by_product_type")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<ChecklistModel> datatableChecklistByProductType(DataTablePostParamModel dataTablePostParamModel){
		try {
			
			ChecklistController checklistController = new ChecklistController();
			return checklistController.datatableChecklistByProductType(dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("ChecklistAPI.datatableChecklistByProductType() Exception : "+ExceptionUtils.stackTraceException(e));	
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/insert_checklist")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertChecklist(String objChecklist) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		String REGEX = ".*script.*";
		Pattern Pattern_check = Pattern.compile("^[ก-๏a-zA-Z0-9\\s()&/._%+-]+$");
		Pattern Pattern_nomatch = Pattern.compile(REGEX);
		Gson gson = new Gson();
		try {
			ChecklistController checklistController = new ChecklistController();
			ChecklistModel checklistModelRequest = gson.fromJson(objChecklist, ChecklistModel.class);	
			String checklistTitle = checklistModelRequest.getChecklistTitle();
			if(!Pattern_check.matcher(checklistTitle).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			String approveSupplierRule = checklistModelRequest.getApproveSupplierRule();
			if(Pattern_nomatch.matcher(approveSupplierRule).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			Map<String, Boolean> result = checklistController.insertChecklist(servletRequest,objChecklist);
			
			if(result.get("validateEffectiveDate")) {
				responseMessageModel.setResult(result.get("resultProcess"));
				if(result.get("resultProcess")) {
					responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
					return Response.status(Status.OK).entity(responseMessageModel).build();
				}else {
					responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Effective_Invalid);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}		
			
		}catch (Exception e) {
			logger.error("ChecklistAPI.insertChecklist() Exception : "+ExceptionUtils.stackTraceException(e));	
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_checklist")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateChecklist(String objChecklist) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		String REGEX = ".*script.*";
		Pattern Pattern_check = Pattern.compile("^[ก-๏a-zA-Z0-9\\s()&/._%+-]+$");
		Pattern Pattern_nomatch = Pattern.compile(REGEX);
		Gson gson = new Gson();
		try {
			logger.info("ChecklistAPI.updateChecklist() String Request : "+objChecklist);
			ChecklistController checklistController = new ChecklistController();
			ChecklistModel checklistModelRequest = gson.fromJson(objChecklist, ChecklistModel.class);	
			String checklistTitle = checklistModelRequest.getChecklistTitle();
			if(!Pattern_check.matcher(checklistTitle).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			String approveSupplierRule = checklistModelRequest.getApproveSupplierRule();
			if(Pattern_nomatch.matcher(approveSupplierRule).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			Map<String, Boolean> result = checklistController.updateChecklist(servletRequest, objChecklist);
			
			if(result.get("validateEffectiveDate")) {
				responseMessageModel.setResult(result.get("resultProcess"));
				if(result.get("resultProcess")) {
					responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
					return Response.status(Status.OK).entity(responseMessageModel).build();
				}else {
					responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Effective_Invalid);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}	
			
		}catch (Exception e) {
			logger.error("ChecklistAPI.updateChecklist() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	@GET
	@Path("/checklist_list/{objChecklist}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getChecklistList(@PathParam("objChecklist") String objChecklist) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			ChecklistController checklistController = new ChecklistController();
			String resultJSON = checklistController.getChecklistList(objChecklist);
			if(!StringUtils.isNullOrEmpty(resultJSON)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSON);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("ChecklistAPI.getChecklistList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@DELETE
	@Path("/delete_checklist")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response deleteChecklist(String objChecklist) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			ChecklistController checklistController = new ChecklistController();
			boolean result = checklistController.deleteChecklist(objChecklist);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("ChecklistAPI.deleteChecklist() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
/*	@GET
	@Path("/get_checklist_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<ChecklistModel> getCheckList_List() {
		try {
			ChecklistController checklistController = new ChecklistController();
			
			return checklistController.GetChecklistList();
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}*/
	
	/*@POST
	@Path("/datatabale/get_checklist_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<ChecklistModel> getCheckList_List(DataTablePostParamModel modelDataTable) {
		try {
			ChecklistController checklistController = new ChecklistController();
			return checklistController.GetChecklistList(modelDataTable);
			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}*/
	
/*	@GET
	@Path("/searchchecklist/{searchOption}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<ChecklistModel> searchChecklist(@PathParam("searchOption") String searchOption) {
		try {
			ChecklistController checklistController = new ChecklistController();
			return checklistController.searchChecklist(searchOption);
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}*/
	
	/*@POST
	@Path("/insert_checklist/{json_string}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertChecklist(@PathParam("json_string") String json_string) {
		try {
			ChecklistController checklistController = new ChecklistController();
			checklistController.InsertChecklist(json_string);
			return Response.status(201).build();
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(201).entity("Error : "+e.getMessage()).build();
		}
	}
	
	@POST
	@Path("/update_checklist/{json_string}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateChecklist(@PathParam("json_string") String json_string) {
		try {
			ChecklistController checklistController = new ChecklistController();
			checklistController.UpdateChecklist(json_string);
			return Response.status(201).build();
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(201).entity("Error : "+e.getMessage()).build();
		}
	}
	
	@DELETE
	@Path("/delete_checklist/{checklistId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response deleteChecklist(@PathParam("checklistId") String checklistId) {
		try {
			ChecklistController checklistController = new ChecklistController();
			checklistController.DeleteChecklist(checklistId);
			return Response.status(201).build();
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			return Response.status(201).entity("Error : "+e.getMessage()).build();
		}
	}	*/
	
	

}
