package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class GradeModel extends StandardAttributeModel{
	private String gradeId;
	private List<ChecklistTypeModel> checklistTypeId;
	public String getGradeId() {
		return gradeId;
	}
	public void setGradeId(String gradeId) {
		this.gradeId = gradeId;
	}
	public List<ChecklistTypeModel> getChecklistTypeId() {
		return checklistTypeId;
	}
	public void setChecklistTypeId(List<ChecklistTypeModel> checklistTypeId) {
		this.checklistTypeId = checklistTypeId;
	}
}
