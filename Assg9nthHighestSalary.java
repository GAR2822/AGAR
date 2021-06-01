 //Jdbc app to get employee details who is having nth highest salary(select query) from emp table
// author::T1
package ag.nt.jdbc;

import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Assg9nthHighestSalary {
	public static void main(String[ ] args) {
		Scanner s=null;
		Connection con=null;
		Statement st = null;
		ResultSet rs = null;
		try {
			s = new Scanner(System.in);
			if(s!=null)//null pointer exception
			System.out.println("Enter the nth Highest salary");
			float nthHighestSal =+(Float.parseFloat(s.nextLine()));
			
			//register driver for Loading Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");//jdbc:oracle:thin:@localhost:1521:xe","system","123
			//establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
			if(con!=null)//to avoid NullPointerException
			st =con.createStatement();
			String query  ="select * from(select ename, sal, dense_rank() over(order by sal desc)r from emp ) where r="+nthHighestSal; 
			if(st!=null)//to avoid NullPointerException
			System.out.println(query);
			System.out.println("The employee with "+nthHighestSal+"th highest sal: ");
			rs = st.executeQuery(query);	
		    if(rs.next()==true) {
		    	System.out.println(rs.getString(1)+"   "+rs.getString(2)+"   "+rs.getString(3));
		    }
		    else {
		    	System.out.println("No records");
		    }
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null)
					 rs.close();//Unhandled exception type SQLException
			}catch(SQLException se) {
				se.printStackTrace();
			}
			
			try{
				if(st!=null)
					st.close();//Unhandled exception type SQLException//Checked Exception@compile time
			}catch(	SQLException se) {
				se.printStackTrace();
			}
				
				try{
					if(con!=null)
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if(s!=null)
						s.close();	//unchecked Exception@execution time
				}catch(Exception e) {
					e.printStackTrace();
				}
				
				
		}
	}
}
/*
 * ResultSet used for -executeQuery(Select Query)
 * ResultSet NOT Used for-(NonSelect Query)-UpdateQueries */
