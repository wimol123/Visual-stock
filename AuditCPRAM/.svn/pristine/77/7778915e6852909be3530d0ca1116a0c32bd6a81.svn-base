package th.co.gosoft.audit.cpram.calculate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import th.co.gosoft.audit.cpram.conts.ConstAnswer;
import th.co.gosoft.audit.cpram.conts.ConstOperation;
import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.model.ApproveSupplierRuleModel;
import th.co.gosoft.audit.cpram.model.ConditionModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class CalculateAuditResult {
	private final static Logger logger = Logger.getLogger(CalculateAuditResult.class);
	
	private CalculateAuditResult() {}
	
	private static CalculateAuditResult calculateAuditResultInstance = null;
	private static Object mutex = new Object();
	
	public static CalculateAuditResult getInstance() {
		synchronized (mutex) {
			if(calculateAuditResultInstance == null) {
				synchronized (mutex) {
					if(calculateAuditResultInstance == null) {
						calculateAuditResultInstance = new CalculateAuditResult();
					}
				}
			}
		}
		
		return calculateAuditResultInstance;
	}
	
	public Map<String, String> calculateGradeAndConclude(List<AuditResultDTO> auditResultDTOs, ChecklistPlanDTO checklistPlanDTO) {
		Map<String, String> resultCalculate = null;
		Gson gson = null;
		try {
			resultCalculate = new HashMap<>();
			gson = new Gson();
			
			Map<String, Integer> resultConclude = calculateConclude(auditResultDTOs);		
			String grade = calculateGrade(resultConclude, checklistPlanDTO);
			
			resultCalculate.put("conclude", gson.toJson(resultConclude));
			resultCalculate.put("grade", grade);
			
			return resultCalculate;
		}catch (Exception e) {
			logger.error("CalculateAuditResult.calculateGradeAndConclude() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	
	private Map<String, Integer> calculateConclude(List<AuditResultDTO> auditResultDTOs) {
		try {
			
			Integer critical = 0, major = 0, minor = 0, observe = 0; 			
			for(AuditResultDTO auditResultDTO : auditResultDTOs) {
				if(auditResultDTO.getAnswerId().getAnswerId() == ConstAnswer.CRITICAL) {
					critical += 1;
				}else if(auditResultDTO.getAnswerId().getAnswerId() == ConstAnswer.MAJOR) {
					major += 1;
				}else if(auditResultDTO.getAnswerId().getAnswerId() == ConstAnswer.MINOR) {
					minor += 1;
				}else if(auditResultDTO.getAnswerId().getAnswerId() == ConstAnswer.OBSERVE) {
					observe += 1;
				}
			}
			
			Map<String, Integer> resultConclude = new HashMap<>();
			resultConclude.put("Critical", critical);
			resultConclude.put("Major", major);
			resultConclude.put("Minor", minor);
			resultConclude.put("Observe", observe);
			
			return resultConclude;			
		}catch (Exception e) {
			logger.error("CalculateAuditResult.calculateConclude() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	private String calculateGrade(Map<String, Integer> resultConclude, ChecklistPlanDTO checklistPlanDTO) {
		String grade = null;
		Gson gson = null;
		try {						
			gson = new Gson();
			String approveRule = checklistPlanDTO.getApproveSupplierRule();
			Type listType = new TypeToken<ArrayList<ApproveSupplierRuleModel>>(){}.getType();
			
			List<ApproveSupplierRuleModel> approveSupplierRuleModels = gson.fromJson(approveRule, listType);
			Collections.sort(approveSupplierRuleModels, new Comparator<ApproveSupplierRuleModel>() {
				@Override
				public int compare(ApproveSupplierRuleModel arg0, ApproveSupplierRuleModel arg1) {
					return arg0.getGrade().trim().compareTo(arg1.getGrade().trim());
				}
				
			});
			
			for(ApproveSupplierRuleModel approveSupplierRule : approveSupplierRuleModels) {
				boolean resultCompair = false;
				Map<String, Boolean> mapHasCondition = checkHasCondition(approveSupplierRule.getCondition());
				
				if(!mapHasCondition.get("hasCritical")) {
					resultCompair = selectorCompairOperation(ConstOperation.EQUAL, NullUtils.cvStr(resultConclude.get("Critical")), NullUtils.cvStr(0));
					if(!resultCompair)
						continue;
				}if(!mapHasCondition.get("hasMajor")) {
					resultCompair = selectorCompairOperation(ConstOperation.EQUAL, NullUtils.cvStr(resultConclude.get("Major")), NullUtils.cvStr(0));
					if(!resultCompair)
						continue;
				}if(!mapHasCondition.get("hasMinor")) {
					resultCompair = selectorCompairOperation(ConstOperation.EQUAL, NullUtils.cvStr(resultConclude.get("Minor")), NullUtils.cvStr(0));
					if(!resultCompair)
						continue;
				}				
				
				
				
				for(ConditionModel conditionModel : approveSupplierRule.getCondition()) {					
					if(conditionModel.getResult_type().equals("1")) {
						resultCompair = selectorCompairOperation(conditionModel.getOp(), NullUtils.cvStr(resultConclude.get("Critical")), conditionModel.getValue());
					}else if(conditionModel.getResult_type().equals("2")) {
						resultCompair = selectorCompairOperation(conditionModel.getOp(), NullUtils.cvStr(resultConclude.get("Major")), conditionModel.getValue());
					}else if(conditionModel.getResult_type().equals("3")) {
						resultCompair = selectorCompairOperation(conditionModel.getOp(), NullUtils.cvStr(resultConclude.get("Minor")), conditionModel.getValue());
					}
					
					if(!resultCompair)
						break;
				}
				
				if(resultCompair) {
					grade = approveSupplierRule.getGrade().trim();
					break;
				}
				
				
			}
			
			return grade;
		}catch (Exception e) {
			logger.error("CalculateAuditResult.calculateGrade() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	
	
	private boolean selectorCompairOperation(String operation, String result, String conditionValue) {
		boolean resultCompair = false;
		
		switch (operation) {
		case ConstOperation.BETWEEN:
			String[] conditionValueArray = conditionValue.split("-");
			resultCompair = CompareCondition.compairBetween(NullUtils.cvInt(conditionValueArray[0].trim()), NullUtils.cvInt(conditionValueArray[1].trim()), NullUtils.cvInt(result.trim()));
			break;
		case ConstOperation.EQUAL:
			resultCompair = CompareCondition.compairEqual(NullUtils.cvInt(result.trim()), NullUtils.cvInt(conditionValue.trim()));
			break;
		case ConstOperation.LESS_THAN:
			resultCompair = CompareCondition.compairLessThan(NullUtils.cvInt(result.trim()), NullUtils.cvInt(conditionValue.trim()));
			break;
		case ConstOperation.LESS_THAN_AND_EQUAL:
			resultCompair = CompareCondition.compairLessThanAndEqual(NullUtils.cvInt(result.trim()), NullUtils.cvInt(conditionValue.trim()));
			break;
		case ConstOperation.MORE_THAN :
			resultCompair = CompareCondition.compairMoreThan(NullUtils.cvInt(result.trim()), NullUtils.cvInt(conditionValue.trim()));
			break;
			
		case ConstOperation.MORE_THAN_AND_EQUAL : 
			resultCompair = CompareCondition.compairMoreThanAndEqual(NullUtils.cvInt(result.trim()), NullUtils.cvInt(conditionValue.trim()));
			break;
			
		default:
			break;
		}
		
		return resultCompair;
	}
	
	
	private Map<String, Boolean> checkHasCondition(List<ConditionModel> conditionModels) {
		Boolean hasCritical = false, hasMajor = false, hasMinor = false;
		Map<String, Boolean> mapCheckConditon = new HashMap<>();
		for(ConditionModel condition : conditionModels) {
			if(condition.getResult_type().equals("1")) {
				hasCritical = true;
			}else if(condition.getResult_type().equals("2")) {
				hasMajor = true;
			}else if(condition.getResult_type().equals("3")) {
				hasMinor = true;
			}
		}
		mapCheckConditon.put("hasCritical", hasCritical);
		mapCheckConditon.put("hasMajor", hasMajor);
		mapCheckConditon.put("hasMinor", hasMinor);
		return mapCheckConditon;
	}
	
	
}



class CompareCondition{
	
	public static boolean compairBetween(Integer start, Integer end, Integer result) {
		return (result >= start && result <= end) ? true : false;
	}
	
	public static boolean compairLessThan(Integer result, Integer conditionValue) {
		return (result < conditionValue) ? true : false;
	}
	
	public static boolean compairLessThanAndEqual(Integer result, Integer conditionValue) {
		return (result <= conditionValue) ? true : false;
	}
	
	public static boolean compairMoreThan(Integer result, Integer conditionValue) {
		return (result > conditionValue) ? true : false;
	}
	
	public static boolean compairMoreThanAndEqual(Integer result, Integer conditionValue) {
		return (result >= conditionValue) ? true : false;
	}
	
	public static boolean compairEqual(Integer result, Integer conditionValue) {
		return (result == conditionValue) ? true : false;
	}
}

