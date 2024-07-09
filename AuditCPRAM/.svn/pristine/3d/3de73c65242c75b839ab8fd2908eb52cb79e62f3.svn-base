package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class QuestionTypeDAO extends StandardAttributeDAO{
	public QuestionTypeDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(QuestionTypeDAO.class);

	    
    public List<QuestionTypeDTO> getQuestionTypeList(Integer startRecord, Integer numOfRows, String queryWhereClause) {
    	try {
    		
    		//SELECT qt.question_type_id, qt.name, qt.enable, qt.create_by, qt.create_date, qt.update_by, qt.update_date FROM question_type qt WHERE 1=1
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT qt.question_type_id, qt.name, qt.enable, qt.create_by, qt.create_date, qt.update_by, qt.update_date ");
    		query.append("FROM ").append(DBConst.TABLE_Question_Type).append(" qt ");
    		query.append("WHERE 1=1 ");   		
    		
    		
    		if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
    			query.append(queryWhereClause);
    		}
    		if(numOfRows != -1) {
    			query.append(String.format(" order by qt.question_type_id asc limit %s,%s", startRecord,numOfRows));
    		}
    		query.append(";");
    		
    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		resultSet = preparedStatement.executeQuery();
    		
    		List<QuestionTypeDTO> questionTypeDTOs = new ArrayList<>();
    		while(resultSet.next()) {
    			QuestionTypeDTO questionTypeDTO = new QuestionTypeDTO();
    			questionTypeDTO.setCreateBy(resultSet.getInt("create_by"));
    			questionTypeDTO.setCreateDate(resultSet.getDate("create_date"));
    			questionTypeDTO.setCreateTime(resultSet.getTime("create_date"));
    			questionTypeDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
    			questionTypeDTO.setName(resultSet.getString("name"));
    			questionTypeDTO.setQuestionTypeId(resultSet.getInt("question_type_id"));
    			questionTypeDTO.setUpdateBy(resultSet.getInt("update_by"));
    			questionTypeDTO.setUpdateDate(resultSet.getDate("update_date"));
    			questionTypeDTO.setUpdateTime(resultSet.getTime("update_date"));
    			questionTypeDTOs.add(questionTypeDTO);
    		}
    		return questionTypeDTOs;
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
    
    public int countQuestionTypeList(String queryWhereClause) {
		int countQuestionType = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Question_Type).append(" qt ");
    		query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countQuestionType = resultSet.getInt("total");
			}
			return countQuestionType;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countQuestionType;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
}
