package th.co.gosoft.audit.cpram.dto;

public class AssignPlanStatusDTO extends StandardAttributeDTO {
	private int assignPlanStatusId;
	private String assignPlanStatusName;
	private String statusColor;
	public int getAssignPlanStatusId() {
		return assignPlanStatusId;
	}
	public void setAssignPlanStatusId(int assignPlanStatusId) {
		this.assignPlanStatusId = assignPlanStatusId;
	}
	public String getAssignPlanStatusName() {
		return assignPlanStatusName;
	}
	public void setAssignPlanStatusName(String assignPlanStatusName) {
		this.assignPlanStatusName = assignPlanStatusName;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
}
