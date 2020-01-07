package com.Bdms;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

public class Frame extends JFrame
{
	private static final long serialVersionUID = 1L;
	//Objects 
	DashboardP dObj;	
	AddRequestP addReqObj;
	RequestsP reqObj;
	AddDonorP addDonorObj;
	DeleteDonorP delDonorObj;
	SearchDonorP searchDonorObj;
	UpdateDonorP updateDonorObj;
	
	//Arrays of day, month, year && bloodGroups
	String[] day;
	String[] month;
	String[] year;
	String[] bloodGroups;
	
	//Border Object to style the borders of components
	Border border;
	
	//Layout of this frame is BorderLayout.In which three parts are used:
	//1st is North  (Top Panel),2nd is West   (Left Panel),3rd is Center (Right/Center Panel)
	
	//JFrame Right Panel(Center)
	public JPanel rightPanel;    //Layout of rightPanel is (Card Layout).
	
	
	//The following 7 panels are added in the rightPanel
	//The Panels that are added in the rightPanel(BorderLayout.CENTER)
	//Frame Top Panel(North)
	private JPanel topPanel;
		
	//Frame Left Panel(West)
	private JPanel leftPanel;
	
	//Labels added into the left Panel(BorderLayout.West of JFrame)
	private JLabel [] leftPanelL = new JLabel[7];  			
	
	//Constructor
	public Frame()
	{		initialize();		}

	//Method
	public void initialize()
	{
		//Arrays of day, month, year & blood Groups
		day= new String[]{"Day","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		month= new String[]{"Month","01","02","03","04","05","06","07","08","09","10","11","12"};
		year= new String[]{"Year","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2025","2026","2027","2028","2029","2030"};
		bloodGroups=new String[] {"Select Below","A+","A-","B+","B-","O+","O-","AB+","AB-"};
		
		//Calling updateAllStatus Stored Procedure
		DbConnection.updateAllStatus();
		
		//Frame Start
		border=BorderFactory.createEtchedBorder();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new BorderLayout());
		
		//Top Panel
		topPanel = new JPanel();
		this.getContentPane().add(topPanel, BorderLayout.NORTH);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER,0,20));
		
		//Top Label added into Top Panel
		JLabel bdmsL = new JLabel("Blood Donors Management System");
		bdmsL.setFont(new Font("Yu Gothic",Font.BOLD, 20));
		bdmsL.setForeground(Color.WHITE);
		topPanel.add(bdmsL);
		topPanel.setBorder(border);
		
		//topPanel.setBackground(new Color(50,140,193));
		topPanel.setBackground(new Color(8,65,92)); //Red,Green,Blue(RGB)
		
		//left Panel
		leftPanel = new JPanel();
		this.getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setBackground(new Color(245,50,64));
		leftPanel.setLayout(new GridLayout(7, 1));			//Setting 7 rows and 1 column of Grid Layout
		border=BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);		
		//Adding labels in the left panel and adding mouselisteners to display relative panels in the right panel
		for(int i=0;i<7;i++)
		{
			leftPanelL[i]=new JLabel();  //Creating Label
			leftPanelL[i].setFont(new Font("Yu Gothic",Font.BOLD,14));
			leftPanelL[i].setForeground(new Color(234,227,234));
			int count=i;
			leftPanelL[i].addMouseListener(new MouseAdapter() //MouseAdapter is an abstract Class that implements mouseListener
			{	@Override
				public void mouseClicked(MouseEvent e) 
				{
					//Removes everyting from righpanel
					rightPanel.removeAll(); //Method of Container Class
					//adding relative panel upon label clicked
					if(count==0)
					{	rightPanel.add(dObj);	  }
					else if(count==1)
					{	rightPanel.add(reqObj);	  }
					else if(count==2)
					{	rightPanel.add(addReqObj);}
					else if(count==3)
					{	rightPanel.add(addDonorObj);}
					else if(count==4)
					{	rightPanel.add(delDonorObj);}
					else if(count==5)
					{   rightPanel.add(searchDonorObj);}
					else if(count==6)
					{	rightPanel.add(updateDonorObj);}
					
					rightPanel.repaint();    //Paint the Container
					rightPanel.revalidate(); //Place the components on Panel
}});
			
			leftPanelL[i].setBorder(border);	//Setting The Border
			leftPanel.add(leftPanelL[i]);       //Adding Panels in the left Panel
		
		//Giving names to the Panels added in the left Panel
		if(i==0)
		{	leftPanelL[i].setText("          Dashboard          "); 	}
		else if(i==1)
		{	leftPanelL[i].setText("          Requests          ");		}
		else if(i==2)
		{	leftPanelL[i].setText("          Add Request          ");	}
		else if(i==3)
		{	leftPanelL[i].setText("          Add Donor          ");		}
		else if(i==4)
		{	leftPanelL[i].setText("          Delete Donor          ");	}
		else if(i==5)
		{	leftPanelL[i].setText("          Search Donor          ");	}
		else if(i==6)
		{	leftPanelL[i].setText("          Update Donor          ");	}
}			
		///Right Panel Start
		rightPanel = new JPanel();
		this.getContentPane().add(rightPanel, BorderLayout.CENTER);
		rightPanel.setLayout(new CardLayout()); 
		
		//Adding Objects of Panels in the Right Panel
		dObj=new DashboardP();
		rightPanel.add(dObj);
		
		addReqObj=new AddRequestP();
		rightPanel.add(addReqObj);
		
		reqObj=new RequestsP();
		rightPanel.add(reqObj);
		
		addDonorObj=new AddDonorP();
		rightPanel.add(addDonorObj);
		
		delDonorObj=new DeleteDonorP();
		rightPanel.add(delDonorObj);
		
		searchDonorObj=new SearchDonorP();
		rightPanel.add(searchDonorObj);
		
		updateDonorObj=new UpdateDonorP();
		rightPanel.add(updateDonorObj);
	}	
}
