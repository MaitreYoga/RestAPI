package ui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ui.common.Frame;
import ui.common.View;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import bl.facade.EventFacade;
import bl.facade.ShopFacade;
import bl.facade.UserFacade;
import dal.product.generic.Order;

@SuppressWarnings("serial")
public class PaymentView extends View implements ActionListener
{
	//constante de prix d'une souscription a modifier si necessaire
	private int amountRegistration = 200;
	
    public EventFacade eventFacade;
    public ShopFacade shopFacade;
    public UserFacade userFacade;

	//labels 
	private JLabel lblCardNumber;
	private JLabel lblCryptogramme;
	private JLabel lblExpDate;
	private JLabel lblAmount;
	private JLabel lblEmptyCardNumber;
	private JLabel lblEmptyCrypto;
	private JLabel lblEmptyExpDate;
	
	//text fields
	private JTextField txtCardNumber;
	private JTextField txtCryptogramme;
	private JTextField txtExpDate;
	private JTextField txtAmount;
	
	//buttons
	private ButtonGroup cbCheckBox;
	private JButton btnSubmitPayment;
	private JRadioButton rdbtnVisaCard;
	private JRadioButton rdbtnMasterCard;
	private JRadioButton rdbtnCB;

	private String event ;
	private String amount;
	private Order order;
	
	// Paiement d'une inscription membre
		public PaymentView() 
		{
			super("Subscription Payment");
			
			setLayout(null);
			cbCheckBox = new ButtonGroup();
			
			rdbtnVisaCard = new JRadioButton("VISA Card");
			rdbtnVisaCard.setBounds(298, 102, 109, 23);
			cbCheckBox.add(rdbtnVisaCard);
			add(rdbtnVisaCard);
			
			rdbtnMasterCard = new JRadioButton("Master Card");
			rdbtnMasterCard.setBounds(148, 102, 109, 23);
			cbCheckBox.add(rdbtnMasterCard);
			add(rdbtnMasterCard);
			
			rdbtnCB = new JRadioButton("CB");
			rdbtnCB.setBounds(43, 102, 79, 23);
			cbCheckBox.add(rdbtnCB);
			add(rdbtnCB);
			
			lblCardNumber = new JLabel("Card Number : ");
			lblCardNumber.setBounds(43, 148, 109, 14);
			add(lblCardNumber);
			
			lblCryptogramme = new JLabel("Cryptogramme :");
			lblCryptogramme.setBounds(43, 186, 109, 14);
			add(lblCryptogramme);
			
			lblExpDate = new JLabel("Expiration Date :");
			lblExpDate.setBounds(43, 216, 109, 23);
			add(lblExpDate);
			
			txtCardNumber = new JTextField();
			txtCardNumber.setBounds(171, 144, 130, 23);
			add(txtCardNumber);
			txtCardNumber.setColumns(10);
			
			txtCryptogramme = new JTextField();
			txtCryptogramme.setColumns(10);
			txtCryptogramme.setBounds(171, 182, 130, 23);
			add(txtCryptogramme);
			
			txtExpDate = new JTextField();
			txtExpDate.setColumns(10);
			txtExpDate.setBounds(171, 216, 130, 23);
			add(txtExpDate);
			
			btnSubmitPayment = new JButton("Submit Payment");
			btnSubmitPayment.setBounds(158, 250, 156, 32);
			btnSubmitPayment.setActionCommand("subscription payment");
			add(btnSubmitPayment);
			
			txtAmount = new JTextField();
			txtAmount.setEditable(false);
			txtAmount.setBounds(171, 56, 97, 23);
			add(txtAmount);
			txtAmount.setColumns(10);
			txtAmount.setBackground(new Color(255, 255, 255));
			txtAmount.setText(Integer.toString(this.amountRegistration));
			
			lblAmount = new JLabel("Amount to pay :");
			lblAmount.setBounds(43, 60, 91, 14);
			add(lblAmount);
			
			//add listeners
			btnSubmitPayment.addActionListener(this);
			
			lblEmptyCardNumber = new JLabel("Fill in your credit card number");
			lblEmptyCardNumber.setForeground(Color.RED);
			lblEmptyCardNumber.setBounds(321, 148, 200, 14);
			lblEmptyCardNumber.setVisible(false);
			add(lblEmptyCardNumber);
			
			lblEmptyCrypto = new JLabel("Fill in your cryptogramme");
			lblEmptyCrypto.setForeground(Color.RED);
			lblEmptyCrypto.setBounds(321, 186, 200, 14);
			lblEmptyCrypto.setVisible(false);
			add(lblEmptyCrypto);
			
			lblEmptyExpDate = new JLabel("Fill in your cards expiration date");
			lblEmptyExpDate.setForeground(Color.RED);
			lblEmptyExpDate.setBounds(321, 220, 200, 14);
			lblEmptyExpDate.setVisible(false);
			add(lblEmptyExpDate);
		}
	
	// Paiement d'un evenement
	public PaymentView(String event, String amount) 
	{
		super(event+" payment");
		this.event = event ;
		this.amount = amount;
		setLayout(null);

		eventFacade = new EventFacade();
		shopFacade = new ShopFacade();
		
		rdbtnVisaCard = new JRadioButton("VISA Card");
		rdbtnVisaCard.setBounds(298, 102, 109, 23);
		add(rdbtnVisaCard);
		
		rdbtnMasterCard = new JRadioButton("Master Card");
		rdbtnMasterCard.setBounds(148, 102, 109, 23);
		add(rdbtnMasterCard);
		
		rdbtnCB = new JRadioButton("CB");
		rdbtnCB.setBounds(43, 102, 79, 23);
		add(rdbtnCB);
		
		lblCardNumber = new JLabel("Card Number : ");
		lblCardNumber.setBounds(43, 148, 109, 14);
		add(lblCardNumber);
		
		lblCryptogramme = new JLabel("Cryptogramme :");
		lblCryptogramme.setBounds(43, 186, 109, 14);
		add(lblCryptogramme);
		
		lblExpDate = new JLabel("Expiration Date :");
		lblExpDate.setBounds(43, 216, 109, 23);
		add(lblExpDate);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setBounds(171, 144, 130, 23);
		add(txtCardNumber);
		txtCardNumber.setColumns(10);
		
		txtCryptogramme = new JTextField();
		txtCryptogramme.setColumns(10);
		txtCryptogramme.setBounds(171, 182, 130, 23);
		add(txtCryptogramme);
		
		txtExpDate = new JTextField();
		txtExpDate.setColumns(10);
		txtExpDate.setBounds(171, 216, 130, 23);
		add(txtExpDate);
		
		btnSubmitPayment = new JButton("Submit Payment");
		btnSubmitPayment.setBounds(158, 250, 156, 32);
		btnSubmitPayment.setActionCommand("event payment");
		add(btnSubmitPayment);
		
		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setBounds(171, 56, 97, 23);
		add(txtAmount);
		txtAmount.setColumns(10);
		txtAmount.setBackground(new Color(255, 255, 255));
		txtAmount.setText(amount);
		
		lblAmount = new JLabel("Amount to pay :");
		lblAmount.setBounds(43, 60, 91, 14);
		add(lblAmount);
		
		//add listeners
		btnSubmitPayment.addActionListener(this);
		
		lblEmptyCardNumber = new JLabel("Fill in your credit card number");
		lblEmptyCardNumber.setForeground(Color.RED);
		lblEmptyCardNumber.setBounds(321, 148, 200, 14);
		lblEmptyCardNumber.setVisible(false);
		add(lblEmptyCardNumber);
		
		lblEmptyCrypto = new JLabel("Fill in your cryptogramme");
		lblEmptyCrypto.setForeground(Color.RED);
		lblEmptyCrypto.setBounds(321, 186, 200, 14);
		lblEmptyCrypto.setVisible(false);
		add(lblEmptyCrypto);
		
		lblEmptyExpDate = new JLabel("Fill in your cards expiration date");
		lblEmptyExpDate.setForeground(Color.RED);
		lblEmptyExpDate.setBounds(321, 220, 200, 14);
		lblEmptyExpDate.setVisible(false);
		add(lblEmptyExpDate);
	}
	
	// Paiement d'une commande
	public PaymentView(Order order) 
	{
		super("Order "+order.getIdOrder()+" payment");
		this.order = order;
		shopFacade = new ShopFacade();
		this.amount = Integer.toString(shopFacade.getAmountOfOrderMember(order.getIdOrder()));
		
		setLayout(null);
		cbCheckBox = new ButtonGroup();
		
		rdbtnVisaCard = new JRadioButton("VISA Card");
		rdbtnVisaCard.setBounds(298, 102, 109, 23);
		cbCheckBox.add(rdbtnVisaCard);
		add(rdbtnVisaCard);
		
		rdbtnMasterCard = new JRadioButton("Master Card");
		rdbtnMasterCard.setBounds(148, 102, 109, 23);
		cbCheckBox.add(rdbtnMasterCard);
		add(rdbtnMasterCard);
		
		rdbtnCB = new JRadioButton("CB");
		rdbtnCB.setBounds(43, 102, 79, 23);
		cbCheckBox.add(rdbtnCB);
		add(rdbtnCB);
		
		lblCardNumber = new JLabel("Card Number : ");
		lblCardNumber.setBounds(43, 148, 109, 14);
		add(lblCardNumber);
		
		lblCryptogramme = new JLabel("Cryptogramme :");
		lblCryptogramme.setBounds(43, 186, 109, 14);
		add(lblCryptogramme);
		
		lblExpDate = new JLabel("Expiration Date :");
		lblExpDate.setBounds(43, 216, 109, 23);
		add(lblExpDate);
		
		txtCardNumber = new JTextField();
		txtCardNumber.setBounds(171, 144, 130, 23);
		add(txtCardNumber);
		txtCardNumber.setColumns(10);
		
		txtCryptogramme = new JTextField();
		txtCryptogramme.setColumns(10);
		txtCryptogramme.setBounds(171, 182, 130, 23);
		add(txtCryptogramme);
		
		txtExpDate = new JTextField();
		txtExpDate.setColumns(10);
		txtExpDate.setBounds(171, 216, 130, 23);
		add(txtExpDate);
		
		btnSubmitPayment = new JButton("Submit Payment");
		btnSubmitPayment.setBounds(158, 250, 156, 32);
		btnSubmitPayment.setActionCommand("order payment");
		add(btnSubmitPayment);
		
		txtAmount = new JTextField();
		txtAmount.setEditable(false);
		txtAmount.setBounds(171, 56, 97, 23);
		add(txtAmount);
		txtAmount.setColumns(10);
		txtAmount.setBackground(new Color(255, 255, 255));
		txtAmount.setText(this.amount);
		
		lblAmount = new JLabel("Amount to pay :");
		lblAmount.setBounds(43, 60, 91, 14);
		add(lblAmount);
		
		//add listeners
		btnSubmitPayment.addActionListener(this);
		
		lblEmptyCardNumber = new JLabel("Fill in your credit card number");
		lblEmptyCardNumber.setForeground(Color.RED);
		lblEmptyCardNumber.setBounds(321, 148, 200, 14);
		lblEmptyCardNumber.setVisible(false);
		add(lblEmptyCardNumber);
		
		lblEmptyCrypto = new JLabel("Fill in your cryptogramme");
		lblEmptyCrypto.setForeground(Color.RED);
		lblEmptyCrypto.setBounds(321, 186, 200, 14);
		lblEmptyCrypto.setVisible(false);
		add(lblEmptyCrypto);
		
		lblEmptyExpDate = new JLabel("Fill in your cards expiration date");
		lblEmptyExpDate.setForeground(Color.RED);
		lblEmptyExpDate.setBounds(321, 220, 200, 14);
		lblEmptyExpDate.setVisible(false);
		add(lblEmptyExpDate);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		boolean error = false;
		
    	lblEmptyCardNumber.setVisible(txtCardNumber.getText().isEmpty());
    	if(lblEmptyCardNumber.isVisible())
    		error = true;
    	
    	lblEmptyCrypto.setVisible(txtCryptogramme.getText().isEmpty());
    	if(lblEmptyCrypto.isVisible())
    		error = true;
    	
    	lblEmptyExpDate.setVisible(txtExpDate.getText().isEmpty());
    	if(lblEmptyExpDate.isVisible())
    		error = true;
    	
    	if(!error)
    	{
    		if (e.getActionCommand().equals("event payment")){
        		RegisterPayment(event);
        		Frame.getFrame().setView(new EventInscriptionView(),true);
        		Frame.getFrame().revalidate();
        		
    		} else if (e.getActionCommand().equals("order payment")){
    			userFacade = new UserFacade();
    			
    			// on recupere la date du jour
    			String paymentDate;
    			String format = "dd/MM/yy";
    			SimpleDateFormat formater = new SimpleDateFormat( format ); 
    			Date date = new Date();
    			paymentDate = formater.format( date );
    			// on recupere le type de paiement selectionné
    			String paymentType = null;
    			if (rdbtnVisaCard.isSelected()){
    				paymentType = rdbtnVisaCard.getText();
    			} else if (rdbtnMasterCard.isSelected()){
    				paymentType = rdbtnMasterCard.getText();
    			} else if (rdbtnCB.isSelected()){
    				paymentType = rdbtnCB.getText();
    			}
    			
    			// on recupere le montant
    			int amountPaid = Integer.parseInt(this.amount);
    			
    			//on recupere l'id de la commande
    			int idOrder = order.getIdOrder();
    			
    			shopFacade.createInvoice(amountPaid, paymentType, paymentDate, idOrder);
    			shopFacade.updateStateOrder(idOrder, "P");
    			
    			// on envoie une notification au vendeur
				String message = "Order "+idOrder+" has been payed by the buyer";
				List<String> sellerToSend = new ArrayList<String>();
				sellerToSend.add(shopFacade.getSellerFromOrder(idOrder));
				userFacade.sendNotifOrder(sellerToSend, message);
 
    			inform("Your payment was successfuly done !\n The seller will contact you soon to complete the transaction");
    			Frame.getFrame().setView(new UserOrdersView(),true);
    			Frame.getFrame().revalidate();
    		
    		} else if (e.getActionCommand().equals("subscription payment")){
    			userFacade = new UserFacade();
    			
    			// on recupere le type de paiement selectionné
    			String paymentType = null;
    			if (rdbtnVisaCard.isSelected()){
    				paymentType = rdbtnVisaCard.getText();
    			} else if (rdbtnMasterCard.isSelected()){
    				paymentType = rdbtnMasterCard.getText();
    			} else if (rdbtnCB.isSelected()){
    				paymentType = rdbtnCB.getText();
    			}
    			
    			// on recupere le montant
    			int amountPaid = this.amountRegistration;
    			
    			// on enregistre le nouveau membre
    			int idNewMember = userFacade.registerMember();
    			
    			// on enregistre le paiement
    			userFacade.createSubscriptionPayment(idNewMember, amountPaid, paymentType);
    			
    			inform("Thank you for your registration !");
    			Frame.getFrame().setView(new MemberProfileView(),true);
    			Frame.getFrame().revalidate();
    		}

    	}

	}

	private void RegisterPayment(String event) 
	{
    	String login = eventFacade.handleEventInscription(event);
    	int memberID = getMemberID(login);
    	int eventID = getEventID(event);
		if (eventFacade.finaliseInscription(memberID,eventID,Integer.parseInt(txtAmount.getText())))
		{
			inform("Your payment was successfuly done !\n You are now registered for the "+event+ " event");
		}
		
		else
		{
			alert("You are already registered for the "+event+" event !");
		}
	}

	private int getEventID(String event) 
	{
		return eventFacade.getEventID(event);
	}

	private int getMemberID(String login) 
	{
		return eventFacade.getMember(login);
	}
}
