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
import th.co.gosoft.audit.cpram.dto.VisualAutoMaterialDTO;
import th.co.gosoft.audit.cpram.dto.VisualAutoPlantDTO;
import th.co.gosoft.audit.cpram.dto.VisualAutoSupplierDTO;
import th.co.gosoft.audit.cpram.dto.VisualListWindowTimeDTO;
import th.co.gosoft.audit.cpram.dto.VisualSearchTableListDTO;
import th.co.gosoft.audit.cpram.dto.VisualStockDTO;
import th.co.gosoft.audit.cpram.model.PoModel;
import th.co.gosoft.audit.cpram.model.VisualStockModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class VisualStockDAO  extends StandardAttributeDAO {
	
	private final static Logger logger = Logger.getLogger(SmartPODAO.class);

	public VisualStockDAO(Connection connection) {
		super(connection);
	}
	
	public List<VisualAutoSupplierDTO> getAutoSupplierList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
			+ " select s.supplier_code as Supplier from supplier s union select s.supplier_company as Supplier from supplier s ");
			
			logger.info("VisualStockDAO.getAutoSupplierList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<VisualAutoSupplierDTO> autoSupplierList = new ArrayList<>();
			while(resultSet.next()) {
				VisualAutoSupplierDTO autoSupplier = new VisualAutoSupplierDTO();
				autoSupplier.setSupplier(resultSet.getString("Supplier"));
				autoSupplierList.add(autoSupplier);
			}
			
			return autoSupplierList;
		} catch (SQLException e) {
			logger.error("VisualStockDAO.getAutoSupplierList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("VisualStockDAO.getAutoSupplierList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public List<VisualAutoMaterialDTO> getAutoMaterialList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
			+ " select m.material_code as Material from material m union select m.material_name as Material from material m  ");
			
			logger.info("VisualStockDAO.getAutoMaterialList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<VisualAutoMaterialDTO> autoMaterialList = new ArrayList<>();
			while(resultSet.next()) {
				VisualAutoMaterialDTO autoMaterial = new VisualAutoMaterialDTO();
				autoMaterial.setMaterial(resultSet.getString("Material"));
				autoMaterialList.add(autoMaterial);
			}
			
			return autoMaterialList;
		} catch (SQLException e) {
			logger.error("VisualStockDAO.getAutoMaterialList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("VisualStockDAO.getAutoMaterialList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public List<VisualAutoPlantDTO> getAutoPlantList() {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
			+ " select p.plant_name as Plant from plant p");
			
			logger.info("VisualStockDAO.getAutoPlantList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<VisualAutoPlantDTO> autoPlantList = new ArrayList<>();
			while(resultSet.next()) {
				VisualAutoPlantDTO autoPlant = new VisualAutoPlantDTO();
				autoPlant.setPlant(resultSet.getString("Plant"));
				autoPlantList.add(autoPlant);
			}
			
			return autoPlantList;
		} catch (SQLException e) {
			logger.error("VisualStockDAO.getAutoPlantList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("VisualStockDAO.getAutoPlantList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	public List<VisualSearchTableListDTO> getSearchTableList(String supplierInput,String meterialInput,String plantInput) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
			//+ " select mr.po_no as Po, mr.order_item as Item ,mr.material_no as Material, mr.material_desc as Description , mr.plant as Plant ,mr.current_stock as Stock,mr.order_unit as Unit  from mat_reservation mr left  join supplier s on mr.supplier_no = s.supplier_code left join material m on mr.material_no  = m.material_code LEFT join plant p on mr.plant = p.plant_code  ");
			+ "select mr.po_no as Po, mr.order_item as Item ,mr.material_no as Material, mr.material_desc as Description , mr.plant as Plant ,mr.current_stock as Stock,mr.order_unit as Unit ,mr.rounding_value as RoundingValue, mr.delivery_date as DateOfDelivery "
			+ "from mat_reservation mr "
			+ "join supplier s on mr.supplier_no = s.supplier_code  and s.supplier_code like " 
			+  "'%" +supplierInput+ "%'"    
			+ " or mr.supplier_name = s.supplier_company and s.supplier_company like "
			+ "'%" +supplierInput+ "%'"
			+ " union "
			+"select mr.po_no as Po, mr.order_item as Item ,mr.material_no as Material, mr.material_desc as Description , mr.plant as Plant ,mr.current_stock as Stock,mr.order_unit as Unit ,mr.rounding_value as RoundingValue, mr.delivery_date as DateOfDelivery "
			+ "from mat_reservation mr "
			+ "join material m  on mr.material_no = m.material_code  and m.material_code like " 
			+  "'%" +meterialInput+ "%'"    
			+ " or mr.material_desc = m.material_name and m.material_name like "
			+ "'%" +meterialInput+ "%'"
					);
	
			logger.info("VisualStockDAO.getSearchTableList SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<VisualSearchTableListDTO> SearchTableList = new ArrayList<>();
			while(resultSet.next()) {
				VisualSearchTableListDTO SearchTable = new VisualSearchTableListDTO();
				SearchTable.setPo(resultSet.getString("Po"));
				SearchTable.setItem(resultSet.getString("Item"));
				SearchTable.setMaterial(resultSet.getString("Material"));
				SearchTable.setDescription(resultSet.getString("Description"));
				SearchTable.setPlant(resultSet.getString("Plant"));
				SearchTable.setStock(resultSet.getString("Stock"));
				SearchTable.setUnit(resultSet.getString("Unit"));
				SearchTable.setDeliveryDate(resultSet.getString("DateOfDelivery"));
				SearchTableList.add(SearchTable);
			}
			
			return SearchTableList;
		} catch (SQLException e) {
			logger.error("VisualStockDAO.getSearchTableList SQLException : " + ExceptionUtils.stackTraceException(e));
 			return null;
		} catch (Exception e) {
			logger.error("VisualStockDAO.getSearchTableList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			this.closeResourceDB();
		}
	}
	
	public List<VisualListWindowTimeDTO> getListWindowTime(String supplierInput, String materialInput, String plantInput) {
	    try {
	        connection.setAutoCommit(false);
	        StringBuilder query = new StringBuilder();
	        query.append(""
	            + "select distinct(mr.windows_time) as Time "
	            + "from mat_reservation mr "
	            + "join supplier s on (mr.supplier_no = s.supplier_code and s.supplier_code = ? "
	            + "or mr.supplier_name = s.supplier_company and s.supplier_company = ?) "
	            + "where mr.material_no = ? and mr.plant = ? and "
	            + "order by mr.windows_time");
	    
	        logger.info("VisualStockDAO.getListWindowTime SQL : " + query.toString());
	        preparedStatement = connection.prepareStatement(query.toString());
	        
	        // Set the parameters
	        preparedStatement.setString(1, supplierInput);
	        preparedStatement.setString(2, supplierInput);
	        preparedStatement.setString(3, materialInput);
	        preparedStatement.setString(4, plantInput);
	        
	        resultSet = preparedStatement.executeQuery();
	        
	        List<VisualListWindowTimeDTO> TimeList = new ArrayList<>();
	        while(resultSet.next()) {
	            VisualListWindowTimeDTO Time = new VisualListWindowTimeDTO();
	            Time.setTime(resultSet.getString("Time"));

	            TimeList.add(Time);
	        }
	        
	        return TimeList;
	    } catch (SQLException e) {
	        logger.error("VisualStockDAO.getSearchTableList SQLException : " + ExceptionUtils.stackTraceException(e));
	        return null;
	    } catch (Exception e) {
	        logger.error("VisualStockDAO.getSearchTableList Exception : " + ExceptionUtils.stackTraceException(e));
	        throw new RuntimeException(e.getMessage(), e);
	    } finally {
	        this.closeResourceDB();
	    }
	}


	public List<VisualSearchTableListDTO> getSearchTableListWithTime(String supplierInput, String materialInput, String plantInput, String timeList) {
	    try {
	        connection.setAutoCommit(false);

	        // Step 2: Split the string by commas
	        String[] timeArray = timeList.split(",");
	        int count = 0;
	        int count1 = 0;

	        // Step 3: Process the times (for example, print them)
	        StringBuilder query1 = new StringBuilder();

	        query1.append("SELECT a.*, (");

	        for (String time : timeArray) {
	            String formattedTime = time.replace(":", "");

	            query1.append("a.`" + formattedTime + "/SupConfirm`");

	            count1++;
	            if (count1 != timeArray.length) {
	                query1.append(" + ");
	            }
	        }

	        query1.append(") AS Total FROM ( ");
	        query1.append("SELECT mr.po_no as Po, mr.order_item as Item, mr.material_no as Material, ");
	        query1.append("mr.supplier_no as Supplier, mr.material_desc as Description, mr.plant as Plant, ");
	        query1.append("mr.current_stock as Stock, mr.order_unit as Unit, ");

	        for (String time : timeArray) {
	            String formattedTime = time.replace(":", "");

	            query1.append("MAX(CASE WHEN mr.windows_time = '").append(time).append("' THEN mr.reservation_qty ELSE 0 END) AS '")
	                    .append(formattedTime).append("/Reservation/Requirement', ");
	            query1.append("MAX(CASE WHEN mr.windows_time = '").append(time).append("' THEN mr.rounding_value ELSE 0 END) AS '")
	                    .append(formattedTime).append("/Rounding', ");
	            query1.append("MAX(CASE WHEN mr.windows_time = '").append(time).append("' THEN mr.rounding_value ELSE 0 END) AS '")
	                    .append(formattedTime).append("/SupConfirm', ");
	            query1.append("MAX(CASE WHEN mr.windows_time = '").append(time).append("' THEN '' ELSE '' END) AS '")
	                    .append(formattedTime).append("/SupRemark', ");
	            query1.append("MAX(CASE WHEN mr.windows_time = '").append(time).append("' THEN '' ELSE '' END) AS '")
	                    .append(formattedTime).append("/SupResponse'");

	            count++;
	            if (count != timeArray.length) {
	                query1.append(", ");
	            }
	        }

	        query1.append(" FROM mat_reservation mr ");
	        query1.append("JOIN supplier s ON (mr.supplier_no = s.supplier_code OR mr.supplier_name = s.supplier_company) ");
	        query1.append("WHERE (s.supplier_code = ? OR s.supplier_company = ?) ");
	        query1.append("AND mr.material_no = ? AND mr.plant = ? ");
	        query1.append("GROUP BY mr.po_no, mr.order_item, mr.material_no, mr.supplier_no, mr.material_desc, mr.plant, mr.current_stock, mr.order_unit ) a");

	        logger.info("VisualStockDAO.getSearchTableListWithTime SQL1: " + query1.toString());

	        preparedStatement = connection.prepareStatement(query1.toString());

	        // Set the parameters for exact match
	        preparedStatement.setString(1, supplierInput);
	        preparedStatement.setString(2, supplierInput);
	        preparedStatement.setString(3, materialInput);
	        preparedStatement.setString(4, plantInput);

	        resultSet = preparedStatement.executeQuery();

	        List<VisualSearchTableListDTO> searchTableList = new ArrayList<>();
	        while (resultSet.next()) {
	            VisualSearchTableListDTO searchTable = new VisualSearchTableListDTO();
	            searchTable.setPo(resultSet.getString("Po"));
	            searchTable.setItem(resultSet.getString("Item"));
	            searchTable.setMaterial(resultSet.getString("Material"));
	            searchTable.setDescription(resultSet.getString("Description"));
	            searchTable.setPlant(resultSet.getString("Plant"));
	            searchTable.setStock(resultSet.getString("Stock"));
	            searchTable.setUnit(resultSet.getString("Unit"));

	            // Set dynamic properties
	            for (String time : timeArray) {
	                String formattedTime = time.replace(":", "");
	                logger.info(formattedTime);

	                searchTable.setDynamicProperty(formattedTime + "/Reservation/Requirement", resultSet.getString(formattedTime + "/Reservation/Requirement"));
	                searchTable.setDynamicProperty(formattedTime + "/Rounding", resultSet.getString(formattedTime + "/Rounding"));
	                searchTable.setDynamicProperty(formattedTime + "/SupConfirm", resultSet.getString(formattedTime + "/SupConfirm"));
	                searchTable.setDynamicProperty(formattedTime + "/SupRemark", resultSet.getString(formattedTime + "/SupRemark"));
	                searchTable.setDynamicProperty(formattedTime + "/SupResponse", resultSet.getString(formattedTime + "/SupResponse"));

	                logger.info(searchTable.getDynamicProperty(formattedTime + "/Reservation/Requirement"));
	            }

	            searchTableList.add(searchTable);
	            logger.info(searchTable.getPo());
	        }

	        return searchTableList;

	    } catch (SQLException e) {
	        logger.error("VisualStockDAO.getSearchTableList SQLException: " + ExceptionUtils.stackTraceException(e));
	        return null;
	    } catch (Exception e) {
	        logger.error("VisualStockDAO.getSearchTableList Exception: " + ExceptionUtils.stackTraceException(e));
	        throw new RuntimeException(e.getMessage(), e);
	    } finally {
	        this.closeResourceDB();
	    }
	}


	
	public List<VisualStockModel> getPoReport(VisualStockDTO visualStockFilter) {
		try {
			connection.setAutoCommit(false);
			StringBuilder query = new StringBuilder();
			query.append(""
					+ "	select mr.po_no as Po, mr.order_item as Item, mr.material_no as Material, mr.material_desc as Description, mr.plant as Plant, mr.current_stock as Stock, mr.order_unit as Unit "
					+ "	from mat_reservation mr ");
			
			logger.info("VisualStockDAO.getVisualReport SQL : " + query.toString());
			preparedStatement = connection.prepareStatement(query.toString());
			resultSet = preparedStatement.executeQuery();
			
			List<VisualStockModel> visualStockList = new ArrayList<>();
			while(resultSet.next()) {
				VisualStockModel vsm = new VisualStockModel();
				vsm.setPo(resultSet.getString("Po"));
				vsm.setItem(resultSet.getString("Item"));
				vsm.setMaterial(resultSet.getString("Material"));
				vsm.setDescription(resultSet.getString("Description"));
				vsm.setPlant(resultSet.getString("Plant"));
				vsm.setStock(resultSet.getString("Stock"));
				vsm.setUnit(resultSet.getString("Unit"));
				
				/*
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
				*/
				visualStockList.add(vsm);
			}
			
			return visualStockList;
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
	
	private String generateFilterVisualStock(VisualStockDTO visualStockFilter) {
		StringBuilder query = new StringBuilder();
		query.append("");
		if (StringUtils.isNotBlank(visualStockFilter.getSupplierUserId())) {
			query.append(""
					+ "	and po.supplier_code in ("
					+ "		select supplier_code "
					+ "		from user "
					+ "		join supplier_user_mapping mp on mp.user_id = user.user_id "
					+ "		join supplier supp on supp.supplier_id = mp.supplier_id "
					+ "		where user.user_id = " + visualStockFilter.getSupplierUserId()
					+ "	)");
		} else if (StringUtils.isNotBlank(visualStockFilter.getSupplier())) {
			query.append(""
					+ "	and replace(concat_ws(' ', po.supplier_code, sup.supplier_company), ' ', '') like "
					+ "		'%" + visualStockFilter.getSupplier().replaceAll(" ", "") + "%' ");
		}
		
		if (StringUtils.isNotBlank(visualStockFilter.getPoNo())) {
			query.append(""
					+ "	and po.po_no like "
					+ "		'%" + visualStockFilter.getPoNo().replaceAll(" ", "") + "%' ");
		}
		
		if (visualStockFilter.getPoStatusId() > 0) {
			query.append(""
					+ "	and po.po_status_id = " + visualStockFilter.getPoStatusId());
		}
		
		if (StringUtils.isNotBlank(visualStockFilter.getPoCreateBy())) {
			query.append(""
					+ "	and replace(concat_ws('', user.employee_id, user.fullname, po.po_create_by_email), ' ', '') like "
					+ "		'%" + visualStockFilter.getPoCreateBy().replaceAll(" ", "") + "%' ");
		}
		
		if (StringUtils.isNotBlank(visualStockFilter.getCreateDateStart())) {
			query.append(""
					+ " and po.create_date >= "
					+ "		str_to_date('" + visualStockFilter.getCreateDateStart() + "', '%d/%m/%Y %H:%i') ");
		}
		
		if (StringUtils.isNotBlank(visualStockFilter.getCreateDateEnd())) {
			query.append(""
					+ " and po.create_date <= "
					+ "		str_to_date('" + visualStockFilter.getCreateDateEnd() + "', '%d/%m/%Y %H:%i') ");
		}
		
		if (visualStockFilter.getNotPoStatusId() > 0) {
			query.append(""
					+ "	and po.po_status_id <> " + visualStockFilter.getNotPoStatusId());
		}
		
		return query.toString();
	}

	
}
