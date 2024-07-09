package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.InformationDetailDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.utils.DBConst;
import th.co.gosoft.audit.cpram.utils.NullUtils;

public class InformationDetailDAO extends StandardAttributeDAO{
	
	private final static Logger logger = Logger.getLogger(InformationDetailDAO.class);

	public InformationDetailDAO(Connection connection) {
		super(connection);
	}

	public boolean insertInformationDetail(InformationDetailDTO informationDetailDTO) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO ").append(DBConst.TABLE_Information_Detail).append(" ");
			query.append("(information_id, supplier_id, create_by, create_date) ");
			query.append(" VALUES ");
			query.append("(?, ?, ?, now());");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1,rowAffective = 0;
			preparedStatement.setInt(index++, informationDetailDTO.getInformationId().getInformationId());
			preparedStatement.setInt(index++, informationDetailDTO.getSupplierId().getSupplierId());
			preparedStatement.setInt(index++, informationDetailDTO.getCreateBy());
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

	public List<InformationDetailDTO> getInformationDetailList(Integer startRecord, Integer numOfRows, String queryWhereClause){		
		try {
			
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT id.information_detail_id, id.information_id, id.supplier_id, s.supplier_company, id.create_by, ");
			query.append(" id.create_date, id.accept_by, u.username, id.accept_date, id.accept_status ");
			query.append("FROM ").append(DBConst.TABLE_Information_Detail).append(" id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_Supplier).append(" s ");
			query.append("ON id.supplier_id = s.supplier_id ");
			query.append("LEFT JOIN ").append(DBConst.TABLE_User).append(" u ");
			query.append("ON u.user_id = id.accept_by ");
			query.append("WHERE 1=1 ");
			
			if(!StringUtils.isNullOrEmpty(queryWhereClause)) {
				query.append(queryWhereClause);
			}
			
			if(numOfRows != -1) {
				query.append(String.format(" order by id.information_detail_id asc limit %s,%s", startRecord,numOfRows));
			}
			query.append(";");
			
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			List<InformationDetailDTO> InformationDetailDTOs = new ArrayList<>();
			while(resultSet.next()) {
				InformationDetailDTO informationDetail = new InformationDetailDTO();
				informationDetail.setInformationDetailId(resultSet.getInt("information_detail_id"));
				
				SupplierDTO supplierId = new SupplierDTO();
				supplierId.setSupplierId(resultSet.getInt("supplier_id"));
				supplierId.setSupplierCompany(resultSet.getString("supplier_company"));
				informationDetail.setSupplierId(supplierId);
				
				informationDetail.setCreateBy(resultSet.getInt("create_by"));
				informationDetail.setCreateDate(resultSet.getDate("create_date"));
				informationDetail.setCreateTime(resultSet.getTime("create_date"));
				informationDetail.setAcceptDate(resultSet.getDate("accept_date"));
				informationDetail.setAcceptTime(resultSet.getTime("accept_date"));
				UserDTO userId = new UserDTO();
				userId.setUserId(resultSet.getInt("accept_by"));
				userId.setUsername(resultSet.getString("username"));
				informationDetail.setAcceptBy(userId);
				informationDetail.setAcceptStatus(NullUtils.cvChar(resultSet.getString("accept_status")));
				
				InformationDetailDTOs.add(informationDetail);
			}
			return InformationDetailDTOs;
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
	
	public int countInformationDetailList(String queryWhereClause) {
		int countInformationlist = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("SELECT ");
			query.append("	COUNT(*) AS total ");
			query.append("FROM ").append(DBConst.TABLE_Information_Detail).append(" id ");
			query.append("WHERE 1=1 ");
			
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
	
	public boolean acceptInformationDetailBySupplier(InformationDetailDTO informationDetailDTO) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE ").append(DBConst.TABLE_Information_Detail).append(" ");
			query.append(" SET ACCEPT_DATE = now(), ACCEPT_BY = ?, ACCEPT_STATUS = 'Y' ");
			query.append(" WHERE INFORMATION_ID = ? AND SUPPLIER_ID = ?");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1,rowAffective = 0;
			preparedStatement.setInt(index++, informationDetailDTO.getCreateBy());
			preparedStatement.setInt(index++, informationDetailDTO.getInformationId().getInformationId());
			preparedStatement.setInt(index++, informationDetailDTO.getSupplierId().getSupplierId());
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
}
