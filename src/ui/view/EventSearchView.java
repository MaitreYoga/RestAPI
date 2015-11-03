package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ui.common.View;
import bl.facade.EventFacade;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class EventSearchView extends View implements ActionListener
{
	EventFacade eventFacade;
	
	//Labels
	private JLabel lblSearch;
	private JLabel lblSearchCriteria;
	//Buttons
	private JButton btnName;
	private JButton btnActivity;
	private JButton btnSpeaker;
	private JButton btnPlace;
	private JButton btnPeriod;
	private JButton btnSearch;
	//boxes
	private JComboBox comboBoxSearch;
	//text
	private JTextArea txtEvents;
	
    public EventSearchView()
    {
		super("Search");
		setLayout(null);
		eventFacade = new EventFacade();
		
		lblSearch= new JLabel("Search event by :");
		lblSearch.setBounds(35, 11, 123, 23);
		add(lblSearch);
		
		btnName = new JButton("name");
		btnName.setBounds(35, 51, 89, 23);
		add(btnName);
		
		btnActivity = new JButton("activity");
		btnActivity.setBounds(35, 97, 89, 23);
		add(btnActivity);
		
		btnSpeaker = new JButton("speaker");
		btnSpeaker.setBounds(35, 146, 89, 23);
		add(btnSpeaker);
		
		btnPlace = new JButton("place");
		btnPlace.setBounds(35, 197, 89, 23);
		add(btnPlace);
		
		btnPeriod = new JButton("period");
		btnPeriod.setBounds(35, 247, 89, 23);
		add(btnPeriod);
		
		lblSearchCriteria = new JLabel("");
		lblSearchCriteria.setBounds(168, 11, 89, 23);
		add(lblSearchCriteria);
		
		comboBoxSearch = new JComboBox();
		comboBoxSearch.setBounds(284, 11, 144, 23);
		add(comboBoxSearch);
		
		txtEvents = new JTextArea();
		txtEvents.setBounds(187, 97, 241, 173);
		add(txtEvents);
		
		btnSearch = new JButton("Search");
		btnSearch.setEnabled(false);
		btnSearch.setBounds(246, 63, 89, 23);
		add(btnSearch);
		
		//add listeners
		btnName.addActionListener(this);
		btnActivity.addActionListener(this);
		btnSpeaker.addActionListener(this);
		btnPlace.addActionListener(this);
		btnPeriod.addActionListener(this);	
		btnSearch.addActionListener(this);
	}

	public String nameField;

    public String activityField;

    public String speakerField;

    public String dateField;

    public String placeField;


    public void eventSearch(String searchCriteria) 
    {
    	if(lblSearchCriteria.getText().equals("name"))
    	{
    		txtEvents.setText(eventFacade.getEvents(searchCriteria,"name"));
    	}
    	if(lblSearchCriteria.getText().equals("speaker"))
    	{
    		txtEvents.setText(eventFacade.getEvents(searchCriteria,"speaker"));
    	}
    	if(lblSearchCriteria.getText().equals("activity"))
    	{
    		txtEvents.setText(eventFacade.getEvents(searchCriteria,"activity"));
    	}
    	if(lblSearchCriteria.getText().equals("place"))
    	{
    		txtEvents.setText(eventFacade.getEvents(searchCriteria,"place")); 	
    	}
    	if(lblSearchCriteria.getText().equals("period"))
    	{
    		txtEvents.setText(eventFacade.getEvents(searchCriteria,"period")); 	
    	}
    }


	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton source = (JButton)e.getSource();
		if(source.equals(btnSearch))
		{
			eventSearch(comboBoxSearch.getSelectedItem().toString());
		}
		else if (source.equals(btnSpeaker))
		{
			lblSearchCriteria.setText(source.getText());
			fillComboBox(source.getText());	
		}
		else
		{
			lblSearchCriteria.setText(source.getText());
			fillComboBox(source.getText()+"Event");	
		}	
	}

	private void fillComboBox(String searchCriteria) 
	{
		//reload contents
		comboBoxSearch.removeAllItems();
		List<Object> eventList = eventFacade.getAllEvents(searchCriteria);
		int i = 0;
		while(i<eventList.size())
		{
			comboBoxSearch.addItem(eventList.get(i));
			i++;
		}
		if(!comboBoxSearch.getSelectedItem().toString().isEmpty())
		{
			btnSearch.setEnabled(true);	    
		}
		else
		{
			btnSearch.setEnabled(false);
		}
	}
}
