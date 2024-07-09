package th.co.gosoft.audit.cpram.dto;

public class ChecklistPlanStatusDTO extends StandardAttributeDTO {
	private int checklistPlanStatusId;
	private String checklistPlanStatusName;
	private String statusColor;
	public int getChecklistPlanStatusId() {
		return checklistPlanStatusId;
	}
	public void setChecklistPlanStatusId(int checklistPlanStatusId) {
		this.checklistPlanStatusId = checklistPlanStatusId;
	}
	public String getChecklistPlanStatusName() {
		return checklistPlanStatusName;
	}
	public void setChecklistPlanStatusName(String checklistPlanStatusName) {
		this.checklistPlanStatusName = checklistPlanStatusName;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
	
}
