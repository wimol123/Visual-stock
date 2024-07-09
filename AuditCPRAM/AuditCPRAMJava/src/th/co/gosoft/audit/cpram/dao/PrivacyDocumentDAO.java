package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.ManualDocumentDTO;
import th.co.gosoft.audit.cpram.dto.PrivacyDocumentDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;

public class PrivacyDocumentDAO extends StandardAttributeDAO{

	public PrivacyDocumentDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(ManualDocumentDAO.class);	
	
	
	public List<PrivacyDocumentDTO> getPrivacyDocumentList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT system_key , system_value ");
			query.append("FROM ").append(DBConst.TABLE_System_Configuration);
			query.append(" WHERE system_key = 'system.path_file.path_privacy_document' ");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			
			List<PrivacyDocumentDTO> PrivacyDocumentDTOs = new ArrayList<>();
			while(resultSet.next()) {
				PrivacyDocumentDTO PrivacyDocumentDTO = new  PrivacyDocumentDTO();
				PrivacyDocumentDTO.setSystem_key(resultSet.getString("system_key"));
				PrivacyDocumentDTO.setSystem_value(resultSet.getString("system_value"));
				PrivacyDocumentDTOs.add(PrivacyDocumentDTO);
			}
			
			return PrivacyDocumentDTOs;
			
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
