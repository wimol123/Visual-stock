package th.co.gosoft.audit.cpram.api;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

@Path("/authen")
public class AuthenAPI {
	private final static Logger logger = Logger.getLogger(AuthenAPI.class);

    @Context private HttpServletRequest servletRequest;
    

	@GET
	@Path("/authen/{user}/{password}")
	public Response authen(@PathParam("user") String user, @PathParam("password") String password) {
		try{
//			AdminController loginController = new AdminController();
			
			logger.info("AuthenAPI..");
			
			return Response.status(201).entity("true").build();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}


}
