package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.controller.AuditResultController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/audit_result")
public class AuditResultAPI {
	
	private final static Logger logger = Logger.getLogger(AuditResultAPI.class);
	@Context private HttpServletRequest servletRequest;
	
	@POST
	@Path("/update_audit_result")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateAuditResult(String listObjAuditResult) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try{
			AuditResultController auditResultController = new AuditResultController();
			boolean result = auditResultController.updateAuditResult(servletRequest, listObjAuditResult);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch(Exception e) {
			logger.error("AuditResultAPI.updateAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
