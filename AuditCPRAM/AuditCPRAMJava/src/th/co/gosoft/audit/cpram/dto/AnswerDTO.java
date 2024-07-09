package th.co.gosoft.audit.cpram.dto;

import java.util.List;

public class AnswerDTO extends StandardAttributeDTO{	
	private int answerId; 
	private String answerDetail;
	private Character isCreateCar;
	private List<EvalFormDTO> evalFormId;
	private List<QuestionTypeDTO> questionTypeId;
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}		
	public String getAnswerDetail() {
		return answerDetail;
	}
	public void setAnswerDetail(String answerDetail) {
		this.answerDetail = answerDetail;
	}	
	
	public Character getIsCreateCar() {
		return isCreateCar;
	}
	public void setIsCreateCar(Character isCreateCar) {
		this.isCreateCar = isCreateCar;
	}
	public List<EvalFormDTO> getEvalFormId() {
		return evalFormId;
	}
	public void setEvalFormId(List<EvalFormDTO> evalFormId) {
		this.evalFormId = evalFormId;
	}
	public List<QuestionTypeDTO> getQuestionTypeId() {
		return questionTypeId;
	}
	public void setQuestionTypeId(List<QuestionTypeDTO> questionTypeId) {
		this.questionTypeId = questionTypeId;
	}

}
