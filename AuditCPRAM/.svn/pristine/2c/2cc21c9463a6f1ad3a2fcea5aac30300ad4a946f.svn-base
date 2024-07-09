package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.conts.ConstChecklistPlanStatus;
import th.co.gosoft.audit.cpram.dao.AuditResultDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistPlanDAO;
import th.co.gosoft.audit.cpram.dao.EvalPlanDAO;
import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.model.EvalPlanModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class EvalPlanController {
	private final static Logger logger = Logger.getLogger(EvalPlanController.class);
	
	public String getEvalPlanList( HttpServletRequest httpServletRequest, String objEvalPlan) {
		Connection connection = null;
		EvalPlanDAO evalPlanDAO = null;
		AuditResultDAO auditResultDAO = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			evalPlanDAO = new EvalPlanDAO(connection);
			auditResultDAO = new AuditResultDAO(connection);
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			EvalPlanModel evalPlanModelRequest = gson.fromJson(objEvalPlan, EvalPlanModel.class);
			StringBuilder queryWhereClause = new StringBuilder();
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			
			queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(evalPlanModelRequest.getChecklistPlanId().getChecklistPlanId()).append("' ");
			List<ChecklistPlanDTO> checklistPlanDTO = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString());
			
			queryWhereClause.setLength(0);
			queryWhereClause = new StringBuilder();
			if(evalPlanModelRequest.getChecklistPlanId() != null) {
				if(!StringUtils.isNullOrEmpty(evalPlanModelRequest.getChecklistPlanId().getChecklistPlanId())) {					
					queryWhereClause.append(" AND eplan.checklist_plan_id = '").append(evalPlanModelRequest.getChecklistPlanId().getChecklistPlanId()).append("' ");
				}
			}
			
			List<EvalPlanDTO> evalPlanDTOs = evalPlanDAO.getEvalPlanList(0, evalPlanDAO.countEvalPlanList(queryWhereClause.toString()), queryWhereClause.toString());
			List<EvalPlanModel> evalPlanModels = new ArrayList<>();
			for(EvalPlanDTO evalPlan : evalPlanDTOs) {
				
				if(evalPlan.getEvalTypeId().getEvalTypeId() == NullUtils.cvInt(StaticVariableUtils.evalTypeQuestion)) {
					if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) != Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN && NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) != Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
						List<AuditResultDTO> auditResultDTOs = new ArrayList<>();
						if(checklistPlanDTO.get(0).getChecklistPlanStatusId().getChecklistPlanStatusId() == ConstChecklistPlanStatus.ACCEPT_RESULT_AUDIT) {
							auditResultDTOs = auditResultDAO.getAuditResultByChecklistPlanAndEvalPlan(evalPlan, " AND ad_result.accepted = 'Y' ");
						}else {
							auditResultDTOs = auditResultDAO.getAuditResultByChecklistPlanAndEvalPlan(evalPlan, "");
						}
						
						evalPlan.setAuditResultId(auditResultDTOs);
						evalPlanModels.add(TransformDTO.transEvalPlanDTO(evalPlan));
					}	
				}else {
					if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) != Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN && NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) != Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
						List<AuditResultDTO> auditResultDTOs = auditResultDAO.getAuditResultByChecklistPlanAndEvalPlan(evalPlan, "");
						evalPlan.setAuditResultId(auditResultDTOs);						
					}
					evalPlanModels.add(TransformDTO.transEvalPlanDTO(evalPlan));
				}
				
				
			}
			return gson.toJson(evalPlanModels);
			
		}catch (Exception e) {
			logger.error("EvalPlanController.getEvalPlanList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
}
