package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class SystemLogDTO extends StandardAttributeDTO{
	private String access;
	private String activity;
	private String detail;
	private String refId;
	private String note;
	private String createByName;
	public String getAccess() {
		return access;
	}
	public void setAccess(String access) {
		this.access = access;
	}
	public String getActivity() {
		return activity;
	}
	public void setActivity(String activity) {
		this.activity = activity;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getRefId() {
		return refId;
	}
	public void setRefId(String refId) {
		this.refId = refId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCreateByName() {
		return createByName;
	}
	public void setCreateByName(String createByName) {
		this.createByName = createByName;
	}	
	
}
