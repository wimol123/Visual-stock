package th.co.gosoft.audit.cpram.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.CryptographyController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/cryptography")
public class CryptographyAPI {
	private final static Logger logger = Logger.getLogger(CryptographyAPI.class);
	
	@POST
	@Path("/encrypt_rsa")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response encryptRSA(String stringEncoder) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			CryptographyController cryptographyController = new CryptographyController();
			String stringEncode = cryptographyController.encryptRSA(stringEncoder.trim());
			if(!StringUtils.isNullOrEmpty(stringEncode)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(stringEncode);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("CryptographyAPI.encryptRSA() Exception : "+ExceptionUtils.stackTraceException(e));	
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/decrypt_rsa")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response decryptRSA(String stringEncoder) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {		
			CryptographyController cryptographyController = new CryptographyController();
			String stringDecode = cryptographyController.decryptRSA(stringEncoder);
			if(!StringUtils.isEmptyOrWhitespaceOnly(stringEncoder)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(stringDecode);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("CryptographyAPI.decryptRSA() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
