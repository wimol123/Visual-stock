package th.co.gosoft.audit.cpram.mail;

import com.mysql.jdbc.StringUtils;

import th.co.gosoft.audit.cpram.dto.PoDTO;

public class CreateMailBody {
	
	public static String getBodyRegisterUser(BodyEmailDTO attribute) {
		StringBuilder html = new StringBuilder();		
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            ถึง "+attribute.getFullnameReceiver()+" <br />\r\n" + 
				"            ระบบได้ทำการสร้างบัญชีของท่านในระบบ CPRAM Audit Supplier เรียบร้อยแล้ว <br />\r\n" + 
				"            สำหรับชื่อผู้ใช้งานและรหัสผ่านในการเข้าใช้งานระบบนี้คือ \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <ul style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0;list-style-type: disc;\">\r\n" + 
				"          <li style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 0;\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"                ชื่อเข้าใช้งาน : "+attribute.getUsernameReceiver()+"\r\n" + 
				"            </span>\r\n" + 
				"          </li>\r\n" + 
				"          <li style=\"Margin-top: 0;Margin-bottom: 0;Margin-left: 0;\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"                รหัสผ่าน : "+attribute.getPasswordReceiver()+"\r\n" + 
				"            </span>\r\n" + 
				"          </li>\r\n" + 
				"        </ul>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            ท่านสามารถเข้าใช้งานผ่านระบบอินเตอร์เน็ตตามที่อยู่นี้ &nbsp;\r\n" + 
				"            <a href=\""+attribute.getUrl()+"\">"+attribute.getUrl()+"</a> <br />\r\n" + 
				"            เพื่อความปลอดภัย ควรเปลี่ยนรหัสผ่านเมื่อเข้าใช้งานระบบในครั้งแรก หากพบปัญหากรุณาติดต่อ <br />\r\n" + 
				"            ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+attribute.getEmailAdmin()+" หรือโทร "+attribute.getTelephoneAdmin()+"\r\n" + 
				"          </span>\r\n" + 
				"        </p>");
		
		return html.toString();
	}
	
	public static String getBodyChangePasswordUser(BodyEmailDTO attribute) {
		StringBuilder html = new StringBuilder();
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            ถึง "+attribute.getFullnameReceiver()+" <br />\r\n" + 
				"            ระบบได้ทำการสร้างรหัสผ่านใหม่ของท่านในระบบ CPRAM Audit Supplier เสร็จเรียบร้อยแล้ว <!--<br />-->            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <ul style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0;list-style-type: disc;\">\r\n" + 
				"          <li style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 0;\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"                ชื่อเข้าใช้งาน : "+attribute.getUsernameReceiver()+"\r\n" + 
				"            </span>\r\n" + 
				"          </li>\r\n" + 
				"          <li style=\"Margin-top: 0;Margin-bottom: 0;Margin-left: 0;\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"                รหัสผ่าน : "+attribute.getPasswordReceiver()+"\r\n" + 
				"            </span>\r\n" + 
				"          </li>\r\n" + 
				"        </ul>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            ท่านสามารถเข้าใช้งานผ่านระบบอินเตอร์เน็ตตามที่อยู่นี้ &nbsp;\r\n" + 
				"            <a href=\""+attribute.getUrl()+"\">"+attribute.getUrl()+"</a> <br />\r\n" + 
				"            เพื่อความปลอดภัย ควรเปลี่ยนรหัสผ่านเมื่อเข้าใช้งานระบบในครั้งแรก หากพบปัญหากรุณาติดต่อ <br />\r\n" + 
				"            ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+attribute.getEmailAdmin()+" หรือโทร "+attribute.getTelephoneAdmin()+"\r\n" + 
				"          </span>\r\n" + 
				"        </p>");
		return html.toString();
	}
	
	
	public static String getBodyCreateNewAppoint(BodyEmailNewAppointDTO bodyEmailNewAppoint) {
		StringBuilder html = new StringBuilder();
		
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            เรียน "+bodyEmailNewAppoint.getSupplierName()+"<br />            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <div style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
							"บริษัท ซีพีแรม จำกัด ขอเข้า"+
							bodyEmailNewAppoint.getDetail() + 
				"          </span>\r\n" + 
				"        </div>\r\n" + 
				"        <div style=\"Margin-top: 15px;Margin-bottom: 0;padding: 0;Margin-left: 24px;\">\r\n" + 
				"<span style=\"color:#000000\">\r\n สถานที่ : " + bodyEmailNewAppoint.getLocation()+"\r\n" + 
				"          <br/></span>"+
				"          <span style=\"color:#000000\">\r\n" + 
				"            วันที่นัดหมาย : "+bodyEmailNewAppoint.getDateAppoint()+" เวลา : "+bodyEmailNewAppoint.getTimeAppoint()+" น.\r\n" + 
				"          </span>\r\n" + 
				"        </div>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            โปรดตอบกลับ ผ่านระบบ CPRAM Audit Supplier <a href=\""+bodyEmailNewAppoint.getUrl()+"\">"+bodyEmailNewAppoint.getUrl()+"</a>\r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\r\n" + 
				"            หากพบปัญหากรุณาติดต่อ ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+bodyEmailNewAppoint.getEmailAdmin()+" หรือโทร "+bodyEmailNewAppoint.getTelephoneAdmin()+"\r\n" + 
				"          </span>\r\n" + 
				"        </p>");
		
		return html.toString();
	}
	
	
	public static String getBodyChangeAppointForSupplier(BodyEmailNewAppointDTO bodyEmailNewAppoint) {
		StringBuilder html = new StringBuilder();
		
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            เรียน ผู้เข้าตรวจ และผู้ติดตาม <br />            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <div style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				bodyEmailNewAppoint.getDetail() + 
				"          </span>\r\n" + 
				"        </div>\r\n" + 
				"        <div style=\"Margin-top: 15px;Margin-bottom: 0;padding: 0;Margin-left: 24px;\">\r\n" + 
				"<span style=\"color:#000000\">\r\n สถานที่ : " + bodyEmailNewAppoint.getLocation()+"\r\n" + 
				"          <br/></span>"+
				"          <span style=\"color:#000000\">\r\n" + 
				"            วันที่นัดหมาย : "+bodyEmailNewAppoint.getDateAppoint()+" เวลา : "+bodyEmailNewAppoint.getTimeAppoint()+" น.\r\n" + 
				"          </span>\r\n" + 
				"        </div>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            โปรดตอบกลับ ผ่านระบบ CPRAM Audit Supplier <a href=\""+bodyEmailNewAppoint.getUrl()+"\">"+bodyEmailNewAppoint.getUrl()+"</a>\r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\r\n" + 
				"            หากพบปัญหากรุณาติดต่อ ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+bodyEmailNewAppoint.getEmailAdmin()+" หรือโทร "+bodyEmailNewAppoint.getTelephoneAdmin()+"\r\n" + 
				"          </span>\r\n" + 
				"        </p>");
		
		return html.toString();
	}
	
	public static String getBodyConfirmEditFinalAuditResultForSupplier(BodyEmailConfirmEditFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier) {
		StringBuilder html = new StringBuilder();
		StringBuilder bodyString = new StringBuilder();
		if(StringUtils.isEmptyOrWhitespaceOnly(bodyEmailConfirmFinalAuditResultForSupplier.getDateAccept())) {
			bodyString.append("ระบบขอแจ้ง ผลการเข้าตรวจ กรุณาตรวจสอบและกดรับทราบผลตาม URL ด้านล่าง  \r\n ");
		}else {			
			bodyString.append("ระบบขอแจ้ง ผลการเข้าตรวจ กรุณาตรวจสอบและกดรับทราบผลตาม URL ด้านล่าง ภายในวันที่ "+bodyEmailConfirmFinalAuditResultForSupplier.getDateAccept().split(" ")[0]+" \r\n");
		}
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            เรียน "+bodyEmailConfirmFinalAuditResultForSupplier.getSupplierName()+"<br />            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <div style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
							bodyString.toString()+ 
				"          </span>\r\n" + 
				"        </div>        \r\n" + 
				"        \r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            URL: <a href=\""+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm()+"\">"+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm().split("\\?")[0]+"</a>            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\r\n" + 
				"            หากพบปัญหากรุณาติดต่อ ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+bodyEmailConfirmFinalAuditResultForSupplier.getEmailAdmin()+" หรือโทร "+bodyEmailConfirmFinalAuditResultForSupplier.getTelephoneAdmin()+"\r\n" + 
				"          </span>\r\n" + 
				"        </p>");
		
		return html.toString();
	}
	
	public static String getBodyConfirmFinalAuditResultForSupplier(BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier) {
		StringBuilder html = new StringBuilder();
		StringBuilder bodyString = new StringBuilder();
		if(StringUtils.isEmptyOrWhitespaceOnly(bodyEmailConfirmFinalAuditResultForSupplier.getDateAccept())) {
			bodyString.append("ระบบขอแจ้ง ผลการเข้าตรวจ กรุณาตรวจสอบและกดรับทราบผลตาม URL ด้านล่าง  \r\n ");
		}else {			
			bodyString.append("ระบบขอแจ้ง ผลการเข้าตรวจ กรุณาตรวจสอบและกดรับทราบผลตาม URL ด้านล่าง ภายในวันที่ "+bodyEmailConfirmFinalAuditResultForSupplier.getDateAccept().split(" ")[0]+" \r\n");
		}
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            เรียน "+bodyEmailConfirmFinalAuditResultForSupplier.getSupplierName()+"<br />            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <div style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
							bodyString.toString()+ 
				"          </span>\r\n" + 
				"        </div>        \r\n" + 
				"        \r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            URL: <a href=\""+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm()+"\">"+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm().split("\\?")[0]+"</a>            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\r\n" + 
				"            หากพบปัญหากรุณาติดต่อ ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+bodyEmailConfirmFinalAuditResultForSupplier.getEmailAdmin()+" หรือโทร "+bodyEmailConfirmFinalAuditResultForSupplier.getTelephoneAdmin()+"\r\n" + 
				"          </span>\r\n" + 
				"        </p>");
		
		return html.toString();
	}
	
	
	public static String getBodyAlertFinalAuditResult(BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier) {
		StringBuilder html = new StringBuilder();
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            เรียน "+bodyEmailConfirmFinalAuditResultForSupplier.getSupplierName()+"<br />            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <div style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            แจ้งสรุปผลการเข้าตรวจ อ้างอิงจากแผนการเข้าตรวจเลขที่ "+bodyEmailConfirmFinalAuditResultForSupplier.getChecklistPlanNo()+" \r\n" + 
				"          </span>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              ผลการตรวจประเมิน (Audit Result) : "+bodyEmailConfirmFinalAuditResultForSupplier.getAuditResult()+"\r\n" + 
				"            </span>\r\n" + 
				"          </div>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              สามารถดูรายละเอียดสรุปผลการเข้าตรวจตาม URL ด้านล่าง\r\n" + 
				"            </span>\r\n" + 
				"          </div>       \r\n" + 
				"          \r\n" + 
				"        </div>    \r\n" + 
				"        \r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            URL: <a href=\""+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm()+"\">"+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm().split("\\?")[0]+"</a>\r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\r\n" + 
				"				หากพบปัญหากรุณาติดต่อ ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+bodyEmailConfirmFinalAuditResultForSupplier.getEmailAdmin()+" หรือโทร "+bodyEmailConfirmFinalAuditResultForSupplier.getTelephoneAdmin()+"\r\n" +
				"          </span>\r\n" + 
				"        </p>");
		return html.toString();
	}
	
	public static String getBodyAlertEditFinalAuditResult(BodyEmailConfirmEditFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier) {
		StringBuilder html = new StringBuilder();
		StringBuilder bodyString = new StringBuilder();
		bodyString.append("หมายเหตุ : "+bodyEmailConfirmFinalAuditResultForSupplier.getReason());
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            เรียน "+bodyEmailConfirmFinalAuditResultForSupplier.getSupplierName()+"<br />            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <div style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            แจ้งสรุปผลการเข้าตรวจ อ้างอิงจากแผนการเข้าตรวจเลขที่ "+bodyEmailConfirmFinalAuditResultForSupplier.getChecklistPlanNo()+" \r\n" + 
				"          </span>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              ผลการตรวจประเมิน (Audit Result) : "+bodyEmailConfirmFinalAuditResultForSupplier.getAuditResult()+"\r\n" + 
				"            </span>\r\n" + 
				"          </div>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				bodyString.toString()+
				"            </span>\r\n" + 
				"          </div>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              สามารถดูรายละเอียดสรุปผลการเข้าตรวจตาม URL ด้านล่าง\r\n" + 
				"            </span>\r\n" + 
				"          </div>       \r\n" + 
				"          \r\n" + 
				"        </div>    \r\n" + 
				"        \r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            URL: <a href=\""+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm()+"\">"+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm().split("\\?")[0]+"</a>\r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\r\n" + 
				"				หากพบปัญหากรุณาติดต่อ ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+bodyEmailConfirmFinalAuditResultForSupplier.getEmailAdmin()+" หรือโทร "+bodyEmailConfirmFinalAuditResultForSupplier.getTelephoneAdmin()+"\r\n" +
				"          </span>\r\n" + 
				"        </p>");
		return html.toString();
	}
	
	public static String getBodyConfirmFinalAuditResultForApprover(BodyEmailConfirmFinalAuditResultForApprover bodyEmailConfirmFinalAuditResultForApprover, String mailSubject) {
		StringBuilder html = new StringBuilder();
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            เรียน ผู้บริหาร<br />            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <div style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            "+mailSubject+" \r\n" + 
				"          </span>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              ผลการเข้าตรวจประเมิน : "+bodyEmailConfirmFinalAuditResultForApprover.getPass()+"\r\n" + 
				"            </span>\r\n" + 
				"          </div>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              เกรด : "+bodyEmailConfirmFinalAuditResultForApprover.getGrade()+"\r\n" + 
				"            </span>\r\n" + 
				"          </div>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              ประเด็นที่พบ : "+bodyEmailConfirmFinalAuditResultForApprover.getConclude()+"\r\n" + 
				"            </span>\r\n" + 
				"          </div>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              กรุณาตรวจสอบและกด Apporve ผลการตรวจประเมิน Audit Result ตาม URL ด้านล่าง\r\n" + 
				"            </span>\r\n" + 
				"          </div>       \r\n" + 
				"          \r\n" + 
				"        </div>    \r\n" + 
				"        \r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            URL: <a href=\""+bodyEmailConfirmFinalAuditResultForApprover.getUrlForConfirm()+"\">"+bodyEmailConfirmFinalAuditResultForApprover.getUrlForConfirm().split("\\?")[0]+"</a>\r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\r\n" + 
				"				หากพบปัญหากรุณาติดต่อ ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+bodyEmailConfirmFinalAuditResultForApprover.getEmailAdmin()+" หรือโทร "+bodyEmailConfirmFinalAuditResultForApprover.getTelephoneAdmin()+"\r\n" +
				"          </span>\r\n" + 
				"        </p>");
		return html.toString();
	}
	
	public static String getBodyAlertInformationForSupplier(BodyEmailConfirmFinalAuditResultForSupplier bodyEmailConfirmFinalAuditResultForSupplier) {
		StringBuilder html = new StringBuilder();
		StringBuilder bodyString = new StringBuilder();
		bodyString.append("กรุณาตรวจสอบและกดยืนยันรับทราบ ในระบบ ภายในเวลา ตามที่อยู่ URL ด้านล่าง  \r\n ");
		
		html.append("<p style=\"Margin-top: 0;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            เรียน "+bodyEmailConfirmFinalAuditResultForSupplier.getSupplierName()+"<br />            \r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <div style=\"Margin-top: 20px;Margin-bottom: 0;Margin-left: 24px;padding: 0\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            แจ้งเตือน ข้อกำหนด/ข่าวสาร/ประกาศ/บันทึก จาก บริษัท ซีพีแรม จำกัด \r\n" + 
				"          </span>\r\n" + 
				"          <div style=\"Margin-top: 5px;Margin-bottom: 0;padding: 0\">\r\n" + 
				"            <span style=\"color:#000000\">\r\n" + 
				"              กรุณาตรวจสอบและกดยืนยันรับทราบ ในระบบ ภายในเวลา ตามที่อยู่ URL ด้านล่าง  \r\n" + 
				"            </span>\r\n" + 
				"          </div>       \r\n" + 
				"          \r\n" + 
				"        </div>    \r\n" + 
				"        \r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"color:#000000\">\r\n" + 
				"            URL: <a href=\""+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm()+"\">"+bodyEmailConfirmFinalAuditResultForSupplier.getUrlForConfirm().split("\\?")[0]+"</a>\r\n" + 
				"          </span>\r\n" + 
				"        </p>\r\n" + 
				"        <p style=\"Margin-top:20px;Margin-bottom: 0;\">\r\n" + 
				"          <span style=\"font-size: 12px;line-height: 19px;color: #adb3b9;font-family: sans-serif;Float: left;max-width: 320px;min-width: 200px; width: 320px;width: calc(72200px - 12000%);\">\r\n" + 
				"				หากพบปัญหากรุณาติดต่อ ผู้ดูแลระบบผ่านช่องทาง อีเมล์ "+bodyEmailConfirmFinalAuditResultForSupplier.getEmailAdmin()+" หรือโทร "+bodyEmailConfirmFinalAuditResultForSupplier.getTelephoneAdmin()+"\r\n" +
				"          </span>\r\n" + 
				"        </p>");
		return html.toString();
	}
	
	public static String getBodyConfirmPOToVendor(BodyEmailDTO bodyEmailDTO) {
		StringBuilder html = new StringBuilder();
		html.append(""
				+ "<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<span style=\"font-size: 16pt;\"><b>บริษัท ซีพีแรม จำกัด</b></span>\r\n"
				+ "</div>\r\n"
				+ "<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<br>\r\n"
				+ "</div>\r\n"
				+ "<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<b>เรียน ผู้ส่งมอบ</b>\r\n"
				+ "</div>\r\n"
				+ "<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<br>\r\n"
				+ "</div>\r\n"
				+ "<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		บริษัท ซีพีแรม จำกัด มีความต้องการ จัดซื้อ/จัดจ้าง โดยได้ส่งข้อมูลรายละเอียด ตาม Link แนบ รบกวนผู้ส่งมอบตรวจสอบข้อมูลการสั่งซื้อ และคอนเฟิร์มแผนการส่งมอบสินค้าทุกเอกสารใบสั่งซื้อ (PO) กลับมาในระบบเท่านั้น\r\n"
				+ "</div>\r\n"
				+ "<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<br>\r\n"
				+ "</div>\r\n"
				+ "<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		บริษัท ซีพีแรม จำกัด มีความต้องการ จัดซื้อ/จ้าง ตาม Link ที่ส่งมาด้านล่างนี้\r\n"
				+ "</div>\r\n"
				+ "<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		&gt;&gt; <a href=\"" + bodyEmailDTO.getUrl() + "\" title=\"" + bodyEmailDTO.getUrl() + "\">" + bodyEmailDTO.getUrl() + "</a>\r\n"
				+ "		<br>\r\n"
				+ "</div>\r\n"
				+ "");
		return html.toString();
	}
	
	public static String getBodySupplierAcceptPO(BodyEmailDTO bodyEmailDTO, PoDTO po) {
		StringBuilder html = new StringBuilder();
		html.append(""
				+ "	<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		แจ้งผลการตอบรับ PO จาก Supplier รายละเอียด ดังนี้\r\n"
				+ "	</div>\r\n"
				+ "	<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<br>\r\n"
				+ "	</div>\r\n"
				+ "	<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<b>Purchasing Doc. : </b>" + po.getPoNo() + "\r\n"
				+ "	</div>\r\n"
				+ "	<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<b>Vendor : </b>" + po.getSupplier() + "\r\n"
				+ "	</div>\r\n"
				+ "	<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<b>ผลตอบรับ : </b>" + po.getPoAcceptedName() + "\r\n"
				+ "	</div>\r\n");
		
		html.append(""
				+ "	<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		<br>\r\n"
				+ "	</div>\r\n"
				+ "	<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\">\r\n"
				+ "		เข้าระบบเพื่อดำเนินการต่อได้ที่\r\n"
				+ "	</div>\r\n"
				+ "	<div style=\"font-family: Calibri, Arial, Helvetica, sans-serif; font-size: 12pt; color: rgb(0, 0, 0);\" class=\"elementToProof\">\r\n"
				+ "		&gt;&gt; <a href=\"" + bodyEmailDTO.getUrl() + "\" title=\"" + bodyEmailDTO.getUrl() + "\">" + bodyEmailDTO.getUrl() + "</a>\r\n"
				+ "		<br>\r\n"
				+ "</div>\r\n"
				+ "");
		return html.toString();
	}
	
}
