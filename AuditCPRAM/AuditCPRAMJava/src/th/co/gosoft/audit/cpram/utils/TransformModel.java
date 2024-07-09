package th.co.gosoft.audit.cpram.utils;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.AnswerDTO;
import th.co.gosoft.audit.cpram.dto.AppointDTO;
import th.co.gosoft.audit.cpram.dto.AppointHistoryDTO;
import th.co.gosoft.audit.cpram.dto.AppointStatusDTO;
import th.co.gosoft.audit.cpram.dto.AppointTypeDTO;
import th.co.gosoft.audit.cpram.dto.AssignPlanDTO;
import th.co.gosoft.audit.cpram.dto.AssignPlanStatusDTO;
import th.co.gosoft.audit.cpram.dto.AuditResultDTO;
import th.co.gosoft.audit.cpram.dto.CarDTO;
import th.co.gosoft.audit.cpram.dto.CarDetailDTO;
import th.co.gosoft.audit.cpram.dto.CarStatusDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistPlanStatusDTO;
import th.co.gosoft.audit.cpram.dto.ChecklistTypeDTO;
import th.co.gosoft.audit.cpram.dto.DistrictDTO;
import th.co.gosoft.audit.cpram.dto.DocumentDTO;
import th.co.gosoft.audit.cpram.dto.EvalFormDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanAnswerDTO;
import th.co.gosoft.audit.cpram.dto.EvalPlanDTO;
import th.co.gosoft.audit.cpram.dto.EvalTypeDTO;
import th.co.gosoft.audit.cpram.dto.EvidenceDTO;
import th.co.gosoft.audit.cpram.dto.EvidenceTypeDTO;
import th.co.gosoft.audit.cpram.dto.FinalAuditResultDTO;
import th.co.gosoft.audit.cpram.dto.FinalAuditResultStatusDTO;
import th.co.gosoft.audit.cpram.dto.GradeDTO;
import th.co.gosoft.audit.cpram.dto.InformationDTO;
import th.co.gosoft.audit.cpram.dto.InformationDetailDTO;
import th.co.gosoft.audit.cpram.dto.InformationDocumentDTO;
import th.co.gosoft.audit.cpram.dto.ManualDocumentDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.ProvinceDTO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.dto.RegionDTO;
import th.co.gosoft.audit.cpram.dto.StandardDocumentDTO;
import th.co.gosoft.audit.cpram.dto.SubDistrictDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.dto.SupplierStandardDocumentDTO;
import th.co.gosoft.audit.cpram.dto.SupplierUserMappingDTO;
import th.co.gosoft.audit.cpram.dto.SystemConfigurationDTO;
import th.co.gosoft.audit.cpram.dto.SystemLogDTO;
import th.co.gosoft.audit.cpram.dto.SystemSequenceDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
import th.co.gosoft.audit.cpram.dto.MenuDTO;
import th.co.gosoft.audit.cpram.dto.PlanDateChecklistPlanDTO;
import th.co.gosoft.audit.cpram.model.AddressModel;
import th.co.gosoft.audit.cpram.model.AnswerModel;
import th.co.gosoft.audit.cpram.model.AppointHistoryModel;
import th.co.gosoft.audit.cpram.model.AppointModel;
import th.co.gosoft.audit.cpram.model.AppointStatusModel;
import th.co.gosoft.audit.cpram.model.AppointTypeModel;
import th.co.gosoft.audit.cpram.model.AssignPlanModel;
import th.co.gosoft.audit.cpram.model.AssignPlanStatusModel;
import th.co.gosoft.audit.cpram.model.AuditResultModel;
import th.co.gosoft.audit.cpram.model.CarDetailModel;
import th.co.gosoft.audit.cpram.model.CarModel;
import th.co.gosoft.audit.cpram.model.CarStatusModel;
import th.co.gosoft.audit.cpram.model.ChecklistModel;
import th.co.gosoft.audit.cpram.model.ChecklistPlanModel;
import th.co.gosoft.audit.cpram.model.ChecklistPlanStatusModel;
import th.co.gosoft.audit.cpram.model.ChecklistTypeModel;
import th.co.gosoft.audit.cpram.model.DistrictModel;
import th.co.gosoft.audit.cpram.model.DocumentModel;
import th.co.gosoft.audit.cpram.model.EvalFormModel;
import th.co.gosoft.audit.cpram.model.EvalPlanAnswerModel;
import th.co.gosoft.audit.cpram.model.EvalPlanModel;
import th.co.gosoft.audit.cpram.model.EvalTypeModel;
import th.co.gosoft.audit.cpram.model.EvidenceModel;
import th.co.gosoft.audit.cpram.model.EvidenceTypeModel;
import th.co.gosoft.audit.cpram.model.FinalAuditResultModel;
import th.co.gosoft.audit.cpram.model.FinalAuditResultStatusModel;
import th.co.gosoft.audit.cpram.model.GradeModel;
import th.co.gosoft.audit.cpram.model.InformationDetailModel;
import th.co.gosoft.audit.cpram.model.InformationDocumentModel;
import th.co.gosoft.audit.cpram.model.InformationModel;
import th.co.gosoft.audit.cpram.model.ManualDocumentModel;
import th.co.gosoft.audit.cpram.model.ProductTypeModel;
import th.co.gosoft.audit.cpram.model.ProvinceModel;
import th.co.gosoft.audit.cpram.model.QuestionTypeModel;
import th.co.gosoft.audit.cpram.model.RegionModel;
import th.co.gosoft.audit.cpram.model.StandardDocumentModel;
import th.co.gosoft.audit.cpram.model.SubDistrictModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.SupplierProductAddressMappingModel;
import th.co.gosoft.audit.cpram.model.SupplierStandardDocumentModel;
import th.co.gosoft.audit.cpram.model.SupplierUserMappingModel;
import th.co.gosoft.audit.cpram.model.SystemConfigurationModel;
import th.co.gosoft.audit.cpram.model.SystemLogModel;
import th.co.gosoft.audit.cpram.model.SystemSequenceModel;
import th.co.gosoft.audit.cpram.model.MenuModel;
import th.co.gosoft.audit.cpram.model.PlanDateModel;
import th.co.gosoft.audit.cpram.model.UserGroupModel;
import th.co.gosoft.audit.cpram.model.UserModel;

public class TransformModel {
	
	
	public static AnswerDTO transAnswerModel(AnswerModel answerModel) {
				
		AnswerDTO answerDTO = new AnswerDTO();
		answerDTO.setAnswerDetail(NullUtils.cvStr(answerModel.getAnswerDetail()));
		answerDTO.setAnswerId(NullUtils.cvInt(answerModel.getAnswerId()));				
		answerDTO.setIsCreateCar(NullUtils.cvChar(answerModel.getIsCreateCar()));
		
		answerDTO.setEvalFormId(new ArrayList<>());
		if(answerModel.getEvalFormId() != null) {
			if(!answerModel.getEvalFormId().isEmpty()) {
				for(EvalFormModel evalFormModel : answerModel.getEvalFormId()) {
					answerDTO.getEvalFormId().add(transEvalFormModel(evalFormModel));
				}
			}
		}
		answerDTO.setQuestionTypeId(new ArrayList<>());
		if(answerModel.getQuestionTypeId() != null) {
			if(!answerModel.getQuestionTypeId().isEmpty()) {
				for(QuestionTypeModel question : answerModel.getQuestionTypeId()) {
					answerDTO.getQuestionTypeId().add(transQuestionTypeModel(question));
				}
			}
			
		}
		

		answerDTO.setCreateBy(NullUtils.cvInt(answerModel.getCreateBy()));
		answerDTO.setEnable(NullUtils.cvChar(answerModel.getEnable()));
		answerDTO.setUpdateBy(NullUtils.cvInt(answerModel.getUpdateBy()));		
		if(!StringUtils.isNullOrEmpty(answerModel.getCreateDate())) {			
			answerDTO.setCreateDate(convertDateStringToSQL(answerModel.getCreateDate().split(" ")[0]));
			answerDTO.setCreateTime(convertTimeStringToSQL(answerModel.getCreateDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(answerModel.getUpdateDate())) {
			answerDTO.setUpdateDate(convertDateStringToSQL(answerModel.getUpdateDate().split(" ")[0]));
			answerDTO.setUpdateTime(convertTimeStringToSQL(answerModel.getUpdateDate()));
		}
		
		return answerDTO;
	}

	
	public static AppointDTO transAppointModel(AppointModel appointModel) {
				
		AppointDTO appointDTO = new AppointDTO();
		appointDTO.setAppointId(NullUtils.cvInt(appointModel.getAppointId()));		
		appointDTO.setDetail(NullUtils.cvStr(appointModel.getDetail()));
		appointDTO.setTitle(NullUtils.cvStr(appointModel.getTitle()));		
		
		if(!StringUtils.isNullOrEmpty(appointModel.getAppointDate())) {			
			appointDTO.setAppointDate(convertDateStringToSQL(appointModel.getAppointDate().split(" ")[0]));
			appointDTO.setAppointTime(convertTimeStringToSQL(appointModel.getAppointDate()));
		}
		
		
		if(appointModel.getSupplierId() != null) {
			appointDTO.setSupplierId(transSupplierModel(appointModel.getSupplierId()));
		}
		if(appointModel.getAppointStatusId() != null) {
			appointDTO.setAppointStatusId(transAppointStatusModel(appointModel.getAppointStatusId()));
		}
		if(appointModel.getAppointCreateBy() != null) {
			appointDTO.setAppointCreateBy(transUserModel(appointModel.getAppointCreateBy()));
		}
		
		if(appointModel.getAppointTypeId() != null) {
			appointDTO.setAppointTypeId(transAppointTypeModel(appointModel.getAppointTypeId()));
		}
		
		if(appointModel.getLocationId() != null) {
			appointDTO.setLocationId(transSupplierProductAddressMappingModel(appointModel.getLocationId()));
		}
		
		
		appointDTO.setAuditorId(new ArrayList<>());
		if(appointModel.getAuditorId() != null) {
			if(!appointModel.getAuditorId().isEmpty()) {
				for(UserModel auditor : appointModel.getAuditorId()) {
					appointDTO.getAuditorId().add(transUserModel(auditor));
				}
			}
		}
		
		appointDTO.setAppointHistoryId(new ArrayList<>());
		if(appointModel.getAppointHistoryId() != null) {
			if(!appointModel.getAppointHistoryId().isEmpty()) {
				for(AppointHistoryModel appointHistory : appointModel.getAppointHistoryId()) {
					appointDTO.getAppointHistoryId().add(transAppointHistoryModel(appointHistory));
				}
			}
		}
		
		appointDTO.setEntourageId(new ArrayList<>());
		if(appointModel.getEntourageId() != null) {
			if(!appointModel.getEntourageId().isEmpty()) {
				for(UserModel entourage : appointModel.getEntourageId()) {
					appointDTO.getEntourageId().add(transUserModel(entourage));
				}
			}
		}

		appointDTO.setEnable(NullUtils.cvChar(appointModel.getEnable()));
		appointDTO.setCreateBy(NullUtils.cvInt(appointModel.getCreateBy()));
		appointDTO.setUpdateBy(NullUtils.cvInt(appointModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(appointModel.getCreateDate())) {			
			appointDTO.setCreateDate(convertDateStringToSQL(appointModel.getCreateDate().split(" ")[0]));
			appointDTO.setCreateTime(convertTimeStringToSQL(appointModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(appointModel.getUpdateDate())) {
			appointDTO.setUpdateDate(convertDateStringToSQL(appointModel.getUpdateDate().split(" ")[0]));
			appointDTO.setUpdateTime(convertTimeStringToSQL(appointModel.getUpdateDate()));
		}
		return appointDTO;
	}
	
	public static AppointHistoryDTO transAppointHistoryModel(AppointHistoryModel appointHistoryModel) {
		AppointHistoryDTO appointHistoryDTO = new AppointHistoryDTO();
		appointHistoryDTO.setAppointHistoryId(NullUtils.cvInt(appointHistoryModel.getAppointHistoryId()));		
		appointHistoryDTO.setDetail(NullUtils.cvStr(appointHistoryModel.getDetail()));		
		appointHistoryDTO.setTitle(NullUtils.cvStr(appointHistoryModel.getTitle()));	
		
		if(appointHistoryModel.getAppointStatusId() != null) {
			appointHistoryDTO.setAppointStatusId(transAppointStatusModel(appointHistoryModel.getAppointStatusId()));
		}
		
		if(appointHistoryModel.getAppointId() != null) {
			appointHistoryDTO.setAppointId(transAppointModel(appointHistoryModel.getAppointId()));
		}
		
		if(appointHistoryModel.getAppointHistoryCreateBy() != null) {
			appointHistoryDTO.setAppointHistoryCreateBy(transUserModel(appointHistoryModel.getAppointHistoryCreateBy()));
		}
		
		appointHistoryDTO.setEnable(NullUtils.cvChar(appointHistoryModel.getEnable()));
		appointHistoryDTO.setCreateBy(NullUtils.cvInt(appointHistoryModel.getCreateBy()));
		appointHistoryDTO.setUpdateBy(NullUtils.cvInt(appointHistoryModel.getUpdateBy()));	
		if(!StringUtils.isNullOrEmpty(appointHistoryModel.getCreateDate())) {			
			appointHistoryDTO.setCreateDate(convertDateStringToSQL(appointHistoryModel.getCreateDate().split(" ")[0]));
			appointHistoryDTO.setCreateTime(convertTimeStringToSQL(appointHistoryModel.getCreateDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(appointHistoryModel.getUpdateDate())) {
			appointHistoryDTO.setUpdateDate(convertDateStringToSQL(appointHistoryModel.getUpdateDate().split(" ")[0]));
			appointHistoryDTO.setUpdateTime(convertTimeStringToSQL(appointHistoryModel.getUpdateDate()));
		}
		
		return appointHistoryDTO;
	}
	
	public static AppointStatusDTO transAppointStatusModel(AppointStatusModel appointStatusModel) {		
		AppointStatusDTO appointStatusDTO = new AppointStatusDTO();
		appointStatusDTO.setAppointStatusId(NullUtils.cvInt(appointStatusModel.getAppointStatusId()));
		appointStatusDTO.setAppointStatusName(NullUtils.cvStr(appointStatusModel.getAppointStatusName()));		
		appointStatusDTO.setStatusColor(NullUtils.cvStr(appointStatusModel.getStatusColor()));		

		appointStatusDTO.setAppointHistoryId(new ArrayList<>());
		if(appointStatusModel.getAppointHistoryId() != null) {
			if(!appointStatusModel.getAppointHistoryId().isEmpty()) {
				for(AppointHistoryModel appointHistory : appointStatusModel.getAppointHistoryId()) {
					appointStatusDTO.getAppointHistoryId().add(transAppointHistoryModel(appointHistory));
				}
			}
		}
		
		appointStatusDTO.setAppointId(new ArrayList<>());
		if(appointStatusModel.getAppointId() != null) {
			if(!appointStatusModel.getAppointId().isEmpty()) {
				for(AppointModel appoint : appointStatusModel.getAppointId()) {
					appointStatusDTO.getAppointId().add(transAppointModel(appoint));
				}
			}
		}
		
		
		appointStatusDTO.setUpdateBy(NullUtils.cvInt(appointStatusModel.getUpdateBy()));
		appointStatusDTO.setCreateBy(NullUtils.cvInt(appointStatusModel.getCreateBy()));
		appointStatusDTO.setEnable(NullUtils.cvChar(appointStatusModel.getEnable()));
		if(!StringUtils.isNullOrEmpty(appointStatusModel.getCreateDate())) {			
			appointStatusDTO.setCreateDate(convertDateStringToSQL(appointStatusModel.getCreateDate().split(" ")[0]));
			appointStatusDTO.setCreateTime(convertTimeStringToSQL(appointStatusModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(appointStatusModel.getUpdateDate())) {
			appointStatusDTO.setUpdateDate(convertDateStringToSQL(appointStatusModel.getUpdateDate().split(" ")[0]));
			appointStatusDTO.setUpdateTime(convertTimeStringToSQL(appointStatusModel.getUpdateDate()));
		}
		
		return appointStatusDTO;
	}
	
	public static AppointTypeDTO transAppointTypeModel(AppointTypeModel appointTypeModel) {	
		AppointTypeDTO appointTypeDTO = new AppointTypeDTO();
		appointTypeDTO.setAppointTypeId(NullUtils.cvInt(appointTypeModel.getAppointTypeId()));		
		appointTypeDTO.setName(NullUtils.cvStr(appointTypeModel.getName()));
		
		appointTypeDTO.setAppointId(new ArrayList<>());
		if(appointTypeModel.getAppointId() != null) {
			if(!appointTypeModel.getAppointId().isEmpty()) {
				for(AppointModel appointModel : appointTypeModel.getAppointId()) {
					appointTypeDTO.getAppointId().add(transAppointModel(appointModel));
				}
			}
		}
		
		
		appointTypeDTO.setCreateBy(NullUtils.cvInt(appointTypeModel.getCreateBy()));
		appointTypeDTO.setEnable(NullUtils.cvChar(appointTypeModel.getEnable()));
		appointTypeDTO.setUpdateBy(NullUtils.cvInt(appointTypeModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(appointTypeModel.getCreateDate())) {			
			appointTypeDTO.setCreateDate(convertDateStringToSQL(appointTypeModel.getCreateDate().split(" ")[0]));
			appointTypeDTO.setCreateTime(convertTimeStringToSQL(appointTypeModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(appointTypeModel.getUpdateDate())) {
			appointTypeDTO.setUpdateDate(convertDateStringToSQL(appointTypeModel.getUpdateDate().split(" ")[0]));
			appointTypeDTO.setUpdateTime(convertTimeStringToSQL(appointTypeModel.getUpdateDate()));
		}
		return appointTypeDTO;
	}
	
	
	public static AssignPlanDTO transAssignPlanModel(AssignPlanModel assignPlanModel) {
		AssignPlanDTO assignPlanDTO = new AssignPlanDTO();
		
		if(assignPlanModel.getChecklistPlanId() != null) {
			assignPlanDTO.setChecklistPlanId(transChecklistPlanModel(assignPlanModel.getChecklistPlanId()));
		}
		
		if(assignPlanModel.getAuditorId() != null) {
			assignPlanDTO.setAuditorId(transUserModel(assignPlanModel.getAuditorId()));
		}
		
		if(assignPlanModel.getAssignPlanStatusId() != null) {
			assignPlanDTO.setAssignPlanStatusId(transAssignPlanStatusModel(assignPlanModel.getAssignPlanStatusId()));
		}
		
		assignPlanDTO.setSignatureOfSupplier(assignPlanModel.getSignatureOfSupplier());
		assignPlanDTO.setCreateBy(NullUtils.cvInt(assignPlanModel.getCreateBy()));
		assignPlanDTO.setEnable(NullUtils.cvChar(assignPlanModel.getEnable()));
		assignPlanDTO.setUpdateBy(NullUtils.cvInt(assignPlanModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(assignPlanModel.getCreateDate())) {			
			assignPlanDTO.setCreateDate(convertDateStringToSQL(assignPlanModel.getCreateDate().split(" ")[0]));
			assignPlanDTO.setCreateTime(convertTimeStringToSQL(assignPlanModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(assignPlanModel.getUpdateDate())) {
			assignPlanDTO.setUpdateDate(convertDateStringToSQL(assignPlanModel.getUpdateDate().split(" ")[0]));
			assignPlanDTO.setUpdateTime(convertTimeStringToSQL(assignPlanModel.getUpdateDate()));
		}
		
		return assignPlanDTO;
	}
	
	
	public static AssignPlanStatusDTO transAssignPlanStatusModel(AssignPlanStatusModel assignPlanStatusModel) {
		AssignPlanStatusDTO assignPlanStatusDTO = new AssignPlanStatusDTO();
		
		assignPlanStatusDTO.setAssignPlanStatusId(NullUtils.cvInt(assignPlanStatusModel.getAssignPlanStatusId()));
		assignPlanStatusDTO.setAssignPlanStatusName(NullUtils.cvStr(assignPlanStatusModel.getAssignPlanStatusName()));
		assignPlanStatusDTO.setStatusColor(NullUtils.cvStr(assignPlanStatusModel.getStatusColor()));		
		assignPlanStatusDTO.setCreateBy(NullUtils.cvInt(assignPlanStatusModel.getCreateBy()));
		assignPlanStatusDTO.setEnable(NullUtils.cvChar(assignPlanStatusModel.getEnable()));
		assignPlanStatusDTO.setUpdateBy(NullUtils.cvInt(assignPlanStatusModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(assignPlanStatusModel.getCreateDate())) {			
			assignPlanStatusDTO.setCreateDate(convertDateStringToSQL(assignPlanStatusModel.getCreateDate().split(" ")[0]));
			assignPlanStatusDTO.setCreateTime(convertTimeStringToSQL(assignPlanStatusModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(assignPlanStatusModel.getUpdateDate())) {
			assignPlanStatusDTO.setUpdateDate(convertDateStringToSQL(assignPlanStatusModel.getUpdateDate().split(" ")[0]));
			assignPlanStatusDTO.setUpdateTime(convertTimeStringToSQL(assignPlanStatusModel.getUpdateDate()));
		}
		
		return assignPlanStatusDTO;
	}
	
	public static AuditResultDTO transAuditResultModel(AuditResultModel auditResultModel) {
		AuditResultDTO auditResultDTO = new AuditResultDTO();
		
		auditResultDTO.setAnswerDetail(NullUtils.cvStr(auditResultModel.getAnswerDetail()));
		auditResultDTO.setAccepted(NullUtils.cvChar(auditResultModel.getAccepted()));
		auditResultDTO.setNote(NullUtils.cvStr(auditResultModel.getNote()));
		
		if(!StringUtils.isNullOrEmpty(auditResultModel.getTransactionDate())) {
			auditResultDTO.setTransactionDate(convertDateStringToSQL(auditResultModel.getTransactionDate().split(" ")[0]));
			auditResultDTO.setTransactionTime(convertTimeStringToSQL(auditResultModel.getTransactionDate()));
		}
		
		if(auditResultModel.getAnswerId() != null) {
			auditResultDTO.setAnswerId(transEvalPlanAnswerModel(auditResultModel.getAnswerId()));
		}
		if(auditResultModel.getEvalPlanId() != null) {
			auditResultDTO.setEvalPlanId(transEvalPlanModel(auditResultModel.getEvalPlanId()));
		}
		
		if(auditResultModel.getAuditorId() != null) {
			auditResultDTO.setAuditorId(transUserModel(auditResultModel.getAuditorId()));
		}
		
		if(auditResultModel.getChecklistPlanId() != null) {
			auditResultDTO.setChecklistPlanId(transChecklistPlanModel(auditResultModel.getChecklistPlanId()));
		}	
		
		auditResultDTO.setCreateBy(NullUtils.cvInt(auditResultModel.getCreateBy()));
		auditResultDTO.setEnable(NullUtils.cvChar(auditResultModel.getEnable()));
		auditResultDTO.setUpdateBy(NullUtils.cvInt(auditResultModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(auditResultModel.getCreateDate())) {			
			auditResultDTO.setCreateDate(convertDateStringToSQL(auditResultModel.getCreateDate().split(" ")[0]));
			auditResultDTO.setCreateTime(convertTimeStringToSQL(auditResultModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(auditResultModel.getUpdateDate())) {
			auditResultDTO.setUpdateDate(convertDateStringToSQL(auditResultModel.getUpdateDate().split(" ")[0]));
			auditResultDTO.setUpdateTime(convertTimeStringToSQL(auditResultModel.getUpdateDate()));
		}
		return auditResultDTO;
	}
	
	
	
	public static CarDTO transCarModel(CarModel carModel) {
		CarDTO carDTO = new CarDTO();		
		carDTO.setCarId(NullUtils.cvInt(carModel.getCarId()));
		carDTO.setCarNo(NullUtils.cvStr(carModel.getCarNo()));
		
		if(!StringUtils.isNullOrEmpty(carModel.getDueDate())) {			
			carDTO.setDueDate(convertDateStringToSQL(carModel.getDueDate().split(" ")[0]));
			carDTO.setDueTime(convertTimeStringToSQL(carModel.getDueDate()));
		}		
		
		if(carModel.getChecklistPlanId() != null) {
			carDTO.setChecklistPlanId(transChecklistPlanModel(carModel.getChecklistPlanId()));
		}
		
		if(carModel.getCarStatusId() != null) {
			carDTO.setCarStatusId(transCarStatusModel(carModel.getCarStatusId()));
		}		
		
		carDTO.setCreateBy(NullUtils.cvInt(carModel.getCreateBy()));
		carDTO.setEnable(NullUtils.cvChar(carModel.getEnable()));
		carDTO.setUpdateBy(NullUtils.cvInt(carModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(carModel.getCreateDate())) {			
			carDTO.setCreateDate(convertDateStringToSQL(carModel.getCreateDate().split(" ")[0]));
			carDTO.setCreateTime(convertTimeStringToSQL(carModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(carModel.getUpdateDate())) {
			carDTO.setUpdateDate(convertDateStringToSQL(carModel.getUpdateDate().split(" ")[0]));
			carDTO.setUpdateTime(convertTimeStringToSQL(carModel.getUpdateDate()));
		}
		
		return carDTO;
	}
	
	public static CarDetailDTO transCarDetailModel(CarDetailModel carDetailModel) {		
		
		CarDetailDTO carDetailDTO = new CarDetailDTO();
		carDetailDTO.setDetail(NullUtils.cvStr(carDetailModel.getDetail()));
		carDetailDTO.setCompleted(NullUtils.cvChar(carDetailModel.getCompleted()));
		
		if(!StringUtils.isNullOrEmpty(carDetailModel.getDueDate())) {
			carDetailDTO.setDueDate(convertDateStringToSQL(carDetailModel.getDueDate().split(" ")[0]));
			carDetailDTO.setDueTime(convertTimeStringToSQL(carDetailModel.getDueDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(carDetailModel.getCompleteDate())) {			
			carDetailDTO.setCompleteDate(convertDateStringToSQL(carDetailModel.getCompleteDate().split(" ")[0]));
			carDetailDTO.setCompleteTime(convertTimeStringToSQL(carDetailModel.getCompleteDate()));
		}		
		
		if(carDetailModel.getAuditResultId() != null) {
			carDetailDTO.setAuditResultId(transAuditResultModel(carDetailModel.getAuditResultId()));
		}
		
		
		if(carDetailModel.getCarId() != null) {
			carDetailDTO.setCarId(transCarModel(carDetailModel.getCarId()));
		}
		
		carDetailDTO.setEvidenceId(new ArrayList<>());
		if(carDetailModel.getEvidenceId() != null) {
			if(!carDetailModel.getEvidenceId().isEmpty()) {
				for(EvidenceModel evidenceModel : carDetailModel.getEvidenceId()) {
					carDetailDTO.getEvidenceId().add(transEvidenceModel(evidenceModel));
				}
			}
		}
		
		carDetailDTO.setRemark(NullUtils.cvStr(carDetailModel.getRemark()));
		carDetailDTO.setCreateBy(NullUtils.cvInt(carDetailModel.getCreateBy()));
		carDetailDTO.setEnable(NullUtils.cvChar(carDetailModel.getEnable()));
		carDetailDTO.setUpdateBy(NullUtils.cvInt(carDetailModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(carDetailModel.getCreateDate())) {			
			carDetailDTO.setCreateDate(convertDateStringToSQL(carDetailModel.getCreateDate().split(" ")[0]));
			carDetailDTO.setCreateTime(convertTimeStringToSQL(carDetailModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(carDetailModel.getUpdateDate())) {
			carDetailDTO.setUpdateDate(convertDateStringToSQL(carDetailModel.getUpdateDate().split(" ")[0]));
			carDetailDTO.setUpdateTime(convertTimeStringToSQL(carDetailModel.getUpdateDate()));
		}
		
		
		return carDetailDTO;
	}
	
	public static CarStatusDTO transCarStatusModel(CarStatusModel carStatusModel) {
		CarStatusDTO carStatusDTO = new CarStatusDTO();
		carStatusDTO.setCarStatusId(NullUtils.cvInt(carStatusModel.getCarStatusId()));
		carStatusDTO.setCarStatusName(NullUtils.cvStr(carStatusModel.getCarStatusName()));
		carStatusDTO.setStatusColor(NullUtils.cvStr(carStatusModel.getStatusColor()));
		
		carStatusDTO.setCreateBy(NullUtils.cvInt(carStatusModel.getCreateBy()));
		carStatusDTO.setEnable(NullUtils.cvChar(carStatusModel.getEnable()));
		carStatusDTO.setUpdateBy(NullUtils.cvInt(carStatusModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(carStatusModel.getCreateDate())) {			
			carStatusDTO.setCreateDate(convertDateStringToSQL(carStatusModel.getCreateDate().split(" ")[0]));
			carStatusDTO.setCreateTime(convertTimeStringToSQL(carStatusModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(carStatusModel.getUpdateDate())) {
			carStatusDTO.setUpdateDate(convertDateStringToSQL(carStatusModel.getUpdateDate().split(" ")[0]));
			carStatusDTO.setUpdateTime(convertTimeStringToSQL(carStatusModel.getUpdateDate()));
		}
		return carStatusDTO;
	}
	
	public static ChecklistDTO transChecklistModel(ChecklistModel checklistModel) {		
		ChecklistDTO checklistDTO = new ChecklistDTO();
		checklistDTO.setChecklistId(NullUtils.cvInt(checklistModel.getChecklistId()));
		checklistDTO.setChecklistTitle(NullUtils.cvStr(checklistModel.getChecklistTitle()));
		checklistDTO.setChecklistScope(NullUtils.cvStr(checklistModel.getChecklistScope()));
		checklistDTO.setScoringCriteria(NullUtils.cvStr(checklistModel.getScoringCriteria()));
		checklistDTO.setApproveSupplierRule(NullUtils.cvStr(checklistModel.getApproveSupplierRule()));
		checklistDTO.setDescription(NullUtils.cvStr(checklistModel.getDescription()));
		checklistDTO.setNoOfCarAcceptDay(NullUtils.cvInt(checklistModel.getNoOfCarAcceptDay()));
		
		if(!StringUtils.isNullOrEmpty(checklistModel.getEffectiveDate())) {
			checklistDTO.setEffectiveDate(convertDateStringToSQL(checklistModel.getEffectiveDate().split(" ")[0]));
			checklistDTO.setEffectiveTime(convertTimeStringToSQL(checklistModel.getEffectiveDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(checklistModel.getExpireDate())) {
			checklistDTO.setExpireDate(convertDateStringToSQL(checklistModel.getExpireDate().split(" ")[0]));
			checklistDTO.setExpireTime(convertTimeStringToSQL(checklistModel.getExpireDate()));
		}
		
		if(checklistModel.getChecklistTypeId() != null) {
			checklistDTO.setChecklistTypeId(transChecklistTypeModel(checklistModel.getChecklistTypeId()));
		}
		
		if(checklistModel.getProductTypeId() != null) {
			checklistDTO.setProductTypeId(transProductTypeModel(checklistModel.getProductTypeId()));
		}
		
		checklistDTO.setEvalFormId(new ArrayList<>());
		if(checklistModel.getEvalFormId() != null) {
			if(!checklistModel.getEvalFormId().isEmpty()) {
				for(EvalFormModel evalForm : checklistModel.getEvalFormId()) {
					checklistDTO.getEvalFormId().add(transEvalFormModel(evalForm));
				}
			}
		}
		
		checklistDTO.setCreateBy(NullUtils.cvInt(checklistModel.getCreateBy()));
		checklistDTO.setEnable(NullUtils.cvChar(checklistModel.getEnable()));
		checklistDTO.setUpdateBy(NullUtils.cvInt(checklistModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(checklistModel.getCreateDate())) {			
			checklistDTO.setCreateDate(convertDateStringToSQL(checklistModel.getCreateDate().split(" ")[0]));
			checklistDTO.setCreateTime(convertTimeStringToSQL(checklistModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(checklistModel.getUpdateDate())) {
			checklistDTO.setUpdateDate(convertDateStringToSQL(checklistModel.getUpdateDate().split(" ")[0]));
			checklistDTO.setUpdateTime(convertTimeStringToSQL(checklistModel.getUpdateDate()));
		}
		
		return checklistDTO;
	}
	
	
	public static ChecklistPlanDTO transChecklistPlanModel(ChecklistPlanModel checklistPlanModel) {
		ChecklistPlanDTO checklistPlanDTO = new ChecklistPlanDTO();
		
		checklistPlanDTO.setChecklistPlanId(NullUtils.cvInt(checklistPlanModel.getChecklistPlanId()));
		checklistPlanDTO.setChecklistTitle(NullUtils.cvStr(checklistPlanModel.getChecklistTitle()));
		checklistPlanDTO.setSupplierCompany(NullUtils.cvStr(checklistPlanModel.getSupplierCompany()));
		checklistPlanDTO.setChecklistTypeName(NullUtils.cvStr(checklistPlanModel.getChecklistTypeName()));
		checklistPlanDTO.setProductTypeName(NullUtils.cvStr(checklistPlanModel.getProductTypeName()));
		checklistPlanDTO.setChecklistScope(NullUtils.cvStr(checklistPlanModel.getChecklistScope()));
		checklistPlanDTO.setScoringCriteria(NullUtils.cvStr(checklistPlanModel.getScoringCriteria()));
		checklistPlanDTO.setApproveSupplierRule(NullUtils.cvStr(checklistPlanModel.getApproveSupplierRule()));
		checklistPlanDTO.setDescription(NullUtils.cvStr(checklistPlanModel.getDescription()));
		checklistPlanDTO.setNoOfCarAcceptDay(NullUtils.cvInt(checklistPlanModel.getNoOfCarAcceptDay()));
		checklistPlanDTO.setChecklistPlanNo(NullUtils.cvStr(checklistPlanModel.getChecklistPlanNo()));
		
		if(checklistPlanModel.getSupplierId() != null) {
			checklistPlanDTO.setSupplierId(transSupplierModel(checklistPlanModel.getSupplierId()));
		}
		
		if(checklistPlanModel.getChecklistTypeId() != null) {
			checklistPlanDTO.setChecklistTypeId(transChecklistTypeModel(checklistPlanModel.getChecklistTypeId()));
		}
		
		if(checklistPlanModel.getProductTypeId() != null) {
			checklistPlanDTO.setProductTypeId(transProductTypeModel(checklistPlanModel.getProductTypeId()));
		}
		
		if(checklistPlanModel.getChecklistPlanStatusId() != null) {
			checklistPlanDTO.setChecklistPlanStatusId(transChecklistPlanStatusModel(checklistPlanModel.getChecklistPlanStatusId()));
		}
		
		if(checklistPlanModel.getCarId() != null) {
			checklistPlanDTO.setCarId(transCarModel(checklistPlanModel.getCarId()));
		}
		
		if(!StringUtils.isNullOrEmpty(checklistPlanModel.getPlanDate())) {
			checklistPlanDTO.setPlanDate(convertDateStringToSQL(checklistPlanModel.getPlanDate().split(" ")[0]));
			checklistPlanDTO.setPlanTime(convertTimeStringToSQL(checklistPlanModel.getPlanDate()));
		}
		
		if(checklistPlanModel.getLocationId() != null) {
			checklistPlanDTO.setLocationId(transSupplierProductAddressMappingModel(checklistPlanModel.getLocationId()));
		}
		
		checklistPlanDTO.setAssignPlanId(new ArrayList<>());
		if(checklistPlanModel.getAssignPlanId() != null) {
			if(!checklistPlanModel.getAssignPlanId().isEmpty()) {
				for(AssignPlanModel assignPlan : checklistPlanModel.getAssignPlanId()) {
					checklistPlanDTO.getAssignPlanId().add(transAssignPlanModel(assignPlan));
				}
			}
		}
		
		checklistPlanDTO.setChecklistPlanEntourageId(new ArrayList<>());
		if(checklistPlanModel.getChecklistPlanEntourageId() != null) {
			if(!checklistPlanModel.getChecklistPlanEntourageId().isEmpty()) {
				for(UserModel entourage : checklistPlanModel.getChecklistPlanEntourageId()) {
					checklistPlanDTO.getChecklistPlanEntourageId().add(transUserModel(entourage));
				}
			}
		}
		
		checklistPlanDTO.setCreateBy(NullUtils.cvInt(checklistPlanModel.getCreateBy()));
		checklistPlanDTO.setEnable(NullUtils.cvChar(checklistPlanModel.getEnable()));
		checklistPlanDTO.setUpdateBy(NullUtils.cvInt(checklistPlanModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(checklistPlanModel.getCreateDate())) {			
			checklistPlanDTO.setCreateDate(convertDateStringToSQL(checklistPlanModel.getCreateDate().split(" ")[0]));
			checklistPlanDTO.setCreateTime(convertTimeStringToSQL(checklistPlanModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(checklistPlanModel.getUpdateDate())) {
			checklistPlanDTO.setUpdateDate(convertDateStringToSQL(checklistPlanModel.getUpdateDate().split(" ")[0]));
			checklistPlanDTO.setUpdateTime(convertTimeStringToSQL(checklistPlanModel.getUpdateDate()));
		}
		
		
		
		return checklistPlanDTO;
	}
	
	public static PlanDateChecklistPlanDTO tranPlanDateChecklistPlanDTO(PlanDateModel planDateModel) {
		PlanDateChecklistPlanDTO planDateChecklistPlanDTO = new PlanDateChecklistPlanDTO();
		
		planDateChecklistPlanDTO.setAppointId(NullUtils.cvInt(planDateModel.getAppointId()));
		
		if(!StringUtils.isNullOrEmpty(planDateModel.getPlanDate())) {
			planDateChecklistPlanDTO.setPlanDate(convertDateStringToSQL(planDateModel.getPlanDate().split(" ")[0]));
			planDateChecklistPlanDTO.setPlanTime(convertTimeStringToSQL(planDateModel.getPlanDate()));
		}
		return planDateChecklistPlanDTO;
	}
	
	public static ChecklistPlanStatusDTO transChecklistPlanStatusModel(ChecklistPlanStatusModel checklistPlanStatusModel) {
		ChecklistPlanStatusDTO checklistPlanStatusDTO = new ChecklistPlanStatusDTO();
		
		checklistPlanStatusDTO.setChecklistPlanStatusId(NullUtils.cvInt(checklistPlanStatusModel.getChecklistPlanStatusId()));
		checklistPlanStatusDTO.setChecklistPlanStatusName(NullUtils.cvStr(checklistPlanStatusModel.getChecklistPlanStatusName()));
		checklistPlanStatusDTO.setStatusColor(NullUtils.cvStr(checklistPlanStatusModel.getStatusColor()));
		
		checklistPlanStatusDTO.setCreateBy(NullUtils.cvInt(checklistPlanStatusModel.getCreateBy()));
		checklistPlanStatusDTO.setEnable(NullUtils.cvChar(checklistPlanStatusModel.getEnable()));
		checklistPlanStatusDTO.setUpdateBy(NullUtils.cvInt(checklistPlanStatusModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(checklistPlanStatusModel.getCreateDate())) {			
			checklistPlanStatusDTO.setCreateDate(convertDateStringToSQL(checklistPlanStatusModel.getCreateDate().split(" ")[0]));
			checklistPlanStatusDTO.setCreateTime(convertTimeStringToSQL(checklistPlanStatusModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(checklistPlanStatusModel.getUpdateDate())) {
			checklistPlanStatusDTO.setUpdateDate(convertDateStringToSQL(checklistPlanStatusModel.getUpdateDate().split(" ")[0]));
			checklistPlanStatusDTO.setUpdateTime(convertTimeStringToSQL(checklistPlanStatusModel.getUpdateDate()));
		}
		
		return checklistPlanStatusDTO;
	}
	
	public static ChecklistTypeDTO transChecklistTypeModel(ChecklistTypeModel checklistTypeModel) {
		ChecklistTypeDTO checklistTypeDTO = new ChecklistTypeDTO();
		checklistTypeDTO.setChecklistTypeId(NullUtils.cvInt(checklistTypeModel.getChecklistTypeId()));
		checklistTypeDTO.setChecklistTypeName(NullUtils.cvStr(checklistTypeModel.getChecklistTypeName()));
		
		checklistTypeDTO.setChecklistId(new ArrayList<>());
		if(checklistTypeModel.getChecklistId() != null) {
			if(!checklistTypeModel.getChecklistId().isEmpty()) {
				for(ChecklistModel checklist : checklistTypeModel.getChecklistId()) {
					checklistTypeDTO.getChecklistId().add(transChecklistModel(checklist));
				}
			}
		}
		
		checklistTypeDTO.setQuestionTypeId(new ArrayList<>());
		if(checklistTypeModel.getQuestionTypeId() != null) {
			if(!checklistTypeModel.getQuestionTypeId().isEmpty()) {
				for(QuestionTypeModel questionType : checklistTypeModel.getQuestionTypeId()) {
					checklistTypeDTO.getQuestionTypeId().add(transQuestionTypeModel(questionType));
				}
			}
		}
		
		checklistTypeDTO.setGradeId(new ArrayList<>());
		if(checklistTypeModel.getGradeId() != null) {
			if(!checklistTypeModel.getGradeId().isEmpty()) {
				for(GradeModel grade : checklistTypeModel.getGradeId()) {
					checklistTypeDTO.getGradeId().add(transGradeModel(grade));
				}
			}
		}
		
		checklistTypeDTO.setCreateBy(NullUtils.cvInt(checklistTypeModel.getCreateBy()));
		checklistTypeDTO.setUpdateBy(NullUtils.cvInt(checklistTypeModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(checklistTypeModel.getEnable())) {
			checklistTypeDTO.setEnable(NullUtils.cvChar(checklistTypeModel.getEnable()));
		}				
		if(!StringUtils.isNullOrEmpty(checklistTypeModel.getCreateDate())) {			
			checklistTypeDTO.setCreateDate(convertDateStringToSQL(checklistTypeModel.getCreateDate().split(" ")[0]));
			checklistTypeDTO.setCreateTime(convertTimeStringToSQL(checklistTypeModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(checklistTypeModel.getUpdateDate())) {
			checklistTypeDTO.setUpdateDate(convertDateStringToSQL(checklistTypeModel.getUpdateDate().split(" ")[0]));
			checklistTypeDTO.setUpdateTime(convertTimeStringToSQL(checklistTypeModel.getUpdateDate()));
		}
		return checklistTypeDTO;
	}
		
	public static EvalFormDTO transEvalFormModel(EvalFormModel evalFormModel) {
		EvalFormDTO evalFormDTO = new EvalFormDTO();
		evalFormDTO.setEvalFormId(NullUtils.cvInt(evalFormModel.getEvalFormId()));
		evalFormDTO.setParentId(NullUtils.cvInt(evalFormModel.getParentId()));
		evalFormDTO.setUniqueId(NullUtils.cvStr(evalFormModel.getUniqueId()));
		evalFormDTO.setRequireAnwser(NullUtils.cvChar(evalFormModel.getRequireAnwser()));
		evalFormDTO.setTitle(NullUtils.cvStr(evalFormModel.getTitle()));
		evalFormDTO.setDetail(NullUtils.cvStr(evalFormModel.getDetail()));
		
		if(evalFormModel.getChecklistId() != null) {
			evalFormDTO.setChecklistId(transChecklistModel(evalFormModel.getChecklistId()));
		}
		
		if(evalFormModel.getEvalTypeId() != null) {
			evalFormDTO.setEvalTypeId(transEvalTypeModel(evalFormModel.getEvalTypeId()));
		}
		
		if(evalFormModel.getQuestionTypeId() != null) {
			evalFormDTO.setQuestionTypeId(transQuestionTypeModel(evalFormModel.getQuestionTypeId()));
		}
		
		evalFormDTO.setAnswerId(new ArrayList<>());
		if(evalFormModel.getAnswerId() != null) {
			if(!evalFormModel.getAnswerId().isEmpty()) {
				for(AnswerModel answer : evalFormModel.getAnswerId()) {
					evalFormDTO.getAnswerId().add(transAnswerModel(answer));
				}
			}
		}
		
		evalFormDTO.setCreateBy(NullUtils.cvInt(evalFormModel.getCreateBy()));
		evalFormDTO.setEnable(NullUtils.cvChar(evalFormModel.getEnable()));
		evalFormDTO.setUpdateBy(NullUtils.cvInt(evalFormModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(evalFormModel.getCreateDate())) {			
			evalFormDTO.setCreateDate(convertDateStringToSQL(evalFormModel.getCreateDate().split(" ")[0]));
			evalFormDTO.setCreateTime(convertTimeStringToSQL(evalFormModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(evalFormModel.getUpdateDate())) {
			evalFormDTO.setUpdateDate(convertDateStringToSQL(evalFormModel.getUpdateDate().split(" ")[0]));
			evalFormDTO.setUpdateTime(convertTimeStringToSQL(evalFormModel.getUpdateDate()));
		}
		return evalFormDTO;
	}
	
	
	public static EvalPlanDTO transEvalPlanModel(EvalPlanModel evalPlanModel) {
		EvalPlanDTO evalPlanDTO = new EvalPlanDTO();
		evalPlanDTO.setEvalPlanId(NullUtils.cvInt(evalPlanModel.getEvalPlanId()));
		evalPlanDTO.setParentId(NullUtils.cvInt(evalPlanModel.getParentId()));
		evalPlanDTO.setEvalTypeName(NullUtils.cvStr(evalPlanModel.getEvalTypeName()));
		evalPlanDTO.setQuestionTypeName(NullUtils.cvStr(evalPlanModel.getQuestionTypeName()));
		evalPlanDTO.setRequireAnswer(NullUtils.cvChar(evalPlanModel.getRequireAnswer()));
		evalPlanDTO.setTitle(NullUtils.cvStr(evalPlanModel.getTitle()));
		evalPlanDTO.setDetail(NullUtils.cvStr(evalPlanModel.getDetail()));
		
		if(evalPlanModel.getChecklistPlanId() != null) {
			evalPlanDTO.setChecklistPlanId(transChecklistPlanModel(evalPlanModel.getChecklistPlanId()));
		}
		
		if(evalPlanModel.getEvalTypeId() != null) {
			evalPlanDTO.setEvalTypeId(transEvalTypeModel(evalPlanModel.getEvalTypeId()));
		}
		
		if(evalPlanModel.getQuestionTypeId() != null) {
			evalPlanDTO.setQuestionTypeId(transQuestionTypeModel(evalPlanModel.getQuestionTypeId()));
		}
		
		evalPlanDTO.setAuditResultId(new ArrayList<>());
		if(evalPlanModel.getAuditResultId() != null) {
			if(!evalPlanModel.getAuditResultId().isEmpty()) {
				for(AuditResultModel auditResult : evalPlanModel.getAuditResultId()) {
					evalPlanDTO.getAuditResultId().add(transAuditResultModel(auditResult));
				}
			}
		}
		
		evalPlanDTO.setCreateBy(NullUtils.cvInt(evalPlanModel.getCreateBy()));
		evalPlanDTO.setEnable(NullUtils.cvChar(evalPlanModel.getEnable()));
		evalPlanDTO.setUpdateBy(NullUtils.cvInt(evalPlanModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(evalPlanModel.getCreateDate())) {			
			evalPlanDTO.setCreateDate(convertDateStringToSQL(evalPlanModel.getCreateDate().split(" ")[0]));
			evalPlanDTO.setCreateTime(convertTimeStringToSQL(evalPlanModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(evalPlanModel.getUpdateDate())) {
			evalPlanDTO.setUpdateDate(convertDateStringToSQL(evalPlanModel.getUpdateDate().split(" ")[0]));
			evalPlanDTO.setUpdateTime(convertTimeStringToSQL(evalPlanModel.getUpdateDate()));
		}
		
		return evalPlanDTO;
	}
	
	
	public static EvalPlanAnswerDTO transEvalPlanAnswerModel(EvalPlanAnswerModel evalPlanAnswerModel) {
		EvalPlanAnswerDTO evalPlanAnswerDTO = new EvalPlanAnswerDTO();
		evalPlanAnswerDTO.setAnswerId(NullUtils.cvInt(evalPlanAnswerModel.getAnswerId()));
		evalPlanAnswerDTO.setAnswerDetail(NullUtils.cvStr(evalPlanAnswerModel.getAnswerDetail()));
		evalPlanAnswerDTO.setIsCreateCar(NullUtils.cvChar(evalPlanAnswerModel.getIsCreateCar()));
		evalPlanAnswerDTO.setIsRequireEvidence(NullUtils.cvChar(evalPlanAnswerModel.getIsRequireEvidence()));
		if(evalPlanAnswerModel.getEvalPlanId() != null) {
			evalPlanAnswerDTO.setEvalPlanId(transEvalPlanModel(evalPlanAnswerModel.getEvalPlanId()));
		}		
		
		evalPlanAnswerDTO.setCreateBy(NullUtils.cvInt(evalPlanAnswerModel.getCreateBy()));
		evalPlanAnswerDTO.setEnable(NullUtils.cvChar(evalPlanAnswerModel.getEnable()));
		evalPlanAnswerDTO.setUpdateBy(NullUtils.cvInt(evalPlanAnswerModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(evalPlanAnswerModel.getCreateDate())) {			
			evalPlanAnswerDTO.setCreateDate(convertDateStringToSQL(evalPlanAnswerModel.getCreateDate().split(" ")[0]));
			evalPlanAnswerDTO.setCreateTime(convertTimeStringToSQL(evalPlanAnswerModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(evalPlanAnswerModel.getUpdateDate())) {
			evalPlanAnswerDTO.setUpdateDate(convertDateStringToSQL(evalPlanAnswerModel.getUpdateDate().split(" ")[0]));
			evalPlanAnswerDTO.setUpdateTime(convertTimeStringToSQL(evalPlanAnswerModel.getUpdateDate()));
		}
		return evalPlanAnswerDTO;
	}
	
	public static EvalTypeDTO transEvalTypeModel(EvalTypeModel evalTypeModel) {
		EvalTypeDTO evalTypeDTO = new EvalTypeDTO();
		evalTypeDTO.setEvalTypeId(NullUtils.cvInt(evalTypeModel.getEvalTypeId()));
		evalTypeDTO.setEvalTypeName(NullUtils.cvStr(evalTypeModel.getEvalTypeName()));
		
		evalTypeDTO.setEvalFormId(new ArrayList<>());
		if(evalTypeModel.getEvalFormId() != null) {
			if(!evalTypeModel.getEvalFormId().isEmpty()) {
				for(EvalFormModel evalForm : evalTypeModel.getEvalFormId()) {
					evalTypeDTO.getEvalFormId().add(transEvalFormModel(evalForm));
				}
			}
		}
		
		evalTypeDTO.setCreateBy(NullUtils.cvInt(evalTypeModel.getCreateBy()));
		evalTypeDTO.setEnable(NullUtils.cvChar(evalTypeModel.getEnable()));
		evalTypeDTO.setUpdateBy(NullUtils.cvInt(evalTypeModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(evalTypeModel.getCreateDate())) {			
			evalTypeDTO.setCreateDate(convertDateStringToSQL(evalTypeModel.getCreateDate().split(" ")[0]));
			evalTypeDTO.setCreateTime(convertTimeStringToSQL(evalTypeModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(evalTypeModel.getUpdateDate())) {
			evalTypeDTO.setUpdateDate(convertDateStringToSQL(evalTypeModel.getUpdateDate().split(" ")[0]));
			evalTypeDTO.setUpdateTime(convertTimeStringToSQL(evalTypeModel.getUpdateDate()));
		}
		return evalTypeDTO;
	}
	
	
	public static EvidenceDTO transEvidenceModel(EvidenceModel evidenceModel) {
		EvidenceDTO evidenceDTO = new EvidenceDTO();
		evidenceDTO.setEvidenceId(NullUtils.cvInt(evidenceModel.getEvidenceId()));
		evidenceDTO.setData(NullUtils.cvStr(evidenceModel.getData()));
		evidenceDTO.setActionType(NullUtils.cvChar(evidenceModel.getActionType()));
		
		if(evidenceModel.getAuditResultId() != null) {
			evidenceDTO.setAuditResultId(transAuditResultModel(evidenceModel.getAuditResultId()));
		}
		
		if(evidenceModel.getEvidenceTypeId() != null) {
			evidenceDTO.setEvidenceTypeId(transEvidenceTypeModel(evidenceModel.getEvidenceTypeId()));
		}
				
		evidenceDTO.setCreateBy(NullUtils.cvInt(evidenceModel.getCreateBy()));
		evidenceDTO.setEnable(NullUtils.cvChar(evidenceModel.getEnable()));
		evidenceDTO.setUpdateBy(NullUtils.cvInt(evidenceModel.getUpdateBy()));
		
		if(!StringUtils.isNullOrEmpty(evidenceModel.getCreateDate())) {			
			evidenceDTO.setCreateDate(convertDateStringToSQL(evidenceModel.getCreateDate().split(" ")[0]));
			evidenceDTO.setCreateTime(convertTimeStringToSQL(evidenceModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(evidenceModel.getUpdateDate())) {
			evidenceDTO.setUpdateDate(convertDateStringToSQL(evidenceModel.getUpdateDate().split(" ")[0]));
			evidenceDTO.setUpdateTime(convertTimeStringToSQL(evidenceModel.getUpdateDate()));
		}
		
		return evidenceDTO;
	}
	
	public static EvidenceTypeDTO transEvidenceTypeModel(EvidenceTypeModel evidenceTypeModel) {
		EvidenceTypeDTO evidenceTypeDTO = new EvidenceTypeDTO();
		evidenceTypeDTO.setEvidenceTypeId(NullUtils.cvInt(evidenceTypeModel.getEvidenceTypeId()));
		evidenceTypeDTO.setEvidenceTypeName(NullUtils.cvStr(evidenceTypeModel.getEvidenceTypeName()));
		
		evidenceTypeDTO.setCreateBy(NullUtils.cvInt(evidenceTypeModel.getCreateBy()));
		evidenceTypeDTO.setEnable(NullUtils.cvChar(evidenceTypeModel.getEnable()));
		evidenceTypeDTO.setUpdateBy(NullUtils.cvInt(evidenceTypeModel.getUpdateBy()));
		
		if(!StringUtils.isNullOrEmpty(evidenceTypeModel.getCreateDate())) {			
			evidenceTypeDTO.setCreateDate(convertDateStringToSQL(evidenceTypeModel.getCreateDate().split(" ")[0]));
			evidenceTypeDTO.setCreateTime(convertTimeStringToSQL(evidenceTypeModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(evidenceTypeModel.getUpdateDate())) {
			evidenceTypeDTO.setUpdateDate(convertDateStringToSQL(evidenceTypeModel.getUpdateDate().split(" ")[0]));
			evidenceTypeDTO.setUpdateTime(convertTimeStringToSQL(evidenceTypeModel.getUpdateDate()));
		}
		
		return evidenceTypeDTO;
	}
	
	public static FinalAuditResultDTO transFinalAuditResultModel(FinalAuditResultModel finalAuditResultModel) {
		FinalAuditResultDTO finalAuditResultDTO = new FinalAuditResultDTO();
		finalAuditResultDTO.setApprovalName(NullUtils.cvStr(finalAuditResultModel.getApprovalName()));
		finalAuditResultDTO.setAuditor(NullUtils.cvStr(finalAuditResultModel.getAuditor()));
		finalAuditResultDTO.setAuditType(NullUtils.cvInt(finalAuditResultModel.getAuditType()));
		finalAuditResultDTO.setCarDetailData(NullUtils.cvStr(finalAuditResultModel.getCarDetailData()));
		finalAuditResultDTO.setChecklistPlanNo(NullUtils.cvStr(finalAuditResultModel.getChecklistPlanNo()));
		
		finalAuditResultDTO.setConclude(NullUtils.cvStr(finalAuditResultModel.getConclude()));
		finalAuditResultDTO.setGrade(NullUtils.cvChar(finalAuditResultModel.getGrade()));
		finalAuditResultDTO.setPass(NullUtils.cvChar(finalAuditResultModel.getPass()));
		
		finalAuditResultDTO.setProductTypeName(NullUtils.cvStr(finalAuditResultModel.getProductTypeName()));
		//finalAuditResultDTO.setSignatureOfSupplier(finalAuditResultModel.getSignatureOfSupplier());
		finalAuditResultDTO.setSupplierData(NullUtils.cvStr(finalAuditResultModel.getSupplierData()));
		finalAuditResultDTO.setSupplierProductAddressMappingData(NullUtils.cvStr(finalAuditResultModel.getSupplierProductAddressMappingData()));
		
		
		
		if(finalAuditResultModel.getChecklistPlanId() != null) {
			finalAuditResultDTO.setChecklistPlanId(transChecklistPlanModel(finalAuditResultModel.getChecklistPlanId()));
		}
		
		if(finalAuditResultModel.getCarId() != null) {
			finalAuditResultDTO.setCarId(transCarModel(finalAuditResultModel.getCarId()));
		}
		
		if(finalAuditResultModel.getFinalAuditResultStatusId() != null) {
			finalAuditResultDTO.setFinalAuditResultStatusId(transFinalAuditResultStatusModel(finalAuditResultModel.getFinalAuditResultStatusId()));
		}
		
		if(finalAuditResultModel.getLocationId() != null) {
			finalAuditResultDTO.setLocationId(transSupplierProductAddressMappingModel(finalAuditResultModel.getLocationId()));
		}
		
		if(finalAuditResultModel.getProductTypeId() != null) {
			finalAuditResultDTO.setProductTypeId(transProductTypeModel(finalAuditResultModel.getProductTypeId()));
		}
		
		if(finalAuditResultModel.getSupplierId() != null) {
			finalAuditResultDTO.setSupplierId(transSupplierModel(finalAuditResultModel.getSupplierId()));
		}
		
		if(!StringUtils.isNullOrEmpty(finalAuditResultModel.getCompleteDate())) {
			finalAuditResultDTO.setUpdateDate(convertDateStringToSQL(finalAuditResultModel.getCompleteDate().split(" ")[0]));
			finalAuditResultDTO.setUpdateTime(convertTimeStringToSQL(finalAuditResultModel.getCompleteDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(finalAuditResultModel.getPlanDate())) {
			finalAuditResultDTO.setUpdateDate(convertDateStringToSQL(finalAuditResultModel.getPlanDate().split(" ")[0]));
			finalAuditResultDTO.setUpdateTime(convertTimeStringToSQL(finalAuditResultModel.getPlanDate()));
		}
		
		finalAuditResultDTO.setCreateBy(NullUtils.cvInt(finalAuditResultModel.getCreateBy()));
		finalAuditResultDTO.setEnable(NullUtils.cvChar(finalAuditResultModel.getEnable()));
		finalAuditResultDTO.setUpdateBy(NullUtils.cvInt(finalAuditResultModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(finalAuditResultModel.getCreateDate())) {			
			finalAuditResultDTO.setCreateDate(convertDateStringToSQL(finalAuditResultModel.getCreateDate().split(" ")[0]));
			finalAuditResultDTO.setCreateTime(convertTimeStringToSQL(finalAuditResultModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(finalAuditResultModel.getUpdateDate())) {
			finalAuditResultDTO.setUpdateDate(convertDateStringToSQL(finalAuditResultModel.getUpdateDate().split(" ")[0]));
			finalAuditResultDTO.setUpdateTime(convertTimeStringToSQL(finalAuditResultModel.getUpdateDate()));
		}
		
		return finalAuditResultDTO;
	}
	
	public static FinalAuditResultStatusDTO transFinalAuditResultStatusModel(FinalAuditResultStatusModel finalAuditResultStatusModel) {
		FinalAuditResultStatusDTO finalAuditResultStatusDTO = new FinalAuditResultStatusDTO();
		
		finalAuditResultStatusDTO.setFinalAuditResultStatusId(NullUtils.cvInt(finalAuditResultStatusModel.getFinalAuditResultStatusId()));
		finalAuditResultStatusDTO.setFinalAuditResultStatusName(NullUtils.cvStr(finalAuditResultStatusModel.getFinalAuditResultStatusName()));
		finalAuditResultStatusDTO.setFinalAuditResultStatusColor(NullUtils.cvStr(finalAuditResultStatusModel.getFinalAuditResultStatusColor()));
		
		finalAuditResultStatusDTO.setCreateBy(NullUtils.cvInt(finalAuditResultStatusModel.getCreateBy()));
		finalAuditResultStatusDTO.setEnable(NullUtils.cvChar(finalAuditResultStatusModel.getEnable()));
		finalAuditResultStatusDTO.setUpdateBy(NullUtils.cvInt(finalAuditResultStatusModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(finalAuditResultStatusModel.getCreateDate())) {			
			finalAuditResultStatusDTO.setCreateDate(convertDateStringToSQL(finalAuditResultStatusModel.getCreateDate().split(" ")[0]));
			finalAuditResultStatusDTO.setCreateTime(convertTimeStringToSQL(finalAuditResultStatusModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(finalAuditResultStatusModel.getUpdateDate())) {
			finalAuditResultStatusDTO.setUpdateDate(convertDateStringToSQL(finalAuditResultStatusModel.getUpdateDate().split(" ")[0]));
			finalAuditResultStatusDTO.setUpdateTime(convertTimeStringToSQL(finalAuditResultStatusModel.getUpdateDate()));
		}
		
		
		return finalAuditResultStatusDTO;
	}
	
	public static GradeDTO transGradeModel(GradeModel gradeModel) {
		GradeDTO gradeDTO = new GradeDTO();
		gradeDTO.setGradeId(NullUtils.cvChar(gradeModel.getGradeId()));
		
		gradeDTO.setChecklistTypeId(new ArrayList<>());
		if(gradeModel.getChecklistTypeId() != null) {
			if(!gradeModel.getChecklistTypeId().isEmpty()) {
				for(ChecklistTypeModel checklistType : gradeModel.getChecklistTypeId()) {
					gradeDTO.getChecklistTypeId().add(transChecklistTypeModel(checklistType));
				}
			}
		}
		
		gradeDTO.setCreateBy(NullUtils.cvInt(gradeModel.getCreateBy()));
		gradeDTO.setEnable(NullUtils.cvChar(gradeModel.getEnable()));
		gradeDTO.setUpdateBy(NullUtils.cvInt(gradeModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(gradeModel.getCreateDate())) {			
			gradeDTO.setCreateDate(convertDateStringToSQL(gradeModel.getCreateDate().split(" ")[0]));
			gradeDTO.setCreateTime(convertTimeStringToSQL(gradeModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(gradeModel.getUpdateDate())) {
			gradeDTO.setUpdateDate(convertDateStringToSQL(gradeModel.getUpdateDate().split(" ")[0]));
			gradeDTO.setUpdateTime(convertTimeStringToSQL(gradeModel.getUpdateDate()));
		}
		return gradeDTO;
	}
	
	public static ProductTypeDTO transProductTypeModel(ProductTypeModel productTypeModel) {
		ProductTypeDTO productTypeDTO = new ProductTypeDTO();
		productTypeDTO.setProductTypeId(NullUtils.cvInt(productTypeModel.getProductTypeId()));
		productTypeDTO.setName(NullUtils.cvStr(productTypeModel.getName()));
		
		if(productTypeModel.getChecklistId() != null) {
			productTypeDTO.setChecklistId(transChecklistModel(productTypeModel.getChecklistId()));
		}
		
		productTypeDTO.setSupplierProductAddressMappingDTO(new ArrayList<>());
		if(productTypeModel.getSupplierProductAddressMappingModel() != null) {
			if(!productTypeModel.getSupplierProductAddressMappingModel().isEmpty()) {
				for(SupplierProductAddressMappingModel supplierProductAddressMappingModel : productTypeModel.getSupplierProductAddressMappingModel()) {
					productTypeDTO.getSupplierProductAddressMappingDTO().add(transSupplierProductAddressMappingModel(supplierProductAddressMappingModel));
				}
			}
		}
		
		productTypeDTO.setCreateBy(NullUtils.cvInt(productTypeModel.getCreateBy()));
		productTypeDTO.setEnable(NullUtils.cvChar(productTypeModel.getEnable()));
		productTypeDTO.setUpdateBy(NullUtils.cvInt(productTypeModel.getUpdateBy()));
		
		if(!StringUtils.isNullOrEmpty(productTypeModel.getCreateDate())) {			
			productTypeDTO.setCreateDate(convertDateStringToSQL(productTypeModel.getCreateDate().split(" ")[0]));
			productTypeDTO.setCreateTime(convertTimeStringToSQL(productTypeModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(productTypeModel.getUpdateDate())) {
			productTypeDTO.setUpdateDate(convertDateStringToSQL(productTypeModel.getUpdateDate().split(" ")[0]));
			productTypeDTO.setUpdateTime(convertTimeStringToSQL(productTypeModel.getUpdateDate()));
		}
		return productTypeDTO;
	}
	
	
	public static QuestionTypeDTO transQuestionTypeModel(QuestionTypeModel questionTypeModel) {
		QuestionTypeDTO questionTypeDTO = new QuestionTypeDTO();
		questionTypeDTO.setQuestionTypeId(NullUtils.cvInt(questionTypeModel.getQuestionTypeId()));
		questionTypeDTO.setName(NullUtils.cvStr(questionTypeModel.getName()));		
		
		questionTypeDTO.setAnswerId(new ArrayList<>());			
		if(questionTypeModel.getAnswerId() != null) {
			if(!questionTypeModel.getAnswerId().isEmpty()) {
				for(AnswerModel answer : questionTypeModel.getAnswerId()) {
					questionTypeDTO.getAnswerId().add(transAnswerModel(answer));
				}
			}
		}
		
		questionTypeDTO.setEvalFormId(new ArrayList<>());
		if(questionTypeModel.getEvalFormId() != null) {
			if(!questionTypeModel.getEvalFormId().isEmpty()) {
				for(EvalFormModel evalFormModel : questionTypeModel.getEvalFormId()) {
					questionTypeDTO.getEvalFormId().add(transEvalFormModel(evalFormModel));
				}
			}
		}
		
		questionTypeDTO.setChecklistTypeId(new ArrayList<>());
		if(questionTypeModel.getChecklistTypeId() != null) {
			if(!questionTypeModel.getChecklistTypeId().isEmpty()) {
				for(ChecklistTypeModel checklistType : questionTypeModel.getChecklistTypeId()) {
					questionTypeDTO.getChecklistTypeId().add(transChecklistTypeModel(checklistType));
				}
			}
		}
		
		questionTypeDTO.setCreateBy(NullUtils.cvInt(questionTypeModel.getCreateBy()));
		questionTypeDTO.setEnable(NullUtils.cvChar(questionTypeModel.getEnable()));
		questionTypeDTO.setUpdateBy(NullUtils.cvInt(questionTypeModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(questionTypeModel.getCreateDate())) {			
			questionTypeDTO.setCreateDate(convertDateStringToSQL(questionTypeModel.getCreateDate().split(" ")[0]));
			questionTypeDTO.setCreateTime(convertTimeStringToSQL(questionTypeModel.getCreateDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(questionTypeModel.getUpdateDate())) {
			questionTypeDTO.setUpdateDate(convertDateStringToSQL(questionTypeModel.getUpdateDate().split(" ")[0]));
			questionTypeDTO.setUpdateTime(convertTimeStringToSQL(questionTypeModel.getUpdateDate()));
		}
		
		return questionTypeDTO;
	}
	
	
	public static StandardDocumentDTO transStandardDocumentModel(StandardDocumentModel standardDocumentModel) {
		StandardDocumentDTO standardDocumentDTO = new StandardDocumentDTO();
		standardDocumentDTO.setStandardDocumentId(NullUtils.cvInt(standardDocumentModel.getStandardDocumentId()));
		standardDocumentDTO.setStandardDocumentName(NullUtils.cvStr(standardDocumentModel.getStandardDocumentName()));
				
		standardDocumentDTO.setCreateBy(NullUtils.cvInt(standardDocumentModel.getCreateBy()));
		standardDocumentDTO.setEnable(NullUtils.cvChar(standardDocumentModel.getEnable()));
		standardDocumentDTO.setUpdateBy(NullUtils.cvInt(standardDocumentModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(standardDocumentModel.getCreateDate())) {			
			standardDocumentDTO.setCreateDate(convertDateStringToSQL(standardDocumentModel.getCreateDate().split(" ")[0]));
			standardDocumentDTO.setCreateTime(convertTimeStringToSQL(standardDocumentModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(standardDocumentModel.getUpdateDate())) {
			standardDocumentDTO.setUpdateDate(convertDateStringToSQL(standardDocumentModel.getUpdateDate().split(" ")[0]));
			standardDocumentDTO.setUpdateTime(convertTimeStringToSQL(standardDocumentModel.getUpdateDate()));
		}
		
		return standardDocumentDTO;
	}
	
	
	public static ManualDocumentDTO transManualDocumentModel(ManualDocumentModel ManualDocumentModel) {
		ManualDocumentDTO ManualDocumentDTO = new ManualDocumentDTO();
		ManualDocumentDTO.setDocumentid(NullUtils.cvInt(ManualDocumentModel.getDocumentid()));
		ManualDocumentDTO.setDocumentlocation(NullUtils.cvStr(ManualDocumentModel.getDocumentlocation()));
		ManualDocumentDTO.setDocument_type(NullUtils.cvStr(ManualDocumentModel.getDocument_type()));
		ManualDocumentDTO.setCreateBy(NullUtils.cvInt(ManualDocumentModel.getCreateBy()));		
		ManualDocumentDTO.setUpdateBy(NullUtils.cvInt(ManualDocumentModel.getUpdateBy()));
		
		return ManualDocumentDTO;
	}
	
	
	public static DocumentDTO transDocumentModel(DocumentModel DocumentModel) {
		DocumentDTO DocumentDTO = new DocumentDTO();
		DocumentDTO.setDocumentId(NullUtils.cvInt(DocumentModel.getDocumentId()));
		DocumentDTO.setDocumentName(NullUtils.cvStr(DocumentModel.getDocumentName()));
		DocumentDTO.setSeq(NullUtils.cvInt(DocumentModel.getSeq()));	
		DocumentDTO.setEnable(NullUtils.cvChar(DocumentModel.getEnable()));
//		DocumentDTO.setEnable(NullUtils.cvChar(DocumentDTO.getEnable()));
		DocumentDTO.setCreateBy(NullUtils.cvInt(DocumentModel.getCreateBy()));
		
		DocumentDTO.setUpdateBy(NullUtils.cvInt(DocumentModel.getUpdateBy()));
		
		return DocumentDTO;
	}
	
	public static SupplierDTO transSupplierModel(SupplierModel supplierModel) {
		SupplierDTO supplierDTO = new SupplierDTO();		
		
		supplierDTO.setIsGreenCard(NullUtils.cvChar(supplierModel.getIsGreenCard()));
		supplierDTO.setLogo(NullUtils.cvStr(supplierModel.getLogo()));
		supplierDTO.setSupplierCode(NullUtils.cvStr(supplierModel.getSupplierCode()));
		supplierDTO.setSupplierCompany(NullUtils.cvStr(supplierModel.getSupplierCompany()));
		supplierDTO.setSupplierId(NullUtils.cvInt(supplierModel.getSupplierId()));
		supplierDTO.setApproval(NullUtils.cvChar(supplierModel.getApproval()));
		supplierDTO.setAuditRound(NullUtils.cvInt(supplierModel.getAuditRound()));
		
		if(supplierModel.getAddressId() != null) {
			supplierDTO.setAddressId(transAddressModel(supplierModel.getAddressId()));
		}
		
		if(supplierModel.getSupplierProductAddressMappingModel() != null) {
			supplierDTO.setSupplierProductAddressMappingDTO(transSupplierProductAddressMappingModel(supplierModel.getSupplierProductAddressMappingModel()));
		}
		
		supplierDTO.setUserId(new ArrayList<>());		
		if(supplierModel.getUserId() != null) {
			if(!supplierModel.getUserId().isEmpty()) {
				for(UserModel userModel : supplierModel.getUserId()) {
					supplierDTO.getUserId().add(transUserModel(userModel));
				}
			}
		}
		
		if(!StringUtils.isNullOrEmpty(supplierModel.getGreenCardExpireDate())) {
			supplierDTO.setGreenCardExpireDate(convertDateStringToSQL(supplierModel.getGreenCardExpireDate().split(" ")[0].trim()));
			supplierDTO.setGreenCardExpireTime(convertTimeStringToSQL(supplierModel.getGreenCardExpireDate()));
		}
			
		
		supplierDTO.setCreateBy(NullUtils.cvInt(supplierModel.getCreateBy()));
		supplierDTO.setEnable(NullUtils.cvChar(supplierModel.getEnable()));
		supplierDTO.setUpdateBy(NullUtils.cvInt(supplierModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(supplierModel.getCreateDate())) {			
			supplierDTO.setCreateDate(convertDateStringToSQL(supplierModel.getCreateDate().split(" ")[0]));
			supplierDTO.setCreateTime(convertTimeStringToSQL(supplierModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(supplierModel.getUpdateDate())) {
			supplierDTO.setUpdateDate(convertDateStringToSQL(supplierModel.getUpdateDate().split(" ")[0]));
			supplierDTO.setUpdateTime(convertTimeStringToSQL(supplierModel.getUpdateDate()));
		}
		
		return supplierDTO;
	}
	
		
	public static SupplierProductAddressMappingDTO transSupplierProductAddressMappingModel(SupplierProductAddressMappingModel supplierProductAddressMappingModel) {
		SupplierProductAddressMappingDTO supplierProductAddressMappingDTO = new SupplierProductAddressMappingDTO();
		
		supplierProductAddressMappingDTO.setId(NullUtils.cvInt(supplierProductAddressMappingModel.getId()));
		supplierProductAddressMappingDTO.setLocationName(NullUtils.cvStr(supplierProductAddressMappingModel.getLocationName()));
		
		if(supplierProductAddressMappingModel.getSupplierId() != null) {
			supplierProductAddressMappingDTO.setSupplierId(transSupplierModel(supplierProductAddressMappingModel.getSupplierId()));
		}
		
		if(supplierProductAddressMappingModel.getAddressId() != null) {
			supplierProductAddressMappingDTO.setAddressId(transAddressModel(supplierProductAddressMappingModel.getAddressId()));
		}
		
		supplierProductAddressMappingDTO.setProductTypeId(new ArrayList<>());
		if(supplierProductAddressMappingModel.getProductTypeId() != null) {
			if(!supplierProductAddressMappingModel.getProductTypeId().isEmpty()) {
				for(ProductTypeModel productTypeModel : supplierProductAddressMappingModel.getProductTypeId()) {
					supplierProductAddressMappingDTO.getProductTypeId().add(transProductTypeModel(productTypeModel));
				}
			}
		}
		
		supplierProductAddressMappingDTO.setCreateBy(NullUtils.cvInt(supplierProductAddressMappingModel.getCreateBy()));
		supplierProductAddressMappingDTO.setEnable(NullUtils.cvChar(supplierProductAddressMappingModel.getEnable()));
		supplierProductAddressMappingDTO.setUpdateBy(NullUtils.cvInt(supplierProductAddressMappingModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(supplierProductAddressMappingModel.getCreateDate())) {			
			supplierProductAddressMappingDTO.setCreateDate(convertDateStringToSQL(supplierProductAddressMappingModel.getCreateDate().split(" ")[0]));
			supplierProductAddressMappingDTO.setCreateTime(convertTimeStringToSQL(supplierProductAddressMappingModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(supplierProductAddressMappingModel.getUpdateDate())) {
			supplierProductAddressMappingDTO.setUpdateDate(convertDateStringToSQL(supplierProductAddressMappingModel.getUpdateDate().split(" ")[0]));
			supplierProductAddressMappingDTO.setUpdateTime(convertTimeStringToSQL(supplierProductAddressMappingModel.getUpdateDate()));
		}
		
		return supplierProductAddressMappingDTO;
	}
	
	
	public static SupplierStandardDocumentDTO transSupplierStandardDocumentModel(SupplierStandardDocumentModel supplierStandardDocumentModel) {
		SupplierStandardDocumentDTO standardDocumentDTO = new SupplierStandardDocumentDTO();
		
		standardDocumentDTO.setSupplierStandardDocumentId(NullUtils.cvInt(supplierStandardDocumentModel.getSupplierStandardDocumentId()));
		standardDocumentDTO.setSupplierStandardDocumentLocation(NullUtils.cvStr(supplierStandardDocumentModel.getSupplierStandardDocumentLocation()));
		
		if(supplierStandardDocumentModel.getStandardDocumentId() != null) {
			standardDocumentDTO.setStandardDocumentId(transStandardDocumentModel(supplierStandardDocumentModel.getStandardDocumentId()));
		}
		
		if(supplierStandardDocumentModel.getSupplierId() != null) {
			standardDocumentDTO.setSupplierId(transSupplierModel(supplierStandardDocumentModel.getSupplierId()));
		}
		
		if(!StringUtils.isNullOrEmpty(supplierStandardDocumentModel.getSupplierStandardDocumentExpireDate())) {
			standardDocumentDTO.setSupplierStandardDocumentExpireDate(convertDateStringToSQL(supplierStandardDocumentModel.getSupplierStandardDocumentExpireDate().split(" ")[0]));
			standardDocumentDTO.setSupplierStandardDocumentExpireTime(convertTimeStringToSQL(supplierStandardDocumentModel.getSupplierStandardDocumentExpireDate()));
		}
		
		standardDocumentDTO.setCreateBy(NullUtils.cvInt(supplierStandardDocumentModel.getCreateBy()));
		standardDocumentDTO.setEnable(NullUtils.cvChar(supplierStandardDocumentModel.getEnable()));
		standardDocumentDTO.setUpdateBy(NullUtils.cvInt(supplierStandardDocumentModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(supplierStandardDocumentModel.getCreateDate())) {			
			standardDocumentDTO.setCreateDate(convertDateStringToSQL(supplierStandardDocumentModel.getCreateDate().split(" ")[0]));
			standardDocumentDTO.setCreateTime(convertTimeStringToSQL(supplierStandardDocumentModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(supplierStandardDocumentModel.getUpdateDate())) {
			standardDocumentDTO.setUpdateDate(convertDateStringToSQL(supplierStandardDocumentModel.getUpdateDate().split(" ")[0]));
			standardDocumentDTO.setUpdateTime(convertTimeStringToSQL(supplierStandardDocumentModel.getUpdateDate()));
		}
				
		return standardDocumentDTO;
	}
	
	public static SupplierUserMappingDTO transSupplierUserMappingModel(SupplierUserMappingModel supplierUserMappingModel) {
		SupplierUserMappingDTO supplierUserMappingDTO = new SupplierUserMappingDTO();
		supplierUserMappingDTO.setSupplierId(NullUtils.cvInt(supplierUserMappingModel.getSupplierId()));
		supplierUserMappingDTO.setUserId(NullUtils.cvInt(supplierUserMappingModel.getUserId()));
		
		supplierUserMappingDTO.setCreateBy(NullUtils.cvInt(supplierUserMappingModel.getCreateBy()));
		supplierUserMappingDTO.setEnable(NullUtils.cvChar(supplierUserMappingModel.getEnable()));
		supplierUserMappingDTO.setUpdateBy(NullUtils.cvInt(supplierUserMappingModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(supplierUserMappingModel.getCreateDate())) {			
			supplierUserMappingDTO.setCreateDate(convertDateStringToSQL(supplierUserMappingModel.getCreateDate().split(" ")[0]));
			supplierUserMappingDTO.setCreateTime(convertTimeStringToSQL(supplierUserMappingModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(supplierUserMappingModel.getUpdateDate())) {
			supplierUserMappingDTO.setUpdateDate(convertDateStringToSQL(supplierUserMappingModel.getUpdateDate().split(" ")[0]));
			supplierUserMappingDTO.setUpdateTime(convertTimeStringToSQL(supplierUserMappingModel.getUpdateDate()));
		}
		
		return supplierUserMappingDTO;
	}
	
	
	public static SystemConfigurationDTO transSystemConfigurationModel(SystemConfigurationModel systemConfigurationModel) {
		SystemConfigurationDTO systemConfigurationDTO = new  SystemConfigurationDTO();
		systemConfigurationDTO.setSystemKey(systemConfigurationModel.getSystemKey());
		systemConfigurationDTO.setSystemValue(systemConfigurationModel.getSystemValue());
		
		systemConfigurationDTO.setCreateBy(NullUtils.cvInt(systemConfigurationModel.getCreateBy()));
		systemConfigurationDTO.setEnable(NullUtils.cvChar(systemConfigurationModel.getEnable()));
		systemConfigurationDTO.setUpdateBy(NullUtils.cvInt(systemConfigurationModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(systemConfigurationModel.getCreateDate())) {			
			systemConfigurationDTO.setCreateDate(convertDateStringToSQL(systemConfigurationModel.getCreateDate().split(" ")[0]));
			systemConfigurationDTO.setCreateTime(convertTimeStringToSQL(systemConfigurationModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(systemConfigurationModel.getUpdateDate())) {
			systemConfigurationDTO.setUpdateDate(convertDateStringToSQL(systemConfigurationModel.getUpdateDate().split(" ")[0]));
			systemConfigurationDTO.setUpdateTime(convertTimeStringToSQL(systemConfigurationModel.getUpdateDate()));
		}
		return systemConfigurationDTO;
	}
	
	public static SystemSequenceDTO transSystemSequenceModel(SystemSequenceModel systemSequenceModel) {
		SystemSequenceDTO systemSequenceDTO = new SystemSequenceDTO();
		
		systemSequenceDTO.setSeqKey(NullUtils.cvStr(systemSequenceModel.getSeqKey()));
		systemSequenceDTO.setSeqValue(NullUtils.cvInt(systemSequenceModel.getSeqValue()));
		systemSequenceDTO.setSeqDigits(NullUtils.cvInt(systemSequenceModel.getSeqDigits()));
		systemSequenceDTO.setUseKeyPrefix(NullUtils.cvChar(systemSequenceModel.getUseKeyPrefix()));
		systemSequenceDTO.setDelimit(NullUtils.cvChar(systemSequenceModel.getDelimit()));
		systemSequenceDTO.setSeqDescription(NullUtils.cvStr(systemSequenceModel.getSeqDescription()));
		
		systemSequenceDTO.setCreateBy(NullUtils.cvInt(systemSequenceModel.getCreateBy()));
		systemSequenceDTO.setEnable(NullUtils.cvChar(systemSequenceModel.getEnable()));
		systemSequenceDTO.setUpdateBy(NullUtils.cvInt(systemSequenceModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(systemSequenceModel.getCreateDate())) {			
			systemSequenceDTO.setCreateDate(convertDateStringToSQL(systemSequenceModel.getCreateDate().split(" ")[0]));
			systemSequenceDTO.setCreateTime(convertTimeStringToSQL(systemSequenceModel.getCreateDate()));
		}		
		if(!StringUtils.isNullOrEmpty(systemSequenceModel.getUpdateDate())) {
			systemSequenceDTO.setUpdateDate(convertDateStringToSQL(systemSequenceModel.getUpdateDate().split(" ")[0]));
			systemSequenceDTO.setUpdateTime(convertTimeStringToSQL(systemSequenceModel.getUpdateDate()));
		}
		
		return systemSequenceDTO;
	}
	
	public static UserDTO transUserModel(UserModel userModel) {
		UserDTO userDTO = new UserDTO();			
		userDTO.setDescription(NullUtils.cvStr(userModel.getDescription()));
		userDTO.setEmail(NullUtils.cvStr(userModel.getEmail()));
		userDTO.setEmployeeId(NullUtils.cvStr(userModel.getEmployeeId()));		
		userDTO.setFullname(NullUtils.cvStr(userModel.getFullname()));
		userDTO.setPassword(NullUtils.cvStr(userModel.getPassword()));
		userDTO.setTelephone(NullUtils.cvStr(userModel.getTelephone()));			
		userDTO.setUserId(NullUtils.cvInt(userModel.getUserId()));
		userDTO.setUsername(NullUtils.cvStr(userModel.getUsername()));
	
		
		if(userModel.getUserGroupId() != null) {
			userDTO.setUserGroupId(transUserGroupModel(userModel.getUserGroupId()));
		}	
		
		userDTO.setSupplierId(new ArrayList<>());
		if(userModel.getSupplierId() != null) {
			if(!userModel.getSupplierId().isEmpty()) {
				for(SupplierModel supplier : userModel.getSupplierId()) {
					userDTO.getSupplierId().add(transSupplierModel(supplier));
				}
			}
		}
		
		userDTO.setUpdateBy(NullUtils.cvInt(userModel.getUpdateBy()));	
		userDTO.setEnable(NullUtils.cvChar(userModel.getEnable()));
		userDTO.setCreateBy(NullUtils.cvInt(userModel.getCreateBy()));
		if(!StringUtils.isNullOrEmpty(userModel.getCreateDate())) {			
			userDTO.setCreateDate(convertDateStringToSQL(userModel.getCreateDate().split(" ")[0]));
			userDTO.setCreateTime(convertTimeStringToSQL(userModel.getCreateDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(userModel.getUpdateDate())) {
			userDTO.setUpdateDate(convertDateStringToSQL(userModel.getUpdateDate().split(" ")[0]));
			userDTO.setUpdateTime(convertTimeStringToSQL(userModel.getUpdateDate()));
		}
		
		return userDTO;
	}
	
	public static UserGroupDTO transUserGroupModel(UserGroupModel userGroupModel) {
		UserGroupDTO userGroupDTO = new UserGroupDTO();
		userGroupDTO.setAuthenType(NullUtils.cvStr(userGroupModel.getAuthenType()));		
		userGroupDTO.setUserGroupId(NullUtils.cvInt(userGroupModel.getUserGroupId()));
		userGroupDTO.setUserGroupName(NullUtils.cvStr(userGroupModel.getUserGroupName()));
		
		userGroupDTO.setUserId(new ArrayList<>());
		if(userGroupModel.getUserId() != null) {
			if(!userGroupModel.getUserId().isEmpty()) {
				for(UserModel user : userGroupModel.getUserId()) {
					userGroupDTO.getUserId().add(transUserModel(user));
				}
			}
		}
		
		userGroupDTO.setUserGroupMenuId(new ArrayList<>());
		if(userGroupModel.getUserGroupMenuId() != null) {
			if(!userGroupModel.getUserGroupMenuId().isEmpty()) {
				for(MenuModel userGroupMenuModel : userGroupModel.getUserGroupMenuId()) {
					userGroupDTO.getUserGroupMenuId().add(transUserGroupMenuModel(userGroupMenuModel));
				}
			}
		}
		
		userGroupDTO.setCreateBy(NullUtils.cvInt(userGroupModel.getCreateBy()));
		userGroupDTO.setEnable(NullUtils.cvChar(userGroupModel.getEnable()));
		userGroupDTO.setUpdateBy(NullUtils.cvInt(userGroupModel.getUpdateBy()));
		if(!StringUtils.isNullOrEmpty(userGroupModel.getCreateDate())) {			
			userGroupDTO.setCreateDate(convertDateStringToSQL(userGroupModel.getCreateDate().split(" ")[0]));
			userGroupDTO.setCreateTime(convertTimeStringToSQL(userGroupModel.getCreateDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(userGroupModel.getUpdateDate())) {
			userGroupDTO.setUpdateDate(convertDateStringToSQL(userGroupModel.getUpdateDate().split(" ")[0]));
			userGroupDTO.setUpdateTime(convertTimeStringToSQL(userGroupModel.getUpdateDate()));
		}
		
		return userGroupDTO;
	}
	
	public static MenuDTO transUserGroupMenuModel(MenuModel userGroupMenuModel) {
		MenuDTO userGroupMenuDTO = new MenuDTO();
		userGroupMenuDTO.setCreateBy(NullUtils.cvInt(userGroupMenuModel.getCreateBy()));
		userGroupMenuDTO.setEnable(NullUtils.cvChar(userGroupMenuModel.getEnable()));
		userGroupMenuDTO.setMenuIcon(NullUtils.cvStr(userGroupMenuModel.getMenuIcon()));
		userGroupMenuDTO.setMenuName(NullUtils.cvStr(userGroupMenuModel.getMenuName()));
		userGroupMenuDTO.setMenuOrder(NullUtils.cvInt(userGroupMenuModel.getMenuOrder()));
		userGroupMenuDTO.setMenuSubOrder(NullUtils.cvInt(userGroupMenuModel.getMenuSubOrder()));
		userGroupMenuDTO.setMenuUrl(NullUtils.cvStr(userGroupMenuModel.getMenuUrl()));
		userGroupMenuDTO.setUpdateBy(NullUtils.cvInt(userGroupMenuModel.getUpdateBy()));
		userGroupMenuDTO.setMenuId(NullUtils.cvInt(userGroupMenuModel.getMenuId()));
		userGroupMenuDTO.setMenuParentId(NullUtils.cvInt(userGroupMenuModel.getMenuParentId()));
		userGroupMenuDTO.setCoutSubMenu(NullUtils.cvInt(userGroupMenuModel.getCountSubMenu()));
		
		userGroupMenuDTO.setUserGroupId(new ArrayList<>());
		if(userGroupMenuModel.getUserGroupId() != null) {
			if(!userGroupMenuModel.getUserGroupId().isEmpty()) {
				for(UserGroupModel userGroupModel : userGroupMenuModel.getUserGroupId()) {
					userGroupMenuDTO.getUserGroupId().add(transUserGroupModel(userGroupModel));
				}
			}
		}
		
		if(!StringUtils.isNullOrEmpty(userGroupMenuModel.getCreateDate())) {			
			userGroupMenuDTO.setCreateDate(convertDateStringToSQL(userGroupMenuModel.getCreateDate().split(" ")[0]));
			userGroupMenuDTO.setCreateTime(convertTimeStringToSQL(userGroupMenuModel.getCreateDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(userGroupMenuModel.getUpdateDate())) {
			userGroupMenuDTO.setUpdateDate(convertDateStringToSQL(userGroupMenuModel.getUpdateDate().split(" ")[0]));
			userGroupMenuDTO.setUpdateTime(convertTimeStringToSQL(userGroupMenuModel.getUpdateDate()));
		}
		return userGroupMenuDTO;
	}
	
	public static AddressDTO transAddressModel(AddressModel addressModel) {
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddress(NullUtils.cvStr(addressModel.getAddress()));
		addressDTO.setAddressId(NullUtils.cvInt(addressModel.getAddressId()));
		addressDTO.setCreateBy(NullUtils.cvInt(addressModel.getCreateBy()));
		addressDTO.setPostcode(NullUtils.cvStr(addressModel.getPostcode()));
		addressDTO.setEnable(NullUtils.cvChar(addressModel.getEnable()));
		addressDTO.setUpdateBy(NullUtils.cvInt(addressModel.getUpdateBy()));
		
		if(!StringUtils.isNullOrEmpty(addressModel.getCreateDate())) {			
			addressDTO.setCreateDate(convertDateStringToSQL(addressModel.getCreateDate().split(" ")[0]));
			addressDTO.setCreateTime(convertTimeStringToSQL(addressModel.getCreateDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(addressModel.getUpdateDate())) {
			addressDTO.setUpdateDate(convertDateStringToSQL(addressModel.getUpdateDate().split(" ")[0]));
			addressDTO.setUpdateTime(convertTimeStringToSQL(addressModel.getUpdateDate()));
		}
		
		if(addressModel.getSupplierId() != null) {
			addressDTO.setSupplierId(transSupplierModel(addressModel.getSupplierId()));
		}
		
		if(addressModel.getDistrictId() != null) {
			addressDTO.setDistrictId(transDistrictModel(addressModel.getDistrictId()));
		}
		if(addressModel.getProvinceId() != null) {
			addressDTO.setProvinceId(transProvinceModel(addressModel.getProvinceId()));
		}
		if(addressModel.getSubDistrictId() != null) {
			addressDTO.setSubDistrictId(transSubDistrictModel(addressModel.getSubDistrictId()));
		}
		
		if(addressModel.getSupplierProductAddressMappingModel() != null) {
			addressDTO.setSupplierProductAddressMappingDTO(transSupplierProductAddressMappingModel(addressModel.getSupplierProductAddressMappingModel()));
		}
		
		return addressDTO;
	}
	
	
	public static RegionDTO transRegionModel(RegionModel regionModel) {
		RegionDTO regionDTO = new RegionDTO();
		regionDTO.setCreateBy(NullUtils.cvInt(regionModel.getCreateBy()));
		regionDTO.setName(NullUtils.cvStr(regionModel.getName()));
		regionDTO.setRegionId(NullUtils.cvInt(regionModel.getRegionId()));
		regionDTO.setUpdateBy(NullUtils.cvInt(regionModel.getUpdateBy()));
		
		if(!StringUtils.isNullOrEmpty(regionModel.getCreateDate())) {			
			regionDTO.setCreateDate(convertDateStringToSQL(regionModel.getCreateDate().split(" ")[0]));
			regionDTO.setCreateTime(convertTimeStringToSQL(regionModel.getCreateDate()));
		}
		
		if(!StringUtils.isNullOrEmpty(regionModel.getUpdateDate())) {
			regionDTO.setUpdateDate(convertDateStringToSQL(regionModel.getUpdateDate().split(" ")[0]));
			regionDTO.setUpdateTime(convertTimeStringToSQL(regionModel.getUpdateDate()));
		}
		return regionDTO;
	}
	
	public static DistrictDTO transDistrictModel(DistrictModel districtModel) {
		DistrictDTO districtDTO = new DistrictDTO();
		districtDTO.setCreateBy(NullUtils.cvInt(districtModel.getCreateBy()));
		districtDTO.setCreateDate(DateUtils.parseWebDateStringToSQLDate(districtModel.getCreateDate()));
		districtDTO.setDistrictId(NullUtils.cvInt(districtModel.getDistrictId()));
		districtDTO.setName(NullUtils.cvStr(districtModel.getName()));
		districtDTO.setPostcode(NullUtils.cvStr(districtModel.getPostcode()));
		districtDTO.setUpdateBy(NullUtils.cvInt(districtModel.getUpdateBy()));
		districtDTO.setUpdateDate(DateUtils.parseWebDateStringToSQLDate(districtModel.getUpdateDate()));
		
		if(districtModel.getProvinceId() != null) {
			districtDTO.setProvinceId(transProvinceModel(districtModel.getProvinceId()));
		}
		
		if(districtModel.getRegionId() != null) {
			districtDTO.setRegionId(transRegionModel(districtModel.getRegionId()));
		}
		
		return districtDTO;
	}
	
	public static ProvinceDTO transProvinceModel(ProvinceModel provinceModel) {
		ProvinceDTO provinceDTO = new ProvinceDTO();
		provinceDTO.setCreateBy(NullUtils.cvInt(provinceModel.getCreateBy()));
		provinceDTO.setCreateDate(DateUtils.parseWebDateStringToSQLDate(provinceModel.getCreateDate()));
		provinceDTO.setName(NullUtils.cvStr(provinceModel.getName()));
		provinceDTO.setProvinceId(NullUtils.cvInt(provinceModel.getProvinceId()));
		provinceDTO.setUpdateBy(NullUtils.cvInt(provinceModel.getUpdateBy()));
		provinceDTO.setUpdateDate(DateUtils.parseWebDateStringToSQLDate(provinceModel.getUpdateDate()));
		
		if(provinceModel.getRegionId() != null) {
			provinceDTO.setRegionId(transRegionModel(provinceModel.getRegionId()));
		}
		
		return provinceDTO;
	}
	
	public static SubDistrictDTO transSubDistrictModel(SubDistrictModel subDistrictModel) {
		SubDistrictDTO subDistrictDTO = new SubDistrictDTO();
		subDistrictDTO.setCreateBy(NullUtils.cvInt(subDistrictModel.getCreateBy()));
		subDistrictDTO.setCreateDate(DateUtils.parseWebDateStringToSQLDate(subDistrictModel.getCreateDate()));
		subDistrictDTO.setName(NullUtils.cvStr(subDistrictModel.getName()));
		subDistrictDTO.setSubDistrictId(NullUtils.cvInt(subDistrictModel.getSubDistrictId()));
		subDistrictDTO.setUpdateBy(NullUtils.cvInt(subDistrictModel.getUpdateBy()));
		subDistrictDTO.setUpdateDate(DateUtils.parseWebDateStringToSQLDate(subDistrictModel.getUpdateDate()));
		
		if(subDistrictModel.getDistrictId() != null) {
			subDistrictDTO.setDistrictId(transDistrictModel(subDistrictModel.getDistrictId()));
		}
		if(subDistrictModel.getProvinceId() != null) {
			subDistrictDTO.setProvinceId(transProvinceModel(subDistrictModel.getProvinceId()));
		}
		if(subDistrictModel.getRegionId() != null) {
			subDistrictDTO.setRegionId(transRegionModel(subDistrictModel.getRegionId()));
		}
		
		return subDistrictDTO;
	}
	
	public static InformationDTO transInformationModel(InformationModel informationModel) {
		InformationDTO informationDTO = new InformationDTO();
		informationDTO.setInformationId(NullUtils.cvInt(informationModel.getInformationId()));
		informationDTO.setInformationTitle(NullUtils.cvStr(informationModel.getInformationTitle()));
		informationDTO.setInformationType(NullUtils.cvChar(informationModel.getInformationType()));
		informationDTO.setDescription(NullUtils.cvStr(informationModel.getDescription()));
		informationDTO.setGroupType(NullUtils.cvStr(informationModel.getGroupType()));
		if(informationModel.getSupplierId() != null) {
			informationDTO.setSupplierId(transSupplierModel(informationModel.getSupplierId()));
		}

		if(informationModel.getSupplierIdList() != null) {
			String supplierText = "";
			for(int i = 0 ; i < informationModel.getSupplierIdList().size() ; i++) {
				if(i!=0) {
					supplierText += ",";
				}
				supplierText += informationModel.getSupplierIdList().get(i);
			}
			informationDTO.setSupplierList(NullUtils.cvStr(supplierText));
		}
		informationDTO.setCreateBy(NullUtils.cvInt(NullUtils.cvStr(informationModel.getCreateBy())));
		informationDTO.setUpdateBy(NullUtils.cvInt(NullUtils.cvStr(informationModel.getUpdateBy())));
		if(informationModel.getProductTypeId() != null) {
			informationDTO.setProductTypeId(transProductTypeModel(informationModel.getProductTypeId()));
		}
		return informationDTO;
	}

	public static InformationDocumentDTO transInformationDocumentModel(InformationDocumentModel informationDocumentModel) {
		InformationDocumentDTO informationDocumentDTO = new InformationDocumentDTO();
		informationDocumentDTO.setCreateBy(NullUtils.cvInt(NullUtils.cvStr(informationDocumentModel.getCreateBy())));
		informationDocumentDTO.setInformationDocumentLocation(NullUtils.cvStr(informationDocumentModel.getInformationDocumentLocation()));
		informationDocumentDTO.setInformationDocumentType(NullUtils.cvStr(informationDocumentModel.getInformationDocumentType()));
		informationDocumentDTO.setInformationId(transInformationModel(informationDocumentModel.getInformationId()));
		return informationDocumentDTO;
	}

	public static InformationDetailDTO transInformationDetailModel(InformationDetailModel informationDetailModel) {
		InformationDetailDTO informationDetailDTO = new InformationDetailDTO();
		informationDetailDTO.setInformationDetailId(NullUtils.cvInt(informationDetailModel.getInformationDetailId()));
		if(informationDetailModel.getInformationId() != null) {
			informationDetailDTO.setInformationId(transInformationModel(informationDetailModel.getInformationId()));
		}
		if(informationDetailModel.getSupplierId() != null) {
			informationDetailDTO.setSupplierId(transSupplierModel(informationDetailModel.getSupplierId()));
		}
		
		informationDetailDTO.setCreateBy(NullUtils.cvInt(NullUtils.cvStr(informationDetailModel.getCreateBy())));
		informationDetailDTO.setAcceptStatus(NullUtils.cvChar(informationDetailModel.getAcceptStatus()));

		if(informationDetailModel.getAcceptBy() != null) {
			informationDetailDTO.setAcceptBy(transUserModel(informationDetailModel.getAcceptBy()));
		}
		return informationDetailDTO;
	}
	
	public static SystemLogDTO transSystemLogModel(SystemLogModel systemLogModel) {
		SystemLogDTO systemLogDTO = new SystemLogDTO();			
		systemLogDTO.setAccess(NullUtils.cvStr(systemLogModel.getAccess()));
		systemLogDTO.setActivity(NullUtils.cvStr(systemLogModel.getActivity()));
		systemLogDTO.setDetail(NullUtils.cvStr(systemLogModel.getDetail()));	
		systemLogDTO.setCreateBy(NullUtils.cvInt(systemLogModel.getCreateBy()));
		systemLogDTO.setRefId(NullUtils.cvStr(systemLogModel.getRefId()));
		systemLogDTO.setNote(NullUtils.cvStr(systemLogModel.getNote()));
	
		if(!StringUtils.isNullOrEmpty(systemLogModel.getCreateDate())) {			
			systemLogDTO.setCreateDate(convertDateStringToSQL(systemLogModel.getCreateDate().split(" ")[0]));
			systemLogDTO.setCreateTime(convertTimeStringToSQL(systemLogModel.getCreateDate()));
		}
		
		return systemLogDTO;
	}
	
	private static Date convertDateStringToSQL(String date) {
		return DateUtils.parseWebDateStringToSQLDate(date);		
	}
	
	private static Time convertTimeStringToSQL(String time) {
		return DateUtils.parseWebTimeStringToSQLTime(time);
	}
}
