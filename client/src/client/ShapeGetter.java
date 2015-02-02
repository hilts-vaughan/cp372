package client;

//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc.. 
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

	final JTextField getText = new JTextField(20);
	final JTextField ipText = new JTextField(20);
	final JTextField portText = new JTextField(4);
	final JTextField shapeText = new JTextField(20);

	private final JList displayList;
	private final ArrayList<Shape> _shapes = new ArrayList<Shape>();

	private final JTextArea _infoArea = new JTextArea();

	JButton connect = new JButton("Connect");
	JButton getBut = new JButton("Get");
	JButton sendBut = new JButton("Send");
	Thread send = new sendThread();
	Thread get = new getThread();

	public static void main(String[] args) {
		new ShapeGetter();
	}

	public ShapeGetter() {

		this.displayList = new JList<Shape>();
		// this._shapes.

		JFrame guiFrame = new JFrame();
		// make sure the program exits when the frame closes
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		guiFrame.setTitle("ShapeGetter");
		guiFrame.setSize(400, 700);

		JPanel grid = new JPanel(new FlowLayout());

		// guiFrame.add(grid);
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
		final JPanel buttonPanel = new JPanel();
		buttonPanel.add(portPanel);

		final JPanel getPanel = new JPanel();

		getPanel.add(getBut);
		getPanel.add(getText);

		guiFrame.add(buttonPanel);
		guiFrame.add(getPanel);
		final JPanel userInputPanel = new JPanel();
		userInputPanel.add(sendBut);
		userInputPanel.add(shapeText);
		guiFrame.add(userInputPanel);
		final JPanel display = new JPanel();

		this._infoArea
				.setText("Please select a shape.");
		this._infoArea.setEditable(false);
		this._infoArea.setColumns(5);
		this._infoArea.setBackground(Color.BLACK);
		this._infoArea.setForeground(Color.WHITE);
        this._infoArea.setFont(new Font("monospaced", Font.PLAIN, 12));


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

		guiFrame.add(displayList);
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
		sendBut.addActionListener(new ActionListener() {
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
		JOptionPane.showMessageDialog (null, reason, "Error", JOptionPane.ERROR_MESSAGE);
	}

	void openConnection(JTextField ipText, JTextField portText)
			throws NumberFormatException, UnknownHostException, IOException {
		System.out.println(ipText.getText());
		System.out.println(Integer.parseInt(portText.getText()));
		shapeConnectionSocket = new Socket(ipText.getText(),
				Integer.parseInt(portText.getText()));
		os = new PrintStream(shapeConnectionSocket.getOutputStream());
		is = new DataInputStream(shapeConnectionSocket.getInputStream());
	}

	public class sendThread extends Thread {

		public void run() {
			try {
				request.acquire();
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				System.out.println("oops");
			}
			os.print("POST " + shapeText.getText() + ENDLINE);
			String response = null;
			try {
				response = is.readUTF();
				System.out.println(response);
			} catch (IOException e) {
				System.out.println("Opps");
				shapeConnectionSocket = null;
				is = null;
				os = null;
			}

			request.release();
			is = null;
			os = null;
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
				System.out.println(response);

				// Seek ahead to the data
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
					@Override
					public void run() {
						displayList.setListData(_shapes.toArray());
						updateInformation(null);
					}
				});

			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Well");
				shapeConnectionSocket = null;
				is = null;
				os = null;

			}
			request.release();
		}
	}

}
