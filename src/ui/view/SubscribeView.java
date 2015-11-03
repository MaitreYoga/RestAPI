package ui.view;

import ui.common.Frame;
import ui.common.View;
import bl.facade.UserFacade;
import bl.other.Utilitary;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import java.awt.Font;

import javax.swing.JPasswordField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

@SuppressWarnings("serial")
public class SubscribeView extends View implements ActionListener {
	private JTextField loginField;
	private JTextField firstNameField;
	private JTextField phoneField;
	private JTextField lastNameField;
	private JTextField mailField;
	private JTextField adressField;
	private JTextField jobField;
	
	private JCheckBox speakerChk;
	private JLabel jobLb;
	
	private JLabel emptyLogin;
	private JLabel emptyPwd;
	private JLabel wrongPwd;
	private JLabel emptyFirstName;
	private JLabel emptyLastName;
	
	private UserFacade userFacade;
	private JPasswordField pwd1Field;
	private JPasswordField pwd2Field;
	
    public SubscribeView() {
		super("Subscribe");
		userFacade = new UserFacade();
		
		SpringLayout springLayout = new SpringLayout();
		this.setLayout(springLayout);
		
		JLabel welcomeLb = new JLabel("Welcome to ZenLounge");
		springLayout.putConstraint(SpringLayout.NORTH, welcomeLb, 21, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, welcomeLb, 251, SpringLayout.WEST, this);
		welcomeLb.setFont(new Font("Sylfaen", Font.BOLD, 16));
		add(welcomeLb);
		
		JLabel loginLb = new JLabel("Login*");
		springLayout.putConstraint(SpringLayout.WEST, loginLb, 158, SpringLayout.WEST, this);
		add(loginLb);
		
		JLabel pwd1Lb = new JLabel("Password*");
		springLayout.putConstraint(SpringLayout.NORTH, pwd1Lb, 18, SpringLayout.SOUTH, loginLb);
		springLayout.putConstraint(SpringLayout.WEST, pwd1Lb, 0, SpringLayout.WEST, loginLb);
		add(pwd1Lb);
		
		JLabel firstNameLb = new JLabel("First Name*");
		springLayout.putConstraint(SpringLayout.WEST, firstNameLb, 158, SpringLayout.WEST, this);
		add(firstNameLb);
		
		JLabel lastNameLb = new JLabel("Last Name*");
		springLayout.putConstraint(SpringLayout.WEST, lastNameLb, 158, SpringLayout.WEST, this);
		add(lastNameLb);
		
		JLabel phoneLb = new JLabel("Phone");
		springLayout.putConstraint(SpringLayout.WEST, phoneLb, 158, SpringLayout.WEST, this);
		add(phoneLb);
		
		JLabel mailLb = new JLabel("Mail");
		springLayout.putConstraint(SpringLayout.WEST, mailLb, 158, SpringLayout.WEST, this);
		add(mailLb);
		
		JLabel pwd2Lb = new JLabel("Password*");
		springLayout.putConstraint(SpringLayout.NORTH, firstNameLb, 21, SpringLayout.SOUTH, pwd2Lb);
		springLayout.putConstraint(SpringLayout.NORTH, pwd2Lb, 27, SpringLayout.SOUTH, pwd1Lb);
		springLayout.putConstraint(SpringLayout.WEST, pwd2Lb, 0, SpringLayout.WEST, loginLb);
		add(pwd2Lb);
		
		JLabel adressLb = new JLabel("Adress");
		springLayout.putConstraint(SpringLayout.WEST, adressLb, 158, SpringLayout.WEST, this);
		add(adressLb);
		
		speakerChk = new JCheckBox("I'm a speaker");
		springLayout.putConstraint(SpringLayout.NORTH, speakerChk, 18, SpringLayout.SOUTH, adressLb);
		springLayout.putConstraint(SpringLayout.WEST, speakerChk, 158, SpringLayout.WEST, this);
		add(speakerChk);
		speakerChk.addActionListener(this);
		speakerChk.setActionCommand("Speaker");
		
		jobLb = new JLabel("Job");
		springLayout.putConstraint(SpringLayout.NORTH, jobLb, 4, SpringLayout.NORTH, speakerChk);
		jobLb.setVisible(false);
		add(jobLb);
		
		JButton subscribeBtn = new JButton("Subscribe");
		springLayout.putConstraint(SpringLayout.WEST, subscribeBtn, 304, SpringLayout.WEST, this);
		add(subscribeBtn);
		subscribeBtn.addActionListener(this);
		subscribeBtn.setActionCommand("Subscribe");
		
		loginField = new JTextField();
		springLayout.putConstraint(SpringLayout.EAST, loginLb, -22, SpringLayout.WEST, loginField);
		springLayout.putConstraint(SpringLayout.WEST, loginField, 262, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.WEST, jobLb, 0, SpringLayout.WEST, loginField);
		springLayout.putConstraint(SpringLayout.NORTH, loginLb, 3, SpringLayout.NORTH, loginField);
		springLayout.putConstraint(SpringLayout.NORTH, loginField, 30, SpringLayout.SOUTH, welcomeLb);
		loginField.setHorizontalAlignment(SwingConstants.CENTER);
		loginField.setToolTipText("");
		add(loginField);
		loginField.setColumns(10);
		
		firstNameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, firstNameField, -3, SpringLayout.NORTH, firstNameLb);
		springLayout.putConstraint(SpringLayout.WEST, firstNameField, 0, SpringLayout.WEST, jobLb);
		firstNameField.setHorizontalAlignment(SwingConstants.CENTER);
		firstNameField.setToolTipText("");
		firstNameField.setColumns(10);
		add(firstNameField);
		
		phoneField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, phoneLb, 3, SpringLayout.NORTH, phoneField);
		springLayout.putConstraint(SpringLayout.WEST, phoneField, 262, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, phoneField, 0, SpringLayout.EAST, loginField);
		phoneField.setHorizontalAlignment(SwingConstants.CENTER);
		phoneField.setToolTipText("");
		phoneField.setColumns(10);
		add(phoneField);
		
		lastNameField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, lastNameField, 15, SpringLayout.SOUTH, firstNameField);
		springLayout.putConstraint(SpringLayout.NORTH, lastNameLb, 3, SpringLayout.NORTH, lastNameField);
		springLayout.putConstraint(SpringLayout.NORTH, phoneField, 12, SpringLayout.SOUTH, lastNameField);
		springLayout.putConstraint(SpringLayout.WEST, lastNameField, 262, SpringLayout.WEST, this);
		lastNameField.setHorizontalAlignment(SwingConstants.CENTER);
		lastNameField.setToolTipText("");
		lastNameField.setColumns(10);
		add(lastNameField);
		
		mailField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, mailLb, 3, SpringLayout.NORTH, mailField);
		springLayout.putConstraint(SpringLayout.NORTH, mailField, 18, SpringLayout.SOUTH, phoneField);
		springLayout.putConstraint(SpringLayout.WEST, mailField, 262, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, mailField, 0, SpringLayout.EAST, loginField);
		mailField.setHorizontalAlignment(SwingConstants.CENTER);
		mailField.setToolTipText("");
		mailField.setColumns(10);
		add(mailField);
		
		adressField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, subscribeBtn, 44, SpringLayout.SOUTH, adressField);
		springLayout.putConstraint(SpringLayout.NORTH, adressField, 15, SpringLayout.SOUTH, mailField);
		springLayout.putConstraint(SpringLayout.NORTH, adressLb, 3, SpringLayout.NORTH, adressField);
		springLayout.putConstraint(SpringLayout.WEST, adressField, 262, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, adressField, 0, SpringLayout.EAST, loginField);
		adressField.setHorizontalAlignment(SwingConstants.CENTER);
		adressField.setToolTipText("");
		adressField.setColumns(10);
		add(adressField);
		
		jobField = new JTextField();
		springLayout.putConstraint(SpringLayout.NORTH, jobField, 1, SpringLayout.NORTH, speakerChk);
		springLayout.putConstraint(SpringLayout.WEST, jobField, 0, SpringLayout.WEST, subscribeBtn);
		springLayout.putConstraint(SpringLayout.EAST, jobField, 0, SpringLayout.EAST, loginField);
		jobField.setVisible(false);
		jobField.setHorizontalAlignment(SwingConstants.CENTER);
		jobField.setToolTipText("");
		jobField.setColumns(10);
		add(jobField);
		
		emptyLogin = new JLabel("Login can't be empty");
		springLayout.putConstraint(SpringLayout.EAST, loginField, -63, SpringLayout.WEST, emptyLogin);
		springLayout.putConstraint(SpringLayout.NORTH, emptyLogin, 76, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, emptyLogin, 475, SpringLayout.WEST, this);
		emptyLogin.setVisible(false);
		emptyLogin.setForeground(Color.RED);
		add(emptyLogin);
		
		emptyPwd = new JLabel("Password can't be empty");
		springLayout.putConstraint(SpringLayout.NORTH, emptyPwd, 18, SpringLayout.SOUTH, emptyLogin);
		springLayout.putConstraint(SpringLayout.WEST, emptyPwd, 475, SpringLayout.WEST, this);
		emptyPwd.setVisible(false);
		emptyPwd.setForeground(Color.RED);
		add(emptyPwd);
		
		wrongPwd = new JLabel("Passwords must correspond");
		springLayout.putConstraint(SpringLayout.NORTH, wrongPwd, 27, SpringLayout.SOUTH, emptyPwd);
		springLayout.putConstraint(SpringLayout.WEST, wrongPwd, 0, SpringLayout.WEST, emptyLogin);
		wrongPwd.setVisible(false);
		wrongPwd.setForeground(Color.RED);
		add(wrongPwd);
		
		emptyFirstName = new JLabel("First name can't be empty");
		springLayout.putConstraint(SpringLayout.EAST, firstNameField, -63, SpringLayout.WEST, emptyFirstName);
		springLayout.putConstraint(SpringLayout.NORTH, emptyFirstName, 21, SpringLayout.SOUTH, wrongPwd);
		springLayout.putConstraint(SpringLayout.WEST, emptyFirstName, 0, SpringLayout.WEST, emptyLogin);
		emptyFirstName.setVisible(false);
		emptyFirstName.setForeground(Color.RED);
		add(emptyFirstName);
		
		emptyLastName = new JLabel("Last name can't be empty");
		springLayout.putConstraint(SpringLayout.NORTH, emptyLastName, 21, SpringLayout.SOUTH, emptyFirstName);
		springLayout.putConstraint(SpringLayout.EAST, lastNameField, -63, SpringLayout.WEST, emptyLastName);
		springLayout.putConstraint(SpringLayout.WEST, emptyLastName, 0, SpringLayout.WEST, emptyLogin);
		emptyLastName.setVisible(false);
		emptyLastName.setForeground(Color.RED);
		add(emptyLastName);
		
		JLabel requiredLabel = new JLabel("* : Required");
		springLayout.putConstraint(SpringLayout.EAST, subscribeBtn, -152, SpringLayout.WEST, requiredLabel);
		springLayout.putConstraint(SpringLayout.NORTH, requiredLabel, 156, SpringLayout.SOUTH, emptyLastName);
		springLayout.putConstraint(SpringLayout.EAST, requiredLabel, -58, SpringLayout.EAST, this);
		add(requiredLabel);
		
		JButton backBtn = new JButton("Back");
		springLayout.putConstraint(SpringLayout.NORTH, backBtn, 0, SpringLayout.NORTH, welcomeLb);
		springLayout.putConstraint(SpringLayout.WEST, backBtn, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, backBtn, -343, SpringLayout.EAST, welcomeLb);
		backBtn.setFont(new Font("Tahoma", Font.ITALIC, 11));
		backBtn.setContentAreaFilled(false);
		backBtn.setBorderPainted(false);
		add(backBtn);
		backBtn.addActionListener(this);
		backBtn.setActionCommand("Back");
		
		pwd1Field = new JPasswordField();
		pwd1Field.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.NORTH, pwd1Field, -3, SpringLayout.NORTH, pwd1Lb);
		springLayout.putConstraint(SpringLayout.WEST, pwd1Field, 0, SpringLayout.WEST, jobLb);
		springLayout.putConstraint(SpringLayout.EAST, pwd1Field, 0, SpringLayout.EAST, loginField);
		add(pwd1Field);
		
		pwd2Field = new JPasswordField();
		pwd2Field.setHorizontalAlignment(SwingConstants.CENTER);
		springLayout.putConstraint(SpringLayout.WEST, pwd2Field, 0, SpringLayout.WEST, jobLb);
		springLayout.putConstraint(SpringLayout.SOUTH, pwd2Field, -15, SpringLayout.NORTH, firstNameField);
		springLayout.putConstraint(SpringLayout.EAST, pwd2Field, 0, SpringLayout.EAST, loginField);
		add(pwd2Field);
	}

    private void subscribe(){
    	String login = loginField.getText(); // Va servir pour la création du panier si le compte est créé
    	boolean error = false;
    	emptyLogin.setVisible(loginField.getText().isEmpty());
    	if(emptyLogin.isVisible())
    		error = true;
    	emptyPwd.setVisible(pwd1Field.getPassword().length==0);
    	if(emptyPwd.isVisible())
    		error = true;
    	wrongPwd.setVisible(!Arrays.equals(pwd1Field.getPassword(),pwd2Field.getPassword()));
    	if(wrongPwd.isVisible())
    		error = true;
    	emptyFirstName.setVisible(firstNameField.getText().isEmpty());
    	if(emptyFirstName.isVisible())
    		error = true;
    	emptyLastName.setVisible(lastNameField.getText().isEmpty());
    	if(emptyLastName.isVisible())
    		error = true;
    	
    	if(!error){
    		String subscribeError = userFacade.handleSubscribe(	loginField.getText(),
    															Utilitary.hash(pwd1Field.getPassword()),
    															firstNameField.getText(),
    															lastNameField.getText(),
    															phoneField.getText(),
    															mailField.getText(),
    															"",adressField.getText(),"","",
    															speakerChk.isSelected(),
    															jobField.getText());
    		if(subscribeError != null){
    			alert(subscribeError);
    		} else {
    			userFacade = new UserFacade();
    			int userId = userFacade.getIdFromLogin(login);
    			userFacade.createShoppingCart(userId); // on crée un panier pour le compte
    			if (confirm("Registration completed ! Would you like to automatically log in ?")) {
    			
	    			if(userFacade.handleLogin(loginField.getText(),Utilitary.hash(pwd1Field.getPassword())) != null) {
	    				alert("Unknown error - Please log in manually");
	            		Frame.getFrame().setView(new LoginView(),false);
	            		Frame.getFrame().revalidate();
	    			}
	    			else {
		        		Frame.getFrame().setView(new NotificationCenterView(),true);
		        		Frame.getFrame().revalidate();
	    			}    			
	    		}
    		}    		
    	}
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Subscribe")){
			subscribe();
		}
		else if(e.getActionCommand().equals("Speaker")){
			jobLb.setVisible(speakerChk.isSelected());
			jobField.setVisible(speakerChk.isSelected());
		}
		else if(e.getActionCommand().equals("Back")){
			Frame.getFrame().setView(new LoginView(),false);
			Frame.getFrame().revalidate();
		}
	}
}
