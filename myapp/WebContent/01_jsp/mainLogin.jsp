<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, org.json.simple.*"%>
<jsp:useBean id="bja" class="beans.BeanDBC" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<%

String ID = request.getParameter("id");
String PW = request.getParameter("pw");
%>
<body>

<%
bja.connect();
if(ID !=null)
	System.out.println("login: "+ID); 
switch(bja.verifyUser(ID, PW)) {
case 1:  
	session = request.getSession();
	session.setAttribute("id", ID);
	session.setAttribute("pw", PW);
	out.println("성공");
     break;
case 0: out.println("실패");
     break;
case -1: out.println("id불일치");
	break;
case -2: out.println("db오류");
	break;
}

bja.disconnect();



%>

</body>
</html>