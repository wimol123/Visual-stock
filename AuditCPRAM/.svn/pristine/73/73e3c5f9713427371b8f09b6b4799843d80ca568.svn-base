package th.co.gosoft.audit.cpram.api;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.controller.MaterialController;
import th.co.gosoft.audit.cpram.dao.MaterialDAO;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;
@Path("/material")
public class MaterialAPI {
	private final static Logger logger = Logger.getLogger(MaterialDAO.class);
	private MaterialController materialController = new MaterialController();
	
	
	@GET
	@Path("/get_material_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getMaterialList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("MaterialAPI.getPoAcceptedList");
			String resultString = materialController.getMaterialList();
			responseMessageModel.setResult(true);
			responseMessageModel.setMessage(resultString.trim());
			return Response.status(Status.OK).entity(responseMessageModel).build();
		} catch (Exception e) {
			logger.error("MaterialAPI.getMaterialList Exception : " + ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
		
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}