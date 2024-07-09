package th.co.gosoft.audit.cpram.mail;

public class BodyEmailConfirmFinalAuditResultForSupplier {
	private String supplierName;	
	private String urlForConfirm;
	private String emailAdmin;
	private String telephoneAdmin;
	private String dateAccept;	
	private String checklistPlanNo;
	private String auditResult;
	
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
	public String getDateAccept() {
		return dateAccept;
	}
	public void setDateAccept(String dateAccept) {
		this.dateAccept = dateAccept;
	}
	public String getChecklistPlanNo() {
		return checklistPlanNo;
	}
	public void setChecklistPlanNo(String checklistPlanNo) {
		this.checklistPlanNo = checklistPlanNo;
	}
	public String getAuditResult() {
		return auditResult;
	}
	public void setAuditResult(String auditResult) {
		this.auditResult = auditResult;
	}
}
