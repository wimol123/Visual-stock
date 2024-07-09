package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.ChecklistPlanEntourageDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class ChecklistPlanEntourageDAO extends StandardAttributeDAO{

	private final static Logger logger = Logger.getLogger(EvalPlanDAO.class);
	
	public ChecklistPlanEntourageDAO(Connection connection) {
		super(connection);
	}
	
	public int insertChecklistPlanEntourage(ChecklistPlanEntourageDTO checklistPlanEntourageDTO) {
		int rowAffect = 0;
		try {
			//INSERT INTO `checklist_plan_entourage` (`checklist_plan_id`, `entourage_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('1', '1', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Checklist_Plan_Entourage).append(" ");
			query.append("(checklist_plan_id, entourage_id, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ").append("(?, ?, ?, ?, now(), ?, now());");
			
			int index = 1;
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(index++, checklistPlanEntourageDTO.getChecklistPlanId());
			preparedStatement.setInt(index++, checklistPlanEntourageDTO.getEntourageId());
			preparedStatement.setString(index++, NullUtils.cvStr(checklistPlanEntourageDTO.getEnable()));
			preparedStatement.setInt(index++, checklistPlanEntourageDTO.getCreateBy());
			preparedStatement.setInt(index++, checklistPlanEntourageDTO.getUpdateBy());
			
			rowAffect = preparedStatement.executeUpdate();
			return rowAffect;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return rowAffect;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
