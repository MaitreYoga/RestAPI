package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import ui.common.Frame;
import ui.common.View;
import bl.facade.EventFacade;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import persistance.data.generic.Event;

@SuppressWarnings("serial")
public class EventDetailsView extends View implements ActionListener
{
	EventFacade eventFacade;
	
	//labels
	private JLabel lblName ;
	private JLabel lblPlace;
	private JLabel lblPeriod;
	private JLabel lblActivity ;
	private JLabel lblSpeaker ;
	private JLabel lblPrice;
	
	//text fields
	private JTextField txtName ;
	private JTextField txtPlace;
	private JTextField txtPeriod;
	private JTextField txtActivity ;
	private JTextField txtSpeaker ;
	private JTextField txtPrice ;
	
	//buttons
	private JButton btnRegister;

	public EventDetailsView(Event myEvent) 
	{
		super(myEvent.getName());
		eventFacade = new EventFacade();
		
		setLayout(null);
		
		lblName = new JLabel("Name :");
		lblName.setBounds(41, 33, 73, 14);
		add(lblName);
		
		lblPlace = new JLabel("Place :");
		lblPlace.setBounds(41, 75, 73, 14);
		add(lblPlace);
		
		lblPeriod = new JLabel("Period :");
		lblPeriod.setBounds(41, 120, 73, 14);
		add(lblPeriod);
		
		lblActivity = new JLabel("Activity : ");
		lblActivity.setBounds(41, 164, 73, 14);
		add(lblActivity);
		
		lblSpeaker = new JLabel("Speaker : ");
		lblSpeaker.setBounds(41, 209, 73, 14);
		add(lblSpeaker);
		
		lblPrice = new JLabel("Price : ");
		lblPrice.setBounds(41, 248, 73, 14);
		add(lblPrice);
		
		txtName = new JTextField();
		txtName.setBounds(142, 30, 203, 20);
		add(txtName);
		txtName.setColumns(10);
		txtName.setText(myEvent.getName());
		
		txtPlace = new JTextField();
		txtPlace.setColumns(10);
		txtPlace.setBounds(142, 72, 203, 20);
		add(txtPlace);
		//fill event place text field 
		String place = getEventPlace(myEvent.getPlace());
		txtPlace.setText(place);
		
		txtActivity = new JTextField();
		txtActivity.setColumns(10);
		txtActivity.setBounds(142, 161, 203, 20);
		add(txtActivity);
		//fill event activity text field 
		String activity = getEventActivity(myEvent.getActivity());
		txtActivity.setText(activity);
		
		txtPeriod = new JTextField();
		txtPeriod.setColumns(10);
		txtPeriod.setBounds(142, 117, 203, 20);
		add(txtPeriod);
		//fill event period text field 
		String period = getEventPeriod(myEvent.getPeriod());
		txtPeriod.setText(period);
		
		txtSpeaker = new JTextField();
		txtSpeaker.setColumns(10);
		txtSpeaker.setBounds(142, 206, 203, 20);
		add(txtSpeaker);
		//fill event speaker text field 
		String speaker = getEventSpeaker(myEvent.getSpeaker());
		txtSpeaker.setText(speaker);
		
		txtPrice = new JTextField();
		txtPrice.setColumns(10);
		txtPrice.setBounds(142, 245, 203, 20);
		add(txtPrice);
		txtPrice.setText(Integer.toString(myEvent.getPrice()));
		
		btnRegister = new JButton("Register");
		btnRegister.setBounds(157, 284, 89, 35);
		add(btnRegister);
		
		//add listeners
		btnRegister.addActionListener(this);
	}

	private String getEventSpeaker(String speakerID) 
	{
		return eventFacade.getSpeaker(speakerID);
	}

	private String getEventPeriod(String periodID) 
	{
		return eventFacade.getPeriod(periodID);
	}

	private String getEventActivity(String activityID)
	{
		return eventFacade.getActivity(activityID);
	}

	private String getEventPlace(String placeID) 
	{	
		return eventFacade.getPlace(placeID);
	}

	public String nameLabel;

    public int priceLabel;

    public Date dateLabel;

    public int nbParticipantLabel;

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		eventRegistration();
	}

	private void eventRegistration() 
	{
		Frame.getFrame().setView(new PaymentView(txtName.getText(),txtPrice.getText()),false);
    	Frame.getFrame().revalidate();
	}
   
}
