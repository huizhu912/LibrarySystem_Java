package gui;

import inventory.Item;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

public class LibraryPanelBookReserve extends JPanel
	implements ActionListener
{
	private final static String BACK_BUTTON = "Back";
	
	private LibraryFrame m_lfFrame;
	
	public LibraryPanelBookReserve(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		final JLabel jlStaticText1 = new JLabel("UNDER CONSTRUCTION");
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		JButton jbCancel = new JButton(BACK_BUTTON);
		jbCancel.setMaximumSize(new Dimension(100, 20));
		jbCancel.setAlignmentX(LEFT_ALIGNMENT);
		jbCancel.addActionListener(this);
		this.add(jbCancel);
	}
	
	public void reset()
	{
		// TBI
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(BACK_BUTTON))
			m_lfFrame.showPanelUserFrontPage();
	}
}
