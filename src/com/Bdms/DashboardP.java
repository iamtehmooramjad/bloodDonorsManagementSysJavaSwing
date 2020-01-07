package com.Bdms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;

public class DashboardP extends JPanel {

	private static final long serialVersionUID = 1L;
	//dashboardP (Components used in the dashboardP)
	JPanel[] dMainP=new JPanel[3];
	JPanel dMainEmptyP;
	JLabel[] dMainL=new JLabel[3];
	JLabel[] dMainImg=new JLabel[3];
	JTextField[] dMainT = new JTextField[3];
	Border border;
	String query;
	ResultSet rs;
	Connection con;

	public DashboardP()
	{ 	initialize();	}

	private void initialize() 
	{
		//Dashboard Section Start
		//Setting the border
		border=BorderFactory.createLoweredBevelBorder();
		
		this.setLayout(new BorderLayout());
		
		//Top panel for main label
		JPanel dTopP = new JPanel();
		dTopP.setBackground(new Color(41,47,54)); //RGB Color Values
		this.add(dTopP, BorderLayout.NORTH); //Adding dTopP in the North part of dashboardP
		
		//label to add in the dTopP panel
		JLabel dTopL = new JLabel("Dashboard");
		dTopL.setFont(new Font("Yu Gothic", Font.PLAIN, 24));
		dTopP.setBorder(border);
		dTopP.add(dTopL); //Adding dTopL in the dTopP	
		dTopL.setForeground(Color.WHITE);
		
		//Center panel for content
		JPanel dCenterP=new JPanel();
		dCenterP.setLayout(new GridLayout(5,1,0,0)); //5rows Panel
		dCenterP.setBackground(Color.white);
		this.add(dCenterP, BorderLayout.CENTER);
		
		//When mouse enters the right panel(dashboardP),Queries will be fire to db to get and display results
		dCenterP.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				
				try 
				{
					query="select COUNT(DISTINCT bloodGroup)  AS  AvailableBloods from donor";
					rs=DbConnection.createAndExecuteStatement(query);
				
					rs.next();
					String countBloods=rs.getString("AvailableBloods");
					dMainT[0].setText(countBloods);
					
					query="select COUNT(donorId) As totalDonors from DONOR";
					rs=DbConnection.createAndExecuteStatement(query);
					rs.next();
					String countDonors=rs.getString("totalDonors");
					dMainT[1].setText(countDonors);
					
					query="SELECT COUNT(*) AS totalReq FROM request";
					rs=DbConnection.createAndExecuteStatement(query);
					rs.next();
					String countRequests=rs.getString("totalReq");
					dMainT[2].setText(countRequests);
					
				} 
				catch (SQLException e1) 
				{
					JOptionPane.showMessageDialog(null, "Error connecting to the database");
				}
				catch(Exception e3)
				{
					JOptionPane.showMessageDialog(null, "Error connecting to the database");
				}
				
				
			}
			
		});

		//First panel that will be empty
		dMainEmptyP=new JPanel();
		dMainEmptyP.setBackground(Color.WHITE);
		dCenterP.add(dMainEmptyP);
		
		//Adding next 3 panels that consist of img,label and textField
	
		for(int i=0;i<3;i++)
		{
			dMainP[i] = new JPanel();  //panel
			dMainL[i] = new JLabel();  //label
			dMainImg[i] = new JLabel();  //label for img
			dMainT[i] = new JTextField(); //textfield
		
			dMainL[i].setFont(new Font("Yu Gothic", Font.PLAIN, 18));
			dMainT[i].setFont(new Font("Yu Gothic", Font.PLAIN, 18));
			if(i==0)
			{
				//Image Icon Class Constructor that creates an ImageIcon from the specified file.
				dMainImg[i].setIcon(new ImageIcon(".\\src\\blood.png"));
				dMainL[i].setText("Total Blood Groups Available are");		
			}
			else if(i==1)
			{	
				dMainImg[i].setIcon(new ImageIcon(".\\src\\donor.png"));
				dMainL[i].setText("Blood Donors Available are");			
				
			}
			else if(i==2)
			{
				dMainImg[i].setIcon(new ImageIcon(".\\src\\requests.png"));
				dMainL[i].setText("Total Blood Requests are");				
				
			}
			
			//adding label and img
			dMainP[i].add(dMainImg[i]);
			dMainP[i].add(dMainL[i]);
			
			//adding textfield
			//SwingConstants(Interface) -> A collection of constants generally used for positioning and orienting components on the screen.
			dMainT[i].setHorizontalAlignment(SwingConstants.CENTER);
			dMainT[i].setEditable(false);
			dMainT[i].setColumns(5);
			dMainT[i].setBackground(Color.WHITE);			
			dMainT[i].setBorder(null);
			dMainP[i].add(dMainT[i]);		
			dMainP[i].setBackground(Color.WHITE);
			dCenterP.add(dMainP[i]);
		}
		
	}//initialize method end

}//DashboardP Class End
