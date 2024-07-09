package th.co.gosoft.audit.cpram.mail;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.MailConfigDTO;
import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class Mail {
	
	private final static Logger logger = Logger.getLogger(Mail.class);
	private MailConfigDTO _mailConfigDTO;
	
	public Mail(MailConfigDTO mailConfigDTO) {
		_mailConfigDTO = mailConfigDTO;
	}
	
	public Properties getProperties() {
		Properties properties = null;
		try {
			
			if(_mailConfigDTO.isEnanbleAuthen() == null)
				_mailConfigDTO.setEnanbleAuthen(true);
			if(_mailConfigDTO.getEnableStartTLS() == null)
				_mailConfigDTO.setEnableStartTLS(true);
			
			properties = new Properties();
			properties.put("mail.smtp.host", _mailConfigDTO.getServerSMTP());			
			properties.put("mail.smtp.auth", _mailConfigDTO.isEnanbleAuthen());
			properties.put("mail.smtp.starttls.enable", _mailConfigDTO.getEnableStartTLS());
			
			return properties;
		}catch(Exception e) {
			logger.error("Mail.getProperties() Exception : "+ExceptionUtils.stackTraceException(e));
			return null;
		}
	}
	
	public Session getSession() {
		Session session = null;
		try {
			session = Session.getInstance(getProperties(),
					new Authenticator() {
				
						protected PasswordAuthentication getPasswordAuthentication() {  
							return new PasswordAuthentication(_mailConfigDTO.getEmailSender(),_mailConfigDTO.getEmailSenderPassword());  
						}
						
					}
				);
			
			return session;
		}catch(Exception e) {
			logger.error("Mail.getSession() Exception : "+ExceptionUtils.stackTraceException(e));
			return null;
		}
	}
	
	
	public void sendMailBodyText() {
		try {
			
			InternetAddress[] arrayReceiver,arrayReceiverCC, arrayReceiverBCC;
						
			MimeMessage mimeMessage = new MimeMessage(getSession());			
			mimeMessage.setFrom(new InternetAddress(_mailConfigDTO.getEmailSender()));
			
			if(_mailConfigDTO.getEmailReceiver() != null) {
				if(!_mailConfigDTO.getEmailReceiver().isEmpty()) {
					arrayReceiver = listEmailAddress(_mailConfigDTO.getEmailReceiver());
					mimeMessage.setRecipients(Message.RecipientType.TO, arrayReceiver);
				}				
			}
			if(_mailConfigDTO.getEmailReceiverCC() != null) {
				if(!_mailConfigDTO.getEmailReceiverCC().isEmpty()) {
					arrayReceiverCC = listEmailAddress(_mailConfigDTO.getEmailReceiverCC());
					mimeMessage.setRecipients(Message.RecipientType.CC, arrayReceiverCC);
				}
			}
			if(_mailConfigDTO.getEmailReceiverBCC() != null) {
				if(!_mailConfigDTO.getEmailReceiverBCC().isEmpty()) {
					arrayReceiverBCC = listEmailAddress(_mailConfigDTO.getEmailReceiverBCC());
					mimeMessage.setRecipients(Message.RecipientType.BCC, arrayReceiverBCC);
				}
			}
			
			mimeMessage.setSubject(_mailConfigDTO.getEmailSubject());  
	        mimeMessage.setText(_mailConfigDTO.getEmailBody());
		    //send the message  
		    Transport.send(mimeMessage);  
			
		}catch(Exception e) {
			logger.error("Mail.sendMailBodyText() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public Boolean sendMailBodyHTML() {
		try {
			
			InternetAddress[] arrayReceiver,arrayReceiverCC, arrayReceiverBCC;
						
			MimeMessage mimeMessage = new MimeMessage(getSession());			
			mimeMessage.setFrom(new InternetAddress(_mailConfigDTO.getEmailSender()));
			if(_mailConfigDTO.getEmailReceiver() != null) {
				if(!_mailConfigDTO.getEmailReceiver().isEmpty()) {
					arrayReceiver = listEmailAddress(_mailConfigDTO.getEmailReceiver());
					mimeMessage.setRecipients(Message.RecipientType.TO, arrayReceiver);
				}				
			}
			if(_mailConfigDTO.getEmailReceiverCC() != null) {
				if(!_mailConfigDTO.getEmailReceiverCC().isEmpty()) {
					arrayReceiverCC = listEmailAddress(_mailConfigDTO.getEmailReceiverCC());
					mimeMessage.setRecipients(Message.RecipientType.CC, arrayReceiverCC);
				}
			}
			if(_mailConfigDTO.getEmailReceiverBCC() != null) {
				if(!_mailConfigDTO.getEmailReceiverBCC().isEmpty()) {
					arrayReceiverBCC = listEmailAddress(_mailConfigDTO.getEmailReceiverBCC());
					mimeMessage.setRecipients(Message.RecipientType.BCC, arrayReceiverBCC);
				}
			}
			
			mimeMessage.setSubject(_mailConfigDTO.getEmailSubject(),"utf-8");  
	        mimeMessage.setContent(getMimeMultipart());
		    //send the message  
		     Transport.send(mimeMessage);  
			return true;
		}catch(Exception e) {
			logger.error("Mail.sendMailBodyHTML() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public InternetAddress[] listEmailAddress(List<String> listEmail) {
		try {
						
			InternetAddress[] arrayInternetAddress = new InternetAddress[listEmail.size()];
			
			int countEmail = 0;
			for (String email : listEmail) {
				arrayInternetAddress[countEmail] = new InternetAddress(email);
				countEmail++;
			}
			
			return arrayInternetAddress;
		}catch(Exception e) {
			logger.error("Mail.listEmailAddress() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage());
		}
	}
	
	
	public MimeMultipart getMimeMultipart() {
		try {
			
			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(getBodyPartContentHTML());
			multipart.addBodyPart(getBodyPartContentImage("image_logo")); //logocomment(local)
			return multipart;
			
		}catch(Exception e) {
			logger.error("Mail.getMimeMultipart() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public BodyPart getBodyPartContentHTML() {
		try {
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(_mailConfigDTO.getEmailBody(),"text/html; charset=utf-8");
			return messageBodyPart;
			
		}catch(Exception e) {
			logger.error("Mail.getBodyPartContentHTML() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public BodyPart getBodyPartContentImage(String contentID) {
		try {
			
			BodyPart messageBodyPart = new MimeBodyPart();

			DataSource fileDataSource = new FileDataSource(FileUploadConst.PATH_Logo_CPRAM_For_Email);
			messageBodyPart.setDataHandler(new DataHandler(fileDataSource));
			messageBodyPart.setHeader("Content-ID", "<"+contentID+">");
			
			return messageBodyPart;
			
		}catch(Exception e) {
			logger.error("Mail.getBodyPartContentImage() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage());
		}
	}
	
}
