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

import th.co.gosoft.audit.cpram.controller.AppointEntourageController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/appoint_entourage")
public class AppointEntourageAPI {
	
	private final static Logger logger = Logger.getLogger(AppointEntourageAPI.class);
	
	@Context private HttpServletRequest servletRequest;

	@GET
	@Path("/get_entourage_by_appoint/{objAppoint}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getEntourageByAppoint(@PathParam("objAppoint") String objAppoint) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			AppointEntourageController appointEntourageController = new AppointEntourageController();
			String resultJson = appointEntourageController.getEntourageByAppoint(objAppoint);
			
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
			logger.error("AppointEntourageAPI.getEntourageByAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
