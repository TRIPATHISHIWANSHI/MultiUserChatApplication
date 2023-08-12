package chatApp.users.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static  chatApp.users.utils.ConfigReader.getValue;
public interface CommonDAO {
//throw early and catch later
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName(getValue("Driver"));
		//step-2 :making a connection
		final String CONNECTION_STRING=getValue("CONNECTION_URL");
		
		final String USER_ID=getValue("USERID");
		final String PASSWORD=getValue("PASSWORD");
		Connection con=DriverManager.getConnection(CONNECTION_STRING,USER_ID,PASSWORD);
		if(con!=null) {
			System.out.println("CONNECTION CREATED...");
			
		}
		return con;
	}
	

}
