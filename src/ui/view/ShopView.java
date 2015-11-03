package ui.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import ui.common.Frame;
import ui.common.View;
import bl.facade.ShopFacade;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import persistance.Session;
import persistance.data.generic.Product;
import persistance.data.generic.ProductCategory;
import persistance.data.mysql.MSProduct;
import persistance.data.mysql.MSProductCategory;

import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class ShopView extends View implements TreeSelectionListener,MouseListener{

	public String addToCartButton;
	public String menuPanel;
	public ShopFacade shopFacade;

	private JTree tree;
	private DefaultMutableTreeNode root;
	private JSplitPane splitPane;
	private JScrollPane scrollPane;
	private JPanel container;
	private CardLayout containerLayout;
	private JPanel emptyPanel;
	private JPanel productPanel;

	public ShopView() {

		super("Shop");
		this.shopFacade = new ShopFacade();
		setLayout(null);

		/* Tree */
		this.root = new DefaultMutableTreeNode("Shop");
		this.tree = new JTree(this.root);
		tree.setBorder(new EmptyBorder(2, 4, 0, 0));
		tree.setRootVisible(false);
		this.tree.addTreeSelectionListener(this);
		this.tree.addMouseListener(this);

		/* Icons for tree nodes */
		ToolTipManager.sharedInstance().registerComponent(tree);
		tree.setCellRenderer(new CustomTreeCellRenderer());

		/* Panel inside ScrollPane */
		this.containerLayout = new CardLayout();
		this.container = new JPanel(this.containerLayout);
		this.emptyPanel = new JPanel();
		this.productPanel = new JPanel();
		container.add(emptyPanel);
		container.add(productPanel);

		/* Scroll Pane */
		this.scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setViewportView(this.container);
		/* SplitPane*/
		this.splitPane = new JSplitPane();
		splitPane.setBounds(0, 0, 879, 438);
		this.splitPane.setLeftComponent(tree);
		this.splitPane.setRightComponent(scrollPane);
		splitPane.setOneTouchExpandable(true);
		add(splitPane);
		this.positionDivider();

	}
	public void init(){
		/* Load root categories */
		this.loadPrimaryCategory();

	}

	public void loadPrimaryCategory(){
		this.root.removeAllChildren();
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
		if (nodeInfo.getClass().getName().endsWith("MSProduct")){
			this.container.removeAll();
			this.productPanel = new ProductDetailsView(((MSProduct)node.getUserObject()).getIdProd(),false,true,this);
			this.container.add(this.productPanel);
			this.splitPane.revalidate();
		}else if (nodeInfo.getClass().getName().endsWith("MSProductCategory")){
			try{
				List<ProductCategory> sub = this.shopFacade.getSubCategoriesByCat(((MSProductCategory)node.getUserObject()).getIdCategory());
				List<Product> products = this.shopFacade.getProductsByCat(((MSProductCategory)node.getUserObject()).getIdCategory());
				for (int i = 0; i < sub.size(); i++){
					node.add(new DefaultMutableTreeNode(sub.get(i),true));
				}

				for (int i = 0; i < products.size(); i++){
					node.add(new DefaultMutableTreeNode(products.get(i),false));
				}
				this.tree.expandPath(new TreePath(node.getPath()));
				Frame.getFrame().requestFocus();
			}catch (ClassCastException ex){
				System.out.println("ClassCastException");
			}
		}
		/* React to the node selection. */

	}

	private void positionDivider() {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				splitPane.setDividerLocation(0.25);
			}
		});
	}

	@Override
	public void mouseClicked(MouseEvent me) {
		/* handle a right click on a category to sell a product in this category */
		if(SwingUtilities.isRightMouseButton(me)){
			try{
				TreePath path = tree.getPathForLocation(me.getX(), me.getY());
				DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
				
				/* ignore right clicks on products */
				if (selectedNode.getUserObject().getClass().getName().endsWith("MSProductCategory")){
					JPopupMenu menu = new JPopupMenu();
					JMenuItem item = new JMenuItem("Sell a product in the \""+((MSProductCategory)selectedNode.getUserObject()).getLibCat()+"\" category");
					item.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							if(e.getModifiers()==MouseEvent.BUTTON1_MASK){
								Frame.getFrame().setView(new AddProductView(((MSProductCategory)selectedNode.getUserObject()).getIdCategory()),true);
							}
							
							
						}
					});
					menu.add(item);
					menu.show(me.getComponent(), me.getX(), me.getY());
				}
			}catch(NullPointerException e){/* catches a right click in an empty area*/}

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

