package th.co.gosoft.audit.cpram.api;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

import th.co.gosoft.audit.cpram.controller.AppointController;
import th.co.gosoft.audit.cpram.controller.CarDetailController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/car_detail")
public class CarDetailAPI {
	
	private final static Logger logger = Logger.getLogger(CarDetailAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@POST
	@Path("/update_state")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateStateCarDetail(String carDetailObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			CarDetailController carDetailController = new CarDetailController();
			boolean result = carDetailController.updateStateCarDetail(servletRequest, carDetailObj);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("CarAPI.updateStateCarDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_due_date")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateDueDateCarDetail(String carDetailObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			CarDetailController carDetailController = new CarDetailController();
			boolean resultUpdate = carDetailController.updateDueDateCarDetail(servletRequest, carDetailObj);
			responseMessageModel.setResult(resultUpdate);
			if(resultUpdate) {				
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}			
			
		}catch (Exception e) {
			logger.error("CarDetailAPI.updateDueDateCarDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@GET
	@Path("/car_detail_list/{objCarDetail}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getCarDetailList(@PathParam("objCarDetail") String carDetailObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			CarDetailController carDetailController = new CarDetailController();
			String resultJsonString = carDetailController.getCarDetailList(carDetailObj,"getDetail");
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultJsonString);
			return Response.status(Status.OK).entity(responseMessageModel).build();
			
		}catch (Exception e) {
			logger.error("CarAPI.getCarDetailList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_car_detail")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertAppoint(String carDetailObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {

			CarDetailController carDetailController = new CarDetailController();
			boolean result = carDetailController.editCarDetail(servletRequest,carDetailObj);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}
				
		}catch(Exception e) {
			logger.error("AppointAPI.insertAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}

}
