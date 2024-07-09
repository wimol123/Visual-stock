package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.AnswerDTO;
import th.co.gosoft.audit.cpram.dto.EvalFormDTO;
import th.co.gosoft.audit.cpram.dto.QuestionAnswerMappingDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class QuestionAnswerMappingDAO extends StandardAttributeDAO{
	
	public QuestionAnswerMappingDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	private final static Logger logger = Logger.getLogger(QuestionAnswerMappingDAO.class);
	
		
	public List<AnswerDTO> getAnswerListByEvalForm(EvalFormDTO evalForm){
		try {
			/*SELECT qam.eval_form_id, qam.answer_id, a.answer_detail, a.is_create_car, a.enable FROM question_answer_mapping qam
			LEFT JOIN answer a ON qam.answer_id = a.answer_id 
			WHERE 1=1 AND qam.eval_form_id = '28';*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT qam.eval_form_id, qam.answer_id, a.answer_detail, a.is_create_car, a.enable ");
			query.append("FROM ").append(DBConst.TABLE_Question_Answer_Mapping).append(" qam ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Answer).append(" a ");
			query.append("ON qam.answer_id = a.answer_id ");
			query.append("WHERE 1=1 AND qam.eval_form_id = ?;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, evalForm.getEvalFormId());
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
	
	public boolean insertQuestionAnswerMapping(QuestionAnswerMappingDTO questionAnswerMappingDTO) {
		boolean resultProcess = false;
		try {
			//INSERT INTO `question_answer_mapping` (`eval_form_id`, `answer_id`, `enable`, `create_by`, `create_daate`, `update_by`, `update_date`) VALUES ('3', '3', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Question_Answer_Mapping).append(" ");
			query.append("(eval_form_id, answer_id, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, questionAnswerMappingDTO.getEvalFormId());
			preparedStatement.setInt(index++, questionAnswerMappingDTO.getAnswerId());
			preparedStatement.setString(index++, NullUtils.cvStr(questionAnswerMappingDTO.getEnable()));
			preparedStatement.setInt(index++, questionAnswerMappingDTO.getCreateBy());
			preparedStatement.setInt(index++, questionAnswerMappingDTO.getUpdateBy());
			
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective > 0) {
				resultProcess = true;
			}
			else {
				resultProcess = false;
			}			
			return resultProcess;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return resultProcess;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean deleteQuestionAnswerMappingByEvalForm(QuestionAnswerMappingDTO questionAnswerMapping) {
		boolean resultProcess = false;
		try {
			//DELETE FROM `question_answer_mapping` WHERE (`eval_form_id` = '28') and (`answer_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Question_Answer_Mapping).append(" ");
			query.append("WHERE (eval_form_id = ?);");
			int rowAffective = 0;
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, questionAnswerMapping.getEvalFormId());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective > 0) {
				resultProcess = true;
			}
			else {
				resultProcess = false;
			}			
			return resultProcess;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return resultProcess;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
