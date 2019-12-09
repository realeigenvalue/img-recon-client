package mvc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;

// TODO: Auto-generated Javadoc
/**
 * The Class ClientModel.
 */
public class ClientModel {
	
	/** The server IP. */
	private String serverIP;
	
	/** The server port. */
	private int serverPort;
	
	/** The socket. */
	private Socket socket;
	
	/** The out. */
	private DataOutputStream out;
	
	/** The in. */
	private DataInputStream in;
	
	
	/** The selected file. */
	private File selected_file;
	
	/**
	 * Instantiates a new client model.
	 */
	public ClientModel() {
		this.serverIP = "";
		this.serverPort = -1;
		socket = null;
		out = null;
		in = null;
		selected_file = null;
	}
	
	/**
	 * Ip config.
	 *
	 * @param serverIP the server IP
	 * @param serverPort the server port
	 */
	public void ipConfig(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;
	}
	
	/**
	 * Gets the selected file.
	 *
	 * @return the selected file
	 */
	public File getSelected_file() {
		return selected_file;
	}
	
	/**
	 * Sets the selected file.
	 *
	 * @param selected_file the new selected file
	 */
	public void setSelected_file(File selected_file) {
		this.selected_file = selected_file;
	}
	
	/**
	 * Adds the to database.
	 *
	 * @param name the name
	 * @param imageURL the image URL
	 * @return the string
	 */
	public String ADD_TO_DATABASE(String name, String imageURL) {
		String result = "FAIL";
		getConnection();
		File image = new File(imageURL);
		sendMessageToServer("ADD_TO_DATABASE" + "|" + name + "|" + image.getName());
		sendFileToServer(imageURL);
		result = receiveMessageFromServer();
		closeConnection();
		return result;
	}
	
	/**
	 * Adds the to samples.
	 *
	 * @param name the name
	 * @param imageURL the image URL
	 * @return the string
	 */
	public String ADD_TO_SAMPLES(String name, String imageURL) {
		String result = "FAIL";
		getConnection();
		File image = new File(imageURL);
		sendMessageToServer("ADD_TO_SAMPLES" + "|" + name + "|" + image.getName());
		sendFileToServer(imageURL);
		result = receiveMessageFromServer();
		closeConnection();
		return result;
	}
	
	/**
	 * Removes the from database.
	 *
	 * @param name the name
	 * @return the string
	 */
	public String REMOVE_FROM_DATABASE(String name) {
		String result = "FAIL";
		getConnection();
		sendMessageToServer("REMOVE_FROM_DATABASE" + "|" + name);
		result = receiveMessageFromServer();
		closeConnection();
		return result;
	}
	
	/**
	 * Recognize.
	 *
	 * @param imageURL the image URL
	 * @return the string
	 */
	public String RECOGNIZE(String imageURL) {
		String result = "FAIL";
		getConnection();
		File image = new File(imageURL);
		sendMessageToServer("RECOGNIZE" + "|" + image.getName());
		sendFileToServer(imageURL);
		result = receiveMessageFromServer();
		closeConnection();
		return result;
	}
	
	/**
	 * Exists.
	 *
	 * @param ipAddress the ip address
	 * @param portNumber the port number
	 * @return true, if successful
	 */
	public static boolean exists(String ipAddress, int portNumber) {
		Socket test = null;
		boolean ret = false;
		try {
			test = new Socket(ipAddress, portNumber);
			ret = true;
		} catch(Exception e) {
			ret = false;
		}
		if(test != null) {
			try {
				test.close();
			} catch (Exception e) {}
		}
		return ret;
	}
	
	/**
	 * Send message to server.
	 *
	 * @param message the message
	 */
	public void sendMessageToServer(String message) {
		try {
			out.writeInt(message.length());
			out.writeBytes(message);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	/**
	 * Send file to server.
	 *
	 * @param URL the url
	 */
	public void sendFileToServer(String URL) {
		File file = new File(URL);
		try {
			byte[] fileBytes = Files.readAllBytes(file.toPath());
			out.writeInt(fileBytes.length);
			out.write(fileBytes);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}
	
	/**
	 * Receive message from server.
	 *
	 * @return the string
	 */
	public String receiveMessageFromServer() {
		String result = null;
		byte[] bytes = receiveBytesFromServer();
		if(bytes != null) {
			result = new String(bytes);
		}
		return result;
	}
	
	/**
	 * Receive bytes from server.
	 *
	 * @return the byte[]
	 */
	public byte[] receiveBytesFromServer() {
		byte[] bytes = null;
		try {
			int length = in.readInt();
			bytes = new byte[length];
			int bytesRead = 0;
			while(bytesRead < length) {
				bytes[bytesRead++] = in.readByte();
			}
			return bytes;
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
		return bytes;
	}
	
	/**
	 * Gets the connection.
	 *
	 * @return the connection
	 */
	public void getConnection() {
		try {
			socket = new Socket(serverIP, serverPort);
			out = new DataOutputStream(socket.getOutputStream());
			in = new DataInputStream(socket.getInputStream());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
	
	/**
	 * Close connection.
	 */
	public void closeConnection() {
		try {
			socket.close();
			out.close();
			in.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
	}
}
