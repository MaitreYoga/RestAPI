package ui.common;

import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import persistance.Session;
import ui.view.AccessoriesManagementView;
import ui.view.ActivityCategoryView;
import ui.view.ActivityManagerAdminView;
import ui.view.AdminActivityManagementView;
import ui.view.CategoryManagementView;
import ui.view.EventInscriptionView;
import ui.view.MemberSubscriptionView;
import ui.view.PlanEventView;
import ui.view.UserEventPlanningView;
import ui.view.EventSearchView;
import ui.view.ManagerManagementView;
import ui.view.ManagerCatAskView;
import ui.view.MemberProfileView;
import ui.view.NotificationCenterView;
import ui.view.OrderView;
import ui.view.RoomAccessoryManagementView;
import ui.view.RoomManagementView;
import ui.view.ShopView;
import ui.view.ShoppingCartView;
import ui.view.SpeakerProfileView;
import ui.view.UserInvoicesView;
import ui.view.UserOrdersView;
import ui.view.UserProfileView;
import ui.view.UserSalesView;

public class Frame extends JFrame implements ChangeListener{
	
	private static Frame f;
	
    private final Dimension defaultDimensions = new Dimension(720,480);

    private JTabbedPane tabbedPane;

    	
	private Frame() {
		super();
		super.setSize(defaultDimensions);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		tabbedPane.addChangeListener(this);
    	
    	SpringLayout springLayout = new SpringLayout();
    	setLayout(springLayout);
    	
		springLayout.putConstraint(SpringLayout.NORTH, tabbedPane, 0, SpringLayout.NORTH, this);
    	springLayout.putConstraint(SpringLayout.WEST, tabbedPane, 0, SpringLayout.WEST, this);
    	springLayout.putConstraint(SpringLayout.SOUTH, tabbedPane, 445, SpringLayout.NORTH, this);
    	springLayout.putConstraint(SpringLayout.EAST, tabbedPane, 714, SpringLayout.WEST, this);
    	    	
    	firstShow = true;
    	
    	
    	this.add(tabbedPane);
	}
	
	private boolean firstShow;
	private void setTabbedPane(){
		if(firstShow){
			firstShow = false;

	    	//NotificationCenter
			tabbedPane.add(new NotificationCenterView());
			
			if(!Session.checkPermission("Member")) {
				JTabbedPane registrationTabbedPane = new JTabbedPane(); 
				tabbedPane.addTab("Member", registrationTabbedPane);
				registrationTabbedPane.add(new MemberSubscriptionView());
			}
			
	    	//Profile
			JTabbedPane profileTabbedPane = new JTabbedPane();
			tabbedPane.addTab("Profile", profileTabbedPane);
			profileTabbedPane.add(new UserProfileView());
			if(Session.checkPermission("Member"))
				profileTabbedPane.add(new MemberProfileView());
			if(Session.checkPermission("Speaker"))
				profileTabbedPane.add(new SpeakerProfileView(false));
			profileTabbedPane.addChangeListener(this);
			
	    	//Store
			JTabbedPane storeTabbedPane = new JTabbedPane();
			tabbedPane.addTab("Store", storeTabbedPane);
			storeTabbedPane.add(new ShopView());
			storeTabbedPane.add(new ShoppingCartView());
			storeTabbedPane.add(new UserOrdersView());
			storeTabbedPane.add(new UserInvoicesView());
			if(Session.checkPermission("Member"))
				storeTabbedPane.add(new UserSalesView());
			storeTabbedPane.addChangeListener(this);
	    	
	    	//Planning
			if(Session.checkPermission("Member"))
				tabbedPane.add(new UserEventPlanningView());

	    	//Events
			if(Session.checkPermission("Member"))
			{
				JTabbedPane eventTabbedPane = new JTabbedPane();
				tabbedPane.addTab("Event", eventTabbedPane);
				eventTabbedPane.add(new EventInscriptionView());
				eventTabbedPane.add(new EventSearchView());
				eventTabbedPane.addChangeListener(this);
			}
	    	
	    	//Management
			if(Session.checkPermission("Manager"))
			{
				JTabbedPane managementTabbedPane = new JTabbedPane();
				tabbedPane.addTab("Management", managementTabbedPane);
				managementTabbedPane.add(new ManagerCatAskView());
				managementTabbedPane.add(new RoomAccessoryManagementView());
				managementTabbedPane.add(new PlanEventView());
				managementTabbedPane.addChangeListener(this);
			}
	    	//Administration
			if(Session.checkPermission("Administrator"))
			{
				JTabbedPane adminTabbedPane = new JTabbedPane(JTabbedPane.TOP);
				tabbedPane.addTab("Administration", adminTabbedPane);
				adminTabbedPane.add(new ActivityCategoryView());
				adminTabbedPane.add(new ActivityManagerAdminView());
				adminTabbedPane.add(new CategoryManagementView());
				adminTabbedPane.add(new RoomManagementView());
				adminTabbedPane.add(new AccessoriesManagementView());
				adminTabbedPane.add(new AdminActivityManagementView());
				adminTabbedPane.add(new ManagerManagementView());
		    	adminTabbedPane.addChangeListener(this);
			}			
		}
	}
	
	
	public static Frame getFrame(){
		if(f == null)
			f = new Frame();
		return f;
	}
		
	public void setView(View v, boolean showTabbedPane){
		super.setTitle(v.getName());
		if(showTabbedPane){
			setTabbedPane();
			super.setContentPane(tabbedPane);
			if(tabbedPane.getSelectedIndex()==-1)
				tabbedPane.setComponentAt(0,v);
			else {
				if( tabbedPane.getSelectedComponent() instanceof JPanel){
					tabbedPane.setComponentAt(tabbedPane.getSelectedIndex(),v);	
				}
				else {
					JTabbedPane subTab = (JTabbedPane)(tabbedPane.getSelectedComponent());
					subTab.setComponentAt(subTab.getSelectedIndex(),v);
				}
			}
		}
		else
			super.setContentPane(v);
		v.init();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		Component selectedComponent = ((JTabbedPane)(e.getSource())).getSelectedComponent();
		if(selectedComponent != null){
			if(selectedComponent instanceof View){
				//System.out.println(selectedComponent.getName());
				((View)(selectedComponent)).init();
			}
			else {
				Component subSelectedComponent = ((JTabbedPane)(selectedComponent)).getSelectedComponent();
				if(subSelectedComponent != null){
					//System.out.println(subSelectedComponent.getName());
					((View)(subSelectedComponent)).init();
				}
			}
		}
	}
}