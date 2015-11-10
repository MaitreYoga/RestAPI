package ui.view;

import bl.facade.UserFacade;
import dal.product.generic.Speaker;
import ui.common.Frame;
import ui.common.View;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class SpeakerProfileView extends View implements ActionListener{
	
	public UserFacade userFacade;
	private JButton btnUpdate;
	private JButton btnCancel;
	private JButton btnConfirm;
	private JTextField jobTextField;
	private JTextField sdTextField;
	private JTextField ldTextField;
	private Speaker speaker;
	
    public SpeakerProfileView(boolean edit) {
		super("Speaker");
		setLayout(null);
		
		JLabel lblJob = new JLabel("Job :");
		lblJob.setBounds(236, 77, 46, 14);
		add(lblJob);
		
		JLabel lblShortDescription = new JLabel("Short Description :");
		lblShortDescription.setBounds(169, 120, 120, 14);
		add(lblShortDescription);
		
		JLabel lblLongDescription = new JLabel("Long Description :");
		lblLongDescription.setBounds(169, 191, 110, 14);
		add(lblLongDescription);
		
		jobTextField = new JTextField();
		
		jobTextField.setBounds(282, 74, 150, 20);
		jobTextField.setBackground(new Color(255, 255, 255));
		add(jobTextField);
		jobTextField.setColumns(10);
		
		sdTextField = new JTextField();
		sdTextField.setColumns(10);
		sdTextField.setBounds(282, 115, 217, 53);
		sdTextField.setBackground(new Color(255, 255, 255));
		add(sdTextField);
		
		ldTextField = new JTextField();
		ldTextField.setColumns(10);
		ldTextField.setBounds(282, 186, 217, 88);
		ldTextField.setBackground(new Color(255, 255, 255));
		add(ldTextField);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(303, 299, 97, 23);
		btnUpdate.addActionListener(this);
		add(btnUpdate);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(290, 299, 97, 23);
		btnCancel.addActionListener(this);
		add(btnCancel);
		
		btnConfirm = new JButton("Confirm");
		btnConfirm.setBounds(400, 299, 97, 23);
		btnConfirm.addActionListener(this);
		add(btnConfirm);
		
		if (edit){
			btnUpdate.setVisible(false);
		} else{
			btnConfirm.setVisible(false);
			btnCancel.setVisible(false);
			
			jobTextField.setEditable(false);
			sdTextField.setEditable(false);
			ldTextField.setEditable(false);
		}
    }
    
    public void init(){
		setValues();
	}
    
    public void setValues(){
    	userFacade = UserFacade.instance();
    	speaker = userFacade.getSpeaker();
    	
    	jobTextField.setText(speaker.getJob());
    	sdTextField.setText(speaker.getShortDescription());
    	ldTextField.setText(speaker.getLongDescription());
    	
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnUpdate){
			View viewEdit = new SpeakerProfileView(true);
			Frame.getFrame().setView(viewEdit,true);
			Frame.getFrame().revalidate();
		} else if (e.getSource() == btnCancel){
			View viewNoEdit = new SpeakerProfileView(false);
			Frame.getFrame().setView(viewNoEdit,true);
			Frame.getFrame().revalidate();
		} else if (e.getSource() == btnConfirm){
			boolean confirmEdit = confirm("Are you sure you want to edit ?");
			if (confirmEdit){
				String newJob = jobTextField.getText();
				String newSd = sdTextField.getText();
				String newLd = ldTextField.getText();
				userFacade.editSpeaker(newJob, newSd, newLd);
				
				View viewNoEdit = new SpeakerProfileView(false);
				Frame.getFrame().setView(viewNoEdit,true);
				Frame.getFrame().revalidate();
				setValues();
			}
		
		}
		
	}
}
