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
import persistance.data.generic.SubscriptionPayment;
import persistance.data.generic.SubscriptionPaymentList;
import bl.facade.ShopFacade;
import bl.facade.UserFacade;
import ui.common.Frame;
import ui.common.VerticalLayout;
import ui.common.View;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class MemberPaymentsHistoryView extends View implements ActionListener{
	   	private UserFacade userFacade;
		
		private JPanel listPanel;
		private JScrollPane panel;
	    private JButton backButton;
	    private SubscriptionPaymentList payments; 
	    
	    private JPanel makeOrderCell(int idSubscriptionPayment, int amountPaid, String date) {
			/* Cellule de la liste*/
			JPanel cell = new JPanel();
			//La cellule est composée de 4 éléments
			cell.setLayout(new GridLayout(0, 4, 10, 0));
			
		    JTextField lbidSubscriptionPayment;
		    lbidSubscriptionPayment = new JTextField();
		    lbidSubscriptionPayment.setEditable(false);
		    lbidSubscriptionPayment.setText(Integer.toString(idSubscriptionPayment));
		    lbidSubscriptionPayment.setBackground(new Color(255, 255, 255));
			cell.add(lbidSubscriptionPayment);
			lbidSubscriptionPayment.setColumns(10);
			
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

			return cell;
	    }
	
	public MemberPaymentsHistoryView() {
		super("My Payments History");

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
		
		backButton = new JButton("Back");
		springLayout.putConstraint(SpringLayout.SOUTH, backButton, -36, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, backButton, -331, SpringLayout.EAST, this);
		backButton.addActionListener(this);
		add(backButton);
		
		JSeparator separator = new JSeparator();
		sepPanel.add(separator);
		
		JPanel libPanel = new JPanel();
		springLayout.putConstraint(SpringLayout.NORTH, libPanel, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, libPanel, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, libPanel, 0, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, libPanel, 45, SpringLayout.EAST, panel);
		add(libPanel);
		GridBagLayout gbl_libPanel = new GridBagLayout();
		gbl_libPanel.columnWidths = new int[]{72, 73, 53, 64, 47, 64, 0};
		gbl_libPanel.rowHeights = new int[]{26, 0};
		gbl_libPanel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_libPanel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		libPanel.setLayout(gbl_libPanel);
		
		JLabel lblNewLabel = new JLabel("Payments");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 0;
		libPanel.add(lblNewLabel, gbc_lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("  Amount");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_1.gridx = 3;
		gbc_lblNewLabel_1.gridy = 0;
		libPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblDate = new JLabel("     Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblDate = new GridBagConstraints();
		gbc_lblDate.fill = GridBagConstraints.BOTH;
		gbc_lblDate.gridx = 5;
		gbc_lblDate.gridy = 0;
		libPanel.add(lblDate, gbc_lblDate);
		
		listPanel = new JPanel();
		panel.setViewportView(listPanel);
		listPanel.setLayout(new VerticalLayout());
		listPanel.add(sepPanel);
	
		setPayments();
		
	}
	
	public void setPayments(){	
		userFacade = new UserFacade();
		int idMember = userFacade.getMemberFromLogin();
		payments = userFacade.getPaymentsFromMember(idMember);
		SubscriptionPayment subPayment;
		for(int i = 0 ; i < payments.size() ; i++) {
			subPayment = payments.get(i);
			listPanel.add(makeOrderCell(subPayment.getIdSubscriptionPayment(),subPayment.getAmountPaid(), subPayment.getDatePayment()));
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton){
			Frame.getFrame().setView(new MemberProfileView(),true);
			Frame.getFrame().revalidate();
			
		}
	}
}