<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="bja" class="beans.BeanDBC" scope="session"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<% 
session = request.getSession();
bja.connect();
bja.createDB("user10");
bja.disconnect2(); 
%>
</body>
</html>