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

import javax.swing.JTextField;
import javax.swing.SwingConstants;

import persistance.data.generic.Accessory;
import persistance.data.generic.AccessoryList;

@SuppressWarnings("serial")
public class AccessoriesManagementView extends View implements ActionListener{

	private EventFacade eventFacade;
	private JTextField accessoryField;
	
	private JPanel list;
	private AccessoryList accessories;
	
	public AccessoriesManagementView() {
		super("Accessory");
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		JLabel lblManageAccessories = new JLabel("Manage Accessories");
		add(lblManageAccessories);
		
		JScrollPane listscrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.WEST, listscrollPane, 33, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, listscrollPane, -83, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, listscrollPane, -34, SpringLayout.EAST, this);
		add(listscrollPane);
		
		JButton addBtn = new JButton("Add an accessory");
		springLayout.putConstraint(SpringLayout.NORTH, listscrollPane, 6, SpringLayout.SOUTH, addBtn);
		springLayout.putConstraint(SpringLayout.WEST, lblManageAccessories, 0, SpringLayout.WEST, addBtn);
		springLayout.putConstraint(SpringLayout.SOUTH, lblManageAccessories, -17, SpringLayout.NORTH, addBtn);
		springLayout.putConstraint(SpringLayout.SOUTH, addBtn, -283, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.WEST, addBtn, 120, SpringLayout.WEST, this);
		add(addBtn);
		addBtn.addActionListener(this);
		addBtn.setActionCommand("Add");
		
		list = new JPanel();
		listscrollPane.setViewportView(list);
		list.setLayout(new VerticalLayout());

		eventFacade = new EventFacade();
		
	}
	public void init(){
		setAccessories();
	}
	
	private void setAccessories (){
		list.removeAll();
		accessories = eventFacade.getAllAccessories();
		for(int i = 0 ; i < accessories.size() ; i++)
			list.add(makeCell(accessories.get(i),i));
		list.revalidate();
	}
	
	private JPanel makeCell(Accessory access,int numAccess){
		JPanel cell = new JPanel();
		cell.setLayout(new GridLayout(0, 3, 30, 0));
		
		accessoryField = new JTextField(access.getDescription());
		accessoryField.setHorizontalAlignment(SwingConstants.CENTER);
		cell.add(accessoryField);
		accessoryField.setColumns(15);
		
		JButton updateBtn = new JButton("Update");
		cell.add(updateBtn);
		updateBtn.addActionListener(this);
		updateBtn.setActionCommand("Update");
		updateBtn.setName(""+numAccess);
		
		JButton removeBtn = new JButton("Remove");
		cell.add(removeBtn);
		removeBtn.addActionListener(this);
		removeBtn.setActionCommand("Remove");
		removeBtn.setName(""+numAccess);
		
		return cell;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = ((JButton)e.getSource());
		if(b.getActionCommand().equals("Add")){
			String description = prompt("Enter the new accessory : ");
			eventFacade.addAccessory(description);
			setAccessories();
		}
		else if(b.getActionCommand().equals("Update")){
			accessories.get(Integer.parseInt(b.getName())).setDescription(accessoryField.getText());
			accessories.get(Integer.parseInt(b.getName())).update();
		}
		else if(b.getActionCommand().equals("Remove")){
			if(confirm("Are you sure you want to delete the accessory "+accessories.get(Integer.parseInt(b.getName())).getDescription()+" ?")){
				accessories.get(Integer.parseInt(b.getName())).delete();
				accessories.remove(Integer.parseInt(b.getName()));
				setAccessories();
			}
		}
	}
}
