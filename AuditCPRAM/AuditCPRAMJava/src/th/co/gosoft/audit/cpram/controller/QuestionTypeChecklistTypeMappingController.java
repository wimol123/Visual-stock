package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.QuestionTypeChecklistTypeMappingDAO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.model.ChecklistTypeModel;
import th.co.gosoft.audit.cpram.model.QuestionTypeModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class QuestionTypeChecklistTypeMappingController {
	
	private final static Logger logger = Logger.getLogger(QuestionTypeChecklistTypeMappingController.class);
	
	
	public String getQuestionTypeByChecklistType(String objChecklistType) {
		Connection connection = null;
		Gson gson = null;
		QuestionTypeChecklistTypeMappingDAO questionTypeChecklistTypeMappingDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			questionTypeChecklistTypeMappingDAO = new QuestionTypeChecklistTypeMappingDAO(connection);
			
			ChecklistTypeModel checklistTypeModelRequest = gson.fromJson(objChecklistType, ChecklistTypeModel.class);
			List<QuestionTypeDTO> questionTypeDTOs = questionTypeChecklistTypeMappingDAO.getQuestionTypeByChecklistType(TransformModel.transChecklistTypeModel(checklistTypeModelRequest));
			List<QuestionTypeModel> questionTypeModels = new ArrayList<>();
			
			for(QuestionTypeDTO questionType : questionTypeDTOs) {
				questionTypeModels.add(TransformDTO.transQuestionTypeDTO(questionType));
			}
			
			return gson.toJson(questionTypeModels); 
		}catch (Exception e) {
			logger.error("QuestionTypeChecklistTypeMappingController.getQuestionTypeByChecklistType() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}

}
