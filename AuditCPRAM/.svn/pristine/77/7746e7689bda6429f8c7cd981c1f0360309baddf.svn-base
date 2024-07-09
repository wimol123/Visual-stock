package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import th.co.gosoft.audit.cpram.controller.FileUploadController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/upload")
public class FileUploadAPI {

	private final static Logger logger = Logger.getLogger(FileUploadAPI.class);

	@Context private HttpServletRequest servletRequest;
	
	@POST
	@Path("/picture")
	@Consumes(MediaType.MULTIPART_FORM_DATA+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response uploadPicture(MultipartFormDataInput multipartFormDataInput) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			FileUploadController fileUploadController = new FileUploadController();
			boolean resultUpload = fileUploadController.uploadPicture(multipartFormDataInput);
			responseMessageModel.setResult(resultUpload);
			if(resultUpload) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("FileUploadAPI.uploadPicture() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/file")
	@Consumes(MediaType.MULTIPART_FORM_DATA+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response uploadFile(MultipartFormDataInput multipartFormDataInput) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		
		try {
			
			FileUploadController fileUploadController = new FileUploadController();
			boolean resultUpload = fileUploadController.uploadFile(multipartFormDataInput);
			responseMessageModel.setResult(resultUpload);
			if(resultUpload) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("FileUploadAPI.uploadFile() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
