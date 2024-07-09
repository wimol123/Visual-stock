package th.co.gosoft.audit.cpram.dto;

import java.util.List;

public class GradeDTO extends StandardAttributeDTO{
	private Character gradeId;
	private List<ChecklistTypeDTO> checklistTypeId;
	
	public Character getGradeId() {
		return gradeId;
	}
	public void setGradeId(Character gradeId) {
		this.gradeId = gradeId;
	}
	public List<ChecklistTypeDTO> getChecklistTypeId() {
		return checklistTypeId;
	}
	public void setChecklistTypeId(List<ChecklistTypeDTO> checklistTypeId) {
		this.checklistTypeId = checklistTypeId;
	}
}
