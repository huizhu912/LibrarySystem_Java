package gui;

import inventory.Item;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import exception.hasOverDueItems;
import exception.invalidCheckOutDate;
import exception.invalidItemCount;

import member.Member;

public class LibraryPanelBookSearch extends JPanel
	implements ActionListener
{
	private final static String INITIAL_TEXT = "";
	private final static String SEARCH_BUTTON = "Search";
	private final static String RESERVE_BUTTON = "Reserve";
	private final static String RETURN_BUTTON = "Return";
	private final static String VIEW_CART_BUTTON = "View Cart";
	private final static String CHECK_OUT_BUTTON = "Check Out";
	private final static String ADD_CART_BUTTON = "Add to Cart";
	private final static String VIEW_RESERVED_BUTTON = "View Reserved";
	private final static String BACK_BUTTON = "Back";
	
	private LibraryFrame m_lfFrame;
	private JTextArea jtaDisplayConsole;
	private JTextField jtfInput;
	
	private static List<Item> cart = new ArrayList<Item>();
	
	public LibraryPanelBookSearch(LibraryFrame lfFrame)
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
						BorderFactory.createTitledBorder("Book Queries"),
						BorderFactory.createEmptyBorder(5,5,5,5)),jspScrollPane.getBorder()));
		//this.add(jtaDisplayConsole, BorderLayout.PAGE_START);
		this.add(jspScrollPane);
		
		jtaDisplayConsole.setText(INITIAL_TEXT);
		
		JPanel jpPaneBottom1 = new JPanel();
		BoxLayout blLayoutBottom1 = new BoxLayout(jpPaneBottom1, BoxLayout.X_AXIS);
		jpPaneBottom1.setLayout(blLayoutBottom1);
		
		final JLabel jlStaticText1 = new JLabel("Enter Query:");
		jlStaticText1.setAlignmentY(CENTER_ALIGNMENT);
		jpPaneBottom1.add(jlStaticText1);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(10, 20)));
		
		jtfInput = new JTextField();
		jtfInput.setAlignmentY(CENTER_ALIGNMENT);
		jtfInput.setMaximumSize(new Dimension(300, 20));
		jpPaneBottom1.add(jtfInput);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbSearch = new JButton(SEARCH_BUTTON);
		jbSearch.setMaximumSize(new Dimension(100, 20));
		jbSearch.setAlignmentY(CENTER_ALIGNMENT);
		jbSearch.addActionListener(this);
		jpPaneBottom1.add(jbSearch);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbReserve = new JButton(RESERVE_BUTTON);
		jbReserve.setMaximumSize(new Dimension(100, 20));
		jbReserve.setAlignmentY(CENTER_ALIGNMENT);
		jbReserve.addActionListener(this);
		jpPaneBottom1.add(jbReserve);
		
		jpPaneBottom1.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbReturn = new JButton(RETURN_BUTTON);
		jbReturn.setMaximumSize(new Dimension(100, 20));
		jbReturn.setAlignmentY(CENTER_ALIGNMENT);
		jbReturn.addActionListener(this);
		jpPaneBottom1.add(jbReturn);
		
		this.add(jpPaneBottom1);
		this.add(Box.createRigidArea(new Dimension(20, 5)));
		
		JPanel jpPaneBottom2 = new JPanel();
		BoxLayout blLayoutBottom2 = new BoxLayout(jpPaneBottom2, BoxLayout.X_AXIS);
		jpPaneBottom2.setLayout(blLayoutBottom2);
		
		jpPaneBottom2.add(Box.createRigidArea(new Dimension(160, 20)));
		
		JButton jbViewCart = new JButton(VIEW_CART_BUTTON);
		jbViewCart.setMaximumSize(new Dimension(100, 20));
		jbViewCart.setAlignmentY(CENTER_ALIGNMENT);
		jbViewCart.addActionListener(this);
		jpPaneBottom2.add(jbViewCart);
		
		jpPaneBottom2.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbCheckOut = new JButton(CHECK_OUT_BUTTON);
		jbCheckOut.setMaximumSize(new Dimension(100, 20));
		jbCheckOut.setAlignmentY(CENTER_ALIGNMENT);
		jbCheckOut.addActionListener(this);
		jpPaneBottom2.add(jbCheckOut);
		
		jpPaneBottom2.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbAddCart = new JButton(ADD_CART_BUTTON);
		jbAddCart.setMaximumSize(new Dimension(100, 20));
		jbAddCart.setAlignmentY(CENTER_ALIGNMENT);
		jbAddCart.addActionListener(this);
		jpPaneBottom2.add(jbAddCart);
		
		jpPaneBottom2.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbViewReserved = new JButton(VIEW_RESERVED_BUTTON);
		jbViewReserved.setMaximumSize(new Dimension(100, 20));
		jbViewReserved.setAlignmentY(CENTER_ALIGNMENT);
		jbViewReserved.addActionListener(this);
		jpPaneBottom2.add(jbViewReserved);
		
		jpPaneBottom2.add(Box.createRigidArea(new Dimension(20, 20)));
		
		JButton jbBack = new JButton(BACK_BUTTON);
		jbBack.setMaximumSize(new Dimension(100, 20));
		jbBack.setAlignmentY(CENTER_ALIGNMENT);
		jbBack.addActionListener(this);
		jpPaneBottom2.add(jbBack);
		
		this.add(jpPaneBottom2);
	}
	
	public void reset()
	{
		jtaDisplayConsole.setText(INITIAL_TEXT);
		jtfInput.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		String strCmd = arg0.getActionCommand();
		Member mTemp = m_lfFrame.getCurrentUser();
		
		if (strCmd.equals(SEARCH_BUTTON))
		{
			try
			{
				jtaDisplayConsole.setText("Search Results:\n\n" + Member.searchForItem(jtfInput.getText()));
			}
			catch (Exception e)
			{
				Toolkit.getDefaultToolkit().beep();
				jtaDisplayConsole.setText("Could not find item.");
			}
		}
		else if (strCmd.equals(RESERVE_BUTTON))
		{
			if (mTemp != null)
			{
				try
				{
					mTemp.reserveItem("Item Reserved:\n\n" + jtfInput.getText());
				}
				catch (Exception e)
				{
					Toolkit.getDefaultToolkit().beep();
					jtaDisplayConsole.setText("Could not reserve item.");
				}
			}
			else
			{
				Toolkit.getDefaultToolkit().beep();
				jtaDisplayConsole.setText("Please log in to perform this action");
			}
		}
		else if (strCmd.equals(RETURN_BUTTON))
		{
			if (mTemp != null)
			{
				try
				{
					mTemp.returnItem(jtfInput.getText());
					
					List<Item> arrItems = Item.getCart();
					
					String strText = "Items Returned: \n\n";
					
					for (int i = 0; i < arrItems.size(); i++)
					{
						strText += "ID: " + arrItems.get(i).getItemID() + "\n";
						strText += "Item Type: " + arrItems.get(i).getItemType() + "\n";
						strText += "Title: " + arrItems.get(i).getTitle() + "\n";
						strText += "Author: " + arrItems.get(i).getAuthor() + "\n";
						strText += "Location: "+ arrItems.get(i).getLocation() + "\n" + "\n";
					}
					
					jtaDisplayConsole.setText(strText);
					mTemp.doneWithReturn();
				}
				catch (Exception e)
				{
					Toolkit.getDefaultToolkit().beep();
					jtaDisplayConsole.setText("Could not return item.");
				}
			}
			else
			{
				Toolkit.getDefaultToolkit().beep();
				jtaDisplayConsole.setText("Please log in to perform this action");
			}
		}
		else if (strCmd.equals(VIEW_CART_BUTTON))
		{
			if (mTemp != null)
			{
				List<Item> arrItems = Item.getCart();
				
				String strText = "Current Cart:\n\n";
				
				for (int i = 0; i < arrItems.size(); i++)
				{
					strText += "ID: " + arrItems.get(i).getItemID() + "\n";
					strText += "Item Type: " + arrItems.get(i).getItemType() + "\n";
					strText += "Title: " + arrItems.get(i).getTitle() + "\n";
					strText += "Author: " + arrItems.get(i).getAuthor() + "\n";
					strText += "Location: "+ arrItems.get(i).getLocation() + "\n" + "\n";
				}
				
				jtaDisplayConsole.setText(strText);
			}
			else
			{
				Toolkit.getDefaultToolkit().beep();
				jtaDisplayConsole.setText("Please log in to perform this action");
			}
		}
		else if (strCmd.equals(CHECK_OUT_BUTTON))
		{
			if (mTemp != null)
			{
				try
				{
					List<Item> arrItems = Item.getCart();
					
					String strText = "Checked out following items:\n\n";
					
					for (int i = 0; i < arrItems.size(); i++)
					{
						strText += "ID: " + arrItems.get(i).getItemID() + "\n";
						strText += "Item Type: " + arrItems.get(i).getItemType() + "\n";
						strText += "Title: " + arrItems.get(i).getTitle() + "\n";
						strText += "Author: " + arrItems.get(i).getAuthor() + "\n";
						strText += "Location: "+ arrItems.get(i).getLocation() + "\n" + "\n";
					}
					
					jtaDisplayConsole.setText(strText);
					
					mTemp.doneWithCheckOut();
				}
				catch (Exception e)
				{
					Toolkit.getDefaultToolkit().beep();
					if (mTemp.getBalance() != 0)
						jtaDisplayConsole.setText("Cannot check out item, overdue fees unpaid");
					else
						jtaDisplayConsole.setText("Cannot check out item, unavailable");
				}
			}
			else
			{
				Toolkit.getDefaultToolkit().beep();
				jtaDisplayConsole.setText("Please log in to perform this action");
			}
		}
		else if (strCmd.equals(ADD_CART_BUTTON))
		{
			if (mTemp != null)
			{
				try
				{
					mTemp.checkOut(jtfInput.getText());
					jtaDisplayConsole.setText(jtfInput.getText() + " added to cart");
				}
				catch (invalidCheckOutDate e) {
					System.out.println(e);
					jtaDisplayConsole.setText(e.toString());
				}
				catch (hasOverDueItems e) {
					System.out.println(e);
					jtaDisplayConsole.setText(e.toString());
				}
				catch (invalidItemCount e) {
					System.out.println(e);
					jtaDisplayConsole.setText(e.toString());
				}
				catch (Exception e)
				{
					Toolkit.getDefaultToolkit().beep();
					if (mTemp.getBalance() != 0)
						jtaDisplayConsole.setText("Cannot check out item, overdue fees unpaid");
					else
						jtaDisplayConsole.setText("Cannot check out item, unavailable");
				}
			}
			else
			{
				Toolkit.getDefaultToolkit().beep();
				jtaDisplayConsole.setText("Please log in to perform this action");
			}
		}
		else if (strCmd.equals(VIEW_RESERVED_BUTTON))
		{
			if (mTemp != null)
			{
				try
				{
					jtaDisplayConsole.setText("Reserved Items: \n\n" + mTemp.printAllReservedItems(mTemp));
				}
				catch (Exception e)
				{
					Toolkit.getDefaultToolkit().beep();
				}
			}
			else
			{
				Toolkit.getDefaultToolkit().beep();
				jtaDisplayConsole.setText("Please log in to perform this action");
			}
		}
		else if (strCmd.equals(BACK_BUTTON))
		{
			if (mTemp != null)
				m_lfFrame.showPanelUserFrontPage();
			else
				m_lfFrame.showPanelLogin();
		}
	}
}
