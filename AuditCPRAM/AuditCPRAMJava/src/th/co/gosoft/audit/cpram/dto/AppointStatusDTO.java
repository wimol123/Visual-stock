package th.co.gosoft.audit.cpram.dto;

import java.util.List;

public class AppointStatusDTO  extends StandardAttributeDTO{
	private int appointStatusId;
	private String appointStatusName;
	private String statusColor; 
	private List<AppointDTO> appointId;
	private List<AppointHistoryDTO> appointHistoryId;
	public int getAppointStatusId() {
		return appointStatusId;
	}
	public void setAppointStatusId(int appointStatusId) {
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
	public List<AppointDTO> getAppointId() {
		return appointId;
	}
	public void setAppointId(List<AppointDTO> appointId) {
		this.appointId = appointId;
	}
	public List<AppointHistoryDTO> getAppointHistoryId() {
		return appointHistoryId;
	}
	public void setAppointHistoryId(List<AppointHistoryDTO> appointHistoryId) {
		this.appointHistoryId = appointHistoryId;
	}
	
}
