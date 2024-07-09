package th.co.gosoft.audit.cpram.fileupload;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.utils.DateUtils;

public class FileUploadConst {
	//public static final String PATH_Temp = "D:\\AuditCPRam\\Temp\\";
	//public static final String PATH_Supplier_Logo = "D:\\AuditCPRam\\Supplier\\Logo\\";
	public static final String PATH_Temp = ConfigurationSystemManager.getInstance().getPathTemp();
	public static final String PATH_Supplier_Logo = ConfigurationSystemManager.getInstance().getPathSupplierLogo();
	public static final String PATH_Supplier_Standard_Document = ConfigurationSystemManager.getInstance().getPathFilePathSupplierStandardDocument();
	public static final String PATH_Supplier_Questionaire_Document = ConfigurationSystemManager.getInstance().getPathFilePathSupplierQuestionaireDocument();
	public static final String Logo_CPRAM_Context = ConfigurationSystemManager.getInstance().getPathLogoCPRAM();
	public static final String PATH_Logo_CPRAM_For_Email = ConfigurationSystemManager.getInstance().getPathLogoCPRAM();
	public static final String PATH_UPLOAD_EVIDENCE = ConfigurationSystemManager.getInstance().getMobileServicePathFileUpload();	
	public static final String PATH_Manual_Document = ConfigurationSystemManager.getInstance().getPathFilePathManualDocument();
	public static final String PATH_Information_Document = ConfigurationSystemManager.getInstance().getPathFilePathInformationDocument();
	
	public static String genPathFileLogoImage(String fileName) {
		if(!fileName.equals("")) {
			// Format FileName => logo_yyyyMMdd_HHmmss.*
			try {
				
				Path path = new File(fileName).toPath();
				String mineType = Files.probeContentType(path);
				
				String[] mineTypeSplit = mineType.split("/");
				if(!mineTypeSplit[0].trim().equals( "image")) {
					return "";
				}
				String dateFormat = DateUtils.getCurrentDateTime("yyyyMMdd_HHmmssSSS");
				String newFileName = String.format("logo_%s.%s", dateFormat,mineTypeSplit[1].trim());
				
				return newFileName;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
