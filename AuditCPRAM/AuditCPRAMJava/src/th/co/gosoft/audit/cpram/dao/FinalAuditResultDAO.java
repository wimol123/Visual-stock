package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.FinalAuditResultDTO;
import th.co.gosoft.audit.cpram.dto.FinalAuditResultStatusDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class FinalAuditResultDAO extends StandardAttributeDAO{

	private final static Logger logger = Logger.getLogger(FinalAuditResultDAO.class);
	
	public FinalAuditResultDAO(Connection connection) {
		super(connection);
	}
	
	public List<FinalAuditResultDTO> getFinalAuditResultList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT final_result.checklist_plan_no, final_result.supplier_id, final_result.supplier_data, final_result.plan_date, final_result.grade, final_result_state.final_audit_result_status_id, final_result_state.final_audit_result_status_name, final_result_state.final_audit_result_status_color FROM final_audit_result final_result
			LEFT JOIN final_audit_result_status final_result_state
			ON final_result.final_audit_result_status_id = final_result_state.final_audit_result_status_id
			WHERE 1=1;*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT final_result.checklist_plan_no, final_result.supplier_id, final_result.supplier_data, final_result.audit_type, final_result.plan_date, final_result.product_type_id, final_result.product_type_name, final_result.location_id, final_result.supplier_product_address_mapping_data, final_result.checklist_plan_id, check_plan.no_of_car_accept_day, final_result.car_id, final_result.car_detail_data, final_result.conclude, final_result.grade, final_result.pass, final_result.auditor, final_result.signature_of_supplier, final_result.complete_date, final_result.approval_name, final_result_state.final_audit_result_status_id, final_result_state.final_audit_result_status_name, final_result_state.final_audit_result_status_color ");
			query.append("FROM ").append(DBConst.TABLE_Final_Audit_Result).append(" final_result ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Final_Audit_Result_Status).append(" final_result_state ");
			query.append("ON final_result.final_audit_result_status_id = final_result_state.final_audit_result_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan).append(" check_plan ");
			query.append("ON final_result.checklist_plan_id = check_plan.checklist_plan_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" supplier ");
			query.append("ON final_result.supplier_id = supplier.supplier_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by final_result.checklist_plan_no asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<FinalAuditResultDTO> finalAuditResultDTOs = new ArrayList<>();
			while(resultSet.next()) {
				FinalAuditResultDTO finalAuditResultDTO = new FinalAuditResultDTO();
				finalAuditResultDTO.setChecklistPlanNo(resultSet.getString("checklist_plan_no"));
				finalAuditResultDTO.setSupplierId(new SupplierDTO());
				finalAuditResultDTO.getSupplierId().setSupplierId(resultSet.getInt("supplier_id"));
				finalAuditResultDTO.setSupplierData(resultSet.getString("supplier_data"));
				finalAuditResultDTO.setPlanDate(resultSet.getDate("plan_date"));
				finalAuditResultDTO.setPlanTime(resultSet.getTime("plan_date"));
				finalAuditResultDTO.setGrade(NullUtils.cvChar(resultSet.getString("grade")));
				finalAuditResultDTO.setSignatureOfSupplier(resultSet.getBytes("signature_of_supplier"));
				finalAuditResultDTO.setAuditType(resultSet.getInt("audit_type"));
				finalAuditResultDTO.setConclude(resultSet.getString("conclude"));
				finalAuditResultDTO.setPass(NullUtils.cvChar(resultSet.getString("pass")));
				finalAuditResultDTO.setAuditor(resultSet.getString("auditor"));
				finalAuditResultDTO.setCompleteDate(resultSet.getDate("complete_date"));
				finalAuditResultDTO.setCompleteTime(resultSet.getTime("complete_date"));
				finalAuditResultDTO.setApprovalName(resultSet.getString("approval_name"));
				
				finalAuditResultDTO.setProductTypeId(new ProductTypeDTO());
				finalAuditResultDTO.getProductTypeId().setProductTypeId(resultSet.getInt("product_type_id"));
				finalAuditResultDTO.setProductTypeName(resultSet.getString("product_type_name"));
				
				finalAuditResultDTO.setChecklistPlanId(new ChecklistPlanDTO());
				finalAuditResultDTO.getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));	
				finalAuditResultDTO.getChecklistPlanId().setNoOfCarAcceptDay(resultSet.getInt("no_of_car_accept_day"));
				
				finalAuditResultDTO.setLocationId(new SupplierProductAddressMappingDTO());
				finalAuditResultDTO.getLocationId().setId(resultSet.getInt("location_id"));
				finalAuditResultDTO.setSupplierProductAddressMappingData(resultSet.getString("supplier_product_address_mapping_data"));
				
				finalAuditResultDTO.setCarId(new CarDTO());
				finalAuditResultDTO.getCarId().setCarId(resultSet.getInt("car_id"));				
				finalAuditResultDTO.setCarDetailData(resultSet.getString("car_detail_data"));
				
				
				finalAuditResultDTO.setFinalAuditResultStatusId(new FinalAuditResultStatusDTO());
				finalAuditResultDTO.getFinalAuditResultStatusId().setFinalAuditResultStatusId(resultSet.getInt("final_audit_result_status_id"));
				finalAuditResultDTO.getFinalAuditResultStatusId().setFinalAuditResultStatusName(resultSet.getString("final_audit_result_status_name"));
				finalAuditResultDTO.getFinalAuditResultStatusId().setFinalAuditResultStatusColor(resultSet.getString("final_audit_result_status_color"));
				
				finalAuditResultDTOs.add(finalAuditResultDTO);
			}
			
			return finalAuditResultDTOs;
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
	
	public List<FinalAuditResultDTO> checkAccessFinalAuditResult(FinalAuditResultDTO finalAuditResultDTO, UserDTO userDTO){
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT final_result.checklist_plan_no, final_result.supplier_id, final_result.supplier_data, final_result.audit_type, final_result.plan_date, final_result.product_type_id, final_result.product_type_name, final_result.location_id, final_result.supplier_product_address_mapping_data, final_result.checklist_plan_id, final_result.car_id, final_result.car_detail_data, final_result.conclude, final_result.grade, final_result.pass, final_result.auditor, final_result.signature_of_supplier, final_result.complete_date, final_result.approval_name ");
			query.append("FROM ").append(DBConst.TABLE_Final_Audit_Result).append(" final_result ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sup_map ");
			query.append("ON final_result.supplier_id = sup_map.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON sup_map.user_id = u.user_id ");
			query.append("WHERE final_result.checklist_plan_id = ? AND u.user_id = ?; ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, finalAuditResultDTO.getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(2, userDTO.getUserId());
			resultSet = preparedStatement.executeQuery();
			
			List<FinalAuditResultDTO> finalAuditResultDTOs = new ArrayList<>();
			while(resultSet.next()) {
				FinalAuditResultDTO finalAuditResult = new FinalAuditResultDTO();
				finalAuditResult.setChecklistPlanNo(resultSet.getString("checklist_plan_no"));
				finalAuditResult.setSupplierId(new SupplierDTO());
				finalAuditResult.getSupplierId().setSupplierId(resultSet.getInt("supplier_id"));
				finalAuditResult.setSupplierData(resultSet.getString("supplier_data"));
				finalAuditResult.setPlanDate(resultSet.getDate("plan_date"));
				finalAuditResult.setPlanTime(resultSet.getTime("plan_date"));
				finalAuditResult.setGrade(NullUtils.cvChar(resultSet.getString("grade")));
				finalAuditResult.setSignatureOfSupplier(resultSet.getBytes("signature_of_supplier"));
				finalAuditResult.setAuditType(resultSet.getInt("audit_type"));
				finalAuditResult.setConclude(resultSet.getString("conclude"));
				finalAuditResult.setPass(NullUtils.cvChar(resultSet.getString("pass")));
				finalAuditResult.setAuditor(resultSet.getString("auditor"));
				finalAuditResult.setCompleteDate(resultSet.getDate("complete_date"));
				finalAuditResult.setCompleteTime(resultSet.getTime("complete_date"));
				finalAuditResult.setApprovalName(resultSet.getString("approval_name"));
				
				finalAuditResult.setProductTypeId(new ProductTypeDTO());
				finalAuditResult.getProductTypeId().setProductTypeId(resultSet.getInt("product_type_id"));
				finalAuditResult.setProductTypeName(resultSet.getString("product_type_name"));
				
				finalAuditResult.setChecklistPlanId(new ChecklistPlanDTO());
				finalAuditResult.getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));	
				
				finalAuditResult.setLocationId(new SupplierProductAddressMappingDTO());
				finalAuditResult.getLocationId().setId(resultSet.getInt("location_id"));
				finalAuditResult.setSupplierProductAddressMappingData(resultSet.getString("supplier_product_address_mapping_data"));
				
				finalAuditResult.setCarId(new CarDTO());
				finalAuditResult.getCarId().setCarId(resultSet.getInt("car_id"));				
				finalAuditResult.setCarDetailData(resultSet.getString("car_detail_data"));		
				
				finalAuditResultDTOs.add(finalAuditResult);
			}
			
			return finalAuditResultDTOs;
			
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
	
	public int insertFinalAuditResult(FinalAuditResultDTO finalAuditResultDTO) {
		int rowAffect = 0;
		try {
			
			//INSERT INTO `final_audit_result` (`checklist_plan_no`, `supplier_id`, `supplier_data`, `audit_type`, `plan_date`, `product_type_id`, `product_type_name`, `location_id`, `supplier_product_address_mapping_data`, `checklist_plan_id`, `auditor_id`, `car_id`, `car_detail_data`, `conclude`, `grade`, `pass`, `auditor`, `signature_of_supplier`, `complete_date`, `approval_name`, `final_audit_result_status_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('CHP_2019/0010', '14', '{sdfsdfsdff}', '2', '2019-04-11 15:17:00', '2', 'Vegetable', '16', '{sdfasdfasdfasdf}', '15', '94', '2', '{dsfasdfasdfaf}', '{}', 'A', NULL, '{dfasdf}', ?, NULL, '{}', '1', 'Y', '1', '2019-03-16 13:51:13', '1', '2019-03-16 13:51:13');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Final_Audit_Result).append(" ");
			query.append("(checklist_plan_no, supplier_id, supplier_data, audit_type, plan_date, product_type_id, product_type_name, location_id, supplier_product_address_mapping_data, checklist_plan_id, car_id, car_detail_data, conclude, grade, pass, auditor, signature_of_supplier, complete_date, approval_name, final_audit_result_status_id, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			int index = 1;
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(index++, finalAuditResultDTO.getChecklistPlanNo());
			preparedStatement.setInt(index++, finalAuditResultDTO.getSupplierId().getSupplierId());
			preparedStatement.setString(index++, finalAuditResultDTO.getSupplierData());
			preparedStatement.setInt(index++, finalAuditResultDTO.getAuditType());
			preparedStatement.setString(index++, String.format("%s %s", finalAuditResultDTO.getPlanDate(), finalAuditResultDTO.getPlanTime()));
			preparedStatement.setInt(index++, finalAuditResultDTO.getProductTypeId().getProductTypeId());
			preparedStatement.setString(index++, finalAuditResultDTO.getProductTypeName());
			preparedStatement.setInt(index++, finalAuditResultDTO.getLocationId().getId());
			preparedStatement.setString(index++, finalAuditResultDTO.getSupplierProductAddressMappingData());
			preparedStatement.setInt(index++, finalAuditResultDTO.getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, finalAuditResultDTO.getCarId().getCarId());
			preparedStatement.setString(index++, finalAuditResultDTO.getCarDetailData());
			preparedStatement.setString(index++, finalAuditResultDTO.getConclude());
			preparedStatement.setString(index++, NullUtils.cvStr(finalAuditResultDTO.getGrade()));
			preparedStatement.setString(index++, NullUtils.cvStr(finalAuditResultDTO.getPass()));
			preparedStatement.setString(index++, NullUtils.cvStr(finalAuditResultDTO.getAuditor()));
			preparedStatement.setBytes(index++, finalAuditResultDTO.getSignatureOfSupplier());
			if(finalAuditResultDTO.getCompleteDate() != null) {
				preparedStatement.setString(index++, String.format("%s %s", finalAuditResultDTO.getCompleteDate(), finalAuditResultDTO.getCompleteTime()));
			}else {
				preparedStatement.setNull(index++, java.sql.Types.DATE);
			}
			preparedStatement.setString(index++, finalAuditResultDTO.getApprovalName());
			preparedStatement.setInt(index++, finalAuditResultDTO.getFinalAuditResultStatusId().getFinalAuditResultStatusId());
			preparedStatement.setString(index++, NullUtils.cvStr(finalAuditResultDTO.getEnable()));
			preparedStatement.setInt(index++, finalAuditResultDTO.getCreateBy());
			preparedStatement.setInt(index++, finalAuditResultDTO.getUpdateBy());
			rowAffect = preparedStatement.executeUpdate();
						
			return rowAffect;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return rowAffect;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int updateGrade(FinalAuditResultDTO finalAuditResultDTO) {
		int rowAffect = 0;
		try {
			//UPDATE `final_audit_result` SET `grade` = 'D' WHERE (`checklist_plan_no` = 'CHP_2019/0010');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Final_Audit_Result).append(" ");
			query.append("SET grade = ?, update_by = ?, update_date = now() ");
			query.append("WHERE (checklist_plan_no = ?) AND (checklist_plan_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, NullUtils.cvStr(finalAuditResultDTO.getGrade()));
			preparedStatement.setInt(2, finalAuditResultDTO.getUpdateBy());
			preparedStatement.setString(3, finalAuditResultDTO.getChecklistPlanNo());
			preparedStatement.setInt(4, finalAuditResultDTO.getChecklistPlanId().getChecklistPlanId());
			rowAffect = preparedStatement.executeUpdate();
			
			return rowAffect;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return rowAffect;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public int updateAuditType(FinalAuditResultDTO finalAuditResultDTO) {
		int rowAffect = 0;
		try {
			
			//UPDATE `final_audit_result` SET `audit_type` = '2', `update_by` = '2', `update_date` = 'now()' WHERE (`checklist_plan_no` = 'CHP_2019/0010');
			connection.setAutoCommit(false);
			StringBuilder query = new  StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Final_Audit_Result).append(" SET ");
			query.append("audit_type = ?, final_audit_result_status_id = ?, update_by = ?, update_date = now() ");
			query.append("WHERE (checklist_plan_no = ?) AND (checklist_plan_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, finalAuditResultDTO.getAuditType());
			preparedStatement.setInt(2, finalAuditResultDTO.getFinalAuditResultStatusId().getFinalAuditResultStatusId());
			preparedStatement.setInt(3, finalAuditResultDTO.getUpdateBy());
			preparedStatement.setString(4, finalAuditResultDTO.getChecklistPlanNo());
			preparedStatement.setInt(5, finalAuditResultDTO.getChecklistPlanId().getChecklistPlanId());
			rowAffect = preparedStatement.executeUpdate();
			
			return rowAffect;			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return rowAffect;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateFinalProcess(FinalAuditResultDTO finalAuditResultDTO) {
		try {
			//UPDATE `audit_supplier_cpram_final`.`final_audit_result` SET `car_detail_data` = 'iiiii', `pass` = 'Y', `complete_date` = 'now()', `approval_name` = '{\"fullname\"}', `final_audit_result_status_id` = '4', `update_by` = '1', `update_date` = 'now()' WHERE (`checklist_plan_no` = 'CHP_2019/0010');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Final_Audit_Result).append(" SET ");
			query.append(" car_detail_data = ?, pass = ?, complete_date = now(), approval_name = ?, final_audit_result_status_id = ?, update_by = ?, update_date = now() WHERE (checklist_plan_no = ?) AND (checklist_plan_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, finalAuditResultDTO.getCarDetailData());
			preparedStatement.setString(index++, NullUtils.cvStr(finalAuditResultDTO.getPass()));
			preparedStatement.setString(index++, finalAuditResultDTO.getApprovalName());
			preparedStatement.setInt(index++, finalAuditResultDTO.getFinalAuditResultStatusId().getFinalAuditResultStatusId());
			preparedStatement.setInt(index++, finalAuditResultDTO.getUpdateBy());
			preparedStatement.setString(index++, finalAuditResultDTO.getChecklistPlanNo());
			preparedStatement.setInt(index++, finalAuditResultDTO.getChecklistPlanId().getChecklistPlanId());
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
	
	public int countFinalAuditResultList(String queryWhereClause) {
		int totalFinalAuditResult = 0;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Final_Audit_Result).append(" final_result ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Final_Audit_Result_Status).append(" final_result_state ");
			query.append("ON final_result.final_audit_result_status_id = final_result_state.final_audit_result_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan).append(" check_plan ");
			query.append("ON final_result.checklist_plan_id = check_plan.checklist_plan_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" supplier ");
			query.append("ON final_result.supplier_id = supplier.supplier_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalFinalAuditResult = resultSet.getInt("total");
			}
			return totalFinalAuditResult;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalFinalAuditResult;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
