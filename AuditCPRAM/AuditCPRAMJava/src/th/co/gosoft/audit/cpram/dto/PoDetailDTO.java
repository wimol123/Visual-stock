package th.co.gosoft.audit.cpram.dto;

public class PoDetailDTO {
	/* Field Map DB */
	private int poDetailId;
	private int poId;
	private String item;
	private String materialCode;
	private String materialName;
	private String deliveryDate;
	private String netPrice;
	private String quantity;
	private String unit;
	private String netValue;
	private String prCreateDate;
	private String plannedDelivTime;
	private String purchaseReq;
	private String suppAccepted1;
	private String suppNote1;
	private String purResponse1;
	private String suppAccepted2;
	private String suppNote2;
	private String purResponse2;
	private String suppAccepted3;
	private String suppNote3;
	private int createBy;
	private String createDate;
	private int updateBy;
	private String updateDate;
	
	/* Field Other */
	private int count;

	public int getPoDetailId() {
		return poDetailId;
	}

	public void setPoDetailId(int poDetailId) {
		this.poDetailId = poDetailId;
	}

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getMaterialCode() {
		return materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getNetPrice() {
		return netPrice;
	}

	public void setNetPrice(String netPrice) {
		this.netPrice = netPrice;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNetValue() {
		return netValue;
	}

	public void setNetValue(String netValue) {
		this.netValue = netValue;
	}

	public String getPrCreateDate() {
		return prCreateDate;
	}

	public void setPrCreateDate(String prCreateDate) {
		this.prCreateDate = prCreateDate;
	}

	public String getPlannedDelivTime() {
		return plannedDelivTime;
	}

	public void setPlannedDelivTime(String plannedDelivTime) {
		this.plannedDelivTime = plannedDelivTime;
	}

	public String getPurchaseReq() {
		return purchaseReq;
	}

	public void setPurchaseReq(String purchaseReq) {
		this.purchaseReq = purchaseReq;
	}

	public String getSuppAccepted1() {
		return suppAccepted1;
	}

	public void setSuppAccepted1(String suppAccepted1) {
		this.suppAccepted1 = suppAccepted1;
	}

	public String getSuppNote1() {
		return suppNote1;
	}

	public void setSuppNote1(String suppNote1) {
		this.suppNote1 = suppNote1;
	}

	public String getPurResponse1() {
		return purResponse1;
	}

	public void setPurResponse1(String purResponse1) {
		this.purResponse1 = purResponse1;
	}

	public String getSuppAccepted2() {
		return suppAccepted2;
	}

	public void setSuppAccepted2(String suppAccepted2) {
		this.suppAccepted2 = suppAccepted2;
	}

	public String getSuppNote2() {
		return suppNote2;
	}

	public void setSuppNote2(String suppNote2) {
		this.suppNote2 = suppNote2;
	}

	public String getPurResponse2() {
		return purResponse2;
	}

	public void setPurResponse2(String purResponse2) {
		this.purResponse2 = purResponse2;
	}

	public String getSuppAccepted3() {
		return suppAccepted3;
	}

	public void setSuppAccepted3(String suppAccepted3) {
		this.suppAccepted3 = suppAccepted3;
	}

	public String getSuppNote3() {
		return suppNote3;
	}

	public void setSuppNote3(String suppNote3) {
		this.suppNote3 = suppNote3;
	}

	public int getCreateBy() {
		return createBy;
	}

	public void setCreateBy(int createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(int updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "PoDetailDTO [poDetailId=" + poDetailId + ", poId=" + poId + ", item=" + item + ", materialCode="
				+ materialCode + ", materialName=" + materialName + ", deliveryDate=" + deliveryDate + ", netPrice="
				+ netPrice + ", quantity=" + quantity + ", unit=" + unit + ", netValue=" + netValue + ", prCreateDate="
				+ prCreateDate + ", plannedDelivTime=" + plannedDelivTime + ", purchaseReq=" + purchaseReq
				+ ", suppAccepted1=" + suppAccepted1 + ", suppNote1=" + suppNote1 + ", purResponse1=" + purResponse1
				+ ", suppAccepted2=" + suppAccepted2 + ", suppNote2=" + suppNote2 + ", purResponse2=" + purResponse2
				+ ", suppAccepted3=" + suppAccepted3 + ", suppNote3=" + suppNote3 + ", createBy=" + createBy
				+ ", createDate=" + createDate + ", updateBy=" + updateBy + ", updateDate=" + updateDate + ", count="
				+ count + "]";
	}


}
