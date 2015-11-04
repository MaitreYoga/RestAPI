package ui.view;

import ui.common.View;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import bl.facade.EventFacade;
import dal.product.generic.ActivityList;
import dal.product.generic.RoomList;
import dal.product.generic.SpeakerList;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

@SuppressWarnings("serial")
public class PlanEventView extends View implements ActionListener {
	
	private EventFacade eventFacade;
	
	private JTextField nameField;
	private JTextField priceField;
	private JTextField startJJField;
	private JTextField startHourField;
	private JTextField startMinuteField;
	private JTextField endHourField;
	private JTextField endMinuteField;

	private JComboBox<String> activities;
	private JComboBox<String> rooms;
	private JComboBox<String> speakers;
	
	private ActivityList activityList;
	private RoomList roomList;
	private SpeakerList speakerList;
	private JTextField startMMField;
	private JTextField startAAAAField;
	private JTextField endJJField;
	private JTextField endMMField;
	private JTextField endAAAAField;
	
	public PlanEventView() {
		super("New Event");
		setLayout(null);
		
		eventFacade = new EventFacade();
		
		JLabel nameLb = new JLabel("Name");
		nameLb.setBounds(93, 70, 46, 14);
		add(nameLb);
		
		nameField = new JTextField();
		nameField.setHorizontalAlignment(SwingConstants.CENTER);
		nameField.setBounds(174, 67, 151, 20);
		add(nameField);
		nameField.setColumns(10);
		
		JLabel titleLb = new JLabel("Plan an activity");
		titleLb.setHorizontalAlignment(SwingConstants.CENTER);
		titleLb.setBounds(303, 11, 125, 14);
		add(titleLb);
		
		JLabel priceLb = new JLabel("Price");
		priceLb.setBounds(93, 123, 46, 14);
		add(priceLb);
		
		priceField = new JTextField();
		priceField.setHorizontalAlignment(SwingConstants.CENTER);
		priceField.setBounds(174, 120, 151, 20);
		add(priceField);
		priceField.setColumns(10);
		
		activities = new JComboBox<String>();
		activities.setBounds(174, 167, 151, 27);
		add(activities);
		
		rooms = new JComboBox<String>();
		rooms.setBounds(174, 220, 151, 27);
		add(rooms);
		
		speakers = new JComboBox<String>();
		speakers.setBounds(174, 267, 151, 27);
		add(speakers);
		
		JLabel startDateLb = new JLabel("Start Date");
		startDateLb.setBounds(423, 70, 62, 14);
		add(startDateLb);
		
		JLabel endDateLb = new JLabel("End Date");
		endDateLb.setBounds(423, 173, 46, 14);
		add(endDateLb);
		
		startJJField = new JTextField();
		startJJField.setHorizontalAlignment(SwingConstants.CENTER);
		startJJField.setText("jj");
		startJJField.setBounds(505, 67, 35, 20);
		add(startJJField);
		startJJField.setColumns(10);
		
		startHourField = new JTextField();
		startHourField.setHorizontalAlignment(SwingConstants.CENTER);
		startHourField.setBounds(505, 120, 46, 20);
		add(startHourField);
		startHourField.setColumns(10);
		
		startMinuteField = new JTextField();
		startMinuteField.setHorizontalAlignment(SwingConstants.CENTER);
		startMinuteField.setBounds(577, 120, 46, 20);
		add(startMinuteField);
		startMinuteField.setColumns(10);
		
		JLabel semicolonLb1 = new JLabel(":");
		semicolonLb1.setBounds(561, 123, 12, 14);
		add(semicolonLb1);
		
		endHourField = new JTextField();
		endHourField.setHorizontalAlignment(SwingConstants.CENTER);
		endHourField.setBounds(505, 223, 46, 20);
		add(endHourField);
		endHourField.setColumns(10);
		
		endMinuteField = new JTextField();
		endMinuteField.setHorizontalAlignment(SwingConstants.CENTER);
		endMinuteField.setBounds(577, 223, 46, 20);
		add(endMinuteField);
		endMinuteField.setColumns(10);
		
		JLabel endTimeLb = new JLabel("End Time");
		endTimeLb.setBounds(423, 226, 46, 14);
		add(endTimeLb);
		
		JLabel roomLb = new JLabel("Room");
		roomLb.setBounds(93, 226, 46, 14);
		add(roomLb);
		
		JLabel activityLb = new JLabel("Activity");
		activityLb.setBounds(93, 173, 46, 14);
		add(activityLb);
		
		JLabel speakerLb = new JLabel("Speaker");
		speakerLb.setBounds(93, 273, 46, 14);
		add(speakerLb);
		
		JLabel startTimeLb = new JLabel("Start Time");
		startTimeLb.setBounds(423, 117, 62, 14);
		add(startTimeLb);
		
		JLabel semicolonLb2 = new JLabel(":");
		semicolonLb2.setBounds(561, 226, 12, 14);
		add(semicolonLb2);
		
		JButton createBtn = new JButton("Create");
		createBtn.setBounds(423, 267, 200, 27);
		createBtn.addActionListener(this);
		add(createBtn);
		
		startMMField = new JTextField();
		startMMField.setText("mm");
		startMMField.setBounds(545, 67, 35, 20);
		add(startMMField);
		startMMField.setColumns(10);
		
		startAAAAField = new JTextField();
		startAAAAField.setText("aaaa");
		startAAAAField.setBounds(585, 67, 38, 20);
		add(startAAAAField);
		startAAAAField.setColumns(10);
		
		endJJField = new JTextField();
		endJJField.setHorizontalAlignment(SwingConstants.CENTER);
		endJJField.setText("jj");
		endJJField.setBounds(505, 170, 35, 20);
		add(endJJField);
		endJJField.setColumns(10);
		
		endMMField = new JTextField();
		endMMField.setHorizontalAlignment(SwingConstants.CENTER);
		endMMField.setText("mm");
		endMMField.setBounds(545, 170, 35, 20);
		add(endMMField);
		endMMField.setColumns(10);
		
		endAAAAField = new JTextField();
		endAAAAField.setHorizontalAlignment(SwingConstants.CENTER);
		endAAAAField.setText("aaaa");
		endAAAAField.setBounds(585, 170, 38, 20);
		add(endAAAAField);
		endAAAAField.setColumns(10);
		// TODO Auto-generated constructor stub
	}
	
	public void init(){
		//Init the ActivityList
		activityList = eventFacade.getAllActivities();
		//Init the RoomList
		roomList = eventFacade.getallRooms();
		//Init the SpeakerList
		speakerList = eventFacade.getAllSpeakers();

		//Add the activityList to the activities combobox
		activities.addItem("No Activity");
		for(int i = 0 ; i < activityList.size() ; i++){
			activities.addItem(activityList.get(i).getName());
		}
		//Add the roomList to the rooms combobox
		rooms.addItem("No Room");
		for(int i = 0 ; i < roomList.size() ; i++){
			rooms.addItem(roomList.get(i).getName());
		}
		//Add the speakerList to the speakers combobox
		speakers.addItem("No Speaker");
		for(int i = 0 ; i < speakerList.size() ; i++){
			speakers.addItem(speakerList.get(i).getName());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Retrieving information
		String name = nameField.getText();
		String price = priceField.getText();
		if(priceField.getText().equals(""))
			price = "0";

		String startDate = startAAAAField.getText()+"-"+startMMField.getText()+"-"+startJJField.getText()+" "+startHourField.getText()+":"+startMinuteField.getText()+":00";
		String endDate = endAAAAField.getText()+"-"+endMMField.getText()+"-"+endJJField.getText()+" "+endHourField.getText()+":"+endMinuteField.getText()+":00";


		String activityID = null;
		if(activities.getSelectedIndex() > 0)
			activityID = activityList.get(activities.getSelectedIndex()).getID();
		String roomID = null;
		if(rooms.getSelectedIndex() > 0)
			roomID = ""+roomList.get(rooms.getSelectedIndex()).getIdRoom();
		String speakerID = null;
		if(speakers.getSelectedIndex() > 0)
			speakerID = speakerList.get(speakers.getSelectedIndex()).getName();
		
		//Create the new event
		String returnMessage = eventFacade.createEvent(name,price,startDate,endDate,activityID,roomID,speakerID);
		
		//Display the message
		alert(returnMessage);
	}
}
