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

import th.co.gosoft.audit.cpram.conts.ConstAppointStatus;
import th.co.gosoft.audit.cpram.conts.ConstAssignPlanStatus;
import th.co.gosoft.audit.cpram.conts.ConstChecklistPlanStatus;
import th.co.gosoft.audit.cpram.dao.AddressDAO;
import th.co.gosoft.audit.cpram.dao.AppointDAO;
import th.co.gosoft.audit.cpram.dao.AssignPlanDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistPlanDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistPlanEntourageDAO;
import th.co.gosoft.audit.cpram.dao.EvalFormDAO;
import th.co.gosoft.audit.cpram.dao.EvalPlanAnswerDAO;
import th.co.gosoft.audit.cpram.dao.EvalPlanDAO;
import th.co.gosoft.audit.cpram.dao.QuestionAnswerMappingDAO;
import th.co.gosoft.audit.cpram.dao.QuestionTypeDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dao.SystemLogDAO;
import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.AnswerDTO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.AssignPlanDTO;
import th.co.gosoft.audit.cpram.dto.AssignPlanStatusDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanEntourageDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanStatusDTO;
import th.co.gosoft.audit.cpram.dto.EvalFormDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanAnswerDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalTypeDTO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.model.AssignPlanModel;
import th.co.gosoft.audit.cpram.model.ChecklistPlanModel;
import th.co.gosoft.audit.cpram.model.ChecklistPlanStatusModel;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.PlanDateModel;
import th.co.gosoft.audit.cpram.model.SystemLogModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.SystemSequenceGenerator;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class ChecklistPlanController {
	
	private final static Logger logger = Logger.getLogger(ChecklistPlanController.class);
	
	public DataTableModel<ChecklistPlanModel> dataTableChecklistPlanList(HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel){
		Connection connection = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		AddressDAO addressDAO = null;
		SessionUtils sessionUtils = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			addressDAO = new AddressDAO(connection);
			DataTableModel<ChecklistPlanModel> dataTableModel = new DataTableModel<>();
			sessionUtils = new SessionUtils(httpServletRequest);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			
			StringBuilder sbWhereCause = new StringBuilder();
			for (Column col : dataTablePostParamModel.getColumns()) {
				
				if (!StringUtils.isNullOrEmpty(col.getName())) {					
					if (col.getName().equals("checklistPlanId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.checklist_plan_id").append(" = '").append(col.getSearch().getValue()).append("'  ");

					} else if (col.getName().equals("supplierId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.supplier_id").append(" = '").append(col.getSearch().getValue()).append("' ");

					} else if (col.getName().equals("checklistTypeId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.checklist_type_id").append(" = '").append(col.getSearch().getValue()).append("' ");

					}else if (col.getName().equals("productTypeId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.product_type_id").append(" = '").append(col.getSearch().getValue()).append("' ");

					}else if (col.getName().equals("enable")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.enable").append(" = '").append(col.getSearch().getValue()).append("' ");
						
					}else if (col.getName().equals("checklistPlanStatusId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan_sta.checklist_plan_status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
						
					}else if (col.getName().equals("checklistPlanNo")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.checklist_plan_no").append(" like '%").append(col.getSearch().getValue()).append("%'  ");

					}else if (col.getName().equals("checklistTitle")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.checklist_title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("supplierCompany")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("checklistTypeName")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.checklist_type_name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("checklistScope")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.checklist_scope").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("productTypeName")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.product_type_name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("scoringCriteria")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.scoring_criteria").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("ApproveSupplierRule")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.approve_supplier_rule").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("description")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.description").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}else if (col.getName().equals("checklistPlanStatusName")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan_sta.checklist_plan_status_name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					}		
					
					else if (col.getName().equals("planDate")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("check_plan.plan_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					
					}
					
				}
			}
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
				List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel));
				sbWhereCause.append(" AND ").append("check_plan.supplier_id = '").append(supplierDTOs.get(0).getSupplierId()).append("' ");
			}
			System.out.println(sbWhereCause);
			List<ChecklistPlanDTO> checklistPlanDTOs = checklistPlanDAO.getChecklistPlanList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString());
			int countChecklistPlan = checklistPlanDAO.countChecklistPlanList(sbWhereCause.toString());
			
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countChecklistPlan);
			dataTableModel.setRecordsTotal(countChecklistPlan);
			
			dataTableModel.setData(new ArrayList<>());
			for(ChecklistPlanDTO checklistPlan : checklistPlanDTOs) {
				List<AddressDTO> addressDTOs = addressDAO.getAddressList(String.format(" AND a.address_id = '%s' ", checklistPlan.getLocationId().getAddressId().getAddressId()));
				
				StringBuilder queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND sd.sub_district_id = '").append(addressDTOs.get(0).getSubDistrictId().getSubDistrictId()).append("' ");
				queryWhereClause.append(" AND d.district_id = '").append(addressDTOs.get(0).getDistrictId().getDistrictId()).append("' ");
				queryWhereClause.append(" AND p.province_id = '").append(addressDTOs.get(0).getProvinceId().getProvinceId()).append("' ");
				queryWhereClause.append(" AND d.postcode = '").append(addressDTOs.get(0).getPostcode()).append("' ");
				System.out.println(queryWhereClause);
				List<AddressDTO> addressDetail = addressDAO.getAddressDetailList(queryWhereClause.toString());
				
				checklistPlan.getLocationId().getAddressId().setAddress(addressDTOs.get(0).getAddress());
				checklistPlan.getLocationId().getAddressId().setSubDistrictId(addressDetail.get(0).getSubDistrictId());
				checklistPlan.getLocationId().getAddressId().setDistrictId(addressDetail.get(0).getDistrictId());
				checklistPlan.getLocationId().getAddressId().setProvinceId(addressDetail.get(0).getProvinceId());
				checklistPlan.getLocationId().getAddressId().setPostcode(addressDetail.get(0).getPostcode());
				
				dataTableModel.getData().add(TransformDTO.transChecklistPlanDTO(checklistPlan));
			}
			
			return dataTableModel;
		}catch (Exception e) {
			logger.error("ChecklistPlanController.dataTableChecklistPlanList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public Map<String, Boolean> insertChecklistPlan(HttpServletRequest httpServletRequest, String objChecklisPlan) {		
		Connection connection = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		EvalFormDAO evalFormDAO = null;
		ChecklistDAO checklistDAO = null;
		EvalPlanDAO evalPlanDAO = null;
		QuestionTypeDAO questionTypeDAO = null;
		EvalPlanAnswerDAO evalPlanAnswerDAO = null;
		QuestionAnswerMappingDAO questionAnswerMappingDAO = null;
		AssignPlanDAO assignPlanDAO = null;
		ChecklistPlanEntourageDAO checklistPlanEntourageDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		boolean resultOfProcess = true;
		boolean resultSearchChecklist = true;
		SystemLogDAO systemLogDAO = null;
		int primaryKeyChecklistPlan = 0;
		
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			checklistDAO = new ChecklistDAO(connection);
			evalPlanDAO = new EvalPlanDAO(connection);
			evalFormDAO = new EvalFormDAO(connection);
			questionTypeDAO = new QuestionTypeDAO(connection);
			evalPlanAnswerDAO = new EvalPlanAnswerDAO(connection);
			questionAnswerMappingDAO = new QuestionAnswerMappingDAO(connection);
			assignPlanDAO = new AssignPlanDAO(connection);
			checklistPlanEntourageDAO = new ChecklistPlanEntourageDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			systemLogDAO = new SystemLogDAO(connection);
			
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			ChecklistPlanModel checklistPlanModelRequest = gson.fromJson(objChecklisPlan, ChecklistPlanModel.class);
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND sup_map.id = '").append(checklistPlanModelRequest.getLocationId().getId()).append("' ");
			
			List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierProduceListMappingProductTypeBySupplier(0, supplierProductTypeAddressMappingDAO.countSupplierProduceListMappingProductTypeBySupplier(queryWhereClause.toString()), queryWhereClause.toString());
			for(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO : supplierProductAddressMappingDTOs) {			
				checklistPlanModelRequest.setProductTypeId(TransformDTO.transProductTypeDTO(supplierProductAddressMappingDTO.getProductTypeId().get(0)));
				checklistPlanModelRequest.setProductTypeName(supplierProductAddressMappingDTO.getProductTypeId().get(0).getName());
			}
		
			queryWhereClause.setLength(0);
			queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND c.product_type_id = '").append(checklistPlanModelRequest.getProductTypeId().getProductTypeId()).append("' ");
			queryWhereClause.append(" AND c.effective_date <= now() AND c.expire_date >= now() AND c.enable = 'Y' ");
			List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklistList(0, checklistDAO.countChecklistList(queryWhereClause.toString()), queryWhereClause.toString());
			if(checklistDTOs != null && !checklistDTOs.isEmpty()) {
				
				for(ChecklistDTO checklist : checklistDTOs) {
					checklistPlanModelRequest.setChecklistTitle(checklist.getChecklistTitle());
					checklistPlanModelRequest.setChecklistTypeName(checklist.getChecklistTypeId().getChecklistTypeName());
					checklistPlanModelRequest.setChecklistTypeId(TransformDTO.transChecklistTypeDTO(checklist.getChecklistTypeId()));
					checklistPlanModelRequest.setChecklistScope(checklist.getChecklistScope());
					checklistPlanModelRequest.setScoringCriteria(checklist.getScoringCriteria());
					checklistPlanModelRequest.setApproveSupplierRule(checklist.getApproveSupplierRule());
					checklistPlanModelRequest.setDescription(checklist.getDescription());
					checklistPlanModelRequest.setNoOfCarAcceptDay(NullUtils.cvStr(checklist.getNoOfCarAcceptDay()));
				}
				
				
				checklistPlanModelRequest.setChecklistPlanNo(SystemSequenceGenerator.getInstance().generator(connection, StaticVariableUtils.sequenceKeyChecklistPlan));
				checklistPlanModelRequest.setCreateBy(NullUtils.cvStr(userSessionId));
				checklistPlanModelRequest.setUpdateBy(NullUtils.cvStr(userSessionId));
				
				
				primaryKeyChecklistPlan = checklistPlanDAO.insertChecklistPlan(TransformModel.transChecklistPlanModel(checklistPlanModelRequest));
				if(primaryKeyChecklistPlan > 0) {
					queryWhereClause.setLength(0);
					queryWhereClause = new StringBuilder();
					queryWhereClause.append(" AND ef.checklist_id = '").append(checklistDTOs.get(0).getChecklistId()).append("' ");
					List<EvalFormDTO> evalFormDTOs = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryWhereClause.toString()), queryWhereClause.toString());
					if(evalFormDTOs != null && !evalFormDTOs.isEmpty()) {
						
						for(EvalFormDTO evalForm : evalFormDTOs) {
							
							EvalPlanDTO evalPlanDTO = new EvalPlanDTO();
							evalPlanDTO.setChecklistPlanId(new ChecklistPlanDTO());
							evalPlanDTO.getChecklistPlanId().setChecklistPlanId(primaryKeyChecklistPlan);
							evalPlanDTO.setEvalTypeId(new EvalTypeDTO());
							evalPlanDTO.getEvalTypeId().setEvalTypeId(evalForm.getEvalTypeId().getEvalTypeId());
							evalPlanDTO.setEvalTypeName(evalForm.getEvalTypeId().getEvalTypeName());
							evalPlanDTO.setTitle(evalForm.getTitle());
							evalPlanDTO.setDetail(evalForm.getDetail());
							evalPlanDTO.setEnable(evalForm.getEnable());
							evalPlanDTO.setCreateBy(userSessionId);
							evalPlanDTO.setUpdateBy(userSessionId);
							evalPlanDTO.setRequireAnswer(NullUtils.cvChar(evalForm.getRequireAnwser()));
							evalPlanDTO.setUniqueId(evalForm.getUniqueId());
							if(evalForm.getEvalTypeId().getEvalTypeId() == NullUtils.cvInt(StaticVariableUtils.evalTypeTopic)) {
								evalPlanDTO.setParentId(0);
							}else {
								queryWhereClause.setLength(0);
								queryWhereClause = new StringBuilder();
								queryWhereClause.append(" AND eplan.checklist_plan_id = '").append(primaryKeyChecklistPlan).append("' ");
								queryWhereClause.append(" AND eplan.unique_id = '").append(evalForm.getParentUniqueId()).append("' ");
								List<EvalPlanDTO> evalPlanDTOs = evalPlanDAO.getEvalPlanList(0, evalPlanDAO.countEvalPlanList(queryWhereClause.toString()), queryWhereClause.toString());
								if(evalPlanDTOs != null && !evalPlanDTOs.isEmpty()) {
									evalPlanDTO.setParentId(evalPlanDTOs.get(0).getEvalPlanId());
								}else {
									
									resultOfProcess = false;
									break;
								}
							}	
							
							if(evalForm.getEvalTypeId().getEvalTypeId() == NullUtils.cvInt(StaticVariableUtils.evalTypeQuestion)) {
																
								queryWhereClause.setLength(0);
								queryWhereClause = new StringBuilder();
								queryWhereClause.append(" AND qt.question_type_id = '").append(evalForm.getQuestionTypeId().getQuestionTypeId()).append("' ");
								List<QuestionTypeDTO> questionTypeDTOs = questionTypeDAO.getQuestionTypeList(0, questionTypeDAO.countQuestionTypeList(queryWhereClause.toString()), queryWhereClause.toString());
								
								if(questionTypeDTOs != null && !questionTypeDTOs.isEmpty()) {
									evalPlanDTO.setQuestionTypeId(new QuestionTypeDTO());
									evalPlanDTO.getQuestionTypeId().setQuestionTypeId(questionTypeDTOs.get(0).getQuestionTypeId());
									evalPlanDTO.setQuestionTypeName(questionTypeDTOs.get(0).getName());
									
									int primaryKeyEvalPlan = evalPlanDAO.insertEvalPlan(evalPlanDTO);									
									if(primaryKeyEvalPlan == 0) {
										
										resultOfProcess = false;
										break;
									}									
									if(resultOfProcess) {
										EvalPlanAnswerDTO evalPlanAnswerDTO = new EvalPlanAnswerDTO();
										evalPlanAnswerDTO.setEvalPlanId(new EvalPlanDTO());
										evalPlanAnswerDTO.getEvalPlanId().setEvalPlanId(primaryKeyEvalPlan);
										evalPlanAnswerDTO.setEnable(evalPlanDTO.getEnable());
										evalPlanAnswerDTO.setCreateBy(userSessionId);
										evalPlanAnswerDTO.setUpdateBy(userSessionId);
										evalPlanAnswerDTO.setIsRequireEvidence('N');
										
										List<AnswerDTO> answerDTOs = questionAnswerMappingDAO.getAnswerListByEvalForm(evalForm);
										for(AnswerDTO answer : answerDTOs) {
											evalPlanAnswerDTO.setAnswerId(answer.getAnswerId());
											evalPlanAnswerDTO.setAnswerDetail(answer.getAnswerDetail());
											evalPlanAnswerDTO.setIsCreateCar(answer.getIsCreateCar());
											int rowAffect = evalPlanAnswerDAO.insertEvalPlanAnswer(evalPlanAnswerDTO);
											if(rowAffect == 0) {
												
												resultOfProcess = false;
												break;
											}
										}
										
									}									
								}else {								
									resultOfProcess = false;
								}								
							}else {
								evalPlanDTO.setQuestionTypeId(new QuestionTypeDTO());
								evalPlanDTO.getQuestionTypeId().setQuestionTypeId(0);
								int primaryKeyEvalPlan = evalPlanDAO.insertEvalPlan(evalPlanDTO);								
								if(primaryKeyEvalPlan == 0) {
									resultOfProcess = false;
									break;
								}
							}
						}
					}else {
						resultOfProcess = false;
					}
					
					if(resultOfProcess) {
						
						AssignPlanDTO assignPlanDTO = new AssignPlanDTO();
						assignPlanDTO.setAssignPlanStatusId(new AssignPlanStatusDTO());
						assignPlanDTO.getAssignPlanStatusId().setAssignPlanStatusId(NullUtils.cvInt(checklistPlanModelRequest.getChecklistPlanStatusId().getChecklistPlanStatusId()));
						assignPlanDTO.setChecklistPlanId(new ChecklistPlanDTO());
						assignPlanDTO.getChecklistPlanId().setChecklistPlanId(primaryKeyChecklistPlan);
						assignPlanDTO.setEnable(NullUtils.cvChar(checklistPlanModelRequest.getEnable()));
						assignPlanDTO.setCreateBy(userSessionId);
						assignPlanDTO.setUpdateBy(userSessionId);
						
						for(AssignPlanModel assignPlan : checklistPlanModelRequest.getAssignPlanId()) {
							assignPlanDTO.setAuditorId(TransformModel.transUserModel(assignPlan.getAuditorId()));
							int rowAffective = assignPlanDAO.insertAssignPlan(assignPlanDTO);
							if(rowAffective == 0) {								
								resultOfProcess = false;
								break;
							}								
						}
						
						if(resultOfProcess) {
							ChecklistPlanEntourageDTO checklistPlanEntourageDTO = new ChecklistPlanEntourageDTO();
							checklistPlanEntourageDTO.setChecklistPlanId(primaryKeyChecklistPlan);
							checklistPlanEntourageDTO.setEnable(NullUtils.cvChar(checklistPlanModelRequest.getEnable()));
							checklistPlanEntourageDTO.setCreateBy(userSessionId);
							checklistPlanEntourageDTO.setUpdateBy(userSessionId);
							for(UserModel user : checklistPlanModelRequest.getChecklistPlanEntourageId()) {
								checklistPlanEntourageDTO.setEntourageId(NullUtils.cvInt(user.getUserId()));
								int rowAffective = checklistPlanEntourageDAO.insertChecklistPlanEntourage(checklistPlanEntourageDTO);
								if(rowAffective == 0) {									
									resultOfProcess = false;
									break;
								}
							}
						}			
						
					}					
				}else {
					
					resultOfProcess = false;
				}				
			}else {
				resultOfProcess = false;
				resultSearchChecklist = false;
			}
			
			
			if(resultOfProcess) {
//				SystemLogModel systemLogModel = new SystemLogModel();
//				systemLogModel.setAccess("checklist_plan");
//				systemLogModel.setActivity("create");
//				systemLogModel.setDetail("เพิ่มแผนการเข้าตรวจ");
//				systemLogModel.setCreateBy(NullUtils.cvStr(userSessionId));
//				systemLogModel.setRefId(NullUtils.cvStr(primaryKeyChecklistPlan));
//				systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}			
			Map<String, Boolean> result = new HashMap<>();
			result.put("resultOfProcess", resultOfProcess);
			result.put("resultSearchChecklist", resultSearchChecklist);
			return result;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ChecklistPlanController.insertChecklistPlan() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
			
		}
	}
	
	
	public boolean cancelChecklistPlan(HttpServletRequest httpServletRequest , String checklistPlanObj) {
		Connection connection = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		AssignPlanDAO assignPlanDAO = null;
		AppointDAO appointDAO = null;
		boolean resultProcess = true;
		Gson gson = null;
		SystemLogDAO systemLogDAO = null;
		SessionUtils sessionUtils = null;
		
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			assignPlanDAO = new AssignPlanDAO(connection);
			appointDAO = new AppointDAO(connection);
			systemLogDAO = new SystemLogDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			
			ChecklistPlanModel checklistPlanRequest = gson.fromJson(checklistPlanObj, ChecklistPlanModel.class);
			checklistPlanRequest.setChecklistPlanStatusId(new ChecklistPlanStatusModel());
			checklistPlanRequest.getChecklistPlanStatusId().setChecklistPlanStatusId(NullUtils.cvStr(ConstChecklistPlanStatus.CANCEL));
			int rowAffect = checklistPlanDAO.updateStatusChecklistPlan(TransformModel.transChecklistPlanModel(checklistPlanRequest));
			if(rowAffect == 1) {
				
				AssignPlanDTO assignPlanDTO = new AssignPlanDTO();
				assignPlanDTO.setChecklistPlanId(new ChecklistPlanDTO());
				assignPlanDTO.getChecklistPlanId().setChecklistPlanId(TransformModel.transChecklistPlanModel(checklistPlanRequest).getChecklistPlanId());
				assignPlanDTO.getChecklistPlanId().setChecklistPlanStatusId(new ChecklistPlanStatusDTO());
				assignPlanDTO.getChecklistPlanId().getChecklistPlanStatusId().setChecklistPlanStatusId(ConstChecklistPlanStatus.CANCEL);
								
				assignPlanDTO.setAssignPlanStatusId(new AssignPlanStatusDTO());
				assignPlanDTO.getAssignPlanStatusId().setAssignPlanStatusId(ConstAssignPlanStatus.CANCLE_PLAN);
				rowAffect = assignPlanDAO.updateStateStatusAssignPlan(assignPlanDTO);
				
				if(rowAffect == 1) {
					StringBuilder queryWhereClause = new StringBuilder();
					queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(checklistPlanRequest.getChecklistPlanId()).append("' ");
					List<ChecklistPlanDTO> checklistPlanDTOs = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString());
					if(checklistPlanDTOs.size() > 0 && !checklistPlanDTOs.isEmpty()) {
						queryWhereClause.setLength(0);
						queryWhereClause = new StringBuilder();
						queryWhereClause.append(" AND sup_map.supplier_id = '").append(checklistPlanDTOs.get(0).getSupplierId().getSupplierId()).append("' ");
						queryWhereClause.append(" AND sup_map.product_type_id = '").append(checklistPlanDTOs.get(0).getProductTypeId().getProductTypeId()).append("' ");
						queryWhereClause.append(" AND sup_map.id = '").append(checklistPlanDTOs.get(0).getLocationId().getId()).append("' ");
						queryWhereClause.append(" AND a.appoint_status_id != '").append(ConstAppointStatus.ACCEPT_APPOINTMENT).append("' ");
						queryWhereClause.append(" AND a.appoint_status_id != '").append(ConstAppointStatus.CANCEL_APPOINTMENT).append("' ");
						queryWhereClause.append(" AND a.appoint_status_id != '").append(ConstAppointStatus.COMPLETE_APPOINTMENT).append("' ");
						List<AppointDTO> appointDTOs = appointDAO.getAppointList(0, appointDAO.countAppointList(queryWhereClause.toString()), queryWhereClause.toString());
						
						for(AppointDTO appointDTO : appointDTOs) {
							appointDTO.getAppointStatusId().setAppointStatusId(ConstAppointStatus.CANCEL_APPOINTMENT);
							boolean result = appointDAO.updateAppoint(appointDTO);
							if(!result) {
								resultProcess = false;
								break;
							}
						}
						if(resultProcess) {
							SystemLogModel systemLogModel = new SystemLogModel();
							systemLogModel.setAccess("checklist_plan");
							systemLogModel.setActivity("cancel");
							systemLogModel.setDetail("ยกเลิกแผนการเข้าตรวจ");
							systemLogModel.setCreateBy(NullUtils.cvStr(userSessionId));
							systemLogModel.setRefId(checklistPlanRequest.getChecklistPlanId());
							systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));
							DatabaseUtils.commit(connection);
						}else {
							DatabaseUtils.rollback(connection);
						}
					}else {
						resultProcess = false;
						DatabaseUtils.rollback(connection);
					}
				}else {
					resultProcess = false;
					DatabaseUtils.rollback(connection);
				}
			}else {
				resultProcess = false;
				DatabaseUtils.rollback(connection);
			}
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ChecklistPlanController.cancelChecklistPlan() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);			
		}
	}
	
	public boolean updatePlanDateChecklistPlan(String checklistPlanObj) {
		Connection connection = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		boolean resultProcess = true;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			checklistPlanDAO = new ChecklistPlanDAO(connection);
			gson = new Gson();
			PlanDateModel planDateRequest = gson.fromJson(checklistPlanObj, PlanDateModel.class);
			int rowAffect = checklistPlanDAO.updatePlanDateChecklistPlan(TransformModel.tranPlanDateChecklistPlanDTO(planDateRequest));
			if(rowAffect == 1) {
				resultProcess = true;
			} else {
				resultProcess = false;
				DatabaseUtils.rollback(connection);
			}
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}
			resultProcess = true;
			return resultProcess;
			
		} catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ChecklistPlanController.updatePlanDateCheckListPlan() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}

}
