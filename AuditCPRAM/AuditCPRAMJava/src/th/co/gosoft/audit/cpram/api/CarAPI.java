package th.co.gosoft.audit.cpram.api;

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
import org.jboss.resteasy.annotations.GZIP;

import th.co.gosoft.audit.cpram.controller.CarController;
import th.co.gosoft.audit.cpram.model.CarModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/car")
public class CarAPI {

	@Context private HttpServletRequest servletRequest;
	
	private static Logger logger = Logger.getLogger(CarAPI.class);
	
	@POST
	@GZIP
	@Path("/datatable/car_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<CarModel> dataTableGetCarList(DataTablePostParamModel dataTablePostParamModel){
		try {
			
			CarController carController = new CarController();
			return carController.dataTableGetCarList(servletRequest, dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("CarAPI.dataTableGetCarList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@POST
	@Path("/update_state_car")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateStateCar(String carObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			CarController carController = new CarController();
			boolean result = carController.updateStateCar(servletRequest, carObj);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch(Exception e) {
			logger.error("CarAPI.updateStateCar() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/car_list/{carObj}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getCarList(@PathParam("carObj")String carObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			CarController carController = new CarController();
			String resultJsonString = carController.getCarList(carObj);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultJsonString);
			return Response.status(Status.OK).entity(responseMessageModel).build();
			
		}catch(Exception e) {
			logger.error("CarAPI.getCarList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
