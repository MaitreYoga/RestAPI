package ui.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import bl.facade.UserFacade;
import ui.common.Frame;
import ui.common.View;
import utilitaries.Encryption;

@SuppressWarnings("serial")
public class LoginView extends View implements ActionListener, KeyListener{
	
    private JLabel loginLabel;
    private JLabel pwdLabel;
	
    private JTextField loginField;
    private JPasswordField pwdField;

    private JButton loginButton;
    private JButton subscribeButton;
	
    public UserFacade userFacade;
    private JLabel errorMessage;
    
    public LoginView(){
    	super("ZenLounge - Login");
    	
    	userFacade = new UserFacade();

    	loginLabel = new JLabel("Login");
    	pwdLabel = new JLabel("Password");

    	loginField = new JTextField(10);
    	pwdField = new JPasswordField(10);
    	
    	loginButton = new JButton(Action.NAME);
    	loginButton.setText("Connect");
    	loginButton.addActionListener(this);
    	
    	pwdField.addKeyListener(this);
    	
    	subscribeButton = new JButton(Action.NAME);
    	subscribeButton.setText("Subscribe");
    	subscribeButton.addActionListener(this);
    	SpringLayout springLayout = new SpringLayout();
    	springLayout.putConstraint(SpringLayout.NORTH, loginButton, 30, SpringLayout.SOUTH, pwdField);
    	springLayout.putConstraint(SpringLayout.NORTH, subscribeButton, 0, SpringLayout.NORTH, loginButton);
    	springLayout.putConstraint(SpringLayout.WEST, subscribeButton, 0, SpringLayout.WEST, pwdLabel);
    	springLayout.putConstraint(SpringLayout.NORTH, pwdField, 202, SpringLayout.NORTH, this);
    	springLayout.putConstraint(SpringLayout.NORTH, pwdLabel, 3, SpringLayout.NORTH, pwdField);
    	springLayout.putConstraint(SpringLayout.EAST, pwdLabel, 0, SpringLayout.EAST, loginLabel);
    	springLayout.putConstraint(SpringLayout.WEST, loginField, 338, SpringLayout.WEST, this);
    	springLayout.putConstraint(SpringLayout.SOUTH, loginField, -17, SpringLayout.NORTH, pwdField);
    	springLayout.putConstraint(SpringLayout.NORTH, loginLabel, 3, SpringLayout.NORTH, loginField);
    	springLayout.putConstraint(SpringLayout.EAST, loginLabel, -41, SpringLayout.WEST, loginField);
    	springLayout.putConstraint(SpringLayout.EAST, loginButton, 0, SpringLayout.EAST, loginField);
    	springLayout.putConstraint(SpringLayout.EAST, pwdField, 0, SpringLayout.EAST, loginField);
    	setLayout(springLayout);

    	this.add(loginLabel);
    	this.add(loginField);
    	this.add(pwdLabel);
    	this.add(pwdField);
    	this.add(loginButton);
    	this.add(subscribeButton);

    	//A enlever//
    	loginField.setText("joris");
    	pwdField.setText("joris");
    	
    	errorMessage = new JLabel();
    	springLayout.putConstraint(SpringLayout.SOUTH, errorMessage, -29, SpringLayout.NORTH, loginField);
    	springLayout.putConstraint(SpringLayout.EAST, errorMessage, -196, SpringLayout.EAST, this);
    	errorMessage.setForeground(Color.RED);
    	errorMessage.setVisible(false);
    	add(errorMessage);
    }

    private void login() {
    	String pwd = Encryption.hash(pwdField.getPassword());
    	pwdField.setText("");
    	String error = userFacade.handleLogin(loginField.getText(),pwd);
    	if(error == null) {
    		Frame.getFrame().setView(new NotificationCenterView(),true);
    		Frame.getFrame().revalidate();
    	}
    	else {
    		this.errorMessage.setText(error);
    		this.errorMessage.setVisible(true);
    	}
    }

    private void subscribe() {
    	//Frame.getFrame().setView(new SubscribeView());
    	Frame.getFrame().setView(new SubscribeView(),false);
		Frame.getFrame().revalidate();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals(loginButton.getActionCommand()))
			login();
		else if(e.getActionCommand().equals(subscribeButton.getActionCommand()))
			subscribe();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyChar() == KeyEvent.VK_ENTER)
			login();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}
}