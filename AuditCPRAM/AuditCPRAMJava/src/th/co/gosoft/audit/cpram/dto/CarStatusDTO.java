package th.co.gosoft.audit.cpram.dto;

public class CarStatusDTO extends StandardAttributeDTO{
	
	private int carStatusId;
	private String carStatusName; 
	private String statusColor;
	public int getCarStatusId() {
		return carStatusId;
	}
	public String getCarStatusName() {
		return carStatusName;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setCarStatusId(int carStatusId) {
		this.carStatusId = carStatusId;
	}
	public void setCarStatusName(String carStatusName) {
		this.carStatusName = carStatusName;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}

}
