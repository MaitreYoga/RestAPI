package ui.view;

import ui.common.Frame;
import ui.common.View;

import java.awt.FlowLayout;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import persistance.Session;
import bl.facade.UserFacade;
import javax.swing.SwingConstants;

public class UserProfileView extends View{
	private JTextField fName;
	private JTextField mail;
	private JTextField phone;
	private JTextField lName;
	private JTextField address;
	
	private UserFacade facade;
	public UserProfileView() {
		super("User");
		setLayout(null);
		JLabel lblFirstName = new JLabel("First name :");
		lblFirstName.setBounds(110, 122, 81, 16);
		add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last name :");
		lblLastName.setBounds(367, 122, 75, 16);
		add(lblLastName);
		
		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setBounds(110, 260, 49, 16);
		add(lblPhone);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setBounds(367, 192, 56, 16);
		add(lblAddress);
		
		JLabel lblNewLabel = new JLabel("Mail : ");
		lblNewLabel.setBounds(110, 192, 36, 16);
		add(lblNewLabel);
		
		JLabel lblUpdateUserProfile = new JLabel("Update User profile");
		lblUpdateUserProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblUpdateUserProfile.setFont(new Font("Source Sans Pro Light", Font.PLAIN, 20));
		lblUpdateUserProfile.setBounds(136, 28, 314, 29);
		add(lblUpdateUserProfile);
		
		fName = new JTextField();
		fName.setText(Session.user().getFirstName());
		fName.setBounds(196, 119, 116, 22);
		add(fName);
		fName.setColumns(10);
		
		mail = new JTextField();
		mail.setText(Session.user().getMail());
		mail.setBounds(196, 189, 116, 22);
		add(mail);
		mail.setColumns(10);
		
		phone = new JTextField();
		phone.setText(Session.user().getPhone());
		phone.setBounds(196, 257, 116, 22);
		add(phone);
		phone.setColumns(10);
		
		lName = new JTextField();
		lName.setText(Session.user().getLastName());
		lName.setBounds(454, 119, 116, 22);
		add(lName);
		lName.setColumns(10);
		
		address = new JTextField();
		address.setText(Session.user().getAddress());
		address.setBounds(454, 189, 116, 22);
		add(address);
		address.setColumns(10);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Session.user().setName(address.getText());
				Session.user().setFirstName(fName.getText());
				Session.user().setLastName(lName.getText());
				Session.user().setMail(mail.getText());
				Session.user().setPhone(phone.getText());
				int result = Session.user().saveCurrentUser();
				if (result == -1){
					JOptionPane.showMessageDialog(null, "An error occured","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "Your profile was updated successful","Information",JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		btnUpdate.setBounds(306, 322, 97, 25);
		add(btnUpdate);
	}

}
