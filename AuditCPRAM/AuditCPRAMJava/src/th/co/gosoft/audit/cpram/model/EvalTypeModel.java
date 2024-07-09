package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class EvalTypeModel extends StandardAttributeModel{
	private String evalTypeId;
	private String evalTypeName;
	private List<EvalFormModel> evalFormId;
	
	public String getEvalTypeId() {
		return evalTypeId;
	}
	public void setEvalTypeId(String evalTypeId) {
		this.evalTypeId = evalTypeId;
	}
	public String getEvalTypeName() {
		return evalTypeName;
	}
	public void setEvalTypeName(String evalTypeName) {
		this.evalTypeName = evalTypeName;
	}
	public List<EvalFormModel> getEvalFormId() {
		return evalFormId;
	}
	public void setEvalFormId(List<EvalFormModel> evalFormId) {
		this.evalFormId = evalFormId;
	}
}
