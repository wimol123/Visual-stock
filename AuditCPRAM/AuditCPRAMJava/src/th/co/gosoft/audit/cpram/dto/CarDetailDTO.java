package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class CarDetailDTO extends StandardAttributeDTO{
	
	private AuditResultDTO auditResultId;
	private CarDTO carId;
	private String detail; 
	private Character completed; 
	private Date dueDate;
	private Time dueTime;
	private Date completeDate;
	private Time completeTime;
	private List<EvidenceDTO> evidenceId;
	private String remark;
	public AuditResultDTO getAuditResultId() {
		return auditResultId;
	}
	public CarDTO getCarId() {
		return carId;
	}
	public String getDetail() {
		return detail;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Time getDueTime() {
		return dueTime;
	}
	public void setDueTime(Time dueTime) {
		this.dueTime = dueTime;
	}
	public Character getCompleted() {
		return completed;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public Time getCompleteTime() {
		return completeTime;
	}
	public void setAuditResultId(AuditResultDTO auditResultId) {
		this.auditResultId = auditResultId;
	}
	public void setCarId(CarDTO carId) {
		this.carId = carId;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public void setCompleted(Character completed) {
		this.completed = completed;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public void setCompleteTime(Time completeTime) {
		this.completeTime = completeTime;
	}
	public List<EvidenceDTO> getEvidenceId() {
		return evidenceId;
	}
	public void setEvidenceId(List<EvidenceDTO> evidenceId) {
		this.evidenceId = evidenceId;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
