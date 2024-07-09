package th.co.gosoft.audit.cpram.testUnit;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.Connection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.UserDAO;
import th.co.gosoft.audit.cpram.dto.SystemSequenceDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.SystemSequenceGenerator;

public class TestController {
	public void testConnnection() {
		Connection connection = null;
		TestDAO testDAO = null;
		Test2DAO test2dao = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			testDAO = new TestDAO(connection);
			test2dao = new Test2DAO(connection);
			testDAO.testConnection();
			//test2dao.test();
			//connection.commit();
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			// TODO: handle exception
		}finally {
			if(connection != null) {
				//connection.close();
				DatabaseUtils.closeConnection(connection);
			}
			
			if(testDAO != null)
				testDAO.closeConnection();
			if(test2dao != null)
				test2dao.closeConnection();
		}
	}
	
	public void getImageUrl(HttpServletRequest httpServletRequest) throws IOException {
		URL website = new URL("https://www.journaldev.com/sitemap.xml");
		ReadableByteChannel rbc = Channels.newChannel(website.openStream());
		FileOutputStream fos = new FileOutputStream("D:\\AuditCPRam\\Supplier\\Logo\\logo_20190114_112852542.png");
		 fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
	     fos.close();
	     rbc.close();
	}
	
	
	public List<SystemSequenceDTO> testLockRow() {
		Connection connection = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			//connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
			TestDAO testDAO = new TestDAO(connection);
			List<SystemSequenceDTO> systemSequenceDTOs = testDAO.LockRowQuery();
			
			return systemSequenceDTOs;
		}catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public List<SystemSequenceDTO> testLockRow2(){
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			//connection.setTransactionIsolation(connection.TRANSACTION_REPEATABLE_READ);
			TestDAO testDAO = new TestDAO(connection);
			
			List<SystemSequenceDTO> systemSequenceDTOs =testDAO.Select();
			return systemSequenceDTOs;
		}catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public void getKey() {
		Connection connection = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			String result = SystemSequenceGenerator.getInstance().generator(connection, StaticVariableUtils.sequenceKeyChecklistPlan);
			System.out.println(result);
			DatabaseUtils.commit(connection);
		}catch (Exception e) {
			// TODO: handle exception
			if(connection != null)
				DatabaseUtils.rollback(connection);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public void getUser() {
		Connection connection = null;
		UserDAO userDAO = null;
		Gson gson = null;
		try {
			
			gson = new Gson();
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userDAO = new UserDAO(connection);
			List<UserDTO> userDTOs = userDAO.getAllUserList(0, userDAO.countAllUser(""), "");
			System.out.println(gson.toJson(userDTOs));
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
}
