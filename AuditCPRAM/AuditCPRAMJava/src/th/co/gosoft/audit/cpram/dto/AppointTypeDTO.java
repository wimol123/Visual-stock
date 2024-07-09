package th.co.gosoft.audit.cpram.dto;

import java.util.List;

public class AppointTypeDTO extends StandardAttributeDTO {
	private int appointTypeId; 
	private String name; 
	private List<AppointDTO> appointId;
	public int getAppointTypeId() {
		return appointTypeId;
	}
	public void setAppointTypeId(int appointTypeId) {
		this.appointTypeId = appointTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AppointDTO> getAppointId() {
		return appointId;
	}
	public void setAppointId(List<AppointDTO> appointId) {
		this.appointId = appointId;
	}
}
