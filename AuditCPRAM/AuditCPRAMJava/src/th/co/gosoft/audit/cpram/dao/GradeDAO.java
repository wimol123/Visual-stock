package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.GradeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class GradeDAO extends StandardAttributeDAO{

	private final static Logger logger = Logger.getLogger(GradeDAO.class);
	public GradeDAO(Connection connection) {
		super(connection);
	}
	
	public List<GradeDTO> getGradeList(){
		try {
			//SELECT g.grade_id FROM grade g;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT g.grade_id FROM ").append(DBConst.TABLE_Grade).append(" g ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<GradeDTO> gradeDTOs = new ArrayList<>();
			while(resultSet.next()) {
				GradeDTO grade = new GradeDTO();
				grade.setGradeId(NullUtils.cvChar(resultSet.getString("grade_id")));
				gradeDTOs.add(grade);
			}
			return gradeDTOs;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
 			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
