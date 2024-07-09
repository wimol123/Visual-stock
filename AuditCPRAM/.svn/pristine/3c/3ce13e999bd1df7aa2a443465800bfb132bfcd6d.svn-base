package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.UserGroupDAO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.model.UserGroupModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class UserGroupController {

	private final static Logger logger = Logger.getLogger(UserGroupController.class);
	
	
	public String getUserGroupList(HttpServletRequest httpServletRequest){
		Connection connection = null;
		UserGroupDAO userGroupDAO = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			userGroupDAO = new UserGroupDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			StringBuilder queryWhereClause = new StringBuilder();
			
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN 
					|| NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA
					|| NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_SALE) {
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_AUDITOR).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_ADMINISTRATOR).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_PURCHASING_OFFICER).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_APPROVE_AUDIT_RESULT).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_REGISTER).append("' ");
			} else if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_REGISTER) {
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_ADMINISTRATOR).append("' ");
			} else if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_PURCHASING_OFFICER) {
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_AUDITOR).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_ADMINISTRATOR).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_PURCHASING_OFFICER).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_APPROVE_AUDIT_RESULT).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_REGISTER).append("' ");
				queryWhereClause.append(" AND ").append("user_group_id != '").append(Config.AUTHEN_USERTYPE_SUPPLIER_QA).append("' ");
			}
			
			List<UserGroupDTO> userGroupDTOs = userGroupDAO.getUserGroupList(queryWhereClause.toString());
			gson = new Gson();
			
			List<UserGroupModel>userGroupModels = new ArrayList<>();
			for(UserGroupDTO userGroupDTO : userGroupDTOs) {
				userGroupModels.add(TransformDTO.transUserGroupDTO(userGroupDTO));
			}
			String resultUserGroupModel = gson.toJson(userGroupModels);
			return resultUserGroupModel;
		}catch (Exception e) {
			logger.error("UserController.getUserGroupList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
}

	