package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.StandardDocumentDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierStandardDocumentDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class SupplierStandardDocumentDAO extends StandardAttributeDAO{

	public SupplierStandardDocumentDAO(Connection connection) {
		super(connection);
	}

	private final static Logger logger = Logger.getLogger(SupplierStandardDocumentDAO.class);	
	
	public List<SupplierStandardDocumentDTO> getSupplierStandardDocumentList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT sup_std_doc.supplier_standard_document_id, sup_std_doc.supplier_standard_document_location, sup_std_doc.supplier_id, sup.supplier_company, std_doc.standard_document_id, std_doc.standard_document_name
			FROM supplier_standard_document sup_std_doc
			LEFT JOIN supplier sup ON sup_std_doc.supplier_id = sup.supplier_id
			LEFT JOIN standard_document std_doc ON sup_std_doc.standard_document_id = std_doc.standard_document_id
			WHERE 1=1*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT sup_std_doc.supplier_standard_document_id, sup_std_doc.supplier_standard_document_location, sup_std_doc.supplier_standard_document_expire_date, sup_std_doc.supplier_id, sup.supplier_company, std_doc.standard_document_id, std_doc.standard_document_name,uc.user_id create_id, uc.fullname create_name ,uu.user_id update_id, uu.fullname update_name, sup_std_doc.create_by, sup_std_doc.create_date, sup_std_doc.update_by, sup_std_doc.update_date ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Standard_Document).append(" sup_std_doc ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" sup ");
			query.append("ON sup_std_doc.supplier_id = sup.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" uc ");
			query.append("ON uc.user_id = sup_std_doc.create_by ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" uu ");
			query.append("ON uu.user_id = sup_std_doc.update_by ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Standard_Document).append(" std_doc ");
			query.append("ON sup_std_doc.standard_document_id = std_doc.standard_document_id WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by sup_std_doc.supplier_standard_document_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<SupplierStandardDocumentDTO> supplierStandardDocumentDTOs = new ArrayList<>();
			while (resultSet.next()) {
				SupplierStandardDocumentDTO supplierStandardDocumentDTO = new SupplierStandardDocumentDTO();
				supplierStandardDocumentDTO.setSupplierStandardDocumentId(resultSet.getInt("supplier_standard_document_id"));
				supplierStandardDocumentDTO.setSupplierStandardDocumentLocation(resultSet.getString("supplier_standard_document_location"));
				supplierStandardDocumentDTO.setSupplierStandardDocumentExpireDate(resultSet.getDate("supplier_standard_document_expire_date"));
				supplierStandardDocumentDTO.setSupplierStandardDocumentExpireTime(resultSet.getTime("supplier_standard_document_expire_date"));
				
				supplierStandardDocumentDTO.setSupplierId(new SupplierDTO());
				supplierStandardDocumentDTO.getSupplierId().setSupplierId(resultSet.getInt("supplier_id"));
				supplierStandardDocumentDTO.getSupplierId().setSupplierCompany(resultSet.getString("supplier_company"));
				
				supplierStandardDocumentDTO.setStandardDocumentId(new StandardDocumentDTO());
				supplierStandardDocumentDTO.getStandardDocumentId().setStandardDocumentId(resultSet.getInt("standard_document_id"));
				supplierStandardDocumentDTO.getStandardDocumentId().setStandardDocumentName(resultSet.getString("standard_document_name"));
				
				UserDTO userCreate = new UserDTO();
				userCreate.setUserId(resultSet.getInt("create_id"));
				userCreate.setUsername(resultSet.getString("create_name"));
				supplierStandardDocumentDTO.setCreateUser(userCreate);

				UserDTO userUpdate = new UserDTO();
				userUpdate.setUserId(resultSet.getInt("update_id"));
				userUpdate.setUsername(resultSet.getString("update_name"));
				supplierStandardDocumentDTO.setUpdateUser(userUpdate);

				supplierStandardDocumentDTO.setCreateBy(resultSet.getInt("create_by"));
				supplierStandardDocumentDTO.setCreateDate(resultSet.getDate("create_date"));
				supplierStandardDocumentDTO.setCreateTime(resultSet.getTime("create_date"));
				supplierStandardDocumentDTO.setUpdateBy(resultSet.getInt("update_by"));
				supplierStandardDocumentDTO.setUpdateDate(resultSet.getDate("update_date"));
				supplierStandardDocumentDTO.setUpdateTime(resultSet.getTime("update_date"));
				
				supplierStandardDocumentDTOs.add(supplierStandardDocumentDTO);
			}
			return supplierStandardDocumentDTOs;
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
	
	public boolean insertSupplierStandardDocument(SupplierStandardDocumentDTO supplierStandardDocumentDTO) {
		try {
			//INSERT INTO `supplier_standard_document` (`supplier_standard_document_location`, `supplier_id`, `standard_document_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('sdfsdfsdf', '12', '1', 'Y', '1', 'now()', '1', 'now()');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Supplier_Standard_Document).append(" ");
			query.append("(supplier_standard_document_location, supplier_standard_document_expire_date, supplier_id, standard_document_id, enable, create_by, create_date, update_by, update_date) ");
			query.append(" VALUE ");
			query.append("(?, ?, ?, ?, ?, ?, now(), ?, now());");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, supplierStandardDocumentDTO.getSupplierStandardDocumentLocation());
			preparedStatement.setString(index++, String.format("%s %s", supplierStandardDocumentDTO.getSupplierStandardDocumentExpireDate(), supplierStandardDocumentDTO.getSupplierStandardDocumentExpireTime()));
			preparedStatement.setInt(index++, supplierStandardDocumentDTO.getSupplierId().getSupplierId());
			preparedStatement.setInt(index++, supplierStandardDocumentDTO.getStandardDocumentId().getStandardDocumentId());
			preparedStatement.setString(index++, NullUtils.cvStr(supplierStandardDocumentDTO.getEnable()));
			preparedStatement.setInt(index++, supplierStandardDocumentDTO.getCreateBy());
			preparedStatement.setInt(index++, supplierStandardDocumentDTO.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
			
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
	
	public boolean updateExpireDateSupplierStandardDocument(SupplierStandardDocumentDTO supplierStandardDocumentDTO) {
		try {
			
			//UPDATE `audit_supplier_cpram`.`supplier_standard_document` SET `supplier_standard_document_expire_date` = '2021-11-26 00:00:00', `update_by` = '55', `update_date` = '2020-11-25 12:21:00' WHERE (`supplier_standard_document_id` = '210');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Supplier_Standard_Document).append(" ");
			query.append("SET `supplier_standard_document_expire_date` = ?, ");
			query.append("`update_by` = ?, ");
			query.append("`update_date` = now() ");
			query.append("WHERE (`supplier_standard_document_id` = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			
			preparedStatement.setString(index++, String.format("%s %s", supplierStandardDocumentDTO.getSupplierStandardDocumentExpireDate(), supplierStandardDocumentDTO.getSupplierStandardDocumentExpireTime()));
			preparedStatement.setInt(index++, supplierStandardDocumentDTO.getUpdateBy());
			preparedStatement.setInt(index++, supplierStandardDocumentDTO.getSupplierStandardDocumentId());
			rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}
			
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return false;
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean deleteSupplierStandardDocument(SupplierStandardDocumentDTO supplierStandardDocumentDTO) {
		try {
			//DELETE FROM `supplier_standard_document` WHERE (`supplier_standard_document_id` = '1');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_Standard_Document).append(" ");
			query.append("WHERE (supplier_standard_document_id = ?);");
			preparedStatement = connection.prepareStatement(query.toString());
			preparedStatement.setInt(1, supplierStandardDocumentDTO.getSupplierStandardDocumentId());
			int rowAffective = preparedStatement.executeUpdate();
			if(rowAffective == 1) {
				return true;
			}else {
				return false;
			}			
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
	
	public int countSupplierStandardDocumentList(String queryWhereClause) {
		int totalSupplierStandardDocument = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Standard_Document).append(" sup_std_doc ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" sup ");
			query.append("ON sup_std_doc.supplier_id = sup.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Standard_Document).append(" std_doc ");
			query.append("ON sup_std_doc.standard_document_id = std_doc.standard_document_id WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalSupplierStandardDocument = resultSet.getInt("total");
			}
			return totalSupplierStandardDocument;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalSupplierStandardDocument;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
}
