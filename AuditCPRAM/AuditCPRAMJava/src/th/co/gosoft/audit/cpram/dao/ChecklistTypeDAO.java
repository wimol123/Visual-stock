package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.ChecklistTypeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class ChecklistTypeDAO extends StandardAttributeDAO{
	
	public ChecklistTypeDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(AnswerDAO.class);
	
		
	
	public List<ChecklistTypeDTO> getChecklistList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT ct.checklist_type_id, ct.checklist_type_name, ct.enable 
			FROM checklist_type ct WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ct.checklist_type_id, ct.checklist_type_name, ct.enable ");
			query.append("FROM ").append(DBConst.TABLE_Checklist_Type).append(" ct ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);				
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by ct.checklist_type_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<ChecklistTypeDTO> checklistTypeDTOs = new ArrayList<>();
			while(resultSet.next()) {
				ChecklistTypeDTO checklistType = new ChecklistTypeDTO();
				checklistType.setChecklistTypeId(resultSet.getInt("checklist_type_id"));
				checklistType.setChecklistTypeName(resultSet.getString("checklist_type_name"));
				checklistType.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				checklistTypeDTOs.add(checklistType);
			}
			
			return checklistTypeDTOs;
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
	
	public int countChecklistTypeList(String queryWhereClause) {
		int countChecklistType = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Checklist_Type).append(" ct ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countChecklistType = resultSet.getInt("total");
			}
			return countChecklistType;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countChecklistType;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
