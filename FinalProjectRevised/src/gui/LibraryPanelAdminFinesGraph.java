package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import admin.Admin;

public class LibraryPanelAdminFinesGraph extends JPanel
	implements ActionListener
{
	private final static String BACK_BUTTON = "Back";
	private final static String IS_GRAPHIC_TEXT = "Total fines collected: ";
	private final static String SHOULD_GRAPHIC_TEXT = "What total fines should be:";
	
	private final static int BAR_X = 100;
	private final static int BAR_H = 150;
	private final static int IS_BAR_Y = 100;
	private final static int SHOULD_BAR_Y = 300;
	private final static int TEXT_SPACE = 5;
	
	private LibraryFrame m_lfFrame;
	private double dFines = 0;
	
	public LibraryPanelAdminFinesGraph(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createRigidArea(new Dimension(10, 25)));
		
		final JLabel jlStaticText1 = new JLabel("Fine Graph:");
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		this.add(Box.createRigidArea(new Dimension(10, 550)));
		
		JButton jbCancel = new JButton(BACK_BUTTON);
		jbCancel.setMaximumSize(new Dimension(100, 20));
		jbCancel.setAlignmentX(LEFT_ALIGNMENT);
		jbCancel.addActionListener(this);
		this.add(jbCancel);
	}
	
	public void paintComponent(Graphics g)
	{
		Dimension d = getSize();
		g.setColor(getBackground());
		
		int nWidth = d.width;
		int nHeight = d.height;
		
		g.fillRect(0, 0, nWidth, nHeight);
		
		g.setColor(Color.BLACK);
		g.fillRect(BAR_X, IS_BAR_Y, 800, BAR_H);
		g.drawLine(BAR_X, SHOULD_BAR_Y, BAR_X, SHOULD_BAR_Y + BAR_H);
		
		g.setColor(Color.BLACK);
		g.drawString(IS_GRAPHIC_TEXT, BAR_X, IS_BAR_Y - TEXT_SPACE);
		g.drawString(SHOULD_GRAPHIC_TEXT, BAR_X, SHOULD_BAR_Y - TEXT_SPACE);
		
		g.drawString("" + dFines, BAR_X + 800 + TEXT_SPACE, IS_BAR_Y + BAR_H / 2);
		g.drawString("" + 0, BAR_X + 0 + TEXT_SPACE, SHOULD_BAR_Y + BAR_H / 2);
	}
	
	void reset(String strFrom, String strTo) throws Exception
	{
		dFines = ((Admin) m_lfFrame.getCurrentUser()).getTotalFines(strFrom, strTo);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(BACK_BUTTON))
			m_lfFrame.showPanelAdminFrontPage();
	}
}
