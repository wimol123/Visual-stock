package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.AnswerDAO;
import th.co.gosoft.audit.cpram.dto.AnswerDTO;
import th.co.gosoft.audit.cpram.model.AnswerModel;
import th.co.gosoft.audit.cpram.model.ChecklistTypeModel;
import th.co.gosoft.audit.cpram.model.QuestionTypeModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class AnswerController {

	
	private final static Logger logger = Logger.getLogger(AnswerController.class);
	
	public String getAnswerListInQuestionType(String objQuestionType) {
		Connection connection = null;
		AnswerDAO answerDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			answerDAO = new AnswerDAO(connection);
			gson = new Gson();
			
			QuestionTypeModel questionTypeModelRequest = gson.fromJson(objQuestionType, QuestionTypeModel.class); 			
			List<AnswerDTO> answerDTOs = answerDAO.getAnswerListInQuestionType(TransformModel.transQuestionTypeModel(questionTypeModelRequest));
			List<AnswerModel> answerModels = new ArrayList<>();
			
			for(AnswerDTO answer : answerDTOs) {
				answerModels.add(TransformDTO.transAnswerDTO(answer));
			}
			
			return gson.toJson(answerModels);
		}catch (Exception e) {
			logger.error("AnswerController.getAnswerListInQuestionType() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getAnswerListGradeCalculator(String objChecklistType) {
		Connection connection = null;
		AnswerDAO answerDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			answerDAO = new AnswerDAO(connection);
			gson = new Gson();
			
			ChecklistTypeModel checklistTypeModel = gson.fromJson(objChecklistType, ChecklistTypeModel.class);
			List<AnswerDTO> answerDTOs = answerDAO.getAnswerListGradeCalculator(TransformModel.transChecklistTypeModel(checklistTypeModel));
			List<AnswerModel> answerModels = new ArrayList<>();
			
			for(AnswerDTO answer : answerDTOs) {
				answerModels.add(TransformDTO.transAnswerDTO(answer));
			}
			
			return gson.toJson(answerModels);
			
		}catch (Exception e) {
			logger.error("AnswerController.getAnswerListGradeCalculator() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
}
