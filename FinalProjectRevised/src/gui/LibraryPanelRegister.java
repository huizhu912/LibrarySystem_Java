package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import member.Member;

public class LibraryPanelRegister extends JPanel
	implements ActionListener
{
	private final static String OK_BUTTON = "OK";
	private final static String CANCEL_BUTTON = "Cancel";
	private final static String NEED_MORE_INFO_TEXT = "Not all required fields filled";
	private final static String DUPLICATE_EMAIL = "Email already in use";
	
	private LibraryFrame m_lfFrame;
	private JTextField m_jtfFirstName;
	private JTextField m_jtfMiddleName;
	private JTextField m_jtfLastName;
	private JTextField m_jtfAddress1;
	private JTextField m_jtfAddress2;
	private JTextField m_jtfCounty;
	private JTextField m_jtfEmail;
	private JLabel m_jlError;
	
	public LibraryPanelRegister(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		final JLabel jlStaticText1 = new JLabel("First Name*:");
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		m_jtfFirstName = new JTextField();
		m_jtfFirstName.setMaximumSize(new Dimension(200, 20));
		m_jtfFirstName.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfFirstName);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText2 = new JLabel("Middle Name:");
		jlStaticText2.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText2);
		
		m_jtfMiddleName = new JTextField();
		m_jtfMiddleName.setMaximumSize(new Dimension(200, 20));
		m_jtfMiddleName.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfMiddleName);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText3 = new JLabel("Last Name*:");
		jlStaticText3.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText3);
		
		m_jtfLastName = new JTextField();
		m_jtfLastName.setMaximumSize(new Dimension(200, 20));
		m_jtfLastName.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfLastName);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText4 = new JLabel("Address*:");
		jlStaticText4.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText4);
		
		m_jtfAddress1 = new JTextField();
		m_jtfAddress1.setMaximumSize(new Dimension(200, 20));
		m_jtfAddress1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfAddress1);
		
		m_jtfAddress2 = new JTextField();
		m_jtfAddress2.setMaximumSize(new Dimension(200, 20));
		m_jtfAddress2.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfAddress2);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText5 = new JLabel("County*:");
		jlStaticText5.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText5);
		
		m_jtfCounty = new JTextField();
		m_jtfCounty.setMaximumSize(new Dimension(200, 20));
		m_jtfCounty.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfCounty);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText6 = new JLabel("Email*:");
		jlStaticText6.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText6);
		
		m_jtfEmail = new JTextField();
		m_jtfEmail.setMaximumSize(new Dimension(200, 20));
		m_jtfEmail.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfEmail);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		JButton jbOk = new JButton(OK_BUTTON);
		jbOk.setMaximumSize(new Dimension(100, 20));
		jbOk.setAlignmentX(CENTER_ALIGNMENT);
		jbOk.addActionListener(this);
		this.add(jbOk);
		
		this.add(Box.createRigidArea(new Dimension(20, 10)));
		
		JButton jbCancel = new JButton(CANCEL_BUTTON);
		jbCancel.setMaximumSize(new Dimension(100, 20));
		jbCancel.setAlignmentX(CENTER_ALIGNMENT);
		jbCancel.addActionListener(this);
		this.add(jbCancel);
		
		this.add(Box.createRigidArea(new Dimension(20, 10)));
		
		m_jlError = new JLabel(" ");
		m_jlError.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jlError);
		
		this.add(Box.createVerticalGlue());
	}
	
	public void reset()
	{
		m_jtfFirstName.setText("");
		m_jtfMiddleName.setText("");
		m_jtfLastName.setText("");
		m_jtfAddress1.setText("");
		m_jtfAddress2.setText("");
		m_jtfCounty.setText("");
		m_jtfEmail.setText("");
		m_jlError.setText(" ");
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(OK_BUTTON))
		{
			if (m_jtfFirstName.getText().equals("") || m_jtfLastName.getText().equals("") ||
				m_jtfAddress1.getText().equals("") || m_jtfCounty.getText().equals("") ||
				m_jtfEmail.getText().equals(""))
			{
				m_jlError.setText(NEED_MORE_INFO_TEXT);
				return;
			}
			
			String strName;
			
			if (!m_jtfMiddleName.getText().equals(""))
				strName = m_jtfFirstName.getText() + " " + m_jtfMiddleName.getText() + " " + m_jtfLastName.getText();
			else
				strName = m_jtfFirstName.getText() + " " + m_jtfLastName.getText();
			
			String strAddress = m_jtfAddress1.getText() + m_jtfAddress2.getText();
			String strCounty = m_jtfCounty.getText();
			
			try
			{
				Member.register(strName, strAddress, strCounty, m_jtfEmail.getText());
				Member.clearMembershipFee();
				Member.register(strName, strAddress, strCounty, m_jtfEmail.getText());
			}
			catch (Exception e)
			{
				m_jlError.setText(DUPLICATE_EMAIL);
				return;
			}
			
			if (strCounty.equals("SpringField"))
				m_lfFrame.showPanelRegConfirmation(false);
			else
				m_lfFrame.showPanelRegConfirmation(true);
		}
		else if (strCmd.equals(CANCEL_BUTTON))
			m_lfFrame.showPanelLogin();
	}
}
