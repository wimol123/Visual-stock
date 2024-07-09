package th.co.gosoft.audit.cpram.controller;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import th.co.gosoft.audit.cpram.calculate.CalculateAuditResult;
import th.co.gosoft.audit.cpram.conts.ConstAuditType;
import th.co.gosoft.audit.cpram.conts.ConstChecklistPlanStatus;
import th.co.gosoft.audit.cpram.dao.AppointDAO;
import th.co.gosoft.audit.cpram.dao.AssignPlanDAO;
import th.co.gosoft.audit.cpram.dao.AuditResultDAO;
import th.co.gosoft.audit.cpram.dao.CarDAO;
import th.co.gosoft.audit.cpram.dao.CarDetailDAO;
import th.co.gosoft.audit.cpram.dao.ChecklistPlanDAO;
import th.co.gosoft.audit.cpram.dao.FinalAuditResultDAO;
import th.co.gosoft.audit.cpram.dao.SupplierDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SystemLogDAO;
import th.co.gosoft.audit.cpram.dto.AssignPlanDTO;
import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.CarDetailDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.FinalAuditResultDTO;
import th.co.gosoft.audit.cpram.dto.FinalAuditResultStatusDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.model.AuditResultModel;
import th.co.gosoft.audit.cpram.model.ChecklistPlanModel;
import th.co.gosoft.audit.cpram.model.ChecklistPlanStatusModel;
import th.co.gosoft.audit.cpram.model.SystemLogModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class AuditResultController {
	
	private final static Logger logger = Logger.getLogger(AuditResultController.class);
	
	public boolean updateAuditResult(HttpServletRequest httpServletRequest ,String listObjAuditResult) {
		Connection connection = null;
		Gson gson = null;
		AuditResultDAO auditResultDAO = null;
		ChecklistPlanDAO checklistPlanDAO = null;
		SupplierDAO supplierDAO = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		CarDAO carDAO = null;
		CarDetailDAO carDetailDAO = null;
		AssignPlanDAO assignPlanDAO = null;
		SessionUtils sessionUtils = null;
		FinalAuditResultDAO finalAuditResultDAO = null;
		boolean result = true;
		int checklistPlanId = 0;
		SystemLogDAO systemLogDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			Type listType = new TypeToken<ArrayList<AuditResultModel>>(){}.getType();
			auditResultDAO = new AuditResultDAO(connection);
			carDetailDAO = new CarDetailDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			systemLogDAO = new SystemLogDAO(connection);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			
			List<AuditResultModel> auditResultRequestList = gson.fromJson(listObjAuditResult, listType);
			
			AuditResultModel resetAuditResult = new AuditResultModel();
			resetAuditResult.setChecklistPlanId(new ChecklistPlanModel());
			resetAuditResult.getChecklistPlanId().setChecklistPlanId(auditResultRequestList.get(0).getChecklistPlanId().getChecklistPlanId());
			
			resetAuditResult.setAccepted("N");
			resetAuditResult.setUpdateBy(userSessionModel.getUserId().trim());
			auditResultDAO.updateAuditResultByChecklistPlan(TransformModel.transAuditResultModel(resetAuditResult));	
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND c_detail.checklist_plan_id = '").append(resetAuditResult.getChecklistPlanId().getChecklistPlanId()).append("' ");
			queryWhereClause.append(" AND c_detail.enable = 'Y' ");
			List<CarDetailDTO> carDetailDTOs = carDetailDAO.getCarDetailList(0, carDetailDAO.countCarDetail(queryWhereClause.toString()), queryWhereClause.toString());
			
			boolean isHasCar = false;
						
			for(AuditResultModel auditResultRequest : auditResultRequestList) {	
				auditResultRequest.setUpdateBy(userSessionModel.getUserId().trim());
				int rowAffect = auditResultDAO.updateAuditResult(TransformModel.transAuditResultModel(auditResultRequest));
				checklistPlanId = NullUtils.cvInt(auditResultRequest.getChecklistPlanId().getChecklistPlanId());
				if(rowAffect != 1) {
					result = false;
					break;
				}
				
				for(CarDetailDTO carDetailDTO : carDetailDTOs) {
					if(carDetailDTO.getAuditResultId().getChecklistPlanId().getChecklistPlanId() == NullUtils.cvInt(auditResultRequest.getChecklistPlanId().getChecklistPlanId()) 
							&& carDetailDTO.getAuditResultId().getAuditorId().getUserId() == NullUtils.cvInt(auditResultRequest.getAuditorId().getUserId())
							&& carDetailDTO.getAuditResultId().getEvalPlanId().getEvalPlanId() == NullUtils.cvInt(auditResultRequest.getEvalPlanId().getEvalPlanId())) {
						isHasCar = true;
						break;
					}
				}
			}
			
			if(result) {	
				SystemLogModel systemLogModel = new SystemLogModel();
				systemLogModel.setAccess("checklist_plan");
				systemLogModel.setActivity("confirm");
				systemLogModel.setDetail("บันทึกการยืนยันผลการเข้าตรวจ");
				systemLogModel.setCreateBy(NullUtils.cvStr(userSessionModel.getUserId()));
				systemLogModel.setRefId(resetAuditResult.getChecklistPlanId().getChecklistPlanId());
				systemLogDAO.insertSystemLog(TransformModel.transSystemLogModel(systemLogModel));	
				DatabaseUtils.commit(connection);			
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			if(result) {
				DatabaseUtils.closeConnection(connection);
				connection = null;
				connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
				supplierDAO = new SupplierDAO(connection);
				checklistPlanDAO = new ChecklistPlanDAO(connection);
				supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
				carDAO = new CarDAO(connection);
				assignPlanDAO = new AssignPlanDAO(connection);
				finalAuditResultDAO = new FinalAuditResultDAO(connection);
				auditResultDAO = new AuditResultDAO(connection);
									
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(checklistPlanId).append("' ");
				ChecklistPlanDTO checklistPlanDTO = checklistPlanDAO.getChecklistPlanList(0, checklistPlanDAO.countChecklistPlanList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
				
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND s.supplier_id = '").append(checklistPlanDTO.getSupplierId().getSupplierId()).append("' ");
				SupplierDTO supplierDTO = supplierDAO.getSupplierList(0, supplierDAO.countSupplierList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
				
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND sup_map.address_id = '").append(checklistPlanDTO.getLocationId().getAddressId().getAddressId()).append("' ");
				SupplierProductAddressMappingDTO supplierProductAddressMappingDTO = supplierProductTypeAddressMappingDAO.getSupplierProduceListMappingProductTypeBySupplier(0, supplierProductTypeAddressMappingDAO.countSupplierProduceListMappingProductTypeBySupplier(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
				
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND check_plan.checklist_plan_id = '").append(checklistPlanDTO.getChecklistPlanId()).append("' ");
				List<CarDTO> carDTOs = carDAO.getCarList(0, carDAO.countCarList(queryWhereClause.toString()), queryWhereClause.toString());
				CarDTO carDTO = new CarDTO();
				
				if(!carDTOs.isEmpty() && carDTOs != null) {					
					for(CarDTO car : carDTOs) {
						if(!isHasCar) {
							carDTO.setCarId(0);
							car.setEnable('N');							
							carDAO.updateCar(car);
						}else {
							carDTO.setCarId(car.getCarId());
						}						
					}
				}else {
					carDTO.setCarId(0);
				}				
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND ad_result.checklist_plan_id = '").append(checklistPlanDTO.getChecklistPlanId()).append("' ");
				queryWhereClause.append(" AND ad_result.accepted = 'Y' ");
				List<AuditResultDTO> auditResultDTOs = auditResultDAO.getAuditResultList(queryWhereClause.toString());
				
				queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND ap.checklist_plan_id = '").append(checklistPlanDTO.getChecklistPlanId()).append("' ");
				List<AssignPlanDTO> assignPlanDTOs = assignPlanDAO.getAssignPlan(queryWhereClause.toString());
				
															
				Map<String, String> resultCalculate = CalculateAuditResult.getInstance().calculateGradeAndConclude(auditResultDTOs, checklistPlanDTO);	
				FinalAuditResultDTO finalAuditResultDTO = new FinalAuditResultDTO();
					
				finalAuditResultDTO.setChecklistPlanId(new ChecklistPlanDTO());
				finalAuditResultDTO.getChecklistPlanId().setChecklistPlanId(checklistPlanDTO.getChecklistPlanId());
				finalAuditResultDTO.setChecklistPlanNo(checklistPlanDTO.getChecklistPlanNo());
					
				finalAuditResultDTO.setSupplierId(new SupplierDTO());
				finalAuditResultDTO.getSupplierId().setSupplierId(checklistPlanDTO.getSupplierId().getSupplierId());
				Map<String, Object> mapSupplierData = new HashMap<>();
				mapSupplierData.put("supplierCompany", supplierDTO.getSupplierCompany());
				mapSupplierData.put("supplierCode", supplierDTO.getSupplierCode());
				finalAuditResultDTO.setSupplierData(gson.toJson(mapSupplierData));
					
				finalAuditResultDTO.setProductTypeId(new ProductTypeDTO());
				finalAuditResultDTO.getProductTypeId().setProductTypeId(checklistPlanDTO.getProductTypeId().getProductTypeId());
				finalAuditResultDTO.setProductTypeName(checklistPlanDTO.getProductTypeName());
					
				finalAuditResultDTO.setLocationId(new SupplierProductAddressMappingDTO());
				finalAuditResultDTO.getLocationId().setId(checklistPlanDTO.getLocationId().getId());
				Map<String, Object> mapLocationSupplier = new HashMap<>();
				mapLocationSupplier.put("locationName", checklistPlanDTO.getLocationId().getLocationName());
				mapLocationSupplier.put("addressId", supplierProductAddressMappingDTO.getAddressId().getAddressId());
				mapLocationSupplier.put("address", supplierProductAddressMappingDTO.getAddressId().getAddress());
				mapLocationSupplier.put("subDistrict", supplierProductAddressMappingDTO.getAddressId().getSubDistrictId().getName());
				mapLocationSupplier.put("district", supplierProductAddressMappingDTO.getAddressId().getDistrictId().getName());
				mapLocationSupplier.put("province", supplierProductAddressMappingDTO.getAddressId().getProvinceId().getName());
				mapLocationSupplier.put("postcode", supplierProductAddressMappingDTO.getAddressId().getPostcode());
				finalAuditResultDTO.setSupplierProductAddressMappingData(gson.toJson(mapLocationSupplier));		

				finalAuditResultDTO.setFinalAuditResultStatusId(new FinalAuditResultStatusDTO());
				finalAuditResultDTO.getFinalAuditResultStatusId().setFinalAuditResultStatusId(1);
				
				List<String> arrayAuditor = new ArrayList<>();
				
				for(AssignPlanDTO assignPlanDTO : assignPlanDTOs) {
					Map<String, Object> mapAuditor = new HashMap<>();
					mapAuditor.put("userId", assignPlanDTO.getAuditorId().getUserId());
					mapAuditor.put("fullname", assignPlanDTO.getAuditorId().getFullname());
					arrayAuditor.add(gson.toJson(mapAuditor));
					
					if(assignPlanDTO.getSignatureOfSupplier() != null) {
						finalAuditResultDTO.setSignatureOfSupplier(assignPlanDTO.getSignatureOfSupplier());
					}
									
				}
				
				finalAuditResultDTO.setAuditor(gson.toJson(arrayAuditor));
				finalAuditResultDTO.setAuditType(ConstAuditType.NONE);
				finalAuditResultDTO.setPlanDate(checklistPlanDTO.getPlanDate());
				finalAuditResultDTO.setPlanTime(checklistPlanDTO.getPlanTime());
				finalAuditResultDTO.setCarId(carDTO);
				finalAuditResultDTO.setCarDetailData(null);
				finalAuditResultDTO.setConclude(NullUtils.cvStr(resultCalculate.get("conclude")));
				finalAuditResultDTO.setGrade(NullUtils.cvChar(resultCalculate.get("grade")));
				finalAuditResultDTO.setPass(null);
				finalAuditResultDTO.setCompleteDate(null);
				finalAuditResultDTO.setCompleteTime(null);
				finalAuditResultDTO.setApprovalName(null);
				finalAuditResultDTO.setEnable('Y');
				finalAuditResultDTO.setCreateBy(NullUtils.cvInt(userSessionModel.getUserId().trim()));
				finalAuditResultDTO.setUpdateBy(NullUtils.cvInt(userSessionModel.getUserId().trim()));
			
				int rowAffect = finalAuditResultDAO.insertFinalAuditResult(finalAuditResultDTO);
				if(rowAffect == 1) {
					result = true;
					
					//Update Appoint Status
					AppointController appointController = new AppointController();
					boolean resultUpdate = appointController.updateAppointAfterFinal(checklistPlanDTO.getAppointId());
					
				}else {
					result = false;
				}
				
				
				if(result) {
					
					ChecklistPlanModel checklistPlanModel = new ChecklistPlanModel();
					checklistPlanModel.setChecklistPlanId(NullUtils.cvStr(checklistPlanId));
					checklistPlanModel.setChecklistPlanStatusId(new ChecklistPlanStatusModel());
					checklistPlanModel.getChecklistPlanStatusId().setChecklistPlanStatusId(NullUtils.cvStr(ConstChecklistPlanStatus.ACCEPT_RESULT_AUDIT));
					rowAffect = checklistPlanDAO.updateStatusChecklistPlan(TransformModel.transChecklistPlanModel(checklistPlanModel));
					
					if(rowAffect == 1) {
						result = true;
					}else {
						result = false;
					}
					
				}					
				
				
			}
			
			if(result) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			return result;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("AuditResultController.updateAuditResult() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	
	

}
