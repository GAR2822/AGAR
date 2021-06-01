package ag.nt.jdbc;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Assg5DeleteRecord {
	public static void main(String[ ] args) {
		Scanner s = null;	
		Connection con = null;
		Statement st = null;

		String bCode=null;
		try {
			//read Inputs
			s = new Scanner(System.in);
			if(s!=null) {
				System.out.println("Enter BCode to be delete:");
				bCode = s.next().toUpperCase();
			}
			bCode = "'"+bCode+"'";
			//Register driver by loading JDBC DriverClass
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
			if(con!=null)//raises nullPinterException
				st = con.createStatement();
			//prepare sql query
			//DELETE FROM BOOK31 WHERE BCODE ='bCode';
			String query = "DELETE FROM BOOK31 WHERE BCODE ="+bCode;
			System.out.println(query);
			//send & execute sql query in db s/w

			int update = 0; 
			if(st!=null)//raises nullPinterException
				update=st.executeUpdate(query);
			if(update!=0) {
				System.out.println("Deleted Successfully");
				update++;
			}else {
				System.out.println("BCode Not available");
			}
		}catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Invalid ColNames or table Names or SQL keywords");
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//closing jdbc objs
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
			try {
				if(s!=null)
					s.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
/*
 * ResultSet used for -executeQuery(Select Query)
 * ResultSet NOT Used for-(NonSelect Query)-UpdateQueries */
