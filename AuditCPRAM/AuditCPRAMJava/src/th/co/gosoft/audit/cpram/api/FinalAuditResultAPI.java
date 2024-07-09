package th.co.gosoft.audit.cpram.api;

import java.io.File;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.FinalAuditResultController;
import th.co.gosoft.audit.cpram.controller.SystemLogController;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.FinalAuditResultModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/final_audit_result")
public class FinalAuditResultAPI {
	
	@Context private HttpServletRequest servletRequest;
	
	private static Logger logger = Logger.getLogger(FinalAuditResultAPI.class);
	
	@POST
	@GZIP
	@Path("/datatable/final_audit_result_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<FinalAuditResultModel> datatableGetFinalAuditResultList(DataTablePostParamModel dataTablePostParamModel){
		try {
			FinalAuditResultController finalAuditResultController = new FinalAuditResultController();
			return finalAuditResultController.datatableGetFinalAuditResultList(servletRequest, dataTablePostParamModel);
		}catch (Exception e) {
			logger.error("FinalAuditResultAPI.datatableGetFinalAuditResultList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@POST
	@Path("/update_grade")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateGrade(String objFinalAuditResult) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			FinalAuditResultController finalAuditResultController = new FinalAuditResultController();
			boolean resultProcess = finalAuditResultController.updateGrade(servletRequest, objFinalAuditResult);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("FinalAuditResultAPI.updateGrade() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_final_audit_result")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateFinalAuditResult(String objFinalAuditResult) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			FinalAuditResultController finalAuditResultController = new FinalAuditResultController();
			boolean resultProcess = finalAuditResultController.updateFinalAuditResult(servletRequest, objFinalAuditResult);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("FinalAuditResultAPI.updateFinalAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/final_audit_result_detail/{objFinalAuditResult}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getFinalAuditResultDetail(@PathParam("objFinalAuditResult") String objFinalAuditResult) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			FinalAuditResultController finalAuditResultController = new FinalAuditResultController();
			String resultJsonSring = finalAuditResultController.getFinalAuditResultDetail(objFinalAuditResult);
			if(!StringUtils.isNullOrEmpty(resultJsonSring)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJsonSring);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("FinalAuditResultAPI.getFinalAuditResultDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/check_access_final_audit_result/{objCheckAccess}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response checkAccessFinalAuditResult(@PathParam("objCheckAccess") String objCheckAccess) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			FinalAuditResultController finalAuditResultController = new FinalAuditResultController();
			String resultCheckAccess = finalAuditResultController.checkAccessFinalAuditResult(objCheckAccess);
			
			if(!StringUtils.isEmptyOrWhitespaceOnly(resultCheckAccess)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultCheckAccess);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage("");
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("FinalAuditResultAPI.checkAccessFinalAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	/*@GET
	@Path("/print_final_audit_result/{finalAuditResultRequestModel}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response printFinalAuditResult(@PathParam("finalAuditResultRequestModel") String finalAuditResultRequestModel) {
		try {
			FinalAuditResultController finalAuditResultController = new FinalAuditResultController();
			File fileOutputStreamPdf = finalAuditResultController.printFinalAuditResult(servletRequest, finalAuditResultRequestModel);
			
			ResponseBuilder response = Response.ok((Object)fileOutputStreamPdf);
			response.header("Content-Type", "application/force-download");
			response.header("Content-Type", "application/pdf");
			response.header("Content-Transfer-Encoding", "binary");
		    response.header("Content-Disposition", "attachment;filename=" + fileOutputStreamPdf.getName().trim());
		    
		    return response.build();
		    //attachment
		}catch (Exception e) {
			logger.error("FinalAuditResultAPI.printFinalAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
	        //return response.build();
			return null;
			//responseMessageModel.setResult(false);
			//responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			//return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}*/
	@POST
	@Path("/print_final_audit_result")
	//@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response printFinalAuditResult(String checklistPlanNo) {
		try {
			String finalAuditResultRequestModel = URLDecoder.decode(checklistPlanNo.split("=")[1], StandardCharsets.UTF_8.name());
			FinalAuditResultController finalAuditResultController = new FinalAuditResultController();
			File fileOutputStreamPdf = finalAuditResultController.printFinalAuditResult(servletRequest, finalAuditResultRequestModel);
			
			ResponseBuilder response = Response.ok((Object)fileOutputStreamPdf);
			response.header("Content-Type", "application/force-download");
			response.header("Content-Type", "application/pdf");
			response.header("Content-Transfer-Encoding", "binary");
		    response.header("Content-Disposition", "attachment;filename=" + fileOutputStreamPdf.getName().trim());
		    
		    return response.build();
		    //attachment
		}catch (Exception e) {
			logger.error("FinalAuditResultAPI.printFinalAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
	        //return response.build();
			return null;
			//responseMessageModel.setResult(false);
			//responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			//return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@GET
	@Path("/final_audit_result_log_list/{objChecklistDetail}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getFinalAuditResultLogList(@PathParam("objChecklistDetail") String checklistDetail) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SystemLogController systemLogController = new SystemLogController();
			String resultJsonString =  systemLogController.getFinalAuditResultLogList(checklistDetail);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultJsonString);
			return Response.status(Status.OK).entity(responseMessageModel).build();
			
		}catch (Exception e) {
			logger.error("FinalAuditResultAPI.getFinalAuditResultLogList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
