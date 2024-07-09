package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
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

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.SupplierStandardDocumentController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/supplier_standard_document")
public class SupplierStandardDocumentAPI {

	private final static Logger logger = Logger.getLogger(SupplierStandardDocumentAPI.class);
	@Context private HttpServletRequest servletRequest;
	
	@GET
	@GZIP
	@Path("/supplier_standard_document_list/{objSupplierStandardDocument}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSupplierStandardDocumentList(@PathParam("objSupplierStandardDocument") String objSupplierStandardDocument) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			SupplierStandardDocumentController supplierStandardDocumentController = new SupplierStandardDocumentController();
			String resultJsonString = supplierStandardDocumentController.getSupplierStandardDocumentList(servletRequest, objSupplierStandardDocument);
			if(!StringUtils.isNullOrEmpty(resultJsonString)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJsonString);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch(Exception e) {
			logger.error("SupplierStandardDocumentAPI.getSupplierStandardDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@POST
	@Path("/insert_supplier_standard_document_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertSupplierStandardDocumentList(String supplierStandardDocumentListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {			
			SupplierStandardDocumentController supplierStandardDocumentController = new SupplierStandardDocumentController();
			boolean resultProcess = supplierStandardDocumentController.insertSupplierStandardDocumentList(servletRequest, supplierStandardDocumentListObj);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch(Exception e) {
			logger.error("SupplierStandardDocumentAPI.insertSupplierStandardDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_expire_date_supplier_standard_document")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateExpireDateSupplierStandardDocument(String supplierStandardDocumentObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SupplierStandardDocumentController supplierStandardDocumentController = new SupplierStandardDocumentController();
			boolean resultProcess = supplierStandardDocumentController.updateExpireDateSupplierStandardDocument(servletRequest, supplierStandardDocumentObj);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("SupplierStandardDocumentAPI.updateExpireDateSupplierStandardDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/delete_supplier_standard_document")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response deleteSupplierStandardDocument(@DefaultValue("0")@FormParam("key") int supplierStandardDocumentId) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			SupplierStandardDocumentController supplierStandardDocumentController = new SupplierStandardDocumentController();
			boolean result = supplierStandardDocumentController.deleteSupplierStandardDocument(supplierStandardDocumentId);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}	
		}catch(Exception e) {
			logger.error("SupplierStandardDocumentAPI.deleteSupplierStandardDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
