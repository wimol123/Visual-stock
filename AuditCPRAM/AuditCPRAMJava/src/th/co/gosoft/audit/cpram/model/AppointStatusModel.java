package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class AppointStatusModel extends StandardAttributeModel{
	private String appointStatusId;
	private String appointStatusName;
	private String statusColor; 
	private List<AppointModel> appointId;
	private List<AppointHistoryModel> appointHistoryId;
	public String getAppointStatusId() {
		return appointStatusId;
	}
	public void setAppointStatusId(String appointStatusId) {
		this.appointStatusId = appointStatusId;
	}
	public String getAppointStatusName() {
		return appointStatusName;
	}
	public void setAppointStatusName(String appointStatusName) {
		this.appointStatusName = appointStatusName;
	}
	public String getStatusColor() {
		return statusColor;
	}
	public void setStatusColor(String statusColor) {
		this.statusColor = statusColor;
	}
	
	public List<AppointModel> getAppointId() {
		return appointId;
	}
	public void setAppointId(List<AppointModel> appointId) {
		this.appointId = appointId;
	}
	public List<AppointHistoryModel> getAppointHistoryId() {
		return appointHistoryId;
	}
	public void setAppointHistoryId(List<AppointHistoryModel> appointHistoryId) {
		this.appointHistoryId = appointHistoryId;
	}
	
}
