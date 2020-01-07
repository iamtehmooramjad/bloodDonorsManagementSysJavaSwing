package com.Bdms;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;

public class Splash extends JFrame implements Runnable
{
	private static final long serialVersionUID = 1L;
	public static JProgressBar progressBar;
	public static JLabel label2;
	//Constructor
	public Splash()
	{	initialize();	}
	
	//Method
	public void initialize()
	{
		//Panel with Borderlayout.center
		JPanel panel = new JPanel();
		panel.setBackground(new Color(8,65,92));
		this.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		//Top Label
		JLabel bdmsL = new JLabel("Blood Donors Management System");
		bdmsL.setFont(new Font("Yu Gothic", Font.BOLD, 18));
		bdmsL.setForeground(Color.WHITE);
		bdmsL.setBounds(85, 100, 334, 33);
		panel.add(bdmsL);
		
		//Blood Image 
		JLabel imgL = new JLabel("");
		imgL.setIcon(new ImageIcon("C:\\Users\\Tehmoor Amjad\\Downloads\\blood50.png"));
		imgL.setBounds(217, 30, 59, 72);
		panel.add(imgL);
		
		//Sub Label
		JLabel bdmsL2 = new JLabel("<html><h4>Donate blood, Save life</h4></html>");
		bdmsL2.setFont(new Font("Yu Gothic", Font.PLAIN, 12));
		bdmsL2.setForeground(Color.WHITE);
		bdmsL2.setBounds(325, 135, 147, 25);
		panel.add(bdmsL2);
		
		//Developed By Label
		JLabel bdmsL3 = new JLabel("<html>Developed by<h4>Muhammad Ayaz, Zeeshan Tanveer & Tehmoor Amjad</h4></html>");
		bdmsL3.setForeground(Color.WHITE);
		bdmsL3.setFont(new Font("Yu Gothic", Font.BOLD, 14));
		bdmsL3.setBounds(85, 206, 393, 56);
		panel.add(bdmsL3);
		
		//Creating progress bar that will execute according to the static JLabel
		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setBackground(Color.white);
		progressBar.setForeground(Color.red);
		progressBar.setBounds(85, 270, 334, 14);
		panel.add(progressBar);
		
		//label for displaying the % of completion of Loading 
		label2 = new JLabel();
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Yu Gothic", Font.BOLD, 12));
		label2.setBounds(85, 275, 393, 56);
		panel.add(label2);
		
	}
	//Run Method
	@Override
	public void run() {
	
		//Setting the Splash Screen
		this.setUndecorated(true);
		this.setLocation(400, 100);
		this.setSize(500,350);
		this.setVisible(true);	
		try 
		{
			for(int i=0;i<100;i++)
			{
				//Thread to Sleep 
				Thread.sleep(35);
				//Progress bar values display
				Splash.progressBar.setValue(i);
				
				if(i>20 && i<40)
					Splash.label2.setText("Connecting to Database");					
				else if(i>45 && i<70)
					Splash.label2.setText("Loading Data from database");
				else if(i>70)
					Splash.label2.setText("Loading all liberaries");		
			}
		}
		catch(Exception e)
		{			JOptionPane.showConfirmDialog(null, e);		}
		
		//Hide the splash screen
		this.setVisible(false);
		  
		//Creates the user object to login or sign up
		  User u=new User();
		  u.setSize(500,350); 
		  u.setLocationRelativeTo(null);
		  u.setVisible(true);
	}
}
