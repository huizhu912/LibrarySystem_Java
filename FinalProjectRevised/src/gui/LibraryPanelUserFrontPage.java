package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import control.VerifySystem;

public class LibraryPanelUserFrontPage extends JPanel
	implements ActionListener
{
	private static final String BOOK_QUERY_BUTTON = "Book Operations";
	/*
	private static final String BOOK_RESERVE_BUTTON = "Reserve a Book";
	private static final String CHECK_OUT_BUTTON = "Check Out";
	private static final String BOOK_RETURN_BUTTON = "Return Books";
	*/
	private static final String VIEW_PAY_FINES_BUTTON = "View or Pay Outstanding Fines";
	private static final String TRANSACTION_HISTORY_BUTTON = "Transaction History";
	private static final String LOGOUT_BUTTON = "Logout";
	
	private LibraryFrame m_lfFrame;
	private LibraryPanelBanner m_lpbBanner;
	
	public LibraryPanelUserFrontPage(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		m_lpbBanner = new LibraryPanelBanner();
		this.add(m_lpbBanner);
		
		this.add(Box.createVerticalGlue());
		
		JButton jbBookQuery = new JButton(BOOK_QUERY_BUTTON);
		jbBookQuery.setPreferredSize(new Dimension(300, 50));
		jbBookQuery.setMaximumSize(new Dimension(300, 50));
		jbBookQuery.setAlignmentX(CENTER_ALIGNMENT);
		jbBookQuery.addActionListener(this);
		this.add(jbBookQuery);
		
		/*
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbBookReserve = new JButton(BOOK_RESERVE_BUTTON);
		jbBookReserve.setPreferredSize(new Dimension(300, 50));
		jbBookReserve.setMaximumSize(new Dimension(300, 50));
		jbBookReserve.setAlignmentX(CENTER_ALIGNMENT);
		jbBookReserve.addActionListener(this);
		this.add(jbBookReserve);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbCheckOut = new JButton(CHECK_OUT_BUTTON);
		jbCheckOut.setPreferredSize(new Dimension(300, 50));
		jbCheckOut.setMaximumSize(new Dimension(300, 50));
		jbCheckOut.setAlignmentX(CENTER_ALIGNMENT);
		jbCheckOut.addActionListener(this);
		this.add(jbCheckOut);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbBookReturn = new JButton(BOOK_RETURN_BUTTON);
		jbBookReturn.setPreferredSize(new Dimension(300, 50));
		jbBookReturn.setMaximumSize(new Dimension(300, 50));
		jbBookReturn.setAlignmentX(CENTER_ALIGNMENT);
		jbBookReturn.addActionListener(this);
		this.add(jbBookReturn);
		*/
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbFines = new JButton(TRANSACTION_HISTORY_BUTTON);
		jbFines.setPreferredSize(new Dimension(300, 50));
		jbFines.setMaximumSize(new Dimension(300, 50));
		jbFines.setAlignmentX(CENTER_ALIGNMENT);
		jbFines.addActionListener(this);
		this.add(jbFines);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbHistory = new JButton(VIEW_PAY_FINES_BUTTON);
		jbHistory.setPreferredSize(new Dimension(300, 50));
		jbHistory.setMaximumSize(new Dimension(300, 50));
		jbHistory.setAlignmentX(CENTER_ALIGNMENT);
		jbHistory.addActionListener(this);
		this.add(jbHistory);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbLogout = new JButton(LOGOUT_BUTTON);
		jbLogout.setPreferredSize(new Dimension(300, 50));
		jbLogout.setMaximumSize(new Dimension(300, 50));
		jbLogout.setAlignmentX(CENTER_ALIGNMENT);
		jbLogout.addActionListener(this);
		this.add(jbLogout);
		
		this.add(Box.createVerticalGlue());
	}
	
	public void reset()
	{
		m_lpbBanner.startTimer();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(BOOK_QUERY_BUTTON))
		{
			m_lfFrame.showPanelBookSearch(true);
			m_lpbBanner.stopTimer();
		}
		/*
		else if (strCmd.equals(BOOK_RESERVE_BUTTON))
			m_lfFrame.showPanelBookReserve(true);
		else if (strCmd.equals(CHECK_OUT_BUTTON))
			m_lfFrame.showPanelBookCheckOut(true);
		else if (strCmd.equals(BOOK_RETURN_BUTTON))
			m_lfFrame.showPanelBookReturn(true);
		*/
		else if (strCmd.equals(TRANSACTION_HISTORY_BUTTON))
		{
			m_lfFrame.showPanelTransactionHistory(true);
			m_lpbBanner.stopTimer();
		}
		else if (strCmd.equals(VIEW_PAY_FINES_BUTTON))
		{
			m_lfFrame.showPanelViewFines();
			m_lpbBanner.stopTimer();
		}
		else if (strCmd.equals(LOGOUT_BUTTON))
		{
			m_lfFrame.showPanelLogin();
			VerifySystem.resetCount();
			m_lpbBanner.stopTimer();
		}
	}
}