package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.AddressDAO;
import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.model.AddressModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class AddressController {
	
	private final static Logger logger = Logger.getLogger(AddressController.class);
	
	public String getAddressList(){
		Connection connection = null;
		AddressDAO addressDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			addressDAO = new AddressDAO(connection);
			gson = new Gson();
			
			List<AddressDTO> supplierAddressDTOs = addressDAO.getAddressList();
			List<AddressModel> supplierAddressModels = new ArrayList<>();
			
			for(AddressDTO supplierAddressDTO : supplierAddressDTOs) {
				supplierAddressModels.add(TransformDTO.transAddressDTO(supplierAddressDTO));
			}
			
			return gson.toJson(supplierAddressDTOs);
			
		}catch (Exception e) {
			logger.error("AddressController.getAddressList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
		
	}
	
	
	
}	
