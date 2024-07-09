package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.ManualDocumentDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;

public class ManualDocumentDAO extends StandardAttributeDAO{

	public ManualDocumentDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(ManualDocumentDAO.class);	
	
	
	public List<ManualDocumentDTO> getManualDocumentList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT document_id , document_location , document_type ");
			query.append("FROM ").append(DBConst.TABLE_Manual_Document);
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<ManualDocumentDTO> ManualDocumentDTOs = new ArrayList<>();
			while(resultSet.next()) {
				ManualDocumentDTO ManualDocumentDTO = new  ManualDocumentDTO();
				ManualDocumentDTO.setDocumentid( resultSet.getInt("document_id") );
				ManualDocumentDTO.setDocumentlocation(resultSet.getString("document_location"));
				ManualDocumentDTO.setDocument_type(resultSet.getString("document_type"));
				ManualDocumentDTOs.add(ManualDocumentDTO);
			}
			
			return ManualDocumentDTOs;
			
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
	
	
	public boolean insertManualDocument(ManualDocumentDTO ManualDocumentDTO) {
		try {
			//INSERT INTO `supplier_standard_document` (`supplier_standard_document_location`, `supplier_id`, `standard_document_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('sdfsdfsdf', '12', '1', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Manual_Document).append(" ");
			query.append("(document_location, document_type , create_by, create_date) ");
			query.append(" VALUE ");
			query.append("(?, ?, ? , now());");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, ManualDocumentDTO.getDocumentlocation());
			preparedStatement.setString(index++, ManualDocumentDTO.getDocument_type());
			preparedStatement.setInt(index++, ManualDocumentDTO.getCreateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public String SelectManualDocument(int manualDocumentID) {
		try {
			String Answer = null;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT document_location FROM ").append(DBConst.TABLE_Manual_Document).append(" ");
			query.append("WHERE document_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, manualDocumentID);
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Answer = resultSet.getString("document_location");
			}
			return Answer;
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
	
	
	public boolean deleteManualDocument(int manualDocumentID) {
		try {
			//DELETE FROM `supplier_standard_document` WHERE (`supplier_standard_document_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Manual_Document).append(" ");
			query.append("WHERE (document_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, manualDocumentID);
			int rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}		
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
