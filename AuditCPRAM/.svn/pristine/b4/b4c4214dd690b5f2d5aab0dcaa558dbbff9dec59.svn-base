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
import th.co.gosoft.audit.cpram.conts.ConstInformation;
import th.co.gosoft.audit.cpram.dao.InformationDAO;
import th.co.gosoft.audit.cpram.dao.InformationDetailDAO;
import th.co.gosoft.audit.cpram.dao.InformationDocumentDAO;
import th.co.gosoft.audit.cpram.dao.SupplierDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dto.InformationDTO;
import th.co.gosoft.audit.cpram.dto.InformationDetailDTO;
import th.co.gosoft.audit.cpram.dto.InformationDocumentDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.UserDTO;
import th.co.gosoft.audit.cpram.fileupload.FileUploadConst;
import th.co.gosoft.audit.cpram.mail.BodyEmailConfirmFinalAuditResultForSupplier;
import th.co.gosoft.audit.cpram.mail.MailConnector;
import th.co.gosoft.audit.cpram.mail.MailReceiver;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.InformationDetailModel;
import th.co.gosoft.audit.cpram.model.InformationDocumentModel;
import th.co.gosoft.audit.cpram.model.InformationModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.FileProcessing;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class InformationController {
	
	private final static Logger logger = Logger.getLogger(InformationController.class);
	
	public String getInformation(HttpServletRequest httpServletRequest, String informationListObj) {
		Connection connection = null;
		Gson gson = null;
		InformationDAO informationDAO = null;
		InformationDetailDAO informationDetailDAO = null;
		String informationListResponse = "";
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			informationDAO = new InformationDAO(connection);
			informationDetailDAO = new InformationDetailDAO(connection);
			StringBuilder sbWhereCause = new StringBuilder();
			InformationModel informationModel = gson.fromJson(informationListObj, InformationModel.class);
			sbWhereCause.append(" AND ");
			sbWhereCause.append("i.information_id").append(" = '").append(informationModel.getInformationId()).append("' ");
			List<InformationDTO> informationDTOs = informationDAO.getInformationList(0, 1, sbWhereCause.toString());
			for(InformationDTO informationDTO : informationDTOs) {
				if(!informationDTO.getInformationType().equals(NullUtils.cvChar(ConstInformation.INFORMATION_DRAFT))) {
					sbWhereCause = new StringBuilder();
					sbWhereCause.append(" AND ");
					sbWhereCause.append("id.information_id").append(" = '").append(informationModel.getInformationId()).append("' ");
					List<InformationDetailDTO> informationDetailDTOs = informationDetailDAO.getInformationDetailList(0, informationDetailDAO.countInformationDetailList(sbWhereCause.toString()), sbWhereCause.toString());
					informationDTO.setInformationDetailId(informationDetailDTOs);
				}
				informationListResponse = gson.toJson(TransformDTO.transInformationDTO(informationDTO));
			}

			return informationListResponse;
		}catch (Exception e) {
			logger.error("InformationController.getInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String insertInformation(HttpServletRequest httpServletRequest, String informationListObj) {
		Connection connection = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		InformationDAO informationDAO = null;
		String informationResponse = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();			
			informationDAO = new InformationDAO(connection);
			InformationModel informationModel = gson.fromJson(informationListObj, InformationModel.class);
			sessionUtils = new SessionUtils(httpServletRequest);		
			informationModel.setCreateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));	

			int informationId = informationDAO.insertInformation(TransformModel.transInformationModel(informationModel));
			informationModel.setInformationId(informationId);
			if(informationId > 0) {
				DatabaseUtils.commit(connection);
				informationResponse = gson.toJson(informationModel);
			}else {
				DatabaseUtils.rollback(connection);
				informationResponse = null;
			}
			
			return informationResponse;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("InformationController.insertInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean updateInformation(HttpServletRequest httpServletRequest, String informationListObj) {
		Connection connection = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		InformationDAO informationDAO = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();			
			informationDAO = new InformationDAO(connection);
			InformationModel informationModel = gson.fromJson(informationListObj, InformationModel.class);
			sessionUtils = new SessionUtils(httpServletRequest);		
			informationModel.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));	
			informationModel.setInformationType(NullUtils.cvStr(ConstInformation.INFORMATION_DRAFT));
			
			resultProcess = informationDAO.updateInformation(TransformModel.transInformationModel(informationModel));
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("InformationController.updateInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean insertInformationDocumentList(HttpServletRequest httpServletRequest, String informationDocumentListObj) {
		Connection connection = null;
		Gson gson = null;
		InformationDocumentDAO informationDocumentDAO = null;
		SessionUtils sessionUtils = null;
		FileProcessing fileProcessing = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			informationDocumentDAO = new InformationDocumentDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			fileProcessing = new FileProcessing();
			gson = new Gson();
			
			Type listType = new TypeToken<ArrayList<InformationDocumentModel>>(){}.getType();
			
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			List<InformationDocumentModel> informationDocumentModels = gson.fromJson(informationDocumentListObj, listType);
			
//			int supplierStandardDocumentExpireDay = ConfigurationSystemManager.getInstance().getDateTimeSupplierStandardDocumentExpireDay();
//			LocalDate localDateExpireDocument = LocalDate.now().plusDays(supplierStandardDocumentExpireDay);
			if(!informationDocumentModels.isEmpty()) {
				for(InformationDocumentModel informationDocument : informationDocumentModels) {
					informationDocument.setCreateBy(userSessionModel.getUserId());
					String filePathTemp = FileUploadConst.PATH_Temp.concat(informationDocument.getInformationDocumentLocation());
					String filePathStandardDocument = FileUploadConst.PATH_Information_Document.concat(NullUtils.cvStr(informationDocument.getInformationId().getInformationId()).concat("_").concat(informationDocument.getInformationDocumentLocation()));
					String urlAccessFilePath = String.format("%s%s%s%s%s", ConfigurationSystemManager.getInstance().getHttpWebserverName(), ConfigurationSystemManager.getInstance().getHttpAliasInformationDocument(), NullUtils.cvStr(informationDocument.getInformationId().getInformationId()), "_", informationDocument.getInformationDocumentLocation());
					
					resultProcess = fileProcessing.CopyFile(filePathTemp, filePathStandardDocument);
					if(resultProcess) {
						informationDocument.setInformationDocumentLocation(urlAccessFilePath);
						informationDocument.setCreateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));	
						resultProcess = informationDocumentDAO.insertInformationDocument(TransformModel.transInformationDocumentModel(informationDocument));
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
			DatabaseUtils.rollback(connection);
			logger.error("InformationController.insertInformationDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public DataTableModel<InformationModel> dataTableGetInformationList(DataTablePostParamModel dataTablePostParamModel){
		InformationDAO informationDAO = null;
		SupplierDAO supplierDAO = null;
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			informationDAO = new InformationDAO(connection);
			supplierDAO = new SupplierDAO(connection);
			DataTableModel<InformationModel> dataTableModel = new DataTableModel<>();
			
			StringBuilder sbWhereCause = new StringBuilder();
			for (Column col : dataTablePostParamModel.getColumns()) {
				
				if (!StringUtils.isNullOrEmpty(col.getName())) {					
					if (col.getName().equals("informationTitle")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						sbWhereCause.append(" AND ");
						sbWhereCause.append("i.information_title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");

					}if (col.getName().equals("selectType")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						if(col.getSearch().getValue().equals("S")) {
							sbWhereCause.append(" AND ");
							sbWhereCause.append("i.group_type").append(" = '2' ");
						}else {
							sbWhereCause.append(" AND ");
							sbWhereCause.append("i.group_type").append(" = '1' ");
							sbWhereCause.append(" AND ");
							sbWhereCause.append("i.product_type_id").append(" = '").append(col.getSearch().getValue()).append("' ");
						}
					}if (col.getName().equals("informationType")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("i.information_type").append(" = '").append(col.getSearch().getValue()).append("' ");
					}if (col.getName().equals("sendDate")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("i.send_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
					if (col.getName().equals("supplierName")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						StringBuilder queryWhereClause = new StringBuilder();
						queryWhereClause.append(" AND s.supplier_company ").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
						List<SupplierDTO> supplierDTOs = supplierDAO.getSupplierList(queryWhereClause.toString());
						for(int i = 0 ; i < supplierDTOs.size() ; i++) {
							if(i==0) {
								sbWhereCause.append(" AND ( concat(',',i.supplier_id,',') like ").append(" '%").append(","+supplierDTOs.get(i).getSupplierId()+",").append("%'  ");
							}else{
								sbWhereCause.append(" OR concat(',',i.supplier_id,',') like ").append(" '%").append(","+supplierDTOs.get(i).getSupplierId()+",").append("%'  ");
							}
							if(i+1 == supplierDTOs.size()) {
								sbWhereCause.append(" ) ");
							}
						}
					}
				}
			}
			
			List<InformationDTO> informationDTOs = informationDAO.getInformationList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString());
			int countChecklist = informationDAO.countInformationList(sbWhereCause.toString());
			dataTableModel.setRecordsFiltered(countChecklist);
			dataTableModel.setRecordsTotal(countChecklist);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setData(new ArrayList<>());
			for(InformationDTO informationDTO : informationDTOs) {
				dataTableModel.getData().add(TransformDTO.transInformationDTO(informationDTO));
			}
			return dataTableModel;
		}catch (Exception e) {
			logger.error("InformationController.dataTableGetInformationList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean sendInformation(HttpServletRequest httpServletRequest, String informationListObj) {
		Connection connection = null;
		Gson gson = null;
		InformationDAO informationDAO = null;
		InformationDetailDAO informationDetailDAO = null;
		SupplierDAO supplierDAO = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			informationDAO = new InformationDAO(connection);
			informationDetailDAO = new InformationDetailDAO(connection);
			supplierDAO = new SupplierDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			InformationModel informationModel = gson.fromJson(informationListObj, InformationModel.class);
			sessionUtils = new SessionUtils(httpServletRequest);		
			informationModel.setUpdateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));	
			informationModel.setInformationType(NullUtils.cvStr(ConstInformation.INFORMATION_SEND));
			resultProcess = informationDAO.updateInformation(TransformModel.transInformationModel(informationModel));

			InformationDetailModel informationDetailModel = new InformationDetailModel();
			informationDetailModel.setInformationId(informationModel);
			informationDetailModel.setCreateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
			if(resultProcess) {
				List<SupplierDTO> supplierDTOs = new ArrayList<SupplierDTO>();
				if(informationModel.getGroupType().equals(NullUtils.cvStr(ConstInformation.GROUP_PRODUCT_TYPE))) {
					supplierDTOs = supplierDAO.getSupplierListGroupByProduct(informationModel.getProductTypeId().getProductTypeId());
				}else if(informationModel.getGroupType().equals(NullUtils.cvStr(ConstInformation.GROUP_SUPPLIER_LIST))) {
					for(String supplier : informationModel.getSupplierIdList()) {
						SupplierDTO supplierDTO = supplierDAO.getSupplierBySupplierId(NullUtils.cvStr(supplier));
						supplierDTOs.add(supplierDTO);
					}
				}
				if(supplierDTOs.size() == 0) {
					resultProcess = false;
				}
				for(SupplierDTO supplierIdObj : supplierDTOs) {
					informationDetailModel.setSupplierId(TransformDTO.transSupplierDTO(supplierIdObj));
					resultProcess = informationDetailDAO.insertInformationDetail(TransformModel.transInformationDetailModel(informationDetailModel));
					if(!resultProcess) {
						break;
					}
				}
				if(resultProcess) {
					DatabaseUtils.commit(connection);
					for(SupplierDTO supplierDTO : supplierDTOs) {
						try {
							List<UserDTO> userDTOs = supplierUserMappingDAO.getUserInSupplier(supplierDTO);
							List<String> reciecverEmail = new ArrayList<>();
							for(UserDTO user : userDTOs) {
								if(user.getEmail() != null) {
									reciecverEmail.add(user.getEmail());
								}
							}
							MailReceiver mailReceiver = new MailReceiver();
							if(reciecverEmail.size() > 0) {
								mailReceiver.setMailReceiver(reciecverEmail);
								
								BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier = new BodyEmailConfirmFinalAuditResultForSupplier();
								bodyEmailConfirmFinalAuditResultForSupplier.setSupplierName(supplierDTO.getSupplierCompany());					
								bodyEmailConfirmFinalAuditResultForSupplier.setUrlForConfirm(ConfigurationSystemManager.getInstance().getMailFooterUrlWeb()+"/home.jsp");
								bodyEmailConfirmFinalAuditResultForSupplier.setEmailAdmin(ConfigurationSystemManager.getInstance().getMailFooterAdminMail());
								bodyEmailConfirmFinalAuditResultForSupplier.setTelephoneAdmin(ConfigurationSystemManager.getInstance().getMailFooterTelephone());
								
								resultProcess = MailConnector.sendMailToSupplierForAlertInformation(mailReceiver, bodyEmailConfirmFinalAuditResultForSupplier, "แจ้งเตือน ข้อกำหนด/ข่าวสาร/ประกาศ/บันทึก จาก บริษัท ซีพีแรม จำกัด");	
								if(!resultProcess) {
									logger.error("send mail fail informationId : "+informationDetailModel.getInformationId()+", supplierName : "+supplierDTO.getSupplierCompany() );
								}
							}
						}catch(Exception e) {
							logger.error("send mail fail informationId : "+informationDetailModel.getInformationId()+", supplierName : "+supplierDTO.getSupplierCompany() );
						}
						
					}
				}else {
					DatabaseUtils.rollback(connection);
				}
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			return resultProcess;
		}catch (Exception e) {
			logger.error("InformationController.sendInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getInformationDocumentList(HttpServletRequest httpServletRequest, String informationListObj) {
		Connection connection = null;
		Gson gson = null;
		InformationDocumentDAO informationDocumentDAO = null;
		ArrayList<InformationDocumentModel> informationDocumentList= new ArrayList<InformationDocumentModel>();
		String informationListResponse = "";
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			informationDocumentDAO = new InformationDocumentDAO(connection);
			InformationModel informationModel = gson.fromJson(informationListObj, InformationModel.class);
			List<InformationDocumentDTO> informationDocumentDTOs = informationDocumentDAO.getInformationDocumentList(NullUtils.cvStr(informationModel.getInformationId()),"doc");
			
			for(InformationDocumentDTO informationDocumentDTO : informationDocumentDTOs) {
				informationDocumentList.add(TransformDTO.transInformationDocumentDTO(informationDocumentDTO));
			}
			informationListResponse = gson.toJson(informationDocumentList);
			return informationListResponse;
		}catch (Exception e) {
			logger.error("InformationController.getInformationDocumentList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}

	public String getInformationPictureList(HttpServletRequest httpServletRequest, String informationListObj) {
		Connection connection = null;
		Gson gson = null;
		InformationDocumentDAO informationDocumentDAO = null;
		ArrayList<InformationDocumentModel> informationPictureList= new ArrayList<InformationDocumentModel>();
		String informationListResponse = "";
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			informationDocumentDAO = new InformationDocumentDAO(connection);
			InformationModel informationModel = gson.fromJson(informationListObj, InformationModel.class);
			List<InformationDocumentDTO> informationPictureDTOs = informationDocumentDAO.getInformationDocumentList(NullUtils.cvStr(informationModel.getInformationId()),"img");
			
			for(InformationDocumentDTO informationPictureDTO : informationPictureDTOs) {
				informationPictureList.add(TransformDTO.transInformationDocumentDTO(informationPictureDTO));
			}
			informationListResponse = gson.toJson(informationPictureList);
			return informationListResponse;
		}catch (Exception e) {
			logger.error("InformationController.getInformationPictureList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean deleteInformationDocument(int informationDocumentId) {
		Connection connection = null;
		InformationDocumentDAO informationDocumentDAO = null;
		FileProcessing fileProcessing = null;
		boolean resultProcess = false;
		String informationDocumentName = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			informationDocumentDAO = new InformationDocumentDAO(connection);
			fileProcessing = new FileProcessing();
			if(informationDocumentId != 0) {
				informationDocumentName =  informationDocumentDAO.getInformationDocument(informationDocumentId);
				resultProcess = informationDocumentDAO.deleteInformationDocument(informationDocumentId);			
				if(resultProcess && informationDocumentName != null) {
					String[] DocName = informationDocumentName.split("/");
					String pathInformationDocument = FileUploadConst.PATH_Information_Document.concat(DocName[DocName.length-1]);
					if(!StringUtils.isEmptyOrWhitespaceOnly(informationDocumentName)) {
						resultProcess = fileProcessing.DeleteFile(pathInformationDocument);
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
				resultProcess = false;
			}
			
			return resultProcess;
			
			
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("InformationController.deleteInformationDocument() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null) {
				DatabaseUtils.closeConnection(connection);
			}
		}
	}
	
	public DataTableModel<InformationDetailModel> dataTableGetInformationListBySupplier(HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel){
		InformationDAO informationDAO = null;
		Connection connection = null;
		SessionUtils sessionUtils = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			informationDAO = new InformationDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			DataTableModel<InformationDetailModel> dataTableModel = new DataTableModel<>();
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
			
			String supplierId = supplierUserMappingDAO.getSupplierByUserId(userSessionModel.getUserId());
			StringBuilder sbWhereCause = new StringBuilder();
			sbWhereCause.append(" AND ");
			sbWhereCause.append("id.supplier_id").append(" = '").append(supplierId).append("' ");
			for (Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {					
					if (col.getName().equals("informationTitle")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {						
						sbWhereCause.append(" AND ");
						sbWhereCause.append("i.information_title").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}if (col.getName().equals("acceptStatus")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("id.accept_status").append(" = '").append(col.getSearch().getValue()).append("' ");
					}if (col.getName().equals("sendDate")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("i.send_date").append(" like ").append(" '%").append(DateUtils.parseWebDateStringToSQLDate(col.getSearch().getValue())).append("%'  ");
					}
				}
			}
			
			List<InformationDetailDTO> informationDetailDTOs = informationDAO.getInformationListBySupplier(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString());
			int countChecklist = informationDAO.countInformationListBySupplier(sbWhereCause.toString());
			dataTableModel.setRecordsFiltered(countChecklist);
			dataTableModel.setRecordsTotal(countChecklist);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setData(new ArrayList<>());
			for(InformationDetailDTO informationDetailDTO : informationDetailDTOs) {
				dataTableModel.getData().add(TransformDTO.transInformationDetailDTO(informationDetailDTO));
			}
			return dataTableModel;
		}catch (Exception e) {
			logger.error("InformationController.dataTableGetInformationListBySupplier() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String acceptInformation(HttpServletRequest httpServletRequest, String informationListObj) {
		Connection connection = null;
		Gson gson = null;
		InformationDetailDAO informationDetailDAO = null;
		InformationDAO informationDAO = null;
		SessionUtils sessionUtils = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		boolean resultProcess = false;
		String informationListResponse = "";
		try {

			StringBuilder sbWhereCause = new StringBuilder();
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			informationDAO = new InformationDAO(connection);
			informationDetailDAO = new InformationDetailDAO(connection);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			InformationModel informationModel = gson.fromJson(informationListObj, InformationModel.class);
			sessionUtils = new SessionUtils(httpServletRequest);
			UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);	
			String supplierId = supplierUserMappingDAO.getSupplierByUserId(userSessionModel.getUserId());
			SupplierModel supplierModel = new SupplierModel();
			supplierModel.setSupplierId(supplierId);
			InformationDetailModel informationDetailModel = new InformationDetailModel();
			informationDetailModel.setInformationId(informationModel);	
			informationDetailModel.setSupplierId(supplierModel);	
			informationDetailModel.setAcceptBy(userSessionModel);	
			informationDetailModel.setCreateBy(NullUtils.cvStr(sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key)));
			resultProcess = informationDetailDAO.acceptInformationDetailBySupplier(TransformModel.transInformationDetailModel(informationDetailModel));

			if(resultProcess) {
				sbWhereCause = new StringBuilder();
				sbWhereCause.append(" AND ");
				sbWhereCause.append("id.information_id").append(" = '").append(informationModel.getInformationId()).append("' ");
				sbWhereCause.append(" AND ");
				sbWhereCause.append("id.supplier_id").append(" = '").append(supplierId).append("' ");
				List<InformationDetailDTO> informationDetailDTOs = informationDetailDAO.getInformationDetailList(0, informationDetailDAO.countInformationDetailList(sbWhereCause.toString()), sbWhereCause.toString());
				InformationDTO informationDTO = new InformationDTO();
				informationDTO.setInformationDetailId(informationDetailDTOs);
				informationListResponse = gson.toJson(TransformDTO.transInformationDTO(informationDTO));
				
				sbWhereCause = new StringBuilder();
				sbWhereCause.append(" AND ");
				sbWhereCause.append("id.information_id").append(" = '").append(informationModel.getInformationId()).append("' ");
				List<InformationDetailDTO> informationDetailCheckDTOs = informationDetailDAO.getInformationDetailList(0, informationDetailDAO.countInformationDetailList(sbWhereCause.toString()), sbWhereCause.toString());
				boolean acceptAll = true;
				for(int i = 0 ; i < informationDetailCheckDTOs.size() ; i++) {
					if(informationDetailCheckDTOs.get(i).getAcceptStatus().equals(NullUtils.cvChar("N"))) {
						acceptAll = false;
						break;
					}
				}
				if(acceptAll) {
					informationModel.setInformationType(NullUtils.cvStr(ConstInformation.INFORMATION_SUCCESS));
					informationDAO.updateInformationSuccess(TransformModel.transInformationModel(informationModel));
				}
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			return informationListResponse;
		}catch (Exception e) {
			logger.error("InformationController.acceptInformation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.getMessage(), e);
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
}
