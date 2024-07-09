package th.co.gosoft.audit.cpram.model;

public class CarStatusModel extends StandardAttributeModel{
	private String carStatusId;
	private String carStatusName; 
	private String statusColor;
	public String getCarStatusId() {
		return carStatusId;
	}
	public String getCarStatusName() {
		return carStatusName;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setCarStatusId(String carStatusId) {
		this.carStatusId = carStatusId;
	}
	public void setCarStatusName(String carStatusName) {
		this.carStatusName = carStatusName;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
	
	
}	
