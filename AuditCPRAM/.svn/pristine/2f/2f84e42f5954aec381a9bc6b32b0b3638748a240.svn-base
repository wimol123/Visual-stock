package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.SystemLogDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class SystemLogDAO extends StandardAttributeDAO{

	public SystemLogDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	private final static Logger logger = Logger.getLogger(SystemLogDAO.class);
	
	public int insertSystemLog(SystemLogDTO systemLogDTO) {
		int logIdResult = 0;
    	try {	
    		
    		connection.setAutoCommit(false);
    		StringBuilder query = new StringBuilder();
    		query.append("INSERT INTO ").append(DBConst.TABLE_System_Log).append(" ");
    		query.append("(access, activity, detail, create_by, create_date, ref_id, note)");
    		query.append(" VALUES ");
    		query.append("(?, ?, ?, ?,  now(), ?, ?);");
    		preparedStatement = connection.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
    		int index = 1,rowAffect = 0;
    		preparedStatement.setString(index++, systemLogDTO.getAccess());
    		preparedStatement.setString(index++, systemLogDTO.getActivity());
    		preparedStatement.setString(index++, systemLogDTO.getDetail());
    		preparedStatement.setInt(index++, systemLogDTO.getCreateBy());
    		preparedStatement.setString(index++, systemLogDTO.getRefId());
    		preparedStatement.setString(index++, systemLogDTO.getNote());
    		rowAffect = preparedStatement.executeUpdate();
    		
    		if(rowAffect == 1) {
    			resultSet = preparedStatement.getGeneratedKeys();
    			while (resultSet.next()) {
    				logIdResult = resultSet.getInt(1);
				}
    		}
    		return logIdResult;
    	}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return logIdResult;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public List<SystemLogDTO> getSystemLogFinalAuditResultList(String queryWhereClause){
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.detail , s.create_by , u.fullname , s.create_date FROM ").append(DBConst.TABLE_System_Log).append(" s ");
			query.append("JOIN user u ON u.user_id = s.create_by ");
			query.append("WHERE s.access IN ('checklist_plan' , 'final_audit_result') ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			query.append(" ORDER BY create_date ;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<SystemLogDTO> systemLogDTOs = new ArrayList<SystemLogDTO>();
			
			while(resultSet.next()) {
				SystemLogDTO systemLog = new SystemLogDTO();
				systemLog.setDetail(resultSet.getString("detail"));
				systemLog.setCreateByName(resultSet.getString("fullname"));
				systemLog.setCreateDate(resultSet.getDate("create_date"));	
				systemLog.setCreateTime(resultSet.getTime("create_date"));
				systemLog.setCreateBy(resultSet.getInt("create_by"));				
				systemLogDTOs.add(systemLog);
			}
			return systemLogDTOs;
			
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
		
}
