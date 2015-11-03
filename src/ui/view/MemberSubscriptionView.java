package ui.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import ui.common.Frame;
import ui.common.View;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Font;

@SuppressWarnings("serial")
public class MemberSubscriptionView extends View implements ActionListener{
	
	private JButton btnJoinUs;
	
	public MemberSubscriptionView() {
		super("Member Subscription");
		setLayout(null);
		
		btnJoinUs = new JButton("Join Us !");
		btnJoinUs.setBounds(311, 244, 109, 23);
		btnJoinUs.addActionListener(this);
		add(btnJoinUs);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBounds(176, 40, 358, 174);
		add(panel);
		panel.setLayout(null);
		
		JLabel label = new JLabel("<html>Hello you ! To participate to our events and to sell your products on our application, you just need to become a member of our community ! <br> <br> So what are you waiting for !? </html>");
		label.setFont(new Font("Microsoft JhengHei UI", Font.PLAIN, 14));
		label.setBounds(21, 11, 309, 134);
		panel.add(label);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnJoinUs){
			Frame.getFrame().setView(new PaymentView(),true);
			Frame.getFrame().revalidate();
		}
	}
}