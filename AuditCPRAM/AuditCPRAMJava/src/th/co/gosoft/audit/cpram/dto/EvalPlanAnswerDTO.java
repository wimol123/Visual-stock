package th.co.gosoft.audit.cpram.dto;

public class EvalPlanAnswerDTO extends StandardAttributeDTO {
	
	private EvalPlanDTO evalPlanId;
	private int answerId;
	private String answerDetail;
	private Character isCreateCar;
	private Character isRequireEvidence;
	
	public EvalPlanDTO getEvalPlanId() {
		return evalPlanId;
	}
	public void setEvalPlanId(EvalPlanDTO evalPlanId) {
		this.evalPlanId = evalPlanId;
	}
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
	public Character getIsRequireEvidence() {
		return isRequireEvidence;
	}
	public void setIsRequireEvidence(Character isRequireEvidence) {
		this.isRequireEvidence = isRequireEvidence;
	}

}
