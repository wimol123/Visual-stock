package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.DistrictDTO;
import th.co.gosoft.audit.cpram.dto.ProvinceDTO;
import th.co.gosoft.audit.cpram.dto.RegionDTO;
import th.co.gosoft.audit.cpram.dto.SubDistrictDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;

public class SupplierAddressMappingDAO extends StandardAttributeDAO {
	
	public SupplierAddressMappingDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(SupplierAddressMappingDAO.class);
	
		
	public AddressDTO getAddresWithSupplierId(SupplierDTO supplierDTO) {
		try {
			
			/*SELECT p.province_id, p.name AS province_name, r.region_id, r.name AS region_name, d.district_id, d.name AS district_name, sd.sub_district_id, sd.name AS sub_district_name 
			FROM address a 
			LEFT JOIN province p ON a.province_id = p.province_id
			LEFT JOIN region r ON p.region_id = r.region_id
			LEFT JOIN district d ON a.district_id = d.district_id
			LEFT JOIN sub_district sd ON a.sub_district_id = sd.sub_district_id
			RIGHT JOIN supplier_address_mapping sam ON a.address_id = sam.address_id
			LEFT JOIN supplier s ON sam.supplier_id = s.supplier_id 
			WHERE s.supplier_id = 8;*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT a.address_id, a.address, a.postcode, p.province_id, p.name AS province_name, r.region_id, r.name AS region_name, d.district_id, d.name AS district_name, sd.sub_district_id, sd.name AS sub_district_name ");
			query.append("FROM ").append(DBConst.TABLE_Address).append(" a ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Province).append(" p ");
			query.append("ON a.province_id = p.province_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Region).append(" r ");
			query.append("ON p.region_id = r.region_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_District).append(" d ");
			query.append("ON a.district_id = d.district_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Sub_District).append(" sd ");
			query.append("ON a.sub_district_id = sd.sub_district_id ");
			query.append("RIGHT JOIN ").append(DBConst.TABLE_Supplier_Address_Mapping).append(" sam ");
			query.append("ON a.address_id = sam.address_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON sam.supplier_id = s.supplier_id ");
			query.append("WHERE s.supplier_id = ?;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			resultSet = preparedStatement.executeQuery();
				
			AddressDTO addressDTO = null;
			while(resultSet.next()) {
				addressDTO = new AddressDTO();
				addressDTO.setAddressId(resultSet.getInt("address_id"));
				addressDTO.setAddress(resultSet.getString("address"));
				addressDTO.setPostcode(resultSet.getString("postcode"));
				
				
				RegionDTO region = new RegionDTO();
				region.setRegionId(resultSet.getInt("region_id"));
				region.setName(resultSet.getString("region_name"));
				
				ProvinceDTO province = new ProvinceDTO();
				province.setProvinceId(resultSet.getInt("province_id"));
				province.setName(resultSet.getString("province_name"));	
				province.setRegionId(region);				
				addressDTO.setProvinceId(province);
				
				DistrictDTO district = new DistrictDTO();
				district.setDistrictId(resultSet.getInt("district_id"));
				district.setName(resultSet.getString("district_name"));
				addressDTO.setDistrictId(district);
				
				SubDistrictDTO subDistrict = new SubDistrictDTO();
				subDistrict.setSubDistrictId(resultSet.getInt("sub_district_id"));
				subDistrict.setName(resultSet.getString("sub_district_name"));
				addressDTO.setSubDistrictId(subDistrict);			
			}
			return addressDTO;
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
	
	public boolean insertSupplierAddressMapping(SupplierDTO supplierDTO) {
		try {
			//INSERT INTO `supplier_address_mapping` (`supplier_id`, `address_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('1', '1', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Supplier_Address_Mapping).append(" ");
			query.append("(supplier_id, address_id, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, 'Y', ?, now(), ?, now());");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, supplierDTO.getSupplierId());
			preparedStatement.setInt(index++, supplierDTO.getAddressId().getAddressId());
			preparedStatement.setInt(index++, supplierDTO.getCreateBy());
			preparedStatement.setInt(index++, supplierDTO.getUpdateBy());
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
	
	public List<SupplierDTO> getSupplierIdList(){
		try {
			//SELECT sam.supplier_id FROM supplier_address_mapping sam;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT sam.supplier_id ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Address_Mapping).append(" sam ");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<SupplierDTO> supplierDTOs = new ArrayList<>();
			while(resultSet.next()) {
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				supplierDTOs.add(supplierDTO);
			}
			return supplierDTOs;
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
	
	
	public boolean deleteSupplierAddressMapping(SupplierDTO supplierDTO) {
		//DELETE FROM `supplier_address_mapping` WHERE (`supplier_id` = '7');
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_Address_Mapping).append(" ");
			query.append("WHERE (supplier_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
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
	
	public boolean checkDupplicateSupplierId(SupplierDTO supplierDTO) {
		try {
			//SELECT sam.supplier_id FROM supplier_address_mapping sam;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT sam.supplier_id ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Address_Mapping).append(" sam ");
			query.append("WHERE sam.supplier_id = ?;");
			
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
	
	
	public int countAddressId(AddressDTO addressDTO) {
		int numOfAddressId = 0;
		try {
			
			/*SELECT COUNT(*) AS total FROM supplier_address_mapping AS sup_add
			WHERE sup_add.address_id = 2;*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT COUNT(*) AS total FROM ").append(DBConst.TABLE_Supplier_Address_Mapping).append(" sup_add ");
			query.append("WHERE sup_add.address_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, addressDTO.getAddressId());
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
	
}
