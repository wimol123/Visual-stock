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

import th.co.gosoft.audit.cpram.controller.QuestionTypeChecklistTypeMappingController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/question_type_checklist_type_mapping")
public class QuestionTypeChecklistTypeMappingAPI {

	private final static Logger logger = Logger.getLogger(QuestionTypeChecklistTypeMappingAPI.class);
	
	@GET
	@Path("/question_type_by_checklist_type/{objChecklistType}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getQuestionTypeByChecklistType(@PathParam("objChecklistType") String objChecklistType) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			QuestionTypeChecklistTypeMappingController questionTypeChecklistTypeMappingController = new QuestionTypeChecklistTypeMappingController();
			String resultObj = questionTypeChecklistTypeMappingController.getQuestionTypeByChecklistType(objChecklistType);
			
			if(!StringUtils.isNullOrEmpty(resultObj)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultObj);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("QuestionTypeChecklistTypeMappingAPI.getQuestionTypeByChecklistType() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
