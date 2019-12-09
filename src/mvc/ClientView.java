package mvc;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientView.
 */
public class ClientView extends JFrame {
	
	/** The Constant TITLE. */
	private static final String TITLE = "imgRecon Client";
	
	/** The tab pane. */
	private JTabbedPane tabPane;
	
	/** The test panel. */
	private JPanel addPanel, removePanel, testPanel;
	
	/** The add name TXT. */
	// add tab
	private JTextField addNameTXT;
	
	/** The add exist BTN. */
	private JButton openFileBTN, createNewBTN, addExistBTN;
	
	/** The remove name TXT. */
	// remove tab
	private JTextField removeNameTXT;
	
	/** The remove BTN. */
	private JButton removeBTN;
	
	/** The test BTN. */
	// test tab
	private JButton openTestFileBTN, testBTN;
	
	/** The fc. */
	private final JFileChooser fc;
	
	/**
	 * Instantiates a new client view.
	 */
	public ClientView() {
		setupGUI();
		fc = new JFileChooser();
		this.setContentPane(tabPane);
		this.pack();
		this.setTitle(ClientView.TITLE);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Setup GUI.
	 */
	private void setupGUI() {
		tabPane = new JTabbedPane();
		
		addPanel = new JPanel();
		addPanel.setLayout(new GridLayout(2, 2));
		addNameTXT = new JTextField("Name");
		openFileBTN = new JButton("Open File");
		createNewBTN = new JButton("Create New Entry");
		addExistBTN = new JButton("Add To Samples");
		addPanel.add(addNameTXT);
		addPanel.add(openFileBTN);
		addPanel.add(createNewBTN);
		addPanel.add(addExistBTN);
		
		removePanel = new JPanel();
		removePanel.setLayout(new GridLayout(1, 2));
		removeNameTXT = new JTextField("Name");
		removeBTN = new JButton("Remove Entry");
		removePanel.add(removeNameTXT);
		removePanel.add(removeBTN);
		
		testPanel = new JPanel();
		testPanel.setLayout(new GridLayout(1, 2));
		openTestFileBTN = new JButton("Open Test File");
		testBTN = new JButton("Recognize");
		testPanel.add(openTestFileBTN);
		testPanel.add(testBTN);
		
		tabPane.addTab("Add", addPanel);
		tabPane.addTab("Remove", removePanel);
		tabPane.addTab("Test", testPanel);
	}
	
	/**
	 * Gets the adds the name TXT.
	 *
	 * @return the adds the name TXT
	 */
	public JTextField getAddNameTXT() {
		return addNameTXT;
	}
	
	/**
	 * Gets the open file BTN.
	 *
	 * @return the open file BTN
	 */
	public JButton getOpenFileBTN() {
		return openFileBTN;
	}
	
	/**
	 * Gets the creates the new BTN.
	 *
	 * @return the creates the new BTN
	 */
	public JButton getCreateNewBTN() {
		return createNewBTN;
	}
	
	/**
	 * Gets the adds the exist BTN.
	 *
	 * @return the adds the exist BTN
	 */
	public JButton getAddExistBTN() {
		return addExistBTN;
	}
	
	/**
	 * Gets the removes the name TXT.
	 *
	 * @return the removes the name TXT
	 */
	public JTextField getRemoveNameTXT() {
		return removeNameTXT;
	}
	
	/**
	 * Gets the removes the BTN.
	 *
	 * @return the removes the BTN
	 */
	public JButton getRemoveBTN() {
		return removeBTN;
	}
	
	/**
	 * Gets the open test file BTN.
	 *
	 * @return the open test file BTN
	 */
	public JButton getOpenTestFileBTN() {
		return openTestFileBTN;
	}
	
	/**
	 * Gets the test BTN.
	 *
	 * @return the test BTN
	 */
	public JButton getTestBTN() {
		return testBTN;
	}
	
	/**
	 * Gets the file chooser.
	 *
	 * @return the file chooser
	 */
	public JFileChooser getFileChooser() {
		return fc;
	}
	
	/**
	 * Adds the button open file listener.
	 *
	 * @param listener the listener
	 */
	public void addButtonOpenFileListener(ActionListener listener) {
		openFileBTN.addActionListener(listener);
	}
	
	/**
	 * Adds the button create new listener.
	 *
	 * @param listener the listener
	 */
	public void addButtonCreateNewListener(ActionListener listener) {
		createNewBTN.addActionListener(listener);
	}
	
	/**
	 * Adds the button add exist listener.
	 *
	 * @param listener the listener
	 */
	public void addButtonAddExistListener(ActionListener listener) {
		addExistBTN.addActionListener(listener);
	}
	
	/**
	 * Adds the button remove listener.
	 *
	 * @param listener the listener
	 */
	public void addButtonRemoveListener(ActionListener listener) {
		removeBTN.addActionListener(listener);
	}
	
	/**
	 * Adds the button open test file listener.
	 *
	 * @param listener the listener
	 */
	public void addButtonOpenTestFileListener(ActionListener listener) {
		openTestFileBTN.addActionListener(listener);
	}
	
	/**
	 * Adds the button test listener.
	 *
	 * @param listener the listener
	 */
	public void addButtonTestListener(ActionListener listener) {
		testBTN.addActionListener(listener);
	}
	
	/**
	 * Connect dialog.
	 *
	 * @return the object[]
	 */
	public static Object[] connectDialog() {
		JTextField ip = new JTextField("127.0.0.1");
		JTextField port = new JTextField("1024");
		Object[] ret = {ip, port};
		Object[] message = {
				"IP", ip,
				"PORT", port,
				
		};
		Object[] options = {"Connect"};
		int option = JOptionPane.showOptionDialog(null, message, ClientView.TITLE,
												  JOptionPane.PLAIN_MESSAGE,
												  JOptionPane.QUESTION_MESSAGE,
												  null, options, options[0]);
		if(option == -1) {
			System.exit(0);
		}
		return ret;
	}
	
	/**
	 * Cannot connect message dialog.
	 */
	public static void cannotConnectMessageDialog() {
		Object[] message = {
				"Server not Found!"
		};
		Object[] options = {"OK"};
		int option = JOptionPane.showOptionDialog(null, message, ClientView.TITLE,
												  JOptionPane.PLAIN_MESSAGE,
												  JOptionPane.QUESTION_MESSAGE,
												  null, options, options[0]);
	}
	
	/**
	 * Added entry message dialog.
	 *
	 * @param name the name
	 */
	public static void addedEntryMessageDialog(String name) {
		Object[] message = {
				"Added " + name + " to database"
		};
		Object[] options = {"OK"};
		int option = JOptionPane.showOptionDialog(null, message, ClientView.TITLE,
												  JOptionPane.PLAIN_MESSAGE,
												  JOptionPane.QUESTION_MESSAGE,
												  null, options, options[0]);
	}
	
	/**
	 * Added sample message dialog.
	 *
	 * @param name the name
	 * @param imageName the image name
	 */
	public static void addedSampleMessageDialog(String name, String imageName) {
		Object[] message = {
				"Added image " + imageName + " to samples for " + name
		};
		Object[] options = {"OK"};
		int option = JOptionPane.showOptionDialog(null, message, ClientView.TITLE,
												  JOptionPane.PLAIN_MESSAGE,
												  JOptionPane.QUESTION_MESSAGE,
												  null, options, options[0]);
	}
	
	/**
	 * Removes the entry message dialog.
	 *
	 * @param name the name
	 */
	public static void removeEntryMessageDialog(String name) {
		Object[] message = {
				"Removed " + name + " from database"
		};
		Object[] options = {"OK"};
		int option = JOptionPane.showOptionDialog(null, message, ClientView.TITLE,
												  JOptionPane.PLAIN_MESSAGE,
												  JOptionPane.QUESTION_MESSAGE,
												  null, options, options[0]);
	}
	
	/**
	 * Recognize message dialog.
	 *
	 * @param name the name
	 * @param confidenceLevel the confidence level
	 */
	public static void recognizeMessageDialog(String name, String confidenceLevel) {
		Object[] message = {
				"Best Guess: " + name + " with probability " + confidenceLevel + "%"
		};
		Object[] options = {"OK"};
		int option = JOptionPane.showOptionDialog(null, message, ClientView.TITLE,
												  JOptionPane.PLAIN_MESSAGE,
												  JOptionPane.QUESTION_MESSAGE,
												  null, options, options[0]);
	}
	
	/**
	 * Does not exist message dialog.
	 *
	 * @param name the name
	 */
	public static void doesNotExistMessageDialog(String name) {
		Object[] message = {
				"The name " + name + " does not exist in the database"
		};
		Object[] options = {"OK"};
		int option = JOptionPane.showOptionDialog(null, message, ClientView.TITLE,
												  JOptionPane.PLAIN_MESSAGE,
												  JOptionPane.QUESTION_MESSAGE,
												  null, options, options[0]);
	}
}
