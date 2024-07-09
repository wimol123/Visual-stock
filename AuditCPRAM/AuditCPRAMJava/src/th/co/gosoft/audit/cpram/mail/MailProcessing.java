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
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class MailProcessing {

	@Context HttpServletRequest httpServletRequest;
	private final static Logger logger = Logger.getLogger(MailProcessing.class);
	private static MailProcessing instanceMailProcessing = null;	
	private static MailConfig mailConfig = null;
	private static Object mutex = new Object();
	
	private MailProcessing() {
		
	}	
		
	public static MailProcessing getInstance() {
		
		synchronized (mutex) {
			if(instanceMailProcessing == null) {
				synchronized (mutex) {
					if(instanceMailProcessing == null) {
						instanceMailProcessing = new MailProcessing();
						mailConfig = Config.getObjectMailConfig();
					}
				}
			}
		}
		return instanceMailProcessing;
	}
	
	public boolean sendMailWithTextBody(MailInfo mailInfo) {
		try {
			
			InternetAddress[] arrayReceiver,arrayReceiverCC, arrayReceiverBCC;
			MimeMessage mimeMessage = new MimeMessage(getSession());			
			//mimeMessage.setFrom(new InternetAddress(mailConfig.getEmailSender()));
			
			if(mailInfo.getReceiverMail().getMailReceiver() != null) {
				if(!mailInfo.getReceiverMail().getMailReceiver().isEmpty()) {
					arrayReceiver = listEmailAddress(mailInfo.getReceiverMail().getMailReceiver());
					mimeMessage.setRecipients(Message.RecipientType.TO, arrayReceiver);
				}				
			}
			
			if(mailInfo.getReceiverMail().getMailReceiverCC() != null) {
				if(!mailInfo.getReceiverMail().getMailReceiverCC().isEmpty()) {
					arrayReceiverCC = listEmailAddress(mailInfo.getReceiverMail().getMailReceiverCC());
					mimeMessage.setRecipients(Message.RecipientType.CC, arrayReceiverCC);
				}
			}
			
			if(mailInfo.getReceiverMail().getMailReceiverBCC() != null) {
				if(!mailInfo.getReceiverMail().getMailReceiverBCC().isEmpty()) {
					arrayReceiverBCC = listEmailAddress(mailInfo.getReceiverMail().getMailReceiverBCC());
					mimeMessage.setRecipients(Message.RecipientType.BCC, arrayReceiverBCC);
				}
			}
			
			mimeMessage.setSubject(mailInfo.getTopicMail());
			mimeMessage.setText(mailInfo.getBodyMail());
			
			Transport.send(mimeMessage);
			return true;
		}catch(Exception e) {
			logger.error("MailProcessing.sendMailWithTextBody() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	public boolean sendMailWithHTMLBody(MailInfo mailInfo) {
		try {
			
			InternetAddress[] arrayReceiver,arrayReceiverCC, arrayReceiverBCC;
			MimeMessage mimeMessage = new MimeMessage(getSession());			
			mimeMessage.setFrom(new InternetAddress(mailConfig.getEmailSender()));
			
			if(mailInfo.getReceiverMail().getMailReceiver() != null) {
				if(!mailInfo.getReceiverMail().getMailReceiver().isEmpty()) {
					arrayReceiver = listEmailAddress(mailInfo.getReceiverMail().getMailReceiver());
					mimeMessage.setRecipients(Message.RecipientType.TO, arrayReceiver);
				}				
			}
			
			if(mailInfo.getReceiverMail().getMailReceiverCC() != null) {
				if(!mailInfo.getReceiverMail().getMailReceiverCC().isEmpty()) {
					arrayReceiverCC = listEmailAddress(mailInfo.getReceiverMail().getMailReceiverCC());
					mimeMessage.setRecipients(Message.RecipientType.CC, arrayReceiverCC);
				}
			}
			
			if(mailInfo.getReceiverMail().getMailReceiverBCC() != null) {
				if(!mailInfo.getReceiverMail().getMailReceiverBCC().isEmpty()) {
					arrayReceiverBCC = listEmailAddress(mailInfo.getReceiverMail().getMailReceiverBCC());
					mimeMessage.setRecipients(Message.RecipientType.BCC, arrayReceiverBCC);
				}
			}
			
			mimeMessage.setSubject(mailInfo.getTopicMail());
			mimeMessage.setContent(getMimeMultipart(mailInfo.getBodyMail()));
			
			Transport.send(mimeMessage);
			return true;
		}catch(Exception e) {
			logger.error("MailProcessing.sendMailWithHTMLBody() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	private Properties getProperties() {
		Properties properties = null;
		try {
			
			if(mailConfig.getEnanbleAuthen() == null) 
				mailConfig.setEnanbleAuthen(false);
			if(mailConfig.getEnableStartTLS() == null)
				mailConfig.setEnableStartTLS(true);
			
			properties = new Properties();
			properties.put("mail.smtp.host", mailConfig.getServerSMTP());			
			properties.put("mail.smtp.auth", mailConfig.getEnanbleAuthen());
			properties.put("mail.smtp.starttls.enable", mailConfig.getEnableStartTLS());
			
			return properties;
		}catch(Exception e) {
			logger.error("MailProcessing.getProperties() Exception : "+ExceptionUtils.stackTraceException(e));
			return null;
		}
	}
	
	private Session getSession() {
		Session session = null;
		try {
			if(mailConfig.getEnanbleAuthen()) {
				session = Session.getInstance(getProperties(),
						new Authenticator() {
					
							protected PasswordAuthentication getPasswordAuthentication() {  
								return new PasswordAuthentication(mailConfig.getEmailSender(),mailConfig.getEmailSenderPassword());  
							}
							
						}
					);
			}else {
				session = Session.getInstance(getProperties());
			}		
			return session;
		}catch(Exception e) {
			logger.error("MailProcessing.getSession() Exception : "+ExceptionUtils.stackTraceException(e));
			return null;
		}
	}
	
	private InternetAddress[] listEmailAddress(List<String> listEmail) {
		try {
						
			InternetAddress[] arrayInternetAddress = new InternetAddress[listEmail.size()];
			
			int countEmail = 0;
			for (String email : listEmail) {
				arrayInternetAddress[countEmail] = new InternetAddress(email);
				countEmail++;
			}
			
			return arrayInternetAddress;
		}catch(Exception e) {
			logger.error("MailProcessing.listEmailAddress() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	private MimeMultipart getMimeMultipart(String htmlBody) {
		try {
			
			MimeMultipart multipart = new MimeMultipart("related");
			multipart.addBodyPart(getBodyPartContentHTML(htmlBody));
			
			multipart.addBodyPart(getBodyPartContentImage("image_logo")); //logocomment(local)
			return multipart;
			
		}catch(Exception e) {
			logger.error("MailProcessing.getMimeMultipart() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	private BodyPart getBodyPartContentHTML(String htmlBody) {
		try {
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(htmlBody,"text/html; charset=utf-8");
			return messageBodyPart;
			
		}catch(Exception e) {
			logger.error("MailProcessing.getBodyPartContentHTML() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	private BodyPart getBodyPartContentImage(String contentID) {
		try {
			
			BodyPart messageBodyPart = new MimeBodyPart();						
		
			DataSource fileDataSource = new FileDataSource(ConfigurationSystemManager.getInstance().getPathLogoCPRAM());
//			DataSource fileDataSource = new FileDataSource("D:\\Project Gosoft\\FileCpram\\resources\\cpram_logo.png");
			messageBodyPart.setDataHandler(new DataHandler(fileDataSource));
			messageBodyPart.setHeader("Content-ID", "<"+contentID+">");
			
			return messageBodyPart;
			
		}catch(Exception e) {
			logger.error("MailProcessing.getBodyPartContentImage() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
		
	
}
