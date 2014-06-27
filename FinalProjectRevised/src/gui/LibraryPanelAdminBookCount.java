package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class LibraryPanelAdminBookCount extends JPanel
	implements ActionListener
{
	private final static String BACK_BUTTON = "Back";
	private final static String ENGINEERING_GRAPHIC_TEXT = "Non-Fiction";
	private final static String FICTION_GRAPHIC_TEXT = "Fiction";
	private final static String VIDEO_GRAPHIC_TEXT = "Video";
	
	private final static int BAR_X = 100;
	private final static int BAR_H = 100;
	private final static int ENG_BAR_Y = 100;
	private final static int FIC_BAR_Y = 250;
	private final static int VID_BAR_Y = 400;
	private final static int TEXT_SPACE = 5;
	
	private LibraryFrame m_lfFrame;
	private int m_nEngBarWid = 0;
	private int m_nFicBarWid = 0;
	private int m_nVidBarWid = 0;
	private int m_nEng = 0;
	private int m_nFic = 0;
	private int m_nVid = 0;
	
	public LibraryPanelAdminBookCount(LibraryFrame lfFrame)
	{
		super();
		
		m_lfFrame = lfFrame;
		
		BoxLayout blLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(blLayout);
		
		this.add(Box.createRigidArea(new Dimension(10, 25)));
		
		final JLabel jlStaticText1 = new JLabel("Book Quantity by type:");
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
		g.fillRect(BAR_X, ENG_BAR_Y, m_nEngBarWid, BAR_H);
		g.setColor(Color.GREEN);
		g.fillRect(BAR_X, FIC_BAR_Y, m_nFicBarWid, BAR_H);
		g.setColor(Color.BLUE);
		g.fillRect(BAR_X, VID_BAR_Y, m_nVidBarWid, BAR_H);
		
		g.setColor(Color.BLACK);
		g.drawString(ENGINEERING_GRAPHIC_TEXT, BAR_X, ENG_BAR_Y - TEXT_SPACE);
		g.drawString(FICTION_GRAPHIC_TEXT, BAR_X, FIC_BAR_Y - TEXT_SPACE);
		g.drawString(VIDEO_GRAPHIC_TEXT, BAR_X, VID_BAR_Y - TEXT_SPACE);
		
		g.drawString("" + m_nEng, BAR_X + m_nEngBarWid + TEXT_SPACE, ENG_BAR_Y + BAR_H / 2);
		g.drawString("" + m_nFic, BAR_X + m_nFicBarWid + TEXT_SPACE, FIC_BAR_Y + BAR_H / 2);
		g.drawString("" + m_nVid, BAR_X + m_nVidBarWid + TEXT_SPACE, VID_BAR_Y + BAR_H / 2);
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
		g.fillRect(BAR_X, ENG_BAR_Y, m_nEngBarWid, BAR_H);
		g.setColor(Color.GREEN);
		g.fillRect(BAR_X, FIC_BAR_Y, m_nFicBarWid, BAR_H);
		g.setColor(Color.BLUE);
		g.fillRect(BAR_X, VID_BAR_Y, m_nVidBarWid, BAR_H);
		
		repaint();
	}
	
	public void reset()
	{
		m_nEng = 0;
		m_nFic = 0;
		m_nVid = 0;
		m_nEngBarWid = 0;
		m_nFicBarWid = 0;
		m_nVidBarWid = 0;
	}
	
	public void setParameters(int nEng, int nFic, int nVid)
	{
		m_nEng = nEng;
		m_nFic = nFic;
		m_nVid = nVid;
	}
	
	public void animate() throws InterruptedException
	{
		Graphics g = this.getGraphics();
		clear(g);
		
		for (int i = 0; i <= 80; i++)
		{
			if (m_nEng >= m_nFic && m_nEng >= m_nVid)
			{
				m_nEngBarWid = 800 * i / 80;
				m_nFicBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nFic) / ((double) m_nEng))));
				m_nVidBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nVid) / ((double) m_nEng))));
			}
			else if (m_nFic >= m_nEng && m_nFic >= m_nVid)
			{
				m_nFicBarWid = 800 * i / 80;
				m_nEngBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nEng) / ((double) m_nFic))));
				m_nVidBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nVid) / ((double) m_nFic))));
			}
			else if (m_nVid > m_nFic && m_nVid > m_nEng)
			{
				m_nVidBarWid = 800 * i / 80;
				m_nEngBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nEng) / ((double) m_nVid))));
				m_nFicBarWid = ((int) (800 * ((double) i / 80) * (((double) m_nFic) / ((double) m_nVid))));
			}
			
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
