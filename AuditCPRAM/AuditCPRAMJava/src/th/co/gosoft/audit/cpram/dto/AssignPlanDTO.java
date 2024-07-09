package th.co.gosoft.audit.cpram.dto;

public class AssignPlanDTO extends StandardAttributeDTO{
	private ChecklistPlanDTO checklistPlanId;
	private UserDTO auditorId;
	private AssignPlanStatusDTO assignPlanStatusId;
	private byte[] signatureOfSupplier;
	
	public ChecklistPlanDTO getChecklistPlanId() {
		return checklistPlanId;
	}
	public void setChecklistPlanId(ChecklistPlanDTO checklistPlanId) {
		this.checklistPlanId = checklistPlanId;
	}
	public UserDTO getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(UserDTO auditorId) {
		this.auditorId = auditorId;
	}
	public AssignPlanStatusDTO getAssignPlanStatusId() {
		return assignPlanStatusId;
	}
	public void setAssignPlanStatusId(AssignPlanStatusDTO assignPlanStatusId) {
		this.assignPlanStatusId = assignPlanStatusId;
	}
	public byte[] getSignatureOfSupplier() {
		return signatureOfSupplier;
	}
	public void setSignatureOfSupplier(byte[] signatureOfSupplier) {
		this.signatureOfSupplier = signatureOfSupplier;
	}
	
}
