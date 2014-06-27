package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import member.Member;

public class LibraryPanelViewFines extends JPanel
	implements ActionListener
{
	private static final String CASH_PAY_BUTTON = "Pay by Cash";
	private static final String CARD_PAY_BUTTON = "Pay by Card";
	private static final String CANCEL_BUTTON = "Cancel";
	
	private LibraryFrame m_lfFrame;
	
	private JLabel jlBalance;
	
	public LibraryPanelViewFines(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createVerticalGlue());
		
		final JLabel jlStaticText1 = new JLabel("Current Outstanding Balance:");
		jlStaticText1.setFont(new Font(jlStaticText1.getFont().getName(), jlStaticText1.getFont().getStyle(), 30));
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		jlBalance = new JLabel("0.00");
		jlBalance.setFont(new Font(jlBalance.getFont().getName(), jlBalance.getFont().getStyle(), 30));
		jlBalance.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlBalance);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbFines = new JButton(CASH_PAY_BUTTON);
		jbFines.setPreferredSize(new Dimension(300, 50));
		jbFines.setMaximumSize(new Dimension(300, 50));
		jbFines.setAlignmentX(CENTER_ALIGNMENT);
		jbFines.addActionListener(this);
		this.add(jbFines);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbHistory = new JButton(CARD_PAY_BUTTON);
		jbHistory.setPreferredSize(new Dimension(300, 50));
		jbHistory.setMaximumSize(new Dimension(300, 50));
		jbHistory.setAlignmentX(CENTER_ALIGNMENT);
		jbHistory.addActionListener(this);
		this.add(jbHistory);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbLogout = new JButton(CANCEL_BUTTON);
		jbLogout.setPreferredSize(new Dimension(300, 50));
		jbLogout.setMaximumSize(new Dimension(300, 50));
		jbLogout.setAlignmentX(CENTER_ALIGNMENT);
		jbLogout.addActionListener(this);
		this.add(jbLogout);
		
		this.add(Box.createVerticalGlue());
	}
	
	public void setBalance()
	{
		Member mCurrentUser = m_lfFrame.getCurrentUser();
		
		if (mCurrentUser != null)
			jlBalance.setText(mCurrentUser.getBalance().toString());
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(CASH_PAY_BUTTON))
			m_lfFrame.showPanelPayFinesCash();
		else if (strCmd.equals(CARD_PAY_BUTTON))
			m_lfFrame.showPanelPayFinesCard();
		else if (strCmd.equals(CANCEL_BUTTON))
			m_lfFrame.showPanelUserFrontPage();
	}
}
