package th.co.gosoft.audit.cpram.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import th.co.gosoft.audit.cpram.conts.ConstSystemProperties;

public class PropertiesUtils {

	private final static Logger logger = Logger.getLogger(PropertiesUtils.class);
	private static final Properties CONFIG_PROPERTIES = initialInstance();
	
	private static Properties initialInstance(){
        try{
            Properties prop = new Properties();
            InputStream input = null;
            //String configFilePath = System.getProperty("auditcpram.config.properties.path");
            String configFilePath = getValuePropertiesByKey(ConstSystemProperties.CONFIGURATION_PATH);
            if(configFilePath != null){
            	logger.info("Found system property : "+ConstSystemProperties.CONFIGURATION_PATH);
            	File fileConfig = new File(configFilePath);
            	if(fileConfig.isDirectory()) {
            		input = new FileInputStream(configFilePath+File.separator+"config.properties");
            	}else {
            		input = new FileInputStream(configFilePath);
            	}
            	
            } else {
            	logger.info("Not found system property : "+ConstSystemProperties.CONFIGURATION_PATH);
            	ClassLoader classLoader = PropertiesUtils.class.getClassLoader();
            	input = classLoader.getResourceAsStream("config.properties");
            }
            if(input != null){
            	logger.info("config.properties is loading");
                prop.load(input);
            }
            return prop;
        } catch (Exception e){
        	logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    public static String getProperties(String keyName){
        String value = System.getenv(keyName);
        if(value == null || "".equals(value)){
            value = CONFIG_PROPERTIES.getProperty(keyName);
        } 
        return value;
    }
    
    public static String getValuePropertiesByKey(String key) {
    	try {
    		return System.getProperty(key);
    	}catch (Exception e){
        	logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    public static void main(String[] args) {
		System.out.println("Properties : "+PropertiesUtils.getProperties("upload.excel.path"));
	}
}
