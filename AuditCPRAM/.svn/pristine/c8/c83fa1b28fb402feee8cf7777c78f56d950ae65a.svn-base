package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.QuestionTypeDAO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.model.QuestionTypeModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class QuestionTypeController {
	
	private final static Logger logger = Logger.getLogger(QuestionTypeController.class);
	
	public String getQuestionTypeList() {
		Connection connection = null;
		QuestionTypeDAO questionTypeDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			questionTypeDAO = new QuestionTypeDAO(connection);
			gson = new Gson();
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND ");
			queryWhereClause.append("qt.enable = 'Y' ");
			
			List<QuestionTypeDTO> questionTypeDTOs = questionTypeDAO.getQuestionTypeList(0, questionTypeDAO.countQuestionTypeList(queryWhereClause.toString()), queryWhereClause.toString());
			List<QuestionTypeModel> questionTypeModels = new ArrayList<>();
			
			for(QuestionTypeDTO questionType : questionTypeDTOs) {
				questionTypeModels.add(TransformDTO.transQuestionTypeDTO(questionType));
			}
			
			return gson.toJson(questionTypeModels);
		}catch (Exception e) {
			logger.error("QuestionTypeController.getQuestionTypeList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
}
