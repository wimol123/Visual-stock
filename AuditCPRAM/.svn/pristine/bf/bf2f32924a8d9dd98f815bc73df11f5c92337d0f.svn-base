package th.co.gosoft.audit.cpram.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtils {

	public static String stackTraceException(Exception e) {
		String exceptionString = null;
    	StringWriter stringWriter = null;
    	PrintWriter printWriter = null;
    	try {
    		stringWriter = new StringWriter();
    		printWriter = new PrintWriter(stringWriter);
    		e.printStackTrace(printWriter);
    		exceptionString = stringWriter.toString();
    	}catch (Exception ex) {
    		exceptionString = null;
		}finally {
			if(printWriter != null) {
				printWriter.close();
			}
			
			if(stringWriter != null) {
				try {
					stringWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
    	return exceptionString;
	}
	
}
