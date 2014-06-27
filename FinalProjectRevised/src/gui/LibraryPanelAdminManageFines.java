package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import member.Member;

public class LibraryPanelAdminManageFines extends JPanel
	implements ActionListener
{
	private final static String MANAGE_MEMBER_FINES_BUTTON = "Clear Fines";
	private final static String VIEW_FINE_HISTORY_BUTTON = "View Fine History";
	private final static String BACK_BUTTON = "Back";
	
	private LibraryFrame m_lfFrame;
	private JTextField m_jtfMemberID;
	private JTextField jtfFromDate;
	private JTextField jtfToDate;
	
	public LibraryPanelAdminManageFines(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createVerticalGlue());
		
		final JLabel jlStaticText1 = new JLabel("Member ID:");
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		m_jtfMemberID = new JTextField();
		m_jtfMemberID.setMaximumSize(new Dimension(200, 20));
		m_jtfMemberID.setAlignmentX(CENTER_ALIGNMENT);
		this.add(m_jtfMemberID);
		
		this.add(Box.createRigidArea(new Dimension(20, 10)));
		
		JButton jbMemberFines = new JButton(MANAGE_MEMBER_FINES_BUTTON);
		jbMemberFines.setMaximumSize(new Dimension(100, 20));
		jbMemberFines.setAlignmentX(CENTER_ALIGNMENT);
		jbMemberFines.addActionListener(this);
		this.add(jbMemberFines);
		
		this.add(Box.createRigidArea(new Dimension(20, 60)));
		
		JPanel jpPaneBottom1 = new JPanel();
		BoxLayout blLayoutBottom1 = new BoxLayout(jpPaneBottom1, BoxLayout.X_AXIS);
		jpPaneBottom1.setLayout(blLayoutBottom1);
		
		final JLabel jlStaticText2 = new JLabel("From:");
		jlStaticText2.setAlignmentY(CENTER_ALIGNMENT);
		jpPaneBottom1.add(jlStaticText2);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(10, 20)));
		
		jtfFromDate = new JTextField();
		jtfFromDate.setAlignmentY(CENTER_ALIGNMENT);
		jtfFromDate.setMaximumSize(new Dimension(150, 20));
		jpPaneBottom1.add(jtfFromDate);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText3 = new JLabel("To:");
		jlStaticText3.setAlignmentY(CENTER_ALIGNMENT);
		jpPaneBottom1.add(jlStaticText3);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(10, 20)));
		
		jtfToDate = new JTextField();
		jtfToDate.setAlignmentY(CENTER_ALIGNMENT);
		jtfToDate.setMaximumSize(new Dimension(150, 20));
		jpPaneBottom1.add(jtfToDate);
		
		this.add(jpPaneBottom1);
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbFineHistory = new JButton(VIEW_FINE_HISTORY_BUTTON);
		jbFineHistory.setMaximumSize(new Dimension(100, 20));
		jbFineHistory.setAlignmentX(CENTER_ALIGNMENT);
		jbFineHistory.addActionListener(this);
		this.add(jbFineHistory);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		JButton jbBack = new JButton(BACK_BUTTON);
		jbBack.setMaximumSize(new Dimension(100, 20));
		jbBack.setAlignmentX(CENTER_ALIGNMENT);
		jbBack.addActionListener(this);
		this.add(jbBack);
		
		this.add(Box.createVerticalGlue());
	}
	
	public void reset()
	{
		m_jtfMemberID.setText("");
		jtfFromDate.setText("");
		jtfToDate.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(MANAGE_MEMBER_FINES_BUTTON))
		{
			try
			{
				Member mTempMember = Member.memberLogIn(m_jtfMemberID.getText());
				if (mTempMember != null)
					mTempMember.payFineAmount();
			}
			catch (Exception e)
			{
				Toolkit.getDefaultToolkit().beep();
			}
		}
		else if (strCmd.equals(VIEW_FINE_HISTORY_BUTTON))
		{
			try
			{
				m_lfFrame.showPanelAdminFinesGraph(jtfFromDate.getText(), jtfToDate.getText());
			}
			catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if (strCmd.equals(BACK_BUTTON))
			m_lfFrame.showPanelAdminFrontPage();
	}
}
