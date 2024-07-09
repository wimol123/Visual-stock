package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.ChecklistTypeController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/checklist_type")
public class ChecklistTypeAPI {
	
	private final static Logger logger = Logger.getLogger(ChecklistTypeAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@GET
	@Path("/checklist_type_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getChecklistTypeList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			ChecklistTypeController checklistTypeController = new ChecklistTypeController();
			String resultJSON = checklistTypeController.getChecklistTypeList();			
			if(!StringUtils.isNullOrEmpty(resultJSON)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSON);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("ChecklistTypeAPI.getChecklistTypeList() Exception : "+ExceptionUtils.stackTraceException(e));	
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
