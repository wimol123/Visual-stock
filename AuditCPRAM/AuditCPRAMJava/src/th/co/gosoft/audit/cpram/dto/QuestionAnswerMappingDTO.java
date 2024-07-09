package th.co.gosoft.audit.cpram.dto;

public class QuestionAnswerMappingDTO extends StandardAttributeDTO{
	
	private int evalFormId;
	private int answerId;
	public int getEvalFormId() {
		return evalFormId;
	}
	public void setEvalFormId(int evalFormId) {
		this.evalFormId = evalFormId;
	}
	public int getAnswerId() {
		return answerId;
	}
	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}
	

}
