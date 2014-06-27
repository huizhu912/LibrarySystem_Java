package control;

import java.util.Date;


public interface Transaction  {
	public String checkOut(String itemID)  throws Exception; // input:  JTextField.getText(), takes  String itemID;  output: JTextField.setText(), prints 
	public void returnItem(String itemID)  throws Exception ; // input:  JTextField.getText(), takes String itemID;  output: JTextField.setText(), prints int memberID, String itemID, String itemType, String title, String author, Date sqlDate
	public void reserveItem(String itemID)  throws Exception; // input:  JTextField.getText(), takes String itemID;  output: JTextField.setText(), prints String name, String title, String status, Date date
	public void payFineAmount()  throws Exception; //input:  JTextField.getText(), takes  int memberID, int amount;  output: JTextField.setText(); 
	public String queryMemberTransactionHistory(String startDate, String endDate) throws Exception; 
}
