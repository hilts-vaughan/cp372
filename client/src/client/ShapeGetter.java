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
public class ShapeGetter {  
	//Note: Typically the main method will be in a 
	//separate class. As this is a simple one class 
	//example it's all in the one class. 
	public static void main(String[] args) 
	{  
		new ShapeGetter(); 
	}  
	public ShapeGetter() { 
		JFrame guiFrame = new JFrame();  
		//make sure the program exits when the frame closes 
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		guiFrame.setTitle("ShapeGetter"); 
		guiFrame.setSize(300,500);  

		JPanel grid = new JPanel(new FlowLayout());
		
	    //guiFrame.add(grid);
		JLabel ipLbl = new JLabel("IP:"); 
		JLabel portLbl = new JLabel("Port:"); 

		
		JTextField ipText = new JTextField(20);
		JTextField portText = new JTextField(4);
		JTextField shapeText = new JTextField(20);
		
		JButton connect = new JButton("Connect");
		JButton getBut = new JButton( "Get");
		JButton sendBut = new JButton("Send");

		JList displayList = new JList();
		
		guiFrame.setLayout(new GridLayout(0,1));
		
		
		final JPanel ipPanel = new JPanel(); 
		ipPanel.add(ipLbl);
		ipPanel.add(ipText);
//		guiFrame.add(ipLbl);
		guiFrame.add(ipPanel);
		final JPanel portPanel = new JPanel(); 
		portPanel.add(portLbl);
		portPanel.add(portText);
		portPanel.add(connect);
		final JPanel buttonPanel = new JPanel(); 
		buttonPanel.add(portPanel);
		buttonPanel.add(getBut);
		guiFrame.add(buttonPanel);
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
		
		
		/*
		
		JFrame guiFrame = new JFrame();  
		//make sure the program exits when the frame closes 
		guiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		guiFrame.setTitle("ShapeGetter"); 
		guiFrame.setSize(300,500);  
		JPanel grid = new JPanel(new FlowLayout());
		guiFrame.setLayout(new GridLayout(6,3));
	    guiFrame.add(grid);
	    
		
		final JPanel ipPanel = new JPanel(); 
		
		
		JLabel ipLbl = new JLabel("IP:"); 
		JTextField ipText = new JTextField(20);
		ipPanel.add(ipLbl);
		ipPanel.add(ipText);
		
		
		
		final JPanel portPanel = new JPanel(); 
		JLabel portLbl = new JLabel("Port:"); 
		JTextField portText = new JTextField(4);
		JButton connect = new JButton("Connect");
		portPanel.add(portLbl);
		portPanel.add(portText);
		
		portPanel.add(connect);		
		
		
		final JPanel shapePanel = new JPanel(); 
		
		JTextField shapeText = new JTextField(20);
		JButton getBut = new JButton( "Get");
		JButton sendBut = new JButton("Send");
		shapePanel.add(getBut);
		shapePanel.add(sendBut);
		final JPanel inputPanel = new JPanel(); 
		inputPanel.add(shapeText);
		final JPanel display = new JPanel();
		
		
		
		//The ActionListener class is used to handle the 
		//event that happens when the user clicks the button. 
		//As there is not a lot that needs to happen we can  
		//define an anonymous inner class to make the code simpler. 
		
		
		
		//The JFrame uses the BorderLayout layout manager. 
		//Put the two JPanels and JButton in different areas. 
	//	guiFrame.add(ipPanel, BorderLayout.NORTH); 
	//	guiFrame.add(portPanel, BorderLayout.CENTER);
		//centerFrame.add(portPanel, BorderLayout.NORTH);
		//centerFrame.add(shapePanel, BorderLayout.SOUTH);
//		guiFrame.add(display,BorderLayout.SOUTH);  
		//make sure the JFrame is visible 

		grid.add(ipLbl);
		grid.add(ipText);
		
		grid.add(portLbl);
		grid.add(portText);
		grid.add(connect);
		grid.add(getBut);
		grid.add(sendBut);
		grid.add(shapeText);
		guiFrame.setVisible(true); 
		
		/*
		grid.add(ipPanel);
		grid.add(portPanel);
		grid.add(shapePanel);
		grid.add(inputPanel);
		grid.add(display);
		
		*/
		
	}
	  
	}
		
