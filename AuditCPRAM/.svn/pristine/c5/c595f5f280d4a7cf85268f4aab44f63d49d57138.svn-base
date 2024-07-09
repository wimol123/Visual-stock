package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class UserGroupDAO extends StandardAttributeDAO{
	public UserGroupDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	private final static Logger logger = Logger.getLogger(UserGroupDAO.class);

	
	public List<UserGroupDTO> getUserGroupList(String queryWhereClause) {
		try {
			List<UserGroupDTO> userGroupDTOList = new ArrayList<UserGroupDTO>();
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	user_group_id ");
			sb.append("	,user_group_name ");
			sb.append(" ,enable ");
			sb.append("FROM ").append(DBConst.TABLE_User_Group).append(" ");
			sb.append("WHERE enable='Y' "); 
			sb.append("  AND (user_group_id=? OR  user_group_id=? OR user_group_id=? OR user_group_id=? OR user_group_id=? OR user_group_id=? OR user_group_id=? OR user_group_id=?) "); 
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				sb.append(queryWhereClause);
			}
 			preparedStatement = connection.prepareStatement(sb.toString());
			preparedStatement.setInt(1,Config.AUTHEN_USERTYPE_ADMINISTRATOR);	
			preparedStatement.setInt(2,Config.AUTHEN_USERTYPE_AUDITOR);
			preparedStatement.setInt(3, Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN);
			preparedStatement.setInt(4, Config.AUTHEN_USERTYPE_SUPPLIER_QA);
			preparedStatement.setInt(5, Config.AUTHEN_USERTYPE_PURCHASING_OFFICER);
			preparedStatement.setInt(6, Config.AUTHEN_USERTYPE_APPROVE_AUDIT_RESULT);
			preparedStatement.setInt(7, Config.AUTHEN_USERTYPE_REGISTER);
			preparedStatement.setInt(8, Config.AUTHEN_USERTYPE_SUPPLIER_SALE);
			
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {
 				UserGroupDTO userGroupDTO = new UserGroupDTO();
 				userGroupDTO.setUserGroupId(resultSet.getInt("user_group_id"));
 				userGroupDTO.setUserGroupName(resultSet.getString("user_group_name"));
 				userGroupDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
 				userGroupDTOList.add(userGroupDTO);
 			}
			return userGroupDTOList;
		}catch(SQLException e) {
 			logger.error(e.getMessage(), e);
 			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public UserGroupDTO getAuthenTypeByUserName(UserDTO userDTO) {
    	try {
    		
    		/*SELECT ug.authen_type FROM USER u 
    		inner join USER_GROUP ug ON u.user_group_id=ug.user_group_id
    		WHERE u.username='admin' AND u.enable='Y'*/
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("SELECT ug.authen_type ");
    		query.append("FROM ").append(DBConst.TABLE_User).append(" u ");
    		query.append("INNER JOIN ").append(DBConst.TABLE_User_Group).append(" ug ");
    		query.append("ON u.user_group_id = ug.user_group_id ");
    		query.append("WHERE u.username = ? AND u.enable='Y' AND u.is_deleted='N' ;");
    		
    		preparedStatement = connection.prepareStatement(query.toString());
    		preparedStatement.setString(1, userDTO.getUsername());
    		resultSet = preparedStatement.executeQuery();
    		
    		UserGroupDTO userGroupDTO = null;
    		while(resultSet.next()) {    		
    			userGroupDTO = new UserGroupDTO();
    			userGroupDTO.setAuthenType(resultSet.getString("authen_type"));
   			
    		}
    		return userGroupDTO;
    		
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

    
}
