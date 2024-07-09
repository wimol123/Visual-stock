package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class ProductTypeDAO extends StandardAttributeDAO{
	
	public ProductTypeDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	private final static Logger logger = Logger.getLogger(ProductTypeDAO.class);
		
	
	public List<ProductTypeDTO> getProductList(Integer startRecord, Integer numOfRows, String whereClause){
		try {
			connection.setAutoCommit(false);
			List<ProductTypeDTO> productTypeDTOs = new ArrayList<>();
			//SELECT pt.product_type_id, pt.name, pt.status_id, pt.create_by, pt.create_date, pt.update_by, pt.update_date FROM product_type pt 
			StringBuilder query = new StringBuilder();
			query.append("SELECT pt.product_type_id, pt.name, pt.enable, pt.create_by, pt.create_date, pt.update_by, pt.update_date ");
			query.append("FROM ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			if(numOfRows != -1) {
				query.append(String.format(" order by pt.product_type_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ProductTypeDTO productTypeDTO = new ProductTypeDTO();
				
				productTypeDTO.setProductTypeId(resultSet.getInt("product_type_id"));
				productTypeDTO.setName(resultSet.getString("name"));
				productTypeDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				productTypeDTO.setCreateBy(resultSet.getInt("create_by"));
				productTypeDTO.setCreateDate(resultSet.getDate("create_date"));
				productTypeDTO.setUpdateBy(resultSet.getInt("update_by"));
				productTypeDTO.setUpdateDate(resultSet.getDate("update_date"));
				
				productTypeDTOs.add(productTypeDTO);
			}
			
			return productTypeDTOs;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int insertProductType(ProductTypeDTO productTypeDTO) {
		int primaryKeyProductType = 0;
		try {
			//INSERT INTO `product_type` (`name`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('www', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Product_Type).append(" ");
			query.append("(name, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, productTypeDTO.getName());
			preparedStatement.setString(index++, NullUtils.cvStr(productTypeDTO.getEnable()));
			preparedStatement.setInt(index++, productTypeDTO.getCreateBy());
			preparedStatement.setInt(index++, productTypeDTO.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					primaryKeyProductType = resultSet.getInt(1);
				}
			}
			
			return primaryKeyProductType;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryKeyProductType;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateProductType(ProductTypeDTO productTypeDTO) {
		boolean resultUpdate = false;
		try {
			
			//UPDATE `product_type` SET `name` = 'wwwwww', `enable` = 'Y', `update_by` = '2', `update_date` = 'now()' WHERE (`product_type_id` = '5');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Product_Type).append(" ");
			query.append("SET ").append("name = ?, ");
			query.append("enable = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (product_type_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, productTypeDTO.getName());
			preparedStatement.setString(index++, NullUtils.cvStr(productTypeDTO.getEnable()));
			preparedStatement.setInt(index++, productTypeDTO.getUpdateBy());
			preparedStatement.setInt(index++, productTypeDTO.getProductTypeId());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective > 0) {
				resultUpdate = true;
			}else {
				resultUpdate = false;
			}
			return resultUpdate;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return resultUpdate;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean deleteProductType(ProductTypeDTO productTypeDTO) {
		try {
			//DELETE FROM `audit_supplier_cpram_final`.`product_type` WHERE (`product_type_id` = '4');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Product_Type).append(" ");
			query.append("WHERE (product_type_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, productTypeDTO.getProductTypeId());
			preparedStatement.executeUpdate();
			return true;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countProduct(String whereClause) {
		try {
			connection.setAutoCommit(false);
			
			int totalProduct = 0;
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("COUNT(*) AS total ");		
			query.append("FROM ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				totalProduct = resultSet.getInt("total");
			}			
			return totalProduct;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return 0;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	/*
	
	
	public boolean insert(ProductTypeDTO productTypeDTO) {
		try {
			connection.setAutoCommit(false);
			//INSERT INTO `product_type` (`name`, `status_id`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('test', '1', '1', 'now()', '1', 'now()');
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_ProductType).append(" ");
			query.append("(name, status_id, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setString(index++, productTypeDTO.getName());
			preparedStatement.setInt(index++, productTypeDTO.getStatusId());
			preparedStatement.setInt(index++, productTypeDTO.getCreateBy());
			preparedStatement.setInt(index++, productTypeDTO.getUpdateBy());
			
			int rowAffect = preparedStatement.executeUpdate();
			if(rowAffect > 0) {
				connection.commit();
				return true;
			}
			DatabaseUtils.rollback(connection);
			return false;
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			log.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean delete(ProductTypeDTO productTypeDTO) {
		try {
			//DELETE FROM `audit_supplier_cpram`.`product_type` WHERE (`product_type_id` = '1');
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_ProductType).append(" ");
			query.append("WHERE (product_type_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, productTypeDTO.getProductTypeId());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			return true;
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			log.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public ProductTypeDTO getProductById(ProductTypeDTO productTypeDTO) {
		try {
			
			//SELECT pt.product_type_id, pt.name, pt.status_id, pt.create_by, pt.create_date, pt.update_by, pt.update_date FROM product_type pt 
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			ProductTypeDTO resultProductTypeDTO = new ProductTypeDTO();
			
			query.append("SELECT pt.product_type_id, pt.name, pt.status_id, pt.create_by, pt.create_date, pt.update_by, pt.update_date ");
			query.append("FROM ").append(DBConst.TABLE_ProductType).append(" pt ");
			query.append("WHERE pt.product_type_id = ?;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, productTypeDTO.getProductTypeId());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				resultProductTypeDTO.setCreateBy(resultSet.getInt("create_by"));
				resultProductTypeDTO.setCreateDate(resultSet.getDate("create_date"));
				resultProductTypeDTO.setName(resultSet.getString("name"));
				resultProductTypeDTO.setProductTypeId(resultSet.getInt("product_type_id"));
				resultProductTypeDTO.setStatusId(resultSet.getInt("status_id"));
				resultProductTypeDTO.setUpdateBy(resultSet.getInt("update_by"));
				resultProductTypeDTO.setUpdateDate(resultSet.getDate("update_date"));				
			}
			return resultProductTypeDTO;
			
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean updateProductType(ProductTypeDTO productTypeDTO) {
		try {
			
			//UPDATE `product_type` SET `name` = 'Meath', `status_id` = '0', `update_by` = '2', `update_date` = 'now()' WHERE (`product_type_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_ProductType).append(" ");
			query.append("SET name = ?, status_id = ?, update_by = ?, update_date = now() ");
			query.append("WHERE (product_type_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index =1;
			preparedStatement.setString(index++, productTypeDTO.getName());
			preparedStatement.setInt(index++, productTypeDTO.getStatusId());
			preparedStatement.setInt(index++, productTypeDTO.getUpdateBy());
			preparedStatement.setInt(index++, productTypeDTO.getProductTypeId());
			
			preparedStatement.executeUpdate();
			connection.commit();
			
			return true;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	*/
	
	/*public List<ProductDTO> getProductList() {
		try {
			List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	p.product_id ");
			sb.append("	,p.product_name ");
			sb.append("	,p.status_id ");
			sb.append(" ,p.create_date ");
			sb.append(" ,p.update_date ");
			sb.append("FROM ").append(DBConst.TABLE_Product).append(" p ");
 			preparedStatement = connection.prepareStatement(sb.toString());
 			resultSet = preparedStatement.executeQuery();			
 			
 			while(resultSet.next()) {
 				ProductDTO dto = new ProductDTO();
 				dto.setCreateDate(resultSet.getDate("create_date"));
 				dto.setProductId(resultSet.getInt("product_id"));
 				dto.setProductName(resultSet.getString("product_name"));
 				dto.setStatusId(resultSet.getInt("status_id"));
 				dto.setUpdateDate(resultSet.getDate("update_date"));
 				productDTOList.add(dto);
 			}
			return productDTOList;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean isExist(String productName) {
		try {

			int totalProduct = 0;
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	COUNT(*) AS total ");
			sb.append("FROM ").append(DBConst.TABLE_Product).append(" p ");
			sb.append("WHERE p.product_name = ? ");
 			preparedStatement = connection.prepareStatement(sb.toString());
 			preparedStatement.setString(1, productName);
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {

 				totalProduct = resultSet.getInt("total"); 				
 			}
			return (totalProduct > 0);
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<ProductDTO> getProductList(DataTablePostParamModel param) {
		try {
			String whereClause = buildWhereClause(param);
			List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	p.product_id ");
			sb.append("	,p.product_name ");
			sb.append("	,p.status_id ");
			sb.append(" ,p.create_date ");
			sb.append(" ,p.update_date ");
			sb.append("FROM ").append(DBConst.TABLE_Product).append(" p ");
			sb.append("WHERE 1=1 ");
			sb.append(whereClause);
			sb.append(" order by p.product_id limit ").append(param.getStart()).append(", ").append(param.getLength());

 			preparedStatement = connection.prepareStatement(sb.toString());
 			preparedStatement.setFetchSize(param.getLength());
 			preparedStatement.setMaxRows(param.getLength());
 			
 			resultSet = preparedStatement.executeQuery();
 			
 			while(resultSet.next()) {
 				ProductDTO dto = new ProductDTO();
 				dto.setCreateDate(resultSet.getDate("create_date"));
 				dto.setProductId(resultSet.getInt("product_id"));
 				dto.setProductName(resultSet.getString("product_name"));
 				dto.setStatusId(resultSet.getInt("status_id"));
 				dto.setUpdateDate(resultSet.getDate("update_date"));
 				productDTOList.add(dto);
 			}
			return productDTOList;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	private String buildWhereClause(DataTablePostParamModel param)
	{	
		StringBuilder sb = new StringBuilder();
		
		for(Column col : param.getColumns()) {
			if(!StringUtils.isNullOrEmpty(col.getName()))
			{
				if(!StringUtils.isNullOrEmpty(col.getSearch().getValue()))
				{
					sb.append(" AND ");
					String data [] = col.getName().split("\\|");
					String colName  = data[0];
					String op = data[1];
					if(op.equals("%"))
					{
						sb.append("  p.").append(colName).append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else 
					{
						sb.append(" p.").append(colName).append(" = '").append(col.getSearch().getValue()).append("' ");
					}
				}
			}
		}
		
		return sb.toString();
	}
	
	public boolean insert(ProductDTO productDTO){
		try {
 			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
 			sb.append("INSERT INTO ").append(DBConst.TABLE_Product).append(" (product_name, status_id, create_date, update_date) ");
 			sb.append("VALUE (?, ?, NOW(), NOW()) ");

 			preparedStatement = connection.prepareStatement(sb.toString());
 			int index = 1;
 			preparedStatement.setString(index++, productDTO.getProductName());
 			preparedStatement.setInt(index++, productDTO.getStatusId());
 			preparedStatement.executeUpdate();
		    return true;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
 		}
	}
	
	public boolean update(ProductDTO productDTO){
		try {
 			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
 			sb.append("UPDATE ").append(DBConst.TABLE_Product).append(" SET product_name = ?, status_id = ?, update_date = NOW() ");
 			sb.append("WHERE product_id = ? ");

 			preparedStatement = connection.prepareStatement(sb.toString());
 			int index = 1;
 			preparedStatement.setString(index++, productDTO.getProductName());
 			preparedStatement.setInt(index++, productDTO.getStatusId());
 			preparedStatement.setInt(index++, productDTO.getProductId());
 			preparedStatement.executeUpdate();
		    return true;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
 		}
	}
	
	public boolean delete(int productId){
    	try{
	    	connection.setAutoCommit(false);
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ").append(DBConst.TABLE_Product);
			sb.append(" WHERE product_id = ? "); 
			preparedStatement = connection.prepareStatement(sb.toString());
			preparedStatement.setInt(1,productId);
			preparedStatement.executeUpdate();
 			return true;
    	}catch(SQLException e){
			log.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
    }
	
	public boolean deleteProductSupplier(int productId, int supplierId){
    	try{
	    	connection.setAutoCommit(false);
			StringBuilder sb = new StringBuilder();
			sb.append("DELETE FROM ").append(DBConst.TABLE_Supplier_Product_Mapping);
			sb.append(" WHERE product_id = ? and supplier_id = ?"); 
			preparedStatement = connection.prepareStatement(sb.toString());
			preparedStatement.setInt(1,productId);
			preparedStatement.setInt(2,supplierId);
			preparedStatement.executeUpdate();
 			return true;
    	}catch (SQLException e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
    }
	
	public ProductDTO getProductById(String productId) {
		try {
			ProductDTO productDTO = new ProductDTO();
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
 			sb.append("SELECT ");
			sb.append("	p.product_id ");
			sb.append("	,p.product_name ");
			sb.append("	,p.status_id ");
			sb.append(" ,p.create_date ");
			sb.append(" ,p.update_date ");
			sb.append("FROM ").append(DBConst.TABLE_Product).append(" p ");
			sb.append("WHERE p.product_id = ? ");
 			preparedStatement = connection.prepareStatement(sb.toString());	
			preparedStatement.setString(1,productId);	
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {
 				productDTO.setCreateDate(resultSet.getDate("create_date"));
 				productDTO.setProductId(resultSet.getInt("product_id"));
 				productDTO.setProductName(resultSet.getString("product_name"));
 				productDTO.setStatusId(resultSet.getInt("status_id"));
 				productDTO.setUpdateDate(resultSet.getDate("update_date"));
 			}
			return productDTO;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public int countProductList(DataTablePostParamModel param) {
		try {
			String whereClause = buildWhereClause(param);
			int totalProduct = 0;
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	COUNT(*) AS total ");
			sb.append("FROM ").append(DBConst.TABLE_Product).append(" p ");
			sb.append("WHERE 1=1 ");
			sb.append(whereClause);
 			preparedStatement = connection.prepareStatement(sb.toString());
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {

 				totalProduct = resultSet.getInt("total"); 				
 			}
			return totalProduct;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<ProductDTO> getProductListBySupplierId(DataTablePostParamModel modelDataTable,String supplierId) {
		try {
			connection.setAutoCommit(false);
			String whereCause = buildWhereCauseForSupplier(modelDataTable);
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT p.product_id,p.product_name,spm.status_id ");
			stringBuilder.append("FROM product p INNER JOIN supplier_product_mapping spm ");
			stringBuilder.append("ON p.product_id = spm.product_id ");
			stringBuilder.append("WHERE spm.supplier_id = ?");
			stringBuilder.append(whereCause);
			if(modelDataTable.getLength() != -1) {
				stringBuilder.append(String.format(" limit %s,%s", modelDataTable.getStart(),modelDataTable.getLength()));
			}
			stringBuilder.append(";");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(supplierId));
			resultSet = preparedStatement.executeQuery();
			
			List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
			while(resultSet.next()) {
				ProductDTO product = new ProductDTO();
				product.setProductId(resultSet.getInt("product_id"));
				product.setProductName(resultSet.getString("product_name"));
				product.setStatusId(resultSet.getInt("status_id"));
				productDTOList.add(product);
			}
			
			return productDTOList;
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<ProductDTO> getProductListAddToSupplier(DataTablePostParamModel modelDataTable, String supplierId) {
		try {
			connection.setAutoCommit(false);
			
			String whereCause = buildWhereCauseForSupplier(modelDataTable);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("SELECT p.product_id,p.product_name,p.status_id ");
			stringBuilder.append("FROM product p ");
			stringBuilder.append("WHERE p.product_name NOT IN ");
			stringBuilder.append("(SELECT pr.product_name FROM product pr ");
			stringBuilder.append("RIGHT JOIN supplier_product_mapping spm ");
			stringBuilder.append("ON pr.product_id = spm.product_id ");
			stringBuilder.append("WHERE spm.supplier_id = ?)");
			stringBuilder.append(whereCause);
			if(modelDataTable.getLength() != -1) {
				stringBuilder.append(String.format(" limit %s,%s", modelDataTable.getStart(),modelDataTable.getLength()));
			}
			stringBuilder.append(";");
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(supplierId));
			resultSet = preparedStatement.executeQuery();
			
			List<ProductDTO> productDTOList = new ArrayList<ProductDTO>();
			while (resultSet.next()) {
				ProductDTO product = new ProductDTO();
				product.setProductId(resultSet.getInt("product_id"));
				product.setProductName(resultSet.getString("product_name"));
				product.setStatusId(resultSet.getInt("status_id"));
				productDTOList.add(product);
			}
			
			return productDTOList;
			
		} catch (SQLException e) {
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	private String buildWhereCauseForSupplier(DataTablePostParamModel param) {
		String productName = param.getColumns().get(1).getSearch().getValue().toString().trim();
		String status = param.getColumns().get(3).getSearch().getValue().toString().trim();
		StringBuilder stringBuild = new StringBuilder();
		
		if(!productName.equals("")) {
			stringBuild.append(String.format(" && (p.product_name LIKE '%%%s%%')",productName));
		}
		if(!status.equals("")) {
			stringBuild.append(String.format(" && (spm.status_id = %s)",status));
		}
				
		return stringBuild.toString();
	}
	
	
	public boolean insertProductToSupplier(SupplierProductMappingModel supplierProductMappingModel) {
		try {
			
			//insert into supplier_product_mapping values(3,2,0,now());
			
			connection.setAutoCommit(false);
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("insert into supplier_product_mapping ");
			stringBuilder.append("values(?,?,1,now());");
			
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(supplierProductMappingModel.getSupplierId()));
			preparedStatement.setInt(2, NullUtils.cvInt(supplierProductMappingModel.getProductId()));
			preparedStatement.executeUpdate();
			
			connection.commit();
			return true;
			
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	public boolean deleteProductToSupplier(SupplierProductMappingModel supplierProductMappingModel) {
		
		try {
			connection.setAutoCommit(false);
			
			//DELETE FROM `supplier_product_mapping` WHERE (`supplier_id` = '1') and (`product_id` = '4');
			
			StringBuilder stringBuilder = new StringBuilder();
			stringBuilder.append("DELETE FROM supplier_product_mapping ");
			stringBuilder.append("WHERE (supplier_id = ?) and (product_id = ?);");
			
			preparedStatement = connection.prepareStatement(stringBuilder.toString());
			preparedStatement.setInt(1, NullUtils.cvInt(supplierProductMappingModel.getSupplierId()));
			preparedStatement.setInt(2,NullUtils.cvInt(supplierProductMappingModel.getProductId()));
			preparedStatement.executeUpdate();
			connection.commit();
			
			return true;
			
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	public int countProductBySupplierId(DataTablePostParamModel model,String supplierId) {
		try {
			
			String whereCause = buildWhereCauseForSupplier(model);
			int totalProduct = 0;
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	COUNT(*) AS total ");
			sb.append("FROM product p INNER JOIN supplier_product_mapping spm ");
			sb.append("ON p.product_id = spm.product_id ");
			sb.append("WHERE spm.supplier_id = ?");
			sb.append(whereCause);
 			preparedStatement = connection.prepareStatement(sb.toString());
 			preparedStatement.setInt(1, NullUtils.cvInt(supplierId));
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {

 				totalProduct = resultSet.getInt("total"); 				
 			}
			return totalProduct;
			
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	
	public int countProductAddToSupplier(DataTablePostParamModel model,String supplierId) {
		try {
			
			String whereCause = buildWhereCauseForSupplier(model);
			int totalProduct = 0;
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	COUNT(*) AS total ");
			sb.append("FROM product p ");
			sb.append("WHERE p.product_name NOT IN ");
			sb.append("(SELECT pr.product_name FROM product pr ");
			sb.append("RIGHT JOIN supplier_product_mapping spm ");
			sb.append("ON pr.product_id = spm.product_id ");
			sb.append("WHERE spm.supplier_id = ?)");
			sb.append(whereCause);
 			preparedStatement = connection.prepareStatement(sb.toString());
 			preparedStatement.setInt(1, NullUtils.cvInt(supplierId));
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {

 				totalProduct = resultSet.getInt("total"); 				
 			}
			return totalProduct;
			
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}*/
}
