package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;

public class SupplierProductTypeMappingDAO extends StandardAttributeDAO{

	public SupplierProductTypeMappingDAO(Connection connection) {
		super(connection);
	}
	
	private final static Logger logger = Logger.getLogger(SupplierProductTypeMappingDAO.class);
	
	public boolean checkMemberProductType(ProductTypeDTO productTypeDTO) {
		try {
			//SELECT * FROM supplier_product_type_mapping sup_map WHERE sup_map.product_type_id = 1;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM ").append(DBConst.TABLE_Supplier_Product_Type_Mapping).append(" sup_map ");
			query.append("WHERE sup_map.product_type_id = ?; ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, productTypeDTO.getProductTypeId());
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean checkMemberSupplier(SupplierDTO supplierDTO) {
		try {
			//SELECT * FROM supplier_product_type_mapping sup_map WHERE sup_map.product_type_id = 1;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT * FROM ").append(DBConst.TABLE_Supplier_Product_Type_Mapping).append(" sup_map ");
			query.append("WHERE sup_map.supplier_id = ?; ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			resultSet = preparedStatement.executeQuery();
			return resultSet.next();
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
