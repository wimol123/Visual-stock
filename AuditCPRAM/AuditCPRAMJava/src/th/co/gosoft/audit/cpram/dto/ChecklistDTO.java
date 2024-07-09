package th.co.gosoft.audit.cpram.dto;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

public class ChecklistDTO extends StandardAttributeDTO{
	private int checklistId;
	private String checklistTitle; 
	private ChecklistTypeDTO checklistTypeId; 
	private ProductTypeDTO productTypeId; 
	private String checklistScope;
	private String scoringCriteria;
	private String approveSupplierRule;
	private String description;
	private int noOfCarAcceptDay;
	private Date effectiveDate;
	private Time effectiveTime; 
	private Date expireDate; 
	private Time expireTime;	
	private List<EvalFormDTO> evalFormId;
	public int getChecklistId() {
		return checklistId;
	}
	public void setChecklistId(int checklistId) {
		this.checklistId = checklistId;
	}
	public String getChecklistTitle() {
		return checklistTitle;
	}
	public void setChecklistTitle(String checklistTitle) {
		this.checklistTitle = checklistTitle;
	}
	public ChecklistTypeDTO getChecklistTypeId() {
		return checklistTypeId;
	}
	public void setChecklistTypeId(ChecklistTypeDTO checklistTypeId) {
		this.checklistTypeId = checklistTypeId;
	}
	public ProductTypeDTO getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(ProductTypeDTO productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getChecklistScope() {
		return checklistScope;
	}
	public void setChecklistScope(String checklistScope) {
		this.checklistScope = checklistScope;
	}
	public String getScoringCriteria() {
		return scoringCriteria;
	}
	public void setScoringCriteria(String scoringCriteria) {
		this.scoringCriteria = scoringCriteria;
	}
	public String getApproveSupplierRule() {
		return approveSupplierRule;
	}
	public void setApproveSupplierRule(String approveSupplierRule) {
		this.approveSupplierRule = approveSupplierRule;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getEffectiveDate() {
		return effectiveDate;
	}
	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	public Time getEffectiveTime() {
		return effectiveTime;
	}
	public void setEffectiveTime(Time effectiveTime) {
		this.effectiveTime = effectiveTime;
	}
	public Date getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}
	public Time getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(Time expireTime) {
		this.expireTime = expireTime;
	}
	public List<EvalFormDTO> getEvalFormId() {
		return evalFormId;
	}
	public void setEvalFormId(List<EvalFormDTO> evalFormId) {
		this.evalFormId = evalFormId;
	}
	public int getNoOfCarAcceptDay() {
		return noOfCarAcceptDay;
	}
	public void setNoOfCarAcceptDay(int noOfCarAcceptDay) {
		this.noOfCarAcceptDay = noOfCarAcceptDay;
	}
	
	
}
