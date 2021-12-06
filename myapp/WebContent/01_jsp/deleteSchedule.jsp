<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, org.json.simple.*"%>
    <jsp:useBean id="bja" class="beans.BeanDBC" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
<% 

request.setCharacterEncoding("utf-8");
String ID =request.getParameter("id");
String Sno = request.getParameter("sno");
 
if(ID !=null)
	System.out.println("schedule: "+ID+"/"+Sno);

/*  
 String ID="test";
 String Sno="5";
   */
bja.connect();

switch(bja.deleteSchedule(ID, Sno)) {
case 1:  
	out.println("성공");
     break;
case -1: out.println("실패");
	break;
default: out.println("일정삭제: 예외 error");
	break;
}

bja.disconnect();
%>
</body>
</html>