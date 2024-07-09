package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.DocumentDAO;
import th.co.gosoft.audit.cpram.dto.DocumentDTO;
import th.co.gosoft.audit.cpram.model.DocumentModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class DocumentController {
	
	private final static Logger logger = Logger.getLogger(DocumentController.class);
	

	
	public String getDocumentList(){
		Connection connection = null;
		Gson gson = null;
		DocumentDAO DocumentDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			DocumentDAO = new DocumentDAO(connection);
			List<DocumentDTO> DocumentDTOs = DocumentDAO.getDocumentList();			
			List<DocumentModel> DocumentModels = new ArrayList<>();
			
			for(DocumentDTO DocumentDTO : DocumentDTOs) {
				DocumentModels.add(TransformDTO.transDocumentDTO(DocumentDTO));
			}
			return gson.toJson(DocumentModels);
		}catch (Exception e) {
			logger.error("DocumentController.getDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	
	
	public boolean updateDocument(HttpServletRequest httpServletRequest, String DocumentObj) {
		Connection connection = null;
		Gson gson = null;
		DocumentDAO DocumentDAO = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = false;
		
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			DocumentDAO = new DocumentDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			DocumentModel[] DocumentModelRequest = gson.fromJson(DocumentObj, DocumentModel[].class);
			
			for(int i = 0; i<DocumentModelRequest.length; i++) {
				DocumentDTO DocumentDTO = TransformModel.transDocumentModel(DocumentModelRequest[i]);
				DocumentDTO.setUpdateBy(NullUtils.cvInt(userSessionModel.getUserId()));	
				resultProcess = DocumentDAO.updateDocument(DocumentDTO, (i+1) );
				
				if(resultProcess == false) {
					logger.error("no pass");
					break;
				}else {
					logger.error("pass");
				}
			}	
				
			if(resultProcess) {
				logger.error("Controller is Fine");
					DatabaseUtils.commit(connection);
			}else {
				logger.error("Controller broken");
					DatabaseUtils.rollback(connection);
			}
	
					
			return resultProcess;
			
			} catch (Exception e) {
				DatabaseUtils.rollback(connection);
				logger.error("DocumentController.updateDocument() Exception : "+ExceptionUtils.stackTraceException(e));
				throw new RuntimeException(e.toString());
			} finally {
				if(connection != null)
					DatabaseUtils.closeConnection(connection);
			}
		
	}


	public boolean insertDocument(HttpServletRequest httpServletRequest, String DocumentObj) {
		Connection connection = null;
		Gson gson = null;
		DocumentDAO DocumentDAO = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = false;
		
	try {
		connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
		gson = new Gson();
		DocumentDAO = new DocumentDAO(connection);
		sessionUtils = new SessionUtils(httpServletRequest);
		
		UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
		DocumentModel DocumentModelRequest = gson.fromJson(DocumentObj, DocumentModel.class);
		
		
		DocumentDTO DocumentDTO = TransformModel.transDocumentModel(DocumentModelRequest);
		DocumentDTO.setCreateBy(NullUtils.cvInt(userSessionModel.getUserId()));
		DocumentDTO.setUpdateBy(NullUtils.cvInt(userSessionModel.getUserId()));
		resultProcess = DocumentDAO.InsertDocument(DocumentDTO);
		
		if(resultProcess == true) {
			logger.error("Insert is Fine");
			DatabaseUtils.commit(connection);
		}else {
			logger.error("Insert broken");
			DatabaseUtils.rollback(connection);
		}
				
		return resultProcess;
		
		}catch (Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("DocumentController.InsertDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	
	}
	

}
