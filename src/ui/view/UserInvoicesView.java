package ui.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;

import persistance.data.generic.Invoice;
import persistance.data.generic.InvoiceList;
import persistance.data.generic.Order;
import persistance.data.generic.OrderList;
import persistance.data.generic.ProductLine;
import bl.facade.ShopFacade;
import bl.facade.UserFacade;
import ui.common.Frame;
import ui.common.VerticalLayout;
import ui.common.View;

@SuppressWarnings("serial")
public class UserInvoicesView extends View implements ActionListener{
	   	private ShopFacade shopFacade = new ShopFacade();
		
	    public List<JTextField> prodPriceList = new ArrayList<JTextField> ();
	    public List<JTextField> prodTotList = new ArrayList<JTextField> ();
	    public List<JButton> detailsButtonList = new ArrayList<JButton> ();
		private JPanel listPanel;
		private JScrollPane panel;
	    private JButton backButton;
	    private InvoiceList invoices; 
	    
	    private JPanel makeOrderCell(int idInvoice, int amountPaid, String date, int idButton) {
			/* Cellule de la liste*/
			JPanel cell = new JPanel();
			//La cellule est composée de 4 éléments
			cell.setLayout(new GridLayout(0, 4, 10, 0));
			
		    JTextField lbInvoice;
		    lbInvoice = new JTextField();
		    lbInvoice.setEditable(false);
		    lbInvoice.setText(Integer.toString(idInvoice));
		    lbInvoice.setBackground(new Color(255, 255, 255));
			cell.add(lbInvoice);
			lbInvoice.setColumns(10);
			
			JTextField lbAmountPaid;
			lbAmountPaid = new JTextField();
			lbAmountPaid.setEditable(false);
			lbAmountPaid.setText(Integer.toString(amountPaid));
			lbAmountPaid.setBackground(new Color(255, 255, 255));
			cell.add(lbAmountPaid);
			lbAmountPaid.setColumns(10);
			
			JTextField lbDate;
			lbDate = new JTextField();
			lbDate.setEditable(false);
			lbDate.setText(date);
			lbDate.setBackground(new Color(255, 255, 255));
			cell.add(lbDate);
			lbDate.setColumns(10);
			
			JButton detailsButton = new JButton("Details");
			detailsButton.setName(Integer.toString(idButton));
			detailsButton.setActionCommand("details");
			detailsButton.addActionListener(this);
			detailsButtonList.add(detailsButton);
			cell.add(detailsButton);

			return cell;
	    }
	
	public UserInvoicesView() {
		super("Invoices");

		JPanel sepPanel = new JPanel();
		sepPanel.setLayout(new GridLayout(1, 1));
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		panel = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, panel, 99, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, panel, 55, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, panel, -107, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, panel, -55, SpringLayout.EAST, this);
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setBackground(Color.WHITE);
		add(panel);
		
		/*backButton = new JButton("Back");
		springLayout.putConstraint(SpringLayout.SOUTH, backButton, -36, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, backButton, -331, SpringLayout.EAST, this);
		backButton.addActionListener(this);
		add(backButton);*/
		
		JSeparator separator = new JSeparator();
		sepPanel.add(separator);
		
		JPanel libPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.WEST, libPanel, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.NORTH, libPanel, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.SOUTH, libPanel, 0, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, libPanel, 45, SpringLayout.EAST, panel);
		add(libPanel);
		libPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Invoices");
		lblNewLabel.setBounds(78, 0, 76, 26);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("  Amount");
		lblNewLabel_1.setBounds(197, 0, 76, 26);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel_1);
		
		JLabel lblDate = new JLabel("     Date");
		lblDate.setBounds(320, 0, 68, 26);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblDate);
		
		listPanel = new JPanel();
		panel.setViewportView(listPanel);
		listPanel.setLayout(new VerticalLayout());
		listPanel.add(sepPanel);
	
	}
	public void init(){
		setInvoices();
	}
	
	public void setInvoices(){	
		listPanel.removeAll();
		invoices = shopFacade.getAllInvoices();
		Invoice invoice;
		for(int i = 0 ; i < invoices.size() ; i++) {
			invoice = invoices.get(i);
			listPanel.add(makeOrderCell(invoice.getIdInvoice(),invoice.getAmountPaid(), invoice.getPaymentDate(), i));
		}
		listPanel.revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton){
			Frame.getFrame().setView(new OrderView(),true);
			Frame.getFrame().revalidate();
			
		} else if (e.getActionCommand().equals("details")) {
			int indice = Integer.parseInt(((JButton) e.getSource()).getName());
			Invoice invoice = ((Invoice) invoices.get(indice));
			
			Frame.getFrame().setView(new InvoiceDetailsView(invoice),true);
			Frame.getFrame().revalidate();
			
		}
	}
}