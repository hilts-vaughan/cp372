package client;
//Imports are listed in full to show what's being used
//could just import javax.swing.* and java.awt.* etc.. 
import javax.swing.JFrame; 
import javax.swing.JPanel; 
import javax.swing.JComboBox; 
import javax.swing.JButton; 
import javax.swing.JLabel; 
import javax.swing.JList; 
import javax.swing.JTextField;
import javax.swing.SwingWorker;

import java.awt.BorderLayout; 
import java.awt.Component;
import java.awt.FlowLayout;
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
import java.util.concurrent.Semaphore;

public class ShapeGetter {  
	//Note: Typically the main method will be in a 
	//separate class. As this is a simple one class 
	//example it's all in the one class. 
	private final Semaphore request = new Semaphore(1);
    
    static Socket shapeConnectionSocket = null;
	static PrintStream os = null;
	static DataInputStream is = null;
	
	final String ENDLINE = "\r\n";
	
    final JTextField getText = new JTextField(20);
	final JTextField ipText = new JTextField(20);
	final JTextField portText = new JTextField(4);
	final JTextField shapeText = new JTextField(20);
	final JList<String> displayList = new JList();
	JButton connect = new JButton("Connect");
	JButton getBut = new JButton( "Get");
	JButton sendBut = new JButton("Send");
	Thread send=new sendThread();
	Thread get =new getThread();
	
	public static void main(String[] args) 
	{  
		new ShapeGetter(); 
	}  
	

	
	public ShapeGetter(){ 
		
		JFrame guiFrame = new JFrame();  
		//make sure the program exits when the frame closes 
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		guiFrame.setTitle("ShapeGetter"); 
		guiFrame.setSize(300,500);  

		JPanel grid = new JPanel(new FlowLayout());
		
	    //guiFrame.add(grid);
		JLabel ipLbl = new JLabel("Host:"); 
		JLabel portLbl = new JLabel("Port:"); 

		

		
		guiFrame.setLayout(new GridLayout(0,1));
		
		
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
		
		guiFrame.add(displayList);
		guiFrame.setVisible(true); 

//for saving some time
		ipText.setText("localhost");
		portText.setText("5555");
		
		
		
		connect.addActionListener(new ActionListener()		
		{ 
			@Override public void actionPerformed(ActionEvent event) 
			{ 
			    
			try {
				openConnection(ipText, portText);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			} 
			
		}
		);
		
		
		getBut.addActionListener(new ActionListener() 
		{ 
			@Override public void actionPerformed(ActionEvent event) 
			{ 	
				if(os!=null){
					//if(!os.checkError()){
					Thread get = new getThread();
					get.start();
					//}
					//else{
				//		System.out.println("there was a problem with sending");
			//		}
				}else{
					System.out.println("You forgot to connect");
				}
						
			} 
			
		}
		);
		sendBut.addActionListener(new ActionListener() 
		{ 
			@Override public void actionPerformed(ActionEvent event) 
			{ 
			if(os!=null){
				//if(!os.checkError()){
				Thread send = new sendThread();
				send.start();
			//	}else{
				//	System.out.println("there was a problem with sending");
			//	}
				
			}	
			else{
				System.out.println("You forgot to connect");
			}
			} 
			}
		);
		
		
				
		
		
		
	}
	void openConnection(JTextField ipText, JTextField portText) throws NumberFormatException, UnknownHostException, IOException {
		System.out.println(ipText.getText());
		System.out.println(Integer.parseInt(portText.getText()));
		shapeConnectionSocket = new Socket(ipText.getText(), Integer.parseInt(portText.getText()));
		os = new PrintStream(shapeConnectionSocket.getOutputStream());
		is = new DataInputStream(shapeConnectionSocket.getInputStream());  
	}
	
	  public class sendThread extends Thread {

		    public void run(){
		    	try {
					request.acquire();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					System.out.println("oops");
				}
		    	os.print("POST "+ shapeText.getText() +ENDLINE);
		    	String response = null;
				try {
					response = is.readUTF();
					System.out.println(response);
				} catch (IOException e) {
					System.out.println("Opps");
					shapeConnectionSocket=null;
					is=null;
					os=null;
				}
				
				request.release();
				is=null;
				os=null;
		    }
		  }
		 
	  public class getThread extends Thread {

		    public void run(){
		    	try {
					request.acquire();
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		    	os.print("GET "+ getText.getText() +ENDLINE);
		    	String response = null;
				try {
					response = is.readUTF()+"ok";
					System.out.println(response);
					String[] parts = response.split("&");
					if(parts.length>0 && parts[0].contains("200 ok"))
					{
						parts[0]= (String) parts[0].subSequence(7, parts[0].length());
					//	ArrayList<Object> shapesArray = new ArrayList<Object>();
					  	//for(int i=0; i<parts.length; i++){
					//		shapesArray.add(parts[i]);
					//	}
					//	shapesArray.add("ok");
						
					//	displayList.setListData((String[]) shapesArray.toArray());
						parts[0]="WORK";
						String[] because = {"hello","do", "you", "work"};
						displayList.setListData(because);
					}	
			    } catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Well");
					shapeConnectionSocket=null;
					is=null;
					os=null;
				
			}
				request.release();
		  }
	  }
			 
	
	}



		
