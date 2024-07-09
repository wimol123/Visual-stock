package th.co.gosoft.audit.cpram.model.datatable.parameter;

public class Search {
	private String value;
	private String regex;
    
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		String resultStr = value;
		
		try {
			
			if(value.matches(".*\\\\.*"))
			{
				resultStr= value.replaceAll("\\\\", "\\\\\\\\");
			}
			
			if(resultStr.matches(".*\'.*"))
			{				
				resultStr = resultStr.replaceAll("'", "\\\\\'");
			}
			
    	}catch (Exception e) {
    		this.value = "";
		}
		
		this.value = resultStr;
	}
	public String getRegex() {
		return regex;
	}
	public void setRegex(String regex) {
		this.regex = regex;
	}    
    
}
