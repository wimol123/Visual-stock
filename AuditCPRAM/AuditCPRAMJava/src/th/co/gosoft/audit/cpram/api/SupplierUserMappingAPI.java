package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.controller.SupplierUserMappingController;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/supplier_user_mapping")
public class SupplierUserMappingAPI {
private final static Logger logger = Logger.getLogger(SupplierUserMappingAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@POST
	@Path("/datatable/contract_supplier")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<UserModel> getUserContractSupplier(DataTablePostParamModel dataTablePostParamModel){
		try {
			
			SupplierUserMappingController supplierUserMappingController = new SupplierUserMappingController();
			return supplierUserMappingController.getUserContractSupplier(dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("SupplierUserMappingAPI.getUserContractSupplier() Exception : "+ExceptionUtils.stackTraceException(e));			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	

}
