package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.AppointStatusDTO;
import th.co.gosoft.audit.cpram.dto.AppointTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class AppointDAO extends StandardAttributeDAO{

	private final static Logger logger = Logger.getLogger(AppointDAO.class);	
	
	public AppointDAO(Connection connection) {
		super(connection);
	}
	
	public List<AppointDTO> getAppointList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT a.appoint_id, a.appoint_type_id, a.appoint_create_by, u.fullname, a.supplier_id, sup.supplier_company, a.appoint_date, a.title, a.detail, a.appoint_status_id, app_status.appoint_status_name, app_status.status_color
			FROM appoint a
			LEFT JOIN user u ON a.appoint_create_by = u.user_id
			LEFT JOIN appoint_status app_status ON a.appoint_status_id = app_status.appoint_status_id
			LEFT JOIN supplier sup ON a.supplier_id = sup.supplier_id*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT a.appoint_id, a.appoint_type_id, a.appoint_create_by, a.location_id, sup_map.location_name, sup_map.address_id, u.fullname, a.supplier_id, sup.supplier_company, a.appoint_date, a.title, a.detail, a.appoint_status_id, app_status.appoint_status_name, app_status.status_color ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" a ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON a.appoint_create_by = u.user_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Appoint_Status).append(" app_status ");
			query.append("ON a.appoint_status_id = app_status.appoint_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" sup ");
			query.append("ON a.supplier_id = sup.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("ON a.location_id = sup_map.id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by a.appoint_date desc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<AppointDTO> appointDTOs = new ArrayList<>();
			while(resultSet.next()) {
				AppointDTO appoint = new AppointDTO();
				
				appoint.setAppointId(resultSet.getInt("appoint_id"));
				appoint.setAppointDate(resultSet.getDate("appoint_date"));
				appoint.setAppointTime(resultSet.getTime("appoint_date"));
				appoint.setTitle(resultSet.getString("title"));
				appoint.setDetail(resultSet.getString("detail"));
				
												
				AppointTypeDTO appointTypeId = new AppointTypeDTO();
				appointTypeId.setAppointTypeId(resultSet.getInt("appoint_type_id"));
				appoint.setAppointTypeId(appointTypeId);		
				
				appoint.setAppointCreateBy(new UserDTO());
				appoint.getAppointCreateBy().setUserId(resultSet.getInt("appoint_create_by"));
				appoint.getAppointCreateBy().setFullname(resultSet.getString("fullname"));
				
				appoint.setSupplierId(new SupplierDTO());
				appoint.getSupplierId().setSupplierId(resultSet.getInt("supplier_id"));
				appoint.getSupplierId().setSupplierCompany(resultSet.getString("supplier_company"));
				
				appoint.setAppointStatusId(new AppointStatusDTO());
				appoint.getAppointStatusId().setAppointStatusId(resultSet.getInt("appoint_status_id"));
				appoint.getAppointStatusId().setAppointStatusName(resultSet.getString("appoint_status_name"));
				appoint.getAppointStatusId().setStatusColor(resultSet.getString("status_color"));
				
				appoint.setLocationId(new SupplierProductAddressMappingDTO());
				appoint.getLocationId().setAddressId(new AddressDTO());
				appoint.getLocationId().getAddressId().setAddressId(resultSet.getInt("address_id"));
				appoint.getLocationId().setLocationName(resultSet.getString("location_name"));
				appoint.getLocationId().setId(resultSet.getInt("location_id"));
				
				appointDTOs.add(appoint);
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
	
	
	public int insertAppoint(AppointDTO appointDTO) {
		int primaryKeyAppoint = 0;
		try {
			//INSERT INTO `appoint` (`appoint_type_id`, `appoint_create_by`, `supplier_id`, `location_id`, `appoint_date`, `title`, `detail`, `appoint_status_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2', '1', '2', '1', '2019-01-12 15:00:00', 'sdafasdfasdfasf', 'asdfasdfasdfasdf', '1', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Appoint).append(" ");
			query.append("(appoint_type_id, appoint_create_by, supplier_id, location_id, appoint_date, title, detail, appoint_status_id, enable, create_by, create_date, update_by, update_date) ");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, appointDTO.getAppointTypeId().getAppointTypeId());
			preparedStatement.setInt(index++, appointDTO.getAppointCreateBy().getUserId());
			preparedStatement.setInt(index++, appointDTO.getSupplierId().getSupplierId());
			preparedStatement.setInt(index++, appointDTO.getLocationId().getId());
			preparedStatement.setString(index++, String.format("%s %s", appointDTO.getAppointDate(),appointDTO.getAppointTime()));
			preparedStatement.setString(index++, appointDTO.getTitle());
			preparedStatement.setString(index++, appointDTO.getDetail());
			preparedStatement.setInt(index++, appointDTO.getAppointStatusId().getAppointStatusId());
			preparedStatement.setString(index++, NullUtils.cvStr(appointDTO.getEnable()));
			preparedStatement.setInt(index++, appointDTO.getCreateBy());
			preparedStatement.setInt(index++, appointDTO.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					primaryKeyAppoint = resultSet.getInt(1);					
				}
			}
			
			return primaryKeyAppoint;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryKeyAppoint;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public boolean updateAppoint(AppointDTO appointDTO) {
		try {
			
			//UPDATE `appoint` SET `appoint_date` = '2019-01-20 10:35:00', `title` = 'นัดหมายการเข้า Auditsdfadf', `detail` = 'นัดหมายการเข้า Audit กับ Company A (ยืนยันการเลื่อนนัด ครั้งที่ 2)www', `appoint_status_id` = '2', `update_by` = '3', `update_date` = 'now()' WHERE (`appoint_id` = '3');

			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Appoint).append(" ");
			query.append(" SET ");
			query.append("appoint_date = ?, ");
			query.append("title = ?, ");
			query.append("detail = ?, ");
			query.append("appoint_status_id = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (appoint_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, String.format("%s %s", appointDTO.getAppointDate(), appointDTO.getAppointTime()));
			preparedStatement.setString(index++, appointDTO.getTitle());
			preparedStatement.setString(index++, appointDTO.getDetail());
			preparedStatement.setInt(index++, appointDTO.getAppointStatusId().getAppointStatusId());
			preparedStatement.setInt(index++, appointDTO.getUpdateBy());
			preparedStatement.setInt(index++, appointDTO.getAppointId());
			
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
	
	public boolean updateAppointAfterFinalResult (int appointId) {
		int rowAffective = 0;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Appoint).append(" ");
			query.append(" SET ");
			query.append("appoint_status_id = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (appoint_id = ? AND appoint_status_id = 1);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, 5);
			preparedStatement.setInt(2, appointId);
			
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective > 0) {
				return true;
			}else {
				return false;
			}
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	
	public boolean deleteAppoint(AppointDTO appointDTO) {
		try {
			//DELETE FROM `audit_supplier_cpram_final`.`appoint` WHERE (`appoint_id` = '17');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Appoint).append(" ");
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
	
	public int countAppointList(String queryWhereClause) {
		int totalAppoint = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" a ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON a.appoint_create_by = u.user_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Appoint_Status).append(" app_status ");
			query.append("ON a.appoint_status_id = app_status.appoint_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" sup ");
			query.append("ON a.supplier_id = sup.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("ON a.location_id = sup_map.id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalAppoint = resultSet.getInt("total");
			}
			return totalAppoint;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalAppoint;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	/*public List<AppointDTO> getAppointList(Integer startRecord, Integer numOfRows, String whereClause) {
		try {
			
			SELECT a.appoint_id,a.appoint_create_by,u.fullname,s.supplier_id,s.supplier_company,a.appoint_date,a.appoint_type_id,a.title,a.detail,aps.appoint_status_id,aps.appoint_status_name,aps.status_color
			FROM appoint a LEFT join supplier s ON a.supplier_id = s.supplier_id
			LEFT join appoint_status aps ON a.appoint_status_id = aps.appoint_status_id
			LEFT join user u ON a.appoint_create_by = u.user_id
			WHERE 1=1;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT a.appoint_id,a.appoint_create_by,u.fullname,u.user_id,s.supplier_id,s.supplier_company,a.appoint_date,a.appoint_type_id,a.title,a.detail,aps.appoint_status_id,aps.appoint_status_name,aps.status_color ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" a ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON a.supplier_id = s.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Appoint_Status).append(" aps ");
			query.append("ON a.appoint_status_id = aps.appoint_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON a.appoint_create_by = u.user_id WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			if (numOfRows != -1) {
				query.append(String.format(" order by a.appoint_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");

			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();

			List<AppointDTO> appointList = new ArrayList<>();
			while (resultSet.next()) {
				AppointDTO appointDTO = new AppointDTO();
				appointDTO.setAppointId(resultSet.getInt("appoint_id"));
				appointDTO.setAppointDate(resultSet.getDate("appoint_date"));
				appointDTO.setAppointTime(resultSet.getTime("appoint_date"));
				appointDTO.setAppointTypeId(resultSet.getInt("appoint_type_id"));
				appointDTO.setTitle(resultSet.getString("title"));
				appointDTO.setDetail(resultSet.getString("detail"));
				
				UserDTO userDTO = new UserDTO();
				//userDTO.setFullName(resultSet.getString("fullname"));
				userDTO.setUserId(resultSet.getInt("user_id"));
				appointDTO.setAppointCreateBy(userDTO);
				
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				appointDTO.setSupplierId(supplierDTO);
				
				AppointStatusDTO appointStatusDTO = new AppointStatusDTO();
				appointStatusDTO.setAppointStatusId(resultSet.getInt("appoint_status_id"));
				appointStatusDTO.setAppointStatusName(resultSet.getString("appoint_status_name"));
				appointStatusDTO.setStatusColor(resultSet.getString("status_color"));
				appointDTO.setAppointStatusId(appointStatusDTO);
				
				appointList.add(appointDTO);
			}

			return appointList;
		} catch(SQLException e){
			logger.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public List<AppointDTO> getAppointList() {

		try {
			connection.setAutoCommit(false);

			StringBuilder query = new StringBuilder();
			query.append("SELECT a.appoint_id,a.appoint_create_by,u.fullname,s.supplier_id,s.supplier_company,a.appoint_date,a.appoint_type_id,a.title,a.detail,aps.appoint_status_id,aps.appoint_status_name,aps.status_color ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" a ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON a.supplier_id = s.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Appoint_Status).append(" aps ");
			query.append("ON a.appoint_status_id = aps.appoint_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("On a.appoint_create_by = u.user_id;");

			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();

			List<AppointDTO> appointList = new ArrayList<>();
			while (resultSet.next()) {
				AppointDTO appoint = new AppointDTO();
				UserDTO appointCreateDTO = new UserDTO();
				SupplierDTO supplierDTO = new SupplierDTO();
				AppointStatusDTO appointStatusId = new AppointStatusDTO();
				appoint.setAppointId(resultSet.getInt("appoint_id"));
				appointCreateDTO.setUserId(resultSet.getInt("appoint_create_by"));
				//appointCreateDTO.setFullName(resultSet.getString("fullname"));
				appoint.setAppointCreateBy(appointCreateDTO);
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				appoint.setSupplierId(supplierDTO);
				appoint.setAppointDate(resultSet.getDate("appoint_date"));
				appoint.setAppointTime(resultSet.getTime("appoint_date"));
				appoint.setAppointTypeId(resultSet.getInt("appoint_type_id"));
				appoint.setTitle(resultSet.getString("title"));
				appoint.setDetail(resultSet.getString("detail"));
				appointStatusId.setAppointStatusId(resultSet.getInt("appoint_status_id"));
				appointStatusId.setAppointStatusName(resultSet.getString("appoint_status_name"));
				appointStatusId.setStatusColor(resultSet.getString("status_color"));
				appoint.setAppointStatusId(appointStatusId);

				appointList.add(appoint);
			}
			
			return appointList;

		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw new RuntimeException(e.toString(), e);
		}

	}

	public AppointDTO getAppointHistory(String appointId) {
		try {
			connection.setAutoCommit(false);

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append(
					"SELECT s.supplier_id,s.supplier_company,ah.appoint_history_id,ah.title,ah.detail,ah.create_date,ah.create_by,ah.status_id,usr.fullname,usr.user_group_id");
			stringBuilder.append(" FROM ");
			stringBuilder.append(DBConst.TABLE_Appoint_History.toString().trim()).append(" ah ");
			stringBuilder.append("JOIN ").append(DBConst.TABLE_Appoint).append(" a ");
			stringBuilder.append("ON ah.appoint_id = a.appoint_id ");
			stringBuilder.append("JOIN ").append(DBConst.TABLE_User).append(" usr ");
			stringBuilder.append("ON ah.create_by = usr.user_id ");
			stringBuilder.append("JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			stringBuilder.append("ON s.supplier_id = a.supplier_id ");
			stringBuilder.append("WHERE ah.appoint_id = ?;");

			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(appointId));
			resultSet = preparedStatement.executeQuery();

			AppointDTO appoint = new AppointDTO();
			appoint.setAppointId(NullUtils.cvInt(appointId));
			appoint.setAppointHistory(new ArrayList<>());
			appoint.setUser(new ArrayList<>());
			while (resultSet.next()) {
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				appoint.setSupplierId(supplierDTO);

				AppointHistoryDTO appointHistoryDTO = new AppointHistoryDTO();
				appointHistoryDTO.setAppointHistoryId(resultSet.getInt("appoint_history_id"));
				appointHistoryDTO.setTitle(resultSet.getString("title"));
				appointHistoryDTO.setDetail(resultSet.getString("detail"));
				appointHistoryDTO.setCreateDate(resultSet.getDate("create_date"));
				appointHistoryDTO.setCreateTime(resultSet.getTime("create_date"));
				appointHistoryDTO.setCreateBy(resultSet.getInt("create_by"));
				appointHistoryDTO.setStatusId(resultSet.getInt("status_id"));
				appoint.getAppointHistory().add(appointHistoryDTO);

				UserDTO userDTO = new UserDTO();
				//userDTO.setFullName(resultSet.getString("fullname"));
				UserGroupDTO userGroupDTO = new UserGroupDTO();			
				userGroupDTO.setUserGroupId(resultSet.getInt("user_group_id"));
				userDTO.setUserGroupId(userGroupDTO);
				
				appoint.getUser().add(userDTO);
			}

			return appoint;

		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw new RuntimeException(e.toString(), e);
		}

	}
	
	public boolean changeAppointStatus(AppointDTO appointDTO) {
		try {
			
			connection.setAutoCommit(false);
			
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Appoint).append(" ");
			query.append("SET status_id = ?, update_by = ?, appoint_status_id = ?, update_date = now() WHERE (appoint_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, appointDTO.getStatusId());
			preparedStatement.setInt(index++, appointDTO.getUpdateBy());
			preparedStatement.setInt(index++, appointDTO.getAppointStatusId().getAppointStatusId());
			preparedStatement.setInt(index++, appointDTO.getAppointId());
			preparedStatement.executeUpdate();
			
			connection.commit();
			return true;
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean changeAppointStatus(AppointModel appointModel) {
		try {
			connection.setAutoCommit(false);
			boolean statusProcess = false;
			// int statusAppoint =
			// NullUtils.cvInt(appointModel.getStatusId().getStatusId());
			StringBuilder stringBuilder = new StringBuilder();
			try {

				// UPDATE `audit_supplier_cpram`.`appoint` SET `appoint_date` = 'now()',
				// `status_id` = '2', `update_by` = '11', `update_date` = 'now()' WHERE
				// (`appoint_id` = '6');
				stringBuilder.append("UPDATE ").append(DBConst.TABLE_Appoint);
				stringBuilder.append(" SET ");
				stringBuilder.append("status_id = ?, ");
				stringBuilder.append("update_by = ?, ");
				stringBuilder.append("appoint_status_id = ?, ");
				stringBuilder.append("update_date = now() ");
				stringBuilder.append("WHERE (appoint_id = ?);");
				int index = 1;
				preparedStatement = connection.prepareStatement(stringBuilder.toString());
				preparedStatement.setInt(index++,NullUtils.cvInt(appointModel.getStatusId().toString().trim()));
				preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getUpdateBy().trim()));
				preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getAppointStatusId().getAppointStatusId()));
				preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getAppointId().trim()));
				preparedStatement.executeUpdate();

				statusProcess = true;

			} catch (Exception e) {
				DatabaseUtils.rollback(connection);
				logger.error(e.toString(), e);
				statusProcess = false;
			}

			if (!statusProcess) {
				return statusProcess;
			}

			try {
				// INSERT INTO `audit_supplier_cpram`.`appoint_history` (`appoint_id`, `detail`,
				// `status_id`, `create_by`, `create_date`) VALUES ('1', 'sdfdfs', '1', '1',
				// 'now()');
				stringBuilder.setLength(0);
				stringBuilder = new StringBuilder();

				stringBuilder.append("INSERT INTO appoint_history ");
				stringBuilder.append("(appoint_id, title, detail, appoint_status_id, status_id, create_by, create_date)");
				stringBuilder.append(" VALUES ");
				stringBuilder.append("(?, ?, ?, ?, ?, ?, now());");
				preparedStatement = connection.prepareStatement(stringBuilder.toString());
				int index = 1;
				preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getAppointId().trim()));
				preparedStatement.setString(index++, NullUtils.cvStr(appointModel.getTitle().trim()));
				preparedStatement.setString(index++, NullUtils.cvStr(appointModel.getDetail().trim()));
				preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getAppointStatusId().getAppointStatusId()));
				preparedStatement.setInt(index++,NullUtils.cvInt(appointModel.getStatusId().toString().trim()));
				preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getUpdateBy().trim()));
				preparedStatement.executeUpdate();
				statusProcess = true;
			} catch (Exception e) {
				DatabaseUtils.rollback(connection);
				logger.error(e.toString(), e);
				statusProcess = false;
			}

			if (statusProcess) {
				connection.commit();
			} else {
				DatabaseUtils.rollback(connection);
			}
			return statusProcess;

		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
			logger.error(e.toString(), e);
			throw new RuntimeException(e.toString(), e);
		}
	}

	public int insertAppoint(AppointDTO appointDTO) {
		try {
			connection.setAutoCommit(false);
			
			INSERT INTO `appoint` (`appoint_type_id`, `appoint_create_by`, `supplier_id`, `appoint_date`, `title`, `detail`, `appoint_status_id`, `status_id`, `create_date`, `create_by`, `update_by`, `update_date`) 
			VALUES ('1', '1', '1', 'now()', 'test', 'zsdfasdfasdf', '1', '1', 'now()', '1', '1', 'now()');

			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Appoint).append(" ");
			query.append("(appoint_type_id, appoint_create_by, supplier_id, appoint_date, title, detail, appoint_status_id, status_id, create_date, create_by, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, now(), ?, ?, now());");
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, appointId = 0;
			preparedStatement.setInt(index++, appointDTO.getAppointTypeId());
			preparedStatement.setInt(index++, appointDTO.getAppointCreateBy().getUserId());
			preparedStatement.setInt(index++, appointDTO.getSupplierId().getSupplierId());
			preparedStatement.setString(index++, String.format("%s %s", appointDTO.getAppointDate(),appointDTO.getAppointTime()));
			preparedStatement.setString(index++, appointDTO.getTitle());
			preparedStatement.setString(index++, appointDTO.getDetail());
			preparedStatement.setInt(index++, appointDTO.getAppointStatusId().getAppointStatusId());
			preparedStatement.setInt(index++, appointDTO.getStatusId());
			preparedStatement.setInt(index++, appointDTO.getCreateBy());
			preparedStatement.setInt(index++, appointDTO.getUpdateBy());			
			int rowAffect = preparedStatement.executeUpdate();

			if (rowAffect == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next())
					appointId = resultSet.getInt(1);
			}

			// INSERT INTO `audit_supplier_cpram`.`appoint_user_mapping` (`appoint_id`,
			// `user_id`, `status_id`, `create_date`) VALUES ('2', '1', '1', 'now()');
			if (appointId != 0) {
				for (UserModel userAudit : appointModel.getUser()) {
					stringBuilder.setLength(0);
					stringBuilder = new StringBuilder();
					stringBuilder.append("INSERT INTO appoint_user_mapping ");
					stringBuilder.append("(appoint_id, user_id, status_id, create_date)");
					stringBuilder.append(" VALUES ");
					stringBuilder.append("(?,?,1,now());");
					preparedStatement = connection.prepareStatement(stringBuilder.toString());
					preparedStatement.setInt(1, NullUtils.cvInt(appointId));
					preparedStatement.setInt(2, NullUtils.cvInt(userAudit.getUserId()));
					preparedStatement.executeUpdate();
				}
			} else {
				DatabaseUtils.rollback(connection);
				return false;
			}

			// INSERT INTO `audit_supplier_cpram`.`appoint_history` (`appoint_id`, `detail`,
			// `status_id`, `create_by`, `create_date`) VALUES ('1', 'sdfdfs', '1', '1',
			// 'now()');
			if (appointId != 0) {

				stringBuilder.setLength(0);
				stringBuilder = new StringBuilder();
				stringBuilder.append("INSERT INTO appoint_history ");
				stringBuilder.append("(appoint_id, title, detail, status_id, create_by, create_date)");
				stringBuilder.append(" VALUES ");
				stringBuilder.append("(?, ?, ?, '1', ?, now());");
				preparedStatement = connection.prepareStatement(stringBuilder.toString());
				index = 1;
				preparedStatement.setInt(index++, appointId);
				preparedStatement.setString(index++, NullUtils.cvStr(appointModel.getTitle().trim()));
				preparedStatement.setString(index++, NullUtils.cvStr(appointModel.getDetail().trim()));
				preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getAppointCreateBy().getUserId()));
				preparedStatement.executeUpdate();

			} else {
				DatabaseUtils.rollback(connection);
				return false;
			}

			connection.commit();
			return appointId;

		} catch(SQLException e){
			DatabaseUtils.rollback(connection);
			logger.error(e.getMessage(), e);
			return 0;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public boolean updateAppoint(AppointDTO appointDTO) {
		// UPDATE `audit_supplier_cpram`.`appoint` SET `appoint_date` = '2018-11-13
		// 12:10:00', `title` = 'แจ้งการเข้าตรวจในวันที่ 13/11/2018 ในเsdsdfsd',
		// `detail` = 'แจ้งการเข้าตรวจในวันที่ 13/11/2018 ในเวลา 12:sdfdsf00 น',
		// `update_by` = '2', `update_date` = '2018-11-13 09:39:06sdfsdf' WHERE
		// (`appoint_id` = '6');
		try {
			connection.setAutoCommit(false);
			// UPDATE `audit_supplier_cpram`.`appoint` SET
			// `appoint_date` = 'now()',
			// `title` = 'ขอแจ้งการนัดเข้า Auditsd',
			// `detail` = 'ขอแจ้งการนัดเข้า Audit กับบริษัท Company A รายชื่อผู้เข้าตรวจ :
			// Auditor B, Auditor A วันที่นัดหมายเข้าตรวจ : 30/11/2018 เวลานัดหมายเข้าตรวจ :
			// 14:30:00 จึงแจ้งมาเพื่อนัดหมายในการเข้าตรวsdsdจ',
			// `status_id` = '2',
			// `update_by` = '9',
			// `update_date` = 'now()'
			// WHERE (`appoint_id` = '10');

			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("UPDATE ").append(DBConst.TABLE_Appoint);
			stringBuilder.append(" SET ");
			stringBuilder.append("appoint_date = ?, ");
			stringBuilder.append("title = ?, ");
			stringBuilder.append("detail = ?, ");
			stringBuilder.append("status_id = ?, ");
			stringBuilder.append("update_by = ?, ");
			stringBuilder.append("appoint_status_id = ?, ");
			stringBuilder.append("update_date = now() ");
			stringBuilder.append("WHERE (appoint_id = ?);");

			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			int index = 1;
			preparedStatement.setString(index++, String.format("%s %s", appointDTO.getAppointDate(),appointDTO.getAppointTime()));
			preparedStatement.setString(index++, appointDTO.getTitle());
			preparedStatement.setString(index++, appointDTO.getDetail());
			preparedStatement.setInt(index++, appointDTO.getStatusId());
			preparedStatement.setInt(index++, appointDTO.getUpdateBy());
			preparedStatement.setInt(index++, appointDTO.getAppointStatusId().getAppointStatusId());
			preparedStatement.setInt(index++, appointDTO.getAppointId());

			preparedStatement.executeUpdate();

			connection.commit();
			return true;
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
			logger.error(e.toString(), e);
			throw new RuntimeException(e.toString(), e);
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public boolean insertAppointHistory(AppointModel appointModel) {
		try {
			connection.setAutoCommit(false);
			// INSERT INTO `audit_supplier_cpram`.`appoint_history` (`appoint_id`, `detail`,
			// `status_id`, `create_by`, `create_date`) VALUES ('1', 'sdfdfs', '1', '1',
			// 'now()');
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("INSERT INTO appoint_history ");
			stringBuilder.append("(appoint_id, title, detail, status_id, appoint_status_id, create_by, create_date)");
			stringBuilder.append(" VALUES ");
			stringBuilder.append("(?, ?, ?, ?, ?, ?, now());");
			int index = 1;
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getAppointId()));
			preparedStatement.setString(index++, NullUtils.cvStr(appointModel.getTitle()));
			preparedStatement.setString(index++, NullUtils.cvStr(appointModel.getDetail()));
			preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getStatusId()));
			preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getAppointStatusId().getAppointStatusId()));
			preparedStatement.setInt(index++, NullUtils.cvInt(appointModel.getUpdateBy()));
			preparedStatement.executeUpdate();

			connection.commit();
			return true;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw new RuntimeException(e.toString(), e);
		}
	}

	public boolean InsertAppointMappingUser(List<UserModel> auditDetail, String appointId) {
		try {
			connection.setAutoCommit(false);

			StringBuilder stringBuilder = new StringBuilder();
			// DELETE FROM `audit_supplier_cpram`.`appoint_user_mapping` WHERE (`appoint_id`
			// = '1')
			stringBuilder.setLength(0);
			stringBuilder.append("DELETE FROM ").append(DBConst.TABLE_Appoint_User_Mapping);
			stringBuilder.append(" WHERE ");
			stringBuilder.append("(appoint_id = ?);");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(appointId));
			preparedStatement.executeUpdate();
			
			// INSERT INTO `audit_supplier_cpram`.`appoint_user_mapping` (`appoint_id`,
			// `user_id`, `status_id`, `create_date`) VALUES ('2', '1', '1', 'now()');
			for (UserModel audit : auditDetail) {
				stringBuilder.setLength(0);
				stringBuilder = new StringBuilder();
				stringBuilder.append("INSERT INTO ").append(DBConst.TABLE_Appoint_User_Mapping).append(" ");
				stringBuilder.append("(appoint_id, user_id, status_id, create_date)");
				stringBuilder.append(" VALUES ");
				stringBuilder.append("(?, ?, 1, now());");
				preparedStatement = connection.prepareStatement(stringBuilder.toString());
				preparedStatement.setInt(1, NullUtils.cvInt(appointId));
				preparedStatement.setInt(2, NullUtils.cvInt(audit.getUserId()));
				preparedStatement.executeUpdate();
			}

			connection.commit();
			return true;
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
			logger.error(e.toString(), e);
			throw new RuntimeException(e.toString(), e);
		}
	}
	
	public boolean deleteAppointByAppointId(AppointDTO appointDTO) {
		
		try {
			connection.setAutoCommit(false);
			StringBuilder stringBuilder = new StringBuilder();
			
			//DELETE FROM `audit_supplier_cpram`.`appoint` WHERE (`appoint_id` = '4');
			stringBuilder.append("DELETE FROM ").append(DBConst.TABLE_Appoint).append(" ");
			stringBuilder.append("WHERE (appoint_id = ?);");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(appointDTO.getAppointId()));
			preparedStatement.executeUpdate();			
			connection.commit();
			return true;			
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}


	public int countAppointList(String whereClause) {
		int totalAppoint = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Appoint).append(" a ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON a.supplier_id = s.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Appoint_Status).append(" aps ");
			query.append("ON a.appoint_status_id = aps.appoint_status_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON a.appoint_create_by = u.user_id WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalAppoint = resultSet.getInt("total");
			}
			return totalAppoint;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalAppoint;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public List<UserModel> getAppointMappingUser(String appointId) {
		try {
			connection.setAutoCommit(false);

			StringBuilder stringBuilder = new StringBuilder();

			// SELECT aum.user_id FROM appoint_user_mapping aum WHERE aum.appoint_id = 2;
			stringBuilder.append("SELECT aum.user_id, u.fullname ");
			stringBuilder.append("FROM ").append(DBConst.TABLE_Appoint_User_Mapping).append(" aum ");
			stringBuilder.append("JOIN ").append(DBConst.TABLE_User).append(" u ON aum.user_id = u.user_id ");
			stringBuilder.append("WHERE aum.appoint_id = ?;");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(appointId));
			resultSet = preparedStatement.executeQuery();
			List<UserModel> auditDetailFromDB = new ArrayList<>();

			while (resultSet.next()) {
				UserModel auditDB = new UserModel();
				auditDB.setUserId(resultSet.getString("user_id"));
				//auditDB.setFullName(resultSet.getString("fullname"));
				auditDetailFromDB.add(auditDB);
			}
			return auditDetailFromDB;

		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw new RuntimeException(e.toString(), e);
		}
	}

	public AppointDTO getAppointDateByAppointId(AppointDTO paramAppointDTO) {
		try {
			connection.setAutoCommit(false);

			// SELECT a.appoint_date FROM appoint a WHERE a.appoint_id = 8;
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT a.appoint_id, a.appoint_date, s.supplier_id, s.supplier_company");
			stringBuilder.append(" FROM ").append(DBConst.TABLE_Appoint).append(" a ");
			stringBuilder.append("JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			stringBuilder.append("ON a.supplier_id = s.supplier_id ");
			stringBuilder.append("WHERE a.appoint_id = ?");

			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(paramAppointDTO.getAppointId()));
			resultSet = preparedStatement.executeQuery();
			
			AppointDTO appointDTO = new AppointDTO();
			while(resultSet.next()) {
				appointDTO.setAppointId(resultSet.getInt("appoint_id"));
				appointDTO.setAppointDate(resultSet.getDate("appoint_date"));
				appointDTO.setAppointTime(resultSet.getTime("appoint_date"));

				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				appointDTO.setSupplierId(supplierDTO);
			}

			return appointDTO;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
			throw new RuntimeException(e.toString(), e);
		}
	}*/
	
	
	

}
