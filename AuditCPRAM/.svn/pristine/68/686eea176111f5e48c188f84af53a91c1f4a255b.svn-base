package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.ChecklistDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistTypeDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class ChecklistDAO extends StandardAttributeDAO{
	
	public ChecklistDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(ChecklistDAO.class);
	
	
	public List<ChecklistDTO> getChecklistList(Integer startRecord, Integer numOfRows, String queryWhereClause){		
		try {
			
			/*SELECT c.checklist_id, c.checklist_title, c.checklist_type_id, c.product_type_id, pt.name AS name_product_type, c.checklist_scope, c.scoring_criteria, c.approve_supplier_rule, c.description, c.effective_date, c.expire_date, c.enable, c.create_by, c.update_by, c.update_date
			FROM checklist c LEFT JOIN product_type pt ON c.product_type_id = pt.product_type_id
			WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT c.checklist_id, c.checklist_title, c.checklist_type_id, check_type.checklist_type_name, c.product_type_id, pt.name AS name_product_type, c.checklist_scope, c.scoring_criteria, c.approve_supplier_rule, c.description, c.no_of_car_accept_day, c.effective_date, c.expire_date, c.enable, c.create_by, c.update_by, c.update_date ");
			query.append("FROM ").append(DBConst.TABLE_CheckList).append(" c ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("ON c.product_type_id = pt.product_type_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Type).append(" check_type ");
			query.append("ON c.checklist_type_id = check_type.checklist_type_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by c.checklist_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<ChecklistDTO> checklistDTOs = new ArrayList<>();
			while(resultSet.next()) {
				ChecklistDTO checklist = new ChecklistDTO();
				checklist.setChecklistId(resultSet.getInt("checklist_id"));
				checklist.setChecklistTitle(resultSet.getString("checklist_title"));
				
				ChecklistTypeDTO checklistTypeId = new ChecklistTypeDTO();
				checklistTypeId.setChecklistTypeId(resultSet.getInt("checklist_type_id"));
				checklistTypeId.setChecklistTypeName(resultSet.getString("checklist_type_name"));
				checklist.setChecklistTypeId(checklistTypeId);
				
				ProductTypeDTO productTypeId = new ProductTypeDTO();
				productTypeId.setProductTypeId(resultSet.getInt("product_type_id"));
				productTypeId.setName(resultSet.getString("name_product_type"));
				checklist.setProductTypeId(productTypeId);
				
				checklist.setChecklistScope(resultSet.getString("checklist_scope"));
				checklist.setScoringCriteria(resultSet.getString("scoring_criteria"));
				checklist.setApproveSupplierRule(resultSet.getString("approve_supplier_rule"));
				checklist.setDescription(resultSet.getString("description"));
				checklist.setNoOfCarAcceptDay(resultSet.getInt("no_of_car_accept_day"));
				checklist.setEffectiveDate(resultSet.getDate("effective_date"));
				checklist.setEffectiveTime(resultSet.getTime("effective_date"));
				checklist.setExpireDate(resultSet.getDate("expire_date"));
				checklist.setExpireTime(resultSet.getTime("expire_date"));
				checklist.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				checklist.setCreateBy(resultSet.getInt("create_by"));
				checklist.setUpdateBy(resultSet.getInt("update_by"));
				
				checklistDTOs.add(checklist);
			}
			return checklistDTOs;
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
	
	
	public List<ChecklistDTO> getChecklistOrderEffectiveDate(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT c.checklist_id, c.checklist_title, c.checklist_type_id, c.product_type_id, pt.name AS name_product_type, c.checklist_scope, c.scoring_criteria, c.approve_supplier_rule, c.description, c.no_of_car_accept_day, c.effective_date, c.expire_date, c.enable, c.create_by, c.update_by, c.update_date ");
			query.append("FROM ").append(DBConst.TABLE_CheckList).append(" c ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("ON c.product_type_id = pt.product_type_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by c.effective_date asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<ChecklistDTO> checklistDTOs = new ArrayList<>();
			while(resultSet.next()) {
				ChecklistDTO checklist = new ChecklistDTO();
				checklist.setChecklistId(resultSet.getInt("checklist_id"));
				checklist.setChecklistTitle(resultSet.getString("checklist_title"));
				
				ChecklistTypeDTO checklistTypeId = new ChecklistTypeDTO();
				checklistTypeId.setChecklistTypeId(resultSet.getInt("checklist_type_id"));
				checklist.setChecklistTypeId(checklistTypeId);
				
				ProductTypeDTO productTypeId = new ProductTypeDTO();
				productTypeId.setProductTypeId(resultSet.getInt("product_type_id"));
				productTypeId.setName(resultSet.getString("name_product_type"));
				checklist.setProductTypeId(productTypeId);
				
				checklist.setChecklistScope(resultSet.getString("checklist_scope"));
				checklist.setScoringCriteria(resultSet.getString("scoring_criteria"));
				checklist.setApproveSupplierRule(resultSet.getString("approve_supplier_rule"));
				checklist.setDescription(resultSet.getString("description"));
				checklist.setNoOfCarAcceptDay(resultSet.getInt("no_of_car_accept_day"));
				checklist.setEffectiveDate(resultSet.getDate("effective_date"));
				checklist.setEffectiveTime(resultSet.getTime("effective_date"));
				checklist.setExpireDate(resultSet.getDate("expire_date"));
				checklist.setExpireTime(resultSet.getTime("expire_date"));
				checklist.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				checklist.setCreateBy(resultSet.getInt("create_by"));
				checklist.setUpdateBy(resultSet.getInt("update_by"));
				
				checklistDTOs.add(checklist);
			}
			return checklistDTOs;
			
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
	
	public int insertChecklist(ChecklistDTO checklistDTO) {
		int primaryChecklist = 0;
		try {
			//INSERT INTO `checklist` (`checklist_title`, `checklist_type_id`, `product_type_id`, `checklist_scope`, `scoring_criteria`, `approve_supplier_rule`, `description`, `effective_date`, `expire_date`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) 
			//VALUES ('ffffff', '2', '1', 'dfgdfgdfg', 'dfgdfgdfg', '[{}]', 'dfsgdfgsdfgsdfg', '2019-01-30', '2019-01-30', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_CheckList).append(" ");
			query.append("(checklist_title, checklist_type_id, product_type_id, checklist_scope, scoring_criteria, approve_supplier_rule, description, no_of_car_accept_day, effective_date, expire_date, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			preparedStatement = connection.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, checklistDTO.getChecklistTitle());
			preparedStatement.setInt(index++, checklistDTO.getChecklistTypeId().getChecklistTypeId());
			preparedStatement.setInt(index++, checklistDTO.getProductTypeId().getProductTypeId());
			preparedStatement.setString(index++, checklistDTO.getChecklistScope());
			preparedStatement.setString(index++, checklistDTO.getScoringCriteria());
			preparedStatement.setString(index++, checklistDTO.getApproveSupplierRule());
			preparedStatement.setString(index++, checklistDTO.getDescription());
			preparedStatement.setInt(index++, checklistDTO.getNoOfCarAcceptDay());
			preparedStatement.setString(index++, String.format("%s %s", checklistDTO.getEffectiveDate(), checklistDTO.getEffectiveTime()));
			preparedStatement.setString(index++, String.format("%s %s", checklistDTO.getExpireDate(), checklistDTO.getExpireTime()));
			preparedStatement.setString(index++, NullUtils.cvStr(checklistDTO.getEnable()));
			preparedStatement.setInt(index++, checklistDTO.getCreateBy());
			preparedStatement.setInt(index++, checklistDTO.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					primaryChecklist = resultSet.getInt(1);
				}
			}
			return primaryChecklist;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryChecklist;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}	
	}
	
	
	public boolean updateChecklist(ChecklistDTO checklistDTO) {
		try {
			
			//UPDATE `checklist` SET `checklist_title` = 'Audit Checklist (Vegetablessss Plant)', 
			//`checklist_type_id` = '6', `product_type_id` = '3', `checklist_scope` = 'เพื่อยืนยัน Supplierssss', 
			//`scoring_criteria` = 'สอดคล้องกับข้อกำหนดอย่างสมบูรณ์<br>Obs: นำข้อกำหนดมาใช้เพียงบางส่วน<br>Minor: นำข้อกำหนดมาใช้เพียงบางส่วน ซึ่งอาจส่งผลกระทบต่อคุณภาพ หรือ ความปลอดภัยต่sอผู้บริโภค', 
			//`approve_supplier_rule` = '[{\"grade\":\"A\",\"condition\":[{\"op\":\"=\",\"value\":\"0\",\"result_type\":\"1\"},{\"op\":\"=\",\"value\":\"0\",\"result_type\":\"2\"},{\"op\":\"≤\",\"value\":\"5\",\"result_type\":\"3\"}],\"corrective_action\":\"ตอบกลับพร้อมหลักฐานการแก้ไขภายใน 30 วัน\"},{\"grade\":\"B\",\"condition\":[{\"op\":\"=\",\"value\":\"0\",\"result_type\":\"1\"},{\"op\":\"=\",\"value\":\"1\",\"result_type\":\"2\"},{\"op\":\"≤\",\"value\":\"5\",\"result_type\":\"3\"}],\"corrective_action\":\"ตอบกลับพร้อมหลักsฐานการแก้ไขภายใน 30 วัน โดยต้องปิดประเด็น Major ก่อนการสั่งซื้อ\"}]', 
			//`description` = 'เอกสารฉบับนี้ใช้สำหรับการตรวจประเมินผู้ขายของบริษัท CP เท่านั้น ข้อกำหนดเฉพาะบางส่วน ถูกกำหนดขึ้นตามหลักการของ Thai Gap / EUREPGAP มาตรฐานวัตถุดิบของ CP และข้อกำหนดจากลูกค้าบางส่วนซึ่งครอบคลุมถึงวัตถุดิบ รวมทั้งกฏหsมายที่เกี่ยวข้อง', 
			//`effective_date` = '2019-02-09 00:00:01', `expire_date` = '2019-02-07 00:00:01', `enable` = 'N', `update_by` = '2', `update_date` = 'now()' WHERE (`checklist_id` = '9');

			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_CheckList).append(" SET ");
			query.append("checklist_title = ?, ");
			query.append("checklist_type_id = ?, ");
			query.append("product_type_id = ?, ");
			query.append("checklist_scope = ?, ");
			query.append("scoring_criteria = ?, ");
			query.append("approve_supplier_rule = ?, ");
			query.append("description = ?, ");
			query.append("no_of_car_accept_day = ?, ");
			query.append("effective_date = ?, ");
			query.append("expire_date = ?, ");
			query.append("enable = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() WHERE (checklist_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, checklistDTO.getChecklistTitle());
			preparedStatement.setInt(index++, checklistDTO.getChecklistTypeId().getChecklistTypeId());
			preparedStatement.setInt(index++, checklistDTO.getProductTypeId().getProductTypeId());
			preparedStatement.setString(index++, checklistDTO.getChecklistScope());
			preparedStatement.setString(index++, checklistDTO.getScoringCriteria());
			preparedStatement.setString(index++, checklistDTO.getApproveSupplierRule());
			preparedStatement.setString(index++, checklistDTO.getDescription());
			preparedStatement.setInt(index++, checklistDTO.getNoOfCarAcceptDay());
			preparedStatement.setString(index++, String.format("%s %s", checklistDTO.getEffectiveDate(), checklistDTO.getEffectiveTime()));
			preparedStatement.setString(index++, String.format("%s %s", checklistDTO.getExpireDate(), checklistDTO.getExpireTime()));
			preparedStatement.setString(index++, NullUtils.cvStr(checklistDTO.getEnable()));
			preparedStatement.setInt(index++, checklistDTO.getUpdateBy());
			preparedStatement.setInt(index++, checklistDTO.getChecklistId());
			
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective > 0) {
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
	
	public boolean deleteChecklist(ChecklistDTO checklistDTO) {
		try {
			//DELETE FROM `checklist` WHERE (`checklist_id` = '9');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_CheckList).append(" ");
			query.append("WHERE (checklist_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, checklistDTO.getChecklistId());
			preparedStatement.executeUpdate();
			return true;
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
	
	public int countChecklistList(String queryWhereClause) {
		int countChecklist = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_CheckList).append(" c ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("ON c.product_type_id = pt.product_type_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Checklist_Type).append(" check_type ");
			query.append("ON c.checklist_type_id = check_type.checklist_type_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countChecklist = resultSet.getInt("total");
			}
			return countChecklist;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countChecklist;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	/*public List<ChecklistDTO> GetChecklist_List() {
		try {
			connection.setAutoCommit(false);
			List<ChecklistDTO> checklist_list = new ArrayList<>();
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT c.* ");
			stringBuilder.append("FROM checklist c;");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				ChecklistDTO checklist = new ChecklistDTO();
				checklist.setChecklistId(resultSet.getInt("checklist_id"));
				checklist.setChecklistName(resultSet.getString("checklist_name"));
				checklist.setCreateDate(resultSet.getDate("create_date"));
				checklist.setStatusId(resultSet.getInt("status_id"));
				checklist.setUpdateDate(resultSet.getDate("update_date"));
				checklist_list.add(checklist);
			}
			return checklist_list;
			
		} catch (SQLException e) {
			logger.error(e.toString(), e);
 			throw new RuntimeException(e.toString(), e);
		}
	}*/
	
	/*public List<ChecklistDTO> getChecklist_List(Integer startRecord, Integer numOfRows, String whereClause) {
		try {
			connection.setAutoCommit(false);
			
			SELECT c.checklist_id, c.checklist_title, c.product_type_id, c.checklist_scope, c.scoring_criteria, c.approve_supplier_rule, c.description, c.effective_date, c.expire_date, c.status_id
			FROM checklist c WHERE 1=1 ;
			List<ChecklistDTO> checklist_list = new ArrayList<>();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT c.checklist_id, c.checklist_title, c.product_type_id, c.checklist_scope, c.scoring_criteria, c.approve_supplier_rule, c.description, c.effective_date, c.expire_date, c.status_id ");
			query.append("FROM ").append(DBConst.TABLE_CheckList).append(" c ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(whereClause))
			{
				query.append(whereClause);
			}
			
			query.append(String.format(" limit %s,%s", startRecord,numOfRows));
			
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				ChecklistDTO checklist = new ChecklistDTO();				
				checklist.setChecklistId(resultSet.getInt("checklist_id"));
				checklist.setChecklistTitle(resultSet.getString("checklist_title"));
				checklist.setChecklistScope(resultSet.getString("checklist_scope"));
				checklist.setScoringCriteria(resultSet.getString("scoring_criteria"));
				checklist.setApproveSupplierRule(resultSet.getString("approve_supplier_rule"));
				checklist.setDescription(resultSet.getString("description"));
				checklist.setEffectiveDate(resultSet.getDate("effective_date"));
				checklist.setExpireDate(resultSet.getDate("expire_date"));
				checklist.setStatusId(resultSet.getInt("status_id"));				
				checklist_list.add(checklist);
			}
			return checklist_list;
			
			
		} catch (SQLException e) {
			logger.error(e.toString(), e);
 			throw new RuntimeException(e.toString(), e);
		}
	}*/
	
	
	/*public List<ChecklistDTO> searchChecklist(ChecklistModel searchOption) {
		try {
			connection.setAutoCommit(false);
			List<ChecklistDTO> checklist_list = new ArrayList<>();
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT c.* ");
			stringBuilder.append("FROM checklist c ");
			stringBuilder.append("WHERE 1=1");
			
			if(searchOption.getChecklistName() != "" && !searchOption.getChecklistName().equals("")) {
				stringBuilder.append(String.format(" && (c.checklist_name LIKE '%%%s%%')", searchOption.getChecklistName()));
			}
			if(searchOption.getStatusId() != "" && !searchOption.getStatusId().equals("")) {
				stringBuilder.append(String.format(" && (c.status_id = %s)", searchOption.getStatusId()));
			}
			
			stringBuilder.append(";");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				ChecklistDTO checklist = new ChecklistDTO();
				checklist.setChecklistId(resultSet.getInt("checklist_id"));
				checklist.setChecklistName(resultSet.getString("checklist_name"));
				checklist.setCreateDate(resultSet.getDate("create_date"));
				checklist.setStatusId(resultSet.getInt("status_id"));
				checklist.setUpdateDate(resultSet.getDate("update_date"));
				checklist_list.add(checklist);
			}
			return checklist_list;
			
		} catch (SQLException e) {
			logger.error(e.toString(), e);
 			throw new RuntimeException(e.toString(), e);
		}
		
	}*/
	
	/*public boolean InsertChecklist(ChecklistModel _checklistModel) {
		try {
			connection.setAutoCommit(false);
			//INSERT INTO `audit_supplier_cpram`.`checklist` (`checklist_name`, `status_id`, `create_date`, `update_date`) VALUES ('Veg plant', '0', now(), now());
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO checklist ");
			stringBuilder.append("(checklist_name, status_id, create_date, update_date)");
			stringBuilder.append(" VALUES ");
			stringBuilder.append("(?, ?, now(), now());");
			
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			int index =1;
			//preparedStatement.setString(index++, NullUtils.cvStr(_checklistModel.getChecklistName()));
			preparedStatement.setInt(index++, NullUtils.cvInt(Boolean.parseBoolean( _checklistModel.getStatusId()) ? 1 : 0));
			
			preparedStatement.executeUpdate();
			connection.commit();
			return true;
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public boolean UpdateChecklist(ChecklistModel _checklistModel) {
		try {
			connection.setAutoCommit(false);
			//UPDATE `audit_supplier_cpram`.`checklist` SET `checklist_name` = 'TestTopice', `status_id` = '0', `update_date` = '2018-10-17 17:15:08' WHERE (`checklist_id` = '4');
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("UPDATE checklist SET ");
			stringBuilder.append("checklist_name = ?, ");
			stringBuilder.append("status_id = ?, ");
			stringBuilder.append("update_date = now() ");
			stringBuilder.append("WHERE (checklist_id = ?);");
			
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			int index =1;
			//preparedStatement.setString(index++, NullUtils.cvStr(_checklistModel.getChecklistName()));
			preparedStatement.setInt(index++, NullUtils.cvInt(Boolean.parseBoolean( _checklistModel.getStatusId()) ? 1 : 0));
			preparedStatement.setInt(index++, NullUtils.cvInt(_checklistModel.getChecklistId()));
			
			preparedStatement.executeUpdate();
			connection.commit();
			return true;
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public boolean DeleteChecklist(String checklistId) {
		try {
			connection.setAutoCommit(false);
			StringBuilder stringBuilder = new StringBuilder();
			//DELETE SupplierMappingQuestion
			//from checklist_question_mapping cqm WHERE cqm.checklist_id = 1;
			stringBuilder.append("DELETE FROM checklist_question_mapping ");
			stringBuilder.append("WHERE checklist_id = ?;");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(checklistId));
			preparedStatement.executeUpdate();
			
			//DELETE Supplier
			//DELETE FROM `audit_supplier_cpram`.`checklist` WHERE (`checklist_id` = '4');
			stringBuilder.setLength(0);
			stringBuilder = new StringBuilder();
			stringBuilder.append("DELETE FROM checklist ");
			stringBuilder.append("WHERE (checklist_id = ?);");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(checklistId));
			
			preparedStatement.executeUpdate();
			connection.commit();
			return true;
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	

	public int countChecklist(String whereClause) {
		try {
			int totalChecklist = 0;
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	COUNT(*) AS total ");
			sb.append("FROM ").append(DBConst.TABLE_CheckList).append(" c ");
			sb.append("WHERE 1=1");
			if(!StringUtils.isNullOrEmpty(whereClause))
			{
				sb.append(whereClause);
			}
 			preparedStatement = connection.prepareStatement(sb.toString());
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {

 				totalChecklist = resultSet.getInt("total"); 				
 			}
			return totalChecklist;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public String buildWhereCause(DataTablePostParamModel param) {
		String checklistName = param.getColumns().get(1).getSearch().getValue().toString().trim();
		String statusId = param.getColumns().get(3).getSearch().getValue().toString().trim();
		
		StringBuilder stringBuilder = new StringBuilder();
		
		if(!checklistName.equals("")) {
			stringBuilder.append(String.format(" && (c.checklist_name LIKE '%%%s%%')", checklistName));
		}
		if(!statusId.equals("")) {
			stringBuilder.append(String.format(" && (c.status_id = %s)", statusId));
		}
		
		return stringBuilder.toString();
	}*/
	
}
