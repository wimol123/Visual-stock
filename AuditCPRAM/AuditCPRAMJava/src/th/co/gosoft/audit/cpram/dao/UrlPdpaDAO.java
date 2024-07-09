package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.PrivacyDocumentDTO;
import th.co.gosoft.audit.cpram.dto.UrlPdpaDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;

public class UrlPdpaDAO extends StandardAttributeDAO{

	public UrlPdpaDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(ManualDocumentDAO.class);	
	
	
	public UrlPdpaDTO getUrlPdpa() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT system_key , system_value ");
			query.append("FROM ").append(DBConst.TABLE_System_Configuration);
			query.append(" WHERE system_key = 'system.url.pdpa' ");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			UrlPdpaDTO result = new UrlPdpaDTO();
			while(resultSet.next()) {
				result.setSystem_key(resultSet.getString("system_key"));
				result.setSystem_value(resultSet.getString("system_value"));
			}
			
			return result;
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
