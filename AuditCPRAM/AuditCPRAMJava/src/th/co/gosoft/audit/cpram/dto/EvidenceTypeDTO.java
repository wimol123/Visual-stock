package th.co.gosoft.audit.cpram.dto;

public class EvidenceTypeDTO extends StandardAttributeDTO{
	
	private int evidenceTypeId;
	private String evidenceTypeName;
	
	public int getEvidenceTypeId() {
		return evidenceTypeId;
	}
	public String getEvidenceTypeName() {
		return evidenceTypeName;
	}
	public void setEvidenceTypeId(int evidenceTypeId) {
		this.evidenceTypeId = evidenceTypeId;
	}
	public void setEvidenceTypeName(String evidenceTypeName) {
		this.evidenceTypeName = evidenceTypeName;
	}

}
