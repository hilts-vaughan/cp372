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

import java.awt.BorderLayout; 
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;  
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
public class ShapeGetter {  
	//Note: Typically the main method will be in a 
	//separate class. As this is a simple one class 
	//example it's all in the one class. 
	
	Socket shapeConnectionSocket=null;
	DataOutputStream os = null;
    DataInputStream is = null;
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

		final JTextField getText = new JTextField(20);
		final JTextField ipText = new JTextField(20);
		final JTextField portText = new JTextField(4);
		final JTextField shapeText = new JTextField(20);
		
		JButton connect = new JButton("Connect");
		JButton getBut = new JButton( "Get");
		JButton sendBut = new JButton("Send");

		JList displayList = new JList();
		
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

		
		
		
		
		connect.addActionListener(new ActionListener()		
		{ 
			@Override public void actionPerformed(ActionEvent event) 
			{ 
			    
			
			} 
			
		}
		);
		
		
		getBut.addActionListener(new ActionListener() 
		{ 
			@Override public void actionPerformed(ActionEvent event) 
			{ 
				try {
					os.writeChars("GET");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			
		}
		);
		sendBut.addActionListener(new ActionListener() 
		{ 
			@Override public void actionPerformed(ActionEvent event) 
			{ 
				//When the fruit of veg button is pressed 
				//the setVisible value of the listPanel and 
				//comboPanel is switched from true to  
				//value or vice versa. 
		//		listPanel.setVisible(!listPanel.isVisible()); 
		//		comboPanel.setVisible(!comboPanel.isVisible()); 
				} 
			}
		);
		
				
		
		
		
	}
	
	
	  
	}



		
