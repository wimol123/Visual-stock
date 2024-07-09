package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;

public class AuditResultDTO extends StandardAttributeDTO{
	private ChecklistPlanDTO checklistPlanId;
	private UserDTO auditorId; 
	private EvalPlanDTO evalPlanId;
	private EvalPlanAnswerDTO answerId; 
	private String answerDetail;
	private Date transactionDate; 
	private Time transactionTime; 
	private Character accepted; 
	private String note;
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
	public EvalPlanDTO getEvalPlanId() {
		return evalPlanId;
	}
	public void setEvalPlanId(EvalPlanDTO evalPlanId) {
		this.evalPlanId = evalPlanId;
	}
	public EvalPlanAnswerDTO getAnswerId() {
		return answerId;
	}
	public void setAnswerId(EvalPlanAnswerDTO answerId) {
		this.answerId = answerId;
	}
	public String getAnswerDetail() {
		return answerDetail;
	}
	public void setAnswerDetail(String answerDetail) {
		this.answerDetail = answerDetail;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public Time getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Time transactionTime) {
		this.transactionTime = transactionTime;
	}
	public Character getAccepted() {
		return accepted;
	}
	public void setAccepted(Character accepted) {
		this.accepted = accepted;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
