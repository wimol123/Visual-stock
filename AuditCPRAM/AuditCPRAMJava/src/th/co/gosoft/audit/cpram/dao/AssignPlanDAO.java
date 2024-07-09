package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AssignPlanDTO;
import th.co.gosoft.audit.cpram.dto.AssignPlanStatusDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class AssignPlanDAO extends StandardAttributeDAO {
	
	private final static Logger logger = Logger.getLogger(AssignPlanDAO.class);	
	public AssignPlanDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public List<AssignPlanDTO> getAssignPlanList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT ap.auditor_id, u.fullname, u.description, u.email, u.enable, u.user_group_id 
			FROM assign_plan ap 
			LEFT JOIN checklist_plan check_plan
			ON ap.checklist_plan_id = check_plan.checklist_plan_id
			LEFT JOIN user u
			ON ap.auditor_id = u.user_id*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ap.auditor_id, u.fullname, u.description, u.email, u.enable, u.user_group_id, ap.assign_plan_status_id, ap.signature_of_supplier ");
			query.append("FROM ").append(DBConst.TABLE_Assign_Plan).append(" ap ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan).append(" check_plan ");
			query.append("ON ap.checklist_plan_id = check_plan.checklist_plan_id ");
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
			
			List<AssignPlanDTO> assignPlanDTOs = new ArrayList<>();
			while(resultSet.next()) {
				AssignPlanDTO assignPlan = new AssignPlanDTO();
				
				assignPlan.setAssignPlanStatusId(new AssignPlanStatusDTO());
				assignPlan.getAssignPlanStatusId().setAssignPlanStatusId(resultSet.getInt("assign_plan_status_id"));
				assignPlan.setSignatureOfSupplier(resultSet.getBytes("signature_of_supplier"));
				
				UserDTO userAuditor = new UserDTO();
				userAuditor.setUserId(resultSet.getInt("auditor_id"));
				userAuditor.setFullname(resultSet.getString("fullname"));
				userAuditor.setDescription(resultSet.getString("description"));
				userAuditor.setEmail(resultSet.getString("email"));
				userAuditor.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				userAuditor.setUserGroupId(new UserGroupDTO());
				userAuditor.getUserGroupId().setUserGroupId(resultSet.getInt("user_group_id"));
				
				assignPlan.setAuditorId(userAuditor);
								
				assignPlanDTOs.add(assignPlan);
			}
			return assignPlanDTOs;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public List<AssignPlanDTO> getAssignPlan(String queryWhereClause) {
		try {
			/*SELECT ap.checklist_plan_id, ap.auditor_id, ap.signature_of_supplier, ap.assign_plan_status_id
			FROM assign_plan ap  WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ap.checklist_plan_id, ap.auditor_id, u.fullname, u.description, u.email, u.enable, u.user_group_id, ap.signature_of_supplier, ap.assign_plan_status_id ");
			query.append("FROM ").append(DBConst.TABLE_Assign_Plan).append(" ap ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON ap.auditor_id = u.user_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<AssignPlanDTO> assignPlanDTOs = new ArrayList<>();
			while(resultSet.next()) {
				AssignPlanDTO assignPlan = new AssignPlanDTO();
				
				assignPlan.setAssignPlanStatusId(new AssignPlanStatusDTO());
				assignPlan.getAssignPlanStatusId().setAssignPlanStatusId(resultSet.getInt("assign_plan_status_id"));
				assignPlan.setSignatureOfSupplier(resultSet.getBytes("signature_of_supplier"));
				
				UserDTO userAuditor = new UserDTO();
				userAuditor.setUserId(resultSet.getInt("auditor_id"));	
				userAuditor.setFullname(resultSet.getString("fullname"));
				userAuditor.setDescription(resultSet.getString("description"));
				userAuditor.setEmail(resultSet.getString("email"));
				assignPlan.setAuditorId(userAuditor);
								
				assignPlanDTOs.add(assignPlan);
			}
			return assignPlanDTOs;
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int insertAssignPlan(AssignPlanDTO assignPlanDTO) {
		int rowAffective = 0;
		try {
			//INSERT INTO `assign_plan` (`checklist_plan_id`, `auditor_id`, `assign_plan_status_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2', '1', '1', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query  = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Assign_Plan).append(" ");
			query.append("(checklist_plan_id, auditor_id, assign_plan_status_id, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ").append("(?, ?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index =1;
			preparedStatement.setInt(index++, assignPlanDTO.getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, assignPlanDTO.getAuditorId().getUserId());
			preparedStatement.setInt(index++, assignPlanDTO.getAssignPlanStatusId().getAssignPlanStatusId());
			preparedStatement.setString(index++, NullUtils.cvStr(assignPlanDTO.getEnable()));
			preparedStatement.setInt(index++, assignPlanDTO.getCreateBy());
			preparedStatement.setInt(index++, assignPlanDTO.getUpdateBy());
			
			rowAffective = preparedStatement.executeUpdate();
			
			return rowAffective;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return rowAffective;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int updateStateStatusAssignPlan(AssignPlanDTO assignPlanDTO) {
		int rowAffective = 0;
		try {
			//UPDATE `audit_supplier_cpram_final`.`assign_plan` SET `assign_plan_status_id` = '2' WHERE (`checklist_plan_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Assign_Plan).append(" ");
			query.append("SET assign_plan_status_id = ? ");
			query.append("WHERE (checklist_plan_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, assignPlanDTO.getAssignPlanStatusId().getAssignPlanStatusId());
			preparedStatement.setInt(2, assignPlanDTO.getChecklistPlanId().getChecklistPlanId());
			rowAffective = preparedStatement.executeUpdate();
			return rowAffective;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return rowAffective;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countAssignPlanList(String queryWhereClause) {
		int totalAssignPlanList = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Assign_Plan).append(" ap ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan).append(" check_plan ");
			query.append("ON ap.checklist_plan_id = check_plan.checklist_plan_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON ap.auditor_id = u.user_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalAssignPlanList = resultSet.getInt("total");
			}
			return totalAssignPlanList;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalAssignPlanList;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
