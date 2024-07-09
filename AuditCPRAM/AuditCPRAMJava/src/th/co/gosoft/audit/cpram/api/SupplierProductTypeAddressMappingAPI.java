package th.co.gosoft.audit.cpram.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import java.util.regex.*;
import javax.ws.rs.GET;
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

import th.co.gosoft.audit.cpram.controller.SupplierProductTypeAddressMappingController;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.SupplierProductAddressMappingModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/supplier_product_type_address_mapping")
public class SupplierProductTypeAddressMappingAPI {
	
	private final static Logger logger = Logger.getLogger(SupplierProductTypeAddressMappingAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	@POST
	@Path("/datatable/supplier_list_mapping_by_product")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierProductAddressMappingModel> datatableGetSupplierListMappingByProduct(DataTablePostParamModel dataTablePostParamModel){
		try {
			SupplierProductTypeAddressMappingController supplierProductTypeAddressMappingController = new SupplierProductTypeAddressMappingController();
			return supplierProductTypeAddressMappingController.datatableGetSupplierListMappingByProduct(dataTablePostParamModel);
		}catch(Exception e) {
			logger.error("SupplierProductTypeAddressMappingAPI.datatableGetSupplierListMappingByProduct() Exception : "+ExceptionUtils.stackTraceException(e));			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	
	@POST
	@Path("/datatable/supplier_produce_mapping_product_type_by_supplier")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierProductAddressMappingModel> datatableSupplierProduceMappingProductTypeBySupplier(DataTablePostParamModel dataTablePostParamModel){
		try {
			SupplierProductTypeAddressMappingController supplierProductTypeAddressMappingController = new SupplierProductTypeAddressMappingController();
			return supplierProductTypeAddressMappingController.datatableSupplierProduceMappingProductTypeBySupplier(servletRequest, dataTablePostParamModel);
		}catch(Exception e) {
			logger.error("SupplierProductTypeAddressMappingAPI.datatableSupplierProduceMappingProductTypeBySupplier() Exception : "+ExceptionUtils.stackTraceException(e));				
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/insert_address_prouduct_type_by_supplier")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertAddressProuductTypeBySupplier(String objSupplierProductAddressMapping) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SupplierProductTypeAddressMappingController supplierProductTypeAddressMappingController = new SupplierProductTypeAddressMappingController();
			boolean result = supplierProductTypeAddressMappingController.insertAddressProuductTypeBySupplier(servletRequest, objSupplierProductAddressMapping);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}	
			
		}catch(Exception e) {
			logger.error("SupplierProductTypeAddressMappingAPI.insertAddressProuductTypeBySupplier() Exception : "+ExceptionUtils.stackTraceException(e));	
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/update_address_prouduct_type_by_supplier")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response updateAddressProuductTypeBySupplier(String objSupplierProductAddressMapping) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		Pattern Pattern_check = Pattern.compile("^[ก-๏a-zA-Z0-9\\s()&/._%+-]+$");
		Gson gson = new Gson();
		try {
			
			SupplierProductTypeAddressMappingController supplierProductTypeAddressMappingController = new SupplierProductTypeAddressMappingController();
			SupplierProductAddressMappingModel supplierProductAddressMappingModelRequest = gson.fromJson(objSupplierProductAddressMapping, SupplierProductAddressMappingModel.class);
			String locationName = supplierProductAddressMappingModelRequest.getLocationName();
			if(!Pattern_check.matcher(locationName).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			boolean result = supplierProductTypeAddressMappingController.updateAddressProuductTypeBySupplier(servletRequest, objSupplierProductAddressMapping);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}	
			
		}catch(Exception e) {
			logger.error("SupplierProductTypeAddressMappingAPI.updateAddressProuductTypeBySupplier() Exception : "+ExceptionUtils.stackTraceException(e));	
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@GET
	@Path("/supplier_location/{supplierObj}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSupplierLocation(@PathParam("supplierObj") String supplierObj){
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			SupplierProductTypeAddressMappingController supplierProductTypeAddressMappingController = new SupplierProductTypeAddressMappingController();
			String resultJson = supplierProductTypeAddressMappingController.getSupplierLocation(supplierObj);
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
			logger.error("SupplierProductTypeAddressMappingAPI.getSupplierLocation() Exception : "+ExceptionUtils.stackTraceException(e));		
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@DELETE
	@Path("/delete_supplier_location")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response deleteSupplierLocation(String objSupplierProductAddressMapping) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			SupplierProductTypeAddressMappingController supplierProductTypeAddressMappingController = new SupplierProductTypeAddressMappingController();
			boolean result = supplierProductTypeAddressMappingController.deleteSupplierLocation(objSupplierProductAddressMapping);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}			
		}catch(Exception e) {
			logger.error("SupplierProductTypeAddressMappingAPI.deleteSupplierLocation() Exception : "+ExceptionUtils.stackTraceException(e));				
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	/*@POST
	@Path("/datatable/supplier_list_mapping_by_product/{objProductType}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierModel> getSupplierListMappingByProduct(DataTablePostParamModel dataTablePostParamModel, @PathParam("objProductType") String objProductType){
		try {
			
			SupplierProductTypeMappingController supplierProductTypeMappingController = new SupplierProductTypeMappingController();
			return supplierProductTypeMappingController.getProductTypeList(dataTablePostParamModel, objProductType);
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	
	@POST
	@Path("/datatable/supplier_list_not_in_product/{objProductType}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierModel> getsupplierListNotInProduct(DataTablePostParamModel dataTablePostParamModel, @PathParam("objProductType") String objProductType){
		try {
			
			SupplierProductTypeMappingController supplierProductTypeMappingController = new SupplierProductTypeMappingController();
			return supplierProductTypeMappingController.getsupplierListNotInProduct(dataTablePostParamModel, objProductType);
		}catch(Exception e) {
			log.error(e.getMessage(), e);			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/datatable/product_list_mapping_by_supplier/{objSupplier}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<ProductTypeModel> productListMappingBySupplier(DataTablePostParamModel dataTablePostParamModel, @PathParam("objSupplier") String objSupplier){
		try {
			
			SupplierProductTypeMappingController supplierProductTypeMappingController = new SupplierProductTypeMappingController();
			return supplierProductTypeMappingController.productListMappingBySupplier(dataTablePostParamModel, objSupplier);			
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/datatable/product_list_not_in_supplier/{objSupplier}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<ProductTypeModel> productListNotInSupplier(DataTablePostParamModel dataTablePostParamModel, @PathParam("objSupplier") String objSupplier){
		try {
			
			SupplierProductTypeMappingController supplierProductTypeMappingController = new SupplierProductTypeMappingController();
			return supplierProductTypeMappingController.productListNotInSupplier(dataTablePostParamModel, objSupplier);
			
		}catch(Exception e) {
			log.error(e.getMessage(), e);			
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/insert_supplier_product_to_mapping/{objMapping}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response insertSupplierProductToMapping(@PathParam("objMapping") String objMapping) {
		ResponseMessageModel responseMessage = new ResponseMessageModel();
		try {
			
			SupplierProductTypeMappingController supplierProductTypeMappingController = new SupplierProductTypeMappingController();
			boolean result = supplierProductTypeMappingController.insertSupplierProductToMapping(servletRequest, objMapping);
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
	
	
	
	@DELETE
	@Path("/delete_supplier_product_to_mapping/{objMapping}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response deleteSupplierProductToMapping(@PathParam("objMapping") String objMapping) {
		ResponseMessageModel responseMessage = new ResponseMessageModel();
		try {
			
			SupplierProductTypeMappingController supplierProductTypeMappingController = new SupplierProductTypeMappingController();
			boolean result = supplierProductTypeMappingController.deleteSupplierProductToMapping(servletRequest, objMapping);
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
