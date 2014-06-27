package exception;


import java.util.Calendar;
import java.util.Date;

import control.VerifySystem;


public class invalidItemCount extends Exception {	
		public String itemType;
		public int limit;
		
		public invalidItemCount(String itemtype) { 
			this.itemType = itemtype;
			if (this.itemType.equals("Fiction")) {
				this.limit = VerifySystem.getMaxF();
			}
			else if (this.itemType.equals("Non-Fiction")) {
				this.limit = VerifySystem.getMaxNF();
			}
			else if (this.itemType.equals("Video")) {
				this.limit = VerifySystem.getMaxV();
			}
		
		}
		
		public String toString() { 
			return "The number of " + this.itemType + " has exceeded the limit of " + this.limit;
		}
}