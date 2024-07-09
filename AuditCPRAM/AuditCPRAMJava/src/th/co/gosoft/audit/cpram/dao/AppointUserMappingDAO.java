package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.AppointStatusDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class AppointUserMappingDAO extends StandardAttributeDAO{
	
	public AppointUserMappingDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(AppointUserMappingDAO.class);

	
	
	public List<AppointDTO> getAppointUserMappingList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT app.appoint_id, app.appoint_create_by, app.appoint_date, app.appoint_status_id, app.detail, app.title, usr.user_id, usr.fullname, usr.description, usr.telephone
			FROM appoint app 
			RIGHT JOIN appoint_user_mapping app_usr_map
			ON app.appoint_id = app_usr_map.appoint_id
			LEFT JOIN user usr
			ON app_usr_map.user_id = usr.user_id 
			WHERE 1=1*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT app.appoint_id, app.appoint_create_by, app.appoint_date, app.appoint_status_id, app.detail, app.title, usr.user_id, usr.fullname, usr.description, usr.telephone ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" app ");
			query.append("RIGHT JOIN ").append(DBConst.TABLE_Appoint_User_Mapping).append(" app_usr_map ");
			query.append("ON app.appoint_id = app_usr_map.appoint_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" usr ");
			query.append("ON app_usr_map.user_id = usr.user_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by app.appoint_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<AppointDTO> appointDTOs = new ArrayList<>();
			while(resultSet.next()) {
				AppointDTO appointDTO = new AppointDTO();
				
				appointDTO.setAppointId(resultSet.getInt("appoint_id"));
				appointDTO.setAppointDate(resultSet.getDate("appoint_date"));
				appointDTO.setAppointTime(resultSet.getTime("appoint_date"));
				appointDTO.setDetail(resultSet.getString("detail"));
				appointDTO.setTitle(resultSet.getString("title"));
				
				appointDTO.setAppointCreateBy(new UserDTO());
				appointDTO.getAppointCreateBy().setUserId(resultSet.getInt("appoint_create_by"));
				
				appointDTO.setAppointStatusId(new AppointStatusDTO());
				appointDTO.getAppointStatusId().setAppointStatusId(resultSet.getInt("appoint_status_id"));
				
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				userDTO.setFullname(resultSet.getString("fullname"));
				userDTO.setDescription(resultSet.getString("description"));
				userDTO.setTelephone(resultSet.getString("telephone"));
				appointDTO.setAuditorId(new ArrayList<>());
				appointDTO.getAuditorId().add(userDTO);
				
				appointDTOs.add(appointDTO);
			}
			return appointDTOs;
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
	
	public boolean insertAppointUserMapping(AppointDTO appointDTO, UserDTO auditDTO) {
		try {
			
			connection.setAutoCommit(false);
			//INSERT INTO `appoint_user_mapping` (`appoint_id`, `user_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('3', '10', 'Y', '1', 'now()', '1', 'now()');
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Appoint_User_Mapping).append(" ");
			query.append("(appoint_id, user_id, enable, create_by, create_date, update_by, update_date) ");	
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, appointDTO.getAppointId());
			preparedStatement.setInt(index++, auditDTO.getUserId());
			preparedStatement.setString(index++, NullUtils.cvStr(appointDTO.getEnable()));
			preparedStatement.setInt(index++, appointDTO.getCreateBy());
			preparedStatement.setInt(index++, appointDTO.getUpdateBy());
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
	
	public boolean deleteAppointUserMapping(AppointDTO appointDTO, UserDTO userDTO) {
		try {
			
			//DELETE FROM `appoint_user_mapping` WHERE (`appoint_id` = '3') and (`user_id` = '9');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Appoint_User_Mapping).append(" ");
			query.append("WHERE (appoint_id = ?) and (user_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, appointDTO.getAppointId());
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
	
	
	public boolean deleteAppointUserMappingWithAppoint(AppointDTO appointDTO) {
		try {
			
			//DELETE FROM `appoint_user_mapping` WHERE (`appoint_id` = '3') and (`user_id` = '9');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Appoint_User_Mapping).append(" ");
			query.append("WHERE (appoint_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, appointDTO.getAppointId());
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
	
	public int countAppointUserMappingList(String queryWhereClause) {
		int totalAppointMapping = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" app ");
			query.append("RIGHT JOIN ").append(DBConst.TABLE_Appoint_User_Mapping).append(" app_usr_map ");
			query.append("ON app.appoint_id = app_usr_map.appoint_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" usr ");
			query.append("ON app_usr_map.user_id = usr.user_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalAppointMapping = resultSet.getInt("total");
			}
			return totalAppointMapping;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalAppointMapping;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	/*public boolean deleteByAppointId(AppointDTO appointDTO) {
		try {
			//DELETE FROM `appoint_user_mapping` WHERE (`appoint_id` = '1') and (`user_id` = '9');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Appoint_User_Mapping).append(" ");
			query.append("WHERE (appoint_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, appointDTO.getAppointId());
			preparedStatement.executeUpdate();
			connection.commit();
			
			return true;
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			log.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean insert(AppointDTO appointDTO, UserDTO userDTO) {
		try {
			
			//INSERT INTO `appoint_user_mapping` (`appoint_id`, `user_id`, `status_id`, `create_date`) VALUES ('3', '3', '1', 'now()');

			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Appoint_User_Mapping).append(" ");
			query.append("(appoint_id, user_id, status_id, create_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, appointDTO.getAppointId());
			preparedStatement.setInt(index++, userDTO.getUserId());
			preparedStatement.setInt(index++, appointDTO.getStatusId());
			preparedStatement.executeUpdate();
			connection.commit();
			
			return true;
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			log.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<UserDTO> getUserListMappingAppoint(AppointDTO appointDTO){
		try {
			
			SELECT aum.user_id, u.fullname FROM appoint_user_mapping aum
			LEFT JOIN user u ON u.user_id = aum.user_id
			WHERE aum.appoint_id = 14
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT aum.user_id, u.fullname ");
			query.append("FROM ").append(DBConst.TABLE_Appoint_User_Mapping).append(" aum ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON u.user_id = aum.user_id WHERE aum.appoint_id = ?");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, appointDTO.getAppointId());
			resultSet = preparedStatement.executeQuery();
			
			List<UserDTO> userDTOs = new ArrayList<>();
			while(resultSet.next()) {
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				//userDTO.setFullName(resultSet.getString("fullname"));
				userDTOs.add(userDTO);
			}
			return userDTOs;
			
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}*/
}
