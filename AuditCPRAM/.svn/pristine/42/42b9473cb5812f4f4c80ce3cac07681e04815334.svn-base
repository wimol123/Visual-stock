package th.co.gosoft.audit.cpram.controller;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dao.ChecklistDAO;
import th.co.gosoft.audit.cpram.dao.EvalFormDAO;
import th.co.gosoft.audit.cpram.dao.ProductTypeDAO;
import th.co.gosoft.audit.cpram.dao.QuestionAnswerMappingDAO;
import th.co.gosoft.audit.cpram.dto.ChecklistDTO;
import th.co.gosoft.audit.cpram.dto.EvalFormDTO;
import th.co.gosoft.audit.cpram.dto.ProductTypeDTO;
import th.co.gosoft.audit.cpram.dto.QuestionAnswerMappingDTO;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ProductTypeModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.Column;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.relational.ProductTypeRelational;
import th.co.gosoft.audit.cpram.utils.DatabaseUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.NullUtils;
import th.co.gosoft.audit.cpram.utils.SessionUtils;
import th.co.gosoft.audit.cpram.utils.StaticVariableUtils;
import th.co.gosoft.audit.cpram.utils.TransformDTO;
import th.co.gosoft.audit.cpram.utils.TransformModel;

public class ProductTypeController {
	private final static Logger logger = Logger.getLogger(ProductTypeController.class);
	
	
	public DataTableModel<ProductTypeModel> dataTableGetProductTypeList(DataTablePostParamModel dataTablePostParamModel) {
		Connection connection = null;
		ProductTypeDAO productTypeDAO = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			DataTableModel<ProductTypeModel> dataTableModel = new DataTableModel<>();
			
			StringBuilder sbWhereCause = new StringBuilder();
			for (Column col : dataTablePostParamModel.getColumns()) {
				
				if (!StringUtils.isNullOrEmpty(col.getName())) {	
					
					if (col.getName().equals("productTypeId")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.product_type_id").append(" = '").append(col.getSearch().getValue()).append("' ");

					} else if (col.getName().equals("name")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					
					} else if (col.getName().equals("enable")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.enable").append(" = '").append(col.getSearch().getValue()).append("' ");
						
					}else if (col.getName().equals("createBy")&& !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.create_by").append(" = '").append(col.getSearch().getValue()).append("' ");
						
					}
					
				}
			}
			
			List<ProductTypeDTO> productTypeDTOs = productTypeDAO.getProductList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString());
			dataTableModel.setData(new ArrayList<>());
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			int countProductType = productTypeDAO.countProduct(sbWhereCause.toString());
			dataTableModel.setRecordsFiltered(countProductType);
			dataTableModel.setRecordsTotal(countProductType);
			
			for(ProductTypeDTO productTypeDTO : productTypeDTOs) {
				dataTableModel.getData().add(TransformDTO.transProductTypeDTO(productTypeDTO));
			}
			return dataTableModel;
			
		}catch(Exception e) {
			logger.error("ProductTypeController.dataTableGetProductTypeList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	
	public boolean insertProductType(HttpServletRequest httpServletRequest, String objProductType) {
		Connection connection = null;
		ProductTypeDAO productTypeDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			int userSessionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			ProductTypeModel productTypeModelRequest = gson.fromJson(objProductType, ProductTypeModel.class);
			productTypeModelRequest.setCreateBy(NullUtils.cvStr(userSessionId));
			productTypeModelRequest.setUpdateBy(NullUtils.cvStr(userSessionId));
			
			int productTypeId = productTypeDAO.insertProductType(TransformModel.transProductTypeModel(productTypeModelRequest));
			if(productTypeId > 0) {
				DatabaseUtils.commit(connection);
				return true;
			}else {
				DatabaseUtils.rollback(connection);
				return false;
			}
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ProductTypeController.insertProductType() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean updateProductType(HttpServletRequest httpServletRequest, String productTypeObj) {
		Connection connection = null;
		ProductTypeDAO productTypeDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		try {
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			int userSesssionId = sessionUtils.getUserIdSession(StaticVariableUtils.Session_Key);
			ProductTypeModel productTypeModelRequest = gson.fromJson(productTypeObj, ProductTypeModel.class);
			productTypeModelRequest.setUpdateBy(NullUtils.cvStr(userSesssionId));
			boolean resultUpdate = productTypeDAO.updateProductType(TransformModel.transProductTypeModel(productTypeModelRequest));
			
			if(resultUpdate) {
				DatabaseUtils.commit(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			return resultUpdate;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ProductTypeController.updateProductType() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getProductList(){
		Connection connection = null;
		ProductTypeDAO productTypeDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			gson = new Gson();
			int numOfRows = productTypeDAO.countProduct("");
			List<ProductTypeDTO> productTypeDTOs = productTypeDAO.getProductList(0, numOfRows, "");
			List<ProductTypeModel> productTypeModels = new ArrayList<>();
			for(ProductTypeDTO productTypeDTO : productTypeDTOs) {
				productTypeModels.add(TransformDTO.transProductTypeDTO(productTypeDTO));
			}
			return gson.toJson(productTypeModels);
		}catch(Exception e) {
			logger.error("ProductTypeController.getProductList() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public String getProductTypeById(String objProductType) {
		Connection connection = null;
		ProductTypeDAO productTypeDAO = null;
		Gson gson = null;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			gson = new Gson();
			
			ProductTypeModel productTypeModelRequest = gson.fromJson(objProductType, ProductTypeModel.class);
			StringBuilder queryWhereClause = new StringBuilder();
			queryWhereClause.append("AND ").append("pt.product_type_id = '").append(productTypeModelRequest.getProductTypeId()).append("' ");
			List<ProductTypeDTO> productTypeDTOs = productTypeDAO.getProductList(0, productTypeDAO.countProduct(queryWhereClause.toString()), queryWhereClause.toString());
			List<ProductTypeModel> productTypeModels = new ArrayList<>();
			
			for(ProductTypeDTO productTypeDTO : productTypeDTOs) {
				productTypeModels.add(TransformDTO.transProductTypeDTO(productTypeDTO));
			}
			return gson.toJson(productTypeModels);
		}catch(Exception e) {
			logger.error("ProductTypeController.getProductTypeById() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	public boolean deleteProductType(String productTypeObj) {
		Connection connection = null;
		ProductTypeDAO productTypeDAO = null;
		ChecklistDAO checklistDAO = null;
		EvalFormDAO evalFormDAO = null;
		QuestionAnswerMappingDAO questionAnswerMappingDAO = null;
		Gson gson = null;
		boolean resultProcess = false;
		try {
			
			connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			checklistDAO = new ChecklistDAO(connection);
			evalFormDAO = new EvalFormDAO(connection);
			questionAnswerMappingDAO = new QuestionAnswerMappingDAO(connection);
			gson = new Gson();			
			ProductTypeModel productTypeModelRequest = gson.fromJson(productTypeObj, ProductTypeModel.class);
			
			if(!ProductTypeRelational.checkProductTypeRelation(connection, TransformModel.transProductTypeModel(productTypeModelRequest))) {
				StringBuilder queryWhereClause = new StringBuilder();
				queryWhereClause.append(" AND ").append("c.product_type_id = '").append(productTypeModelRequest.getProductTypeId()).append("' ");
				List<ChecklistDTO> checklistDTOs = checklistDAO.getChecklistList(0, checklistDAO.countChecklistList(queryWhereClause.toString()), queryWhereClause.toString());
				if(checklistDTOs != null && !checklistDTOs.isEmpty()) {
					for(ChecklistDTO checklist : checklistDTOs) {
						
						StringBuilder queryGetEvalForm = new StringBuilder();
						queryGetEvalForm.append(" AND ").append("ef.checklist_id ='").append(checklist.getChecklistId()).append("'");
						List<EvalFormDTO> evalFormDTOs = evalFormDAO.getEvalFormList(0, evalFormDAO.countEvalFormList(queryGetEvalForm.toString()), queryGetEvalForm.toString());
						
						for(EvalFormDTO evalFormDTO : evalFormDTOs) {
							resultProcess = evalFormDAO.deleteEvalForm(evalFormDTO);
							if(!resultProcess) {
								resultProcess = false;
								break;
							}
							if(evalFormDTO.getEvalTypeId().getEvalTypeId() == NullUtils.cvInt(StaticVariableUtils.evalTypeQuestion)) {
								QuestionAnswerMappingDTO questionAnswerMapping = new QuestionAnswerMappingDTO();
								questionAnswerMapping.setEvalFormId(evalFormDTO.getEvalFormId());
								
								resultProcess = questionAnswerMappingDAO.deleteQuestionAnswerMappingByEvalForm(questionAnswerMapping);
								if(!resultProcess) {
									resultProcess = false;
									break;
								}
							}
						}
						
						if(resultProcess) {
							resultProcess = checklistDAO.deleteChecklist(checklist);			
							
						}
					}
				}else {
					resultProcess = true;
				}
				if(resultProcess) {
					resultProcess = productTypeDAO.deleteProductType(TransformModel.transProductTypeModel(productTypeModelRequest));
					
				}	
			}
				
			
			if(resultProcess)
				DatabaseUtils.commit(connection);
			else
				DatabaseUtils.rollback(connection);
			
			return resultProcess;
		}catch(Exception e) {
			DatabaseUtils.rollback(connection);
			logger.error("ProductTypeController.deleteProductType() Exception : "+ExceptionUtils.stackTraceException(e));
			throw new RuntimeException(e.toString());
		}finally {
			if(connection != null)
				DatabaseUtils.closeConnection(connection);
		}
	}
	
	/*public DataTableModel<ProductTypeModel> getProductTypeList(DataTablePostParamModel dataTablePostParamModel){
		ProductTypeDAO productTypeDAO = null;
		
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			
			DataTableModel<ProductTypeModel> dataTableModel = new DataTableModel<>();
			StringBuilder sbWhereCause = new StringBuilder();
			
			for(Column col : dataTablePostParamModel.getColumns()) {
				if (!StringUtils.isNullOrEmpty(col.getName())) {
					
					if (col.getName().equals("productTypeName") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.name").append(" like ").append(" '%").append(col.getSearch().getValue()).append("%'  ");
					}
					
					else if (col.getName().equals("statusId") && !StringUtils.isNullOrEmpty(col.getSearch().getValue())) {
						sbWhereCause.append(" AND ");
						sbWhereCause.append("pt.status_id").append(" = '").append(col.getSearch().getValue()).append("' ");
					} 					
					
				}
			}
			
			List<ProductTypeDTO> productTypeDTOs = productTypeDAO.getProductList(dataTablePostParamModel.getStart(), dataTablePostParamModel.getLength(), sbWhereCause.toString());
			
			dataTableModel.setData(new ArrayList<>());
			
			for(ProductTypeDTO productTypeDTO : productTypeDTOs) {
				dataTableModel.getData().add(TransformDTO.transProductTypeDTO(productTypeDTO));
			}
			int totalCountProduct = productTypeDAO.countProduct(sbWhereCause.toString());
			dataTableModel.setRecordsFiltered(totalCountProduct);
			dataTableModel.setRecordsTotal(totalCountProduct);
			dataTableModel.setDraw(dataTablePostParamModel.getDraw());
			return dataTableModel;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(productTypeDAO != null)
				productTypeDAO.closeConnection();
		}
	}
		
	
	public boolean addProductType(HttpServletRequest httpServletRequest, String jsonString) {
		ProductTypeDAO productTypeDAO = null;
		Gson gson = null;
		SessionUtils sessionUtils = null;
		boolean resultProcess = true;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			gson = new Gson();
			sessionUtils = new SessionUtils(httpServletRequest);
			
			ProductTypeModel productTypeModel = gson.fromJson(jsonString, ProductTypeModel.class);
			int userIdSession = sessionUtils.getUserIdSession("sesionAuthen");
			productTypeModel.setCreateBy(NullUtils.cvStr(userIdSession));
			productTypeModel.setUpdateBy(NullUtils.cvStr(userIdSession));
			
			resultProcess = productTypeDAO.insert(TransformModel.transProductTypeModel(productTypeModel));
			return resultProcess;
			
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(productTypeDAO != null)
				productTypeDAO.closeConnection();
		}
	}
	
	public boolean updateProductType(HttpServletRequest httpServletRequest, String objProductType) {
		ProductTypeDAO productTypeDAO = null;
		Gson gson = null;
		boolean resultProcess = true;
		SessionUtils sessionUtils = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			sessionUtils = new SessionUtils(httpServletRequest);
			gson = new Gson();
			
			ProductTypeModel productTypeModel = gson.fromJson(objProductType, ProductTypeModel.class);
			int userIdSession = sessionUtils.getUserIdSession("sesionAuthen");
			productTypeModel.setCreateBy(NullUtils.cvStr(userIdSession));
			productTypeModel.setUpdateBy(NullUtils.cvStr(userIdSession));
			
			resultProcess = productTypeDAO.updateProductType(TransformModel.transProductTypeModel(productTypeModel));
			
			return resultProcess;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(productTypeDAO != null)
				productTypeDAO.closeConnection();
		}
		
	}
	
	public ProductTypeModel getProductById(String objProductType) {
		ProductTypeDAO productTypeDAO = null;
		Gson gson = null;
		try {
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			gson = new Gson();
			ProductTypeModel resultproductTypeModel = new ProductTypeModel();
			
			ProductTypeModel productTypeModel = gson.fromJson(objProductType, ProductTypeModel.class);
			
			ProductTypeDTO productTypeDTO = productTypeDAO.getProductById(TransformModel.transProductTypeModel(productTypeModel));
			resultproductTypeModel = TransformDTO.transProductTypeDTO(productTypeDTO);
			
			return resultproductTypeModel;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(productTypeDAO != null)
				productTypeDAO.closeConnection();
		}
	}
	
	public boolean deleteProductType(String objProductType) {
		
		ProductTypeDAO productTypeDAO = null;
		SupplierProductTypeMappingDAO supplierProductTypeMappingDAO = null;
		Gson gson = null;
		boolean resultProcess = true;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productTypeDAO = new ProductTypeDAO(connection);
			supplierProductTypeMappingDAO = new SupplierProductTypeMappingDAO(connection);
			gson = new Gson();
			
			ProductTypeModel productTypeModel = gson.fromJson(objProductType, ProductTypeModel.class);
			resultProcess = supplierProductTypeMappingDAO.deleteMappingWithProductTypeId(TransformModel.transProductTypeModel(productTypeModel));
			if(resultProcess) {
				resultProcess = productTypeDAO.delete(TransformModel.transProductTypeModel(productTypeModel));
				
				if(!resultProcess)
					DatabaseUtils.rollback(connection);
			}else {
				DatabaseUtils.rollback(connection);
			}
			
			return resultProcess;
		}catch(Exception e) {
			logger.error(e.toString());
			throw new RuntimeException(e.toString());
		}finally {
			if(productTypeDAO != null)
				productTypeDAO.closeConnection();
			if(supplierProductTypeMappingDAO != null)
				supplierProductTypeMappingDAO.closeConnection();
		}
	
		
	}*/
	/*public List<ProductModel> getProductList()
	{
		ProductDAO productDAO = null;		
		try
		{
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			
			List<ProductModel>  productModelList = new ArrayList<ProductModel>();			
			
			List<ProductDTO> productDTOList = productDAO.getProductList();
			for (ProductDTO productDTO : productDTOList){				
				productModelList.add(transformDTO(productDTO));
			}
			
			return productModelList;
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (productDAO != null) {
				productDAO.closeConnection();
			}
		}			
	}
	
//	public DataTableModel<ProductModel> getProductDataTable()
//	{
//		ProductDAO productDAO = null;		
//		try
//		{
//			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
//			productDAO = new ProductDAO(connection);
//			
//			DataTableModel<ProductModel>  dataTableModel = new DataTableModel<ProductModel>();			
//			
//			List<ProductDTO> productDTOList = productDAO.getProductList();
//			dataTableModel.setData(new ArrayList<ProductModel>());
//			
//			for (ProductDTO productDTO : productDTOList){	
//				dataTableModel.getData().add(transformDTO(productDTO));
//			}
//			dataTableModel.setRecordsFiltered(dataTableModel.getData().size());
//			dataTableModel.setRecordsTotal(productDAO.countProduct());
//			
//			return dataTableModel;
//			
//		}catch (Exception e) {
//			log.error(e.getMessage(), e);
//			throw new RuntimeException(e.getMessage(), e);
//		} finally {
//			if (productDAO != null) {
//				productDAO.closeConnection();
//			}
//		}			
//	}
	
	public DataTableModel<ProductModel> getDataProductDataTable(DataTablePostParamModel param)
	{
		ProductDAO productDAO = null;		
		try
		{
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			
			DataTableModel<ProductModel>  dataTableModel = new DataTableModel<ProductModel>();			
			
			List<ProductDTO> productDTOList = productDAO.getProductList(param);
			dataTableModel.setData(new ArrayList<ProductModel>());
			
			for (ProductDTO productDTO : productDTOList){	
				dataTableModel.getData().add(transformDTO(productDTO));
			}
			
			Integer totalRecord = productDAO.countProductList(param);
			
			dataTableModel.setRecordsFiltered(totalRecord);
			dataTableModel.setRecordsTotal(totalRecord);
			
			return dataTableModel;
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (productDAO != null) {
				productDAO.closeConnection();
			}
		}			
	}
	
	public boolean addProduct(ProductModel productModel) {
		ProductDAO productDAO = null;
		try {    		
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			boolean res = false;

			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductName(NullUtils.cvStr(productModel.getProductName()));
			productDTO.setStatusId(NullUtils.cvInt(productModel.getStatusId()));
			res = productDAO.insert(productDTO);
			
						
			return res;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (productDAO != null) {
				productDAO.closeConnection();
			}
		}
	}
	
	public boolean checkExisting(String productName) {
		ProductDAO productDAO = null;
		try {    		
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			
			return  productDAO.isExist(productName);
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (productDAO != null) {
				productDAO.closeConnection();
			}
		}
	}
	
	
	public boolean updateProduct(ProductModel productModel) {
		ProductDAO productDAO = null;
		try {    		
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			boolean res = false;

			ProductDTO productDTO = new ProductDTO();
			productDTO.setProductName(NullUtils.cvStr(productModel.getProductName()));
			productDTO.setStatusId(NullUtils.cvInt(productModel.getStatusId()));
			productDTO.setProductId(NullUtils.cvInt(productModel.getProductId()));
			
			res = productDAO.update(productDTO);
						
			return res;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (productDAO != null) {
				productDAO.closeConnection();
			}
		}
	}
	
	public boolean deleteProduct(String productId){
		ProductDAO productDAO = null;
		try{
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			return productDAO.delete(NullUtils.cvInt(productId));						
		}catch (Exception e) {
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally{
			if(productDAO != null){
				productDAO.closeConnection();
			}
		}		
	}
	
	public boolean deleteProductSupplier(String productId, String supplierId){
		ProductDAO productDAO = null;
		try{
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			return productDAO.deleteProductSupplier(NullUtils.cvInt(productId), NullUtils.cvInt(supplierId));						
		}catch (Exception e) {
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally{
			if(productDAO != null){
				productDAO.closeConnection();
			}
		}		
	}
	
	public ProductModel getProductById(String productId) {
		ProductDAO productDAO = null;
		try {
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			ProductDTO productDTO = productDAO.getProductById(productId);			
			return this.transformDTO(productDTO);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (productDAO != null) {
				productDAO.closeConnection();
			}
		}
	}
	
	
		
		public DataTableModel<ProductModel> getProductListBySupplierId(DataTablePostParamModel modelDataTable,String supplierId) {
		ProductDAO productDAO = null;
		try
		{
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			DataTableModel<ProductModel>  dataTableModel = new DataTableModel<ProductModel>();
			dataTableModel.setData(new ArrayList<ProductModel>());
			
			List<ProductDTO> productList = productDAO.getProductListBySupplierId(modelDataTable, supplierId);
			for(ProductDTO product : productList) {
				dataTableModel.getData().add(transformDTO(product));
			}
			dataTableModel.setRecordsFiltered(productDAO.countProductBySupplierId(modelDataTable, supplierId));
			dataTableModel.setRecordsTotal(productDAO.countProductBySupplierId(modelDataTable, supplierId));
			dataTableModel.setDraw(modelDataTable.getDraw());
			
			return dataTableModel;
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (productDAO != null) {
				productDAO.closeConnection();
			}
		}
		
	}
	
	public DataTableModel<ProductModel> getProductListAddToSupplier(DataTablePostParamModel modelDataTable, String supplierId) {
		ProductDAO productDAO = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			DataTableModel<ProductModel> dataTableModel = new DataTableModel<ProductModel>();
			dataTableModel.setData(new ArrayList<ProductModel>());
			
			List<ProductDTO> productList = productDAO.getProductListAddToSupplier(modelDataTable, supplierId);
			for(ProductDTO product : productList) {
				dataTableModel.getData().add(transformDTO(product));
			}
			int countProduct = productDAO.countProductAddToSupplier(modelDataTable, supplierId);
			dataTableModel.setRecordsFiltered(countProduct);
			dataTableModel.setRecordsTotal(countProduct);
			dataTableModel.setDraw(modelDataTable.getDraw());
			
			return dataTableModel;
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		} finally {
			if (productDAO != null) {
				productDAO.closeConnection();
			}
		}
	}
	
	
	public boolean insertProductToSupplier(String objForm) {
		ProductDAO productDAO = null;
		Gson gson = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			gson = new Gson();
			
			SupplierProductMappingModel supplierProductMappingModel = gson.fromJson(objForm, SupplierProductMappingModel.class);
			
			return productDAO.insertProductToSupplier(supplierProductMappingModel);
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally{
			if(productDAO != null){
				productDAO.closeConnection();
			}
		}
	}
	
	
	public boolean deleteProductToSupplier(String objForm) {
		ProductDAO productDAO = null;
		Gson gson = null;
		try {
			
			Connection connection = DatabaseUtils.connectToDatasourceWithoutAutoCommit();
			productDAO = new ProductDAO(connection);
			gson = new Gson();
			
			SupplierProductMappingModel supplierProductMappingModel = gson.fromJson(objForm, SupplierProductMappingModel.class);
			return productDAO.deleteProductToSupplier(supplierProductMappingModel);
			
		}catch (Exception e) {
			log.error(e.getMessage(), e);
 			throw new RuntimeException(e.getMessage(), e);
		}finally{
			if(productDAO != null){
				productDAO.closeConnection();
			}
		}
	}
	
	
	public ProductModel transformDTO(ProductDTO productDTO)
	{
		ProductModel productModel = new ProductModel();
		productModel.setCreateDate(NullUtils.cvStr(productDTO.getCreateDate()));
		productModel.setProductId(NullUtils.cvStr(productDTO.getProductId()));
		productModel.setProductName(NullUtils.cvStr(productDTO.getProductName()));
		productModel.setStatusId(NullUtils.cvStr(productDTO.getStatusId()));
		productModel.setUpdateDate(NullUtils.cvStr(productDTO.getUpdateDate()));	
		
		return productModel;
	}*/
}
