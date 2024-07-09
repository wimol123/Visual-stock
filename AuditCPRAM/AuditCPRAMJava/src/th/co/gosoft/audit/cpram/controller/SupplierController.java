package th.co.gosoft.audit.cpram.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.dao.AddressDAO;
import th.co.gosoft.audit.cpram.dao.AppointDAO;
import th.co.gosoft.audit.cpram.dao.SupplierAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dao.UserDAO;
import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.fileupload.FileUpload;
import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.mail.BodyEmailDTO;
import th.co.gosoft.audit.cpram.mail.MailConnector;
import th.co.gosoft.audit.cpram.mail.MailReceiver;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.SupplierUserMappingModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.relational.SupplierRelationnal;
import th.co.gosoft.audit.cpram.relational.UserRelational;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.EncryptUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.PasswordGenerator;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class SupplierController {
	private final static Logger logger = Logger.getLogger(SupplierController.class);

	public DataTableModel<SupplierModel> getSupplierList(DataTablePostParamModel dataTablePostParamModel){
		SupplierDAO supplierDAO = null;
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			DataTableModel<SupplierModel> dataTableModel = new DataTableModel<>();
			
			StringBuilder sbWhereCause = new StringBuilder();
			for (Column col : dataTablePostParamModel.getColumns()) {
				
				if (!StringUtils.isNullOrEmpty(col.getName())) {					
					if (col.getName().equals("supplierCode")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.supplier_code").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");

					} else if (col.getName().equals("enable")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.enable").append(" = '").append(col.getSearch().getValue()).append("' ");

					} else if (col.getName().equals("supplierCompany")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if (col.getName().equals("isGreenCard")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.is_green_card").append(" = '").append(col.getSearch().getValue()).append("' ");
					}else if (col.getName().equals("approval")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.approval").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
				}
			}
									
			
			List<SupplierDTO> supplierDTOs = supplierDAO.getSupplierList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString());
			int countSupplier = supplierDAO.countSupplierList(sbWhereCause.toString());			
			dataTableModel.setData(new ArrayList<>());
			
			dataTableModel.setRecordsFiltered(countSupplier);
			dataTableModel.setRecordsTotal(countSupplier);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			for(SupplierDTO supplierDTO : supplierDTOs) {
				dataTableModel.getData().add(TransformDTO.transSupplierDTO(supplierDTO));
			}
			return dataTableModel;
		}catch(Exception e) {
			logger.error("SupplierController.getSupplierList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public Map<String, Object> supplierInsert(HttpServletRequest httpServletRequest, String objSupplier) {
		Connection connection = null;
		SupplierDAO supplierDAO = null;
		UserDAO userDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		SupplierAddressMappingDAO supplierAddressMappingDAO = null;
		AddressDAO addressDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		FileProcessing fileProcessing = null;
		PasswordGenerator passwordGenerator = null;
		boolean dupplicateUserName = false, dupplicateSupplier = false, dupplicateLogoSupplier = false, dupplicateSupplierMapping = false, resultProcess = true; 
		
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			supplierDAO = new SupplierDAO(connection);
			userDAO = new UserDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			supplierAddressMappingDAO = new SupplierAddressMappingDAO(connection);
			addressDAO = new AddressDAO(connection);
			fileProcessing = new FileProcessing();			
			sessionUtils = new SessionUtils(httpServletRequest);	
			passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
					.useDigits(true)
					.useLower(true)
					.useUpper(true)
					.build();
			
			int userIdSession = sessionUtils.getUserIdSession("sessionAuthen");
			
			SupplierModel supplierRequestModel = gson.fromJson(objSupplier, SupplierModel.class);
			supplierRequestModel.setCreateBy(NullUtils.cvStr(userIdSession));
			supplierRequestModel.setUpdateBy(NullUtils.cvStr(userIdSession));
			supplierRequestModel.getAddressId().setCreateBy(NullUtils.cvStr(userIdSession));
			supplierRequestModel.getAddressId().setUpdateBy(NullUtils.cvStr(userIdSession));
			
			if(!StringUtils.isNullOrEmpty(supplierRequestModel.getGreenCardExpireDate())) {
				supplierRequestModel.setGreenCardExpireDate(String.format("%s %s", supplierRequestModel.getGreenCardExpireDate(), StaticVariableUtils.timeOfExpire));
			}
			
			for(UserModel userRequest : supplierRequestModel.getUserId()) {
				dupplicateUserName = userDAO.checkDupplicateUserName(TransformModel.transUserModel(userRequest));
				if(dupplicateUserName)
					break;
			}
			
			if(!dupplicateUserName) {
				List<SupplierDTO> supplierCompanyList = supplierDAO.getSupllierCompanyList();
				for(SupplierDTO supplierCompanyDTO : supplierCompanyList) {
					if(supplierCompanyDTO.getSupplierCompany().equals(supplierRequestModel.getSupplierCompany())) {
						dupplicateSupplier = true;
						break;
					}
				}
				
				dupplicateSupplier = supplierDAO.checkDupplicateSupplierCompany(TransformModel.transSupplierModel(supplierRequestModel));
									
				if(!dupplicateSupplier) {
					
					String newLogoName = FileUploadConst.genPathFileLogoImage(supplierRequestModel.getLogo().trim());
					
					if(!StringUtils.isNullOrEmpty(supplierRequestModel.getLogo().trim())) {
						dupplicateLogoSupplier = fileProcessing.checkFileExists(FileUploadConst.PATH_Supplier_Logo.concat(newLogoName));
					}else {
						dupplicateLogoSupplier = false;
					}
					
					
					if(!dupplicateLogoSupplier) {
						int supplierId = 0, addressId = 0, userId = 0;
						String password = passwordGenerator.generate(new Random().nextInt((5 - 4) + 1) + 4);
						if(!StringUtils.isNullOrEmpty(supplierRequestModel.getLogo().trim())) {
							resultProcess = fileProcessing.CopyFile(FileUploadConst.PATH_Temp.concat(supplierRequestModel.getLogo()), FileUploadConst.PATH_Supplier_Logo.concat(newLogoName));
						}else {
							resultProcess = true;
						}
						
						if(resultProcess) {
							String LogoInPathTemp = FileUploadConst.PATH_Temp.concat(supplierRequestModel.getLogo()).trim();								
							//supplierRequestModel.setLogo(FileUploadConst.PATH_Supplier_Logo.concat(newLogoName));
							//supplierRequestModel.setLogo(ConfigurationSystemManager.getInstance().getHttpWebserverName().concat(ConfigurationSystemManager.getInstance().getHttpAliasLogoname()).concat("/").concat(newLogoName));
							if(!StringUtils.isNullOrEmpty(supplierRequestModel.getLogo().trim())) {
								supplierRequestModel.setLogo(String.format("%s",newLogoName));
							}else {
								supplierRequestModel.setLogo("");
							}								
							supplierId = supplierDAO.insertSupplier(TransformModel.transSupplierModel(supplierRequestModel));
							if(supplierId > 0) {
								resultProcess = true;
								supplierRequestModel.setSupplierId(NullUtils.cvStr(supplierId));
							}//if(supplierId > 0)
							else {
								resultProcess = false;
							}
							
							if(resultProcess) {
								
								addressId = addressDAO.insertAddress(TransformModel.transAddressModel(supplierRequestModel.getAddressId()));
								if(addressId > 0) {
									resultProcess = true;
									supplierRequestModel.getAddressId().setAddressId(NullUtils.cvStr(addressId));
								}else {
									resultProcess = false;
								}
								
								if(resultProcess) {
									
									for(UserModel user : supplierRequestModel.getUserId()) {
										user.setCreateBy(NullUtils.cvStr(userIdSession));
										user.setUpdateBy(NullUtils.cvStr(userIdSession));
										user.setPassword(password.concat(EncryptUtils.EncryptSHA256()));
										userId = userDAO.insertUser(TransformModel.transUserModel(user));
										if(userId > 0) {
											resultProcess = true;
											user.setUserId(NullUtils.cvStr(userId));
										}else {
											resultProcess = false;
											DatabaseUtils.rollback(connection);
											break;
										}
									}
									
																			
									if(resultProcess) {
										dupplicateSupplierMapping = supplierAddressMappingDAO.checkDupplicateSupplierId(TransformModel.transSupplierModel(supplierRequestModel));
										if(!dupplicateSupplierMapping) {
											resultProcess = supplierAddressMappingDAO.insertSupplierAddressMapping(TransformModel.transSupplierModel(supplierRequestModel));
										}//if(!dupplicateSupplierMapping)
										
																					
										SupplierUserMappingModel supplierUserMappingModel = new SupplierUserMappingModel();
										supplierUserMappingModel.setCreateBy(supplierRequestModel.getCreateBy());
										supplierUserMappingModel.setUpdateBy(supplierRequestModel.getUpdateBy());
										supplierUserMappingModel.setEnable("Y");
										supplierUserMappingModel.setSupplierId(supplierRequestModel.getSupplierId());
										for(UserModel user : supplierRequestModel.getUserId()) {
											supplierUserMappingModel.setUserId(user.getUserId());
											 resultProcess = supplierUserMappingDAO.insert(TransformModel.transSupplierUserMappingModel(supplierUserMappingModel));
											 if(!resultProcess) {
												 break;
											 }
										}											
										
										if(resultProcess) {
											
											List<String> receiver = new ArrayList<>();
											BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();	
											bodyEmailDTO.setPasswordReceiver(password);												
											for(UserModel user : supplierRequestModel.getUserId()) {
												receiver.add(user.getEmail());
												bodyEmailDTO.setFullnameReceiver(user.getFullname());
												bodyEmailDTO.setUsernameReceiver(user.getUsername());
											}
											MailReceiver mailReceiver = new MailReceiver();
											mailReceiver.setMailReceiver(receiver);
											resultProcess = MailConnector.sendMailRegisterUser(mailReceiver, bodyEmailDTO, "Authentification CPRam Audit Supplier");
											
											if(resultProcess) {
												if(!StringUtils.isNullOrEmpty(supplierRequestModel.getLogo().trim())) {
													fileProcessing.DeleteFile(LogoInPathTemp);
												}
												
											}
											
										}//if(resultProcess)
										
									}//if(resultProcess)		
									
								}//if(resultProcess)										
								
							}//if(resultProcess)
							
						}//if(resultProcess)
						
					}//if(!dupplicateLogoSupplier)
					
				}//if(!dupplicateSupplier)			
					
			}//if(!dupplicateUserName)
						
			if(!dupplicateUserName && !dupplicateSupplier && !dupplicateLogoSupplier && !dupplicateSupplierMapping && resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
				
			
			Map<String, Object> resultMaps = new HashMap<>();
			resultMaps.put("dupplicateUserName", dupplicateUserName);
			resultMaps.put("dupplicateSupplier", dupplicateSupplier);
			resultMaps.put("dupplicateSupplierMapping", dupplicateSupplierMapping);
			resultMaps.put("dupplicateLogoSupplier", dupplicateLogoSupplier);
			resultMaps.put("resultProcess", resultProcess);
			return resultMaps;
			
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierController.supplierInsert() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public Map<String, Object> supplierUpdate(HttpServletRequest httpServletRequest, String objSupplier) {
		Connection connection = null;
		SupplierDAO supplierDAO = null;
		AddressDAO addressDAO = null;
		Gson gson = null;
		FileProcessing fileProcessing = null;
		SessionUtils sessionUtils = null;
		boolean dupplicateSupplierCompany = true, resultProcess = true;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			addressDAO = new AddressDAO(connection);
			fileProcessing = new FileProcessing();
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			
			SupplierModel supplierRequestModel = gson.fromJson(objSupplier, SupplierModel.class);
			int userIdSession = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			supplierRequestModel.setUpdateBy(NullUtils.cvStr(userIdSession));
			supplierRequestModel.getAddressId().setUpdateBy(NullUtils.cvStr(userIdSession));
			if(!StringUtils.isNullOrEmpty(supplierRequestModel.getGreenCardExpireDate())) {
				supplierRequestModel.setGreenCardExpireDate(String.format("%s %s", supplierRequestModel.getGreenCardExpireDate(), StaticVariableUtils.timeOfExpire));
			}
			
			dupplicateSupplierCompany = supplierDAO.checkDupplicateSupplierCompanyNotInSupplierId(TransformModel.transSupplierModel(supplierRequestModel));
			if(!dupplicateSupplierCompany) {
				
				if(!StringUtils.isNullOrEmpty(supplierRequestModel.getLogo())) {
					String newLogoName = FileUploadConst.genPathFileLogoImage(supplierRequestModel.getLogo().trim());
					if(fileProcessing.checkFileExists(FileUploadConst.PATH_Temp.concat(supplierRequestModel.getLogo()))) {
						boolean result = fileProcessing.CopyFile(FileUploadConst.PATH_Temp.concat(supplierRequestModel.getLogo()), FileUploadConst.PATH_Supplier_Logo.concat(newLogoName));
						
						if(result) {
							//supplierRequestModel.setLogo(String.format("logo = '%s', ", FileUploadConst.PATH_Supplier_Logo.concat(newLogoName).replace("\\", "\\\\")));
							//System.out.println(fileProcessing.DeleteFile(supplierDAO.getLogoSupplierPath(TransformModel.transSupplierModel(supplierRequestModel)).getLogo()));
							fileProcessing.DeleteFile(FileUploadConst.PATH_Temp.concat(supplierRequestModel.getLogo()));
							//supplierRequestModel.setLogo(String.format("logo = '%s', ",ConfigurationSystemManager.getInstance().getHttpWebserverName().concat(ConfigurationSystemManager.getInstance().getHttpAliasLogoname()).concat("/").concat(newLogoName)));
							supplierRequestModel.setLogo(String.format("logo = '%s', ",newLogoName));
						}else {
							supplierRequestModel.setLogo("");
						}
					}else {
						supplierRequestModel.setLogo("");
					}
				}
				
				resultProcess = supplierDAO.updateSupplier(TransformModel.transSupplierModel(supplierRequestModel));
				if(resultProcess) {
					resultProcess= addressDAO.updateAddress(TransformModel.transAddressModel(supplierRequestModel.getAddressId()));
					
				}else {
					resultProcess = false;				
				}
			}else {
				resultProcess = false;
			}
			
			if(resultProcess && !dupplicateSupplierCompany)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			Map<String, Object> result = new HashMap<>();
			result.put("dupplicateSupplierCompany", dupplicateSupplierCompany);
			result.put("resultProcess", resultProcess);
			return result;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierController.supplierUpdate() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getSupplierList(HttpServletRequest httpServletRequest) {
		Connection connection = null;
		SupplierDAO supplierDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			StringBuilder queryWhereClause = new StringBuilder();
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN 
					|| NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA
					|| NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_SALE) {
				List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel));
				System.out.println(supplierDTOs.toString());
				queryWhereClause.append(" AND ").append(" s.supplier_id = '").append(supplierDTOs.get(0).getSupplierId()).append("' ");
			}
			
			List<SupplierDTO> supplierDTOs = supplierDAO.getSupplierList(0, supplierDAO.countSupplierList(queryWhereClause.toString()), queryWhereClause.toString());
			List<SupplierModel> supplierModels = new ArrayList<>();
			for(SupplierDTO supplierDTO : supplierDTOs) {
				List<UserDTO> userList = supplierUserMappingDAO.getUserInSupplier(supplierDTO);
				supplierDTO.setUserId(userList);
				supplierDTO.setLogo(ConfigurationSystemManager.getInstance().getHttpWebserverName().concat(ConfigurationSystemManager.getInstance().getHttpAliasLogoname()).concat("/").concat(supplierDTO.getLogo()));
				supplierModels.add(TransformDTO.transSupplierDTO(supplierDTO));
			}
			return gson.toJson(supplierModels);
		}catch(Exception e) {
			logger.error("SupplierController.getSupplierList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getSupplierDetail(HttpServletRequest httpServletRequest, String objSupplier) {
		Connection connection = null;
		SupplierDAO supplierDAO = null;
		Gson gson = null;
		SupplierAddressMappingDAO supplierAddressMappingDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		SessionUtils sessionUtils = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			supplierAddressMappingDAO = new SupplierAddressMappingDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();			
			
			StringBuilder queryWhereClause = new StringBuilder();
			
			if(!StringUtils.isNullOrEmpty(objSupplier)) {
				SupplierModel supplierModel = gson.fromJson(objSupplier, SupplierModel.class);
				if(!StringUtils.isNullOrEmpty(supplierModel.getSupplierId())) {
					queryWhereClause.append("AND s.supplier_id = ").append(supplierModel.getSupplierId()).append(" ");
				}else {
					UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
					if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN 
							|| NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA
							|| NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_SALE) {
						List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel));
						queryWhereClause.append("AND s.supplier_id = ").append(supplierDTOs.get(0).getSupplierId()).append(" ");
					}
					
				}
			}			
			
			List<SupplierDTO> supplierDTOs = supplierDAO.getSupplierList(0, supplierDAO.countSupplierList(queryWhereClause.toString()), queryWhereClause.toString());
			List<SupplierModel> supplierModels = new ArrayList<>();
			
			for (SupplierDTO supplier : supplierDTOs) {
				AddressDTO address = supplierAddressMappingDAO.getAddresWithSupplierId(supplier);
				supplier.setAddressId(address);
				supplier.setLogo(ConfigurationSystemManager.getInstance().getHttpWebserverName().concat(ConfigurationSystemManager.getInstance().getHttpAliasLogoname()).concat("/").concat(supplier.getLogo()));
				supplierModels.add(TransformDTO.transSupplierDTO(supplier));
			}
			
			return gson.toJson(supplierModels);
		}catch(Exception e) {
			logger.error("SupplierController.getSupplierDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean supplierDelete(String objSupplier) {
		Connection connection = null;
		SupplierDAO supplierDAO = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		AddressDAO addressDAO = null;
		AppointDAO appointDAO = null;
		SupplierAddressMappingDAO supplierAddressMappingDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		UserDAO userDAO = null;
		boolean resultProcess = false;
		
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			addressDAO = new AddressDAO(connection);
			supplierAddressMappingDAO = new SupplierAddressMappingDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			appointDAO = new AppointDAO(connection);
			userDAO = new UserDAO(connection);
			gson = new Gson();			
			
			SupplierModel supplierModelRequest = gson.fromJson(objSupplier, SupplierModel.class);	
			
			if(!SupplierRelationnal.checkSupplierRelational(connection, TransformModel.transSupplierModel(supplierModelRequest))) {
				String querySelectDeatilSupplier = String.format(" AND s.supplier_id = '%s'", supplierModelRequest.getSupplierId());			
				List<SupplierDTO> supplierDTOs = supplierDAO.getSupplierList(0, supplierDAO.countSupplierList(querySelectDeatilSupplier), querySelectDeatilSupplier);
				for(SupplierDTO supplier : supplierDTOs) {
					
					String queryWhereClauseSearchId = String.format(" AND sup_map.supplier_id ='%s'", supplier.getSupplierId());
					List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierProduceListMappingProductTypeBySupplier(0, supplierProductTypeAddressMappingDAO.countSupplierProduceListMappingProductTypeBySupplier(queryWhereClauseSearchId), queryWhereClauseSearchId);
					if(!supplierProductAddressMappingDTOs.isEmpty()) {
						for(SupplierProductAddressMappingDTO supplierProductAddressMapping : supplierProductAddressMappingDTOs) {
							
							int countAddressInSupplierMapping = supplierAddressMappingDAO.countAddressId(supplierProductAddressMapping.getAddressId());
							int countAddressInSupplierProductAddressMapping = supplierProductTypeAddressMappingDAO.countAddressId(supplierProductAddressMapping);
							if(countAddressInSupplierProductAddressMapping <= 1 && countAddressInSupplierMapping == 0) {
								addressDAO.deleteAddress(supplierProductAddressMapping.getAddressId());
							}
							resultProcess = supplierProductTypeAddressMappingDAO.deleteSupplierProductAddressMapping(supplierProductAddressMapping);
							if(!resultProcess) {
								break;
							}					
						}
					}else {
						resultProcess = true;
					}
					if(resultProcess) {
						
						AddressDTO addressDTO = supplierAddressMappingDAO.getAddresWithSupplierId(TransformModel.transSupplierModel(supplierModelRequest));
						if(addressDTO != null) {
							
							SupplierProductAddressMappingDTO supplierProductAddressMapping = new SupplierProductAddressMappingDTO();
							supplierProductAddressMapping.setAddressId(addressDTO);
							int countAddressInSupplierMapping = supplierAddressMappingDAO.countAddressId(addressDTO);
							int countAddressInSupplierProductAddressMapping = supplierProductTypeAddressMappingDAO.countAddressId(supplierProductAddressMapping);
							if(countAddressInSupplierProductAddressMapping == 0 && countAddressInSupplierMapping <= 1) {
								resultProcess = addressDAO.deleteAddress(addressDTO);							
							}
							
							if(resultProcess) {
								resultProcess = supplierAddressMappingDAO.deleteSupplierAddressMapping(TransformModel.transSupplierModel(supplierModelRequest));							
							}
						}else {
							resultProcess = true;
						}
						
						if(resultProcess) {
							List<UserDTO> userDTOs = supplierUserMappingDAO.getUserInSupplier(TransformModel.transSupplierModel(supplierModelRequest));
							for(UserDTO user : userDTOs) {
								resultProcess = userDAO.deleteByUserId(user);
								if(!resultProcess) {
									break;
								}							
								
							}	
						}
						List<UserDTO> userDTOs = supplierUserMappingDAO.getUserInSupplier(TransformModel.transSupplierModel(supplierModelRequest));
						if(!userDTOs.isEmpty()) {
							for(UserDTO user : userDTOs) {		
								if(user.getUserId() != 0) {
									resultProcess = UserRelational.checkUserRelational(connection, user);
									if(resultProcess) {
										resultProcess = false;
										break;
									}
								}								
								
							}
						}
						
						if(resultProcess) {
							
							resultProcess = supplierUserMappingDAO.deleteBySupplierId(TransformModel.transSupplierModel(supplierModelRequest));
						}
						
						if(resultProcess) {
							resultProcess = supplierDAO.deleteSupplier(TransformModel.transSupplierModel(supplierModelRequest));
							
						}
						
						if(resultProcess) {
							StringBuilder queryWhereClause = new StringBuilder();
							queryWhereClause.append(" AND sup.supplier_id = '").append(supplierModelRequest.getSupplierId()).append("' ");
							List<AppointDTO> appointDTOs = appointDAO.getAppointList(0, appointDAO.countAppointList(queryWhereClause.toString()), queryWhereClause.toString());
							if(appointDTOs != null && !appointDTOs.isEmpty()) {
								for(AppointDTO appoint : appointDTOs) {
									resultProcess = appointDAO.deleteAppoint(appoint);
									if(!resultProcess) {
										resultProcess = false;
										break;
									}
								}
							}
						}
											
					}else {					
						break;
					}
				}
			}
			
			
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierController.supplierDelete() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean uploadLogo(MultipartFormDataInput multipartFormDataInput) {
		FileUpload fileUpload = null;
		FileProcessing fileProcessing = null;
		boolean resultWrite = true;
		String fileName = null;
		int chunk = 0;
		long size = 0;
		try {
			
			fileUpload = new FileUpload(multipartFormDataInput);
			fileProcessing = new FileProcessing();
			
			List<InputPart> logoParts = fileUpload.getFormDataMap("logo_supplier");
			
			for(InputPart inputPart : logoParts) {
				MultivaluedMap<String, String> header = fileUpload.getHeader(inputPart);
				
				
				fileName = fileUpload.getFileName(header);
				
				InputStream logoInputStream = fileUpload.getBody(inputPart);
				InputStream logoInputStreamToRead = fileUpload.getBody(inputPart);
				
				String pathFile = FileUploadConst.PATH_Temp.concat(fileName);
				byte[] buffer = new byte[1024];
				while((chunk = logoInputStreamToRead.read(buffer)) != -1) {
					size += chunk;
				}
				if(size > 300000) {
					BufferedImage img = ImageIO.read(logoInputStream);
					Image scaledImage = img.getScaledInstance(280, 280, Image.SCALE_SMOOTH);
			        BufferedImage imageBuff = new BufferedImage(280, 280, BufferedImage.TYPE_INT_RGB);
			        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
			        
			        resultWrite = ImageIO.write(imageBuff, "png", new File(pathFile.trim()));
				}else {
					resultWrite = fileProcessing.InputStreamToFile(logoInputStream, pathFile);
				}				
				if (!resultWrite)
					break;
				
			}
			return resultWrite;
		}catch(Exception e) {
			logger.error("SupplierController.uploadLogo() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	public boolean deleteLogo(String fileName) {
		FileProcessing writeFile = null;
		try {
			writeFile = new FileProcessing();
			return writeFile.DeleteFile(FileUploadConst.PATH_Temp.concat(fileName));
			
		}catch(Exception e) {
			logger.error("SupplierController.deleteLogo() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	/*
	 * 
	 * public boolean uploadLogo(MultipartFormDataInput inputForm) {
		FileUpload fileUpload = null;
		WriteFile writeFile = null;
		try {
			boolean resultWrite = true;
			String fileName = "";
			fileUpload = new FileUpload(inputForm);
			writeFile = new WriteFile();
			List<InputPart> logoParts = fileUpload.getFormDataMap("logo_supplier");
			
			for (InputPart logoPart : logoParts) {
				MultivaluedMap<String, String> header = fileUpload.getHeader(logoPart);
				fileName = fileUpload.getFileName(header);

				InputStream logoInputStream = fileUpload.getBody(logoPart);
				String pathFile = FileUploadConst.PATH_Temp.concat(fileName);
				resultWrite = writeFile.InputStreamToFile(logoInputStream, pathFile);
				if (!resultWrite)
					break;
			}
			
			
			return resultWrite;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	 * 
	 * 
	 * 
	 * 
	 * public DataTableModel<SupplierModel> getSupplierList(DataTablePostParamModel modelDataTable) {
		SupplierDAO supplierDAO = null;
		try {

			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);

			DataTableModel<SupplierModel> dataTableModel = new DataTableModel<>();

			StringBuilder sbWhereCause = new StringBuilder();
			for (Column col : modelDataTable.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {

					if (col.getName().equals("supplierCode")
							&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.supplier_code").append(" like ").append(" '%")
								.append(col.getSearch().getValue()).append("%'  ");

					} else if (col.getName().equals("statusId")
							&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.status_id").append(" = '").append(col.getSearch().getValue())
								.append("' ");

					} else if (col.getName().equals("supplierCompany")
							&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.supplier_company").append(" like ").append(" '%")
								.append(col.getSearch().getValue()).append("%'  ");
					}

					else if (col.getName().equals("isGreenCard")
							&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.is_green_card").append(" = '").append(col.getSearch().getValue())
								.append("' ");
					}

					else if (col.getName().equals("email") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("u.email").append(" like ").append(" '%").append(col.getSearch().getValue())
								.append("%'  ");
					}

					else if (col.getName().equals("telephone")
							&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("u.telephone").append(" like ").append(" '%")
								.append(col.getSearch().getValue()).append("%'  ");
					}

					else if (col.getName().equals("fullName")
							&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("u.fullName").append(" like ").append(" '%")
								.append(col.getSearch().getValue()).append("%'  ");
					}
				}
			}

			List<SupplierDTO> supplierDTOs = supplierDAO.getSupplierList(modelDataTable.getStart(),	modelDataTable.getLength(), sbWhereCause.toString());
			dataTableModel.setData(new ArrayList<>());

			for (SupplierDTO supplierDTO : supplierDTOs) {
				dataTableModel.getData().add(TransformDTO(supplierDTO));
			}

			Integer totalRecord = supplierDAO.countSupplier(sbWhereCause.toString());
			dataTableModel.setRecordsFiltered(totalRecord);
			dataTableModel.setRecordsTotal(totalRecord);
			dataTableModel.setDraw(modelDataTable.getDraw());
			return dataTableModel;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		} finally {
			if (supplierDAO != null)
				supplierDAO.closeConnection();
		}
	}

	public List<SupplierModel> getSupplierList() {
		SupplierDAO supplierDAO = null;
		try {

			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			List<SupplierDTO> supplierDTOs = supplierDAO.getSupplierList();

			List<SupplierModel> supplierModels = new ArrayList<>();
			for (SupplierDTO supplierDTO : supplierDTOs) {
				supplierModels.add(TransformDTO(supplierDTO));
			}

			return supplierModels;
		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		} finally {
			if (supplierDAO != null)
				supplierDAO.closeConnection();
		}
	}

	public SupplierModel getSupplierDetail(String _supplierId) {
		SupplierDAO supplierDAO = null;
		try {
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			SupplierDTO supplier = supplierDAO.getSupplierDetail(_supplierId);

			SupplierModel supplierModel = TransformDTO(supplier);

			return supplierModel;

		} catch (Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		} finally {
			if (supplierDAO != null)
				supplierDAO.closeConnection();
		}
	}

	public DataTableModel<SupplierModel> dataTableSupplierByProduct(DataTablePostParamModel param) {
		SupplierDAO supplierDAO = null;
		try {
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);

			DataTableModel<SupplierModel> dataTableModel = new DataTableModel<SupplierModel>();

			List<SupplierDTO> supplierDTOList = supplierDAO.getSupplierByProduct(param);
			dataTableModel.setData(new ArrayList<SupplierModel>());

			for (SupplierDTO dto : supplierDTOList) {
				dataTableModel.getData().add(TransformDTO(dto));
			}

			Integer totalRecord = supplierDAO.countSupplierByProductId(param);
			dataTableModel.setRecordsFiltered(totalRecord);
			dataTableModel.setRecordsTotal(totalRecord);

			return dataTableModel;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (supplierDAO != null) {
				supplierDAO.closeConnection();
			}
		}
	}

	public DataTableModel<SupplierModel> dataTableSupplierForProduct(DataTablePostParamModel param) {
		SupplierDAO supplierDAO = null;
		try {
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);

			DataTableModel<SupplierModel> dataTableModel = new DataTableModel<SupplierModel>();

			String productId = "";
			StringBuilder sb = new StringBuilder();

			for (Column col : param.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					if (col.getName().equals("product_id")) {
						productId = col.getSearch().getValue();
						continue;
					} else {
						if (col.getName().equals("supplier_id")
								&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
							sb.append(" AND ");
							sb.append("s.supplier_id").append(" = '").append(col.getSearch().getValue()).append("' ");
						} else if (col.getName().equals("supplier_code")
								&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
							sb.append(" AND ");
							sb.append("s.supplier_code").append(" like ").append(" '%")
									.append(col.getSearch().getValue()).append("%'  ");
						} else if (col.getName().equals("email")
								&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
							sb.append(" AND ");
							sb.append("s.email").append(" like ").append(" '%").append(col.getSearch().getValue())
									.append("%'  ");
						} else if (col.getName().equals("telephone")
								&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
							sb.append(" AND ");
							sb.append("s.telephone").append(" like ").append(" '%").append(col.getSearch().getValue())
									.append("%'  ");
						} else if (col.getName().equals("status_id")
								&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
							sb.append(" AND ");
							sb.append("s.status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
						} else if (col.getName().equals("supplier_company")
								&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
							sb.append(" AND ");
							sb.append("s.supplier_company").append(" like ").append(" '%")
									.append(col.getSearch().getValue()).append("%'  ");
						} else if (col.getName().equals("is_green_card")
								&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
							sb.append(" AND ");
							sb.append("s.is_green_card").append(" = '").append(col.getSearch().getValue()).append("' ");
						}
					}
				}
			}

			List<SupplierDTO> supplierDTOList = supplierDAO.getSupplierForProduct(productId, param.getStart(),
					param.getLength(), sb.toString());
			dataTableModel.setData(new ArrayList<SupplierModel>());

			for (SupplierDTO dto : supplierDTOList) {
				dataTableModel.getData().add(TransformDTO(dto));
			}

			Integer totalRecord = supplierDAO.countSupplierForProduct(productId, sb.toString());
			dataTableModel.setRecordsFiltered(totalRecord);
			dataTableModel.setRecordsTotal(totalRecord);

			return dataTableModel;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (supplierDAO != null) {
				supplierDAO.closeConnection();
			}
		}
	}

	public SupplierModel TransformDTO(SupplierDTO supplierDTO) {

		SupplierModel supplierModel = new SupplierModel();
		supplierModel.setSupplierId(NullUtils.cvStr(supplierDTO.getSupplierId()));
		supplierModel.setSupplierCode(NullUtils.cvStr(supplierDTO.getSupplierCode()));
		// supplierModel.setEmail(NullUtils.cvStr(supplierDTO.getEmail()));
		// supplierModel.setTelephone(NullUtils.cvStr(supplierDTO.getTelephone()));
		//supplierModel.setStatusId(NullUtils.cvStr(supplierDTO.getStatusId()));
		supplierModel.setCreateBy(NullUtils.cvStr(supplierDTO.getCreateBy()));
		supplierModel.setCreateDate(NullUtils.cvStr(supplierDTO.getCreateDate()));
		supplierModel.setUpdateBy(NullUtils.cvStr(supplierDTO.getUpdateBy()));
		supplierModel.setUpdateDate(NullUtils.cvStr(supplierDTO.getUpdateDate()));
		supplierModel.setSupplierCompany(NullUtils.cvStr(supplierDTO.getSupplierCompany()));
		supplierModel.setIsGreenCard(NullUtils.cvStr(supplierDTO.getIsGreenCard()));
		//supplierModel.setUserId(new ArrayList<>());

		if (supplierDTO.getUserId() != null) {
			for (UserDTO user : supplierDTO.getUserId()) {

				UserModel userModel = new UserModel();
				userModel.setUserId(NullUtils.cvStr(user.getUserId()));
				userModel.setEmployeeId(NullUtils.cvStr(user.getEmployeeId()));
				userModel.setUserParent(NullUtils.cvStr(user.getUserParent()));
				userModel.setUsername(NullUtils.cvStr(user.getUsername()));
				userModel.setPassword(NullUtils.cvStr(user.getPassword()));
				//userModel.setFullName(NullUtils.cvStr(user.getFullName()));
				userModel.setEmail(NullUtils.cvStr(user.getEmail()));
				userModel.setTelephone(NullUtils.cvStr(user.getTelephone()));
				//userModel.setStatusId(NullUtils.cvStr(user.getStatusId()));
				userModel.setUpdateDate(NullUtils.cvStr(user.getUpdateDate()));

				if (user.getUserGroupId() != null) {
					UserGroupModel userGroupModel = new UserGroupModel();
					userGroupModel.setUserGroupId(NullUtils.cvStr(user.getUserGroupId().getUserGroupId()));
					userGroupModel.setUserGroupName(NullUtils.cvStr(user.getUserGroupId().getUserGroupName()));
					userModel.setUserGroupId(userGroupModel);
				}

				supplierModel.getUserId().add(userModel);
			}
		}

		return supplierModel;
	}

	public boolean deleteSupplier(String _supplierObj) {
		SupplierDAO supplierDAO = null;
		UserDAO userDAO = null;
		SupplierAddressDAO supplierAddressDAO = null;
		Gson _gson = null;
		boolean resultProcess = true;
		try {
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			userDAO = new UserDAO(connection);
			supplierAddressDAO = new SupplierAddressDAO(connection);
			_gson = new Gson();
			
			SupplierModel supplierModel = _gson.fromJson(_supplierObj, SupplierModel.class);
			
			
			for(UserModel userModel : supplierModel.getUserId()) {
				resultProcess = userDAO.deleteByUserParent(TransformModel.transUserModel(userModel));
				if(!resultProcess) {
					DatabaseUtils.rollback(connection);
					break;
				}
			}
			
			if(resultProcess) {
				
				for(UserModel userModel : supplierModel.getUserId()) {
					resultProcess = userDAO.deleteByUserId(TransformModel.transUserModel(userModel));
					if(!resultProcess) {
						DatabaseUtils.rollback(connection);
						break;
					}
				}
				
				if(resultProcess) {
					resultProcess = supplierAddressDAO.deleteBySupplierId(TransformModel.transSupplierModel(supplierModel));
					if(resultProcess) {
						
						resultProcess = supplierDAO.delete(TransformModel.transSupplierModel(supplierModel));
						if(!resultProcess)
							DatabaseUtils.rollback(connection);
						
					}else {
						DatabaseUtils.rollback(connection);
					}
				}
				
			}
			
			return resultProcess;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (supplierDAO != null)
				supplierDAO.closeConnection();
			if(supplierAddressDAO != null)
				supplierAddressDAO.closeConnection();
			if(userDAO != null)
				userDAO.closeConnection();
		}
	}

	public boolean updateSupplier(HttpServletRequest request, String _jsonString) {
		SupplierDAO supplierDAO = null;
		UserDAO userDAO = null;
		Gson gson = null;
		boolean resultProcess = true;
		try {
			HttpServletRequest req = (HttpServletRequest) request;
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			userDAO = new UserDAO(connection);
			gson = new Gson();
			SupplierModel supplierJson = gson.fromJson(_jsonString, SupplierModel.class);

			UserModel userSession = (UserModel) req.getSession().getAttribute("sesionAuthen");
			String userId = NullUtils.cvStr(userSession.getUserId());
			supplierJson.setCreateBy(userId);
			supplierJson.setUpdateBy(userId);

			for (UserModel userModel : supplierJson.getUserId()) {
				resultProcess = userDAO.update(TransformModel.transUserModel(userModel));
				if (!resultProcess) {
					DatabaseUtils.rollback(connection);
					break;
				}
			}
			if (resultProcess) {
				resultProcess = supplierDAO.UpdateSupplier(TransformModel.transSupplierModel(supplierJson));
				if (!resultProcess)
					DatabaseUtils.rollback(connection);
			}
			return resultProcess;

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (supplierDAO != null)
				supplierDAO.closeConnection();
		}
	}

	public Map<String, Object> insertSupplier(HttpServletRequest request, String _stringJsonModel) {
		SupplierDAO supplierDAO = null;
		UserDAO userDAO = null;
		SupplierAddressDAO supplierAddressDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		Gson gson = null;
		WriteFile writeFile = null;
		boolean checkDupplicateUserName = false, checkDupplicateSupplier = false, resultOfProcess = true, checkDupplicateLogoSupplier = false;

		try {
			HttpServletRequest req = (HttpServletRequest) request;
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			userDAO = new UserDAO(connection);
			supplierAddressDAO = new SupplierAddressDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			writeFile = new WriteFile();
			gson = new Gson();

			SupplierModel supplierModel = gson.fromJson(_stringJsonModel, SupplierModel.class);
			UserModel userSession = (UserModel) req.getSession().getAttribute("sesionAuthen");
			String userId = NullUtils.cvStr(userSession.getUserId());
			supplierModel.setCreateBy(userId);
			String shaString = EncryptUtils.EncryptSHA256();

			for (UserModel userSupplier : supplierModel.getUserId()) {
				checkDupplicateUserName = userDAO.getUserName(userSupplier.getUsername());
				if (checkDupplicateUserName)
					break;
			}
			checkDupplicateSupplier = supplierDAO.checkDupplicateSupplier(supplierModel);
			// checkDupplicateUserName =
			// userDAO.getUserName(supplierJson.getUserId().getUsername().trim());
			if (!checkDupplicateUserName && !checkDupplicateSupplier) {
				
				checkDupplicateLogoSupplier = writeFile.checkFileExists(FileUploadConst.PATH_Supplier_Logo.concat(supplierModel.getLogo()));
				if (!checkDupplicateLogoSupplier) {
					List<Integer> userIdInsert = new ArrayList<>();
					for (UserModel userModel : supplierModel.getUserId()) {
						userModel.setPassword(userModel.getPassword().concat(shaString));
						Map<String, Object> resultUserDAO = userDAO.insert(userModel);
						if (!(boolean) resultUserDAO.get("result")) {
							resultOfProcess = false;
							break;
						}
						userIdInsert.add(NullUtils.cvInt(resultUserDAO.get("userId")));
					}

					if (resultOfProcess) {
						String newFileName = FileUploadConst.genPathFileLogoImage(supplierModel.getLogo()).toString().trim();
						resultOfProcess = writeFile.CopyFile(FileUploadConst.PATH_Temp.concat(supplierModel.getLogo()),FileUploadConst.PATH_Supplier_Logo.concat(newFileName));
						if (resultOfProcess) {							
							writeFile.DeleteFile(FileUploadConst.PATH_Temp.concat(supplierModel.getLogo()));
							supplierModel.setLogo(FileUploadConst.PATH_Supplier_Logo.concat(newFileName));
							Map<String, Object> resultInsertSupplier = supplierDAO.InsertSupplier(supplierModel,userId);
							resultOfProcess = (boolean) resultInsertSupplier.get("result");
							int supplierID = (int) resultInsertSupplier.get("supplierId");
							if (resultOfProcess) {
								for (SupplierAddressModel supplierAddressModel : supplierModel.getAddressId()) {
									supplierAddressModel.setSupplierId(NullUtils.cvStr(supplierID));
									supplierAddressModel.setCreateBy(NullUtils.cvStr(userId));
									supplierAddressModel.setUpdateBy(NullUtils.cvStr(userId));
									resultOfProcess = supplierAddressDAO.insert(supplierAddressModel);
									if (!resultOfProcess)
										break;
								}
								if (resultOfProcess) {

									for (Integer userIdInt : userIdInsert) {
										SupplierUserMappingDTO supplierUserMappingDTO = new SupplierUserMappingDTO();
										supplierUserMappingDTO.setSupplierId(supplierID);
										supplierUserMappingDTO.setUserId(userIdInt);
										supplierUserMappingDTO
												.setStatusId(NullUtils.cvInt(supplierModel.getStatusId()));
										supplierUserMappingDTO.setCreateBy(NullUtils.cvInt(userId));
										supplierUserMappingDTO.setUpdateBy(NullUtils.cvInt(userId));
										resultOfProcess = supplierUserMappingDAO.insert(supplierUserMappingDTO);
										if (!resultOfProcess)
											break;
									}

									if (!resultOfProcess)
										DatabaseUtils.rollback(connection);

								} else {
									DatabaseUtils.rollback(connection);
								}
							} else {
								DatabaseUtils.rollback(connection);
							}
						} else {
							DatabaseUtils.rollback(connection);
						}
					} else {
						DatabaseUtils.rollback(connection);
					}
				}
				// resultProcess = supplierDAO.InsertSupplier(supplierModel, userId);
			}

			Map<String, Object> returnResult = new HashMap<>();
			returnResult.put("dupplicateLogoSupplier", checkDupplicateLogoSupplier);
			returnResult.put("duplicateUser", checkDupplicateUserName);
			returnResult.put("dupplicateSupplier", checkDupplicateSupplier);
			
			returnResult.put("resultProcess", resultOfProcess);
			return returnResult;
			// return

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (supplierDAO != null)
				supplierDAO.closeConnection();
		}
	}

	public boolean uploadLogo(MultipartFormDataInput inputForm) {
		FileUpload fileUpload = null;
		WriteFile writeFile = null;
		try {
			boolean resultWrite = true;
			String fileName = "";
			fileUpload = new FileUpload(inputForm);
			writeFile = new WriteFile();
			List<InputPart> logoParts = fileUpload.getFormDataMap("logo_supplier");
			
			for (InputPart logoPart : logoParts) {
				MultivaluedMap<String, String> header = fileUpload.getHeader(logoPart);
				fileName = fileUpload.getFileName(header);

				InputStream logoInputStream = fileUpload.getBody(logoPart);
				String pathFile = FileUploadConst.PATH_Temp.concat(fileName);
				resultWrite = writeFile.InputStreamToFile(logoInputStream, pathFile);
				if (!resultWrite)
					break;
			}
			
			
			return resultWrite;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}*/

}
