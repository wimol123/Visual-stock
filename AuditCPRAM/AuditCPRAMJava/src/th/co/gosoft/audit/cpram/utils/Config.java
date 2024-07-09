package th.co.gosoft.audit.cpram.utils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.mail.BodyEmailDTO;
import th.co.gosoft.audit.cpram.mail.MailConfig;

public class Config {

	public static final int AUTHEN_USERTYPE_ADMINISTRATOR = 1;
	public static final int AUTHEN_USERTYPE_AUDITOR = 2;
	public static final int AUTHEN_USERTYPE_SUPPLIER_ADMIN = 3;
	public static final int AUTHEN_USERTYPE_SUPPLIER_QA = 4;
	public static final int AUTHEN_USERTYPE_PURCHASING_OFFICER = 5;
	public static final int AUTHEN_USERTYPE_APPROVE_AUDIT_RESULT = 6;
	public static final int AUTHEN_USERTYPE_REGISTER = 7;
	public static final int AUTHEN_USERTYPE_SUPPLIER_SALE = 8;
	
	public static final String AUTHEN_TYPE_AD = "AD";
	public static final String AUTHEN_TYPE_USER = "USER";
	
	/*public static MailConfigDTO getObjectConfigEmail() {
		MailConfigDTO mailConfigDTO = new MailConfigDTO();
		mailConfigDTO.setEmailSender("teerapat.ratc@gmail.com");
		mailConfigDTO.setEmailSenderPassword("kill25317");
		//mailConfigDTO.setEmailSubject("Authentification CPRam Audit Supplier");
		mailConfigDTO.setEnanbleAuthen(true);
		mailConfigDTO.setServerSMTP("smtp.gmail.com");
		mailConfigDTO.setPortSMTP(25);
		//mailConfigDTO.setEmailBody(CreateMailBody.bodyRegisterHTML(bodyEmailDTO));
		return mailConfigDTO;
	}*/
	
	public static MailConfig getObjectMailConfig() {
		MailConfig mailConfig = new MailConfig();
		mailConfig.setEmailSender(ConfigurationSystemManager.getInstance().getSenderMail());
		mailConfig.setEmailSenderPassword(ConfigurationSystemManager.getInstance().getSenderPassword());
		mailConfig.setEnableStartTLS(ConfigurationSystemManager.getInstance().getEnableStartTLS());
		mailConfig.setEnanbleAuthen(ConfigurationSystemManager.getInstance().getEnanbleAuthen());
		mailConfig.setPortSMTP(ConfigurationSystemManager.getInstance().getPortSMTP());
		mailConfig.setServerSMTP(ConfigurationSystemManager.getInstance().getServerSMTP());
		return mailConfig;
	}
	
	public static BodyEmailDTO getObjectBodyMail() {
		BodyEmailDTO bodyEmailDTO = new BodyEmailDTO();
		bodyEmailDTO.setEmailAdmin(ConfigurationSystemManager.getInstance().getMailFooterAdminMail());
		bodyEmailDTO.setTelephoneAdmin(ConfigurationSystemManager.getInstance().getMailFooterTelephone());
		bodyEmailDTO.setUrl(ConfigurationSystemManager.getInstance().getMailFooterUrlWeb());		
		return bodyEmailDTO;
	}
}
