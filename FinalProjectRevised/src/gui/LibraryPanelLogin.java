package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import admin.Admin;

import member.Member;

public class LibraryPanelLogin extends JPanel
	implements ActionListener
{
	private final static String LOGIN_BUTTON = "Login";
	private final static String REGISTER_BUTTON = "Register";
	private final static String SEARCH_BUTTON = "Search";
	private final static String INVALID_LOGIN_TEXT = "Invalid Login Credentials";
	
	private LibraryFrame m_lfFrame;
	private JTextField m_jtfLoginName;
	private JTextField m_jtfAdminPassword;
	private JLabel m_jlInvalidCredentials;
	private LibraryPanelBanner m_lpbBanner;
	
	public LibraryPanelLogin(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		m_lpbBanner = new LibraryPanelBanner();
		this.add(m_lpbBanner);
		m_lpbBanner.startTimer();
		
		this.add(Box.createVerticalGlue());
		
		final JLabel jlStaticText1 = new JLabel("User Name:");
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		m_jtfLoginName = new JTextField();
		m_jtfLoginName.setMaximumSize(new Dimension(200, 20));
		m_jtfLoginName.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfLoginName);
		
		this.add(Box.createRigidArea(new Dimension(20, 10)));
		
		final JLabel jlStaticText2 = new JLabel("Password (Admin's Only):");
		jlStaticText2.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText2);
		
		m_jtfAdminPassword = new JTextField();
		m_jtfAdminPassword.setMaximumSize(new Dimension(200, 20));
		m_jtfAdminPassword.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfAdminPassword);
		
		this.add(Box.createRigidArea(new Dimension(20, 30)));
		
		JButton jbLoginOk = new JButton(LOGIN_BUTTON);
		jbLoginOk.setMaximumSize(new Dimension(100, 20));
		jbLoginOk.setAlignmentX(CENTER_ALIGNMENT);
		jbLoginOk.addActionListener(this);
		this.add(jbLoginOk);
		
		this.add(Box.createRigidArea(new Dimension(20, 10)));
		
		JButton jbNewMember = new JButton(REGISTER_BUTTON);
		jbNewMember.setMaximumSize(new Dimension(100, 20));
		jbNewMember.setAlignmentX(CENTER_ALIGNMENT);
		jbNewMember.addActionListener(this);
		this.add(jbNewMember);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		final JLabel jlStaticText3 = new JLabel("Book Query Without Logging in");
		jlStaticText3.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText3);
		
		JButton jbSearchWithoutLogin = new JButton(SEARCH_BUTTON);
		jbSearchWithoutLogin.setMaximumSize(new Dimension(100, 20));
		jbSearchWithoutLogin.setAlignmentX(CENTER_ALIGNMENT);
		jbSearchWithoutLogin.addActionListener(this);
		this.add(jbSearchWithoutLogin);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		m_jlInvalidCredentials = new JLabel(" ");
		m_jlInvalidCredentials.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jlInvalidCredentials);
		
		this.add(Box.createVerticalGlue());
	}
	
	public void reset()
	{
		m_jtfLoginName.setText("");
		m_jtfAdminPassword.setText("");
		m_jlInvalidCredentials.setText(" ");
		m_lpbBanner.startTimer();
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(LOGIN_BUTTON))
		{
			// verify login TBI
			if (m_jtfAdminPassword.getText().equals(""))
			{
				try
				{
					m_lfFrame.setCurrentUser(Member.memberLogIn(m_jtfLoginName.getText()));
				}
				catch (Exception e)
				{
					m_lfFrame.setCurrentUser(null);
					m_jtfLoginName.setText("");
					m_jlInvalidCredentials.setText(INVALID_LOGIN_TEXT);
					return;
				}
				
				m_lfFrame.showPanelUserFrontPage();
				m_lpbBanner.stopTimer();
			}
			else
			{
				try
				{
					m_lfFrame.setCurrentUser(new Admin(m_jtfLoginName.getText(), m_jtfAdminPassword.getText()));
				}
				catch (Exception e)
				{
					m_lfFrame.setCurrentUser(null);
					m_jtfLoginName.setText("");
					m_jtfAdminPassword.setText("");
					m_jlInvalidCredentials.setText(INVALID_LOGIN_TEXT);
					return;
				}
				
				m_lfFrame.showPanelAdminFrontPage();
				m_lpbBanner.stopTimer();
			}
		}
		else if (strCmd.equals(REGISTER_BUTTON))
		{
			m_lfFrame.showPanelRegister();
			m_lpbBanner.stopTimer();
		}
		else if (strCmd.equals(SEARCH_BUTTON))
		{
			m_lfFrame.showPanelBookSearch(true); // TBI
			m_lpbBanner.stopTimer();
		}
	}
}
