package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class SupplierDTO extends StandardAttributeDTO {
	
	private int supplierId;
	private String supplierCode;
	private String supplierCompany;
	private Character isGreenCard;
	private Date greenCardExpireDate;
	private Time greenCardExpireTime;
	private String logo;
	private Character approval;	
	private int auditRound;
	private List<UserDTO> userId;
	private AddressDTO addressId;
	private SupplierProductAddressMappingDTO supplierProductAddressMappingDTO;
	public int getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(int supplierId) {
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
	public Character getIsGreenCard() {
		return isGreenCard;
	}
	public void setIsGreenCard(Character isGreenCard) {
		this.isGreenCard = isGreenCard;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public List<UserDTO> getUserId() {
		return userId;
	}
	public void setUserId(List<UserDTO> userId) {
		this.userId = userId;
	}
	public AddressDTO getAddressId() {
		return addressId;
	}
	public void setAddressId(AddressDTO addressId) {
		this.addressId = addressId;
	}
	public Character getApproval() {
		return approval;
	}
	public void setApproval(Character approval) {
		this.approval = approval;
	}
	public SupplierProductAddressMappingDTO getSupplierProductAddressMappingDTO() {
		return supplierProductAddressMappingDTO;
	}
	public void setSupplierProductAddressMappingDTO(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO) {
		this.supplierProductAddressMappingDTO = supplierProductAddressMappingDTO;
	}
	public Date getGreenCardExpireDate() {
		return greenCardExpireDate;
	}
	public void setGreenCardExpireDate(Date greenCardExpireDate) {
		this.greenCardExpireDate = greenCardExpireDate;
	}
	public Time getGreenCardExpireTime() {
		return greenCardExpireTime;
	}
	public void setGreenCardExpireTime(Time greenCardExpireTime) {
		this.greenCardExpireTime = greenCardExpireTime;
	}
	public int getAuditRound() {
		return auditRound;
	}
	public void setAuditRound(int auditRound) {
		this.auditRound = auditRound;
	}
	
}
