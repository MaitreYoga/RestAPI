package ui.view;

import ui.common.Frame;
import ui.common.VerticalLayout;
import ui.common.View;
import bl.facade.ShopFacade;
import bl.facade.UserFacade;
import dal.product.generic.ProductLine;
import dal.product.generic.ProductLineList;

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


@SuppressWarnings("serial")
public class ShoppingCartView extends View implements ActionListener, ChangeListener{
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
    private JButton buyButton;
    private ProductLineList prods;
    
    private JPanel makeProductCell(String prodName, int quantity, int unitPrice, int idButton) {
		/* Cellule de la liste*/
		JPanel cell = new JPanel();
		//La cellule est composée de 4 éléments
		cell.setLayout(new GridLayout(0, 6, 8, 0));
		
		lbProdName = new JTextField();
		lbProdName.setEditable(false);
		lbProdName.setText(prodName);
		cell.add(lbProdName);
		lbProdName.setColumns(8);
		
		SpinnerNumberModel modelSpinner = new SpinnerNumberModel(1, 1, 100, 1);
		JSpinner spinner = new JSpinner(modelSpinner);
		spinner.setName("spinner"+Integer.toString(idButton));
		spinner.setValue(quantity);
		spinner.addChangeListener(this);
		cell.add(spinner);
		
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
		lbProdTot.setName(Integer.toString(idButton));
		lbProdTot.setText(Integer.toString(unitPrice*quantity));
		lbProdTot.setBackground(new Color(255, 255, 255));
		cell.add(lbProdTot);
		lbProdTot.setColumns(8);
		prodTotList.add(lbProdTot);
		
		JButton removeButton = new JButton("Remove");
		removeButton.setName(Integer.toString(idButton));
		removeButton.setActionCommand("remove");
		removeButton.addActionListener(this);
		removeButtonList.add(removeButton);
		cell.add(removeButton);
		
		JButton detailsButton = new JButton("Details");
		detailsButton.setName(Integer.toString(idButton));
		detailsButton.setActionCommand("details");
		detailsButton.addActionListener(this);
		detailsButtonList.add(detailsButton);
		cell.add(detailsButton);
		
		return cell;
    }
    
	public ShoppingCartView() {
		super("Shopping Cart");
		
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
		
		buyButton = new JButton("Buy !");
		springLayout.putConstraint(SpringLayout.SOUTH, buyButton, -36, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, buyButton, -331, SpringLayout.EAST, this);
		buyButton.addActionListener(this);
		add(buyButton);
		
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
		
		JPanel libPanel = new JPanel();
		libPanel.setLayout(new GridLayout(0, 6, 25, 0));
		springLayout.putConstraint(SpringLayout.NORTH, libPanel, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, libPanel, 70, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, libPanel, 0, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, libPanel, 0, SpringLayout.EAST, panel);
		add(libPanel);
		
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
		listPanel.add(sepPanel);
	}
	
	public void init(){
		prods = shopFacade.getAllProductInCart();
		setProducts();
    }

	public void setProducts(){
		listPanel.removeAll();
		int total = 0;
		String priceTest;
		int price;
		ProductLine prodL;
		for(int i = 0 ; i < prods.size() ; i++) {
			prodL = prods.get(i);
			if (userFacade.isMember()){
				priceTest = Integer.toBinaryString(prodL.getMemberPrice());
				if (priceTest.equals("0")){
					price = prodL.getUnitPrice();
				} else {
					price = prodL.getMemberPrice();
				}
			} else{
				price = prodL.getUnitPrice();
			}
			listPanel.add(makeProductCell(prodL.getProdName(),prodL.getQuantity(),price,i));
			total = total + prodL.getQuantity()*price;
		}
		lbTotal.setText(Integer.toString(total));
		listPanel.revalidate();
	}
    
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("remove")){
			boolean confirm = confirm("Are you sure you want to remove this product from your cart ?");
			if (confirm){
				int indice = Integer.parseInt(((JButton) e.getSource()).getName());
				int idProd = ((ProductLine) prods.get(indice)).getIdProduct();
		    	shopFacade.handleRemove(idProd);
		    	prods.remove(indice);
		    	setProducts();
			}
		} else if (e.getActionCommand().equals("details")) {
			int indice = Integer.parseInt(((JButton) e.getSource()).getName());
			int idProd = ((ProductLine) prods.get(indice)).getIdProduct();
			Frame.getFrame().setView(new ProductDetailsView(idProd,true,false,this),true);
			Frame.getFrame().revalidate();
			
		} 	else if (e.getSource() == buyButton) {
			if (prods.size() == 0){
				alert("No product in your cart.");
			} else {
				boolean confirmCart = confirm("Are you sure you want validate your cart ?");
				if (confirmCart){
					alert("Thank you. Sellers will receive a notification to confirm your order.");
					//On envoie la notif aux vendeurs
					List<Integer> idProducts = new ArrayList<Integer>();
					int idProduct;
					for (int i = 0; i<prods.size();i++){
						idProduct = prods.get(i).getIdProduct();
						idProducts.add(idProduct);
					}
					
					// on notifie les vendeurs respectifs
					String message = "New order to confirm.";
					List<String> sellers = shopFacade.getSellers(idProducts);
					userFacade.sendNotifOrder(sellers, message);
					
					//On créé les commandes avec le statut correspondant
					shopFacade.createOrders(prods, sellers);
					
					// on vide le panier
					for (int i = (prods.size()-1); i>=0 ;i--){
						idProduct = prods.get(i).getIdProduct();
						shopFacade.handleRemove(idProduct);
						prods.remove(i);
					}
					setProducts();
				}

			}
		}
	}
	
	public void stateChanged(ChangeEvent ce) {
		int newProdTot = 0;
		int total = 0;
		int newQuant = 0;
		for(int i = 0 ; i < prods.size() ; i++) {
			String spinner = "spinner"+Integer.toString(i);
			if(((JSpinner) ce.getSource()).getName().equals(spinner)) {
				newQuant = ((int) ((JSpinner) ce.getSource()).getValue());
				newProdTot = newQuant*Integer.parseInt(prodPriceList.get(i).getText());
				prodTotList.get(i).setText(Integer.toString(newProdTot));
				shopFacade.changeQuantity(((ProductLine) prods.get(i)).getIdProduct(), newQuant);
			}
			total = total + Integer.parseInt(prodTotList.get(i).getText());
		}
		lbTotal.setText(Integer.toString(total));
	}
}
