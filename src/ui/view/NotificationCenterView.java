package ui.view;

import ui.common.VerticalLayout;
import ui.common.View;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SpringLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;

import persistance.data.generic.Notification;
import persistance.data.generic.NotificationList;
import bl.facade.UserFacade;

import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class NotificationCenterView extends View implements ActionListener{

	private UserFacade userFacade;
	
	private JLabel titleLabel;
	private JPanel listPanel;
		
	public NotificationCenterView() {
		super("Notification Center");
		
		userFacade = new UserFacade();
		
		SpringLayout springLayout = new SpringLayout();
		setLayout(springLayout);
		
		titleLabel = new JLabel("Notifications");
		springLayout.putConstraint(SpringLayout.NORTH, titleLabel, 0, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, titleLabel, 0, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.EAST, titleLabel, 597, SpringLayout.WEST, this);
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		add(titleLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 92, SpringLayout.SOUTH, titleLabel);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 107, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, -106, SpringLayout.SOUTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, -108, SpringLayout.EAST, this);
		add(scrollPane);
		
		listPanel = new JPanel();
		scrollPane.setViewportView(listPanel);
		listPanel.setLayout(new VerticalLayout());

		//Enlever le commentaire suivant pour éditer avec WindowsBuilder l'apparence de la cellule
		listPanel.add(makeHeaderCell());
		//listPanel.add(makeNotificationCell(-1,true,"Message","Sender"));
				
	}
	public void init(){
		setNotifications();
	}
	
	private JPanel makeHeaderCell(){
		JPanel headercell = new JPanel();
		
		//La cellule est composée de 4 éléments
		headercell.setLayout(new GridLayout(0, 3, 0, 0));
		
		JLabel messageLb = new JLabel("Message");
		messageLb.setHorizontalAlignment(SwingConstants.CENTER);
		headercell.add(messageLb);
		
		JLabel senderLb = new JLabel("Sender");
		senderLb.setHorizontalAlignment(SwingConstants.CENTER);
		headercell.add(senderLb);
		
		JLabel checkLb = new JLabel("    New Notification ?    ");
		checkLb.setHorizontalAlignment(SwingConstants.CENTER);
		headercell.add(checkLb);
		
		return headercell;
	}
	
	private JPanel makeNotificationCell(int id, boolean read, String message,String sender) {
		JPanel cell = new JPanel();
		
		//La cellule est composée de 4 éléments
		cell.setLayout(new GridLayout(0, 3, 0, 0));
		
		JTextArea lbMessage = new JTextArea(message);
		lbMessage.setRows(3);
		lbMessage.setWrapStyleWord(true);
		lbMessage.setLineWrap(true);
		lbMessage.setEditable(false);
		cell.add(lbMessage);
		
		JLabel senderLb = new JLabel(sender);
		senderLb.setHorizontalAlignment(SwingConstants.CENTER);
		cell.add(senderLb);
		
		JCheckBox isRead = new JCheckBox("");
		isRead.setHorizontalAlignment(SwingConstants.CENTER);
		if(!read)
			isRead.setText("New");
		isRead.setSelected(read);
		isRead.setEnabled(!read);
		isRead.setActionCommand("Check");
		isRead.setName(""+id);
		isRead.addActionListener(this);
		cell.add(isRead);
		
		return cell;
	}
	

	
	public void setNotifications() {
		NotificationList notifs = userFacade.getNotifications();

		int newNotifNumber = 0;
		Notification notif;
		for(int i = 0 ; i < notifs.size() ; i++) {
			notif = notifs.get(i);
			if(!notif.getRead())
				newNotifNumber++;
			listPanel.add(makeNotificationCell(notif.getID(),notif.getRead(),notif.getMessage(),notif.getSender()));
			
		}
		if(newNotifNumber == 0)
			titleLabel.setText("No new notification");
		else
			titleLabel.setText("You have "+newNotifNumber+" new notifications");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("Check")) {
			JCheckBox box = (JCheckBox)(e.getSource());
			int numNotif = Integer.parseInt(box.getName());
			box.setSelected(true);
			box.setText("");
			box.setEnabled(false);
			userFacade.setNotificationViewed(numNotif);
		}
	}
}
