package th.co.gosoft.audit.cpram.controller;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.common.ConfigurationSystemManager;
import th.co.gosoft.audit.cpram.conts.ConstQuestionaireDocumentType;
import th.co.gosoft.audit.cpram.dao.SupplierQuestionaireDocumentDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dto.QuestionaireDocumentDTO;
import th.co.gosoft.audit.cpram.dto.QuestionaireDocumentTypeDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierQuestionaireDocumentDTO;
import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.SupplierQuestionaireDocumentModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class SupplierQuestionaireDocumentController {
	
	private final static Logger logger = Logger.getLogger(SupplierQuestionaireDocumentController.class);
	
	public String getSupplierQuestionaireDocumentList(HttpServletRequest servletRequest, String objSupplierQuestionaireDocument) {
		Connection connection = null;
		SupplierQuestionaireDocumentDAO supplierQuestionaireDocumentDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO  = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		String resultResponse = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierQuestionaireDocumentDAO = new SupplierQuestionaireDocumentDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			sessionUtils = new SessionUtils(servletRequest);
			gson = new Gson();
			
			UserModel userModelSession = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			SupplierQuestionaireDocumentModel supplierQuestionaireDocumentModelRequest = gson.fromJson(objSupplierQuestionaireDocument, SupplierQuestionaireDocumentModel.class);
			StringBuilder queryWhereClause = new StringBuilder();
			
			if(userModelSession != null) {
				
				queryWhereClause.append(" AND ").append(" ( qdt.questionaire_document_type_id = ").append(ConstQuestionaireDocumentType.TEMPLATE_QUESTIONAIRE).append(" ");
				queryWhereClause.append(" OR ").append(" qdt.questionaire_document_type_id = ").append(ConstQuestionaireDocumentType.SUPPLIER_QUESTIONAIRE).append(" ) ");
				
				if(NullUtils.cvInt(userModelSession.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || 
				   NullUtils.cvInt(userModelSession.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {			
					
					List<SupplierDTO> supplierList = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userModelSession));
					if(supplierList != null && !supplierList.isEmpty()) {
						queryWhereClause.append(" AND ( ");
						int countSupplierList = 1;
						for(SupplierDTO supplier : supplierList) {
							if(countSupplierList != 1) {
								queryWhereClause.append(" OR ");
							}
							queryWhereClause.append(" sqd.supplier_id = ").append(supplier.getSupplierId()).append(" ");
						}
						queryWhereClause.append(" OR ").append(" sqd.supplier_id = ").append("0").append(" ) ");
					}else {
						queryWhereClause = new StringBuilder();
					}
				}else {
					
					if(supplierQuestionaireDocumentModelRequest.getSupplierId() != null && !StringUtils.isNullOrEmpty(supplierQuestionaireDocumentModelRequest.getSupplierId().getSupplierId())) {
						queryWhereClause.append(" AND ").append(" s.supplier_id = ").append(supplierQuestionaireDocumentModelRequest.getSupplierId().getSupplierId()).append(" ");
					}
					
				}	
				
				
				if(!StringUtils.isNullOrEmpty(queryWhereClause.toString().trim())) {
					queryWhereClause.append(" AND ").append(" sqd.enable = 'Y' ");
					
					List<SupplierQuestionaireDocumentDTO> supplierQuestionaireDocumentDTOList = supplierQuestionaireDocumentDAO.getSupplierQuestionaireDocumentList(0, supplierQuestionaireDocumentDAO.countSupplierQuestionaireDocumentList(queryWhereClause.toString()), queryWhereClause.toString());
					List<SupplierQuestionaireDocumentModel> supplierQuestionaireDocumentModelList = new ArrayList<SupplierQuestionaireDocumentModel>();
					
					for(SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTO : supplierQuestionaireDocumentDTOList) {
						supplierQuestionaireDocumentModelList.add(TransformDTO.transSupplierQuestionaireDocumentDTO(supplierQuestionaireDocumentDTO));
					}
					
					resultResponse = gson.toJson(supplierQuestionaireDocumentModelList);
				}
				
			}
			
			return resultResponse;
		}catch (Exception e) {
			logger.error("SupplierQuestionaireDocumentController.getSupplierQuestionaireDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public DataTableModel<SupplierQuestionaireDocumentModel> getQuestionaireDocumentMappingSupplierList(DataTablePostParamModel dataTablePostParamModel) {
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			SupplierQuestionaireDocumentDAO supplierQuestionaireDocumentDAO = new SupplierQuestionaireDocumentDAO(connection);
			DataTableModel<SupplierQuestionaireDocumentModel> dataTableModel = new DataTableModel<>();
			
			StringBuilder queryWhereClause = new StringBuilder();
			for (Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					if (col.getName().equals("supplierCompany")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						queryWhereClause.append(" AND ");
						queryWhereClause.append("summary_questionaire_document_supplier.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if(col.getName().equals("questionaireDocumentId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("summary_questionaire_document_supplier.questionaire_document_id").append(" = ").append(col.getSearch().getValue()).append(" ");
					}
				}
			}
			
			List<SupplierQuestionaireDocumentDTO> supplierQuestionaireDocumentDTOList = supplierQuestionaireDocumentDAO.getQuestionaireDocumentMappingSupplierList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			int countSupplierQuestionaireDocument = supplierQuestionaireDocumentDAO.countQuestionaireDocumentMappingSupplierList(queryWhereClause.toString());		
			dataTableModel.setData(new ArrayList<>());
			
			dataTableModel.setRecordsFiltered(countSupplierQuestionaireDocument);
			dataTableModel.setRecordsTotal(countSupplierQuestionaireDocument);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			
			for(SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTO : supplierQuestionaireDocumentDTOList) {
				dataTableModel.getData().add(TransformDTO.transSupplierQuestionaireDocumentDTO(supplierQuestionaireDocumentDTO));
			}
			
			return dataTableModel;
		}catch (Exception e) {
			logger.error("SupplierQuestionaireDocumentController.getQuestionaireDocumentMappingSupplierList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public boolean insertSupplierQuestionaireDocumentList(HttpServletRequest servletRequest, String objSupplierQuestionaireDocumentList) {
		Connection connection = null;	
		
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			SupplierQuestionaireDocumentDAO supplierQuestionaireDocumentDAO = new SupplierQuestionaireDocumentDAO(connection);
			SupplierUserMappingDAO supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			SessionUtils sessionUtils = new SessionUtils(servletRequest);
			FileProcessing fileProcessing = new FileProcessing();
			Gson gson = new Gson();			
			boolean resultProcess = false;
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			Type listType = new TypeToken<ArrayList<SupplierQuestionaireDocumentModel>>(){}.getType();
			List<SupplierQuestionaireDocumentModel> supplierQuestionaireDocumentModelListRequest = gson.fromJson(objSupplierQuestionaireDocumentList, listType);
			
			int supplierId = 0;
			if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
				SupplierDTO supplierDTO = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel)).get(0);
				supplierId = supplierDTO.getSupplierId();
			}
			
			if(supplierQuestionaireDocumentModelListRequest != null && !supplierQuestionaireDocumentModelListRequest.isEmpty()) {
				for(SupplierQuestionaireDocumentModel supplierQuestionaireDocumentModelRequest : supplierQuestionaireDocumentModelListRequest) {
					
					String filePathTemp = FileUploadConst.PATH_Temp.concat(supplierQuestionaireDocumentModelRequest.getSupplierQuestionaireDocumentLocation());
					String filePathQuestionaireDocument = FileUploadConst.PATH_Supplier_Questionaire_Document.concat(NullUtils.cvStr(supplierId).concat("_").concat(supplierQuestionaireDocumentModelRequest.getSupplierQuestionaireDocumentLocation()));
					String urlAccessFilePath = String.format("%s%s%s%s%s", ConfigurationSystemManager.getInstance().getHttpWebserverName(), ConfigurationSystemManager.getInstance().getHttpAliasSupplierQuestionaireDocument(), NullUtils.cvStr(supplierId), "_", supplierQuestionaireDocumentModelRequest.getSupplierQuestionaireDocumentLocation());
					
					resultProcess = fileProcessing.CopyFile(filePathTemp, filePathQuestionaireDocument);
					if(resultProcess) {
						
						SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTOInsert = new SupplierQuestionaireDocumentDTO();
						supplierQuestionaireDocumentDTOInsert.setCreateBy(NullUtils.cvInt(userSessionModel.getUserId()));
						supplierQuestionaireDocumentDTOInsert.setUpdateBy(NullUtils.cvInt(userSessionModel.getUserId()));
						supplierQuestionaireDocumentDTOInsert.setEnable('Y');
						supplierQuestionaireDocumentDTOInsert.setSupplierQuestionaireDocumentLocation(urlAccessFilePath);
						
						supplierQuestionaireDocumentDTOInsert.setSupplierId(new SupplierDTO());
						supplierQuestionaireDocumentDTOInsert.getSupplierId().setSupplierId(NullUtils.cvInt(supplierId));
						
						supplierQuestionaireDocumentDTOInsert.setQuestionaireDocumentId(new QuestionaireDocumentDTO());
						supplierQuestionaireDocumentDTOInsert.getQuestionaireDocumentId().setQuestionaireDocumentId(NullUtils.cvInt(supplierQuestionaireDocumentModelRequest.getQuestionaireDocumentId().getQuestionaireDocumentId()));
						
						supplierQuestionaireDocumentDTOInsert.setQuestionaireDocumentTypeId(new QuestionaireDocumentTypeDTO());
						supplierQuestionaireDocumentDTOInsert.getQuestionaireDocumentTypeId().setQuestionaireDocumentTypeId(NullUtils.cvInt(supplierQuestionaireDocumentModelRequest.getQuestionaireDocumentTypeId().getQuestionaireDocumentTypeId()));
						
						resultProcess = supplierQuestionaireDocumentDAO.insertSupplierQuestionaireDocument(supplierQuestionaireDocumentDTOInsert);
						
						if(!resultProcess) {
							break;
						}else {
							fileProcessing.DeleteFile(filePathTemp);
						}
						
					}else {
						break;
					}					
					
				}
			}
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}			
			return resultProcess;
		}catch (Exception e) {
			logger.error("SupplierQuestionaireDocumentController.insertSupplierQuestionaireDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public boolean deleteSupplierQuestionaireDocument(int supplierQuestionaireDocumentId) {
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			SupplierQuestionaireDocumentDAO supplierQuestionaireDocumentDAO = new SupplierQuestionaireDocumentDAO(connection);
			StringBuilder queryWhereClause = new StringBuilder();
			FileProcessing fileProcessing = new FileProcessing();
			boolean resultProcess = false;
			if(supplierQuestionaireDocumentId != 0) {
				
				queryWhereClause.append(" AND ").append(" sqd.supplier_questionaire_document_id = '").append(supplierQuestionaireDocumentId).append("' ");
				SupplierQuestionaireDocumentDTO supplierQuestionaireDocumentDTO = supplierQuestionaireDocumentDAO.getSupplierQuestionaireDocumentList(0, supplierQuestionaireDocumentDAO.countSupplierQuestionaireDocumentList(queryWhereClause.toString()), queryWhereClause.toString()).get(0);
				
				resultProcess = supplierQuestionaireDocumentDAO.deleteSupplierQuestionaireDocument(supplierQuestionaireDocumentDTO);
				
				if(resultProcess) {
					String filenameQuestionaireDocument = supplierQuestionaireDocumentDTO.getSupplierQuestionaireDocumentLocation().split("/")[supplierQuestionaireDocumentDTO.getSupplierQuestionaireDocumentLocation().split("/").length-1].trim();
					String pathQuestionaireDocument = FileUploadConst.PATH_Supplier_Questionaire_Document.concat(filenameQuestionaireDocument);
					if(!StringUtils.isEmptyOrWhitespaceOnly(filenameQuestionaireDocument)) {
						resultProcess = fileProcessing.DeleteFile(pathQuestionaireDocument);
					}else {
						resultProcess = false;
					}
				}
				
				if(resultProcess) {
					DatabaseUtils.commit(connection);
				}else {
					DatabaseUtils.rollback(connection);
				}
				
			}else {
				resultProcess = true;
			}			
			return resultProcess;
		}catch (Exception e) {
			logger.error("SupplierQuestionaireDocumentController.deleteSupplierQuestionaireDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}

}
