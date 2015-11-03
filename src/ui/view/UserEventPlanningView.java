package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import ui.common.Frame;
import ui.common.View;
import bl.facade.EventFacade;

import javax.swing.SpringLayout;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JTable;

import persistance.data.generic.EventList;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class UserEventPlanningView extends View implements MouseListener, ActionListener {
	
    public EventFacade eventFacade;
    
    private JScrollPane scrollPane;
    
    private JTable table;
    
    private JButton btnDetail;
    private SpringLayout springLayout;
    
    public UserEventPlanningView() {
		super("My Planning");
		
		eventFacade = new EventFacade();
		
		springLayout = new SpringLayout();
		setLayout(springLayout);
		
		scrollPane = new JScrollPane();
		springLayout.putConstraint(SpringLayout.NORTH, scrollPane, 28, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.WEST, scrollPane, 10, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, scrollPane, 102, SpringLayout.NORTH, this);
		springLayout.putConstraint(SpringLayout.EAST, scrollPane, 600, SpringLayout.WEST, this);
		add(scrollPane);
		
		btnDetail = new JButton("Detail");
		springLayout.putConstraint(SpringLayout.WEST, btnDetail, 290, SpringLayout.WEST, this);
		springLayout.putConstraint(SpringLayout.SOUTH, btnDetail, -100, SpringLayout.SOUTH, this);
		btnDetail.setVisible(false);
		btnDetail.addActionListener(this);
		add(btnDetail);
				
    }
    public void init(){
		showEventDetails();
	}

	private JTable makeEventTable(EventList events) {
		
		String[] header = {"Activity","Event","Date","Duration","Room","Speaker","Status"};
		String[][] content = new String[events.size()][header.length];
		
		for(int i = 0 ; i < events.size() ; i++){
			content[i][0] = events.get(i).getActivity();
			content[i][1] = events.get(i).getName();
			content[i][2] = events.get(i).getStartDate();
			content[i][3] = events.get(i).getEndDate();
			content[i][4] = events.get(i).getRoom();
			content[i][5] = events.get(i).getSpeaker();
			content[i][6] = events.get(i).getState();
		}
		
		table = new JTable(content,header);
		
		table.addMouseListener(this);		   
		
		return table;
	}
		
    public void showEventDetails() {
    	EventList events = eventFacade.getUserEvents();
    	
    	if(events.size() == 0){
    		scrollPane.setVisible(false);
        	JLabel emptyEvents = new JLabel("You don't have registered to any event yet");
        	springLayout.putConstraint(SpringLayout.NORTH, emptyEvents, 9, SpringLayout.SOUTH, scrollPane);
        	springLayout.putConstraint(SpringLayout.WEST, emptyEvents, 215, SpringLayout.WEST, this);
        	JButton checkEvents = new JButton("Check events now !");
        	springLayout.putConstraint(SpringLayout.WEST, checkEvents, 256, SpringLayout.WEST, this);
        	springLayout.putConstraint(SpringLayout.SOUTH, checkEvents, -24, SpringLayout.NORTH, btnDetail);
        	
        	checkEvents.addActionListener(this);
        	checkEvents.setActionCommand("EventInscription");
        	
        	add(emptyEvents);
        	add(checkEvents);
    	}
    	else {
        	JTable table = makeEventTable(events);
        	scrollPane.setColumnHeaderView(table.getTableHeader());
    		scrollPane.setViewportView(table);
    	}
    }

	@Override
	public void mouseClicked(MouseEvent e) {
		/*Point pnt = e.getPoint();
		int row = table.rowAtPoint(pnt);
		btnDetail.setActionCommand("Detail");
		btnDetail.setName(""+row);
		btnDetail.setVisible(true);*/
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {
		/*if(e.getActionCommand().equals("Detail")){
			int numEvent = Integer.parseInt(((JButton) (e.getSource())).getName());
			alert("Vous allez voir le détail de l'événement N°"+numEvent);
		}
		else */if(e.getActionCommand().equals("EventInscription")){
	    	Frame.getFrame().setView(new EventSearchView(),true);
			Frame.getFrame().revalidate();
		}
	}
}
