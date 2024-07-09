package th.co.gosoft.audit.cpram.dto;

import java.util.List;

public class ProductTypeDTO extends StandardAttributeDTO {
	private int productTypeId;
	private String name;	
	private ChecklistDTO checklistId;
	private List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTO;
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ChecklistDTO getChecklistId() {
		return checklistId;
	}
	public void setChecklistId(ChecklistDTO checklistId) {
		this.checklistId = checklistId;
	}
	public List<SupplierProductAddressMappingDTO> getSupplierProductAddressMappingDTO() {
		return supplierProductAddressMappingDTO;
	}
	public void setSupplierProductAddressMappingDTO(
			List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTO) {
		this.supplierProductAddressMappingDTO = supplierProductAddressMappingDTO;
	}
	
	
	
}
