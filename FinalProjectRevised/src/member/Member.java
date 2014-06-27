package member;


import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.Calendar;
import java.lang.Exception;
import java.util.Scanner;
import control.Database;
import control.VerifySystem;
import control.Transaction;
import inventory.Item;
import java.text.SimpleDateFormat;
import gui.LibraryPanelBookReserve;

import main.Main;



public class Member implements control.Transaction {
	private static String inputText;
	private int memberID;
	private String itemID;
	private String name;
	private String address;
	private String county;
	private String email;
	private double balance = 0.0;
	private ArrayList cart;
	private Date startDate = new Date();
	private Date lastCheckOutDate = new Date();
	private Calendar c = Calendar.getInstance();
	private Database db;
	private VerifySystem verify;
	private static Scanner scanner = new Scanner(System.in);
	private String transactionType;
	private static double membershipFee;
	private static boolean membershipFeePaid = false;
	private static Calendar startC = Calendar.getInstance();
	private static Calendar endC = Calendar.getInstance();
	
	
	public Member(int memberid) throws Exception {
		this.memberID = memberid;
		this.verify = new VerifySystem(this);
		this.db = new Database(this);
		if (this.verify.validMemberID(this)) {
			this.db.queryMember(this);
			this.printMemberInfo();
			
			//GUI: when checkOut button is clicked, call checkOut()
			//this.checkOut("F0003");
			//this.checkOut("F0001");
			//this.checkOut("NF0001");
			//this.checkOut("NF0002");
			//this.checkOut("F0001");
			//this.checkOut("F0003");
			//this.checkOut("NF0004");
			//this.checkOut("V0002");
			//doneWithCheckOut();
			
			//this.returnItem("F0004");
			//this.returnItem("NF0004");
			//this.returnItem("NF0001");
			//this.returnItem("NF0002");
			//this.returnItem("F0001");
			//this.returnItem("F0002");
			//this.returnItem("V0002");
			//doneWithReturn();
			
			
			//this.reserveItem("F0001");
			//this.returnItem("NF0001");
			//this.returnItem("F0002");
			//this.claimItemLost("V0003"); 
			//this.payFineAmount();
			//this.queryMemberTransactionHistory("2013-08-01", "2013-08-25");


		}
		else {
			//GUI: display error message
			System.out.println("You have entered an invalid member ID.");
		}
	}
	
	public Member() throws Exception { };
	
	//in GUI, pass int memberID as parameter
	public static Member memberLogIn(String JTextField) throws Exception {
		//System.out.printf("Please enter your member ID:  %n");
		//Member.inputText = scanner.nextLine();
		Member.inputText =  JTextField;
		return new Member(Integer.parseInt(inputText)); //convert text input to integer
	}
	
	//GUI: if member login successfully, display member info 
		public void printMemberInfo() throws Exception {
			this.db.updateBalance(this.memberID);
			System.out.println("name: " + this.name);
			System.out.println("address:" + this.address);
			System.out.println("county: " + this.county);
			System.out.println("email: " + this.email);
			System.out.println("balance: " + this.balance);
			System.out.printf("start date: %tm/%td/%tY%n", this.startDate, this.startDate, this.startDate);
			System.out.printf("last checkout date: %tm/%td/%tY%n", this.lastCheckOutDate, this.lastCheckOutDate, this.lastCheckOutDate);
			System.out.printf("%n");
		}
			
		//pass user information from MySQL member table to member object
		public void setMemberInfo(String name, String address, String county, String email, double balance, Date startdate, Date lastcheckoutdate){
			this.setName(name);
			this.setAddress(address);
			this.setCounty(county);
			this.setEmail(email);
			this.setBalance(balance);
			this.setStartDate(startdate);
			this.setLastCheckOutDate(lastcheckoutdate);
		}
	
		public void disconnectDB() throws Exception {
			this.db.disconnect();
		}
	
		public int getMemberID() {
			return this.memberID;
		}
	
	//assign member ID when register 
	public void setMemberID(int memberid) {
		this.memberID = memberid;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getCounty() {
		return this.county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Double getBalance() {
		return this.balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	public void setStartDate(Date startdate) {
		this.startDate.setTime(startdate.getTime());
	}
	
	public Date getLastCheckOutDate() {
		return this.lastCheckOutDate;
	}
	
	public void setLastCheckOutDate(Date lastcheckoutdate) {
		this.lastCheckOutDate.setTime(lastcheckoutdate.getTime());
	}
	
	public VerifySystem getVerify()
	{
		return verify;
	}
	
	//in GUI, pass string itemID as parameter
	public String checkOut(String JTextField) throws Exception {
			this.transactionType = "BORROW";
			//System.out.printf("Your last check out date was: %tm/%td/%tY%n", this.lastCheckOutDate, this.lastCheckOutDate, this.lastCheckOutDate);
			
			//GUI: if the member is invalid for checkout, display error message. 
			//for invalid checkout date: see exception.invalidCheckOutDate for error message
			//for overdue items: see exception.hasOverDueitems for error message; display total amount of fines, when click on "pay" button, clear member.balance
			if (this.verify.validForCheckOut(this.lastCheckOutDate, this.balance)){
					
					Member.inputText = JTextField;
					this.itemID = Member.inputText;
					Item newItem = new Item(this.itemID);

					if (Item.flag == true) {
						Item.getCart().add(newItem);
						System.out.println(this.itemID + " has been added to the cart.");
						return this.itemID + " has been added to the cart.";
					}
					else {
						System.out.println(this.itemID  + " is not added to the cart");
						System.out.printf("%n");
						return this.itemID  + " is not added to the cart";
					}
			}
			else
				return "You have outstanding fines or too many recent check outs";
			
	}
	
	//in GUI, when click on "done with checkout" button, call doneWithCheckout() to print all the checked out items and empty the cart
	public  void doneWithCheckOut() throws Exception {
		this.db.updateInventoryCheckOut(Item.getCart().toArray(), this.memberID);  //update item status, numOfCheckedOut, dueDate
		this.db.updateLastCheckOutDate();  //update member lastCheckOutDate
		this.db.updateTransactionHistory(transactionType);	 //update member transaction history
		this.db.deleteReservedItems(); //delete reserved items
		Item.printItemInfo();
		Item.clearCart();
	}
	
	//in GUI, pass string itemID as parameter	
		public void reserveItem(String JTextField) throws Exception {
			this.transactionType = "RESERVE";
			Item.clearCart();
			//searchForItem();

			//System.out.println("Reserve an item by entering the item ID: ");
			//Member.inputText = null;
			//Member.inputText = scanner.nextLine();
			Member.inputText = JTextField;
			this.itemID = Member.inputText;
			Item itemObj = new Item(this.itemID);
			if (this.verify.validItemID(itemObj)) {
				if (this.verify.doubleReserve(this.memberID, this.itemID) == true){
					System.out.println("You have reserved this item.");
				}
				else if (db.checkItemAvailability(this.itemID) == false) {
					this.db.addToReserve(this.itemID);
					Item.getCart().add(itemObj);
				    this.db.updateTransactionHistory(this.transactionType);
				    //this.printAllReservedItems(this);
				    this.printReservedItems(this);
					 
				}
				else {
					System.out.println("No need to reserve. This item is available.");
				}
			}			
	}
	
	//in GUI, pass string itemID as parameter 	
	public void returnItem(String JTextField) throws Exception {
		this.transactionType = "RETURN";
		
		//GUI take member entries from here
		//System.out.println("Please enter the item ID to be returned: ");
			//Member.inputText = null;
			//Member.inputText = scanner.nextLine();
			Member.inputText = JTextField;
			this.itemID = Member.inputText;
			Item itemObj = new Item(this.itemID);
			if (this.verify.validItemID(itemObj)) {
				Item.getCart().add(itemObj);		
			}
		
			//if (this.verify.hasOverDueItem(this)) {
			//	this.db.updateBalance(this.memberID);
			//}		
			//this.db.updateInventoryReturn();
			//returnCount++;
			//this.printReturnedItems(this);
			//this.db.updateTransactionHistory(this.transactionType);
	}
	
	//in GUI, when click on "done with return", call doneWithReturn() and print all the returned items
	public void doneWithReturn() throws Exception {
			this.db.updateInventoryReturn();
			this.printReturnedItems(this);
			this.db.updateTransactionHistory(this.transactionType);
			Item.clearCart();
	}
	
	//in GUI, when click on button "Pay Fine", call payFineAmount()
	public void payFineAmount() throws Exception {
		if (this.db.getBalance(this.memberID) == 0.0) {
			System.out.println("Your balance is 0.00");
		}
		else {
			this.transactionType = "PAYBALANCE"; 
			int memberid = this.memberID;
			this.db.updateTransactionHistoryPay(this.transactionType);
			this.db.clearBalance(memberid);
			this.balance = 0.0;
			System.out.println("Your balance is now cleared.");
			this.printMemberInfo();
		}
	}
	
	//in GUI, pass string book name, title, or keyword as parameter
	public static String searchForItem(String JTextField) throws Exception {
		//System.out.println("Please enter the book name, title, or keyword for an item for search: ");
		//Member.inputText = scanner.nextLine();
		Member.inputText = JTextField;
		return Database.queryMatchingItem(Member.inputText); 
	}

	//in GUI, pass name, address, county, email as parameters
	public static void register(String JTextFieldName, String JTextFieldAddress, String JTextFieldCounty, String JTextFieldEmail) throws Exception {
			String transactionType = "REGISTER";
			String name = new String(); 
			String address = new String(); 
			String county = new String(); 
			String email = new String(); 
	
			Date startDate = new Date();
			Date defaultDate = new Date();
			Date lastCheckOutDate = new Date();
			java.sql.Date sqlStartDate = new java.sql.Date(startDate.getTime());
			Calendar c = Calendar.getInstance();
			c.set(1970, 0, 1);
			defaultDate.setTime(c.getTime().getTime());
			lastCheckOutDate.setTime(defaultDate.getTime());
			java.sql.Date sqlLastCheckOutDate = new java.sql.Date(lastCheckOutDate.getTime());
			
			//Member.inputText = null;
			//System.out.println("Name: ");
			//Member.inputText = scanner.nextLine();
			//name = Member.inputText;
			//System.out.println("Address: ");
			//Member.inputText = scanner.nextLine();
			//address = Member.inputText;
			//System.out.println("County: ");
			//Member.inputText = scanner.nextLine();
			//county = Member.inputText;
			//System.out.println("Email: ");
			//Member.inputText = scanner.nextLine();
			//email = Member.inputText;
			
			name = JTextFieldName;
			address = JTextFieldAddress;
			county = JTextFieldCounty;
			email = JTextFieldEmail;
			
			//when click on submit
			if (VerifySystem.hasDuplicateEmail(email)) {
				System.out.println("This email address has already been registered.");
			}
			else if (county.equals("SpringField")) {				
				Database.addToMember(name, address, county, email, sqlStartDate,  sqlLastCheckOutDate);
				System.out.println("Welcome to BookMarkers Library. Your member ID is: " + Database.getMemberID());
				
			}
			else { 
				Member.membershipFee = 10.00;
				if (!(county.equals("SpringField")) && (Member.membershipFee == 10.00 && (Member.membershipFeePaid == false))){
					System.out.println("You are not resident of SpringField. A $10 membership is required for registration. Please click on button Pay Registration Fee to complete registration.");
				}
				else {
					Database.addToMember(name, address, county, email, sqlStartDate,  sqlLastCheckOutDate);
					System.out.println("Welcome to BookMarkers Library. Your member ID is: " + Database.getMemberID());
				}	
			}
	}
	
	//in GUI, when click on the button "pay registration fee", call clearMembershipFee()
	public static void clearMembershipFee() throws Exception {
		 	Member.membershipFee = 0.0;
		 	Member.membershipFeePaid = true;
		 	System.out.println("The membership fee has been paid.");
	}
	
	//in GUI, pass itemID as parameter
	public void claimItemLost(String JTextField) throws Exception {
			this.transactionType = "CLAIMLOST";
			int memberId = this.memberID;
			//System.out.println("Enter the itemID for the item that is lost:");
			//Member.inputText = scanner.nextLine();
			Member.inputText = JTextField;
			String itemId = Member.inputText;
			Item.getCart().add(new Item(itemId));
			this.db.issueFine(memberId, itemId);
			//this.db.deleteLostItem(itemId);
			this.db.updateItemStatus(itemId);
			this.db.updateTransactionHistory(this.transactionType);				
	}
	
	
	
	public String printAllReservedItems(Member memberObj) throws Exception {
			// int memberID = memberObj.memberID;
			return this.db.getAllReservedItems(memberObj);
			
			
			/*SELECT member.name, transactionhistory.date, inventory.itemID, inventory.title, inventory.status
			FROM  `transactionhistory` 
			INNER JOIN inventory, member
			WHERE transactionhistory.itemID = inventory.itemID
			AND transactionhistory.memberID = member.memberID
			AND transactionhistory.itemID =  "F0001"
			ORDER BY DATE DESC 
			LIMIT 0 , 1 */
	}
	
	//in GUI, user type in a span of time in form of "yyyy-mm-dd"
	public String queryMemberTransactionHistory(String JTextFieldStartDate, String JTextFieldEndDate) throws Exception {
			int memberId = this.memberID;			
			String startDate = JTextFieldStartDate;
			String endDate = JTextFieldEndDate;
			Date startdate = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
			Date enddate =  new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
			java.sql.Date sqlStartDate = new java.sql.Date(startdate.getTime()); 
			java.sql.Date sqlEndDate = new java.sql.Date(enddate.getTime()); 
			startC.setTime(startdate);
			endC.setTime(enddate);
			System.out.printf("Member transactions from %tY-%tm-%td to %tY-%tm-%td:%n%n", startC , startC, startC, endC , endC, endC );		
			String strReturn = "Member transactions from " + JTextFieldStartDate + " to " + JTextFieldEndDate + "\n";
			strReturn += this.db.getMemberTransactionHistory(memberId, sqlStartDate, sqlEndDate);
			
			return strReturn;
	}
	
	public static String outputMemberTransactionHistory(int memberId, String itemId, String transactionType, Date date, double payment) {
		System.out.println("Member ID: " + memberId);
		System.out.println("Item ID: " + itemId);
		System.out.println("Transaction Type: " + transactionType);
		System.out.println("Date: " + date);
		if ((payment > 0.00) && (transactionType.equals("PAYBALANCE"))) {
			System.out.println("Payment: " + payment);
		}
		System.out.printf("%n");
		
		return "Member ID: " + memberId + "\n" + "Item ID: " + itemId + "\n" + "Transaction Type: " + transactionType + "\n" +
			   "Date: " + date + "\n" + "Payment: " + payment + "\n";
	}
	
	
	public void printReservedItems(Member memberObj) throws Exception {
		this.db.getReservedItems(memberObj);
	}

	public static String outputReservedItems(String name, String title, String status, Date date) {
		System.out.println("Member name: " + name);
		System.out.println("Item title: " + title);
		System.out.println("Current Status: " + status);
		System.out.println("Reserve Date: " + date);
		System.out.printf("%n");
		
		return "Member name: " + name + "\n" + "Item title: " + title + "\n" + "Current Status: " + status + "\n" + 
			   "Reserve Date: " + date + "\n";
	}
	
	public void printReturnedItems(Member memberObj) throws Exception {
		System.out.println("List of items that have been returned to the library.");
		this.db.getReturnedItems(memberObj);
	}
	
	public static void outputReturnedItems(int memberID, String itemID, String itemType, String title, String author, Date sqlDate) {
		System.out.println("Member ID: " + memberID);
		System.out.println("Item ID: " + itemID);
		System.out.println("Item Type: " + itemType);
		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		System.out.println("Return Date: " + sqlDate);
		System.out.printf("%n");
	}
	
	public static String outputMatchingItem(String itemID, String title, String author, String itemType, String status, String location) {
		System.out.println("Item ID: " + itemID);
		System.out.println("Title: " + title);
		System.out.println("Author: " + author);
		System.out.println("Item Type: " + itemType);
		System.out.println("Current Status: " + status);
		if (status == "AVAILABLE"){
			System.out.println("Location: " + location);
		}
		
		return "Item ID: " + itemID + "\n" + "Author: " + author + "\n"  + 
			   "Item Type: " + itemType + "\n" + "Current Status: " + status + "\n" + "Location: " + location + "\n";
	}
	
	public static String printNoMatchingResult() {
		System.out.println("Sorry, no matches have been found for the search query.");
		return "Sorry, no matches have been found for the search query.";
	}
	

	
	
}
