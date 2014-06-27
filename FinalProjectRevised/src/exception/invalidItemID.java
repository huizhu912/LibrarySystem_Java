package exception;

import java.util.Calendar;
import java.util.Date;

public class invalidItemID extends Exception {	

		public invalidItemID() { }
		
		public String toString() { 
			return "You have entered an invalid item ID";
		}
}