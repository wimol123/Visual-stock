package th.co.gosoft.audit.cpram.model;

public class MailConfigModel {
	private String emailReceiver;
	private String emailSender;
	private String emailSenderPassword;
	private String emailSubject;
	private String serverSMTP;
	private String portSMTP;
	private String enableAlert;
	private String enableAuthen;
	public String getEmailReceiver() {
		return emailReceiver;
	}
	public void setEmailReceiver(String emailReceiver) {
		this.emailReceiver = emailReceiver;
	}
	public String getEmailSender() {
		return emailSender;
	}
	public void setEmailSender(String emailSender) {
		this.emailSender = emailSender;
	}
	public String getEmailSenderPassword() {
		return emailSenderPassword;
	}
	public void setEmailSenderPassword(String emailSenderPassword) {
		this.emailSenderPassword = emailSenderPassword;
	}
	public String getEmailSubject() {
		return emailSubject;
	}
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}
	public String getServerSMTP() {
		return serverSMTP;
	}
	public void setServerSMTP(String serverSMTP) {
		this.serverSMTP = serverSMTP;
	}
	public String getPortSMTP() {
		return portSMTP;
	}
	public void setPortSMTP(String portSMTP) {
		this.portSMTP = portSMTP;
	}
	public String getEnableAlert() {
		return enableAlert;
	}
	public void setEnableAlert(String enableAlert) {
		this.enableAlert = enableAlert;
	}
	public String getEnableAuthen() {
		return enableAuthen;
	}
	public void setEnableAuthen(String enableAuthen) {
		this.enableAuthen = enableAuthen;
	}
}
