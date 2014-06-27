package exception;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.Calendar;
import java.lang.Exception;

public class invalidCheckOutDate extends Exception {	
		private Date lastCheckOutDate = new Date();
		private Date validCheckOutDate = new Date();
		public static String s;
		public invalidCheckOutDate(Date lastcheckoutdate) { 
			Calendar c = Calendar.getInstance();
			c.setTime(lastcheckoutdate);
			c.add(Calendar.DAY_OF_MONTH, 14);
		    //validCheckOutDate.setTime((c.getTime()).getTime());
		    s = String.format("%tm/%td/%tY", c, c, c);
		}
		
		public String toString() { 
			return "You are not qualified to check out at this moment. The earliest date for you to check out is: " + s;
		}
}

