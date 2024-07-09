package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class AppointModel extends StandardAttributeModel {
	private String appointId;
	private AppointTypeModel appointTypeId;
	private UserModel appointCreateBy;
	private SupplierModel supplierId; 
	private String appointDate;
	private String title;
	private String detail; 
	private AppointStatusModel appointStatusId;
	private List<UserModel> auditorId;
	private List<AppointHistoryModel> appointHistoryId;
	private SupplierProductAddressMappingModel locationId;
	private List<UserModel> entourageId;
	
	public String getAppointId() {
		return appointId;
	}
	public void setAppointId(String appointId) {
		this.appointId = appointId;
	}
	public AppointTypeModel getAppointTypeId() {
		return appointTypeId;
	}
	public void setAppointTypeId(AppointTypeModel appointTypeId) {
		this.appointTypeId = appointTypeId;
	}
	public UserModel getAppointCreateBy() {
		return appointCreateBy;
	}
	public void setAppointCreateBy(UserModel appointCreateBy) {
		this.appointCreateBy = appointCreateBy;
	}
	public SupplierModel getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(SupplierModel supplierId) {
		this.supplierId = supplierId;
	}
	public String getAppointDate() {
		return appointDate;
	}
	public void setAppointDate(String appointDate) {
		this.appointDate = appointDate;
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
	public AppointStatusModel getAppointStatusId() {
		return appointStatusId;
	}
	public void setAppointStatusId(AppointStatusModel appointStatusId) {
		this.appointStatusId = appointStatusId;
	}
	
	public List<UserModel> getAuditorId() {
		return auditorId;
	}
	public void setAuditorId(List<UserModel> auditorId) {
		this.auditorId = auditorId;
	}
	public List<AppointHistoryModel> getAppointHistoryId() {
		return appointHistoryId;
	}
	public void setAppointHistoryId(List<AppointHistoryModel> appointHistoryId) {
		this.appointHistoryId = appointHistoryId;
	}
	public SupplierProductAddressMappingModel getLocationId() {
		return locationId;
	}
	public void setLocationId(SupplierProductAddressMappingModel locationId) {
		this.locationId = locationId;
	}
	public List<UserModel> getEntourageId() {
		return entourageId;
	}
	public void setEntourageId(List<UserModel> entourageId) {
		this.entourageId = entourageId;
	}
	
}
