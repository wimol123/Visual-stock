package th.co.gosoft.audit.cpram.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.controller.DownloadController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/download")
public class DownloadAPI {
	private final static Logger logger = Logger.getLogger(DownloadAPI.class);
	@GET
	@Path("/logo_supplier/{ObjSupplier}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getLogoSupplier(@PathParam("ObjSupplier") String objSupplier) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			DownloadController downloadController = new DownloadController();
			String imagePath = downloadController.getLogoSupplier(objSupplier);
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(imagePath);
			return Response.status(Status.OK).entity(responseMessageModel).build();
			/*if(!StringUtils.isNullOrEmpty(imagePath)) {				
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(imagePath);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
			}*/
			
		}catch (Exception e) {
			logger.error("DownloadAPI.getLogoSupplier() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}

}
