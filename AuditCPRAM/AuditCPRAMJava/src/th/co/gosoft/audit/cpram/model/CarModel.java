package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class CarModel extends StandardAttributeModel {
	private String carId;
	private String carNo;
	private ChecklistPlanModel checklistPlanId; 
	private String dueDate;
	private CarStatusModel carStatusId;
	private List<CarDetailModel> carDetail;
	public String getCarId() {
		return carId;
	}
	public String getCarNo() {
		return carNo;
	}
	public ChecklistPlanModel getChecklistPlanId() {
		return checklistPlanId;
	}
	public String getDueDate() {
		return dueDate;
	}
	public CarStatusModel getCarStatusId() {
		return carStatusId;
	}
	public void setCarId(String carId) {
		this.carId = carId;
	}
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	public void setChecklistPlanId(ChecklistPlanModel checklistPlanId) {
		this.checklistPlanId = checklistPlanId;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public void setCarStatusId(CarStatusModel carStatusId) {
		this.carStatusId = carStatusId;
	}
	public List<CarDetailModel> getCarDetail() {
		return carDetail;
	}
	public void setCarDetail(List<CarDetailModel> carDetail) {
		this.carDetail = carDetail;
	}
	
	
}
