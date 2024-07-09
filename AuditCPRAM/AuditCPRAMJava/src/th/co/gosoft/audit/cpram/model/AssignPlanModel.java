package th.co.gosoft.audit.cpram.model;

public class AssignPlanModel extends StandardAttributeModel{
	private ChecklistPlanModel checklistPlanId;
	private UserModel auditorId;
	private AssignPlanStatusModel assignPlanStatusId;
	private byte[] signatureOfSupplier;
	
	public ChecklistPlanModel getChecklistPlanId() {
		return checklistPlanId;
	}
	public void setChecklistPlanId(ChecklistPlanModel checklistPlanId) {
		this.checklistPlanId = checklistPlanId;
	}
	public UserModel getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(UserModel auditorId) {
		this.auditorId = auditorId;
	}
	public AssignPlanStatusModel getAssignPlanStatusId() {
		return assignPlanStatusId;
	}
	public void setAssignPlanStatusId(AssignPlanStatusModel assignPlanStatusId) {
		this.assignPlanStatusId = assignPlanStatusId;
	}
	public byte[] getSignatureOfSupplier() {
		return signatureOfSupplier;
	}
	public void setSignatureOfSupplier(byte[] signatureOfSupplier) {
		this.signatureOfSupplier = signatureOfSupplier;
	}
	
}
