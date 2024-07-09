package th.co.gosoft.audit.cpram.controller;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import th.co.gosoft.audit.cpram.fileupload.FileUpload;
import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;

public class FileUploadController {
	
	private final static Logger logger = Logger.getLogger(FileUploadController.class);
	
	public boolean uploadPicture(MultipartFormDataInput multipartFormDataInput) {
		FileUpload fileUpload = null;
		FileProcessing fileProcessing = null;
		boolean resultWrite = true;
		String fileName = null;
		int chunk = 0;
		long size = 0;
		try {
			
			fileUpload = new FileUpload(multipartFormDataInput);
			fileProcessing = new FileProcessing();
			List<InputPart> pictureParts = fileUpload.getFormDataMap("picture_elements");
			for(InputPart inputPart : pictureParts) {
				MultivaluedMap<String, String> header = fileUpload.getHeader(inputPart);
//				fileName = fileUpload.getFileName(header);
				fileName = URLDecoder.decode(fileUpload.getFileName(header), StandardCharsets.UTF_8.name()) ;
				
				InputStream logoInputStream = fileUpload.getBody(inputPart);
				InputStream logoInputStreamToRead = fileUpload.getBody(inputPart);
				String pathFile = FileUploadConst.PATH_Temp.concat(fileName);
				
				byte[] buffer = new byte[1024];
				while((chunk = logoInputStreamToRead.read(buffer)) != -1) {
					size += chunk;
				}
				
				if(size > 300000) {
					BufferedImage img = ImageIO.read(logoInputStream);
					Image scaledImage = img.getScaledInstance(280, 280, Image.SCALE_SMOOTH);
			        BufferedImage imageBuff = new BufferedImage(280, 280, BufferedImage.TYPE_INT_RGB);
			        imageBuff.getGraphics().drawImage(scaledImage, 0, 0, new Color(0,0,0), null);
			        
			        resultWrite = ImageIO.write(imageBuff, "png", new File(pathFile.trim()));
				}else {
					resultWrite = fileProcessing.InputStreamToFile(logoInputStream, pathFile);
				}
				
				
				if (!resultWrite)
					break;
			}
			return resultWrite;
		}catch(Exception e) {
			logger.error("FileUploadController.uploadPicture() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
	public boolean uploadFile(MultipartFormDataInput multipartFormDataInput) {
		FileUpload fileUpload = null;
		FileProcessing fileProcessing = null;
		String fileName = null;
		 
		try {
			fileUpload = new FileUpload(multipartFormDataInput);
			fileProcessing = new FileProcessing();
			List<InputPart> fileParts = fileUpload.getFormDataMap("file_elements");
			
			for(InputPart inputPart : fileParts) {
				MultivaluedMap<String, String> header = fileUpload.getHeader(inputPart);
				
				fileName = URLDecoder.decode(fileUpload.getFileName(header), StandardCharsets.UTF_8.name()) ;
				final File file = new File(fileName);
				String fileType = null;
				fileType = Files.probeContentType(file.toPath());
				InputStream logoInputStream = fileUpload.getBody(inputPart);
				String pathFile = FileUploadConst.PATH_Temp.concat(fileName);
				fileProcessing.InputStreamToFile(logoInputStream, pathFile);
				
			}
			return true;
		}catch(Exception e) {
			logger.error("FileUploadController.uploadFile() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}
	}
	
}
