package th.co.gosoft.audit.cpram.controller;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.dao.SupplierStandardDocumentDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierStandardDocumentDTO;
import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.model.SupplierStandardDocumentModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class SupplierStandardDocumentController {
	private final static Logger logger = Logger.getLogger(SupplierStandardDocumentController.class);
	
	
	public String getSupplierStandardDocumentList(HttpServletRequest httpServletRequest, String objSupplierStandardDocument) {
		Connection connection = null;
		SessionUtils sessionUtils = null;
		SupplierStandardDocumentDAO supplierStandardDocumentDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		Gson gson = null;
		try {
			
			sessionUtils = new SessionUtils(httpServletRequest);
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierStandardDocumentDAO = new SupplierStandardDocumentDAO(connection);
			gson = new Gson();
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);			
			StringBuilder queryWhereClause = new StringBuilder();
			
			SupplierStandardDocumentModel supplierStandardDocumentModel = gson.fromJson(objSupplierStandardDocument, SupplierStandardDocumentModel.class);
			if(supplierStandardDocumentModel.getSupplierId() == null) {				
				UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
				
				queryWhereClause.append(" AND sup_std_doc.enable = 'Y' ");
				if(userSessionModel.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN)) || userSessionModel.getUserGroupId().getUserGroupId().equals(NullUtils.cvStr(Config.AUTHEN_USERTYPE_SUPPLIER_QA))) {
					List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel));
					for(SupplierDTO supplierDTO : supplierDTOs) {
						queryWhereClause.append(" AND sup_std_doc.supplier_id = '").append(supplierDTO.getSupplierId()).append("' ");
					}
				}
				
			}else {
				queryWhereClause.append(" AND sup_std_doc.enable = 'Y' ");
				queryWhereClause.append(" AND sup_std_doc.supplier_id = '").append(supplierStandardDocumentModel.getSupplierId().getSupplierId()).append("' ");				
			}		
			
			

			List<SupplierStandardDocumentDTO> supplierStandardDocumentDTOs = supplierStandardDocumentDAO.getSupplierStandardDocumentList(0, supplierStandardDocumentDAO.countSupplierStandardDocumentList(queryWhereClause.toString()), queryWhereClause.toString());
			List<SupplierStandardDocumentModel> supplierStandardDocumentModels = new ArrayList<>();
			for(SupplierStandardDocumentDTO supplierStandardDocument : supplierStandardDocumentDTOs) {
				supplierStandardDocumentModels.add(TransformDTO.transSupplierStandardDocumentDTO(supplierStandardDocument));
			}	
			
			return gson.toJson(supplierStandardDocumentModels);
			
		}catch (Exception e) {
			logger.error("SupplierStandardDocumentController.getSupplierStandardDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	
	public boolean insertSupplierStandardDocumentList(HttpServletRequest httpServletRequest, String supplierStandardDocumentListObj) {
		Connection connection = null;
		Gson gson = null;
		SupplierStandardDocumentDAO supplierStandardDocumentDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		SessionUtils sessionUtils = null;
		FileProcessing fileProcessing = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierStandardDocumentDAO = new SupplierStandardDocumentDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			fileProcessing = new FileProcessing();
			gson = new Gson();
			
			Type listType = new TypeToken<ArrayList<SupplierStandardDocumentModel>>(){}.getType();
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			List<SupplierStandardDocumentModel> supplierStandardDocumentModels = gson.fromJson(supplierStandardDocumentListObj, listType);
			
			int supplierStandardDocumentExpireDay = ConfigurationSystemManager.getInstance().getDateTimeSupplierStandardDocumentExpireDay();
			LocalDate localDateExpireDocument = LocalDate.now().plusDays(supplierStandardDocumentExpireDay);
			
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
				SupplierDTO supplierDTO = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel)).get(0);
				if(!supplierStandardDocumentModels.isEmpty()) {
					for(SupplierStandardDocumentModel supplierStandardDocument : supplierStandardDocumentModels) {
						supplierStandardDocument.setCreateBy(userSessionModel.getUserId());
						supplierStandardDocument.setUpdateBy(userSessionModel.getUserId());
						supplierStandardDocument.setSupplierId(TransformDTO.transSupplierDTO(supplierDTO));
						String filePathTemp = FileUploadConst.PATH_Temp.concat(supplierStandardDocument.getSupplierStandardDocumentLocation());
						String filePathStandardDocument = FileUploadConst.PATH_Supplier_Standard_Document.concat(NullUtils.cvStr(supplierDTO.getSupplierId()).concat("_").concat(supplierStandardDocument.getSupplierStandardDocumentLocation()));
						String urlAccessFilePath = String.format("%s%s%s%s%s", ConfigurationSystemManager.getInstance().getHttpWebserverName(), ConfigurationSystemManager.getInstance().getHttpAliasSupplierDocument(), NullUtils.cvStr(supplierDTO.getSupplierId()), "_", supplierStandardDocument.getSupplierStandardDocumentLocation());
						
						resultProcess = fileProcessing.CopyFile(filePathTemp, filePathStandardDocument);
						if(resultProcess) {
							supplierStandardDocument.setSupplierStandardDocumentLocation(urlAccessFilePath);
							supplierStandardDocument.setSupplierStandardDocumentExpireDate(String.format("%s 00:00:00", localDateExpireDocument.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
							resultProcess = supplierStandardDocumentDAO.insertSupplierStandardDocument(TransformModel.transSupplierStandardDocumentModel(supplierStandardDocument));
							if(!resultProcess) {
								break;
							}else {
								fileProcessing.DeleteFile(filePathTemp);
							}
						}else {
							break;
						}
					}					
					
				}
			}else {
				resultProcess = false;
			}
			
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierStandardDocumentController.insertSupplierStandardDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public boolean updateExpireDateSupplierStandardDocument(HttpServletRequest httpServletRequest, String supplierStandardDocumentObj) {
		Connection connection = null;
		Gson gson = null;
		SupplierStandardDocumentDAO supplierStandardDocumentDAO = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			supplierStandardDocumentDAO = new SupplierStandardDocumentDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			SupplierStandardDocumentModel supplierStandardDocumentModelRequest = gson.fromJson(supplierStandardDocumentObj, SupplierStandardDocumentModel.class);
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND sup_std_doc.supplier_standard_document_id = '").append(supplierStandardDocumentModelRequest.getSupplierStandardDocumentId()).append("' ");
			List<SupplierStandardDocumentDTO> supplierStandardDocumentDTOList = supplierStandardDocumentDAO.getSupplierStandardDocumentList(0, supplierStandardDocumentDAO.countSupplierStandardDocumentList(queryWhereClause.toString()), queryWhereClause.toString());
			if(supplierStandardDocumentDTOList.size() > 0) {
				
				SupplierStandardDocumentDTO supplierStandardDocumentDTO = TransformModel.transSupplierStandardDocumentModel(supplierStandardDocumentModelRequest);
				supplierStandardDocumentDTO.setUpdateBy(NullUtils.cvInt(userSessionModel.getUserId()));
				
				resultProcess = supplierStandardDocumentDAO.updateExpireDateSupplierStandardDocument(supplierStandardDocumentDTO);		
				if(resultProcess) {
					DatabaseUtils.commit(connection);
				}else {
					DatabaseUtils.rollback(connection);
				}
				
			}else {
				logger.error("!!!! Not Found Data Detail Of [supplier_standard_document_id] : "+supplierStandardDocumentModelRequest.getSupplierStandardDocumentId()+" !!!!");
			}			
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierStandardDocumentController.updateExpireDateSupplierStandardDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public boolean deleteSupplierStandardDocument(int supplierStandardDocumentId) {
		Connection connection = null;
		SupplierStandardDocumentDAO supplierStandardDocumentDAO = null;
		StringBuilder queryWhereClause = null;
		FileProcessing fileProcessing = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierStandardDocumentDAO = new SupplierStandardDocumentDAO(connection);
			queryWhereClause = new StringBuilder();
			fileProcessing = new FileProcessing();
			if(supplierStandardDocumentId != 0) {
				queryWhereClause.append(" AND sup_std_doc.supplier_standard_document_id = '").append(supplierStandardDocumentId).append("' ");			
				SupplierStandardDocumentDTO supplierStandardDocumentDetail = supplierStandardDocumentDAO.getSupplierStandardDocumentList(0, supplierStandardDocumentDAO.countSupplierStandardDocumentList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
				
				resultProcess = supplierStandardDocumentDAO.deleteSupplierStandardDocument(supplierStandardDocumentDetail);
				if(resultProcess) {
					String filenameStandardDocument = supplierStandardDocumentDetail.getSupplierStandardDocumentLocation().split("/")[supplierStandardDocumentDetail.getSupplierStandardDocumentLocation().split("/").length-1].trim();
					String pathStandardDocument = FileUploadConst.PATH_Supplier_Standard_Document.concat(filenameStandardDocument);
					if(!StringUtils.isEmptyOrWhitespaceOnly(filenameStandardDocument)) {
						resultProcess = fileProcessing.DeleteFile(pathStandardDocument);
					}else {
						resultProcess = false;
					}
				}
				
				if(resultProcess) {
					DatabaseUtils.commit(connection);
				}else {
					DatabaseUtils.rollback(connection);
				}
			}else {
				resultProcess = true;
			}
			
			return resultProcess;
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierStandardDocumentController.deleteSupplierStandardDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	
	
}
