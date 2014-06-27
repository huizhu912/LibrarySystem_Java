package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LibraryPanelPayFinesCard extends JPanel
	implements ActionListener
{
	private static final String CONFIRM_BUTTON = "Confirm";
	private static final String CANCEL_BUTTON = "Cancel";
	
	private LibraryFrame m_lfFrame;
	private JTextField m_jtfFirstName;
	private JTextField m_jtfLastName;
	private JTextField m_jtfCardNumber;
	private JTextField m_jtfAddress1;
	private JTextField m_jtfAddress2;
	
	public LibraryPanelPayFinesCard(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		final JLabel jlStaticText1 = new JLabel("First Name on Card:");
		jlStaticText1.setAlignmentX(LEFT_ALIGNMENT);
		this.add(jlStaticText1);
		
		m_jtfFirstName = new JTextField();
		m_jtfFirstName.setMaximumSize(new Dimension(200, 20));
		m_jtfFirstName.setAlignmentX(LEFT_ALIGNMENT);
		this.add(m_jtfFirstName);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText3 = new JLabel("Last Name on Card:");
		jlStaticText3.setAlignmentX(LEFT_ALIGNMENT);
		this.add(jlStaticText3);
		
		m_jtfLastName = new JTextField();
		m_jtfLastName.setMaximumSize(new Dimension(200, 20));
		m_jtfLastName.setAlignmentX(LEFT_ALIGNMENT);
		this.add(m_jtfLastName);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText4 = new JLabel("Card Number:");
		jlStaticText4.setAlignmentX(LEFT_ALIGNMENT);
		this.add(jlStaticText4);
		
		m_jtfCardNumber = new JTextField();
		m_jtfCardNumber.setMaximumSize(new Dimension(200, 20));
		m_jtfCardNumber.setAlignmentX(LEFT_ALIGNMENT);
		this.add(m_jtfCardNumber);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText5 = new JLabel("Address of Card Holder:");
		jlStaticText5.setAlignmentX(LEFT_ALIGNMENT);
		this.add(jlStaticText5);
		
		m_jtfAddress1 = new JTextField();
		m_jtfAddress1.setMaximumSize(new Dimension(200, 20));
		m_jtfAddress1.setAlignmentX(LEFT_ALIGNMENT);
		this.add(m_jtfAddress1);
		
		m_jtfAddress2 = new JTextField();
		m_jtfAddress2.setMaximumSize(new Dimension(200, 20));
		m_jtfAddress2.setAlignmentX(LEFT_ALIGNMENT);
		this.add(m_jtfAddress2);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		JButton jbOk = new JButton(CONFIRM_BUTTON);
		jbOk.setMaximumSize(new Dimension(100, 20));
		jbOk.setAlignmentX(LEFT_ALIGNMENT);
		jbOk.addActionListener(this);
		this.add(jbOk);
		
		this.add(Box.createRigidArea(new Dimension(20, 10)));
		
		JButton jbCancel = new JButton(CANCEL_BUTTON);
		jbCancel.setMaximumSize(new Dimension(100, 20));
		jbCancel.setAlignmentX(LEFT_ALIGNMENT);
		jbCancel.addActionListener(this);
		this.add(jbCancel);
		
		this.add(Box.createVerticalGlue());
	}
	
	public void reset()
	{
		m_jtfFirstName.setText("");
		m_jtfLastName.setText("");
		m_jtfCardNumber.setText("");
		m_jtfAddress1.setText("");
		m_jtfAddress2.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(CONFIRM_BUTTON))
		{
			try
			{
				m_lfFrame.getCurrentUser().payFineAmount();
			}
			catch (Exception e)
			{
				Toolkit.getDefaultToolkit().beep();
			}
			
			m_lfFrame.showPanelPayFinesCardConfirmation();
		}
		else if (strCmd.equals(CANCEL_BUTTON))
			m_lfFrame.showPanelViewFines();
	}
}
