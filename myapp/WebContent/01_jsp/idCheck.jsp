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

switch(bja.idCheck(ID)) {
case 1:  
	session = request.getSession();
	session.setAttribute("id", ID);
	out.println("중복");
     break;
case 0: out.println("id없음");
     break;
case -1: out.println("id없음");
	break;
case -2: out.println("db오류");
	break;
}

bja.disconnect();



%>

</body>
</html>