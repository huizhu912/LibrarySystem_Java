package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;

public class LibraryPanelBanner extends JPanel
	implements ActionListener
{
	private Timer m_tTimer;
	private static int m_nSlide = 0;
	private static Image[] m_arrImages;
	
	public LibraryPanelBanner()
	{
		super();
		
		m_tTimer = new Timer(3000, this);
		
		this.setPreferredSize(new Dimension(800, 200));
		this.setMaximumSize(new Dimension(800, 200));
		
		m_arrImages = new Image[4];
		
		try
		{
			InputStream stream = LibraryPanelBanner.class.getResourceAsStream("banner1.bmp");
			m_arrImages[0] = ImageIO.read(stream);
			stream = LibraryPanelBanner.class.getResourceAsStream("banner2.bmp");
			m_arrImages[1] = ImageIO.read(stream);
			stream = LibraryPanelBanner.class.getResourceAsStream("banner3.bmp");
			m_arrImages[2] = ImageIO.read(stream);
			stream = LibraryPanelBanner.class.getResourceAsStream("banner4.bmp");
			m_arrImages[3] = ImageIO.read(stream);
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	void startTimer()
	{
		m_tTimer.start();
	}
	
	void stopTimer()
	{
		m_tTimer.stop();
	}
	
	void slideShow(Graphics g)
	{
		g.drawImage(m_arrImages[m_nSlide], 0, 0, null);
		m_nSlide = (m_nSlide + 1) % 4;
		//repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		Dimension d = getSize();
		g.setColor(getBackground());
		
		int nWidth = d.width;
		int nHeight = d.height;
		
		g.fillRect(0, 0, nWidth, nHeight);
		g.drawImage(m_arrImages[m_nSlide], 0, 0, null);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		/*
		Graphics g = getGraphics();
		g.drawImage(m_arrImages[m_nSlide], 0, 0, null);
		m_nSlide = (m_nSlide + 1) % 4;
		*/
		repaint();
		m_nSlide = (m_nSlide + 1) % 4;
	}
}
