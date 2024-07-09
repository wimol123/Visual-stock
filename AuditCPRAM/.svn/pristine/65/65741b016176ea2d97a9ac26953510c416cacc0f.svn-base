package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.DistrictDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.ProvinceDTO;
import th.co.gosoft.audit.cpram.dto.SubDistrictDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class SupplierProductTypeAddressMappingDAO extends StandardAttributeDAO{

	public SupplierProductTypeAddressMappingDAO(Connection connection) {
		super(connection);
		// TODO Auto-generated constructor stub
	}

	private final static Logger logger = Logger.getLogger(SupplierProductTypeAddressMappingDAO.class);

		
	public List<SupplierProductAddressMappingDTO> getSupplierListMappingProductType(Integer startRecord, Integer numOfRows, String whereClause){
		try {
			
			/*select sup.supplier_id, sup.supplier_company, sup_map.product_type_id, sup_map.location_name, sup_map.id
			from supplier_product_address_mapping as sup_map
			left join supplier as sup on sup.supplier_id = sup_map.supplier_id
			where sup_map.product_type_id = 1;*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT sup.supplier_id, sup.supplier_company, sup_map.product_type_id, sup_map.location_name, sup_map.id, sup_map.address_id ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" sup ");
			query.append("ON sup.supplier_id = sup_map.supplier_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			
			if (numOfRows != -1) {
				query.append(String.format(" order by sup.supplier_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = new ArrayList<>();
			while (resultSet.next()) {
				SupplierProductAddressMappingDTO supplierProductAddressMappingDTO = new SupplierProductAddressMappingDTO();
				
				supplierProductAddressMappingDTO.setId(resultSet.getInt("id"));
				supplierProductAddressMappingDTO.setLocationName(resultSet.getString("location_name"));
				
				SupplierDTO supplier = new SupplierDTO();
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				
				ProductTypeDTO productType = new ProductTypeDTO();
				productType.setProductTypeId(resultSet.getInt("product_type_id"));
				
				AddressDTO addressDTO = new AddressDTO();
				addressDTO.setAddressId(resultSet.getInt("address_id"));
				
				supplierProductAddressMappingDTO.setSupplierId(supplier);
				supplierProductAddressMappingDTO.setProductTypeId(new ArrayList<>());
				supplierProductAddressMappingDTO.getProductTypeId().add(productType);
				supplierProductAddressMappingDTO.setAddressId(addressDTO);
				supplierProductAddressMappingDTOs.add(supplierProductAddressMappingDTO);
			}
			return supplierProductAddressMappingDTOs;
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
	
	public List<SupplierProductAddressMappingDTO> getSupplierProduceListMappingProductTypeBySupplier(Integer startRecord, Integer numOfRows, String whereClause) {
		/*SELECT sup_map.id, sup_map.supplier_id, sup_map.product_type_id, sup_map.address_id, sup_map.location_name, pt.name, a.address, a.sub_district_id, sd.name AS name_sub_district, a.district_id, d.name AS name_district, a.province_id, p.name AS name_province, a.postcode FROM supplier_product_address_mapping AS sup_map
		LEFT JOIN product_type AS pt 
		ON sup_map.product_type_id = pt.product_type_id
		LEFT JOIN address AS a
		ON sup_map.address_id = a.address_id
		LEFT JOIN sub_district AS sd
		ON a.sub_district_id = sd.sub_district_id
		LEFT JOIN district AS d
		ON a.district_id = d.district_id
		LEFT JOIN province AS p
		ON p.province_id = a.province_id
		WHERE 1=1 AND sup_map.supplier_id = '6'*/
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT sup_map.id, sup_map.supplier_id, sup_map.product_type_id, sup_map.address_id, sup_map.location_name, sup_map.enable, pt.name, a.address, a.sub_district_id, sd.name AS name_sub_district, a.district_id, d.name AS name_district, a.province_id, p.name AS name_province, a.postcode ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("ON sup_map.product_type_id = pt.product_type_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Address).append(" a ");
			query.append("ON sup_map.address_id = a.address_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Sub_District).append(" sd ");
			query.append("ON a.sub_district_id = sd.sub_district_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_District).append(" d ");
			query.append("ON a.district_id = d.district_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Province).append(" p ");
			query.append("ON p.province_id = a.province_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			
			if (numOfRows != -1) {
				query.append(String.format(" order by sup_map.id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = new ArrayList<>();
			while(resultSet.next()) {
				SupplierProductAddressMappingDTO supplierProductAddressMappingDTO = new SupplierProductAddressMappingDTO();
				
				supplierProductAddressMappingDTO.setId(resultSet.getInt("id"));
				supplierProductAddressMappingDTO.setLocationName(resultSet.getString("location_name"));
				supplierProductAddressMappingDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				
				SupplierDTO supplier = new SupplierDTO();
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplierProductAddressMappingDTO.setSupplierId(supplier);
				
				ProductTypeDTO productType = new ProductTypeDTO();
				productType.setProductTypeId(resultSet.getInt("product_type_id"));
				productType.setName(resultSet.getString("name"));
				supplierProductAddressMappingDTO.setProductTypeId(new ArrayList<>());
				supplierProductAddressMappingDTO.getProductTypeId().add(productType);
				
				AddressDTO address = new AddressDTO();
				address.setAddressId(resultSet.getInt("address_id"));
				address.setAddress(resultSet.getString("address"));
				address.setPostcode(resultSet.getString("postcode"));
				
				SubDistrictDTO subDistrict = new SubDistrictDTO();
				subDistrict.setSubDistrictId(resultSet.getInt("sub_district_id"));
				subDistrict.setName(resultSet.getString("name_sub_district"));
				address.setSubDistrictId(subDistrict);
				
				DistrictDTO district = new DistrictDTO();
				district.setDistrictId(resultSet.getInt("district_id"));
				district.setName(resultSet.getString("name_district"));
				address.setDistrictId(district);
				
				ProvinceDTO province = new ProvinceDTO();
				province.setProvinceId(resultSet.getInt("province_id"));
				province.setName(resultSet.getString("name_province"));
				address.setProvinceId(province);
				
				supplierProductAddressMappingDTO.setAddressId(address);
				
				supplierProductAddressMappingDTOs.add(supplierProductAddressMappingDTO);
			}
			return supplierProductAddressMappingDTOs;
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
	
	public int insertSupplierProductAddressMapping(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO) {
		int primaryKey = 0;
		try {
			//INSERT INTO `supplier_product_address_mapping` (`supplier_id`, `product_type_id`, `address_id`, `location_name`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('7', '1', '5', 'ฟาร์มผัก นครปฐม', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query  = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" ");
			query.append("(supplier_id, product_type_id, address_id, location_name, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getSupplierId().getSupplierId());
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getProductTypeId().get(0).getProductTypeId());
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getAddressId().getAddressId());
			preparedStatement.setString(index++, supplierProductAddressMappingDTO.getLocationName());
			preparedStatement.setString(index++, NullUtils.cvStr(supplierProductAddressMappingDTO.getEnable()));
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getCreateBy());
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					primaryKey = resultSet.getInt(1);
				}
			}
			return primaryKey;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			return primaryKey;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateSupplierProductAddressMapping(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO) {
		//UPDATE `supplier_product_address_mapping` SET `supplier_id` = '3', `product_type_id` = '2', `address_id` = '2', `location_name` = 'sss', `enable` = 'N', `update_by` = '2', `update_date` = 'now()' WHERE (`id` = '1');
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" ");
			query.append("SET supplier_id = ?, ");
			query.append("product_type_id = ?, ");
			query.append("address_id = ?, ");
			query.append("location_name = ?, ");
			query.append("enable = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getSupplierId().getSupplierId());
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getProductTypeId().get(0).getProductTypeId());
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getAddressId().getAddressId());
			preparedStatement.setString(index++, supplierProductAddressMappingDTO.getLocationName());
			preparedStatement.setString(index++, NullUtils.cvStr(supplierProductAddressMappingDTO.getEnable()));
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getUpdateBy());
			preparedStatement.setInt(index++, supplierProductAddressMappingDTO.getId());
			int rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean deleteSupplierProductAddressMapping(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO) {
		//DELETE FROM `supplier_product_address_mapping` WHERE (`id` = '1');
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" ");
			query.append("WHERE (id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierProductAddressMappingDTO.getId());
			int rowAffective = preparedStatement.executeUpdate();
			
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
		
	}
	
	public int countAddressId(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO) {
		int numOfAddressId = 0;
		try {
			
			//SELECT COUNT(*) AS total FROM supplier_product_address_mapping AS sup_map
			//WHERE sup_map.address_id = 3;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT COUNT(*) AS total FROM ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("WHERE sup_map.address_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierProductAddressMappingDTO.getAddressId().getAddressId());
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				numOfAddressId = resultSet.getInt(1);				
			}
			return numOfAddressId;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return numOfAddressId;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countSupplierListMappingProductType(String queryWhereClause) {
		int countSupplier = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" sup ");
			query.append("ON sup.supplier_id = sup_map.supplier_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countSupplier = resultSet.getInt("total");
			}
			return countSupplier;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countSupplier;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countSupplierProduceListMappingProductTypeBySupplier(String queryWhereClause) {
		int countSupplier = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" sup_map ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("ON sup_map.product_type_id = pt.product_type_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Address).append(" a ");
			query.append("ON sup_map.address_id = a.address_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Sub_District).append(" sd ");
			query.append("ON a.sub_district_id = sd.sub_district_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_District).append(" d ");
			query.append("ON a.district_id = d.district_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Province).append(" p ");
			query.append("ON p.province_id = a.province_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countSupplier = resultSet.getInt("total");
			}
			return countSupplier;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countSupplier;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
/*	public List<SupplierDTO> getSupplierListMappingProductType(Integer startRecord, Integer numOfRow, String whereClause, ProductTypeDTO productTypeDTO){
		try {
			
			SELECT s.supplier_code, s.supplier_company, s.supplier_id, s.status_id AS status_id_supplier, pt.product_type_id, pt.name, pt.status_id AS status_id_product_type
			FROM supplier s 
			LEFT JOIN supplier_product_type_mapping spm ON s.supplier_id = spm.supplier_id
			LEFT JOIN product_type pt ON spm.product_type_id = pt.product_type_id
			WHERE pt.product_type_id = 2 ;
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			List<SupplierDTO> supplierDTOs = new ArrayList<>();
			
			query.append("SELECT s.supplier_code, s.supplier_company, s.supplier_id, s.status_id AS status_id_supplier, pt.product_type_id, pt.name, pt.status_id AS status_id_product_type ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" spm ");
			query.append("ON s.supplier_id = spm.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_ProductType).append(" pt ");
			query.append("ON spm.product_type_id = pt.product_type_id ");
			query.append("WHERE pt.product_type_id = ? ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			if(numOfRow != -1) {
				query.append(String.format(" order by s.supplier_id asc limit %s,%s", startRecord,numOfRow));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, productTypeDTO.getProductTypeId());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierCode(resultSet.getString("supplier_code"));
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				//supplierDTO.setStatusId(resultSet.getInt("status_id_supplier"));
				//supplierDTO.setProductTypeId(new ArrayList<>());
				ProductTypeDTO resultProductType = new ProductTypeDTO();
				resultProductType.setProductTypeId(resultSet.getInt("product_type_id"));
				resultProductType.setName(resultSet.getString("name"));
				resultProductType.setStatusId(resultSet.getInt("status_id_product_type"));
				
				//supplierDTO.getProductTypeId().add(resultProductType);
				supplierDTOs.add(supplierDTO);
			}
						
			return supplierDTOs;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<SupplierDTO> getsupplierListNotInProduct(Integer startRecord, Integer numOfRow, String whereClause, ProductTypeDTO productTypeDTO){
		try {
			
			SELECT s.supplier_code, s.supplier_company, s.supplier_id, s.status_id AS status_id_supplier
			FROM supplier s WHERE s.supplier_id NOT IN (SELECT spm.supplier_id FROM supplier_product_type_mapping spm WHERE spm.product_type_id = 1);
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			List<SupplierDTO> supplierDTOs = new ArrayList<>();
			
			query.append("SELECT s.supplier_code, s.supplier_company, s.supplier_id, s.status_id AS status_id_supplier ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("WHERE s.supplier_id NOT IN ");
			query.append("(SELECT spm.supplier_id FROM ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" spm ");
			query.append("WHERE spm.product_type_id = ?) ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			if(numOfRow != -1) {
				query.append(String.format(" order by s.supplier_id asc limit %s,%s", startRecord,numOfRow));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, productTypeDTO.getProductTypeId());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierCode(resultSet.getString("supplier_code"));
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				//supplierDTO.setStatusId(resultSet.getInt("status_id_supplier"));
				
				supplierDTOs.add(supplierDTO);
			}
						
			return supplierDTOs;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<ProductTypeDTO> productListMappingBySupplier(Integer startRecord, Integer numOfRow, String whereClause, SupplierDTO supplierDTO){
		try {
			
			SELECT s.supplier_code, s.supplier_company, s.supplier_id, s.status_id AS status_id_supplier, pt.product_type_id, pt.name, pt.status_id AS status_id_product_type 
			FROM product_type pt
			LEFT JOIN supplier_product_type_mapping spm ON spm.product_type_id = pt.product_type_id
			LEFT JOIN supplier s ON s.supplier_id = spm.supplier_id
			WHERE s.supplier_id = 2;
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_code, s.supplier_company, s.supplier_id, s.status_id AS status_id_supplier, pt.product_type_id, pt.name, pt.status_id AS status_id_product_type ");
			query.append("FROM ").append(DBConst.TABLE_ProductType).append(" pt ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" spm ");
			query.append("ON spm.product_type_id = pt.product_type_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON s.supplier_id = spm.supplier_id WHERE s.supplier_id = ? ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			
			if(numOfRow != -1) {
				query.append(String.format(" order by pt.product_type_id asc limit %s,%s", startRecord,numOfRow));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			resultSet = preparedStatement.executeQuery();
			
			List<ProductTypeDTO> productTypeDTOs = new ArrayList<>();
			while (resultSet.next()) {
				
				ProductTypeDTO productTypeDTO = new ProductTypeDTO();				
				SupplierDTO supplier = new SupplierDTO();
				productTypeDTO.setSupplierId(new ArrayList<>());
				productTypeDTO.setProductTypeId(resultSet.getInt("product_type_id"));
				productTypeDTO.setName(resultSet.getString("name"));
				productTypeDTO.setStatusId(resultSet.getInt("status_id_product_type"));
				
				supplier.setSupplierCode(resultSet.getString("supplier_code"));
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				//supplier.setStatusId(resultSet.getInt("status_id_supplier"));
				
				productTypeDTO.getSupplierId().add(supplier);
				productTypeDTOs.add(productTypeDTO);
			}
			return productTypeDTOs;
			
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<ProductTypeDTO> productListNotInSupplier(Integer startRecord, Integer numOfRow, String whereClause, SupplierDTO supplierDTO){
		try {
			
			SELECT pt.product_type_id, pt.name, pt.status_id AS status_id_product_type
			FROM product_type pt WHERE pt.product_type_id NOT IN 
			(SELECT spm.product_type_id FROM supplier_product_type_mapping spm WHERE spm.supplier_id = 2);
			
			connection.setAutoCommit(false);			
			List<ProductTypeDTO> productTypeDTOs = new ArrayList<>();
			StringBuilder query = new StringBuilder();
			query.append("SELECT pt.product_type_id, pt.name, pt.status_id AS status_id_product_type ");
			query.append("FROM ").append(DBConst.TABLE_ProductType).append(" pt ");
			query.append("WHERE pt.status_id = 1 AND pt.product_type_id NOT IN ");
			query.append("(SELECT spm.product_type_id FROM ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" spm ");
			query.append("WHERE spm.supplier_id = ?) ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			
			if(numOfRow != -1) {
				query.append(String.format(" order by pt.product_type_id asc limit %s,%s", startRecord,numOfRow));
			}			
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				ProductTypeDTO productTypeDTO = new ProductTypeDTO();
				productTypeDTO.setProductTypeId(resultSet.getInt("product_type_id"));
				productTypeDTO.setName(resultSet.getString("name"));
				productTypeDTO.setStatusId(resultSet.getInt("status_id_product_type"));
				productTypeDTOs.add(productTypeDTO);
			}
			
			return productTypeDTOs;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return null;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean insertSupplierProductToMapping(SupplierDTO supplierDTO, ProductTypeDTO productTypeDTO) {
		try {
			//INSERT INTO `supplier_product_type_mapping` (`supplier_id`, `product_type_id`, `status_id`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2', '1', '1', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" ");
			query.append("(supplier_id, product_type_id, status_id, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, supplierDTO.getSupplierId());
			preparedStatement.setInt(index++, productTypeDTO.getProductTypeId());
			//preparedStatement.setInt(index++, supplierDTO.getStatusId());
			preparedStatement.setInt(index++, supplierDTO.getCreateBy());
			preparedStatement.setInt(index++, supplierDTO.getUpdateBy());
			
			int rowAffect = preparedStatement.executeUpdate();
			if(rowAffect > 0) {
				connection.commit();
				return true;
			}
			else {
				DatabaseUtils.rollback(connection);
				return false;
			}
		}catch(SQLException e){
			DatabaseUtils.rollback(connection);
			log.error(e.getMessage(), e);
			return false;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean deleteMappingWithProductTypeId(ProductTypeDTO productTypeDTO) {
		try {
			//DELETE FROM `supplier_product_type_mapping` WHERE (`supplier_id` = '1') and (`product_type_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" ");
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
	
	public boolean deleteMappingWithSupplierId(SupplierDTO supplierDTO) {
		try {
			//DELETE FROM `supplier_product_type_mapping` WHERE (`supplier_id` = '1') and (`product_type_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" ");
			query.append("WHERE (supplier_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
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
	
	public boolean deleteMappingWithSupplierIdAndProductTypeId(int supplierId, int productTypeId) {
		try {
			//DELETE FROM `supplier_product_type_mapping` WHERE (`supplier_id` = '1') and (`product_type_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" ");
			query.append("WHERE (supplier_id = ?) and (product_type_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierId);
			preparedStatement.setInt(2, productTypeId);
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
	
	public int countSupplierListMappingProductType(String whereClause,ProductTypeDTO productTypeDTO) {
		int countResult = 0;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("COUNT(*) AS total ");	
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" spm ");
			query.append("ON s.supplier_id = spm.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_ProductType).append(" pt ");
			query.append("ON spm.product_type_id = pt.product_type_id ");
			query.append("WHERE pt.product_type_id = ? ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, productTypeDTO.getProductTypeId());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				countResult = resultSet.getInt("total");
			}			
			return countResult;
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return countResult;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public int countSupplierListNotInProductType(String whereClause,ProductTypeDTO productTypeDTO) {
		int totalCount = 0;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("COUNT(*) AS total ");	
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("WHERE s.supplier_id NOT IN ");
			query.append("(SELECT spm.supplier_id FROM ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" spm ");
			query.append("WHERE spm.product_type_id = ?) ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, productTypeDTO.getProductTypeId());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				totalCount = resultSet.getInt("total");
			}			
			return totalCount;
			
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return totalCount;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public int countProductListMappingBySupplier(String whereClause, SupplierDTO supplierDTO) {
		int totalCount = 0;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("COUNT(*) AS total ");	
			query.append("FROM ").append(DBConst.TABLE_ProductType).append(" pt ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" spm ");
			query.append("ON spm.product_type_id = pt.product_type_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON s.supplier_id = spm.supplier_id WHERE s.supplier_id = ? ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				totalCount = resultSet.getInt("total");
			}			
			return totalCount;
			
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return totalCount;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public int countProductListNotInSupplier(String whereClause, SupplierDTO supplierDTO) {
		int totalCount = 0;
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("COUNT(*) AS total ");	
			query.append("FROM ").append(DBConst.TABLE_ProductType).append(" pt ");
			query.append("WHERE pt.product_type_id NOT IN ");
			query.append("(SELECT spm.product_type_id FROM ").append(DBConst.TABLE_Supplier_ProductType_Mapping).append(" spm ");
			query.append("WHERE spm.supplier_id = ?) ");
			
			if(!StringUtils.isNullOrEmpty(whereClause)) {
				query.append(whereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				totalCount = resultSet.getInt("total");
			}			
			return totalCount;
			
		}catch(SQLException e){
			log.error(e.getMessage(), e);
			return totalCount;
		}catch(Exception e) {
 			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}*/
	
}
