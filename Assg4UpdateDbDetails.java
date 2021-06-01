package ag.nt.jdbc;

//Jdbc App to modify student name,avg & add based on given student number(Sno)
import java.util.Scanner;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class Assg4UpdateDbDetails {
	public static void main(String[] args) {
		Scanner s = null;
		String sNo=null;
		String sName=null;
		String sAdd=null;
		Float sAvg=0.00f;
		String query = null;
		Connection con=null;
		Statement st = null;
		try {
			s = new Scanner(System.in);
			if(s!=null) {
				System.out.println("Enter the student number SNo:" );
				sNo=s.nextLine();
				System.out.println("Enter the student SName:" );
				sName=s.nextLine();
				System.out.println("Enter the student SAdd:" );
				sAdd=s.nextLine();
				System.out.println("Enter the student SAvg:" );
				sAvg=s.nextFloat();
			}
			 sNo="'"+sNo+"'";
			sName="'"+sName+"'";
			sAdd="'"+sAdd+"'";
			sAvg= sAvg;
			//Register driver by loading JDBC DriverClass
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//Establishing connection b/w app & db(store) s/w
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
			//create Statement object
			if(con!=null)//null pointerException
				st = con.createStatement();
			//Prepare the query
			//update student set sname='abj mk',sadd='adf25',avg=25.32 where sno=251; 
			query = "UPDATE STUDENT SET SNAME="+sName+",SADD="+sAdd+",AVG="+sAvg+" WHERE SNO="+sNo; 
			
			//Send & execute the query in db s/w
			int count=0;
			if(st!=null)//nullPointerException
			count = st.executeUpdate(query);
			if(count!=0) {
				System.out.println("Details Updated Updated Successfully,No of Record: "+count);
			}else {
				System.out.println("Not Updated");
			} 
		}catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999) 
				System.out.println("Invalid Col Name, Table Names or SQL keywords");	
			else if(se.getErrorCode()==12899) 
				System.out.println("Do not Insert more than Col size Data to Sname,Sadd,Savg cols");
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			//closing jdbc apps
			try {
				if(st!=null) 
					st.close();//throws exception
			}catch(SQLException se) {
				se.printStackTrace();
			}try {
				if(con!=null)
					con.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if(s!=null)
					s.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
