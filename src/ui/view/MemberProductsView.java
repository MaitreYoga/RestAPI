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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import bl.facade.ShopFacade;
import bl.facade.UserFacade;
import dal.product.generic.Invoice;
import dal.product.generic.InvoiceList;
import dal.product.generic.Order;
import dal.product.generic.OrderList;
import dal.product.generic.Product;
import dal.product.generic.ProductLine;
import dal.product.generic.ProductList;
import dal.product.generic.SubscriptionPayment;
import dal.product.generic.SubscriptionPaymentList;
import ui.common.Frame;
import ui.common.VerticalLayout;
import ui.common.View;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

@SuppressWarnings("serial")
public class MemberProductsView extends View implements ActionListener, ChangeListener{
	   	private UserFacade userFacade;
	   	private ShopFacade shopFacade;
		
		private JPanel listPanel;
		private JScrollPane panel;
	    private JButton backButton;
	    private ProductList products; 
	    public List<JButton> removeButtonList = new ArrayList<JButton> ();
	    public List<JButton> detailsButtonList = new ArrayList<JButton> ();
	    
	    private JPanel makeOrderCell(String productName, int productPrice, int quantityAv, int idButton) {
			/* Cellule de la liste*/
			JPanel cell = new JPanel();
			//La cellule est compos�e de 4 �l�ments
			cell.setLayout(new GridLayout(0, 4, 10, 0));
			
			JTextField lbNameProd;
			lbNameProd = new JTextField();
			lbNameProd.setEditable(false);
			lbNameProd.setText(productName);
			lbNameProd.setBackground(new Color(255, 255, 255));
			cell.add(lbNameProd);
			lbNameProd.setColumns(10);
			
			JTextField lbPriceProd;
			lbPriceProd = new JTextField();
			lbPriceProd.setEditable(false);
			lbPriceProd.setText(Integer.toString(productPrice));
			lbPriceProd.setBackground(new Color(255, 255, 255));
			cell.add(lbPriceProd);
			lbPriceProd.setColumns(10);
			
			SpinnerNumberModel modelSpinner = new SpinnerNumberModel(1, 1, 100, 1);
			JSpinner spinner = new JSpinner(modelSpinner);
			spinner.setName(Integer.toString(idButton));
			spinner.setValue(quantityAv);
			spinner.addChangeListener(this);
			cell.add(spinner);
			
			JButton detailsButton = new JButton("Details");
			detailsButton.setName(Integer.toString(idButton));
			detailsButton.setActionCommand("details");
			detailsButton.addActionListener(this);
			detailsButtonList.add(detailsButton);
			cell.add(detailsButton);

			return cell;
	    }
	
	public MemberProductsView() {
		super("My Products");

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
		springLayout.putConstraint(SpringLayout.NORTH, libPanel, 66, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, libPanel, 0, SpringLayout.WEST, panel);
		springLayout.putConstraint(SpringLayout.SOUTH, libPanel, 0, SpringLayout.NORTH, panel);
		springLayout.putConstraint(SpringLayout.EAST, libPanel, 53, SpringLayout.EAST, panel);
		add(libPanel);
		libPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Products");
		lblNewLabel.setBounds(82, 0, 78, 33);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("  Price");
		lblNewLabel_1.setBounds(197, 0, 78, 33);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblNewLabel_1);
		
		JLabel lblDate = new JLabel("     Quantity Available");
		lblDate.setBounds(265, 0, 173, 33);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 15));
		libPanel.add(lblDate);
		
		listPanel = new JPanel();
		panel.setViewportView(listPanel);
		listPanel.setLayout(new VerticalLayout());
		listPanel.add(sepPanel);
	
		setProducts();
		
	}
	
	public void setProducts(){	
		listPanel.removeAll();
		userFacade = UserFacade.instance();
		int idMember = userFacade.getMemberFromLogin();
		products = userFacade.getProductsFromMember(idMember);
		Product product;
		for(int i = 0 ; i < products.size() ; i++) {
			product = products.get(i);
			listPanel.add(makeOrderCell(product.getName(),product.getPrice(), product.getQuantityAvailable(),i));
		}
		listPanel.revalidate();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == backButton){
			Frame.getFrame().setView(new MemberProfileView(),true);
			Frame.getFrame().revalidate();
			
		} else if (e.getActionCommand().equals("details")) {
			int indice = Integer.parseInt(((JButton) e.getSource()).getName());
			int idProd = ((Product) products.get(indice)).getIdProd();
			Frame.getFrame().setView(new ProductDetailsView(idProd,true,false,this),true);
			Frame.getFrame().revalidate();
			
		}
	}

	@Override
	public void stateChanged(ChangeEvent ce) {
		shopFacade = ShopFacade.instance();
		String spinner;
		int newQuant;
		for(int i = 0 ; i < products.size() ; i++) {
			spinner = Integer.toString(i);
			if(((JSpinner) ce.getSource()).getName().equals(spinner)) {
				newQuant = ((int) ((JSpinner) ce.getSource()).getValue());
				shopFacade.updateQuantityAvailable(((Product) products.get(i)).getIdProd(), newQuant);
			}
		}
		
	}
}