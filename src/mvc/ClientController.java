package mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientController.
 */
public class ClientController {
	
	/** The model. */
	private ClientModel model;
	
	/** The view. */
	private ClientView view;
	
	/** The Button open file listener. */
	private ActionListener ButtonOpenFileListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int retval = view.getFileChooser().showOpenDialog(null);
			if(retval == JFileChooser.APPROVE_OPTION) {
				model.setSelected_file(view.getFileChooser().getSelectedFile());
				view.getOpenFileBTN().setText(model.getSelected_file().getName());
			}
		}
	};
	
	/** The Button create new listener. */
	private ActionListener ButtonCreateNewListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(model.getSelected_file() != null) {
				String name = view.getAddNameTXT().getText();
				String imageURL = model.getSelected_file().getAbsolutePath();
				model.ADD_TO_DATABASE(name, imageURL);
				view.addedEntryMessageDialog(name);
				model.setSelected_file(null);
				view.getOpenFileBTN().setText("Open File");
			}
		}
	};
	
	/** The Button add exist listener. */
	private ActionListener ButtonAddExistListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(model.getSelected_file() != null) {
				String name = view.getAddNameTXT().getText();
				String imageURL = model.getSelected_file().getAbsolutePath();
				String imageName = model.getSelected_file().getName();
				String serverResponse = model.ADD_TO_SAMPLES(name, imageURL);
				model.setSelected_file(null);
				view.getOpenFileBTN().setText("Open File");
				String[] result = serverResponse.split("\\|");
				if(result[1].equals("TRUE")) {
					view.addedSampleMessageDialog(name, imageName);
				} else {
					view.doesNotExistMessageDialog(name);
				}
			}
		}
	};
	
	/** The Button remove listener. */
	private ActionListener ButtonRemoveListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String name = view.getRemoveNameTXT().getText();
			String serverResponse = model.REMOVE_FROM_DATABASE(name);
			String[] result = serverResponse.split("\\|");
			if(result[1].equals("TRUE")) {
				view.removeEntryMessageDialog(name);
			} else {
				view.doesNotExistMessageDialog(name);
			}
		}
	};
	
	/** The Button open test file listener. */
	private ActionListener ButtonOpenTestFileListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			int retval = view.getFileChooser().showOpenDialog(null);
			if(retval == JFileChooser.APPROVE_OPTION) {
				model.setSelected_file(view.getFileChooser().getSelectedFile());
				view.getOpenTestFileBTN().setText(model.getSelected_file().getName());
			}
		}
	};
	
	/** The Button test listener. */
	private ActionListener ButtonTestListener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(model.getSelected_file() != null) {
				String imageURL = model.getSelected_file().getAbsolutePath();
				String serverResponse = model.RECOGNIZE(imageURL);
				model.setSelected_file(null);
				view.getOpenTestFileBTN().setText("Open Test File");
				String[] result = serverResponse.split("\\|");
				view.recognizeMessageDialog(result[2], result[3]);
			}
		}
	};
	
	/**
	 * Instantiates a new client controller.
	 *
	 * @param model the model
	 * @param view the view
	 */
	public ClientController(ClientModel model, ClientView view) {
		this.model = model;
		this.view = view;
		while(true) {
			Object[] result = view.connectDialog();
			String ipAddress = ((JTextField)result[0]).getText();
			int portNumber = Integer.parseInt(((JTextField)result[1]).getText());
			if(model.exists(ipAddress, portNumber)) {
				model.ipConfig(ipAddress, portNumber);
				break;
			} else {
				view.cannotConnectMessageDialog();
			}
		}
		this.view.addButtonOpenFileListener(ButtonOpenFileListener);
		this.view.addButtonCreateNewListener(ButtonCreateNewListener);
		this.view.addButtonAddExistListener(ButtonAddExistListener);
		this.view.addButtonRemoveListener(ButtonRemoveListener);
		this.view.addButtonOpenTestFileListener(ButtonOpenTestFileListener);
		this.view.addButtonTestListener(ButtonTestListener);
	}
}
