package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LibraryPanelPayFinesCash extends JPanel
	implements ActionListener
{
	private static final String OK_BUTTON = "OK";
	
	private LibraryFrame m_lfFrame;
	
	public LibraryPanelPayFinesCash(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createVerticalGlue());
		
		final JLabel jlStaticText1 = new JLabel("Please Pay at the Front Desk");
		jlStaticText1.setFont(new Font(jlStaticText1.getFont().getName(), jlStaticText1.getFont().getStyle(), 50));
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		this.add(Box.createRigidArea(new Dimension(20, 40)));
		
		JButton jbOK = new JButton(OK_BUTTON);
		jbOK.setPreferredSize(new Dimension(300, 50));
		jbOK.setMaximumSize(new Dimension(300, 50));
		jbOK.setAlignmentX(CENTER_ALIGNMENT);
		jbOK.addActionListener(this);
		this.add(jbOK);
		
		this.add(Box.createVerticalGlue());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(OK_BUTTON))
			m_lfFrame.showPanelUserFrontPage();
	}
}
