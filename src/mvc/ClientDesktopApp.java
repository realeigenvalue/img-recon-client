package mvc;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientDesktopApp.
 */
public class ClientDesktopApp {
	
	/**
	 * Instantiates a new client desktop app.
	 */
	public ClientDesktopApp() {
		ClientModel model = new ClientModel();
		ClientView view = new ClientView();
		ClientController controller = new ClientController(model, view);
	}
}
