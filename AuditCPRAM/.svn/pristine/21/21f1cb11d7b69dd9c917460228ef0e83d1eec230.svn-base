package th.co.gosoft.audit.cpram.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.jboss.resteasy.annotations.GZIP;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.AddressController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/address")
public class AddressAPI {
	
	private final static Logger logger = Logger.getLogger(AddressAPI.class);
	
	@GET	
	@Path("/get_address_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@GZIP
	public Response getAddressList(){
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			logger.info("### Request API : /api/address/get_address_list ###");
			logger.debug("### AddressAPI.getAddressList() ###");
			AddressController addressController = new AddressController();
			String result = addressController.getAddressList();
			if(!StringUtils.isNullOrEmpty(result)) {
				logger.info("### Result Json String Is Not Null ###");
				logger.debug("### Output JSON String : "+result.toString().trim()+" ###");
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(result);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				logger.warn("### Result Json String Is Null ###");
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error(" Request API : /api/address/get_address_list Error : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
}
