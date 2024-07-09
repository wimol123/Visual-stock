package th.co.gosoft.audit.cpram.dto;

public class PoLogInterfaceDTO {
	
	private int poLogInterfaceId;
	private String type;
	private String result;
	private String fileName;
	private int createBy;
	private String createDate;
	
	private String createByName;

	public int getPoLogInterfaceId() {
		return poLogInterfaceId;
	}

	public void setPoLogInterfaceId(int poLogInterfaceId) {
		this.poLogInterfaceId = poLogInterfaceId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
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
