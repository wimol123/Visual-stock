package th.co.gosoft.audit.cpram.model;

public class SubDistrictModel {
	
	private String subDistrictId;
	private String name;
	private DistrictModel districtId;
	private ProvinceModel provinceId;
	private RegionModel regionId; 
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	public String getSubDistrictId() {
		return subDistrictId;
	}
	public void setSubDistrictId(String subDistrictId) {
		this.subDistrictId = subDistrictId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCreateBy() {
		return createBy;
	}
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateBy() {
		return updateBy;
	}
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public DistrictModel getDistrictId() {
		return districtId;
	}
	public void setDistrictId(DistrictModel districtId) {
		this.districtId = districtId;
	}
	public ProvinceModel getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(ProvinceModel provinceId) {
		this.provinceId = provinceId;
	}
	public RegionModel getRegionId() {
		return regionId;
	}
	public void setRegionId(RegionModel regionId) {
		this.regionId = regionId;
	}
}
