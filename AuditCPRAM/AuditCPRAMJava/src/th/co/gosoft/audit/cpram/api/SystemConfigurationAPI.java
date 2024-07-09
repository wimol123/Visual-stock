package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
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
import org.jboss.resteasy.annotations.GZIP;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.SystemConfigurationController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/system_configuration")
public class SystemConfigurationAPI {
	
	private final static Logger logger = Logger.getLogger(SystemConfigurationAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@GET
	@Path("/system_configuration_by_key/{configurationKey}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@GZIP
	public Response getSystemConfigurationByKey(@PathParam("configurationKey") String configurationObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SystemConfigurationController systemConfigurationController = new SystemConfigurationController();
			String result = systemConfigurationController.getSystemConfigurationByKey(servletRequest, configurationObj);
			if(!StringUtils.isNullOrEmpty(result)) {				
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(result);
				return Response.status(Status.OK).entity(responseMessageModel).build();				
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch(Exception e) {
			logger.error("SystemConfigurationAPI.getSystemConfigurationByKey() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_system_configuration")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateSystemConfiguration(String configurationSystemObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			SystemConfigurationController systemConfigurationController = new SystemConfigurationController();
			boolean result = systemConfigurationController.updateSystemConfigurationSystem(servletRequest, configurationSystemObj);
			
			if(result) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();		
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();		
			}
			
		}catch(Exception e) {
			logger.error("SystemConfigurationAPI.updateSystemConfiguration() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/reload_system_configuration")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response reloadSystemConfiguration() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SystemConfigurationController systemConfigurationController = new SystemConfigurationController();
			boolean result = systemConfigurationController.reloadSystemConfiguration(servletRequest);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Reload_config_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();		
			}else {
				responseMessageModel.setMessage(ResponseMessage.Reload_config_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();	
			}			
		}catch(Exception e) {
			logger.error("SystemConfigurationAPI.reloadSystemConfiguration() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
}
