package th.co.gosoft.audit.cpram.api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.DateTimeController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/date_time")
public class DateTimeAPI {
	private final static Logger logger = Logger.getLogger(DateTimeAPI.class);
	
	@POST
	//@Path("/current_date_time/{format_date_time}")
	@Path("/current_date_time")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	//public Response getCurrentDate(@PathParam("format_date_time") String format) {
	public Response getCurrentDate(String format) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			DateTimeController dateTimeController = new DateTimeController();
			String currentDate = dateTimeController.getCurrentDateTimeUsingFormat(format);
			if(!StringUtils.isNullOrEmpty(currentDate)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(currentDate);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("DateTimeAPI.getCurrentDate() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
}
