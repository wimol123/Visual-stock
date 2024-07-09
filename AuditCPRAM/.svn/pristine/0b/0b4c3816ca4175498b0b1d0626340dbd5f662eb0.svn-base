package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.CarDetailDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanAnswerDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class CarDetailDAO  extends StandardAttributeDAO{

	private final static Logger logger = Logger.getLogger(CarDAO.class);
	
	public CarDetailDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public List<CarDetailDTO> getCarDetailList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT c_detail.checklist_plan_id, c_detail.auditor_id, c_detail.eval_plan_id, c_detail.answer_id, c_detail.car_id, c_detail.detail, c_detail.completed, c_detail.complete_date, c_detail.enable, ad_result.answer_detail, ad_result.transaction_date, ad_result.accepted, ad_result.note
			FROM car_detail c_detail
			LEFT JOIN audit_result ad_result ON (c_detail.checklist_plan_id = ad_result.checklist_plan_id AND c_detail.auditor_id = ad_result.auditor_id AND c_detail.eval_plan_id = ad_result.eval_plan_id AND c_detail.answer_id = ad_result.answer_id)*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT c_detail.checklist_plan_id, c_plan.checklist_plan_no, c_plan.no_of_car_accept_day, c_detail.auditor_id, c_detail.eval_plan_id, c_detail.answer_id, c_detail.car_id, c_detail.detail, c_detail.due_date, c_detail.completed, c_detail.complete_date, c_detail.remark, c_detail.enable, ad_result.answer_detail, ad_result.transaction_date, ad_result.accepted, ad_result.note, c_detail.create_date ");
			query.append("FROM ").append(DBConst.TABLE_Car_Deatil).append(" c_detail ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Audit_Result).append(" ad_result ");
			query.append("ON (c_detail.checklist_plan_id = ad_result.checklist_plan_id AND c_detail.auditor_id = ad_result.auditor_id AND c_detail.eval_plan_id = ad_result.eval_plan_id AND c_detail.answer_id = ad_result.answer_id) ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan).append(" c_plan ");
			query.append("ON c_detail.checklist_plan_id = c_plan.checklist_plan_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause.toString())) {
				query.append(queryWhereClause.toString());
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by c_detail.checklist_plan_id asc, c_detail.auditor_id asc, c_detail.eval_plan_id asc, c_detail.answer_id asc limit %s,%s", startRecord,numOfRows));
			}
			
			query.append(";");
			
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<CarDetailDTO> carDetailDTOs = new ArrayList<>();
			while(resultSet.next()) {
				CarDetailDTO carDetailDTO = new CarDetailDTO();
				
				carDetailDTO.setAuditResultId(new AuditResultDTO());
				carDetailDTO.getAuditResultId().setAnswerDetail(resultSet.getString("answer_detail"));
				carDetailDTO.getAuditResultId().setTransactionDate(resultSet.getDate("transaction_date"));
				carDetailDTO.getAuditResultId().setTransactionTime(resultSet.getTime("transaction_date"));
				carDetailDTO.getAuditResultId().setAccepted(NullUtils.cvChar(resultSet.getString("accepted")));
				carDetailDTO.getAuditResultId().setNote(resultSet.getString("note"));
				
				carDetailDTO.getAuditResultId().setChecklistPlanId(new ChecklistPlanDTO());
				carDetailDTO.getAuditResultId().getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));
				carDetailDTO.getAuditResultId().getChecklistPlanId().setChecklistPlanNo(resultSet.getString("checklist_plan_no"));
				carDetailDTO.getAuditResultId().getChecklistPlanId().setNoOfCarAcceptDay(resultSet.getInt("no_of_car_accept_day"));
				
				carDetailDTO.getAuditResultId().setAuditorId(new UserDTO());
				carDetailDTO.getAuditResultId().getAuditorId().setUserId(resultSet.getInt("auditor_id"));
				
				carDetailDTO.getAuditResultId().setEvalPlanId(new EvalPlanDTO());
				carDetailDTO.getAuditResultId().getEvalPlanId().setEvalPlanId(resultSet.getInt("eval_plan_id"));
				
				carDetailDTO.getAuditResultId().setAnswerId(new EvalPlanAnswerDTO());
				carDetailDTO.getAuditResultId().getAnswerId().setAnswerId(resultSet.getInt("answer_id"));
				
				carDetailDTO.setCarId(new CarDTO());
				carDetailDTO.getCarId().setCarId(resultSet.getInt("car_id"));
				
				carDetailDTO.setDetail(resultSet.getString("detail"));
				carDetailDTO.setDueDate(resultSet.getDate("due_date"));
				carDetailDTO.setDueTime(resultSet.getTime("due_date"));
				carDetailDTO.setCompleted(NullUtils.cvChar(resultSet.getString("completed")));
				carDetailDTO.setCompleteDate(resultSet.getDate("complete_date"));
				carDetailDTO.setCompleteTime(resultSet.getTime("complete_date"));
				carDetailDTO.setRemark(resultSet.getString("remark"));
				carDetailDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				carDetailDTO.setCreateDate(resultSet.getDate("create_date"));
				carDetailDTO.setCreateTime(resultSet.getTime("create_date"));
				
				carDetailDTOs.add(carDetailDTO);
			}
			return carDetailDTOs;
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
	
	public boolean updateCarDetail(CarDetailDTO carDetailDTO) {
		try {
			
			//UPDATE `car_detail` SET `detail` = ' โต๊ะรกมากเลยwwwww', `completed` = 'Y', `complete_date` = 'now()', `remark` = 'Supplier ได้ทำการแก้ไขเรียบร้อยแล้ว จึงทำการยกdเลิก', `enable` = 'Y', `update_by` = '3', `update_date` = 'now()' WHERE (`checklist_plan_id` = '15') and (`auditor_id` = '94') and (`eval_plan_id` = '44') and (`answer_id` = '2');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Car_Deatil).append(" SET ");
			query.append("detail = ?, ");
			query.append("completed = ?, ");
			query.append("complete_date = ?, ");
			query.append("remark = ?, ");
			query.append("enable = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (checklist_plan_id = ?) and (auditor_id = ?) and (eval_plan_id = ?) and (answer_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index=1, rowAffective = 0;
			preparedStatement.setString(index++, carDetailDTO.getDetail());
			preparedStatement.setString(index++, NullUtils.cvStr(carDetailDTO.getCompleted()));
			
			if(carDetailDTO.getCompleteDate() != null && carDetailDTO.getCompleteTime() != null) {
				preparedStatement.setString(index++, String.format("%s %s", carDetailDTO.getCompleteDate(), carDetailDTO.getCompleteTime()));
			}else {
				preparedStatement.setNull(index++, java.sql.Types.DATE);
			}
			
			preparedStatement.setString(index++, carDetailDTO.getRemark());
			preparedStatement.setString(index++, NullUtils.cvStr(carDetailDTO.getEnable()));
			preparedStatement.setInt(index++, carDetailDTO.getUpdateBy());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getAuditorId().getUserId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getEvalPlanId().getEvalPlanId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getAnswerId().getAnswerId());
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
	
	public boolean updateDueDateCarDetail(CarDetailDTO carDetailDTO) {
		try {
			
			//UPDATE `car_detail` SET due_date = '2019-05-13 23:57:57', `update_by` = '3', `update_date` = now() WHERE (`checklist_plan_id` = '14') and (`auditor_id` = '95') and (`eval_plan_id` = '33') and (`answer_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Car_Deatil.trim()).append(" SET ");
			query.append("due_date = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (checklist_plan_id = ?) and (auditor_id = ?) and (eval_plan_id = ?) and (answer_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index=1, rowAffective = 0;
			if(carDetailDTO.getDueDate() != null && carDetailDTO.getDueTime() != null) {
				preparedStatement.setString(index++, String.format("%s %s", carDetailDTO.getDueDate(), carDetailDTO.getDueTime()));
			}else {
				preparedStatement.setNull(index++, java.sql.Types.DATE);
			}
			preparedStatement.setInt(index++, carDetailDTO.getUpdateBy());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getAuditorId().getUserId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getEvalPlanId().getEvalPlanId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getAnswerId().getAnswerId());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e){
			logger.error("CarDetailDAO.updateDueDateCarDetail() SQLException : "+ExceptionUtils.stackTraceException(e));
			return false;
		}catch (Exception e) {
			logger.error("CarDetailDAO.updateDueDateCarDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			return false;
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateByChecklistPlanAndEvalPlan(CarDetailDTO carDetailDTO) {
		
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Car_Deatil).append(" SET ");
			query.append("completed = ?, ");
			query.append("remark = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (checklist_plan_id = ?) and (eval_plan_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			int index=1, rowAffective = 0;
			
			preparedStatement.setString(index++, NullUtils.cvStr(carDetailDTO.getCompleted()));
			preparedStatement.setString(index++, carDetailDTO.getRemark());
			preparedStatement.setInt(index++, carDetailDTO.getUpdateBy());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getEvalPlanId().getEvalPlanId());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective >= 1) {
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
	
	public int countCarDetail(String queryWhereClause) {
		int totalCarDetail = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Car_Deatil).append(" c_detail ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Audit_Result).append(" ad_result ");
			query.append("ON (c_detail.checklist_plan_id = ad_result.checklist_plan_id AND c_detail.auditor_id = ad_result.auditor_id AND c_detail.eval_plan_id = ad_result.eval_plan_id AND c_detail.answer_id = ad_result.answer_id) ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalCarDetail = resultSet.getInt("total");
			}
			return totalCarDetail;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalCarDetail;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean editCarDetail(CarDetailDTO carDetailDTO) {
		try {
			
			//UPDATE `car_detail` SET `detail` = ' โต๊ะรกมากเลยwwwww', `update_by` = '3', `update_date` = 'now()' WHERE (`checklist_plan_id` = '15') and (`auditor_id` = '94') and (`eval_plan_id` = '44') and (`answer_id` = '2');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Car_Deatil).append(" SET ");
			query.append("detail = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (checklist_plan_id = ?) and (auditor_id = ?) and (eval_plan_id = ?) and (answer_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index=1, rowAffective = 0;
			preparedStatement.setString(index++, carDetailDTO.getDetail());
			preparedStatement.setInt(index++, carDetailDTO.getUpdateBy());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getAuditorId().getUserId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getEvalPlanId().getEvalPlanId());
			preparedStatement.setInt(index++, carDetailDTO.getAuditResultId().getAnswerId().getAnswerId());
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
}
