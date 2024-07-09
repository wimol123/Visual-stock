package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.AddressDAO;
import th.co.gosoft.audit.cpram.dao.SupplierAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierProductTypeAddressMappingDAO;
import th.co.gosoft.audit.cpram.dao.SupplierUserMappingDAO;
import th.co.gosoft.audit.cpram.dto.AddressDTO;
import th.co.gosoft.audit.cpram.dto.SupplierDTO;
import th.co.gosoft.audit.cpram.dto.SupplierProductAddressMappingDTO;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.SupplierProductAddressMappingModel;
import th.co.gosoft.audit.cpram.model.UserModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.relational.SupplierProductAddressMappingRelation;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class SupplierProductTypeAddressMappingController {
	
	private final static Logger logger = Logger.getLogger(SupplierProductTypeAddressMappingController.class);
	
	
	public DataTableModel<SupplierProductAddressMappingModel> datatableGetSupplierListMappingByProduct(DataTablePostParamModel dataTablePostParamModel) {
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		Connection connection = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			StringBuilder queryWhereClause = new StringBuilder();
			DataTableModel<SupplierProductAddressMappingModel> dataTableModel = new DataTableModel<>();
			
			String productTypeId = dataTablePostParamModel.getOptionString();
			if(!StringUtils.isNullOrEmpty(productTypeId)) {
				queryWhereClause.append(" AND ").append("sup_map.product_type_id").append(" = '").append(productTypeId).append("' ");
			}		
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("supplierCompany") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("sup.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if (col.getName().equals("locationName") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("sup_map.location_name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
				}
			}
			
			int countSupplierListMappingProductType = supplierProductTypeAddressMappingDAO.countSupplierListMappingProductType(queryWhereClause.toString());
			List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierListMappingProductType(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			dataTableModel.setData(new ArrayList<>());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countSupplierListMappingProductType);
			dataTableModel.setRecordsTotal(countSupplierListMappingProductType);
			
			for(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO : supplierProductAddressMappingDTOs) {
				dataTableModel.getData().add(TransformDTO.transSupplierProductAddressMappingDTO(supplierProductAddressMappingDTO));
			}
			return dataTableModel;
		}catch(Exception e) {
			logger.error("SupplierProductTypeAddressMappingController.datatableGetSupplierListMappingByProduct() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public DataTableModel<SupplierProductAddressMappingModel> datatableSupplierProduceMappingProductTypeBySupplier(HttpServletRequest httpServletRequest, DataTablePostParamModel dataTablePostParamModel){
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		Connection connection = null;
		SupplierUserMappingDAO supplierUserMappingDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			StringBuilder queryWhereClause = new StringBuilder();
			DataTableModel<SupplierProductAddressMappingModel> dataTableModel = new DataTableModel<>();
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			supplierUserMappingDAO = new SupplierUserMappingDAO(connection);
			
			if(!StringUtils.isNullOrEmpty(dataTablePostParamModel.getOptionString())) {
				SupplierModel supplierModel = gson.fromJson(dataTablePostParamModel.getOptionString(), SupplierModel.class);
				
				if(!StringUtils.isNullOrEmpty(supplierModel.getSupplierId())) {
					queryWhereClause.append(" AND ").append("sup_map.supplier_id").append(" = '").append(supplierModel.getSupplierId()).append("' ");
				}	
			}else {
				UserModel userSessionModel = sessionUtils.getObjectSession(StaticVariableUtils.Session_Key, UserModel.class);
				if(NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_ADMIN || NullUtils.cvInt(userSessionModel.getUserGroupId().getUserGroupId()) == Config.AUTHEN_USERTYPE_SUPPLIER_QA) {
					List<SupplierDTO> supplierDTOs = supplierUserMappingDAO.getSupplierInUser(TransformModel.transUserModel(userSessionModel));
					queryWhereClause.append(" AND sup_map.supplier_id = ").append(supplierDTOs.get(0).getSupplierId()).append(" ");
				}
			}
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("locationName") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("sup_map.location_name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}else if (col.getName().equals("productTypeName") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						queryWhereClause.append(" AND ");
						queryWhereClause.append("pt.name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
				}
			}
			
			int countSupplierProduce = supplierProductTypeAddressMappingDAO.countSupplierProduceListMappingProductTypeBySupplier(queryWhereClause.toString());
			List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierProduceListMappingProductTypeBySupplier(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), queryWhereClause.toString());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countSupplierProduce);
			dataTableModel.setRecordsTotal(countSupplierProduce);
			dataTableModel.setData(new ArrayList<>());
			for(SupplierProductAddressMappingDTO supplierProductAddressMappingDTO : supplierProductAddressMappingDTOs) {
				
				dataTableModel.getData().add(TransformDTO.transSupplierProductAddressMappingDTO(supplierProductAddressMappingDTO));
			}
			return dataTableModel;
		}catch(Exception e) {
			logger.error("SupplierProductTypeAddressMappingController.datatableSupplierProduceMappingProductTypeBySupplier() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean insertAddressProuductTypeBySupplier(HttpServletRequest httpServletRequest, String objSupplierProductAddressMapping) {
		Connection connection = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;	
		AddressDAO addressDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			addressDAO = new AddressDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			SupplierProductAddressMappingModel supplierProductAddressMappingModelRequest = gson.fromJson(objSupplierProductAddressMapping, SupplierProductAddressMappingModel.class);
			supplierProductAddressMappingModelRequest.setCreateBy(NullUtils.cvStr(userSessionId));
			supplierProductAddressMappingModelRequest.setUpdateBy(NullUtils.cvStr(userSessionId));
			supplierProductAddressMappingModelRequest.getAddressId().setCreateBy(NullUtils.cvStr(userSessionId));
			supplierProductAddressMappingModelRequest.getAddressId().setUpdateBy(NullUtils.cvStr(userSessionId));
			supplierProductAddressMappingModelRequest.getAddressId().setEnable(supplierProductAddressMappingModelRequest.getEnable());
			
			if(StringUtils.isNullOrEmpty(supplierProductAddressMappingModelRequest.getAddressId().getAddressId())) {				
				int primaryKeyAddress = addressDAO.insertAddress(TransformModel.transAddressModel(supplierProductAddressMappingModelRequest.getAddressId()));
				if(primaryKeyAddress > 0) {					
					supplierProductAddressMappingModelRequest.getAddressId().setAddressId(NullUtils.cvStr(primaryKeyAddress));
					resultProcess = true;
				}else {
					resultProcess = false;
				}
			}else {
				resultProcess = true;
			}
			
			if(resultProcess) {
				int primaryKey = supplierProductTypeAddressMappingDAO.insertSupplierProductAddressMapping(TransformModel.transSupplierProductAddressMappingModel(supplierProductAddressMappingModelRequest));
				if(primaryKey > 0) {
					resultProcess = true;
				}else {
					resultProcess = false;
				}
			}
			
			if(resultProcess) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierProductTypeAddressMappingController.insertAddressProuductTypeBySupplier() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public boolean updateAddressProuductTypeBySupplier(HttpServletRequest httpServletRequest, String objSupplierProductAddressMapping) {
		Connection connection = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		AddressDAO addressDAO = null;
		SupplierAddressMappingDAO supplierAddressMappingDAO = null;
		SessionUtils sessionUtils = null;
		Gson gson = null;
		boolean resultProcess = false;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			addressDAO = new AddressDAO(connection);
			supplierAddressMappingDAO = new SupplierAddressMappingDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			SupplierProductAddressMappingModel supplierProductAddressMappingModelRequest = gson.fromJson(objSupplierProductAddressMapping, SupplierProductAddressMappingModel.class);
			supplierProductAddressMappingModelRequest.setCreateBy(NullUtils.cvStr(userSessionId));
			supplierProductAddressMappingModelRequest.setUpdateBy(NullUtils.cvStr(userSessionId));
			supplierProductAddressMappingModelRequest.getAddressId().setCreateBy(NullUtils.cvStr(userSessionId));
			supplierProductAddressMappingModelRequest.getAddressId().setUpdateBy(NullUtils.cvStr(userSessionId));
			
			StringBuilder queryWhereClause = new StringBuilder();
			if(supplierProductAddressMappingModelRequest.getAddressId() != null) {				
				if(!StringUtils.isNullOrEmpty(supplierProductAddressMappingModelRequest.getAddressId().getAddress())) {
					queryWhereClause.append(" AND ").append("a.address = '").append(supplierProductAddressMappingModelRequest.getAddressId().getAddress()).append("' ");
				}
				
				if(!StringUtils.isNullOrEmpty(supplierProductAddressMappingModelRequest.getAddressId().getSubDistrictId().getSubDistrictId())) {
					queryWhereClause.append(" AND ").append("a.sub_district_id = '").append(supplierProductAddressMappingModelRequest.getAddressId().getSubDistrictId().getSubDistrictId()).append("' ");
				}
				
				if(!StringUtils.isNullOrEmpty(supplierProductAddressMappingModelRequest.getAddressId().getDistrictId().getDistrictId())) {
					queryWhereClause.append(" AND ").append("a.district_id = '").append(supplierProductAddressMappingModelRequest.getAddressId().getDistrictId().getDistrictId()).append("' ");
				}
				
				if(!StringUtils.isNullOrEmpty(supplierProductAddressMappingModelRequest.getAddressId().getPostcode())) {
					queryWhereClause.append(" AND ").append("a.postcode = '").append(supplierProductAddressMappingModelRequest.getAddressId().getPostcode()).append("' ");
				}
				
				if(!StringUtils.isNullOrEmpty(supplierProductAddressMappingModelRequest.getAddressId().getProvinceId().getProvinceId())) {
					queryWhereClause.append(" AND ").append("a.province_id = '").append(supplierProductAddressMappingModelRequest.getAddressId().getProvinceId().getProvinceId()).append("' ");
				}
				
			}
			
			
			
			List<AddressDTO> addressDTOs = addressDAO.getAddressList(queryWhereClause.toString());
			if(addressDTOs != null && !addressDTOs.isEmpty()) {
				for(AddressDTO addressDTO : addressDTOs) {
					supplierProductAddressMappingModelRequest.getAddressId().setAddressId(NullUtils.cvStr(addressDTO.getAddressId()));
				}
			}else {
				queryWhereClause.setLength(0);
				queryWhereClause = new StringBuilder();
				
				queryWhereClause.append(" AND ").append("sup_map.id = '").append(supplierProductAddressMappingModelRequest.getId()).append("' ");
				List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierProduceListMappingProductTypeBySupplier(0, supplierProductTypeAddressMappingDAO.countSupplierProduceListMappingProductTypeBySupplier(queryWhereClause.toString()), queryWhereClause.toString());
				for(SupplierProductAddressMappingDTO supplierProductAddressMapping : supplierProductAddressMappingDTOs) {
					int countAddressIdInSupplierProductTypeAddressMapping = supplierProductTypeAddressMappingDAO.countAddressId(supplierProductAddressMapping);
					int countAddressIdInSupplierAddressMapping = supplierAddressMappingDAO.countAddressId(supplierProductAddressMapping.getAddressId());
					if(countAddressIdInSupplierProductTypeAddressMapping <= 1 && countAddressIdInSupplierAddressMapping == 0) {
						resultProcess = addressDAO.deleteAddress(TransformModel.transAddressModel(supplierProductAddressMappingModelRequest.getAddressId()));
					}
				}
				
				supplierProductAddressMappingModelRequest.getAddressId().setEnable(NullUtils.cvStr(supplierProductAddressMappingModelRequest.getEnable()));
				int primaryKeyAddress = addressDAO.insertAddress(TransformModel.transAddressModel(supplierProductAddressMappingModelRequest.getAddressId()));
				supplierProductAddressMappingModelRequest.getAddressId().setAddressId(NullUtils.cvStr(primaryKeyAddress));
			}
			
			resultProcess = supplierProductTypeAddressMappingDAO.updateSupplierProductAddressMapping(TransformModel.transSupplierProductAddressMappingModel(supplierProductAddressMappingModelRequest));
			
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else 
				DatabaseUtils.rollback(connection);
			
			return resultProcess;			
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierProductTypeAddressMappingController.updateAddressProuductTypeBySupplier() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public String getSupplierLocation(String supplierObj) {
		Connection connection = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			gson = new Gson();
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			
			SupplierModel supplierModel = gson.fromJson(supplierObj, SupplierModel.class);
			
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append(" AND ").append("sup_map.supplier_id = '").append(supplierModel.getSupplierId()).append("' ");
			
			List<SupplierProductAddressMappingDTO> supplierProductAddressMappingDTOs = supplierProductTypeAddressMappingDAO.getSupplierProduceListMappingProductTypeBySupplier(0,supplierProductTypeAddressMappingDAO.countSupplierProduceListMappingProductTypeBySupplier(queryWhereClause.toString()), queryWhereClause.toString());
			List<SupplierProductAddressMappingModel> supplierProductAddressMappingModels = new ArrayList<>();
			
			for(SupplierProductAddressMappingDTO supplierProductAddressMapping : supplierProductAddressMappingDTOs) {
				supplierProductAddressMappingModels.add(TransformDTO.transSupplierProductAddressMappingDTO(supplierProductAddressMapping));
			}
			return gson.toJson(supplierProductAddressMappingModels);
		}catch(Exception e) {
			logger.error("SupplierProductTypeAddressMappingController.getSupplierLocation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
			
		}
	}
	
	public boolean deleteSupplierLocation(String objSupplierProductAddressMapping) {
		Connection connection = null;
		SupplierProductTypeAddressMappingDAO supplierProductTypeAddressMappingDAO = null;
		AddressDAO addressDAO = null;
		SupplierAddressMappingDAO supplierAddressMappingDAO = null;
		Gson gson = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeAddressMappingDAO = new SupplierProductTypeAddressMappingDAO(connection);
			supplierAddressMappingDAO = new SupplierAddressMappingDAO(connection);
			addressDAO = new AddressDAO(connection);
			gson = new Gson();	
			
			
			SupplierProductAddressMappingModel supplierProductAddressMappingModelRequest = gson.fromJson(objSupplierProductAddressMapping, SupplierProductAddressMappingModel.class);
			
			if(!SupplierProductAddressMappingRelation.checkSupplierProductAddressMappingRelation(connection, TransformModel.transSupplierProductAddressMappingModel(supplierProductAddressMappingModelRequest))) {
				int countAddressIdInSupplierProductTypeAddressMapping = supplierProductTypeAddressMappingDAO.countAddressId(TransformModel.transSupplierProductAddressMappingModel(supplierProductAddressMappingModelRequest));
				int countAddressIdInSupplierAddressMapping = supplierAddressMappingDAO.countAddressId(TransformModel.transAddressModel(supplierProductAddressMappingModelRequest.getAddressId()));
				if(countAddressIdInSupplierProductTypeAddressMapping <= 1 && countAddressIdInSupplierAddressMapping == 0) {
					resultProcess = addressDAO.deleteAddress(TransformModel.transAddressModel(supplierProductAddressMappingModelRequest.getAddressId()));
				}else {
					resultProcess = true;
				}
				
				if(resultProcess) {
					resultProcess = supplierProductTypeAddressMappingDAO.deleteSupplierProductAddressMapping(TransformModel.transSupplierProductAddressMappingModel(supplierProductAddressMappingModelRequest));
					
				}else {
					resultProcess = false;
				}
			}
			
			
			
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
			
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("SupplierProductTypeAddressMappingController.deleteSupplierLocation() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	/*public DataTableModel<SupplierModel> getProductTypeList(DataTablePostParamModel dataTablePostParamModel, String objProductType){
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		Gson gson = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);
			gson = new Gson();
			StringBuilder sbWhereCause = new StringBuilder();			
			ProductTypeModel productTypeModel = gson.fromJson(objProductType, ProductTypeModel.class);
			DataTableModel<SupplierModel> dataTableModel = new DataTableModel<>();
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("supplierCompany") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("isGreenCard") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.is_green_card").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("statusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("supplierCode") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.supplierCode").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
				}
			}
			
			dataTableModel.setData(new ArrayList<>());
			List<SupplierDTO> supplierDTOs = supplierProductTypeMappingDAO.getSupplierListMappingProductType(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString(), TransformModel.transProductTypeModel(productTypeModel));
			for(SupplierDTO supplierDTO : supplierDTOs) {
				dataTableModel.getData().add(TransformDTO.transSupplierDTO(supplierDTO));
			}		
			
			int countResult = supplierProductTypeMappingDAO.countSupplierListMappingProductType(sbWhereCause.toString(), TransformModel.transProductTypeModel(productTypeModel));
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countResult);
			dataTableModel.setRecordsTotal(countResult);
			
			
			return dataTableModel;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(supplierProductTypeMappingDAO != null)
				supplierProductTypeMappingDAO.closeConnection();
		}
	}
	
	
	public DataTableModel<SupplierModel> getsupplierListNotInProduct(DataTablePostParamModel dataTablePostParamModel, @PathParam("objProductType") String objProductType){
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		Gson gson = null;
		
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);
			gson = new Gson();
			
			DataTableModel<SupplierModel> dataTableModel = new DataTableModel<>();
			StringBuilder sbWhereCause = new StringBuilder();			
			ProductTypeModel productTypeModel = gson.fromJson(objProductType, ProductTypeModel.class);
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("supplierCompany") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.supplier_company").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("isGreenCard") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.is_green_card").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("statusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					else if (col.getName().equals("supplierCode") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("s.supplierCode").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					
				}
			}
			
			List<SupplierDTO> supplierDTOs = supplierProductTypeMappingDAO.getsupplierListNotInProduct(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString(), TransformModel.transProductTypeModel(productTypeModel));
			dataTableModel.setData(new ArrayList<>());
			for(SupplierDTO supplierDTO : supplierDTOs) {
				dataTableModel.getData().add(TransformDTO.transSupplierDTO(supplierDTO));
			}
			
			int countResult = supplierProductTypeMappingDAO.countSupplierListNotInProductType(sbWhereCause.toString(), TransformModel.transProductTypeModel(productTypeModel));
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			dataTableModel.setRecordsFiltered(countResult);
			dataTableModel.setRecordsTotal(countResult);
			
			return dataTableModel;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(supplierProductTypeMappingDAO != null)
				supplierProductTypeMappingDAO.closeConnection();
		}
	}
	
	public DataTableModel<ProductTypeModel> productListMappingBySupplier(DataTablePostParamModel dataTablePostParamModel, String objSupplier){
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		Gson gson = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);
			gson = new Gson();
			DataTableModel<ProductTypeModel> dataTableModel = new DataTableModel<>();
			StringBuilder sbWhereCause = new StringBuilder();	
			
			SupplierModel supplierModel = gson.fromJson(objSupplier, SupplierModel.class);
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("name") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("statusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					
				}
			}
			
			dataTableModel.setData(new ArrayList<>());
			List<ProductTypeDTO> productTypeDTOs = supplierProductTypeMappingDAO.productListMappingBySupplier(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString(), TransformModel.transSupplierModel(supplierModel));
			for(ProductTypeDTO productTypeDTO : productTypeDTOs) {
				dataTableModel.getData().add(TransformDTO.transProductTypeDTO(productTypeDTO));
			}
			int countResult = supplierProductTypeMappingDAO.countProductListMappingBySupplier(sbWhereCause.toString(), TransformModel.transSupplierModel(supplierModel));
			dataTableModel.setRecordsFiltered(countResult);
			dataTableModel.setRecordsTotal(countResult);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			
			return dataTableModel;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(supplierProductTypeMappingDAO != null)
				supplierProductTypeMappingDAO.closeConnection();
		}
	}
	
	public DataTableModel<ProductTypeModel> productListNotInSupplier(DataTablePostParamModel dataTablePostParamModel, String objSupplier){
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		Gson gson = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);
			gson = new Gson();
			DataTableModel<ProductTypeModel> dataTableModel = new DataTableModel<>();
			StringBuilder sbWhereCause = new StringBuilder();	
			
			SupplierModel supplierModel = gson.fromJson(objSupplier, SupplierModel.class);
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("name") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					else if (col.getName().equals("statusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					}
					
				}
			}
			List<ProductTypeDTO> productTypeDTOs = supplierProductTypeMappingDAO.productListNotInSupplier(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(),sbWhereCause.toString(), TransformModel.transSupplierModel(supplierModel));
			int countResult = supplierProductTypeMappingDAO.countProductListNotInSupplier(sbWhereCause.toString(), TransformModel.transSupplierModel(supplierModel));
			
			dataTableModel.setData(new ArrayList<>());
			for(ProductTypeDTO productTypeDTO : productTypeDTOs) {
				dataTableModel.getData().add(TransformDTO.transProductTypeDTO(productTypeDTO));
			}
			dataTableModel.setRecordsFiltered(countResult);
			dataTableModel.setRecordsTotal(countResult);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			
			return dataTableModel;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(supplierProductTypeMappingDAO != null)
				supplierProductTypeMappingDAO.closeConnection();
		}
	}
	
	public boolean insertSupplierProductToMapping(HttpServletRequest httpServletRequest, String objMapping) {
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		boolean resultOfProcess = true;
		Gson gson = null;
		SupplierModel supplierModel = null;
		ProductTypeModel productTypeModel = null;
		SessionUtils sessionUtils = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			
			int userSessionId = sessionUtils.getUserIdSession("sesionAuthen");
			try {
				supplierModel = gson.fromJson(objMapping, SupplierModel.class);
			}catch(Exception e) {
				logger.warn("Can't cast json string to supplierModel system will cast ProductTypeModel next step");
			}
			
			if(supplierModel == null) {
				try {
					productTypeModel = gson.fromJson(objMapping, ProductTypeModel.class);
				}catch(Exception e) {
					logger.error("Can't cast json string to ProductTypeModel");
					throw new RuntimeException(e.toString());
				}
				
				supplierModel = new SupplierModel();
				///supplierModel.setStatusId(NullUtils.cvStr(productTypeModel.getStatusId()));
				supplierModel.setCreateBy(NullUtils.cvStr(userSessionId));
				supplierModel.setUpdateBy(NullUtils.cvStr(userSessionId));
				
				for(SupplierModel supplier : productTypeModel.getSupplierId()) {
					supplierModel.setSupplierId(NullUtils.cvStr(supplier.getSupplierId()));
				}
				
			}else {				
				
				supplierModel.setCreateBy(NullUtils.cvStr(userSessionId));
				supplierModel.setUpdateBy(NullUtils.cvStr(userSessionId));
				productTypeModel = new ProductTypeModel();
				for(ProductTypeModel productType : supplierModel.getProductTypeId()) {
					productTypeModel.setProductTypeId(productType.getProductTypeId());
				}
			}
						
			resultOfProcess = supplierProductTypeMappingDAO.insertSupplierProductToMapping(TransformModel.transSupplierModel(supplierModel), TransformModel.transProductTypeModel(productTypeModel));		
			
			
			return resultOfProcess ;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(supplierProductTypeMappingDAO != null)
				supplierProductTypeMappingDAO.closeConnection();
		}
	}
	
	
	public boolean deleteSupplierProductToMapping(HttpServletRequest httpServletRequest, String objMapping) {
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		boolean resultOfProcess = true;
		Gson gson = null;
		SupplierModel supplierModel = null;
		ProductTypeModel productTypeModel = null;
		
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);
			gson = new Gson();
						
			try {
				supplierModel = gson.fromJson(objMapping, SupplierModel.class);
			}catch(Exception e) {
				logger.warn("Can't cast json string to supplierModel system will cast ProductTypeModel next step");
			}
			
			
			if(supplierModel == null) {
				try {
					productTypeModel = gson.fromJson(objMapping, ProductTypeModel.class);
				}catch(Exception e) {
					logger.error("Can't cast json string to ProductTypeModel");
					throw new RuntimeException(e.toString());
				}
				
				supplierModel = new SupplierModel();				
				for(SupplierModel supplier : productTypeModel.getSupplierId()) {
					supplierModel.setSupplierId(NullUtils.cvStr(supplier.getSupplierId()));
				}
				
			}else {				
				
				productTypeModel = new ProductTypeModel();
				for(ProductTypeModel productType : supplierModel.getProductTypeId()) {
					productTypeModel.setProductTypeId(productType.getProductTypeId());
				}
			}
			
			resultOfProcess = supplierProductTypeMappingDAO.deleteMappingWithSupplierIdAndProductTypeId(NullUtils.cvInt(supplierModel.getSupplierId()), NullUtils.cvInt(productTypeModel.getProductTypeId()));
			
			return resultOfProcess;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(supplierProductTypeMappingDAO != null)
				supplierProductTypeMappingDAO.closeConnection();
		}
	}*/
	
}
