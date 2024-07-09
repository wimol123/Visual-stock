package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.ChecklistTypeDTO;
import th.co.gosoft.audit.cpram.dto.GradeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class GradeChecklistTypeMappingDAO extends StandardAttributeDAO{

	public GradeChecklistTypeMappingDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(GradeChecklistTypeMappingDAO.class);

		
	public List<GradeDTO> getGradeListByChecklistType(ChecklistTypeDTO checklistTypeDTO){
		try {
			
			/*SELECT g.grade_id, g.enable, g.create_by, g.create_date, g.update_by, g.update_date
			FROM grade g RIGHT JOIN grade_checklist_type_mapping gctm 
			ON g.grade_id = gctm.grade_id RIGHT JOIN checklist_type ct
			ON gctm.checklist_type_id = ct.checklist_type_id
			WHERE 1=1 AND ct.checklist_type_id = 1 AND g.enable = 'Y'*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT g.grade_id, g.enable, g.create_by, g.create_date, g.update_by, g.update_date ");
			query.append("FROM ").append(DBConst.TABLE_Grade).append(" g ");
			query.append("RIGHT JOIN ").append(DBConst.TABLE_Grade_Checklist_Type_Mapping).append(" gctm ");
			query.append("ON g.grade_id = gctm.grade_id ");
			query.append("RIGHT JOIN ").append(DBConst.TABLE_Checklist_Type).append(" ct ");
			query.append("ON gctm.checklist_type_id = ct.checklist_type_id ");
			query.append("WHERE 1=1 AND ct.checklist_type_id = ? AND g.enable = 'Y';");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, checklistTypeDTO.getChecklistTypeId());
			resultSet = preparedStatement.executeQuery();
			
			List<GradeDTO> gradeDTOs = new ArrayList<>();			
			while(resultSet.next()) {
				GradeDTO grade = new GradeDTO();
				grade.setGradeId(NullUtils.cvChar(resultSet.getString("grade_id")));
				grade.setCreateBy(resultSet.getInt("create_by"));
				grade.setCreateDate(resultSet.getDate("create_date"));
				grade.setCreateTime(resultSet.getTime("create_date"));
				grade.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				grade.setUpdateBy(resultSet.getInt("update_by"));
				grade.setUpdateDate(resultSet.getDate("update_date"));
				grade.setUpdateTime(resultSet.getTime("update_date"));
				gradeDTOs.add(grade);
			}
			return gradeDTOs;
		} catch(SQLException e){
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
