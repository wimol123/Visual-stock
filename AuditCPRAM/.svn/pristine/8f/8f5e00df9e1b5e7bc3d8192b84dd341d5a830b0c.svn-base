package th.co.gosoft.audit.cpram.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.EncryptUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class UserDAO extends StandardAttributeDAO{
	public UserDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	private final static Logger logger = Logger.getLogger(UserDAO.class);

	    
    /*public UserDTO authen(String username, String password) throws NoSuchAlgorithmException{
    	try{
    		UserDTO userDTO = new UserDTO();
    		connection.setAutoCommit(false);

			String authenType = "";
    		StringBuilder sb = new StringBuilder();
			sb.append("SELECT g.authen_type "); 
			sb.append("FROM USER u inner join USER_GROUP g ON u.user_group_id=g.user_group_id "); 
			sb.append("WHERE u.username=? AND u.enable='Y' "); 
			preparedStatement = connection.prepareStatement(sb.toString());		
			preparedStatement.setString(1,username);	
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				authenType = resultSet.getString("authen_type");	
			}
			
			if (authenType.equals(Config.AUTHEN_TYPE_AD) || authenType.equals(Config.AUTHEN_TYPE_USER)){
				preparedStatement.close();
				resultSet.close();
	    		sb = new StringBuilder();
				sb.append("SELECT u.user_id, u.employee_id, u.username,u.fullname, u.user_group_id, g.authen_type "); 
				sb.append("FROM USER u inner join USER_GROUP g ON u.user_group_id=g.user_group_id "); 
	    		if (authenType.equals(Config.AUTHEN_TYPE_AD)){
	    			sb.append("WHERE u.username=? AND u.enable='Y' "); 
	    			
	    		} else if (authenType.equals(Config.AUTHEN_TYPE_USER)){
	    			sb.append("WHERE u.username=? AND u.password=MD5(?) AND u.password<>'' AND u.enable='Y' "); 
	    		}
				preparedStatement = connection.prepareStatement(sb.toString());		
				preparedStatement.setString(1,username);
				if (authenType.equals(Config.AUTHEN_TYPE_USER)){
					preparedStatement.setString(2,password.concat(EncryptUtils.EncryptSHA256()));
				}
				resultSet = preparedStatement.executeQuery();
				while (resultSet.next()) {
					userDTO.setUserId(resultSet.getInt("user_id"));
					userDTO.setEmployeeId(resultSet.getString("employee_id"));
					userDTO.setUsername(resultSet.getString("username"));
					//userDTO.setFullName(resultSet.getString("fullname"));
					//userDTO.setAuthenType(resultSet.getString("authen_type"));
					
					UserGroupDTO userGroupDTO = new UserGroupDTO();
					userGroupDTO.setUserGroupId(resultSet.getInt("user_group_id"));
					userDTO.setUserGroupId(userGroupDTO);
				}	
			}
			return userDTO;
    	}catch (SQLException e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
    }*/
    
    
    public List<UserDTO> getAllUserList(Integer startRecord, Integer numOfRows, String queryWhereClause){
    	try {
    		
    		/*SELECT u.user_id, u.employee_id, u.user_group_id, ug.user_group_name, u.username, u.password, u.fullname, u.description, u.email, u.telephone, u.enable, u.create_by, u.create_date, u.update_by, u.update_date
    		FROM user u LEFT JOIN user_group ug ON u.user_group_id = ug.user_group_id 
    		LEFT JOIN supplier_user_mapping sup_map ON sup_map.user_id = u.user_id
    		WHERE 1=1;*/
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.user_id, u.employee_id, u.user_group_id, ug.user_group_name, u.username, u.password, u.fullname, u.description, u.email, u.telephone, u.enable, u.create_by, u.create_date, u.update_by, u.update_date,u.last_login_time,u.count ");
    		query.append(" FROM ").append(DBConst.TABLE_User).append(" u ");
    		query.append("LEFT JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
    		query.append("ON u.user_group_id = ug.user_group_id  ");
    		query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sup_map ");
    		query.append("ON sup_map.user_id = u.user_id ");
    		query.append("WHERE 1=1 and u.is_deleted = 'N' ");
    		
    		if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
    			query.append(queryWhereClause);
    		}
    		if (numOfRows != -1) {
				query.append(String.format(" order by u.user_id asc limit %s,%s", startRecord,numOfRows));
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
    			userDTO.setCreateBy(resultSet.getInt("create_by"));
    			userDTO.setUpdateBy(resultSet.getInt("update_by"));
    			userDTO.setCreateDate(resultSet.getDate("create_date"));
    			userDTO.setCreateTime(resultSet.getTime("create_date"));
    			userDTO.setUpdateDate(resultSet.getDate("update_date"));
    			userDTO.setUpdateTime(resultSet.getTime("update_date"));;
    			
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
    public UserDTO getUserDeatilByAuthenTypeIsAdmin(UserDTO userDTO,boolean chkTime) {
    	try {
    		/*SELECT u.user_id, u.employee_id, u.username,u.fullname, u.user_group_id, g.authen_type
    		FROM USER u inner join USER_GROUP ug ON u.user_group_id=ug.user_group_id
    		WHERE u.username=? AND u.enable='Y'*/
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.user_id, u.employee_id, u.username, u.fullname, u.accept_pdpa_status, u.accept_pdpa_date , ug.user_group_id, ug.authen_type, ug.user_group_name,u.count,u.last_login_time ");
    		query.append("FROM ").append(DBConst.TABLE_User).append(" u ");
    		query.append("INNER JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
    		query.append("ON u.user_group_id = ug.user_group_id ");
    		query.append("WHERE u.username=? AND u.enable='Y' ");
    		if(chkTime) {
        		query.append(" AND (last_login_time <= now() or last_login_time is null) ");
    		}
    		query.append(" ;");
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setString(1, userDTO.getUsername());
    		resultSet = preparedStatement.executeQuery();
    		
    		UserDTO resultUser = null;
    		while (resultSet.next()) {
    			resultUser = new UserDTO();
				resultUser.setUserId(resultSet.getInt("user_id"));		
				resultUser.setEmployeeId(resultSet.getString("employee_id"));
				resultUser.setUsername(resultSet.getString("username"));
				resultUser.setFullname(resultSet.getString("fullname"));
				resultUser.setFullname(resultSet.getString("fullname"));
				resultUser.setAccept_pdpa_status(resultSet.getString("accept_pdpa_status"));
				resultUser.setAccept_pdpa_date(resultSet.getString("accept_pdpa_date"));
				resultUser.setcountId(resultSet.getInt("count"));
				resultUser.setlasttimer(resultSet.getTime("last_login_time"));
				
				UserGroupDTO userGroupDTO = new UserGroupDTO();
				userGroupDTO.setAuthenType(resultSet.getString("authen_type"));
				userGroupDTO.setUserGroupId(resultSet.getInt("user_group_id"));
				userGroupDTO.setUserGroupName(resultSet.getString("user_group_name"));
				
				resultUser.setUserGroupId(userGroupDTO);
			}
    		
    		
    		return resultUser;
    		
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
    
    public UserDTO getUserDeatilByAuthenTypeIsUser(UserDTO userDTO) {
    	try {
    		/*SELECT u.user_id, u.employee_id, u.username,u.fullname, u.user_group_id, g.authen_type
    		FROM USER u inner join USER_GROUP ug ON u.user_group_id=ug.user_group_id
    		WHERE u.username=? AND u.enable='Y' AND u.password=MD5(?) AND u.password<>''*/
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.user_id, u.employee_id, u.username,u.fullname, u.accept_pdpa_status, u.accept_pdpa_date ,ug.user_group_id, ug.authen_type, ug.user_group_name, u.last_login_time ");
    		query.append("FROM ").append(DBConst.TABLE_User).append(" u ");
    		query.append("INNER JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
    		query.append("ON u.user_group_id = ug.user_group_id ");
    		query.append("WHERE u.username=? AND u.enable='Y' AND u.password=MD5(?) AND u.password<>'' ;");
    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setString(1, userDTO.getUsername());
    		preparedStatement.setString(2, userDTO.getPassword().concat(EncryptUtils.EncryptSHA256()));
    		resultSet = preparedStatement.executeQuery();
    		
    		UserDTO resultUser = null;
    		while (resultSet.next()) {
    			resultUser = new UserDTO();
				resultUser.setUserId(resultSet.getInt("user_id"));		
				resultUser.setEmployeeId(resultSet.getString("employee_id"));
				resultUser.setUsername(resultSet.getString("username"));
				resultUser.setFullname(resultSet.getString("fullname"));
				resultUser.setAccept_pdpa_status(resultSet.getString("accept_pdpa_status"));
				resultUser.setAccept_pdpa_date(resultSet.getString("accept_pdpa_date"));
				resultUser.setlasttimer(resultSet.getTime("last_login_time"));
				
				UserGroupDTO userGroupDTO = new UserGroupDTO();
				userGroupDTO.setAuthenType(resultSet.getString("authen_type"));
				userGroupDTO.setUserGroupId(resultSet.getInt("user_group_id"));
				userGroupDTO.setUserGroupName(resultSet.getString("user_group_name"));
				
				resultUser.setUserGroupId(userGroupDTO);
			}
    		return resultUser;
    		
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

    public List<UserDTO> getEmailList() {
    	try {
    		
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.email FROM ").append(DBConst.TABLE_User).append(" u WHERE 1=1;");    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		resultSet = preparedStatement.executeQuery();
    		List<UserDTO> userDTOs = new ArrayList<>();
    		while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setEmail(resultSet.getString("email"));
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
    
    public boolean checkDupplicateEmail(UserDTO userDTO){
    	try {    		
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.email FROM ").append(DBConst.TABLE_User).append(" u WHERE u.email = ?;");
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setString(1, userDTO.getEmail());
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
    	
    public List<UserDTO> getUserNameList() {
    	try {
    		
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.username FROM ").append(DBConst.TABLE_User).append(" u WHERE 1=1;");    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		resultSet = preparedStatement.executeQuery();
    		List<UserDTO> userDTOs = new ArrayList<>();
    		while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUsername(resultSet.getString("username"));
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
    
    public boolean checkDupplicateUserName(UserDTO userDTO) {
    	try {    		
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.username FROM ").append(DBConst.TABLE_User).append(" u WHERE u.username = ?;");
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setString(1, userDTO.getUsername());
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
    
    public boolean checkDupplicateEmailNotInUserId(UserDTO userDTO) {
    	try {
    		/*SELECT u.email FROM user u WHERE u.email = 'teerapat.ratc@gmail.com' 
    				AND u.email NOT IN (SELECT usr.email FROM user usr WHERE usr.user_id = 82)*/
    		connection.setAutoCommit(false);
    		StringBuilder subQuery = new StringBuilder();
    		subQuery.append("SELECT usr.email FROM ").append(DBConst.TABLE_User).append(" usr ");
    		subQuery.append("WHERE usr.user_id = ?");
    		
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.email FROM ").append(DBConst.TABLE_User).append(" u ");
    		query.append("WHERE u.email = ? AND u.email NOT IN ( ");
    		query.append(subQuery.toString()).append(" );");
    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setString(1, userDTO.getEmail());
    		preparedStatement.setInt(2, userDTO.getUserId());
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
    
    public boolean checkDupplicateUsernameNotInUserId(UserDTO userDTO) {
    	try {    		
    		/*SELECT u.username FROM user u WHERE u.username = 'FinaceDivision' 
    		AND u.username NOT IN (SELECT usr.username FROM user usr WHERE usr.user_id = 83);*/
    		
    		connection.setAutoCommit(false);
    		StringBuilder subQuery = new StringBuilder();
    		subQuery.append("SELECT usr.username FROM ").append(DBConst.TABLE_User).append(" usr ");
    		subQuery.append("WHERE usr.user_id = ?");
    		
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT u.username FROM ").append(DBConst.TABLE_User).append(" u ");
    		query.append("WHERE u.username = ? AND u.username NOT IN ( ");
    		query.append(subQuery.toString()).append(" );");
    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setString(1, userDTO.getUsername());
    		preparedStatement.setInt(2, userDTO.getUserId());
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
    public boolean updateCount(UserDTO userDTO) {
    	try {
	    	connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_User).append(" ");
			query.append("SET count = count+1 ");
			query.append("WHERE (user_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setInt(1, userDTO.getUserId());
    		preparedStatement.executeUpdate();
    		logger.info(userDTO.getUserId());
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
    public boolean updateCountandTimeDf(UserDTO userDTO) {
    	try {
//    		UPDATE `audit_supplier_cpram`.`user` SET `count` = '0' ,`last_login_time` = 'NULL' WHERE (Time(`last_login_time`)<= Time(now()) AND `user_id` = '2');
	    	connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_User).append(" ");
			query.append("SET `count` = '0' ,`last_login_time` = now() ");
			query.append("WHERE `user_id` = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setInt(1, userDTO.getUserId());
    		preparedStatement.executeUpdate();
    		logger.info(userDTO.getUserId());
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
    public boolean updatelasttime(UserDTO userDTO) {
    	try {
	    	connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_User).append(" ");
			query.append("SET  count = count+1 ");
			if(userDTO.getcount() >= 4) {
				query.append(" , last_login_time = (now() + INTERVAL 5 MINUTE) ");
			}else {
				query.append(" , last_login_time = (now()) ");
			}
			query.append("WHERE (user_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setInt(1, userDTO.getUserId());
    		preparedStatement.executeUpdate();
    		logger.info(userDTO.getUserId());
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
    public boolean userChangePassword(UserDTO userDTO) {
    	try {
    		//UPDATE `user` SET `password` = 'sdfsdfsfdsdfsfsdf' WHERE (`user_id` = '58');
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("UPDATE ").append(DBConst.TABLE_User).append(" ");
    		query.append("SET password = MD5(?) ");
    		query.append("WHERE (user_id = ?);");
    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setString(1, userDTO.getPassword());
    		preparedStatement.setInt(2, userDTO.getUserId());
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
    
    public int insertUser(UserDTO userDTO) {
    	int userIdResult = 0;
    	try {
    		
    		/*INSERT INTO `user` (`employee_id`, `user_group_id`, `user_parent`, `username`, `password`, `fullname`, `description`, `email`, `telephone`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) 
    		VALUES ('222222', '1', '0', 'sdfsdfasdf', 'sdfasdf', 'sdfasdf', 'sdsd', 'sdfdfs@com', '555555', 'Y', '8', 'now()', '1', 'now()');*/
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("INSERT INTO ").append(DBConst.TABLE_User).append(" ");
    		query.append("(employee_id, user_group_id, username, password, fullname, description, email, telephone, enable, create_by, create_date, update_by, update_date)");
    		query.append(" VALUES ");
    		query.append("(?, ?, ?, MD5(?), ?, ?, ?, ?, ?, ?, now(), ?, now());");
    		preparedStatement = connection.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
    		int index = 1,rowAffect = 0;
    		preparedStatement.setString(index++, userDTO.getEmployeeId());
    		preparedStatement.setInt(index++, userDTO.getUserGroupId().getUserGroupId());
    		preparedStatement.setString(index++, userDTO.getUsername());
    		preparedStatement.setString(index++, userDTO.getPassword());
    		preparedStatement.setString(index++, userDTO.getFullname());
    		preparedStatement.setString(index++, userDTO.getDescription());
    		preparedStatement.setString(index++, userDTO.getEmail());
    		preparedStatement.setString(index++, userDTO.getTelephone());
    		preparedStatement.setString(index++, NullUtils.cvStr(userDTO.getEnable()));
    		preparedStatement.setInt(index++, userDTO.getCreateBy());
    		preparedStatement.setInt(index++, userDTO.getUpdateBy());
    		rowAffect = preparedStatement.executeUpdate();
    		
    		if(rowAffect == 1) {
    			resultSet = preparedStatement.getGeneratedKeys();
    			while (resultSet.next()) {
					userIdResult = resultSet.getInt(1);
				}
    		}
    		return userIdResult;
    	}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return userIdResult;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
    }
	
	public boolean updateUser(UserDTO userDTO) {
		try {
			/*UPDATE `user` SET `employee_id` = 'werwer', `user_group_id` = '1', `user_parent` = '0', `password` = 'sdfsdfsfdsdfsfsdf', `fullname` = 'dgfdggdfg', `email` = 'vxcvdfgdfg', `telephone` = '099999999', `enable` = 'N', `update_by` = '2', `update_date` = 'now()' WHERE (`user_id` = '58');*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_User).append(" ");
			query.append("SET ").append("employee_id = ?, ");
			query.append("user_group_id = ?, ");
			query.append(userDTO.getPassword());
			query.append("fullname = ?, ");
			query.append("description = ?, ");
			query.append("email = ?, ");
			query.append("telephone = ?, ");
			query.append("enable = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (user_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setString(index++, userDTO.getEmployeeId());
			preparedStatement.setInt(index++, userDTO.getUserGroupId().getUserGroupId());
			preparedStatement.setString(index++, userDTO.getFullname());
			preparedStatement.setString(index++, userDTO.getDescription());
			preparedStatement.setString(index++, userDTO.getEmail());
			preparedStatement.setString(index++, userDTO.getTelephone());
			preparedStatement.setString(index++, NullUtils.cvStr( userDTO.getEnable()));
			preparedStatement.setInt(index++, userDTO.getUpdateBy());
			preparedStatement.setInt(index++, userDTO.getUserId());
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

	
	public boolean deleteUser(UserDTO userDTO) {
		try {
			//DELETE FROM `user` WHERE (`user_id` = '61');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_User).append(" ");
			query.append("WHERE (user_id = ?);");
			
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
	/*public List<UserDTO> getUserList() {
		try {
			List<UserDTO> userDTOList = new ArrayList<UserDTO>();
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	u.user_id ");
			sb.append("	,u.employee_id ");
			sb.append("	,u.user_group_id ");
			sb.append(" ,u.username ");
			sb.append(" ,u.fullname ");
			sb.append(" ,u.email ");
			sb.append(" ,u.telephone ");
			sb.append(" ,u.update_date ");
			sb.append(" ,u.status_id ");
			sb.append(" ,g.user_group_name ");
			sb.append("FROM USER u ");
			sb.append("  INNER JOIN USER_GROUP g ON u.user_group_id=g.user_group_id "); 
			sb.append("WHERE");
			sb.append("  u.user_group_id=? OR  u.user_group_id=? "); 
 			preparedStatement = connection.prepareStatement(sb.toString());
			preparedStatement.setInt(1,Config.AUTHEN_USERTYPE_ADMINISTRATOR);	
			preparedStatement.setInt(2,Config.AUTHEN_USERTYPE_AUDITOR);
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {
 				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				userDTO.setEmployeeId(resultSet.getString("employee_id")); 				
 				userDTO.setUsername(resultSet.getString("username"));
 				//userDTO.setFullName(resultSet.getString("fullname"));
 				userDTO.setEmail(resultSet.getString("email"));
 				userDTO.setTelephone(resultSet.getString("telephone"));
 				userDTO.setUpdateDate(resultSet.getDate("update_date"));
 				//userDTO.setStatusId(resultSet.getInt("enable"));
 				
 				UserGroupDTO userGroupDTO = new UserGroupDTO();
 				userGroupDTO.setUserGroupId(resultSet.getInt("user_group_id"));
 				userGroupDTO.setUserGroupName(resultSet.getString("user_group_name"));
 				userDTO.setUserGroupId(userGroupDTO);
 				
 				userDTOList.add(userDTO);
 			}
			return userDTOList;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}*/

	
	public List<UserDTO> getUserList(Integer startRecord, Integer numOfRows, String whereClause) {
		try {
			
			List<UserDTO> userDTOList = new ArrayList<UserDTO>();
			connection.setAutoCommit(false);
			
			
			
			StringBuilder stringBuild = new StringBuilder();
			stringBuild.append("SELECT u.user_id,u.username,u.employee_id,u.fullname,u.email,ug.user_group_id,ug.user_group_name,u.status_id, s.supplier_id, s.supplier_company ");
			stringBuild.append("FROM ").append(DBConst.TABLE_User).append(" u ");
			stringBuild.append("INNER JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
			stringBuild.append("ON u.user_group_id = ug.user_group_id ");
			stringBuild.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ON sm.user_id = u.user_id ");
			stringBuild.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ").append("ON sm.supplier_id = s.supplier_id ");
			stringBuild.append("WHERE (u.user_group_id = ? OR u.user_group_id = ? OR u.user_group_id = ? OR u.user_group_id = ?)");
			stringBuild.append(whereClause);
			stringBuild.append(" order by u.user_id asc");
			if(numOfRows != -1) {
				stringBuild.append(String.format(" limit %s,%s", startRecord,numOfRows));
			}
			stringBuild.append(";");
			preparedStatement = connection.prepareStatement(stringBuild.toString());
			preparedStatement.setInt(1, Config.AUTHEN_USERTYPE_ADMINISTRATOR);
			preparedStatement.setInt(2, Config.AUTHEN_USERTYPE_AUDITOR);
			preparedStatement.setInt(3, Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN);
			preparedStatement.setInt(4, Config.AUTHEN_USERTYPE_PURCHASING_OFFICER);
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setEmployeeId(resultSet.getString("employee_id"));
				//userDTO.setFullName(resultSet.getString("fullname"));
				//userDTO.setStatusId(resultSet.getInt("status_id")); 				
 				
 				userDTO.setEmail(resultSet.getString("email"));
 				/*userDTO.setTelephone(resultSet.getString("telephone"));
 				userDTO.setUpdateDate(resultSet.getDate("update_date"));*/
 				
 				SupplierDTO supplierDTO = new SupplierDTO();
 				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
 				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
 				//userDTO.setSupplier(supplierDTO);
 				
 				UserGroupDTO userGroupDTO = new UserGroupDTO();
 				userGroupDTO.setUserGroupId(resultSet.getInt("user_group_id"));
 				userGroupDTO.setUserGroupName(resultSet.getString("user_group_name"));
 				userDTO.setUserGroupId(userGroupDTO);
 				 				
 				userDTOList.add(userDTO);
			}
			
			
			return userDTOList;
			
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<UserDTO> getUserContactSupplier(String userId, Integer startRecord, Integer numOfRows, String whereClause) {
		try {
			
			connection.setAutoCommit(false);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT u.user_id, u.fullname, u.email, u.telephone, u.username, u.password, u.employee_id, u.description ");
			stringBuilder.append("FROM ").append(DBConst.TABLE_User).append(" u ");
			stringBuilder.append("WHERE 1=1 AND u.user_parent = ? ");
			stringBuilder.append(whereClause);
			if(numOfRows != -1) {
				stringBuilder.append(String.format(" order by u.user_id asc limit %s,%s", startRecord,numOfRows));
			}
			
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(userId));
			resultSet = preparedStatement.executeQuery();
			
			List<UserDTO> userDTOs = new ArrayList<>();
			
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				//userDTO.setFullName(resultSet.getString("fullname"));
				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setTelephone(resultSet.getString("telephone"));
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setPassword(resultSet.getString("password"));
				userDTO.setEmployeeId(resultSet.getString("employee_id"));
				userDTO.setDescription(resultSet.getString("description"));
				userDTOs.add(userDTO);
			}
			
			return userDTOs;
		}
		catch (SQLException e) {
			DatabaseUtils.rollback(connection);
			throw new RuntimeException(e.getMessage(), e);
		}
		catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	public List<UserDTO> getUserAuditorList(Integer startRecord, Integer numOfRows, String whereClause) {
		
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			List<UserDTO> userDTOs = new ArrayList<>();
			
			query.append("SELECT u.user_id, u.employee_id, u.user_group_id, u.username, u.password, u.description, u.fullname, u.email, u.telephone, u.update_date, u.status_id, ug.user_group_name ");
			query.append("FROM ").append(DBConst.TABLE_User).append(" u ");
			query.append("INNER JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
			query.append("ON u.user_group_id = ug.user_group_id ");
			query.append("WHERE ug.user_group_id = ? ");
			query.append(whereClause);
			if(numOfRows != -1) {
				query.append(String.format(" order by u.user_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, Config.AUTHEN_USERTYPE_AUDITOR);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				//userDTO.setFullName(resultSet.getString("fullname"));
				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setTelephone(resultSet.getString("telephone"));
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setPassword(resultSet.getString("password"));
				userDTO.setEmployeeId(resultSet.getString("employee_id"));
				userDTO.setDescription(resultSet.getString("description"));
				
				UserGroupDTO userGroupDTO = new UserGroupDTO();
				userGroupDTO.setUserGroupName(resultSet.getString("user_group_name"));
				userDTO.setUserGroupId(userGroupDTO);
				
				userDTOs.add(userDTO);
			}			
			
			return userDTOs;
		}catch (SQLException e) {
			DatabaseUtils.rollback(connection);
			throw new RuntimeException(e.getMessage(), e);
		}
		catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
public List<UserDTO> getUserApproverList() {
		
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			List<UserDTO> userDTOs = new ArrayList<>();
			
			query.append("SELECT u.user_id, u.employee_id, u.user_group_id, u.username, u.password, u.description, u.fullname, u.email, u.telephone, u.update_date, ug.user_group_name ");
			query.append("FROM ").append(DBConst.TABLE_User).append(" u ");
			query.append("INNER JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
			query.append("ON u.user_group_id = ug.user_group_id ");
			query.append("WHERE ug.user_group_id = ? ");
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, Config.AUTHEN_USERTYPE_APPROVE_AUDIT_RESULT);
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				//userDTO.setFullName(resultSet.getString("fullname"));
				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setTelephone(resultSet.getString("telephone"));
				userDTO.setUsername(resultSet.getString("username"));
				userDTO.setPassword(resultSet.getString("password"));
				userDTO.setEmployeeId(resultSet.getString("employee_id"));
				userDTO.setDescription(resultSet.getString("description"));
				
				UserGroupDTO userGroupDTO = new UserGroupDTO();
				userGroupDTO.setUserGroupName(resultSet.getString("user_group_name"));
				userDTO.setUserGroupId(userGroupDTO);
				
				userDTOs.add(userDTO);
			}			
			
			return userDTOs;
		}catch (SQLException e) {
			DatabaseUtils.rollback(connection);
			throw new RuntimeException(e.getMessage(), e);
		}
		catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public UserDTO getUserByUserId(String userId) {
		try {
			/*SELECT u.user_id, u.employee_id, u.user_group_id, u.username, u.password, u.fullname, u.email, u.telephone, u.update_date, u.status_id, s.supplier_id, s.supplier_company
			FROM USER u LEFT JOIN supplier_user_mapping sm ON u.user_id = sm.user_id
			LEFT JOIN supplier s ON s.supplier_id = sm.supplier_id
			WHERE u.user_id = 8;*/
			UserDTO userDTO = new UserDTO();
			connection.setAutoCommit(false);
 			StringBuilder query = new StringBuilder();
			query.append("SELECT u.*, s.supplier_id, s.supplier_company ");
			query.append("FROM user u LEFT JOIN supplier_user_mapping sm ON u.user_id = sm.user_id ");
			query.append("LEFT JOIN supplier s ON s.supplier_id = sm.supplier_id ");
			query.append("WHERE u.user_id = ?;");
 			preparedStatement = connection.prepareStatement(query.toString());	
			preparedStatement.setString(1,userId);	
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {
				userDTO.setUserId(resultSet.getInt("user_id"));
 				userDTO.setEmployeeId(resultSet.getString("employee_id"));
 				userDTO.setUsername(resultSet.getString("username"));
 				userDTO.setPassword(resultSet.getString("password"));
 				userDTO.setFullname(resultSet.getString("fullname"));
 				userDTO.setEmail(resultSet.getString("email"));
 				userDTO.setTelephone(resultSet.getString("telephone"));
 				userDTO.setUpdateDate(resultSet.getDate("update_date"));
 				//userDTO.setStatusId(resultSet.getInt("status_id"));
 				userDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
 				SupplierDTO supplierDTO = new SupplierDTO();
 				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
 				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
 				
 				UserGroupDTO userGroupDTO = new UserGroupDTO();
 				userGroupDTO.setUserGroupId(resultSet.getInt("user_group_id"));
 				userDTO.setUserGroupId(userGroupDTO);
 				
 				//userDTO.setSupplier(supplierDTO);
 			}
			return userDTO;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public int insert(UserDTO userDTO){
		try {
 			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
 			sb.append("INSERT INTO USER (employee_id, user_group_id, username, password, fullname, email, telephone, status_id, update_date, user_parent, description) ");
			if (userDTO.getPassword().equals("")){
	 			sb.append("VALUE (?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?, ?) ");
			}
			else{
	 			sb.append("VALUE (?, ?, ?, MD5(?), ?, ?, ?, ?, NOW(), ?, ?) ");
			}
 			preparedStatement = connection.prepareStatement(sb.toString(),Statement.RETURN_GENERATED_KEYS);
 			int index = 1, userId = 0;
 			preparedStatement.setString(index++, userDTO.getEmployeeId());
 			preparedStatement.setInt(index++, userDTO.getUserGroupId().getUserGroupId());
 			preparedStatement.setString(index++, userDTO.getUsername());
			preparedStatement.setString(index++,userDTO.getPassword().concat(EncryptUtils.EncryptSHA256()));
 			//preparedStatement.setString(index++, userDTO.getFullName());
 			preparedStatement.setString(index++, userDTO.getEmail());
 			preparedStatement.setString(index++, userDTO.getTelephone());
 			//preparedStatement.setInt(index++, userDTO.getStatusId());
 			preparedStatement.setString(index++, userDTO.getDescription());
 			int rowAffect = preparedStatement.executeUpdate();
 			if (rowAffect == 1) {
 				resultSet = preparedStatement.getGeneratedKeys();
 				while (resultSet.next())
 					userId = resultSet.getInt(1);
 			}
 			connection.commit();
		    return userId;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
 		}
	}
	
	
	public Map<String, Object> insert(UserModel userModel) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ");
			query.append(DBConst.TABLE_User);
			query.append(" (employee_id, user_group_id, username, password, fullname, description, email, telephone, status_id, update_date, user_parent) ");
			
			if (userModel.getPassword().equals("")){
	 			query.append("VALUE (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), ?) ");
			}
			else{
	 			query.append("VALUE (?, ?, ?, MD5(?), ?, ?, ?, ?, ?, NOW(), ?) ");
			}
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, userId = 0;
			
			preparedStatement.setString(index++, userModel.getEmployeeId());
 			preparedStatement.setInt(index++, NullUtils.cvInt(userModel.getUserGroupId().getUserGroupId()));
 			preparedStatement.setString(index++, userModel.getUsername());
			preparedStatement.setString(index++,userModel.getPassword());
 			//preparedStatement.setString(index++, userModel.getFullName());
 			preparedStatement.setString(index++, userModel.getDescription());
 			preparedStatement.setString(index++, userModel.getEmail());
 			preparedStatement.setString(index++, userModel.getTelephone());
 			//preparedStatement.setInt(index++, NullUtils.cvInt(userModel.getStatusId()));
 			
 			int rowAffect = preparedStatement.executeUpdate();
			
 			if (rowAffect == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next())
					userId = resultSet.getInt(1);
			}
 			connection.commit();
 			Map<String, Object> result = new HashMap<>();
 			result.put("result", true);
 			result.put("userId", userId);
 			
 			return result;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
 		}
	}

	public boolean update(UserDTO userDTO) throws NoSuchAlgorithmException{
    	try{
	    	connection.setAutoCommit(false);
	    	
	    	boolean checkPassChange = true;
	    	boolean checkNullPassword = false;
	    	
	    	StringBuilder stringBuild = new StringBuilder();
	    	stringBuild.append("SELECT u.password FROM user u WHERE u.user_id = ?;");
	    	preparedStatement = connection.prepareStatement(stringBuild.toString());
	    	preparedStatement.setInt(1, userDTO.getUserId());
	    	resultSet = preparedStatement.executeQuery();
	    	while (resultSet.next()) {
				if(resultSet.getString("password").equals(userDTO.getPassword())) {
					checkPassChange = false;
				}
			}
	    	
	    	
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE USER SET employee_id=?, user_group_id=?, fullname=?, email=?, telephone=?, status_id=?, update_date = NOW(), user_parent=? "); 
				
			if (userDTO.getPassword().equals("") && checkPassChange == false){
				sb.append(", password=? "); 
			}else if(!userDTO.getPassword().equals("")){
				sb.append(", password=MD5(?) "); 
			}else {
				checkNullPassword = true;
				sb.append("");
			}
			
			sb.append("WHERE user_id=? "); 
			preparedStatement = connection.prepareStatement(sb.toString());
			int index = 1;
 			preparedStatement.setString(index++, userDTO.getEmployeeId());
 			preparedStatement.setInt(index++, userDTO.getUserGroupId().getUserGroupId());
			//preparedStatement.setString(index++,userDTO.getFullName());
			preparedStatement.setString(index++,userDTO.getEmail());	
 			preparedStatement.setString(index++, userDTO.getTelephone());	
 			//preparedStatement.setInt(index++, userDTO.getStatusId());
 			if(!checkNullPassword) {
 				if(userDTO.getPassword().equals(""))
 					preparedStatement.setString(index++, userDTO.getPassword());
 				else					
 					preparedStatement.setString(index++,userDTO.getPassword().concat(EncryptUtils.EncryptSHA256()));
 			}
 			preparedStatement.setInt(index++, userDTO.getUserId()); 			
			preparedStatement.executeUpdate();
			connection.commit();
 			return true;
    	}catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
    }
		
	
	public boolean getUserName(String userName) {
		try {
			connection.setAutoCommit(false);
			boolean resultDuplicateUserName = false;
			//select u.username from user u where u.username = 'admin';
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("select u.username ");
			stringBuilder.append("from ");
			stringBuilder.append(DBConst.TABLE_User).append(" u ");
			stringBuilder.append("where u.username = ?;");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setString(1, userName.trim());
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				resultDuplicateUserName = true;
			}
			return resultDuplicateUserName;
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}

    public boolean updateDelete(int userId){
    	try{
	    	connection.setAutoCommit(false);
			StringBuilder sb = new StringBuilder();
			sb.append("UPDATE USER SET status_id=2, update_date = NOW() "); 
			sb.append("WHERE user_id=? "); 
			preparedStatement = connection.prepareStatement(sb.toString());
			preparedStatement.setInt(1,userId);
			preparedStatement.executeUpdate();
			connection.commit();
 			return true;
    	}catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
    }
    
    public boolean delete(int userId){
    	try{
	    	connection.setAutoCommit(false);
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM USER "); 
			sb.append("WHERE user_id = ? "); 
			preparedStatement = connection.prepareStatement(sb.toString());
			preparedStatement.setInt(1,userId);
			preparedStatement.executeUpdate();
			connection.commit();
 			return true;
    	}catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
    }
    
    public boolean deleteByUserId(UserDTO userDTO) {
    	try {
			connection.setAutoCommit(false);
			
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_User).append(" ");
			query.append("WHERE user_id = ? "); 
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, userDTO.getUserId());
			preparedStatement.executeUpdate();
			
			connection.commit();				
			return true;
			
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
    }
    
    public boolean flagDeleteByUserId(UserDTO userDTO) {
    	try {
	    	connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_User).append(" ");
			query.append("SET is_deleted = 'Y', enable = 'N' ");
			query.append("WHERE (user_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setInt(1, userDTO.getUserId());
    		preparedStatement.executeUpdate();
    		logger.info(userDTO.getUserId());
    		
    		connection.commit();
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
    
    public boolean deleteByUserParent(UserDTO userDTO) {
    	try {
			connection.setAutoCommit(false);
			
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_User).append(" ");
			query.append("WHERE user_parent = ?");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, userDTO.getUserId());
			preparedStatement.executeUpdate();
			
			connection.commit();				
			return true;
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
    }
    
    

    public int countAllUser(String queryWhereClause) {
    	int totalUser = 0;
    	try {
    		connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_User).append(" u ");
    		query.append("LEFT JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
    		query.append("ON u.user_group_id = ug.user_group_id  ");
    		query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sup_map ");
    		query.append("ON sup_map.user_id = u.user_id ");
    		query.append("WHERE 1=1 and is_deleted = 'N' ");
    		if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
    		query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				totalUser = resultSet.getInt("total");
			}
			return totalUser;
    		
    	}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalUser;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
    }
    
    public int countUser(String whereClause) {
		try {
			int totalUser = 0;
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	COUNT(*) AS total ");
			sb.append("FROM ").append(DBConst.TABLE_User).append(" u ");
			sb.append("INNER JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
			sb.append("ON u.user_group_id = ug.user_group_id ");
			sb.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ON sm.user_id = u.user_id ");
			sb.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ").append("ON sm.supplier_id = s.supplier_id ");
			sb.append("WHERE (u.user_group_id = ? OR u.user_group_id = ? OR u.user_group_id = ? OR u.user_group_id = ?)");
			sb.append(whereClause);
			sb.append(";");
 			preparedStatement = connection.prepareStatement(sb.toString());
 			preparedStatement.setInt(1, Config.AUTHEN_USERTYPE_ADMINISTRATOR);
			preparedStatement.setInt(2, Config.AUTHEN_USERTYPE_AUDITOR);
			preparedStatement.setInt(3, Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN);
			preparedStatement.setInt(4, Config.AUTHEN_USERTYPE_PURCHASING_OFFICER);
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {

 				totalUser = resultSet.getInt("total"); 				
 			}
			return totalUser;
			
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
    
    
    public boolean updatePdpaStatus(int userId, String pdpaNo,String status){
    	
    	/*
    	try {		
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("UPDATE ").append(DBConst.TABLE_User).append(" ");
    		query.append("SET `accept_pdpa_no` = ? , ");	
			query.append("`accept_pdpa_status` = 'Y' , ");
			query.append("`accept_pdpa_date` = now() ");
			query.append(" WHERE `user_id` = ? ; ");
    		
    		preparedStatement = connection.prepareStatement(query.toString());
      		logger.info(query);
      		
    		preparedStatement.setInt(1, NullUtils.cvInt(pdpaNo) );
    		preparedStatement.setString(2,userId);
    		
    		int result = preparedStatement.executeUpdate();
    		
    		if(result == 1) {
    			return true;
    		}else {
    			return false;
    		}
    		
    		
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
		*/
    	
    	try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_User).append(" ");
			
			query.append("SET `accept_pdpa_no` = ? ,");
			query.append("`accept_pdpa_status` = ? ,");
			query.append("`accept_pdpa_date` = now() ");

			query.append(" WHERE `user_id` = ? ; ");
			
			logger.info(query);
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			logger.info( NullUtils.cvInt(pdpaNo) );
			logger.info( userId );
			
			preparedStatement.setInt(index++, NullUtils.cvInt(pdpaNo));
			preparedStatement.setString(index++, status);
			preparedStatement.setInt(index++, userId);
			
			preparedStatement.executeUpdate();
			
			return true;
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
    	
    }

}
