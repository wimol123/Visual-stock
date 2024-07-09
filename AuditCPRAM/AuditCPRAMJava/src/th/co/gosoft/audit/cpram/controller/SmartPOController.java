package th.co.gosoft.audit.cpram.controller;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.io.IOUtils;
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
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;
import org.jsoup.internal.StringUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.dao.SmartPODAO;
import th.co.gosoft.audit.cpram.dto.PoAcceptedDTO;
import th.co.gosoft.audit.cpram.dto.PoDTO;
import th.co.gosoft.audit.cpram.dto.PoDetailDTO;
import th.co.gosoft.audit.cpram.dto.PoHistoryDTO;
import th.co.gosoft.audit.cpram.dto.PoLogInterfaceDTO;
import th.co.gosoft.audit.cpram.dto.PoStatusDTO;
import th.co.gosoft.audit.cpram.fileupload.FileUpload;
import th.co.gosoft.audit.cpram.mail.BodyEmailDTO;
import th.co.gosoft.audit.cpram.mail.CreateMailBody;
import th.co.gosoft.audit.cpram.mail.MailInfo;
import th.co.gosoft.audit.cpram.mail.MailProcessing;
import th.co.gosoft.audit.cpram.mail.MailReceiver;
import th.co.gosoft.audit.cpram.mail.TemplateMail;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.PoModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;

public class SmartPOController {
	private static Logger logger = Logger.getLogger(SmartPOController.class);
	private static int poImportTemplateData;
	private static String[] poImportField;
	private static boolean[] poImportRequired;
	private static int[] poImportLengthRequired;
	private static boolean[] poImportDate;
	private static boolean[] poImportNumber;
	private static String[] poReportHeader;
	
	public SmartPOController() {
		poImportTemplateData = 16;
		poImportField = new String[] { 
				"Purchasing Doc.", "Item", "Vendor", "Name", "Material", 
				"Short Text", "Delivery date", "Net Price", "PO Quantity", "Order Unit", 
				"Net Value", "Create on(PR)", "Create on(PO)", "Planned Deliv. Time", "Purchase Req", 
				"Create By Email" };
		poImportRequired = new boolean[] { 
				true, true, true, false, true, 
				true, true, true, true, true, 
				true, false, true, false, false, 
				true };
		poImportLengthRequired = new int[] { 
				10, 5, 10, 40, 40, 
				40, 0, 0, 0, 3, 
				0, 0, 0, 0, 10, 
				241 };
		poImportDate = new boolean[] { 
				false, false, false, false, false, 
				false, true, false, false, false, 
				false, true, true, false, false, 
				false };
		poImportNumber = new boolean[] { 
				false, false, false, false, false, 
				false, false, true, true, false, 
				true, false, false, true, false, 
				false };
		poReportHeader = new String[] { 
				"ลำดับ", "เลขที่ PO", "Supplier Code", "Supplier Name", "Delivery Date", 
				"Item", "Material Code", "Material Name", "Net Price", "Quantity", 
				"Unit", "Net Value", "Create on(PR)", "Create on(PO)", "Planned Delivery Time", 
				"Purchase Req", "ผู้บันทึก PO", "วันที่บันทึก", "สถานะ", "ส่งเมล์", 
				"อ่าน", "พิมพ์", "Supplier ตอบรับ PO ครั้งที่ 1", "หมายเหตุ ครั้งที่ 1", "จัดซื้อตอบกลับ ครั้งที่ 1", 
				"Supplier ตอบรับ PO ครั้งที่ 2", "หมายเหตุ ครั้งที่ 2", "จัดซื้อตอบกลับ ครั้งที่ 2", "Supplier ตอบรับ PO ครั้งที่ 3", "หมายเหตุ ครั้งที่ 3" };
	}
	
	private String validatePO(HashMap<String, String> allPO, String po) {
		String errorMsg = "";
		String[] poDetails = po.substring(1).split("\\|");
		if (poDetails.length != poImportTemplateData) {
			errorMsg = "ระบุข้อมูลไม่ครบถ้วน";
		} else if (!StringUtil.isBlank(allPO.get(poDetails[0]))) {
			errorMsg = "มีข้อมูลนี้ในระบบแล้ว";
		} else {
			for (int i = 0; i < poImportTemplateData; i++) {
				if (poImportRequired[i] && StringUtil.isBlank(poDetails[i])) {
					errorMsg = "ข้อมูล " + poImportField[i] + " เป็นค่าว่าง";
					break;
				} else if (poImportLengthRequired[i] > 0 && poDetails[i].length() > poImportLengthRequired[i]) {
					errorMsg = "ขนาดข้อมูล " + poImportField[i] + " เกินที่กำหนด";
					break;
				} else if (poImportDate[i] && !validateDate(poDetails[i])) {
					errorMsg = "รูปแบบข้อมูลวันที่ " + poImportField[i] + " ไม่ถูกต้อง";
					break;
				} else if (poImportNumber[i] && !validateNumber(poDetails[i])) {
					errorMsg = "รูปแบบข้อมูลตัวเลข " + poImportField[i] + " ไม่ถูกต้อง";
					break;
				}
			}
		}
		
		return errorMsg;
	}
	
	private boolean validateDate(String input) {
		if (StringUtil.isBlank(input)) {
			return true;
		} else {
			try {
				Date date = new SimpleDateFormat("dd.MM.yyyy").parse(input);
				return (date != null);
			} catch (Exception e) {
				logger.error("SmartPOController.validateDate Exception : " + ExceptionUtils.stackTraceException(e));
				return false;
			}
		}
	}
	
	private boolean validateNumber(String input) {
		if (StringUtil.isBlank(input)) {
	        return true;
	    } else {
		    try {
		        double d = Double.parseDouble(input);
		        return (d >= 0);
		    } catch (NumberFormatException e) {
		    	logger.error("SmartPOController.validateNumber NumberFormatException : " + ExceptionUtils.stackTraceException(e));
		        return false;
		    }
	    }
	}
	
	private boolean savePO(int userId, List<String> poList) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		boolean result = true;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			HashMap<String, String> poObjs = new HashMap<String, String>();
			String poIdList = "";
			for (String po : poList) {
				String[] poDetails = po.substring(1).split("\\|");
				String poId = poObjs.get(poDetails[0]);
				if (!StringUtil.isBlank(poId)) {
					PoDetailDTO detail = new PoDetailDTO();
					detail.setPoId(Integer.parseInt(poId));
					detail.setItem(poDetails[1]);
					detail.setMaterialCode(poDetails[4]);
					detail.setMaterialName(poDetails[5]);
					detail.setDeliveryDate(poDetails[6]);
					detail.setNetPrice(poDetails[7]);
					detail.setQuantity(poDetails[8]);
					detail.setUnit(poDetails[9]);
					detail.setNetValue(poDetails[10]);
					detail.setPrCreateDate(poDetails[11]);
					detail.setPlannedDelivTime(poDetails[13]);
					detail.setPurchaseReq(poDetails[14]);
					detail.setCreateBy(userId);
					detail.setUpdateBy(userId);
					
					if (smartPODAO.insertPODetail(detail) <= 0) {
						result = false;
						break;
					}
				} else {
					PoDTO poObj = new PoDTO();
					poObj.setPoNo(poDetails[0]);
					poObj.setSupplierCode(poDetails[2]);
					poObj.setSupplierName(poDetails[3]);
					poObj.setPoCreateDate(poDetails[12]);
					poObj.setPoCreateByEmail(poDetails[15]);
					poObj.setPoStatusId(4);
					poObj.setCreateBy(userId);
					poObj.setUpdateBy(userId);
					
					int poReturnId = smartPODAO.insertPO(poObj);
					if (poReturnId <= 0) {
						result = false;
						break;
					}
					
					PoHistoryDTO poHistory = new PoHistoryDTO();
					poHistory.setPoId(poReturnId);
					poHistory.setPoStepId(1);
					poHistory.setNote("Manual Import");
					poHistory.setCreateBy(userId);
					
					if (smartPODAO.insertPOHistory(poHistory) <= 0) {
						result = false;
						break;
					}
					
					PoDetailDTO detail = new PoDetailDTO();
					detail.setPoId(poReturnId);
					detail.setItem(poDetails[1]);
					detail.setMaterialCode(poDetails[4]);
					detail.setMaterialName(poDetails[5]);
					detail.setDeliveryDate(poDetails[6]);
					detail.setNetPrice(poDetails[7]);
					detail.setQuantity(poDetails[8]);
					detail.setUnit(poDetails[9]);
					detail.setNetValue(poDetails[10]);
					detail.setPrCreateDate(poDetails[11]);
					detail.setPlannedDelivTime(poDetails[13]);
					detail.setPurchaseReq(poDetails[14]);
					detail.setCreateBy(userId);
					detail.setUpdateBy(userId);
					
					if (smartPODAO.insertPODetail(detail) <= 0) {
						result = false;
						break;
					}
					
					poObjs.put(poDetails[0], poReturnId + "");
					poIdList = poIdList + poReturnId + ",";
				}
			}
			
			if (result) {
				DatabaseUtils.commit(connection);
			} else {
				DatabaseUtils.rollback(connection);
			}
			
			if (result && poIdList.length() > 0) result = sentMailConfirmPoToVendor(poIdList.substring(0, poIdList.length() - 1), true);
			return result;
		} catch (Exception e) {
			logger.error("SmartPOController.savePO Exception : " + ExceptionUtils.stackTraceException(e));
			DatabaseUtils.rollback(connection);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	private HashMap<String, String> getExitedPOList() {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			return smartPODAO.getAllPOList();
		} catch (Exception e) {
			logger.error("SmartPOController.getExitedPOList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	private boolean sentMailConfirmPoToVendor(String poIdList, boolean sentMail) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			List<PoDTO> mailSendList = smartPODAO.getMailConfirmVendorList(poIdList);
			for (PoDTO mailSend : mailSendList) {
				try {
					BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();
					MailInfo mailInfo = new MailInfo();
					MailReceiver receiver = new MailReceiver();
					receiver.setMailReceiver(Arrays.asList(mailSend.getMailSupplierList().split(",")));
					receiver.setMailReceiverCC(Arrays.asList(mailSend.getMailPurchasingList().split(",")));
					
					mailInfo.setReceiverMail(receiver);
					mailInfo.setTopicMail("!!Confirm Send Email to Vendor");
					mailInfo.setBodyMail(TemplateMail.getTemplateMail(CreateMailBody.getBodyConfirmPOToVendor(bodyEmailDTO)));
					
					if (MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo) && sentMail) {
						smartPODAO.updatePoMailSend(mailSend.getPoIdList());
					}
				} catch (Exception e) {
					logger.error("SmartPOController.sendMailWithHTMLBody Exception : " + ExceptionUtils.stackTraceException(e));
				}
			}
			
			DatabaseUtils.commit(connection);
			
			return true;
		} catch (Exception e) {
			logger.error("SmartPOController.sentMailConfirmPoToVendor Exception : " + ExceptionUtils.stackTraceException(e));
			return false;
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
		
		for (int i = 0; i < poReportHeader.length; i++) {
			Cell cell = row.createCell(i);
			cell.setCellValue(poReportHeader[i]);
			cell.setCellStyle(headerStyle);
		}
	}
	
	private void generateReportDetail(XSSFWorkbook workBook, Row row, PoModel po, int no) {
		DataFormat format = workBook.createDataFormat();
		for (int i = 0; i < poReportHeader.length; i++) {
			CellStyle style = workBook.createCellStyle();
			style.setVerticalAlignment(CellStyle.VERTICAL_TOP);
			style.setWrapText(true);
			
			Cell cell = row.createCell(i);
			switch (i) {
				case 0: cell.setCellValue(no); break;
				case 1: 
					style.setAlignment(CellStyle.ALIGN_CENTER);
					cell.setCellValue(po.getPoNo());  break;
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
				default: cell.setCellValue(""); break;
			}
			
			cell.setCellStyle(style);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private byte[] getFilePoFromSAP(String poNo) {
		String SAP_API_PATH_PO_PDF = ConfigurationSystemManager.getInstance().getSapApiPathPoPdf();
		String SAP_API_USERNAME = ConfigurationSystemManager.getInstance().getSapApiUsername();
		String SAP_API_PASSWORD = ConfigurationSystemManager.getInstance().getSapApiPassword();
		
		try {
	    	String userPassforEncode = SAP_API_USERNAME + ":" + SAP_API_PASSWORD;
			String encodedAuth = "Basic " + Base64.getEncoder().encodeToString(userPassforEncode.getBytes());
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", encodedAuth);
	    	
			RestTemplate restTemplate = new RestTemplate();
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(SAP_API_PATH_PO_PDF);		
		    
			Gson gson = new Gson();
			Map<String, String> poMap = new HashMap<String, String>();
			poMap.put("PUR_ORDER", poNo);
			String json = gson.toJson(poMap);
			logger.info("GET_PDF_FROM_PO Json : " + json);
			
			ResponseEntity<byte[]> response = restTemplate.exchange(
					builder.build().encode().toUri(), 
					HttpMethod.POST, 
					new HttpEntity(json, headers), 
					byte[].class);
			return response.getBody();
		} catch (Exception e) {
			logger.error("SmartPOController.getFilePoFromSAP Exception : " + ExceptionUtils.stackTraceException(e));
			return null;
		}
	}
	
	public String getPoStatusList(String userGroupId) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			List<PoStatusDTO> poStatusList = smartPODAO.getPoStatusList(userGroupId);
			return new Gson().toJson(poStatusList);
		} catch (Exception e) {
			logger.error("SmartPOController.getPoStatusList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getPoAcceptedList() {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			List<PoAcceptedDTO> poAcceptedList = smartPODAO.getPoAcceptedList();
			return new Gson().toJson(poAcceptedList);
		} catch (Exception e) {
			logger.error("SmartPOController.getPoAcceptedList Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public DataTableModel<PoDTO> searchPO(HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel) {
		SessionUtils sessionUtils = null;
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		
		try {
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);	
		
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			
			PoDTO poFilter = new PoDTO();
			List<PoDTO> poList = new ArrayList<PoDTO>();
			DataTableModel<PoDTO> dataTableModel = new DataTableModel<PoDTO>();
			
			for (Column col : dataTablePostParamModel.getColumns()) {
				if (StringUtils.isNotEmpty(col.getName())) {
					if (StringUtils.equals(col.getName(), "poNo")) {
						poFilter.setPoNo(col.getSearch().getValue());
					} else if (StringUtils.equals(col.getName(), "supplier")) {
						poFilter.setSupplier(col.getSearch().getValue());
					} else if (StringUtils.equals(col.getName(), "poStatusId") 
							&& StringUtils.isNotBlank(col.getSearch().getValue())) {
						poFilter.setPoStatusId(Integer.parseInt(col.getSearch().getValue()));
					} else if (StringUtils.equals(col.getName(), "poCreateBy")) {
						poFilter.setPoCreateBy(col.getSearch().getValue());
					} else if (StringUtils.equals(col.getName(), "createDateStart")) {
						poFilter.setCreateDateStart(col.getSearch().getValue());
					} else if (StringUtils.equals(col.getName(), "createDateEnd")) {
						poFilter.setCreateDateEnd(col.getSearch().getValue());
					}
				}
			}
			
			if (user.getUserGroupId().getUserGroupId().equals("3") 
					|| user.getUserGroupId().getUserGroupId().equals("8")) {
				poFilter.setSupplierUserId(user.getUserId());
				poFilter.setNotPoStatusId(4);
			}
			
			poList = smartPODAO.getPoList(poFilter);
			int from = dataTablePostParamModel.getStart();
			int to = dataTablePostParamModel.getStart() + dataTablePostParamModel.getLength();
			
			dataTableModel.setData(poList.subList(from, to <= poList.size() ? to : poList.size()));
			dataTableModel.setRecordsFiltered(poList.size());
			dataTableModel.setRecordsTotal(poList.size());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			
			return dataTableModel;
		} catch (Exception e) {
			logger.error("SmartPOController.searchPO Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public void exportReport(HttpServletResponse servletResponse, HttpServletRequest httpServletRequest, String poSearchParam) {
		SessionUtils sessionUtils = null;
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);	
		
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			List<PoModel> poList = new ArrayList<PoModel>();
			PoDTO poFilter = new PoDTO();
			String[] params = poSearchParam.split("&");
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
			
			poList = smartPODAO.getPoReport(poFilter);
			
			XSSFWorkbook workBook = new XSSFWorkbook();
			XSSFSheet sheet = workBook.createSheet("PO Data Export");
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
			
			for (int i = 0; i < poList.size(); i++) {
				row = sheet.createRow(i + 1);
				generateReportDetail(workBook, row, poList.get(i), i + 1);
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
	
	public void printPo(HttpServletResponse servletResponse, HttpServletRequest httpServletRequest, String poNo) {
		SessionUtils sessionUtils = null;
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);	
		
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			if (StringUtils.equals(user.getUserGroupId().getUserGroupId(), "3") 
					|| StringUtils.equals(user.getUserGroupId().getUserGroupId(), "8")) {
				if (smartPODAO.updatePoPrint(poNo)) DatabaseUtils.commit(connection);
			}
			ServletOutputStream outputStream = servletResponse.getOutputStream();	
			outputStream.write(getFilePoFromSAP(poNo));
			outputStream.close();
			
		} catch (Exception e) {
			logger.error("SmartPOController.printPo Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public byte[] createZipArchive(List<String> filePaths) throws IOException {
	  ByteArrayOutputStream bos = new ByteArrayOutputStream();
	  ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(bos));

	  for (String filePath : filePaths) {
	    try {
	      byte[] fileContent = getFilePoFromSAP(filePath);  // Assuming it retrieves PDF content
	      ZipEntry zipEntry = new ZipEntry(filePath +  ".pdf");  // Use original filePath for name
	      zipOut.putNextEntry(zipEntry);
	      zipOut.write(fileContent, 0, fileContent.length);
	      zipOut.closeEntry();  // Explicitly close entry for each file
	    } catch (Exception e) {
	      // Handle exception (log error, inform user)
	      System.err.println("Error getting file content for: " + filePath + ", Exception: " + e);
	    }
	  }

	  zipOut.close();
	  return bos.toByteArray();
	}

	public void printManyPo(HttpServletResponse servletResponse, HttpServletRequest httpServletRequest, String poNoMany) {
	    SessionUtils sessionUtils = null;
	    Connection connection = null;
	    SmartPODAO smartPODAO = null;
	    System.out.println(poNoMany.replace("%2C",","));
	    String[] stringArray = poNoMany.replace("%2C",",").split(",");

	    // Convert the string array to a List<String>
	    List<String> poNos = Arrays.asList(stringArray);

	    System.out.println(poNos); 
	    
	    
	   //List<String> poNos = convertPoNosToString(poNo);
	   //List<String> poNos = convertPoNosToString(poNo);
	  //List<String> poNos = Arrays.asList("404000054", "404000052");

	    try {
	    	for (String poNo : poNos) {
	    	sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);	
		
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			if (StringUtils.equals(user.getUserGroupId().getUserGroupId(), "3") 
					|| StringUtils.equals(user.getUserGroupId().getUserGroupId(), "8")) {
				if (smartPODAO.updatePoPrint(poNo)) DatabaseUtils.commit(connection);
			}}
	    	System.out.println("rapter");
	    	//System.out.println(zipData.length);
	    	ServletOutputStream outputStream = servletResponse.getOutputStream();
	    	outputStream.write(createZipArchive(poNos));
	    	//outputStream.write(zipData);
	    	outputStream.close();
		} catch (Exception e) {
			logger.error("SmartPOController.printPo Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	    
	
}
	
	public boolean sendMail(String poJson) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		Gson gson = null;
		boolean result = false;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			gson = new Gson();
			PoDTO po = gson.fromJson(poJson, PoDTO.class);
			result = sentMailConfirmPoToVendor(po.getPoId() + "", true);
			
			po = smartPODAO.getPo(po.getPoId());
			return (result && StringUtils.equals(po.getIsMailSent(), "Y"));
		} catch (Exception e) {
			logger.error("SmartPOController.sendMail Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getPo(HttpServletRequest httpServletRequest, String paramPoId) {
		SessionUtils sessionUtils = null;
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			if (StringUtils.equals(user.getUserGroupId().getUserGroupId(), "3") 
					|| StringUtils.equals(user.getUserGroupId().getUserGroupId(), "8")) {
				if (smartPODAO.updatePoView(Integer.parseInt(paramPoId))) DatabaseUtils.commit(connection);
			}
			
			PoDTO po = smartPODAO.getPo(Integer.parseInt(paramPoId));
			return new Gson().toJson(po);
		} catch (Exception e) {
			logger.error("SmartPOController.getPo Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}		

	public String getPoDetail(String paramPoId) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			List<PoDetailDTO> poDetailList = smartPODAO.getPoDetailList(Integer.parseInt(paramPoId));
			GsonBuilder builder = new GsonBuilder();
			builder.serializeNulls();
			Gson gson = builder.create();
			return gson.toJson(poDetailList);
		} catch (Exception e) {
			logger.error("SmartPOController.getPoDetail Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getPoHistory(String paramPoId) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			List<PoHistoryDTO> poHistoryList = smartPODAO.getPoHistoryList(Integer.parseInt(paramPoId));
			GsonBuilder builder = new GsonBuilder();
			builder.serializeNulls();
			Gson gson = builder.create();
			return gson.toJson(poHistoryList);
		} catch (Exception e) {
			logger.error("SmartPOController.getPoHistory Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	public static List<Integer> convertPoIds(String poDetailListJson) {
		  List<Integer> poIds = new ArrayList<>();
		  Gson gson = new Gson();

		  // Parse the JSON string directly into a JsonArray
		  JsonArray poDetailArray = gson.fromJson(poDetailListJson, JsonArray.class);

		  // Iterate through each element in the JsonArray
		  for (JsonElement element : poDetailArray) {
		    // Assuming each element is a JsonObject
		    JsonObject poDetail = element.getAsJsonObject();
		    int poId = poDetail.get("poId").getAsInt();
		    poIds.add(poId);
		  }

		  return poIds;
		}
	public static List<String> convertPoNosToString(String poDetailListJson) {
		  List<String> poIds = new ArrayList<>();
		  Gson gson = new Gson();

		  // Parse the JSON string directly into a JsonArray
		  JsonArray poDetailArray = gson.fromJson(poDetailListJson, JsonArray.class);

		  // Iterate through each element in the JsonArray
		  for (JsonElement element : poDetailArray) {
		    // Assuming each element is a JsonObject
		    JsonObject poDetail = element.getAsJsonObject();
		    String poId = poDetail.get("poNo").getAsString();
		    poIds.add(poId);
		  }

		  return poIds;
		}
	public boolean supplierAcceptedPo(HttpServletRequest httpServletRequest, String poDetailListJson) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		SessionUtils sessionUtils = null;
		boolean result = true;
		List<Integer> poIds = convertPoIds(poDetailListJson);

		
		
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
	
			List<PoDetailDTO> poDetailList = smartPODAO.getPoDetailListByPoIdList(poIds);
			int count = poDetailList.get(0).getCount();
			int checkY = 0;
			
			List<PoDTO> poList = smartPODAO.getPoList(poIds);
			System.out.println(poDetailList);
			System.out.println(poList);



			for (PoDetailDTO poDetail : poDetailList) {
				if (count == 1 )
					poDetail.setSuppAccepted1("Y");
					checkY++;
				if (count == 2)
					poDetail.setSuppAccepted2("Y");
					checkY++;
				if (count == 3) 
					poDetail.setSuppAccepted3("Y");
					checkY++;
				
				poDetail.setUpdateBy(Integer.parseInt(user.getUserId()));
				result = smartPODAO.updatePoDetailSupplierAccepted(count, poDetail);
				System.out.println("work");
				if (!result) 
					System.out.println("not work");
					break;
			}
			if (result) {
				PoAcceptedDTO poAccepted = null;
				if (poDetailList.size() == checkY) poAccepted = smartPODAO.getPoAccepted(1);
				else if (poDetailList.size() > checkY && checkY > 0) poAccepted = smartPODAO.getPoAccepted(2);
				else poAccepted = smartPODAO.getPoAccepted(3);
				
				for (PoDTO po : poList) {
					po.setPoAcceptedId(poAccepted.getPoAcceptedId());
					po.setPoAcceptedName(poAccepted.getPoAcceptedName());
					po.setUpdateBy(Integer.parseInt(user.getUserId()));
					if (poAccepted.getPoAcceptedId() == 1) po.setPoStatusId(3); 
					else po.setPoStatusId(2);
					
					result = smartPODAO.updateSupplierAccepted(po);	
					System.out.println("TEST SMART PO OK ALL 1");
					if (result) {
						System.out.println("TEST SMART PO OK ALL 2");
	
							PoHistoryDTO poHistory = new PoHistoryDTO();
							poHistory.setPoId(po.getPoId());
							poHistory.setPoStepId(2);
							poHistory.setNote(po.getPoAcceptedName());
							poHistory.setCreateBy(Integer.parseInt(user.getUserId()));
							
							if (smartPODAO.insertPOHistory(poHistory) <= 0) result = false;	
							System.out.println("2" + result);
							System.out.println("2" + po);
					}
					if (result) {
						System.out.println("3" + result);

							BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();
							MailInfo mailInfo = new MailInfo(); 
							MailReceiver receiver = new MailReceiver();
						  
							List<String> mailRecieve = new ArrayList<String>();
							mailRecieve.add(po.getPoCreateByEmail());
							receiver.setMailReceiver(mailRecieve);
						  
							mailInfo.setReceiverMail(receiver);
							mailInfo.setTopicMail("แจ้งผลการตอบรับ PO จาก Supplier");
							mailInfo.setBodyMail(TemplateMail.getTemplateMail(
									CreateMailBody.getBodySupplierAcceptPO(bodyEmailDTO, po)));
							MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);	
							System.out.println("3" + po);

	
					}
								
			}}
			
			if (result) DatabaseUtils.commit(connection);
			else DatabaseUtils.rollback(connection);
			return result;
			
		} catch (Exception e) {
			logger.error("SmartPOController.supplierAccepted Exception : " + ExceptionUtils.stackTraceException(e));
			DatabaseUtils.rollback(connection);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
		
	}
		
			

	
	public boolean supplierAccepted(HttpServletRequest httpServletRequest, String poDetailListJson) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		boolean result = true;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			
			gson = new Gson();
			Type listType = new TypeToken<ArrayList<PoDetailDTO>>(){}.getType();
			List<PoDetailDTO> poDetailList = gson.fromJson(poDetailListJson, listType);
			int count = poDetailList.get(0).getCount();
			PoDTO po = smartPODAO.getPo(poDetailList.get(0).getPoId());
			int checkY = 0;
			
			for (PoDetailDTO poDetail : poDetailList) {
				if (count == 1 && StringUtils.equalsIgnoreCase(poDetail.getSuppAccepted1(), "Y")) checkY++;
				if (count == 2 && StringUtils.equalsIgnoreCase(poDetail.getSuppAccepted2(), "Y")) checkY++;
				if (count == 3 && StringUtils.equalsIgnoreCase(poDetail.getSuppAccepted3(), "Y")) checkY++;
				
				poDetail.setUpdateBy(Integer.parseInt(user.getUserId()));
				result = smartPODAO.updatePoDetailSupplierAccepted(count, poDetail);
				if (!result) break;
			}
			
			if (result) {
				PoAcceptedDTO poAccepted = null;
				if (poDetailList.size() == checkY) poAccepted = smartPODAO.getPoAccepted(1);
				else if (poDetailList.size() > checkY && checkY > 0) poAccepted = smartPODAO.getPoAccepted(2);
				else poAccepted = smartPODAO.getPoAccepted(3);
				
				po.setPoAcceptedId(poAccepted.getPoAcceptedId());
				po.setPoAcceptedName(poAccepted.getPoAcceptedName());
				po.setUpdateBy(Integer.parseInt(user.getUserId()));
				if (poAccepted.getPoAcceptedId() == 1) po.setPoStatusId(3); 
				else po.setPoStatusId(2);
				
				result = smartPODAO.updateSupplierAccepted(po);
			}
			
			if (result) {
				PoHistoryDTO poHistory = new PoHistoryDTO();
				poHistory.setPoId(po.getPoId());
				poHistory.setPoStepId(2);
				poHistory.setNote(po.getPoAcceptedName());
				poHistory.setCreateBy(Integer.parseInt(user.getUserId()));
				
				if (smartPODAO.insertPOHistory(poHistory) <= 0) result = false;
			}
			
			if (result) {
				BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();
				MailInfo mailInfo = new MailInfo(); 
				MailReceiver receiver = new MailReceiver();
			  
				List<String> mailRecieve = new ArrayList<String>();
				mailRecieve.add(po.getPoCreateByEmail());
				receiver.setMailReceiver(mailRecieve);
			  
				mailInfo.setReceiverMail(receiver);
				mailInfo.setTopicMail("แจ้งผลการตอบรับ PO จาก Supplier");
				mailInfo.setBodyMail(TemplateMail.getTemplateMail(
						CreateMailBody.getBodySupplierAcceptPO(bodyEmailDTO, po)));
				MailProcessing.getInstance().sendMailWithHTMLBody(mailInfo);
			}
			
			if (result) DatabaseUtils.commit(connection);
			else DatabaseUtils.rollback(connection);
			
			return result;
		} catch (Exception e) {
			logger.error("SmartPOController.supplierAccepted Exception : " + ExceptionUtils.stackTraceException(e));
			DatabaseUtils.rollback(connection);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean purchasingResponse(HttpServletRequest httpServletRequest, String poDetailListJson) {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		boolean result = true;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel user = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			
			gson = new Gson();
			Type listType = new TypeToken<ArrayList<PoDetailDTO>>(){}.getType();
			List<PoDetailDTO> poDetailList = gson.fromJson(poDetailListJson, listType);
			int count = poDetailList.get(0).getCount();
			PoDTO po = smartPODAO.getPo(poDetailList.get(0).getPoId());
			
			for (PoDetailDTO poDetail : poDetailList) {
				poDetail.setUpdateBy(Integer.parseInt(user.getUserId()));
				result = smartPODAO.updatePoDetailPurchasingResponse(count, poDetail);
				if (!result) break;
			}
			
			if (result) {
				po.setUpdateBy(Integer.parseInt(user.getUserId()));
				po.setPoStatusId(1);
				result = smartPODAO.updatePurchasingResponse(po);
			}
						
			if (result) {
				PoHistoryDTO poHistory = new PoHistoryDTO();
				poHistory.setPoId(po.getPoId());
				poHistory.setPoStepId(3);
				poHistory.setCreateBy(Integer.parseInt(user.getUserId()));
				
				if (smartPODAO.insertPOHistory(poHistory) <= 0) result = false;
			}
			
			if (result) result = sentMailConfirmPoToVendor(po.getPoId() + "", false);
			
			if (result) DatabaseUtils.commit(connection);
			else DatabaseUtils.rollback(connection);
			
			return result;
		} catch (Exception e) {
			logger.error("SmartPOController.purchasingResponse Exception : " + ExceptionUtils.stackTraceException(e));
			DatabaseUtils.rollback(connection);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String importPO(HttpServletRequest httpServletRequest, MultipartFormDataInput multipartFormDataInput) {
		String PATH_PO_MANUAL = ConfigurationSystemManager.getInstance().getPathFilePoManual();
		SessionUtils sessionUtils = null;
		FileProcessing fileProcessing = null;
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		String fileName = null;
		try {
			sessionUtils = new SessionUtils(httpServletRequest);
			int userId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			
			logger.info("Get File");
			fileProcessing = new FileProcessing();
			FileUpload fileUpload = new FileUpload(multipartFormDataInput);
			InputPart inputPart = fileUpload.getFormDataMap().get("file_data").get(0);
			InputStream inputStream = inputPart.getBody(InputStream.class, null);
			
			logger.info("Read File To Process");
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line;
			List<String> poList = new ArrayList<String>();
			while ((line = br.readLine()) != null) {
				poList.add(line);
			}
			
			logger.info("Write File To Path");
			MultivaluedMap<String, String> header = inputPart.getHeaders();
			fileName = URLDecoder.decode(fileUpload.getFileName(header), StandardCharsets.UTF_8.name());
			String now = DateUtils.getCurrentDateTime("yyyyMMdd_HHmmssSSS");
			fileName = now + "_" + fileName;
			String pathFile = PATH_PO_MANUAL.concat(fileName);
			InputStream fileInputStream = fileUpload.getBody(inputPart);
			fileProcessing.InputStreamToFile(fileInputStream, pathFile);
			
			HashMap<String, String> allPOList = getExitedPOList();
			List<String> validPO = new ArrayList<String>();
			List<String> invalidPO = new ArrayList<String>();
			List<String> invalidLine = new ArrayList<String>();
			List<String> invalidPOErrorMsg = new ArrayList<String>();
			String validateResult;
			for (int index = 0; index < poList.size(); index++) {
				String po = poList.get(index);
				validateResult = validatePO(allPOList, po);
				if (StringUtil.isBlank(validateResult)) {
					validPO.add(po);
				} else {
					invalidPO.add(po);
					invalidLine.add((index + 1) + "");
					invalidPOErrorMsg.add(validateResult);
				}
			}
			
			HashMap<String, String[]> resultMap = new HashMap<String, String[]>();
			boolean result = savePO(userId, validPO);
			
			PoLogInterfaceDTO log = new PoLogInterfaceDTO();
			log.setType("Manual");
			log.setResult(result ? "Success" : "Fail");
			log.setFileName(fileName);
			log.setCreateBy(userId);
			smartPODAO.insertPoLogInterface(log);
			
			if (result) {				
				resultMap.put("result", new String[] {"บันทึกข้อมูลสำเร็จ"});
				resultMap.put("valid", validPO.toArray(new String[validPO.size()]));
				resultMap.put("invalid", invalidPO.toArray(new String[invalidPO.size()]));
				resultMap.put("invalidLine", invalidLine.toArray(new String[invalidLine.size()]));
				resultMap.put("invalidPOErrorMsg", invalidPOErrorMsg.toArray(new String[invalidPOErrorMsg.size()]));
				return new Gson().toJson(resultMap);
			} else {
				logger.error("SmartPOController.importPO savePO Fail");
				throw new RuntimeException("บันทึกข้อมูลไม่สำเร็จ กรุณาลองใหม่อีกครั้ง");
			}
		} catch (Exception e) {
			logger.error("SmartPOController.importPO Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	
	public String getImportPoHistory() {
		Connection connection = null;
		SmartPODAO smartPODAO = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			smartPODAO = new SmartPODAO(connection);
			List<PoLogInterfaceDTO> poLogList = smartPODAO.getPoLogInterfaceList();
			return new Gson().toJson(poLogList);
		} catch (Exception e) {
			logger.error("SmartPOController.getImportPoHistory Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		} finally {
			if(connection != null) DatabaseUtils.closeConnection(connection);
		}
	}
	
	public void downloadFilePo(HttpServletResponse servletResponse, PoLogInterfaceDTO poLog) {
		String PATH_PO_MANUAL = ConfigurationSystemManager.getInstance().getPathFilePoManual();
		String PATH_PO_SAP = ConfigurationSystemManager.getInstance().getPathFilePoSapBackup();
		
		try {
			String pathFile = "";
			if (StringUtils.equalsIgnoreCase(poLog.getType(), "Manual")) {
				pathFile = PATH_PO_MANUAL + poLog.getFileName();
			} else {
				pathFile = PATH_PO_SAP + poLog.getFileName();
			}
			
			InputStream inputStream = new FileInputStream(new File(pathFile));
			servletResponse.setContentType("application/force-download");
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=" + poLog.getFileName();
	        servletResponse.setHeader(headerKey, headerValue); 
            IOUtils.copy(inputStream, servletResponse.getOutputStream());
            servletResponse.flushBuffer();
            inputStream.close();
		} catch (Exception e) {
			logger.error("SmartPOController.downloadFilePo Exception : " + ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}

}