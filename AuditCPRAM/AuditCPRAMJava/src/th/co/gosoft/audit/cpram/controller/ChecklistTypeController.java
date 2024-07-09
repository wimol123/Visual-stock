package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.ChecklistTypeDAO;
import th.co.gosoft.audit.cpram.dto.ChecklistTypeDTO;
import th.co.gosoft.audit.cpram.model.ChecklistTypeModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class ChecklistTypeController {
	
	private final static Logger logger = Logger.getLogger(ChecklistTypeController.class);
	
	
	public String getChecklistTypeList() {
		Connection connection = null;
		ChecklistTypeDAO checklistTypeDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistTypeDAO = new ChecklistTypeDAO(connection);
			gson = new Gson();
			
			List<ChecklistTypeDTO> checklistTypeDTOs = checklistTypeDAO.getChecklistList(0, checklistTypeDAO.countChecklistTypeList(""), "");
			List<ChecklistTypeModel> checklistTypeModels = new ArrayList<>();
			for(ChecklistTypeDTO checklist : checklistTypeDTOs) {
				checklistTypeModels.add(TransformDTO.transChecklistTypeDTO(checklist));
			}
			return gson.toJson(checklistTypeModels);
		}catch (Exception e) {
			
			logger.error("ChecklistTypeController.getChecklistTypeList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}

}
