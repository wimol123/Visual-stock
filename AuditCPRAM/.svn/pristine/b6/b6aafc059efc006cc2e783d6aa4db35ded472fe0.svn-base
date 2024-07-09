package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;

public class SupplierUserMappingController {
	
	private final static Logger logger = Logger.getLogger(SupplierUserMappingController.class);
	
	public DataTableModel<UserModel> getUserContractSupplier(DataTablePostParamModel dataTablePostParamModel){
		Connection connection = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		Gson gson = null;
		try {
			
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			gson = new Gson();
			DataTableModel<UserModel> dataTableModel = new DataTableModel<>();
			
			StringBuilder queryWhereClause = new StringBuilder();
			
			for (Column col : dataTablePostParamModel.getColumns()) {				
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("employeeId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.employee_id").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
					else if (col.getName().equals("username") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.username").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("password") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.password").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("fullname") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.fullname").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("description") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.description").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("email") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.email").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("telephone") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.telephone").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}	
					else if (col.getName().equals("enable") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.enable").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("userGroupId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("u.user_group_id").append(" = ").append(col.getSearch().getValue()).append(" ");
					}
					else if (col.getName().equals("userGroupName") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("ug.user_group_name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					
				}
			}
			
			if(!StringUtils.isNullOrEmpty(dataTablePostParamModel.getOptionString())) {
				SupplierModel supplierOption = gson.fromJson(dataTablePostParamModel.getOptionString(), SupplierModel.class);
				queryWhereClause.append(" AND ");
				queryWhereClause.append("s.supplier_id").append(" = '").append(supplierOption.getSupplierId()).append("' ");
			}
			
			List<UserDTO> userDTOs = supplierUserMappingDAO.getUserContractSupplier(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			int countContract = supplierUserMappingDAO.countUserContractSupplier(queryWhereClause.toString());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setData(new ArrayList<>());
			dataTableModel.setRecordsFiltered(countContract);
			dataTableModel.setRecordsTotal(countContract);
			for (UserDTO user : userDTOs) {
				dataTableModel.getData().add(TransformDTO.transUserDTO(user));
			}
			return dataTableModel;
		}catch(Exception e) {
			logger.error("SupplierUserMappingController.getUserContractSupplier() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}

}
