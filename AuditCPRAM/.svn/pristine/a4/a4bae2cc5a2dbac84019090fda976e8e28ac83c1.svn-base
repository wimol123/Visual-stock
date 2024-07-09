package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.conts.ConstInformation;
import th.co.gosoft.audit.cpram.dto.InformationDTO;
import th.co.gosoft.audit.cpram.dto.InformationDetailDTO;
import th.co.gosoft.audit.cpram.dto.InformationDocumentDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class InformationDAO extends StandardAttributeDAO{
	
	private final static Logger logger = Logger.getLogger(InformationDAO.class);

	public InformationDAO(Connection connection) {
		super(connection);
	}

	public int insertInformation(InformationDTO informationDTO) {
		int last_inserted_id = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Information).append(" ");
			query.append("(information_title, description, group_type, supplier_id, product_type_id, create_by, create_date) ");
			query.append(" VALUES ");
			query.append("(?, ?, ?, ?, ?, ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString(),Statement.RETURN_GENERATED_KEYS);
			int index = 1;
			preparedStatement.setString(index++, informationDTO.getInformationTitle());
			preparedStatement.setString(index++, informationDTO.getDescription());
			preparedStatement.setString(index++, informationDTO.getGroupType());
			preparedStatement.setString(index++, (informationDTO.getSupplierList()=="")?null:informationDTO.getSupplierList());
			preparedStatement.setInt(index++, informationDTO.getProductTypeId().getProductTypeId());
			preparedStatement.setInt(index++, informationDTO.getCreateBy());
			preparedStatement.executeUpdate();
			ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next())
            {
                last_inserted_id = rs.getInt(1);
            }
			return last_inserted_id;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return last_inserted_id;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateInformation(InformationDTO informationDTO) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Information).append(" ");
			query.append("SET information_title = ?, ");
			query.append("description = ?, ");
			query.append("group_type = ?, ");
			query.append("supplier_id = ?, ");
			query.append("product_type_id = ?, ");
			query.append("update_by = ?, update_date = now(), ");
			if(informationDTO.getInformationType().equals(NullUtils.cvChar(ConstInformation.INFORMATION_SEND))) {
				query.append("send_date = now(), ");
			}
			query.append("information_type = ? ");
			query.append("WHERE information_id = ?; ");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, NullUtils.cvStr(informationDTO.getInformationTitle()));
			preparedStatement.setString(index++, NullUtils.cvStr(informationDTO.getDescription()));
			preparedStatement.setString(index++, NullUtils.cvStr(informationDTO.getGroupType()));
			preparedStatement.setString(index++, (informationDTO.getSupplierList()=="")?null:informationDTO.getSupplierList());
			preparedStatement.setString(index++, NullUtils.cvStr(informationDTO.getProductTypeId().getProductTypeId()));
			preparedStatement.setString(index++, NullUtils.cvStr(informationDTO.getUpdateBy()));
			preparedStatement.setString(index++, NullUtils.cvStr(informationDTO.getInformationType()));
			preparedStatement.setInt(index++, informationDTO.getInformationId());
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
	
	public boolean updateInformationSuccess(InformationDTO informationDTO) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Information).append(" ");
			query.append("SET update_date = now(), ");
			if(informationDTO.getInformationType().equals(NullUtils.cvChar(ConstInformation.INFORMATION_SUCCESS))) {
				query.append("accept_date = now(), ");
			}
			query.append("information_type = ? ");
			query.append("WHERE information_id = ?; ");
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, NullUtils.cvStr(informationDTO.getInformationType()));
			preparedStatement.setInt(index++, informationDTO.getInformationId());
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
	
	public List<InformationDocumentDTO> getInformationdDocumentList(InformationDocumentDTO objInformationDocumentDTO){
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(" SELECT id.information_document_id, id.information_id, information_document_location,id.information_document_type ");
			query.append(" FROM ").append(DBConst.TABLE_Information_Document).append(" id ");
			query.append("WHERE information_id = ?; ");
			
			query.append(String.format(" order by id.information_document_id asc ;"));
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1;
			preparedStatement.setInt(index++, NullUtils.cvInt(objInformationDocumentDTO.getInformationId().getInformationId()));
			resultSet = preparedStatement.executeQuery();
			List<InformationDocumentDTO> informationDocumentDTOs = new ArrayList<>();
			while (resultSet.next()) {
				InformationDocumentDTO informationDocumentDTO = new InformationDocumentDTO();
				informationDocumentDTO.setInformationDocumentId(resultSet.getInt("information_document_id"));
				informationDocumentDTO.setInformationId(new InformationDTO());
				informationDocumentDTO.getInformationId().setInformationId(resultSet.getInt("information_id"));
				informationDocumentDTO.setCreateBy(resultSet.getInt("create_by"));
				informationDocumentDTO.setCreateDate(resultSet.getDate("create_date"));
				informationDocumentDTO.setCreateTime(resultSet.getTime("create_date"));
				
				informationDocumentDTOs.add(informationDocumentDTO);
			}
			return informationDocumentDTOs;
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
	

	public List<InformationDTO> getInformationList(Integer startRecord, Integer numOfRows, String queryWhereClause){		
		try {
			
			/*SELECT c.checklist_id, c.checklist_title, c.checklist_type_id, c.product_type_id, pt.name AS name_product_type, c.checklist_scope, c.scoring_criteria, c.approve_supplier_rule, c.description, c.effective_date, c.expire_date, c.enable, c.create_by, c.update_by, c.update_date
			FROM checklist c LEFT JOIN product_type pt ON c.product_type_id = pt.product_type_id
			WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT i.information_id, i.information_title, i.description, i.group_type, i.supplier_id, i.product_type_id, ");
			query.append(" (CASE ");
			query.append("    WHEN i.group_type = '2' THEN 'Supplier' ");
			query.append("     ELSE pt.name ");
			query.append(" END) name_product_type, ");
			query.append("i.information_type, i.create_by, i.create_date, i.update_by, i.update_date, i.send_date, i.accept_date ");
			query.append("FROM ").append(DBConst.TABLE_Information).append(" i ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("ON i.product_type_id = pt.product_type_id ");
			query.append("WHERE 1=1 AND ( i.accept_date > DATE_ADD(now(), INTERVAL -2 MONTH) OR i.accept_date is null ) ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by i.information_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<InformationDTO> informationDTOs = new ArrayList<>();
			while(resultSet.next()) {
				InformationDTO informationList = new InformationDTO();
				informationList.setInformationId(resultSet.getInt("information_id"));
				informationList.setInformationTitle(resultSet.getString("information_title"));
				informationList.setDescription(resultSet.getString("description"));
				informationList.setGroupType(resultSet.getString("group_type"));
				informationList.setSupplierList(resultSet.getString("supplier_id"));
				
				ProductTypeDTO productTypeId = new ProductTypeDTO();
				productTypeId.setProductTypeId(resultSet.getInt("product_type_id"));
				productTypeId.setName(resultSet.getString("name_product_type"));
				informationList.setProductTypeId(productTypeId);
				
				informationList.setInformationType(NullUtils.cvChar(resultSet.getString("information_type")));
				informationList.setCreateBy(resultSet.getInt("create_by"));
				informationList.setCreateDate(resultSet.getDate("create_date"));
				informationList.setCreateTime(resultSet.getTime("create_date"));
				informationList.setUpdateBy(resultSet.getInt("update_by"));
				informationList.setUpdateDate(resultSet.getDate("update_date"));
				informationList.setUpdateTime(resultSet.getTime("update_date"));
				informationList.setSendDate(resultSet.getDate("send_date"));
				informationList.setSendTime(resultSet.getTime("send_date"));
				informationList.setAcceptDate(resultSet.getDate("accept_date"));
				informationList.setAcceptTime(resultSet.getTime("accept_date"));
				
				informationDTOs.add(informationList);
			}
			return informationDTOs;
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
	
	public int countInformationList(String queryWhereClause) {
		int countInformationlist = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Information).append(" i ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Product_Type).append(" pt ");
			query.append("ON i.product_type_id = pt.product_type_id ");
			query.append("WHERE 1=1 AND ( i.accept_date > DATE_ADD(now(), INTERVAL -2 MONTH) OR i.accept_date is null ) ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countInformationlist = resultSet.getInt("total");
			}
			return countInformationlist;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countInformationlist;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
	
	public List<InformationDetailDTO> getInformationListBySupplier(Integer startRecord, Integer numOfRows, String queryWhereClause){		
		try {
			
			/*SELECT c.checklist_id, c.checklist_title, c.checklist_type_id, c.product_type_id, pt.name AS name_product_type, c.checklist_scope, c.scoring_criteria, c.approve_supplier_rule, c.description, c.effective_date, c.expire_date, c.enable, c.create_by, c.update_by, c.update_date
			FROM checklist c LEFT JOIN product_type pt ON c.product_type_id = pt.product_type_id
			WHERE 1=1*/
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT i.information_id, i.information_title, i.description, i.group_type, i.supplier_id, i.product_type_id, ");
			query.append("i.information_type, i.create_by, i.create_date, i.update_by, i.update_date, i.send_date, i.accept_date, id.accept_date as accept_date_supplier, id.accept_status, id.accept_by, u.username ");
			query.append("FROM ").append(DBConst.TABLE_Information_Detail).append(" id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Information).append(" i ");
			query.append("ON id.information_id = i.information_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON u.user_id = id.accept_by ");
			query.append("WHERE 1=1 AND ( id.accept_date > DATE_ADD(now(), INTERVAL -2 MONTH) OR id.accept_date is null ) ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by i.information_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<InformationDetailDTO> informationDetailDTOs = new ArrayList<>();
			while(resultSet.next()) {
				InformationDTO informationList = new InformationDTO();
				informationList.setInformationId(resultSet.getInt("information_id"));
				informationList.setInformationTitle(resultSet.getString("information_title"));
				informationList.setDescription(resultSet.getString("description"));
				informationList.setGroupType(resultSet.getString("group_type"));
				informationList.setSupplierList(resultSet.getString("supplier_id"));
				
				informationList.setInformationType(NullUtils.cvChar(resultSet.getString("information_type")));
				informationList.setCreateBy(resultSet.getInt("create_by"));
				informationList.setCreateDate(resultSet.getDate("create_date"));
				informationList.setCreateTime(resultSet.getTime("create_date"));
				informationList.setUpdateBy(resultSet.getInt("update_by"));
				informationList.setUpdateDate(resultSet.getDate("update_date"));
				informationList.setUpdateTime(resultSet.getTime("update_date"));
				informationList.setSendDate(resultSet.getDate("send_date"));
				informationList.setSendTime(resultSet.getTime("send_date"));
				informationList.setAcceptDate(resultSet.getDate("accept_date"));
				informationList.setAcceptTime(resultSet.getTime("accept_date"));

				UserDTO userId = new UserDTO();
				userId.setUserId(resultSet.getInt("accept_by"));
				userId.setUsername(resultSet.getString("username"));
				
				InformationDetailDTO informationDetailDTO = new InformationDetailDTO();
				informationDetailDTO.setInformationId(informationList);
				informationDetailDTO.setAcceptBy(userId);
				informationDetailDTO.setAcceptDate(resultSet.getDate("accept_date_supplier"));
				informationDetailDTO.setAcceptTime(resultSet.getTime("accept_date_supplier"));
				informationDetailDTO.setAcceptStatus(NullUtils.cvChar(resultSet.getString("accept_status")));
				informationDetailDTOs.add(informationDetailDTO);
			}
			return informationDetailDTOs;
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
	
	public int countInformationListBySupplier(String queryWhereClause) {
		int countInformationlist = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Information_Detail).append(" id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Information).append(" i ");
			query.append("ON id.information_id = i.information_id ");
			query.append("WHERE 1=1 AND ( id.accept_date > DATE_ADD(now(), INTERVAL -2 MONTH) OR id.accept_date is null ) ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			query.append(";");
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				countInformationlist = resultSet.getInt("total");
			}
			return countInformationlist;
		}catch(SQLException e){
			logger.error(e.getMessage(), e);
			return countInformationlist;
		}catch(Exception e) {
 			logger.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally {
			this.closeResourceDB();
		}
	}
}
