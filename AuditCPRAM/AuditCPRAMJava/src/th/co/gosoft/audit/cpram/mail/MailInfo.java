package th.co.gosoft.audit.cpram.mail;

import java.util.List;

public class MailInfo {

	private MailReceiver receiverMail;
	private String topicMail;
	private String bodyMail;
	private List<String> sender;
	
	public MailReceiver getReceiverMail() {
		return receiverMail;
	}
	public void setReceiverMail(MailReceiver receiverMail) {
		this.receiverMail = receiverMail;
	}
	public String getTopicMail() {
		return topicMail;
	}
	public void setTopicMail(String topicMail) {
		this.topicMail = topicMail;
	}
	public String getBodyMail() {
		return bodyMail;
	}
	public void setBodyMail(String bodyMail) {
		this.bodyMail = bodyMail;
	}
	public List<String> getSender() {
		return sender;
	}
	public void setSender(List<String> sender) {
		this.sender = sender;
	}
	
}
