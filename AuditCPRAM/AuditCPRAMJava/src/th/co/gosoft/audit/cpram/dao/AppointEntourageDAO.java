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

public class AppointEntourageDAO extends StandardAttributeDAO {
	
	private final static Logger logger = Logger.getLogger(AppointEntourageDAO.class);
	public AppointEntourageDAO(Connection connection) {
		super(connection);
	}
		
	public List<AppointDTO> getAppointEntourageList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		
		try {
			
			/*SELECT app.appoint_id, app.appoint_create_by, app.appoint_date, app.appoint_status_id, app.detail, app.title, usr.user_id, usr.fullname, usr.description, usr.telephone
			FROM appoint app 
			RIGHT JOIN appoint_entourage app_ent
			ON app.appoint_id = app_ent.appoint_id
			LEFT JOIN user usr
			ON app_ent.entourage_id = usr.user_id 
			WHERE 1=1*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT app.appoint_id, app.appoint_create_by, app.appoint_date, app.appoint_status_id, app.detail, app.title, usr.user_id, usr.fullname, usr.description, usr.telephone ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" app ");
			query.append("RIGHT JOIN ").append(DBConst.TABLE_Appoint_Entourage).append(" app_ent ");
			query.append("ON app.appoint_id = app_ent.appoint_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" usr ");
			query.append("ON app_ent.entourage_id = usr.user_id ");
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
				
				if(resultSet.getInt("user_id") != 0) {
					UserDTO userDTO = new UserDTO();
					userDTO.setUserId(resultSet.getInt("user_id"));
					userDTO.setFullname(resultSet.getString("fullname"));
					userDTO.setDescription(resultSet.getString("description"));
					userDTO.setTelephone(resultSet.getString("telephone"));
					appointDTO.setEntourageId(new ArrayList<>());
					appointDTO.getEntourageId().add(userDTO);
				}
				
				
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
	
	public boolean insertAppointEntourage(AppointDTO appointDTO, UserDTO entourageDTO) {
		try {
			
			//INSERT INTO `appoint_entourage` (`appoint_id`, `entourage_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('3', '2', 'Y', '1', 'now()', '1', 'now()');

			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Appoint_Entourage).append(" ");
			query.append("(appoint_id, entourage_id, enable, create_by, create_date, update_by, update_date) ");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, appointDTO.getAppointId());
			preparedStatement.setInt(index++, entourageDTO.getUserId());
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
	
	public boolean deleteAppointEntourageWithAppoint(AppointDTO appointDTO) {
		try {
			//DELETE FROM `audit_supplier_cpram_final`.`appoint_entourage` WHERE (`appoint_id` = '3') and (`entourage_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Appoint_Entourage).append(" ");
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
	
	public int countAppointEntourageList(String queryWhereClause) {
		int totalAppointMapping = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" app ");
			query.append("RIGHT JOIN ").append(DBConst.TABLE_Appoint_Entourage).append(" app_ent ");
			query.append("ON app.appoint_id = app_ent.appoint_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" usr ");
			query.append("ON app_ent.entourage_id = usr.user_id ");
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
	
}
