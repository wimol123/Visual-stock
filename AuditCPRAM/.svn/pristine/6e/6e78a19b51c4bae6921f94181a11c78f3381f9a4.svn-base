package th.co.gosoft.audit.cpram.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;


public class FileProcessing {
	
	private final static Logger logger = Logger.getLogger(FileProcessing.class);	
	
	
	public boolean InputStreamToFile(InputStream inputStream, String pathToWriteFile) throws IOException {
		try {
			File file = new File(pathToWriteFile);
			if(!checkFileExists(pathToWriteFile)) {
				FileUtils.copyInputStreamToFile(inputStream, file);	
			}else {
				logger.warn(String.format("File => %s => Is Exists", pathToWriteFile));
			}
			return true;
		}catch(Exception ex) {			
			logger.error(ExceptionUtils.stackTraceException(ex));
			return false;
		}
	}
	
	public boolean CopyFileToDirectory(String sourcePath, String destinationPath) {
		
		try {
			File sourceFile = new File(sourcePath);
			File destinationFile = new File(destinationPath);
			if(checkFileExists(sourcePath)) {				
				FileUtils.copyFileToDirectory(sourceFile, destinationFile);
				return true;
			}else {
				logger.warn(String.format("File => %s => Is Not Exists", sourcePath));
				return false;				
			}
		}catch(Exception ex) {			
			logger.error(ex.getMessage(), ex);
			return false;
		}
	}
	
	public boolean CopyFile(String sourcePath, String destinationPath) {
		
		try {
			File sourceFile = new File(sourcePath);
			File destinationFile = new File(destinationPath);
			if(checkFileExists(sourcePath)) {	
				if(checkFileExists(destinationPath)) {
					logger.warn(String.format("File => %s => Is Exists", destinationPath));
					return false;
				}else {
					FileUtils.copyFile(sourceFile, destinationFile);					
					return true;
				}
				
			}else {
				logger.warn(String.format("File => %s => Is Not Exists", sourcePath));
				return false;
			}
		}catch(Exception ex) {			
			logger.error(ex.getMessage(), ex);
			return false;
		}
	}
	
	public boolean DeleteFile(String sourcePath) {
		try {
			File sourceFile = new File(sourcePath);
			FileUtils.touch(sourceFile);
			if(checkFileExists(sourcePath)) {
				Files.delete(sourceFile.toPath());
				//FileUtils.forceDelete(sourceFile);
				//return FileUtils.deleteQuietly(sourceFile);
				return true;
			}	
			return true;
		}catch(Exception ex) {			
			logger.error(ex.getMessage(), ex);
			return false;
		}
	}
	
	public boolean checkFileExists(String path) {
		File file = new File(path);		
		return file.exists();
	}

}
