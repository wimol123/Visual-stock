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
import th.co.gosoft.audit.cpram.dto.ProvinceDTO;
import th.co.gosoft.audit.cpram.dto.RegionDTO;
import th.co.gosoft.audit.cpram.dto.SubDistrictDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class AddressDAO extends StandardAttributeDAO {
	
	

	private final static Logger logger = Logger.getLogger(AddressDAO.class);
	
	public AddressDAO(Connection connection) {
		super(connection);
	}
	
	public List<AddressDTO> getAddressList() {
		try {			
			/*SELECT sd.sub_distric_id, sd.name AS nameSubDistrict, d.district_id, d.name AS nameDistrict, d.postcode, p.province_id, p.name AS nameProvince, r.region_id, r.name AS nameRegion FROM sub_district sd
			JOIN district d ON sd.district_id = d.district_id
			JOIN province p ON sd.province_id = p.province_id
			JOIN region r ON sd.region_id = r.region_id;*/
			
			connection.setAutoCommit(false);
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT sd.sub_district_id, sd.name AS nameSubDistrict, d.district_id, d.name AS nameDistrict, d.postcode, p.province_id, p.name AS nameProvince, r.region_id, r.name AS nameRegion ");
			queryString.append("FROM ").append(DBConst.TABLE_Sub_District).append(" sd ");
			queryString.append("JOIN ").append(DBConst.TABLE_District).append(" d ").append("ON sd.district_id = d.district_id ");
			queryString.append("JOIN ").append(DBConst.TABLE_Province).append(" p ").append("ON sd.province_id = p.province_id ");
			queryString.append("JOIN ").append(DBConst.TABLE_Region).append(" r ").append("ON sd.region_id = r.region_id;");
			
			preparedStatement = connection.prepareStatement(queryString.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<AddressDTO> supplierAddressDTOs = new ArrayList<>();
			while(resultSet.next()) {
				
				RegionDTO regionDTO = new RegionDTO();
				regionDTO.setRegionId(resultSet.getInt("region_id"));
				regionDTO.setName(resultSet.getString("nameRegion"));
				
				
				ProvinceDTO provinceDTO = new ProvinceDTO();
				provinceDTO.setProvinceId(resultSet.getInt("province_id"));
				provinceDTO.setName(resultSet.getString("nameProvince"));
				//provinceDTO.setRegionId(regionDTO);
				
				DistrictDTO districtDTO = new DistrictDTO();
				districtDTO.setDistrictId(resultSet.getInt("district_id"));
				districtDTO.setName(resultSet.getString("nameDistrict"));
				districtDTO.setPostcode(resultSet.getString("postcode"));
				//districtDTO.setRegionId(regionDTO);
				//districtDTO.setProvinceId(provinceDTO);
				
				SubDistrictDTO subDistrictDTO = new SubDistrictDTO();
				subDistrictDTO.setSubDistrictId(resultSet.getInt("sub_district_id"));
				subDistrictDTO.setName(resultSet.getString("nameSubDistrict"));
				//subDistrictDTO.setDistrictId(districtDTO);
				//subDistrictDTO.setRegionId(regionDTO);
				//subDistrictDTO.setProvinceId(provinceDTO);
						
				AddressDTO supplierAddressDTO = new AddressDTO();
				supplierAddressDTO.setSubDistrictId(subDistrictDTO);
				supplierAddressDTO.setDistrictId(districtDTO);
				supplierAddressDTO.setProvinceId(provinceDTO);
				supplierAddressDTO.setPostcode(resultSet.getString("postcode"));
				
				supplierAddressDTOs.add(supplierAddressDTO);
			}
			
			return supplierAddressDTOs;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
 			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public List<AddressDTO> getAddressDetailList(String queryWhereClause) {
		try {			
			/*SELECT sd.sub_distric_id, sd.name AS nameSubDistrict, d.district_id, d.name AS nameDistrict, d.postcode, p.province_id, p.name AS nameProvince, r.region_id, r.name AS nameRegion FROM sub_district sd
			JOIN district d ON sd.district_id = d.district_id
			JOIN province p ON sd.province_id = p.province_id
			JOIN region r ON sd.region_id = r.region_id;*/
			
			connection.setAutoCommit(false);
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT sd.sub_district_id, sd.name AS nameSubDistrict, d.district_id, d.name AS nameDistrict, d.postcode, p.province_id, p.name AS nameProvince, r.region_id, r.name AS nameRegion ");
			queryString.append("FROM ").append(DBConst.TABLE_Sub_District).append(" sd ");
			queryString.append("JOIN ").append(DBConst.TABLE_District).append(" d ").append("ON sd.district_id = d.district_id ");
			queryString.append("JOIN ").append(DBConst.TABLE_Province).append(" p ").append("ON sd.province_id = p.province_id ");
			queryString.append("JOIN ").append(DBConst.TABLE_Region).append(" r ").append("ON sd.region_id = r.region_id ");
			queryString.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				queryString.append(queryWhereClause);
			}
			
			preparedStatement = connection.prepareStatement(queryString.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<AddressDTO> supplierAddressDTOs = new ArrayList<>();
			while(resultSet.next()) {
				
				RegionDTO regionDTO = new RegionDTO();
				regionDTO.setRegionId(resultSet.getInt("region_id"));
				regionDTO.setName(resultSet.getString("nameRegion"));
				
				
				ProvinceDTO provinceDTO = new ProvinceDTO();
				provinceDTO.setProvinceId(resultSet.getInt("province_id"));
				provinceDTO.setName(resultSet.getString("nameProvince"));
				//provinceDTO.setRegionId(regionDTO);
				
				DistrictDTO districtDTO = new DistrictDTO();
				districtDTO.setDistrictId(resultSet.getInt("district_id"));
				districtDTO.setName(resultSet.getString("nameDistrict"));
				districtDTO.setPostcode(resultSet.getString("postcode"));
				//districtDTO.setRegionId(regionDTO);
				//districtDTO.setProvinceId(provinceDTO);
				
				SubDistrictDTO subDistrictDTO = new SubDistrictDTO();
				subDistrictDTO.setSubDistrictId(resultSet.getInt("sub_district_id"));
				subDistrictDTO.setName(resultSet.getString("nameSubDistrict"));
				//subDistrictDTO.setDistrictId(districtDTO);
				//subDistrictDTO.setRegionId(regionDTO);
				//subDistrictDTO.setProvinceId(provinceDTO);
						
				AddressDTO supplierAddressDTO = new AddressDTO();
				supplierAddressDTO.setSubDistrictId(subDistrictDTO);
				supplierAddressDTO.setDistrictId(districtDTO);
				supplierAddressDTO.setProvinceId(provinceDTO);
				supplierAddressDTO.setPostcode(resultSet.getString("postcode"));
				
				supplierAddressDTOs.add(supplierAddressDTO);
			}
			
			return supplierAddressDTOs;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
 			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public List<AddressDTO> getAddressList(String queryWhereClause) {
		try {			
			//SELECT a.address_id, a.address, a.sub_district_id, a.district_id, a.postcode, a.province_id FROM address a WHERE 1=1
			
			connection.setAutoCommit(false);
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT a.address_id, a.address, a.sub_district_id, a.district_id, a.postcode, a.province_id ");
			queryString.append("FROM ").append(DBConst.TABLE_Address).append(" a ");
			queryString.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				queryString.append(queryWhereClause);
			}
			queryString.append(";");
			
			preparedStatement = connection.prepareStatement(queryString.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<AddressDTO> supplierAddressDTOs = new ArrayList<>();
			while(resultSet.next()) {
				
								
				ProvinceDTO provinceDTO = new ProvinceDTO();
				provinceDTO.setProvinceId(resultSet.getInt("province_id"));
				
				DistrictDTO districtDTO = new DistrictDTO();
				districtDTO.setDistrictId(resultSet.getInt("district_id"));
				districtDTO.setPostcode(resultSet.getString("postcode"));
				
				SubDistrictDTO subDistrictDTO = new SubDistrictDTO();
				subDistrictDTO.setSubDistrictId(resultSet.getInt("sub_district_id"));
			
						
				AddressDTO supplierAddressDTO = new AddressDTO();
				supplierAddressDTO.setSubDistrictId(subDistrictDTO);
				supplierAddressDTO.setDistrictId(districtDTO);
				supplierAddressDTO.setProvinceId(provinceDTO);
				supplierAddressDTO.setPostcode(resultSet.getString("postcode"));
				supplierAddressDTO.setAddressId(resultSet.getInt("address_id"));
				supplierAddressDTO.setAddress(resultSet.getString("address"));
				
				supplierAddressDTOs.add(supplierAddressDTO);
			}
			
			return supplierAddressDTOs;
			
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
 			return null;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();			
		}
	}
	
	
	public int insertAddress(AddressDTO addressDTO) {
		int addressId = 0;
		try {
			//INSERT INTO `address` (`address`, `sub_district_id`, `district_id`, `postcode`, `province_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('2222', '1', '1', '12345', '1', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Address).append(" ");
			query.append("(address, sub_district_id, district_id, postcode, province_id, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffect = 0;
			preparedStatement.setString(index++, addressDTO.getAddress());
			preparedStatement.setInt(index++, addressDTO.getSubDistrictId().getSubDistrictId());
			preparedStatement.setInt(index++, addressDTO.getDistrictId().getDistrictId());
			preparedStatement.setString(index++, addressDTO.getPostcode());
			preparedStatement.setInt(index++, addressDTO.getProvinceId().getProvinceId());
			preparedStatement.setString(index++, NullUtils.cvStr(addressDTO.getEnable()));
			preparedStatement.setInt(index++, addressDTO.getCreateBy());
			preparedStatement.setInt(index++, addressDTO.getUpdateBy());
			
			rowAffect = preparedStatement.executeUpdate();
			if(rowAffect == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					addressId = resultSet.getInt(1);					
				}
			}
			
			return addressId;
		}catch(SQLException e) {
			logger.error(e.getMessage(), e);
 			return addressId;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();			
		}
	}
	
	public boolean updateAddress(AddressDTO addressDTO) {
		try {
			//UPDATE `address` SET `address` = '23/992', `sub_district_id` = '212', `district_id` = '32', `postcode` = '10531', `province_id` = '2', `update_by` = '3', `update_date` = 'now()' WHERE (`address_id` = '6');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Address).append(" SET ");
			query.append("address = ?, ");
			query.append("sub_district_id = ?, ");
			query.append("district_id = ?, ");
			query.append("postcode = ?, ");
			query.append("province_id = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (address_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setString(index++, addressDTO.getAddress());
			preparedStatement.setInt(index++, addressDTO.getSubDistrictId().getSubDistrictId());
			preparedStatement.setInt(index++, addressDTO.getDistrictId().getDistrictId());
			preparedStatement.setString(index++, addressDTO.getPostcode());
			preparedStatement.setInt(index++, addressDTO.getProvinceId().getProvinceId());
			preparedStatement.setInt(index++, addressDTO.getUpdateBy());
			preparedStatement.setInt(index++, addressDTO.getAddressId());
			preparedStatement.executeUpdate();
				
			return true;
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
	
	public boolean deleteAddress(AddressDTO addressDTO) {
		//DELETE FROM `audit_supplier_cpram_final`.`address` WHERE (`address_id` = '1');
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Address).append(" ");
			query.append("WHERE (address_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, addressDTO.getAddressId());
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
	
	
}
