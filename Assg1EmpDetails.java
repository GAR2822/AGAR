package ag.nt.jdbc;
/*Jdbc app tp get emp table details based on given deptno
 * author::T2
 * */
 import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
public class Assg1EmpDetails {//GetEmpDetails
@SuppressWarnings("resource")
public static void main(String[ ] args){
Scanner s =null;	
int dpt1 = 0;
int dpt2 = 0;
int dpt3 = 0;
Connection con=null;
Statement st=null;
ResultSet rs =null;
String query=null;
try {
s = new Scanner(System.in);
if(s!=null) {
System.out.println("Enter Dpt1: ");
dpt1 = s.nextInt();
System.out.println("Enter Dpt2: ");
dpt2 = s.nextInt();
System.out.println("Enter Dpt3: ");
dpt3 = s.nextInt();
}
 
	Class.forName("oracle.jdbc.driver.OracleDriver");
	con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
	if(con!=null)
	st = con.createStatement();
	query = "SELECT EMPNO,ENAME,JOB,HIREDATE,SAL,DEPTNO FROM EMP WHERE DEPTNO IN("+dpt1+","+dpt2+","+dpt3+")ORDER BY DEPTNO";
	System.out.println(query);
	if(st!=null)	
	rs = st.executeQuery(query);
	if(rs!=null)
	System.out.println("Emp Details based on given DptNo are:");
	while(rs.next()==true) {
		System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"      "+rs.getString(3)+"       "+rs.getDate(4)+"    "+rs.getFloat(5)+"    "+rs.getInt(6));
	}   
}catch(SQLException se) {//to handle known exceptions
	System.out.println(se.toString()); //give elaborate details about exception
}catch(Exception e) {// to handle unknown exceptions
	e.printStackTrace();
}
finally {
	try {
		if(rs!=null)
		rs.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		if(st!=null)
		st.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		if(con!=null)
		con.close();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	try {
		if(s!=null) 
			s.close();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
}
	}
}

