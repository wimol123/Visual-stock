package th.co.gosoft.audit.cpram.dto;

public class PoHistoryDTO {

	private int poHistoryId;
	private int poId;
	private int poStepId;
	private String description;
	private String note;
	private int createBy;
	private String createDate;
	
	private String createByName;

	public int getPoHistoryId() {
		return poHistoryId;
	}

	public void setPoHistoryId(int poHistoryId) {
		this.poHistoryId = poHistoryId;
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public int getPoStepId() {
		return poStepId;
	}

	public void setPoStepId(int poStepId) {
		this.poStepId = poStepId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateByName() {
		return createByName;
	}

	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}
	
}
