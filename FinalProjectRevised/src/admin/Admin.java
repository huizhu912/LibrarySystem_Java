package admin;

import member.Member.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.Calendar;
import java.lang.Exception;
import java.util.Scanner;
import control.Database;
import control.VerifySystem;
import inventory.Item;
import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;


public class Admin extends member.Member {
	private String username = "coen275";
	private String password = "summer2013";
	
	Locale locale ;
	private static Calendar startC = Calendar.getInstance();
	private static Calendar endC = Calendar.getInstance();
	private Database db;
	private VerifySystem verify;
	private static Scanner scanner = new Scanner(System.in);
	private static String title;
	private static int count;
	
	public Admin(String username, String password) throws Exception {
		if ((username.equals(this.username)) &&  (password.equals(this.password)))  {
			this.verify = new VerifySystem(this);
			this.db = new Database(this);
			System.out.println("Welcome to library administration system.");
			
			//listNumOfAllTypes();
			//getTotalNumberOfItems("engineering");
			//getTotalNumberOfMembers();
			//getCurrentOutItems();
			//getTotalFines("2013-08-01", "2013-08-25");
			//getMaxOfCheckedOutBook("2013-08-05", "2013-08-25");
			
			
		}
		else {
			System.out.println("Invalid administrator login username or password.");
			throw new Exception();
		}
	}
	
	//in GUI, admin enters/selects an itemType, the selection is passed to the method as input parameter	
	public void getTotalNumberOfItems(String JTextFieldItemType) throws Exception {
		int total = 0;
		String type = JTextFieldItemType.toUpperCase();
		total = this.db.getTotalByType(type);		
		System.out.printf("The total number of %s is %d.%n", type, total);
	}
	
	
	public int getTotalNumberOfMembers() throws Exception {
		int total = 0;
		total = this.db.queryNumOfMember();
		System.out.println("The total number of member is: " + total);
		
		return total;
	}
	
	
	public int getCurrentOutItems () throws Exception {
		int total = 0;
		total = this.db.queryCurrentOutItems();	
		System.out.println("The number of  is items currently out is: " + total);
		
		return total;
	}
	
	public void maxCheckedOutBook() {
		
	}
	
	//admin inputs startDate and endDate through GUI
	public double getTotalFines(String startDate, String endDate )  throws Exception {
		double amount = 0.0;
		Date startdate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
		Date enddate =  new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
		java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime()); 
		java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime()); 
		
		amount = this.db.getTotalFines(sqlStartDate, sqlEndDate);
		startC.setTime(startdate);
		endC.setTime(enddate);
		
		System.out.printf("The total amount of fines that are collected from %tY-%tm-%td to %tY-%tm-%td is %.2f. %n", startC , startC, startC, endC , endC, endC , amount);
		
		return amount;
	}
	
	public String getMaxOfCheckedOutBook (String startDate, String endDate) throws Exception {
			Date startdate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date enddate =  new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime()); 
			java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime()); 
			startC.setTime(startdate);
			endC.setTime(enddate);
			return this.db.queryMaxCheckedOutBook(sqlStartDate, sqlEndDate);
	}
	
	public static String outputMaxCheckedOutBook(String title, int maxCheckedOut) {
			Admin.title = title;
			Admin.count = maxCheckedOut;		
			System.out.printf("The most checked out book from to %tY-%tm-%td to %tY-%tm-%td is %s. %n Count: %d.%n", startC , startC, startC, endC , endC, endC , Admin.title, Admin.count);
			
			return Admin.title + " (" + Admin.count + ")";
	}
	
	//this method lists the pair of type-numOfType in the inventory table, based on which the bar chart can be drawn
	public ArrayList<Integer> listNumOfAllTypes() throws Exception {
			Map<String, Integer> list = new HashMap<String, Integer>(); 
			list = this.db.getNumOfAllTypes ();
			
			ArrayList<Integer> arrReturn = new ArrayList<Integer>();
			
			for (Object key : list.keySet()) {
					int value = list.get((String)key);
					System.out.printf("The total number of %s is %d.%n", key, value);
					arrReturn.add(value);
			}
			
			return arrReturn;
	}
	
}
