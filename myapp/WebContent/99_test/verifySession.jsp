<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.sql.*, org.json.simple.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<%

 String QRY = request.getParameter("qry");
 String ID = "ID";
 String PW = "PW";
%>
<body>

<%
	
	session = request.getSession();
if(session == null){
	out.println("session : null");
}
	ID = session.getAttribute("id").toString();
	PW = session.getAttribute("pw").toString();
	out.println(ID+"/"+PW+"/"+QRY);

	

%>

</body>
</html>