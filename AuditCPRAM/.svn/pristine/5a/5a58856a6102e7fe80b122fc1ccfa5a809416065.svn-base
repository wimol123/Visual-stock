package th.co.gosoft.audit.cpram.utils;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

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
import th.co.gosoft.audit.cpram.dto.MenuDTO;
import th.co.gosoft.audit.cpram.dto.PrivacyDocumentDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.ProvinceDTO;
import th.co.gosoft.audit.cpram.dto.QuestionTypeDTO;
import th.co.gosoft.audit.cpram.dto.QuestionaireDocumentDTO;
import th.co.gosoft.audit.cpram.dto.QuestionaireDocumentTypeDTO;
import th.co.gosoft.audit.cpram.dto.RegionDTO;
import th.co.gosoft.audit.cpram.dto.StandardDocumentDTO;
import th.co.gosoft.audit.cpram.dto.DocumentDTO;
import th.co.gosoft.audit.cpram.dto.SubDistrictDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.dto.SupplierQuestionaireDocumentDTO;
import th.co.gosoft.audit.cpram.dto.SupplierStandardDocumentDTO;
import th.co.gosoft.audit.cpram.dto.SupplierUserMappingDTO;
import th.co.gosoft.audit.cpram.dto.SystemConfigurationDTO;
import th.co.gosoft.audit.cpram.dto.SystemLogDTO;
import th.co.gosoft.audit.cpram.dto.SystemSequenceDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.dto.UserGroupDTO;
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
import th.co.gosoft.audit.cpram.model.MenuModel;
import th.co.gosoft.audit.cpram.model.PrivacyDocumentModel;
import th.co.gosoft.audit.cpram.model.ProductTypeModel;
import th.co.gosoft.audit.cpram.model.ProvinceModel;
import th.co.gosoft.audit.cpram.model.QuestionTypeModel;
import th.co.gosoft.audit.cpram.model.QuestionaireDocumentModel;
import th.co.gosoft.audit.cpram.model.QuestionaireDocumentTypeModel;
import th.co.gosoft.audit.cpram.model.RegionModel;
import th.co.gosoft.audit.cpram.model.StandardDocumentModel;
import th.co.gosoft.audit.cpram.model.SubDistrictModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.SupplierProductAddressMappingModel;
import th.co.gosoft.audit.cpram.model.SupplierQuestionaireDocumentModel;
import th.co.gosoft.audit.cpram.model.SupplierStandardDocumentModel;
import th.co.gosoft.audit.cpram.model.DocumentModel;
import th.co.gosoft.audit.cpram.model.SupplierUserMappingModel;
import th.co.gosoft.audit.cpram.model.SystemConfigurationModel;
import th.co.gosoft.audit.cpram.model.SystemLogModel;
import th.co.gosoft.audit.cpram.model.SystemSequenceModel;
import th.co.gosoft.audit.cpram.model.UserGroupModel;
import th.co.gosoft.audit.cpram.model.UserModel;

public class TransformDTO {
	
	public static AnswerModel transAnswerDTO(AnswerDTO answerDTO) {
		AnswerModel answerModel = new AnswerModel();
		answerModel.setAnswerDetail(answerDTO.getAnswerDetail());
		answerModel.setAnswerId(NullUtils.cvStr(answerDTO.getAnswerId()));
		answerModel.setIsCreateCar(NullUtils.cvStr(answerDTO.getIsCreateCar()));
		
		answerModel.setEvalFormId(new ArrayList<>());		
		if(answerDTO.getEvalFormId() != null) {
			if(!answerDTO.getEvalFormId().isEmpty()) {
				for(EvalFormDTO evalFormDTO : answerDTO.getEvalFormId()) {
					answerModel.getEvalFormId().add(transEvalFormDTO(evalFormDTO));
				}
			}
		}
		
		answerModel.setQuestionTypeId(new ArrayList<>());
		if(answerDTO.getQuestionTypeId() != null) {
			if(!answerDTO.getQuestionTypeId().isEmpty()) {
				for(QuestionTypeDTO question : answerDTO.getQuestionTypeId()) {
					answerModel.getQuestionTypeId().add(transQuestionTypeDTO(question));
				}
			}
			
		}
				
		answerModel.setEnable(NullUtils.cvStr(answerDTO.getEnable()));
		answerModel.setUpdateBy(NullUtils.cvStr(answerDTO.getUpdateBy()));
		answerModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(answerDTO.getCreateDate(), answerDTO.getCreateTime())));
		answerModel.setCreateBy(NullUtils.cvStr(answerDTO.getCreateBy()));
		answerModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(answerDTO.getUpdateDate(), answerDTO.getUpdateTime())));
		
		return answerModel;
	}
	
	
	
	public static AppointHistoryModel transAppointHistoryDTO(AppointHistoryDTO appointHistoryDTO) {
		AppointHistoryModel appointHistoryModel = new AppointHistoryModel();
		appointHistoryModel.setAppointHistoryId(NullUtils.cvStr(appointHistoryDTO.getAppointHistoryId()));		
		appointHistoryModel.setDetail(NullUtils.cvStr(appointHistoryDTO.getDetail()));		
		appointHistoryModel.setTitle(NullUtils.cvStr(appointHistoryDTO.getTitle()));
		
		if(appointHistoryDTO.getAppointId() != null) {
			appointHistoryModel.setAppointId(transAppointDTO(appointHistoryDTO.getAppointId()));
		}
		
		if(appointHistoryDTO.getAppointStatusId() != null) {
			appointHistoryModel.setAppointStatusId(transAppointStatusModel(appointHistoryDTO.getAppointStatusId()));
		}
		
		if(appointHistoryDTO.getAppointHistoryCreateBy() != null) {
			appointHistoryModel.setAppointHistoryCreateBy(transUserDTO(appointHistoryDTO.getAppointHistoryCreateBy()));
		}
		
		appointHistoryModel.setEnable(NullUtils.cvStr(appointHistoryDTO.getEnable()));
		appointHistoryModel.setUpdateBy(NullUtils.cvStr(appointHistoryDTO.getUpdateBy()));
		appointHistoryModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(appointHistoryDTO.getUpdateDate(), appointHistoryDTO.getUpdateTime())));
		appointHistoryModel.setCreateBy(NullUtils.cvStr(appointHistoryDTO.getCreateBy()));
		appointHistoryModel.setCreateDate(convertDateTimeSQLToString(appointHistoryDTO.getCreateDate(), appointHistoryDTO.getCreateTime()));
		
		return appointHistoryModel;
	}

	
	public static AppointModel transAppointDTO(AppointDTO appointDTO) {
		AppointModel appointModel = new AppointModel();
		appointModel.setAppointDate(NullUtils.cvStr(convertDateTimeSQLToString(appointDTO.getAppointDate(), appointDTO.getAppointTime())));
		appointModel.setAppointId(NullUtils.cvStr(appointDTO.getAppointId()));
		appointModel.setDetail(NullUtils.cvStr(appointDTO.getDetail()));
		appointModel.setTitle(NullUtils.cvStr(appointDTO.getTitle()));
		
		if(appointDTO.getAppointCreateBy() != null) {
			appointModel.setAppointCreateBy(transUserDTO(appointDTO.getAppointCreateBy()));
		}
		
		appointModel.setAppointHistoryId(new ArrayList<>());
		if(appointDTO.getAppointHistoryId() != null) {
			if(!appointDTO.getAppointHistoryId().isEmpty()) {
				for(AppointHistoryDTO appointHistory : appointDTO.getAppointHistoryId()) {
					appointModel.getAppointHistoryId().add(transAppointHistoryDTO(appointHistory));
				}
			}
		}
		
		appointModel.setAuditorId(new ArrayList<>());
		if(appointDTO.getAuditorId() != null) {
			if(!appointDTO.getAuditorId().isEmpty()) {
				for(UserDTO auditor : appointDTO.getAuditorId()) {
					appointModel.getAuditorId().add(transUserDTO(auditor));
				}
			}
		}
		
		appointModel.setEntourageId(new ArrayList<>());
		if(appointDTO.getEntourageId() != null) {
			if(!appointDTO.getEntourageId().isEmpty()) {
				for(UserDTO entourage : appointDTO.getEntourageId()) {
					appointModel.getEntourageId().add(transUserDTO(entourage));
				}
			}
		}
		
		if(appointDTO.getAppointStatusId() != null) {
			appointModel.setAppointStatusId(transAppointStatusModel(appointDTO.getAppointStatusId()));
		}
		
		if(appointDTO.getAppointTypeId() != null) {
			appointModel.setAppointTypeId(transAppointTypeModel(appointDTO.getAppointTypeId()));
		}
		
		if(appointDTO.getSupplierId() != null) {
			appointModel.setSupplierId(transSupplierDTO(appointDTO.getSupplierId()));
		}
		
		if(appointDTO.getLocationId() != null) {
			appointModel.setLocationId(transSupplierProductAddressMappingDTO(appointDTO.getLocationId()));
		}
		
		

		appointModel.setEnable(NullUtils.cvStr(appointDTO.getEnable()));
		appointModel.setCreateBy(NullUtils.cvStr(appointDTO.getCreateBy()));
		appointModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(appointDTO.getCreateDate(), appointDTO.getCreateTime())));
		appointModel.setUpdateBy(NullUtils.cvStr(appointDTO.getUpdateBy()));
		appointModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(appointDTO.getUpdateDate(), appointDTO.getUpdateTime())));
		
		return appointModel;
	}
	
	public static AppointStatusModel transAppointStatusModel(AppointStatusDTO appointStatusDTO) {
		AppointStatusModel appointStatusModel = new AppointStatusModel();
		appointStatusModel.setAppointStatusId(NullUtils.cvStr(appointStatusDTO.getAppointStatusId()));
		appointStatusModel.setAppointStatusName(NullUtils.cvStr(appointStatusDTO.getAppointStatusName()));		
		appointStatusModel.setStatusColor(NullUtils.cvStr(appointStatusDTO.getStatusColor()));
		
		appointStatusModel.setAppointHistoryId(new ArrayList<>());
		if(appointStatusDTO.getAppointHistoryId() != null) {
			if(!appointStatusDTO.getAppointHistoryId().isEmpty()) {
				for(AppointHistoryDTO appointHistory : appointStatusDTO.getAppointHistoryId()) {
					appointStatusModel.getAppointHistoryId().add(transAppointHistoryDTO(appointHistory));
				}
			}
		}
		
		appointStatusModel.setAppointId(new ArrayList<>());
		if(appointStatusDTO.getAppointId() != null) {
			if(!appointStatusDTO.getAppointId().isEmpty()) {
				for(AppointDTO appoint : appointStatusDTO.getAppointId()) {
					appointStatusModel.getAppointId().add(transAppointDTO(appoint));
				}
			}
		}
		
		appointStatusModel.setCreateBy(NullUtils.cvStr(appointStatusDTO.getCreateBy()));
		appointStatusModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(appointStatusDTO.getCreateDate(), appointStatusDTO.getCreateTime())));
		appointStatusModel.setEnable(NullUtils.cvStr(appointStatusDTO.getEnable()));
		appointStatusModel.setUpdateBy(NullUtils.cvStr(appointStatusDTO.getUpdateBy()));
		appointStatusModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(appointStatusDTO.getUpdateDate(), appointStatusDTO.getUpdateTime())));
		
		return appointStatusModel;
	}
	
	
	public static AppointTypeModel transAppointTypeModel(AppointTypeDTO appointTypeDTO) {
		AppointTypeModel appointTypeModel = new AppointTypeModel();		
		appointTypeModel.setAppointTypeId(NullUtils.cvStr(appointTypeDTO.getAppointTypeId()));		
		appointTypeModel.setName(NullUtils.cvStr(appointTypeDTO.getName()));		
		
		appointTypeModel.setAppointId(new ArrayList<>());
		if(appointTypeDTO.getAppointId() != null) {
			if(!appointTypeDTO.getAppointId().isEmpty()) {
				for(AppointDTO appoint : appointTypeDTO.getAppointId()) {
					appointTypeModel.getAppointId().add(transAppointDTO(appoint));
				}
			}
		}
		
		appointTypeModel.setCreateBy(NullUtils.cvStr(appointTypeDTO.getCreateBy()));
		appointTypeModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(appointTypeDTO.getCreateDate(), appointTypeDTO.getCreateTime())));
		appointTypeModel.setEnable(NullUtils.cvStr(appointTypeDTO.getEnable()));
		appointTypeModel.setUpdateBy(NullUtils.cvStr(appointTypeDTO.getUpdateBy()));
		appointTypeModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(appointTypeDTO.getUpdateDate(), appointTypeDTO.getUpdateTime())));
		
		return appointTypeModel;
	}
	
	public static AssignPlanModel transAssignPlanDTO(AssignPlanDTO assignPlanDTO) {
		AssignPlanModel assignPlanModel = new AssignPlanModel();
		
		if(assignPlanDTO.getChecklistPlanId() != null) {
			assignPlanModel.setChecklistPlanId(transChecklistPlanDTO(assignPlanDTO.getChecklistPlanId()));
		}
		
		if(assignPlanDTO.getAuditorId() != null) {
			assignPlanModel.setAuditorId(transUserDTO(assignPlanDTO.getAuditorId()));
		}
		
		if(assignPlanDTO.getAssignPlanStatusId() != null) {
			assignPlanModel.setAssignPlanStatusId(transAssignPlanStatusDTO(assignPlanDTO.getAssignPlanStatusId()));
		}
		assignPlanModel.setSignatureOfSupplier(assignPlanDTO.getSignatureOfSupplier());
		assignPlanModel.setCreateBy(NullUtils.cvStr(assignPlanDTO.getCreateBy()));
		assignPlanModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(assignPlanDTO.getCreateDate(), assignPlanDTO.getCreateTime())));
		assignPlanModel.setEnable(NullUtils.cvStr(assignPlanDTO.getEnable()));
		assignPlanModel.setUpdateBy(NullUtils.cvStr(assignPlanDTO.getUpdateBy()));
		assignPlanModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(assignPlanDTO.getUpdateDate(), assignPlanDTO.getUpdateTime())));
		
		return assignPlanModel;
	}
	
	public static AssignPlanStatusModel transAssignPlanStatusDTO(AssignPlanStatusDTO assignPlanStatusDTO) {
		AssignPlanStatusModel assignPlanStatusModel = new AssignPlanStatusModel();
		
		assignPlanStatusModel.setAssignPlanStatusId(NullUtils.cvStr(assignPlanStatusDTO.getAssignPlanStatusId()));
		assignPlanStatusModel.setAssignPlanStatusName(NullUtils.cvStr(assignPlanStatusDTO.getAssignPlanStatusName()));
		assignPlanStatusModel.setStatusColor(NullUtils.cvStr(assignPlanStatusDTO.getStatusColor()));
		
		assignPlanStatusModel.setCreateBy(NullUtils.cvStr(assignPlanStatusDTO.getCreateBy()));
		assignPlanStatusModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(assignPlanStatusDTO.getCreateDate(), assignPlanStatusDTO.getCreateTime())));
		assignPlanStatusModel.setEnable(NullUtils.cvStr(assignPlanStatusDTO.getEnable()));
		assignPlanStatusModel.setUpdateBy(NullUtils.cvStr(assignPlanStatusDTO.getUpdateBy()));
		assignPlanStatusModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(assignPlanStatusDTO.getUpdateDate(), assignPlanStatusDTO.getUpdateTime())));
		
		return assignPlanStatusModel;
	}
	
	public static AuditResultModel transAuditResultDTO(AuditResultDTO auditResultDTO) {
		AuditResultModel auditResultModel = new AuditResultModel();
		
		auditResultModel.setAnswerDetail(NullUtils.cvStr(auditResultDTO.getAnswerDetail()));
		auditResultModel.setTransactionDate(NullUtils.cvStr(convertDateTimeSQLToString(auditResultDTO.getTransactionDate(), auditResultDTO.getTransactionTime())));
		auditResultModel.setAccepted(NullUtils.cvStr(auditResultDTO.getAccepted()));
		auditResultModel.setNote(NullUtils.cvStr(auditResultDTO.getNote()));
		if(auditResultDTO.getAnswerId() != null) {
			auditResultModel.setAnswerId(transEvalPlanAnswerDTO(auditResultDTO.getAnswerId()));
		}
		if(auditResultDTO.getEvalPlanId() != null) {
			auditResultModel.setEvalPlanId(transEvalPlanDTO(auditResultDTO.getEvalPlanId()));
		}
		
		if(auditResultDTO.getAuditorId() != null) {
			auditResultModel.setAuditorId(transUserDTO(auditResultDTO.getAuditorId()));
		}
		
		if(auditResultDTO.getChecklistPlanId() != null) {
			auditResultModel.setChecklistPlanId(transChecklistPlanDTO(auditResultDTO.getChecklistPlanId()));
		}	
		
		auditResultModel.setCreateBy(NullUtils.cvStr(auditResultDTO.getCreateBy()));
		auditResultModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(auditResultDTO.getCreateDate(), auditResultDTO.getCreateTime())));
		auditResultModel.setEnable(NullUtils.cvStr(auditResultDTO.getEnable()));
		auditResultModel.setUpdateBy(NullUtils.cvStr(auditResultDTO.getUpdateBy()));
		auditResultModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(auditResultDTO.getUpdateDate(), auditResultDTO.getUpdateTime())));
		return auditResultModel;
	}
	
	public static CarDetailModel transCarDetailDTO(CarDetailDTO carDetailDTO) {
		CarDetailModel carDetailModel = new CarDetailModel();
		carDetailModel.setDetail(NullUtils.cvStr(carDetailDTO.getDetail()));
		carDetailModel.setCompleted(NullUtils.cvStr(carDetailDTO.getCompleted()));
		carDetailModel.setCompleteDate(NullUtils.cvStr(convertDateTimeSQLToString(carDetailDTO.getCompleteDate(), carDetailDTO.getCompleteTime())));
		carDetailModel.setDueDate(NullUtils.cvStr(convertDateTimeSQLToString(carDetailDTO.getDueDate(), carDetailDTO.getDueTime())));
		
		if(carDetailDTO.getAuditResultId() != null) {
			carDetailModel.setAuditResultId(transAuditResultDTO(carDetailDTO.getAuditResultId()));
		}
		
		if(carDetailDTO.getCarId() != null) {
			carDetailModel.setCarId(transCarDTO(carDetailDTO.getCarId()));
		}
		
		carDetailModel.setEvidenceId(new ArrayList<>());
		if(carDetailDTO.getEvidenceId() != null) {
			if(!carDetailDTO.getEvidenceId().isEmpty()) {
				for(EvidenceDTO evidence : carDetailDTO.getEvidenceId()) {
					carDetailModel.getEvidenceId().add(transEvidenceDTO(evidence));
				}
			}
		}
		
		carDetailModel.setRemark(NullUtils.cvStr(carDetailDTO.getRemark()));
		carDetailModel.setCreateBy(NullUtils.cvStr(carDetailDTO.getCreateBy()));
		carDetailModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(carDetailDTO.getCreateDate(), carDetailDTO.getCreateTime())));
		if(carDetailModel.getCreateDate() != "") {
		     LocalDateTime now = LocalDateTime.now();
		     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		     String nowDate = now.format(formatter);
		     LocalDateTime dateTime1 = LocalDateTime.parse(nowDate,formatter);
		     LocalDateTime dateTime2 = LocalDateTime.parse(carDetailModel.getCreateDate(),formatter);
//		     LocalDateTime dateTime2 = LocalDateTime.parse("06/04/2021 17:31:00",formatter);
		    long daysBetween = ChronoUnit.DAYS.between(dateTime2, dateTime1);
		    if(daysBetween < 3) {
			    carDetailModel.setCanEditProblem("Y");
		    }else {
			    carDetailModel.setCanEditProblem("N");
		    }
		}
		carDetailModel.setEnable(NullUtils.cvStr(carDetailDTO.getEnable()));
		carDetailModel.setUpdateBy(NullUtils.cvStr(carDetailDTO.getUpdateBy()));
		carDetailModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(carDetailDTO.getUpdateDate(), carDetailDTO.getUpdateTime())));
		return carDetailModel;
	}
	
	public static CarModel transCarDTO(CarDTO carDTO) {
		CarModel carModel = new CarModel();		
		carModel.setCarId(NullUtils.cvStr(carDTO.getCarId()));
		carModel.setCarNo(NullUtils.cvStr(carDTO.getCarNo()));
		carModel.setDueDate(NullUtils.cvStr(convertDateTimeSQLToString(carDTO.getDueDate(), carDTO.getDueTime())));
		
		
		if(carDTO.getChecklistPlanId() != null) {
			carModel.setChecklistPlanId(transChecklistPlanDTO(carDTO.getChecklistPlanId()));
		}
		
		if(carDTO.getCarStatusId() != null) {
			carModel.setCarStatusId(transCarStatusDTO(carDTO.getCarStatusId()));
		}
		
		
		carModel.setCreateBy(NullUtils.cvStr(carDTO.getCreateBy()));
		carModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(carDTO.getCreateDate(), carDTO.getCreateTime())));
		carModel.setEnable(NullUtils.cvStr(carDTO.getEnable()));
		carModel.setUpdateBy(NullUtils.cvStr(carDTO.getUpdateBy()));
		carModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(carDTO.getUpdateDate(), carDTO.getUpdateTime())));
		
		return carModel;
	}
	
	public static CarStatusModel transCarStatusDTO(CarStatusDTO carStatusDTO) {
		CarStatusModel carStatusModel = new CarStatusModel();
		carStatusModel.setCarStatusId(NullUtils.cvStr(carStatusDTO.getCarStatusId()));
		carStatusModel.setCarStatusName(NullUtils.cvStr(carStatusDTO.getCarStatusName()));
		carStatusModel.setStatusColor(NullUtils.cvStr(carStatusDTO.getStatusColor()));
		
		carStatusModel.setCreateBy(NullUtils.cvStr(carStatusDTO.getCreateBy()));
		carStatusModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(carStatusDTO.getCreateDate(), carStatusDTO.getCreateTime())));
		carStatusModel.setEnable(NullUtils.cvStr(carStatusDTO.getEnable()));
		carStatusModel.setUpdateBy(NullUtils.cvStr(carStatusDTO.getUpdateBy()));
		carStatusModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(carStatusDTO.getUpdateDate(), carStatusDTO.getUpdateTime())));
		
		return carStatusModel;
	}
	
	public static ChecklistModel transChecklistDTO(ChecklistDTO checklistDTO) {
		ChecklistModel checklistModel = new ChecklistModel();
		checklistModel.setChecklistId(NullUtils.cvStr(checklistDTO.getChecklistId()));
		checklistModel.setChecklistTitle(NullUtils.cvStr(checklistDTO.getChecklistTitle()));
		checklistModel.setChecklistScope(NullUtils.cvStr(checklistDTO.getChecklistScope()));
		checklistModel.setScoringCriteria(NullUtils.cvStr(checklistDTO.getScoringCriteria()));
		checklistModel.setApproveSupplierRule(NullUtils.cvStr(checklistDTO.getApproveSupplierRule()));
		checklistModel.setDescription(NullUtils.cvStr(checklistDTO.getDescription()));
		checklistModel.setNoOfCarAcceptDay(NullUtils.cvStr(checklistDTO.getNoOfCarAcceptDay()));
		checklistModel.setEffectiveDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistDTO.getEffectiveDate(), checklistDTO.getEffectiveTime())));
		checklistModel.setExpireDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistDTO.getExpireDate(), checklistDTO.getExpireTime())));
		
		if(checklistDTO.getChecklistTypeId() != null) {
			checklistModel.setChecklistTypeId(transChecklistTypeDTO(checklistDTO.getChecklistTypeId()));
		}
		if(checklistDTO.getProductTypeId() != null) {
			checklistModel.setProductTypeId(transProductTypeDTO(checklistDTO.getProductTypeId()));
		}
		
		checklistModel.setEvalFormId(new ArrayList<>());
		if(checklistDTO.getEvalFormId() != null) {
			if(!checklistDTO.getEvalFormId().isEmpty()) {
				for(EvalFormDTO evalForm : checklistDTO.getEvalFormId()) {
					checklistModel.getEvalFormId().add(transEvalFormDTO(evalForm));
				}
				
			}
		}
		
		checklistModel.setCreateBy(NullUtils.cvStr(checklistDTO.getCreateBy()));
		checklistModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistDTO.getCreateDate(), checklistDTO.getCreateTime())));
		checklistModel.setEnable(NullUtils.cvStr(checklistDTO.getEnable()));
		checklistModel.setUpdateBy(NullUtils.cvStr(checklistDTO.getUpdateBy()));
		checklistModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistDTO.getUpdateDate(), checklistDTO.getUpdateTime())));
		return checklistModel;
	}	
	
	
	
	public static ChecklistPlanModel transChecklistPlanDTO(ChecklistPlanDTO checklistPlanDTO) {
		ChecklistPlanModel checklistPlanModel = new ChecklistPlanModel();
		
		checklistPlanModel.setChecklistPlanId(NullUtils.cvStr(checklistPlanDTO.getChecklistPlanId()));
		checklistPlanModel.setChecklistTitle(NullUtils.cvStr(checklistPlanDTO.getChecklistTitle()));
		checklistPlanModel.setSupplierCompany(NullUtils.cvStr(checklistPlanDTO.getSupplierCompany()));
		checklistPlanModel.setChecklistTypeName(NullUtils.cvStr(checklistPlanDTO.getChecklistTypeName()));
		checklistPlanModel.setProductTypeName(NullUtils.cvStr(checklistPlanDTO.getProductTypeName()));
		checklistPlanModel.setChecklistScope(NullUtils.cvStr(checklistPlanDTO.getChecklistScope()));
		checklistPlanModel.setScoringCriteria(NullUtils.cvStr(checklistPlanDTO.getScoringCriteria()));
		checklistPlanModel.setApproveSupplierRule(NullUtils.cvStr(checklistPlanDTO.getApproveSupplierRule()));
		checklistPlanModel.setDescription(NullUtils.cvStr(checklistPlanDTO.getDescription()));
		checklistPlanModel.setNoOfCarAcceptDay(NullUtils.cvStr(checklistPlanDTO.getNoOfCarAcceptDay()));
		checklistPlanModel.setPlanDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistPlanDTO.getPlanDate(), checklistPlanDTO.getPlanTime())));
		checklistPlanModel.setChecklistPlanNo(NullUtils.cvStr(checklistPlanDTO.getChecklistPlanNo()));
		
		if(checklistPlanDTO.getSupplierId() != null) {
			checklistPlanModel.setSupplierId(transSupplierDTO(checklistPlanDTO.getSupplierId()));
		}
		
		if(checklistPlanDTO.getChecklistTypeId() != null) {
			checklistPlanModel.setChecklistTypeId(transChecklistTypeDTO(checklistPlanDTO.getChecklistTypeId()));
		}
		
		if(checklistPlanDTO.getProductTypeId() != null) {
			checklistPlanModel.setProductTypeId(transProductTypeDTO(checklistPlanDTO.getProductTypeId()));
		}
		
		if(checklistPlanDTO.getChecklistPlanStatusId() != null) {
			checklistPlanModel.setChecklistPlanStatusId(transChecklistPlanStatusDTO(checklistPlanDTO.getChecklistPlanStatusId()));
		}
		
		if(checklistPlanDTO.getLocationId() != null) {
			checklistPlanModel.setLocationId(transSupplierProductAddressMappingDTO(checklistPlanDTO.getLocationId()));
		}
		
		if(checklistPlanDTO.getCarId() != null) {
			checklistPlanModel.setCarId(transCarDTO(checklistPlanDTO.getCarId()));
		}
		
		checklistPlanModel.setAssignPlanId(new ArrayList<>());
		if(checklistPlanDTO.getAssignPlanId() != null) {
			if(!checklistPlanDTO.getAssignPlanId().isEmpty()) {
				for(AssignPlanDTO assignPlan : checklistPlanDTO.getAssignPlanId()) {
					checklistPlanModel.getAssignPlanId().add(transAssignPlanDTO(assignPlan));
				}
			}
		}
		
		checklistPlanModel.setChecklistPlanEntourageId(new ArrayList<>());
		if(checklistPlanDTO.getChecklistPlanEntourageId() != null) {
			if(!checklistPlanDTO.getChecklistPlanEntourageId().isEmpty()) {
				for(UserDTO entourage : checklistPlanDTO.getChecklistPlanEntourageId()) {
					checklistPlanModel.getChecklistPlanEntourageId().add(transUserDTO(entourage));
				}
			}
		}
		
		checklistPlanModel.setCreateBy(NullUtils.cvStr(checklistPlanDTO.getCreateBy()));
		checklistPlanModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistPlanDTO.getCreateDate(), checklistPlanDTO.getCreateTime())));
		checklistPlanModel.setEnable(NullUtils.cvStr(checklistPlanDTO.getEnable()));
		checklistPlanModel.setUpdateBy(NullUtils.cvStr(checklistPlanDTO.getUpdateBy()));
		checklistPlanModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistPlanDTO.getUpdateDate(), checklistPlanDTO.getUpdateTime())));
		
		return checklistPlanModel;
	}
	
	public static ChecklistPlanStatusModel transChecklistPlanStatusDTO(ChecklistPlanStatusDTO checklistPlanStatusDTO) {
		ChecklistPlanStatusModel checklistPlanStatusModel = new  ChecklistPlanStatusModel();
		
		checklistPlanStatusModel.setChecklistPlanStatusId(NullUtils.cvStr(checklistPlanStatusDTO.getChecklistPlanStatusId()));
		checklistPlanStatusModel.setChecklistPlanStatusName(NullUtils.cvStr(checklistPlanStatusDTO.getChecklistPlanStatusName()));
		checklistPlanStatusModel.setStatusColor(NullUtils.cvStr(checklistPlanStatusDTO.getStatusColor()));
		
		checklistPlanStatusModel.setCreateBy(NullUtils.cvStr(checklistPlanStatusDTO.getCreateBy()));
		checklistPlanStatusModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistPlanStatusDTO.getCreateDate(), checklistPlanStatusDTO.getCreateTime())));
		checklistPlanStatusModel.setEnable(NullUtils.cvStr(checklistPlanStatusDTO.getEnable()));
		checklistPlanStatusModel.setUpdateBy(NullUtils.cvStr(checklistPlanStatusDTO.getUpdateBy()));
		checklistPlanStatusModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistPlanStatusDTO.getUpdateDate(), checklistPlanStatusDTO.getUpdateTime())));
		
		return checklistPlanStatusModel;
	}
	
	public static ChecklistTypeModel transChecklistTypeDTO(ChecklistTypeDTO checklistTypeDTO) {
		ChecklistTypeModel checklistTypeModel = new ChecklistTypeModel();
		
		checklistTypeModel.setChecklistTypeId(NullUtils.cvStr(checklistTypeDTO.getChecklistTypeId()));
		checklistTypeModel.setChecklistTypeName(NullUtils.cvStr(checklistTypeDTO.getChecklistTypeName()));
		
		checklistTypeModel.setChecklistId(new ArrayList<>());
		if(checklistTypeDTO.getChecklistId() != null) {
			if(!checklistTypeDTO.getChecklistId().isEmpty()) {
				for(ChecklistDTO checklist : checklistTypeDTO.getChecklistId()) {
					checklistTypeModel.getChecklistId().add(transChecklistDTO(checklist));
				}
			}
		}
		
		checklistTypeModel.setQuestionTypeId(new ArrayList<>());
		if(checklistTypeDTO.getQuestionTypeId() != null) {
			if(!checklistTypeDTO.getQuestionTypeId().isEmpty()) {
				for(QuestionTypeDTO questionType : checklistTypeDTO.getQuestionTypeId()) {
					checklistTypeModel.getQuestionTypeId().add(transQuestionTypeDTO(questionType));
				}
			}
		}
		
		checklistTypeModel.setGradeId(new ArrayList<>());
		if(checklistTypeDTO.getGradeId() != null) {
			if(!checklistTypeDTO.getGradeId().isEmpty()) {
				for(GradeDTO grade : checklistTypeDTO.getGradeId()) {
					checklistTypeModel.getGradeId().add(transGradeDTO(grade));
				}
			}
		}
		
		checklistTypeModel.setCreateBy(NullUtils.cvStr(checklistTypeDTO.getCreateBy()));
		checklistTypeModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistTypeDTO.getCreateDate(), checklistTypeDTO.getCreateTime())));
		checklistTypeModel.setEnable(NullUtils.cvStr(checklistTypeDTO.getEnable()));
		checklistTypeModel.setUpdateBy(NullUtils.cvStr(checklistTypeDTO.getUpdateBy()));
		checklistTypeModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(checklistTypeDTO.getUpdateDate(), checklistTypeDTO.getUpdateTime())));
		
		return checklistTypeModel;
	}
	
	public static EvalFormModel transEvalFormDTO(EvalFormDTO evalFormDTO) {
		EvalFormModel evalFormModel = new EvalFormModel();		
		
		evalFormModel.setEvalFormId(NullUtils.cvStr(evalFormDTO.getEvalFormId()));
		evalFormModel.setParentId(NullUtils.cvStr(evalFormDTO.getParentId()));
		evalFormModel.setUniqueId(NullUtils.cvStr(evalFormDTO.getUniqueId()));
		evalFormModel.setTitle(NullUtils.cvStr(evalFormDTO.getTitle()));
		evalFormModel.setDetail(NullUtils.cvStr(evalFormDTO.getDetail()));
		
		if(evalFormDTO.getChecklistId() != null) {
			evalFormModel.setChecklistId(transChecklistDTO(evalFormDTO.getChecklistId()));
		}
		
		if(evalFormDTO.getEvalTypeId() != null) {
			evalFormModel.setEvalTypeId(transEvalTypeDTO(evalFormDTO.getEvalTypeId()));
		}
		
		if(evalFormDTO.getQuestionTypeId() != null) {
			evalFormModel.setQuestionTypeId(transQuestionTypeDTO(evalFormDTO.getQuestionTypeId()));
		}
		
		evalFormModel.setAnswerId(new ArrayList<>());
		if(evalFormDTO.getAnswerId() != null) {
			if(!evalFormDTO.getAnswerId().isEmpty()) {
				for(AnswerDTO answer : evalFormDTO.getAnswerId()) {
					evalFormModel.getAnswerId().add(transAnswerDTO(answer));
				}
			}
		}
		
		evalFormModel.setCreateBy(NullUtils.cvStr(evalFormDTO.getCreateBy()));
		evalFormModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(evalFormDTO.getCreateDate(), evalFormDTO.getCreateTime())));
		evalFormModel.setEnable(NullUtils.cvStr(evalFormDTO.getEnable()));
		evalFormModel.setUpdateBy(NullUtils.cvStr(evalFormDTO.getUpdateBy()));
		evalFormModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(evalFormDTO.getUpdateDate(), evalFormDTO.getUpdateTime())));
		return evalFormModel;
	}
	
	
	public static EvalPlanAnswerModel transEvalPlanAnswerDTO(EvalPlanAnswerDTO evalPlanAnswerDTO) {
		EvalPlanAnswerModel evalPlanAnswerModel = new EvalPlanAnswerModel();
		evalPlanAnswerModel.setAnswerId(NullUtils.cvStr(evalPlanAnswerDTO.getAnswerId()));
		evalPlanAnswerModel.setAnswerDetail(NullUtils.cvStr(evalPlanAnswerDTO.getAnswerDetail()));
		evalPlanAnswerModel.setIsCreateCar(NullUtils.cvStr(evalPlanAnswerDTO.getIsCreateCar()));
		evalPlanAnswerModel.setIsRequireEvidence(NullUtils.cvStr(evalPlanAnswerDTO.getIsRequireEvidence()));
		if(evalPlanAnswerDTO.getEvalPlanId() != null) {
			evalPlanAnswerModel.setEvalPlanId(transEvalPlanDTO(evalPlanAnswerDTO.getEvalPlanId()));
		}		
		evalPlanAnswerModel.setCreateBy(NullUtils.cvStr(evalPlanAnswerDTO.getCreateBy()));
		evalPlanAnswerModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(evalPlanAnswerDTO.getCreateDate(), evalPlanAnswerDTO.getCreateTime())));
		evalPlanAnswerModel.setEnable(NullUtils.cvStr(evalPlanAnswerDTO.getEnable()));
		evalPlanAnswerModel.setUpdateBy(NullUtils.cvStr(evalPlanAnswerDTO.getUpdateBy()));
		evalPlanAnswerModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(evalPlanAnswerDTO.getUpdateDate(), evalPlanAnswerDTO.getUpdateTime())));
		return evalPlanAnswerModel;
	}
	
	
	public static EvalPlanModel transEvalPlanDTO(EvalPlanDTO evalPlanDTO) {
		EvalPlanModel evalPlanModel = new EvalPlanModel();
		
		evalPlanModel.setEvalPlanId(NullUtils.cvStr(evalPlanDTO.getEvalPlanId()));
		evalPlanModel.setParentId(NullUtils.cvStr(evalPlanDTO.getParentId()));
		evalPlanModel.setEvalTypeName(NullUtils.cvStr(evalPlanDTO.getEvalTypeName()));
		evalPlanModel.setQuestionTypeName(NullUtils.cvStr(evalPlanDTO.getQuestionTypeName()));
		evalPlanModel.setRequireAnswer(NullUtils.cvStr(evalPlanDTO.getRequireAnswer()));
		evalPlanModel.setTitle(NullUtils.cvStr(evalPlanDTO.getTitle()));
		evalPlanModel.setDetail(NullUtils.cvStr(evalPlanDTO.getDetail()));
		
		if(evalPlanDTO.getChecklistPlanId() != null) {
			evalPlanModel.setChecklistPlanId(transChecklistPlanDTO(evalPlanDTO.getChecklistPlanId()));
		}
		
		if(evalPlanDTO.getEvalTypeId() != null) {
			evalPlanModel.setEvalTypeId(transEvalTypeDTO(evalPlanDTO.getEvalTypeId()));
		}
		
		if(evalPlanDTO.getQuestionTypeId() != null) {
			evalPlanModel.setQuestionTypeId(transQuestionTypeDTO(evalPlanDTO.getQuestionTypeId()));
		}
		
		evalPlanModel.setAuditResultId(new ArrayList<>());
		if(evalPlanDTO.getAuditResultId() != null) {
			if(!evalPlanDTO.getAuditResultId().isEmpty()) {
				for(AuditResultDTO auditResult : evalPlanDTO.getAuditResultId()) {
					evalPlanModel.getAuditResultId().add(transAuditResultDTO(auditResult));
				}
			}
		}
		
		evalPlanModel.setCreateBy(NullUtils.cvStr(evalPlanDTO.getCreateBy()));
		evalPlanModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(evalPlanDTO.getCreateDate(), evalPlanDTO.getCreateTime())));
		evalPlanModel.setEnable(NullUtils.cvStr(evalPlanDTO.getEnable()));
		evalPlanModel.setUpdateBy(NullUtils.cvStr(evalPlanDTO.getUpdateBy()));
		evalPlanModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(evalPlanDTO.getUpdateDate(), evalPlanDTO.getUpdateTime())));
		
		return evalPlanModel;
	}
	
	public static EvalTypeModel transEvalTypeDTO(EvalTypeDTO evalTypeDTO) {
		EvalTypeModel evalTypeModel = new EvalTypeModel();
				
		evalTypeModel.setEvalTypeId(NullUtils.cvStr(evalTypeDTO.getEvalTypeId()));
		evalTypeModel.setEvalTypeName(NullUtils.cvStr(evalTypeDTO.getEvalTypeName()));
		
		evalTypeModel.setEvalFormId(new ArrayList<>());
		if(evalTypeDTO.getEvalFormId() != null) {
			if(!evalTypeDTO.getEvalFormId().isEmpty()) {
				for(EvalFormDTO evalForm : evalTypeDTO.getEvalFormId()) {
					evalTypeModel.getEvalFormId().add(transEvalFormDTO(evalForm));
				}
			}
		}
		
		evalTypeModel.setCreateBy(NullUtils.cvStr(evalTypeDTO.getCreateBy()));
		evalTypeModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(evalTypeDTO.getCreateDate(), evalTypeDTO.getCreateTime())));
		evalTypeModel.setEnable(NullUtils.cvStr(evalTypeDTO.getEnable()));
		evalTypeModel.setUpdateBy(NullUtils.cvStr(evalTypeDTO.getUpdateBy()));
		evalTypeModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(evalTypeDTO.getUpdateDate(), evalTypeDTO.getUpdateTime())));
		return evalTypeModel;
	}
	
	public static EvidenceModel transEvidenceDTO(EvidenceDTO evidenceDTO) {
		EvidenceModel evidenceModel = new EvidenceModel();
		evidenceModel.setEvidenceId(NullUtils.cvStr(evidenceDTO.getEvidenceId()));
		evidenceModel.setActionType(NullUtils.cvStr(evidenceDTO.getActionType()));
		evidenceModel.setData(NullUtils.cvStr(evidenceDTO.getData()));
		evidenceModel.setActionType(NullUtils.cvStr(evidenceDTO.getActionType()));
		
		if(evidenceDTO.getAuditResultId() != null) {
			evidenceModel.setAuditResultId(transAuditResultDTO(evidenceDTO.getAuditResultId()));;
		}
		
		if(evidenceDTO.getEvidenceTypeId() != null) {
			evidenceModel.setEvidenceTypeId(transEvidenceTypeDTO(evidenceDTO.getEvidenceTypeId()));
		}
		
		evidenceModel.setCreateBy(NullUtils.cvStr(evidenceDTO.getCreateBy()));
		evidenceModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(evidenceDTO.getCreateDate(), evidenceDTO.getCreateTime())));
		evidenceModel.setEnable(NullUtils.cvStr(evidenceDTO.getEnable()));
		evidenceModel.setUpdateBy(NullUtils.cvStr(evidenceDTO.getUpdateBy()));
		evidenceModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(evidenceDTO.getUpdateDate(), evidenceDTO.getUpdateTime())));
		
		return evidenceModel;
	}
	
	public static EvidenceTypeModel transEvidenceTypeDTO(EvidenceTypeDTO evidenceTypeDTO) {
		EvidenceTypeModel evidenceTypeModel = new EvidenceTypeModel();
		evidenceTypeModel.setEvidenceTypeId(NullUtils.cvStr(evidenceTypeDTO.getEvidenceTypeId()));
		evidenceTypeModel.setEvidenceTypeName(NullUtils.cvStr(evidenceTypeDTO.getEvidenceTypeName()));
		
		evidenceTypeModel.setCreateBy(NullUtils.cvStr(evidenceTypeDTO.getCreateBy()));
		evidenceTypeModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(evidenceTypeDTO.getCreateDate(), evidenceTypeDTO.getCreateTime())));
		evidenceTypeModel.setEnable(NullUtils.cvStr(evidenceTypeDTO.getEnable()));
		evidenceTypeModel.setUpdateBy(NullUtils.cvStr(evidenceTypeDTO.getUpdateBy()));
		evidenceTypeModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(evidenceTypeDTO.getUpdateDate(), evidenceTypeDTO.getUpdateTime())));
		return evidenceTypeModel;
	}
	
	public static FinalAuditResultModel transFinalAuditResultDTO(FinalAuditResultDTO finalAuditResultDTO) {
		FinalAuditResultModel finalAuditResultModel = new FinalAuditResultModel();
		finalAuditResultModel.setApprovalName(NullUtils.cvStr(finalAuditResultDTO.getApprovalName()));
		finalAuditResultModel.setAuditor(NullUtils.cvStr(finalAuditResultDTO.getAuditor()));
		finalAuditResultModel.setAuditType(NullUtils.cvStr(finalAuditResultDTO.getAuditType()));
		finalAuditResultModel.setCarDetailData(NullUtils.cvStr(finalAuditResultDTO.getCarDetailData()));
		finalAuditResultModel.setChecklistPlanNo(NullUtils.cvStr(finalAuditResultDTO.getChecklistPlanNo()));
		finalAuditResultModel.setCompleteDate(NullUtils.cvStr(convertDateTimeSQLToString(finalAuditResultDTO.getCompleteDate(), finalAuditResultDTO.getCompleteTime())));
		finalAuditResultModel.setConclude(NullUtils.cvStr(finalAuditResultDTO.getConclude()));
		finalAuditResultModel.setGrade(NullUtils.cvStr(finalAuditResultDTO.getGrade()));
		finalAuditResultModel.setPass(NullUtils.cvStr(finalAuditResultDTO.getPass()));
		finalAuditResultModel.setPlanDate(NullUtils.cvStr(convertDateTimeSQLToString(finalAuditResultDTO.getPlanDate(), finalAuditResultDTO.getPlanTime())));
		finalAuditResultModel.setProductTypeName(NullUtils.cvStr(finalAuditResultDTO.getProductTypeName()));
		finalAuditResultModel.setSignatureOfSupplier(Base64.getEncoder().encodeToString(finalAuditResultDTO.getSignatureOfSupplier()));
		finalAuditResultModel.setSupplierData(NullUtils.cvStr(finalAuditResultDTO.getSupplierData()));
		finalAuditResultModel.setSupplierProductAddressMappingData(NullUtils.cvStr(finalAuditResultDTO.getSupplierProductAddressMappingData()));
		
		
		if(finalAuditResultDTO.getChecklistPlanId() != null) {
			finalAuditResultModel.setChecklistPlanId(transChecklistPlanDTO(finalAuditResultDTO.getChecklistPlanId()));
		}
		
		if(finalAuditResultDTO.getCarId() != null) {
			finalAuditResultModel.setCarId(transCarDTO(finalAuditResultDTO.getCarId()));
		}
		
		if(finalAuditResultDTO.getFinalAuditResultStatusId() != null) {
			finalAuditResultModel.setFinalAuditResultStatusId(transFinalAuditResultStatusDTO(finalAuditResultDTO.getFinalAuditResultStatusId()));
		}
		
		if(finalAuditResultDTO.getLocationId() != null) {
			finalAuditResultModel.setLocationId(transSupplierProductAddressMappingDTO(finalAuditResultDTO.getLocationId()));
		}
		
		if(finalAuditResultDTO.getProductTypeId() != null) {
			finalAuditResultModel.setProductTypeId(transProductTypeDTO(finalAuditResultDTO.getProductTypeId()));
		}
		
		if(finalAuditResultDTO.getSupplierId() != null) {
			finalAuditResultModel.setSupplierId(transSupplierDTO(finalAuditResultDTO.getSupplierId()));
		}
		
		
		
		finalAuditResultModel.setCreateBy(NullUtils.cvStr(finalAuditResultDTO.getCreateBy()));
		finalAuditResultModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(finalAuditResultDTO.getCreateDate(), finalAuditResultDTO.getCreateTime())));
		finalAuditResultModel.setEnable(NullUtils.cvStr(finalAuditResultDTO.getEnable()));
		finalAuditResultModel.setUpdateBy(NullUtils.cvStr(finalAuditResultDTO.getUpdateBy()));
		finalAuditResultModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(finalAuditResultDTO.getUpdateDate(), finalAuditResultDTO.getUpdateTime())));
		
		return finalAuditResultModel;
	}
	
	public static FinalAuditResultStatusModel transFinalAuditResultStatusDTO(FinalAuditResultStatusDTO finalAuditResultStatusDTO) {
		FinalAuditResultStatusModel finalAuditResultStatusModel = new FinalAuditResultStatusModel();
		finalAuditResultStatusModel.setFinalAuditResultStatusId(NullUtils.cvStr(finalAuditResultStatusDTO.getFinalAuditResultStatusId()));
		finalAuditResultStatusModel.setFinalAuditResultStatusName(NullUtils.cvStr(finalAuditResultStatusDTO.getFinalAuditResultStatusName()));
		finalAuditResultStatusModel.setFinalAuditResultStatusColor(NullUtils.cvStr(finalAuditResultStatusDTO.getFinalAuditResultStatusColor()));
		
		finalAuditResultStatusModel.setCreateBy(NullUtils.cvStr(finalAuditResultStatusDTO.getCreateBy()));
		finalAuditResultStatusModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(finalAuditResultStatusDTO.getCreateDate(), finalAuditResultStatusDTO.getCreateTime())));
		finalAuditResultStatusModel.setEnable(NullUtils.cvStr(finalAuditResultStatusDTO.getEnable()));
		finalAuditResultStatusModel.setUpdateBy(NullUtils.cvStr(finalAuditResultStatusDTO.getUpdateBy()));
		finalAuditResultStatusModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(finalAuditResultStatusDTO.getUpdateDate(), finalAuditResultStatusDTO.getUpdateTime())));
		
		return finalAuditResultStatusModel;
	}
	
	public static GradeModel transGradeDTO(GradeDTO gradeDTO) {
		GradeModel gradeModel = new GradeModel();
		gradeModel.setGradeId(NullUtils.cvStr(gradeDTO.getGradeId()));
		
		gradeModel.setChecklistTypeId(new ArrayList<>());
		if(gradeDTO.getChecklistTypeId() != null) {
			if(!gradeDTO.getChecklistTypeId().isEmpty()) {
				for(ChecklistTypeDTO checklistType : gradeDTO.getChecklistTypeId()) {
					gradeModel.getChecklistTypeId().add(transChecklistTypeDTO(checklistType));
				}
			}
		}
		
		gradeModel.setCreateBy(NullUtils.cvStr(gradeDTO.getCreateBy()));
		gradeModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(gradeDTO.getCreateDate(), gradeDTO.getCreateTime())));
		gradeModel.setEnable(NullUtils.cvStr(gradeDTO.getEnable()));
		gradeModel.setUpdateBy(NullUtils.cvStr(gradeDTO.getUpdateBy()));
		gradeModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(gradeDTO.getUpdateDate(), gradeDTO.getUpdateTime())));
		
		return gradeModel;
	}
	
	public static ProductTypeModel transProductTypeDTO(ProductTypeDTO productTypeDTO) {
		ProductTypeModel productTypeModel = new ProductTypeModel();
				
		productTypeModel.setProductTypeId(NullUtils.cvStr(productTypeDTO.getProductTypeId()));
		productTypeModel.setName(NullUtils.cvStr(productTypeDTO.getName()));
		
		if(productTypeDTO.getChecklistId() != null) {
			productTypeModel.setChecklistId(transChecklistDTO(productTypeDTO.getChecklistId()));
		}
		
		productTypeModel.setSupplierProductAddressMappingModel(new ArrayList<>());
		if(productTypeDTO.getSupplierProductAddressMappingDTO() != null) {
			if(!productTypeDTO.getSupplierProductAddressMappingDTO().isEmpty()) {
				for(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO : productTypeDTO.getSupplierProductAddressMappingDTO()) {
					productTypeModel.getSupplierProductAddressMappingModel().add(transSupplierProductAddressMappingDTO(supplierProductAddressMappingDTO));
				}
			}
		}
		
		productTypeModel.setCreateBy(NullUtils.cvStr(productTypeDTO.getCreateBy()));
		productTypeModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(productTypeDTO.getCreateDate(), productTypeDTO.getCreateTime())));
		productTypeModel.setEnable(NullUtils.cvStr(productTypeDTO.getEnable()));
		productTypeModel.setUpdateBy(NullUtils.cvStr(productTypeDTO.getUpdateBy()));
		productTypeModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(productTypeDTO.getUpdateDate(), productTypeDTO.getUpdateTime())));
		return productTypeModel;
	}
	
	public static QuestionTypeModel transQuestionTypeDTO(QuestionTypeDTO questionTypeDTO) {
		QuestionTypeModel questionTypeModel = new QuestionTypeModel();
				
		questionTypeModel.setName(NullUtils.cvStr(questionTypeDTO.getName()));
		questionTypeModel.setQuestionTypeId(NullUtils.cvStr(questionTypeDTO.getQuestionTypeId()));
			
		questionTypeModel.setAnswerId(new ArrayList<>());
		if(questionTypeDTO.getAnswerId() != null) {
			if(!questionTypeDTO.getAnswerId().isEmpty()) {
				for(AnswerDTO answer : questionTypeDTO.getAnswerId()) {
					questionTypeModel.getAnswerId().add(transAnswerDTO(answer));
				}
			}
		}
		
		questionTypeModel.setEvalFormId(new ArrayList<>());
		if(questionTypeDTO.getEvalFormId() != null) {
			if(!questionTypeDTO.getEvalFormId().isEmpty()) {
				for(EvalFormDTO evalForm : questionTypeDTO.getEvalFormId()) {
					questionTypeModel.getEvalFormId().add(transEvalFormDTO(evalForm));
				}
			}
		}
		
		questionTypeModel.setChecklistTypeId(new ArrayList<>());
		if(questionTypeDTO.getChecklistTypeId() != null) {
			if(!questionTypeDTO.getChecklistTypeId().isEmpty()) {
				for(ChecklistTypeDTO checklistType : questionTypeDTO.getChecklistTypeId()) {
					questionTypeModel.getChecklistTypeId().add(transChecklistTypeDTO(checklistType));
				}
			}
		}
		
		questionTypeModel.setEnable(NullUtils.cvStr(questionTypeDTO.getEnable()));
		questionTypeModel.setUpdateBy(NullUtils.cvStr(questionTypeDTO.getUpdateBy()));
		questionTypeModel.setCreateBy(NullUtils.cvStr(questionTypeDTO.getCreateBy()));
		questionTypeModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(questionTypeDTO.getCreateDate(), questionTypeDTO.getCreateTime())));
		questionTypeModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(questionTypeDTO.getUpdateDate(), questionTypeDTO.getUpdateTime())));	
		
		return questionTypeModel;
	}
	
	public static StandardDocumentModel transStandardDocumentDTO(StandardDocumentDTO standardDocumentDTO) {
		StandardDocumentModel standardDocumentModel = new StandardDocumentModel();
		standardDocumentModel.setStandardDocumentId(NullUtils.cvStr(standardDocumentDTO.getStandardDocumentId()));
		standardDocumentModel.setStandardDocumentName(NullUtils.cvStr(standardDocumentDTO.getStandardDocumentName()));
		standardDocumentModel.setEnable(NullUtils.cvStr(standardDocumentDTO.getEnable()));
		standardDocumentModel.setCreateBy(NullUtils.cvStr(standardDocumentDTO.getCreateBy()));
		standardDocumentModel.setUpdateBy(NullUtils.cvStr(standardDocumentDTO.getUpdateBy()));
		standardDocumentModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(standardDocumentDTO.getCreateDate(), standardDocumentDTO.getCreateTime())));
		standardDocumentModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(standardDocumentDTO.getUpdateDate(), standardDocumentDTO.getUpdateTime())));		
		return standardDocumentModel;
	}
	
	public static DocumentModel transDocumentDTO(DocumentDTO DocumentDTO) {
		DocumentModel DocumentModel = new DocumentModel();
		DocumentModel.setDocumentId(NullUtils.cvStr(DocumentDTO.getDocumentId()));
		DocumentModel.setDocumentName(NullUtils.cvStr(DocumentDTO.getDocumentName()));
		DocumentModel.setEnable(NullUtils.cvStr(DocumentDTO.getEnable()));
		return DocumentModel;
	}
	
	public static ManualDocumentModel transDocumentDTO(ManualDocumentDTO ManualDocumentDTO) {
		ManualDocumentModel ManualDocumentModel = new ManualDocumentModel();
		ManualDocumentModel.setDocumentid(NullUtils.cvStr(ManualDocumentDTO.getDocumentid()));
		ManualDocumentModel.setDocumentlocation(NullUtils.cvStr( ManualDocumentDTO.getDocumentlocation() ) );
		ManualDocumentModel.setDocument_type(NullUtils.cvStr(ManualDocumentDTO.getDocument_type()));
		return ManualDocumentModel;
	}
	
	public static PrivacyDocumentModel transDocumentDTO(PrivacyDocumentDTO PrivacyDocumentDTO) {
		PrivacyDocumentModel PrivacyDocumentModel = new PrivacyDocumentModel();
		PrivacyDocumentModel.setSystem_key(NullUtils.cvStr( PrivacyDocumentDTO.getSystem_key() ) );
		PrivacyDocumentModel.setSystem_value(NullUtils.cvStr(PrivacyDocumentDTO.getSystem_value()));
		return PrivacyDocumentModel;
	}
	
	public static SupplierModel transSupplierDTO(SupplierDTO supplierDTO) {
		SupplierModel supplierModel = new SupplierModel();
				
		supplierModel.setIsGreenCard(NullUtils.cvStr(supplierDTO.getIsGreenCard()));
		supplierModel.setLogo(NullUtils.cvStr(supplierDTO.getLogo()));
		supplierModel.setSupplierCode(NullUtils.cvStr(supplierDTO.getSupplierCode()));
		supplierModel.setSupplierCompany(NullUtils.cvStr(supplierDTO.getSupplierCompany()));
		supplierModel.setSupplierId(NullUtils.cvStr(supplierDTO.getSupplierId()));
		supplierModel.setApproval(NullUtils.cvStr(supplierDTO.getApproval()));
		supplierModel.setLogo(NullUtils.cvStr(supplierDTO.getLogo()));
		supplierModel.setGreenCardExpireDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierDTO.getGreenCardExpireDate(), supplierDTO.getGreenCardExpireTime())));
		supplierModel.setAuditRound(NullUtils.cvStr(supplierDTO.getAuditRound()));
		
		if(supplierDTO.getAddressId() != null) {
			supplierModel.setAddressId(transAddressDTO(supplierDTO.getAddressId()));
		}
		
		if(supplierDTO.getSupplierProductAddressMappingDTO() != null) {
			supplierModel.setSupplierProductAddressMappingModel(transSupplierProductAddressMappingDTO(supplierDTO.getSupplierProductAddressMappingDTO()));
		}
				
		supplierModel.setUserId(new ArrayList<>());	
		if(supplierDTO.getUserId() != null) {
			if(!supplierDTO.getUserId().isEmpty()) {
				for(UserDTO userDTO : supplierDTO.getUserId()) {
					supplierModel.getUserId().add(transUserDTO(userDTO));
				}
			}
		}	
		
			
		
		supplierModel.setEnable(NullUtils.cvStr(supplierDTO.getEnable()));
		supplierModel.setCreateBy(NullUtils.cvStr(supplierDTO.getCreateBy()));
		supplierModel.setUpdateBy(NullUtils.cvStr(supplierDTO.getUpdateBy()));
		supplierModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierDTO.getCreateDate(), supplierDTO.getCreateTime())));
		supplierModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierDTO.getUpdateDate(), supplierDTO.getUpdateTime())));		
		
		return supplierModel;
	}
		
	
	public static SupplierProductAddressMappingModel transSupplierProductAddressMappingDTO(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO) {
		SupplierProductAddressMappingModel supplierProductAddressMappingModel = new SupplierProductAddressMappingModel();
		
		supplierProductAddressMappingModel.setId(NullUtils.cvStr(supplierProductAddressMappingDTO.getId()));
		supplierProductAddressMappingModel.setLocationName(NullUtils.cvStr(supplierProductAddressMappingDTO.getLocationName()));
		
		
		if(supplierProductAddressMappingDTO.getAddressId() != null) {
			supplierProductAddressMappingModel.setAddressId(transAddressDTO(supplierProductAddressMappingDTO.getAddressId()));
		}
		
		supplierProductAddressMappingModel.setProductTypeId(new ArrayList<>());
		if(supplierProductAddressMappingDTO.getProductTypeId() != null) {
			if(!supplierProductAddressMappingDTO.getProductTypeId().isEmpty()) {
				for(ProductTypeDTO productTypeDTO : supplierProductAddressMappingDTO.getProductTypeId()) {
					supplierProductAddressMappingModel.getProductTypeId().add(transProductTypeDTO(productTypeDTO));
				}
			}
			
		}
		
		if(supplierProductAddressMappingDTO.getSupplierId() != null) {
			supplierProductAddressMappingModel.setSupplierId(transSupplierDTO(supplierProductAddressMappingDTO.getSupplierId()));
		}
		
		supplierProductAddressMappingModel.setEnable(NullUtils.cvStr(supplierProductAddressMappingDTO.getEnable()));
		supplierProductAddressMappingModel.setCreateBy(NullUtils.cvStr(supplierProductAddressMappingDTO.getCreateBy()));
		supplierProductAddressMappingModel.setUpdateBy(NullUtils.cvStr(supplierProductAddressMappingDTO.getUpdateBy()));
		supplierProductAddressMappingModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierProductAddressMappingDTO.getCreateDate(), supplierProductAddressMappingDTO.getCreateTime())));
		supplierProductAddressMappingModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierProductAddressMappingDTO.getUpdateDate(), supplierProductAddressMappingDTO.getUpdateTime())));
		
		return supplierProductAddressMappingModel;
	}
	
	public static SupplierStandardDocumentModel transSupplierStandardDocumentDTO(SupplierStandardDocumentDTO supplierStandardDocumentDTO) {
		SupplierStandardDocumentModel standardDocumentModel = new SupplierStandardDocumentModel();
		
		standardDocumentModel.setSupplierStandardDocumentId(NullUtils.cvStr(supplierStandardDocumentDTO.getSupplierStandardDocumentId()));
		standardDocumentModel.setSupplierStandardDocumentLocation(NullUtils.cvStr(supplierStandardDocumentDTO.getSupplierStandardDocumentLocation()));
		standardDocumentModel.setSupplierStandardDocumentExpireDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierStandardDocumentDTO.getSupplierStandardDocumentExpireDate(), supplierStandardDocumentDTO.getSupplierStandardDocumentExpireTime())));
		
		if(supplierStandardDocumentDTO.getStandardDocumentId() != null) {
			standardDocumentModel.setStandardDocumentId(transStandardDocumentDTO(supplierStandardDocumentDTO.getStandardDocumentId()));
		}
		
		if(supplierStandardDocumentDTO.getSupplierId() != null) {
			standardDocumentModel.setSupplierId(transSupplierDTO(supplierStandardDocumentDTO.getSupplierId()));
		}
		
		standardDocumentModel.setEnable(NullUtils.cvStr(supplierStandardDocumentDTO.getEnable()));
		standardDocumentModel.setCreateBy(NullUtils.cvStr(supplierStandardDocumentDTO.getCreateBy()));
		standardDocumentModel.setUpdateBy(NullUtils.cvStr(supplierStandardDocumentDTO.getUpdateBy()));
		standardDocumentModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierStandardDocumentDTO.getCreateDate(), supplierStandardDocumentDTO.getCreateTime())));
		standardDocumentModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierStandardDocumentDTO.getUpdateDate(), supplierStandardDocumentDTO.getUpdateTime())));
		standardDocumentModel.setCreateUser(transUserDTO(supplierStandardDocumentDTO.getCreateUser()));
		standardDocumentModel.setUpdateUser(transUserDTO(supplierStandardDocumentDTO.getUpdateUser()));
		return standardDocumentModel;
	}
	
	public static SupplierUserMappingModel transSupplierUserMappingDTO(SupplierUserMappingDTO supplierUserMappingDTO) {
		SupplierUserMappingModel supplierUserMappingModel = new SupplierUserMappingModel();
		
		supplierUserMappingModel.setSupplierId(NullUtils.cvStr(supplierUserMappingDTO.getSupplierId()));
		supplierUserMappingModel.setUserId(NullUtils.cvStr(supplierUserMappingDTO.getUserId()));
		
		supplierUserMappingModel.setEnable(NullUtils.cvStr(supplierUserMappingDTO.getEnable()));
		supplierUserMappingModel.setCreateBy(NullUtils.cvStr(supplierUserMappingDTO.getCreateBy()));
		supplierUserMappingModel.setUpdateBy(NullUtils.cvStr(supplierUserMappingDTO.getUpdateBy()));
		supplierUserMappingModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierUserMappingDTO.getCreateDate(), supplierUserMappingDTO.getCreateTime())));
		supplierUserMappingModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(supplierUserMappingDTO.getUpdateDate(), supplierUserMappingDTO.getUpdateTime())));
		return supplierUserMappingModel;
	}
	
	public static SystemConfigurationModel transSystemConfigurationDTO(SystemConfigurationDTO systemConfigurationDTO) {
		SystemConfigurationModel systemConfigurationModel = new SystemConfigurationModel();
		
		systemConfigurationModel.setSystemKey(systemConfigurationDTO.getSystemKey());
		systemConfigurationModel.setSystemValue(systemConfigurationDTO.getSystemValue());
		
		systemConfigurationModel.setEnable(NullUtils.cvStr(systemConfigurationDTO.getEnable()));
		systemConfigurationModel.setCreateBy(NullUtils.cvStr(systemConfigurationDTO.getCreateBy()));
		systemConfigurationModel.setUpdateBy(NullUtils.cvStr(systemConfigurationDTO.getUpdateBy()));
		systemConfigurationModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(systemConfigurationDTO.getCreateDate(), systemConfigurationDTO.getCreateTime())));
		systemConfigurationModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(systemConfigurationDTO.getUpdateDate(), systemConfigurationDTO.getUpdateTime())));
		
		return systemConfigurationModel;
	}
	
	public static SystemSequenceModel transSystemSequenceDTO(SystemSequenceDTO systemSequenceDTO) {
		SystemSequenceModel systemSequenceModel = new SystemSequenceModel();
		
		systemSequenceModel.setSeqKey(NullUtils.cvStr(systemSequenceDTO.getSeqKey()));
		systemSequenceModel.setSeqValue(NullUtils.cvStr(systemSequenceDTO.getSeqValue()));
		systemSequenceModel.setSeqDigits(NullUtils.cvStr(systemSequenceDTO.getSeqDigits()));
		systemSequenceModel.setUseKeyPrefix(NullUtils.cvStr(systemSequenceDTO.getUseKeyPrefix()));
		systemSequenceModel.setDelimit(NullUtils.cvStr(systemSequenceDTO.getDelimit()));
		systemSequenceModel.setSeqDescription(NullUtils.cvStr(systemSequenceDTO.getSeqDescription()));
		
		systemSequenceModel.setEnable(NullUtils.cvStr(systemSequenceDTO.getEnable()));
		systemSequenceModel.setCreateBy(NullUtils.cvStr(systemSequenceDTO.getCreateBy()));
		systemSequenceModel.setUpdateBy(NullUtils.cvStr(systemSequenceDTO.getUpdateBy()));
		systemSequenceModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(systemSequenceDTO.getCreateDate(), systemSequenceDTO.getCreateTime())));
		systemSequenceModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(systemSequenceDTO.getUpdateDate(), systemSequenceDTO.getUpdateTime())));
		
		return systemSequenceModel;
	}
	
	public static UserModel transUserDTO(UserDTO userDTO) {
		UserModel userModel = new UserModel();		
		userModel.setCreateBy(NullUtils.cvStr(userDTO.getCreateBy()));		
		userModel.setDescription(NullUtils.cvStr(userDTO.getDescription()));
		userModel.setEmail(NullUtils.cvStr(userDTO.getEmail()));
		userModel.setEmployeeId(NullUtils.cvStr(userDTO.getEmployeeId()));
		userModel.setEnable(NullUtils.cvStr(userDTO.getEnable()));
		userModel.setFullname(NullUtils.cvStr(userDTO.getFullname()));
		userModel.setPassword(NullUtils.cvStr(userDTO.getPassword()));
		userModel.setTelephone(NullUtils.cvStr(userDTO.getTelephone()));
		userModel.setUpdateBy(NullUtils.cvStr(userDTO.getUpdateBy()));		
		userModel.setUserId(NullUtils.cvStr(userDTO.getUserId()));
		userModel.setUsername(NullUtils.cvStr(userDTO.getUsername()));
		userModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(userDTO.getCreateDate(), userDTO.getCreateTime())));
		userModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(userDTO.getUpdateDate(), userDTO.getUpdateTime())));
		userModel.setSupplierId(new ArrayList<>());
		if(userDTO.getUserGroupId() != null) {
			userModel.setUserGroupId(transUserGroupDTO(userDTO.getUserGroupId()));
		}
		
		if(userDTO.getSupplierId() != null) {
			if(!userDTO.getSupplierId().isEmpty()) {
				for(SupplierDTO supplier : userDTO.getSupplierId()) {
					userModel.getSupplierId().add(transSupplierDTO(supplier));
				}
			}
		}
		
		return userModel;
	}	
	
	public static UserGroupModel transUserGroupDTO(UserGroupDTO userGroupDTO) {
		UserGroupModel userGroupModel = new UserGroupModel();
		userGroupModel.setAuthenType(NullUtils.cvStr(userGroupDTO.getAuthenType()));
		userGroupModel.setCreateBy(NullUtils.cvStr(userGroupDTO.getCreateBy()));		
		userGroupModel.setEnable(NullUtils.cvStr(userGroupDTO.getEnable()));
		userGroupModel.setUpdateBy(NullUtils.cvStr(userGroupDTO.getUpdateBy()));		
		userGroupModel.setUserGroupId(NullUtils.cvStr(userGroupDTO.getUserGroupId()));
		userGroupModel.setUserGroupName(NullUtils.cvStr(userGroupDTO.getUserGroupName()));
		userGroupModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(userGroupDTO.getCreateDate(), userGroupDTO.getCreateTime())));
		userGroupModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(userGroupDTO.getUpdateDate(), userGroupDTO.getUpdateTime())));
		return userGroupModel;
	}
	
	public static MenuModel transUserGroupMenuDTO(MenuDTO userGroupMenuDTO) {
		MenuModel userGroupMenuModel = new MenuModel();
		userGroupMenuModel.setCreateBy(NullUtils.cvStr(userGroupMenuDTO.getCreateBy()));
		userGroupMenuModel.setEnable(NullUtils.cvStr(userGroupMenuDTO.getEnable()));
		userGroupMenuModel.setMenuIcon(NullUtils.cvStr(userGroupMenuDTO.getMenuIcon()));
		userGroupMenuModel.setMenuName(NullUtils.cvStr(userGroupMenuDTO.getMenuName()));
		userGroupMenuModel.setMenuOrder(NullUtils.cvStr(userGroupMenuDTO.getMenuOrder()));
		userGroupMenuModel.setMenuSubOrder(NullUtils.cvStr(userGroupMenuDTO.getMenuSubOrder()));
		userGroupMenuModel.setMenuUrl(NullUtils.cvStr(userGroupMenuDTO.getMenuUrl()));
		userGroupMenuModel.setUpdateBy(NullUtils.cvStr(userGroupMenuDTO.getUpdateBy()));
		userGroupMenuModel.setMenuId(NullUtils.cvStr(userGroupMenuDTO.getMenuId()));
		userGroupMenuModel.setMenuParentId(NullUtils.cvStr(userGroupMenuDTO.getMenuParentId()));
		userGroupMenuModel.setCountSubMenu(NullUtils.cvStr(userGroupMenuDTO.getCoutSubMenu()));
		
		userGroupMenuModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(userGroupMenuDTO.getCreateDate(), userGroupMenuDTO.getCreateTime())));
		userGroupMenuModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(userGroupMenuDTO.getUpdateDate(), userGroupMenuDTO.getUpdateTime())));	
		
		userGroupMenuModel.setUserGroupId(new ArrayList<>());
		if(userGroupMenuDTO.getUserGroupId() != null) {
			if(!userGroupMenuDTO.getUserGroupId().isEmpty()) {
				for(UserGroupDTO userGroupDTO : userGroupMenuDTO.getUserGroupId()) {
					userGroupMenuModel.getUserGroupId().add(transUserGroupDTO(userGroupDTO));
				}
			}

		}
		return userGroupMenuModel;
	}
	
	
	public static AddressModel transAddressDTO(AddressDTO addressDTO) {
		AddressModel addressModel = new AddressModel();		
		addressModel.setAddress(NullUtils.cvStr(addressDTO.getAddress()));
		addressModel.setAddressId(NullUtils.cvStr(addressDTO.getAddressId()));
		addressModel.setCreateBy(NullUtils.cvStr(addressDTO.getCreateBy()));		
		addressModel.setEnable(NullUtils.cvStr(addressDTO.getEnable()));
		addressModel.setPostcode(NullUtils.cvStr(addressDTO.getPostcode()));
		addressModel.setUpdateBy(NullUtils.cvStr(addressDTO.getUpdateBy()));
		addressModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(addressDTO.getCreateDate(), addressDTO.getCreateTime())));
		addressModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(addressDTO.getUpdateDate(), addressDTO.getUpdateTime())));
		if(addressDTO.getSupplierId() != null) {
			addressModel.setSupplierId(transSupplierDTO(addressDTO.getSupplierId()));
		}
		if(addressDTO.getDistrictId() != null) {
			addressModel.setDistrictId(transDistrictDTO(addressDTO.getDistrictId()));
		}
		
		if(addressDTO.getProvinceId() != null) {
			addressModel.setProvinceId(transProvinceDTO(addressDTO.getProvinceId()));
		}
		
		if(addressDTO.getSubDistrictId() != null) {
			addressModel.setSubDistrictId(transSubDistrictDTO(addressDTO.getSubDistrictId()));
		}
		if(addressDTO.getSupplierProductAddressMappingDTO() != null) {
			addressModel.setSupplierProductAddressMappingModel(transSupplierProductAddressMappingDTO(addressDTO.getSupplierProductAddressMappingDTO()));
		}
		return addressModel;
	}
	
	public static DistrictModel transDistrictDTO(DistrictDTO districtDTO) {
		DistrictModel districtModel = new DistrictModel();
		districtModel.setCreateBy(NullUtils.cvStr(districtDTO.getCreateBy()).trim());
		districtModel.setCreateDate(NullUtils.cvStr(districtDTO.getCreateDate()).trim());
		districtModel.setDistrictId(NullUtils.cvStr(districtDTO.getDistrictId()).trim());
		districtModel.setName(NullUtils.cvStr(districtDTO.getName()).trim());
		districtModel.setPostcode(NullUtils.cvStr(districtDTO.getPostcode()).trim());				
		districtModel.setUpdateBy(NullUtils.cvStr(districtDTO.getUpdateBy()).trim());
		districtModel.setUpdateDate(NullUtils.cvStr(districtDTO.getUpdateDate()).trim());
		
		if(districtDTO.getProvinceId() != null) {
			districtModel.setProvinceId(transProvinceDTO(districtDTO.getProvinceId()));
		}
		if(districtDTO.getRegionId() != null) {
			districtModel.setRegionId(transRegionDTO(districtDTO.getRegionId()));
		}
		
		return districtModel;
	}
	
	public static ProvinceModel transProvinceDTO(ProvinceDTO provinceDTO) {
		ProvinceModel provinceModel = new ProvinceModel();
		provinceModel.setCreateBy(NullUtils.cvStr(provinceDTO.getCreateBy()).trim());
		provinceModel.setCreateDate(NullUtils.cvStr(provinceDTO.getCreateDate()).trim());
		provinceModel.setName(NullUtils.cvStr(provinceDTO.getName()).trim());
		provinceModel.setProvinceId(NullUtils.cvStr(provinceDTO.getProvinceId()).trim());		
		provinceModel.setUpdateBy(NullUtils.cvStr(provinceDTO.getUpdateBy()).trim());
		provinceModel.setUpdateDate(NullUtils.cvStr(provinceDTO.getUpdateDate()).trim());
		
		if(provinceDTO.getRegionId() != null) {
			provinceModel.setRegionId(transRegionDTO(provinceDTO.getRegionId()));
		}
		
		return provinceModel;
	}
	
	public static SubDistrictModel transSubDistrictDTO(SubDistrictDTO subDistrictDTO) {
		SubDistrictModel subDistrictModel = new SubDistrictModel();
		subDistrictModel.setCreateBy(NullUtils.cvStr(subDistrictDTO.getCreateBy()).trim());
		subDistrictModel.setCreateDate(NullUtils.cvStr(subDistrictDTO.getCreateDate()).trim());
		subDistrictModel.setName(NullUtils.cvStr(subDistrictDTO.getName()).trim());
		subDistrictModel.setSubDistrictId(NullUtils.cvStr(subDistrictDTO.getSubDistrictId()).trim());
		subDistrictModel.setUpdateBy(NullUtils.cvStr(subDistrictDTO.getUpdateBy()).trim());
		subDistrictModel.setUpdateDate(NullUtils.cvStr(subDistrictDTO.getUpdateDate()).trim());
		
		if(subDistrictDTO.getDistrictId() != null) {
			subDistrictModel.setDistrictId(transDistrictDTO(subDistrictDTO.getDistrictId()));
		}
		
		if(subDistrictDTO.getProvinceId() != null) {
			subDistrictModel.setProvinceId(transProvinceDTO(subDistrictDTO.getProvinceId()));
		}
		
		if(subDistrictDTO.getRegionId() != null) {
			subDistrictModel.setRegionId(transRegionDTO(subDistrictDTO.getRegionId()));
		}
		
		return subDistrictModel;
	}
	
	
	public static RegionModel transRegionDTO(RegionDTO regionDTO) {
		RegionModel regionModel = new RegionModel();
		regionModel.setCreateBy(NullUtils.cvStr(regionDTO.getCreateBy()).trim());
		regionModel.setCreateDate(DateUtils.parseSQLDateStringToWebDate(regionDTO.getCreateDate()).trim());
		regionModel.setName(NullUtils.cvStr(regionDTO.getName()).trim());
		regionModel.setRegionId(NullUtils.cvStr(regionDTO.getRegionId()).trim());
		regionModel.setUpdateBy(NullUtils.cvStr(regionDTO.getUpdateBy()).trim());
		regionModel.setUpdateDate(DateUtils.parseSQLDateStringToWebDate(regionDTO.getUpdateDate()).trim());		
		return regionModel;
	}
	
	public static QuestionaireDocumentModel transQuestionaireDocumentDTO(QuestionaireDocumentDTO questionaireDocumentDTO) {
		QuestionaireDocumentModel questionaireDocumentModel = new QuestionaireDocumentModel();
		questionaireDocumentModel.setQuestionaireDocumentId(NullUtils.cvStr(questionaireDocumentDTO.getQuestionaireDocumentId()));
		questionaireDocumentModel.setQuestionaireDocumentName(NullUtils.cvStr(questionaireDocumentDTO.getQuestionaireDocumentName()));
		questionaireDocumentModel.setEnable(NullUtils.cvStr(questionaireDocumentDTO.getEnable()));
		questionaireDocumentModel.setCreateBy(NullUtils.cvStr(questionaireDocumentDTO.getCreateBy()));
		questionaireDocumentModel.setCreateDate(DateUtils.parseSQLDateStringToWebDate(questionaireDocumentDTO.getCreateDate()));
		questionaireDocumentModel.setUpdateBy(NullUtils.cvStr(questionaireDocumentDTO.getUpdateBy()));
		questionaireDocumentModel.setUpdateDate(DateUtils.parseSQLDateStringToWebDate(questionaireDocumentDTO.getUpdateDate()));
		return questionaireDocumentModel;
	}
	
	public static QuestionaireDocumentTypeModel transQuestionaireDocumentTypeDTO(QuestionaireDocumentTypeDTO questionaireDocumentTypeDTO) {
		QuestionaireDocumentTypeModel questionaireDocumentTypeModel = new QuestionaireDocumentTypeModel();
		questionaireDocumentTypeModel.setQuestionaireDocumentTypeId(NullUtils.cvStr(questionaireDocumentTypeDTO.getQuestionaireDocumentTypeId()));
		questionaireDocumentTypeModel.setQuestionaireDocumentTypeName(NullUtils.cvStr(questionaireDocumentTypeDTO.getQuestionaireDocumentTypeName()));
		questionaireDocumentTypeModel.setEnable(NullUtils.cvStr(questionaireDocumentTypeDTO.getEnable()));
		questionaireDocumentTypeModel.setCreateBy(NullUtils.cvStr(questionaireDocumentTypeDTO.getCreateBy()));
		questionaireDocumentTypeModel.setCreateDate(DateUtils.parseSQLDateStringToWebDate(questionaireDocumentTypeDTO.getCreateDate()));
		questionaireDocumentTypeModel.setUpdateBy(NullUtils.cvStr(questionaireDocumentTypeDTO.getUpdateBy()));
		questionaireDocumentTypeModel.setUpdateDate(DateUtils.parseSQLDateStringToWebDate(questionaireDocumentTypeDTO.getUpdateDate()));
		return questionaireDocumentTypeModel;
	}
	
	public static SupplierQuestionaireDocumentModel transSupplierQuestionaireDocumentDTO(SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTO) {
		SupplierQuestionaireDocumentModel supplierQuestionaireDocumentModel = new SupplierQuestionaireDocumentModel();
		supplierQuestionaireDocumentModel.setSupplierQuestionaireDocumentId(NullUtils.cvStr(supplierQuestionaireDocumentDTO.getSupplierQuestionaireDocumentId()));
		supplierQuestionaireDocumentModel.setSupplierQuestionaireDocumentLocation(NullUtils.cvStr(supplierQuestionaireDocumentDTO.getSupplierQuestionaireDocumentLocation()));
		supplierQuestionaireDocumentModel.setEnable(NullUtils.cvStr(supplierQuestionaireDocumentDTO.getEnable()));
		supplierQuestionaireDocumentModel.setCreateBy(NullUtils.cvStr(supplierQuestionaireDocumentDTO.getCreateBy()));
		supplierQuestionaireDocumentModel.setCreateDate(DateUtils.parseSQLDateStringToWebDate(supplierQuestionaireDocumentDTO.getCreateDate()));
		supplierQuestionaireDocumentModel.setUpdateBy(NullUtils.cvStr(supplierQuestionaireDocumentDTO.getUpdateBy()));
		supplierQuestionaireDocumentModel.setUpdateDate(DateUtils.parseSQLDateStringToWebDate(supplierQuestionaireDocumentDTO.getUpdateDate()));
		
		if(supplierQuestionaireDocumentDTO.getQuestionaireDocumentTypeId() != null) {
			supplierQuestionaireDocumentModel.setQuestionaireDocumentTypeId(transQuestionaireDocumentTypeDTO(supplierQuestionaireDocumentDTO.getQuestionaireDocumentTypeId()));
		}
		
		if(supplierQuestionaireDocumentDTO.getSupplierId() != null) {
			supplierQuestionaireDocumentModel.setSupplierId(transSupplierDTO(supplierQuestionaireDocumentDTO.getSupplierId()));
		}
		
		if(supplierQuestionaireDocumentDTO.getQuestionaireDocumentId() != null) {
			supplierQuestionaireDocumentModel.setQuestionaireDocumentId(transQuestionaireDocumentDTO(supplierQuestionaireDocumentDTO.getQuestionaireDocumentId()));
		}
		
		return supplierQuestionaireDocumentModel;
	}
	
	public static InformationModel transInformationDTO(InformationDTO informationDTO) {
		InformationModel informationModel = new InformationModel();
		informationModel.setInformationId(NullUtils.cvInt(informationDTO.getInformationId()));
		informationModel.setInformationTitle(NullUtils.cvStr(informationDTO.getInformationTitle()));
		informationModel.setInformationType(NullUtils.cvStr(informationDTO.getInformationType()));
		informationModel.setDescription(NullUtils.cvStr(informationDTO.getDescription()));
		informationModel.setGroupType(NullUtils.cvStr(informationDTO.getGroupType()));

		if(informationDTO.getSupplierId() != null) {
			informationModel.setSupplierId(transSupplierDTO(informationDTO.getSupplierId()));
		}

		if(informationDTO.getSupplierList() != null ) {
			String [] supplierList = informationDTO.getSupplierList().split(",");
			ArrayList<String> suplist = new ArrayList<String>();
			for(String supplier : supplierList) {
				suplist.add(supplier);
			}
			informationModel.setSupplierIdList(suplist);
		}
		if(informationDTO.getProductTypeId() != null) {
			informationModel.setProductTypeId(transProductTypeDTO(informationDTO.getProductTypeId()));
		}
		informationModel.setEnable(NullUtils.cvStr(informationDTO.getEnable()));
		informationModel.setCreateBy(NullUtils.cvStr(informationDTO.getCreateBy()));
		informationModel.setUpdateBy(NullUtils.cvStr(informationDTO.getUpdateBy()));
		informationModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(informationDTO.getCreateDate(), informationDTO.getCreateTime())));
		informationModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(informationDTO.getUpdateDate(), informationDTO.getUpdateTime())));
		informationModel.setAcceptDate(NullUtils.cvStr(convertDateTimeSQLToString(informationDTO.getAcceptDate(), informationDTO.getAcceptTime())));
		informationModel.setSendDate(NullUtils.cvStr(convertDateTimeSQLToString(informationDTO.getSendDate(), informationDTO.getSendTime())));

		ArrayList<InformationDetailModel> informationDetailId = new ArrayList<InformationDetailModel>();
		if(informationDTO.getInformationDetailId() != null) {
			for(InformationDetailDTO informationDetailDTO : informationDTO.getInformationDetailId()) {
				informationDetailId.add(transInformationDetailDTO(informationDetailDTO));
			}
		}
		informationModel.setInformationDetailId(informationDetailId);

		ArrayList<InformationDocumentModel> informationPictureList= new ArrayList<InformationDocumentModel>();
		if(informationDTO.getInformationPictureList() != null) {
			for(InformationDocumentDTO informationDocumentDTO : informationDTO.getInformationPictureList()) {
				informationPictureList.add(TransformDTO.transInformationDocumentDTO(informationDocumentDTO));
			}
		}
		informationModel.setInformationPictureList(informationPictureList);
		
		ArrayList<InformationDocumentModel> informationDocumentList = new ArrayList<InformationDocumentModel>();
		if(informationDTO.getInformationDocumentList() != null) {
			for(InformationDocumentDTO informationDocumentDTO : informationDTO.getInformationDocumentList()) {
				informationDocumentList.add(TransformDTO.transInformationDocumentDTO(informationDocumentDTO));
			}
		}
		informationModel.setInformationDocumentList(informationDocumentList);
		return informationModel;
	}
	
	public static InformationDocumentModel transInformationDocumentDTO(InformationDocumentDTO informationDocumentDTO) {
		InformationDocumentModel informationDocumentModel = new InformationDocumentModel();
		informationDocumentModel.setInformationDocumentId(NullUtils.cvStr(informationDocumentDTO.getInformationDocumentId()));
		if(informationDocumentDTO.getInformationId() != null) {
			informationDocumentModel.setInformationId(transInformationDTO(informationDocumentDTO.getInformationId()));
		}
		informationDocumentModel.setInformationDocumentLocation(NullUtils.cvStr(informationDocumentDTO.getInformationDocumentLocation()));
		informationDocumentModel.setInformationDocumentType(NullUtils.cvStr(informationDocumentDTO.getInformationDocumentType()));
		
		informationDocumentModel.setEnable(NullUtils.cvStr(informationDocumentDTO.getEnable()));
		informationDocumentModel.setCreateBy(NullUtils.cvStr(informationDocumentDTO.getCreateBy()));
		informationDocumentModel.setUpdateBy(NullUtils.cvStr(informationDocumentDTO.getUpdateBy()));
		informationDocumentModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(informationDocumentDTO.getCreateDate(), informationDocumentDTO.getCreateTime())));
		informationDocumentModel.setUpdateDate(NullUtils.cvStr(convertDateTimeSQLToString(informationDocumentDTO.getUpdateDate(), informationDocumentDTO.getUpdateTime())));
		
		return informationDocumentModel;
	}
	
	public static InformationDetailModel transInformationDetailDTO(InformationDetailDTO informationDetailDTO) {
		InformationDetailModel informationDetailModel = new InformationDetailModel();
		informationDetailModel.setInformationDetailId(NullUtils.cvStr(informationDetailDTO.getInformationDetailId()));
		if(informationDetailDTO.getInformationId() != null) {
			informationDetailModel.setInformationId(transInformationDTO(informationDetailDTO.getInformationId()));
		}
		if(informationDetailDTO.getSupplierId() != null) {
			informationDetailModel.setSupplierId(transSupplierDTO(informationDetailDTO.getSupplierId()));
		}
		informationDetailModel.setCreateBy(NullUtils.cvStr(informationDetailDTO.getCreateBy()));
		informationDetailModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(informationDetailDTO.getCreateDate(), informationDetailDTO.getCreateTime())));

		if(informationDetailDTO.getAcceptBy() != null) {
			informationDetailModel.setAcceptBy(transUserDTO(informationDetailDTO.getAcceptBy()));
		}
		informationDetailModel.setAcceptDate(NullUtils.cvStr(convertDateTimeSQLToString(informationDetailDTO.getAcceptDate(), informationDetailDTO.getAcceptTime())));
		informationDetailModel.setAcceptStatus(NullUtils.cvChar(informationDetailDTO.getAcceptStatus()));
		return informationDetailModel;
	}
	
	public static SystemLogModel transSystemLogDTO(SystemLogDTO systemLogDTO) {
		SystemLogModel systemLogModel = new SystemLogModel();		
		if(systemLogDTO.getAccess() != null) {
			systemLogModel.setAccess(NullUtils.cvStr(systemLogDTO.getAccess()));
		}
		if(systemLogDTO.getActivity() != null) {
			systemLogModel.setActivity(NullUtils.cvStr(systemLogDTO.getActivity()));
		}
		if(systemLogDTO.getDetail() != null) {
			systemLogModel.setDetail(NullUtils.cvStr(systemLogDTO.getDetail()));
		}
		if(systemLogDTO.getRefId() != null) {
			systemLogModel.setRefId(NullUtils.cvStr(systemLogDTO.getRefId()));
		}
		if(systemLogDTO.getCreateDate() != null) {
			systemLogModel.setCreateDate(NullUtils.cvStr(convertDateTimeSQLToString(systemLogDTO.getCreateDate(),systemLogDTO.getCreateTime())));
		}
		systemLogModel.setCreateBy(NullUtils.cvStr(systemLogDTO.getCreateBy()));
		systemLogModel.setCreateByName(NullUtils.cvStr(systemLogDTO.getCreateByName()));
		
		if(systemLogDTO.getNote() != null) {
			systemLogModel.setNote(NullUtils.cvStr(systemLogDTO.getNote()));
		}		
		return systemLogModel;
	}
	
	private static String convertDateTimeSQLToString(Date date, Time time) {
		return String.format("%s %s", DateUtils.parseSQLDateStringToWebDate(date), DateUtils.parseSQLTimeStringToWebTime(time));
	}
	
}
