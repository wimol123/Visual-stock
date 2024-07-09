package th.co.gosoft.audit.cpram.common;

import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.controller.SystemConfigurationController;
import th.co.gosoft.audit.cpram.model.SystemConfigurationModel;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class ConfigurationSystemManager extends ConfigurationSystemValue {

	private final static Logger logger = Logger.getLogger(ConfigurationSystemManager.class);
	private static ConfigurationSystemManager configurationSystemInstance = null;
	private static Object mutex = new Object();

	private ConfigurationSystemManager() {
		
	}

	public static ConfigurationSystemManager getInstance() {
		synchronized (mutex) {
			if (configurationSystemInstance == null) {
				synchronized (mutex) {
					if (configurationSystemInstance == null) {
						logger.info("loading System Config Please Wait ...");
						configurationSystemInstance = new ConfigurationSystemManager();
						setConfigurationValue(configurationSystemInstance);
						logger.info("loading System Config Success");
					}
				}
			}
		}
		
		return configurationSystemInstance;
	}
	
	public static void loadNewConfiguration() {
		configurationSystemInstance = null;
		logger.info("Re-Loading New System Configuration Request");
		getInstance();
	}
	
	private static void setConfigurationValue(ConfigurationSystemManager configurationSystemInstance) {
		List<SystemConfigurationModel> systemConfigurationModels = getObjectSystemConfiguration();
		for (SystemConfigurationModel systemConfiguration : systemConfigurationModels) {
			if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.httpAliasLogoname)) {
				configurationSystemInstance.httpAliasLogoname = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.httpAliasAuditResult)) {
				configurationSystemInstance.httpAliasAuditResult = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.httpAliasSupplierDocument)) {
				configurationSystemInstance.httpAliasSupplierDocument = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.httpAliasManualDocument)) {
				configurationSystemInstance.httpAliasManualDocument = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.httpWebserverName)) {
				configurationSystemInstance.httpWebserverName = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemEmerFooterAdminMail)) {
				configurationSystemInstance.emerFooterMail = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemEmerFooterTelephone)) {
				configurationSystemInstance.emerFooterTelephone = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemEmerFooterUrlWeb)) {
				configurationSystemInstance.emerFooterUrlWeb = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemLdapDomain)) {
				configurationSystemInstance.ldapDomain = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemLdapPassword)) {
				configurationSystemInstance.ldapPassword = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemLdapSearchBase)) {
				configurationSystemInstance.ldapSearchBase = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemLdapUsername)) {
				configurationSystemInstance.ldapUsername = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemLdapServer)) {
				configurationSystemInstance.ldapSever = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailEnableStartTLS)) {
				configurationSystemInstance.enableStartTLS = Boolean
						.parseBoolean(systemConfiguration.getSystemValue().trim());
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailEnableAuthen)) {
				configurationSystemInstance.enanbleAuthen = Boolean
						.parseBoolean(systemConfiguration.getSystemValue().trim());
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailFooterAdminMail)) {
				configurationSystemInstance.mailFooterAdminMail = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailFooterTelephone)) {
				configurationSystemInstance.mailFooterTelephone = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailFooterUrlWeb)) {
				configurationSystemInstance.mailFooterUrlWeb = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailPathLogoCpram)) {
				configurationSystemInstance.pathLogoCPRAM = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailPortSMTP)) {
				configurationSystemInstance.portSMTP = NullUtils.cvInt(systemConfiguration.getSystemValue().trim());
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailSenderMail)) {
				configurationSystemInstance.senderMail = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailSenderPassword)) {
				configurationSystemInstance.senderPassword = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemMailServerSMTP)) {
				configurationSystemInstance.serverSMTP = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemMobileServicePathFileUpload)) {
				configurationSystemInstance.mobileServicePathFileUpload = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemPathFilePathReport)) {
				configurationSystemInstance.pathFileReport = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemPathFilePathSupplierLogo)) {
				configurationSystemInstance.pathSupplierLogo = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemPathFilePathSupplierStandardDocument)) {
				configurationSystemInstance.pathFilePathSupplierStandardDocument = systemConfiguration.getSystemValue()
						.trim();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemPathFilePathManualDocument)) {
				configurationSystemInstance.pathFilePathManualDocument = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemPathFilePathTemp)) {
				configurationSystemInstance.pathTemp = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemPathFileReportPdfFont.trim())) {
				configurationSystemInstance.pathFileReportPdfFont = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemPathFileReportPdfFontSymbol.trim())) {
				configurationSystemInstance.pathFileReportPdfFontSymbol = systemConfiguration.getSystemValue().trim();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemSecurePrivateKey)) {
				configurationSystemInstance.securePrivateKey = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemSecurePublicKey)) {
				configurationSystemInstance.securePublicKey = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemSecureSaltedString)) {
				configurationSystemInstance.secureSaltedString = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemSequenceChecklistPlanKey)) {
				configurationSystemInstance.sequenceChecklistPlanKey = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemSequenceCarKey)) {
				configurationSystemInstance.sequenceCarKey = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemDateTimeSupplierStandardDocumentExpireDay)) {
				configurationSystemInstance.dateTimeSupplierStandardDocumentExpireDay = NullUtils
						.cvInt(systemConfiguration.getSystemValue().trim());
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.httpAliasSupplierQuestionaireDocument)) {
				configurationSystemInstance.httpAliasSupplierQuestionaireDocument = systemConfiguration
						.getSystemValue();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemPathFilePathSupplierQuestionaireDocument)) {
				configurationSystemInstance.pathFilePathSupplierQuestionaireDocument = systemConfiguration
						.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.httpAliasInformationDocument)) {
				configurationSystemInstance.httpAliasInformationDocument = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey()
					.equals(ConfigurationSystemKey.systemPathFilePathInformationDocument)) {
				configurationSystemInstance.pathFilePathInformationDocument = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemAcceptPdpaHtml)) {
				configurationSystemInstance.systemAcceptPdpaHtml = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemAcceptPdpaNo)) {
				configurationSystemInstance.systemAcceptPdpaNo = NullUtils.cvInt(systemConfiguration.getSystemValue());
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemUrlPdpa)) {
				configurationSystemInstance.systemUrlPdpa = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemPathFilePoManual)) {
				configurationSystemInstance.pathFilePoManual = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemSapApiPathPdfPo)) {
				configurationSystemInstance.sapApiPathPoPdf = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemSapApiUsername)) {
				configurationSystemInstance.sapApiUsername = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemSapApiPassword)) {
				configurationSystemInstance.sapApiPassword = systemConfiguration.getSystemValue();
			} else if (systemConfiguration.getSystemKey().equals(ConfigurationSystemKey.systemPathFilePoSapBackup)) {
				configurationSystemInstance.pathFilePoSapBackup = systemConfiguration.getSystemValue();
			}
		}
	}
	
	private static List<SystemConfigurationModel> getObjectSystemConfiguration() {
		SystemConfigurationController systemConfigurationController = new SystemConfigurationController();
		List<SystemConfigurationModel> systemConfigurationModels = systemConfigurationController
				.getSystemConfigurationList();
		logger.info("System Configuration Value : " + new Gson().toJson(systemConfigurationModels));
		return systemConfigurationModels;
	}

}
