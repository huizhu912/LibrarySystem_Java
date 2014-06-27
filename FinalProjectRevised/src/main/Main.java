package main;
import javax.swing.JFrame;

import exception.hasOverDueItems;
import exception.invalidCheckOutDate;
import exception.invalidItemCount;
import member.Member;
import admin.Admin;
import control.Database;
import inventory.Item;
import gui.*;

public class Main {
	public static void main(String[] args) throws Exception {
		try {
			 //Member.memberLogIn("10004");
			 //Member.register("Tracy Hudson", "address6", "Sunnyvale", "test@scu.edu");
			 //Member.clearMembershipFee(); //the feature used by non-local resident when sing up for membership
			 //Member.register("Tracy Hudson", "address6", "Sunnyvale", "test@scu.edu");
			 //Member.searchForItem("western");
			 //new Admin("coen275", "summer2013");
		
				LibraryFrame app = new LibraryFrame();
				app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		}
		//catch (invalidCheckOutDate e) {
		//	System.out.println(e);
		//}
		//catch (hasOverDueItems e) {
		//	System.out.println(e);
		//}
		//catch (invalidItemCount e) {
		//	System.out.println(e);
		//}
		catch (Exception e) {
			System.out.println(e);
		}
		//catch (invalidMemberID e) {
		//System.out.println(e);
	    //}
		//catch (invalidItemID e){
			//System.out.println(e);
		//}
		finally {
			Database.disconnect();
		}
	}
}
