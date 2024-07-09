package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.common.ConfigurationSystemKey;
import th.co.gosoft.audit.cpram.common.ConfigurationSystemValue;
import th.co.gosoft.audit.cpram.dao.SystemConfigurationDAO;
import th.co.gosoft.audit.cpram.dto.SystemConfigurationDTO;
import th.co.gosoft.audit.cpram.model.SystemConfigurationModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class SystemConfigurationController {
	
	private final static Logger logger = Logger.getLogger(SystemConfigurationController.class);
	
	public List<SystemConfigurationModel> getSystemConfigurationList() {
		Connection connection = null;
		SystemConfigurationDAO systemConfigurationDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			systemConfigurationDAO = new SystemConfigurationDAO(connection);
			List<SystemConfigurationDTO> systemConfigurationDTOs = systemConfigurationDAO.getSystemConfigurationList("");
			List<SystemConfigurationModel> systemConfigurationModels = new ArrayList<>();
			
			
			for(SystemConfigurationDTO systemConfiguration : systemConfigurationDTOs) {
				systemConfigurationModels.add(TransformDTO.transSystemConfigurationDTO(systemConfiguration));				
			}
			return systemConfigurationModels;
		}catch (Exception e) {
			logger.error("SystemConfigurationController.getSystemConfigurationList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getSystemConfigurationByKey(HttpServletRequest httpServletRequest, String configurationObj) {
		Connection connection = null;
		SystemConfigurationDAO systemConfigurationDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			systemConfigurationDAO = new SystemConfigurationDAO(connection);
			gson = new Gson();
			StringBuilder queryWhereClause = new StringBuilder();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			if(!StringUtils.isNullOrEmpty(configurationObj)) {				
				SystemConfigurationModel systemConfigurationModel = gson.fromJson(configurationObj, SystemConfigurationModel.class);
				if(!StringUtils.isNullOrEmpty(systemConfigurationModel.getSystemKey())) {
					queryWhereClause.append(" AND ").append("system_key LIKE 'system.").append(systemConfigurationModel.getSystemKey()).append("%' ");
				}				
			}

			List<SystemConfigurationDTO> systemConfigurationDTOs = systemConfigurationDAO.getSystemConfigurationList(queryWhereClause.toString());
			List<SystemConfigurationModel> systemConfigurationModels = new ArrayList<>();
			for(SystemConfigurationDTO systemConfiguration : systemConfigurationDTOs) {					
				systemConfigurationModels.add(TransformDTO.transSystemConfigurationDTO(systemConfiguration));
			}
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);			
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_ADMINISTRATOR) {
				return gson.toJson(systemConfigurationModels);
			}else {
				return null;
			}		
			
		}catch (Exception e) {
			logger.error("SystemConfigurationController.getSystemConfigurationByKey() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public boolean updateSystemConfigurationSystem(HttpServletRequest httpServletRequest, String systemConfigurationObj) {
		Gson gson = null;
		SessionUtils sessionUtils = null;
		Connection connection = null;
		SystemConfigurationDAO systemConfigurationDAO = null;
		boolean resultProcess = true;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			systemConfigurationDAO = new SystemConfigurationDAO(connection);
			gson = new Gson();	
			sessionUtils = new SessionUtils(httpServletRequest);	
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			ConfigurationSystemValue configurationSystemValue = gson.fromJson(systemConfigurationObj, ConfigurationSystemValue.class);
			if(configurationSystemValue != null) {
				if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_ADMINISTRATOR) {
					SystemConfigurationModel systemConfigurationModel = new SystemConfigurationModel();
					systemConfigurationModel.setUpdateBy(userSessionModel.getUserId());
										
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getEmerFooterMail())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemEmerFooterAdminMail);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getEmerFooterMail());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getEmerFooterTelephone())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemEmerFooterTelephone);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getEmerFooterTelephone());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getEmerFooterUrlWeb())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemEmerFooterUrlWeb);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getEmerFooterUrlWeb());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getLdapDomain())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemLdapDomain);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getLdapDomain());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getLdapPassword())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemLdapPassword);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getLdapPassword());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getLdapSearchBase())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemLdapSearchBase);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getLdapSearchBase());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getLdapUsername())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemLdapUsername);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getLdapUsername());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getLdapSever())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemLdapServer);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getLdapSever());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getMailFooterAdminMail())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailFooterAdminMail);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getMailFooterAdminMail());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getMailFooterTelephone())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailFooterTelephone);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getMailFooterTelephone());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getMailFooterUrlWeb())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailFooterUrlWeb);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getMailFooterUrlWeb());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getPathLogoCPRAM())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailPathLogoCpram);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getPathLogoCPRAM());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getPathSupplierLogo())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemPathFilePathSupplierLogo);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getPathSupplierLogo());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getPathTemp())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemPathFilePathTemp);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getPathTemp());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getSecureSaltedString())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemSecureSaltedString);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getSecureSaltedString());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getSenderMail())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailSenderMail);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getSenderMail());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getSenderPassword())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailSenderPassword);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getSenderPassword());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(!StringUtils.isNullOrEmpty(configurationSystemValue.getServerSMTP())) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailServerSMTP);
							systemConfigurationModel.setSystemValue(configurationSystemValue.getServerSMTP());
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(configurationSystemValue.getEnableStartTLS() != null) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailEnableStartTLS);
							systemConfigurationModel.setSystemValue(NullUtils.cvStr(configurationSystemValue.getEnableStartTLS()));
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(configurationSystemValue.getEnanbleAuthen() != null) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailEnableAuthen);
							systemConfigurationModel.setSystemValue(NullUtils.cvStr(configurationSystemValue.getEnanbleAuthen()));
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(configurationSystemValue.getPortSMTP() != null) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemMailPortSMTP);
							systemConfigurationModel.setSystemValue(NullUtils.cvStr(configurationSystemValue.getPortSMTP()));
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						if(configurationSystemValue.getDateTimeSupplierStandardDocumentExpireDay() != null) {
							systemConfigurationModel.setSystemKey(ConfigurationSystemKey.systemDateTimeSupplierStandardDocumentExpireDay);
							systemConfigurationModel.setSystemValue(NullUtils.cvStr(configurationSystemValue.getDateTimeSupplierStandardDocumentExpireDay()));
							resultProcess = systemConfigurationDAO.updateSystemConfiguration(TransformModel.transSystemConfigurationModel(systemConfigurationModel));
						}
					}
					
					if(resultProcess) {
						DatabaseUtils.commit(connection);
						ConfigurationSystemManager.loadNewConfiguration();
						return true;
					}						
					else {
						DatabaseUtils.rollback(connection);
						return false;
					}						
					
				}else {
					return false;
				}
			}else {
				return false;
			}			
		}catch (Exception e) {
			logger.error("SystemConfigurationController.updateSystemConfigurationSystem() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean reloadSystemConfiguration(HttpServletRequest httpServletRequest) {
		SessionUtils sessionUtils = null;
		try {
			
			sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_ADMINISTRATOR) {
				ConfigurationSystemManager.loadNewConfiguration();
				return true;
			}else {
				return false;
			}
			
		}catch (Exception e) {
			logger.error("SystemConfigurationController.reloadSystemConfiguration() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e);
		}
	}

}
