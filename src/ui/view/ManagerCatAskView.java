package ui.view;

import bl.facade.UserFacade;
import ui.common.View;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class ManagerCatAskView extends View implements ActionListener{
	
	public UserFacade userFacade;
	
	private JTextField fieldAsk;
	private JLabel lbEmptyCat;
	JButton btnAsk;
	
    public ManagerCatAskView() {
		super("Activity Category");
		
		userFacade = UserFacade.instance();
		
		setLayout(null);
		
		JLabel lbAsk = new JLabel("Ask for a new Activity Category");
		lbAsk.setBounds(60, 36, 193, 14);
		add(lbAsk);
		
		fieldAsk = new JTextField();
		fieldAsk.setBounds(60, 77, 153, 20);
		add(fieldAsk);
		fieldAsk.setColumns(10);
		
		btnAsk = new JButton("Ask");
		btnAsk.setBounds(92, 122, 89, 23);
		btnAsk.addActionListener(this);
		add(btnAsk);
		
		lbEmptyCat = new JLabel("The activity category can't be empty");
		lbEmptyCat.setForeground(Color.RED);
		lbEmptyCat.setBounds(245, 80, 314, 14);
		lbEmptyCat.setVisible(false);
		add(lbEmptyCat);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(btnAsk)) {
			String activityCategory = fieldAsk.getText();
			if(activityCategory.isEmpty()){
				lbEmptyCat.setVisible(true);
			}
			else {
				lbEmptyCat.setVisible(false);
				String  errorMessage = askActivityCategory("Cat activity ask : "+activityCategory);
				if(errorMessage != null)
					alert("Error while trying to ask for the new activity category");
				else
					alert("Notification sent !");
			}
		}
	}

	private String askActivityCategory(String activityCategory) {
		return userFacade.handleActivityCatAsk(activityCategory);
	}
}
