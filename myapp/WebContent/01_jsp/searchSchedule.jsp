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
String ID =request.getParameter("id");
String Date =request.getParameter("date");

bja.connect();

if(ID !=null){
	System.out.println(ID+"/"+Date);
}
JSONObject jom =  bja.selectSchedule(ID, Date);
String main = jom.get("main").toString();

if(main=="error"){
	System.out.println("main if error");
	out.println(main);	
}
else
	out.println(jom.toJSONString());

bja.disconnect();
//bja.insertUserDB("user5", "patients", "name,sex,dom", "name,M,19300102");
//bja.deleteUserDB("user5", "patients", "no", "1");
%>
</body>
</html>