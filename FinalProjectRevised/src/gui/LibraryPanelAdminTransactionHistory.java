package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import admin.Admin;

public class LibraryPanelAdminTransactionHistory extends JPanel
	implements ActionListener
{
	private final static String INITIAL_TEXT = "Top Check Out's";
	private final static String LOOK_UP_BUTTON = "Look Up";
	private final static String BACK_BUTTON = "Back";
	
	private LibraryFrame m_lfFrame;
	private JTextField jtaDisplayConsole;
	private JTextField jtfFromDate;
	private JTextField jtfToDate;
	
	public LibraryPanelAdminTransactionHistory(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createVerticalGlue());
		
		jtaDisplayConsole = new JTextField(INITIAL_TEXT);
		jtaDisplayConsole.setAlignmentX(CENTER_ALIGNMENT);
		jtaDisplayConsole.setMaximumSize(new Dimension(500, 20));
		jtaDisplayConsole.setEditable(false);
		this.add(jtaDisplayConsole);
		
		this.add(Box.createRigidArea(new Dimension(10, 80)));
		
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
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbSearch = new JButton(LOOK_UP_BUTTON);
		jbSearch.setMaximumSize(new Dimension(100, 20));
		jbSearch.setAlignmentY(CENTER_ALIGNMENT);
		jbSearch.addActionListener(this);
		jpPaneBottom1.add(jbSearch);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(40, 20)));
		
		JButton jbBack = new JButton(BACK_BUTTON);
		jbBack.setMaximumSize(new Dimension(100, 20));
		jbBack.setAlignmentY(CENTER_ALIGNMENT);
		jbBack.addActionListener(this);
		jpPaneBottom1.add(jbBack);
		
		this.add(jpPaneBottom1);
		this.add(Box.createVerticalGlue());
	}
	
	public void reset()
	{
		// TBI
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(LOOK_UP_BUTTON))
		{
			try
			{
				String strTemp = ((Admin) m_lfFrame.getCurrentUser()).getMaxOfCheckedOutBook(jtfFromDate.getText(), jtfToDate.getText());
				jtaDisplayConsole.setText("The most checked out item from " + jtfFromDate.getText() + " to " +
										  jtfToDate.getText() + "is: " + strTemp);
			}
			catch (Exception e)
			{
				jtaDisplayConsole.setText("Error, bad query parameters");
			}
		}
		else if (strCmd.equals(BACK_BUTTON))
			m_lfFrame.showPanelAdminFrontPage();
	}
}
