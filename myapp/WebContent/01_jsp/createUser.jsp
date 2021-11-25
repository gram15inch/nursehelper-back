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
String NAME = request.getParameter("name");
String SEX = request.getParameter("sex");
String PN = request.getParameter("pn");

%>
<body>

<%
bja.connect();


switch(bja.resist(ID, PW, NAME, SEX, PN)) {
case 1:  
	session = request.getSession();
	session.setAttribute("id", ID);
	out.println("성공");
     break;
case 0: out.println("실패");
     break;
case -1: out.println("계정");
	break;
case -2: out.println("db");
	break;
}

bja.disconnect();
%>

</body>
</html>