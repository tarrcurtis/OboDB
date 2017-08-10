package app;

import java.sql.SQLException;
import java.util.Scanner;
import database.*;

public class UI {
	
	private Scanner s;
	private Database db;

	public UI() {
		s = new Scanner(System.in);
		db = new Database();
	}
	
	public void login() {
		System.out.println("Please log in:");
		System.out.println("Username:");
		String user = s.nextLine();
		System.out.println("Password:");
		String pass = s.nextLine();
		try {
			db.connect(user, pass, "localhost");
			this.menu();
		} catch (ClassNotFoundException e1) {
			
		} catch (SQLException e2) {
			try {
				db.connect(user, pass, "192.168.1.201");
				this.menu();
			} catch (ClassNotFoundException e3) {
				
			} catch (SQLException e4) {
				try {
					db.connect(user, pass, "192.168.1.102");
					this.menu();
				} catch (ClassNotFoundException e5) {
					
				} catch (SQLException e6) {
					System.out.println("Login failed");
					this.login();
				}
			}
		}
	}
	
	private void menu() {
		System.out.println();
		System.out.println("Please select an operation");
		System.out.println("1. New employee");
		System.out.println("2. HR report");
		int x = s.nextInt();
		
		switch (x) {
		case 1:
			this.newEmployee();
			break;
		case 2:
			this.hrReport();
			break;
		default:
			System.out.println("Invalid option");
			this.menu();
		}
	}
	
	private void newEmployee() {
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
		
		this.menu();
	}
	
	private void hrReport() {
		
	
		this.menu();
	}

}
