package ui.view;

import bl.facade.ShopFacade;
import ui.common.Frame;
import ui.common.View;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class AddProductView extends View implements ActionListener{
	private int category;
	private ShopFacade shopFacade;
	private JTextField urlField;
	private JTextField nameField;
	private JTextField brandField;
	private JTextField memberPriceField;
	private JTextField priceField;
	private JTextField qtyField;
	public AddProductView(int category){
		super("New product");
		this.shopFacade = ShopFacade.instance();
		
		this.category = category;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{67, 121, 31, 116, 49, 28, 60, 30, 116, 0};
		gridBagLayout.rowHeights = new int[]{50, 25, 54, 22, 37, 22, 43, 22, 49, 25, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel titleLabel = new JLabel("Add a product to the shop");
		titleLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
		gbc_titleLabel.gridwidth = 4;
		gbc_titleLabel.gridx = 3;
		gbc_titleLabel.gridy = 1;
		add(titleLabel, gbc_titleLabel);
		
		JLabel urlLabel = new JLabel("Product image URL :");
		urlLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_urlLabel = new GridBagConstraints();
		gbc_urlLabel.anchor = GridBagConstraints.WEST;
		gbc_urlLabel.insets = new Insets(0, 0, 5, 5);
		gbc_urlLabel.gridx = 1;
		gbc_urlLabel.gridy = 3;
		add(urlLabel, gbc_urlLabel);
		
		urlField = new JTextField();
		GridBagConstraints gbc_urlField = new GridBagConstraints();
		gbc_urlField.anchor = GridBagConstraints.NORTHWEST;
		gbc_urlField.insets = new Insets(0, 0, 5, 5);
		gbc_urlField.gridx = 3;
		gbc_urlField.gridy = 3;
		add(urlField, gbc_urlField);
		urlField.setColumns(10);
		
		JLabel priceLabel = new JLabel("Price :");
		priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_priceLabel = new GridBagConstraints();
		gbc_priceLabel.anchor = GridBagConstraints.EAST;
		gbc_priceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_priceLabel.gridx = 6;
		gbc_priceLabel.gridy = 3;
		add(priceLabel, gbc_priceLabel);
		
		priceField = new JTextField();
		GridBagConstraints gbc_priceField = new GridBagConstraints();
		gbc_priceField.anchor = GridBagConstraints.NORTHWEST;
		gbc_priceField.insets = new Insets(0, 0, 5, 0);
		gbc_priceField.gridx = 8;
		gbc_priceField.gridy = 3;
		add(priceField, gbc_priceField);
		priceField.setColumns(10);
		
		JLabel nameLabel = new JLabel("Name : ");
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_nameLabel = new GridBagConstraints();
		gbc_nameLabel.anchor = GridBagConstraints.EAST;
		gbc_nameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_nameLabel.gridx = 1;
		gbc_nameLabel.gridy = 5;
		add(nameLabel, gbc_nameLabel);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.anchor = GridBagConstraints.NORTHWEST;
		gbc_nameField.insets = new Insets(0, 0, 5, 5);
		gbc_nameField.gridx = 3;
		gbc_nameField.gridy = 5;
		add(nameField, gbc_nameField);
		nameField.setColumns(10);
		
		JLabel memberPriceLabel = new JLabel("Member price :");
		memberPriceLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_memberPriceLabel = new GridBagConstraints();
		gbc_memberPriceLabel.anchor = GridBagConstraints.WEST;
		gbc_memberPriceLabel.insets = new Insets(0, 0, 5, 5);
		gbc_memberPriceLabel.gridwidth = 2;
		gbc_memberPriceLabel.gridx = 5;
		gbc_memberPriceLabel.gridy = 5;
		add(memberPriceLabel, gbc_memberPriceLabel);
		
		memberPriceField = new JTextField();
		GridBagConstraints gbc_memberPriceField = new GridBagConstraints();
		gbc_memberPriceField.anchor = GridBagConstraints.NORTHWEST;
		gbc_memberPriceField.insets = new Insets(0, 0, 5, 0);
		gbc_memberPriceField.gridx = 8;
		gbc_memberPriceField.gridy = 5;
		add(memberPriceField, gbc_memberPriceField);
		memberPriceField.setColumns(10);
		
		JLabel brandLabel = new JLabel("Brand :");
		brandLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_brandLabel = new GridBagConstraints();
		gbc_brandLabel.anchor = GridBagConstraints.EAST;
		gbc_brandLabel.insets = new Insets(0, 0, 5, 5);
		gbc_brandLabel.gridx = 1;
		gbc_brandLabel.gridy = 7;
		add(brandLabel, gbc_brandLabel);
		
		brandField = new JTextField();
		GridBagConstraints gbc_brandField = new GridBagConstraints();
		gbc_brandField.anchor = GridBagConstraints.NORTHWEST;
		gbc_brandField.insets = new Insets(0, 0, 5, 5);
		gbc_brandField.gridx = 3;
		gbc_brandField.gridy = 7;
		add(brandField, gbc_brandField);
		brandField.setColumns(10);
		
		JLabel qtyLabel = new JLabel("Quantity :");
		qtyLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		GridBagConstraints gbc_qtyLabel = new GridBagConstraints();
		gbc_qtyLabel.anchor = GridBagConstraints.WEST;
		gbc_qtyLabel.insets = new Insets(0, 0, 5, 5);
		gbc_qtyLabel.gridx = 6;
		gbc_qtyLabel.gridy = 7;
		add(qtyLabel, gbc_qtyLabel);
		
		qtyField = new JTextField();
		GridBagConstraints gbc_qtyField = new GridBagConstraints();
		gbc_qtyField.anchor = GridBagConstraints.NORTHWEST;
		gbc_qtyField.insets = new Insets(0, 0, 5, 0);
		gbc_qtyField.gridx = 8;
		gbc_qtyField.gridy = 7;
		add(qtyField, gbc_qtyField);
		qtyField.setColumns(10);
		
		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(this);
		GridBagConstraints gbc_subitBtn = new GridBagConstraints();
		gbc_subitBtn.anchor = GridBagConstraints.EAST;
		gbc_subitBtn.fill = GridBagConstraints.VERTICAL;
		gbc_subitBtn.insets = new Insets(0, 0, 0, 5);
		gbc_subitBtn.gridwidth = 3;
		gbc_subitBtn.gridx = 3;
		gbc_subitBtn.gridy = 9;
		add(submitBtn, gbc_subitBtn);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if ((priceField.getText()!="")
				&&(memberPriceField.getText()!="")
				&&(urlField.getText()!="")
				&&(nameField.getText()!="")
				&&(qtyField.getText()!="")
				&&(brandField.getText()!="")){
					String result = shopFacade.sellProduct(nameField.getText(),brandField.getText(),
							Integer.parseInt(priceField.getText()),Integer.parseInt(memberPriceField.getText()),Integer.parseInt(qtyField.getText()),this.category,urlField.getText());
					
					if (result.equals("ok")){
						JOptionPane.showMessageDialog(null, "Your product was successfully added to the shop","Information",JOptionPane.INFORMATION_MESSAGE);
						Frame.getFrame().setView(new ShopView(), true);
					}else{
						JOptionPane.showMessageDialog(null, "An error occured","Error",JOptionPane.ERROR_MESSAGE);
					}
			
				}else{
					JOptionPane.showMessageDialog(null, "All fields are required","Warning",JOptionPane.WARNING_MESSAGE); 
				}
		
	}
}
