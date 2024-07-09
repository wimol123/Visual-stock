package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.StandardDocumentDAO;
import th.co.gosoft.audit.cpram.dto.StandardDocumentDTO;
import th.co.gosoft.audit.cpram.model.StandardDocumentModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class StandardDocumentController {
	
	private final static Logger logger = Logger.getLogger(SupplierStandardDocumentController.class);
	
	public String getStandardDocumentList() {
		Connection connection = null;
		Gson gson = null;
		StandardDocumentDAO standardDocumentDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			standardDocumentDAO = new StandardDocumentDAO(connection);
			List<StandardDocumentDTO> standardDocumentDTOs = standardDocumentDAO.getStandardDocumentList(0, standardDocumentDAO.countStandardDocumentList(" AND st_doc.enable = 'Y' "), " AND st_doc.enable = 'Y' ");			
			List<StandardDocumentModel> standardDocumentModels = new ArrayList<>();
			for(StandardDocumentDTO standardDocumentDTO : standardDocumentDTOs) {
				standardDocumentModels.add(TransformDTO.transStandardDocumentDTO(standardDocumentDTO));
			}			
			return gson.toJson(standardDocumentModels);
		}catch (Exception e) {
			logger.error("SupplierStandardDocumentController.getStandardDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}

}
