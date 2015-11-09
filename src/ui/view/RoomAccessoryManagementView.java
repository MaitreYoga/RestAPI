package ui.view;

import ui.common.View;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import bl.facade.EventFacade;
import dal.product.generic.Accessory;
import dal.product.generic.AccessoryList;
import dal.product.generic.Room;

import javax.swing.JButton;

public class RoomAccessoryManagementView extends View{
	private EventFacade eventFacade;
	private List<Room> listRooms;
	JList accessoryList;

	public RoomAccessoryManagementView() {
		super("Room Accessory");
		setLayout(null);
		eventFacade = new EventFacade();
		
		final DefaultComboBoxModel accessoryModel = new DefaultComboBoxModel();
		final JComboBox accessoryList = new JComboBox(accessoryModel);
		accessoryList.setBounds(219, 106, 161, 22);
		add(accessoryList);
		
		DefaultListModel listModel = new DefaultListModel();
		final JList list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if (!arg0.getValueIsAdjusting()){
					accessoryModel.removeAllElements();
					int roomID = ((Room)list.getSelectedValue()).getIdRoom();
					ArrayList<Accessory> listAcc = new ArrayList<Accessory>();
					listAcc = eventFacade.getAccessoriesByRoom(roomID);
					for (int i = 0; i< listAcc.size(); i++){
						accessoryModel.addElement(listAcc.get(i));
					}
				}
				
			}
		});
		list.setBounds(12, 43, 170, 303);

		listRooms = eventFacade.getRooms();
		for (int i = 0; i < listRooms.size(); i++){
			listModel.addElement(listRooms.get(i));
		}
		add(list);
		
		JLabel lblRoom = new JLabel("Room number :");
		lblRoom.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblRoom.setBounds(52, 14, 107, 16);
		add(lblRoom);
		
		
		JLabel lblAccessories = new JLabel("Accessories :");
		lblAccessories.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAccessories.setBounds(263, 77, 89, 16);
		add(lblAccessories);
		
		
		DefaultComboBoxModel allAccessoriesModel = new DefaultComboBoxModel();
		final JComboBox comboAcc = new JComboBox(allAccessoriesModel);
		List<Accessory> accList = eventFacade.getAllAccessories().getList();
		for (int i = 0; i < accList.size(); i++){
			allAccessoriesModel.addElement(accList.get(i));
		}
		comboAcc.setBounds(219, 260, 161, 22);
		add(comboAcc);
		
		JLabel lblAddAnAccessory = new JLabel("Add an accessory :");
		lblAddAnAccessory.setBounds(245, 231, 117, 16);
		add(lblAddAnAccessory);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(454, 260, 97, 25);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = eventFacade.addAccessoryToRoom((Integer.parseInt(((Accessory)comboAcc.getSelectedItem()).getID())),((Room)list.getSelectedValue()).getIdRoom());
				if (result == -1){
					JOptionPane.showMessageDialog(null, "An error occured","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "The accessory for this room was added successfully","Information",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		add(btnAdd);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(454, 106, 97, 25);
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				((Accessory)accessoryList.getSelectedItem()).getID();
				int result = eventFacade.deleteAccessoryFromRoom(((Room)list.getSelectedValue()).getIdRoom(), Integer.parseInt(((Accessory)accessoryList.getSelectedItem()).getID()));
				if (result == -1){
					JOptionPane.showMessageDialog(null, "An error occured","Error",JOptionPane.ERROR_MESSAGE);
				}else{
					JOptionPane.showMessageDialog(null, "The accessory for this room was deleted successfully","Information",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		add(btnDelete);
	}
	}

