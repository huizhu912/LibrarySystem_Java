package inventory;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.Calendar;
import java.lang.Exception;
import control.Database;
import control.VerifySystem;
import exception.invalidItemCount;


public class Item {
	private String itemID;
	private String itemType;
	private String title;
	private String author;
	private String location;
	private String status;
	private Date dueDate = new Date();
	private int numOfCheckedOut;
	private static List<Item> cart = new ArrayList<Item>();
	private Calendar c = Calendar.getInstance();
	private static Calendar currentDate = Calendar.getInstance(); 
	private Database db;
	private VerifySystem verify;
	public static boolean flag = true;

	
	public Item(String itemid) throws Exception{
		this.itemID = itemid;
		this.verify = new VerifySystem(this);
		this.db = new Database(this);
		if (!this.verify.validItemID(this)) {
			//GUI: displays error message if an invalid item ID is entered
			flag = false;
			System.out.println("You have entered an invalid item ID.");
			System.out.printf("%n");
		}
		else	if (!this.verify.validItemCount(this)){ 
			//GUI: displays error message if the number of items exceeds the limit for each type
			flag = false;
		}
		else {
			flag = true;
			this.db.queryInventory(this);
			this.updateItem();
			if (!this.db.queryItemStatus(this.getItemID())){
				//System.out.println("This item is not available at the moment");
				System.out.printf("%n");	
			}
		}
	}
	
	//pass item information from MySQL inventory table to item object
	public void setItemInfo(String itemtype, String title, String author, String location, String status,Date duedate, int numofcheckedout) {
		this.setItemType(itemtype);
		this.setTitle(title);
		this.setAuthor(author);
		this.setLocation(location);
		this.setStatus(status);
		this.setDueDate(duedate);
		this.setNumOfCheckedOut(numofcheckedout);
	}
	
	//GUI: if checkout submit button is clicked, UI displays the list of items that a member has checked out  
	public static void printItemInfo() {
		System.out.println("Number of items in the cart: " + cart.size());
		System.out.printf("The item(s) that you have checked out on %tm/%td/%tY%n" , currentDate, currentDate, currentDate);
		for (Item item : Item.cart) {
			System.out.println("Item ID: " + item.itemID);
			System.out.println("Item Type: " + item.itemType);
			System.out.println("Title:" + item.title);
			System.out.println("Author: " + item.author);
			System.out.println("Location: " + item.location);
			//System.out.println("Status: " + item.status);
			System.out.printf("Due Date: %tm/%td/%tY%n", item.dueDate, item.dueDate, item.dueDate);
			System.out.println("Number of Checked Out: " + item.numOfCheckedOut);
			System.out.printf("%n");
			//checkOutString = "Item ID: " + item.itemID + "\n" + "Item Type: " + item.itemType + "\n" + "Title:" + item.title + "\n"  +"Author: " + item.author + "\n" + "Location: " + item.location + "\n" + String.format("Due Date: %tm/%td/%tY%n", item.dueDate, item.dueDate, item.dueDate) + "\n" + String.format("Number of Checked Out: " + item.numOfCheckedOut) + "\n\n";
			
		}
	}
	
	//return the cart
	public static List<Item> getCart() {
		return Item.cart;
	}
	
	//clear cart when checkout is complete
	public static void clearCart() {
		 Item.cart.clear();
	}
	
	//if an item is checked out, then the info of item object is updated and will be used to update inventory table
	public void updateItem() {
		this.status = "BORROWED";
		if (this.itemType.equals("FICTION") || this.itemType.equals("NON-FICTION")) {
			c.add(Calendar.DAY_OF_MONTH, 14); 
		}
		else if (this.itemType.equals("VIDEO")){
			c.add(Calendar.DAY_OF_MONTH, 7); 
		}
		this.dueDate.setTime(c.getTime().getTime());
		this.numOfCheckedOut++;
	}
	
	public String getItemID() {
		return this.itemID;
	}
	
	public void setItemType (String itemtype) {
		this.itemType = itemtype;
	}
	
	public String getItemType () {
		return this.itemType;
	}
	
	public void setTitle (String title) {
		this.title = title;
	}
	
	public String getTitle () {
		return this.title;
	}
	
	public void setAuthor (String author) {
		this.author = author;
	}
	
	public String getAuthor () {
		return this.author;
	}
	
	public void setLocation (String location) {
		this.location = location;
	}
	
	public String getLocation () {
		return this.location;
	}
	
	public void setStatus (String status) {
		this.status = status;
	}
	
	public String getStatus () {
		return this.status;
	}
	
	public void setDueDate(Date duedate) {
		this.dueDate.setTime(duedate.getTime());
	}
	
	public Date getDueDate() {
		return this.dueDate;
	}
	
	public void setNumOfCheckedOut (int numofcheckedout) {
		this.numOfCheckedOut = numofcheckedout;
	}
	
	public int getNumOfCheckedOut () {
		return this.numOfCheckedOut;
	}
	
	
	
}
