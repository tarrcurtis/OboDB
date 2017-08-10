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
//		char passArray[] = System.console().readPassword("Pasword:");
//		String pass = new String(passArray);
		String pass = s.nextLine();
		try {
			db.connect(user, pass, "192.168.1.215");
			System.out.println("c server: ");
			this.menu();
		} 
		catch (ClassNotFoundException e1) {
			
		} 
		catch (SQLException e2) {
			try {
				db.connect(user, pass, "192.168.1.201");
				System.out.println("n server: ");
				this.menu();
			} catch (ClassNotFoundException e3) {
				
			} catch (SQLException e4) {
				try {
					db.connect(user, pass, "192.168.1.102");
					System.out.println("d server: ");
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
		System.out.println("2. New sales employee");
		System.out.println("3. HR report");
		System.out.println("4. Finance report");
		int x = s.nextInt();
		
		switch (x) {
		case 1:
			this.newEmployee();
			break;
		case 2:
			this.newSalesEmployee();
		case 3:
			this.hrReport();
			break;
		case 4:
			this.financeReport();
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
		System.out.println("Which department is " + name + "in? Select integer value: ");
		try{
		db.showAllDepartments();
		}
		catch (SQLException e1){
			System.out.println(e1.getMessage());
		}
		
		int dept = s.nextInt();
		
		try {
			db.newEmployee(name, address, nin, bankNo, salary, dept);
			System.out.println("Employee added succesfully");
		} catch (SQLException e2){
			System.out.println(e2.getMessage());
		}
		
		this.menu();
	}
	
	private void newSalesEmployee() {
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
		System.out.println("Which department is " + name + " in? Select integer value: ");
		try{
		db.showAllDepartments();
		}
		catch (SQLException e1){
			System.out.println(e1.getMessage());
		}
		int dept = s.nextInt();
		System.out.println("commision:");
		float commission = s.nextFloat();
		System.out.println("total sales:");
		int sales = s.nextInt();
		
		try {
			db.newSalesEmployee(name, address, nin, bankNo, salary, dept, commission, sales);
			System.out.println("Employee added succesfully");
		} catch (SQLException e2){
			System.out.println(e2.getMessage());
		}
		
		this.menu();
	}
	
	private void hrReport() {
		
		System.out.println("Please enter which department you would like to see: ");
		int dept = s.nextInt();
		try{
		db.hrReportByDepartment(dept);
		}
		catch (SQLException e1){
			System.out.println(e1.getMessage());
		}
	
		this.menu();
	}
	
	private void financeReport(){
	    
        System.out.println("Finance Report (Employee ID, Name, Starting Salary, Gross Pay)");
        try{
            db.generateFinanceReport();
            db.generateFinancialReportSalesEmployees();
        }
        catch (SQLException e3){
            System.out.println(e3.getMessage());
        }
        
        this.menu();
    }

}
