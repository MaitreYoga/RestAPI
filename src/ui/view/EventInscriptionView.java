package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;

import ui.common.Frame;
import ui.common.View;
import bl.facade.EventFacade;

import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import persistance.data.generic.Event;

public class EventInscriptionView extends View implements ActionListener
{
	private JButton btnSubmit;
    private JButton btnOk;
	
	//Labels
	private JLabel lblChoice;
	private JLabel lblStartDate;

	
	//Combo boxes
	private JComboBox comboBoxChoice;
	private JComboBox comboBoxStartDate;

	public String inscriptionButton;

    public EventFacade eventFacade;
	
    public EventInscriptionView() 
    {
		super("Inscription");
		
		eventFacade = new EventFacade();
		setLayout(null);
		
		lblChoice = new JLabel("Please choose an event :");
		lblChoice.setBounds(23, 48, 166, 14);
		add(lblChoice);
		
		comboBoxChoice = new JComboBox();
		comboBoxChoice.setBounds(199, 45, 88, 20);
		add(comboBoxChoice);
		
		btnSubmit = new JButton("Details");
		btnSubmit.setBounds(182, 176, 81, 23);
		add(btnSubmit);
		
		lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(35, 109, 72, 14);
		add(lblStartDate);
		
		comboBoxStartDate = new JComboBox();
		comboBoxStartDate.setBounds(117, 106, 109, 20);
		add(comboBoxStartDate);
		
		btnOk = new JButton("OK");
		btnOk.setBounds(330, 44, 89, 23);
		add(btnOk);
		
		//add listeners
		btnSubmit.addActionListener(this);
		btnOk.addActionListener(this);
    }
    
    public void init()
    {
    	//fill combo box with events
		List<Object> eventList = eventFacade.getAllEvents();
		int i = 0;
		while(i<eventList.size())
		{
			comboBoxChoice.addItem(eventList.get(i));
			i++;
		}
		//fill combo boxes with start & end dates
		List<Object> periodList = eventFacade.getPeriods(comboBoxChoice.getSelectedItem().toString());
		int j = 0;
		while(j<periodList.size())
		{
			comboBoxStartDate.addItem(periodList.get(j));
			j++;
		}
	}

    public void eventInscription() 
    {
    	String chosenEvent = comboBoxChoice.getSelectedItem().toString();
    	String userID = eventFacade.handleEventInscription(chosenEvent);
    	if(userID != null)
    	{
        	Event myEvent = eventFacade.getEvent(chosenEvent);
        	
    		Frame.getFrame().setView(new EventDetailsView(myEvent),true);
        	Frame.getFrame().revalidate();
    	}
    	else 
    	{
    		JOptionPane.showMessageDialog(null, "vous n'etes pas un utilisateur, vérifiez votre ID");
    	} 	
    }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton source = (JButton)e.getSource();
		if(source.equals(btnSubmit))
		{
			eventInscription();
		}
		if(source.equals(btnOk))
		{
			getChosenEventPeriod();
		}		
	}

	private void getChosenEventPeriod() 
	{
		comboBoxStartDate.removeAllItems();
		List<Object> periodList = eventFacade.getPeriods(comboBoxChoice.getSelectedItem().toString());
		int j = 0;
		while(j<periodList.size())
		{
			comboBoxStartDate.addItem(periodList.get(j));
			j++;
		}
	}
}
