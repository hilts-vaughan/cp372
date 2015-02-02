package client;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc.. 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class ShapeGetter {
	// Note: Typically the main method will be in a
	// separate class. As this is a simple one class
	// example it's all in the one class.
	private final Semaphore request = new Semaphore(1);

	static Socket shapeConnectionSocket = null;
	static PrintStream os = null;
	static DataInputStream is = null;

	final String ENDLINE = "\r\n";

	final JTextArea getText = new JTextArea(1,20);
	final JTextField ipText = new JTextField(20);
	final JTextField portText = new JTextField(7);
	final JTextArea shapeText = new JTextArea(1,20);
	
	
	@SuppressWarnings("rawtypes")
	private final JList displayList;
	private final ArrayList<Shape> _shapes = new ArrayList<Shape>();

	private final JTextArea _infoArea = new JTextArea();

	JButton connect = new JButton("Connect");
	JButton disconnect = new JButton("Disconnect");
	JButton getBut = new JButton("Get");
	JButton postBut = new JButton("post");
	Thread send = new sendThread();
	Thread get = new getThread();

	public static void main(String[] args) {
		new ShapeGetter();
	}

	public ShapeGetter() {
		
		this.displayList = new JList<Shape>();
		// this._shapes.
		JScrollPane listScrollPane = new JScrollPane(displayList,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		JFrame guiFrame = new JFrame();
		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("ShapeGetter");
		guiFrame.setSize(400, 700);
		getText.setTabSize(2);
		shapeText.setTabSize(2);
		JScrollPane getScrollPane = new JScrollPane(getText,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		getText.setLineWrap(false);
		// guiFrame.add(grid);
		JScrollPane shapeScrollPane = new JScrollPane(shapeText,JScrollPane.VERTICAL_SCROLLBAR_NEVER,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		shapeText.setLineWrap(false);
		
		
		
		JLabel ipLbl = new JLabel("Host:");
		JLabel portLbl = new JLabel("Port:");

		guiFrame.setLayout(new GridLayout(0, 1));

		final JPanel ipPanel = new JPanel();
		ipPanel.add(ipLbl);
		ipPanel.add(ipText);
		guiFrame.add(ipPanel);
		final JPanel portPanel = new JPanel();
		portPanel.add(portLbl);
		portPanel.add(portText);
		portPanel.add(connect);
		portPanel.add(disconnect);
		final JPanel buttonPanel = new JPanel();
		buttonPanel.add(portPanel);

		final JPanel getPanel = new JPanel();

		getPanel.add(getBut);
		getPanel.add(getScrollPane);

		guiFrame.add(buttonPanel);
		guiFrame.add(getPanel);
		final JPanel userInputPanel = new JPanel();
		userInputPanel.add(postBut);
		userInputPanel.add(shapeScrollPane);
		guiFrame.add(userInputPanel);

		this._infoArea.setText("Please select a shape.");
		this._infoArea.setEditable(false);
		this._infoArea.setColumns(5);
		this._infoArea.setBackground(Color.BLACK);
		this._infoArea.setForeground(Color.WHITE);
		this._infoArea.setFont(new Font("monospaced", Font.PLAIN, 12));
		this._infoArea.setLineWrap(true);
		

		guiFrame.add(this._infoArea);

		this.displayList.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = displayList.getSelectedIndex();
				if (index > -1) {
					updateInformation(_shapes.get(index));
				}
			}
		});

		guiFrame.add(listScrollPane);
		guiFrame.setVisible(true);

		// for saving some time
		ipText.setText("localhost");
		portText.setText("5555");

		connect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				try {
					openConnection(ipText, portText);
				} catch (Exception e) {
					displayError("The connection to the server was refused. Are your settings correct?");
				}
			}

		});
		
		disconnect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {

				try {
					closeConnection();
				} catch (Exception e) {
					displayError("The connection to the server was refused. Are your settings correct?");
				}
			}

			

		});

		getBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (os != null) {
					// if(!os.checkError()){
					Thread get = new getThread();
					get.start();
					// }
					// else{
					// System.out.println("there was a problem with sending");
					// }
				} else {
					displayError("Please connect before attempting to use the client.");
				}

			}

		});
		postBut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				if (os != null) {
					// if(!os.checkError()){
					Thread send = new sendThread();
					send.start();
					// }else{
					// System.out.println("there was a problem with sending");
					// }

				} else {
					displayError("Please connect before attempting to use the client.");
				}
			}
		});

	}

	void updateInformation(Shape shape) {
		if (shape == null) {
			this._infoArea.setText("");
		} else {
			this._infoArea.setText(shape.getInfo());
		}
	}

	private void displayError(String reason) {
		JOptionPane.showMessageDialog(null, reason, "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	void openConnection(JTextField ipText2, JTextField portText2)
			throws NumberFormatException, UnknownHostException, IOException {
		shapeConnectionSocket = new Socket(ipText2.getText(),
				Integer.parseInt(portText2.getText()));
		os = new PrintStream(shapeConnectionSocket.getOutputStream());
		is = new DataInputStream(shapeConnectionSocket.getInputStream());
	}
	void closeConnection() 
			throws NumberFormatException, UnknownHostException, IOException {
		if(shapeConnectionSocket!=null){
		shapeConnectionSocket.close();
		os.close();
		is.close();
		shapeConnectionSocket = null;
		is = null;
		os = null;
		}
		
	}

	public class sendThread extends Thread {

		public void run() {
			try {
				request.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
			}
			os.print("POST " + shapeText.getText() + ENDLINE);
			String response = null;
			try {
				response = is.readUTF();

				if (response.indexOf("200") > -1) {
					displayError("This isn't an error; but I thought you might like to know your POST went okay.");
				} else {
					this.doBadRequest(response);
				}

			}

			catch (IOException e) {
			
				//clean up if forced
				shapeConnectionSocket = null;
				is = null;
				os = null;
			}

			request.release();

		}

		private void doBadRequest(String response) {
			final String searchFor = "Reason:";

			int index = response.indexOf(searchFor);
			String reason = response.substring(index + searchFor.length() + 1)
					.trim();

			displayError("Bad Request: " + reason);
		}

	}

	public class getThread extends Thread {

		public void run() {
			try {
				request.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			os.print("GET " + getText.getText() + ENDLINE);
			String response = null;
			try {

				response = is.readUTF();

				// Check out the response
				System.out.println(response);

				// Seek ahead to the data
				if (response.indexOf("400") > -1) {
					doBadRequest(response);
				} else if (response.indexOf("200") > -1) {
					doGET(response);
				} else {
					doOther(response);
				}

			} catch (IOException e) {

				// Clean up if we're forced to

				shapeConnectionSocket = null;
				is = null;
				os = null;

			}
			request.release();
		}

		private void doOther(String response) {
			displayError("The server return an unknown response. Is your client up to date?");
		}

		private void doBadRequest(String response) {
			final String searchFor = "Reason:";

			int index = response.indexOf(searchFor);
			String reason = response.substring(index + searchFor.length() + 1)
					.trim();

			displayError("Bad Request: " + reason);
		}

		private void doGET(String response) {
			response = response.substring(response.indexOf("OK") + 2);

			// Extract out the various different parts
			String[] parts = response.split("&");

			_shapes.clear();
			for (String part : parts) {
				Shape shape = new Shape(part);
				_shapes.add(shape);
			}

			// We need to delegate or we'll be in trouble with cross thread
			// violations
			SwingUtilities.invokeLater(new Runnable() {
				@SuppressWarnings("unchecked")
				@Override
				public void run() {
					displayList.setListData(_shapes.toArray());
					updateInformation(null);
				}
			});
		}
	}

}
