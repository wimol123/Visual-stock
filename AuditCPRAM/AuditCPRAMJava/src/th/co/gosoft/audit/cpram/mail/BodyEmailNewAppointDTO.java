package th.co.gosoft.audit.cpram.mail;

public class BodyEmailNewAppointDTO {
	
	private String supplierName;
	private String detail;
	private String dateAppoint;
	private String timeAppoint;
	private String location;
	private String url;
	private String emailAdmin;
	private String telephoneAdmin;
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getDateAppoint() {
		return dateAppoint;
	}
	public void setDateAppoint(String dateAppoint) {
		this.dateAppoint = dateAppoint;
	}
	public String getTimeAppoint() {
		return timeAppoint;
	}
	public void setTimeAppoint(String timeAppoint) {
		this.timeAppoint = timeAppoint;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getEmailAdmin() {
		return emailAdmin;
	}
	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}
	public String getTelephoneAdmin() {
		return telephoneAdmin;
	}
	public void setTelephoneAdmin(String telephoneAdmin) {
		this.telephoneAdmin = telephoneAdmin;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

}
