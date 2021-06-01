package ag.nt.jdbc;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
 
/*Jdbc application to get student details from studentDetails11 Table based on given studentCity(3 No's) 
 * version::
 * author::T1*/

public class Assg2StudentCity {//GetEmpDetails
public static void main(String[ ] args){

	Scanner s = null;//Scanner is a Stream
	//To get anything from Inside the block @end code define that first outside the block
	String City1=null;//same as above
	String City2=null;//same as above 
	String City3=null;//same as above 
	Statement st = null;//-->As st is Inside the If() {  ....}
	ResultSet rs =null;
	Connection con = null; 
	String query=null;
	try {
		s=new Scanner(System.in);
		if(s!=null){//to avoid NullPointerException
			System.out.println("Enter City1:" );
			City1=s.nextLine()  ;
			System.out.println("Enter City2:" );
			City2=s.nextLine() ;
			System.out.println("Enter City3:" );
			City3=s.nextLine()  ;
		}//convert input values as required for sql query(Only col values are CaseSensitive)
		City1="'"+City1+"'";
		City2="'"+City2+"'";
		City3="'"+City3+"'";
		 //loading Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","123");
		//Class.staticMethods-->NoNeed of null check 
		if(con!=null)//as con is ref var-->forCallingNonStaticMethods-->so nullCheck//////to avoid NullPointerException
		st = con.createStatement();//createStatement is nonStactic method as we are calling with Reference Var
		 
		//prepare sql query
		//SELECT SID,SNAME,SMARKS,SCITY FROM STUDENTDETAILS11 WHERE SCITY IN(City1,City2,City3)ORDER BY SCITY
		query ="SELECT SID,SNAME,SMARKS,SCITY FROM STUDENTDETAILS11 WHERE SCITY IN("+City1+","+City2+","+City3+")ORDER BY SCITY";
		System.out.println(query);
		
		//send and execute sql query in DB s/w
		if(st!=null)//to avoid NullPointerException
		rs = st.executeQuery(query);
		
		if(rs!=null) {//to avoid NullPointerException
			System.out.println("Student City Details are: ");
			while(rs.next()==true) {
				System.out.println(rs.getString(1)+"  "+rs.getString(2)+"     "+rs.getString(3)+"   "+rs.getString(4));
			}
		}
		
	}catch(SQLException se) {//to handle known exceptions
		System.out.println(se.toString()); //give elaborate details about exception
	}catch(Exception e) {// to handle unknown exceptions
		e.printStackTrace();
	}
	finally {
		//to close jdbc & stram objs
		//but here close() throws checked exception so catch and handle checked exception using try
		//we can put all close() in one try bt if the exception is at 1st (say rs.close()) then next steps are skipped so the reason separate Try Blocks
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
		try {
			if(s!=null) 
				s.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
}
