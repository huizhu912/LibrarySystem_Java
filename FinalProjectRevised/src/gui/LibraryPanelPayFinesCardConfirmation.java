package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class LibraryPanelPayFinesCardConfirmation extends JPanel
	implements ActionListener
{
	private final static String OK_BUTTON = "OK";
	
	private LibraryFrame m_lfFrame;
	
	public LibraryPanelPayFinesCardConfirmation(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createVerticalGlue());
		
		final JLabel jlStaticText1 = new JLabel("Thank you for paying your fines");
		jlStaticText1.setFont(new Font(jlStaticText1.getFont().getName(), jlStaticText1.getFont().getStyle(), 40));
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		this.add(Box.createRigidArea(new Dimension(20, 80)));
		
		JButton jbOk = new JButton(OK_BUTTON);
		jbOk.setMaximumSize(new Dimension(100, 20));
		jbOk.setAlignmentX(CENTER_ALIGNMENT);
		jbOk.addActionListener(this);
		this.add(jbOk);
		
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
