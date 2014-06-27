package gui;

import java.awt.*;
import member.Member;

import javax.swing.*;

public class LibraryFrame extends JFrame
{
	// Basic
	private final static String LOGIN_PANEL = "Login Panel";
	private final static String REGISTER_PANEL = "Registration Panel";
	private final static String REG_CONFIRMATION_PANEL = "Reg Confirmation Panel";
	
	// Members
	private final static String USER_FRONT_PAGE_PANEL = "User Front Page Panel";
	private final static String BOOK_SEARCH_PANEL = "Book Search Panel";
	private final static String BOOK_RESERVE_PANEL = "Book Reserve Panel";
	private final static String BOOK_CHECKOUT_PANEL = "Book Check Out Panel";
	private final static String BOOK_RETURN_PANEL = "Book Return Panel";
	private final static String TRANSACTION_HISTORY_PANEL = "Transaction History Panel";
	private final static String VIEW_FINES_PANEL = "View Fines Panel";
	private final static String PAY_FINES_CASH_PANEL = "Pay Fines Cash Panel";
	private final static String PAY_FINES_CARD_PANEL = "Pay Fines Card Panel";
	private final static String PAY_FINES_CARD_CONFIRMATION_PANEL = "Pay Fines Card Confirmation Panel";
	
	// Admin
	private final static String ADMIN_FRONT_PAGE_PANEL = "Admin Front Page Panel";
	private final static String ADMIN_BOOK_COUNT_PANEL = "Admin Book Count Panel";
	private final static String ADMIN_MEMBER_COUNT_PANEL = "Admin Member Count Panel";
	private final static String ADMIN_CHECK_OUT_COUNT_PANEL = "Admin Check Out Count Panel";
	private final static String ADMIN_TRANSACTION_HISTORY_PANEL = "Admin Transaction History Panel";
	private final static String ADMIN_MANAGE_FINES_PANEL = "Admin Manage Fines Panel";
	private final static String ADMIN_FINES_GRAPH_PANEL = "Admin Fines Graph Panel";
	
	// Basic
	private JPanel m_jpPanel;
	private JPanel m_jpLogin;
	private JPanel m_jpRegister;
	private JPanel m_jpRegConfirmation;
	
	// Members
	private JPanel m_jpUserFrontPage;
	private JPanel m_jpBookSearch;
	private JPanel m_jpBookReserve;
	private JPanel m_jpBookCheckOut;
	private JPanel m_jpBookReturn;
	private JPanel m_jpTransactionHistory;
	private JPanel m_jpViewFines;
	private JPanel m_jpPayFinesCash;
	private JPanel m_jpPayFinesCard;
	private JPanel m_jpPayFinesCardConfirmation;
	
	// Admin
	private JPanel m_jpAdminFrontPage;
	private JPanel m_jpAdminBookCount;
	private JPanel m_jpAdminMemberCount;
	private JPanel m_jpAdminCheckOutCount;
	private JPanel m_jpAdminTransactionHistory;
	private JPanel m_jpAdminManageFines;
	private JPanel m_jpAdminFineGraph;
	
	private Member m_mCurrentUserID;
	
	public LibraryFrame()
	{
		super("Library Browser");

		m_jpPanel = new JPanel(new CardLayout());
		
		m_mCurrentUserID = null;
		
		// Basic
		m_jpLogin = new LibraryPanelLogin(this);
		m_jpRegister = new LibraryPanelRegister(this);
		m_jpRegConfirmation =  new LibraryPanelRegConfirmation(this);
		
		// Member
		m_jpUserFrontPage =  new LibraryPanelUserFrontPage(this);
		m_jpBookSearch = new LibraryPanelBookSearch(this);
		m_jpBookReserve = new LibraryPanelBookReserve(this);
		m_jpBookCheckOut = new LibraryPanelCheckOut(this);
		m_jpBookReturn = new LibraryPanelBookReturn(this);
		m_jpTransactionHistory = new LibraryPanelTransactionHistory(this);
		m_jpViewFines = new LibraryPanelViewFines(this);
		m_jpPayFinesCash = new LibraryPanelPayFinesCash(this);
		m_jpPayFinesCard = new LibraryPanelPayFinesCard(this);
		m_jpPayFinesCardConfirmation = new LibraryPanelPayFinesCardConfirmation(this);
		
		// Admin
		m_jpAdminFrontPage = new LibraryPanelAdminFrontPage(this);
		m_jpAdminBookCount = new LibraryPanelAdminBookCount(this);
		m_jpAdminMemberCount = new LibraryPanelAdminMemberCount(this);
		m_jpAdminCheckOutCount = new LibraryPanelAdminCheckOutCount(this);
		m_jpAdminTransactionHistory = new LibraryPanelAdminTransactionHistory(this);
		m_jpAdminManageFines = new LibraryPanelAdminManageFines(this);
		m_jpAdminFineGraph = new LibraryPanelAdminFinesGraph(this);
		
		// Basic
		m_jpPanel.add(m_jpLogin, LOGIN_PANEL);
		m_jpPanel.add(m_jpRegister, REGISTER_PANEL);
		m_jpPanel.add(m_jpRegConfirmation, REG_CONFIRMATION_PANEL);
		
		// Member
		m_jpPanel.add(m_jpUserFrontPage, USER_FRONT_PAGE_PANEL);
		m_jpPanel.add(m_jpBookSearch, BOOK_SEARCH_PANEL);
		m_jpPanel.add(m_jpBookReserve, BOOK_RESERVE_PANEL);
		m_jpPanel.add(m_jpBookCheckOut, BOOK_CHECKOUT_PANEL);
		m_jpPanel.add(m_jpBookReturn, BOOK_RETURN_PANEL);
		m_jpPanel.add(m_jpTransactionHistory, TRANSACTION_HISTORY_PANEL);
		m_jpPanel.add(m_jpViewFines, VIEW_FINES_PANEL);
		m_jpPanel.add(m_jpPayFinesCash, PAY_FINES_CASH_PANEL);
		m_jpPanel.add(m_jpPayFinesCard, PAY_FINES_CARD_PANEL);
		m_jpPanel.add(m_jpPayFinesCardConfirmation, PAY_FINES_CARD_CONFIRMATION_PANEL);
		
		// Admin
		m_jpPanel.add(m_jpAdminFrontPage, ADMIN_FRONT_PAGE_PANEL);
		m_jpPanel.add(m_jpAdminBookCount, ADMIN_BOOK_COUNT_PANEL);
		m_jpPanel.add(m_jpAdminMemberCount, ADMIN_MEMBER_COUNT_PANEL);
		m_jpPanel.add(m_jpAdminCheckOutCount, ADMIN_CHECK_OUT_COUNT_PANEL);
		m_jpPanel.add(m_jpAdminTransactionHistory, ADMIN_TRANSACTION_HISTORY_PANEL);
		m_jpPanel.add(m_jpAdminManageFines, ADMIN_MANAGE_FINES_PANEL);
		m_jpPanel.add(m_jpAdminFineGraph, ADMIN_FINES_GRAPH_PANEL);
		
		setSize(1000,700);
		setContentPane(m_jpPanel);
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, LOGIN_PANEL);
		setVisible(true);
	}
	
	public Member getCurrentUser()
	{
		return m_mCurrentUserID;
	}
	
	public void setCurrentUser(Member mCurrentUserID)
	{
		m_mCurrentUserID = mCurrentUserID;
	}
	
	// ----------------------------------------- Basic Show Panels -----------------------------------------
	
	public void showPanelLogin()
	{
		m_mCurrentUserID = null;
		((LibraryPanelLogin) m_jpLogin).reset();
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, LOGIN_PANEL);
	}
	
	public void showPanelRegister()
	{
		((LibraryPanelRegister) m_jpRegister).reset();
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, REGISTER_PANEL);
	}
	
	public void showPanelRegConfirmation(boolean bPayFee)
	{
		((LibraryPanelRegConfirmation) m_jpRegConfirmation).changeFee(bPayFee);
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, REG_CONFIRMATION_PANEL);
	}
	
	// ----------------------------------------- Member Show Panels -----------------------------------------
	
	public void showPanelUserFrontPage()
	{
		((LibraryPanelUserFrontPage) m_jpUserFrontPage).reset();
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, USER_FRONT_PAGE_PANEL);
	}
	
	public void showPanelBookSearch(boolean bReset)
	{
		if (bReset)
			((LibraryPanelBookSearch) m_jpBookSearch).reset();
		
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, BOOK_SEARCH_PANEL);
	}
	
	public void showPanelBookReserve(boolean bReset)
	{
		if (bReset)
			((LibraryPanelBookReserve) m_jpBookReserve).reset();
		
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, BOOK_RESERVE_PANEL);
	}
	
	public void showPanelBookCheckOut(boolean bReset)
	{
		if (bReset)
			((LibraryPanelCheckOut) m_jpBookCheckOut).reset();
		
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, BOOK_CHECKOUT_PANEL);
	}
	
	public void showPanelBookReturn(boolean bReset)
	{
		if (bReset)
			((LibraryPanelBookReturn) m_jpBookReturn).reset();
		
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, BOOK_RETURN_PANEL);
	}
	
	public void showPanelTransactionHistory(boolean bReset)
	{
		if (bReset)
			((LibraryPanelTransactionHistory) m_jpTransactionHistory).reset();
		
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, TRANSACTION_HISTORY_PANEL);
	}
	
	public void showPanelViewFines()
	{
		((LibraryPanelViewFines) m_jpViewFines).setBalance();
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, VIEW_FINES_PANEL);
	}
	
	public void showPanelPayFinesCash()
	{
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, PAY_FINES_CASH_PANEL);
	}
	
	public void showPanelPayFinesCard()
	{
		((LibraryPanelPayFinesCard) m_jpPayFinesCard).reset();
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, PAY_FINES_CARD_PANEL);
	}
	
	public void showPanelPayFinesCardConfirmation()
	{
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, PAY_FINES_CARD_CONFIRMATION_PANEL);
	}
	
	// ----------------------------------------- Admin Show Panels -----------------------------------------
	
	public void showPanelAdminFrontPage()
	{
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, ADMIN_FRONT_PAGE_PANEL);
	}
	
	public void showPanelAdminBookCount(int nEng, int nFic, int nVid) throws InterruptedException
	{
		((LibraryPanelAdminBookCount) m_jpAdminBookCount).reset();
		((LibraryPanelAdminBookCount) m_jpAdminBookCount).setParameters(nEng, nFic, nVid);
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, ADMIN_BOOK_COUNT_PANEL);
		((LibraryPanelAdminBookCount) m_jpAdminBookCount).animate();
	}
	
	public void showPanelAdminMemberCount(int nUser, int nAdmin) throws InterruptedException
	{
		((LibraryPanelAdminMemberCount) m_jpAdminMemberCount).reset();
		((LibraryPanelAdminMemberCount) m_jpAdminMemberCount).setParameters(nUser, nAdmin);
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, ADMIN_MEMBER_COUNT_PANEL);
		((LibraryPanelAdminMemberCount) m_jpAdminMemberCount).animate();
	}
	
	public void showPanelAdminCheckOutCount(int nOut, int nIn) throws InterruptedException
	{
		((LibraryPanelAdminCheckOutCount) m_jpAdminCheckOutCount).reset();
		((LibraryPanelAdminCheckOutCount) m_jpAdminCheckOutCount).setParameters(nOut, nIn);
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, ADMIN_CHECK_OUT_COUNT_PANEL);
		((LibraryPanelAdminCheckOutCount) m_jpAdminCheckOutCount).animate();
	}

	public void showPanelAdminTransactionHistory()
	{
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, ADMIN_TRANSACTION_HISTORY_PANEL);
	}
	
	public void showPanelAdminManageFines()
	{
		((LibraryPanelAdminManageFines) m_jpAdminManageFines).reset();
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, ADMIN_MANAGE_FINES_PANEL);
	}
	
	public void showPanelAdminFinesGraph(String strFrom, String strTo) throws Exception
	{
		((LibraryPanelAdminFinesGraph) m_jpAdminFineGraph).reset(strFrom, strTo);
		((CardLayout)m_jpPanel.getLayout()).show(m_jpPanel, ADMIN_FINES_GRAPH_PANEL);
	}
	
	//public static void main(String[] args)
	//{
	//	LibraryFrame app = new LibraryFrame();
	//	app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	//}
}
