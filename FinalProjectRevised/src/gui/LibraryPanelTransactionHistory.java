package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LibraryPanelTransactionHistory extends JPanel
	implements ActionListener
{
	private final static String INITIAL_TEXT = "Search Transaction History (yyyy-MM-dd)";
	private final static String LOOK_UP_BUTTON = "Look Up";
	private final static String BACK_BUTTON = "Back";
	
	private LibraryFrame m_lfFrame;
	private JTextArea jtaDisplayConsole;
	private JTextField jtfFromDate;
	private JTextField jtfToDate;
	
	public LibraryPanelTransactionHistory(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createRigidArea(new Dimension(20, 20)));
		
		jtaDisplayConsole = new JTextArea();
		jtaDisplayConsole.setFont(Font.decode("Courier-PLAIN-14"));
		jtaDisplayConsole.setLineWrap(true);
		jtaDisplayConsole.setWrapStyleWord(true);
		jtaDisplayConsole.setEditable(false);
		jtaDisplayConsole.setPreferredSize(new Dimension(800, 700));
		jtaDisplayConsole.setMaximumSize(new Dimension(800, 1000));
		JScrollPane jspScrollPane = new JScrollPane(jtaDisplayConsole);
		jspScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		jspScrollPane.setPreferredSize(new Dimension(800, 1000));
		jspScrollPane.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder("Transaction History"),
						BorderFactory.createEmptyBorder(5,5,5,5)),jspScrollPane.getBorder()));
		this.add(jspScrollPane, BorderLayout.PAGE_START);
		
		jtaDisplayConsole.setText(INITIAL_TEXT);
		
		JPanel jpPaneBottom1 = new JPanel();
		BoxLayout blLayoutBottom1 = new BoxLayout(jpPaneBottom1, BoxLayout.X_AXIS);
		jpPaneBottom1.setLayout(blLayoutBottom1);
		
		final JLabel jlStaticText1 = new JLabel("From:");
		jlStaticText1.setAlignmentY(CENTER_ALIGNMENT);
		jpPaneBottom1.add(jlStaticText1);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(10, 20)));
		
		jtfFromDate = new JTextField();
		jtfFromDate.setAlignmentY(CENTER_ALIGNMENT);
		jtfFromDate.setMaximumSize(new Dimension(150, 20));
		jpPaneBottom1.add(jtfFromDate);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(20, 20)));
		
		final JLabel jlStaticText2 = new JLabel("To:");
		jlStaticText2.setAlignmentY(CENTER_ALIGNMENT);
		jpPaneBottom1.add(jlStaticText2);
		
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
	}
	
	public void reset()
	{
		jtaDisplayConsole.setText(INITIAL_TEXT);
		jtfFromDate.setText("");
		jtfToDate.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(LOOK_UP_BUTTON))
		{
			try
			{
				jtaDisplayConsole.setText(m_lfFrame.getCurrentUser().queryMemberTransactionHistory(jtfFromDate.getText(), jtfToDate.getText()));
			}
			catch (Exception e)
			{
				jtaDisplayConsole.setText("Error, bad query parameters");
			}
		}
		else if (strCmd.equals(BACK_BUTTON))
			m_lfFrame.showPanelUserFrontPage();
	}
}
