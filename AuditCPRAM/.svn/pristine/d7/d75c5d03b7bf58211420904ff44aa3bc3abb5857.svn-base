package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.StatusDTO;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class StatusDAO extends StandardAttributeDAO{

	public StatusDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	private static Logger logger = Logger.getLogger(StatusDAO.class);
	
	public List<StatusDTO> getStatusListByTable(String tableStatusName) {
		try {
			
			connection.setAutoCommit(false);
			StringBuilder queryString = new StringBuilder();
			queryString.append(String.format("SELECT status.%s_id AS id, status.%s_name AS name, status.status_color AS color, status.enable AS enable ", tableStatusName.trim(), tableStatusName.trim()));
			queryString.append(String.format("FROM %s status;", tableStatusName.trim()));
			
			preparedStatement = connection.prepareStatement(queryString.toString().trim());
			resultSet = preparedStatement.executeQuery();
			
			List<StatusDTO> statusDTOList = new ArrayList<>();
			while (resultSet.next()) {
				StatusDTO statusDTO = new StatusDTO();
				statusDTO.setStatusId(resultSet.getInt("id"));
				statusDTO.setStatusName(resultSet.getString("name"));
				statusDTO.setStatusColor(resultSet.getString("color"));
				statusDTO.setEnable(resultSet.getString("enable"));
				statusDTOList.add(statusDTO);
			}
			return statusDTOList;
		}catch (SQLException e) {
			logger.error("StatusDAO.getStatusListByTable() SQLException : "+ExceptionUtils.stackTraceException(e));
 			return null;
		}catch (Exception e) {
			logger.error("StatusDAO.getStatusListByTable() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
}
