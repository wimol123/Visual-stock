package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.SystemLogDAO;
import th.co.gosoft.audit.cpram.dto.SystemLogDTO;
import th.co.gosoft.audit.cpram.model.FinalAuditResultModel;
import th.co.gosoft.audit.cpram.model.SystemLogModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class SystemLogController {
	private final static Logger logger = Logger.getLogger(SystemLogController.class);
	
	public String getFinalAuditResultLogList(String finalAuditDetailObj) {
		Connection connection = null;
		Gson gson = null;
		SystemLogDAO systemLogDAO = null;
		String resultJson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			systemLogDAO = new SystemLogDAO(connection);
			
			FinalAuditResultModel finalAuditResultModel = gson.fromJson(finalAuditDetailObj, FinalAuditResultModel.class);
			
			StringBuilder queryWhereClause = new StringBuilder();
			if(finalAuditResultModel.getChecklistPlanId() != null) {
				queryWhereClause.append(" AND s.ref_id = '").append(finalAuditResultModel.getChecklistPlanId().getChecklistPlanId()).append("' ");
			}
			
			List<SystemLogDTO> systemLogDTOs = systemLogDAO.getSystemLogFinalAuditResultList(queryWhereClause.toString());
			
			List<SystemLogModel> systemLogModels = new ArrayList<>();
			if(systemLogDTOs != null && !systemLogDTOs.isEmpty()) {				
				for(SystemLogDTO systemLogDTO : systemLogDTOs) {					
					systemLogModels.add(TransformDTO.transSystemLogDTO(systemLogDTO));
				}
				resultJson = gson.toJson(systemLogModels);
				logger.info(resultJson);
				return resultJson;
			}else {
				return resultJson;
			}			
		}catch (Exception e) {
			logger.error("SystemLogController.getFinalAuditResultLogList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}		
	}
}
