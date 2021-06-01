package ag.nt.jdbc;
//Jdbc app to hike empSalary basedd on given % for employess whose salary is in range 

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;


public class Assg6UpdateEmpSalary {
	public static void main(String[ ] args) {
		Scanner s = null;
		float percentage=0.00f;
		float sRange = 0.00f;
		float eRange = 0.00f;
		Connection con=null;
		Statement st = null;
		try {
			s = new Scanner(System.in);
			if(s!=null) {
				System.out.println("Enter Percentage of Salary Increment");
				percentage =s.nextInt();
				System.out.println("Increment to the Employees with Salary Range:");
				System.out.println("Enter the Start Range value: ");
				sRange=s.nextFloat();
				System.out.println("Enter the End Range value: ");
				eRange=s.nextFloat();
			}
			percentage =(percentage/100); 

			//Register driver by loading JDBC DriverClass
			//Class.forName("jdbc.oracle.driver.OracleDriver");
			//Establish connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
			//create Statement obj
			if(con!=null)
				st = con.createStatement();
			//write query
			////String query ="update emp set sal=sal+sal*0.1 where sal between 1500 and 2500";
			//execute query
			String query ="update emp set sal=sal+(sal*"+percentage+") where sal between "+sRange+" and "+ eRange;
			System.out.println(query);
			int k = 0;
			if(st!=null)
				k = st.executeUpdate(query);
			if(k==0) {
				System.out.println("No Salaries updated");
			}else {
				System.out.println("No. of Salary updated: "+k);
			}
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {// to handle unknown exceptions
			e.printStackTrace();
		}
		finally {

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
/*
 * ResultSet used for -executeQuery(Select Query)
 * ResultSet NOT Used for-(NonSelect Query)-UpdateQueries */
