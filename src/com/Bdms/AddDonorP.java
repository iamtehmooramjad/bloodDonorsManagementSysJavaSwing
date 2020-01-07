package com.Bdms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class AddDonorP extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
		//Arrays of day, month, year && bloodGroups
		String[] day;
		String[] month;
		String[] year;
		String[] bloodGroups;
		//Add Donor Panel Components	
		JLabel [] addDonorPL=new JLabel[9];
		JPanel [] addDonorDateP=new JPanel[2];
		JTextField[] addDonorT=new JTextField[6];
		JComboBox<String>[] addDonorC=new JComboBox[7];
		JButton[] addDonorB=new JButton[2];
		GridBagConstraints gbcAddDonor;
		JLabel addDonorTopL;
		JPanel addDonCenP;

		public AddDonorP()
		{
			initialize();
		}
		private void initialize()
		{
			//Arrays of day, month, year & blood Groups
			day= new String[]{"Day","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
			month= new String[]{"Month","01","02","03","04","05","06","07","08","09","10","11","12"};
			year= new String[]{"Year","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021","2022","2025","2026","2027","2028","2029","2030"};
			bloodGroups=new String[] {"Select Below","A+","A-","B+","B-","O+","O-","AB+","AB-"};
			
			//Add Donor Panel to add donor records in the database.			 
			this.addMouseListener(new MouseAdapter()
			{
				//Getting auto generated donorId from db
				public void mouseEntered(MouseEvent e)
				{		
					try 
					{
						String query="SELECT donorId FROM donor\r\n" + 
								"ORDER BY donorId DESC\r\n" + 
								"LIMIT 1";
						
						ResultSet rs=DbConnection.createAndExecuteStatement(query);
						String getDonorId = "0";					
						
						if(rs.next())
						{	getDonorId=Integer.toString(Integer.parseInt(rs.getString("donorId"))+1);	}
						else
						{	getDonorId=Integer.toString(Integer.parseInt(getDonorId)+1);	}
					
						addDonorT[2].setText(getDonorId);
					} 
					catch (Exception e1) 
					{
						JOptionPane.showMessageDialog(null, e1);
					}
				}
			});
			
			//Setting the layout of addDonorP
			this.setLayout(new BorderLayout());	
			
			//addDonorTopP to add in the north part for labels
			JPanel addDonorTopP = new JPanel();
			addDonorTopP.setBackground(new Color(41,47,54));
			this.add(addDonorTopP, BorderLayout.NORTH);
			
			//TOP label to add in the top panel 
			addDonorTopL = new JLabel("Add Blood Donor");
			addDonorTopL.setFont(new Font("Yu Gothic", Font.PLAIN, 24));
			addDonorTopL.setForeground(Color.WHITE);
			addDonorTopP.add(addDonorTopL);
			
			//add Donor Center Panel for form
			addDonCenP = new JPanel();
			addDonCenP.setBackground(Color.WHITE);
			this.add(addDonCenP, BorderLayout.CENTER);

			//Layout of addDonorCenP is GridBagLayout
			addDonCenP.setLayout(new GridBagLayout());

			
			int gx=0;
			int gy=0;
			int countC=-1;
			int countP=-1;
			
			//GridBaghConstraint object  for add donors 
			gbcAddDonor=new GridBagConstraints();

			for(int i=0; i<9;i++)
			{
				//setting internal padding to 0
				gbcAddDonor.ipadx=0;
				gbcAddDonor.ipady=0;
				
				
				if(i==0 || i==2 || i==4 || i==6 || i==7 || i==8)
					gx=0;  //starts  placing components from beginning
				else
					gx=2; //starts  placing components from position 2
				
				if(i==2 || i==4 || i==6 || i==7 || i==8)
					gy++;  //increment in y
				
				//Adding Labels
				addDonorPL[i]=new JLabel();
				addDonorPL[i].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
				gbcAddDonor.anchor=GridBagConstraints.EAST;
				gbcAddDonor.insets= new Insets(20,20,20,20);
				gbcAddDonor.gridx=gx;
				gbcAddDonor.gridy=gy;
				addDonCenP.add(addDonorPL[i],gbcAddDonor);
				
				//For TextFields and JComboBox
				gbcAddDonor.gridx=gx+1;          //gx=0-> gx+1=1
				gbcAddDonor.anchor=GridBagConstraints.WEST;
				gbcAddDonor.ipadx=60;
				gbcAddDonor.ipady=10;
				if(i<6)
				{		
					//Creating TextFields 
					addDonorT[i]=new JTextField();
					if(i==2)
					{			addDonorT[i].setEditable(false);				}
					addDonorT[i].setColumns(10);
					addDonorT[i].setBackground(Color.WHITE);
					addDonCenP.add(addDonorT[i],gbcAddDonor);
					addDonorT[i].addActionListener(this);
				}
				else if(i==6)
				{
					countC++;
					gbcAddDonor.ipadx=0;
					gbcAddDonor.ipady=0;
					
					//Creating and adding ComboBoxes 
					addDonorC[countC]=new JComboBox<String>(bloodGroups);
					addDonorC[countC].setFont(new Font("Yu Gothic",Font.PLAIN,12));
					addDonorC[countC].setBackground(Color.WHITE);
					addDonorC[countC].setForeground(Color.BLACK);
					addDonCenP.add(addDonorC[countC],gbcAddDonor);
					addDonorC[countC].addActionListener(this);
				}
				else if(i==7 || i==8)
				{
					gbcAddDonor.ipadx=0;
					gbcAddDonor.ipady=0;
					countP++;
					//adding panels for date comboboxes
					addDonorDateP[countP]=new JPanel();
					addDonorDateP[countP].setLayout(new GridLayout(0, 3, 10, 0));//1 row ,3 columns and hGap=10
					addDonorDateP[countP].setBackground(Color.WHITE);
					addDonCenP.add(addDonorDateP[countP],gbcAddDonor);
					
					for(int k=0;k<3;k++)
					{
						//adding comboboxes
						countC++;
						
						if(k==0)
							addDonorC[countC]=new JComboBox<String>(day);
						else if(k==1)
							addDonorC[countC]=new JComboBox<String>(month);
						else
							addDonorC[countC]=new JComboBox<String>(year);
						
						addDonorC[countC].setFont(new Font("Yu Gothic",Font.PLAIN,12));
						addDonorC[countC].setBackground(Color.WHITE);
						addDonorC[countC].setForeground(Color.BLACK);
						addDonorDateP[countP].add(addDonorC[countC]);
						addDonorC[countC].addActionListener(this); //add ActionListener for JComboBox
					}
				}
				//Setting the labels according to loop
				if(i==0)
				{				addDonorPL[i].setText("Enter First name");				}
				else if(i==1)
				{				addDonorPL[i].setText("Enter Last name");				}
				else if(i==2)
				{				addDonorPL[i].setText("Donor Id");						}
				else if(i==3)
				{				addDonorPL[i].setText("Enter City");						}
				else if(i==4)
				{				addDonorPL[i].setText("<html>Enter Cnic number<br><h4>( Without - )</h4></html>");				}
				else if(i==5)
				{				addDonorPL[i].setText("<html>Enter Phone number<br> <h4>( Begin with 03 )</h4></html>)");				}
				else if(i==6)
				{				addDonorPL[i].setText("Select Blood Group");				}
				else if(i==7)
				{				addDonorPL[i].setText("Select Date of Birth");			}
				else if(i==8)
				{				addDonorPL[i].setText("<html>Select Last Donation Date<br><h4>(Optional)</h4></html>");		}
						
				//For Buttons
				if (i==8)
				{
					gbcAddDonor.ipadx=0;
					gbcAddDonor.ipady=0;
					for(int j=0;j<2;j++)
					{
						//Creating and adding buttons in panel
						addDonorB[j]=new JButton();
						addDonorB[j].setBackground(new Color(8,65,92));
						addDonorB[j].setForeground(Color.WHITE);

						addDonorB[j].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
						gbcAddDonor.insets = new Insets(20,20,20,20);
						gbcAddDonor.anchor=GridBagConstraints.EAST;
						if(j==0)
						{
							//gx=0
							gx=gx+2;
							gbcAddDonor.gridx=gx;
							addDonorB[j].setText("Add");
						}
							
						else
						{
							gbcAddDonor.anchor=GridBagConstraints.WEST;
							gbcAddDonor.gridx=gx+1;
							addDonorB[j].setText("Cancel");						
						}
						addDonCenP.add(addDonorB[j], gbcAddDonor);
						addDonorB[j].addActionListener(this);   //Adding ActionListener
					}
					
				}
						
			}	
}//AddDonorP initialize End


	//AddDonorP ActionListener Start
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		Donor d=new Donor();			//Donor Object "d"
		String query;
	
		if(e.getSource()==addDonorB[0]) //addDonor "Add Button"
		{
			PreparedStatement preSt=null;
			String fName ="",lName="",city="",cnic="",phoneNum="",bloodGroup = "",dob="",lastDonationD="";
			
			query="INSERT INTO donor(fName,lName,city,cnic,phoneNum,bloodGroup,dob,lastDonationDate) VALUES(?,?,?,?,?,?,?,?);";
			try 
			{
				//Prepared Statement
				preSt=DbConnection.createPreparedStatement(query);
			}  
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null, e1);
			}
			
			//first name
			boolean res=d.checkName(addDonorT[0].getText(), addDonorT[0]);//this
			if(res==true)
			{	fName=addDonorT[0].getText().toLowerCase();	}

			//last name
			boolean res1=d.checkName(addDonorT[1].getText(), addDonorT[1]); //this
			if(res1==true)
			{	lName=addDonorT[1].getText().toLowerCase();	}

			//City
			boolean res2=d.checkName(addDonorT[3].getText(), addDonorT[3]);//this
			if(res2==true)
			{	city=addDonorT[3].getText().toLowerCase();	}

			//CNIC
			boolean res3=d.checkNum(addDonorT[4].getText(), addDonorT[4],13); //this
			if(res3==true)
			{	cnic=addDonorT[4].getText().toLowerCase();	}

			//Phone Number
			boolean res4=d.checkNum(addDonorT[5].getText(), addDonorT[5], 11);//this
			if(res4==true)
			{	phoneNum=addDonorT[5].getText().toLowerCase();}

			//BloodGroups
			boolean res5 = d.checkComboBox(addDonorC[0]);
			if(res5==true)
				bloodGroup=addDonorC[0].getSelectedItem().toString();
			
			//Date of Birth and Last Donation Date
			int countC=4; //for addDonorC[countC>0]
			boolean [] result = new boolean[3];
			for(int i=2;i>=0;i--)
			{
				countC=countC-1;
				result[i]=d.checkComboBox(addDonorC[countC]);
				if(result[i]==true)
				{
					//dob[i]=addDonorC[countC].getSelectedItem().toString();
					if(i==2)
						dob=dob+addDonorC[countC].getSelectedItem().toString();
					else if(i!=2)
						dob=dob+"-"+addDonorC[countC].getSelectedItem().toString();
				}
			}

			//Executing Query
			if(res==true && res1==true && res2==true && res3==true && res4==true && res5==true && result[0] && result[1] && result[2])
			{
				boolean [] result2=new boolean[3];
				
				if(addDonorC[6].getSelectedIndex()!=0 && addDonorC[5].getSelectedIndex()!=0 && addDonorC[4].getSelectedIndex()!=0)
				{
					countC=7;
					for(int i=2;i>=0;i--)
					{
						countC=countC-1;
						result2[i]=d.checkComboBox(addDonorC[countC]);
						if(result2[i]==true)
						{
							//dob[i]=addDonorC[countC].getSelectedItem().toString();
							if(i==2)
								lastDonationD=lastDonationD+addDonorC[countC].getSelectedItem().toString();
							else if(i!=2)
								lastDonationD=lastDonationD+"-"+addDonorC[countC].getSelectedItem().toString();
						}
					}

				}
				else if(addDonorC[6].getSelectedIndex()==0 && addDonorC[5].getSelectedIndex()==0 && addDonorC[4].getSelectedIndex()==0)
				{	lastDonationD="2018-01-01";	}
					
				try 
				{
					//Setting values to the Attributes of donor Table
					preSt.setString(1, fName);
					preSt.setString(2, lName);
					preSt.setString(3, city);
					preSt.setString(4, cnic);
					preSt.setString(5, phoneNum);
					preSt.setString(6, bloodGroup);
					preSt.setString(7, dob);
					preSt.setString(8, lastDonationD);
					int count =DbConnection.executeUpdate(preSt);
					
					//show how many rows are affected
					JOptionPane.showMessageDialog(null, count+" record saved successfully");	
					DbConnection.close(preSt);
					//Setting all fields to empty
					for(int i=0;i<6;i++)
					{
						addDonorT[i].setText("");
						addDonorC[i].setSelectedIndex(0);
						if(i==5)
							addDonorC[i+1].setSelectedIndex(0);
					}
					
				}
				catch(Exception e3)
				{	JOptionPane.showMessageDialog(null, e3);	}
			}
			else 
			{	JOptionPane.showMessageDialog(null, "Please enter correct data");	}
		}
		else if(e.getSource()==addDonorB[1]) //addDonor "Cancel button"
		{
			for(int i=0;i<6;i++)
			{
				addDonorT[i].setText("");
				addDonorT[i].setBackground(Color.WHITE);
				addDonorC[i].setSelectedIndex(0);
				addDonorC[i].setBackground(Color.WHITE);
				if(i==5)
				{
					i=i+1;
					addDonorC[i].setSelectedIndex(0);
					addDonorC[i].setBackground(Color.WHITE);

				}
			}			
		}
	}//AddDonorP ActionListener End
}
//AddDonorP Class End
