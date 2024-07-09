package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalTypeDTO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class EvalPlanDAO extends StandardAttributeDAO {

	

	private final static Logger logger = Logger.getLogger(EvalPlanDAO.class);
	
	public EvalPlanDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}
	
	public List<EvalPlanDTO> getEvalPlanList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT eplan.eval_plan_id, eplan.checklist_plan_id, eplan.parent_id, eplan.eval_type_id, eplan.eval_type_name, eplan.question_type_id, eplan.question_type_name, eplan.require_answer, eplan.title, eplan.detail 
			FROM eval_plan eplan 
			WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT eplan.eval_plan_id, eplan.checklist_plan_id, eplan.parent_id, eplan.unique_id, eplan.eval_type_id, eplan.eval_type_name, eplan.question_type_id, eplan.question_type_name, eplan.require_answer, eplan.title, eplan.detail ");
			query.append("FROM ").append(DBConst.TABLE_Eval_Plan).append(" eplan ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by eplan.eval_plan_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<EvalPlanDTO> evalPlanDTOs = new ArrayList<>();
			
			while(resultSet.next()) {
				EvalPlanDTO evalPlan = new EvalPlanDTO();
				
				evalPlan.setEvalPlanId(resultSet.getInt("eval_plan_id"));
				evalPlan.setParentId(resultSet.getInt("parent_id"));
				evalPlan.setUniqueId(resultSet.getString("unique_id"));
				evalPlan.setEvalTypeName(resultSet.getString("eval_type_name"));
				evalPlan.setQuestionTypeName(resultSet.getString("question_type_name"));
				evalPlan.setRequireAnswer(NullUtils.cvChar(resultSet.getString("require_answer")));
				evalPlan.setTitle(resultSet.getString("title"));
				evalPlan.setDetail(resultSet.getString("detail"));
				
				evalPlan.setChecklistPlanId(new ChecklistPlanDTO());
				evalPlan.getChecklistPlanId().setChecklistPlanId(resultSet.getInt("checklist_plan_id"));
				
				evalPlan.setEvalTypeId(new EvalTypeDTO());
				evalPlan.getEvalTypeId().setEvalTypeId(resultSet.getInt("eval_type_id"));
				
				evalPlan.setQuestionTypeId(new QuestionTypeDTO());
				evalPlan.getQuestionTypeId().setQuestionTypeId(resultSet.getInt("question_type_id"));
				
				evalPlanDTOs.add(evalPlan);
			}
			return evalPlanDTOs;
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
	
	public int insertEvalPlan(EvalPlanDTO evalPlanDTO) {
		int primaryKey = 0;
		try {
			
			//INSERT INTO`eval_plan` (`checklist_plan_id`, `parent_id`, `eval_type_id`, `eval_type_name`, `question_type_id`, `question_type_name`, `require_answer`, `title`, `detail`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2', '2', '2', 'topic', '0', 'เลือกตอบ ', 'N', 'sdfsdfsdf', 'sdfsdfsdf', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Eval_Plan).append(" ");
			query.append("(checklist_plan_id, parent_id, unique_id, eval_type_id, eval_type_name, question_type_id, question_type_name, require_answer, title, detail, enable, create_by, create_date, update_by, update_date) ");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			int index = 1, rowAffective = 0;
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setInt(index++, evalPlanDTO.getChecklistPlanId().getChecklistPlanId());
			preparedStatement.setInt(index++, evalPlanDTO.getParentId());
			preparedStatement.setString(index++, evalPlanDTO.getUniqueId());
			preparedStatement.setInt(index++, evalPlanDTO.getEvalTypeId().getEvalTypeId());
			preparedStatement.setString(index++, evalPlanDTO.getEvalTypeName());
			preparedStatement.setInt(index++, evalPlanDTO.getQuestionTypeId().getQuestionTypeId());
			preparedStatement.setString(index++, evalPlanDTO.getQuestionTypeName());
			preparedStatement.setString(index++, NullUtils.cvStr(evalPlanDTO.getRequireAnswer()));
			preparedStatement.setString(index++, evalPlanDTO.getTitle());
			preparedStatement.setString(index++, evalPlanDTO.getDetail());
			preparedStatement.setString(index++, NullUtils.cvStr(evalPlanDTO.getEnable()));
			preparedStatement.setInt(index++, evalPlanDTO.getCreateBy());
			preparedStatement.setInt(index++, evalPlanDTO.getUpdateBy());
			
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					primaryKey = resultSet.getInt(1);
				}
			}
			
			return primaryKey;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryKey;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countEvalPlanList(String queryWhereClause) {
		int totalEvalPlanList = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Eval_Plan).append(" eplan ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalEvalPlanList = resultSet.getInt("total");
			}
			return totalEvalPlanList;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalEvalPlanList;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
