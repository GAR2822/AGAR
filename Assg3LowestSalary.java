package ag.nt.jdbc;

//Jdbc App to get Employee Details who is having Lowest Salary 
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class Assg3LowestSalary {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		 
		 
		try {
		//loading Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");//throws java.lang.ClassNotFoundException
		//Establishing Connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");/* throws java.sql.SQLException*/
		
		//Jdbc Statement Object creating
		if(con!=null)
		st = con.createStatement();/* throws java.sql.SQLException*/
		//Prepare SQL Query
		String query = "SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP)";
		//java.sql.SQLSyntaxErrorException
		System.out.println(query+"\n");
		
		//send & execute SQL Query
		if(st!=null)
		rs = st.executeQuery(query);
		
		if(rs!=null) { 
		System.out.println("Employee Details who is having Lowest Salary is: ");
		if(rs.next()) {
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4)+" "+rs.getInt(5));
			System.out.println("Salary: "+rs.getFloat(4));
				}//if
			}//if
		}catch(SQLException se) {//to handle know exceptions
		 se.printStackTrace();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {//close JDBC objs
			try {
				if(rs!=null)
					rs.close();
				}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null) 
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}		
		}
	}
}
