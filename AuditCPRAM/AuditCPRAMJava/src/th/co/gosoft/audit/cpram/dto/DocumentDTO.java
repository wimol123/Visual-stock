package th.co.gosoft.audit.cpram.dto;

public class DocumentDTO extends StandardAttributeDTO {

	private int DocumentId;	
	private String DocumentName;
	private int Seq;
	
	
	public int getSeq() {
		return Seq;
	}
	
	public int getDocumentId() {
		return DocumentId;
	}

	public String getDocumentName() {
		return DocumentName;
	}

	public void setDocumentId(int DocumentId) {
		this.DocumentId = DocumentId;
	}

	public void setDocumentName(String DocumentName) {
		this.DocumentName = DocumentName;
	}
	
	public void setSeq(int seq) {
		this.Seq = seq;
	}
	
	
}
