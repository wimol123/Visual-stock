package th.co.gosoft.audit.cpram.dto;

import java.util.List;

public class SupplierProductAddressMappingDTO extends StandardAttributeDTO {
	private int id;
	private SupplierDTO supplierId;
	private List<ProductTypeDTO> productTypeId;
	private AddressDTO addressId;
	private String locationName;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public SupplierDTO getSupplierId() {
		return supplierId;
	}
	public void setSupplierId(SupplierDTO supplierId) {
		this.supplierId = supplierId;
	}
	public AddressDTO getAddressId() {
		return addressId;
	}
	public void setAddressId(AddressDTO addressId) {
		this.addressId = addressId;
	}
	public List<ProductTypeDTO> getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(List<ProductTypeDTO> productTypeId) {
		this.productTypeId = productTypeId;
	}
	
}
