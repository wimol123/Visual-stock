package th.co.gosoft.audit.cpram.controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;

public class DateTimeController {
	private final static Logger logger = Logger.getLogger(DateTimeController.class);
	
	public String getCurrentDateTimeUsingFormat(String format) {
		Gson gson = null;
		String dateTimeCurrent = "";
		try {
			gson = new Gson();
			if(StringUtils.isNullOrEmpty(format)) {
				//dateTimeCurrent = DateUtils.getCurrentDateTime("yyyy/MM/dd HH:mm:ss");	
				dateTimeCurrent = DateUtils.getCurrentDateTime("dd/MM/yyyy HH:mm:ss");
			}else {
				format = URLDecoder.decode(format, StandardCharsets.UTF_8.name());
				dateTimeCurrent = DateUtils.getCurrentDateTime(format.trim());	
			}
					
			Map<String, Object> objReturn = new HashMap<>();
			objReturn.put("currentDateTime", dateTimeCurrent);
			return gson.toJson(objReturn);			
		}catch (Exception e) {
			logger.error("DateTimeController.getCurrentDateTimeUsingFormat() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}
	}	
	
	
}
