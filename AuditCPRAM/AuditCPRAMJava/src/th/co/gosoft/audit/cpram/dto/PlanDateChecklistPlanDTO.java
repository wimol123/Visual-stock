package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;

public class PlanDateChecklistPlanDTO {
	
	private int appointId;
	private Date planDate;
	private Time planTime;
	public int getAppointId() {
		return appointId;
	}
	public void setAppointId(int appointId) {
		this.appointId = appointId;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public Time getPlanTime() {
		return planTime;
	}
	public void setPlanTime(Time planTime) {
		this.planTime = planTime;
	}
	
}
