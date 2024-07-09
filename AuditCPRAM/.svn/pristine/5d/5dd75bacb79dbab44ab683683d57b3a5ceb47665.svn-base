package th.co.gosoft.audit.cpram.controller;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.MaterialDAO;
import th.co.gosoft.audit.cpram.dto.MaterialDTO;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

import java.sql.Connection;
import java.util.List;

import org.apache.log4j.Logger;

public class MaterialController {
	private final static Logger logger = Logger.getLogger(MaterialController.class);
	
	public String getMaterialList() {
		Connection connection = null;
		MaterialDAO materialDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			materialDAO = new MaterialDAO(connection);
			List<MaterialDTO> materialDTOList = materialDAO.getMaterialList();
			return new Gson().toJson(materialDTOList);
		} catch (Exception e) {
			logger.error("SmartPOController.getPoAcceptedList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
}