package th.co.gosoft.audit.cpram.mail;

public class BodyEmailConfirmFinalAuditResultForApprover {
	private String supplierName;	
	private String urlForConfirm;
	private String emailAdmin;
	private String telephoneAdmin;
	private String grade;
	private String pass;
	private String conclude;
	
	public String getSupplierName() {
		return supplierName;
	}
	public String getUrlForConfirm() {
		return urlForConfirm;
	}
	public String getEmailAdmin() {
		return emailAdmin;
	}
	public String getTelephoneAdmin() {
		return telephoneAdmin;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public void setUrlForConfirm(String urlForConfirm) {
		this.urlForConfirm = urlForConfirm;
	}
	public void setEmailAdmin(String emailAdmin) {
		this.emailAdmin = emailAdmin;
	}
	public void setTelephoneAdmin(String telephoneAdmin) {
		this.telephoneAdmin = telephoneAdmin;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getConclude() {
		return conclude;
	}
	public void setConclude(String conclude) {
		this.conclude = conclude;
	}
	
}
