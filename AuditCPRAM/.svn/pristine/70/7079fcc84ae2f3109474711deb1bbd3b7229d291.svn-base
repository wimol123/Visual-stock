package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvidenceDTO;
import th.co.gosoft.audit.cpram.dto.EvidenceTypeDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class EvidenceDAO extends StandardAttributeDAO{
	
	private final static Logger logger = Logger.getLogger(EvidenceDAO.class);

	public EvidenceDAO(Connection connection) {
		super(connection);
	}
	

	public List<EvidenceDTO> getEvidenceList(String queryWhereClause) {
		return getEvidenceList(queryWhereClause,"");
	}
	
	public List<EvidenceDTO> getEvidenceList(String queryWhereClause,String actionType) {
		try {
			
			/*SELECT e.evidence_id, e.checklist_plan_id, e.auditor_id, e.eval_plan_id, e.data, e.action_type, et.evidence_type_id, et.evidence_type_name 
			FROM evidence e LEFT JOIN evidence_type et ON e.evidence_type_id = et.evidence_type_id WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT e.evidence_id, e.checklist_plan_id, e.auditor_id, e.eval_plan_id, e.data, e.action_type, et.evidence_type_id, et.evidence_type_name ");
			query.append("FROM ").append(DBConst.TABLE_Evidence).append(" e ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Evidence_Type).append(" et ");
			query.append("ON e.evidence_type_id = et.evidence_type_id WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause.toString())) {
				query.append(queryWhereClause.toString());
			}
			
			if(actionType.equals("PRINT")) {
				query.append(String.format(" order by et.evidence_type_id desc, e.action_type desc "));
			}else {
				query.append(String.format(" order by et.evidence_type_id asc "));
			}
			
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<EvidenceDTO> evidenceDTOs = new ArrayList<>();
			while(resultSet.next()) {
				EvidenceDTO evidenceDTO = new EvidenceDTO();
				evidenceDTO.setEvidenceId(resultSet.getInt("evidence_id"));
				
				evidenceDTO.setAuditResultId(new AuditResultDTO());
				evidenceDTO.getAuditResultId().setChecklistPlanId(new ChecklistPlanDTO());
				evidenceDTO.getAuditResultId().getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));
				evidenceDTO.getAuditResultId().setAuditorId(new UserDTO());
				evidenceDTO.getAuditResultId().getAuditorId().setUserId(resultSet.getInt("auditor_id"));
				evidenceDTO.getAuditResultId().setEvalPlanId(new EvalPlanDTO());
				evidenceDTO.getAuditResultId().getEvalPlanId().setEvalPlanId(resultSet.getInt("eval_plan_id"));
				evidenceDTO.setData(resultSet.getString("data"));
				evidenceDTO.setActionType(NullUtils.cvChar(resultSet.getString("action_type")));
				
				evidenceDTO.setEvidenceTypeId(new EvidenceTypeDTO());
				evidenceDTO.getEvidenceTypeId().setEvidenceTypeId(resultSet.getInt("evidence_type_id"));
				evidenceDTO.getEvidenceTypeId().setEvidenceTypeName(resultSet.getString("evidence_type_name"));
				
				evidenceDTOs.add(evidenceDTO);
			}
			return evidenceDTOs;
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
	
	public boolean insertEvidence(EvidenceDTO evidenceDTO) {
		try {
			//INSERT INTO `evidence` (`checklist_plan_id`, `auditor_id`, `eval_plan_id`, `evidence_type_id`, `data`, `action_type`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('15', '0', '42', '1', 'p14_u95/d20190405150201847_p14_u95/evidence/ev_34_1.png', 'S', 'Y', '94', 'now()', '94', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Evidence).append(" ");
			query.append("(checklist_plan_id, auditor_id, eval_plan_id, evidence_type_id, data, action_type, enable, create_by, create_date, update_by, update_date) ");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, evidenceDTO.getAuditResultId().getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, evidenceDTO.getAuditResultId().getAuditorId().getUserId());
			preparedStatement.setInt(index++, evidenceDTO.getAuditResultId().getEvalPlanId().getEvalPlanId());
			preparedStatement.setInt(index++, evidenceDTO.getEvidenceTypeId().getEvidenceTypeId());
			preparedStatement.setString(index++, evidenceDTO.getData());
			preparedStatement.setString(index++, NullUtils.cvStr(evidenceDTO.getActionType()));
			preparedStatement.setString(index++, NullUtils.cvStr(evidenceDTO.getEnable()));
			preparedStatement.setInt(index++, evidenceDTO.getCreateBy());
			preparedStatement.setInt(index++, evidenceDTO.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateEvidence(EvidenceDTO evidenceDTO) {
		try {
			//UPDATE `evidence` SET `data` = '---------- ทดสอบการแก้ไขใบคาร์ -------ddd', `action_type` = 'A', `enable` = 'N', `update_by` = '2', `update_date` = 'now()' WHERE (`evidence_id` = '87');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Evidence).append(" ");
			query.append("SET data = ?, ");
			query.append("action_type = ?, ");
			query.append("enable = ?, update_date = now() ");
			query.append("WHERE evidence_id = ?; ");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, evidenceDTO.getData());
			preparedStatement.setString(index++, NullUtils.cvStr(evidenceDTO.getActionType()));
			preparedStatement.setString(index++, NullUtils.cvStr(evidenceDTO.getEnable()));
			preparedStatement.setInt(index++, evidenceDTO.getEvidenceId());
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean deleteEvidence(EvidenceDTO evidenceDTO) {
		try {
			
			//DELETE FROM `evidence` WHERE (`evidence_id` = '69');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Evidence).append(" WHERE (evidence_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, evidenceDTO.getEvidenceId());
			
			int rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
