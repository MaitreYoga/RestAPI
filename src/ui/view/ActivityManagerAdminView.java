package ui.view;

import ui.common.VerticalLayout;
import ui.common.View;

import javax.swing.JLabel;
import javax.swing.SpringLayout;

import bl.facade.EventFacade;

import javax.swing.JScrollPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;

import persistance.data.generic.Activity;
import persistance.data.generic.ActivityList;
import persistance.data.generic.Manager;
import persistance.data.generic.ManagerList;
import persistance.factory.Factory;

@SuppressWarnings("serial")
public class ActivityManagerAdminView extends View implements ActionListener {

	private EventFacade eventFacade;
	
	private JPanel list;
	
	private List<JComboBox> managersList;
	
	private ManagerList managers;
	private ActivityList activities;
	
	public ActivityManagerAdminView() {
		super("Activity managers");

		managersList = new ArrayList<JComboBox>();
		
		eventFacade = new EventFacade();
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblEditActivityManagers = new JLabel("Edit Activity Managers");
		springLayout.putConstraint(SpringLayout.NORTH, lblEditActivityManagers, 69, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, lblEditActivityManagers, 98, SpringLayout.WEST, this);
		add(lblEditActivityManagers);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 26, SpringLayout.SOUTH, lblEditActivityManagers);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 37, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 226, SpringLayout.SOUTH, lblEditActivityManagers);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 656, SpringLayout.WEST, this);
		add(scrollPane);
		
		list = new JPanel();
		scrollPane.setViewportView(list);
		springLayout.putConstraint(SpringLayout.NORTH, list, 10, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, list, -354, SpringLayout.EAST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, list, 60, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, list, -154, SpringLayout.EAST, this);

		list.setLayout(new VerticalLayout());

		//list.add(makeCell());
	}
	public void init(){
		setAssignments();
	}

	private void setAssignments() {
		managers = eventFacade.getAllManagers();
		activities = eventFacade.getAllActivities();
		for(int i = 0 ; i < activities.size() ; i++){
			list.add(makeCell(i,activities.get(i),managers,activities.get(i).getManager()));
		}
	}

	private JPanel makeCell(int numCell, Activity activity, ManagerList managers, Manager currentManager){
		JPanel cell = new JPanel();
		cell.setLayout(new GridLayout(0, 3, 30, 0));
		
		JTextField activityField = new JTextField(activity.getName());
		cell.add(activityField);
		activityField.setColumns(16);
		
		JComboBox<String> managerList = new JComboBox<String>();
		managersList.add(managerList);
		if(currentManager==null)
		{
			managerList.addItem("No Manager");
			for(int i = 0 ; i < managers.size() ; i++){
					managerList.addItem(managers.get(i).getName());
			}
		}
		else
		{
			managerList.addItem(currentManager.getName());
			for(int i = 0 ; i < managers.size() ; i++){
				if(!managers.get(i).getName().equals(currentManager.getName()))
					managerList.addItem(managers.get(i).getName());
			}
			managerList.addItem("No Manager");
		}
		cell.add(managerList);
		
		JButton updateBtn = new JButton("Update");
		updateBtn.addActionListener(this);
		updateBtn.setName(""+numCell);
		updateBtn.setActionCommand("Update");
		cell.add(updateBtn);
		
		return cell;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Update")){
			JButton sender = (JButton)(e.getSource());
			int numActivity = Integer.parseInt(sender.getName());
			JComboBox<String> managers = managersList.get(numActivity);
			
			if(((String)(managers.getSelectedItem())).equals("No Manager")){
				activities.get(numActivity).setManager(Factory.getInstance().makeManager());
			}
			else {
				Manager manager = this.managers.get((String)(managers.getSelectedItem()));
				activities.get(numActivity).setManager(manager);
			}
			activities.get(numActivity).update();
		}
	}
}
