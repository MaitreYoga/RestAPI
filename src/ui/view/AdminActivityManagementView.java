package ui.view;

import bl.facade.EventFacade;
import ui.common.VerticalLayout;
import ui.common.View;

import javax.swing.JLabel;
import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import persistance.data.generic.Activity;
import persistance.data.generic.ActivityCategory;
import persistance.data.generic.ActivityCategoryList;
import persistance.data.generic.ActivityList;
import persistance.factory.Factory;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class AdminActivityManagementView extends View implements ActionListener{

	private EventFacade eventFacade;
	private JTextField activityField;
	
	private JPanel list;
	private ActivityList activities;
	private ActivityCategoryList catActs;
	
	private List<JComboBox> catActList;
	
	public AdminActivityManagementView() {
		super("Activity");
		
		catActList = new ArrayList<JComboBox>();
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblManageAct = new JLabel("Manage Activities");
		add(lblManageAct);
		
		JScrollPane listscrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.WEST, lblManageAct, 0, SpringLayout.WEST, listscrollPane);
		springLayout.putConstraint(SpringLayout.NORTH, listscrollPane, 97, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, listscrollPane, 32, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, listscrollPane, -33, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, listscrollPane, -35, SpringLayout.EAST, this);
		add(listscrollPane);
		
		JButton addBtn = new JButton("Add an activity");
		springLayout.putConstraint(SpringLayout.SOUTH, lblManageAct, -18, SpringLayout.NORTH, addBtn);
		springLayout.putConstraint(SpringLayout.WEST, addBtn, 0, SpringLayout.WEST, listscrollPane);
		springLayout.putConstraint(SpringLayout.SOUTH, addBtn, -6, SpringLayout.NORTH, listscrollPane);
		add(addBtn);
		addBtn.addActionListener(this);
		addBtn.setActionCommand("Add");
		
		list = new JPanel();
		listscrollPane.setViewportView(list);
		list.setLayout(new VerticalLayout());
		
		eventFacade = new EventFacade();
		
	}
	public void init(){
		setActivities();
	}
	
	private void setActivities (){
		list.removeAll();
		activities = eventFacade.getAllActivities();
		catActs = eventFacade.getAllActivityCategories();
		for(int i = 0 ; i < activities.size() ; i++)
			list.add(makeCell(activities.get(i),activities.get(i).getCatAct(),i));
		list.revalidate();
	}
	
	private JPanel makeCell(Activity act,ActivityCategory currentCatAct,int numAct){
		JPanel cell = new JPanel();
		cell.setLayout(new GridLayout(0, 4, 30, 0));
		
		activityField = new JTextField(act.getName());
		activityField.setHorizontalAlignment(SwingConstants.CENTER);
		cell.add(activityField);
		activityField.setColumns(15);
		
		JComboBox<String> activitycatBox = new JComboBox<String>();
		catActList.add(activitycatBox);
		if(currentCatAct==null)
		{
			activitycatBox.addItem("No Activity Category");
			for(int i = 0 ; i < catActs.size() ; i++){
				activitycatBox.addItem(catActs.get(i).getName());
			}
		}
		else
		{
			activitycatBox.addItem(currentCatAct.getName());
			for(int i = 0 ; i < catActs.size() ; i++){
				if(!catActs.get(i).getName().equals(currentCatAct.getName()))
					activitycatBox.addItem(catActs.get(i).getName());
			}
			activitycatBox.addItem("No Activity Category");
		}
		cell.add(activitycatBox);

		
		JButton updateBtn = new JButton("Update");
		cell.add(updateBtn);
		updateBtn.addActionListener(this);
		updateBtn.setActionCommand("Update");
		updateBtn.setName(""+numAct);
		
		JButton removeBtn = new JButton("Remove");
		cell.add(removeBtn);
		removeBtn.addActionListener(this);
		removeBtn.setActionCommand("Remove");
		removeBtn.setName(""+numAct);
		
		return cell;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = ((JButton)e.getSource());
		if(b.getActionCommand().equals("Add")){
			String name = prompt("Enter the new activity : ");
			eventFacade.addActivity(name);
			setActivities();
		}
		else if(b.getActionCommand().equals("Update")){
			activities.get(Integer.parseInt(b.getName())).setName(activityField.getText());
			activities.get(Integer.parseInt(b.getName())).updateName();
			
			int numCatAct = Integer.parseInt(b.getName());
			JComboBox<String> activityCategories = catActList.get(numCatAct);
			
			if(((String)(activityCategories.getSelectedItem())).equals("No Activity Category")){
				activities.get(numCatAct).setCatAct(Factory.getInstance().makeActivityCategory());
			}
			else {
				ActivityCategory catAct = catActs.get((String)(activityCategories.getSelectedItem()));
				activities.get(numCatAct).setCatAct(catAct);
			}
			activities.get(Integer.parseInt(b.getName())).updateCatAct();
		}
		else if(b.getActionCommand().equals("Remove")){
			if(confirm("Are you sure you want to delete the activity "+activities.get(Integer.parseInt(b.getName())).getName()+" ?")){
				activities.get(Integer.parseInt(b.getName())).delete();
				activities.remove(Integer.parseInt(b.getName()));
				setActivities();
			}
		}
	}
}
