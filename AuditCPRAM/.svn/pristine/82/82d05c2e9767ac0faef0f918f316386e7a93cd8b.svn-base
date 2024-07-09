package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import java.util.regex.*;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import com.google.gson.Gson;
import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.ProductTypeController;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ProductTypeModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/product_type")
public class ProductTypeAPI {
	private final static Logger logger = Logger.getLogger(ProductTypeAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	
	@POST
	@Path("/datatable/product_type_list")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public DataTableModel<ProductTypeModel> dataTableGetProductTypeList(DataTablePostParamModel dataTablePostParamModel) {
		
		try {			
			ProductTypeController productTypeController = new ProductTypeController();
			return productTypeController.dataTableGetProductTypeList(dataTablePostParamModel);		
			
		}catch(Exception e) {
			logger.error("ProductTypeAPI.dataTableGetProductTypeList() Exception : "+ExceptionUtils.stackTraceException(e));	
			throw new RuntimeException(e.toString());
		}
	}
	
	@POST
	@Path("/insert_product_type")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response insertProductType(String objProductType) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		Pattern Pattern_check = Pattern.compile("^[ก-๏a-zA-Z0-9\\s()&/._%+-]+$");
		Gson gson = new Gson();
		try {
			ProductTypeController productTypeController = new ProductTypeController();
			ProductTypeModel productTypeModelRequest = gson.fromJson(objProductType, ProductTypeModel.class);
			String name = productTypeModelRequest.getName();
			if(!Pattern_check.matcher(name).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			boolean result = productTypeController.insertProductType(servletRequest, objProductType);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("ProductTypeAPI.insertProductType() Exception : "+ExceptionUtils.stackTraceException(e));			
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_product_type")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response updateProductType(String productTypeObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			ProductTypeController productTypeController = new ProductTypeController();
			boolean result = productTypeController.updateProductType(servletRequest, productTypeObj);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("ProductTypeAPI.updateProductType() Exception : "+ExceptionUtils.stackTraceException(e));	
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/product_type_list")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getProductTypeList(){
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			ProductTypeController productTypeController = new ProductTypeController();
			String resultJson = productTypeController.getProductList();
			if(!StringUtils.isNullOrEmpty(resultJson)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJson);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch(Exception e) {
			logger.error("ProductTypeAPI.getProductTypeList() Exception : "+ExceptionUtils.stackTraceException(e));				
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/product_type_by_id/{objProductType}")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response getProductTypeById(@PathParam("objProductType") String objProductType) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			ProductTypeController productTypeController = new ProductTypeController();
			String resultJson = productTypeController.getProductTypeById(objProductType);
			if(!StringUtils.isNullOrEmpty(resultJson)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJson);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch(Exception e) {
			logger.error("ProductTypeAPI.getProductTypeById() Exception : "+ExceptionUtils.stackTraceException(e));			
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@DELETE
	@Path("/delete_product_type")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response deleteProductType(String productTypeObj) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			ProductTypeController productTypeController = new ProductTypeController();
			boolean result = productTypeController.deleteProductType(productTypeObj);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("ProductTypeAPI.deleteProductType() Exception : "+ExceptionUtils.stackTraceException(e));			
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	/*@POST
	@Path("/datatable/product_list")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<ProductTypeModel> dataTableProductList(DataTablePostParamModel dataTablePostParamModel) throws Exception
	{
		try
		{
			ProductTypeController productController = new ProductTypeController();
			return productController.getProductTypeList(dataTablePostParamModel);
			//DataTableModel<T> productDataTableModel = productController.getDataProductDataTable(dataTablePostParamModel);
			
			//return productDataTableModel;			
		}catch(Exception e) {
			log.error(e.getMessage(), e);			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	
	
	@POST
	@Path("/add_product_type")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response addProductType(String jsonString) {
		ResponseMessageModel responseMessage = new ResponseMessageModel();
		try {
			
			ProductTypeController productTypeController = new ProductTypeController();
			boolean result = productTypeController.addProductType(servletRequest,jsonString);
			responseMessage.setResult(result);
			
			if(result) {
				responseMessage.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessage).build();
			}else {
				responseMessage.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessage).build();
			}
				
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			responseMessage.setResult(false);
			responseMessage.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}
	}
	
	@POST
	@Path("/update_product_type")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response updateProductType(String objProductType) {
		ResponseMessageModel responseMessage = new ResponseMessageModel();
		try {
			
			ProductTypeController productTypeController = new ProductTypeController();
			boolean result = productTypeController.updateProductType(servletRequest, objProductType);
			
			responseMessage.setResult(result);
			
			if(result) {
				responseMessage.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessage).build();
			}else {
				responseMessage.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessage).build();
			}
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			responseMessage.setResult(false);
			responseMessage.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}
	}
	
	@GET
	@Path("/product_by_id/{objProductType}")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public ProductTypeModel getProductById(@PathParam("objProductType") String objProductType) {
		try {
			
			ProductTypeController productTypeController = new ProductTypeController();
			return productTypeController.getProductById(objProductType);
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@DELETE
	@Path("/delete_product_type/{objProductType}")
	@Produces(MediaType.APPLICATION_JSON+";charset=utf-8")
	public Response deleteProductType(@PathParam("objProductType") String objProductType) {
		ResponseMessageModel responseMessage = new ResponseMessageModel();
		try {
			
			ProductTypeController productTypeController = new ProductTypeController();
			boolean result = productTypeController.deleteProductType(objProductType);
			responseMessage.setResult(result);
			
			if(result) {
				responseMessage.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessage).build();
			}else {
				responseMessage.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.OK).entity(responseMessage).build();
			}			
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);
			responseMessage.setResult(false);
			responseMessage.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}
	}*/
	
}
