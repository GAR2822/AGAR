 //Jdbc App to count records from database table
//Team:a1
package ag.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Assg10thCountRecords {
	public static void main(String[ ] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
		//register driver for loading driver class
		Class.forName("oracle.jdbc.driver.OracleDriver");//throws exception so catch and handle
		//establish connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");//throws sqlException
		if(con!=null)//to avoid NullPointerExeption
		//create statement object
		st = con.createStatement();//throws sqlException
		String query = "select count(*) from student";//to display the no. of records//select query so use st.executeQuery
		System.out.println(query);
		//send & execute sql query
		if(st!=null) //to avoid NullPointerExeption
		 rs = st.executeQuery(query);//throws sqlException
		//process resultset (0 or 1 record)
		 if(rs!=null)//to avoid NullPointerExeption
		//if(rs.next()==true) 
			 rs.next() ;//as we know that this hold 1 or 0 record
			System.out.println("Total no. of records: "+rs.getInt("COUNT(*)"));
		 //calling record from which is holding  say COUNT(*) col_name & record value=count of records
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		//close jdbc objects
		finally {
			try {
				if(rs!=null)
				rs.close()	;//exception type SQLException
			}catch(SQLException se){
				se.printStackTrace();
			}
			
			try {
				if(st!=null)
				st.close();//exception type SQLException
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if(con!=null)
				con.close();//exception type SQLException
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}
}
 