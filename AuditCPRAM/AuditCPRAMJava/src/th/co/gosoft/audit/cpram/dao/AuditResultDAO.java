package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanAnswerDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class AuditResultDAO extends StandardAttributeDAO{
	
	public AuditResultDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	private final static Logger logger = Logger.getLogger(AuditResultDAO.class);
	
	
	
	public List<AuditResultDTO> getAuditResultList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT ap.checklist_plan_id, ap.auditor_id, u.fullname, ad_result.eval_plan_id, ad_result.answer_id, ad_result.answer_detail, ad_result.transaction_date, ad_result.accepted, ad_result.note
			FROM assign_plan ap
			LEFT JOIN audit_result ad_result ON ap.auditor_id = ad_result.auditor_id AND ap.checklist_plan_id = ad_result.checklist_plan_id AND  ad_result.eval_plan_id = 4
			LEFT JOIN user u ON ap.auditor_id = u.user_id
			WHERE ap.checklist_plan_id = 1 ;*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ap.checklist_plan_id, ap.auditor_id, u.fullname, ad_result.eval_plan_id, ad_result.answer_id, ad_result.answer_detail, ad_result.transaction_date, ad_result.accepted, ad_result.note ");
			query.append("FROM ").append(DBConst.TABLE_Assign_Plan).append(" ap ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Audit_Result).append(" ad_result ");
			query.append("ON ap.auditor_id = ad_result.auditor_id AND ap.checklist_plan_id = ad_result.checklist_plan_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON ap.auditor_id = u.user_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by ap.auditor_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<AuditResultDTO> auditResultDTOs = new ArrayList<>();
			while (resultSet.next()) {
				AuditResultDTO auditResultDTO = new AuditResultDTO();
				
				auditResultDTO.setChecklistPlanId(new ChecklistPlanDTO());
				auditResultDTO.getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));
				
				auditResultDTO.setAuditorId(new UserDTO());
				auditResultDTO.getAuditorId().setUserId(resultSet.getInt("auditor_id"));
				auditResultDTO.getAuditorId().setFullname(resultSet.getString("fullname"));
				
				auditResultDTO.setEvalPlanId(new EvalPlanDTO());
				auditResultDTO.getEvalPlanId().setEvalPlanId(resultSet.getInt("eval_plan_id"));
				
				auditResultDTO.setAnswerId(new EvalPlanAnswerDTO());
				auditResultDTO.getAnswerId().setAnswerId(resultSet.getInt("answer_id"));
				auditResultDTO.setAnswerDetail(resultSet.getString("answer_detail"));
				
				auditResultDTO.setTransactionDate(resultSet.getDate("transaction_date"));
				auditResultDTO.setTransactionTime(resultSet.getTime("transaction_date"));
				auditResultDTO.setAccepted(NullUtils.cvChar(resultSet.getString("accepted")));
				auditResultDTO.setNote(resultSet.getString("note"));
				
				auditResultDTOs.add(auditResultDTO);
			}
			return auditResultDTOs;
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			DatabaseUtils.rollback(connection);
			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public List<AuditResultDTO> getAuditResultByChecklistPlanAndEvalPlan(EvalPlanDTO evalPlanDTO, String queryWhereClause){
		try {
			/*SELECT ap.checklist_plan_id, ap.auditor_id, u.fullname, ad_result.eval_plan_id, ad_result.answer_id, ad_result.answer_detail, ad_result.transaction_date, ad_result.accepted, ad_result.note
			FROM assign_plan ap
			LEFT JOIN audit_result ad_result ON ap.auditor_id = ad_result.auditor_id AND ap.checklist_plan_id = ad_result.checklist_plan_id AND ad_result.eval_plan_id = 4
			LEFT JOIN user u ON ap.auditor_id = u.user_id
			WHERE ap.checklist_plan_id = 1 ;*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ap.checklist_plan_id, ap.auditor_id, u.fullname, ad_result.eval_plan_id, ad_result.answer_id, ad_result.answer_detail, ad_result.transaction_date, ad_result.accepted, ad_result.note ");
			query.append("FROM ").append(DBConst.TABLE_Assign_Plan).append(" ap ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Audit_Result).append(" ad_result ");
			query.append("ON ap.auditor_id = ad_result.auditor_id AND ap.checklist_plan_id = ad_result.checklist_plan_id AND ad_result.eval_plan_id = ? ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON ap.auditor_id = u.user_id ");			
			query.append("WHERE ap.checklist_plan_id = ? ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append("order by ap.auditor_id asc;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, evalPlanDTO.getEvalPlanId());
			preparedStatement.setInt(2, evalPlanDTO.getChecklistPlanId().getChecklistPlanId());
			
			resultSet = preparedStatement.executeQuery();
			/*if(resultSet.next()) {*/
				
			List<AuditResultDTO> auditResultDTOs = new ArrayList<>();
			while (resultSet.next()) {
				AuditResultDTO auditResultDTO = new AuditResultDTO();
				
				auditResultDTO.setChecklistPlanId(new ChecklistPlanDTO());
				auditResultDTO.getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));
				
				auditResultDTO.setAuditorId(new UserDTO());
				auditResultDTO.getAuditorId().setUserId(resultSet.getInt("auditor_id"));
				auditResultDTO.getAuditorId().setFullname(resultSet.getString("fullname"));
				
				auditResultDTO.setEvalPlanId(new EvalPlanDTO());
				auditResultDTO.getEvalPlanId().setEvalPlanId(resultSet.getInt("eval_plan_id"));
				
				auditResultDTO.setAnswerId(new EvalPlanAnswerDTO());
				auditResultDTO.getAnswerId().setAnswerId(resultSet.getInt("answer_id"));
				auditResultDTO.setAnswerDetail(resultSet.getString("answer_detail"));
				
				auditResultDTO.setTransactionDate(resultSet.getDate("transaction_date"));
				auditResultDTO.setTransactionTime(resultSet.getTime("transaction_date"));
				auditResultDTO.setAccepted(NullUtils.cvChar(resultSet.getString("accepted")));
				auditResultDTO.setNote(resultSet.getString("note"));
				
				auditResultDTOs.add(auditResultDTO);
			}
			return auditResultDTOs;
			/*}else {
				return null;
			}*/
			
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public List<AuditResultDTO> getAuditResultList(String queryWhereClause){
		try {
			
			//SELECT * FROM audit_result a_result WHERE 1=1 AND a_result.checklist_plan_id = 1 AND a_result.accepted = 'Y'
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ad_result.checklist_plan_id, ad_result.eval_plan_id, ad_result.auditor_id, ad_result.answer_id, ad_result.answer_detail, ad_result.transaction_date, ad_result.accepted, ad_result.note ");
			query.append("FROM ").append(DBConst.TABLE_Audit_Result).append(" ad_result ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			preparedStatement = connection.prepareStatement(query.toString());
			
			resultSet = preparedStatement.executeQuery();
			List<AuditResultDTO> auditResultDTOs = new ArrayList<>();
			while (resultSet.next()) {
				AuditResultDTO auditResultDTO = new AuditResultDTO();
				
				auditResultDTO.setChecklistPlanId(new ChecklistPlanDTO());
				auditResultDTO.getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));
				
				
				auditResultDTO.setEvalPlanId(new EvalPlanDTO());
				auditResultDTO.getEvalPlanId().setEvalPlanId(resultSet.getInt("eval_plan_id"));
				
				auditResultDTO.setAuditorId(new UserDTO());
				auditResultDTO.getAuditorId().setUserId(resultSet.getInt("auditor_id"));
				
				auditResultDTO.setAnswerId(new EvalPlanAnswerDTO());
				auditResultDTO.getAnswerId().setAnswerId(resultSet.getInt("answer_id"));
				auditResultDTO.setAnswerDetail(resultSet.getString("answer_detail"));
				
				auditResultDTO.setTransactionDate(resultSet.getDate("transaction_date"));
				auditResultDTO.setTransactionTime(resultSet.getTime("transaction_date"));
				auditResultDTO.setAccepted(NullUtils.cvChar(resultSet.getString("accepted")));
				auditResultDTO.setNote(resultSet.getString("note"));
				
				auditResultDTOs.add(auditResultDTO);
			}
			return auditResultDTOs;
			
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int updateAuditResult(AuditResultDTO auditResultDTO) {
		int rowAffect = 0;
		try {
			
			//UPDATE `audit_result` SET `accepted` = 'Y' WHERE (`checklist_plan_id` = '1') and (`auditor_id` = '9') and (`eval_plan_id` = '8') and (`answer_id` = '2');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Audit_Result).append(" ");
			query.append("SET ").append(" accepted = ?, update_by = ?, update_date = now() ");
			query.append("WHERE ").append("(checklist_plan_id = ?) ");
			query.append("and (auditor_id = ?) ");
			query.append("and (eval_plan_id = ?) ");
			query.append("and (answer_id = ?);");
			
			int index = 1;
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(index++, NullUtils.cvStr(auditResultDTO.getAccepted()));
			preparedStatement.setInt(index++, auditResultDTO.getUpdateBy());
			preparedStatement.setInt(index++, auditResultDTO.getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, auditResultDTO.getAuditorId().getUserId());
			preparedStatement.setInt(index++, auditResultDTO.getEvalPlanId().getEvalPlanId());
			preparedStatement.setInt(index++, auditResultDTO.getAnswerId().getAnswerId());
			
			rowAffect = preparedStatement.executeUpdate();
			return rowAffect;			
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return rowAffect;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int updateAuditResultByChecklistPlan(AuditResultDTO auditResultDTO) {
		int rowAffect = 0;
		try {
			
			//UPDATE `audit_result` SET `accepted` = 'Y' WHERE (`checklist_plan_id` = '1') and (`auditor_id` = '9') and (`eval_plan_id` = '8') and (`answer_id` = '2');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Audit_Result).append(" ");
			query.append("SET ").append(" accepted = ?, update_by = ?, update_date = now() ");
			query.append("WHERE ").append("(checklist_plan_id = ?);");
			
			int index = 1;
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(index++, NullUtils.cvStr(auditResultDTO.getAccepted()));
			preparedStatement.setInt(index++, auditResultDTO.getUpdateBy());
			preparedStatement.setInt(index++, auditResultDTO.getChecklistPlanId().getChecklistPlanId());
			
			rowAffect = preparedStatement.executeUpdate();
			return rowAffect;			
		}catch (SQLException e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countAuditResultList(String queryWhereClause) {
		int totalAuditResultList = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Audit_Result).append(" ad_result ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalAuditResultList = resultSet.getInt("total");
			}
			return totalAuditResultList;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalAuditResultList;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
