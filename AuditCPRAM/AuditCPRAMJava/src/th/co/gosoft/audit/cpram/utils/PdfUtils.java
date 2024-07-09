package th.co.gosoft.audit.cpram.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabSettings;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.StringUtils;

import sun.misc.BASE64Decoder;
import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.controller.EvidenceController;
import th.co.gosoft.audit.cpram.model.AuditResultModel;
import th.co.gosoft.audit.cpram.model.CarDetailModel;
import th.co.gosoft.audit.cpram.model.EvalPlanModel;
import th.co.gosoft.audit.cpram.model.EvidenceModel;
import th.co.gosoft.audit.cpram.model.PrintFinalAuditResultPdfModel;
import th.co.gosoft.audit.cpram.model.UserModel;

public class PdfUtils {

	private final static Logger logger = Logger.getLogger(PdfUtils.class);
	
	public static File createFinalAuditResultPdf(String pathFilenameOfFinalAuditResult, PrintFinalAuditResultPdfModel printFinalAuditResultPdfModel) {
		Document document = null;
		PdfWriter pdfWriter = null;
		try {
			EvidenceController evidenceController = new EvidenceController();
			document = new Document(PageSize.A4, 40, 40, 60, 60);
			
			File file = new File(pathFilenameOfFinalAuditResult);
			
			String absolutePathOfFile = file.getAbsolutePath().substring(0, file.getAbsolutePath().lastIndexOf(File.separator));
			if(!Files.exists(Paths.get(absolutePathOfFile))) {
				new File(Paths.get(absolutePathOfFile).toString()).mkdirs();
			}
			
			FileOutputStream fileOutputStreamPdf = new FileOutputStream(file);
			pdfWriter = PdfWriter.getInstance(document, fileOutputStreamPdf);
			document.open();
			
			setupPropertiesPdf(document, "FinalAuditResultReport", "This Report Final Audit Result Of Checklist Plan No. "+printFinalAuditResultPdfModel.getChecklistPlanNo().trim());	
			char checkedSymbol = '\u00FE', uncheckedSymbol = '\u00A8';
			System.out.println(ConfigurationSystemManager.getInstance().getPathFileReportPdfFont().trim());
			System.out.println(ConfigurationSystemManager.getInstance().getPathFileReportPdfFontSymbol().trim());
			BaseFont baseFont = BaseFont.createFont(ConfigurationSystemManager.getInstance().getPathFileReportPdfFont().trim(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED); 
			BaseFont baseFontSymbol = BaseFont.createFont(ConfigurationSystemManager.getInstance().getPathFileReportPdfFontSymbol().trim(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//รูป			BaseFont baseFont = BaseFont.createFont("D:\\Users\\thitipapre\\Desktop\\2.2.0V2\\data\\resources\\THSarabunNew.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
//รูป				BaseFont baseFontSymbol = BaseFont.createFont("D:\\Users\\thitipapre\\Desktop\\2.2.0V2\\data\\resources\\wingding.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
			
			logger.info("Starting Generate Cover Page ........");
			
			
			PdfPTable tableFooterCoverPage = new PdfPTable(1);
			PdfPTable tableHeaderCoverPage = new PdfPTable(1);
			tableFooterCoverPage.setTotalWidth(523);
			tableHeaderCoverPage.setTotalWidth(523);
			float[] columnTableFooterWidthsCoverPage = {100};
			tableFooterCoverPage.setWidths(columnTableFooterWidthsCoverPage);
			
			Paragraph paragraphFooterCoverPage = new Paragraph();
			paragraphFooterCoverPage.add(new Chunk("บริษัท ซีพีแรม จำกัด 177 ม.4 ถ.ปทุมธานี-ลาดหลุมแก้ว ต.ระแหง อ.ลาดหลุมแก้ว จ.ปทุมธานี 12140 โทรศัพท์ 02-844-8100 โทรสาร 02-976-2265 www.cpram.co.th", new Font(baseFont, 11)));
			paragraphFooterCoverPage.setAlignment(Element.PTABLE);			
			PdfPCell columnFooterCoverPage = new PdfPCell(paragraphFooterCoverPage);
			columnFooterCoverPage.setBorderColor(BaseColor.WHITE);
			columnFooterCoverPage.setUseAscender(true);
			columnFooterCoverPage.setVerticalAlignment(Rectangle.ALIGN_CENTER);
			columnFooterCoverPage.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
			tableFooterCoverPage.addCell(columnFooterCoverPage);
			tableFooterCoverPage.completeRow();
			
			paragraphFooterCoverPage = new Paragraph();
			paragraphFooterCoverPage.add(new Chunk("CPRAM Co.,Ltd. 177 Moo 4, Pathum Thani-Lat Lum Kaeo Rd., Rahaeng Lat Lum Kaeo, Pathum Thani 12140 Thailand Tel. +662-844-8100 Fax.+662-976-2265 www.cpram.co.th", new Font(baseFont, 11)));
			paragraphFooterCoverPage.setAlignment(Element.PTABLE);			
			columnFooterCoverPage = new PdfPCell(paragraphFooterCoverPage);
			columnFooterCoverPage.setBorderColor(BaseColor.WHITE);
			columnFooterCoverPage.setUseAscender(true);
			columnFooterCoverPage.setVerticalAlignment(Rectangle.ALIGN_CENTER);
			columnFooterCoverPage.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
			tableFooterCoverPage.addCell(columnFooterCoverPage);
			tableFooterCoverPage.completeRow();
			
			
			FooterTable eventCoverPage  = new FooterTable(tableHeaderCoverPage, tableFooterCoverPage);
			
			pdfWriter.setPageEvent(eventCoverPage);
			System.out.println(ConfigurationSystemManager.getInstance().getPathLogoCPRAM().trim());
			Image img = Image.getInstance(ConfigurationSystemManager.getInstance().getPathLogoCPRAM().trim());
//รูป			Image img = Image.getInstance("D:\\Users\\thitipapre\\Desktop\\2.2.0V2\\data\\resources\\cpram_logo.png");
			img.scaleToFit(550f, 20f);
			Paragraph paragraphCoverPage = new Paragraph();
			paragraphCoverPage.add(new Chunk(img, -20, 30));
			paragraphCoverPage.setAlignment(Element.ALIGN_RIGHT);
			document.add(paragraphCoverPage);
			
			paragraphCoverPage = new Paragraph();
			paragraphCoverPage.add(new Chunk("รายงานการตรวจประเมินผู้ส่งมอบ", new Font(baseFont, 18, Font.BOLD)));
			paragraphCoverPage.setAlignment(Element.ALIGN_CENTER);
			paragraphCoverPage.setSpacingAfter(100);
			document.add(paragraphCoverPage);
			
			paragraphCoverPage = new Paragraph();
			paragraphCoverPage.add(new Chunk("Supplier : "+printFinalAuditResultPdfModel.getSupplierName().trim(), new Font(baseFont, 18)));
			paragraphCoverPage.setAlignment(Element.ALIGN_CENTER);
			paragraphCoverPage.setSpacingAfter(100);
			document.add(paragraphCoverPage);
			
			paragraphCoverPage = new Paragraph();
			paragraphCoverPage.add(new Chunk("Date : "+printFinalAuditResultPdfModel.getPlanDate()+" ", new Font(baseFont, 16)));
			paragraphCoverPage.setAlignment(Element.ALIGN_CENTER);
			paragraphCoverPage.setSpacingAfter(10);
			document.add(paragraphCoverPage);
			
			paragraphCoverPage = new Paragraph();
			paragraphCoverPage.add(new Chunk("สถานที่ประเมิน : ", new Font(baseFont, 18)));
			paragraphCoverPage.add(new Chunk(printFinalAuditResultPdfModel.getSupplierLocation().trim(), new Font(baseFont, 16)));
			paragraphCoverPage.setAlignment(Element.ALIGN_CENTER);
			paragraphCoverPage.setSpacingAfter(80);
			document.add(paragraphCoverPage);
			
			paragraphCoverPage = new Paragraph();
			paragraphCoverPage.add(new Chunk("ผลการตรวจประเมิน  ", new Font(baseFont, 18)));
			paragraphCoverPage.setAlignment(Element.ALIGN_CENTER);
			document.add(paragraphCoverPage);
			
			paragraphCoverPage = new Paragraph();
			paragraphCoverPage.add(new Chunk("เกรด :  ", new Font(baseFont, 16, Font.BOLD)));
			paragraphCoverPage.add(new Chunk(printFinalAuditResultPdfModel.getGrade(), new Font(baseFont, 16, Font.BOLD)));
			paragraphCoverPage.setAlignment(Element.ALIGN_CENTER);
			paragraphCoverPage.setSpacingAfter(80);
			document.add(paragraphCoverPage);
			
			paragraphCoverPage = new Paragraph();
			paragraphCoverPage.add(new Chunk("ผู้เข้าตรวจประเมิน/ผู้ติดตาม  ", new Font(baseFont, 16)));
			paragraphCoverPage.setAlignment(Element.ALIGN_CENTER);
			paragraphCoverPage.setSpacingAfter(10);
			document.add(paragraphCoverPage);
			
			int noAuditor = 1;
			for(String auditor : printFinalAuditResultPdfModel.getAuditorList()) {
				UserModel userAuditTmp = new Gson().fromJson(auditor.trim(), UserModel.class);
				
				paragraphCoverPage = new Paragraph();
				paragraphCoverPage.setTabSettings(new TabSettings(190f));
				paragraphCoverPage.add(Chunk.TABBING);
				paragraphCoverPage.add(new Chunk(noAuditor+". "+userAuditTmp.getFullname(), new Font(baseFont, 16)));
				paragraphCoverPage.setSpacingAfter(5);
				document.add(paragraphCoverPage);
				noAuditor += 1;
			}
			
			
			
			logger.info("Generate Cover Page Successfully");
			
			document.newPage();
			
			
			PdfPTable tableHeaderPdf = new PdfPTable(1);
			tableHeaderPdf.setTotalWidth(523);
			float[] columnTableHeaderWidths = {100};
			tableHeaderPdf.setWidths(columnTableHeaderWidths);
			Paragraph paragraphHeader = new Paragraph();
			paragraphHeader.add(new Chunk("แผนการเข้าตรวจเลขที่ :  "+printFinalAuditResultPdfModel.getChecklistPlanNo().trim(), new Font(baseFont, 12)));
			paragraphHeader.setAlignment(Element.PTABLE);
			PdfPCell columnHeader = new PdfPCell(paragraphHeader);
			columnHeader.setBorderColor(BaseColor.WHITE);
			columnHeader.setUseAscender(true);
			columnHeader.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			columnHeader.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			tableHeaderPdf.addCell(columnHeader);
			tableHeaderPdf.completeRow();
			
			PdfPTable tableFooter = new PdfPTable(2);
			tableFooter.setTotalWidth(523);
			float[] columnTableFooterWidths = {50, 50};
			tableFooter.setWidths(columnTableFooterWidths);
			
			Paragraph paragraphFooter = new Paragraph();	
			paragraphFooter.add(new Chunk("Print Date : ", new Font(baseFont, 12)));
			paragraphFooter.add(new Chunk(printFinalAuditResultPdfModel.getPrintDate(), new Font(baseFont, 12)));
			paragraphFooter.setAlignment(Element.PTABLE);
			PdfPCell columnFooter = new PdfPCell(paragraphFooter);
			columnFooter.setBorderColor(BaseColor.WHITE);
			columnFooter.setUseAscender(true);
			columnFooter.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			columnFooter.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
			tableFooter.addCell(columnFooter);
			
			paragraphFooter = new Paragraph();	
			paragraphFooter.add(new Chunk("ชื่อผู้พิมพ์ : ", new Font(baseFont, 12)));
			paragraphFooter.add(new Chunk(printFinalAuditResultPdfModel.getPrintName(), new Font(baseFont, 12)));
			paragraphFooter.setAlignment(Element.PTABLE);
			columnFooter = new PdfPCell(paragraphFooter);
			columnFooter.setBorderColor(BaseColor.WHITE);
			columnFooter.setUseAscender(true);
			columnFooter.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			columnFooter.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			
	        
	        tableFooter.addCell(columnFooter);
			tableFooter.completeRow();
			
			
			FooterTable event  = new FooterTable(tableHeaderPdf, tableFooter);
			pdfWriter.setPageEvent(null);
			pdfWriter.setPageEvent(event);
			
			
			
			
			
			//Paragraph paragraph = new Paragraph("แผนการเข้าตรวจเลขที่ :  "+printFinalAuditResultPdfModel.getChecklistPlanNo().trim(), new Font(baseFont));
			//paragraph.setAlignment(Element.ALIGN_RIGHT);			
			//document.add(paragraph);
			logger.info("Starting Generate Page Body Detail .......");
			Paragraph paragraph = new Paragraph("CPRAM CO., Ltd", new Font(baseFont, 18, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setSpacingAfter(0);
			document.add(paragraph);
			
			paragraph = new Paragraph("Quality Assurance Department", new Font(baseFont, 18, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_CENTER);	
			paragraph.setSpacingAfter(10);
			document.add(paragraph);
			
			PdfPTable tableHeader = new PdfPTable(2);
			tableHeader.setWidthPercentage(100);
			
			float[] columnWidths = {80, 20};
			tableHeader.setWidths(columnWidths);
			
			
			paragraph = new Paragraph();	
			paragraph.add(new Chunk("Supplier : ", new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getSupplierName().trim(), new Font(baseFont, 14)));
			paragraph.setAlignment(Element.PTABLE);
			PdfPCell column1 = new PdfPCell(paragraph);
			column1.setBorderColor(BaseColor.BLACK);
			column1.setUseAscender(true);
			column1.setExtraParagraphSpace(5);
			column1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			
			
			paragraph = new Paragraph();	
			paragraph.add(new Chunk("Date : ", new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getPlanDate(), new Font(baseFont, 14)));
			paragraph.setAlignment(Element.PTABLE);
			PdfPCell column2 = new PdfPCell(paragraph);
			column2.setBorderColor(BaseColor.BLACK);
			column2.setUseAscender(true);
			column2.setExtraParagraphSpace(5);
			column2.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			
			tableHeader.addCell(column1);
			tableHeader.addCell(column2);
			tableHeader.completeRow();
					
						
			column1 = new PdfPCell();
			column1.setFixedHeight(50f);
			paragraph = new Paragraph();
			
			paragraph.setTabSettings(new TabSettings(56f));
			paragraph.add(Chunk.TABBING);
			if(printFinalAuditResultPdfModel.getAuditType().equals("1")) {
				paragraph.add(new Chunk(String.valueOf(checkedSymbol), new Font(baseFontSymbol, 14)));
			}else {
				paragraph.add(new Chunk(String.valueOf(uncheckedSymbol), new Font(baseFontSymbol, 14)));
			}			
			paragraph.add(new Chunk("ตรวจประเมินประจำปี", new Font(baseFont, 14)));
			
			paragraph.setTabSettings(new TabSettings(100f));
			paragraph.add(Chunk.TABBING);			
			if(printFinalAuditResultPdfModel.getAuditType().equals("2")) {
				paragraph.add(new Chunk(String.valueOf(checkedSymbol), new Font(baseFontSymbol, 14)));
			}else {
				paragraph.add(new Chunk(String.valueOf(uncheckedSymbol), new Font(baseFontSymbol, 14)));
			}	
			paragraph.add(new Chunk("ตรวจประเมิน RM/Supplier รายใหม่", new Font(baseFont, 14)));
			
			paragraph.setTabSettings(new TabSettings(40f));
			paragraph.add(Chunk.TABBING);			
			if(printFinalAuditResultPdfModel.getAuditType().equals("3")) {
				paragraph.add(new Chunk(String.valueOf(checkedSymbol), new Font(baseFontSymbol, 14)));
			}else {
				paragraph.add(new Chunk(String.valueOf(uncheckedSymbol), new Font(baseFontSymbol, 14)));
			}	
			paragraph.add(new Chunk("ตรวจติดตามปัญหา", new Font(baseFont, 14)));
			
			paragraph.setAlignment(Element.PTABLE);
			column1.addElement(paragraph);
			column1.setColspan(2);
			column1.setBorderColor(BaseColor.BLACK);
			column1.setUseAscender(true);
			column1.setExtraParagraphSpace(5);
			column1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			tableHeader.addCell(column1);
			tableHeader.completeRow();
			
			paragraph = new Paragraph();
			paragraph.add(new Chunk("สถานที่ประเมิน : " , new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getSupplierLocation().trim(), new Font(baseFont, 14)));
			paragraph.setAlignment(Element.PTABLE);
			column1 = new PdfPCell(paragraph);
			column1.setColspan(2);
			column1.setBorderColor(BaseColor.BLACK);
			column1.setUseAscender(true);
			column1.setExtraParagraphSpace(5);
			column1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			tableHeader.addCell(column1);
			tableHeader.completeRow();			
			
			document.add(tableHeader);
			
			PdfPTable tableCarDetail = new PdfPTable(5);
			tableCarDetail.setWidthPercentage(100);
			tableCarDetail.setSpacingBefore(20);
			tableCarDetail.setSpacingAfter(15);
			float[] columnWidthTableCarDetail = {25,40,10,26,12};
			tableCarDetail.setWidths(columnWidthTableCarDetail);
			
			column1 = new PdfPCell(new Paragraph("ปัญหา (Problem)", new Font(baseFont, 14, Font.BOLD)));
			column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			column1.setExtraParagraphSpace(5);
			tableCarDetail.addCell(column1);
			
			column1 = new PdfPCell(new Paragraph("Criteria", new Font(baseFont, 14, Font.BOLD)));
			column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			column1.setExtraParagraphSpace(5);
			tableCarDetail.addCell(column1);
			
			column1 = new PdfPCell(new Paragraph("ระดับ", new Font(baseFont, 14, Font.BOLD)));
			column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			column1.setExtraParagraphSpace(5);
			tableCarDetail.addCell(column1);
			
			column1 = new PdfPCell(new Paragraph("Corrective Action", new Font(baseFont, 14, Font.BOLD)));
			column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			column1.setExtraParagraphSpace(5);
			tableCarDetail.addCell(column1);
			
			column1 = new PdfPCell(new Paragraph("Complete Date", new Font(baseFont, 14, Font.BOLD)));
			column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			column1.setExtraParagraphSpace(5);
			tableCarDetail.addCell(column1);
			
			/*column1 = new PdfPCell(new Paragraph("Due Date", new Font(baseFont, 14, Font.BOLD)));
			column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			column1.setExtraParagraphSpace(5);
			tableCarDetail.addCell(column1);*/
			
			tableCarDetail.setHeaderRows(1);
			tableCarDetail.completeRow();

			PdfPTable tableCarProblem = new PdfPTable(3);
			
			if(printFinalAuditResultPdfModel.getCarDetailModelList() == null || printFinalAuditResultPdfModel.getCarDetailModelList().isEmpty()) {
				paragraph = new Paragraph();
				paragraph.add(new Chunk("ไม่พบใบคาร์",new Font(baseFont, 14)));
				paragraph.setAlignment(Element.PTABLE);
				column1 = new PdfPCell(paragraph);
				column1.setColspan(5);
				column1.setUseAscender(true);
				column1.setExtraParagraphSpace(5);
				column1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
				column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
				tableCarDetail.addCell(column1);
				tableCarDetail.completeRow();		
			}else {
				
				tableCarProblem.setWidthPercentage(100);
				tableCarProblem.setSpacingBefore(20);
				tableCarProblem.setSpacingAfter(15);
				float[] columnWidthTableCarProblem = {5,5,5};
				tableCarProblem.setWidths(columnWidthTableCarProblem);
				
				column1 = new PdfPCell(new Paragraph("ปัญหา (Problem)", new Font(baseFont, 14, Font.BOLD)));
				column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
				column1.setExtraParagraphSpace(5);
				tableCarProblem.addCell(column1);
				
				column1 = new PdfPCell(new Paragraph("Criteria", new Font(baseFont, 14, Font.BOLD)));
				column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
				column1.setExtraParagraphSpace(5);
				tableCarProblem.addCell(column1);
				
				column1 = new PdfPCell(new Paragraph("หลักฐานการแก้ไข", new Font(baseFont, 14, Font.BOLD)));
				column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
				column1.setExtraParagraphSpace(5);
				tableCarProblem.addCell(column1);
				
				tableCarProblem.setHeaderRows(1);
				tableCarProblem.completeRow();
				
				boolean findProblem = false;
				
				for(CarDetailModel carDetailModel : printFinalAuditResultPdfModel.getCarDetailModelList()) {
					tableCarDetail.addCell(new Paragraph(carDetailModel.getDetail().trim(), new Font(baseFont, 14)));
									
					org.jsoup.nodes.Document docHtml = Jsoup.parse(carDetailModel.getAuditResultId().getEvalPlanId().getDetail().trim());				
					tableCarDetail.addCell(new Paragraph(docHtml.body().text(), new Font(baseFont, 14)));
					tableCarDetail.addCell(new Paragraph(carDetailModel.getAuditResultId().getAnswerDetail(), new Font(baseFont, 14)));
					
					EvidenceModel evidenceModel = new EvidenceModel();
					evidenceModel.setAuditResultId(carDetailModel.getAuditResultId());
					List<EvidenceModel> listEvidenceSolveCar = new Gson().fromJson(evidenceController.getEvidenceSolveCar(new Gson().toJson(evidenceModel)), new TypeToken<List<EvidenceModel>>(){}.getType()) ;
					if(listEvidenceSolveCar != null && !listEvidenceSolveCar.isEmpty()) {
						String corrective = "";
						for(EvidenceModel evidence : listEvidenceSolveCar) {
							if(evidence.getEvidenceTypeId().getEvidenceTypeId().equals("2")) {
								corrective += evidence.getData().trim();
							}
						}
						tableCarDetail.addCell(new Paragraph(corrective, new Font(baseFont, 14)));
					}else {
						tableCarDetail.addCell(new Paragraph("", new Font(baseFont, 14)));
					}				
					
					if(!StringUtils.isNullOrEmpty(carDetailModel.getCompleteDate())) {
						tableCarDetail.addCell(new Paragraph(carDetailModel.getCompleteDate().trim().split(" ")[0], new Font(baseFont, 14)));
					}else {
						tableCarDetail.addCell(new Paragraph("", new Font(baseFont, 14)));
					}
					
					tableCarDetail.completeRow();
					

					ArrayList<String> imageSup = new ArrayList<String>();
					ArrayList<String> imageAudit = new ArrayList<String>();
					boolean findPicture = false;
					for(EvidenceModel evidence : carDetailModel.getEvidenceId()) {
						if(evidence.getActionType().equals("S")) {
							if(evidence.getEvidenceTypeId().getEvidenceTypeId().equals("1")) 
							{
								imageSup.add(evidence.getData().trim());
								findPicture = true;
							}
						}else if(evidence.getActionType().equals("A")) {
							if(evidence.getEvidenceTypeId().getEvidenceTypeId().equals("1")) {
								imageAudit.add(evidence.getData().trim());
								findPicture = true;
							}
						}
					}

					if(findPicture) {
						findProblem = true;
						tableCarProblem.addCell(new Paragraph(carDetailModel.getDetail().trim(), new Font(baseFont, 14)));
						tableCarProblem.addCell(new Paragraph(docHtml.body().text(), new Font(baseFont, 14)));
						
						paragraph = new Paragraph();				
 						paragraph.setAlignment(Element.PTABLE);
 						if(imageSup.size() > 0) {
 							paragraph.add(new Chunk("รูปภาพ Supplier : ",new Font(baseFont, 14)));
 							for(String imageS : imageSup) {
 								try {
	 								Image imgSup = Image.getInstance(imageS);
//	 								Image imgSup = Image.getInstance("D:\\Project Gosoft\\FileCpram\\resources\\evidence_173_1.jpeg");
	 								imgSup.scaleToFit(170, 170);
	 			 					paragraph.add(new Chunk(imgSup, 0, 0, true));
	 		 						paragraph.add(new Chunk("\n",new Font(baseFont, 14)));
 								}catch (Exception e) {
 									logger.error("PdfUtils.errorFileNotFound() Exception : "+ExceptionUtils.stackTraceException(e));
 								}
 							}
 						}
						
 						if(imageAudit.size() > 0) {
 							paragraph.add(new Chunk("รูปภาพ Auditor : ",new Font(baseFont, 14)));
 							for(String imageA : imageAudit) {
 								try {
	 	 							Image imgAudit = Image.getInstance(imageA);
//รูป	 	 							Image imgAudit = Image.getInstance("D:\\Users\\thitipapre\\Desktop\\2.2.0V2\\data\\resources\\cpram_logo.png");
	 	 							imgAudit.scaleToFit(170, 170);
	 	 			 				paragraph.add(new Chunk(imgAudit, 0, 0, true));
	 	 		 					paragraph.add(new Chunk("\n",new Font(baseFont, 14)));
 								}catch (Exception e) {
 									logger.error("PdfUtils.errorFileNotFound() Exception : "+ExceptionUtils.stackTraceException(e));
 								}
 							}
 						}
						
 						column1 = new PdfPCell(paragraph);
 						column1.setColspan(1);
 						column1.setUseAscender(true);
 						column1.setExtraParagraphSpace(5);
 						column1.setVerticalAlignment(Rectangle.ALIGN_LEFT);
 						column1.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
 						tableCarProblem.addCell(column1);
					}
				}				
				
				if(!findProblem) {
					paragraph = new Paragraph();
					paragraph.add(new Chunk("ไม่พบหลักฐานการแก้ปัญหา",new Font(baseFont, 14)));
					paragraph.setAlignment(Element.PTABLE);
					column1 = new PdfPCell(paragraph);
					column1.setColspan(3);
					column1.setUseAscender(true);
					column1.setExtraParagraphSpace(3);
					column1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
					column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
					tableCarProblem.addCell(column1);
					tableCarProblem.completeRow();		
				}
				
			}		
			
			document.add(tableCarDetail);
			
			if(printFinalAuditResultPdfModel.getCarDetailModelList() != null && !printFinalAuditResultPdfModel.getCarDetailModelList().isEmpty()) {
				document.add(tableCarProblem);
			}
			
			paragraph = new Paragraph();	
			paragraph.setSpacingAfter(15);
			paragraph.setTabSettings(new TabSettings(10f));
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("สรุปผล ", new Font(baseFont, 14, Font.BOLD)));
			
			paragraph.setTabSettings(new TabSettings(70f));
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("Critical : ", new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getCritical(), new Font(baseFont, 14)));	
			
			paragraph.setTabSettings(new TabSettings(80f));
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("Major : ", new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getMajor(), new Font(baseFont, 14)));	
			
			paragraph.setTabSettings(new TabSettings(80f));
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("Minor : ", new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getMinor(), new Font(baseFont, 14)));	
			
			paragraph.setTabSettings(new TabSettings(80f));
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("Observe : ", new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getObserve(), new Font(baseFont, 14)));	
			
			document.add(paragraph);
			
			paragraph = new Paragraph();
			paragraph.setSpacingAfter(15);
			paragraph.setTabSettings(new TabSettings(80f));
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("ผลการตรวจประเมิน (Audit Result)", new Font(baseFont, 14, Font.BOLD)));
			
			paragraph.setTabSettings(new TabSettings(80f));
			paragraph.add(Chunk.TABBING);
			if(printFinalAuditResultPdfModel.getAuditResult().equals("Y")) {
				paragraph.add(new Chunk(String.valueOf(checkedSymbol), new Font(baseFontSymbol, 16)));
			}else {
				paragraph.add(new Chunk(String.valueOf(uncheckedSymbol), new Font(baseFontSymbol, 16)));
			}	
			paragraph.add(new Chunk("ผ่าน (Pass)", new Font(baseFont, 14)));
			
			//paragraph.setTabSettings(new TabSettings(80f));
			paragraph.add(Chunk.TABBING);
			if(printFinalAuditResultPdfModel.getAuditResult().equals("N")) {
				paragraph.add(new Chunk(String.valueOf(checkedSymbol), new Font(baseFontSymbol, 16)));
			}else {
				paragraph.add(new Chunk(String.valueOf(uncheckedSymbol), new Font(baseFontSymbol, 16)));
			}	
			paragraph.add(new Chunk("ไม่ผ่าน (Not Pass)", new Font(baseFont, 14)));
			
			document.add(paragraph);
			
			paragraph = new Paragraph();
			paragraph.setSpacingAfter(30);
			paragraph.setTabSettings(new TabSettings(80f));
			paragraph.add(Chunk.TABBING);			
			paragraph.add(new Chunk("เกรด : ", new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getGrade().trim(), new Font(baseFont, 14, Font.BOLD)));	
			
			document.add(paragraph);
			
			
			paragraph = new Paragraph();
			paragraph.setSpacingAfter(10);
			paragraph.add(new Chunk("ขอให้ทางผู้ส่งมอบตอบกลับการแก้ไข ภายใน "+printFinalAuditResultPdfModel.getAcceptDay()+" วันหลังการ Audit โดยแนบภาพปิดประเด็นและหลักฐาน ทาง Fax "+printFinalAuditResultPdfModel.getFax()+" หรือ "+printFinalAuditResultPdfModel.getEmail(), new Font(baseFont, 14)));
			document.add(paragraph);
			
			paragraph = new Paragraph();
			paragraph.setTabSettings(new TabSettings(50f));
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("Auditor : ",new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getAuditor(), new Font(baseFont, 14 )));
			
			paragraph.add(Chunk.TABBING);
			paragraph.add(Chunk.TABBING);
			paragraph.add(Chunk.TABBING);
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("วันที่ปิดคำขอแก้ไข : ",new Font(baseFont, 14, Font.BOLD)));
			if(printFinalAuditResultPdfModel.getCompleteDate() != null) {
				paragraph.add(new Chunk(printFinalAuditResultPdfModel.getCompleteDate(), new Font(baseFont, 14 )));
			}
			
			document.add(paragraph);
			
			
			paragraph = new Paragraph();
			paragraph.setTabSettings(new TabSettings(50f));
			paragraph.add(Chunk.TABBING);
			BASE64Decoder decoder = new BASE64Decoder();
            byte[] imageByte = decoder.decodeBuffer(printFinalAuditResultPdfModel.getSignatureOfSupplier().trim());
			paragraph.add(new Chunk("Supplier : ",new Font(baseFont, 14, Font.BOLD)));
			Image image = Image.getInstance(imageByte);
			image.scaleAbsolute(100f, 50f);
			paragraph.add(new Chunk(image, 0, -30));
			
			paragraph.add(Chunk.TABBING);
			paragraph.add(Chunk.TABBING);
			paragraph.add(Chunk.TABBING);
			paragraph.add(Chunk.TABBING);
			paragraph.add(new Chunk("ชื่อผู้ตรวจสอบ : ",new Font(baseFont, 14, Font.BOLD)));
			paragraph.add(new Chunk(printFinalAuditResultPdfModel.getApprovalName(), new Font(baseFont, 14 )));
			//paragraph.add(new Chunk(printFinalAuditResultPdfModel.getAuditor(), new Font(baseFont, 14 )));
			
			logger.info("Generate Page Body Detail Successfully");
			document.add(paragraph);
				
			
//			pdf page checklsit_plan
			document.newPage();
			
			tableHeaderPdf = new PdfPTable(1);
			tableHeaderPdf.setTotalWidth(523);
			tableHeaderPdf.setWidths(columnTableHeaderWidths);
			paragraphHeader = new Paragraph();
			paragraphHeader.add(new Chunk("แผนการเข้าตรวจเลขที่ :  "+printFinalAuditResultPdfModel.getChecklistPlanNo().trim(), new Font(baseFont, 12)));
			paragraphHeader.setAlignment(Element.PTABLE);
			columnHeader = new PdfPCell(paragraphHeader);
			columnHeader.setBorderColor(BaseColor.WHITE);
			columnHeader.setUseAscender(true);
			columnHeader.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			columnHeader.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			tableHeaderPdf.addCell(columnHeader);
			tableHeaderPdf.completeRow();
			
			tableFooter = new PdfPTable(2);
			tableFooter.setTotalWidth(523);
			tableFooter.setWidths(columnTableFooterWidths);
			
			paragraphFooter = new Paragraph();	
			paragraphFooter.add(new Chunk("Print Date : ", new Font(baseFont, 12)));
			paragraphFooter.add(new Chunk(printFinalAuditResultPdfModel.getPrintDate(), new Font(baseFont, 12)));
			paragraphFooter.setAlignment(Element.PTABLE);
			columnFooter = new PdfPCell(paragraphFooter);
			columnFooter.setBorderColor(BaseColor.WHITE);
			columnFooter.setUseAscender(true);
			columnFooter.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			columnFooter.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
			tableFooter.addCell(columnFooter);
			
			paragraphFooter = new Paragraph();	
			paragraphFooter.add(new Chunk("ชื่อผู้พิมพ์ : ", new Font(baseFont, 12)));
			paragraphFooter.add(new Chunk(printFinalAuditResultPdfModel.getPrintName(), new Font(baseFont, 12)));
			paragraphFooter.setAlignment(Element.PTABLE);
			columnFooter = new PdfPCell(paragraphFooter);
			columnFooter.setBorderColor(BaseColor.WHITE);
			columnFooter.setUseAscender(true);
			columnFooter.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			columnFooter.setHorizontalAlignment(Rectangle.ALIGN_RIGHT);
			
	        
	        tableFooter.addCell(columnFooter);
			tableFooter.completeRow();
			
			
			event  = new FooterTable(tableHeaderPdf, tableFooter);
			pdfWriter.setPageEvent(null);
			pdfWriter.setPageEvent(event);
			
			logger.info("Starting Generate Page Check List Plan Detail .......");
			paragraph = new Paragraph("ตารางสรุปแผนการดำเนินงาน", new Font(baseFont, 18, Font.BOLD));
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setSpacingAfter(0);
			document.add(paragraph);
			
			PdfPTable tableChecklistDetail = new PdfPTable(4);
			tableChecklistDetail.setWidthPercentage(100);
			tableChecklistDetail.setSpacingBefore(20);
			tableChecklistDetail.setSpacingAfter(15);
			float[] columnWidthTableChecklistDetail = {6,2,3,3};
			tableChecklistDetail.setWidths(columnWidthTableChecklistDetail);
			
			paragraph = new Paragraph();				
			paragraph.setAlignment(Element.PTABLE);
			paragraph.add(new Chunk("ตารางสรุปแผนการดำเนินงาน",new Font(baseFont, 14, Font.BOLD)));
			column1 = new PdfPCell(paragraph);
			column1.setColspan(4);
			column1.setUseAscender(true);
			column1.setExtraParagraphSpace(5);
			column1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
			column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
			tableChecklistDetail.addCell(column1);
			tableChecklistDetail.completeRow();		
			
			if(printFinalAuditResultPdfModel.getEvalPlanModelList() == null || printFinalAuditResultPdfModel.getEvalPlanModelList().isEmpty()) {
				paragraph = new Paragraph();
				paragraph.add(new Chunk("ไม่พบเช็คลิส",new Font(baseFont, 14)));
				paragraph.setAlignment(Element.PTABLE);
				column1 = new PdfPCell(paragraph);
				column1.setColspan(4);
				column1.setUseAscender(true);
				column1.setExtraParagraphSpace(5);
				column1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
				column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
				tableChecklistDetail.addCell(column1);
				tableChecklistDetail.completeRow();		
			}else {
				int indexEvident = 0;
				for(int i = 0 ; i < printFinalAuditResultPdfModel.getEvalPlanModelList().size() ; i++) {
					EvalPlanModel evalPlanObj = printFinalAuditResultPdfModel.getEvalPlanModelList().get(i);
					String evalTypeId = evalPlanObj.getEvalTypeId().getEvalTypeId();
					
					if(i != 0 && evalTypeId.equals("5") && !printFinalAuditResultPdfModel.getEvalPlanModelList().get(i-1).getEvalTypeId().getEvalTypeId().equals("5")) {
						column1 = new PdfPCell(new Paragraph("ข้อตรวจ", new Font(baseFont, 14)));
						column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
						column1.setExtraParagraphSpace(5);
						tableChecklistDetail.addCell(column1);
						
						column1 = new PdfPCell(new Paragraph("ผลการตรวจ", new Font(baseFont, 14)));
						column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
						column1.setExtraParagraphSpace(5);
						tableChecklistDetail.addCell(column1);
						
						column1 = new PdfPCell(new Paragraph("note", new Font(baseFont, 14)));
						column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
						column1.setExtraParagraphSpace(5);
						tableChecklistDetail.addCell(column1);
						
						column1 = new PdfPCell(new Paragraph("หลักฐาน", new Font(baseFont, 14)));
						column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
						column1.setExtraParagraphSpace(5);
						tableChecklistDetail.addCell(column1);
						
						tableChecklistDetail.setHeaderRows(1);
						tableChecklistDetail.completeRow();
					}
					
					
					String spacebar = "";
 					if(!evalTypeId.equals("2")){
						if(evalPlanObj.getEvalTypeName().equals("sub_topic")) {
							spacebar += "\u00a0\u00a0\u00a0";
						}else if(evalPlanObj.getEvalTypeName().equals("question_group")) {
							spacebar += "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0";
						}else if(evalPlanObj.getEvalTypeName().equals("question")) {
							spacebar += "\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0\u00a0";
						}
					}
 					String detail = "";
					if(!evalTypeId.equals("5")){
						detail += evalPlanObj.getTitle()+" : "+evalPlanObj.getDetail();
					}else {
						detail += evalPlanObj.getDetail();
					}
					org.jsoup.nodes.Document docHtml = Jsoup.parse(detail);
					detail = spacebar+docHtml.body().text();
 					if(evalTypeId.equals("2") || evalTypeId.equals("3") || evalTypeId.equals("4")){
 						paragraph = new Paragraph();				
 						paragraph.setAlignment(Element.PTABLE);
 						paragraph.add(new Chunk(detail,new Font(baseFont, 14, Font.BOLD)));
 						column1 = new PdfPCell(paragraph);
 						column1.setColspan(4);
 						column1.setUseAscender(true);
 						column1.setExtraParagraphSpace(5);
 						column1.setVerticalAlignment(Rectangle.ALIGN_LEFT);
 						column1.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
 						tableChecklistDetail.addCell(column1);
 						tableChecklistDetail.completeRow();		
 					}else {
 						paragraph = new Paragraph();				
 						paragraph.setAlignment(Element.PTABLE);
 						paragraph.add(new Chunk(detail,new Font(baseFont, 14)));
 						column1 = new PdfPCell(paragraph);
 						column1.setColspan(1);
 						column1.setUseAscender(true);
 						column1.setExtraParagraphSpace(5);
 						column1.setVerticalAlignment(Rectangle.ALIGN_LEFT);
 						column1.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
 						tableChecklistDetail.addCell(column1);
 						
 						String answerName = "";
 						String note = "";
 						if(evalPlanObj.getAuditResultId() != null && evalPlanObj.getAuditResultId().size() > 0) {
 	 						for (AuditResultModel auditResult : evalPlanObj.getAuditResultId()) {
 	 							answerName += auditResult.getAnswerDetail();
 	 							note += auditResult.getNote();
 	 						}
 						}
 						paragraph = new Paragraph();				
 						paragraph.setAlignment(Element.PTABLE);
 						paragraph.add(new Chunk(answerName,new Font(baseFont, 14)));
 						column1 = new PdfPCell(paragraph);
 						column1.setColspan(1);
 						column1.setUseAscender(true);
 						column1.setExtraParagraphSpace(5);
 						column1.setVerticalAlignment(Rectangle.ALIGN_MIDDLE);
 						column1.setHorizontalAlignment(Rectangle.ALIGN_CENTER);
 						tableChecklistDetail.addCell(column1);

 						paragraph = new Paragraph();				
 						paragraph.setAlignment(Element.PTABLE);
 						paragraph.add(new Chunk(note,new Font(baseFont, 14)));
 						column1 = new PdfPCell(paragraph);
 						column1.setColspan(1);
 						column1.setUseAscender(true);
 						column1.setExtraParagraphSpace(5);
 						column1.setVerticalAlignment(Rectangle.ALIGN_LEFT);
 						column1.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
 						tableChecklistDetail.addCell(column1);
 						
 						
 						if(printFinalAuditResultPdfModel.getCarDetailModelList() != null || !printFinalAuditResultPdfModel.getCarDetailModelList().isEmpty()) {
 							if(indexEvident < printFinalAuditResultPdfModel.getCarDetailModelList().size()) {
 	 							CarDetailModel carDetailModel = printFinalAuditResultPdfModel.getCarDetailModelList().get(indexEvident);
 	 							if(carDetailModel.getAuditResultId().getEvalPlanId().getEvalPlanId().equals(evalPlanObj.getEvalPlanId())) {
 	 								System.out.println("In EvalPlanId " +evalPlanObj.getEvalPlanId());
 	 								indexEvident++;
	 								String textSup = "" , textAudit = "";
	 								ArrayList<String> imageSup = new ArrayList<String>();
	 								ArrayList<String> imageAudit = new ArrayList<String>();
 	 								for(EvidenceModel evidence : carDetailModel.getEvidenceId()) {
 	 									if(evidence.getActionType().equals("S")) {
 		 									if(evidence.getEvidenceTypeId().getEvidenceTypeId().equals("2")) {
 		 										textSup += evidence.getData().trim();
 		 									}else if(evidence.getEvidenceTypeId().getEvidenceTypeId().equals("1")) {
 		 										imageSup.add(evidence.getData().trim());
 		 									}
 	 									}else if(evidence.getActionType().equals("A")) {
 	 		 								if(evidence.getEvidenceTypeId().getEvidenceTypeId().equals("2")) {
 	 		 									textAudit += evidence.getData().trim();
 	 		 								}else if(evidence.getEvidenceTypeId().getEvidenceTypeId().equals("1")) {
 	 		 									imageAudit.add(evidence.getData().trim());
 	 		 								}
 	 	 								}
	 								}

									paragraph = new Paragraph();
									if(carDetailModel.getEvidenceId().size() > 0) {
	 	 								if(!StringUtils.isNullOrEmpty(textSup) || imageSup.size() > 0) {
	 	 									if(!StringUtils.isNullOrEmpty(textSup)) {
	 	 										textSup = "ข้อความ Supplier : "+textSup;
	 	 									}else {
		 	 									textSup = "ข้อความ Supplier : -";
	 	 									}
					 						paragraph.setAlignment(Element.PTABLE);
					 						paragraph.add(new Chunk(textSup+"\n",new Font(baseFont, 14)));
					 						for(String imageS : imageSup) {
					 							try {
													Image imgSup = Image.getInstance(imageS);
//													Image imgSup = Image.getInstance("D:\\Project Gosoft\\FileCpram\\resources\\evidence_173_1.jpeg");
													imgSup.scaleToFit(105, 105);
							 						paragraph.add(new Chunk(imgSup, 0, 0, true));
					 							}catch (Exception e) {
				 									logger.error("PdfUtils.errorFileNotFound() Exception : "+ExceptionUtils.stackTraceException(e));
				 								}
	 	 									}
		 								}if(!StringUtils.isNullOrEmpty(textAudit) || imageAudit.size() > 0) {
		 									if(!StringUtils.isNullOrEmpty(textSup) || imageAudit.size() > 0) {
		 										paragraph.add(new Chunk("\n",new Font(baseFont, 14)));
		 									}
	 	 									if(!StringUtils.isNullOrEmpty(textAudit)) {
	 	 										textSup = "ข้อความ Auditor : "+textAudit;
	 	 									}else {
		 	 									textSup = "ข้อความ Auditor : -";
	 	 									}
					 						paragraph.setAlignment(Element.PTABLE);
					 						paragraph.add(new Chunk(textAudit,new Font(baseFont, 14)));
					 						for(String imageA : imageAudit) {
					 							try {
													Image imgAudit = Image.getInstance(imageA);
//													Image imgAudit = Image.getInstance("D:\\Project Gosoft\\FileCpram\\resources\\evidence_173_1.jpeg");
													imgAudit.scaleToFit(105, 105);
							 						paragraph.add(new Chunk(imgAudit, 0, 0, true));
					 							}catch (Exception e) {
				 									logger.error("PdfUtils.errorFileNotFound() Exception : "+ExceptionUtils.stackTraceException(e));
				 								}
	 	 									}
		 								}
		 								column1 = new PdfPCell(paragraph);
				 						column1.setColspan(1);
				 						column1.setUseAscender(true);
				 						column1.setExtraParagraphSpace(5);
				 						column1.setVerticalAlignment(Rectangle.ALIGN_LEFT);
				 						column1.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
				 						tableChecklistDetail.addCell(column1);
									}
 	 							}
 							}	
 						}	
 						
 						tableChecklistDetail.completeRow();	
 					}
				}				
				
			}		
			
			document.add(tableChecklistDetail);
			
			
			return file;
			
		}catch (Exception e) {
			logger.error("PdfUtils.createFinalAuditResultPdf() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(document != null) {
				document.close();
			}
			if(pdfWriter != null) {
				pdfWriter.close();
			}			
		}
	}
	
	private static void setupPropertiesPdf(Document doc, String title, String subject) {
		doc.addAuthor("Audit Supplier Online Author");
		doc.addCreationDate();
		doc.addCreator("Audit Supplier Online Creator");
		doc.addTitle(title.trim());
		doc.addSubject(subject.trim());
	}
	
	
	
}

class FooterTable extends PdfPageEventHelper {
    protected PdfPTable footer;
    protected PdfPTable header;
    
    public FooterTable(PdfPTable header, PdfPTable footer) {
        this.footer = footer;
        this.header = header;
    }
    public void onEndPage(PdfWriter writer, Document document) {
    	
        //header.setTotalWidth(527);
        header.setLockedWidth(true);
        header.getDefaultCell().setFixedHeight(20);

        //BaseFont baseFont = BaseFont.createFont(ConfigurationSystemManager.getInstance().getPathFileReportPdfFont().trim(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        
        
        // add text
        //PdfPCell text = new PdfPCell();
        //text.setPaddingBottom(15);
        //text.setPaddingLeft(10);
        //text.setBorder(Rectangle.BOTTOM);
        //text.setBorderColor(BaseColor.LIGHT_GRAY);
        //text.addElement(new Phrase("iText PDF Header Footer Example", new Font(Font.FontFamily.HELVETICA, 12)));
        //text.addElement(new Phrase("https://memorynotfound.com", new Font(Font.FontFamily.HELVETICA, 8)));
        //header.addCell(text);

        // write content
        header.writeSelectedRows(0, -1, 34, 803, writer.getDirectContent());
    	
        footer.writeSelectedRows(0, -1, 36, 64, writer.getDirectContent());
        //footer.writeSelectedRows(0, -1, 36, 80, writer.getDirectContent());
    }
    
}