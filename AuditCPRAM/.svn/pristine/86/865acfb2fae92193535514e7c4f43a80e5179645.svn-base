package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.QuestionaireDocumentDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class QuestionaireDocumentDAO extends StandardAttributeDAO{
	
	private final static Logger logger = Logger.getLogger(QuestionaireDocumentDAO.class);
	
	public QuestionaireDocumentDAO(Connection connection) {
		super(connection);
	}

	public List<QuestionaireDocumentDTO> getQuestionaireDocumentList(Integer startRecord, Integer numOfRows, String queryWhereClause){		
		//SELECT qd.questionaire_document_id, qd.questionaire_document_name, qd.enable, qd.create_by, qd.create_date, qd.update_by, qd.update_date FROM questionaire_document qd WHERE 1=1
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT qd.questionaire_document_id, qd.questionaire_document_name, qd.enable, qd.create_by, qd.create_date, qd.update_by, qd.update_date ");
			query.append("FROM ").append(DBConst.TABLE_Questionaire_Document).append(" qd ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by qd.questionaire_document_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<QuestionaireDocumentDTO> questionaireDocumentDTOList = new ArrayList<>();
			
			while(resultSet.next()) {
				QuestionaireDocumentDTO questionaireDocumentDTO = new QuestionaireDocumentDTO();
				questionaireDocumentDTO.setQuestionaireDocumentId(resultSet.getInt("questionaire_document_id"));
				questionaireDocumentDTO.setQuestionaireDocumentName(resultSet.getString("questionaire_document_name"));
				questionaireDocumentDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				questionaireDocumentDTO.setCreateBy(resultSet.getInt("create_by"));
				questionaireDocumentDTO.setCreateDate(resultSet.getDate("create_date"));
				questionaireDocumentDTO.setCreateTime(resultSet.getTime("create_date"));
				questionaireDocumentDTO.setUpdateBy(resultSet.getInt("update_by"));
				questionaireDocumentDTO.setUpdateDate(resultSet.getDate("update_date"));
				questionaireDocumentDTO.setUpdateTime(resultSet.getTime("update_date"));
				questionaireDocumentDTOList.add(questionaireDocumentDTO);
			}
			
			return questionaireDocumentDTOList;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	
	public int countQuestionaireDocumentList(String queryWhereClause) {
		int totalStandardDocument = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Questionaire_Document).append(" qd ");
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
