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
String Pno = request.getParameter("pcode");
String Name =request.getParameter("name");
String Addr =request.getParameter("addr");
String Date =request.getParameter("date"); 
 

if(ID !=null){
	System.out.println("document: "+ID+"/"+Name+"/"+Pno+"/"+Date);
}	

	/* 
	 String ID="user1";
	String Pno="";
	String Name="김";
	String Addr="";
	String Date="20211101";   */
	
	bja.connect(); 

JSONObject jom =null;
if((Name!="" && Name!= null)&&(Date!="" && Date!= null)){
	jom =  bja.selectDocument(ID,"name",Name,Date);	
}
else if(Name!=""&&Name!= null){
	 jom =  bja.selectDocument(ID,"name",Name,Date);
}else if(Addr!=""&&Addr!= null){
	 jom =  bja.selectDocument(ID,"addr",Addr ,Date);
}else if(Pno!=""&&Pno!= null){
	 jom =  bja.selectDocument(ID,"pno",Pno ,Date);
}

if(jom ==null){
	System.out.println("jom if error");
	out.println("error");	
}else{

	String main = jom.get("main").toString();
	
	if(main=="error"){
		System.out.println("main if error");
		out.println(main);	
	}
	else{
		out.println(jom.toJSONString());
	}
} 

bja.disconnect();
%>
</body>
</html>