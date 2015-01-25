package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JTextField;





public class HttpRequest extends JFrame {

	static Socket shapeConnectionSocket = null;
	static DataOutputStream os = null;
	static DataInputStream is = null; 
	public HttpRequest() throws SocketException {
	    // Initialize the data member with the open socket.
		  
		  /*
		  try {
		    	shapeConnectionSocket = new Socket(ipText.getText(), Integer.parseInt(portText.getText()));
	            os = new DataOutputStream(shapeConnectionSocket.getOutputStream());
	            is = new DataInputStream(shapeConnectionSocket.getInputStream());   
		    }
		    catch (IOException e) {
		        System.out.println(e);
		    }
		    */
	  }
	  void openConnection(JTextField ipText, JTextField portText) {
		 try {
		    this.shapeConnectionSocket = new Socket(ipText.getText(), Integer.parseInt(portText.getText()));
		    DataOutputStream os = new DataOutputStream(shapeConnectionSocket.getOutputStream());
		    DataInputStream is = new DataInputStream(shapeConnectionSocket.getInputStream());   
		 }
		 catch (IOException e) {
		        System.out.println(e);
		    }
	}
	  void closeConnection() {
			 try {
			    shapeConnectionSocket.close();
			    os.close();
			    is.close();
			 }
			 catch (IOException e) {
			        System.out.println(e);
			    }
		}
	public void writing(JTextField getText) {
		// TODO Auto-generated method stub
		try {
			os.writeChars("GET"+getText.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void posting(JTextField getText) {
		try {
			os.writeChars("POST"+getText.getText());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}