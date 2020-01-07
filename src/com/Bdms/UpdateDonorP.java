package com.Bdms;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class UpdateDonorP extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;

	//Update Donor Panel Components
	JLabel [] updateDonorPL=new JLabel[9];
	JPanel [] updateDonorDateP=new JPanel[2];
	JTextField[] updateDonorT=new JTextField[6];
	JComboBox<String>[] updateDonorC=new JComboBox[7];
	JButton[] updateDonorB=new JButton[3];
	GridBagConstraints gbcUpDon;
	JPanel upDonTopP;
	JLabel upDonTopL;
	JPanel upDonCenP;
	String [] day;
	String [] month;
	String [] year;
	String [] bloodGroups;
	
	public UpdateDonorP()
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

		//Update Donor Start
		this.setBackground(Color.WHITE);
		this.setLayout(new BorderLayout());

		upDonTopP = new JPanel();
		upDonTopP.setBackground(new Color(41,47,54));
		this.add(upDonTopP, BorderLayout.NORTH);
		
		upDonTopL = new JLabel("Update Blood Donor");
		upDonTopL.setFont(new Font("Yu Gothic", Font.PLAIN, 24));
		upDonTopL.setForeground(Color.WHITE);
		upDonTopP.add(upDonTopL);
		
		upDonCenP = new JPanel();
		upDonCenP.setBackground(Color.WHITE);
		this.add(upDonCenP, BorderLayout.CENTER);
		upDonCenP.setLayout(new GridBagLayout());

		gbcUpDon=new GridBagConstraints();
		
		int gx_upD=0;
		int gy_upD=0;
		int countC_upD=-1;
		int countP_upD=-1;
		int countB_upD=-1;
		
		for(int i=0;i<9;i++)
		{
			gbcUpDon.ipadx=0;
			gbcUpDon.ipady=0;

			//adjusting the y position
			if(i==1 || i==3 || i==5 || i==7 || i==8)
				gy_upD++;
		
			//adjusting the x position
			if(i==0 || i==1 || i==3 || i==5 || i==7 ||i==8)
				gx_upD=0;
			else
				gx_upD=2;
			
			//Label
			updateDonorPL[i]=new JLabel();
			updateDonorPL[i].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
			gbcUpDon.anchor=GridBagConstraints.EAST;
			gbcUpDon.insets= new Insets(20,20,20,20);
			gbcUpDon.gridx=gx_upD;
			gbcUpDon.gridy=gy_upD;
			upDonCenP.add(updateDonorPL[i],gbcUpDon);
			
			//Textfield & JComboBox
			gbcUpDon.gridx=gx_upD+1;
			gbcUpDon.anchor=GridBagConstraints.WEST;
			gbcUpDon.ipadx=60;
			gbcUpDon.ipady=10;
			
			if(i<6)
			{		
				updateDonorT[i]=new JTextField();
				updateDonorT[i].setColumns(10);
				upDonCenP.add(updateDonorT[i],gbcUpDon);
				updateDonorT[i].addActionListener(this); //action listener for text fields
			}
			else if(i==6)
			{
				countC_upD=countC_upD+1;
				gbcUpDon.ipadx=0;
				gbcUpDon.ipady=0;
				String[] bloodGroups= {"Select Below","A+","A-","B+","B-","O+","O-","AB+","AB-"};
				updateDonorC[countC_upD]=new JComboBox(bloodGroups);
				updateDonorC[countC_upD].setBackground(Color.WHITE);
				updateDonorC[countC_upD].setFont(new Font("Yu Gothic",Font.PLAIN,12));
				upDonCenP.add(updateDonorC[countC_upD],gbcUpDon);
				updateDonorC[countC_upD].addActionListener(this);  //action listener for blood groups
			
			}
			else if(i==7 || i==8)
			{
				//Setting the internal padding
				gbcUpDon.ipadx=0;
				gbcUpDon.ipady=0;
				
				countP_upD=countP_upD+1; //-1 -> 0
				
				//Adding panels for date comboboxes
				updateDonorDateP[countP_upD]=new JPanel(); //P[0]
				updateDonorDateP[countP_upD].setLayout(new GridLayout(0, 3, 10, 0));
				updateDonorDateP[countP_upD].setBackground(Color.WHITE);
				upDonCenP.add(updateDonorDateP[countP_upD],gbcUpDon);
				
				for(int k=0;k<3;k++)
				{
					//Adding ComboBoxes
					countC_upD=countC_upD+1;
					if(k==0)
						updateDonorC[countC_upD]=new JComboBox(day);
					else if(k==1)
						updateDonorC[countC_upD]=new JComboBox(month);
					else
						updateDonorC[countC_upD]=new JComboBox(year);
					
					updateDonorC[countC_upD].setFont(new Font("Yu Gothic",Font.PLAIN,12));
					updateDonorC[countC_upD].setBackground(Color.WHITE);
					updateDonorDateP[countP_upD].add(updateDonorC[countC_upD]); //Adding ComboBox
					updateDonorC[countC_upD].addActionListener(this); //Adding ActionListener
				
				}
				
			}
			//Setting the names of labels
			if(i==0)
			{				updateDonorPL[i].setText("Enter Donor Id");				}
			else if(i==1)
			{				updateDonorPL[i].setText("First name");					}
			else if(i==2)
			{				updateDonorPL[i].setText("Last name");					}
			else if(i==3)
			{				updateDonorPL[i].setText("City");						}
			else if(i==4)
			{				updateDonorPL[i].setText("Phone number");				}
			else if(i==5)
			{				updateDonorPL[i].setText("Cnic number");					}
			else if(i==6)
			{				updateDonorPL[i].setText("Blood Group");					}
			else if(i==7)
			{				updateDonorPL[i].setText("Date of Birth");					}
			else if(i==8)
			{				updateDonorPL[i].setText("<html>Select Last Donation Date<h4>(Optional)</h4></html>");			}

			//For Buttons
			if(i==0 || i==8)
			{
				//Setting internal padding to 0
				gbcUpDon.ipadx=0;
				gbcUpDon.ipady=0;
				
				//Increment for handling buttons
				countB_upD=countB_upD+1;
				updateDonorB[countB_upD]=new JButton();
				updateDonorB[countB_upD].setBackground(new Color(8,65,92));
				updateDonorB[countB_upD].setForeground(Color.WHITE);
				updateDonorB[countB_upD].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
				//Grid Bag Constraints
				gbcUpDon.insets = new Insets(20,20,20,20);
				gbcUpDon.anchor=GridBagConstraints.EAST;
				gbcUpDon.gridx=2;
				
				if(i==0)
				{
					//Adding Search Button
					updateDonorB[countB_upD].setText("Search");
					gbcUpDon.gridy=0;
					upDonCenP.add(updateDonorB[countB_upD], gbcUpDon);
					
				}
				else if(i==8)
				{
					//Adding Update and Cancel
					updateDonorB[countB_upD].setText("Update");
					//gbc for update button
					gbcUpDon.gridy=gy_upD;
					upDonCenP.add(updateDonorB[countB_upD], gbcUpDon);
					updateDonorB[countB_upD].addActionListener(this); //Add Action listener for button
					
					//Increment to add another button (Cancel)
					countB_upD++;
					updateDonorB[countB_upD]=new JButton();
					updateDonorB[countB_upD].setBackground(new Color(8,65,92));
					updateDonorB[countB_upD].setForeground(Color.WHITE);
					updateDonorB[countB_upD].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
					updateDonorB[countB_upD].setText("Cancel");
					
					//Gbc for cancel button 
					gbcUpDon.anchor=GridBagConstraints.WEST;
					gbcUpDon.gridx=3;
					gbcUpDon.insets = new Insets(20,20,20,20);
					upDonCenP.add(updateDonorB[countB_upD], gbcUpDon);	

				}
				
				updateDonorB[countB_upD].addActionListener(this); //Add Action Listener for  button
		}	
	}
		
}//UpdateDonorP initialize End

	//UpdateDonor ActionListener Start
	public void actionPerformed(ActionEvent e) 
	{
		String query;
		if(e.getSource()==updateDonorB[0]) //UpdateDonor "Search button" 
		{
			String fName ="",lName="",city="",cnic="",phoneNum="",bloodGroup = "",dob="",lastDonationD="";
			query="SELECT * FROM donor WHERE donorId = '"+updateDonorT[0].getText()+"'";
			
			try 
			{
				ResultSet rs=DbConnection.createAndExecuteStatement(query);
				rs.next();
				
				fName=rs.getString("fName");
				lName=rs.getString("lName");
				city=rs.getString("city");
				phoneNum=rs.getString("phoneNum");
				cnic=rs.getString("cnic");
				bloodGroup=rs.getString("bloodGroup");				
				dob=rs.getString("dob");
				lastDonationD=rs.getString("lastDonationDate");

				//Setting the fetched data in the relative textFields
				updateDonorT[1].setText(fName);
				updateDonorT[2].setText(lName);
				updateDonorT[3].setText(city);
				updateDonorT[4].setText(phoneNum);
				updateDonorT[5].setText(cnic);
				
				updateDonorC[0].setSelectedItem(bloodGroup);
				String [] splitDob=dob.split("-");
				
				updateDonorC[1].setSelectedItem(splitDob[2]); //Set the day of dob ComboBox
				updateDonorC[2].setSelectedItem(splitDob[1]); //Set the month of dob ComboBox
				updateDonorC[3].setSelectedItem(splitDob[0]); //Set the Year of dob ComboBox
				
				String [] splitLastDonationD=lastDonationD.split("-");
				
				updateDonorC[4].setSelectedItem(splitLastDonationD[2]); //Set the day of dob ComboBox
				updateDonorC[5].setSelectedItem(splitLastDonationD[1]); //Set the month of dob ComboBox
				updateDonorC[6].setSelectedItem(splitLastDonationD[0]); //Set the Year of dob ComboBox
				
			} 
			catch (SQLException e1) 
			{
				JOptionPane.showMessageDialog(null, "Result not found...!");
			}

		}
		else if(e.getSource()==updateDonorB[1]) //UpdateDonor "update button"
		{
			query="UPDATE donor set fName = '"+updateDonorT[1].getText()+"' , lName = '"+updateDonorT[2].getText()+"', city = '"+updateDonorT[3].getText()+"' , phoneNum = '"+updateDonorT[4].getText()+"', cnic = '"+updateDonorT[5].getText()+"', dob = '"+updateDonorC[3].getSelectedItem().toString()+"-"+updateDonorC[2].getSelectedItem().toString()+"-"+updateDonorC[1].getSelectedItem().toString()+"' , bloodGroup = '"+updateDonorC[0].getSelectedItem().toString()+"' , lastDonationDate = '"+updateDonorC[6].getSelectedItem().toString()+"-"+updateDonorC[5].getSelectedItem().toString()+"-"+updateDonorC[4].getSelectedItem().toString()+"' WHERE donorId = '"+updateDonorT[0].getText()+"';";
				
			PreparedStatement preSt=null;
			try 
			{
				preSt=DbConnection.createPreparedStatement(query);

				//executeUpdate returns the int to show how many rows are affected
				int count =DbConnection.executeUpdate(preSt);
				
				//show how many rows are affected
				if(count==1)
					JOptionPane.showMessageDialog(null, count+" record updated Successfully...");	
				else
					JOptionPane.showMessageDialog(null, "Kindly First Search the donor");	
				
				preSt.close();
				
				for(int i=0;i<6;i++)
				{
					updateDonorT[i].setText("");
					updateDonorC[i].setSelectedIndex(0);
					if(i==5)
						updateDonorC[i+1].setSelectedIndex(0);
				}
			} 
			catch (SQLException e1) 
			{
				JOptionPane.showMessageDialog(null, "Please search first to update...!");
			}
			

		}
		else if(e.getSource()==updateDonorB[2]) //UpdateDonor "Cancel Button"
		{
			for(int i=0 ;i<7;i++)
			{
				if(i<6)
				{
					updateDonorT[i].setText("");
				}
			
				updateDonorC[i].setSelectedIndex(0);
			}
		}
	}//UpdateDonorP ActionListener End
}
//UpdateDonorP Class End
