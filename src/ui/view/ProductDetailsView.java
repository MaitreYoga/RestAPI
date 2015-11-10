package ui.view;

import ui.common.Frame;
import ui.common.View;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;

import bl.facade.ShopFacade;
import bl.facade.UserFacade;
import dal.product.generic.Product;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.net.MalformedURLException;
import java.net.URL;

@SuppressWarnings("serial")
public class ProductDetailsView extends View implements ActionListener{
	private JTextField sellerTextField;
	private JTextField brandTextField;
	private JTextField quantTextField;
	private JTextField normalPriceTextField;
	private JTextField membPriceTextField;
	private JButton btnBack;
	JButton btnAddToCart;
	private ShopFacade shopFacade = ShopFacade.instance();
	private UserFacade userFacade = UserFacade.instance();
	View comingFromView;
	
	public Product prod;

	public ProductDetailsView(int product,boolean showButtonBack, boolean showButtonAdd, View comingFromView) {
		super("Product Details");
		
		this.comingFromView=comingFromView;
		this.prod = shopFacade.getProduct(product);
		String sellerLogin = userFacade.getLoginFromMember(prod.getSeller());
		
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		JPanel panel = new JPanel();
		try {
			final Image bg = new ImageIcon(new URL(this.prod.getUrlImage())).getImage();
			panel = new JPanel(){
				@Override
				public void paintComponent(Graphics g){
					g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
				}
			};
		} catch (MalformedURLException e) {
			panel.setBackground(Color.WHITE);
		}finally{
			springLayout.putConstraint(SpringLayout.NORTH, panel, 70, SpringLayout.NORTH, this);
			springLayout.putConstraint(SpringLayout.WEST, panel, 43, SpringLayout.WEST, this);
			springLayout.putConstraint(SpringLayout.SOUTH, panel, -144, SpringLayout.SOUTH, this);
			panel.setBounds(129, 66, 215, 202);
			add(panel);
		}


			btnAddToCart = new JButton("Add to Cart");
			springLayout.putConstraint(SpringLayout.SOUTH, btnAddToCart, 0, SpringLayout.SOUTH, panel);
			btnAddToCart.addActionListener(this);
			btnAddToCart.setBounds(358, 245, 110, 23);
			if (!showButtonAdd){
				btnAddToCart.setVisible(false);
			}
			add(btnAddToCart);
		
		
		JLabel lblSeller = new JLabel("Seller:");
		springLayout.putConstraint(SpringLayout.NORTH, lblSeller, 73, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, lblSeller, -317, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.WEST, btnAddToCart, 0, SpringLayout.WEST, lblSeller);
		springLayout.putConstraint(SpringLayout.EAST, panel, -29, SpringLayout.WEST, lblSeller);
		lblSeller.setBounds(376, 70, 43, 14);
		add(lblSeller);
		
		sellerTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, sellerTextField, 70, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, sellerTextField, 35, SpringLayout.EAST, lblSeller);
		springLayout.putConstraint(SpringLayout.EAST, sellerTextField, -193, SpringLayout.EAST, this);
		sellerTextField.setBounds(426, 66, 104, 22);
		sellerTextField.setEditable(false);
		sellerTextField.setText(sellerLogin);
		sellerTextField.setBackground(new Color(255, 255, 255));
		add(sellerTextField);
		sellerTextField.setColumns(10);
		
		JLabel lblBrand = new JLabel("Brand:");
		springLayout.putConstraint(SpringLayout.NORTH, lblBrand, 12, SpringLayout.SOUTH, lblSeller);
		springLayout.putConstraint(SpringLayout.WEST, lblBrand, 0, SpringLayout.WEST, btnAddToCart);
		lblBrand.setBounds(376, 103, 43, 14);
		add(lblBrand);
		
		brandTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, brandTextField, 6, SpringLayout.SOUTH, sellerTextField);
		springLayout.putConstraint(SpringLayout.WEST, brandTextField, 0, SpringLayout.WEST, sellerTextField);
		springLayout.putConstraint(SpringLayout.EAST, brandTextField, -193, SpringLayout.EAST, this);
		brandTextField.setBounds(426, 99, 104, 23);
		brandTextField.setEditable(false);
		brandTextField.setText(prod.getBrand());
		brandTextField.setBackground(new Color(255, 255, 255));
		add(brandTextField);
		brandTextField.setColumns(10);
		
		JLabel lblQuantityAvailable = new JLabel("Quantity Available:");
		springLayout.putConstraint(SpringLayout.NORTH, lblQuantityAvailable, 14, SpringLayout.SOUTH, brandTextField);
		springLayout.putConstraint(SpringLayout.WEST, lblQuantityAvailable, 0, SpringLayout.WEST, lblSeller);
		lblQuantityAvailable.setBounds(376, 141, 110, 14);
		add(lblQuantityAvailable);
		
		quantTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, quantTextField, -3, SpringLayout.NORTH, lblQuantityAvailable);
		springLayout.putConstraint(SpringLayout.WEST, quantTextField, 24, SpringLayout.EAST, lblQuantityAvailable);
		springLayout.putConstraint(SpringLayout.EAST, quantTextField, -193, SpringLayout.EAST, this);
		quantTextField.setBounds(487, 137, 43, 23);
		quantTextField.setEditable(false);
		quantTextField.setColumns(10);
		quantTextField.setText(Integer.toString(prod.getQuantityAvailable()));
		quantTextField.setBackground(new Color(255, 255, 255));
		add(quantTextField);
		
		JLabel lblNormalPrice = new JLabel("Normal Price:");
		springLayout.putConstraint(SpringLayout.NORTH, lblNormalPrice, 15, SpringLayout.SOUTH, lblQuantityAvailable);
		springLayout.putConstraint(SpringLayout.WEST, lblNormalPrice, 0, SpringLayout.WEST, lblSeller);
		lblNormalPrice.setBounds(376, 182, 85, 14);
		add(lblNormalPrice);
		
		JLabel lblMemberPrice = new JLabel("Member Price:");
		springLayout.putConstraint(SpringLayout.NORTH, lblMemberPrice, 14, SpringLayout.SOUTH, lblNormalPrice);
		springLayout.putConstraint(SpringLayout.WEST, lblMemberPrice, 0, SpringLayout.WEST, lblSeller);
		lblMemberPrice.setBounds(378, 215, 90, 14);
		add(lblMemberPrice);
		
		normalPriceTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, normalPriceTextField, -3, SpringLayout.NORTH, lblNormalPrice);
		springLayout.putConstraint(SpringLayout.WEST, normalPriceTextField, 42, SpringLayout.EAST, lblNormalPrice);
		springLayout.putConstraint(SpringLayout.EAST, normalPriceTextField, -193, SpringLayout.EAST, this);
		normalPriceTextField.setBounds(487, 177, 43, 23);
		normalPriceTextField.setEditable(false);
		normalPriceTextField.setColumns(10);
		normalPriceTextField.setText(Integer.toString(prod.getPrice()));
		normalPriceTextField.setBackground(new Color(255, 255, 255));
		add(normalPriceTextField);
		
		membPriceTextField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, membPriceTextField, -3, SpringLayout.NORTH, lblMemberPrice);
		springLayout.putConstraint(SpringLayout.WEST, membPriceTextField, 37, SpringLayout.EAST, lblMemberPrice);
		springLayout.putConstraint(SpringLayout.EAST, membPriceTextField, -193, SpringLayout.EAST, this);
		membPriceTextField.setBounds(487, 211, 43, 23);
		membPriceTextField.setEditable(false);
		membPriceTextField.setColumns(10);
		if (Integer.toString(prod.getMemberPrice()).equals("0")){
			membPriceTextField.setText(Integer.toString(prod.getPrice()));
		} else{
			membPriceTextField.setText(Integer.toString(prod.getMemberPrice()));
		}
		membPriceTextField.setBackground(new Color(255, 255, 255));
		add(membPriceTextField);
		
		
		btnBack = new JButton("Back");
		springLayout.putConstraint(SpringLayout.WEST, btnBack, 263, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnBack, -44, SpringLayout.SOUTH, this);
		btnBack.setBounds(295, 316, 72, 23);
		btnBack.addActionListener(this);
		if(!showButtonBack){
			btnBack.setVisible(false);
		}
		add(btnBack);
		
		JLabel lblProduct = new JLabel("Product");
		springLayout.putConstraint(SpringLayout.WEST, lblProduct, 130, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, lblProduct, -6, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, lblProduct, 22, SpringLayout.EAST, panel);
		lblProduct.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 17));
		lblProduct.setText(prod.getName());
		add(lblProduct);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnBack){
			Frame.getFrame().setView(comingFromView,true);
			Frame.getFrame().revalidate();
		} else if (e.getSource() == btnAddToCart){
			shopFacade.addToCart(prod.getIdProd());
			alert("Product added !");
		}
		
	}
}
