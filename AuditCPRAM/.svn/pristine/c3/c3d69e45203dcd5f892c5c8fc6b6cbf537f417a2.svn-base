package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.SystemConfigurationDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class SystemConfigurationDAO extends StandardAttributeDAO{

	public SystemConfigurationDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	private final static Logger logger = Logger.getLogger(SystemConfigurationDAO.class);
	
	public List<SystemConfigurationDTO> getSystemConfigurationList(String queryWhereClause) {
		try {
			
			/*SELECT sys_conf.system_key, sys_conf.system_value, sys_conf.enable, sys_conf.create_by, sys_conf.create_date, sys_conf.update_by, sys_conf.update_date 
			FROM system_configuration sys_conf;*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT sys_conf.system_key, sys_conf.system_value, sys_conf.enable, sys_conf.create_by, sys_conf.create_date, sys_conf.update_by, sys_conf.update_date ");
			query.append(" FROM ").append(DBConst.TABLE_System_Configuration).append(" sys_conf ");
			query.append("WHERE 1=1 AND sys_conf.enable = 'Y' ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<SystemConfigurationDTO> systemConfigurationDTOs = new ArrayList<>();
			while(resultSet.next()) {				
				SystemConfigurationDTO systemConfigurationDTO = new SystemConfigurationDTO();
				systemConfigurationDTO.setSystemKey(resultSet.getString("system_key"));
				systemConfigurationDTO.setSystemValue(resultSet.getString("system_value"));
				systemConfigurationDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				systemConfigurationDTO.setCreateBy(resultSet.getInt("create_by"));
				systemConfigurationDTO.setCreateDate(resultSet.getDate("create_date"));
				systemConfigurationDTO.setCreateTime(resultSet.getTime("create_date"));
				systemConfigurationDTO.setUpdateBy(resultSet.getInt("update_by"));
				systemConfigurationDTO.setUpdateDate(resultSet.getDate("update_date"));
				systemConfigurationDTO.setUpdateTime(resultSet.getTime("update_date"));
				systemConfigurationDTOs.add(systemConfigurationDTO);
			}
			return systemConfigurationDTOs;
		}catch (SQLException e) {
			logger.error(e.getMessage(), e); 	
			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateSystemConfiguration(SystemConfigurationDTO systemConfigurationDTO) {
		try {
			
			//UPDATE `system_configuration` SET `system_value` = 'cpram.cp.co.thsdfsdfsdf', `update_by` = '2', `update_date` = 'now()' WHERE (`system_key` = 'system.ldap.domain');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_System_Configuration).append(" ");
			query.append("SET system_value = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (system_key = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setString(index++, systemConfigurationDTO.getSystemValue().trim());
			preparedStatement.setInt(index++, systemConfigurationDTO.getUpdateBy());
			preparedStatement.setString(index++, systemConfigurationDTO.getSystemKey().trim());
			int rowAffect = preparedStatement.executeUpdate();
			if(rowAffect == 1) {
				return true;
			}else {
				return false;
			}			
		}catch (SQLException e) {
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
