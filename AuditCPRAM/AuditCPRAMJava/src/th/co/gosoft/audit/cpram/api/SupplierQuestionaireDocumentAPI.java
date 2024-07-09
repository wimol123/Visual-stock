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

import th.co.gosoft.audit.cpram.controller.SupplierQuestionaireDocumentController;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.SupplierQuestionaireDocumentModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/supplier_questionaire_document")
public class SupplierQuestionaireDocumentAPI {
	
	private final static Logger logger = Logger.getLogger(SupplierQuestionaireDocumentAPI.class);
	@Context private HttpServletRequest servletRequest;
	
	@GET
	@GZIP
	@Path("/supplier_questionaire_document_list/{objSupplierQuestionaireDocument}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSupplierQuestionaireDocumentList(@PathParam("objSupplierQuestionaireDocument") String objSupplierQuestionaireDocument) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SupplierQuestionaireDocumentController supplierQuestionaireDocumentController = new SupplierQuestionaireDocumentController();
			String resultResponse = supplierQuestionaireDocumentController.getSupplierQuestionaireDocumentList(servletRequest, objSupplierQuestionaireDocument);
			
			if(!StringUtils.isNullOrEmpty(resultResponse)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultResponse);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("SupplierQuestionaireDocumentAPI.getSupplierQuestionaireDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@GZIP
	@Path("/datatable/get_questionaire_document_mapping_supplier_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierQuestionaireDocumentModel> getQuestionaireDocumentMappingSupplierList(DataTablePostParamModel dataTablePostParamModel) {
		try {
			
			SupplierQuestionaireDocumentController supplierQuestionaireDocumentController = new SupplierQuestionaireDocumentController();
			return supplierQuestionaireDocumentController.getQuestionaireDocumentMappingSupplierList(dataTablePostParamModel);
			
		}catch (Exception e) {
			logger.error("SupplierQuestionaireDocumentAPI.getQuestionaireDocumentMappingSupplierList() Exception : "+ExceptionUtils.stackTraceException(e));		
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	
	@POST
	@Path("/insert_supplier_questionaire_document_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertSupplierQuestionaireDocumentList(String objSupplierQuestionaireDocumentList) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SupplierQuestionaireDocumentController supplierQuestionaireDocumentController = new SupplierQuestionaireDocumentController();
			boolean resultProcess = supplierQuestionaireDocumentController.insertSupplierQuestionaireDocumentList(servletRequest, objSupplierQuestionaireDocumentList);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("SupplierQuestionaireDocumentAPI.insertSupplierQuestionaireDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/delete_supplier_questionaire_document")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response deleteSupplierQuestionaireDocument(@DefaultValue("0")@FormParam("key") int supplierQuestionaireDocumentId) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SupplierQuestionaireDocumentController supplierQuestionaireDocumentController = new SupplierQuestionaireDocumentController();
			boolean result = supplierQuestionaireDocumentController.deleteSupplierQuestionaireDocument(supplierQuestionaireDocumentId);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("SupplierQuestionaireDocumentAPI.deleteSupplierQuestionaireDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	

}
