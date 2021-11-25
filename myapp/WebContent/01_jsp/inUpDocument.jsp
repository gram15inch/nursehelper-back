<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, org.json.simple.*"%>
    <jsp:useBean id="bja" class="beans.BeanDBC" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<%
 request.setCharacterEncoding("utf-8");
String ID =request.getParameter("id");
String Type =request.getParameter("type"); 
String Pno = request.getParameter("pcode");
String Date =request.getParameter("date");
String Memo =request.getParameter("memo"); 
 
bja.connect();
/* 
String ID="test";
String Pno="3";
String Type="1";
String Date="20211101"; 
String Memo = "jspmemoIn";
 */
if(ID !=null){
	System.out.println("InUpDocument: "+ID+"/"+Type+"/"+Pno+"/"+Date+"/"+Memo);
}

switch(bja.inUpdateDocument(ID, Type, Pno, Date, Memo) ) {
case 1: out.println("success");
     break;
case -2: out.println("db error");
     break;
default: out.println("??? error");
     break;
}


bja.disconnect();

%>
</body>
</html>