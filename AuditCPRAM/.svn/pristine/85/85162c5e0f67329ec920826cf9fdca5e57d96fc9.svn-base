package th.co.gosoft.audit.cpram.testUnit;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.cryptography.AsymmetricCryptography;
import th.co.gosoft.audit.cpram.dto.SystemSequenceDTO;
import th.co.gosoft.audit.cpram.ldap.LDAPConnector;
import th.co.gosoft.audit.cpram.utils.EncryptUtils;
import th.co.gosoft.audit.cpram.utils.PropertiesUtils;

@Path("/testapi")
public class TestAPI {
	
	private static Logger logger = Logger.getLogger(TestAPI.class);
	@Context 
	private HttpServletRequest httpServletRequest;
	
	
	@GET
	@Path("/testimg")
	public void testImg() {
		try {
			TextToImage.execute();
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	@GET
	@Path("/test_connection")
	public void testConnection() {
		TestController testController = new TestController();
		testController.testConnnection();
	}
	
	@GET
	@Path("/testldap/{username}")
	public void testLDAP(@PathParam("username") String username) {
		LDAPConnector ldapConnector = new LDAPConnector();
		try {
			ldapConnector.checkUserExitsInLDAP(username);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/test_contextPath")
	public void testContextPath() throws UnsupportedEncodingException {
		//logger.info(httpServletRequest.getServletContext().getContextPath());
		System.out.println(httpServletRequest.getServletContext().getContextPath());
		
	}
	
	@GET
	@Path("/image_url")
	public void getImageUrl() {
		try {
			TestController testController = new TestController();
			testController.getImageUrl(httpServletRequest);
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
		}
		
	}
	
	@GET
	@Path("/lock_row")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response testLockRow() {
		try{
			
			TestController testController = new TestController();
			List<SystemSequenceDTO> systemSequenceDTOs = testController.testLockRow();
			return Response.status(Status.OK).entity(systemSequenceDTOs).build();
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
	
	@GET
	@Path("/lock_row_2")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response testLockRow2() {
		try {
			
			TestController testController = new TestController();
			List<SystemSequenceDTO> systemSequenceDTOs = testController.testLockRow2();
			return Response.status(Status.OK).entity(systemSequenceDTOs).build();
		}catch (Exception e) {
			// TODO: handle exception
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}
	}
	
	@GET
	@Path("/get_key")
	public void testGetKeys() {
		TestController testController = new TestController();
		testController.getKey();
	}
	
	
	@GET
	@Path("/test_connection2")
	public void testConnection2() {
		TestController testController = new TestController();
		testController.getUser();
	}
	
	@GET
	@Path("/encript")
	public void encript() {
		try {
			AsymmetricCryptography asymmetricCryptography = new AsymmetricCryptography("RSA");
			//asymmetricCryptography.generateKeyPair(512);
			//String encoderString = asymmetricCryptography.encryptText("1", asymmetricCryptography.getPrivateKey(PropertiesUtils.getValuePropertiesByKey("auditcpram.secure.privatekey.path")));
			String decoderString = asymmetricCryptography.decryptText("a6g8Sj9Y+/mespA0ztRZtvh3wmiiNddVyooJvTZ30nL0H/lyD9ZG783WApmYaCvNvtSgD4Ut3JwdH5YahkjSPg", asymmetricCryptography.getPublicKey(PropertiesUtils.getValuePropertiesByKey("auditcpram.secure.publickey.path")));
			//System.out.println(encoderString);
			System.out.println(decoderString);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
