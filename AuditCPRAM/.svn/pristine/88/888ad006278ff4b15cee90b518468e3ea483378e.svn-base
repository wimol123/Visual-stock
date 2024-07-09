package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.StandardDocumentController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/standard_document")
public class StandardDocumentAPI {
	private final static Logger logger = Logger.getLogger(StandardDocumentAPI.class);
	@Context private HttpServletRequest servletRequest;	
	
	@GET
	@GZIP
	@Path("/standard_documet_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getStandardDocumentList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			StandardDocumentController standardDocumentController = new StandardDocumentController();
			String resultJson = standardDocumentController.getStandardDocumentList();
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
			logger.error("StandardDocumentAPI.getStandardDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
