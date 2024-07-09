package th.co.gosoft.audit.cpram.model;

public class AuditResultModel extends StandardAttributeModel{
	
	private ChecklistPlanModel checklistPlanId;
	private UserModel auditorId; 
	private EvalPlanModel evalPlanId;
	private EvalPlanAnswerModel answerId; 
	private String answerDetail;
	private String transactionDate; 
	private String accepted; 
	private String note;
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
	public EvalPlanModel getEvalPlanId() {
		return evalPlanId;
	}
	public void setEvalPlanId(EvalPlanModel evalPlanId) {
		this.evalPlanId = evalPlanId;
	}
	public EvalPlanAnswerModel getAnswerId() {
		return answerId;
	}
	public void setAnswerId(EvalPlanAnswerModel answerId) {
		this.answerId = answerId;
	}
	public String getAnswerDetail() {
		return answerDetail;
	}
	public void setAnswerDetail(String answerDetail) {
		this.answerDetail = answerDetail;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getAccepted() {
		return accepted;
	}
	public void setAccepted(String accepted) {
		this.accepted = accepted;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	

}
