package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.DocumentDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class DocumentDAO extends StandardAttributeDAO {
	public DocumentDAO(Connection connection) {
		super(connection);
	}
	
	private final static Logger logger = Logger.getLogger(DocumentDAO.class);
	
	public List<DocumentDTO> getDocumentList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT * ");
			query.append("FROM ").append(DBConst.TABLE_Standard_Document);
			query.append(" order by `seq` asc ;");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<DocumentDTO> DocumentDTOs = new ArrayList<>();
			while(resultSet.next()) {
				DocumentDTO DocumentDTO = new  DocumentDTO();
				DocumentDTO.setDocumentId(resultSet.getInt("standard_document_id"));
				DocumentDTO.setDocumentName(resultSet.getString("standard_document_name"));
				DocumentDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				DocumentDTOs.add(DocumentDTO);
			}
			return DocumentDTOs;
			
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
	
	
	
	
	public boolean updateDocument(DocumentDTO DocumentDTO , int num) {
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Standard_Document).append(" ");
			
			query.append("SET `seq` = ?, ");
			query.append("`update_by` = ? ,");
			
			query.append("`enable` = ? ,");
			query.append("`update_date` = now() ");
			
			query.append(" WHERE (`standard_document_id` = ?); ");		
			logger.info(query);
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			
			preparedStatement.setInt(index++, num);
			preparedStatement.setInt(index++, DocumentDTO.getUpdateBy());
			preparedStatement.setString(index++, NullUtils.cvStr(DocumentDTO.getEnable()));
			preparedStatement.setInt(index++, DocumentDTO.getDocumentId());
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public boolean InsertDocument(DocumentDTO DocumentDTO) {
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Standard_Document).append(" ");
			query.append("( standard_document_name , seq , enable , create_by , create_date , update_by , update_date )");
			query.append(" VALUES ");
			query.append("( ?, ?, ?, ?, now(), ?, now() );");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			
			preparedStatement.setString(index++, DocumentDTO.getDocumentName());
			preparedStatement.setInt(index++,  DocumentDTO.getSeq() );
			preparedStatement.setString(index++, NullUtils.cvStr(DocumentDTO.getEnable()));
			preparedStatement.setInt(index++, DocumentDTO.getCreateBy());
			preparedStatement.setInt(index++, DocumentDTO.getUpdateBy());
			
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
}
