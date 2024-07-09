package th.co.gosoft.audit.cpram.model;

public class AddressModel extends StandardAttributeModel {
	private String addressId;
	private String address;
	private String postcode;
	private SubDistrictModel subDistrictId;
	private DistrictModel districtId;	
	private ProvinceModel provinceId;		
	private SupplierModel supplierId;
	private SupplierProductAddressMappingModel supplierProductAddressMappingModel;
		
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public SubDistrictModel getSubDistrictId() {
		return subDistrictId;
	}
	public void setSubDistrictId(SubDistrictModel subDistrictId) {
		this.subDistrictId = subDistrictId;
	}
	public DistrictModel getDistrictId() {
		return districtId;
	}
	public void setDistrictId(DistrictModel districtId) {
		this.districtId = districtId;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public ProvinceModel getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(ProvinceModel provinceId) {
		this.provinceId = provinceId;
	}
	public SupplierModel getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(SupplierModel supplierId) {
		this.supplierId = supplierId;
	}
	public SupplierProductAddressMappingModel getSupplierProductAddressMappingModel() {
		return supplierProductAddressMappingModel;
	}
	public void setSupplierProductAddressMappingModel(
			SupplierProductAddressMappingModel supplierProductAddressMappingModel) {
		this.supplierProductAddressMappingModel = supplierProductAddressMappingModel;
	}
	
	
}
