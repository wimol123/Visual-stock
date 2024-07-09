package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.internal.StringUtil;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierUserMappingDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class SupplierUserMappingDAO extends StandardAttributeDAO{
	public SupplierUserMappingDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(SupplierUserMappingDAO.class);
	
		
	public List<UserDTO> getUserContractSupplier(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			/*SELECT u.user_id, u.employee_id, u.user_group_id, u.username, u.fullname, u.email, u.telephone, u.enable, ug.user_group_name FROM user u 
			LEFT JOIN supplier_user_mapping smu ON u.user_id = smu.user_id
			LEFT JOIN supplier s ON smu.supplier_id = s.supplier_id
			LEFT JOIN user_group ug ON u.user_group_id = ug.user_group_id
			WHERE 1=1 AND s.supplier_id = 7; */
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT u.user_id, u.employee_id, u.user_group_id, u.username, u.password, u.description, u.fullname, u.email, u.telephone, u.enable, ug.user_group_name ");
			query.append("FROM ").append(DBConst.TABLE_User).append(" u ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" smu ");
			query.append("ON u.user_id = smu.user_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON smu.supplier_id = s.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
			query.append("ON u.user_group_id = ug.user_group_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if (numOfRows != -1) {
				query.append(String.format(" order by u.user_group_id asc, u.user_id asc limit %s,%s", startRecord,numOfRows));
			}
			
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<UserDTO> userDTOs = new ArrayList<>();			
			while(resultSet.next()) {
				
				UserDTO userDTO = new UserDTO();
    			userDTO.setUserId(resultSet.getInt("user_id"));
    			userDTO.setEmployeeId(resultSet.getString("employee_id"));
    			userDTO.setUsername(resultSet.getString("username"));
    			userDTO.setPassword(resultSet.getString("password"));
    			userDTO.setFullname(resultSet.getString("fullname"));
    			userDTO.setDescription(resultSet.getString("description"));
    			userDTO.setEmail(resultSet.getString("email"));
    			userDTO.setTelephone(resultSet.getString("telephone"));
    			userDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
    			
    			UserGroupDTO userGroup = new UserGroupDTO();
    			userGroup.setUserGroupId(resultSet.getInt("user_group_id"));
    			userGroup.setUserGroupName(resultSet.getString("user_group_name"));
    			userDTO.setUserGroupId(userGroup);
    			
				userDTOs.add(userDTO);
			}
			
			return userDTOs;
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
	
	public List<SupplierDTO> getSupplierInUser(UserDTO userDTO){
		try{
			/*SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.logo, s.is_green_card, s.enable FROM supplier s
			LEFT JOIN supplier_user_mapping sm
			ON s.supplier_id = sm.supplier_id LEFT JOIN user u
			ON sm.user_id = u.user_id WHERE u.user_id = 38;*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.logo, s.enable ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ");
			query.append("ON s.supplier_id = sm.supplier_id LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON sm.user_id = u.user_id WHERE u.user_id = ?;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, userDTO.getUserId());
			resultSet = preparedStatement.executeQuery();
			List<SupplierDTO> supplierDTOs = new ArrayList<>();
			System.out.println(query.toString());
			System.out.println(userDTO.getUserId());
			while (resultSet.next()) {
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				supplierDTO.setSupplierCode(resultSet.getString("supplier_code"));	
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				supplierDTO.setIsGreenCard(NullUtils.cvChar(resultSet.getString("is_green_card")));
				supplierDTO.setLogo(resultSet.getString("logo"));
				supplierDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				supplierDTOs.add(supplierDTO);
			}
			return supplierDTOs;
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
	
	public List<UserDTO> getUserInSupplier(SupplierDTO supplierDTO){
		try {
			/*SELECT u.user_id, u.employee_id, u.user_group_id, u.user_parent, u.username, u.fullname, u.email, u.telephone, u.enable 
			FROM supplier s LEFT JOIN supplier_user_mapping sm
			ON s.supplier_id = sm.supplier_id LEFT JOIN user u
			ON sm.user_id = u.user_id WHERE s.supplier_id = 7;*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT u.user_id, u.employee_id, u.user_group_id, u.username, u.fullname, u.email, u.telephone, u.enable ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ");
			query.append("ON s.supplier_id = sm.supplier_id LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON sm.user_id = u.user_id WHERE s.supplier_id = ?;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			resultSet = preparedStatement.executeQuery();
			
			List<UserDTO> userList = new ArrayList<>();
			while(resultSet.next()) {
				UserDTO user = new UserDTO();
				user.setUserId(resultSet.getInt("user_id"));
				user.setEmployeeId(resultSet.getString("employee_id"));
				user.setUsername(resultSet.getString("username"));
				user.setFullname(resultSet.getString("fullname"));
				user.setEmail(resultSet.getString("email"));
				user.setTelephone(resultSet.getString("telephone"));
				user.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				
				UserGroupDTO userGroup = new UserGroupDTO();
				userGroup.setUserGroupId(resultSet.getInt("user_group_id"));				
				user.setUserGroupId(userGroup);
				
				userList.add(user);
			}
			return userList;
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
	
	public boolean insert(SupplierUserMappingDTO supplierUserMappingDTO) {	
		try {
			//INSERT INTO `supplier_user_mapping` (`supplier_id`, `user_id`, `status_id`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('1', '2', '1', '2', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Supplier_User_Mapping).append(" ");
			query.append("(supplier_id, user_id, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, supplierUserMappingDTO.getSupplierId());
			preparedStatement.setInt(index++, supplierUserMappingDTO.getUserId());
			preparedStatement.setString(index++, NullUtils.cvStr(supplierUserMappingDTO.getEnable()));
			preparedStatement.setInt(index++, supplierUserMappingDTO.getCreateBy());
			preparedStatement.setInt(index++, supplierUserMappingDTO.getUpdateBy());			
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
	
	
	
	public boolean deleteByUserId(UserDTO userDTO) {
		try {
			//DELETE FROM `supplier_user_mapping` WHERE (`supplier_id` = '5') and (`user_id` = '51');
			connection.setAutoCommit(false);
			
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_User_Mapping).append(" ");
			query.append("WHERE user_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, userDTO.getUserId());
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
	
	
	public boolean deleteBySupplierId(SupplierDTO supplierDTO) {
		try {
			//DELETE FROM `supplier_user_mapping` WHERE (`supplier_id` = '5') and (`user_id` = '51');

			connection.setAutoCommit(false);
			
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_User_Mapping).append(" ");
			query.append("WHERE (supplier_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			
			preparedStatement.executeUpdate();
			return true;			
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
 			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countUserContractSupplier(String queryWhereClause) {
		int countSupplier = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_User).append(" u ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" smu ");
			query.append("ON u.user_id = smu.user_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON smu.supplier_id = s.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
			query.append("ON u.user_group_id = ug.user_group_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countSupplier = resultSet.getInt("total");
			}
			return countSupplier;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countSupplier;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public String getSupplierByUserId(String userId){
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT sm.supplier_id ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ");
			query.append("WHERE sm.user_id = ?;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, userId);
			resultSet = preparedStatement.executeQuery();
			
			String result = "";
			while(resultSet.next()) {
				result = resultSet.getString("supplier_id");
			}
			return result;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return "";
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean checkDupplicateSupplierAdmin(SupplierUserMappingDTO supplierDTO){
    	try {    		
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT 1 FROM ").append(DBConst.TABLE_Supplier_User_Mapping)
    		.append(" su LEFT JOIN ").append(DBConst.TABLE_User)
    		.append(" u ON su.user_id = u.user_id  WHERE supplier_id = ? AND u.user_group_id = 3 and u.enable = 'Y' ");
    		
    		if(supplierDTO.getUserId() > 0) {
    			query.append(" and u.user_id <> ? ");    			
    		}
    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setInt(1, supplierDTO.getSupplierId());
    		
    		if(supplierDTO.getUserId() > 0) {
    			preparedStatement.setInt(2, supplierDTO.getUserId());
    		}
    		
    		resultSet = preparedStatement.executeQuery();
    		return resultSet.next();
    	}catch(SQLException e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
    }
}
