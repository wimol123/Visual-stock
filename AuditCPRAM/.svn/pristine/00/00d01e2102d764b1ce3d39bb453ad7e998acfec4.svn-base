package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.QuestionaireDocumentDTO;
import th.co.gosoft.audit.cpram.dto.QuestionaireDocumentTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierQuestionaireDocumentDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class SupplierQuestionaireDocumentDAO extends StandardAttributeDAO{

	private final static Logger logger = Logger.getLogger(SupplierQuestionaireDocumentDAO.class);	
	
	public SupplierQuestionaireDocumentDAO(Connection connection) {
		super(connection);
	}
	
	public List<SupplierQuestionaireDocumentDTO> getSupplierQuestionaireDocumentList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		/*SELECT sqd.supplier_questionaire_document_id, sqd.supplier_questionaire_document_location, sqd.enable, sqd.create_by, sqd.create_date, sqd.update_by, sqd.update_date,
		qdt.questionaire_document_type_id, qdt.questionaire_document_type_name,
		s.supplier_id, s.supplier_code, s.supplier_company,
		qd.questionaire_document_id, qd.questionaire_document_name
		FROM supplier_questionaire_document sqd
		LEFT JOIN questionaire_document_type qdt ON sqd.questionaire_document_type_id = qdt.questionaire_document_type_id
		LEFT JOIN supplier s ON sqd.supplier_id = s.supplier_id
		LEFT JOIN questionaire_document qd ON sqd.questionaire_document_id = qd.questionaire_document_id
		WHERE 1=1*/		
		try {
						
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(" SELECT sqd.supplier_questionaire_document_id, sqd.supplier_questionaire_document_location, sqd.enable, sqd.create_by, sqd.create_date, sqd.update_by, sqd.update_date,");
			query.append(" qdt.questionaire_document_type_id, qdt.questionaire_document_type_name, s.supplier_id, s.supplier_code, s.supplier_company, qd.questionaire_document_id, qd.questionaire_document_name ");
			query.append(" FROM ").append(DBConst.TABLE_Supplier_Questionaire_Document).append(" sqd ");
			query.append(" LEFT JOIN ").append(DBConst.TABLE_Questionaire_Document_Type).append(" qdt ");
			query.append(" ON sqd.questionaire_document_type_id = qdt.questionaire_document_type_id ");
			query.append(" LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append(" ON sqd.supplier_id = s.supplier_id ");
			query.append(" LEFT JOIN ").append(DBConst.TABLE_Questionaire_Document).append(" qd ");
			query.append(" ON sqd.questionaire_document_id = qd.questionaire_document_id ");
			query.append(" WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by sqd.supplier_questionaire_document_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<SupplierQuestionaireDocumentDTO> supplierQuestionaireDocumentDTOList = new ArrayList<SupplierQuestionaireDocumentDTO>();
			while (resultSet.next()) {
				SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTO = new SupplierQuestionaireDocumentDTO();
				
				supplierQuestionaireDocumentDTO.setSupplierQuestionaireDocumentId(resultSet.getInt("supplier_questionaire_document_id"));
				supplierQuestionaireDocumentDTO.setSupplierQuestionaireDocumentLocation(resultSet.getString("supplier_questionaire_document_location"));
				supplierQuestionaireDocumentDTO.setEnable(NullUtils.cvChar(resultSet.getString("enable")));
				supplierQuestionaireDocumentDTO.setCreateBy(resultSet.getInt("create_by"));
				supplierQuestionaireDocumentDTO.setCreateDate(resultSet.getDate("create_date"));
				supplierQuestionaireDocumentDTO.setCreateTime(resultSet.getTime("create_date"));
				supplierQuestionaireDocumentDTO.setUpdateBy(resultSet.getInt("update_by"));
				supplierQuestionaireDocumentDTO.setUpdateDate(resultSet.getDate("update_date"));
				supplierQuestionaireDocumentDTO.setUpdateTime(resultSet.getTime("update_date"));
				
				QuestionaireDocumentTypeDTO questionaireDocumentTypeDTO = new QuestionaireDocumentTypeDTO();
				questionaireDocumentTypeDTO.setQuestionaireDocumentTypeId(resultSet.getInt("questionaire_document_type_id"));
				questionaireDocumentTypeDTO.setQuestionaireDocumentTypeName(resultSet.getString("questionaire_document_type_name"));
				supplierQuestionaireDocumentDTO.setQuestionaireDocumentTypeId(questionaireDocumentTypeDTO);
				
				SupplierDTO supplierDTO = new SupplierDTO();
				supplierDTO.setSupplierId(resultSet.getInt("supplier_id"));
				supplierDTO.setSupplierCode(resultSet.getString("supplier_code"));
				supplierDTO.setSupplierCompany(resultSet.getString("supplier_company"));
				supplierQuestionaireDocumentDTO.setSupplierId(supplierDTO);
				
				QuestionaireDocumentDTO questionaireDocumentDTO = new QuestionaireDocumentDTO();
				questionaireDocumentDTO.setQuestionaireDocumentId(resultSet.getInt("questionaire_document_id"));
				questionaireDocumentDTO.setQuestionaireDocumentName(resultSet.getString("questionaire_document_name"));
				supplierQuestionaireDocumentDTO.setQuestionaireDocumentId(questionaireDocumentDTO);			
				
				supplierQuestionaireDocumentDTOList.add(supplierQuestionaireDocumentDTO);
			}
			return supplierQuestionaireDocumentDTOList;
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
	
	public List<SupplierQuestionaireDocumentDTO> getQuestionaireDocumentMappingSupplierList(Integer startRecord, Integer numOfRows, String queryWhereClause){
		try {
			
			/*SELECT summary_questionaire_document_supplier.questionaire_document_id, summary_questionaire_document_supplier.questionaire_document_name, summary_questionaire_document_supplier.supplier_id, summary_questionaire_document_supplier.supplier_company, sqd.supplier_questionaire_document_id, sqd.supplier_questionaire_document_location
			FROM 
			(
			SELECT qd.questionaire_document_id, qd.questionaire_document_name, s.supplier_company, s.supplier_id
			FROM questionaire_document qd, supplier s
			) as summary_questionaire_document_supplier
			LEFT JOIN supplier_questionaire_document sqd ON summary_questionaire_document_supplier.supplier_id = sqd.supplier_id 
			AND summary_questionaire_document_supplier.questionaire_document_id = sqd.questionaire_document_id*/
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT summary_questionaire_document_supplier.questionaire_document_id, summary_questionaire_document_supplier.questionaire_document_name, summary_questionaire_document_supplier.supplier_id, summary_questionaire_document_supplier.supplier_company, sqd.supplier_questionaire_document_id, sqd.supplier_questionaire_document_location ");
			query.append("FROM ( ");
			query.append("SELECT qd.questionaire_document_id, qd.questionaire_document_name, s.supplier_company, s.supplier_id ");
			query.append("FROM ").append(DBConst.TABLE_Questionaire_Document).append(" qd, ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append(") as summary_questionaire_document_supplier ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_Questionaire_Document).append(" sqd ");
			query.append("ON summary_questionaire_document_supplier.supplier_id = sqd.supplier_id ");
			query.append("AND summary_questionaire_document_supplier.questionaire_document_id = sqd.questionaire_document_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause.trim())) {
				query.append(queryWhereClause);
			}
			
			query.append(" order by summary_questionaire_document_supplier.supplier_id asc, summary_questionaire_document_supplier.questionaire_document_id asc ");
			
			if(numOfRows != -1) {
				query.append(String.format("limit %s,%s", startRecord,numOfRows));
			}
			
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<SupplierQuestionaireDocumentDTO> supplierQuestionaireDocumentDTOList = new ArrayList<SupplierQuestionaireDocumentDTO>();
			while (resultSet.next()) {
				
				SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTO = new SupplierQuestionaireDocumentDTO();
				
				supplierQuestionaireDocumentDTO.setQuestionaireDocumentId(new QuestionaireDocumentDTO());
				supplierQuestionaireDocumentDTO.getQuestionaireDocumentId().setQuestionaireDocumentId(resultSet.getInt("questionaire_document_id"));
				supplierQuestionaireDocumentDTO.getQuestionaireDocumentId().setQuestionaireDocumentName(resultSet.getString("questionaire_document_name"));
				
				supplierQuestionaireDocumentDTO.setSupplierId(new SupplierDTO());
				supplierQuestionaireDocumentDTO.getSupplierId().setSupplierId(resultSet.getInt("supplier_id"));
				supplierQuestionaireDocumentDTO.getSupplierId().setSupplierCompany(resultSet.getString("supplier_company"));
				
				supplierQuestionaireDocumentDTO.setSupplierQuestionaireDocumentId(resultSet.getInt("supplier_questionaire_document_id"));
				supplierQuestionaireDocumentDTO.setSupplierQuestionaireDocumentLocation(resultSet.getString("supplier_questionaire_document_location"));
				
				supplierQuestionaireDocumentDTOList.add(supplierQuestionaireDocumentDTO);				
			}
			return supplierQuestionaireDocumentDTOList;
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
	
	public boolean insertSupplierQuestionaireDocument(SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTO) {
		
		try {
			
			//INSERT INTO `audit_supplier_cpram`.`supplier_questionaire_document` (`supplier_questionaire_document_location`, `questionaire_document_type_id`, `supplier_id`, `questionaire_document_id`, `enable`, `create_by`, `create_date`, `update_by`, `update_date`) VALUES ('http://app.cpram.co.th/audit/supplier/standard_document/24_กรรมวิธีผลิตเส้นหญ่_1568878633657.doc', '1', '2', '1', 'Y', '1', 'now()', '1', 'now()');
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Supplier_Questionaire_Document).append(" ");
			query.append("(supplier_questionaire_document_location, questionaire_document_type_id, supplier_id, questionaire_document_id, enable, create_by, create_date, update_by, update_date) ");
			query.append("VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, now(), ?, now());");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, supplierQuestionaireDocumentDTO.getSupplierQuestionaireDocumentLocation());
			preparedStatement.setInt(index++, supplierQuestionaireDocumentDTO.getQuestionaireDocumentTypeId().getQuestionaireDocumentTypeId());
			preparedStatement.setInt(index++, supplierQuestionaireDocumentDTO.getSupplierId().getSupplierId());
			preparedStatement.setInt(index++, supplierQuestionaireDocumentDTO.getQuestionaireDocumentId().getQuestionaireDocumentId());
			preparedStatement.setString(index++, NullUtils.cvStr(supplierQuestionaireDocumentDTO.getEnable()));
			preparedStatement.setInt(index++, supplierQuestionaireDocumentDTO.getCreateBy());
			preparedStatement.setInt(index++, supplierQuestionaireDocumentDTO.getUpdateBy());
			
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
	
	public boolean deleteSupplierQuestionaireDocument(SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTO) {
		try {
			
			//DELETE FROM `audit_supplier_cpram`.`supplier_questionaire_document` WHERE (`supplier_questionaire_document_id` = '2');
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("DELETE FROM ").append(DBConst.TABLE_Supplier_Questionaire_Document).append(" ");
			query.append("WHERE ( supplier_questionaire_document_id = ? ); ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, supplierQuestionaireDocumentDTO.getSupplierQuestionaireDocumentId());
			
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
	
	public int countSupplierQuestionaireDocumentList(String queryWhereClause) {
		int totalSupplierQuestionaireDocument = 0;
		try {			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Supplier_Questionaire_Document).append(" sqd ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Questionaire_Document_Type).append(" qdt ");
			query.append("ON sqd.questionaire_document_type_id = qdt.questionaire_document_type_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON sqd.supplier_id = s.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Questionaire_Document).append(" qd ");
			query.append("ON sqd.questionaire_document_id = qd.questionaire_document_id ");
			query.append("WHERE 1=1 ");
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalSupplierQuestionaireDocument = resultSet.getInt("total");
			}
			return totalSupplierQuestionaireDocument;

		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalSupplierQuestionaireDocument;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public int countQuestionaireDocumentMappingSupplierList(String queryWhereClause) {
		int totalSupplierQuestionaireDocument = 0;
		try {
			
			connection.setAutoCommit(false);
			
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ( ");
			query.append("SELECT qd.questionaire_document_id, qd.questionaire_document_name, s.supplier_company, s.supplier_id ");
			query.append("FROM ").append(DBConst.TABLE_Questionaire_Document).append(" qd, ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append(") as summary_questionaire_document_supplier ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier_Questionaire_Document).append(" sqd ");
			query.append("ON summary_questionaire_document_supplier.supplier_id = sqd.supplier_id ");
			query.append("AND summary_questionaire_document_supplier.questionaire_document_id = sqd.questionaire_document_id ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				totalSupplierQuestionaireDocument = resultSet.getInt("total");
			}
			return totalSupplierQuestionaireDocument;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return totalSupplierQuestionaireDocument;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}

}
