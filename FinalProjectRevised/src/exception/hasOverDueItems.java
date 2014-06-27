package exception;

import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.sql.*;
import java.util.Calendar;
import java.lang.Exception;

public class hasOverDueItems extends Exception {	
		public Double balance;

		public hasOverDueItems (Double balance) { 
			this.balance = balance;
		}
		
		public String toString() { 
			return "You are not qualified to check out due to the overdue items remained in your account : " + balance;
		}
}

