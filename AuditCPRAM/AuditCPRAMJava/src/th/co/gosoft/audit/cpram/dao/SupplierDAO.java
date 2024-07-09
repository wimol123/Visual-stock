package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class SupplierDAO extends StandardAttributeDAO{
	public SupplierDAO(Connection connection) {
		super(connection);
	}
	
	private final static Logger logger = Logger.getLogger(SupplierDAO.class);
	
	
	
	public List<SupplierDTO> getSupplierList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.logo, s.enable, s.create_by, s.create_date, s.update_by, s.update_date
			FROM supplier s WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.green_card_expire_date, s.audit_round, s.logo, s.enable, s.approval, s.create_by, s.create_date, s.update_by, s.update_date ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			if (numOfRows != -1) {
				query.append(String.format(" order by s.supplier_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<SupplierDTO> supplierDTOs = new ArrayList<>();
			
			while(resultSet.next()) {
				SupplierDTO supplier = new SupplierDTO();
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCode(resultSet.getString("supplier_code"));	
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				supplier.setIsGreenCard(NullUtils.cvChar(resultSet.getString("is_green_card")));
				supplier.setGreenCardExpireDate(resultSet.getDate("green_card_expire_date"));
				supplier.setGreenCardExpireTime(resultSet.getTime("green_card_expire_date"));
				supplier.setAuditRound(resultSet.getInt("audit_round"));
				supplier.setLogo(resultSet.getString("logo"));
				supplier.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				supplier.setApproval(NullUtils.cvChar(resultSet.getString("approval")));
				supplier.setCreateBy(resultSet.getInt("create_by"));
				supplier.setCreateDate(resultSet.getDate("create_date"));
				supplier.setCreateTime(resultSet.getTime("create_date"));
				supplier.setUpdateBy(resultSet.getInt("update_by"));
				supplier.setUpdateDate(resultSet.getDate("update_date"));
				supplier.setUpdateTime(resultSet.getTime("update_date"));
				supplierDTOs.add(supplier);
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
	
	public List<SupplierDTO> getSupplierList(String queryWhereClause){
		try {
			
			/*SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.logo, s.enable, s.create_by, s.create_date, s.update_by, s.update_date
			FROM supplier s WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.green_card_expire_date, s.audit_round, s.logo, s.enable, s.approval, s.create_by, s.create_date, s.update_by, s.update_date ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(String.format(" order by s.supplier_id asc "));
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<SupplierDTO> supplierDTOs = new ArrayList<>();
			
			while(resultSet.next()) {
				SupplierDTO supplier = new SupplierDTO();
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCode(resultSet.getString("supplier_code"));	
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				supplier.setIsGreenCard(NullUtils.cvChar(resultSet.getString("is_green_card")));
				supplier.setGreenCardExpireDate(resultSet.getDate("green_card_expire_date"));
				supplier.setGreenCardExpireTime(resultSet.getTime("green_card_expire_date"));
				supplier.setAuditRound(resultSet.getInt("audit_round"));
				supplier.setLogo(resultSet.getString("logo"));
				supplier.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				supplier.setApproval(NullUtils.cvChar(resultSet.getString("approval")));
				supplier.setCreateBy(resultSet.getInt("create_by"));
				supplier.setCreateDate(resultSet.getDate("create_date"));
				supplier.setCreateTime(resultSet.getTime("create_date"));
				supplier.setUpdateBy(resultSet.getInt("update_by"));
				supplier.setUpdateDate(resultSet.getDate("update_date"));
				supplier.setUpdateTime(resultSet.getTime("update_date"));
				supplierDTOs.add(supplier);
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
	
	public int insertSupplier(SupplierDTO supplierDTO) {
		int primaryKeySupplierId = 0;
		try {
			
			//INSERT INTO `supplier` (`supplier_code`, `supplier_company`, `is_green_card`, `approval`, `logo`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) 
			//VALUES ('3333333', 'comB', 'Y', 'Y', 'D:\\AuditCPRam\\Supplier\\Logo\\logo_20190114_112852542.png', 'Y', '1', 'now()', '1', 'now()');
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Supplier).append(" ");
			query.append("(supplier_code, supplier_company, is_green_card, green_card_expire_date, approval, audit_round, logo, enable, create_by, create_date, update_by, update_date)");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, now(), ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffect = 0;
			preparedStatement.setString(index++, supplierDTO.getSupplierCode());
			preparedStatement.setString(index++, supplierDTO.getSupplierCompany());
			preparedStatement.setString(index++, NullUtils.cvStr(supplierDTO.getIsGreenCard()));			
			preparedStatement.setString(index++, (supplierDTO.getGreenCardExpireDate() == null ? null : String.format("%s %s",supplierDTO.getGreenCardExpireDate(), supplierDTO.getGreenCardExpireTime())));
			preparedStatement.setString(index++, NullUtils.cvStr(supplierDTO.getApproval()));
			preparedStatement.setInt(index++, supplierDTO.getAuditRound());
			preparedStatement.setString(index++, supplierDTO.getLogo());
			preparedStatement.setString(index++, NullUtils.cvStr(supplierDTO.getEnable()));
			preparedStatement.setInt(index++, supplierDTO.getCreateBy());
			preparedStatement.setInt(index++, supplierDTO.getUpdateBy());
			rowAffect = preparedStatement.executeUpdate();
			
			if(rowAffect == 1) {
				resultSet = preparedStatement.getGeneratedKeys();
				while(resultSet.next()) {
					primaryKeySupplierId = resultSet.getInt(1);
				}
			}
			return primaryKeySupplierId;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return primaryKeySupplierId;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	
	public boolean updateSupplier(SupplierDTO supplierDTO) {
		try {
			//UPDATE `supplier` SET `supplier_code` = '111111', `supplier_company` = 'sdfsdfsdfsdf', `is_green_card` = 'Y', `approval` = 'Y', `logo` = 'sdgsdfgsdfgsdfg', `enable` = 'N', `update_by` = '3', `update_date` = 'now()' WHERE (`supplier_id` = '9');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Supplier).append(" SET ");
			query.append("supplier_code = ?, ");
			query.append("supplier_company = ?, ");
			query.append("is_green_card = ?, ");
			query.append("green_card_expire_date =?, ");
			query.append("approval = ?, ");
			query.append("audit_round = ?, ");
			query.append(supplierDTO.getLogo());
			query.append("enable = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (supplier_id = ?);");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setString(index++, supplierDTO.getSupplierCode());
			preparedStatement.setString(index++, supplierDTO.getSupplierCompany());
			preparedStatement.setString(index++, NullUtils.cvStr(supplierDTO.getIsGreenCard()));
			preparedStatement.setString(index++, (supplierDTO.getGreenCardExpireDate() == null ? null : String.format("%s %s",supplierDTO.getGreenCardExpireDate(), supplierDTO.getGreenCardExpireTime())));
			preparedStatement.setString(index++, NullUtils.cvStr(supplierDTO.getApproval()));
			preparedStatement.setInt(index++, NullUtils.cvInt(supplierDTO.getAuditRound()));
			preparedStatement.setString(index++, NullUtils.cvStr(supplierDTO.getEnable()));
			preparedStatement.setInt(index++, supplierDTO.getUpdateBy());
			preparedStatement.setInt(index++, supplierDTO.getSupplierId());
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
	
	public boolean deleteSupplier(SupplierDTO supplierDTO) {
		try {
			//DELETE FROM `supplier` WHERE (`supplier_id` = '7');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier).append(" ");
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
	
	public SupplierDTO getLogoSupplierPath(SupplierDTO supplierDTO) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.logo FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("WHERE s.supplier_id = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			resultSet = preparedStatement.executeQuery();
			SupplierDTO result = null;
			while (resultSet.next()) {
				result = new SupplierDTO();
				result.setLogo(resultSet.getString("logo"));				
			}
			return result;
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
	
	public boolean checkDupplicateSupplierCompany(SupplierDTO supplierDTO) {
		try {
			//SELECT s.supplier_company FROM supplier s WHERE s.supplier_company = 'cm';
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_company ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("WHERE s.supplier_company = ?;");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, supplierDTO.getSupplierCompany());
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
	
	public boolean checkDupplicateSupplierCompanyNotInSupplierId(SupplierDTO supplierDTO) {
		try {
			
			//SELECT s.supplier_company FROM supplier s WHERE s.supplier_company = 'บริษัท A จำกัด' AND s.supplier_company NOT IN (SELECT s.supplier_company FROM supplier s WHERE s.supplier_id = 7);
			connection.setAutoCommit(false);
			
			StringBuilder subQuery = new StringBuilder();
			subQuery.append("SELECT sp.supplier_company FROM ").append(DBConst.TABLE_Supplier).append(" sp ");
			subQuery.append("WHERE sp.supplier_id = ? ");			
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_company FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("WHERE s.supplier_company = ? AND ");
			query.append("s.supplier_company NOT IN ( ");
			query.append(subQuery.toString());
			query.append(");");
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, supplierDTO.getSupplierCompany());
			preparedStatement.setInt(2, supplierDTO.getSupplierId());
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
	
	public List<SupplierDTO> getSupllierCompanyList(){		
		try {
			//SELECT s.supplier_company FROM supplier s;
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_company ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s;");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<SupplierDTO> supplierDTOs = new ArrayList<>();
			while(resultSet.next()) {
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
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
	
	
	
	public int countSupplierList(String queryWhereClause) {
		int countSupplier = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
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
	

	public List<SupplierDTO> getSupplierListGroupByProduct(String productTypeId){
		try {
			
			/*SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.green_card_expire_date, s.audit_round, s.logo, s.enable, s.approval, s.create_by, s.create_date, s.update_by, s.update_date
			 FROM (
			SELECT supplier_id,product_type_id FROM audit_supplier_cpram.supplier_product_address_mapping 
			where product_type_id = '1'
			GROUP BY supplier_id,product_type_id) p
			INNER JOIN supplier s ON s.supplier_id = p.supplier_id
			;*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.green_card_expire_date, s.audit_round, s.logo, s.enable, s.approval, s.create_by, s.create_date, s.update_by, s.update_date ");
			query.append("FROM ( ");
			query.append("          SELECT supplier_id,product_type_id ");
			query.append("          FROM ").append(DBConst.TABLE_Supplier_Product_Address_Mapping).append(" s ");
			query.append("          WHERE product_type_id = ? ");
			query.append("          GROUP BY supplier_id,product_type_id ");
			query.append("      ) p ");
			query.append(" INNER JOIN ").append(DBConst.TABLE_Supplier).append(" s ON s.supplier_id = p.supplier_id ");
			
			query.append(String.format(" order by s.supplier_id asc ;"));
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, productTypeId);
			resultSet = preparedStatement.executeQuery();
			List<SupplierDTO> supplierDTOs = new ArrayList<>();

			while(resultSet.next()) {
				SupplierDTO supplier = new SupplierDTO();
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCode(resultSet.getString("supplier_code"));	
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				supplier.setIsGreenCard(NullUtils.cvChar(resultSet.getString("is_green_card")));
				supplier.setGreenCardExpireDate(resultSet.getDate("green_card_expire_date"));
				supplier.setGreenCardExpireTime(resultSet.getTime("green_card_expire_date"));
				supplier.setAuditRound(resultSet.getInt("audit_round"));
				supplier.setLogo(resultSet.getString("logo"));
				supplier.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				supplier.setApproval(NullUtils.cvChar(resultSet.getString("approval")));
				supplier.setCreateBy(resultSet.getInt("create_by"));
				supplier.setCreateDate(resultSet.getDate("create_date"));
				supplier.setCreateTime(resultSet.getTime("create_date"));
				supplier.setUpdateBy(resultSet.getInt("update_by"));
				supplier.setUpdateDate(resultSet.getDate("update_date"));
				supplier.setUpdateTime(resultSet.getTime("update_date"));
				supplierDTOs.add(supplier);
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
	

	public SupplierDTO getSupplierBySupplierId(String supplierId){
		try {
			
			/*SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.green_card_expire_date, s.audit_round, s.logo, s.enable, s.approval, s.create_by, s.create_date, s.update_by, s.update_date
			 FROM (
			SELECT supplier_id,product_type_id FROM audit_supplier_cpram.supplier_product_address_mapping 
			where product_type_id = '1'
			GROUP BY supplier_id,product_type_id) p
			INNER JOIN supplier s ON s.supplier_id = p.supplier_id
			;*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.green_card_expire_date, s.audit_round, s.logo, s.enable, s.approval, s.create_by, s.create_date, s.update_by, s.update_date ");
			query.append(" FROM ").append(DBConst.TABLE_Supplier).append(" s where s.supplier_id = ? ");
			
			query.append(String.format(" order by s.supplier_id asc ;"));
			
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setString(1, supplierId);
			resultSet = preparedStatement.executeQuery();

			SupplierDTO supplier = new SupplierDTO();
			while(resultSet.next()) {
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCode(resultSet.getString("supplier_code"));	
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				supplier.setIsGreenCard(NullUtils.cvChar(resultSet.getString("is_green_card")));
				supplier.setGreenCardExpireDate(resultSet.getDate("green_card_expire_date"));
				supplier.setGreenCardExpireTime(resultSet.getTime("green_card_expire_date"));
				supplier.setAuditRound(resultSet.getInt("audit_round"));
				supplier.setLogo(resultSet.getString("logo"));
				supplier.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				supplier.setApproval(NullUtils.cvChar(resultSet.getString("approval")));
				supplier.setCreateBy(resultSet.getInt("create_by"));
				supplier.setCreateDate(resultSet.getDate("create_date"));
				supplier.setCreateTime(resultSet.getTime("create_date"));
				supplier.setUpdateBy(resultSet.getInt("update_by"));
				supplier.setUpdateDate(resultSet.getDate("update_date"));
				supplier.setUpdateTime(resultSet.getTime("update_date"));
			}
			return supplier;
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
	/*public List<SupplierDTO> getSupplierList(Integer startRecord, Integer numOfRows, String whereClause) {
		try {
			
			SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.status_id, u.fullname, u.email, u.telephone
			FROM supplier s LEFT JOIN supplier_user_mapping sm
			ON s.supplier_id = sm.supplier_id
			LEFT JOIN user u ON sm.user_id = u.user_id;

			connection.setAutoCommit(false);
			List<SupplierDTO>supplierList = new ArrayList<>();			
			
			StringBuilder stringBuild = new StringBuilder();
			stringBuild.append("SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.status_id, u.user_id, u.fullname, u.email, u.telephone ");
			stringBuild.append("FROM ").append(DBConst.TABLE_Supplier).append(" s LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ");
			stringBuild.append("ON s.supplier_id = sm.supplier_id ");
			stringBuild.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			stringBuild.append("ON sm.user_id = u.user_id ");
			stringBuild.append("WHERE 1=1 AND u.user_parent = 0 ");
			if(!StringUtils.isNullOrEmpty(whereClause))
			{
				stringBuild.append(whereClause);
			}		
			if(numOfRows != -1) {
				stringBuild.append(String.format(" order by s.supplier_id asc limit %s,%s", startRecord,numOfRows));
			}
			stringBuild.append(";");
			preparedStatement = connection.prepareStatement(stringBuild.toString());	
			//preparedStatement.setMaxRows(model.getLength());
			
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				SupplierDTO supplier = new SupplierDTO();
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCode(resultSet.getString("supplier_code"));
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				//supplier.setIsGreenCard(resultSet.getInt("is_green_card"));
				//supplier.setStatusId(resultSet.getInt("status_id"));	
				//supplier.setUserId(new ArrayList<>());
				
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setTelephone(resultSet.getString("telephone"));
				//userDTO.setFullName(resultSet.getString("fullname"));
							
				//supplier.getUserId().add(userDTO);
				supplierList.add(supplier);
			}
			return supplierList;
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<SupplierDTO> getSupplierByProduct(DataTablePostParamModel param) {
		try {
			
			String whereClause = buildSupplierByProductIdWhereClause(param);
						
			List<SupplierDTO> supplierDTOList = new ArrayList<SupplierDTO>();
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	s.supplier_id ");
			sb.append("	,s.supplier_code ");
			sb.append("	,s.supplier_company ");
			sb.append("	,s.email ");
			sb.append("	,s.telephone ");
			sb.append("	,s.is_green_card ");
			sb.append("	,s.status_id ");
			sb.append("	,s.create_by ");
			sb.append("	,s.update_by ");
			sb.append("	,s.create_date ");
			sb.append("	,s.update_date ");
			//sb.append("FROM ").append(DBConst.TABLE_Supplier_Product_Mapping).append(" p ");
			sb.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ON p.supplier_id = s.supplier_id ");
			sb.append("WHERE 1=1 ");
			sb.append(whereClause);
			sb.append(" order by s.supplier_id limit ").append(param.getStart()).append(", ").append(param.getLength());
			
//			if(!productId.isEmpty() )
//			{
//				sb.append("WHERE p.product_id = ? AND s.supplier_company like %?%  ");
//				sb.append(" order by s.supplier_id limit ").append(param.getStart()).append(", ").append(param.getLength());
//				preparedStatement = connection.prepareStatement(sb.toString());
//	 			preparedStatement.setInt(1, NullUtils.cvInt(productId)); 
//	 			preparedStatement.setInt(2, NullUtils.cvInt(productId)); 
//			}else
//			{
//				sb.append(" order by s.supplier_id limit ").append(param.getStart()).append(", ").append(param.getLength());
//				preparedStatement = connection.prepareStatement(sb.toString());
//			}
			preparedStatement = connection.prepareStatement(sb.toString());		
 			resultSet = preparedStatement.executeQuery();
 			
 			while(resultSet.next()) {
 				
 				SupplierDTO dto = new SupplierDTO();
 				dto.setSupplierId(resultSet.getInt("supplier_id"));
 				dto.setSupplierCode(resultSet.getString("supplier_code"));
 				dto.setSupplierCompany(resultSet.getString("supplier_company"));
 				//dto.setEmail(resultSet.getString("email"));
 				//dto.setTelephone(resultSet.getString("telephone"));
 				//dto.setIsGreenCard(resultSet.getInt("is_green_card"));				
 				//dto.setStatusId(resultSet.getInt("status_id"));
 				dto.setCreateBy(resultSet.getInt("create_by"));
 				dto.setUpdateBy(resultSet.getInt("update_by"));
 				dto.setCreateDate(resultSet.getDate("create_date"));
 				dto.setUpdateDate(resultSet.getDate("update_date"));
 				
 				supplierDTOList.add(dto);
 			}
			return supplierDTOList;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public List<SupplierDTO> getSupplierForProduct(String productId, Integer startRecord, Integer numOfRows, String whereClause) {
		try {
									
			List<SupplierDTO> supplierDTOList = new ArrayList<SupplierDTO>();
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	s.supplier_id ");
			sb.append("	,s.supplier_code ");
			sb.append("	,s.supplier_company ");
			sb.append("	,s.email ");
			sb.append("	,s.telephone ");
			sb.append("	,s.is_green_card ");
			sb.append("	,s.status_id ");
			sb.append("	,s.create_by ");
			sb.append("	,s.update_by ");
			sb.append("	,s.create_date ");
			sb.append("	,s.update_date ");
			
			sb.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			sb.append("WHERE s.status_id = 1 AND s.supplier_id  not in ");
			//sb.append(" (SELECT sp.supplier_id FROM ").append(DBConst.TABLE_Supplier_Product_Mapping).append(" sp ");
			sb.append(" WHERE sp.product_id = ? ) ");
			
			if(!StringUtils.isNullOrEmpty(whereClause))
			{
				sb.append(whereClause);
			}
			
			sb.append(" ORDER BY s.supplier_id LIMIT ").append(startRecord).append(", ").append(numOfRows);
			logger.debug(sb.toString());
			preparedStatement = connection.prepareStatement(sb.toString());		
			
			preparedStatement.setString(1, productId);
			
 			resultSet = preparedStatement.executeQuery();
 			
 			while(resultSet.next()) {
 				
 				SupplierDTO dto = new SupplierDTO();
 				dto.setSupplierId(resultSet.getInt("supplier_id"));
 				dto.setSupplierCode(resultSet.getString("supplier_code"));
 				dto.setSupplierCompany(resultSet.getString("supplier_company"));
 				//dto.setEmail(resultSet.getString("email"));
 				//dto.setTelephone(resultSet.getString("telephone"));
 				//dto.setIsGreenCard(resultSet.getInt("is_green_card"));				
 				//dto.setStatusId(resultSet.getInt("status_id"));
 				dto.setCreateBy(resultSet.getInt("create_by"));
 				dto.setUpdateBy(resultSet.getInt("update_by"));
 				dto.setCreateDate(resultSet.getDate("create_date"));
 				dto.setUpdateDate(resultSet.getDate("update_date"));
 				
 				supplierDTOList.add(dto);
 			}
			return supplierDTOList;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public int countSupplierForProduct(String productId, String whereClause) {
		try {			
			
			int totalProduct = 0;
			connection.setAutoCommit(false);
			
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	COUNT(*) AS total ");
			sb.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			sb.append("WHERE s.status_id = 1 AND s.supplier_id  not in ");
			//sb.append(" (SELECT sp.supplier_id FROM ").append(DBConst.TABLE_Supplier_Product_Mapping).append(" sp ");
			sb.append(" WHERE sp.product_id = ? ) ");
			if(!StringUtils.isNullOrEmpty(whereClause))
			{
				sb.append(whereClause);
			}
			preparedStatement = connection.prepareStatement(sb.toString());	
			preparedStatement.setString(1, productId);
 			resultSet = preparedStatement.executeQuery();
 			
 			while(resultSet.next()) {
 				totalProduct = resultSet.getInt("total"); 				
 			}
			return totalProduct;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public int countSupplierByProductId(DataTablePostParamModel param) {
		try {			
			
			String whereClause = buildSupplierByProductIdWhereClause(param);
			
			
			int totalProduct = 0;
			connection.setAutoCommit(false);
			
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("	COUNT(*) AS total ");
			//sb.append("FROM ").append(DBConst.TABLE_Supplier_Product_Mapping).append(" p ");
			sb.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ON p.supplier_id = s.supplier_id ");
			sb.append("WHERE 1=1 ");
			sb.append(whereClause);

			preparedStatement = connection.prepareStatement(sb.toString());		
 			resultSet = preparedStatement.executeQuery();
 			
 			while(resultSet.next()) {
 				totalProduct = resultSet.getInt("total"); 				
 			}
			return totalProduct;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public int getUserId(int supplierId) {
		try {
			connection.setAutoCommit(false);
			
			StringBuilder stringBuild = new StringBuilder();
			stringBuild.append("SELECT u.user_id ");
			stringBuild.append("FROM ").append(DBConst.TABLE_Supplier).append(" s LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ");
			stringBuild.append("ON s.supplier_id = sm.supplier_id ");
			stringBuild.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			stringBuild.append("ON sm.user_id = u.user_id ");
			stringBuild.append("WHERE s.supplier_id = ? AND u.user_parent = 0");
			
			preparedStatement = connection.prepareStatement(stringBuild.toString());
			preparedStatement.setInt(1, supplierId);
			resultSet = preparedStatement.executeQuery();
			int userId = 0;
			while(resultSet.next()) {
				userId = resultSet.getInt("user_id");
			}
			return userId;
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public List<SupplierDTO> getSupplierList() {
		try {
			connection.setAutoCommit(false);
			List<SupplierDTO> supplierList = new ArrayList<>();
			StringBuilder stringBuild = new StringBuilder();
			stringBuild.append("SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.status_id");
			stringBuild.append(" FROM ");
			stringBuild.append("SUPPLIER s ");
			stringBuild.append("order by s.supplier_id asc;");
			preparedStatement = connection.prepareStatement(stringBuild.toString());
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				SupplierDTO supplier = new SupplierDTO();
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCode(resultSet.getString("supplier_code"));
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				//supplier.setEmail(resultSet.getString("email"));
				//supplier.setStatusId(resultSet.getInt("status_id"));
				supplierList.add(supplier);
			}
			return supplierList;
			
		} catch (SQLException e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
		
	}
	
	public String buildSupplierByProductIdWhereClause(DataTablePostParamModel param)
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
						sb.append(colName).append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else 
					{
						sb.append(colName).append(" = '").append(col.getSearch().getValue()).append("' ");
					}
				}
			}
		}
		
		return sb.toString();
	}
	
	public String buildWhereClause(DataTablePostParamModel param) {
		StringBuilder stringBuild = new StringBuilder();
		String company = param.getColumns().get(1).getSearch().getValue().trim();
		String email = param.getColumns().get(3).getSearch().getValue().trim();
		String statusId = param.getColumns().get(4).getSearch().getValue().trim();
		
		
		if(!company.equals("")) {
			stringBuild.append(String.format(" && (s.supplier_company LIKE '%%%s%%')", company));
		}
		if(!email.equals("")) {
			stringBuild.append(String.format(" && (s.email LIKE '%%%s%%')", email));
		}
		if(!statusId.equals("")) {
			stringBuild.append(String.format(" && (s.status_id = %s)",statusId));	
		}
		
		for(Column col : param.getColumns()) {
			if(!StringUtils.isNullOrEmpty(col.getName()))
			{
				if(!StringUtils.isNullOrEmpty(col.getSearch().getValue()))
				{
					String data [] = col.getName().split("\\|");
					String colName  = data[0];
					String op = data[1];					
					if(op.equals("LIKE")) {
						stringBuild.append(String.format(" && (s.%s %s '%%%s%%')", colName,op,col.getSearch().getValue()));	
					}else {
						stringBuild.append(String.format(" && (s.%s %s %s)", colName,op,col.getSearch().getValue()));	
					}
				}
			}
		}
		
		
		return stringBuild.toString();
		
	}
	
	
	public SupplierDTO getSupplierDetail(String supplierId) {
		try {
			connection.setAutoCommit(false);
			StringBuilder stringBuild = new StringBuilder();
			
			stringBuild.append("SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.status_id, u.user_id, u.fullname, u.email, u.telephone ");
			stringBuild.append("FROM ").append(DBConst.TABLE_Supplier).append(" s LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ");
			stringBuild.append("ON s.supplier_id = sm.supplier_id ");
			stringBuild.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			stringBuild.append("ON sm.user_id = u.user_id ");
			stringBuild.append("WHERE s.supplier_id = ? AND u.user_parent = 0 order by s.supplier_id asc;");
			preparedStatement = connection.prepareStatement(stringBuild.toString());
			preparedStatement.setString(1, NullUtils.cvStr(supplierId));
			resultSet = preparedStatement.executeQuery();
			SupplierDTO supplier = new SupplierDTO();
			while(resultSet.next()) {				
				supplier.setSupplierId(resultSet.getInt("supplier_id"));
				supplier.setSupplierCode(resultSet.getString("supplier_code"));
				supplier.setSupplierCompany(resultSet.getString("supplier_company"));
				//supplier.setEmail(resultSet.getString("email"));
				//supplier.setTelephone(resultSet.getString("telephone"));
				//supplier.setIsGreenCard(resultSet.getInt("is_green_card"));				
				//supplier.setStatusId(resultSet.getInt("status_id"));
				//supplier.setUserId(new ArrayList<>());
				
				UserDTO userDTO = new UserDTO();
				userDTO.setUserId(resultSet.getInt("user_id"));
				//userDTO.setFullName(resultSet.getString("fullname"));
				userDTO.setEmail(resultSet.getString("email"));
				userDTO.setTelephone(resultSet.getString("telephone"));
				//supplier.getUserId().add(userDTO);
				//supplier.setUserId(userDTO);
			}
			
			stringBuild.setLength(0);
			stringBuild = new StringBuilder();
			stringBuild.append("SELECT s.*, p.* ");
			stringBuild.append("from supplier s ");
			stringBuild.append("join supplier_product_mapping sp on s.supplier_id = sp.supplier_id ");
			stringBuild.append("join product p on sp.product_id = p.product_id ");
			stringBuild.append("where s.supplier_code = ?;");
			preparedStatement = connection.prepareStatement(stringBuild.toString());
			preparedStatement.setString(1, NullUtils.cvStr(supplierCode));
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				System.out.println(resultSet.getString("product_name"));
			}
			return supplier;
		} catch (SQLException e) {
			logger.error(e.toString(), e);
 			throw new RuntimeException(e.toString(), e);
		}
		
	}
	
	public boolean delete(SupplierDTO supplierDTO){
		try {
			connection.setAutoCommit(false);
			
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM supplier "); 
			query.append("WHERE supplier_id = ? "); 
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierDTO.getSupplierId());
			preparedStatement.executeUpdate();
			
			connection.commit();				
			return true;
			
			
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean UpdateSupplier(SupplierDTO supplierDTO) {
		try {
			
			connection.setAutoCommit(false);
			//UPDATE `supplier` SET `supplier_code` = 'comA', `supplier_company` = 'Company A1', `is_green_card` = '0', `status_id` = '0', `update_by` = '8', `update_date` = 'now()' WHERE (`supplier_id` = '1');
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Supplier);
			query.append(" SET ");
			query.append("supplier_code = ?, ");
			query.append("supplier_company = ?, ");
			query.append("is_green_card = ?, ");
			query.append("status_id = ?, ");
			query.append("update_by = ?, ");
			query.append("update_date = now() ");
			query.append("WHERE (supplier_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setString(index++, supplierDTO.getSupplierCode());
			preparedStatement.setString(index++, supplierDTO.getSupplierCompany());
			preparedStatement.setInt(index++, supplierDTO.getIsGreenCard());
			//preparedStatement.setInt(index++, supplierDTO.getStatusId());
			preparedStatement.setInt(index++, supplierDTO.getUpdateBy());
			preparedStatement.setInt(index++, supplierDTO.getSupplierId());			
			
			preparedStatement.executeUpdate();
			connection.commit();
			return true;
			
		}catch(SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public boolean checkDupplicateSupplier(SupplierModel supplierModel) {
		boolean checkDuplicateSupplier = false;
		try {
			
			connection.setAutoCommit(false);
			
			//SELECT s.supplier_code,s.supplier_company FROM supplier s WHERE s.supplier_code = '111121';
			SELECT s.supplier_id, s.supplier_code, s.supplier_company, s.is_green_card, s.status_id, u.email, u.telephone
			FROM supplier s LEFT JOIN supplier_user_mapping sm
			ON s.supplier_id = sm.supplier_id
			LEFT JOIN user u ON sm.user_id = u.user_id;
			StringBuilder sb = null;
			for(UserModel userModel : supplierModel.getUserId()) {
			sb = new StringBuilder();
			sb.setLength(0);
			sb.append("SELECT u.email,s.supplier_company ");
			sb.append("FROM ").append(DBConst.TABLE_Supplier).append(" s ");
			sb.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ");
				sb.append("ON s.supplier_id = sm.supplier_id ");
				sb.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ON sm.user_id = u.user_id ");
				sb.append("WHERE s.supplier_company = ? OR u.email = ?;");
				preparedStatement = connection.prepareStatement(sb.toString());
			preparedStatement.setString(1, supplierModel.getSupplierCompany().trim());
				preparedStatement.setString(2, userModel.getEmail().trim());
				resultSet = preparedStatement.executeQuery();
				if(resultSet.next()) {					
					checkDuplicateSupplier = true;
					break;
				}
			}
			
			return checkDuplicateSupplier;
		}catch(SQLException e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public Map<String, Object> InsertSupplier(SupplierModel _supplier,String _userId) {
		
		try {		
		
		//INSERT INTO `audit_supplier_cpram`.`supplier` (`supplier_code`, `email`, `telephone`, `status_id`, `create_by`, `create_date`, `update_by`, `update_date`, `supplier_company`, `is_green_card`) VALUES ('121212', 'test4@gosoft.co.th', '000000000', '0', '12', '2018-10-11 18:00:37', '12', '2018-10-11 18:00:37', 'Company D', '1');
		StringBuilder query = new StringBuilder();
		query.append("INSERT INTO ").append(DBConst.TABLE_Supplier).append(" ");
		query.append("(supplier_code, supplier_company, is_green_card, logo, status_id, create_by, create_date, update_by, update_date)");
		query.append(" VALUES ");
		query.append("(?, ?, ?, ?, ?, ?, now(), ?, now());");
		
		preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
		int index = 1, supplierId = 0;
		preparedStatement.setString(index++, NullUtils.cvStr(_supplier.getSupplierCode()));		
		preparedStatement.setString(index++, NullUtils.cvStr(_supplier.getSupplierCompany()));
		preparedStatement.setInt(index++, NullUtils.cvInt(_supplier.getIsGreenCard()));
		preparedStatement.setString(index++, NullUtils.cvStr(_supplier.getLogo()));
		//preparedStatement.setInt(index++, NullUtils.cvInt(_supplier.getStatusId()));
		preparedStatement.setInt(index++, NullUtils.cvInt(_userId));
		preparedStatement.setInt(index++, NullUtils.cvInt(_userId));
		//preparedStatement.setInt(index++, NullUtils.cvInt(Boolean.parseBoolean(_supplier.getStatusId()) ? 1 : 0));
		int rowAffect = preparedStatement.executeUpdate();	
		if (rowAffect == 1) {
			resultSet = preparedStatement.getGeneratedKeys();
			while (resultSet.next())
				supplierId = resultSet.getInt(1);
		}
		connection.commit();	
		
		Map<String, Object> resultInsert = new HashMap<>();
		resultInsert.put("result", true);
		resultInsert.put("supplierId", supplierId);
			
		return resultInsert;
		} catch (SQLException e) {
			DatabaseUtils.rollback(connection);
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	
	public int countSupplier(String whereClause) {
		try {
			int totalSupplier = 0;
			connection.setAutoCommit(false);
 			StringBuilder sb = new StringBuilder();
			sb.append("SELECT ");
			sb.append("COUNT(*) AS total ");
			sb.append("FROM ").append(DBConst.TABLE_Supplier).append(" s LEFT JOIN ").append(DBConst.TABLE_Supplier_User_Mapping).append(" sm ");
			sb.append("ON s.supplier_id = sm.supplier_id ");
			sb.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			sb.append("ON sm.user_id = u.user_id ");
			sb.append("WHERE 1=1 AND u.user_parent = 0 ");
			if(!StringUtils.isNullOrEmpty(whereClause))
			{
				sb.append(whereClause);
			}
			sb.append(";");
 			preparedStatement = connection.prepareStatement(sb.toString());
 			resultSet = preparedStatement.executeQuery();
 			while(resultSet.next()) {

 				totalSupplier = resultSet.getInt("total"); 				
 			}
			return totalSupplier;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}
	}
	*/

	
}
