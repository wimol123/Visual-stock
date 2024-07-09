package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
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

import th.co.gosoft.audit.cpram.controller.AppointHistoryController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/appoint_history")
public class AppointHistoryAPI {
private final static Logger logger = Logger.getLogger(AppointHistoryAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@GET
	@Path("/appoint_history_list_by_appoint/{objAppoint}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response appointHistoryListByAppoint(@PathParam("objAppoint") String objAppoint){
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			AppointHistoryController appointHistoryController = new AppointHistoryController();
			String resultJson = appointHistoryController.appointHistoryListByAppoint(objAppoint);
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
			logger.error("AppointHistoryAPI.appointHistoryListByAppoint() Exception : "+ExceptionUtils.stackTraceException(e));		
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
