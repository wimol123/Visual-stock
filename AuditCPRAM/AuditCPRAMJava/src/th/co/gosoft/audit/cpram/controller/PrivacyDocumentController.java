package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.ManualDocumentDAO;
import th.co.gosoft.audit.cpram.dao.PrivacyDocumentDAO;
import th.co.gosoft.audit.cpram.dao.TermsDocumentDAO;
import th.co.gosoft.audit.cpram.dao.UrlPdpaDAO;
import th.co.gosoft.audit.cpram.dto.ManualDocumentDTO;
import th.co.gosoft.audit.cpram.dto.PrivacyDocumentDTO;
import th.co.gosoft.audit.cpram.dto.TermsDocumentDTO;
import th.co.gosoft.audit.cpram.dto.UrlPdpaDTO;
import th.co.gosoft.audit.cpram.model.ManualDocumentModel;
import th.co.gosoft.audit.cpram.model.PrivacyDocumentModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class PrivacyDocumentController {
	private final static Logger logger = Logger.getLogger(ManualDocumentController.class);

	public String getPrivacyDocumentList(){
		Connection connection = null;
		Gson gson = null;
		PrivacyDocumentDAO PrivacyDocumentDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			PrivacyDocumentDAO = new PrivacyDocumentDAO(connection);
			List<PrivacyDocumentDTO> PrivacyDocumentDTOs = PrivacyDocumentDAO.getPrivacyDocumentList();			
			List<PrivacyDocumentModel> PrivacyDocumentModels = new ArrayList<>();
			
			for(PrivacyDocumentDTO PrivacyDocumentDTO : PrivacyDocumentDTOs) {
				PrivacyDocumentModels.add(TransformDTO.transDocumentDTO(PrivacyDocumentDTO));
			}
			return gson.toJson(PrivacyDocumentModels);
			
		}catch (Exception e) {
			logger.error("PrivacyDocumentController.getPrivacyDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public String getUrlPdpa() {
		Connection connection = null;
		Gson gson = null;
		UrlPdpaDAO UrlPdpaDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			UrlPdpaDAO = new UrlPdpaDAO(connection);
			UrlPdpaDTO UrlPdpaDTO = UrlPdpaDAO.getUrlPdpa();
			return gson.toJson(UrlPdpaDTO);
		}catch (Exception e) {
			logger.error("PrivacyDocumentController.getPrivacyDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public String getTermsDocument() {
		Connection connection = null;
		TermsDocumentDAO TermsDocumentDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			TermsDocumentDAO = new TermsDocumentDAO(connection);
			TermsDocumentDTO TermsDocumentDTO = TermsDocumentDAO.getTermsDocument();
			return TermsDocumentDTO.getSystem_value();
		}catch (Exception e) {
			logger.error("PrivacyDocumentController.getTermsDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	
}
