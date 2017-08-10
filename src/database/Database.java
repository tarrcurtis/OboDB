package database;

import java.sql.*;

public class Database {

	private Connection c;

	public Database() {
		// TODO Auto-generated constructor stub
	}

	public void connect(String user, String pass, String host) throws ClassNotFoundException, SQLException {

		Class driver = Class.forName("com.mysql.jdbc.Driver");
		c = DriverManager.getConnection("jdbc:mysql://" + host + "/obodb?autoReconnect=true&useSSL=false", user, pass);
	}

	public void newEmployee(String name, String address, String nin, String bankNo, int salary, int deptNum)
			throws SQLException {
		Statement st = c.createStatement();
		st.executeUpdate(
				"INSERT INTO `employee` (`Name`, `DepartmentID`, `Address`, `NIN`, `BankNumber`, `StartingSalary`) "
						+ "VALUES ('" + name + "','" + deptNum + "','" + address + "','" + nin + "','" + bankNo + "',"
						+ salary + ")");
	}

	public void newSalesEmployee(String name, String address, String nin, String bankNo, int salary, int deptNum,
			float commission, int sales) throws SQLException {
		this.newEmployee(name, address, nin, bankNo, salary, deptNum);
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT ID FROM employee ORDER BY ID DESC LIMIT 1;");
		rs.next();
		int empID = Integer.parseInt(rs.getString("ID"));
		st.executeUpdate("INSERT INTO `salesEmployee` VALUES (" + empID + "," + commission + "," + sales + ")");
	}

	public void hrReport() throws SQLException {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT name FROM employee ORDER BY RAND() LIMIT 5");
		while (rs.next()) {
			String out = rs.getString("name");
			System.out.println(out);
		}
	}

	public void hrReportByDepartment(int deptID) throws SQLException {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT name FROM employee WHERE `DepartmentID` = " + deptID + ";");
		while (rs.next()) {
			String out = rs.getString("name");
			System.out.println(out);
		}
	}

	public void showAllDepartments() throws SQLException {
		Statement st = c.createStatement();
		ResultSet rs = st.executeQuery("SELECT `ID`, `Name` FROM department;");
		while (rs.next()) {
			String out = rs.getString("ID");
			String outName = rs.getString("Name");
			System.out.println(out + " - " + outName);
		}
	}

}
