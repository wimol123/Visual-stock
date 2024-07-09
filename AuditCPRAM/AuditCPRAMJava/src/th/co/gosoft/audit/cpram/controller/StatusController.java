package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.StatusDAO;
import th.co.gosoft.audit.cpram.dto.StatusDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class StatusController {

	private static Logger logger = Logger.getLogger(StatusController.class);
	
	public String getStatusList(String objStringRequest) {
		Connection connection = null;
		StatusDAO statusDAO = null;
		try {
			
						
			if(!StringUtils.isNullOrEmpty(objStringRequest.trim())) {
				String tableStatusName = "";
				switch (objStringRequest.trim()) {
				case "StatusOfCar":
					tableStatusName = DBConst.TABLE_Car_Status.trim();
					logger.info("Request Is Macth TABLE Status : "+DBConst.TABLE_Car_Status.trim());
					break;

				default:
					logger.error("Request Is Not Match In Case Please Config Table With String Request : "+objStringRequest.trim());
					break;
				}
				
				if(!StringUtils.isNullOrEmpty(tableStatusName.trim())) {
					connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
					statusDAO = new StatusDAO(connection);
					
					List<StatusDTO> ststusDtoList = statusDAO.getStatusListByTable(tableStatusName.trim());
					
					return new Gson().toJson(ststusDtoList);
				}else {
					return "";
				}
				
			}else {
				logger.warn("String Request Is Null Or Empty");
				return "";
			}
		}catch (Exception e) {
			logger.error("StatusController.getStatusList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
}
