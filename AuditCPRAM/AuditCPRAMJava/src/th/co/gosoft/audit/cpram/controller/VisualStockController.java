package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;

import th.co.gosoft.audit.cpram.dao.SmartPODAO;
import th.co.gosoft.audit.cpram.dao.VisualStockDAO;
import th.co.gosoft.audit.cpram.dto.PoDTO;
import th.co.gosoft.audit.cpram.dto.PoStatusDTO;
import th.co.gosoft.audit.cpram.dto.StockDTO;
import th.co.gosoft.audit.cpram.dto.VisualAutoMaterialDTO;
import th.co.gosoft.audit.cpram.dto.VisualAutoPlantDTO;
import th.co.gosoft.audit.cpram.dto.VisualAutoSupplierDTO;
import th.co.gosoft.audit.cpram.dto.VisualSearchTableListDTO;
import th.co.gosoft.audit.cpram.dto.VisualStockDTO;
import th.co.gosoft.audit.cpram.dto.VisualListWindowTimeDTO;

import th.co.gosoft.audit.cpram.model.PoModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.VisualStockModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;


public class VisualStockController {
	private static Logger logger = Logger.getLogger(VisualStockController.class);
	private static String[] visualStockReportHeader;

	//Supplier Confirm : แบ่ง Column ย่อยเป็น ครั้งที่ 1, ครั้งที่ 2, ครั้งที่ 3 โดยแต่ละครั้งมีข้อมูลดังนี้ o Delivery Date & Time : แสดงแยกตาม Window Time โดยแต่ละ Window Time มีข้อมูลดังนี้ § Reservation/Requirement § Rounding § Sup. Confirm § หมายเหตุ § จัดซื้อตอบกลับ o Total : แสดงยอดรวมจาก Column Sup. Confirm จากทุก Window Time ในครั้งนั้นๆ
	
	public VisualStockController() {
		visualStockReportHeader = new String[] { 
				"ลำดับ", "PO No.", "Item No.", "Material No.", "Description", 
				"Plant", "Total Stock", "Unit", "Supplier Confirm 1", "Supplier Confirm 2", 
				"Supplier Confirm 3" };
	}

	
	public String getSupplierList() {
		Connection connection = null;
		VisualStockDAO visualStockDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			visualStockDAO = new VisualStockDAO(connection);
			List<VisualAutoSupplierDTO> AutoSupplierList = visualStockDAO.getAutoSupplierList();
			return new Gson().toJson(AutoSupplierList);
		} catch (Exception e) {
			logger.error("VisualStockController.getSupplierList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getMaterialList() {
		Connection connection = null;
		VisualStockDAO visualStockDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			visualStockDAO = new VisualStockDAO(connection);
			List<VisualAutoMaterialDTO> AutoMaterialList = visualStockDAO.getAutoMaterialList();
			return new Gson().toJson(AutoMaterialList);
		} catch (Exception e) {
			logger.error("VisualStockController.getMaterialList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getPlantList() {
		Connection connection = null;
		VisualStockDAO visualStockDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			visualStockDAO = new VisualStockDAO(connection);
			List<VisualAutoPlantDTO> AutoPlantList = visualStockDAO.getAutoPlantList();
			return new Gson().toJson(AutoPlantList);
		} catch (Exception e) {
			logger.error("VisualStockController.getPlantList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getSearchTableList(String supplierInput,String meterialInput,String plantInput) {
		Connection connection = null;
		VisualStockDAO visualStockDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			visualStockDAO = new VisualStockDAO(connection);
			List<VisualSearchTableListDTO> SearchTableList = visualStockDAO.getSearchTableList(supplierInput,meterialInput,plantInput);
			return new Gson().toJson(SearchTableList);
		} catch (Exception e) {
			logger.error("VisualStockController.getSearchTableList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getListWindowTime(String supplierInput,String meterialInput,String plantInput) {
		Connection connection = null;
		VisualStockDAO visualStockDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			visualStockDAO = new VisualStockDAO(connection);
			List<VisualListWindowTimeDTO> SearchTableList = visualStockDAO.getListWindowTime(supplierInput,meterialInput,plantInput);
			return new Gson().toJson(SearchTableList);
		} catch (Exception e) {
			logger.error("VisualStockController.getListWindowTime Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	public String getSearchTableListWithTime(String supplierInput,String meterialInput,String plantInput,String timeList) {
		Connection connection = null;
		VisualStockDAO visualStockDAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			visualStockDAO = new VisualStockDAO(connection);
			List<VisualSearchTableListDTO> SearchTableList = visualStockDAO.getSearchTableListWithTime(supplierInput,meterialInput,plantInput,timeList);
			
			return new Gson().toJson(SearchTableList);
		} catch (Exception e) {
			logger.error("VisualStockController.getSearchTableList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	public void exportReport(HttpServletResponse servletResponse, HttpServletRequest httpServletRequest, String visualStockSearchParam) {
		SessionUtils sessionUtils = null;
		Connection connection = null;
		VisualStockDAO visualStockDAO = null;
		try {
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);	
		
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			visualStockDAO = new VisualStockDAO(connection);
			
			List<VisualStockModel> visualStockList = new ArrayList<VisualStockModel>();
			VisualStockDTO poFilter = new VisualStockDTO();
			String[] params = visualStockSearchParam.split("&");
			for (String param : params) {
				String[] val = param.split("=");
				if (StringUtils.equals(val[0], "supplier") && val.length > 1) {
					poFilter.setSupplier(val[1]);
				} else if (StringUtils.equals(val[0], "po_no") && val.length > 1) {
					poFilter.setPoNo(val[1]);
				} else if (StringUtils.equals(val[0], "po_status") && val.length > 1 
						&& StringUtils.isNotBlank(val[1])) {
					poFilter.setPoStatusId(Integer.parseInt(val[1]));
				} else if (StringUtils.equals(val[0], "po_create_by") && val.length > 1) {
					poFilter.setPoCreateBy(val[1]);
				} else if (StringUtils.equals(val[0], "create_date_start") && val.length > 1) {
					String value = val[1];
					value = value.replaceAll("%2F", "/");
					value = value.replaceAll("%3A", ":");
					value = value.replaceAll("\\+", " ");
					poFilter.setCreateDateStart(value);
				} else if (StringUtils.equals(val[0], "create_date_end") && val.length > 1) {
					String value = val[1];
					value = value.replaceAll("%2F", "/");
					value = value.replaceAll("%3A", ":");
					value = value.replaceAll("\\+", " ");
					poFilter.setCreateDateEnd(value);
				}
			}
			
			if (user.getUserGroupId().getUserGroupId().equals("3") 
					|| user.getUserGroupId().getUserGroupId().equals("8")) {
				poFilter.setSupplierUserId(user.getUserId());
				poFilter.setNotPoStatusId(4);
			}
			
			visualStockList = visualStockDAO.getPoReport(poFilter);
			
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet("Visual Stock Data Export");
			sheet.setColumnWidth(0, 1500);
			sheet.setColumnWidth(1, 3000);
			sheet.setColumnWidth(2, 4000);
			sheet.setColumnWidth(3, 8000);
			sheet.setColumnWidth(4, 3000);
			sheet.setColumnWidth(5, 3000);
			sheet.setColumnWidth(6, 4000);
			sheet.setColumnWidth(7, 15000);
			sheet.setColumnWidth(8, 4000);
			sheet.setColumnWidth(9, 4000);
			sheet.setColumnWidth(10, 1500);
			sheet.setColumnWidth(11, 4000);
			sheet.setColumnWidth(12, 3000);
			sheet.setColumnWidth(13, 3000);
			sheet.setColumnWidth(14, 3000);
			sheet.setColumnWidth(15, 3000);
			sheet.setColumnWidth(16, 12000);
			sheet.setColumnWidth(17, 5000);
			sheet.setColumnWidth(18, 5000);
			sheet.setColumnWidth(19, 2000);
			sheet.setColumnWidth(20, 2000);
			sheet.setColumnWidth(21, 2000);
			sheet.setColumnWidth(22, 2000);
			sheet.setColumnWidth(23, 10000);
			sheet.setColumnWidth(24, 10000);
			sheet.setColumnWidth(25, 2000);
			sheet.setColumnWidth(26, 10000);
			sheet.setColumnWidth(27, 10000);
			sheet.setColumnWidth(28, 2000);
			sheet.setColumnWidth(29, 10000);
			
			Row row = sheet.createRow(0);
			generateReportHeader(workBook, row);
			
			for (int i = 0; i < visualStockList.size(); i++) {
				row = sheet.createRow(i + 1);
				generateReportDetail(workBook, row, visualStockList.get(i), i + 1);
			}
			
			ServletOutputStream outputStream = servletResponse.getOutputStream();
			workBook.write(outputStream);
	        outputStream.close();
		} catch (Exception e) {
			logger.error("SmartPOController.exportReport Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}

	private void generateReportHeader(XSSFWorkbook workBook, Row row) {
		Font font = workBook.createFont();
		font.setBoldweight(Font.BOLDWEIGHT_BOLD);
		
		CellStyle headerStyle = workBook.createCellStyle();
		headerStyle.setFont(font);
		headerStyle.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
		headerStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);
		headerStyle.setBorderBottom(CellStyle.BORDER_THIN);
		headerStyle.setBorderRight(CellStyle.BORDER_THIN);
		headerStyle.setAlignment(CellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		headerStyle.setWrapText(true);
		
		for (int i = 0; i < visualStockReportHeader.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(visualStockReportHeader[i]);
			cell.setCellStyle(headerStyle);
		}
	}

	private void generateReportDetail(XSSFWorkbook workBook, Row row, VisualStockModel vsm, int no) {
		DataFormat format = workBook.createDataFormat();
		for (int i = 0; i < visualStockReportHeader.length; i++) {
			CellStyle style = workBook.createCellStyle();
			style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			style.setWrapText(true);
			
			
			Cell cell = row.createCell(i);
			switch (i) {
				case 0: cell.setCellValue(no); break;
				case 1: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(vsm.getPo());  break;
				case 2: 
					cell.setCellValue(vsm.getItem());  break;
				case 3: 
					cell.setCellValue(vsm.getMaterial());  break;
				case 4: 
					cell.setCellValue(vsm.getDescription());  break;	
				case 5: 
					cell.setCellValue(vsm.getPlant());  break;	
				case 6: 
					cell.setCellValue(vsm.getStock());  break;	
				case 7: 
					cell.setCellValue(vsm.getUnit());  break;	
					
/*					
				case 2: cell.setCellValue(po.getSupplierCode()); break;
				case 3: cell.setCellValue(po.getSupplierName()); break;
				case 4: cell.setCellValue(po.getDeliveryDate()); break;
				case 5: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getItem()); break;
				case 6: cell.setCellValue(po.getMaterialCode()); break;
				case 7: cell.setCellValue(po.getMaterialName()); break;
				case 8: 
					style.setDataFormat(format.getFormat("#,##0.00"));
					cell.setCellValue(Double.parseDouble(po.getNetPrice())); break;
				case 9: 
					style.setDataFormat(format.getFormat("#,##0.000"));
					cell.setCellValue(Double.parseDouble(po.getQuantity())); break;
				case 10: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getUnit()); break;
				case 11: 
					style.setDataFormat(format.getFormat("#,##0.00"));
					cell.setCellValue(Double.parseDouble(po.getNetValue())); break;
				case 12: cell.setCellValue(po.getPrCreateDate()); break;
				case 13: cell.setCellValue(po.getPoCreateDate()); break;
				case 14: cell.setCellValue(po.getPlannedDelivTime()); break;
				case 15: cell.setCellValue(po.getPurchaseReq()); break;
				case 16: cell.setCellValue(po.getPoCreateBy()); break;
				case 17: cell.setCellValue(po.getCreateDate()); break;
				case 18: cell.setCellValue(po.getPoStatusName()); break;
				case 19: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getIsMailSent()); break;
				case 20: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getIsViewed()); break;
				case 21: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getIsPrinted()); break;
				case 22: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getSuppAccepted1()); break;
				case 23: cell.setCellValue(po.getSuppNote1()); break;
				case 24: cell.setCellValue(po.getPurResponse1()); break;
				case 25: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getSuppAccepted2()); break;
				case 26: cell.setCellValue(po.getSuppNote2()); break;
				case 27: cell.setCellValue(po.getPurResponse2()); break;
				case 28: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getSuppAccepted3()); break;
				case 29: cell.setCellValue(po.getSuppNote3()); break;
*/
				default: cell.setCellValue(""); break;
			}
			
			cell.setCellStyle(style);
		}
	}

}