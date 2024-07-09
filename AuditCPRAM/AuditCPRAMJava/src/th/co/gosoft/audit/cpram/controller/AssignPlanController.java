package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.AssignPlanDAO;
import th.co.gosoft.audit.cpram.dto.AssignPlanDTO;
import th.co.gosoft.audit.cpram.model.AssignPlanModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class AssignPlanController {
	private final static Logger logger = Logger.getLogger(AssignPlanController.class);
	
	public String getAssignPlanList(HttpServletRequest httpServletRequest, String objAssignPlan) {		
		AssignPlanDAO assignPlanDAO = null;
		Connection connection = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			assignPlanDAO = new AssignPlanDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			AssignPlanModel assignPlanModelRequest = gson.fromJson(objAssignPlan, AssignPlanModel.class);
			StringBuilder queryWhereClause = new StringBuilder();
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			
			if(assignPlanModelRequest.getAuditorId() != null) {
				if(!StringUtils.isNullOrEmpty(assignPlanModelRequest.getAuditorId().getUserId())) {
					queryWhereClause.append(" AND ap.auditor_id = '").append(assignPlanModelRequest.getAuditorId().getUserId()).append("' ");
				}
					
			}
			
			
			if(assignPlanModelRequest.getChecklistPlanId() != null) {			
				if(!StringUtils.isNullOrEmpty(assignPlanModelRequest.getChecklistPlanId() .getChecklistPlanId())) {
					queryWhereClause.append(" AND ap.checklist_plan_id = '").append(assignPlanModelRequest.getChecklistPlanId() .getChecklistPlanId()).append("' ");
				}
				
			}
			
			List<AssignPlanDTO> assignPlanDTOs = assignPlanDAO.getAssignPlanList(0, assignPlanDAO.countAssignPlanList(queryWhereClause.toString()), queryWhereClause.toString());
			List<AssignPlanModel> assignPlanModels = new ArrayList<>();
			for(AssignPlanDTO assignPlan : assignPlanDTOs) {
				if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
					assignPlan.setAuditorId(null);
				}
				assignPlanModels.add(TransformDTO.transAssignPlanDTO(assignPlan));
			}
			return gson.toJson(assignPlanModels);
		}catch (Exception e) {
			logger.error("AssignPlanController.getAssignPlanList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
}
