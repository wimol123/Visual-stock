package th.co.gosoft.audit.cpram.api;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import th.co.gosoft.audit.cpram.controller.SmartPOController;
import th.co.gosoft.audit.cpram.dto.PoDTO;
import th.co.gosoft.audit.cpram.dto.PoLogInterfaceDTO;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/smart_po")
public class SmartPOAPI {
	
	private final static Logger logger = Logger.getLogger(SmartPOAPI.class);
	private SmartPOController smartPOController = new SmartPOController();
	
	@Context private HttpServletRequest servletRequest;
	@Context private HttpServletResponse servletResponse;
	
	@GET
	@Path("/get_po_status_list/{userGroupId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getPoStatusList(@PathParam("userGroupId") String userGroupId) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.getPoStatusList for userGroupId : " + userGroupId);
			String resultString = smartPOController.getPoStatusList(userGroupId);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("SmartPOAPI.getPoStatusList Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_po_accepted_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getPoAcceptedList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.getPoAcceptedList");
			String resultString = smartPOController.getPoAcceptedList();
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("SmartPOAPI.getPoAcceptedList Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
		
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/search_po")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<PoDTO> searchPO(DataTablePostParamModel dataTablePostParamModel) {
		try {
			logger.info("SmartPOAPI.searchPO");
			DataTableModel<PoDTO> dataTableModel = smartPOController.searchPO(servletRequest, dataTablePostParamModel);
			return dataTableModel;
		} catch(Exception e) {
			logger.error("SmartPOAPI.searchPO Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	@POST
	@Path("/export_excel")
	public void exportExcel(String poSearchParam) {
		try {
			logger.info("SmartPOAPI.exportExcel");
			servletResponse.setContentType("application/octet-stream");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=po_export.xlsx";
	        servletResponse.setHeader(headerKey, headerValue);
	        smartPOController.exportReport(servletResponse, servletRequest, poSearchParam);
		} catch (Exception e) {
			logger.error("SmartPOAPI.exportExcel Exception : " + ExceptionUtils.stackTraceException(e));
		}
	}
	
	@POST
	@Path("/print_po")
	public void printPo( String poNoParam) {
		System.out.println(poNoParam);
		try {		

			logger.info("SmartPOAPI.printPo");
			String poNo = poNoParam.split("=")[1];
			servletResponse.setContentType("application/octet-stream");
	        String headerKey = "Content-Disposition";
	        String headerValue = " filename=" + poNo + ".pdf";
	        servletResponse.setHeader(headerKey, headerValue);
	        smartPOController.printPo(servletResponse, servletRequest, poNo);
	        
		} catch (Exception e) {
			logger.error("SmartPOAPI.printPo Exception : " + ExceptionUtils.stackTraceException(e));
		}
	}
	@POST
	@Path("/print_many_po")
	public void printAllPo(String poNoParam) {
		try {
			
			    logger.info("SmartPOAPI.printAllPo");
				System.out.println(poNoParam);
				String poNo = poNoParam.split("=")[1];
				servletResponse.setContentType("application/octet-stream");
		        String headerKey = "Content-Disposition";
		        String headerValue = " filename=" + "all_pos.zip";
		        servletResponse.setHeader(headerKey, headerValue);
		        //System.out.println(poNo);
		        System.out.println("dasdasdas");
			    smartPOController.printManyPo(servletResponse,servletRequest,poNo);

		} catch (Exception e) {
			logger.error("SmartPOAPI.printAllPo Exception : " + ExceptionUtils.stackTraceException(e));
		}
	}
	
	@POST
	@Path("/send_mail")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response sendMail(String poJson) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.sendMail");
			boolean result = smartPOController.sendMail(poJson);
			responseMessageModel.setResult(result);
			if (result) {
				responseMessageModel.setMessage(ResponseMessage.Process_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			} else {
				responseMessageModel.setMessage(ResponseMessage.Process_Fail);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}
		} catch (Exception e) {
			logger.error("SmartPOAPI.sendMail Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_po_info/{poId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getPoInfo(@PathParam("poId") String paramPoId) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.getPoInfo for poId : " + paramPoId);
			String resultString = smartPOController.getPo(servletRequest, paramPoId);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("SmartPOAPI.getPoInfo Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_po_detail/{poId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getPoDetail(@PathParam("poId") String paramPoId) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.getPoDetail for poId : " + paramPoId);
			String resultString = smartPOController.getPoDetail(paramPoId);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("SmartPOAPI.getPoDetail Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/get_po_history/{poId}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getPoHistory(@PathParam("poId") String paramPoId) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.getPoHistory for poId : " + paramPoId);
			String resultString = smartPOController.getPoHistory(paramPoId);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("SmartPOAPI.getPoHistory Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/supplier_accepted")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response supplierAccepted(String poDetailListJson) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.supplierAccepted");
			boolean result = smartPOController.supplierAccepted(servletRequest, poDetailListJson);
			responseMessageModel.setResult(result);
			if (result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			} else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}
		} catch (Exception e) {
			logger.error("SmartPOAPI.supplierAccepted Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	@POST
	@Path("/supplier_accepted_po")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response supplierAcceptedByPoId(String poDetailListJson) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			System.out.println(poDetailListJson);
			logger.info("SmartPOAPI.supplierAcceptedByPoId");
			boolean result = smartPOController.supplierAcceptedPo(servletRequest, poDetailListJson);
			responseMessageModel.setResult(result);
			if (result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			} else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}
		} catch (Exception e) {
			logger.error("SmartPOAPI.supplierAcceptedByPoId Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	
	@POST
	@Path("/purchasing_response")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response purchasingResponse(String poDetailListJson) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.purchasingResponse");
			boolean result = smartPOController.purchasingResponse(servletRequest, poDetailListJson);
			responseMessageModel.setResult(result);
			if (result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			} else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}
		} catch (Exception e) {
			logger.error("SmartPOAPI.purchasingResponse Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/import_po")
	@Consumes(MediaType.MULTIPART_FORM_DATA + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response importPO(MultipartFormDataInput multipartFormDataInput) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.importPO");
			String resultString = smartPOController.importPO(servletRequest, multipartFormDataInput);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("SmartPOAPI.importPO Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/import_po_history")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getImportPoHistory() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("SmartPOAPI.getImportPoHistory");
			String resultString = smartPOController.getImportPoHistory();
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("SmartPOAPI.getImportPoHistory Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/download_file_po")
	public void downloadFilePo(String params) {
		try {
			logger.info("SmartPOAPI.downloadFilePo");
			String[] param = params.split("&");
			PoLogInterfaceDTO poLog = new PoLogInterfaceDTO();
			poLog.setFileName(param[0].split("=")[1]);
			poLog.setType(param[1].split("=")[1]);
	        smartPOController.downloadFilePo(servletResponse, poLog);
		} catch (Exception e) {
			logger.error("SmartPOAPI.downloadFilePo Exception : " + ExceptionUtils.stackTraceException(e));
		}
	}
	
}
