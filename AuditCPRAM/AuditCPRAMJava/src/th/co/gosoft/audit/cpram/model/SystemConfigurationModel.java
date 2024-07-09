package th.co.gosoft.audit.cpram.model;

public class SystemConfigurationModel extends StandardAttributeModel{
	private String systemKey; 
	private String systemValue;
	public String getSystemKey() {
		return systemKey;
	}
	public void setSystemKey(String systemKey) {
		this.systemKey = systemKey;
	}
	public String getSystemValue() {
		return systemValue;
	}
	public void setSystemValue(String systemValue) {
		this.systemValue = systemValue;
	}
}
