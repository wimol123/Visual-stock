package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.UserGroupController;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/user_group")
public class UserGroupAPI {
	
	private final static Logger logger = Logger.getLogger(UserGroupAPI.class);
    @Context private HttpServletRequest servletRequest;
	
	@GET
	@Path("/user_group_list")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response userGroupList() {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {			
			UserGroupController userGroupController = new UserGroupController();
			String userGroupModelJSON = userGroupController.getUserGroupList(servletRequest);
			if(!StringUtils.isNullOrEmpty(userGroupModelJSON)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(userGroupModelJSON);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch(Exception e) {
			logger.error("UserAPI.userGroupList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}

}
