package database;

import java.sql.*;

public class Database {
	
	private Connection c;

	public Database() {
		// TODO Auto-generated constructor stub
	}
	
	public void connect(String user, String pass) throws ClassNotFoundException, SQLException {
		
		Class driver = Class.forName("com.mysql.jdbc.Driver");
        c = DriverManager.getConnection("jdbc:mysql://localhost/obodb?autoReconnect=true&useSSL=false", user, pass);
	}
	
	public void newEmployee(String name, String address, String nin, String bankNo, int salary) throws SQLException {
		Statement st = c.createStatement();
		st.executeUpdate("INSERT INTO `employee` (`Name`, `Address`, `NIN`, `Bank Number`, `Starting Salary`) "
				+ "VALUES ('" + name + "','" + address + "','" + nin + "','" + bankNo + "'," + salary +")");
	}
	
	public void hrReport() throws SQLException {
      Statement st = c.createStatement();
      ResultSet rs = st.executeQuery("SELECT name FROM employee ORDER BY RAND() LIMIT 5");
      while (rs.next()) {
          String out = rs.getString("name");
          System.out.println(out);
      }
	}
	
}
