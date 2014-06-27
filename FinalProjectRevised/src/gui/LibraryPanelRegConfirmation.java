package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LibraryPanelRegConfirmation extends JPanel
	implements ActionListener
{
	private final static String OK_BUTTON = "OK";
	private final static String DEFAULT_FEE_TEXT = "10.00";
	private final static String COUNTY_FEE_TEXT = "0.00";
	
	private LibraryFrame m_lfFrame;
	private JLabel m_jlBalance;
	
	public LibraryPanelRegConfirmation(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createVerticalGlue());
		
		final JLabel jlStaticText1 = new JLabel("Thank you for registering, please pay at the front desk and collect your member ID");
		jlStaticText1.setFont(new Font(jlStaticText1.getFont().getName(), jlStaticText1.getFont().getStyle(), 40));
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		final JLabel jlStaticText2 = new JLabel("Your outstanding balance is (Membership is free if you are a county resident):");
		jlStaticText2.setFont(new Font(jlStaticText2.getFont().getName(), jlStaticText2.getFont().getStyle(), 20));
		jlStaticText2.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText2);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		m_jlBalance = new JLabel(DEFAULT_FEE_TEXT);
		m_jlBalance.setFont(new Font(m_jlBalance.getFont().getName(), m_jlBalance.getFont().getStyle(), 20));
		m_jlBalance.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jlBalance);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		JButton jbOk = new JButton(OK_BUTTON);
		jbOk.setMaximumSize(new Dimension(100, 20));
		jbOk.setAlignmentX(CENTER_ALIGNMENT);
		jbOk.addActionListener(this);
		this.add(jbOk);
		
		this.add(Box.createVerticalGlue());
	}
	
	public void setBalance(String strBalance)
	{
		m_jlBalance.setText(strBalance);
	}
	
	public void changeFee(boolean bPayFee)
	{
		if (bPayFee)
			m_jlBalance.setText(DEFAULT_FEE_TEXT);
		else
			m_jlBalance.setText(COUNTY_FEE_TEXT);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(OK_BUTTON))
			m_lfFrame.showPanelLogin();
	}
}
