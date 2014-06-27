package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class LibraryPanelAdminMemberCount extends JPanel
	implements ActionListener
{
	private final static String BACK_BUTTON = "Back";
	private final static String MEMBERS_GRAPHIC_TEXT = "Members";
	private final static String ADMINS_GRAPHIC_TEXT = "Admins";
	
	private final static int BAR_X = 100;
	private final static int BAR_H = 150;
	private final static int USER_BAR_Y = 100;
	private final static int ADMIN_BAR_Y = 300;
	private final static int TEXT_SPACE = 5;
	
	private LibraryFrame m_lfFrame;
	private int m_nUserBarWid = 0;
	private int m_nAdminBarWid = 0;
	private int m_nUser = 0;
	private int m_nAdmin = 0;
	
	public LibraryPanelAdminMemberCount(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createRigidArea(new Dimension(10, 25)));
		
		final JLabel jlStaticText1 = new JLabel("Member Count:");
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
		
		g.setColor(Color.RED);
		g.fillRect(BAR_X, USER_BAR_Y, m_nUserBarWid, BAR_H);
		g.setColor(Color.GREEN);
		g.fillRect(BAR_X, ADMIN_BAR_Y, m_nAdminBarWid, BAR_H);
		
		g.setColor(Color.BLACK);
		g.drawString(MEMBERS_GRAPHIC_TEXT, BAR_X, USER_BAR_Y - TEXT_SPACE);
		g.drawString(ADMINS_GRAPHIC_TEXT, BAR_X, ADMIN_BAR_Y - TEXT_SPACE);
		
		g.drawString("" + m_nUser, BAR_X + m_nUserBarWid + TEXT_SPACE, USER_BAR_Y + BAR_H / 2);
		g.drawString("" + m_nAdmin + " <---- (You ^_^v)", BAR_X + m_nAdminBarWid + TEXT_SPACE, ADMIN_BAR_Y + BAR_H / 2);
	}
	
	public void clear(Graphics g)
	{
		Dimension d = getSize();
		g.setColor(getBackground());
		
		int nWidth = d.width;
		int nHeight = d.height;
		
		g.fillRect(0, 0, nWidth, nHeight);
	}
	
	public void drawAnimation(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRect(BAR_X, USER_BAR_Y, m_nUserBarWid, BAR_H);
		g.setColor(Color.GREEN);
		g.fillRect(BAR_X, ADMIN_BAR_Y, m_nAdminBarWid, BAR_H);
		
		repaint();
	}
	
	public void reset()
	{
		m_nUser = 0;
		m_nAdmin = 0;
		m_nUserBarWid = 0;
		m_nAdminBarWid = 0;
	}
	
	public void setParameters(int nUser, int nAdmin)
	{
		m_nUser = nUser;
		m_nAdmin = nAdmin;
	}
	
	public void animate() throws InterruptedException
	{
		Graphics g = this.getGraphics();
		clear(g);
		
		for (int i = 0; i <= 80; i++)
		{
			m_nUserBarWid = 800 * i / 80;
			m_nAdminBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nAdmin) / ((double) m_nUser))));
			
			drawAnimation(g);
			
			TimeUnit.MILLISECONDS.sleep(20);
		}
		
		paintComponent(g);
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		
		if (strCmd.equals(BACK_BUTTON))
			m_lfFrame.showPanelAdminFrontPage();
	}
}
