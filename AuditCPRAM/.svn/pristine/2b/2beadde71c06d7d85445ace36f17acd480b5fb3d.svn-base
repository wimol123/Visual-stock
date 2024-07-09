package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.EvalFormDAO;
import th.co.gosoft.audit.cpram.dao.QuestionAnswerMappingDAO;
import th.co.gosoft.audit.cpram.dao.QuestionTypeDAO;
import th.co.gosoft.audit.cpram.dto.AnswerDTO;
import th.co.gosoft.audit.cpram.dto.EvalFormDTO;
import th.co.gosoft.audit.cpram.dto.QuestionAnswerMappingDTO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.model.AnswerModel;
import th.co.gosoft.audit.cpram.model.ChecklistToFrontEndModel;
import th.co.gosoft.audit.cpram.model.EvalFormModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class EvalFormController {

	private final static Logger logger = Logger.getLogger(EvalFormController.class);
	
	public Map<String, Object> updateEvalFormByUniqueId(HttpServletRequest httpServletRequest, String evalFormObj) {
		Connection connection = null;
		EvalFormDAO evalFormDAO = null;
		QuestionAnswerMappingDAO questionAnswerMappingDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		Boolean processQuestionType = false, processAnswer = false, processStatus = false; 
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			evalFormDAO = new EvalFormDAO(connection);
			questionAnswerMappingDAO = new QuestionAnswerMappingDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			StringBuilder querySearchId = new StringBuilder();
			
			EvalFormModel evalFormModelRequest = gson.fromJson(evalFormObj, EvalFormModel.class);
			evalFormModelRequest.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
			
			querySearchId.append(" AND ef.unique_id ='").append(evalFormModelRequest.getUniqueId()).append("' ");
			List<EvalFormDTO> evalFormDTOs = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(querySearchId.toString()), querySearchId.toString());
			for(EvalFormDTO evalForm : evalFormDTOs) {
				evalFormModelRequest.setEvalFormId(NullUtils.cvStr(evalForm.getEvalFormId()));				
			}
			
			if(evalFormModelRequest.getQuestionTypeId() != null && evalFormModelRequest.getAnswerId() != null) {
				processQuestionType = evalFormDAO.updateQuestionType(TransformModel.transEvalFormModel(evalFormModelRequest));
				if(processQuestionType) {
					QuestionAnswerMappingDTO questionAnswerMapping = new QuestionAnswerMappingDTO();
					questionAnswerMapping.setEvalFormId(NullUtils.cvInt(evalFormModelRequest.getEvalFormId()));
					questionAnswerMapping.setEnable('Y');
					questionAnswerMapping.setUpdateBy(NullUtils.cvInt(evalFormModelRequest.getUpdateBy()));
					questionAnswerMapping.setCreateBy(NullUtils.cvInt(evalFormModelRequest.getUpdateBy()));
					processAnswer = questionAnswerMappingDAO.deleteQuestionAnswerMappingByEvalForm(questionAnswerMapping);
					if(processAnswer) {
						for(AnswerModel answer : evalFormModelRequest.getAnswerId()) {
							questionAnswerMapping.setAnswerId(NullUtils.cvInt(answer.getAnswerId()));
							processAnswer = questionAnswerMappingDAO.insertQuestionAnswerMapping(questionAnswerMapping);
							if(!processAnswer) {
								processStatus = false;
								break;
							}
						}
					}else {
						processStatus = false;
					}
				}else {
					processStatus = false;
				}
			}else {
				processQuestionType = true;
				processAnswer = true;
			}
			
			if(processQuestionType && processAnswer) {
				processStatus = evalFormDAO.updateEvalForm(TransformModel.transEvalFormModel(evalFormModelRequest));
			}else {
				processStatus = false;				
			}
			
			
			if(processAnswer && processQuestionType && processStatus) 
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			Map<String, Object> resultMap = new HashMap<>();
			resultMap.put("processQuestionType", processQuestionType);
			resultMap.put("processAnswer", processAnswer);
			resultMap.put("processStatus", processStatus);
			return resultMap;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("EvalFormController.updateEvalFormByUniqueId() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getEvalFormList(String evalFormObj) {
		Connection connection = null;
		EvalFormDAO evalFormDAO = null;
		QuestionTypeDAO questionTypeDAO = null;
		QuestionAnswerMappingDAO questionAnswerMappingDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			evalFormDAO = new EvalFormDAO(connection);
			questionTypeDAO = new QuestionTypeDAO(connection);		
			questionAnswerMappingDAO = new QuestionAnswerMappingDAO(connection);
			gson = new Gson();
			
			EvalFormModel evalFormRequest = gson.fromJson(evalFormObj, EvalFormModel.class);
			EvalFormModel EvalFormReturn = new EvalFormModel();
			EvalFormReturn.setChecklistToFrontEnd(new ArrayList<>());
			int parentId = 0, checklistId = 0;
			if(!StringUtils.isNullOrEmpty(evalFormRequest.getChecklistId().getChecklistId())) {
				checklistId = NullUtils.cvInt(evalFormRequest.getChecklistId().getChecklistId());
				StringBuilder queryTopic = new StringBuilder();
				queryTopic.append("AND ef.checklist_id = '").append(checklistId).append("' ");
				queryTopic.append("AND ef.parent_id = '").append(parentId).append("' ");
				queryTopic.append("AND ef.eval_type_id = '").append(StaticVariableUtils.evalTypeTopic).append("' ");
				List<EvalFormDTO> evalFormTopicList = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryTopic.toString()), queryTopic.toString());
				if(evalFormTopicList != null) {
					if(!evalFormTopicList.isEmpty()) {
						for(EvalFormDTO evalFormTopic : evalFormTopicList) {
							
							parentId = evalFormTopic.getEvalFormId();
							StringBuilder querySubTopic = new StringBuilder();
							querySubTopic.append("AND ef.checklist_id = '").append(checklistId).append("' ");
							querySubTopic.append("AND ef.parent_id = '").append(parentId).append("' ");
							querySubTopic.append("AND ef.eval_type_id = '").append(StaticVariableUtils.evalTypeSubTopic).append("' ");
							List<EvalFormDTO> evalFormSubTopicList = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(querySubTopic.toString()), querySubTopic.toString());
							if(evalFormSubTopicList != null) {
								if(!evalFormSubTopicList.isEmpty()) {
									
									for(EvalFormDTO evalFormSubTopic : evalFormSubTopicList) {										
										parentId = evalFormSubTopic.getEvalFormId();
										StringBuilder queryQuestionGroup = new StringBuilder();
										queryQuestionGroup.append("AND ef.checklist_id = '").append(checklistId).append("' ");
										queryQuestionGroup.append("AND ef.parent_id = '").append(parentId).append("' ");
										queryQuestionGroup.append("AND ef.eval_type_id = '").append(StaticVariableUtils.evalTypeQuestionGroup).append("' ");
										List<EvalFormDTO> evalFormQuestionGroupList = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryQuestionGroup.toString()), queryQuestionGroup.toString());
										
										if(evalFormQuestionGroupList != null) {
											if(!evalFormQuestionGroupList.isEmpty()) {
												
												for(EvalFormDTO evalFormQuestionGroup : evalFormQuestionGroupList) {
													
													parentId = evalFormQuestionGroup.getEvalFormId();
													StringBuilder queryQuestion = new StringBuilder();
													queryQuestion.append("AND ef.checklist_id = '").append(checklistId).append("' ");
													queryQuestion.append("AND ef.parent_id = '").append(parentId).append("' ");
													queryQuestion.append("AND ef.eval_type_id = '").append(StaticVariableUtils.evalTypeQuestion).append("' ");
													List<EvalFormDTO> evalFormQuestionList = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryQuestion.toString()), queryQuestion.toString());
													if(evalFormQuestionList != null) {
														if(!evalFormQuestionList.isEmpty()) {
															List<EvalFormModel> evalQuestionModelList = new ArrayList<>();
															for(EvalFormDTO evalFormQuestion : evalFormQuestionList) {
																
																String queryQuestionType = String.format("AND qt.question_type_id ='%s' ", evalFormQuestion.getQuestionTypeId().getQuestionTypeId());
																List<QuestionTypeDTO> questionTypeDTOs = questionTypeDAO.getQuestionTypeList(0, questionTypeDAO.countQuestionTypeList(queryQuestionType), queryQuestionType);
																for(QuestionTypeDTO questionType : questionTypeDTOs) {																	
																	evalFormQuestion.setQuestionTypeId(questionType);
																}
																
																List<AnswerDTO> answerDTOs = questionAnswerMappingDAO.getAnswerListByEvalForm(evalFormQuestion);
																evalFormQuestion.setAnswerId(answerDTOs);
																
																evalQuestionModelList.add(TransformDTO.transEvalFormDTO(evalFormQuestion));
															}
															ChecklistToFrontEndModel checklistToFrontEndModel = new ChecklistToFrontEndModel();
															checklistToFrontEndModel.setTopic(TransformDTO.transEvalFormDTO(evalFormTopic));
															checklistToFrontEndModel.setSubTopic(TransformDTO.transEvalFormDTO(evalFormSubTopic));
															checklistToFrontEndModel.setGroupQuestion(TransformDTO.transEvalFormDTO(evalFormQuestionGroup));
															checklistToFrontEndModel.setQuestion(evalQuestionModelList);
															EvalFormReturn.getChecklistToFrontEnd().add(checklistToFrontEndModel);
															
														}else {
															logger.error(">>>>>> Eval Question Is Empty <<<<<<<");
														}
													}else {
														logger.error(">>>>>> Eval Question Is Null <<<<<<<");
													}
												}
												
											}else {
												logger.error(">>>>>> Eval QuestionGroup Is Empty <<<<<<<");
											}
										}else {
											logger.error(">>>>>> Eval QuestionGroup Is Null <<<<<<<");
										}
									}
									
								}else {
									
									EvalFormDTO evalFormSubTopic = new EvalFormDTO();
									evalFormSubTopic.setTitle("");
									evalFormSubTopic.setDetail("");
									evalFormSubTopic.setUniqueId("");
									
									StringBuilder queryQuestionGroup = new StringBuilder();
									queryQuestionGroup.append("AND ef.checklist_id = '").append(checklistId).append("' ");
									queryQuestionGroup.append("AND ef.parent_id = '").append(parentId).append("' ");
									queryQuestionGroup.append("AND ef.eval_type_id = '").append(StaticVariableUtils.evalTypeQuestionGroup).append("' ");
									List<EvalFormDTO> evalFormQuestionGroupList = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryQuestionGroup.toString()), queryQuestionGroup.toString());
									
									if(evalFormQuestionGroupList != null) {
										if(!evalFormQuestionGroupList.isEmpty()) {
											
											for(EvalFormDTO evalFormQuestionGroup : evalFormQuestionGroupList) {
												
												parentId = evalFormQuestionGroup.getEvalFormId();
												StringBuilder queryQuestion = new StringBuilder();
												queryQuestion.append("AND ef.checklist_id = '").append(checklistId).append("' ");
												queryQuestion.append("AND ef.parent_id = '").append(parentId).append("' ");
												queryQuestion.append("AND ef.eval_type_id = '").append(StaticVariableUtils.evalTypeQuestion).append("' ");
												List<EvalFormDTO> evalFormQuestionList = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryQuestion.toString()), queryQuestion.toString());
												if(evalFormQuestionList != null) {
													if(!evalFormQuestionList.isEmpty()) {
														List<EvalFormModel> evalQuestionModelList = new ArrayList<>();
														for(EvalFormDTO evalFormQuestion : evalFormQuestionList) {
															
															String queryQuestionType = String.format("AND qt.question_type_id ='%s' ", evalFormQuestion.getQuestionTypeId().getQuestionTypeId());
															List<QuestionTypeDTO> questionTypeDTOs = questionTypeDAO.getQuestionTypeList(0, questionTypeDAO.countQuestionTypeList(queryQuestionType), queryQuestionType);
															for(QuestionTypeDTO questionType : questionTypeDTOs) {																	
																evalFormQuestion.setQuestionTypeId(questionType);
															}
															
															List<AnswerDTO> answerDTOs = questionAnswerMappingDAO.getAnswerListByEvalForm(evalFormQuestion);
															evalFormQuestion.setAnswerId(answerDTOs);
															
															evalQuestionModelList.add(TransformDTO.transEvalFormDTO(evalFormQuestion));
														}
														ChecklistToFrontEndModel checklistToFrontEndModel = new ChecklistToFrontEndModel();
														checklistToFrontEndModel.setTopic(TransformDTO.transEvalFormDTO(evalFormTopic));
														checklistToFrontEndModel.setSubTopic(TransformDTO.transEvalFormDTO(evalFormSubTopic));
														checklistToFrontEndModel.setGroupQuestion(TransformDTO.transEvalFormDTO(evalFormQuestionGroup));
														checklistToFrontEndModel.setQuestion(evalQuestionModelList);
														EvalFormReturn.getChecklistToFrontEnd().add(checklistToFrontEndModel);
														
													}else {
														logger.error(">>>>>> Eval Question Is Empty <<<<<<<");
													}
												}else {
													logger.error(">>>>>> Eval Question Is Null <<<<<<<");
												}
											}
											
										}else {
											logger.error(">>>>>> Eval QuestionGroup Is Empty <<<<<<<");
										}
									}else {
										logger.error(">>>>>> Eval QuestionGroup Is Null <<<<<<<");
									}
									
								}
							}else {
								logger.error(">>>>>> Eval SubTopic Is Null <<<<<<<");
							}
						}
					}else {
						logger.error(">>>>>> Eval Topic Is Empty <<<<<<<");
					}
				}else {
					logger.error(">>>>>> Eval Topic Is Null <<<<<<<");
				}
			}else {
				logger.error(">>>>>> Check List ID Is Null <<<<<<<");
			}
			return gson.toJson(EvalFormReturn);
		}catch (Exception e) {
			logger.error("EvalFormController.getEvalFormList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
		finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
}
