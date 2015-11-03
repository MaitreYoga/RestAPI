package ui.view;

import ui.common.Frame;
import ui.common.VerticalLayout;
import ui.common.View;
import bl.facade.ShopFacade;
import bl.facade.UserFacade;

import javax.swing.JPanel;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JSeparator;
import javax.swing.JSpinner;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import persistance.data.generic.OrderLine;
import persistance.data.generic.OrderLineList;
import persistance.data.generic.Product;
import persistance.data.generic.ProductLine;
import persistance.data.generic.ProductLineList;


@SuppressWarnings("serial")
public class OrderDetailsView extends View implements ActionListener{
    private ShopFacade shopFacade = new ShopFacade();
    private UserFacade userFacade = new UserFacade();
	
    public List<JTextField> prodPriceList = new ArrayList<JTextField> ();
    public List<JTextField> prodTotList = new ArrayList<JTextField> ();
    public List<JButton> removeButtonList = new ArrayList<JButton> ();
    public List<JButton> detailsButtonList = new ArrayList<JButton> ();
	private JPanel listPanel;
	private JScrollPane panel;
	private JTextField lbTotal;
    private JTextField lbProdName;
    private JButton backButton;
    private OrderLineList prodsOrder;
    private View view;
    
    private JPanel makeProductCell(String prodName, int quantity, int unitPrice, int idButton) {
		/* Cellule de la liste*/
		JPanel cell = new JPanel();
		//La cellule est composée de 4 éléments
		cell.setLayout(new GridLayout(0, 6, 8, 0));
		
		lbProdName = new JTextField();
		lbProdName.setEditable(false);
		lbProdName.setText(prodName);
		lbProdName.setBackground(new Color(255, 255, 255));
		cell.add(lbProdName);
		lbProdName.setColumns(8);
		
		JTextField lbQuantity = new JTextField();
		lbQuantity.setEditable(false);
		lbQuantity.setText(Integer.toString(quantity));
		lbQuantity.setBackground(new Color(255, 255, 255));
		cell.add(lbQuantity);
		lbQuantity.setColumns(8);
		
		JTextField lbProdPrice;
		lbProdPrice = new JTextField();
		lbProdPrice.setEditable(false);
		lbProdPrice.setText(Integer.toString(unitPrice));
		lbProdPrice.setBackground(new Color(255, 255, 255));
		cell.add(lbProdPrice);
		lbProdPrice.setColumns(8);
		prodPriceList.add(lbProdPrice);
		
	    JTextField lbProdTot;
		lbProdTot = new JTextField();
		lbProdTot.setEditable(false);
		lbProdTot.setName("lbProdTot"+Integer.toString(idButton));
		lbProdTot.setText(Integer.toString(unitPrice*quantity));
		lbProdTot.setBackground(new Color(255, 255, 255));
		cell.add(lbProdTot);
		lbProdTot.setColumns(8);
		prodTotList.add(lbProdTot);
		
		JButton detailsButton = new JButton("Details");
		detailsButton.setName(Integer.toString(idButton));
		detailsButton.setActionCommand("details");
		detailsButton.addActionListener(this);
		detailsButtonList.add(detailsButton);
		cell.add(detailsButton);
		
		return cell;
    }
    
	public OrderDetailsView(int idOrder, View view) {
		super("Order "+idOrder+" Details");
		
		this.view = view;
		
		JPanel libPanel = new JPanel();
		libPanel.setLayout(new GridLayout(0, 6, 25, 0));
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
		
		lbTotal = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lbTotal, 11, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.WEST, lbTotal, 555, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lbTotal, -73, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lbTotal, -61, SpringLayout.EAST, this);
		lbTotal.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lbTotal.setEditable(false);
		lbTotal.setBackground(new Color(255, 255, 255));
		add(lbTotal);
		
		JLabel lblTotal = new JLabel("Total");
		springLayout.putConstraint(SpringLayout.NORTH, lblTotal, 13, SpringLayout.SOUTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, lblTotal, -18, SpringLayout.WEST, lbTotal);
		lblTotal.setFont(new Font("Tahoma", Font.BOLD, 15));
		add(lblTotal);
		
		JSeparator separator = new JSeparator();
		sepPanel.add(separator);
		
		JLabel lblNewLabel = new JLabel("Product");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Quantity");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel_1);
		
		JLabel lblUnitPrice = new JLabel("Unit Price");
		lblUnitPrice.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblUnitPrice);
		
		JLabel lblNewLabel_2 = new JLabel("   Price");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel_2);
		
		listPanel = new JPanel();
		panel.setViewportView(listPanel);
		listPanel.setLayout(new VerticalLayout());
		listPanel.add(libPanel);
		listPanel.add(sepPanel);
		
		prodsOrder = shopFacade.getAllProductInOrder(idOrder);
		setProducts();
    }

	public void setProducts(){
		int total = 0;
		String priceTest;
		int price;
		OrderLine prodO;
		for(int i = 0 ; i < prodsOrder.size() ; i++) {
			prodO = prodsOrder.get(i);
			Product prod = shopFacade.getProduct(prodO.getIdProduct());
			if (userFacade.isMember()){
				priceTest = Integer.toBinaryString(prod.getMemberPrice());
				if (priceTest.equals("0")){
					price = prod.getPrice();
				} else {
					price = prod.getMemberPrice();
				}
			} else{
				price = prod.getPrice();
			}
			
			listPanel.add(makeProductCell(prod.getName(),prodO.getQuantity(),price,i));
			total = total + prodO.getQuantity()*price;
		}
		lbTotal.setText(Integer.toString(total));
	}
    
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("details")) {
			int indice = Integer.parseInt(((JButton) e.getSource()).getName());
			int idProd = ((OrderLine) prodsOrder.get(indice)).getIdProduct();
			Frame.getFrame().setView(new ProductDetailsView(idProd,true,false,this),true);
			Frame.getFrame().revalidate();
			
		} else if (e.getSource() == backButton) {
			Frame.getFrame().setView(this.view,true);
			Frame.getFrame().revalidate();
		}
	}
}
