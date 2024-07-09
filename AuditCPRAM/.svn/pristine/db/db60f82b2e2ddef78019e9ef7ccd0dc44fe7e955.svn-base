package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.AppointHistoryDTO;
import th.co.gosoft.audit.cpram.dto.AppointStatusDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class AppointHistoryDAO extends StandardAttributeDAO{
	

	private final static Logger logger = Logger.getLogger(AppointHistoryDAO.class);
	
	public AppointHistoryDAO(Connection connection) {
		super(connection);
	}
	
	
	public List<AppointHistoryDTO> getAppointHistory(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT app_his.appoint_history_id, app_his.appoint_status_id, app_his.title, app_his.detail, a.appoint_id, usr.user_id, usr.fullname, usr.user_group_id, sup.supplier_company 
			FROM appoint_history app_his
			LEFT JOIN appoint a ON app_his.appoint_id = a.appoint_id
			LEFT JOIN user usr ON app_his.create_by = usr.user_id
			LEFT JOIN supplier_user_mapping sup_usr_map ON usr.user_id = sup_usr_map.user_id
			LEFT JOIN supplier sup ON sup_usr_map.supplier_id = sup.supplier_id
			LEFT JOIN appoint_status app_status ON app_his.appoint_status_id = app_status.appoint_status_id
			WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT app_his.appoint_history_id, app_his.appoint_status_id, app_status.appoint_status_name, app_his.title, app_his.detail, a.appoint_id, usr.user_id, usr.fullname, usr.user_group_id, sup.supplier_id, sup.supplier_company, app_his.create_date ");
			query.append("FROM ").append(DBConst.TABLE_Appoint_History).append(" app_his ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Appoint).append(" a ");
			query.append("ON app_his.appoint_id = a.appoint_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" usr ");
			query.append("ON app_his.create_by = usr.user_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sup_usr_map ");
			query.append("ON usr.user_id = sup_usr_map.user_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" sup ");
			query.append("ON sup_usr_map.supplier_id = sup.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Appoint_Status).append(" app_status ");
			query.append("ON app_his.appoint_status_id = app_status.appoint_status_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by app_his.create_date asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<AppointHistoryDTO> appointHistoryDTOs = new ArrayList<>();
			while(resultSet.next()) {
				AppointHistoryDTO appointHistoryDTO = new AppointHistoryDTO();
				appointHistoryDTO.setAppointHistoryId(resultSet.getInt("appoint_history_id"));
				appointHistoryDTO.setTitle(resultSet.getString("title"));
				appointHistoryDTO.setDetail(resultSet.getString("detail"));
				appointHistoryDTO.setCreateDate(resultSet.getDate("create_date"));
				appointHistoryDTO.setCreateTime(resultSet.getTime("create_date"));
							
				
				appointHistoryDTO.setAppointStatusId(new AppointStatusDTO());
				appointHistoryDTO.getAppointStatusId().setAppointStatusId(resultSet.getInt("appoint_status_id"));
				appointHistoryDTO.getAppointStatusId().setAppointStatusName(resultSet.getString("appoint_status_name"));
				
				appointHistoryDTO.setAppointId(new AppointDTO());
				appointHistoryDTO.getAppointId().setAppointId(resultSet.getInt("appoint_id"));
				
				appointHistoryDTO.setAppointHistoryCreateBy(new UserDTO());
				appointHistoryDTO.getAppointHistoryCreateBy().setUserId(resultSet.getInt("user_id"));
				appointHistoryDTO.getAppointHistoryCreateBy().setFullname(resultSet.getString("fullname"));
				
				appointHistoryDTO.getAppointHistoryCreateBy().setUserGroupId(new UserGroupDTO());
				appointHistoryDTO.getAppointHistoryCreateBy().getUserGroupId().setUserGroupId(resultSet.getInt("user_group_id"));
				
				SupplierDTO supplier = new SupplierDTO();
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				
				appointHistoryDTO.getAppointHistoryCreateBy().setSupplierId(new ArrayList<>());
				appointHistoryDTO.getAppointHistoryCreateBy().getSupplierId().add(supplier);
				
				
				appointHistoryDTOs.add(appointHistoryDTO);
			}
			
			return appointHistoryDTOs;
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
	
	public int insertAppointHistory(AppointHistoryDTO appointHistoryDTO) {
		//INSERT INTO `appoint_history` (`appoint_id`, `title`, `detail`, `appoint_status_id`, `enable`, `create_by`, `create_date`, `upadate_by`, `update_date`) VALUES ('2', 'sdfasdfasdf', 'asdfasdfasdf', '1', 'Y', '1', 'now()', '1', 'now()');
		int primaryKeyAppointHistory = 0;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Appoint_History).append(" ");
			query.append("(appoint_id, title, detail, appoint_status_id, enable, create_by, create_date, upadate_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, appointHistoryDTO.getAppointId().getAppointId());
			preparedStatement.setString(index++, appointHistoryDTO.getTitle());
			preparedStatement.setString(index++, appointHistoryDTO.getDetail());
			preparedStatement.setInt(index++, appointHistoryDTO.getAppointStatusId().getAppointStatusId());
			preparedStatement.setString(index++, NullUtils.cvStr(appointHistoryDTO.getEnable()));
			preparedStatement.setInt(index++, appointHistoryDTO.getCreateBy());
			preparedStatement.setInt(index++, appointHistoryDTO.getUpdateBy());
			
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					primaryKeyAppointHistory = resultSet.getInt(1);
				}
			}
				
			return primaryKeyAppointHistory;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryKeyAppointHistory;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public boolean deleteAppointHistoryByAppoint(AppointDTO appointDTO) {
		try {
			
			//DELETE FROM `audit_supplier_cpram_final`.`appoint_history` WHERE (`appoint_history_id` = '66');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Appoint_History).append(" ");
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
	
	public int countAppointHistory(String queryWhereClause) {
		int totalAppointHistory = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Appoint_History).append(" app_his ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Appoint).append(" a ");
			query.append("ON app_his.appoint_id = a.appoint_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" usr ");
			query.append("ON app_his.create_by = usr.user_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sup_usr_map ");
			query.append("ON usr.user_id = sup_usr_map.user_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" sup ");
			query.append("ON sup_usr_map.supplier_id = sup.supplier_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalAppointHistory = resultSet.getInt("total");
			}
			return totalAppointHistory;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalAppointHistory;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	/*public List<AppointHistoryDTO> appointHistoryListByAppoint(AppointDTO appointDTO){
		try {
			connection.setAutoCommit(false);
			List<AppointHistoryDTO> appointHistoryDTOs = new ArrayList<>();
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT aph.appoint_history_id, aph.appoint_id, aph.appoint_status_id, aph.create_by, aph.create_date, aph.detail, aph.title ");
			query.append("FROM ").append(DBConst.TABLE_Appoint_History).append(" aph ");
			query.append("WHERE aph.appoint_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, appointDTO.getAppointId());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				AppointHistoryDTO appointHistoryDTO = new AppointHistoryDTO();
				AppointStatusDTO appointStatusDTO = new AppointStatusDTO();
				appointHistoryDTO.setAppointHistoryId(resultSet.getInt("appoint_history_id"));
				appointHistoryDTO.setAppointId(resultSet.getInt("appoint_id"));
				appointStatusDTO.setAppointStatusId(resultSet.getInt("appoint_status_id"));
				appointHistoryDTO.setAppointStatusId(appointStatusDTO);
				appointHistoryDTO.setCreateBy(resultSet.getInt("create_by"));
				appointHistoryDTO.setCreateDate(resultSet.getDate("create_date"));
				appointHistoryDTO.setCreateTime(resultSet.getTime("create_date"));
				appointHistoryDTO.setDetail(resultSet.getString("detail"));
				appointHistoryDTO.setTitle(resultSet.getString("title"));
				appointHistoryDTOs.add(appointHistoryDTO);				
			}
			return appointHistoryDTOs;
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean deleteByAppointId(AppointHistoryDTO appointHistoryDTO) {
		try {
			
			//DELETE FROM `appoint_history` WHERE (`appoint_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Appoint_History).append(" ");
			query.append("WHERE (appoint_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, appointHistoryDTO.getAppointId());
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
	
	public boolean insert(AppointHistoryDTO appointHistoryDTO) {
		try {
			
			//INSERT INTO `appoint_history` (`appoint_id`, `appoint_status_id`, `title`, `detail`, `status_id`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2', '1', 'dsfsdf', 'sdfsdf', '1', '1', 'now()', '1', 'now()');
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Appoint_History).append(" ");
			query.append("(appoint_id, appoint_status_id, title, detail, status_id, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, appointHistoryDTO.getAppointId());
			preparedStatement.setInt(index++, appointHistoryDTO.getAppointStatusId().getAppointStatusId());
			preparedStatement.setString(index++, appointHistoryDTO.getTitle());
			preparedStatement.setString(index++, appointHistoryDTO.getDetail());
			preparedStatement.setInt(index++, appointHistoryDTO.getStatusId());
			preparedStatement.setInt(index++, appointHistoryDTO.getCreateBy());
			preparedStatement.setInt(index++, appointHistoryDTO.getUpdateBy());
			
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
	}*/
	
}
