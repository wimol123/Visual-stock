package th.co.gosoft.audit.cpram.dto;

public class VisualStockDTO {
	@Override
	public String toString() {
		return "PoDTO [poId=" + poId + ", poNo=" + poNo + ", supplierCode=" + supplierCode + ", supplierName="
				+ supplierName + ", poCreateDate=" + poCreateDate + ", poCreateByEmail=" + poCreateByEmail
				+ ", isMailSent=" + isMailSent + ", isViewed=" + isViewed + ", isPrinted=" + isPrinted + ", poStatusId="
				+ poStatusId + ", poAcceptedId=" + poAcceptedId + ", createBy=" + createBy + ", createDate="
				+ createDate + ", updateBy=" + updateBy + ", updateDate=" + updateDate + ", supplier=" + supplier
				+ ", poStatusName=" + poStatusName + ", poAcceptedName=" + poAcceptedName + ", poCreateBy=" + poCreateBy
				+ ", createDateStart=" + createDateStart + ", createDateEnd=" + createDateEnd
				+ ", countSupplierAccepted=" + countSupplierAccepted + ", poIdList=" + poIdList + ", mailSupplierList="
				+ mailSupplierList + ", mailPurchasingList=" + mailPurchasingList + ", supplierUserId=" + supplierUserId
				+ ", notPoStatusId=" + notPoStatusId + "]";
	}

	/* Field Map DB */
	private int poId;
	private String poNo;
	private String supplierCode;
	private String supplierName;
	private String poCreateDate;
	private String poCreateByEmail;
	private String isMailSent;
	private String isViewed;
	private String isPrinted;
	private int poStatusId;
	private int poAcceptedId;
	private int createBy;
	private String createDate;
	private int updateBy;
	private String updateDate;

	/* Field Other */
	private String supplier;
	private String poStatusName;
	private String poAcceptedName;
	private String poCreateBy;
	private String createDateStart;
	private String createDateEnd;
	private int countSupplierAccepted;
	private String poIdList;
	private String mailSupplierList;
	private String mailPurchasingList;
	private String supplierUserId;
	private int notPoStatusId;

	public int getPoId() {
		return poId;
	}

	public void setPoId(int poId) {
		this.poId = poId;
	}

	public String getPoNo() {
		return poNo;
	}

	public void setPoNo(String poNo) {
		this.poNo = poNo;
	}

	public String getSupplierCode() {
		return supplierCode;
	}

	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPoCreateDate() {
		return poCreateDate;
	}

	public void setPoCreateDate(String poCreateDate) {
		this.poCreateDate = poCreateDate;
	}

	public String getPoCreateByEmail() {
		return poCreateByEmail;
	}

	public void setPoCreateByEmail(String poCreateByEmail) {
		this.poCreateByEmail = poCreateByEmail;
	}

	public String getIsMailSent() {
		return isMailSent;
	}

	public void setIsMailSent(String isMailSent) {
		this.isMailSent = isMailSent;
	}

	public String getIsViewed() {
		return isViewed;
	}

	public void setIsViewed(String isViewed) {
		this.isViewed = isViewed;
	}

	public String getIsPrinted() {
		return isPrinted;
	}

	public void setIsPrinted(String isPrinted) {
		this.isPrinted = isPrinted;
	}

	public int getPoStatusId() {
		return poStatusId;
	}

	public void setPoStatusId(int poStatusId) {
		this.poStatusId = poStatusId;
	}

	public int getPoAcceptedId() {
		return poAcceptedId;
	}

	public void setPoAcceptedId(int poAcceptedId) {
		this.poAcceptedId = poAcceptedId;
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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getPoStatusName() {
		return poStatusName;
	}

	public void setPoStatusName(String poStatusName) {
		this.poStatusName = poStatusName;
	}

	public String getPoAcceptedName() {
		return poAcceptedName;
	}

	public void setPoAcceptedName(String poAcceptedName) {
		this.poAcceptedName = poAcceptedName;
	}

	public String getPoCreateBy() {
		return poCreateBy;
	}

	public void setPoCreateBy(String poCreateBy) {
		this.poCreateBy = poCreateBy;
	}

	public String getCreateDateStart() {
		return createDateStart;
	}

	public void setCreateDateStart(String createDateStart) {
		this.createDateStart = createDateStart;
	}

	public String getCreateDateEnd() {
		return createDateEnd;
	}

	public void setCreateDateEnd(String createDateEnd) {
		this.createDateEnd = createDateEnd;
	}

	public int getCountSupplierAccepted() {
		return countSupplierAccepted;
	}

	public void setCountSupplierAccepted(int countSupplierAccepted) {
		this.countSupplierAccepted = countSupplierAccepted;
	}

	public String getPoIdList() {
		return poIdList;
	}

	public void setPoIdList(String poIdList) {
		this.poIdList = poIdList;
	}

	public String getMailSupplierList() {
		return mailSupplierList;
	}

	public void setMailSupplierList(String mailSupplierList) {
		this.mailSupplierList = mailSupplierList;
	}

	public String getMailPurchasingList() {
		return mailPurchasingList;
	}

	public void setMailPurchasingList(String mailPurchasingList) {
		this.mailPurchasingList = mailPurchasingList;
	}

	public String getSupplierUserId() {
		return supplierUserId;
	}

	public void setSupplierUserId(String supplierUserId) {
		this.supplierUserId = supplierUserId;
	}

	public int getNotPoStatusId() {
		return notPoStatusId;
	}

	public void setNotPoStatusId(int notPoStatusId) {
		this.notPoStatusId = notPoStatusId;
	}

}
