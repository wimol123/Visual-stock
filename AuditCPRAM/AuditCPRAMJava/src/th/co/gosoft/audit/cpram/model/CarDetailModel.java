package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class CarDetailModel extends StandardAttributeModel {
	private AuditResultModel auditResultId;
	private CarModel carId;
	private String detail; 
	private String completed; 
	private String dueDate;
	private String completeDate;
	private List<EvidenceModel> evidenceId;
	private String remark;
	private String canEditProblem;
	public AuditResultModel getAuditResultId() {
		return auditResultId;
	}
	public CarModel getCarId() {
		return carId;
	}
	public String getDetail() {
		return detail;
	}
	public String getCompleted() {
		return completed;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public void setAuditResultId(AuditResultModel auditResultId) {
		this.auditResultId = auditResultId;
	}
	public void setCarId(CarModel carId) {
		this.carId = carId;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	public List<EvidenceModel> getEvidenceId() {
		return evidenceId;
	}
	public void setEvidenceId(List<EvidenceModel> evidenceId) {
		this.evidenceId = evidenceId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCanEditProblem() {
		return canEditProblem;
	}
	public void setCanEditProblem(String canEditProblem) {
		this.canEditProblem = canEditProblem;
	}
	
}
