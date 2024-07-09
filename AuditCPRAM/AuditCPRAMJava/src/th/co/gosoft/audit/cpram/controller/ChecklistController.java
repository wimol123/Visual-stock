package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.ChecklistDAO;
import th.co.gosoft.audit.cpram.dao.EvalFormDAO;
import th.co.gosoft.audit.cpram.dao.QuestionAnswerMappingDAO;
import th.co.gosoft.audit.cpram.dto.ChecklistDTO;
import th.co.gosoft.audit.cpram.dto.EvalFormDTO;
import th.co.gosoft.audit.cpram.dto.QuestionAnswerMappingDTO;
import th.co.gosoft.audit.cpram.model.AnswerModel;
import th.co.gosoft.audit.cpram.model.ChecklistModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.EvalFormModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class ChecklistController {
	private final static Logger logger = Logger.getLogger(ChecklistController.class);

	
	public DataTableModel<ChecklistModel> dataTableGetChecklistList(DataTablePostParamModel dataTablePostParamModel){
		ChecklistDAO checklistDAO = null;
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			DataTableModel<ChecklistModel> dataTableModel = new DataTableModel<>();
			
			StringBuilder sbWhereCause = new StringBuilder();
			for (Column col : dataTablePostParamModel.getColumns()) {
				
				if (!StringUtils.isNullOrEmpty(col.getName())) {					
					if (col.getName().equals("checklistTitle")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.checklist_title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");

					} else if (col.getName().equals("checklistTypeId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.checklist_type_id").append(" = '").append(col.getSearch().getValue()).append("' ");

					} else if (col.getName().equals("productTypeId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.product_type_id").append(" = '").append(col.getSearch().getValue()).append("' ");

					}else if (col.getName().equals("checklistScope")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.checklist_scope").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("scoringCriteria")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.scoring_criteria").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("approveSupplierRule")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.approve_supplier_rule").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("description")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.description").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("effectiveDate")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.effective_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					
					}else if (col.getName().equals("expireDate")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.expire_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					
					}else if (col.getName().equals("enable")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.enable").append(" = '").append(col.getSearch().getValue()).append("' ");
						
					}else if (col.getName().equals("createBy")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.create_by").append(" = '").append(col.getSearch().getValue()).append("' ");
						
					}
					
				}
			}
			
			List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklistList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString());
			int countChecklist = checklistDAO.countChecklistList(sbWhereCause.toString());
			dataTableModel.setRecordsFiltered(countChecklist);
			dataTableModel.setRecordsTotal(countChecklist);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setData(new ArrayList<>());
			for(ChecklistDTO checklist : checklistDTOs) {
				dataTableModel.getData().add(TransformDTO.transChecklistDTO(checklist));
			}
			return dataTableModel;
		}catch (Exception e) {
			logger.error("ChecklistController.dataTableGetChecklistList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public DataTableModel<ChecklistModel> datatableChecklistByProductType(DataTablePostParamModel dataTablePostParamModel) {
		ChecklistDAO checklistDAO = null;
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			DataTableModel<ChecklistModel> dataTableModel = new DataTableModel<>();
			String productTypeId = dataTablePostParamModel.getOptionString();			
			
			StringBuilder sbWhereCause = new StringBuilder();
			sbWhereCause.append(" AND ");
			sbWhereCause.append("c.product_type_id").append(" = '").append(productTypeId).append("' ");
			for (Column col : dataTablePostParamModel.getColumns()) {
				
				if (!StringUtils.isNullOrEmpty(col.getName())) {					
					if (col.getName().equals("checklistTitle")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.checklist_title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");

					} else if (col.getName().equals("checklistTypeId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.checklist_type_id").append(" = '").append(col.getSearch().getValue()).append("' ");

					} else if (col.getName().equals("checklistScope")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.checklist_scope").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("scoringCriteria")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.scoring_criteria").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("approveSupplierRule")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.approve_supplier_rule").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("description")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.description").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("effectiveDate")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.effective_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					
					}else if (col.getName().equals("expireDate")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.expire_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");					
					}					
				}
			}
			
			int numOfChecklist = checklistDAO.countChecklistList(sbWhereCause.toString());
			List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklistList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString());
			dataTableModel.setData(new ArrayList<>());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(numOfChecklist);
			dataTableModel.setRecordsTotal(numOfChecklist);
			for(ChecklistDTO checklistDTO : checklistDTOs) {
				dataTableModel.getData().add(TransformDTO.transChecklistDTO(checklistDTO));
			}			
			return dataTableModel;
		}catch (Exception e) {
			logger.error("ChecklistController.datatableChecklistByProductType() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public Map<String, Boolean> insertChecklist(HttpServletRequest httpServletRequest, String objChecklist) {
		Gson gson = null;
		ChecklistDAO checklistDAO = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = true;
		boolean validateEffectiveDate = true;
		Connection connection = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			ChecklistModel checklistModelRequest = gson.fromJson(objChecklist, ChecklistModel.class);				
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND ").append("c.product_type_id = '").append(checklistModelRequest.getProductTypeId().getProductTypeId()).append("' ");
			List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklistOrderEffectiveDate(0, checklistDAO.countChecklistList(queryWhereClause.toString()), queryWhereClause.toString());
			
			Date dateEffective = DateUtils.parseWebDateStringToSQLDate(checklistModelRequest.getEffectiveDate().trim());
			for(ChecklistDTO checklist : checklistDTOs) {
				if(checklist.getExpireDate().after(dateEffective) || checklist.getExpireDate().equals(dateEffective)) {
					resultProcess = false;
					validateEffectiveDate = false;
					break;
				}
			}
			
			if(validateEffectiveDate) {
				checklistModelRequest.setEffectiveDate(String.format("%s %s", checklistModelRequest.getEffectiveDate().trim(),StaticVariableUtils.timeOfEffective));
				checklistModelRequest.setExpireDate(String.format("%s %s", checklistModelRequest.getExpireDate().trim(),StaticVariableUtils.timeOfExpire));
				checklistModelRequest.setCreateBy(NullUtils.cvStr(userSessionId));
				checklistModelRequest.setUpdateBy(NullUtils.cvStr(userSessionId));
				
				int primaryKeyChecklist = checklistDAO.insertChecklist(TransformModel.transChecklistModel(checklistModelRequest));
				
				if(primaryKeyChecklist > 0) {
					checklistModelRequest.setChecklistId(NullUtils.cvStr(primaryKeyChecklist));
					resultProcess = insertEvalFormByChecklist(connection, checklistModelRequest);
				}else {
					resultProcess = false;
				}	
			}
			
			if(resultProcess && validateEffectiveDate) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			Map<String, Boolean> resultMap = new HashMap<>();
			resultMap.put("validateEffectiveDate", validateEffectiveDate);
			resultMap.put("resultProcess", resultProcess);
			return resultMap;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ChecklistController.insertChecklist() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public Map<String, Boolean> updateChecklist(HttpServletRequest httpServletRequest, String objChecklist) {
		Connection connection = null;
		Gson gson = null;
		ChecklistDAO checklistDAO = null;
		EvalFormDAO evalFormDAO = null;
		QuestionAnswerMappingDAO questionAnswerMappingDAO = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = true;
		boolean validateEffectiveDate = true;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			evalFormDAO = new EvalFormDAO(connection);
			questionAnswerMappingDAO = new QuestionAnswerMappingDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			ChecklistModel checklistModelRequest = gson.fromJson(objChecklist, ChecklistModel.class);
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND ").append("c.product_type_id = '").append(checklistModelRequest.getProductTypeId().getProductTypeId()).append("' ");
			queryWhereClause.append(" AND ").append("c.checklist_id != '").append(checklistModelRequest.getChecklistId()).append("' ");
			List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklistOrderEffectiveDate(0, checklistDAO.countChecklistList(queryWhereClause.toString()), queryWhereClause.toString());
			
			Date dateEffective = DateUtils.parseWebDateStringToSQLDate(checklistModelRequest.getEffectiveDate().trim());
			for(ChecklistDTO checklist : checklistDTOs) {
				if(checklist.getExpireDate().after(dateEffective) || checklist.getExpireDate().equals(dateEffective)) {
					resultProcess = false;
					validateEffectiveDate = false;
					break;
				}
			}
			
			if(validateEffectiveDate) {
				
				checklistModelRequest.setEffectiveDate(String.format("%s %s", checklistModelRequest.getEffectiveDate().trim(),StaticVariableUtils.timeOfEffective));
				checklistModelRequest.setExpireDate(String.format("%s %s", checklistModelRequest.getExpireDate().trim(),StaticVariableUtils.timeOfExpire));
				checklistModelRequest.setCreateBy(NullUtils.cvStr(userSessionId));
				checklistModelRequest.setUpdateBy(NullUtils.cvStr(userSessionId));	
				
				resultProcess = checklistDAO.updateChecklist(TransformModel.transChecklistModel(checklistModelRequest));
				if(resultProcess) {
					StringBuilder queryGetEvalForm = new StringBuilder();
					queryGetEvalForm.append(" AND ").append("ef.checklist_id ='").append(checklistModelRequest.getChecklistId()).append("'");
					List<EvalFormDTO> evalFormDTOs = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryGetEvalForm.toString()), queryGetEvalForm.toString());
					for(EvalFormDTO evalFormDTO : evalFormDTOs) {
						resultProcess = evalFormDAO.deleteEvalForm(evalFormDTO);
						if(!resultProcess) {
							resultProcess = false;
							break;
						}
						if(evalFormDTO.getEvalTypeId().getEvalTypeId() == NullUtils.cvInt(StaticVariableUtils.evalTypeQuestion)) {
							QuestionAnswerMappingDTO questionAnswerMapping = new QuestionAnswerMappingDTO();
							questionAnswerMapping.setEvalFormId(evalFormDTO.getEvalFormId());;
							
							resultProcess = questionAnswerMappingDAO.deleteQuestionAnswerMappingByEvalForm(questionAnswerMapping);
							if(!resultProcess) {
								resultProcess = false;
								break;
							}
						}
					}
					if(resultProcess) {
						resultProcess = insertEvalFormByChecklist(connection, checklistModelRequest);						
					}
				}
				
			}
			
			if(resultProcess && validateEffectiveDate)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			Map<String, Boolean> resultMap = new HashMap<>();
			resultMap.put("validateEffectiveDate", validateEffectiveDate);
			resultMap.put("resultProcess", resultProcess);
			return resultMap;
			
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ChecklistController.updateChecklist() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public boolean insertEvalFormByChecklist(Connection connection, ChecklistModel checklistModel) {
		boolean resultProcess = true;
		EvalFormDAO evalFormDAO = null;
		QuestionAnswerMappingDAO questionAnswerMappingDAO = null;
		try {
			
			evalFormDAO = new EvalFormDAO(connection);
			questionAnswerMappingDAO = new QuestionAnswerMappingDAO(connection);
			
			
			for(EvalFormModel evalForm : checklistModel.getEvalFormId()) {
				evalForm.setCreateBy(NullUtils.cvStr(checklistModel.getCreateBy()));
				evalForm.setUpdateBy(NullUtils.cvStr(checklistModel.getUpdateBy()));
				evalForm.setChecklistId(new ChecklistModel());
				evalForm.getChecklistId().setChecklistId(checklistModel.getChecklistId().trim());
				
				if(evalForm.getEvalTypeId().getEvalTypeId().equals(StaticVariableUtils.evalTypeTopic)) {					
					evalForm.setParentId(evalForm.getUniqueIdParent());
					evalForm.setEnable(checklistModel.getEnable());
					int primaryKey = evalFormDAO.insertEvalForm(TransformModel.transEvalFormModel(evalForm));
					if(primaryKey == 0) {
						resultProcess = false;
						break;
					}
				}else {
					StringBuilder queryWhereClause = new StringBuilder();
					queryWhereClause.append(" AND ").append("ef.unique_id ='").append(evalForm.getUniqueIdParent()).append("' ");
					queryWhereClause.append(" AND ").append("ef.checklist_id ='").append(checklistModel.getChecklistId()).append("' ");
					queryWhereClause.append(" AND ").append("ef.create_date = ( ");
					queryWhereClause.append("SELECT max(eval_f.create_date) FROM eval_form eval_f WHERE eval_f.unique_id ='"+evalForm.getUniqueIdParent()+"'  AND eval_f.checklist_id ='"+checklistModel.getChecklistId()+"')");
					List<EvalFormDTO> evalFormDTOs = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryWhereClause.toString()), queryWhereClause.toString());
					evalForm.setParentId(NullUtils.cvStr(evalFormDTOs.get(0).getEvalFormId()));
					evalForm.setEnable(checklistModel.getEnable());
					
					if(evalForm.getEvalTypeId().getEvalTypeId().equals(StaticVariableUtils.evalTypeSubTopic) || evalForm.getEvalTypeId().getEvalTypeId().equals(StaticVariableUtils.evalTypeQuestionGroup)) {
						int primaryKey = evalFormDAO.insertEvalForm(TransformModel.transEvalFormModel(evalForm));
						if(primaryKey == 0) {
							resultProcess = false;
							break;
						}
					}else {
						
						int primaryKey = evalFormDAO.insertEvalForm(TransformModel.transEvalFormModel(evalForm));
						if(primaryKey == 0) {
							resultProcess = false;
							break;
						}else {
							
							boolean resultInsertMapping = true;
							for(AnswerModel answer : evalForm.getAnswerId()) {
								QuestionAnswerMappingDTO questionAnswerMappingDTO = new QuestionAnswerMappingDTO();
								questionAnswerMappingDTO.setAnswerId(NullUtils.cvInt(answer.getAnswerId()));
								questionAnswerMappingDTO.setEvalFormId(NullUtils.cvInt(primaryKey));
								questionAnswerMappingDTO.setCreateBy(NullUtils.cvInt(checklistModel.getCreateBy()));
								questionAnswerMappingDTO.setUpdateBy(NullUtils.cvInt(checklistModel.getUpdateBy()));	
								questionAnswerMappingDTO.setEnable(NullUtils.cvChar(evalForm.getEnable()));
								resultInsertMapping = questionAnswerMappingDAO.insertQuestionAnswerMapping(questionAnswerMappingDTO);
								
								if(!resultInsertMapping) {
									break;
								}
							}
							if(!resultInsertMapping) {
								resultProcess = false;
								break;
							}
							
						}
						
					}					
				}
				
			}
			
			/*if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);*/
			
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ChecklistController.insertEvalFormByChecklist() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}/*finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}*/
	}
	
	public boolean insertEvalFormByChecklist_temp(ChecklistModel checklistModel) {
		Connection connection = null;
		EvalFormDAO evalFormDAO = null;
		QuestionAnswerMappingDAO questionAnswerMappingDAO = null;
		boolean resultProcess = true;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			evalFormDAO = new EvalFormDAO(connection);
			questionAnswerMappingDAO = new QuestionAnswerMappingDAO(connection);
			
			for(List<EvalFormModel> evalFormModels : checklistModel.getEvalFormFontEnd()) {
				int parentId = 0;
				boolean resultInLoop = true;
				for(EvalFormModel evalForm : evalFormModels) {
					if(!evalForm.getEvalTypeId().getEvalTypeId().equals(StaticVariableUtils.evalTypeQuestion)) {						
						evalForm.setChecklistId(checklistModel);
						evalForm.setCreateBy(NullUtils.cvStr(checklistModel.getCreateBy()));
						evalForm.setUpdateBy(NullUtils.cvStr(checklistModel.getUpdateBy()));
						int primaryKeyEvalForm = evalFormDAO.checkDupplicateTitle(TransformModel.transEvalFormModel(evalForm));
						if(primaryKeyEvalForm == 0) {								
							evalForm.setParentId(NullUtils.cvStr(parentId));
							evalForm.setEnable(checklistModel.getEnable());								
							primaryKeyEvalForm = evalFormDAO.insertEvalForm(TransformModel.transEvalFormModel(evalForm));
							if(primaryKeyEvalForm > 0)
								parentId = primaryKeyEvalForm;
							else {
								resultInLoop = false;
								break;
							}
							
						}else {
							parentId = primaryKeyEvalForm;
							continue;
						}
					}else {						
						evalForm.setChecklistId(checklistModel);
						evalForm.setCreateBy(NullUtils.cvStr(checklistModel.getCreateBy()));
						evalForm.setUpdateBy(NullUtils.cvStr(checklistModel.getUpdateBy()));
						int primaryKeyEvalForm = evalFormDAO.checkDupplicateDetail(TransformModel.transEvalFormModel(evalForm));
						if(primaryKeyEvalForm == 0) {
							evalForm.setParentId(NullUtils.cvStr(parentId));	
							evalForm.setEnable(checklistModel.getEnable());
							primaryKeyEvalForm = evalFormDAO.insertEvalForm(TransformModel.transEvalFormModel(evalForm));
							if(primaryKeyEvalForm > 0) {
								parentId = primaryKeyEvalForm;
								boolean resultInsertMapping = true;
								for(AnswerModel answer : evalForm.getAnswerId()) {
									QuestionAnswerMappingDTO questionAnswerMappingDTO = new QuestionAnswerMappingDTO();
									questionAnswerMappingDTO.setAnswerId(NullUtils.cvInt(answer.getAnswerId()));
									questionAnswerMappingDTO.setEvalFormId(NullUtils.cvInt(primaryKeyEvalForm));
									questionAnswerMappingDTO.setCreateBy(NullUtils.cvInt(checklistModel.getCreateBy()));
									questionAnswerMappingDTO.setUpdateBy(NullUtils.cvInt(checklistModel.getUpdateBy()));	
									questionAnswerMappingDTO.setEnable(NullUtils.cvChar(evalForm.getEnable()));
									resultInsertMapping = questionAnswerMappingDAO.insertQuestionAnswerMapping(questionAnswerMappingDTO);
									if(!resultInsertMapping) {										
										break;
									}
								}
								if(!resultInsertMapping) {
									resultInLoop = false;
									break;
								}
							}
							else {
								resultInLoop = false;
								break;
							}
						}else {
							parentId = primaryKeyEvalForm;
							continue;
						}
					}
				}
				if(!resultInLoop) {
					resultProcess = false;
					break;
				}						
			}
			
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ChecklistController.insertEvalFormByChecklist_temp() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getChecklistList(String objChecklist) {
		Connection connection = null;
		ChecklistDAO checklistDAO = null;
		Gson gson = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			gson = new Gson();
			
			ChecklistModel checklistModelRequest = gson.fromJson(objChecklist, ChecklistModel.class);
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND ");
			queryWhereClause.append("c.checklist_id").append(" = '").append(checklistModelRequest.getChecklistId()).append("' ");
			
			List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklistList(0, checklistDAO.countChecklistList(queryWhereClause.toString()), queryWhereClause.toString());
			List<ChecklistModel> checklistModels = new ArrayList<>();
			for(ChecklistDTO checklist : checklistDTOs) {				
				checklistModels.add(TransformDTO.transChecklistDTO(checklist));
			}
			
			return gson.toJson(checklistModels);
		}catch (Exception e) {
			
			logger.error("ChecklistController.getChecklistList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public boolean deleteChecklist(String objChecklist) {
		Connection connection = null;
		ChecklistDAO checklistDAO = null;
		EvalFormDAO evalFormDAO = null;
		QuestionAnswerMappingDAO questionAnswerMappingDAO = null;
		Gson gson = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			checklistDAO = new ChecklistDAO(connection);
			evalFormDAO = new EvalFormDAO(connection);
			questionAnswerMappingDAO = new QuestionAnswerMappingDAO(connection);
			
			ChecklistModel checklistModelRequest = gson.fromJson(objChecklist, ChecklistModel.class);
			
			StringBuilder queryGetEvalForm = new StringBuilder();
			queryGetEvalForm.append(" AND ").append("ef.checklist_id ='").append(checklistModelRequest.getChecklistId()).append("'");
			List<EvalFormDTO> evalFormDTOs = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryGetEvalForm.toString()), queryGetEvalForm.toString());
			for(EvalFormDTO evalFormDTO : evalFormDTOs) {
				resultProcess = evalFormDAO.deleteEvalForm(evalFormDTO);
				if(!resultProcess) {
					resultProcess = false;
					break;
				}
				if(evalFormDTO.getEvalTypeId().getEvalTypeId() == NullUtils.cvInt(StaticVariableUtils.evalTypeQuestion)) {
					QuestionAnswerMappingDTO questionAnswerMapping = new QuestionAnswerMappingDTO();
					questionAnswerMapping.setEvalFormId(evalFormDTO.getEvalFormId());
					
					resultProcess = questionAnswerMappingDAO.deleteQuestionAnswerMappingByEvalForm(questionAnswerMapping);
					if(!resultProcess) {
						resultProcess = false;
						break;
					}
				}
			}
			if(resultProcess) {
				resultProcess = checklistDAO.deleteChecklist(TransformModel.transChecklistModel(checklistModelRequest));			
				if(!resultProcess) {
					resultProcess = false;
				}
			}
			
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ChecklistController.deleteChecklist() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	/*public DataTableModel<ChecklistModel> GetChecklistList(DataTablePostParamModel modelDataTable) {
		ChecklistDAO checklistDAO = null;
		
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			DataTableModel<ChecklistModel> dataTableModel = new DataTableModel<>();
			
			
			StringBuilder sbWhereCause = new StringBuilder();
			for(Column col : modelDataTable.getColumns()) {
				if(!StringUtils.isNullOrEmpty(col.getName())) {
					if(col.getName().equals("checklistTitle") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND");
						sbWhereCause.append("c.checklist_title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if(col.getName().equals("statusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					}else if(col.getName().equals("checklistScope") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND");
						sbWhereCause.append("c.checklist_scope").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if(col.getName().equals("scoringCriteria") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND");
						sbWhereCause.append("c.scoring_criteria").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if(col.getName().equals("approveSupplierRule") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND");
						sbWhereCause.append("c.approve_supplier_rule").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if(col.getName().equals("description") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND");
						sbWhereCause.append("c.description").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if (col.getName().equals("effectiveDate") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.effective_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}else if (col.getName().equals("expireDate") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.expire_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}else if (col.getName().equals("expireDate") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("c.expire_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
				}
			}
			
			List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklist_List(modelDataTable.getStart(), modelDataTable.getLength(), sbWhereCause.toString());
			dataTableModel.setData(new ArrayList<>());
			for(ChecklistDTO checklistDTO : checklistDTOs) {
				//dataTableModel.getData().add(TransformDTO(checklistDTO));
				dataTableModel.getData().add(th.co.gosoft.audit.cpram.utils.TransformDTO.transChecklistDTO(checklistDTO));
			}
			Integer totalRecord = checklistDAO.countChecklist(sbWhereCause.toString());
			dataTableModel.setRecordsFiltered(totalRecord);
			dataTableModel.setRecordsTotal(totalRecord);			
			return dataTableModel;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(checklistDAO != null)
				checklistDAO.closeConnection();
		}
	}
	
	
	public ChecklistModel TransformDTO(ChecklistDTO checklistDTO) {
		
		ChecklistModel checklistModel = new ChecklistModel();
		checklistModel.setChecklistId(NullUtils.cvStr(checklistDTO.getChecklistId()));
		checklistModel.setChecklistTitle(NullUtils.cvStr(checklistDTO.getChecklistTitle()));
		//checklistModel.setSupplierId(NullUtils.cvStr(checklistDTO.getSupplierId()));
		//checklistModel.setSupplierName(NullUtils.cvStr(checklistDTO.getSupplierName()));
		//checklistModel.setSupplierLocation(NullUtils.cvStr(checklistDTO.getSupplierLocation()));
		checklistModel.setChecklistScope(NullUtils.cvStr(checklistDTO.getChecklistScope()));
		//checklistModel.setJobNo(NullUtils.cvStr(checklistDTO.getJobNo()));
		//checklistModel.setVisitNo(NullUtils.cvStr(checklistDTO.getVisitNo()));
		checklistModel.setApproveSupplierRule(NullUtils.cvStr(checklistDTO.getApproveSupplierRule()));
		checklistModel.setScoringCriteria(NullUtils.cvStr(checklistDTO.getScoringCriteria()));
		checklistModel.setDescription(NullUtils.cvStr(checklistDTO.getDescription()));
		checklistModel.setCreateDate(NullUtils.cvStr(checklistDTO.getCreateDate()));
		checklistModel.setStatusId(NullUtils.cvStr(checklistDTO.getStatusId()));
		checklistModel.setUpdateDate(NullUtils.cvStr(checklistDTO.getUpdateDate()));
	
		
		return checklistModel;
	}
	
	public List<ChecklistModel> searchChecklist(String searchOption) {
		ChecklistDAO checklistDAO = null;
		Gson gson = null;
		
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			
			gson = new Gson();
			ChecklistModel searchOptionObj = gson.fromJson(searchOption, ChecklistModel.class);
			
			List<ChecklistDTO> checklistDTO_list = checklistDAO.searchChecklist(searchOptionObj);
			
			List<ChecklistModel> checklistModel_list = new ArrayList<>();
			
			for(ChecklistDTO checklistDTO : checklistDTO_list) {
				ChecklistModel checklistModel = new ChecklistModel();
				checklistModel.setChecklistId(NullUtils.cvStr(checklistDTO.getChecklistId()));
				checklistModel.setChecklistName(NullUtils.cvStr(checklistDTO.getChecklistName()));
				checklistModel.setCreateDate(NullUtils.cvStr(checklistDTO.getCreateDate()));
				checklistModel.setStatusId(NullUtils.cvStr(checklistDTO.getStatusId()));
				checklistModel.setUpdateDate(NullUtils.cvStr(checklistDTO.getUpdateDate()));
				checklistModel_list.add(checklistModel);
			}
			return checklistModel_list;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(checklistDAO != null)
				checklistDAO.closeConnection();
		}
		
	}
	
	public boolean InsertChecklist(String jsonString) {
		ChecklistDAO checklistDAO = null;
		Gson gson = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			
			gson = new Gson();
			
			ChecklistModel checklistJson = gson.fromJson(jsonString, ChecklistModel.class);
			
			return checklistDAO.InsertChecklist(checklistJson);
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(checklistDAO != null)
				checklistDAO.closeConnection();
		}
	}
	
	public boolean UpdateChecklist(String jsonString) {
		ChecklistDAO checklistDAO = null;
		Gson gson = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			
			gson = new Gson();
			
			ChecklistModel checklistJson = gson.fromJson(jsonString, ChecklistModel.class);
			
			return checklistDAO.UpdateChecklist(checklistJson);
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(checklistDAO != null)
				checklistDAO.closeConnection();
		}
	}
	
	
	public boolean DeleteChecklist(String checklistId) {
		ChecklistDAO checklistDAO = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistDAO = new ChecklistDAO(connection);
			
			return checklistDAO.DeleteChecklist(checklistId);			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(checklistDAO != null)
				checklistDAO.closeConnection();
		}
		
	}*/
	
	
}
