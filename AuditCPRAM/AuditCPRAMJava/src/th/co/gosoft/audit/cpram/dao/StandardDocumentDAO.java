package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.StandardDocumentDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class StandardDocumentDAO extends StandardAttributeDAO {
	public StandardDocumentDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(StandardDocumentDAO.class);
	
	public List<StandardDocumentDTO> getStandardDocumentList(Integer startRecord, Integer numOfRows, String queryWhereClause) {
		//SELECT st_doc.standard_document_id, st_doc.standard_document_name, st_doc.enable, st_doc.create_by, st_doc.create_date, st_doc.update_by, st_doc.update_date FROM standard_document st_doc WHERE 1=1
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT * ");
			query.append("FROM ").append(DBConst.TABLE_Standard_Document).append(" st_doc ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}			
			if(numOfRows != -1) {
				query.append(String.format("order by st_doc.seq asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<StandardDocumentDTO> standardDocumentDTOs = new ArrayList<>();
			while(resultSet.next()) {
				StandardDocumentDTO standardDocumentDTO = new  StandardDocumentDTO();
				standardDocumentDTO.setStandardDocumentId(resultSet.getInt("standard_document_id"));
				standardDocumentDTO.setStandardDocumentName(resultSet.getString("standard_document_name"));
				standardDocumentDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				standardDocumentDTO.setCreateBy(resultSet.getInt("create_by"));
				standardDocumentDTO.setCreateDate(resultSet.getDate("create_date"));
				standardDocumentDTO.setCreateTime(resultSet.getTime("create_date"));
				standardDocumentDTO.setUpdateBy(resultSet.getInt("update_by"));
				standardDocumentDTO.setUpdateDate(resultSet.getDate("update_date"));
				standardDocumentDTO.setUpdateTime(resultSet.getTime("update_date"));
				standardDocumentDTOs.add(standardDocumentDTO);
			}			
			return standardDocumentDTOs;
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
	
	public int countStandardDocumentList(String queryWhereClause) {
		int totalStandardDocument = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Standard_Document).append(" st_doc ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalStandardDocument = resultSet.getInt("total");
			}
			return totalStandardDocument;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalStandardDocument;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
}
