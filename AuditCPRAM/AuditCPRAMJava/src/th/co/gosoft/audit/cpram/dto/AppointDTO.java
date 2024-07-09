package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class AppointDTO extends StandardAttributeDTO {
	private int appointId;
	private AppointTypeDTO appointTypeId;
	private UserDTO appointCreateBy;
	private SupplierDTO supplierId; 
	private Date appointDate;
	private Time appointTime;
	private String title;
	private String detail; 
	private AppointStatusDTO appointStatusId; 	
	private List<UserDTO> auditorId;
	private List<AppointHistoryDTO> appointHistoryId;
	private SupplierProductAddressMappingDTO locationId;
	private List<UserDTO> entourageId;
	
	public List<AppointHistoryDTO> getAppointHistoryId() {
		return appointHistoryId;
	}
	public void setAppointHistoryId(List<AppointHistoryDTO> appointHistoryId) {
		this.appointHistoryId = appointHistoryId;
	}
	public void setAppointStatusId(AppointStatusDTO appointStatusId) {
		this.appointStatusId = appointStatusId;
	}
	public int getAppointId() {
		return appointId;
	}
	public void setAppointId(int appointId) {
		this.appointId = appointId;
	}
	public AppointTypeDTO getAppointTypeId() {
		return appointTypeId;
	}
	public void setAppointTypeId(AppointTypeDTO appointTypeId) {
		this.appointTypeId = appointTypeId;
	}
	public UserDTO getAppointCreateBy() {
		return appointCreateBy;
	}
	public void setAppointCreateBy(UserDTO appointCreateBy) {
		this.appointCreateBy = appointCreateBy;
	}
	public SupplierDTO getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(SupplierDTO supplierId) {
		this.supplierId = supplierId;
	}
	public Date getAppointDate() {
		return appointDate;
	}
	public void setAppointDate(Date appointDate) {
		this.appointDate = appointDate;
	}
	public Time getAppointTime() {
		return appointTime;
	}
	public void setAppointTime(Time appointTime) {
		this.appointTime = appointTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public List<UserDTO> getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(List<UserDTO> auditorId) {
		this.auditorId = auditorId;
	}
	public AppointStatusDTO getAppointStatusId() {
		return appointStatusId;
	}
	public SupplierProductAddressMappingDTO getLocationId() {
		return locationId;
	}
	public void setLocationId(SupplierProductAddressMappingDTO locationId) {
		this.locationId = locationId;
	}
	public List<UserDTO> getEntourageId() {
		return entourageId;
	}
	public void setEntourageId(List<UserDTO> entourageId) {
		this.entourageId = entourageId;
	}
	
}
