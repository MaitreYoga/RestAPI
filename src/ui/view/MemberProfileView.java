package ui.view;

import ui.common.Frame;
import ui.common.View;
import bl.facade.UserFacade;
import dal.product.generic.SubscriptionPayment;

import javax.swing.SpringLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

import java.awt.SystemColor;

@SuppressWarnings("serial")
public class MemberProfileView extends View implements ActionListener{
	
	public UserFacade userFacade;
	private JTextField lastPTextField;
	private JTextField amountTextField;
	private JButton btnHistory;
	private JButton btnMyProducts;
	private JTextField idMemberTextField;
	
    public MemberProfileView() {
		super("Member");
		userFacade = new UserFacade();
    	boolean testMember = userFacade.isMember();
    	if (!testMember){
			Frame.getFrame().setView(new MemberSubscriptionView(),true);
			Frame.getFrame().revalidate();
    	} else{
		
		setLayout(null);
		
		btnHistory = new JButton("Payments History");
		btnHistory.setBounds(274, 163, 141, 23);
		btnHistory.addActionListener(this);
		add(btnHistory);
		
		btnMyProducts = new JButton("My Products");
		btnMyProducts.setBounds(274, 220, 141, 23);
		btnMyProducts.addActionListener(this);
		add(btnMyProducts);
		
		JPanel panel = new JPanel();
		panel.setBounds(32, 27, 176, 86);
		panel.setForeground(SystemColor.activeCaption);
		panel.setBackground(Color.WHITE);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("Last Payment");
		label.setBounds(38, 3, 101, 19);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		panel.add(label);
		
		amountTextField = new JTextField();
		amountTextField.setBounds(71, 30, 75, 20);
		amountTextField.setEditable(false);
		panel.add(amountTextField);
		amountTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("On:");
		lblNewLabel.setBounds(10, 59, 27, 14);
		panel.add(lblNewLabel);
		
		JLabel lblAmount = new JLabel("Amount:");
		lblAmount.setBounds(10, 33, 51, 14);
		panel.add(lblAmount);
		
		lastPTextField = new JTextField();
		lastPTextField.setBounds(71, 56, 75, 20);
		lastPTextField.setColumns(10);
		lastPTextField.setEditable(false);
		panel.add(lastPTextField);
		
		JLabel lblNumMember = new JLabel("Num Member:");
		lblNumMember.setBounds(466, 47, 82, 14);
		add(lblNumMember);
		
		idMemberTextField = new JTextField();
		idMemberTextField.setColumns(10);
		idMemberTextField.setBounds(558, 44, 75, 20);
		idMemberTextField.setEditable(false);
		idMemberTextField.setBackground(Color.WHITE);
		add(idMemberTextField);
    	}
	}
    
    public void init(){
    	userFacade = new UserFacade();
    	boolean testMember = userFacade.isMember();
    	System.out.println(testMember);
    	if (!testMember){
			Frame.getFrame().setView(new MemberSubscriptionView(),true);
			Frame.getFrame().revalidate();
    	} else{
        	setValues();
    	}

    }
    
    public void setValues(){
    	userFacade = new UserFacade();
    	
    	int idMember = userFacade.getMemberFromLogin();
    	SubscriptionPayment lastPayment = userFacade.getLastSubscriptionPayment(idMember);
    	
    	idMemberTextField.setText(userFacade.getLoginFromMember(idMember));
    	amountTextField.setText(Integer.toString(lastPayment.getAmountPaid()));
    	lastPTextField.setText(lastPayment.getDatePayment());
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnHistory){
			Frame.getFrame().setView(new MemberPaymentsHistoryView(),true);
			Frame.getFrame().revalidate();
		} else if (e.getSource() == btnMyProducts){
			Frame.getFrame().setView(new MemberProductsView(),true);
			Frame.getFrame().revalidate();
		}
		
	}
}