package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.EvalPlanAnswerDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class EvalPlanAnswerDAO extends StandardAttributeDAO {

	private final static Logger logger = Logger.getLogger(EvalPlanAnswerDAO.class);
	
	public EvalPlanAnswerDAO(Connection connection) {
		super(connection);
	}
	
	public int insertEvalPlanAnswer(EvalPlanAnswerDTO evalPlanAnswerDTO) {
		int rowAffective = 0;
		try {
			
			connection.setAutoCommit(false);
			//INSERT INTO `eval_plan_answer` (`eval_plan_id`, `answer_id`, `answer_detail`, `is_create_car`, `is_require_evidence`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2', '1', 'M', 'Y', 'Y', 'Y', '1', 'now()', '1', 'now()');
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Eval_Plan_Answer).append(" ");
			query.append("(eval_plan_id, answer_id, answer_detail, is_create_car, is_require_evidence, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ").append("(?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			int index=1;
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(index++, evalPlanAnswerDTO.getEvalPlanId().getEvalPlanId());
			preparedStatement.setInt(index++, evalPlanAnswerDTO.getAnswerId());
			preparedStatement.setString(index++, evalPlanAnswerDTO.getAnswerDetail());
			preparedStatement.setString(index++, NullUtils.cvStr(evalPlanAnswerDTO.getIsCreateCar()));
			preparedStatement.setString(index++, NullUtils.cvStr(evalPlanAnswerDTO.getIsRequireEvidence()));
			preparedStatement.setString(index++, NullUtils.cvStr(evalPlanAnswerDTO.getEnable()));
			preparedStatement.setInt(index++, evalPlanAnswerDTO.getCreateBy());
			preparedStatement.setInt(index++, evalPlanAnswerDTO.getUpdateBy());
			
			rowAffective = preparedStatement.executeUpdate();
			
			return rowAffective;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return rowAffective;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
