package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.ChecklistTypeDTO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;

public class QuestionTypeChecklistTypeMappingDAO extends StandardAttributeDAO {
	public QuestionTypeChecklistTypeMappingDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(QuestionTypeChecklistTypeMappingDAO.class);
	
	public List<QuestionTypeDTO> getQuestionTypeByChecklistType(ChecklistTypeDTO checklistTypeDTO) {
		try {

			/*SELECT q_type.question_type_id, q_type.name FROM question_type_checklist_type_mapping mapping_tb
			LEFT JOIN question_type q_type ON mapping_tb.question_type_id = q_type.question_type_id
			WHERE 1=1 AND mapping_tb.checklist_type_id = 1 AND mapping_tb.enable = 'Y'*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT q_type.question_type_id, q_type.name FROM ").append(DBConst.TABLE_Question_Type_Checklist_Type_Mapping).append(" mapping_tb ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Question_Type).append(" q_type ON mapping_tb.question_type_id = q_type.question_type_id ");
			query.append("WHERE 1=1 AND mapping_tb.checklist_type_id = ? AND mapping_tb.enable = 'Y'; ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, checklistTypeDTO.getChecklistTypeId());
			resultSet = preparedStatement.executeQuery();
			
			List<QuestionTypeDTO> questionTypeDTOs = new ArrayList<>();
			while (resultSet.next()) {
				QuestionTypeDTO questionTypeDTO = new QuestionTypeDTO();
				questionTypeDTO.setQuestionTypeId(resultSet.getInt("question_type_id"));
				questionTypeDTO.setName(resultSet.getString("name"));
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
}
