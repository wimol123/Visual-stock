package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.ChecklistDTO;
import th.co.gosoft.audit.cpram.dto.EvalFormDTO;
import th.co.gosoft.audit.cpram.dto.EvalTypeDTO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class EvalFormDAO extends StandardAttributeDAO{
	public EvalFormDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(EvalFormDAO.class);
	
	
	
	public List<EvalFormDTO> getEvalFormList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			/*SELECT ef.eval_form_id, ef.checklist_id, ef.parent_id, ef.unique_id, 
			(
			SELECT eform.unique_id FROM eval_form eform 
			WHERE eform.eval_form_id = ef.parent_id
			) 
			parent_unique_id, 
			ef.eval_type_id, ef.question_type_id, ef.require_anwser, ef.title, ef.detail, ef.enable
			FROM eval_form ef 
			WHERE 1=1 AND ef.checklist_id = 15;*/
			connection.setAutoCommit(false);
			
			StringBuilder subQuery = new StringBuilder();
			subQuery.append(" (SELECT eform.unique_id FROM ").append(DBConst.TABLE_Eval_Form).append(" eform ");
			subQuery.append(" WHERE eform.eval_form_id = ef.parent_id) ");			
			StringBuilder query = new StringBuilder();
			query.append("SELECT ef.eval_form_id, ef.checklist_id, ef.parent_id, ef.unique_id, ef.eval_type_id, et.eval_type_name, ef.question_type_id, ef.require_anwser, ef.title, ef.detail, ef.enable, ");
			query.append(subQuery).append(" parent_unique_id ");
			query.append("FROM ").append(DBConst.TABLE_Eval_Form).append(" ef ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Eval_Type).append(" et ");
			query.append("ON ef.eval_type_id = et.eval_type_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			if(numOfRows != -1) {
				query.append(String.format(" order by ef.eval_form_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			logger.debug("Query String : "+query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<EvalFormDTO> evalFormDTOs = new ArrayList<>();
			while(resultSet.next()) {
				EvalFormDTO evalForm = new EvalFormDTO();
				evalForm.setEvalFormId(resultSet.getInt("eval_form_id"));
				evalForm.setParentId(resultSet.getInt("parent_id"));
				evalForm.setUniqueId(resultSet.getString("unique_id"));
				evalForm.setParentUniqueId(resultSet.getString("parent_unique_id"));
				evalForm.setRequireAnwser(NullUtils.cvChar(resultSet.getString("require_anwser")));
				evalForm.setTitle(resultSet.getString("title"));
				evalForm.setDetail(resultSet.getString("detail"));
				evalForm.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				
				ChecklistDTO checklist = new ChecklistDTO();
				checklist.setChecklistId(resultSet.getInt("checklist_id"));
				evalForm.setChecklistId(checklist);
				
				EvalTypeDTO evalTypeDTO = new EvalTypeDTO();
				evalTypeDTO.setEvalTypeId(resultSet.getInt("eval_type_id"));
				evalTypeDTO.setEvalTypeName(resultSet.getString("eval_type_name"));
				evalForm.setEvalTypeId(evalTypeDTO);
				
				QuestionTypeDTO questionType = new QuestionTypeDTO();
				questionType.setQuestionTypeId(resultSet.getInt("question_type_id"));
				evalForm.setQuestionTypeId(questionType);
				
				evalFormDTOs.add(evalForm);
			}
			return evalFormDTOs;
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			logger.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateQuestionType(EvalFormDTO evalForm) {
		boolean result = false;
		try {
			//UPDATE `eval_form` SET `question_type_id` = '2' WHERE (`eval_form_id` = '25');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Eval_Form).append(" ");
			query.append("SET ").append("question_type_id = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (eval_form_id = ?);");
			int rowAffect = 0, index = 1;
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(index++, evalForm.getQuestionTypeId().getQuestionTypeId());
			preparedStatement.setInt(index++, evalForm.getUpdateBy());
			preparedStatement.setInt(index++, evalForm.getEvalFormId());
			rowAffect = preparedStatement.executeUpdate();
			if(rowAffect > 0) {
				result = true;
			}
			return result;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return result;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateEvalForm(EvalFormDTO evalForm) {
		boolean result = false;
		try {
			//UPDATE `eval_form` SET `title` = 'มาตรฐานสภาวะแวดล้อมภายนอกfghfghdfgh', `detail` = 'dfghdfghdfghdfghdfgh', `update_by` = '2', `update_date` = 'now()' WHERE (`eval_form_id` = '22');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Eval_Form).append(" ");
			query.append("SET ");
			query.append("title = ?, ");
			query.append("detail = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (eval_form_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffect = 0;
			preparedStatement.setString(index++, evalForm.getTitle());
			preparedStatement.setString(index++, evalForm.getDetail());
			preparedStatement.setInt(index++, evalForm.getUpdateBy());
			preparedStatement.setInt(index++, evalForm.getEvalFormId());
			rowAffect = preparedStatement.executeUpdate();
			
			if(rowAffect > 0) {		
				result = true;
			}
			return result;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return result;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}	
	
	public int insertEvalForm(EvalFormDTO evalFormDTO) {
		int primaryEvalForm = 0;
		try {
			//INSERT INTO `audit_supplier_cpram_final`.`eval_form` (`checklist_id`, `parent_id`, `unique_id`, `eval_type_id`, `question_type_id`, `require_anwser`, `title`, `detail`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2', '0', '1555555555555', '2', '0', 'Y', 'sdafasdfasdfasdfasdf', 'asdfasdfasdfasdfasdfasdfasdf', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Eval_Form).append(" ");
			query.append("(checklist_id, parent_id, unique_id, eval_type_id, question_type_id, require_anwser, title, detail, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, evalFormDTO.getChecklistId().getChecklistId());
			preparedStatement.setInt(index++, evalFormDTO.getParentId());
			preparedStatement.setString(index++, evalFormDTO.getUniqueId());
			preparedStatement.setInt(index++, evalFormDTO.getEvalTypeId().getEvalTypeId());
			preparedStatement.setInt(index++, evalFormDTO.getQuestionTypeId().getQuestionTypeId());
			preparedStatement.setString(index++, NullUtils.cvStr(evalFormDTO.getRequireAnwser()));
			preparedStatement.setString(index++, evalFormDTO.getTitle());
			preparedStatement.setString(index++, evalFormDTO.getDetail());
			preparedStatement.setString(index++, NullUtils.cvStr(evalFormDTO.getEnable()));
			preparedStatement.setInt(index++, evalFormDTO.getCreateBy());
			preparedStatement.setInt(index++, evalFormDTO.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					primaryEvalForm = resultSet.getInt(1);
				}
			}
			return primaryEvalForm;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryEvalForm;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean deleteEvalForm(EvalFormDTO evalFormDTO) {
		try {
			//DELETE FROM `audit_supplier_cpram_final`.`eval_form` WHERE (`eval_form_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Eval_Form).append(" ");
			query.append("WHERE (eval_form_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, evalFormDTO.getEvalFormId());
			preparedStatement.executeUpdate();
			
			return true;
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}	
	}
	
	public int checkDupplicateTitle(EvalFormDTO evalFormDTO) {
		int primaryEvalForm = 0;
		try {
			//SELECT ef.title, ef.eval_form_id FROM eval_form ef WHERE ef.title = 'I. GMP'
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ef.title, ef.eval_form_id ");
			query.append("FROM ").append(DBConst.TABLE_Eval_Form).append(" ef ");
			query.append("WHERE ef.title = ? AND checklist_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, evalFormDTO.getTitle());
			preparedStatement.setInt(2, evalFormDTO.getChecklistId().getChecklistId());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				primaryEvalForm = resultSet.getInt("eval_form_id");
			}
			return primaryEvalForm;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryEvalForm;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}	
	}
	
	public int checkDupplicateDetail(EvalFormDTO evalFormDTO) {
		int primaryEvalForm = 0;
		try {
			//SELECT ef.detail, ef.eval_form_id FROM eval_form ef WHERE ef.detail = '1.1.1 ต้องพิจารณาถึงกิจกรรมต่าง ๆ และสภาพแวดล้อมในบริเวณปฏิบัติงานซึ่งอาจเกิดผลกระทบในทางเสียหาย และต้องกำหนดการป้องกันการปนเปื้อนในสถานที่ปฏิบัติงานต้องทบทวนอยู่เสมอเพื่อให้มั่นใจว่าคงประสิทธิภาพ'
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ef.detail, ef.eval_form_id ");
			query.append("FROM ").append(DBConst.TABLE_Eval_Form).append(" ef ");
			query.append("WHERE ef.detail = ? AND checklist_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, evalFormDTO.getDetail());
			preparedStatement.setInt(2, evalFormDTO.getChecklistId().getChecklistId());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				primaryEvalForm = resultSet.getInt("eval_form_id");
			}
			return primaryEvalForm;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryEvalForm;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int checkDupplicateEvalFormByUniqueId(EvalFormDTO evalFormDTO) {
		int primaryEvalForm = 0;
		try {
			//SELECT ef.eval_form_id FROM eval_form ef WHERE ef.unique_id = '1549527629487' AND checklist_id = '2';
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ef.eval_form_id ");
			query.append("FROM ").append(DBConst.TABLE_Eval_Form).append(" ef ");
			query.append("WHERE ef.unique_id = ? AND checklist_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, evalFormDTO.getUniqueId());
			preparedStatement.setInt(2, evalFormDTO.getChecklistId().getChecklistId());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				primaryEvalForm = resultSet.getInt("eval_form_id");
			}
			return primaryEvalForm;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryEvalForm;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}	
	}
	
	public int countEvalFormList(String queryWhereClause) {
		int countEvalForm = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Eval_Form).append(" ef ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countEvalForm = resultSet.getInt("total");
			}
			return countEvalForm;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countEvalForm;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
}
