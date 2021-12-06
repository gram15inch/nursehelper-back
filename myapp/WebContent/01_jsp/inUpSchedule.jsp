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
/*  request.setCharacterEncoding("utf-8");
String ID =request.getParameter("id");
String Sno =request.getParameter("sno"); 
String Pno =request.getParameter("pno"); 
String Sdate = request.getParameter("sdate");
String Edate =request.getParameter("edate");
String Color =request.getParameter("color"); 
 
if(ID !=null){
	System.out.println("InUpSchedule: "+ID+"/"+Sno+"/"+Pno+"/"+Sdate+"/"+Edate+"/"+Color);
} */

bja.connect();

String ID="test";
String Sno="4";
String Pno="4";
String Sdate="1";
String Edate="20211101"; 
String Color = "jspmemoIn";

//작동 테스트

switch(bja.inUpdateSchedule(ID, Sno,Pno,Sdate, Edate, Color) ) {
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