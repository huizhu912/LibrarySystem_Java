package exception;

import java.util.Calendar;
import java.util.Date;

public class invalidMemberID extends Exception {	

		public invalidMemberID() { }
		
		public String toString() { 
			return "You have entered an invalid member ID";
		}
}