package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;

public class StandardAttributeDTO {
	private Character enable;
	private int createBy; 
	private Date createDate;
	private Time createTime;
	private int updateBy;
	private Date updateDate;
	private Time updateTime;
	public Character getEnable() {
		return enable;
	}
	public void setEnable(Character enable) {
		this.enable = enable;
	}
	public int getCreateBy() {
		return createBy;
	}
	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Time getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Time createTime) {
		this.createTime = createTime;
	}
	public int getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Time getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Time updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
