package th.co.gosoft.audit.cpram.model;

public class InformationDocumentModel extends StandardAttributeModel{
	private String informationDocumentId;
	private InformationModel informationId;
	private String informationDocumentLocation;
	private String informationDocumentType;
	public String getInformationDocumentId() {
		return informationDocumentId;
	}
	public void setInformationDocumentId(String informationDocumentId) {
		this.informationDocumentId = informationDocumentId;
	}
	public InformationModel getInformationId() {
		return informationId;
	}
	public void setInformationId(InformationModel informationId) {
		this.informationId = informationId;
	}
	public String getInformationDocumentLocation() {
		return informationDocumentLocation;
	}
	public void setInformationDocumentLocation(String informationDocumentLocation) {
		this.informationDocumentLocation = informationDocumentLocation;
	}
	public String getInformationDocumentType() {
		return informationDocumentType;
	}
	public void setInformationDocumentType(String informationDocumentType) {
		this.informationDocumentType = informationDocumentType;
	}
}
