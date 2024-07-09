package th.co.gosoft.audit.cpram.dto;

public class QuestionaireDocumentTypeDTO extends StandardAttributeDTO{

	private int questionaireDocumentTypeId;
	
	private String questionaireDocumentTypeName;

	public int getQuestionaireDocumentTypeId() {
		return questionaireDocumentTypeId;
	}

	public void setQuestionaireDocumentTypeId(int questionaireDocumentTypeId) {
		this.questionaireDocumentTypeId = questionaireDocumentTypeId;
	}

	public String getQuestionaireDocumentTypeName() {
		return questionaireDocumentTypeName;
	}

	public void setQuestionaireDocumentTypeName(String questionaireDocumentTypeName) {
		this.questionaireDocumentTypeName = questionaireDocumentTypeName;
	}
	
}
