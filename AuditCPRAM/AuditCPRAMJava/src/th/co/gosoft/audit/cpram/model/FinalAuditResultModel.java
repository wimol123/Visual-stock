package th.co.gosoft.audit.cpram.model;

public class FinalAuditResultModel extends StandardAttributeModel{
	private String checklistPlanNo; 
	private SupplierModel supplierId; 
	private String supplierData; 
	private String auditType;
	private String planDate;
	private ProductTypeModel productTypeId; 
	private String productTypeName;
	private SupplierProductAddressMappingModel locationId;
	private String supplierProductAddressMappingData; 
	private ChecklistPlanModel checklistPlanId;
	private CarModel carId;
	private String carDetailData;
	private String reason;
	private String conclude;
	private String grade;
	private String pass;
	private String auditor;
	private String signatureOfSupplier; 
	private String completeDate;
	private String approvalName;
	private FinalAuditResultStatusModel finalAuditResultStatusId;
	public String getReason() {
		return reason;
	}
	public String getChecklistPlanNo() {
		return checklistPlanNo;
	}
	public SupplierModel getSupplierId() {
		return supplierId;
	}
	public String getSupplierData() {
		return supplierData;
	}
	public String getAuditType() {
		return auditType;
	}
	public String getPlanDate() {
		return planDate;
	}
	public ProductTypeModel getProductTypeId() {
		return productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public SupplierProductAddressMappingModel getLocationId() {
		return locationId;
	}
	public String getSupplierProductAddressMappingData() {
		return supplierProductAddressMappingData;
	}
	public ChecklistPlanModel getChecklistPlanId() {
		return checklistPlanId;
	}
	public String getCarDetailData() {
		return carDetailData;
	}
	public String getConclude() {
		return conclude;
	}
	public String getGrade() {
		return grade;
	}
	public String getPass() {
		return pass;
	}
	public String getAuditor() {
		return auditor;
	}
	public String getSignatureOfSupplier() {
		return signatureOfSupplier;
	}
	public String getCompleteDate() {
		return completeDate;
	}
	public String getApprovalName() {
		return approvalName;
	}
	public FinalAuditResultStatusModel getFinalAuditResultStatusId() {
		return finalAuditResultStatusId;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setChecklistPlanNo(String checklistPlanNo) {
		this.checklistPlanNo = checklistPlanNo;
	}
	public void setSupplierId(SupplierModel supplierId) {
		this.supplierId = supplierId;
	}
	public void setSupplierData(String supplierData) {
		this.supplierData = supplierData;
	}
	public void setAuditType(String auditType) {
		this.auditType = auditType;
	}
	public void setPlanDate(String planDate) {
		this.planDate = planDate;
	}
	public void setProductTypeId(ProductTypeModel productTypeId) {
		this.productTypeId = productTypeId;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}
	public void setLocationId(SupplierProductAddressMappingModel locationId) {
		this.locationId = locationId;
	}
	public void setSupplierProductAddressMappingData(String supplierProductAddressMappingData) {
		this.supplierProductAddressMappingData = supplierProductAddressMappingData;
	}
	public void setChecklistPlanId(ChecklistPlanModel checklistPlanId) {
		this.checklistPlanId = checklistPlanId;
	}
	public void setCarDetailData(String carDetailData) {
		this.carDetailData = carDetailData;
	}
	public void setConclude(String conclude) {
		this.conclude = conclude;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}
	public void setSignatureOfSupplier(String signatureOfSupplier) {
		this.signatureOfSupplier = signatureOfSupplier;
	}
	public void setCompleteDate(String completeDate) {
		this.completeDate = completeDate;
	}
	public void setApprovalName(String approvalName) {
		this.approvalName = approvalName;
	}
	public void setFinalAuditResultStatusId(FinalAuditResultStatusModel finalAuditResultStatusId) {
		this.finalAuditResultStatusId = finalAuditResultStatusId;
	}
	public CarModel getCarId() {
		return carId;
	}
	public void setCarId(CarModel carId) {
		this.carId = carId;
	}
		
}
