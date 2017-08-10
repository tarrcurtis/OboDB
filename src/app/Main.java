package app;

import java.sql.SQLException;
import java.util.Scanner;

import database.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Database db = new Database();
		try {
			db.connect("root", "password");
		} catch (ClassNotFoundException e1) {
			System.out.println(e1.getMessage());
		} catch (SQLException e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("Please insert a new employee");
		System.out.println("name:");
		Scanner s = new Scanner(System.in);
		String name = s.nextLine();
		System.out.println("address:");
		String address = s.nextLine();
		System.out.println("nin:");
		String nin = s.nextLine();
		System.out.println("bank number:");
		String bankNo = s.nextLine();
		System.out.println("salary:");
		int salary = s.nextInt();
		
		try {
			db.newEmployee(name, address, nin, bankNo, salary);
			System.out.println("Employee added succesfully");
			db.hrReport();
		} catch (SQLException e3){
			System.out.println(e3.getMessage());
		}
	}

}
