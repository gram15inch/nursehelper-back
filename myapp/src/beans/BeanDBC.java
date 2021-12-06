package beans;
import java.sql.*;
import javax.sql.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.naming.*;

public class BeanDBC {
	
	  Connection conn =null;
	  Statement st=null;
	  PreparedStatement ps=null;
	  ResultSet rs=null; 


	public void connect() {
		 try {
			 Context ic = new InitialContext();
			 Context ec = (Context) ic.lookup("java:/comp/env");
			 DataSource ds = (DataSource) ec.lookup("jdbc/android");
			 conn = ds.getConnection();
			 //st = conn.createStatement();
			 } catch (Exception e) { e.printStackTrace(); }
	 }
	public void disconnect() {
		 try {
			 if (conn != null) { rs.close();  ps.close(); conn.close();
			 //st.close();
			 }
			 } catch (SQLException e) { e.printStackTrace(); }

	 }
	

	public int idCheck(String id) {
		String u0 = "use android";
		String sql = "SELECT id FROM USER WHERE ID = ?";
		
		
		try {
		
			ps = conn.prepareStatement(u0);
			ps.executeUpdate();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(id)) 				
					return 1; // id중복
				else
					return 0; // id없음
						
			}else
				return -1; // id없음
			
			
		}catch(Exception e) { System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
		  System.out.println(e);
		  return -2; // db오류
		  }
		  
	
	}
	
	public int resist(String id, String pw, String name, String sex, String pn) {
		int no = createUser(id,pw,name,sex,pn);
		
		if(no>0) {//유저생성이 정상적으로 될경우	
			return createDB(id);
		}else
			return no;

	}
	public void withdrawal(String id) {
		int no = deleteUser(id);
		if(no>0) {//유저 삭제가 정상적으로 될경우
			deleteDB(id);
		}
		
	}
	
	public int createDB(String id) {
			String u0 = "use android";
			String DBname = "DB"+id;
			try {
			
				 String d0 = "DROP DATABASE IF EXISTS "+DBname ; 
				 String c0 = "CREATE DATABASE "+DBname ;
				 String u1 = "USE "+ DBname;
	
				 String d1 = "DROP TABLE IF EXISTS patients";
				 String c1 = "CREATE TABLE patients ("
					+"no INT NOT NULL AUTO_INCREMENT COMMENT '환자번호',"
					+"name VARCHAR(10) NOT NULL COMMENT '이름',"
					+"sex VARCHAR(5) NOT NULL COMMENT '성별',"
					+"dom VARCHAR(10) NOT NULL COMMENT '생년월일',"
					+"addr VARCHAR(100) NOT NULL COMMENT '주소',"
					+"PRIMARY KEY (no)"
					+") COMMENT='환자목록'";
	
				 String d2 = "DROP TABLE IF EXISTS product";
				 String c2 = "CREATE TABLE product ("
						 +"no INT NOT NULL AUTO_INCREMENT COMMENT '물품번호',"
						 +"name VARCHAR(20) NOT NULL COMMENT '물품명',"
					+"type VARCHAR(10) NOT NULL COMMENT '종류',"
					+"mfg VARCHAR(20) COMMENT '제조사',"
					+"spec1 VARCHAR(20) NOT NULL COMMENT '규격1',"
					+"spec2 VARCHAR(20) COMMENT '규격2',"
					+"stock INT NOT NULL DEFAULT 0 COMMENT '재고',"
					+"PRIMARY KEY (no)"
					+") COMMENT='물품목록'";
	
				 String d3 = "DROP TABLE IF EXISTS schedule" ;
				 String c3 = "CREATE TABLE SCHEDULE ("
						 +"sno INT NOT NULL AUTO_INCREMENT COMMENT '일정번호'," 
						 +"no INT NOT NULL COMMENT '환자번호', "
					+"sdate VARCHAR(20) NOT NULL COMMENT '시작날짜',"
					+"edate VARCHAR(20) NOT NULL COMMENT '끝날짜',"
					+"color VARCHAR(20) COMMENT '색상',"
					+"PRIMARY KEY (sno)"
					+") COMMENT='일정'";
				 
				 
				 
				 

				 String d4 = "DROP TABLE IF EXISTS document" ;
				 String c4 = "CREATE TABLE document ("
						 +"dno INT NOT NULL AUTO_INCREMENT COMMENT '문서번호', " 
						 +"type INT NOT NULL COMMENT '문서종류', "
					+"pno INT NOT NULL COMMENT '환자번호',"
					+"date VARCHAR(20) NOT NULL COMMENT '진료날짜',"
					+"memo VARCHAR(2048) COMMENT '메모',"
					+"PRIMARY KEY (dno)"
					+") COMMENT='문서목록'";
			
				 
				 
				 ps = conn.prepareStatement(u0);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(d0);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(c0);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(u1);
				 ps.executeUpdate();
				 
				 ps = conn.prepareStatement(d1);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(c1);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(d2);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(c2);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(d3);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(c3);
				 ps.executeUpdate();
				 
				 ps = conn.prepareStatement(d4);
				 ps.executeUpdate();
				 ps = conn.prepareStatement(c4);
				 ps.executeUpdate();
				 
				 return 1;
				 }catch (Exception e) {
					 System.out.println(e);
					 return -2;
				 }
		}
	public void deleteDB(String id) {
		String u0 = "use android";
		String DBname = "DB"+id;
		try {
		
			ps = conn.prepareStatement(u0);
			 ps.executeUpdate();
			 String d0 = "DROP DATABASE IF EXISTS "+DBname ; 
			
			 ps = conn.prepareStatement(d0);
			 ps.executeUpdate();
		
			 
			 }catch (Exception e) {
				 System.out.println(e);
			 }
	}

	public int createUser(String id, String pw, String name, String sex, String pn) {
		try {
				String u0 = "use android";
				ps = conn.prepareStatement(u0);
				ps.executeUpdate();
				ps = conn.prepareStatement("INSERT INTO " +
				"user(ID, PW, NAME, SEX, PN) VALUES(?,?,?,?,?)");
				
				ps.setString(1, id);
				ps.setString(2, pw);
				ps.setString(3, name);
				ps.setString(4, sex);
				ps.setString(5, pn);
				ps.executeUpdate();
					 	  					
		  		
		  		return 1;
		  			
			  }catch(Exception e) { System.out.println("user생성을 실패하거나, SQL문이 틀렸습니다.");
			  System.out.println(e);
			  return -1;}
		
	}
	public int deleteUser(String id) {
			
			try {
				String u0 = "use android";
				ps = conn.prepareStatement(u0);
				ps.executeUpdate();
				ps = conn.prepareStatement("SELECT NO FROM user WHERE id=?");
				ps.setString(1, id);
		  		rs= ps.executeQuery();
		  					 
							  
				return 1;
				
		  		
		  	
		  			
			  }catch(Exception e) { System.out.println("user삭제를 실패하거나, SQL문이 틀렸습니다.");
			  System.out.println(e); 
			  return -1;}
			
			
		}
	
	public int verifyUser(String id,String pw) {
		
		String u0 = "use android";
		String sql = "SELECT PW FROM USER WHERE ID = ?";
		
		
		try {
		
			ps = conn.prepareStatement(u0);
			rs = ps.executeQuery();
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				if(rs.getString(1).equals(pw)) 				
					return 1; // 비밀번호 맞음
				else
					return 0; // 비밀번호 틀림
						
			}else
				return -1; // id없음
			
			
		}catch(Exception e) { System.out.println("DB연결 실패하거나, SQL문이 틀렸습니다.");
		  System.out.println(e);
		  return -2; // db오류
		  }
		  
		
	}
	
	public JSONObject selectSchedule(String id,String date) {
		JSONObject jom = new JSONObject();
	 	JSONArray jArray = new JSONArray();
	 	
		String dbname = "db"+id;
		 try {

			 	String u0 = "use "+dbname;
			 	ps = conn.prepareStatement(u0);		
		  		rs= ps.executeQuery();
		  		
			 	String s0 = "SELECT sno,name,addr,sex,dom,sdate,edate,color "
			 			+ "FROM patients as p "
			 			+ "INNER JOIN schedule as s ON (p.no= s.no) "
			 			+ "WHERE sdate LIKE ?";
		  		ps = conn.prepareStatement(s0);
		  		ps.setString(1, date+"%");
		  		rs= ps.executeQuery();
		  		
		  		int index = 0;
		  		while(rs.next()){
		  			JSONObject jo = new JSONObject();
		  			jo.put("idcode",rs.getString("sno"));
		  			jo.put("name",rs.getString("name"));
		  			jo.put("addr",rs.getString("addr"));
		  			jo.put("sex",rs.getString("sex"));
		  			jo.put("dom",rs.getString("dom"));
		  			jo.put("sdate",rs.getString("sdate"));
		  			jo.put("edate",rs.getString("edate"));
		  			jo.put("color",rs.getString("color"));
		  			jArray.add(index,jo);
		  			index++;
		  		}
		  		
		  		jom.put("main", jArray);
		  		
		  
		  
	  }catch(Exception e) {
		  System.out.println(e);
		  System.out.println("main error");
		  jom.put("main","error");
		  return jom;
	  }
		
		return jom;
		
	}
	public int deleteSchedule(String id,String sno) {
		
		String DBname = "DB"+id;
		String u0 = "use "+DBname;
		
		try {
		
			ps = conn.prepareStatement(u0);
			 ps.executeUpdate();
			 String d0 = "DELETE from schedule WHERE sno = ?"; 
			
			 ps = conn.prepareStatement(d0);
			 ps.setInt(1, Integer.parseInt(sno));
			 ps.executeUpdate();
		
			 return 1;
			 }catch (Exception e) {
				 System.out.println("일정삭제를 실패하거나, SQL문이 틀렸습니다.");
				 System.out.println(e);
				 return -1;
			 }
		
	}
	public int inUpdateSchedule(String id, String sno,String pno, String sdate, String edate, String color) {
		
		String dbname = "db"+id;
		 try {

			 	String u0 = "use "+dbname;
			 	ps = conn.prepareStatement(u0);		
		  		rs= ps.executeQuery();
		  		
			 	String s0 = "SELECT s.sno AS scode " + 
			 			" FROM schedule AS s " + 
			 			"WHERE (s.sno = ?) ";
		  		ps = conn.prepareStatement(s0);
		  		ps.setInt(1,Integer.parseInt(sno));
		  		rs= ps.executeQuery();
		  		
		  		if(rs.next()){//값이 있을경우
		  				String up0 = "update schedule " + 
		  						"SET " + 
		  						" sdate = ? " + 
		  						" edate = ? " + 
		  						" color = ? " + 
		  						"WHERE sno =? ";
				  		ps = conn.prepareStatement(up0);
				  		
				  		ps.setString(1, sdate);
				  		ps.setString(2, edate);
				  		ps.setString(3, color);
				  		ps.setInt(4,Integer.parseInt(sno));
				  		ps.executeUpdate();
		  			
		  				return 1;

		  		}else {//값이 없을경우
	  				
	  				String in0 = "INSERT INTO schedule(pno,sdate,edate,color) VALUES(?,?,?,?) ";
			  		ps = conn.prepareStatement(in0);
			  		ps.setInt(1,Integer.parseInt(pno));
			  		ps.setString(2, sdate);
			  		ps.setString(3, edate);
			  		ps.setString(4, color);
			  		ps.executeUpdate();
	  				return 1;
	  			}
		  			  
	  }catch(Exception e) {
		  System.out.println(e);
		  System.out.println("inUpDoc sql error");
		  return -2;
	  }
	}
	
	public JSONObject selectDocument(String id,String col,String value,String date) {
		
		JSONObject jom = new JSONObject();
	 	JSONArray jArray = new JSONArray();
	 	
		String dbname = "db"+id;
		 try {

			 	String u0 = "use "+dbname;
			 	ps = conn.prepareStatement(u0);		
		  		rs= ps.executeQuery();
		  		
			 	String s0 = "SELECT d.dno AS dcode, p.no AS pcode,name,addr,sex,dom,date,memo "
			 			+ "FROM patients as p "
			 			+ "INNER JOIN document as d ON (p.no= d.pno) "
			 			+ "WHERE ("+col+" LIKE ?) AND (date >= ?)  "
			 			+ "ORDER BY name, date ASC";
			 	
		  		ps = conn.prepareStatement(s0);
		  		ps.setString(1, "%"+value+"%");
		  		ps.setString(2, date);
		  		rs= ps.executeQuery();
		  		
		  		int index = 0;
		  		while(rs.next()){
		  			JSONObject jo = new JSONObject();
		  			jo.put("dcode",rs.getString("dcode"));
		  			jo.put("pcode",rs.getString("pcode"));
		  			jo.put("name",rs.getString("name"));
		  			jo.put("addr",rs.getString("addr"));
		  			jo.put("date",rs.getString("date"));
		  			jo.put("sex",rs.getString("sex"));
		  			jo.put("dom",rs.getString("dom"));
		  			jo.put("memo",rs.getString("memo"));	
		  			jArray.add(index,jo);
		  			index++;
		  		}
		  		
		  		jom.put("main", jArray);
		  		
		  
		  
	  }catch(Exception e) {
		  System.out.println(e);
		  System.out.println("main error");
		  jom.put("main","error");
		  return jom;
	  }
		
		return jom;
		
	}
	public int inUpdateDocument(String id,String type, String pno, String date,String memo) {
		
	 	
		String dbname = "db"+id;
		 try {

			 	String u0 = "use "+dbname;
			 	ps = conn.prepareStatement(u0);		
		  		rs= ps.executeQuery();
		  		
			 	String s0 = " SELECT d.dno AS dcode,type,DATE  " + 
			 			" FROM document as d  " + 
			 			"WHERE (d.pno = ?) AND (d.type = ?) AND (d.date = ?) ";
		  		ps = conn.prepareStatement(s0);
		  		ps.setInt(1,Integer.parseInt(pno));
		  		ps.setInt(2, Integer.parseInt(type));
		  		ps.setString(3, date);
		  		rs= ps.executeQuery();
		  		
		  		if(rs.next()){//값이 있을경우
		  				String up0 = "update document " + 
		  						"SET " + 
		  						" memo = ? " + 
		  						"WHERE DATE =? AND TYPE =? AND pno =? ;";
				  		ps = conn.prepareStatement(up0);
				  		ps.setString(1, memo);
				  		ps.setString(2, date);
				  		ps.setInt(3,Integer.parseInt(type));
				  		ps.setInt(4,Integer.parseInt(pno));
				  		ps.executeUpdate();
		  			
		  				return 1;

		  		}else {//값이 없을경우
	  				
	  				String in0 = "INSERT INTO document(TYPE,pno,DATE,memo) VALUES(?,?,?,?) ";
			  		ps = conn.prepareStatement(in0);
			  		ps.setInt(1,Integer.parseInt(type));
			  		ps.setInt(2,Integer.parseInt(pno));
			  		ps.setString(3, date);
			  		ps.setString(4, memo);
			  		ps.executeUpdate();
	  				return 1;
	  			}
		  			  
	  }catch(Exception e) {
		  System.out.println(e);
		  System.out.println("inUpDoc sql error");
		  return -2;
	  }
	}
	
	
	public int insertUserDB(String id, String table ,String cols, String values) {
		
		
		String[] colList = cols.split(",");
		String[] valueList = values.split(",");
		if(colList.length != valueList.length)
			return -2;
		
		String qstr="";
		for(int i =0; i<colList.length;i++)
			qstr +="?,";
		qstr=qstr.substring(0, qstr.length()-1);
		
		try {
			
			String u0 = "USE "+ "DB"+id;
			ps = conn.prepareStatement(u0);
			ps.executeUpdate();
			
			
	    	ps = conn.prepareStatement("INSERT INTO " +
					 table +"("+cols+") VALUES("+qstr+")");
	    	for(int i =0; i<colList.length;i++)
	    		ps.setString(i+1, valueList[i].toString());
			ps.executeUpdate();
			
				
		  	return 1;
			  }catch(Exception e) { System.out.println("user생성을 실패하거나, SQL문이 틀렸습니다.");
			  System.out.println(e);
			  return -1;
			  }
	}
	public int deleteUserDB(String id,String table,String col, String where) {
		try {
				
				String u0 = "USE "+ "DB"+id;
				
				ps = conn.prepareStatement(u0);
				ps.executeUpdate();
				
				String d0 = "delete from "+ table +" where "+ col +" =" +where;
		    	ps = conn.prepareStatement(d0);
				ps.executeUpdate();
				
					
			  	return 1;
			  }catch(Exception e) { System.out.println("userDB삭제를 실패하거나, SQL문이 틀렸습니다.");
			  System.out.println(e);
			  return -1;
			  }
		}
	
	public int updateUserDB(String id,String table,String cols, String values, String where) {
		return 1;
	}
	

}
