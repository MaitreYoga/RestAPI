import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.SwingConstants;
import java.awt.Font;

public class loadingScreen extends JFrame {
	
	public loadingScreen() {
		setUndecorated(true);
		setType(Type.POPUP);
		setResizable(false);
		setBackground(UIManager.getColor("ComboBox.buttonShadow"));
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 200, 90);
		
		JPanel contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("scrollbar"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JLabel lblZenloungeIsLoading = new JLabel("ZenLounge is loading...");
		lblZenloungeIsLoading.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblZenloungeIsLoading.setFont(new Font("Arial", Font.BOLD, 12));
		lblZenloungeIsLoading.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblZenloungeIsLoading, BorderLayout.CENTER);
		setLocationRelativeTo(null);
	}
}