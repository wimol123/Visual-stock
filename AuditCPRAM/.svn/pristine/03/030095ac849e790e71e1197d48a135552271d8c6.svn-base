package th.co.gosoft.audit.cpram.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.AnswerController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/answer")
public class AnswerAPI {
	private final static Logger logger = Logger.getLogger(AnswerAPI.class);
	
	@GET
	@Path("/answer_list_in_question_type/{objQuestionType}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAnswerListInQuestionType(@PathParam("objQuestionType") String objQuestionType) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			AnswerController answerController = new AnswerController();
			String resultJson = answerController.getAnswerListInQuestionType(objQuestionType);
			
			if(!StringUtils.isEmptyOrWhitespaceOnly(resultJson)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJson);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("AnswerAPI.getAnswerListInQuestionType() Exception : "+ExceptionUtils.stackTraceException(e)); 
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/answer_list_grade_calculator/{objChecklistType}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getAnswerListGradeCalculator(@PathParam("objChecklistType") String objChecklistType) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			AnswerController answerController = new AnswerController();
			String resultObj = answerController.getAnswerListGradeCalculator(objChecklistType);
			if(!StringUtils.isNullOrEmpty(resultObj)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultObj);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
			
		}catch(Exception e) {
			logger.error("AnswerAPI.getAnswerListGradeCalculator() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
