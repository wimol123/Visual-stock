package th.co.gosoft.audit.cpram.utils;


import com.mysql.jdbc.StringUtils;


public class NullUtils {

    public static String cvStr(Object value) {
        if (value == null) {
            return "";
        }
         
        return value.toString().trim();
    }

    public static Character cvChar(Object value) {    	
    	if(value == null || StringUtils.isNullOrEmpty(value.toString())) {
    		return Character.MIN_VALUE;
    	}
    	
    	String castString = cvStr(value);
    	return castString.charAt(0);
    	
    }
    
    public static String cvStrSearch(Object value, Object key) {
        String val="";
        if (value == null) {
             if (key == null) {
                val= "";
             }else{
                val= key.toString();
             }
        }else{
            val= value.toString();
        }
        return val;
    }

    public static String cvStr(Object value, String default_val) {
        if (value == null) {
            return default_val;
        }
        return value.toString();
    }

    public static int cvInt(Object value) {
        try {
            return Integer.valueOf((String) value.toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public static int cvInt(Object value, int default_val) {
        try {
            return Integer.valueOf((String) value.toString());
        } catch (Exception ex) {
            return default_val;
        }
    }

    public static float cvFloat(Object value) {
        try {
            if (value == null) {
                return 0;
            }
            return Float.parseFloat((String) value.toString());
        } catch (Exception ex) {
            return 0;
        }
    }

    public static float cvFloat(Object value, float default_val) {
        try {
            if (value == null) {
                return default_val;
            }
            return Float.parseFloat((String) value.toString());
        } catch (Exception ex) {
            return 0;
        }
    }
    
   
	
}
