package th.co.gosoft.audit.cpram.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import th.co.gosoft.audit.cpram.controller.SmartPOController;
import th.co.gosoft.audit.cpram.controller.VisualStockController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/visual_stock")
public class VisualStockAPI {

	private final static Logger logger = Logger.getLogger(SmartPOAPI.class);
	private VisualStockController visualStockController = new VisualStockController();
	
	@Context private HttpServletRequest servletRequest;
	@Context private HttpServletResponse servletResponse;
	
	
	@GET
	@Path("/get_auto_supplier_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAutoSupplierList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("VisualStockAPI.getAutoSupplierList for userGroupId : ");
			String resultString = visualStockController.getSupplierList();
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("VisualStockAPI.getAutoSupplierList Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_auto_material_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAutoMaterialList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("VisualStockAPI.getAutoMaterialList for userGroupId : ");
			String resultString = visualStockController.getMaterialList();
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("VisualStockAPI.getAutoMaterialList Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_auto_plant_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAutoPlantList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("VisualStockAPI.getAutoPlantList for userGroupId : ");
			String resultString = visualStockController.getPlantList();
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("VisualStockAPI.getAutoPlantList Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_search_table_list/{supplierInput}/{meterialInput}/{plantInput}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSearchTableList(@PathParam("supplierInput") String supplierInput, @PathParam("meterialInput") String meterialInput, @PathParam("plantInput")  String plantInput) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("VisualStockAPI.getSearchTableList for supplier_input: " + supplierInput);
			logger.info("VisualStockAPI.getSearchTableList for meterial_input: " + meterialInput);
			logger.info("VisualStockAPI.getSearchTableList for plant_input: " + plantInput);

			
			String resultString = visualStockController.getSearchTableList(supplierInput,meterialInput,plantInput);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
			
		} catch (Exception e) {
			logger.error("VisualStockAPI.getAutoPlantList Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	

	@GET
	@Path("/get_list_window_time/{supplierInput}/{meterialInput}/{plantInput}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getListWindowTime(@PathParam("supplierInput") String supplierInput, @PathParam("meterialInput") String meterialInput, @PathParam("plantInput")  String plantInput) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("VisualStockAPI.getListWindowTime for supplier_input: " + supplierInput);
			logger.info("VisualStockAPI.getListWindowTime for meterial_input: " + meterialInput);
			logger.info("VisualStockAPI.getListWindowTime for plant_input: " + plantInput);
//			logger.info("VisualStockAPI.getSearchTableList for status_input: " + statusInput);
			String resultString = visualStockController.getListWindowTime(supplierInput,meterialInput,plantInput);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
			
		} catch (Exception e) {
			logger.error("VisualStockAPI.getListWindowTime Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_search_table_list_with_time/{supplierInput}/{meterialInput}/{plantInput}/{timeList}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSearchTableListWithTime(@PathParam("supplierInput") String supplierInput, @PathParam("meterialInput") String meterialInput, @PathParam("plantInput")  String plantInput ,@PathParam("timeList")  String timeList) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("VisualStockAPI.getListWindowTime for supplier_input: " + supplierInput);
			logger.info("VisualStockAPI.getListWindowTime for meterial_input: " + meterialInput);
			logger.info("VisualStockAPI.getListWindowTime for plant_input: " + plantInput);
			logger.info("VisualStockAPI.getListWindowTime for time_list: " + timeList);

			String resultString = visualStockController.getSearchTableListWithTime(supplierInput,meterialInput,plantInput,timeList);
	           logger.info(resultString);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
			
		} catch (Exception e) {
			logger.error("VisualStockAPI.getListWindowTime Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/export_excel")
	public void exportExcel(String visualStockSearchParam) {
		try {
			logger.info("VisualStockAPI.exportExcel");
			servletResponse.setContentType("application/octet-stream");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=visual_stock_export.xlsx";
	        servletResponse.setHeader(headerKey, headerValue);
	        visualStockController.exportReport(servletResponse, servletRequest, visualStockSearchParam);
		} catch (Exception e) {
			logger.error("SmartPOAPI.exportExcel Exception : " + ExceptionUtils.stackTraceException(e));
		}
	}

	
}