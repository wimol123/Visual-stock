package th.co.gosoft.audit.cpram.dto;

public class AddressDTO extends StandardAttributeDTO {
	private int addressId;
	private String postcode;
	private String address;
	private SubDistrictDTO subDistrictId;
	private DistrictDTO districtId;	
	private ProvinceDTO provinceId;	
	private SupplierDTO supplierId;
	private SupplierProductAddressMappingDTO supplierProductAddressMappingDTO;
	
	public int getAddressId() {
		return addressId;
	}
	public void setAddressId(int addressId) {
		this.addressId = addressId;
	}
	public SupplierDTO getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(SupplierDTO supplierId) {
		this.supplierId = supplierId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SubDistrictDTO getSubDistrictId() {
		return subDistrictId;
	}
	public void setSubDistrictId(SubDistrictDTO subDistrictId) {
		this.subDistrictId = subDistrictId;
	}
	public DistrictDTO getDistrictId() {
		return districtId;
	}
	public void setDistrictId(DistrictDTO districtId) {
		this.districtId = districtId;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public ProvinceDTO getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(ProvinceDTO provinceId) {
		this.provinceId = provinceId;
	}
	public SupplierProductAddressMappingDTO getSupplierProductAddressMappingDTO() {
		return supplierProductAddressMappingDTO;
	}
	public void setSupplierProductAddressMappingDTO(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO) {
		this.supplierProductAddressMappingDTO = supplierProductAddressMappingDTO;
	}
}
