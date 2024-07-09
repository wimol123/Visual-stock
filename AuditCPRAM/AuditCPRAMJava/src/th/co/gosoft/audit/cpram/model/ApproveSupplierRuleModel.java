package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class ApproveSupplierRuleModel {
	
	private String grade;
	
	private List<ConditionModel> condition;
	
	private String corrective_action;

	public String getGrade() {
		return grade;
	}

	public List<ConditionModel> getCondition() {
		return condition;
	}

	public String getCorrective_action() {
		return corrective_action;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public void setCondition(List<ConditionModel> condition) {
		this.condition = condition;
	}

	public void setCorrective_action(String corrective_action) {
		this.corrective_action = corrective_action;
	}


}
