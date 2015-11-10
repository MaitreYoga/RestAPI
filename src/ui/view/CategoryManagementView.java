package ui.view;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import ui.common.Frame;
import ui.common.View;
import bl.facade.ShopFacade;
import dal.product.generic.ProductCategory;
import dal.product.mysql.MSProductCategory;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

@SuppressWarnings("serial")
public class CategoryManagementView extends View implements TreeSelectionListener,MouseListener{

	public String addToCartButton;
	public String menuPanel;
	public ShopFacade shopFacade;

	private JTree tree;
	private DefaultMutableTreeNode root;

	public CategoryManagementView() {

		super("Product Category");
		this.shopFacade = ShopFacade.instance();

		/* Tree */
		this.root = new DefaultMutableTreeNode("Shop");

		/* Icons for tree nodes */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{709, 0};
		gridBagLayout.rowHeights = new int[]{360, 0};
		gridBagLayout.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		this.tree = new JTree(this.root);
		tree.setBorder(new EmptyBorder(2, 4, 0, 0));
		tree.setRootVisible(false);
		this.tree.addTreeSelectionListener(this);
		this.tree.addMouseListener(this);
		ToolTipManager.sharedInstance().registerComponent(tree);
		tree.setCellRenderer(new CustomTreeCellRenderer());
		
		GridBagConstraints gbc_tree = new GridBagConstraints();
		gbc_tree.fill = GridBagConstraints.BOTH;
		gbc_tree.gridx = 0;
		gbc_tree.gridy = 0;
		add(tree, gbc_tree);
		
		
	}
	public void init(){
		/* Load root categories */
		this.loadPrimaryCategory();

	}

	public void loadPrimaryCategory(){
		List<ProductCategory> categories = shopFacade.getPrimaryCategories();
		for(int i=0; i<categories.size();i++){
			DefaultMutableTreeNode node = new DefaultMutableTreeNode(categories.get(i),true);
			this.root.add(node);
		}
		this.tree.expandPath(new TreePath(this.tree.getModel().getRoot()));
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

		/* if nothing is selected */ 
		if (node == null) return;

		/* retrieve the node that was selected */ 
		Object nodeInfo = node.getUserObject();
		if (nodeInfo.getClass().getName().endsWith("MSProductCategory")){
			try{
				List<ProductCategory> sub = this.shopFacade.getSubCategoriesByCat(((MSProductCategory)node.getUserObject()).getIdCategory());
				for (int i = 0; i < sub.size(); i++){
					node.add(new DefaultMutableTreeNode(sub.get(i),true));
				}
				this.tree.expandPath(new TreePath(node.getPath()));
				Frame.getFrame().requestFocus();
			}catch (ClassCastException ex){
				System.out.println("ClassCastException");
			}
		}
		/* React to the node selection. */

	}

	@Override
	public void mouseClicked(MouseEvent me) {
		/* handle a right click on a category*/
		if(SwingUtilities.isRightMouseButton(me)){
			try{
				TreePath path = tree.getPathForLocation(me.getX(), me.getY());
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
				
				/* if the user right-clicked on a category */
				if (selectedNode.getUserObject().getClass().getName().endsWith("MSProductCategory")){
					String lib = ((MSProductCategory)selectedNode.getUserObject()).getLibCat();
					final int parent = ((MSProductCategory)selectedNode.getUserObject()).getIdCategory();
					final int catId = ((MSProductCategory)selectedNode.getUserObject()).getIdCategory();
					JPopupMenu menu = new JPopupMenu();
					JMenuItem rename = new JMenuItem("Rename the \""+lib+"\" category");
					JMenuItem delete = new JMenuItem("Delete the \""+lib+"\" category");
					JMenuItem newCat = new JMenuItem("Add a subcategory in the \""+lib+"\" category");
					rename.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getModifiers()==MouseEvent.BUTTON1_MASK){
								String catName = prompt("Please specify a new name for this category");
								int result = shopFacade.updateCatName(catName, catId);
								if (result == -1){
									alert("An error occured");
								}else{
									inform("The catagory name was updated successfully");
									Frame.getFrame().setView(new CategoryManagementView(),true);
								}
							}												
						}
					});
					
					delete.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getModifiers()==MouseEvent.BUTTON1_MASK){
								boolean confirm = confirm("Are you sure tou want to delete ?");
								if (confirm){
									int result = shopFacade.delete(catId);
									if (result == -1){
										alert("An error occured");
									}else{
										inform("The catagory was deleted successfully");
										Frame.getFrame().setView(new CategoryManagementView(),true);
									}
								}
							}
							
							
						}
					});
					
					newCat.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getModifiers()==MouseEvent.BUTTON1_MASK){
								String name = prompt("Please specify a name for this category");
								String result = shopFacade.createCategory(name, parent);
								if (result.equals("ok")){
									inform("The catagory was created successfully");
									Frame.getFrame().setView(new CategoryManagementView(),true);
									
								}else{
									alert("An error occured");
								}
							}
							
							
						}
					});
					menu.add(rename);
					menu.add(delete);
					menu.add(newCat);
					menu.show(me.getComponent(), me.getX(), me.getY());
				}
			}catch(NullPointerException e){/* catches a right click in an empty area*/
				
				JPopupMenu menu = new JPopupMenu();
				JMenuItem newCat = new JMenuItem("Add a new category");
				newCat.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						if(e.getModifiers()==MouseEvent.BUTTON1_MASK){
							String name = prompt("Please specify a name for this category");
							String result = shopFacade.createCategory(name, 0);
							if (result.equals("ok")){
								inform("The catagory was created successfully");
							}else{
								alert("An error occured");
							}
						}
						
						
					}
				});
				menu.add(newCat);
				menu.show(me.getComponent(), me.getX(), me.getY());
				
				
			}

		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}