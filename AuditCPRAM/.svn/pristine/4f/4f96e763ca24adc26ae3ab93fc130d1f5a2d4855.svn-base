package th.co.gosoft.audit.cpram.api;


import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import java.util.regex.*;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.controller.SupplierController;
import th.co.gosoft.audit.cpram.model.DataTableModel;
import th.co.gosoft.audit.cpram.model.ResponseMessageModel;
import th.co.gosoft.audit.cpram.model.SupplierModel;
import th.co.gosoft.audit.cpram.model.datatable.parameter.DataTablePostParamModel;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.ResponseMessage;

@Path("/supplier")
public class SupplierAPI {
	private final static Logger logger = Logger.getLogger(SupplierAPI.class);
	
	@Context private HttpServletRequest servletRequest;
	
	
	@POST
	@Path("/datatable/get_supplier_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierModel> getSupplierList(DataTablePostParamModel dataTablePostParamModel){
		try {
			
			SupplierController supplierController = new SupplierController();
			return supplierController.getSupplierList(dataTablePostParamModel);
			
		}catch(Exception e) {
			logger.error("SupplierAPI.getSupplierList() Exception : "+ExceptionUtils.stackTraceException(e));		
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/upload_logo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadLogo(MultipartFormDataInput multipartFormDataInput) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			SupplierController supplierController = new SupplierController();
			boolean result = supplierController.uploadLogo(multipartFormDataInput);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
				return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
			}
		}catch (Exception e) {
			logger.error("SupplierAPI.uploadLogo() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/supplier_insert")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response supplierInsert(String objSupplier) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		Pattern Pattern_check = Pattern.compile("^[ก-๏a-zA-Z0-9\\s()&/._%+-]+$");
		Gson gson = new Gson();
		try {
			SupplierController supplierController = new SupplierController();
			SupplierModel supplierRequestModel = gson.fromJson(objSupplier, SupplierModel.class);
			String supplierCompany = supplierRequestModel.getSupplierCompany();
			if(!Pattern_check.matcher(supplierCompany).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			Map<String, Object> result = supplierController.supplierInsert(servletRequest,objSupplier);
			
			if(!(Boolean)result.get("dupplicateUserName")) {
				if(!(Boolean)result.get("dupplicateSupplier")) {
					if(!(Boolean)result.get("dupplicateSupplierMapping")) {
						if(!(Boolean)result.get("dupplicateLogoSupplier")) {
							if((Boolean)result.get("resultProcess")) {
								responseMessageModel.setResult(true);
								responseMessageModel.setMessage(ResponseMessage.Save_Data_Success);
								return Response.status(Status.OK).entity(responseMessageModel).build();
							}//if((Boolean)result.get("resultProcess"))
							else {
								responseMessageModel.setResult(false);
								responseMessageModel.setMessage(ResponseMessage.Save_Data_Unsuccess);
								return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
							}								
						}//if(!(Boolean)result.get("dupplicateLogoSupplier"))
						else {
							responseMessageModel.setResult(false);
							responseMessageModel.setMessage(ResponseMessage.Supplier_Logo_Existing);
							return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
						}
					}//if(!(Boolean)result.get("dupplicateSupplierMapping"))
					else {
						responseMessageModel.setResult(false);
						responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
						return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
					}
				}//if(!(Boolean)result.get("dupplicateSupplier"))
				else {
					responseMessageModel.setResult(false);
					responseMessageModel.setMessage(ResponseMessage.Supplier_Existing);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}
			}//if(!(Boolean)result.get("dupplicateUserName"))
			else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.UserName_Existing);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("SupplierAPI.supplierInsert() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@POST
	@Path("/supplier_update")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response supplierUpdate(String objSupplier) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		Pattern Pattern_check = Pattern.compile("^[ก-๏a-zA-Z0-9\\s()&/._%+-]+$");
		Gson gson = new Gson();
		try {
			
			SupplierController supplierController = new SupplierController();
			SupplierModel supplierRequestModel = gson.fromJson(objSupplier, SupplierModel.class);
			String supplierCompany = supplierRequestModel.getSupplierCompany();
			if(!Pattern_check.matcher(supplierCompany).matches()) {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			Map<String, Object> result = supplierController.supplierUpdate(servletRequest, objSupplier);
			if(!(Boolean)result.get("dupplicateSupplierCompany")) {
				if((Boolean)result.get("resultProcess")) {
					responseMessageModel.setResult(true);
					responseMessageModel.setMessage(ResponseMessage.Edit_Data_Success);
					return Response.status(Status.OK).entity(responseMessageModel).build();
				}else {
					responseMessageModel.setResult(false);
					responseMessageModel.setMessage(ResponseMessage.Edit_Data_Unsuccess);
					return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
				}				
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.Supplier_Existing);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch (Exception e) {
			logger.error("SupplierAPI.supplierUpdate() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/supplier_list")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response getSupplierList() {
		ResponseMessageModel responseMessageModel  = new ResponseMessageModel();
		try {
			
			SupplierController supplierController = new SupplierController();
			String resultJSONSupplier = supplierController.getSupplierList(servletRequest);
			if(!StringUtils.isNullOrEmpty(resultJSONSupplier)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSONSupplier);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
		}catch(Exception e) {
			logger.error("SupplierAPI.getSupplierList() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	@GET
	@Path("/supplier_detail/{supplier_obj}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")	
	public Response getSupplierDetail(@PathParam("supplier_obj") String objSupplier) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {			
			
			SupplierController supplierController = new SupplierController();
			String resultJSONSupplier = supplierController.getSupplierDetail(servletRequest, objSupplier);
			if(!StringUtils.isNullOrEmpty(resultJSONSupplier)) {
				responseMessageModel.setResult(true);
				responseMessageModel.setMessage(resultJSONSupplier);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setResult(false);
				responseMessageModel.setMessage(ResponseMessage.BAD_REQUEST);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("SupplierAPI.getSupplierDetail() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}		
	}
	
	
	@DELETE
	@Path("/delete_logo")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response deleteLogo(String fileName) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			SupplierController supplierController = new SupplierController();
			boolean result = supplierController.deleteLogo(fileName);
			responseMessageModel.setResult(result);
			if(result) {
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("SupplierAPI.deleteLogo() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	
	@DELETE
	@Path("/supplier_delete")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response supplierDelete(String objSupplier) {
		ResponseMessageModel responseMessageModel = new ResponseMessageModel();
		try {
			
			SupplierController supplierController = new SupplierController();
			boolean result = supplierController.supplierDelete(objSupplier);
			responseMessageModel.setResult(result);
			if(result) {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Success);
				return Response.status(Status.OK).entity(responseMessageModel).build();
			}else {
				responseMessageModel.setMessage(ResponseMessage.Delete_Data_Unsuccess);
				return Response.status(Status.BAD_REQUEST).entity(responseMessageModel).build();
			}
			
		}catch(Exception e) {
			logger.error("SupplierAPI.supplierDelete() Exception : "+ExceptionUtils.stackTraceException(e));
			responseMessageModel.setResult(false);
			responseMessageModel.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessageModel).build();
		}
	}
	
	/*@GET
	@POST
	@Path("/supplier_detail/{supplierId}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public SupplierModel getSupplierDetail(@PathParam("supplierId") String supplierId) {
		try {
			
			SupplierController supplierController = new SupplierController();
			SupplierModel supplier = supplierController.getSupplierDetail(NullUtils.cvStr(supplierId));
			return supplier;
			
		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(ex.getMessage(), ex);
		}
	}
	
	
	@POST
	@Path("/datatable/get_supplier_list")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierModel> getSupplierList(DataTablePostParamModel model) {		
		try {
			
			SupplierController supplierController = new SupplierController();
						
			return supplierController.getSupplierList(model);
			
		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	@POST
	@Path("/datatable/supplier_by_product")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierModel> dataTableSupplierByProduct(DataTablePostParamModel param) throws Exception
	{
		try
		{
			SupplierController supplierController = new SupplierController();		
			DataTableModel<SupplierModel> SupplierDataTableModel = supplierController.dataTableSupplierByProduct(param);
			
			return SupplierDataTableModel;			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(e.getMessage(), e);
		}
	}
	

	@POST
	@Path("/datatable/supplier_for_product")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public DataTableModel<SupplierModel> dataTableSupplierForProduct(DataTablePostParamModel param) throws Exception
	{
		try
		{
			SupplierController supplierController = new SupplierController();		
			DataTableModel<SupplierModel> SupplierDataTableModel = supplierController.dataTableSupplierForProduct(param);
			
			return SupplierDataTableModel;			
		}catch(Exception e) {
			logger.error(e.getMessage(), e);
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	

	@GET
	@Path("/supplier_list")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public List<SupplierModel> getSupplierList() {
		try {
			
			SupplierController supplierController = new SupplierController();
			return supplierController.getSupplierList();
			
		}catch(Exception ex) {
			logger.error(ex.getMessage(), ex);
			throw new RuntimeException(ResponseMessage.Internal_Server_Error);
		}
	}
	
	

	@DELETE
	@Path("/supplier_delete/{supplierObj}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response deleteSupplier(@PathParam("supplierObj") String supplierObj) {
		
		ResponseMessageModel res = new ResponseMessageModel();
		try {			
			SupplierController supplierController = new SupplierController();
			boolean result = supplierController.deleteSupplier(supplierObj);	
			res.setResult(result);
			
			if(result)
			{
				res.setMessage(ResponseMessage.Delete_Data_Success);					
				return Response.status(Status.OK).entity(res).build();
			}else
			{
				res.setMessage(ResponseMessage.Delete_Data_Unsuccess);					
				return Response.status(Status.OK).entity(res).build();
			}
			
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ResponseMessage.Internal_Server_Error);
			res.setResult(false);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	@POST
	@Path("/supplier_update/{json_objform}")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response UpdateSupplier(@PathParam("json_objform") String json_objform) {
		
		ResponseMessageModel res = new ResponseMessageModel();
		try {			
			SupplierController supplierController = new SupplierController();
			boolean result = supplierController.updateSupplier(servletRequest,json_objform);
			res.setResult(result);
			
			if(result)
			{
				res.setMessage(ResponseMessage.Edit_Data_Success);					
				return Response.status(Status.OK).entity(res).build();
			}else
			{
				res.setMessage(ResponseMessage.Edit_Data_Unsuccess);					
				return Response.status(Status.OK).entity(res).build();
			}
		}catch(Exception ex) {
			res.setMessage(ResponseMessage.Internal_Server_Error);
			res.setResult(false);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(res).build();
		}
	}
	
	
	@POST
	@Path("/supplier_insert")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public Response InsertSupplier(String StringJsonModel) {
		ResponseMessageModel responseMessage = new ResponseMessageModel();
		try {
			//boolean checkStatus = false;
			SupplierController supplierController = new SupplierController();
			Map<String, Object> result = supplierController.insertSupplier(servletRequest, StringJsonModel);
			boolean resultDupplicateLogoSupplier = (boolean)result.get("dupplicateLogoSupplier");
			if(!resultDupplicateLogoSupplier) {
				
				boolean resultDupplicateUser = (boolean) result.get("duplicateUser");					
				
				if(!resultDupplicateUser) {
					
					
					boolean resultDupplicateSupplier = (boolean) result.get("dupplicateSupplier");		
					
					if(!resultDupplicateSupplier) {
						boolean resultProcessing = (boolean) result.get("resultProcess");		
						if(resultProcessing) {
							responseMessage.setResult(resultProcessing);
							responseMessage.setMessage(ResponseMessage.Save_Data_Success);
							return Response.status(Status.OK).entity(responseMessage).build();	
						}else {
							responseMessage.setResult(resultProcessing);
							responseMessage.setMessage(ResponseMessage.Save_Data_Unsuccess);
							return Response.status(Status.OK).entity(responseMessage).build();	
						}	
					}else {
						responseMessage.setResult(resultDupplicateSupplier);
						responseMessage.setMessage(ResponseMessage.Supplier_Company_Existing);
						return Response.status(Status.BAD_REQUEST).entity(responseMessage).build();
					}
					
				}else {
					responseMessage.setResult(resultDupplicateUser);
					responseMessage.setMessage(ResponseMessage.UserName_Existing);
					return Response.status(Status.BAD_REQUEST).entity(responseMessage).build();
				}
			}else {
				
				responseMessage.setResult(resultDupplicateLogoSupplier);
				responseMessage.setMessage(ResponseMessage.Supplier_Logo_Existing);
				return Response.status(Status.BAD_REQUEST).entity(responseMessage).build();
				
			}		
			
		}catch(Exception e) {
			responseMessage.setResult(false);
			responseMessage.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}
	}
	
	@POST
	@Path("/upload_logo")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response uploadLogo(MultipartFormDataInput inputForm) {
		ResponseMessageModel responseMessage = new ResponseMessageModel();
		try {
			SupplierController supplierController = new SupplierController();
			boolean result = supplierController.uploadLogo(inputForm);
			responseMessage.setResult(result);
			
			if(result)
			{
				responseMessage.setMessage(ResponseMessage.Save_Data_Success);					
				return Response.status(Status.OK).entity(result).build();
			}else
			{
				responseMessage.setMessage(ResponseMessage.Save_Data_Unsuccess);					
				return Response.status(Status.OK).entity(result).build();
			}
			
		}catch (Exception e) {
			responseMessage.setResult(false);
			responseMessage.setMessage(ResponseMessage.Internal_Server_Error);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}
	}

	@GET
	@Path("/supplier_insert2/{fileName}")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public Response InsertSupplier2(@PathParam("fileName") String filename) {
		
		EncryptUtils encryptUtils = new EncryptUtils();
		WriteFile writeFile = new WriteFile();
		try {
			System.out.println(encryptUtils.EncryptSHA256());
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	        System.out.println(timestamp.getTime());

			System.out.println(writeFile.CopyFileToDirectory(FileUploadConst.PATH_Temp.concat("map.png"), FileUploadConst.PATH_Supplier_Logo));
			System.out.println(writeFile.DeleteFile(FileUploadConst.PATH_Temp.concat("map.png")));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String fileName = "";
		List<InputPart> imagePart = input.getFormDataMap().get("logo");
		for(InputPart img : imagePart) {
			 MultivaluedMap<String, String> headers = img.getHeaders();
			 String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
			 for (String name : contentDispositionHeader) {
				 if ((name.trim().startsWith("filename"))) {
					 String[] tmp = name.split("=");
					 fileName = tmp[1].trim().replaceAll("\"","");          
				 }
			 }
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			InputStream is2 = input.getFormDataPart("logo", InputStream.class,null);
			String outputFile = "C:\\temp\\"+fileName;
		    Files.copy(is2, Paths.get(outputFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		StringBuffer fileDetails = new StringBuffer("");
		
		BodyPartEntity bodyPartEntity = ((BodyPartEntity) multiPart.getField("logo").getEntity());
		String file2Name = multiPart.getField("logo").getFormDataContentDisposition().getFileName();
		java.nio.file.Path path = FileSystems.getDefault().getPath("c:/temp/" + file2Name);
		try {
			Files.copy(bodyPartEntity.getInputStream(), path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		fileDetails.append(" File saved at c:/temp" + file2Name);

		
		System.out.println(fileDetails);
		
		System.out.println(FileUploadConst.genPathFileLogoImage(filename));
		
	    return Response.status(Status.OK).build();
	}*/
	
	
	
	
}
