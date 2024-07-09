package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;

public class FinalAuditResultDTO extends StandardAttributeDTO {
	private String checklistPlanNo; 
	private SupplierDTO supplierId; 
	private String supplierData; 
	private int auditType;
	private Date planDate;
	private Time planTime;
	private ProductTypeDTO productTypeId; 
	private String productTypeName;
	private SupplierProductAddressMappingDTO locationId;
	private String supplierProductAddressMappingData; 
	private ChecklistPlanDTO checklistPlanId;
	private CarDTO carId;
	private String carDetailData; 
	private String conclude;
	private Character grade;
	private Character pass;
	private String auditor;
	private byte[] signatureOfSupplier; 
	private Date completeDate;
	private Time completeTime;
	private String approvalName;
	private FinalAuditResultStatusDTO finalAuditResultStatusId;
	
	public String getChecklistPlanNo() {
		return checklistPlanNo;
	}
	public SupplierDTO getSupplierId() {
		return supplierId;
	}
	public String getSupplierData() {
		return supplierData;
	}
	public int getAuditType() {
		return auditType;
	}
	public Date getPlanDate() {
		return planDate;
	}
	public Time getPlanTime() {
		return planTime;
	}
	public ProductTypeDTO getProductTypeId() {
		return productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public SupplierProductAddressMappingDTO getLocationId() {
		return locationId;
	}
	public String getSupplierProductAddressMappingData() {
		return supplierProductAddressMappingData;
	}
	public ChecklistPlanDTO getChecklistPlanId() {
		return checklistPlanId;
	}
	public String getCarDetailData() {
		return carDetailData;
	}
	public String getConclude() {
		return conclude;
	}
	public Character getGrade() {
		return grade;
	}
	public Character getPass() {
		return pass;
	}
	public String getAuditor() {
		return auditor;
	}
	public Date getCompleteDate() {
		return completeDate;
	}
	public Time getCompleteTime() {
		return completeTime;
	}
	public String getApprovalName() {
		return approvalName;
	}
	public FinalAuditResultStatusDTO getFinalAuditResultStatusId() {
		return finalAuditResultStatusId;
	}
	public void setChecklistPlanNo(String checklistPlanNo) {
		this.checklistPlanNo = checklistPlanNo;
	}
	public void setSupplierId(SupplierDTO supplierId) {
		this.supplierId = supplierId;
	}
	public void setSupplierData(String supplierData) {
		this.supplierData = supplierData;
	}
	public void setAuditType(int auditType) {
		this.auditType = auditType;
	}
	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}
	public void setPlanTime(Time planTime) {
		this.planTime = planTime;
	}
	public void setProductTypeId(ProductTypeDTO productTypeId) {
		this.productTypeId = productTypeId;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public void setLocationId(SupplierProductAddressMappingDTO locationId) {
		this.locationId = locationId;
	}
	public void setSupplierProductAddressMappingData(String supplierProductAddressMappingData) {
		this.supplierProductAddressMappingData = supplierProductAddressMappingData;
	}
	public void setChecklistPlanId(ChecklistPlanDTO checklistPlanId) {
		this.checklistPlanId = checklistPlanId;
	}
	public void setCarDetailData(String carDetailData) {
		this.carDetailData = carDetailData;
	}
	public void setConclude(String conclude) {
		this.conclude = conclude;
	}
	public void setGrade(Character grade) {
		this.grade = grade;
	}
	public void setPass(Character pass) {
		this.pass = pass;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}
	public void setCompleteTime(Time completeTime) {
		this.completeTime = completeTime;
	}
	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}
	public void setFinalAuditResultStatusId(FinalAuditResultStatusDTO finalAuditResultStatusId) {
		this.finalAuditResultStatusId = finalAuditResultStatusId;
	}
	public CarDTO getCarId() {
		return carId;
	}
	public void setCarId(CarDTO carId) {
		this.carId = carId;
	}
	public byte[] getSignatureOfSupplier() {
		return signatureOfSupplier;
	}
	public void setSignatureOfSupplier(byte[] signatureOfSupplier) {
		this.signatureOfSupplier = signatureOfSupplier;
	}
	
	
}
