package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
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

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.InformationController;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.InformationDetailModel;
import th.co.gosoft.audit.cpram.model.InformationModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/information")
public class InformationAPI {
	
	private final static Logger logger = Logger.getLogger(InformationAPI.class);

	@Context private HttpServletRequest servletRequest;

	@POST
	@Path("/get_information")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getInformation(String informationListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			InformationController informationController = new InformationController();
			String result = informationController.getInformation(servletRequest, informationListObj);
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
			logger.error("InformationAPI.getInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@POST
	@Path("/insert_information")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertInformation(String informationListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			InformationController informationController = new InformationController();
			String result = informationController.insertInformation(servletRequest, informationListObj);
			if(!StringUtils.isNullOrEmpty(result)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(result);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("InformationAPI.insertInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@POST
	@Path("/update_information")
	@Consumes(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response updateInformation(String informationListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			InformationController informationController = new InformationController();
			boolean resultProcess = informationController.updateInformation(servletRequest, informationListObj);
			responseMessageModel.setResult(resultProcess);
			if(resultProcess) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("InformationAPI.updateInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/insert_information_document_list")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertInformationDocumentList(String informationDocumentListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			InformationController informationController = new InformationController();
			boolean result = informationController.insertInformationDocumentList(servletRequest, informationDocumentListObj);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch (Exception e) {
			logger.error("InformationAPI.insertInformationDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/datatable/information_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<InformationModel> dataTableGetInformationList(DataTablePostParamModel dataTablePostParamModel){
		try {
						
			InformationController informationController = new InformationController();
			return informationController.dataTableGetInformationList(dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("InformationAPI.dataTableGetInformationList() Exception : "+ExceptionUtils.stackTraceException(e));		
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	

	@POST
	@Path("/send_information")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response sendInformation(String informationListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			InformationController informationController = new InformationController();
			boolean result = informationController.sendInformation(servletRequest, informationListObj);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}		
		}catch (Exception e) {
			logger.error("InformationAPI.sendInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/get_information_document_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getInformationDocumentList(String informationListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			InformationController informationController = new InformationController();
			String result = informationController.getInformationDocumentList(servletRequest, informationListObj);
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
			logger.error("InformationAPI.getInformationDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}

	@POST
	@Path("/get_information_picture_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getInformationPictureList(String informationListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			InformationController informationController = new InformationController();
			String result = informationController.getInformationPictureList(servletRequest, informationListObj);
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
			logger.error("InformationAPI.getInformationPictureList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/delete_information_document")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED+ ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON+ ";charset=utf-8")
	public Response deleteInformationDocument( @DefaultValue("0")@FormParam("key") int informationDocumentId) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			InformationController informationController = new InformationController();
			boolean result = informationController.deleteInformationDocument(informationDocumentId);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("InformationAPI.deleteInformationDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
		
	}
	
	@POST
	@Path("/datatable/information_list_by_supplier")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<InformationDetailModel> dataTableGetInformationListBySupplier(DataTablePostParamModel dataTablePostParamModel){
		try {
						
			InformationController informationController = new InformationController();
			return informationController.dataTableGetInformationListBySupplier(servletRequest, dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("InformationAPI.dataTableGetInformationListBySupplier() Exception : "+ExceptionUtils.stackTraceException(e));		
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/accept_information")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response acceptInformation(String informationListObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			InformationController informationController = new InformationController();
			String result = informationController.acceptInformation(servletRequest, informationListObj);
			if(!StringUtils.isNullOrEmpty(result)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(result);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}		
		}catch (Exception e) {
			logger.error("InformationAPI.acceptInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
}
