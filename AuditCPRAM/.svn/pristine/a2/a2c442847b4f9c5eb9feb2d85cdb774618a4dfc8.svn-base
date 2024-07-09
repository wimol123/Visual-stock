package th.co.gosoft.audit.cpram.api;

import java.util.Map;

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

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.EvalFormController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/eval_form")
public class EvalFormAPI {
	
	private final static Logger logger = Logger.getLogger(EvalFormAPI.class);
	
	@Context private HttpServletRequest servletRequest;

	
	@POST
	@Path("/update_eval_form_by_uniqueId")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateEvalFormByUniqueId(String evalFormObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			EvalFormController evalFormController = new EvalFormController();
			Map<String, Object> resultMap = evalFormController.updateEvalFormByUniqueId(servletRequest, evalFormObj);
			if((Boolean)resultMap.get("processQuestionType")) {
				if((Boolean)resultMap.get("processAnswer")) {
					if((Boolean)resultMap.get("processStatus")) {
						responseMessageModel.setResult(true);
						responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
						return Response.status(Status.OK).entity(responseMessageModel).build();
					}else {
						responseMessageModel.setResult(false);
						responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
						return Response.status(Status.OK).entity(responseMessageModel).build();
					}
				}else {
					responseMessageModel.setResult(false);
					responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("EvalFormAPI.updateEvalFormByUniqueId() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/eval_form_list/{evalFormObj}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getEvalFormList(@PathParam("evalFormObj") String evalFormObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			EvalFormController evalFormController = new EvalFormController();
			String resultJSON = evalFormController.getEvalFormList(evalFormObj);
			if(!StringUtils.isNullOrEmpty(resultJSON)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSON);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("EvalFormAPI.getEvalFormList() Exception : "+ExceptionUtils.stackTraceException(e));	
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
