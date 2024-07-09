package th.co.gosoft.audit.cpram.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import th.co.gosoft.audit.cpram.dto.MailConfigDTO;
import th.co.gosoft.audit.cpram.mail.BodyEmailDTO;
import th.co.gosoft.audit.cpram.mail.Mail;
import th.co.gosoft.audit.cpram.mail.MailConnector;
import th.co.gosoft.audit.cpram.mail.MailReceiver;
import th.co.gosoft.audit.cpram.model.AppointModel;
import th.co.gosoft.audit.cpram.utils.Config;
import th.co.gosoft.audit.cpram.utils.DateUtils;
import th.co.gosoft.audit.cpram.utils.ExceptionUtils;
import th.co.gosoft.audit.cpram.utils.PasswordGenerator;

@Path("/test")
public class TestAPI {	
	
	@GET
	@Path("testcast/{obj}")
	@Produces(MediaType.APPLICATION_JSON + ";charset=utf-8")
	@Consumes(MediaType.APPLICATION_JSON + ";charset=utf-8")
	public void testCast(@PathParam("obj")String obj) {
		//String t = "";
		//UserDTO userDTO = new UserDTO();
		//userDTO.setEnable(NullUtils.cvChar(t));
		//System.out.println(userDTO.getEnable());
		System.out.println(obj);
	}
	
	@GET
	@Path("/gen_pass")
	public void genPassword() {
		PasswordGenerator passwordGenerator = new PasswordGenerator.PasswordGeneratorBuilder()
				.useDigits(true)
				.useLower(true)
				.useUpper(true)
				.build();
		System.out.println(passwordGenerator.generate(12));
	}
	
	@GET
	@Path("/logo_supplier")
	@Produces({"image/png", "image/jpg", "image/jpeg", "image/gif"})
	public Response getLogoSupplier() throws IOException {
		
		File sourceimage = new File("D:\\AuditCPRam\\Supplier\\Logo\\logo_20190125_085754757.png");		
		//Image image = ImageIO.read(sourceimage);
		BufferedImage bufferedImage = ImageIO.read(sourceimage);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.flush();
		ImageIO.write(bufferedImage, "png", bos );
		bos.close();
		byte[] byteImage = bos.toByteArray();
		
		return Response.status(Status.OK).entity(new ByteArrayInputStream(byteImage)).build();	
	}
	
	@GET
	@Path("/test_new_mail")
	public void testNewMail() {
		List<String> receiver = new ArrayList<>();
		BodyEmailDTO bodyEmailDTO = Config.getObjectBodyMail();	
		bodyEmailDTO.setPasswordReceiver("adfhsgogrsiowerjg;vdmb");		
		receiver.add("teerapat.ratc@hotmail.com");
		bodyEmailDTO.setFullnameReceiver("Test");
		bodyEmailDTO.setUsernameReceiver("teerapatrat");
		MailReceiver mailReceiver = new MailReceiver();
		mailReceiver.setMailReceiver(receiver);
		System.out.println(MailConnector.sendMailRegisterUser(mailReceiver, bodyEmailDTO, "Authentification CPRam Audit Supplier"));
		
		//MailProcessing.instance();
		
	}
	
	@GET
	@Path("testemail")
	public void mail() {
		BodyEmailDTO bodyEmailDTO = new BodyEmailDTO();
		bodyEmailDTO.setEmailAdmin("admincpram@cpram.co.th");
		bodyEmailDTO.setFullnameReceiver("ธีรภัทร รัชตธนกุล");
		bodyEmailDTO.setPasswordReceiver("@Admin@");
		bodyEmailDTO.setTelephoneAdmin("02-739-4423");
		bodyEmailDTO.setUrl("http://localhost:8080/AuditCPRAMWeb");
//		bodyEmailDTO.setUrl("http://10.182.236.158/AuditCPRAMWeb");
//		bodyEmailDTO.setUrl("http://10.182.236.159:3309/AuditCPRAMWeb");
//		bodyEmailDTO.setUrl("http://10.182.236.159:3309/auditsupplier");
//		
		
		bodyEmailDTO.setUsernameReceiver("teerapatrat");
		
		MailConfigDTO mailConfigDTO = new MailConfigDTO();
		List<String> to = new ArrayList<>();
		to.add("teerapat.ratchatathanakun@gmail.com");
		//to.add("Aranya_9959@hotmail.com");
		List<String> cc = new ArrayList<>();
		//cc.add("teerapat.ratc@gmail.com");
		cc.add("teerapat.ratc@hotmail.com");
		List<String> BCC = new ArrayList<>();
		BCC.add("teerapat_kill@hotmail.com");
		BCC.add("teerapat_killer@hotmail.com");
		mailConfigDTO.setEmailReceiver(to);
		mailConfigDTO.setEmailReceiverCC(cc);
		mailConfigDTO.setEmailReceiverBCC(BCC);
		mailConfigDTO.setEmailSender("teerapat.ratc@gmail.com");
		mailConfigDTO.setEmailSenderPassword("kill25317");
		mailConfigDTO.setEmailSubject("test Mail");
		mailConfigDTO.setEnanbleAuthen(true);
		mailConfigDTO.setServerSMTP("smtp.gmail.com");
		mailConfigDTO.setPortSMTP(25);
		//mailConfigDTO.setEmailBody(CreateMailBody.bodyRegisterHTML(bodyEmailDTO));
		
		Mail mail = new Mail(mailConfigDTO);
		mail.sendMailBodyHTML();
	}
	
	@GET
	@Path("/test_gson")
	public void testGson() {
		try {
			Gson gson = new Gson();
			String gsonObj = "{\"appointId\":\"3\",\"appointDate\":\"20/01/2019 10:30:00\",\"title\":\"นัดหมายการเข้า Audit\",\"detail\":\"นัดหมายการเข้า Audit กับ Company A (ยืนยันการเลื่อนนัด ครั้งที่ 2)\",\"auditorId\":[{\"userId\":\"9\",\"fullname\":\"Auditor A\"}],\"entourageId\":[{\"userId\":\"1\",\"fullname\":\"Admin\"}],\"supplierId\":{\"supplierId\":\"6\",\"supplierCompany\":\"บริษัท A จำกัด\"},\"locationId\":{\"id\":\"2\"},\"appointStatusId\":{\"appointStatusId\":\"2\"},\"enable\":\"Y\",\"appointHistoryId\":[{\"title\":\"ขอเลื่อนการเข้าตรวจ\",\"detail\":\"{\\\"Detail\\\":\\\"ขอเลื่อนนัด - ขอเลื่อนนัดเป็นวันที่ 18/02/2019 เวลา 16:37 น. โดยประมาณ จึงแจ้งมาเพื่อพิจารณาการเลื่อนนัดในครั้งนี้\\\",\\\"Auditor\\\":[{\\\"Id\\\":\\\"9\\\",\\\"Name\\\":\\\"Auditor A\\\"}],\\\"Entourage\\\":[{\\\"Id\\\":\\\"1\\\",\\\"Name\\\":\\\"Admin\\\"}],\\\"AppointDate\\\":\\\"20/01/2019\\\",\\\"AppointTime\\\":\\\"10:30\\\"}\",\"appointStatusId\":{\"appointStatusId\":\"2\"},\"enable\":\"Y\"}]}";
			AppointModel appointModel = gson.fromJson(gsonObj, AppointModel.class);
			System.out.println(appointModel.getAppointHistoryId().get(0).getDetail());
			
			JsonElement jsonElement = new JsonParser().parse(appointModel.getAppointHistoryId().get(0).getDetail());
			JsonObject  jobject = jsonElement.getAsJsonObject();
			System.out.println(jobject.get("Detail").getAsString());
			
		}catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	
	@GET
	@Path("/compair_date")
	public void compairDate() {
		try {
			
			String dateStart = "01/02/2019";
			String dateEnd = "13/02/2019";
			
			Date date_start = DateUtils.parseWebDateStringToSQLDate(dateStart);
			Date date_end = DateUtils.parseWebDateStringToSQLDate(dateEnd);
			
			System.out.println(date_start.before(date_end));
			System.out.println(date_end.before(date_start));
			
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	@POST
	@Path("/replace_regular")
	public void replaceRegular(String str) {
		try {
			
			String test = str;
			System.out.println("Str param:" + str);
			
			if(str.matches(".*\\\\.*"))
			{
				test= str.replaceAll("\\\\", "\\\\\\\\");
				System.out.println("Replace \\ :"+str.replaceAll("\\\\", "\\\\\\\\"));
			}
			
			if(test.matches(".*\'.*"))
			{				
				System.out.println("Replace \' :"+test.replaceAll("'", "\\\\\'"));
				test = test.replaceAll("'", "\\\\\'");
			}
			
			System.out.println("Final Result:"+test);
			
		}catch (Exception e) {
			System.out.println(ExceptionUtils.stackTraceException(e));
		}
	}
}
