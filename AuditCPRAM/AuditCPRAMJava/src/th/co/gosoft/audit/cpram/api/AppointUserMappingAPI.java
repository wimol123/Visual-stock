package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.AppointUserMappingController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/appoint_mapping_user")
public class AppointUserMappingAPI {
	
	private final static Logger logger = Logger.getLogger(AppointUserMappingAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@GET
	@Path("/get_auditor_mapping_appoint/{objAppoint}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAuditorMappingAppoint(@PathParam("objAppoint") String objAppoint) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			AppointUserMappingController appointUserMappingController = new AppointUserMappingController();
			String resultJson = appointUserMappingController.getAuditorMappingAppoint(objAppoint);
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
			logger.error("AppointUserMappingAPI.getAuditorMappingAppoint() Exception : "+ExceptionUtils.stackTraceException(e));		
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}

}
