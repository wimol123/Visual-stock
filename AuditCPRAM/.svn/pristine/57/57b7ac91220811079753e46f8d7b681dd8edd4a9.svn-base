package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.AppointUserMappingDAO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.model.AppointModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class AppointUserMappingController {
	
	
	private final static Logger logger = Logger.getLogger(AppointUserMappingController.class);
	
	public String getAuditorMappingAppoint(String objAppoint) {
		Connection connection = null;
		AppointUserMappingDAO appointUserMappingDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			gson = new Gson();
			
			StringBuilder queryWhereClause = new StringBuilder();
			
			if(!StringUtils.isNullOrEmpty(objAppoint)) {
				AppointModel appointModelRequest = gson.fromJson(objAppoint, AppointModel.class);
				
				if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointDate())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("app.appoint_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(appointModelRequest.getAppointDate())).append("%'  ");
				}else if(!StringUtils.isNullOrEmpty(appointModelRequest.getAppointId())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("app.appoint_id").append(" = ").append(" '").append((appointModelRequest.getAppointId())).append("'  ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointCreateBy().getUserId())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("app.appoint_create_by").append(" = '").append(appointModelRequest.getAppointCreateBy().getUserId()).append("' ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointCreateBy().getFullname())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("usr.fullname").append(" like ").append(" '%").append(appointModelRequest.getAppointCreateBy().getFullname()).append("%'  ");
				}					
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getTitle())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("app.title").append(" like ").append(" '%").append(appointModelRequest.getTitle()).append("%'  ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getDetail())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("app.detail").append(" like ").append(" '%").append(appointModelRequest.getDetail()).append("%'  ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointStatusId().getAppointStatusId())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("app.appoint_status_id").append(" = '").append(appointModelRequest.getAppointStatusId().getAppointStatusId()).append("' ");
				}		
			}
			
			List<AppointDTO> appointDTOs = appointUserMappingDAO.getAppointUserMappingList(0, appointUserMappingDAO.countAppointUserMappingList(queryWhereClause.toString()), queryWhereClause.toString());
			List<AppointModel> appointModels = new ArrayList<>();
			for(AppointDTO appoint : appointDTOs) {
				appointModels.add(TransformDTO.transAppointDTO(appoint));
			}
			
			return gson.toJson(appointModels);
		}catch(Exception e) {
			logger.error("AppointUserMappingController.getAuditorMappingAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}

}
