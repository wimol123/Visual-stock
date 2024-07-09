package th.co.gosoft.audit.cpram.controller;

import java.io.InputStream;

import javax.servlet.ServletRequest;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;

public class ResourceFilterController {
	private final static Logger logger = Logger.getLogger(ResourceFilterController.class);
	private ServletRequest _servletRequest;
	
	public ResourceFilterController(ServletRequest servletRequest) {
		this._servletRequest = servletRequest;
	}
	
	public void searchLogoForEmail() {
		try {

			FileProcessing writeFile = new FileProcessing();
			if(!writeFile.checkFileExists(FileUploadConst.PATH_Logo_CPRAM_For_Email)) {
				InputStream input = _servletRequest.getServletContext().getResourceAsStream(FileUploadConst.Logo_CPRAM_Context);
				writeFile.InputStreamToFile(input, FileUploadConst.PATH_Logo_CPRAM_For_Email);
			}	
			
		}catch(Exception e) {
			logger.error("ResourceFilterController.searchLogoForEmail() Exception : "+ExceptionUtils.stackTraceException(e));
		}
	}
}
