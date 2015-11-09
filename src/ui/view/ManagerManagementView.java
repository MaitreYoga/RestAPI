package ui.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import bl.facade.UserFacade;
import ui.common.View;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ManagerManagementView extends View implements ActionListener, ItemListener
{
	public UserFacade userFacade;
	
	//graphic components
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JPanel container;
	private CardLayout containerLayout;
	private JPanel emptyPanel;
	private JTextArea txtManagers;
	private JTextField txtUser;
	private JComboBox comboBoxUsers;
	private JComboBox comboBoxManagers;
	private JButton btnAddManager;
	private JButton btnDeleteManager;
	private JLabel lblManager;
	private JTextField txtManager;
	
	public ManagerManagementView()
	{
		super("Manager");
		setLayout(null);
		
		userFacade = UserFacade.instance();
		
		/* Panel inside ScrollPane */
		this.containerLayout = new CardLayout();
		this.container = new JPanel(this.containerLayout);
		this.emptyPanel = new JPanel();
		container.add(emptyPanel);
		emptyPanel.setLayout(null);
		
		comboBoxUsers = new JComboBox();
		comboBoxUsers.setBounds(10, 36, 86, 20);
		emptyPanel.add(comboBoxUsers);	

		JLabel lblUsers = new JLabel("Please select a user : ");
		lblUsers.setBounds(10, 11, 142, 14);
		emptyPanel.add(lblUsers);

		txtUser = new JTextField();
		txtUser.setBounds(134, 36, 86, 20);
		emptyPanel.add(txtUser);
		txtUser.setColumns(10);
		
		btnAddManager = new JButton("Add as Manager");
		btnAddManager.setBounds(43, 77, 138, 23);
		emptyPanel.add(btnAddManager);
		
		btnDeleteManager = new JButton("Delete Manager");
		btnDeleteManager.setBounds(54, 250, 138, 23);
		emptyPanel.add(btnDeleteManager);
		
		lblManager = new JLabel("Please select a manager :");
		lblManager.setBounds(10, 186, 171, 14);
		emptyPanel.add(lblManager);
		
		comboBoxManagers = new JComboBox();
		comboBoxManagers.setBounds(10, 211, 86, 20);
		emptyPanel.add(comboBoxManagers);


		txtManager = new JTextField();
		txtManager.setBounds(134, 211, 86, 20);
		emptyPanel.add(txtManager);
		txtManager.setColumns(10);

		this.scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(this.container);
		
		txtManagers = new JTextArea();
		
		/* SplitPane*/
		this.splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 879, 438);
		this.splitPane.setLeftComponent(txtManagers);
		this.splitPane.setRightComponent(scrollPane);
		splitPane.setOneTouchExpandable(true);
		add(splitPane);
		this.positionDivider();
		
		//add listeners
		btnAddManager.addActionListener(this);
		btnDeleteManager.addActionListener(this);
		comboBoxUsers.addItemListener(this);
		comboBoxManagers.addItemListener(this);
	}
	public void init(){
		//fill combo box with users
		/*List<User> userList = userFacade.getUsers();
		int i = 0;
		while(i<userList.size())
		{
			comboBoxUsers.addItem(userList.get(i));
			i++;
		}
		
		//fill combo box with managers
		List<Object> managerList = userFacade.getManagers();
		int j = 0;
		while(j<managerList.size())
		{
			comboBoxManagers.addItem(managerList.get(j));
			j++;
		}
		
		//display manager list
		txtManagers.setText(userFacade.updateManagerList());*/
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
	public void itemStateChanged(ItemEvent e) 
	{
		JComboBox source = (JComboBox)e.getSource();
		if(source.equals(comboBoxUsers))
		{
			displaySelectedUser(txtUser);
		}
		else if (source.equals(comboBoxManagers))
		{
			displaySelectedManager(txtManager);
		}
	}

	private void displaySelectedManager(JTextField txtManager) 
	{
		if(!(comboBoxManagers.getSelectedItem().toString().isEmpty()))
		{
			txtManager.setText(comboBoxManagers.getSelectedItem().toString());		

		}
	}

	private void displaySelectedUser(JTextField txtUser) 
	{
		txtUser.setText(comboBoxUsers.getSelectedItem().toString());
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JButton source = (JButton)e.getSource();
		if(source.equals(btnAddManager))
		{
			addNewManager(txtUser.getText().toString());
			updateManagerList();
			
			comboBoxManagers.addItem(txtUser.getText().toString());
			
    		txtUser.setText("");
		}
		if(source.equals(btnDeleteManager))
		{
			deleteManager(txtManager.getText().toString());
			updateManagerList();
			
			comboBoxManagers.removeItem(txtManager.getText().toString());
			
			txtManager.setText("");
		}
	}

	private void updateManagerList() 
	{
		txtManagers.removeAll();
		txtManagers.setText(userFacade.updateManagerList());		
	}
	
	private void deleteManager(String userName) 
	{
		if(userFacade.handleManagerDelete(userName))
		{
			inform(userName+ " is no longer a manager");
		}
		else
		{
			alert(userName +" is not a manager");
		}
	}

	private void addNewManager(String user) 
	{
		if(userFacade.handleManagerAdd(user))
		{
			inform(user+" was successfuly added as a manager !");
		}
		else
		{
			alert(user+" is already registered as a manager !");
		}
	}

}
