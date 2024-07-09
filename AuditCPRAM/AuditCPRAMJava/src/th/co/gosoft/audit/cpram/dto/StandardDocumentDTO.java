package th.co.gosoft.audit.cpram.dto;

public class StandardDocumentDTO extends StandardAttributeDTO {

	private int standardDocumentId;	
	private String standardDocumentName;

	public int getStandardDocumentId() {
		return standardDocumentId;
	}

	public String getStandardDocumentName() {
		return standardDocumentName;
	}

	public void setStandardDocumentId(int standardDocumentId) {
		this.standardDocumentId = standardDocumentId;
	}

	public void setStandardDocumentName(String standardDocumentName) {
		this.standardDocumentName = standardDocumentName;
	}
	
}
