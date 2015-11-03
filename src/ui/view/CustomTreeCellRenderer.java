package ui.view;

import java.awt.Component;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

@SuppressWarnings("serial")
class CustomTreeCellRenderer extends DefaultTreeCellRenderer {
    Icon category;
    Icon product;

    public CustomTreeCellRenderer() {
        this.category = new ImageIcon(CustomTreeCellRenderer.class.getResource("category.jpg"));
        this.product = new ImageIcon(CustomTreeCellRenderer.class.getResource("product.jpg"));
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        
    	super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        
        if (((DefaultMutableTreeNode) value).getUserObject().getClass().getName().endsWith("MSProduct")) {
            setIcon(product);
        } else if (((DefaultMutableTreeNode) value).getUserObject().getClass().getName().endsWith("MSProductCategory")){
            setIcon(category);
        }
        return this;
    }
}