package th.co.gosoft.audit.cpram.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

import th.co.gosoft.audit.cpram.controller.StatusController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/status")
public class StatusAPI {
	
	private final static Logger logger = Logger.getLogger(StatusAPI.class);

	
	@GET
	@Path("/get_status_list/{statusname}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@GZIP
	public Response getStatusList(@PathParam("statusname") String objRequestString) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			logger.info("objRequestString Is : "+objRequestString);
			StatusController statusController = new StatusController();
			String resultString = statusController.getStatusList(objRequestString.trim());
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
			
		}catch (Exception e) {
			logger.error("StatusAPI.getStatusList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
