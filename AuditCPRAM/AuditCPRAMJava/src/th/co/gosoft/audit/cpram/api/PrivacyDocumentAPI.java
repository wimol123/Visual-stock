package th.co.gosoft.audit.cpram.api;

import java.io.File;
import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.ManualDocumentController;
import th.co.gosoft.audit.cpram.controller.PrivacyDocumentController;
import th.co.gosoft.audit.cpram.dto.TermsDocumentDTO;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/privacy_document")
public class PrivacyDocumentAPI {
	
	private final static Logger logger = Logger.getLogger(PrivacyDocumentAPI.class);
	@Context private HttpServletRequest servletRequest;
	
	
	@GET
	@GZIP
	@Path("/privacy_document_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getPrivacyDocumentList(){
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			PrivacyDocumentController PrivacyDocumentController = new PrivacyDocumentController();
			String resultJson = PrivacyDocumentController.getPrivacyDocumentList();
			
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
			logger.error("PrivacyDocumentAPI.getPrivacyDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@GZIP
	@Path("/get_url_pdpa")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getUrlPdpa(){
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			PrivacyDocumentController PrivacyDocumentController = new PrivacyDocumentController();
			String resultJson = PrivacyDocumentController.getUrlPdpa();
			
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
			logger.error("PrivacyDocumentAPI.getPrivacyDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@GZIP
	@Path("/get_terms_document")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getTermsDocument(){
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			PrivacyDocumentController PrivacyDocumentController = new PrivacyDocumentController();
			String result = PrivacyDocumentController.getTermsDocument();
			logger.info("path file : "+result);
						
			byte[] fileContent = FileUtils.readFileToByteArray(new File(result));
			
			if(!StringUtils.isNullOrEmpty(result)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setFile(fileContent);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("PrivacyDocumentAPI.getTermsDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
