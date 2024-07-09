package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
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

import th.co.gosoft.audit.cpram.controller.GradeChecklistTypeMappingController;
import th.co.gosoft.audit.cpram.controller.GradeController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/grade")
public class GradeAPI {
	private final static Logger logger = Logger.getLogger(GradeAPI.class);
	@Context private HttpServletRequest servletRequest;
	
	@GET
	@Path("/grade_list/{objChecklistType}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@GZIP
	public Response getGradeListByChecklistType(@PathParam("objChecklistType") String objChecklistType) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			GradeChecklistTypeMappingController gradeChecklistTypeMappingController = new GradeChecklistTypeMappingController();
			String resultJSON = gradeChecklistTypeMappingController.getGradeListByChecklistType(objChecklistType);
			
			if(!StringUtils.isNullOrEmpty(resultJSON)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSON);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
			
		}catch(Exception e) {
			logger.error("GradeAPI.getGradeListByChecklistType() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/grade_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@GZIP
	public Response getGradeList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			GradeController gradeController = new GradeController();
			String resultJSON = gradeController.getGradeList();
			
			if(!StringUtils.isNullOrEmpty(resultJSON)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSON);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
			
		}catch(Exception e) {
			logger.error("GradeAPI.getGradeList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
