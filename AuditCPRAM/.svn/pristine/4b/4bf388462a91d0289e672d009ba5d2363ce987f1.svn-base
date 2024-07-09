package th.co.gosoft.audit.cpram.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.QuestionaireDocumentController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/questionaire_document")
public class QuestionaireDocumentAPI {
	
	private final static Logger logger = Logger.getLogger(QuestionaireDocumentAPI.class);

	@GET
	@Path("/questionaire_document_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@GZIP
	public Response getQuestionaireDocumentList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			QuestionaireDocumentController questionaireDocumentController = new QuestionaireDocumentController();
			String resultJson = questionaireDocumentController.getQuestionaireDocumentList();
			if(!StringUtils.isNullOrEmpty(resultJson)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJson);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("QuestionaireDocumentAPI.getQuestionaireDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
