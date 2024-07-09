package th.co.gosoft.audit.cpram.utils;

public class ResponseMessage {
	public static final String Product_Name_Existing = "มีชื่อสินค้า นี้ในระบบแล้ว";
	public static final String Supplier_Existing = "มีชื่อบริษัท นี้ในระบบแล้ว";
	public static final String UserName_Existing = "มีชื่อผู้ใช้งาน นี้ในระบบแล้ว";
	public static final String Checklist_Not_Exitsting = "ไม่พบเช็คลิสต์ในระบบ";
	public static final String Email_Existing = "มีอีเมล์ นี้ในระบบแล้ว";
	public static final String Effective_Invalid = "ช่วงเวลา Effective Date - Expire Date ซ้อนทับกับที่มีอยู่ในระบบ";
	public static final String AppointDateTime_invalid = "ไม่สามารถสร้างการนัดหมายได้เนื่องจากเวลานัดหมายน้อยกว่า 24 ชั่วโมงนับจากวันเวลาที่สร้างนัด";
 	public static final String Question_Group_Name_Existing = "มีกลุ่มคำถาม นี้ในระบบแล้ว";	
	public static final String Save_Data_Success = "บันทึกข้อมูลสำเร็จ";
	public static final String Save_Data_Unsuccess = "บันทึกข้อมูลไม่สำเร็จ";
	public static final String Edit_Data_Success = "แก้ไขข้อมูลสำเร็จ";
	public static final String Edit_Data_Unsuccess = "แก้ไขข้อมูลไม่สำเร็จ";
	public static final String Delete_Data_Success = "ลบข้อมูลสำเร็จ";
	public static final String Delete_Data_Unsuccess = "ลบข้อมูลไม่สำเร็จ";
	public static final String Supplier_Logo_Existing = "มีโลโก้นี้อยู่แล้ว กรุณาเลือกโลโก้ใหม่อีกครั้ง";
	public static final String Internal_Server_Error = "พบข้อผิดพลาด : (  กรุณาติดต่อผู้ดูแลระบบ";
	public static final String BAD_REQUEST = "พบข้อผิดพลาด : (  กรุณาติดต่อผู้ดูแลระบบ";
	public static final String UserName_LDAP_Non_Existing = "ไม่พบชื่อเข้าใช้งานนี้ในระบบ LDAP :( กรุณาติดต่อหน่วยงานต้นสังกัด";
	public static final String Change_Password_Success = "ทำการเปลี่ยนรหัสผ่านสำเร็จ";
	public static final String Change_Password_Unsuccess = "ทำการเปลี่ยนรหัสผ่านไม่สำเร็จ";
	public static final String Change_Password_Admin = "ไม่สามารถเปลี่ยนรหัสผ่านได้เนื่องจากชื่อผู้ใช้นี้เป็นผู้ดูแลระบบ กรุณาติดต่อผู้พัฒนาระบบ";
	public static final String Reload_config_Success = "ทำการโหลดการตั้งค่าใหม่เรียบร้อย";
	public static final String Reload_config_Unsuccess = "ไม่ทำการโหลดการตั้งค่าใหม่ได้";
	public static final String SupplierAdmin_Existing = "Supplier นี้มี User ที่เป็น Supplier Admin แล้ว";
	public static final String Process_Success = "ดำเนินการสำเร็จ";
	public static final String Process_Fail = "ดำเนินการไม่สำเร็จ";
}
