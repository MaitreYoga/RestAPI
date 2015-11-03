import persistance.MySQLDatabase;
import ui.common.Frame;
import ui.view.LoginView;

public class Main {
	public static void main(String[] args) {
		//Display the loading splash screen
		loadingScreen ls = new loadingScreen();
		ls.setVisible(true);
	
		//Load datas
		MySQLDatabase.getInstance().open();
		Frame f = Frame.getFrame();
		f.setView(new LoginView(),false);

		//Remove the splash screen and show the application
		ls.dispose();
		f.setVisible(true);
	}
}