<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, org.json.simple.*"%>
<jsp:useBean id="bja" class="beans.BeanDBC" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<%
request.setCharacterEncoding("utf-8");
String ID = request.getParameter("id");
String PW = request.getParameter("pw");
%>
<body>

<%

out.println(ID+"/"+PW);
System.out.println(ID+"/"+PW);

%>

</body>
</html>