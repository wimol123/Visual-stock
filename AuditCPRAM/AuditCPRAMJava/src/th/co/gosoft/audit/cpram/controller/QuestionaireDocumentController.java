package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.QuestionaireDocumentDAO;
import th.co.gosoft.audit.cpram.dto.QuestionaireDocumentDTO;
import th.co.gosoft.audit.cpram.model.QuestionaireDocumentModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class QuestionaireDocumentController {
	
	private final static Logger logger = Logger.getLogger(QuestionaireDocumentController.class);
	
	public String getQuestionaireDocumentList() {
		Connection connection = null;
		QuestionaireDocumentDAO questionaireDocumentDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			questionaireDocumentDAO = new QuestionaireDocumentDAO(connection);
			gson = new Gson();
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND ");
			queryWhereClause.append("qd.enable = 'Y'");
			
			List<QuestionaireDocumentDTO> questionaireDocumentDTOList = questionaireDocumentDAO.getQuestionaireDocumentList(0, questionaireDocumentDAO.countQuestionaireDocumentList(queryWhereClause.toString()), queryWhereClause.toString());
			List<QuestionaireDocumentModel> questionaireDocumentModelList = new ArrayList<QuestionaireDocumentModel>();
			
			for(QuestionaireDocumentDTO questionaireDocumentDTO : questionaireDocumentDTOList) {
				questionaireDocumentModelList.add(TransformDTO.transQuestionaireDocumentDTO(questionaireDocumentDTO));
			}
			
			return gson.toJson(questionaireDocumentModelList);
		}catch (Exception e) {
			logger.error("QuestionaireDocumentController.getQuestionaireDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
		
	}

}
