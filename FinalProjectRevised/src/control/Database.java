package control;


import inventory.Item;
import sendmail.*;
import java.sql.*;
import java.util.Date;
import java.util.Calendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ArrayList;
import member.Member;
import admin.Admin;
import java.util.List;
import java.util.ArrayList;
import java.util.*;



public class Database {
	private String query;
	private static Connection con;
	private static Statement statement;
	private PreparedStatement preparedStatement = null;
	private PreparedStatement preparedStatementA = null;
	private PreparedStatement preparedStatementB = null;
	private PreparedStatement preparedStatementC = null;
	private static PreparedStatement preparedStatement2 = null;
	private Date defaultDate = new Date();
	//private Date lastCheckOutDate = new Date();
	private Member memberObj;
	private Admin adminObj;
	private Item itemObj;
	private String transactionType;
	private List<Item> cart = Item.getCart();

	
	public Database(Member memberObj) throws Exception {
		this.memberObj	= memberObj;
		Class.forName("com.mysql.jdbc.Driver"); //Loading the driver			
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
		statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}
	
	public Database(Admin adminObj) throws Exception {
		this.adminObj	= adminObj;
		Class.forName("com.mysql.jdbc.Driver"); //Loading the driver			
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
		statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}
	
	public Database(Item itemObj) throws Exception {
		this.itemObj = itemObj;
		Class.forName("com.mysql.jdbc.Driver"); //Loading the driver			
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
		statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
	}
	
/////////// Member methods start//////////////////////  
	
	public boolean queryMemberID(int memberID) throws Exception {
		this.query = "select memberID from member";
		ResultSet result = statement.executeQuery(this.query);
		
		result.beforeFirst();
		while (result.next()) {
			if ((result.getInt("memberID") == memberID) && (Integer.toString(memberID).length() == 5)){
				return true;
			}
		}
		return false;
	}
	
	public void queryMember(Member memberObj) throws Exception {
		this.memberObj = memberObj;
		int memberID = this.memberObj.getMemberID();
		this.query = "select * from member where memberID=" + memberID + ";";
		ResultSet result = statement.executeQuery(this.query);
			
		result.beforeFirst();
		while (result.next()) {
			java.sql.Date d = result.getDate("startDate");
			Date utilStartDate = new Date(d.getTime());
			d = result.getDate("lastCheckOutDate");
			Date utilLastCheckOutDate = new Date(d.getTime());	
			this.memberObj.setMemberInfo(result.getString("name"), result.getString("address"), result.getString("county"), result.getString("email"), result.getDouble("balance"),  utilStartDate, utilLastCheckOutDate);
		}
	}
	
	
	public Member getMemberObj() {
		return this.memberObj;
	}
	
    public boolean queryItemID(String itemID) throws Exception {
		this.query = "select itemID from inventory";
		ResultSet result = statement.executeQuery(this.query);
		
		result.beforeFirst();
		while (result.next()) {
			if (result.getString("itemID").equals(itemID)) {
				return true;
			}
		}
		return false;
	} 
	
    public void queryInventory(Item itemObj) throws Exception {
    	this.itemObj = itemObj;
		this.query = "select * from inventory where itemID=" + '"' + this.itemObj.getItemID() + '"' + ";";
		ResultSet result = statement.executeQuery(this.query);
			
		result.beforeFirst();
		while (result.next()) {
			java.sql.Date d = result.getDate("dueDate");
			Date utilDueDate = new Date(d.getTime());
			this.itemObj.setItemInfo(result.getString("itemType"), result.getString("title"), result.getString("author"), result.getString("location"), result.getString("status"), utilDueDate, result.getInt("numOfCheckedOut"));
		}
	}
	
    public void updateInventoryCheckOut(Object[] cart, int memberID) throws Exception {
    	/*this.query = "select * from inventory";
    	ResultSet result = statement.executeQuery(this.query);
    	
    	for (int i = 0; i < cart.length; i++) {
    		result.beforeFirst();
    		while(result.next()){
    			if (result.getString("itemID").equals(((Item)cart[i]).getItemID())) {
    				result.updateString("status", ((Item)cart[i]).getStatus());				 
    				java.sql.Date sqlDate = new java.sql.Date(((Item)cart[i]).getDueDate().getTime());
    				result.updateDate("dueDate", sqlDate);
    				result.updateInt("numOfCheckedOut", ((Item)cart[i]).getNumOfCheckedOut());
    				result.updateInt("currentBorrowerID", memberID);
    				result.updateRow();
    			}
    			
    		}
    	} */
    	
    	for (Object item : cart) {
    		java.sql.Date sqlDate = new java.sql.Date(((Item)item).getDueDate().getTime());
    		PreparedStatement preparedStatement = con.prepareStatement("update inventory set status=?, dueDate=?, numOfCheckedOut=?, currentBorrowerID=? where itemID=?"); 
    		preparedStatement.setString(1, ((Item)item).getStatus());
    		preparedStatement.setDate(2, sqlDate);
    		preparedStatement.setInt(3, ((Item)item).getNumOfCheckedOut());
    		preparedStatement.setInt(4, memberID);
    		preparedStatement.setString(5, ((Item)item).getItemID());
    		preparedStatement.executeUpdate();
    	}
    	
    }
    
    public void updateLastCheckOutDate() throws Exception {
    	this.query = "select * from member";
    	ResultSet result = statement.executeQuery(this.query);
    	
    	result.beforeFirst();
    	while(result.next()) {
    		if (result.getInt("memberID") == this.memberObj.getMemberID()) {
    			Date currentDate = new Date();
    			java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
    			result.updateDate("lastCheckOutDate", sqlDate);
    			result.updateRow();
    		}
    	}
    }
    
    public void updateTransactionHistory(String transactiontype) throws Exception {
    	this.transactionType = transactiontype;
    	Date currentDate = new Date(); 	
		java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
    	for (Item item : Item.getCart()) {
    		preparedStatement = con.prepareStatement("insert into transactionhistory (memberID, transactionType, itemID, date, payment) values (?, ?, ?, ?,?)");
    		preparedStatement.setInt(1, this.memberObj.getMemberID()); 
    		preparedStatement.setString(2, this.transactionType);
    		preparedStatement.setString(3, item.getItemID());
    		preparedStatement.setDate(4, sqlDate);
    		preparedStatement.setDouble(5, 0.0);
    		preparedStatement.executeUpdate();
    	}
    }
    
    
    public void updateTransactionHistoryPay(String transactiontype) throws Exception {
    	this.transactionType = transactiontype;
    	Date currentDate = new Date(); 	
		java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
    	PreparedStatement preparedStatement3 = con.prepareStatement("insert into transactionhistory (memberID, transactionType, itemID, date, payment) values (?, ?, ?, ?, ?)");
		preparedStatement3.setInt(1, this.memberObj.getMemberID()); 
		preparedStatement3.setString(2, this.transactionType);
		preparedStatement3.setString(3, "*");
		preparedStatement3.setDate(4, sqlDate);
    	preparedStatement3.setDouble(5, this.getBalance(this.memberObj.getMemberID()));   
    	preparedStatement3.executeUpdate();
    }
    
    
    public double getBalance(int memberID) throws Exception {
    	double balance = 0.0;
    	preparedStatement = con.prepareStatement("select balance from member where memberID=?");
    	preparedStatement.setInt(1, memberID);
    	ResultSet result = preparedStatement.executeQuery();
    	result.beforeFirst();
    	while(result.next()){
    		balance = result.getDouble("balance");
    	}
    	return balance;
    }
    
    public void clearBalance(int memberID) throws Exception {
    	preparedStatement = con.prepareStatement("update member set balance=? where memberID=?");
    	preparedStatement.setDouble(1, 0.0);
    	preparedStatement.setInt(2, memberID);
    	preparedStatement.executeUpdate();
    }
    
    public boolean queryReservedItems(int memberID, String itemID) throws Exception {
    		boolean found = false;
	   		preparedStatement = con.prepareStatement("select * from reserveditems");
	   		ResultSet result = preparedStatement.executeQuery();
	   		result.beforeFirst();
	   		while(result.next()){
	   			if ((result.getInt("memberID") == memberID) && (result.getString("itemID").equals(itemID))) {
	   				found = true; //given memberID and itemID found in reserveditems table 
	   				break;
	   			}
	   		}
	   		return found;
    }
    
    public void deleteReservedItems() throws Exception {
    	int memberid = this.memberObj.getMemberID();
    	String itemid;
    	
    	for (Item item : cart) {
    		itemid = item.getItemID();
    		this.query = "select * from reserveditems";
    		ResultSet result = statement.executeQuery(this.query);
  
    		result.beforeFirst();
    		while (result.next()) {
    			if((result.getInt("memberID") == memberid) && result.getString("itemID").equals(itemid)) {
    				//delete the record from the table
    				preparedStatement = con.prepareStatement("delete from reserveditems where memberID = ? AND itemID = ?" );
    				preparedStatement.setInt(1, memberid);
    				preparedStatement.setString(2, itemid);
    	    		preparedStatement.executeUpdate();
    			}
    		
    		}
    	}
    }
    		
    public void addToReserve(String itemid) throws Exception {
    			//insert into table
    			int memberid = this.memberObj.getMemberID();
    			Date utilDate = new Date();
    			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    			
    			preparedStatement = con.prepareStatement("insert into reserveditems (memberID, itemID, date) values (?, ?, ?)" );
				preparedStatement.setInt(1, memberid);
				preparedStatement.setString(2, itemid);
				preparedStatement.setDate(3, sqlDate);
	    		preparedStatement.executeUpdate();
    }
    
    public boolean checkItemAvailability(String itemID) throws Exception {
 	   		String status = "";
 	   		preparedStatement = con.prepareStatement("select status from inventory where itemID = ?");
 	   		preparedStatement.setString(1, itemID);	
 	   		ResultSet result = preparedStatement.executeQuery();
 	   		result.beforeFirst();
    	   while(result.next()) {
    		   status = result.getString("status");
    	   }
    	   if (status.equals("BORROWED") || status.equals("LOST")) {
    		   return false;
    	   }
    	   return true;
    }
    
    public String getReservedItems (Member memberObj) throws Exception {
    	int memberID = memberObj.getMemberID();
    	Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
		String strReturn = "";

        for (Item item : Item.getCart()) {
        	preparedStatement = con.prepareStatement("SELECT member.name, inventory.title, inventory.status, reserveditems.date FROM reserveditems INNER JOIN member, inventory  WHERE reserveditems.memberID =member.memberID AND reserveditems.memberID=? AND reserveditems.date=? AND reserveditems.itemID=inventory.itemID AND reserveditems.itemID =? GROUP BY member.memberID, reserveditems.itemID");
        	preparedStatement.setInt(1, memberID);
        	preparedStatement.setDate(2, sqlDate);
        	preparedStatement.setString(3, item.getItemID());
        	ResultSet result = preparedStatement.executeQuery();
        	result.beforeFirst();
    		while(result.next()) {
    			strReturn += Member.outputReservedItems(result.getString("name"), result.getString("title"), result.getString("status"), result.getDate("date"));
    		}
    	}
        
        return strReturn;
    }
    
    public String getAllReservedItems (Member memberObj) throws Exception {
    	int memberID = memberObj.getMemberID();
    	String strReturn = "";
		
		preparedStatement = con.prepareStatement("SELECT member.name, inventory.title, inventory.status, reserveditems.date FROM reserveditems INNER JOIN member, inventory  WHERE reserveditems.memberID =member.memberID AND reserveditems.memberID=?  AND reserveditems.itemID=inventory.itemID GROUP BY member.memberID, reserveditems.itemID");
		preparedStatement.setInt(1, memberID);
		ResultSet result = preparedStatement.executeQuery();		
		result.beforeFirst();
		while(result.next()) {
			strReturn += Member.outputReservedItems(result.getString("name"), result.getString("title"), result.getString("status"), result.getDate("date"));
		}
		
		return strReturn;
    }
    
    public void updateInventoryReturn() throws Exception {
    	java.sql.Date sqlDefaultDate =  new java.sql.Date(this.setDefaultDate().getTime());
    	
    	//update inventory
    	for (Object item : Item.getCart()) {
    		String itemID = ((Item)item).getItemID();
    		preparedStatement = con.prepareStatement("update inventory set status=? , dueDate= ?, currentBorrowerID=0  where itemID=?");
    		preparedStatement.setString(1, "AVAILABLE");
    		preparedStatement.setDate(2, sqlDefaultDate);
    		preparedStatement.setString(3, itemID);
    		preparedStatement.executeUpdate();  	
    		
    		//email member who reserved this item	
    		String content = "";
    		preparedStatement = con.prepareStatement("select itemID, itemType, title, author from inventory where itemID=?");
    		preparedStatement.setString(1, itemID);		
    		ResultSet result = preparedStatement.executeQuery();		
    		result.beforeFirst();
    		while(result.next()) {
    				content = String.format("This item is now available in the library.  Item ID: %s ||  Item Type: %s ||  Title: %s || Author: %s ", result.getString("itemID"), result.getString("itemType"),result.getString("title"), result.getString("author"));
    		}
    		
    		if (getEmailAddress(itemID).size() > 0) {
    			 for (Object email : getEmailAddress(itemID)) {
    				 	String toEmail = (String)email;
    				 	System.out.println("email: " + toEmail);
    	    			new SendMail(toEmail, content);
    			 } 				 
    		}
    		
    	}
    }
    
    
    public List<String> getEmailAddress(String itemID) throws Exception {
  	   List<String> email = new ArrayList<String>();
  	   preparedStatement = con.prepareStatement("select member.email from member inner join reserveditems where member.memberID=reserveditems.memberID and reserveditems.itemID=?");
  	   preparedStatement.setString(1, itemID);
  	   ResultSet result = preparedStatement.executeQuery();
  	   result.beforeFirst();
  		while(result.next()) {
  			email.add(result.getString("email"));
  		}
  		return email;
     }
    
    
    public void getReturnedItems(Member memberObj) throws Exception {
    	
    	//print returned items
    	System.out.println("Number of returned items: " + Item.getCart().size());
    	int memberID = memberObj.getMemberID();
    	Date utilDate = new Date();
		java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
    	for (Object item : Item.getCart()) {   		
    		String itemID = ((Item)item).getItemID();
    		preparedStatement = con.prepareStatement("select itemID, itemType, title, author from inventory where itemID=?");
    		preparedStatement.setString(1, itemID);		
    		ResultSet result = preparedStatement.executeQuery();		
    		result.beforeFirst();
    		while(result.next()) {
    			Member.outputReturnedItems(memberID, result.getString("itemID"), result.getString("itemType"), result.getString("title"), result.getString("author"), sqlDate);
    		}
    	}
    	
   }


   
   public boolean overdueItemsOnHold(Member memberObj) throws Exception {
	   int memberID = memberObj.getMemberID();
	   boolean onHold = false;
	   //select all the items currently on hold by the member
	   preparedStatement = con.prepareStatement("SELECT count(currentBorrowerID) AS borrowedItems FROM `inventory` WHERE currentBorrowerID=?");
	   preparedStatement.setInt(1, memberID);
	   ResultSet result = preparedStatement.executeQuery();
	   result.beforeFirst();
	   while (result.next()) {
		   if (result.getInt("borrowedItems") > 0) {
			   double x = this.calculateFine(memberID);
			   if (x > 0) {
				   //System.out.println("overdueItemsOnHold::total fines: " + x);
				   onHold = true;
				   VerifySystem.getTotalFines(x);
			   }
		   }
	   }
	   return onHold;
   }
	   
 
	public double calculateFine(int memberID) throws Exception {
		long numOfPassedDays = 0;   
		double totalFines = 0.0;
		double balance = 0.0;
	    Date currentDate = new Date();
	    Date defaultDate = this.setDefaultDate ();
	    preparedStatement = con.prepareStatement("select itemID, itemType, dueDate, price from inventory where currentBorrowerID=? AND status=?");
	    preparedStatement.setInt(1, memberID);
	    preparedStatement.setString(2, "BORROWED");
	    ResultSet result = preparedStatement.executeQuery();
	    result.beforeFirst();
	    while(result.next()){
		   		java.util.Date utilDueDate = new java.util.Date(result.getDate("dueDate").getTime());
		   		//System.out.println(result.getString("itemID"));
		   		//count amount of fines
		   		if ((utilDueDate.compareTo(defaultDate)) > 0 && (utilDueDate.compareTo(currentDate) < 0)) {
		   				numOfPassedDays = (currentDate.getTime() - utilDueDate.getTime())/(3600*1000*24);	 
		   				//System.out.println("numOfPassedDays: " + (int)numOfPassedDays);
		   				if (result.getString("itemType").equals("FICTION")){
		   					totalFines = totalFines + (VerifySystem.RATE_FICTION * (int)numOfPassedDays);
		   					//System.out.println(" FICTION::total fines: " + totalFines);
		   				}
		   				else if (result.getString("itemType").equals("NON-FICTION")){
		   					totalFines = totalFines + (VerifySystem.RATE_NONFICTION * (int)numOfPassedDays);
		   					//System.out.println("NON-FICTION::total fines: " + totalFines);
		   				}
		   				else if (result.getString("itemType").equals("VIDEO")){
		   					totalFines = totalFines + (VerifySystem.RATE_VIDEO * (int)numOfPassedDays);
		   					//System.out.println(" VIDEO::total fines: " + totalFines);
		   				}
	   					if (totalFines > result.getDouble("price")) {
	   						totalFines = result.getDouble("price");
	   					}
	   					//System.out.println(" calculateFine::total fines: " + totalFines);
		   		}		   		
	   }
	    				PreparedStatement preparedStatement1 = con.prepareStatement("select balance from member where memberID=?");
	    				preparedStatement1.setInt(1, memberID);
	    				ResultSet result1 = preparedStatement1.executeQuery();
	    				result1.beforeFirst();
	    				while(result1.next()) {
	    						balance = result1.getDouble("balance");
	    				}
	    				if (balance > totalFines) {
	    						totalFines =  balance;
	    				}  
	    				return totalFines;
   }
   
	public void updateBalance(int memberID) throws Exception {
		int memberid = memberID;
		double totalFines = this.calculateFine(memberID);
		PreparedStatement preparedStatement1 = con.prepareStatement("update member set balance=? where memberID=?"); 
		preparedStatement1.setDouble(1, totalFines);
		preparedStatement1.setInt(2, memberid);
		preparedStatement1.executeUpdate();
		this.memberObj.setBalance(totalFines);
	}
	
	
    //when an item is returned, set the defaultDate to 1970-01-01
   public Date setDefaultDate () {
	   Calendar c = Calendar.getInstance();
	   c.set(1970, 0, 1);
	   defaultDate.setTime(c.getTime().getTime());
	   //System.out.printf("the default date is: ", defaultDate , defaultDate , defaultDate);
	   return defaultDate;
   }
   
   public static boolean queryEmail(String email) throws Exception {
	   boolean duplicate = false;
		//connect to DB
		Class.forName("com.mysql.jdbc.Driver"); //Loading the driver			
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
		preparedStatement2 = con.prepareStatement("select email from member");
		ResultSet result = preparedStatement2.executeQuery();
		result.beforeFirst();
		while(result.next()) {
			if (result.getString("email").equals(email)) {
				duplicate = true;
			}
		}
		return duplicate;	   
   }
   
   public static int getMemberID() throws Exception {
	   int memberID = 0;
		//connect to DB
		Class.forName("com.mysql.jdbc.Driver"); //Loading the driver			
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
	   preparedStatement2 = con.prepareStatement("select memberID from member order by memberID desc limit 0, 1");
	   ResultSet result = preparedStatement2.executeQuery();
	   result.beforeFirst();
	   while(result.next()){
		   memberID =  result.getInt("memberID");
	   }
	   return memberID;
   }
   
  public static void addToMember(String name, String address, String county, String email, java.sql.Date startDate, java.sql.Date lastCheckOutDate) throws Exception {
		//connect to DB
		Class.forName("com.mysql.jdbc.Driver"); //Loading the driver			
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
		preparedStatement2 = con.prepareStatement("insert into member (name, address, county, email, balance, startDate, lastCheckOutDate) values (?, ?, ?, ?, ?, ?, ?)");
		preparedStatement2.setString(1, name);
		preparedStatement2.setString(2, address);
		preparedStatement2.setString(3, county);
		preparedStatement2.setString(4, email);
		preparedStatement2.setDouble(5, 0.0);
		preparedStatement2.setDate(6, startDate);
		preparedStatement2.setDate(7, lastCheckOutDate);
		preparedStatement2.executeUpdate();
  }
   
  public static String queryMatchingItem (String input) throws Exception {	
	  boolean foundMatchingItem = false;
	  Class.forName("com.mysql.jdbc.Driver"); //Loading the driver			
	  con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
	  preparedStatement2 = con.prepareStatement("select * from inventory");
	  ResultSet result = preparedStatement2.executeQuery();
	  result.beforeFirst();
	  String strReturn = "";
	  while(result.next()) {
		  if ((result.getString("title").toLowerCase()).contains(input.toLowerCase()) || (result.getString("author").toLowerCase()).contains(input.toLowerCase()) || (result.getString("itemID").equalsIgnoreCase(input)))  {	  
			  foundMatchingItem = true;
			  strReturn += Member.outputMatchingItem(result.getString("itemID"), result.getString("title"), result.getString("author"), result.getString("itemType"), result.getString("status"), result.getString("location"));
		  }
	  }
	  if (foundMatchingItem == false) {
		  	  return Member.printNoMatchingResult();
	  }
	  
	  return strReturn;
  }

/////////// Member methods end//////////////////////    
  
/////////// Admin methods start//////////////////////  
  
  public int getTotalByType(String type) throws Exception {
		int totalNum = 0;
			preparedStatement = con.prepareStatement("select count(itemID) as total from inventory where itemType=? OR subType=?");
			preparedStatement.setString(1, type);
			preparedStatement.setString(2, type);
			ResultSet result = preparedStatement.executeQuery();
			result.beforeFirst();
			while(result.next()){
				totalNum = totalNum + result.getInt("total");
			}
			return totalNum;
	}
	
  	
  	public int queryNumOfMember() throws Exception {
  		int totalNum = 0;
		preparedStatement = con.prepareStatement("select count(memberID) as total from member");
		ResultSet result = preparedStatement.executeQuery();
		result.beforeFirst();
		while(result.next()){
			totalNum = totalNum + result.getInt("total");
		}
		return totalNum;
  	}
  	
  	
  	public int queryCurrentOutItems () throws Exception {
  		int totalNum = 0;
		preparedStatement = con.prepareStatement("select count(itemID) as total from inventory where status =? ");
		preparedStatement.setString(1, "BORROWED");
		ResultSet result = preparedStatement.executeQuery();
		result.beforeFirst();
		while(result.next()){
			totalNum = totalNum + result.getInt("total");
		}
		return totalNum;
  	}
  	
  	public double getTotalFines(java.sql.Date sqlStartDate, java.sql.Date sqlEndDate) throws Exception {
  			double amount = 0.0;
  			preparedStatement = con.prepareStatement("select payment from transactionhistory where transactionType=? and date >= ? and date <=?");
  			preparedStatement.setString(1, "PAYBALANCE");
  			preparedStatement.setDate(2, sqlStartDate);
  			preparedStatement.setDate(3, sqlEndDate);
  			ResultSet result = preparedStatement.executeQuery();
  			result.beforeFirst();
  			while(result.next()){
  					amount = amount + result.getDouble("payment");
  			}
  			return amount;
  	}
  	
  	
  	public String queryMaxCheckedOutBook(java.sql.Date sqlStartDate, java.sql.Date sqlEndDate) throws Exception {
  		String title = new String();
  		String itemId = new String();
  		int maxCheckedOut = 0;
  		ResultSet resultA;
  		ResultSet resultB;
  		ResultSet resultC;
  		
  		preparedStatement = con.prepareStatement("SELECT DISTINCT transactionhistory.itemID AS itemID, inventory.title AS title FROM transactionhistory INNER JOIN inventory WHERE transactionhistory.itemID = inventory.itemID AND transactionhistory.transactionType =? AND transactionhistory.date >= ? AND transactionhistory.date <= ? AND (inventory.itemType = ? OR inventory.itemType =?) ");
  		preparedStatement.setString(1, "BORROW");
  		preparedStatement.setDate(2, sqlStartDate);
  		preparedStatement.setDate(3, sqlEndDate);
  		preparedStatement.setString(4, "FICTION");
  		preparedStatement.setString(5, "NON-FICTION");
  		ResultSet result1 = preparedStatement.executeQuery(); //result1 holds the deduped list of Book ID and title
  		result1.beforeFirst();
  		while(result1.next()){
  				itemId = result1.getString("itemID");
  				title = result1.getString("title");
  				//System.out.printf("itemID: %s%n" , itemId);
  				//System.out.printf("title: %s%n" , title);
  				preparedStatementA = con.prepareStatement("select count(itemID) AS COUNT from transactionhistory where itemID=? and transactionType=? and date >=? and date <= ? ");
  		  		preparedStatementA.setString(1, itemId);
  		  		preparedStatementA.setString(2, "BORROW");
  		  		preparedStatementA.setDate(3, sqlStartDate);
  		  		preparedStatementA.setDate(4, sqlEndDate);
  		  		resultA = preparedStatementA.executeQuery(); //resultA holds count of a particular book
  		  		resultA.beforeFirst();
  		  		while(resultA.next()){
  		  				//System.out.println("count = " + resultA.getInt("COUNT"));
  		  				preparedStatementB = con.prepareStatement("insert into numofcheckedoutspan (title, numOfCheckedOut) values (?, ?)");
  		  				preparedStatementB.setString(1, title);
  		  				preparedStatementB.setInt(2, resultA.getInt("count"));
  		  				preparedStatementB.executeUpdate();
  		  		}
  		}
  		preparedStatementB = con.prepareStatement("select MAX(numOfCheckedOut) AS max from numofcheckedoutspan");
  		resultB = preparedStatementB.executeQuery();
  		resultB.beforeFirst();
  		while(resultB.next()) {
  				maxCheckedOut = resultB.getInt("max");
		}				
  		
  		preparedStatementC = con.prepareStatement("select title, numOfCheckedOut from numofcheckedoutspan");
  		resultC = preparedStatementC.executeQuery();
  		resultC.beforeFirst();
  		while(resultC.next()) {
  				title = resultC.getString("title");
  				if (resultC.getInt("numOfCheckedOut") == maxCheckedOut) {
  						return Admin.outputMaxCheckedOutBook(title, maxCheckedOut); 
  				}
  		}	
  		preparedStatementC = con.prepareStatement("delete from numofcheckedoutspan");
  		preparedStatementC.executeUpdate();
  		
  		return "Error, no max";
  	
  	}
  	
  	public boolean queryItemStatus(String itemId) throws Exception {
  		boolean availability = true;
  		preparedStatement = con.prepareStatement("SELECT status from inventory where itemID=?");
  		preparedStatement.setString(1, itemId);
  		ResultSet result = preparedStatement.executeQuery();
  		result.beforeFirst();
  		while(result.next()) {
  				if (result.getString("status").equals("BORROWED")){
  						availability = false;
  				}
  		}
  		return availability;
  	}
  	
  	
  	public void issueFine(int memberId, String itemId) throws Exception {
  		double price = 0.0;
  		preparedStatement = con.prepareStatement("SELECT price from inventory where itemID=?");
  		preparedStatement.setString(1, itemId);
  		ResultSet result = preparedStatement.executeQuery();
  		result.beforeFirst();
  		while(result.next()) {
  				price = result.getDouble("price");
  		}
  		
  		preparedStatementA = con.prepareStatement("update member set balance=? where memberID=?");
  		preparedStatementA.setDouble(1, price);
  		preparedStatementA.setInt(2, memberId);
  		preparedStatementA.executeUpdate();
  		System.out.println("A fine for the lost item has been issued to your account. Amount: $" + price);
  	}
  	
  	
  	public void deleteLostItem(String itemId) throws Exception {
  		preparedStatement = con.prepareStatement("delete from inventory where itemID=?");
  		preparedStatement.setString(1, itemId);
  		preparedStatement.executeUpdate();
  	}
  	
  	public void updateItemStatus(String itemId) throws Exception {
  		preparedStatement = con.prepareStatement("update inventory set status=? where itemID=?");
  		preparedStatement.setString(1, "LOST");
  		preparedStatement.setString(2, itemId);
  		preparedStatement.executeUpdate();
  	}
  	
  	
  	public String getMemberTransactionHistory(int memberId, java.sql.Date sqlStartDate, java.sql.Date sqlEndDate) throws Exception {
  		preparedStatement = con.prepareStatement("SELECT * from transactionhistory where memberID=? and date >= ? and date <=?");
  		preparedStatement.setInt(1, memberId);
  		preparedStatement.setDate(2, sqlStartDate);
  		preparedStatement.setDate(3, sqlEndDate);
  		ResultSet result = preparedStatement.executeQuery();
  		result.beforeFirst();
  		String strReturn = "";
  		while(result.next()){
  				strReturn += Member.outputMemberTransactionHistory(result.getInt("memberID"), result.getString("itemId"), result.getString("transactionType"), result.getDate("date"), result.getDouble("payment"));
  		}
  		
  		return strReturn;
  	}
  	
  	public Map<String, Integer> getNumOfAllTypes () throws Exception {
  		Map<String, Integer> listKeyVal = new HashMap<String, Integer>();
  		List<String>  typeList = new ArrayList<String>();
  		preparedStatement = con.prepareStatement("SELECT DISTINCT itemType FROM  `inventory` ");
  		ResultSet result = preparedStatement.executeQuery();
  		result.beforeFirst();
  		while (result.next()) {
  			//if (!(result.getString("itemType").equals("NON-FICTION"))){
  				typeList.add(result.getString("itemType"));
  			//}
  			//else {
  			//	typeList.add(result.getString("subType"));
  			//}
  		}

  		for (Object key : typeList) {
  				int val = this.getTotalByType((String)key);
  				//System.out.println("type: " + (String)key + "; val: " + val);
  				//System.out.printf("%n");
  				listKeyVal.put((String)key, val);
  		}
  		return listKeyVal;
  	}
  	
  	
	public static void disconnect() throws Exception {
		if (con != null) {
			con.close();
		}
	}
}
