package th.co.gosoft.audit.cpram.utils;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.mysql.jdbc.StringUtils;

public class DateUtils {

	public static final DateFormat DD_MM_YYYY_FORMAT = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
	public static final DateFormat YYYY_MM_DD_FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
	public static final SimpleDateFormat HH_mm_ss_FORMAT = new SimpleDateFormat("HH:mm:ss", Locale.US);
	
    public static String getCurrentDate(String format) {
        if (format.equals("")) {
            return "";
        }
        DateFormat dateFormat = new SimpleDateFormat(format,Locale.US);
    	Calendar cal = Calendar.getInstance();
        return dateFormat.format(cal.getTime());
    }
    
    public static String getCurrentDateTime() {
    	Date _date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.US);
		return dateFormat.format(_date);
    }
    
    public static String getCurrentDateTime(String format) {
    	Date _date = new Date();
    	SimpleDateFormat dateFormat = new SimpleDateFormat(format,Locale.US);
    	return dateFormat.format(_date);
    }

	public static java.sql.Date parseWebDateStringToSQLDate(String webDate){
		if(webDate == null){
			return null;
		}else if(StringUtils.isNullOrEmpty(webDate)){			
			return null;
		}
		String sqlDateString = formatWebDateToSQLDateString(webDate);
		return java.sql.Date.valueOf(sqlDateString);
	}
	
		
	public static java.sql.Time parseWebTimeStringToSQLTime(String webTime){
		if(webTime == null)
			return null;
		else if(StringUtils.isNullOrEmpty(webTime)) {
			return null;
		}
		 SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss",Locale.US);
		 //Time timeParse;
		 Date date;
		try {
			date = format.parse(webTime);
			//timeParse = (Time) format.parse(webTime);			
			return new Time(date.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		 
	}

	public static String formatWebDateToSQLDateString(String webDate){
		Date date;
		try {
			if(webDate == null){
				return null;
			}			
			date = DD_MM_YYYY_FORMAT.parse(webDate);	
			return YYYY_MM_DD_FORMAT.format(date); //Return To Format yyyy-MM-dd
		} catch (ParseException e) {
			//logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
		}
	}

	public static String  parseSQLDateStringToWebDate(java.sql.Date webDate){
		if(webDate == null){
			return "";
		}
		return DD_MM_YYYY_FORMAT.format(webDate);
	}
	public static String  parseSQLTimeStringToWebTime(java.sql.Time webTime){
		if(webTime == null){
			return "";
		}
		
		return HH_mm_ss_FORMAT.format(webTime);
	}
	
}
