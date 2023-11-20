package cafepj;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB_connect {
	
	public static Connection makeConnection()
    {
          String url = "jdbc:mysql://localhost/cafe?serverTimezone=UTC";
          String id = "root";
          String password = "root";
          Connection con = null;
          try {
                 Class.forName("com.mysql.cj.jdbc.Driver");
                 con = DriverManager.getConnection(url, id, password);
          } catch (ClassNotFoundException e) {
        	  e.printStackTrace();
          } catch (SQLException e) {
        	  e.printStackTrace();
          }
          return con;
    }
} 