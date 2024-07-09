package th.co.gosoft.audit.cpram.mail;

public class MailConfig {
	private String emailSender;
	private String emailSenderPassword;
	private String serverSMTP;
	private int portSMTP;
	private Boolean enableStartTLS;
	private Boolean enanbleAuthen;
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
	public String getServerSMTP() {
		return serverSMTP;
	}
	public void setServerSMTP(String serverSMTP) {
		this.serverSMTP = serverSMTP;
	}
	public int getPortSMTP() {
		return portSMTP;
	}
	public void setPortSMTP(int portSMTP) {
		this.portSMTP = portSMTP;
	}
	public Boolean getEnableStartTLS() {
		return enableStartTLS;
	}
	public void setEnableStartTLS(Boolean enableStartTLS) {
		this.enableStartTLS = enableStartTLS;
	}
	public Boolean getEnanbleAuthen() {
		return enanbleAuthen;
	}
	public void setEnanbleAuthen(Boolean enanbleAuthen) {
		this.enanbleAuthen = enanbleAuthen;
	}
}
