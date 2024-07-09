package th.co.gosoft.audit.cpram.relational;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dao.AppointUserMappingDAO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class UserRelational {
	
	private final static Logger logger = Logger.getLogger(UserRelational.class);

	public static boolean checkUserRelational(Connection connection, UserDTO userDTO) {
		boolean checkHasMember = false;
		AppointUserMappingDAO appointUserMappingDAO = null;
		try {
			
			appointUserMappingDAO = new AppointUserMappingDAO(connection);
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND usr.user_id = '").append(userDTO.getUserId()).append("' ");
			List<AppointDTO> appointDTOs = appointUserMappingDAO.getAppointUserMappingList(0, appointUserMappingDAO.countAppointUserMappingList(queryWhereClause.toString()), queryWhereClause.toString());
			
			if(appointDTOs != null) {
				if(!appointDTOs.isEmpty()) {
					checkHasMember = true;
				}
			}
			
			return checkHasMember;
		}catch (Exception e) {
			logger.error("UserRelational.checkUserRelational() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
}
