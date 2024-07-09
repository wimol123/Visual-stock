package th.co.gosoft.audit.cpram.mail;

public class BodyEmailDTO {
	private String fullnameReceiver;
	private String usernameReceiver;
	private String passwordReceiver;
	private String url;
	private String emailAdmin;
	private String telephoneAdmin;
	public String getFullnameReceiver() {
		return fullnameReceiver;
	}
	public void setFullnameReceiver(String fullnameReceiver) {
		this.fullnameReceiver = fullnameReceiver;
	}
	public String getUsernameReceiver() {
		return usernameReceiver;
	}
	public void setUsernameReceiver(String usernameReceiver) {
		this.usernameReceiver = usernameReceiver;
	}
	public String getPasswordReceiver() {
		return passwordReceiver;
	}
	public void setPasswordReceiver(String passwordReceiver) {
		this.passwordReceiver = passwordReceiver;
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
}
