package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class EvalPlanModel extends StandardAttributeModel{
	private String evalPlanId;
	private ChecklistPlanModel checklistPlanId; 
	private String parentId;
	private EvalTypeModel evalTypeId;
	private String evalTypeName;
	private QuestionTypeModel questionTypeId;
	private String questionTypeName;
	private String requireAnswer;
	private String title;
	private String detail;
	private List<AuditResultModel> auditResultId;
	public String getEvalPlanId() {
		return evalPlanId;
	}
	public void setEvalPlanId(String evalPlanId) {
		this.evalPlanId = evalPlanId;
	}
	public ChecklistPlanModel getChecklistPlanId() {
		return checklistPlanId;
	}
	public void setChecklistPlanId(ChecklistPlanModel checklistPlanId) {
		this.checklistPlanId = checklistPlanId;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public EvalTypeModel getEvalTypeId() {
		return evalTypeId;
	}
	public void setEvalTypeId(EvalTypeModel evalTypeId) {
		this.evalTypeId = evalTypeId;
	}
	public String getEvalTypeName() {
		return evalTypeName;
	}
	public void setEvalTypeName(String evalTypeName) {
		this.evalTypeName = evalTypeName;
	}
	public QuestionTypeModel getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(QuestionTypeModel questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
	public String getQuestionTypeName() {
		return questionTypeName;
	}
	public void setQuestionTypeName(String questionTypeName) {
		this.questionTypeName = questionTypeName;
	}
	public String getRequireAnswer() {
		return requireAnswer;
	}
	public void setRequireAnswer(String requireAnswer) {
		this.requireAnswer = requireAnswer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public List<AuditResultModel> getAuditResultId() {
		return auditResultId;
	}
	public void setAuditResultId(List<AuditResultModel> auditResultId) {
		this.auditResultId = auditResultId;
	}
	
}
