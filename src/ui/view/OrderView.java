package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.common.Frame;
import ui.common.View;

@SuppressWarnings("serial")
public class OrderView extends View implements ActionListener{
	private JButton btnMyOrders;
	private JButton btnMySales;
	private JButton btnMyInvoices;
	
	public OrderView() {
		super("Orders");
		setLayout(null);
		
		btnMyOrders = new JButton("My Orders");
		btnMyOrders.setBounds(290, 100, 114, 23);
		btnMyOrders.addActionListener(this);
		add(btnMyOrders);
		
		btnMySales = new JButton("My Sales");
		btnMySales.setBounds(290, 194, 114, 23);
		btnMySales.addActionListener(this);
		add(btnMySales);
		
		btnMyInvoices = new JButton("My Invoices");
		btnMyInvoices.setBounds(290, 147, 114, 23);
		btnMyInvoices.addActionListener(this);
		add(btnMyInvoices);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnMyOrders){
			Frame.getFrame().setView(new UserOrdersView(),true);
			Frame.getFrame().revalidate();
		}
		else if (e.getSource() == btnMySales){
			Frame.getFrame().setView(new UserSalesView(),true);
			Frame.getFrame().revalidate();
		}
		else if (e.getSource() == btnMyInvoices){
			Frame.getFrame().setView(new UserInvoicesView(),true);
			Frame.getFrame().revalidate();
		}
		
	}
}