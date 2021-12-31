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
String Sno =request.getParameter("sno"); 
String Pno =request.getParameter("pno"); 
String Sdate = request.getParameter("sdate");
String Edate =request.getParameter("edate");
String Color =request.getParameter("color"); 

String Date = Sdate.substring(0,8);
String Type = "1";
String Memo ="";

if(ID !=null){
	System.out.println("InUpSchedule: "+ID+"/"+Sno+"/"+Pno+"/"+Sdate+"/"+Edate+"/"+Color);
}  

bja.connect();
/* 
String ID="test";
String Sno="28";
String Pno="-1";
String Sdate="202111010800";
String Edate="202111010903"; 
String Color = "#jspColorIn";
  */
//작동 테스트

int sr = bja.inUpdateSchedule(ID, Sno,Pno,Sdate, Edate, Color);


switch(sr) {
case -1: out.println("??? error"); //save error
     break;
case -2: out.println("db error");
     break;
default: {
	out.println(""+sr);
	if(Sno == "-1")
	bja.inUpdateDocument(ID,Type,Pno,Date,Memo);
	
};
	break;
}


bja.disconnect();

%>
</body>
</html>