package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.AppointHistoryDAO;
import th.co.gosoft.audit.cpram.dto.AppointHistoryDTO;
import th.co.gosoft.audit.cpram.model.AppointHistoryModel;
import th.co.gosoft.audit.cpram.model.AppointModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class AppointHistoryController {
	private final static Logger logger = Logger.getLogger(AppointHistoryController.class);
	
	public String appointHistoryListByAppoint(String objAppoint){
		Connection connection = null;
		AppointHistoryDAO appointHistoryDAO = null;
		Gson gson = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			appointHistoryDAO = new AppointHistoryDAO(connection);
			gson = new Gson();
			StringBuilder queryWhereClause = new StringBuilder();
			
			if(!StringUtils.isNullOrEmpty(objAppoint)) {
				AppointModel appointModelRequest = gson.fromJson(objAppoint, AppointModel.class);
				if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointDate())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.appoint_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(appointModelRequest.getAppointDate())).append("%'  ");
				}else if(!StringUtils.isNullOrEmpty(appointModelRequest.getAppointId())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.appoint_id").append(" = ").append(" '").append((appointModelRequest.getAppointId())).append("'  ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointCreateBy().getUserId())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.appoint_create_by").append(" = '").append(appointModelRequest.getAppointCreateBy().getUserId()).append("' ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointCreateBy().getFullname())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("u.fullname").append(" like ").append(" '%").append(appointModelRequest.getAppointCreateBy().getFullname()).append("%'  ");
				}					
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getTitle())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.title").append(" like ").append(" '%").append(appointModelRequest.getTitle()).append("%'  ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getDetail())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.detail").append(" like ").append(" '%").append(appointModelRequest.getDetail()).append("%'  ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getAppointStatusId().getAppointStatusId())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("a.appoint_status_id").append(" = '").append(appointModelRequest.getAppointStatusId().getAppointStatusId()).append("' ");
				}
				else if (!StringUtils.isNullOrEmpty(appointModelRequest.getSupplierId().getSupplierCompany())) {
					queryWhereClause.append(" AND ");
					queryWhereClause.append("sup.supplier_company").append(" like ").append(" '%").append(appointModelRequest.getSupplierId().getSupplierCompany()).append("%'  ");
				}
				
			}
			
			
			List<AppointHistoryDTO> appointHistoryDTOs = appointHistoryDAO.getAppointHistory(0, appointHistoryDAO.countAppointHistory(queryWhereClause.toString()), queryWhereClause.toString());
			List<AppointHistoryModel> appointHistoryModels = new ArrayList<>();
			for(AppointHistoryDTO appointHistoryDTO : appointHistoryDTOs) {
				appointHistoryModels.add(TransformDTO.transAppointHistoryDTO(appointHistoryDTO));
			}
			return gson.toJson(appointHistoryModels);
		}catch(Exception e) {
			logger.error("AppointHistoryController.appointHistoryListByAppoint() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
		finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
}
