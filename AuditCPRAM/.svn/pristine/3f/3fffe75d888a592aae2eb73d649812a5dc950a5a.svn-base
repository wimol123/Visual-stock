package th.co.gosoft.audit.cpram.mail;

import com.mysql.jdbc.StringUtils;

public class MailConnector {
	
	public static boolean sendMailRegisterUser(MailReceiver mailReceiver, BodyEmailDTO bodyEmailDTO, String mailSubject) {
		if(mailReceiver == null || bodyEmailDTO == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		/*if(mailReceiver == null || bodyEmailDTO == null || !StringUtils.isNullOrEmpty(mailSubject))
			return false;*/
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyRegisterUser(bodyEmailDTO)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}
	
	public static boolean sendMailChangePasswordUser(MailReceiver mailReceiver, BodyEmailDTO bodyEmailDTO, String mailSubject) {
		if(mailReceiver == null || bodyEmailDTO == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyChangePasswordUser(bodyEmailDTO)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}
	
	public static boolean sendMailNewAppoint(MailReceiver mailReceiver, BodyEmailNewAppointDTO bodyEmailNewAppointDTO , String mailSubject) {
		if(mailReceiver == null || bodyEmailNewAppointDTO == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyCreateNewAppoint(bodyEmailNewAppointDTO)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}
	
	public static boolean sendMailChangeAppointForSupplier(MailReceiver mailReceiver, BodyEmailNewAppointDTO bodyEmailNewAppointDTO , String mailSubject) {
		if(mailReceiver == null || bodyEmailNewAppointDTO == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyChangeAppointForSupplier(bodyEmailNewAppointDTO)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}
	
	public static boolean sendMailToSupplierForConfirmFinalAuditResult(MailReceiver mailReceiver, BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier, String mailSubject) {
		
		if(mailReceiver == null || bodyEmailConfirmFinalAuditResultForSupplier == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyConfirmFinalAuditResultForSupplier(bodyEmailConfirmFinalAuditResultForSupplier)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}
	
	public static boolean sendMailToApproveForConfirmFinalAuditResult(MailReceiver mailReceiver, BodyEmailConfirmFinalAuditResultForApprover bodyEmailConfirmFinalAuditResultForApprover, String mailSubject) {
		
		if(mailReceiver == null || bodyEmailConfirmFinalAuditResultForApprover == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyConfirmFinalAuditResultForApprover(bodyEmailConfirmFinalAuditResultForApprover,mailSubject)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}
	
	public static boolean sendMailToSupplierForAlertFinalAuditResult(MailReceiver mailReceiver, BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier, String mailSubject) {
		if(mailReceiver == null || bodyEmailConfirmFinalAuditResultForSupplier == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyAlertFinalAuditResult(bodyEmailConfirmFinalAuditResultForSupplier)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}
	
	public static boolean sendMailToSupplierForAlertEditFinalAuditResult(MailReceiver mailReceiver, BodyEmailConfirmEditFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier, String mailSubject) {
		if(mailReceiver == null || bodyEmailConfirmFinalAuditResultForSupplier == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyAlertEditFinalAuditResult(bodyEmailConfirmFinalAuditResultForSupplier)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}

	public static boolean sendMailToSupplierForAlertInformation(MailReceiver mailReceiver, BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier, String mailSubject) {
		
		if(mailReceiver == null || bodyEmailConfirmFinalAuditResultForSupplier == null || StringUtils.isNullOrEmpty(mailSubject))
			return false;
		MailInfo mailInfo = new MailInfo();
		mailInfo.setReceiverMail(mailReceiver);
		mailInfo.setTopicMail(mailSubject);
		mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyAlertInformationForSupplier(bodyEmailConfirmFinalAuditResultForSupplier)));
		return MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
	}

}
