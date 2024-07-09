package th.co.gosoft.audit.cpram.model;

import java.util.List;

public class SupplierModel extends StandardAttributeModel{
	private String supplierId;
	private String supplierCode;
	private String supplierCompany;
	private String isGreenCard;
	private String greenCardExpireDate;
	private String approval;
	private String auditRound;
	private String logo;
	private List<UserModel> userId;
	private AddressModel addressId;
	private SupplierProductAddressMappingModel supplierProductAddressMappingModel;
	
	public SupplierProductAddressMappingModel getSupplierProductAddressMappingModel() {
		return supplierProductAddressMappingModel;
	}
	public void setSupplierProductAddressMappingModel(
			SupplierProductAddressMappingModel supplierProductAddressMappingModel) {
		this.supplierProductAddressMappingModel = supplierProductAddressMappingModel;
	}
	public String getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierCompany() {
		return supplierCompany;
	}
	public void setSupplierCompany(String supplierCompany) {
		this.supplierCompany = supplierCompany;
	}
	public String getIsGreenCard() {
		return isGreenCard;
	}
	public void setIsGreenCard(String isGreenCard) {
		this.isGreenCard = isGreenCard;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<UserModel> getUserId() {
		return userId;
	}
	public void setUserId(List<UserModel> userId) {
		this.userId = userId;
	}
	public AddressModel getAddressId() {
		return addressId;
	}
	public void setAddressId(AddressModel addressId) {
		this.addressId = addressId;
	}
	public String getApproval() {
		return approval;
	}
	public void setApproval(String approval) {
		this.approval = approval;
	}
	public String getGreenCardExpireDate() {
		return greenCardExpireDate;
	}
	public void setGreenCardExpireDate(String greenCardExpireDate) {
		this.greenCardExpireDate = greenCardExpireDate;
	}
	public String getAuditRound() {
		return auditRound;
	}
	public void setAuditRound(String auditRound) {
		this.auditRound = auditRound;
	}
	
	
	
}
