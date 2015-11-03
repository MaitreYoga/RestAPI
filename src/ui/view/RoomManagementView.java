package ui.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import bl.facade.EventFacade;
import ui.common.View;

import javax.swing.JSpinner;

import persistance.data.generic.Room;

@SuppressWarnings("serial")
public class RoomManagementView extends View implements ActionListener
{
	EventFacade eventFacade;
	
	//graphic components
	 private JTree tree;
	 private DefaultMutableTreeNode root;
	 private JSplitPane splitPane;
	 private JScrollPane scrollPane;
	 private JPanel container;
	 private CardLayout containerLayout;
	 private JPanel emptyPanel;
	 private JTextArea txtRooms;
	 private JLabel lblRoomName;
	 private JTextField txtRoomName;
	 private JButton btnAddRoom;
	 private JButton btnDeleteRoom;
	 private JLabel lblChosenRoom;
	 private JComboBox comboBoxRooms;
	 private JLabel lblSurface;
	 private JSpinner spinner;
	 private List<Room>listRoom;

	public RoomManagementView() 
	{
		super("Room");
		setLayout(null);
		eventFacade = new EventFacade();

		/* Panel inside ScrollPane */
		this.containerLayout = new CardLayout();
		this.container = new JPanel(this.containerLayout);
		this.emptyPanel = new JPanel();
		container.add(emptyPanel);
		emptyPanel.setLayout(null);
		
		lblRoomName = new JLabel("Room name :");
		lblRoomName.setBounds(10, 43, 102, 14);
		emptyPanel.add(lblRoomName);
		
		txtRoomName = new JTextField();
		txtRoomName.setBounds(119, 40, 91, 20);
		emptyPanel.add(txtRoomName);
		txtRoomName.setColumns(10);
		
		btnAddRoom = new JButton("Add Room");
		btnAddRoom.setBounds(55, 112, 111, 23);
		emptyPanel.add(btnAddRoom);
		
		lblChosenRoom = new JLabel("Choose a room");
		lblChosenRoom.setBounds(0, 173, 89, 14);
		emptyPanel.add(lblChosenRoom);
		
		comboBoxRooms = new JComboBox();
		comboBoxRooms.setBounds(99, 170, 122, 20);

		emptyPanel.add(comboBoxRooms);
		
		btnDeleteRoom = new JButton("Delete Room");
		btnDeleteRoom.setBounds(53, 228, 127, 23);
		emptyPanel.add(btnDeleteRoom);
		
		lblSurface = new JLabel("Surface (m\u00B2) :");
		lblSurface.setBounds(10, 77, 91, 14);
		emptyPanel.add(lblSurface);
		
		spinner = new JSpinner();
		spinner.setBounds(129, 71, 79, 20);
		emptyPanel.add(spinner);
		
		/* Scroll Pane */
		this.scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(this.container);
		
		txtRooms = new JTextArea();

		/* SplitPane*/
		this.splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 879, 438);
		this.splitPane.setLeftComponent(txtRooms);
		this.splitPane.setRightComponent(scrollPane);
		splitPane.setOneTouchExpandable(true);
		add(splitPane);
		this.positionDivider();
		
		//add listeners
		btnAddRoom.addActionListener(this);
		btnDeleteRoom.addActionListener(this);
		
	}
	public void init (){
		//fill combo box with rooms
		listRoom = eventFacade.getRooms();
		for (int i =0; i < listRoom.size(); i++ ){
			comboBoxRooms.addItem(listRoom.get(i).getName());
		}
		txtRooms.setText(eventFacade.updateRoomList());
		
	}
	
	 private void positionDivider() 
	 {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                splitPane.setDividerLocation(0.25);
            }
        });
	 }

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton source = (JButton)e.getSource();
		if(source.equals(btnAddRoom))
		{
			String roomName = txtRoomName.getText().toString();
			int surface = (int)spinner.getValue();
			
			addRoom(roomName,surface);	
			comboBoxRooms.addItem(roomName);
		
			txtRoomName.setText("");
		}
		if(source.equals(btnDeleteRoom))
		{
			String roomName = comboBoxRooms.getSelectedItem().toString();
			
			deleteRoom(roomName);	
			comboBoxRooms.removeItem(roomName);		
		}	
		
	}

	private void deleteRoom(String roomName) 
	{
		if(eventFacade.deleteRoom(roomName))
		{
			inform(roomName+ " has been successfully deleted !");
		}
		else
		{
			alert(roomName +" does not exist");
		}
		updateRoomList();
	}

	private void addRoom(String roomName, int surface) 
	{
		if(eventFacade.createRoom(roomName,surface))
		{
			inform("A new room has been successfuly added !");
		}
		else
		{
			alert("The "+roomName+" room already exists !");
		}
		updateRoomList();
		
	}
	private void updateRoomList()
	{
		txtRooms.removeAll();
		txtRooms.setText(eventFacade.updateRoomList());		
	}
}
