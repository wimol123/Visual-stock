package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.GradeChecklistTypeMappingDAO;
import th.co.gosoft.audit.cpram.dto.GradeDTO;
import th.co.gosoft.audit.cpram.model.ChecklistTypeModel;
import th.co.gosoft.audit.cpram.model.GradeModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class GradeChecklistTypeMappingController {
	
	private final static Logger logger = Logger.getLogger(GradeChecklistTypeMappingController.class);
	
	public String getGradeListByChecklistType(String objChecklistType) {
		Connection connection = null;
		GradeChecklistTypeMappingDAO gradeChecklistTypeMappingDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gradeChecklistTypeMappingDAO = new GradeChecklistTypeMappingDAO(connection);
			gson = new Gson();
			
			ChecklistTypeModel checklistTypeModel = gson.fromJson(objChecklistType, ChecklistTypeModel.class);
			List<GradeDTO> gradeDTOs = gradeChecklistTypeMappingDAO.getGradeListByChecklistType(TransformModel.transChecklistTypeModel(checklistTypeModel));
			List<GradeModel> gradeModels = new ArrayList<>();
			
			for(GradeDTO grade : gradeDTOs) {
				gradeModels.add(TransformDTO.transGradeDTO(grade));
			}
			
			return gson.toJson(gradeModels);
			
		}catch (Exception e) {
			logger.error("GradeChecklistTypeMappingController.getGradeListByChecklistType() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}

}
