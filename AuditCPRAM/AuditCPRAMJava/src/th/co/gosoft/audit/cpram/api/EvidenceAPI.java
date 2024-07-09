package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.EvidenceController;
import th.co.gosoft.audit.cpram.model.EvidenceModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/evidence")
public class EvidenceAPI {
	
	private final static Logger logger = Logger.getLogger(EvidenceAPI.class);

	@Context private HttpServletRequest servletRequest;
	
	@POST
	@Path("/get_evidence")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEvidence(String evidenceObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			EvidenceController evidenceController = new EvidenceController();
			String result = evidenceController.getEvidence(servletRequest, evidenceObj);
			if(!StringUtils.isNullOrEmpty(result)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(result);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("EvidenceAPI.insertEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@POST
	@Path("/insert_evidence")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertEvidence(String evidenceListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			EvidenceController evidenceController = new EvidenceController();
			boolean resultProcess = evidenceController.insertEvidence(servletRequest, evidenceListObj);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("EvidenceAPI.insertEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@POST
	@Path("/update_evidence")
	@Consumes(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response updateEvidence(String evidenceListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			EvidenceController evidenceController = new EvidenceController();
			boolean resultProcess = evidenceController.updateEvidence(servletRequest, evidenceListObj);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("EvidenceAPI.updateEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@POST
	@GZIP
	@Path("/evidence_solve_car")
	@Consumes(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response getEvidenceSolveCar(String evidenceObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			EvidenceController evidenceController = new EvidenceController();
			String resultJsonString = evidenceController.getEvidenceSolveCar(evidenceObj);
			if(!StringUtils.isNullOrEmpty(resultJsonString)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJsonString);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("EvidenceAPI.getEvidenceSolveCar() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@DELETE
	@Path("/delete_evidence")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteEvidence(String evidenceObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			EvidenceController evidenceController = new EvidenceController();
			boolean resultProcess = evidenceController.deleteEvidence(evidenceObj);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("EvidenceAPI.deleteEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/delete_evidence_document")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response deleteEvidenceDocument(@DefaultValue("0")@FormParam("key") int evidenceId) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			
			EvidenceModel evidenceModel = new EvidenceModel();
			evidenceModel.setEvidenceId(NullUtils.cvStr(evidenceId));
			String evidenceObj = new Gson().toJson(evidenceModel);
			
			EvidenceController evidenceController = new EvidenceController();	
			boolean resultProcess = evidenceController.deleteEvidence(evidenceObj);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("EvidenceAPI.deleteEvidence() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
