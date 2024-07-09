package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.AnswerDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistTypeDTO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class AnswerDAO extends StandardAttributeDAO {

	private final static Logger logger = Logger.getLogger(AnswerDAO.class);
	
	public AnswerDAO(Connection connection) {
		super(connection);
	}
	
	public List<AnswerDTO> getAnswerListInQuestionType(QuestionTypeDTO questionTypeDTO) {
		try {
			
			/*SELECT a.answer_id, a.answer_detail, a.is_create_car, a.is_require_evidence, a.enable FROM answer a
			LEFT JOIN answer_question_type_mapping aqm ON a.answer_id = aqm.answer_id
			LEFT JOIN question_type qt ON aqm.question_type_id = qt.question_type_id
			WHERE qt.question_type_id = 2 AND a.enable = 'Y'*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT a.answer_id, a.answer_detail, a.is_create_car, a.enable ");
			query.append("FROM ").append(DBConst.TABLE_Answer).append(" a ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Answer_Question_Type_Mapping).append(" aqm ");
			query.append("ON a.answer_id = aqm.answer_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Question_Type).append(" qt ");
			query.append("ON aqm.question_type_id = qt.question_type_id ");
			query.append("WHERE qt.question_type_id = ? AND a.enable = 'Y';");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, questionTypeDTO.getQuestionTypeId());
			resultSet = preparedStatement.executeQuery();
			
			List<AnswerDTO> answerDTOs = new ArrayList<>();
			while(resultSet.next()) {
				AnswerDTO answer = new AnswerDTO();
				answer.setAnswerId(resultSet.getInt("answer_id"));
				answer.setAnswerDetail(resultSet.getString("answer_detail"));
				answer.setIsCreateCar(NullUtils.cvChar(resultSet.getString("is_create_car")));
				answer.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				answerDTOs.add(answer);
			}
			return answerDTOs;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
 			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();			
		}
	}
	
	
	public List<AnswerDTO> getAnswerListGradeCalculator(ChecklistTypeDTO checklistTypeDTO){
		try {
			
			/*SELECT a.answer_id, a.answer_detail, a.is_create_car, a.enable 
			FROM checklist_type ct 
			LEFT JOIN answer_grade_calculator agc 
			ON ct.checklist_type_id = agc.checklist_type_id
			LEFT JOIN answer a
			ON agc.answer_id = a.answer_id
			WHERE 1=1 AND ct.checklist_type_id = 1 AND a.enable = 'Y';*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT a.answer_id, a.answer_detail, a.is_create_car, a.enable ");
			query.append("FROM ").append(DBConst.TABLE_Checklist_Type).append(" ct ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Answer_Grade_Calculator).append(" agc ");
			query.append("ON ct.checklist_type_id = agc.checklist_type_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Answer).append(" a ");
			query.append("ON agc.answer_id = a.answer_id ");
			query.append("WHERE 1=1 AND ct.checklist_type_id = ? AND a.enable = 'Y';");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, checklistTypeDTO.getChecklistTypeId());
			resultSet = preparedStatement.executeQuery();
			
			List<AnswerDTO> answerDTOs = new ArrayList<>();
			while(resultSet.next()) {
				AnswerDTO answerDTO = new AnswerDTO();
				answerDTO.setAnswerId(resultSet.getInt("answer_id"));
				answerDTO.setAnswerDetail(resultSet.getString("answer_detail"));
				answerDTO.setIsCreateCar(NullUtils.cvChar(resultSet.getString("is_create_car")));
				answerDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				answerDTOs.add(answerDTO);
			}
			return answerDTOs;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
 			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();			
		}
	}
	

	
}
