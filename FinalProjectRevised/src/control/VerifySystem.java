package control;



import inventory.Item;

import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import exception.hasOverDueItems;
import exception.invalidCheckOutDate;
import exception.invalidItemCount;
import member.Member;
import admin.Admin;



public class VerifySystem {
	private Database db;
	private Member memberObj;
	private Admin adminObj;
	private Item itemObj;
	private String itemtype;
	private static int countF = 0;
	private static int countNF = 0;
	private static int countV = 0;
	private final static int MAX_F = 3;
	private final static int MAX_NF = 2;
	private final static int MAX_V = 2;
	protected final static double RATE_FICTION = 0.15;
	protected final static double RATE_NONFICTION = 0.30;
	protected final static double RATE_VIDEO = 0.50;
	protected static double totalFines;
	
	
	public VerifySystem (Member memberobj) throws Exception {
		this.memberObj = memberobj;
		this.db = new Database(memberobj);
	}
	
	public VerifySystem (Admin adminobj) throws Exception {
		this.adminObj = adminobj;
		this.db = new Database(adminobj);
	}
	
	public VerifySystem (Item itemobj) throws Exception {
		this.itemObj = itemobj;
		this.db = new Database(itemobj);
	}
	
	public boolean validMemberID(Member memberObj) throws Exception {	
		this.memberObj = memberObj;
		int memberID = this.memberObj.getMemberID();
		if (this.db.queryMemberID(memberID)) {
			return true;
		}
		return false;
	}
	
	public boolean validItemID(Item itemObj) throws Exception {	
		this.itemObj = itemObj;
		String itemID = this.itemObj.getItemID();
		if (this.db.queryItemID(itemID)) {
			return true;
		}
		return false;
	}


	public boolean validForCheckOut(Date lastcheckoutdate, double balance) throws Exception {
		boolean validity = true;
		Calendar currentDate = Calendar.getInstance();
		Calendar c = Calendar.getInstance();
		c.setTime(lastcheckoutdate);
		c.add(Calendar.DAY_OF_MONTH, 14);
		if (currentDate.before(c)) {
			validity = false;
			throw new invalidCheckOutDate(this.memberObj.getLastCheckOutDate());
		}
		else if(hasOverDueItem(this.memberObj) || (balance > 0)) {  
			validity = false;
			this.db.updateBalance(this.memberObj.getMemberID());
			//System.out.println("You are not qualified to check out due to the overdue items remained in your account : " + this.db.getBalance(this.memberObj.getMemberID()));
			throw new hasOverDueItems(this.memberObj.getBalance());
			
		}
		return validity;
	}
	
	public boolean hasOverDueItem(Member memberObj) throws Exception {
		if (this.db.overdueItemsOnHold(memberObj)) {
			//System.out.println("total fines: " + totalFines);
			return true;
		}
		return false;
	}
		
	public static boolean hasDuplicateEmail(String email) throws Exception {
		if (Database.queryEmail(email)) {
			return true;
		}
		return false;
	}
	
	
	
	public boolean validItemCount(Item itemObj) throws Exception {
		this.itemObj = itemObj;
		boolean validityCount = true;
		if (this.itemObj.getItemID().startsWith("F")) {
			this.itemtype = "Fiction";
			countF++;
			if (countF > VerifySystem.MAX_F) {
				System.out.println("The number of " + this.itemtype + " has exceeded the limit of " + VerifySystem.MAX_F);
				validityCount =  false;
				throw new invalidItemCount(this.itemtype); 
			}
		}
		
		else if (this.itemObj.getItemID().startsWith("NF")) {
			this.itemtype = "Non-Fiction";
			countNF++;
			if (countNF > VerifySystem.MAX_NF) {
				System.out.println("The number of " + this.itemtype + " has exceeded the limit of " + VerifySystem.MAX_NF);
				validityCount =  false;
				throw new invalidItemCount(this.itemtype); 
			}
		}
		
		else if (this.itemObj.getItemID().startsWith("V")) {
			this.itemtype = "Video";
			countV++;
			if (countV > VerifySystem.MAX_V) {
				System.out.println("The number of " + this.itemtype + " has exceeded the limit of " + VerifySystem.MAX_V);
				validityCount =  false;
				throw new invalidItemCount(this.itemtype); 		
			}
		}
		return validityCount;
	}
	
	public String getItemType() {
		return this.itemtype;
	}
	
	public void getCount() {
		System.out.println("Fiction: " +  countF);
		System.out.println("Non-Fiction: " +  countNF);
		System.out.println("Video: " +  countV);
	}
	
	public static int getMaxF() {
		return VerifySystem.MAX_F;
	}
	
	public static int getMaxNF() {
		return VerifySystem.MAX_NF;
	}
	
	public static int getMaxV() {
		return VerifySystem.MAX_V;
	}
	
	public static void resetCount()
	{
		countF = 0;
		countNF = 0;
		countV = 0;
	}
	
	public static void getTotalFines(double totalFines) {
		VerifySystem.totalFines = totalFines;
	}
	
	public boolean doubleReserve(int memberID, String itemID) throws Exception {
		if (this.db.queryReservedItems(memberID, itemID) == false) {
			return false;
		}
		return true;
	}
	
}
