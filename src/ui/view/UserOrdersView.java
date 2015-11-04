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

import bl.facade.ShopFacade;
import bl.facade.UserFacade;
import dal.product.generic.Order;
import dal.product.generic.OrderList;
import dal.product.generic.ProductLine;
import ui.common.Frame;
import ui.common.VerticalLayout;
import ui.common.View;

@SuppressWarnings("serial")
public class UserOrdersView extends View implements ActionListener{
	   	private ShopFacade shopFacade = new ShopFacade();
		
	    public List<JTextField> prodPriceList = new ArrayList<JTextField> ();
	    public List<JTextField> prodTotList = new ArrayList<JTextField> ();
	    public List<JButton> detailsButtonList = new ArrayList<JButton> ();
		private JPanel listPanel;
		private JScrollPane panel;
	    private JButton backButton;
	    private OrderList orders; 
	    
	    private JPanel makeOrderCell(int idOrder, String state, String date, String seller, int idButton) {
			/* Cellule de la liste*/
			JPanel cell = new JPanel();
			//La cellule est composée de 4 éléments
			cell.setLayout(new GridLayout(0, 6, 8, 0));
			
		    JTextField lbOrder;
		    lbOrder = new JTextField();
		    lbOrder.setEditable(false);
		    lbOrder.setText(Integer.toString(idOrder));
		    lbOrder.setBackground(new Color(255, 255, 255));
			cell.add(lbOrder);
			lbOrder.setColumns(8);
			
			JTextField lbState;
			lbState = new JTextField();
			lbState.setEditable(false);
			lbState.setText(state);
			lbState.setBackground(new Color(255, 255, 255));
			cell.add(lbState);
			lbState.setColumns(8);
			
			JTextField lbDate;
			lbDate = new JTextField();
			lbDate.setEditable(false);
			lbDate.setText(date);
			lbDate.setBackground(new Color(255, 255, 255));
			cell.add(lbDate);
			lbDate.setColumns(8);
			
			JTextField lbSeller;
			lbSeller = new JTextField();
			lbSeller.setEditable(false);
			lbSeller.setText(seller);
			lbSeller.setBackground(new Color(255, 255, 255));
			cell.add(lbSeller);
			lbSeller.setColumns(8);
			
			JButton detailsButton = new JButton("Details");
			detailsButton.setName(Integer.toString(idButton));
			detailsButton.setActionCommand("details");
			detailsButton.addActionListener(this);
			detailsButtonList.add(detailsButton);
			cell.add(detailsButton);
			
			JButton paymentButton = new JButton("Pay");
			paymentButton.setName(Integer.toString(idButton));
			paymentButton.setActionCommand("payment");
			paymentButton.addActionListener(this);
			detailsButtonList.add(paymentButton);
			if (state.equals("W") || state.equals("P") || state.equals("A")){
				paymentButton.setEnabled(false);
			}
			cell.add(paymentButton);

			return cell;
	    }
	
	public UserOrdersView() {
		super("Orders");
		
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
		libPanel.setLayout(new GridLayout(0, 6, 25, 0));
		springLayout.putConstraint(SpringLayout.NORTH, libPanel, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, libPanel, 70, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, libPanel, 0, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, libPanel, 0, SpringLayout.EAST, panel);
		add(libPanel);
		
		JLabel lblNewLabel = new JLabel("Orders");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("State");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel_1);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblDate);
		
		JLabel lblseller = new JLabel("Seller");
		lblseller.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblseller);
		
		listPanel = new JPanel();
		panel.setViewportView(listPanel);
		listPanel.setLayout(new VerticalLayout());
		listPanel.add(sepPanel);
		
	}
	public void init(){
		setOrders();
	}
	
	public void setOrders(){	
		listPanel.removeAll();
		orders = shopFacade.getAllOrders();
		Order order;
		String seller;
		for(int i = 0 ; i < orders.size() ; i++) {
			order = orders.get(i);
			seller = shopFacade.getSellerFromOrder(order.getIdOrder());
			listPanel.add(makeOrderCell(order.getIdOrder(),order.getStateOrder(),order.getDateOrder(),seller, i));
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
			int idOrder = ((Order) orders.get(indice)).getIdOrder();
			
			Frame.getFrame().setView(new OrderDetailsView(idOrder, this),true);
			Frame.getFrame().revalidate();
			
		} else if (e.getActionCommand().equals("payment")) {
			int indice = Integer.parseInt(((JButton) e.getSource()).getName());
			Order order = ((Order) orders.get(indice));
			System.out.println(shopFacade.getAmountOfOrder(order.getIdOrder()));
			setOrders();
			Frame.getFrame().setView(new PaymentView(order),true);
			Frame.getFrame().revalidate();
		}
		
	}
}