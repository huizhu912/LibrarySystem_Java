package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class LibraryPanelAdminCheckOutCount extends JPanel
	implements ActionListener
{
	private final static String BACK_BUTTON = "Back";
	private final static String BOOKS_OUT_GRAPHIC_TEXT = "Checked out Items";
	private final static String BOOKS_IN_GRAPHIC_TEXT = "Available Items";
	
	private final static int BAR_X = 100;
	private final static int BAR_H = 150;
	private final static int OUT_BAR_Y = 100;
	private final static int IN_BAR_Y = 300;
	private final static int TEXT_SPACE = 5;
	
	private LibraryFrame m_lfFrame;
	private int m_nOutBarWid = 0;
	private int m_nInBarWid = 0;
	private int m_nOut = 0;
	private int m_nIn = 0;
	
	public LibraryPanelAdminCheckOutCount(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createRigidArea(new Dimension(10, 25)));
		
		final JLabel jlStaticText1 = new JLabel("Items out vs in:");
		jlStaticText1.setAlignmentX(CENTER_ALIGNMENT);
		this.add(jlStaticText1);
		
		this.add(Box.createRigidArea(new Dimension(10, 550)));
		
		JButton jbCancel = new JButton(BACK_BUTTON);
		jbCancel.setMaximumSize(new Dimension(100, 20));
		jbCancel.setAlignmentX(LEFT_ALIGNMENT);
		jbCancel.addActionListener(this);
		this.add(jbCancel);
		
		this.add(Box.createVerticalGlue());
	}
	
	public void paintComponent(Graphics g)
	{
		Dimension d = getSize();
		g.setColor(getBackground());
		
		int nWidth = d.width;
		int nHeight = d.height;
		
		g.fillRect(0, 0, nWidth, nHeight);

		g.setColor(Color.PINK);
		g.fillRect(BAR_X, OUT_BAR_Y, m_nOutBarWid, BAR_H);
		g.setColor(Color.WHITE);
		g.fillRect(BAR_X, IN_BAR_Y, m_nInBarWid, BAR_H);
		
		g.setColor(Color.BLACK);
		g.drawString(BOOKS_OUT_GRAPHIC_TEXT, BAR_X, OUT_BAR_Y - TEXT_SPACE);
		g.drawString(BOOKS_IN_GRAPHIC_TEXT, BAR_X, IN_BAR_Y - TEXT_SPACE);
		
		g.drawString("" + m_nOut, BAR_X + m_nOutBarWid + TEXT_SPACE, OUT_BAR_Y + BAR_H / 2);
		g.drawString("" + m_nIn, BAR_X + m_nInBarWid + TEXT_SPACE, IN_BAR_Y + BAR_H / 2);
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
		g.setColor(Color.PINK);
		g.fillRect(BAR_X, OUT_BAR_Y, m_nOutBarWid, BAR_H);
		g.setColor(Color.WHITE);
		g.fillRect(BAR_X, IN_BAR_Y, m_nInBarWid, BAR_H);
		
		repaint();
	}
	
	public void reset()
	{
		m_nOut = 0;
		m_nIn = 0;
		m_nOutBarWid = 0;
		m_nInBarWid = 0;
	}
	
	public void setParameters(int nOut, int nIn)
	{
		m_nOut = nOut;
		m_nIn = nIn;
	}
	
	public void animate() throws InterruptedException
	{
		Graphics g = this.getGraphics();
		clear(g);
		
		if (m_nOut >= m_nIn)
		{
			for (int i = 0; i <= 80; i++)
			{
				m_nOutBarWid = 800 * i / 80;
				m_nInBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nIn) / ((double) m_nOut))));
				
				drawAnimation(g);
				
				TimeUnit.MILLISECONDS.sleep(20);
			}
		}
		else
		{
			for (int i = 0; i <= 80; i++)
			{
				m_nInBarWid = 800 * i / 80;
				m_nOutBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nOut) / ((double) m_nIn))));
				
				drawAnimation(g);
				
				TimeUnit.MILLISECONDS.sleep(20);
			}
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
