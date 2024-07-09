package th.co.gosoft.audit.cpram.fileupload;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MultivaluedMap;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

public class FileUpload {
	
	private final MultipartFormDataInput inputFormData;
	
	public FileUpload(MultipartFormDataInput _inputFormData) {
		this.inputFormData = _inputFormData;
	}

	public MultivaluedMap<String, String> getHeader(InputPart inputPart) {
		return inputPart.getHeaders();
	}
	
	public List<InputPart> getFormDataMap(String key) {
		if(!key.equals("")) {
			return inputFormData.getFormDataMap().get(key);
		}
		return null;
	}
	
	public Map<String, List<InputPart>> getFormDataMap() {
		return inputFormData.getFormDataMap();
	}
	
	public String getFileName(MultivaluedMap<String, String> header) {
		String[] contentDisposition = header.getFirst("Content-Disposition").split(";");		
		for (String filename : contentDisposition) {
			
			if ((filename.trim().startsWith("filename"))) {

				String[] name = filename.split("=");
				
				String finalFileName = name[1].trim().replaceAll("\"", "");
				return finalFileName;
			}
		}
		return "unknown";
	}
	
	public String getContentDisposition(MultivaluedMap<String, String> header,String stringContentDisposition) {
		if(!stringContentDisposition.equals("") || stringContentDisposition != "") {
			String[] contentDispositions = header.getFirst("Content-Disposition").split(";");
			for(String contentDisposition : contentDispositions) {
				
				if(contentDisposition.trim().startsWith(stringContentDisposition)) {
					
					String[] name = contentDisposition.split("=");
					
					String value = name[1].trim().replaceAll("\"", "");
					return value;
				}
			}
		}
		return "unknown";
	}
	
	public InputStream getBody(InputPart inputPart) throws Exception {
		return inputPart.getBody(InputStream.class,null);
	}
	
}
