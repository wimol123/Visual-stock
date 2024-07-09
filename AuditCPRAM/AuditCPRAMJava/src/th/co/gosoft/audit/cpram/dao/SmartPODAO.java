package th.co.gosoft.audit.cpram.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.internal.StringUtil;

import com.mysql.jdbc.Statement;

import th.co.gosoft.audit.cpram.dto.PoAcceptedDTO;
import th.co.gosoft.audit.cpram.dto.PoDTO;
import th.co.gosoft.audit.cpram.dto.PoDetailDTO;
import th.co.gosoft.audit.cpram.dto.PoHistoryDTO;
import th.co.gosoft.audit.cpram.dto.PoLogInterfaceDTO;
import th.co.gosoft.audit.cpram.dto.PoStatusDTO;
import th.co.gosoft.audit.cpram.model.PoModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class SmartPODAO extends StandardAttributeDAO {
	
	private final static Logger logger = Logger.getLogger(SmartPODAO.class);
	
	private String generateFilterPO(PoDTO poFilter) {
		StringBuilder query = new StringBuilder();
		query.append("");
		if (StringUtils.isNotBlank(poFilter.getSupplierUserId())) {
			query.append(""
					+ "	and po.supplier_code in ("
					+ "		select supplier_code "
					+ "		from user "
					+ "		join supplier_user_mapping mp on mp.user_id = user.user_id "
					+ "		join supplier supp on supp.supplier_id = mp.supplier_id "
					+ "		where user.user_id = " + poFilter.getSupplierUserId()
					+ "	)");
		} else if (StringUtils.isNotBlank(poFilter.getSupplier())) {
			query.append(""
					+ "	and replace(concat_ws(' ', po.supplier_code, sup.supplier_company), ' ', '') like "
					+ "		'%" + poFilter.getSupplier().replaceAll(" ", "") + "%' ");
		}
		
		if (StringUtils.isNotBlank(poFilter.getPoNo())) {
			query.append(""
					+ "	and po.po_no like "
					+ "		'%" + poFilter.getPoNo().replaceAll(" ", "") + "%' ");
		}
		
		if (poFilter.getPoStatusId() > 0) {
			query.append(""
					+ "	and po.po_status_id = " + poFilter.getPoStatusId());
		}
		
		if (StringUtils.isNotBlank(poFilter.getPoCreateBy())) {
			query.append(""
					+ "	and replace(concat_ws('', user.employee_id, user.fullname, po.po_create_by_email), ' ', '') like "
					+ "		'%" + poFilter.getPoCreateBy().replaceAll(" ", "") + "%' ");
		}
		
		if (StringUtils.isNotBlank(poFilter.getCreateDateStart())) {
			query.append(""
					+ " and po.create_date >= "
					+ "		str_to_date('" + poFilter.getCreateDateStart() + "', '%d/%m/%Y %H:%i') ");
		}
		
		if (StringUtils.isNotBlank(poFilter.getCreateDateEnd())) {
			query.append(""
					+ " and po.create_date <= "
					+ "		str_to_date('" + poFilter.getCreateDateEnd() + "', '%d/%m/%Y %H:%i') ");
		}
		
		if (poFilter.getNotPoStatusId() > 0) {
			query.append(""
					+ "	and po.po_status_id <> " + poFilter.getNotPoStatusId());
		}
		
		return query.toString();
	}

	public SmartPODAO(Connection connection) {
		super(connection);
	}
	
	public List<PoStatusDTO> getPoStatusList(String userGroupId) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
					+ " select "
					+ "		po_status_id, "
					+ "		po_status_name, "
					+ "		case "
					+ "			when find_in_set('" + userGroupId + "', status_owner_group) > 0 "
					+ "				then 'selected' "
					+ "			else '' "
					+ "		end as selected "
					+ "	from po_status "
					+ "	where enable = 'Y' ");
			
			if (StringUtils.equals(userGroupId, "3") || StringUtils.equals(userGroupId, "8")) {
				query.append(" and po_status_id <> 4 ");
			}
			
			logger.info("SmartPODAO.getPoStatusList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoStatusDTO> poStatusList = new ArrayList<>();
			while(resultSet.next()) {
				PoStatusDTO poStatus = new PoStatusDTO();
				poStatus.setPoStatusId(resultSet.getInt("po_status_id"));
				poStatus.setPoStatusName(resultSet.getString("po_status_name"));
				poStatus.setSelected(resultSet.getString("selected"));
				poStatusList.add(poStatus);
			}
			
			return poStatusList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoStatusList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoStatusList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public List<PoAcceptedDTO> getPoAcceptedList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("select * from po_accepted where enable = 'Y'");
			
			logger.info("SmartPODAO.getPoAcceptedList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoAcceptedDTO> poAcceptedList = new ArrayList<>();
			while(resultSet.next()) {
				PoAcceptedDTO poAccepted = new PoAcceptedDTO();
				poAccepted.setPoAcceptedId(resultSet.getInt("po_accepted_id"));
				poAccepted.setPoAcceptedName(resultSet.getString("po_accepted_name"));
				poAccepted.setPoAcceptedValue(resultSet.getString("po_accepted_value"));
				poAcceptedList.add(poAccepted);
			}
			
			return poAcceptedList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoAcceptedList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoAcceptedList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public PoAcceptedDTO getPoAccepted(int poAcceptedId) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("select * from po_accepted where po_accepted_id = " + poAcceptedId);
			
			logger.info("SmartPODAO.getPoAccepted SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			PoAcceptedDTO poAccepted = new PoAcceptedDTO();
			while(resultSet.next()) {
				poAccepted.setPoAcceptedId(resultSet.getInt("po_accepted_id"));
				poAccepted.setPoAcceptedName(resultSet.getString("po_accepted_name"));
				poAccepted.setPoAcceptedValue(resultSet.getString("po_accepted_value"));
				break;
			}
			
			return poAccepted;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoAccepted SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoAccepted Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public List<PoDTO> getPoList(PoDTO poFilter) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
					+ "	select po.po_id, po.po_no, po.supplier_code, "
					+ "		concat_ws(' ', po.supplier_code, sup.supplier_company) as supplier , "
					+ "    	date_format(po.create_date, '%d/%m/%Y %H:%i') as create_date, "
					+ "    	po.po_status_id, sta.po_status_name as po_status_name, "
					+ "    	po.is_mail_sent, po.is_viewed, po.is_printed, "
					+ "    	po.po_accepted_id, "
					+ "		po.po_create_by_email, "
					+ "		(	select count(1) "
					+ "			from po_history "
					+ "			where po_id = po.po_id and po_step_id = 2 "
					+ "		) as count_supplier_accepted "
					+ "	from po "
					+ "	inner join po_status sta on sta.po_status_id = po.po_status_id "
					+ "	left join supplier sup on sup.supplier_code = po.supplier_code "
					+ "	left join user on user.email = po.po_create_by_email and user.user_group_id = 5 "
					+ " where 1 = 1 " + generateFilterPO(poFilter));
			
			query.append(""
					+ " order by supplier asc, po.po_accepted_id desc , po.create_date asc, po.po_no asc ");
			
			logger.info("SmartPODAO.getPoList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoDTO> poList = new ArrayList<>();
			while(resultSet.next()) {
				PoDTO po = new PoDTO();
				po.setPoId(resultSet.getInt("po_id"));
				po.setPoNo(resultSet.getString("po_no"));
				po.setSupplierCode(resultSet.getString("supplier_code"));
				po.setSupplier(resultSet.getString("supplier"));
				po.setCreateDate(resultSet.getString("create_date"));
				po.setPoStatusId(resultSet.getInt("po_status_id"));
				po.setPoStatusName(resultSet.getString("po_status_name"));
				po.setIsMailSent(resultSet.getString("is_mail_sent"));
				po.setIsViewed(resultSet.getString("is_viewed"));
				po.setIsPrinted(resultSet.getString("is_printed"));
				po.setPoAcceptedId(resultSet.getInt("po_accepted_id"));
				po.setPoCreateByEmail(resultSet.getString("po_create_by_email"));
				po.setCountSupplierAccepted(resultSet.getInt("count_supplier_accepted"));
				poList.add(po);
			}
			
			return poList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public List<PoModel> getPoReport(PoDTO poFilter) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
					+ "	select po.po_no, po.supplier_code, "
					+ "		sup.supplier_company as supplier_name, "
					+ "		date_format(po.po_create_date, '%d/%m/%Y') as po_create_date, "
					+ "		po.is_mail_sent, po.is_viewed, po.is_printed, "
					+ "    	date_format(po.create_date, '%d/%m/%Y %H:%i') as create_date, "
					+ "    	sta.po_status_name as po_status_name, "
					+ "		pac.po_accepted_name as po_accepted_name, "
					+ "		concat(ifnull(user.fullname, ''), ' (', po.po_create_by_email, ')') as po_create_by, "
					+ "		pd.item, pd.material_code, pd.material_name, "
					+ "		date_format(pd.delivery_date, '%d/%m/%Y') as delivery_date, "
					+ "		pd.net_price, pd.quantity, pd.unit, pd.net_value, "
					+ "		date_format(pd.gr_date, '%d/%m/%Y') as gr_date, "
					+ "		date_format(pd.pr_create_date, '%d/%m/%Y') as pr_create_date, "
					+ "		pd.planned_deliv_time, pd.purchase_req, "
					+ "		pd.supp_accepted_1, pd.supp_note_1, pd.pur_response_1, "
					+ "		pd.supp_accepted_2, pd.supp_note_2, pd.pur_response_2, "
					+ "		pd.supp_accepted_3, pd.supp_note_3 "
					+ "	from po "
					+ "	inner join po_status sta on sta.po_status_id = po.po_status_id "
					+ "	left join po_accepted pac on pac.po_accepted_id = po.po_accepted_id "
					+ "	left join supplier sup on sup.supplier_code = po.supplier_code "
					+ "	left join user on user.email = po.po_create_by_email and user.user_group_id = 5 "
					+ "	left join po_detail pd on pd.po_id = po.po_id "
					+ " where 1 = 1 " + generateFilterPO(poFilter));
			
			query.append(""
					+ "	order by po.po_no asc, pd.item asc ");
			
			logger.info("SmartPODAO.getPoReport SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoModel> poList = new ArrayList<>();
			while(resultSet.next()) {
				PoModel po = new PoModel();
				po.setPoNo(resultSet.getString("po_no"));
				po.setSupplierCode(resultSet.getString("supplier_code"));
				po.setSupplierName(resultSet.getString("supplier_name"));
				po.setPoCreateDate(resultSet.getString("po_create_date"));
				po.setIsMailSent(resultSet.getString("is_mail_sent"));
				po.setIsViewed(resultSet.getString("is_viewed"));
				po.setIsPrinted(resultSet.getString("is_printed"));
				po.setCreateDate(resultSet.getString("create_date"));
				po.setPoStatusName(resultSet.getString("po_status_name"));
				po.setPoAcceptedName(resultSet.getString("po_accepted_name"));
				po.setPoCreateBy(resultSet.getString("po_create_by"));
				po.setItem(resultSet.getString("item"));
				po.setMaterialCode(resultSet.getString("material_code"));
				po.setMaterialName(resultSet.getString("material_name"));
				po.setDeliveryDate(resultSet.getString("delivery_date"));
				po.setNetPrice(resultSet.getString("net_price"));
				po.setQuantity(resultSet.getString("quantity"));
				po.setUnit(resultSet.getString("unit"));
				po.setNetValue(resultSet.getString("net_value"));
				po.setGrDate(resultSet.getString("gr_date"));
				po.setPrCreateDate(resultSet.getString("pr_create_date"));
				po.setPlannedDelivTime(resultSet.getString("planned_deliv_time"));
				po.setPurchaseReq(resultSet.getString("purchase_req"));
				po.setSuppAccepted1(resultSet.getString("supp_accepted_1"));
				po.setSuppNote1(resultSet.getString("supp_note_1"));
				po.setPurResponse1(resultSet.getString("pur_response_1"));
				po.setSuppAccepted2(resultSet.getString("supp_accepted_2"));
				po.setSuppNote2(resultSet.getString("supp_note_2"));
				po.setPurResponse2(resultSet.getString("pur_response_2"));
				po.setSuppAccepted3(resultSet.getString("supp_accepted_3"));
				po.setSuppNote3(resultSet.getString("supp_note_3"));
				poList.add(po);
			}
			
			return poList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoReport SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoReport Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public PoDTO getPo(int poId) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
					+ "	select po.po_id, po.po_no, po.supplier_code, "
					+ "		concat_ws(' ', po.supplier_code, sup.supplier_company) as supplier, "
					+ "    	date_format(po.create_date, '%d/%m/%Y %H:%i') as create_date, "
					+ "    	po.po_status_id, sta.po_status_name as po_status_name, "
					+ "		date_format(po.po_create_date, '%d/%m/%Y') as po_create_date, "
					+ "		po.po_create_by_email, "
					+ "		concat(ifnull(us.fullname, ''), ' (', po.po_create_by_email, ')') as po_create_by, "
					+ " 	po.is_mail_sent "
					+ "	from po "
					+ "	inner join po_status sta on sta.po_status_id = po.po_status_id "
					+ "	left join supplier sup on sup.supplier_code = po.supplier_code "
					+ "	left join user us on us.email = po.po_create_by_email and us.user_group_id = 5 "
					+ "	where po.po_id = " + poId);
			
			logger.info("SmartPODAO.getPo SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			PoDTO po = new PoDTO();
			while(resultSet.next()) {
				po.setPoId(resultSet.getInt("po_id"));
				po.setPoNo(resultSet.getString("po_no"));
				po.setSupplierCode(resultSet.getString("supplier_code"));
				po.setSupplier(resultSet.getString("supplier"));
				po.setCreateDate(resultSet.getString("create_date"));
				po.setPoStatusId(resultSet.getInt("po_status_id"));
				po.setPoStatusName(resultSet.getString("po_status_name"));
				po.setPoCreateDate(resultSet.getString("po_create_date"));
				po.setPoCreateByEmail(resultSet.getString("po_create_by_email"));
				po.setPoCreateBy(resultSet.getString("po_create_by"));
				po.setIsMailSent(resultSet.getString("is_mail_sent"));
				break;
			}
			
			return po;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPo SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPo Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	////////////////////////////////////////////////////////
	////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////
	public List<PoDTO> getPoList(List<Integer>  poIds) {
		try {
			StringBuilder poIdsString = new StringBuilder();
			  poIdsString.append("(");
			  for (int i = 0; i < poIds.size(); i++) {
			    poIdsString.append(poIds.get(i));
			    if (i < poIds.size() - 1) {
			      poIdsString.append(",");
			    }
			  }
			  poIdsString.append(")");
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
					+ "	select po.po_id, po.po_no, po.supplier_code, "
					+ "		concat_ws(' ', po.supplier_code, sup.supplier_company) as supplier, "
					+ "    	date_format(po.create_date, '%d/%m/%Y %H:%i') as create_date, "
					+ "    	po.po_status_id, sta.po_status_name as po_status_name, "
					+ "		date_format(po.po_create_date, '%d/%m/%Y') as po_create_date, "
					+ "		po.po_create_by_email, "
					+ "		concat(ifnull(us.fullname, ''), ' (', po.po_create_by_email, ')') as po_create_by, "
					+ " 	po.is_mail_sent "
					+ "	from po "
					+ "	inner join po_status sta on sta.po_status_id = po.po_status_id "
					+ "	left join supplier sup on sup.supplier_code = po.supplier_code "
					+ "	left join user us on us.email = po.po_create_by_email and us.user_group_id = 5 "
					+ "	where po.po_id IN " + poIdsString.toString());
			
			logger.info("SmartPODAO.getPo SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoDTO> poList = new ArrayList<>();
			while(resultSet.next()) {
				PoDTO po = new PoDTO();
				po.setPoId(resultSet.getInt("po_id"));
				po.setPoNo(resultSet.getString("po_no"));
				po.setSupplierCode(resultSet.getString("supplier_code"));
				po.setSupplier(resultSet.getString("supplier"));
				po.setCreateDate(resultSet.getString("create_date"));
				po.setPoStatusId(resultSet.getInt("po_status_id"));
				po.setPoStatusName(resultSet.getString("po_status_name"));
				po.setPoCreateDate(resultSet.getString("po_create_date"));
				po.setPoCreateByEmail(resultSet.getString("po_create_by_email"));
				po.setPoCreateBy(resultSet.getString("po_create_by"));
				po.setIsMailSent(resultSet.getString("is_mail_sent"));
				poList.add(po);
			}
			System.out.println(poList);
			
			return poList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPo SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPo Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	/*public List<PoDetailDTO> getPoDetailList(int poId ) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("select po_detail_id, po_id,item, "
					+ "		material_code, material_name, "
					+ "		date_format(delivery_date, '%d/%m/%Y') as delivery_date, "
					+ "		format(net_price, 2) as net_price, "
					+ "		format(quantity, 3) as quantity, unit, "
					+ "		format(net_value, 2) as net_value, "
					+ "		date_format(pr_create_date, '%d/%m/%Y') as pr_create_date, "
					+ "		planned_deliv_time, purchase_req, "
					+ "		supp_accepted_1, supp_note_1, pur_response_1, "
					+ "		supp_accepted_2, supp_note_2, pur_response_2, "
					+ "		supp_accepted_3, supp_note_3 "
					+ "	from po_detail where po_id = " + poId);
			
			logger.info("SmartPODAO.getPoDetailList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoDetailDTO> poDetailList = new ArrayList<>();
			while(resultSet.next()) {
				PoDetailDTO poDetail = new PoDetailDTO();
				poDetail.setPoDetailId(resultSet.getInt("po_detail_id"));
				poDetail.setPoId(resultSet.getInt("po_id"));
				poDetail.setItem(resultSet.getString("item"));
				poDetail.setMaterialCode(resultSet.getString("material_code"));
				poDetail.setMaterialName(resultSet.getString("material_name"));
				poDetail.setDeliveryDate(resultSet.getString("delivery_date"));
				poDetail.setNetPrice(resultSet.getString("net_price"));
				poDetail.setQuantity(resultSet.getString("quantity"));
				poDetail.setUnit(resultSet.getString("unit"));
				poDetail.setNetValue(resultSet.getString("net_value"));
				poDetail.setPrCreateDate(resultSet.getString("pr_create_date"));
				poDetail.setPlannedDelivTime(resultSet.getString("planned_deliv_time"));
				poDetail.setPurchaseReq(resultSet.getString("purchase_req"));
				poDetail.setSuppAccepted1(resultSet.getString("supp_accepted_1"));
				poDetail.setSuppNote1(resultSet.getString("supp_note_1"));
				poDetail.setPurResponse1(resultSet.getString("pur_response_1"));
				poDetail.setSuppAccepted2(resultSet.getString("supp_accepted_2"));
				poDetail.setSuppNote2(resultSet.getString("supp_note_2"));
				poDetail.setPurResponse2(resultSet.getString("pur_response_2"));
				poDetail.setSuppAccepted3(resultSet.getString("supp_accepted_3"));
				poDetail.setSuppNote3(resultSet.getString("supp_note_3"));
				poDetailList.add(poDetail);
			}
			
			return poDetailList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoDetailList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoDetailList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}*/
	public List<PoDetailDTO> getPoDetailList(int poId ) {
		List<Integer> poIds = new ArrayList<>();
		poIds.add(poId);
		
		return getPoDetailListByPoIdList(poIds);
		

		
	}
	public List<PoDetailDTO> getPoDetailListByPoIdList(List<Integer> poIds ) {
		try {
			 // Convert List<Integer> to String with parentheses
			StringBuilder poIdsString = new StringBuilder();
			  poIdsString.append("(");
			  for (int i = 0; i < poIds.size(); i++) {
			    poIdsString.append(poIds.get(i));
			    if (i < poIds.size() - 1) {
			      poIdsString.append(",");
			    }
			  }
			  poIdsString.append(")");
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			

			
			query.append("select po_detail_id, po_id,item, "
					+ "		material_code, material_name, "
					+ "		date_format(delivery_date, '%d/%m/%Y') as delivery_date, "
					+ "		format(net_price, 2) as net_price, "
					+ "		format(quantity, 3) as quantity, unit, "
					+ "		format(net_value, 2) as net_value, "
					+ "		date_format(pr_create_date, '%d/%m/%Y') as pr_create_date, "
					+ "		planned_deliv_time, purchase_req, "
					+ "		supp_accepted_1, supp_note_1, pur_response_1, "
					+ "		supp_accepted_2, supp_note_2, pur_response_2, "
					+ "		supp_accepted_3, supp_note_3 "
					+ "	from po_detail where po_id IN " + poIdsString.toString()  );
			
			logger.info("SmartPODAO.getPoDetailList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoDetailDTO> poDetailList = new ArrayList<>();
			while(resultSet.next()) {
				PoDetailDTO poDetail = new PoDetailDTO();
				poDetail.setPoDetailId(resultSet.getInt("po_detail_id"));
				poDetail.setPoId(resultSet.getInt("po_id"));
				poDetail.setItem(resultSet.getString("item"));
				poDetail.setMaterialCode(resultSet.getString("material_code"));
				poDetail.setMaterialName(resultSet.getString("material_name"));
				poDetail.setDeliveryDate(resultSet.getString("delivery_date"));
				poDetail.setNetPrice(resultSet.getString("net_price"));
				poDetail.setQuantity(resultSet.getString("quantity"));
				poDetail.setUnit(resultSet.getString("unit"));
				poDetail.setNetValue(resultSet.getString("net_value"));
				poDetail.setPrCreateDate(resultSet.getString("pr_create_date"));
				poDetail.setPlannedDelivTime(resultSet.getString("planned_deliv_time"));
				poDetail.setPurchaseReq(resultSet.getString("purchase_req"));
				poDetail.setSuppAccepted1(resultSet.getString("supp_accepted_1"));
				poDetail.setSuppNote1(resultSet.getString("supp_note_1"));
				poDetail.setPurResponse1(resultSet.getString("pur_response_1"));
				poDetail.setSuppAccepted2(resultSet.getString("supp_accepted_2"));
				poDetail.setSuppNote2(resultSet.getString("supp_note_2"));
				poDetail.setPurResponse2(resultSet.getString("pur_response_2"));
				poDetail.setSuppAccepted3(resultSet.getString("supp_accepted_3"));
				poDetail.setSuppNote3(resultSet.getString("supp_note_3"));
				poDetailList.add(poDetail);
			}
			
			return poDetailList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoDetailList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoDetailList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	public List<PoHistoryDTO> getPoHistoryList(int poId) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("select po_history.description, "
					+ "		date_format(po_history.create_date, '%d/%m/%Y %H:%i') as create_date,"
					+ "		user.fullname as create_by_name, po_history.note "
					+ "	from po_history "
					+ "	left join user on user.user_id = po_history.create_by "
					+ "	where po_history.po_id = " + poId);
			
			logger.info("SmartPODAO.getPoHistoryList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoHistoryDTO> poHistoryList = new ArrayList<>();
			while(resultSet.next()) {
				PoHistoryDTO poHistory = new PoHistoryDTO();
				poHistory.setDescription(resultSet.getString("description"));
				poHistory.setCreateDate(resultSet.getString("create_date"));
				poHistory.setCreateByName(resultSet.getString("create_by_name"));
				poHistory.setNote(resultSet.getString("note"));
				poHistoryList.add(poHistory);
			}
			
			return poHistoryList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoHistoryList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoHistoryList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public HashMap<String, String> getAllPOList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("select po_no from po");
			
			logger.info("SmartPODAO.getAllPOList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			String poNo;
			HashMap<String, String> allPOList = new HashMap<String, String>();
			while(resultSet.next()) {
				poNo = resultSet.getString("po_no");
				allPOList.put(poNo, poNo);
			}
			
			return allPOList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getAllPOList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getAllPOList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public int insertPO(PoDTO po) {
		int poId = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO po ( "
					+ "		po_no, supplier_code, supplier_name, "
					+ "		po_create_date, po_create_by_email, po_status_id, "
					+ "		create_by, create_date, update_by, update_date "
					+ "	) VALUES ( "
					+ "		?, ?, ?, ");
			
			if (StringUtil.isBlank(po.getPoCreateDate())) {
				query.append("null, ");
			} else {
				query.append("STR_TO_DATE('" + po.getPoCreateDate() + "', '%d.%m.%Y'), ");
			}
			
			query.append("	?, ?, "
					+ "		?, now(), ?, now() "
					+ " ) ");
					
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, po.getPoNo());
			preparedStatement.setString(index++, po.getSupplierCode());
			preparedStatement.setString(index++, po.getSupplierName());
			preparedStatement.setString(index++, po.getPoCreateByEmail());
			preparedStatement.setInt(index++, po.getPoStatusId());
			preparedStatement.setInt(index++, po.getCreateBy());
			preparedStatement.setInt(index++, po.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if (rowAffective > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					poId = resultSet.getInt(1);
				}
			}
			
			return poId;
		} catch (SQLException e) {
			logger.error("SmartPODAO.insertPO SQLException : " + ExceptionUtils.stackTraceException(e));
			return poId;
		} catch(Exception e) {
			logger.error("SmartPODAO.insertPO Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public int insertPODetail(PoDetailDTO poDetail) {
		int poDetailId = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO po_detail ( "
					+ "		po_id, item, material_code, material_name, delivery_date, "
					+ "		net_price, quantity, unit, net_value, pr_create_date, "
					+ "		planned_deliv_time, purchase_req, "
					+ "		create_by, create_date, update_by, update_date "
					+ "	) VALUES ("
					+ "		?, ?, ?, ?, ");
					
			if (StringUtil.isBlank(poDetail.getDeliveryDate())) {
				query.append("null, ");
			} else {
				query.append("STR_TO_DATE('" + poDetail.getDeliveryDate() + "', '%d.%m.%Y'), ");
			}
			
			query.append("	?, ?, ?, ?, ");
			
			if (StringUtil.isBlank(poDetail.getPrCreateDate())) {
				query.append("null, ");
			} else {
				query.append("STR_TO_DATE('" + poDetail.getPrCreateDate() + "', '%d.%m.%Y'), ");
			}
			
			query.append("	?, ?, "
					+ "		?, now(), ?, now() "
					+ " ) ");
					
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, poDetail.getPoId());
			preparedStatement.setString(index++, poDetail.getItem());
			preparedStatement.setString(index++, poDetail.getMaterialCode());
			preparedStatement.setString(index++, poDetail.getMaterialName());
			preparedStatement.setString(index++, poDetail.getNetPrice());
			preparedStatement.setString(index++, poDetail.getQuantity());
			preparedStatement.setString(index++, poDetail.getUnit());
			preparedStatement.setString(index++, poDetail.getNetValue());
			preparedStatement.setString(index++, poDetail.getPlannedDelivTime());
			preparedStatement.setString(index++, poDetail.getPurchaseReq());
			preparedStatement.setInt(index++, poDetail.getCreateBy());
			preparedStatement.setInt(index++, poDetail.getUpdateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if (rowAffective > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					poDetailId = resultSet.getInt(1);
				}
			}
			
			return poDetailId;
		} catch (SQLException e) {
			logger.error("SmartPODAO.insertPODetail SQLException : " + ExceptionUtils.stackTraceException(e));
			return poDetailId;
		} catch(Exception e) {
			logger.error("SmartPODAO.insertPODetail Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}

	public int insertPOHistory(PoHistoryDTO poHistory) {
		int poHistoryId = 0;
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO po_history ( "
					+ "		po_id, po_step_id, description, "
					+ "		note, create_by, create_date"
					+ "	) VALUES ("
					+ "		?, ?, (SELECT po_step_name FROM po_step WHERE po_step_id = ?), "
					+ "		?, ?, now() "
					+ "	) ");
					
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, poHistory.getPoId());
			preparedStatement.setInt(index++, poHistory.getPoStepId());
			preparedStatement.setInt(index++, poHistory.getPoStepId());
			preparedStatement.setString(index++, poHistory.getNote());
			preparedStatement.setInt(index++, poHistory.getCreateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if (rowAffective > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					poHistoryId = resultSet.getInt(1);
				}
			}
			
			return poHistoryId;
		} catch (SQLException e) {
			logger.error("SmartPODAO.insertPOHistory SQLException : " + ExceptionUtils.stackTraceException(e));
			return poHistoryId;
		} catch(Exception e) {
			logger.error("SmartPODAO.insertPOHistory Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public int insertPoLogInterface(PoLogInterfaceDTO poLogInterface) {
		int poLogInterfaceId = 0;
		try {
			connection.setAutoCommit(true);
			StringBuilder query = new StringBuilder();
			query.append("INSERT INTO po_log_interface ("
					+ "		type, result, file_name, create_by, create_date"
					+ "	) VALUES ("
					+ "		?, ?, ?, ?, now()"
					+ "	) ");
					
			preparedStatement = connection.prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS);
			int index = 1, rowAffective = 0;
			preparedStatement.setString(index++, poLogInterface.getType());
			preparedStatement.setString(index++, poLogInterface.getResult());
			preparedStatement.setString(index++, poLogInterface.getFileName());
			preparedStatement.setInt(index++, poLogInterface.getCreateBy());
			rowAffective = preparedStatement.executeUpdate();
			
			if (rowAffective > 0) {
				resultSet = preparedStatement.getGeneratedKeys();
				while (resultSet.next()) {
					poLogInterfaceId = resultSet.getInt(1);
				}
			}
			
			return poLogInterfaceId;
		} catch (SQLException e) {
			logger.error("SmartPODAO.insertPoLogInterface SQLException : " + ExceptionUtils.stackTraceException(e));
			return poLogInterfaceId;
		} catch(Exception e) {
			logger.error("SmartPODAO.insertPoLogInterface Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updatePoDetailSupplierAccepted(int count, PoDetailDTO poDetail) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE po_detail SET ");
			if (count == 1) {
				query.append(" supp_accepted_1 = ?, ");
				query.append(" supp_note_1 = ?, ");
			} else if (count == 2) {
				query.append(" supp_accepted_2 = ?, ");
				query.append(" supp_note_2 = ?, ");
			} else if (count == 3) {
				query.append(" supp_accepted_3 = ?, ");
				query.append(" supp_note_3 = ?, ");
			}
			
			query.append("	update_by = ?, "
					+ "		update_date = now() "
					+ "	WHERE po_id = ? AND po_detail_id = ? ");
					
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			if (count == 1) {
				preparedStatement.setString(index++, poDetail.getSuppAccepted1());
				preparedStatement.setString(index++, poDetail.getSuppNote1());
			} else if (count == 2) {
				preparedStatement.setString(index++, poDetail.getSuppAccepted2());
				preparedStatement.setString(index++, poDetail.getSuppNote2());
			} else if (count == 3) {
				preparedStatement.setString(index++, poDetail.getSuppAccepted3());
				preparedStatement.setString(index++, poDetail.getSuppNote3());
			}
			
			preparedStatement.setInt(index++, poDetail.getUpdateBy());
			preparedStatement.setInt(index++, poDetail.getPoId());
			preparedStatement.setInt(index++, poDetail.getPoDetailId());
			rowAffective = preparedStatement.executeUpdate();
			
			return rowAffective == 1;
		} catch (SQLException e) {
			logger.error("SmartPODAO.updatePoDetailSupplierAccepted SQLException : " + ExceptionUtils.stackTraceException(e));
			return false;
		} catch(Exception e) {
			logger.error("SmartPODAO.updatePoDetailSupplierAccepted Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updateSupplierAccepted(PoDTO poAccepted) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE po SET "
					+ "		po_accepted_id = ?, "
					+ "		po_status_id = ?, "
					+ "		update_by = ?, "
					+ "		update_date = now() "
					+ "	WHERE po_id = ? ");
					
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, poAccepted.getPoAcceptedId());
			preparedStatement.setInt(index++, poAccepted.getPoStatusId());
			preparedStatement.setInt(index++, poAccepted.getUpdateBy());
			preparedStatement.setInt(index++, poAccepted.getPoId());
			rowAffective = preparedStatement.executeUpdate();
			
			return rowAffective == 1;
		} catch (SQLException e) {
			logger.error("SmartPODAO.updateSupplierAccepted SQLException : " + ExceptionUtils.stackTraceException(e));
			return false;
		} catch(Exception e) {
			logger.error("SmartPODAO.updateSupplierAccepted Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updatePoDetailPurchasingResponse(int count, PoDetailDTO poDetail) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE po_detail SET ");
			if (count == 1) query.append(" pur_response_1 = ?, ");
			else if (count == 2) query.append(" pur_response_2 = ?, ");
			
			query.append("	update_by = ?, "
					+ "		update_date = now() "
					+ "	WHERE po_id = ? AND po_detail_id = ? ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			if (count == 1) preparedStatement.setString(index++, poDetail.getPurResponse1());
			else if (count == 2) preparedStatement.setString(index++, poDetail.getPurResponse2());
			
			preparedStatement.setInt(index++, poDetail.getUpdateBy());
			preparedStatement.setInt(index++, poDetail.getPoId());
			preparedStatement.setInt(index++, poDetail.getPoDetailId());
			rowAffective = preparedStatement.executeUpdate();
			
			return rowAffective == 1;
		} catch (SQLException e) {
			logger.error("SmartPODAO.updatePoDetailPurchasingResponse SQLException : " + ExceptionUtils.stackTraceException(e));
			return false;
		} catch(Exception e) {
			logger.error("SmartPODAO.updatePoDetailPurchasingResponse Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updatePurchasingResponse(PoDTO poResponse) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE po SET "
					+ "		po_status_id = ?, "
					+ "		update_by = ?, "
					+ "		update_date = now() "
					+ "	WHERE po_id = ? ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int index = 1, rowAffective = 0;
			preparedStatement.setInt(index++, poResponse.getPoStatusId());
			preparedStatement.setInt(index++, poResponse.getUpdateBy());
			preparedStatement.setInt(index++, poResponse.getPoId());
			rowAffective = preparedStatement.executeUpdate();
			
			return rowAffective == 1;
		} catch (SQLException e) {
			logger.error("SmartPODAO.updatePurchasingResponse SQLException : " + ExceptionUtils.stackTraceException(e));
			return false;
		} catch(Exception e) {
			logger.error("SmartPODAO.updatePurchasingResponse Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public List<PoDTO> getMailConfirmVendorList(String poIds) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
					+ "	SELECT po.supplier_code, "
					+ "		GROUP_CONCAT(distinct(po.po_id) SEPARATOR ',') as po_id_list, "
					+ "		GROUP_CONCAT(distinct(us.email) SEPARATOR ',') as supplier_mail_list, "
					+ "		GROUP_CONCAT(distinct(po.po_create_by_email) SEPARATOR ',') as purchasing_mail_list "
					+ "	FROM po "
					+ "	left join supplier su on su.supplier_code = po.supplier_code "
					+ "	left join supplier_user_mapping sum on sum.supplier_id = su.supplier_id "
					+ "	left join user us on us.user_id = sum.user_id and us.user_group_id in (8) "
					+ "	where po.po_id in (" + poIds + ") "
					+ "	GROUP BY po.supplier_code ");
			
			logger.info("SmartPODAO.getMailConfirmVendorList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoDTO> poList = new ArrayList<>();
			while(resultSet.next()) {
				PoDTO po = new PoDTO();
				po.setSupplierCode(resultSet.getString("supplier_code"));
				po.setPoIdList(resultSet.getString("po_id_list"));
				po.setMailSupplierList(resultSet.getString("supplier_mail_list"));
				po.setMailPurchasingList(resultSet.getString("purchasing_mail_list"));
				poList.add(po);
			}
			
			return poList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getMailConfirmVendorList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getMailConfirmVendorList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updatePoMailSend(String poIdList) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE po SET "
					+ "		is_mail_sent = 'Y', "
					+ "		po_status_id = IF(po_status_id=4, 1, po_status_id) "
					+ "	WHERE po_id in (" + poIdList + ") ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int rowAffective = 0;
			rowAffective = preparedStatement.executeUpdate();
			
			if (rowAffective > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("SmartPODAO.updatePoMailSend SQLException : " + ExceptionUtils.stackTraceException(e));
			return false;
		} catch(Exception e) {
			logger.error("SmartPODAO.updatePoMailSend Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updatePoView(int poId) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE po SET "
					+ "		is_viewed = 'Y' "
					+ "	WHERE po_id = " + poId);
			
			preparedStatement = connection.prepareStatement(query.toString());
			int rowAffective = 0;
			rowAffective = preparedStatement.executeUpdate();
			
			if (rowAffective > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("SmartPODAO.updatePoView SQLException : " + ExceptionUtils.stackTraceException(e));
			return false;
		} catch(Exception e) {
			logger.error("SmartPODAO.updatePoView Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public boolean updatePoPrint(String poNo) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("UPDATE po SET "
					+ "		is_printed = 'Y' "
					+ "	WHERE po_no = '" + poNo + "' ");
			
			preparedStatement = connection.prepareStatement(query.toString());
			int rowAffective = 0;
			rowAffective = preparedStatement.executeUpdate();
			
			if (rowAffective > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			logger.error("SmartPODAO.updatePoPrint SQLException : " + ExceptionUtils.stackTraceException(e));
			return false;
		} catch(Exception e) {
			logger.error("SmartPODAO.updatePoPrint Exception : " + ExceptionUtils.stackTraceException(e));
 			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public List<PoLogInterfaceDTO> getPoLogInterfaceList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append("select po_log_interface.type, "
					+ "		po_log_interface.result, "
					+ "		po_log_interface.file_name, "
					+ "		date_format(po_log_interface.create_date, '%d/%m/%Y %H:%i') as create_date, "
					+ "		ifnull(user.fullname, 'System') as create_by_name "
					+ "	from po_log_interface "
					+ "	left join user on user.user_id = po_log_interface.create_by "
					+ "	order by po_log_interface.create_date desc ");
			
			logger.info("SmartPODAO.getPoLogInterfaceList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<PoLogInterfaceDTO> poLogInterfaceList = new ArrayList<>();
			while(resultSet.next()) {
				PoLogInterfaceDTO log = new PoLogInterfaceDTO();
				log.setType(resultSet.getString("type"));
				log.setResult(resultSet.getString("result"));
				log.setFileName(resultSet.getString("file_name"));
				log.setCreateDate(resultSet.getString("create_date"));
				log.setCreateByName(resultSet.getString("create_by_name"));
				poLogInterfaceList.add(log);
			}
			
			return poLogInterfaceList;
		} catch (SQLException e) {
			logger.error("SmartPODAO.getPoLogInterfaceList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("SmartPODAO.getPoLogInterfaceList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
}