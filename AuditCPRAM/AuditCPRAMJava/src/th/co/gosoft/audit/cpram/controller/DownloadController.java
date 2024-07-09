package th.co.gosoft.audit.cpram.controller;

import java.net.URLDecoder;
import java.sql.Connection;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.dao.SupplierDAO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class DownloadController {
	private final static Logger logger = Logger.getLogger(DownloadController.class);
	
	public String getLogoSupplier(String objSupplier) {
		Gson gson = null;	
		Connection connection = null;
		SupplierDAO supplierDAO = null;
		try {
			
			gson = new Gson();
			connection = DatabaseUtils.connectToDatasourceWithAutoCommit();
			supplierDAO = new SupplierDAO(connection);
			SupplierModel supplierModel = gson.fromJson(objSupplier, SupplierModel.class);
			if(!StringUtils.isNullOrEmpty(supplierModel.getLogo())) {
				supplierModel.setLogo(URLDecoder.decode(supplierModel.getLogo(), "UTF-8"));
			}else {
				if(!StringUtils.isNullOrEmpty(supplierModel.getSupplierId())) {
					SupplierDTO supplierDTO = supplierDAO.getLogoSupplierPath(TransformModel.transSupplierModel(supplierModel));	
					if(!StringUtils.isNullOrEmpty(supplierDTO.getLogo().trim())) {
						supplierModel.setLogo(ConfigurationSystemManager.getInstance().getHttpWebserverName().concat(ConfigurationSystemManager.getInstance().getHttpAliasLogoname()).concat("/").concat(supplierDTO.getLogo().trim()));
					}else {
						supplierModel.setLogo("");
					}
				}
			}
			
			return supplierModel.getLogo();
		}catch (Exception e) {
			logger.error("DownloadController.getLogoSupplier() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
		
}
