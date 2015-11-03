package ui.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import ui.common.View;

import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;

import bl.facade.EventFacade;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ActivityCategoryView extends View implements ActionListener
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
	 private JTextArea txtActivityCategories;
	 private JLabel lblCategoryName;
	 private JTextField txtCategoryName;
	 private JButton btnAddCategory;
	 private JButton btnDeleteCategory;
	 private JLabel lblChosenCategory;
	 private JComboBox comboBoxCategory;
    
	public ActivityCategoryView() 
	{
		super("Activity Category");
		setLayout(null);
		
		eventFacade = new EventFacade();
		
		/* Panel inside ScrollPane */
		this.containerLayout = new CardLayout();
		this.container = new JPanel(this.containerLayout);
		this.emptyPanel = new JPanel();
		container.add(emptyPanel);
		emptyPanel.setLayout(null);
		
		lblCategoryName = new JLabel("Category name :");
		lblCategoryName.setBounds(10, 43, 115, 14);
		emptyPanel.add(lblCategoryName);
		
		txtCategoryName = new JTextField();
		txtCategoryName.setBounds(119, 40, 91, 20);
		emptyPanel.add(txtCategoryName);
		txtCategoryName.setColumns(10);
		
		btnAddCategory = new JButton("Add Category");
		btnAddCategory.setBounds(53, 89, 111, 23);
		emptyPanel.add(btnAddCategory);
		
		lblChosenCategory = new JLabel("Choose a category :");
		lblChosenCategory.setBounds(10, 173, 115, 14);
		emptyPanel.add(lblChosenCategory);
		
		comboBoxCategory = new JComboBox();
		comboBoxCategory.setBounds(130, 170, 91, 20);
		emptyPanel.add(comboBoxCategory);

		btnDeleteCategory = new JButton("Delete Category");
		btnDeleteCategory.setBounds(53, 228, 127, 23);
		emptyPanel.add(btnDeleteCategory);
		
		/* Scroll Pane */
		this.scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(this.container);
		
		txtActivityCategories = new JTextArea();
		
		/* SplitPane*/
		this.splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 879, 438);
		this.splitPane.setLeftComponent(txtActivityCategories);
		this.splitPane.setRightComponent(scrollPane);
		splitPane.setOneTouchExpandable(true);
		add(splitPane);
		this.positionDivider();
		
		//add listeners
		btnAddCategory.addActionListener(this);
		btnDeleteCategory.addActionListener(this);
		
	}
	public void init(){
		//fill combo box with activity categories
		List<Object> categoryList = eventFacade.getCategories();
		int i = 0;
		while(i<categoryList.size())
		{
			comboBoxCategory.addItem(categoryList.get(i));
			i++;
		}
		//display list of categories
		txtActivityCategories.setText(eventFacade.updateCategoryList());
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
		if(source.equals(btnAddCategory))
		{
			String activityCategoryName = txtCategoryName.getText().toString();
			
			addActivityCategory(activityCategoryName);	
			comboBoxCategory.addItem(activityCategoryName);
		
			txtCategoryName.setText("");
		}
		if(source.equals(btnDeleteCategory))
		{
			String activityCategoryName = comboBoxCategory.getSelectedItem().toString();
			
			deleteActivityCategory(activityCategoryName);	
			comboBoxCategory.removeItem(activityCategoryName);
		
		}	
	}

	private void deleteActivityCategory(String activityCategoryName) 
	{
		if(eventFacade.deleteActivityCategory(activityCategoryName))
		{
			inform(activityCategoryName+ " has been successfully deleted !");
		}
		else
		{
			alert(activityCategoryName +" does not exist");
		}
		updateList();
		
	}

	private void updateList() 
	{
		txtActivityCategories.removeAll();
		txtActivityCategories.setText(eventFacade.updateCategoryList());			
	}
	private void addActivityCategory(String activityCategoryName) 
	{
		if(eventFacade.createActivityCategory(activityCategoryName))
		{
			inform("A new category has been successfuly added !");
		}
		else
		{
			alert("The "+activityCategoryName+" category already exists !");
		}
		updateList();
	}

}
