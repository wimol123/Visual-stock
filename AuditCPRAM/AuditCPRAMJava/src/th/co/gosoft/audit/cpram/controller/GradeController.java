package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.GradeDAO;
import th.co.gosoft.audit.cpram.dto.GradeDTO;
import th.co.gosoft.audit.cpram.model.GradeModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class GradeController {
	private final static Logger logger = Logger.getLogger(GradeController.class);
	
	public String getGradeList() {
		Connection connection = null;
		GradeDAO gradeDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gradeDAO = new GradeDAO(connection);
			gson = new Gson();
			
			List<GradeDTO> gradeDTOs = gradeDAO.getGradeList();
			List<GradeModel> gradeModels = new ArrayList<>();
			for(GradeDTO grade : gradeDTOs) {
				gradeModels.add(TransformDTO.transGradeDTO(grade));
			}
			return gson.toJson(gradeModels);
		}catch (Exception e) {
			logger.error("GradeController.getGradeList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
}
