<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, org.json.simple.*"%>

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

session = request.getSession();
session.setAttribute("id", ID);
session.setAttribute("pw", PW);
out.println(session.getId()+ID+PW);


%>

</body>
</html>