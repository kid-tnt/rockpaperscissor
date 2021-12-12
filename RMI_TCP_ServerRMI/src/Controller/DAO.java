package Controller;
 
import java.io.File;

import java.sql.Connection;
import java.sql.DriverManager;


 
public class DAO {
	protected static Connection con;
	//contructor
	public DAO () {
		if(con ==null) {
			String dbUrl="jdbc:mysql://localhost:3306/gametest"; 
			String dbClass="com.mysql.jdbc.Driver";
			try {
				Class.forName(dbClass);
				con=DriverManager.getConnection(dbUrl,"root","NgoVanThangb18dcat240"); 
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
