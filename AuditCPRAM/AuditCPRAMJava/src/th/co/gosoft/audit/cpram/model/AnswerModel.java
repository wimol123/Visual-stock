package th.co.gosoft.audit.cpram.model;

import java.util.List;


public class AnswerModel extends StandardAttributeModel{
	private String answerId; 
	private String answerDetail;
	private String isCreateCar;
	private List<EvalFormModel> evalFormId;
	private List<QuestionTypeModel> questionTypeId;
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	
	public String getAnswerDetail() {
		return answerDetail;
	}
	public void setAnswerDetail(String answerDetail) {
		this.answerDetail = answerDetail;
	}
	public String getIsCreateCar() {
		return isCreateCar;
	}
	public void setIsCreateCar(String isCreateCar) {
		this.isCreateCar = isCreateCar;
	}
	public List<EvalFormModel> getEvalFormId() {
		return evalFormId;
	}
	public void setEvalFormId(List<EvalFormModel> evalFormId) {
		this.evalFormId = evalFormId;
	}
	public List<QuestionTypeModel> getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(List<QuestionTypeModel> questionTypeId) {
		this.questionTypeId = questionTypeId;
	}
}
