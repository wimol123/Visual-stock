<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8" />
	<title>Email</title>
</head>
<body>

<h3>Batch & Email</h3>
<ul>
	<li>Batch, Email => ตรวจสอบเพื่อแจ้งล่วงหน้าก่อนเข้าตรวจ 30 วัน</li>
	<li>Batch?, Email => แจ้งนัดเข้าตรวจ กรณี Supplier ใหม่</li>
	<li>Batch, Email => ไม่คำสั่งซื้อ (PO)เกิน 365 วัน และเกิน  730 วัน </li>
	<li>Email => สร้างการนัดหมายใหม่ หรือมีการส่งการเลือนนัด, มีการตอบกลับจาก Supplier</li>
	<li>Batch, Email => แจ้งเตือนก่อนเข้าตรวจ 7 วัน  batch จะทำงานตอน 20.00</li>
	<li>Batch, Email => แจ้งเตือนก่อนเข้าตรวจ 1 วัน  batch จะทำงานตอน 20.00</li>
	<li></li>
</ul>

</body>
</html>