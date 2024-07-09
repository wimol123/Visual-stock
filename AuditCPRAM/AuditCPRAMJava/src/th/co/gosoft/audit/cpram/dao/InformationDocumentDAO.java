package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.InformationDTO;
import th.co.gosoft.audit.cpram.dto.InformationDocumentDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;

public class InformationDocumentDAO extends StandardAttributeDAO{
	
	private final static Logger logger = Logger.getLogger(InformationDocumentDAO.class);

	public InformationDocumentDAO(Connection connection) {
		super(connection);
	}
	
	public List<InformationDocumentDTO> getInformationDocumentList(String informationId,String informationDocumentType){
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT inf_doc.information_document_id, inf_doc.information_id, inf_doc.information_document_location, inf_doc.information_document_type ");
			query.append("FROM ").append(DBConst.TABLE_Information_Document).append(" inf_doc ");
			query.append("WHERE inf_doc.information_id = ? and inf_doc.information_document_type = ? ");
			query.append(String.format(" order by inf_doc.information_document_id asc ;"));
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setString(index++, informationId);
			preparedStatement.setString(index++, informationDocumentType);
			resultSet = preparedStatement.executeQuery();
			List<InformationDocumentDTO> informationDocumentDTOs = new ArrayList<>();
			while (resultSet.next()) {
				InformationDocumentDTO informationDocumentDTO = new InformationDocumentDTO();
				informationDocumentDTO.setInformationDocumentId(resultSet.getInt("information_document_id"));
				informationDocumentDTO.setInformationId(new InformationDTO());
				informationDocumentDTO.getInformationId().setInformationId(resultSet.getInt("information_id"));
				informationDocumentDTO.setInformationDocumentLocation(resultSet.getString("information_document_location"));
				informationDocumentDTO.setInformationDocumentType(resultSet.getString("information_document_type"));
				informationDocumentDTOs.add(informationDocumentDTO);
			}
			return informationDocumentDTOs;
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
	
	public boolean insertInformationDocument(InformationDocumentDTO informationDocumentDTO) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Information_Document).append(" ");
			query.append("(information_id, information_document_location, information_document_type, create_by, create_date) ");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, informationDocumentDTO.getInformationId().getInformationId());
			preparedStatement.setString(index++, informationDocumentDTO.getInformationDocumentLocation());
			preparedStatement.setString(index++, informationDocumentDTO.getInformationDocumentType());
			preparedStatement.setInt(index++, informationDocumentDTO.getCreateBy());
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
	
	public String getInformationDocument(int informationDocumentId) {
		try {
			String loaction = null;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT information_document_location FROM ").append(DBConst.TABLE_Information_Document).append(" ");
			query.append("WHERE information_document_id = ?; ");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, informationDocumentId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				 loaction = resultSet.getString("information_document_location");
			}
			return  loaction;
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
	

	public boolean deleteInformationDocument(int informationDocumentId) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Information_Document).append(" ");
			query.append("WHERE (information_document_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, informationDocumentId);
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
