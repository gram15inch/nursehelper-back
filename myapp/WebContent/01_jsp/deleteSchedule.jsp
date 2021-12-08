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
String ID =request.getParameter("id");
String Sno = request.getParameter("sno"); 

bja.connect();

%>

<body>
<% 
  
/*   String ID="user1";
 String Sno="50"; 
  */
 if(ID !=null)
		System.out.println("schedule: "+ID+"/"+Sno); 

switch(bja.deleteSchedule(ID, Sno)) { 
case 1:  
	out.println("성공");
     break;
case -1: out.println("실패");
	break;
default: out.println("일정삭제: 예외 error");
	break;
}

bja.disconnect2();
%>
</body>
</html>