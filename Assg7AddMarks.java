//Jdbc app  to add given percentage ff marks to existing avg, 
//based on given 3 city names as the addresses from the student
package ag.nt.jdbc;

import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Assg7AddMarks {
	public static void main(String[ ] args) {
		Scanner s = null;
		Connection con = null;
		Statement st =null;
		try {
			//reading inputs
			s = new Scanner(System.in);
			String sCity1=null,sCity2=null,sCity3=null;
			float per = 0.00f;
			if(s!=null)//NullPointerException
			{
				System.out.println("Enter the Smarks Percentage  to be modified");
				per = s.nextFloat();//as per user
				System.out.println("Enter the SCity1:");
				sCity1 = s.next();//gives Ahmedabad
				System.out.println("Enter the SCity2:");
				sCity2 = s.next();//gives lucknow 
				System.out.println("Enter the SCity3:");
				sCity3 = s.next();// gives jaipur
			}
			sCity1 = "'"+sCity1+"'";//gives 'Ahmedabad'
			sCity2 = "'"+sCity2+"'";//gives 'lucknow '
			sCity3 = "'"+sCity3+"'";// gives 'jaipur'
			//Register driver by loading Jdbc driver class 
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing Connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
			//Prepare Statement obj;
			if(con!=null)//nullPointerException
				st= con.createStatement();
			//prepare sql Query 
			//"update studentdetails11 set smarks=smarks+(0.2*smarks) where scity in ('Ahmedabad','lucknow','jaipur') "
			String query = "update studentdetails11 set smarks=smarks+("+(per/100)+"*smarks) where scity in ("+sCity1+","+sCity2+","+sCity3+")";
			System.out.println(query);
			//executeQuery and update
			int k =0;
			if(st!=null)
				k = st.executeUpdate(query);
			if(k!=0 ) {
				System.out.println("Total No. of Records updated Successfully: "+k);
			}else {
				System.out.println("No Records Updated");
			}
		}catch(Exception se){
			se.printStackTrace();//prints indepth (all details of the Exception) 			
		}
		finally {
			try {
				if(st!=null)
					st.close();//checked exception //raised at compile time
			} catch (SQLException e) {//to handle checked exception //raised at compile time
				e.printStackTrace();
			}

			try {
				if(con!=null)
					con.close();//checked exception //raised at compile time
			} catch (SQLException e) { //checked exception //raised at compile time
				e.printStackTrace();
			}
			try {
				if(s!=null) 
					s.close();	//unchecked raised at ExecutionTime
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}
/*
 * ResultSet used for -executeQuery(Select Query)
 * ResultSet NOT Used for-(NonSelect Query)-UpdateQueries */
