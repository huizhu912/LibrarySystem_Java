package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import admin.Admin;

public class LibraryPanelAdminFrontPage extends JPanel
	implements ActionListener
{
	private static final String BOOK_CATEGORY_COUNT_BUTTON = "Book Count By Category";
	private static final String MEMBER_TOTAL_BUTTON = "Total Members";
	private static final String BOOK_OUT_COUNT_BUTTON = "Checked out Book Count";
	private static final String TRANSACTION_HISTORY_BUTTON = "Top Books";
	private static final String MANAGE_FINES_BUTTON = "Manage Member Fines";
	private static final String LOGOUT_BUTTON = "Logout";
	
	private LibraryFrame m_lfFrame;
	
	public LibraryPanelAdminFrontPage(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createVerticalGlue());
		
		JButton jbBookCategory = new JButton(BOOK_CATEGORY_COUNT_BUTTON);
		jbBookCategory.setPreferredSize(new Dimension(300, 50));
		jbBookCategory.setMaximumSize(new Dimension(300, 50));
		jbBookCategory.setAlignmentX(CENTER_ALIGNMENT);
		jbBookCategory.addActionListener(this);
		this.add(jbBookCategory);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbMemberTotal = new JButton(MEMBER_TOTAL_BUTTON);
		jbMemberTotal.setPreferredSize(new Dimension(300, 50));
		jbMemberTotal.setMaximumSize(new Dimension(300, 50));
		jbMemberTotal.setAlignmentX(CENTER_ALIGNMENT);
		jbMemberTotal.addActionListener(this);
		this.add(jbMemberTotal);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbBookOutCount = new JButton(BOOK_OUT_COUNT_BUTTON);
		jbBookOutCount.setPreferredSize(new Dimension(300, 50));
		jbBookOutCount.setMaximumSize(new Dimension(300, 50));
		jbBookOutCount.setAlignmentX(CENTER_ALIGNMENT);
		jbBookOutCount.addActionListener(this);
		this.add(jbBookOutCount);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbTransactionHistory = new JButton(TRANSACTION_HISTORY_BUTTON);
		jbTransactionHistory.setPreferredSize(new Dimension(300, 50));
		jbTransactionHistory.setMaximumSize(new Dimension(300, 50));
		jbTransactionHistory.setAlignmentX(CENTER_ALIGNMENT);
		jbTransactionHistory.addActionListener(this);
		this.add(jbTransactionHistory);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbManageFines = new JButton(MANAGE_FINES_BUTTON);
		jbManageFines.setPreferredSize(new Dimension(300, 50));
		jbManageFines.setMaximumSize(new Dimension(300, 50));
		jbManageFines.setAlignmentX(CENTER_ALIGNMENT);
		jbManageFines.addActionListener(this);
		this.add(jbManageFines);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbLogout = new JButton(LOGOUT_BUTTON);
		jbLogout.setPreferredSize(new Dimension(300, 50));
		jbLogout.setMaximumSize(new Dimension(300, 50));
		jbLogout.setAlignmentX(CENTER_ALIGNMENT);
		jbLogout.addActionListener(this);
		this.add(jbLogout);
		
		this.add(Box.createVerticalGlue());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(BOOK_CATEGORY_COUNT_BUTTON))
		{
			try
			{
				ArrayList<Integer> arrTemp = ((Admin) m_lfFrame.getCurrentUser()).listNumOfAllTypes();
				m_lfFrame.showPanelAdminBookCount(arrTemp.get(0), arrTemp.get(1), arrTemp.get(2));
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (strCmd.equals(MEMBER_TOTAL_BUTTON))
		{
			try
			{
				m_lfFrame.showPanelAdminMemberCount(((Admin) m_lfFrame.getCurrentUser()).getTotalNumberOfMembers(), 1);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (strCmd.equals(BOOK_OUT_COUNT_BUTTON))
		{
			try
			{
				ArrayList<Integer> arrTemp = ((Admin) m_lfFrame.getCurrentUser()).listNumOfAllTypes();
				int nOutTemp = ((Admin) m_lfFrame.getCurrentUser()).getCurrentOutItems();
				int nInTemp = arrTemp.get(0) + arrTemp.get(1) + arrTemp.get(2) - nOutTemp;
				m_lfFrame.showPanelAdminCheckOutCount(nOutTemp, nInTemp);
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (strCmd.equals(TRANSACTION_HISTORY_BUTTON))
			m_lfFrame.showPanelAdminTransactionHistory();
		else if (strCmd.equals(MANAGE_FINES_BUTTON))
			m_lfFrame.showPanelAdminManageFines();
		else if (strCmd.equals(LOGOUT_BUTTON))
			m_lfFrame.showPanelLogin();
	}
}
