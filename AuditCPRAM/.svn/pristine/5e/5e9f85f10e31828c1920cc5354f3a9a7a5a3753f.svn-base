package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.CarStatusDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanStatusDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistTypeDTO;
import th.co.gosoft.audit.cpram.dto.PlanDateChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class ChecklistPlanDAO extends StandardAttributeDAO{
	private final static Logger logger = Logger.getLogger(ChecklistPlanDAO.class);
	
	public ChecklistPlanDAO(Connection connection) {
		super(connection);
	}	
	
	public List<ChecklistPlanDTO> getChecklistPlanList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		
		try {
			
			/*SELECT check_plan.checklist_plan_id, check_plan.checklist_title, check_plan.supplier_id, check_plan.supplier_company, check_plan.checklist_type_id, check_plan.checklist_type_name, check_plan.checklist_scope, check_plan.product_type_id, check_plan.product_type_name, check_plan.scoring_criteria, check_plan.approve_supplier_rule, check_plan.description, check_plan.plan_date, check_plan_sta.checklist_plan_status_id, check_plan_sta.checklist_plan_status_name, check_plan_sta.status_color, check_plan.enable,
			sup_map.id, sup_map.location_name, sup_map.address_id
			FROM checklist_plan check_plan
			LEFT JOIN checklist_plan_status check_plan_sta 
			ON check_plan.checklist_plan_status_id = check_plan_sta.checklist_plan_status_id
			LEFT JOIN supplier_product_address_mapping sup_map
			ON check_plan.location_id = sup_map.id*/
			
			connection.setAutoCommit(false);
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT check_plan.checklist_plan_id, check_plan.appoint_id, check_plan.checklist_plan_no, check_plan.checklist_title, check_plan.supplier_id, check_plan.supplier_company, check_plan.checklist_type_id, check_plan.checklist_type_name, check_plan.checklist_scope, check_plan.product_type_id, check_plan.product_type_name, check_plan.scoring_criteria, check_plan.approve_supplier_rule, check_plan.description, check_plan.plan_date, check_plan_sta.checklist_plan_status_id, check_plan_sta.checklist_plan_status_name, check_plan_sta.status_color, check_plan.enable, sup_map.id, sup_map.location_name, sup_map.address_id, c.car_id, c.car_no, c.due_date, c.car_status_id, c.enable AS enable_car ");
			
			query.append("FROM ").append(DBConst.TABLE_Checklist_Plan).append(" check_plan ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan_Status).append(" check_plan_sta ");
			query.append("ON check_plan.checklist_plan_status_id = check_plan_sta.checklist_plan_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("ON check_plan.location_id = sup_map.id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Car).append(" c ");
			query.append(" ON check_plan.checklist_plan_id = c.checklist_plan_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by check_plan.plan_date desc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<ChecklistPlanDTO> checklistPlanDTOs = new ArrayList<>();
			while (resultSet.next()) {
				
				ChecklistPlanDTO checklistPlanDTO = new ChecklistPlanDTO();
				
				checklistPlanDTO.setChecklistPlanId(resultSet.getInt("checklist_plan_id"));
				checklistPlanDTO.setAppointId(resultSet.getInt("appoint_id"));
				checklistPlanDTO.setChecklistTitle(resultSet.getString("checklist_title"));
				checklistPlanDTO.setChecklistScope(resultSet.getString("checklist_scope"));
				checklistPlanDTO.setScoringCriteria(resultSet.getString("scoring_criteria"));
				checklistPlanDTO.setApproveSupplierRule(resultSet.getString("approve_supplier_rule"));
				checklistPlanDTO.setDescription(resultSet.getString("description"));	
				checklistPlanDTO.setPlanDate(resultSet.getDate("plan_date"));
				checklistPlanDTO.setPlanTime(resultSet.getTime("plan_date"));
				checklistPlanDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				checklistPlanDTO.setChecklistPlanNo(resultSet.getString("checklist_plan_no"));
				
				checklistPlanDTO.setSupplierId(new SupplierDTO());
				checklistPlanDTO.getSupplierId().setSupplierId(resultSet.getInt("supplier_id"));
				checklistPlanDTO.getSupplierId().setSupplierCompany(resultSet.getString("supplier_company"));
				checklistPlanDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				
				checklistPlanDTO.setChecklistTypeId( new ChecklistTypeDTO());
				checklistPlanDTO.getChecklistTypeId().setChecklistTypeId(resultSet.getInt("checklist_type_id"));
				checklistPlanDTO.getChecklistTypeId().setChecklistTypeName(resultSet.getString("checklist_type_name"));
				checklistPlanDTO.setChecklistTypeName(resultSet.getString("checklist_type_name"));
				
				checklistPlanDTO.setProductTypeId(new ProductTypeDTO());
				checklistPlanDTO.getProductTypeId().setProductTypeId(resultSet.getInt("product_type_id"));
				checklistPlanDTO.getProductTypeId().setName(resultSet.getString("product_type_name"));
				checklistPlanDTO.setProductTypeName(resultSet.getString("product_type_name"));
				
				checklistPlanDTO.setChecklistPlanStatusId(new ChecklistPlanStatusDTO());
				checklistPlanDTO.getChecklistPlanStatusId().setChecklistPlanStatusId(resultSet.getInt("checklist_plan_status_id"));
				checklistPlanDTO.getChecklistPlanStatusId().setChecklistPlanStatusName(resultSet.getString("checklist_plan_status_name"));
				checklistPlanDTO.getChecklistPlanStatusId().setStatusColor(resultSet.getString("status_color"));
				
				checklistPlanDTO.setLocationId(new SupplierProductAddressMappingDTO());
				checklistPlanDTO.getLocationId().setId(resultSet.getInt("id"));
				checklistPlanDTO.getLocationId().setLocationName(resultSet.getString("location_name"));
				checklistPlanDTO.getLocationId().setAddressId(new AddressDTO());
				checklistPlanDTO.getLocationId().getAddressId().setAddressId(resultSet.getInt("address_id"));		
				
				checklistPlanDTO.setCarId(new CarDTO());
				checklistPlanDTO.getCarId().setCarId(resultSet.getInt("car_id"));
				checklistPlanDTO.getCarId().setCarNo(resultSet.getString("car_no"));
				//checklistPlanDTO.getCarId().setChecklistPlanId(checklistPlanDTO);
				checklistPlanDTO.getCarId().setCarStatusId(new CarStatusDTO());
				checklistPlanDTO.getCarId().getCarStatusId().setCarStatusId(resultSet.getInt("car_status_id"));
				checklistPlanDTO.getCarId().setEnable(NullUtils.cvChar(resultSet.getString("enable_car")));
				checklistPlanDTO.getCarId().setDueDate(resultSet.getDate("due_date"));
				checklistPlanDTO.getCarId().setDueTime(resultSet.getTime("due_date"));
								
				checklistPlanDTOs.add(checklistPlanDTO);				
			}
			
			return checklistPlanDTOs;
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
	
	
	public int insertChecklistPlan(ChecklistPlanDTO checklistPlanDTO) {
		int primaryKey = 0;
		try {
			//INSERT INTO `checklist_plan` (`checklist_plan_no`, `checklist_title`, `supplier_id`, `supplier_company`, `checklist_type_id`, `checklist_type_name`, `product_type_id`, `product_type_name`, `checklist_scope`, `scoring_criteria`, `approve_supplier_rule`, `description`, `plan_date`, `location_id`, `no_of_car_accept_day`, `checklist_plan_status_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('eeeee', 'adsfasdf', '4', 'dfgdfg', '1', 'sdfsdf', '3', 'dsfsdfsdf', 'sdfsdf', 'sdfsdf', 'dsfdsf', 'sdfsdfsdf', '23234324', '2', '7', '1', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Checklist_Plan).append(" ");
			query.append("(checklist_plan_no, checklist_title, supplier_id, supplier_company, checklist_type_id, checklist_type_name, product_type_id, product_type_name, checklist_scope, scoring_criteria, approve_supplier_rule, description, plan_date, location_id, no_of_car_accept_day, checklist_plan_status_id, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			int index = 1, rowAffective = 0;
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(index++, checklistPlanDTO.getChecklistPlanNo());
			preparedStatement.setString(index++, checklistPlanDTO.getChecklistTitle());
			preparedStatement.setInt(index++, checklistPlanDTO.getSupplierId().getSupplierId());
			preparedStatement.setString(index++, checklistPlanDTO.getSupplierId().getSupplierCompany());
			preparedStatement.setInt(index++, checklistPlanDTO.getChecklistTypeId().getChecklistTypeId());
			preparedStatement.setString(index++, checklistPlanDTO.getChecklistTypeName());
			preparedStatement.setInt(index++, checklistPlanDTO.getProductTypeId().getProductTypeId());
			preparedStatement.setString(index++, checklistPlanDTO.getProductTypeName());
			preparedStatement.setString(index++, checklistPlanDTO.getChecklistScope());
			preparedStatement.setString(index++, checklistPlanDTO.getScoringCriteria());
			preparedStatement.setString(index++, checklistPlanDTO.getApproveSupplierRule());
			preparedStatement.setString(index++, checklistPlanDTO.getDescription());
			preparedStatement.setString(index++, String.format("%s %s", checklistPlanDTO.getPlanDate(), checklistPlanDTO.getPlanTime()));
			preparedStatement.setInt(index++, checklistPlanDTO.getLocationId().getId());
			preparedStatement.setInt(index++, checklistPlanDTO.getNoOfCarAcceptDay());
			preparedStatement.setInt(index++, checklistPlanDTO.getChecklistPlanStatusId().getChecklistPlanStatusId());
			preparedStatement.setString(index++, NullUtils.cvStr(checklistPlanDTO.getEnable()));
			preparedStatement.setInt(index++, checklistPlanDTO.getCreateBy());
			preparedStatement.setInt(index++, checklistPlanDTO.getUpdateBy());
			
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					primaryKey = resultSet.getInt(1);
				}
			}
			return primaryKey;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryKey;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
		
	}
	
	public int updateStatusChecklistPlan(ChecklistPlanDTO checklistPlanDTO) {
		int rowAffective = 0;
		try {
			
			//UPDATE `checklist_plan` SET `checklist_plan_status_id` = '2' WHERE (`checklist_plan_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Checklist_Plan).append(" SET ");
			query.append("checklist_plan_status_id = ? ");
			query.append("WHERE checklist_plan_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, checklistPlanDTO.getChecklistPlanStatusId().getChecklistPlanStatusId());
			preparedStatement.setInt(2, checklistPlanDTO.getChecklistPlanId());
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
	
	public int updatePlanDateChecklistPlan(PlanDateChecklistPlanDTO plandateChecklistPlanDTO) {
		int rowPlanDateAffective = 0;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Checklist_Plan).append(" SET ");
			query.append("plan_date = ? ");
			query.append("WHERE appoint_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			String date = String.format("%s %s", plandateChecklistPlanDTO.getPlanDate(), plandateChecklistPlanDTO.getPlanTime());
			preparedStatement.setString(1, date);
			preparedStatement.setInt(2, plandateChecklistPlanDTO.getAppointId());
			logger.error(preparedStatement.toString());
			logger.error(date);
			
			rowPlanDateAffective = preparedStatement.executeUpdate();
			return rowPlanDateAffective;
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return rowPlanDateAffective;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public int countChecklistPlanList(String queryWhereClause) {
		int totalChecklistPlan = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Checklist_Plan).append(" check_plan ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Plan_Status).append(" check_plan_sta ");
			query.append("ON check_plan.checklist_plan_status_id = check_plan_sta.checklist_plan_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("ON check_plan.location_id = sup_map.id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Car).append(" c ");
			query.append(" ON check_plan.checklist_plan_id = c.checklist_plan_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalChecklistPlan = resultSet.getInt("total");
			}
			return totalChecklistPlan;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalChecklistPlan;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
