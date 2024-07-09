package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.dao.ManualDocumentDAO;
import th.co.gosoft.audit.cpram.dto.ManualDocumentDTO;
import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.model.ManualDocumentModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class ManualDocumentController {
	private final static Logger logger = Logger.getLogger(ManualDocumentController.class);
	
	
	public String getManualDocumentList(){
		Connection connection = null;
		Gson gson = null;
		ManualDocumentDAO ManualDocumentDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			ManualDocumentDAO = new ManualDocumentDAO(connection);
			List<ManualDocumentDTO> ManualDocumentDTOs = ManualDocumentDAO.getManualDocumentList();			
			List<ManualDocumentModel> ManualDocumentModels = new ArrayList<>();
			
			for(ManualDocumentDTO ManualDocumentDTO : ManualDocumentDTOs) {
				ManualDocumentModels.add(TransformDTO.transDocumentDTO(ManualDocumentDTO));
			}
			return gson.toJson(ManualDocumentModels);
			
		}catch (Exception e) {
			logger.error("DocumentController.getManualDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	
	public boolean insertManualDocumentList(HttpServletRequest httpServletRequest, String ManualDocumentListObj) {
		Connection connection = null;
		Gson gson = null;
		ManualDocumentDAO ManualDocumentDAO = null;
		SessionUtils sessionUtils = null;
		FileProcessing fileProcessing = null;
		boolean resultProcess = false;
		logger.info("Start Controller");
		
		
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			ManualDocumentDAO = new ManualDocumentDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			fileProcessing = new FileProcessing();
			gson = new Gson();
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			ManualDocumentModel[] ManualDocumentDocumentModelRequest = gson.fromJson(ManualDocumentListObj, ManualDocumentModel[].class);
			
//			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_USER) {
				
//				SupplierDTO supplierDTO = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel)).get(0);
				
				for(int i = 0; i<ManualDocumentDocumentModelRequest.length; i++) {
					logger.info("Round: " +i);
					String filePathTemp = FileUploadConst.PATH_Temp.concat(ManualDocumentDocumentModelRequest[i].getDocumentlocation());
					String filePathStandardDocument = FileUploadConst.PATH_Manual_Document.concat(NullUtils.cvStr(ManualDocumentDocumentModelRequest[i].getDocumentlocation()));
					String urlAccessFilePath = String.format("%s%s%s", ConfigurationSystemManager.getInstance().getHttpWebserverName(), ConfigurationSystemManager.getInstance().getHttpAliasManualDocument(), NullUtils.cvStr(ManualDocumentDocumentModelRequest[i].getDocumentlocation()));
					
					resultProcess = fileProcessing.CopyFile(filePathTemp, filePathStandardDocument);
					logger.info(" filePathTemp: " + filePathTemp);		
					logger.info(" filePathStandardDocument: " + filePathStandardDocument);		
					logger.info(" fileProcessing: " + resultProcess);
					if(resultProcess) {
						logger.info(" File Is Pass ");
						ManualDocumentDocumentModelRequest[i].setDocumentlocation(urlAccessFilePath);
						ManualDocumentDTO ManualDocumentDTO = TransformModel.transManualDocumentModel(ManualDocumentDocumentModelRequest[i]);
						ManualDocumentDTO.setCreateBy(NullUtils.cvInt(userSessionModel.getUserId()));
						ManualDocumentDTO.setUpdateBy(NullUtils.cvInt(userSessionModel.getUserId()));
						resultProcess = ManualDocumentDAO.insertManualDocument(ManualDocumentDTO);
						if(resultProcess == true) {
							
						}else {
							logger.info(" Query Is Error ");
							fileProcessing.DeleteFile(filePathTemp);
						}
					}
					
				}
//			}else {
//					resultProcess = false;
//			}
					
			if(resultProcess) {
				logger.error("Pass");
				DatabaseUtils.commit(connection);
			}else {
				logger.error("no Pass");
				DatabaseUtils.rollback(connection);
			}
			
			
			return resultProcess;
	
		}catch (Exception e) {
			logger.error("ManualDocumentController.InsertManualDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
		
		
		
	}
	
	
	
	
	public boolean deleteManualDocument(int manualDocumentId) {
		Connection connection = null;
		ManualDocumentDAO ManualDocumentDAO = null;
		FileProcessing fileProcessing = null;
		boolean resultProcess = false;
		String manualDocumentName = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			ManualDocumentDAO = new ManualDocumentDAO(connection);
			fileProcessing = new FileProcessing();
			if(manualDocumentId != 0) {
				manualDocumentName =  ManualDocumentDAO.SelectManualDocument(manualDocumentId);
				resultProcess = ManualDocumentDAO.deleteManualDocument(manualDocumentId);			
				if(resultProcess && manualDocumentName != null) {
					String[] DocName = manualDocumentName.split("/");
					
					String pathManualDocument = FileUploadConst.PATH_Manual_Document.concat(DocName[DocName.length-1]);
					logger.info(pathManualDocument);
					if(!StringUtils.isEmptyOrWhitespaceOnly(manualDocumentName)) {
						resultProcess = fileProcessing.DeleteFile(pathManualDocument);
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
				resultProcess = false;
			}
			
			return resultProcess;
			
			
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ManualDocumentController.deleteManualDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
}
