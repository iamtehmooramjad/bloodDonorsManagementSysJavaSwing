package com.Bdms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class DeleteDonorP extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	//Delete Donor Panel Components
	JLabel []delDonorL=new JLabel[9];
	JTextField []delDonorT=new JTextField[9];
	JButton [] delDonorB=new JButton[3];
	GridBagConstraints gbcDelDonor;	
	JPanel delTopP;
	JLabel delTopL;
	JPanel delCenterP;

	public DeleteDonorP()
	{
		initialize();
	}
	private void initialize()
	{
		
		//Delete Donor Section Start
		this.setLayout(new BorderLayout());
		
		//top panel 
		delTopP = new JPanel();
		delTopP.setBackground(new Color(41,47,54));
		this.add(delTopP, BorderLayout.NORTH);
		//top label for top panels
		delTopL = new JLabel("Delete Donor");
		delTopL.setFont(new Font("Yu Gothic", Font.PLAIN, 24));
		delTopL.setForeground(Color.WHITE);
		delTopP.add(delTopL);
		
		//Center panel
		delCenterP = new JPanel();
		this.add(delCenterP, BorderLayout.CENTER);
		delCenterP.setBackground(Color.WHITE);
		delCenterP.setLayout(new GridBagLayout());
		
		//Adding Components in the Center Panel
		
		int gx_delD=0;    //to control gridx
		int gy_delD=0;	  //to control gridy
		int countB=-1;    //to count buttons
		
		//gridbaghconstraint object for delete donors
		gbcDelDonor=new GridBagConstraints();
		
		//adding components in the delete donor panel
		for(int i=0;i<9;i++)
		{
			//setting internal padding to zero
			gbcDelDonor.ipadx=0;
			gbcDelDonor.ipady=0;
			
			if(i==0 || i==1 || i==3 || i==5 || i==7)
				gx_delD=0;   //starts for this if condition true
			else
				gx_delD=2;
								
			//if this condition holds then grid y is increased
			if(i==1 || i==3 || i==5 || i==7)
				gy_delD++;
			
			//Labels for delete donors
			delDonorL[i]=new JLabel();
			delDonorT[i]=new JTextField();
			delDonorT[i].setBackground(Color.WHITE);
			delDonorL[i].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
			
			//setting gbc constraints
			gbcDelDonor.insets=new Insets(20,20,20,20);
			gbcDelDonor.gridx=gx_delD;
			gbcDelDonor.gridy=gy_delD;
			gbcDelDonor.anchor = GridBagConstraints.EAST;
			delCenterP.add(delDonorL[i], gbcDelDonor);
			
			
			//Adding Textfield
			delDonorT[i].setColumns(10);
			delDonorT[i].addActionListener(this);
			
			if(i!=0)
			{				delDonorT[i].setEditable(false);			}
			gbcDelDonor.ipadx=60;
			gbcDelDonor.ipady=10;
			gbcDelDonor.insets=new Insets(20,10,20,20);
			gbcDelDonor.gridx=gx_delD+1;
			gbcDelDonor.anchor = GridBagConstraints.WEST;
			delCenterP.add(delDonorT[i], gbcDelDonor);
			
			//Setting the names of delete donor 
			if(i==0)
			{				delDonorL[i].setText("Donor Id");			}
			if(i==1)
			{				delDonorL[i].setText("First name");			}
			if(i==2)
			{				delDonorL[i].setText("Last name");			}
			if(i==3)
			{				delDonorL[i].setText("Blood Group");			}
			if(i==4)
			{				delDonorL[i].setText("<html>Date of Birth<br><h4>(YYYY-MM-DD)</h4></html>"); }
			if(i==5)
			{				delDonorL[i].setText("Cnic number");			}
			if(i==6)
			{				delDonorL[i].setText("City");			}
			if(i==7)
			{				delDonorL[i].setText("Phone number");			}
			if(i==8)
			{				delDonorL[i].setText("<html>Last Donation Date<br><h4>(YYYY-MM-DD)</h4></html>");			}

	
			//Adding Buttons
			if(i==0 || i==8)
			{
				//Setting the internal paddings to 0
				gbcDelDonor.ipadx=0;
				gbcDelDonor.ipady=0;
				
				//increment in countB for Buttons
				countB++;
				delDonorB[countB]=new JButton();
				delDonorB[countB].setBackground(new Color(8,65,92));
				delDonorB[countB].setForeground(Color.WHITE);
				delDonorB[countB].addActionListener(this);
				delDonorB[countB].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
				//gbc constraints 
				gbcDelDonor.insets = new Insets(25,25,25,25);
				gbcDelDonor.anchor=GridBagConstraints.EAST;
				gbcDelDonor.gridx=2;
				
				if(i==0)
				{
					delDonorB[countB].setText("Search");
					gbcDelDonor.gridy=0;
					delCenterP.add(delDonorB[countB], gbcDelDonor);
					
				}
				else if(i==8)
				{
					//adding buttons in delete donor
					delDonorB[countB].setText("Delete");
					gbcDelDonor.gridy=gy_delD+1;
					delCenterP.add(delDonorB[countB], gbcDelDonor);
					
					countB++;
					delDonorB[countB]=new JButton();
					delDonorB[countB].setBackground(new Color(8,65,92));
					delDonorB[countB].setForeground(Color.WHITE);
					delDonorB[countB].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
					delDonorB[countB].setText("Cancel");
					gbcDelDonor.anchor=GridBagConstraints.WEST;
					gbcDelDonor.gridx=3;
					gbcDelDonor.insets = new Insets(20,10,20,20);
					delDonorB[countB].addActionListener(this);
					delCenterP.add(delDonorB[countB], gbcDelDonor);	

				}		
			}
		}
	}//DeleteDonorP initialize End
	
	//DeleteDonorP ActionListener Start
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String query;
		if(e.getSource()==delDonorB[1]) //DeleteDonor "Delete button"
		{
			if(delDonorT[1].getText().isEmpty())
			{	JOptionPane.showMessageDialog(null, "Please search the donor to delete");		}
			else
			{	
				query="DELETE FROM donor WHERE donorId = '"+delDonorT[0].getText()+"'";
				PreparedStatement preSt=null;
				preSt=DbConnection.createPreparedStatement(query);
				
				//executeUpdate returns the int to show how many rows are affected
				int count =DbConnection.executeUpdate(preSt);
								
				//show how many rows are affected
				JOptionPane.showMessageDialog(null, count+"record deleted Successfully..!");	
				
				DbConnection.close(preSt);
				
				//Setting all fields to empty
				for(int k=0;k<9;k++)
				{	delDonorT[k].setText("");	}

			}
		}
		else if(e.getSource()==delDonorB[2]) //Delete Donor Cancel Button
		{
			for(int i=0;i<9;i++)
			{	delDonorT[i].setText("");	}
		}
		else if(e.getSource()==	delDonorB[0]) //deleteDonor "Search button"
		{
			//Setting all fields to empty EXCEPTI FIRST field
			for(int i=1;i<9;i++)
			{	delDonorT[i].setText("");	}
			
			String fName ="",lName="",city="",cnic="",phoneNum="",bloodGroup = "",dob="",lastDonationD="";
			query="SELECT * FROM donor WHERE donorId = '"+delDonorT[0].getText()+"'";
			
			try 
			{
				ResultSet rs=DbConnection.createAndExecuteStatement(query);
				rs.next();
				fName=rs.getString("fName");
				lName=rs.getString("lName");
				city=rs.getString("city");
				cnic=rs.getString("cnic");
				phoneNum=rs.getString("phoneNum");
				bloodGroup=rs.getString("bloodGroup");				
				dob=rs.getString("dob");
				lastDonationD=rs.getString("lastDonationDate");
				//Setting the fetched data in the relative textFields
				delDonorT[1].setText(fName);
				delDonorT[2].setText(lName);
				delDonorT[3].setText(bloodGroup);
				delDonorT[4].setText(dob);
				delDonorT[5].setText(cnic);
				delDonorT[6].setText(city);
				delDonorT[7].setText(phoneNum);
				delDonorT[8].setText(lastDonationD);
				//add later the else part if no data fetched from database
				
			} 
			catch (SQLException e1) 
			{
				JOptionPane.showMessageDialog(null, "No result found...!");
			}
		}
}//DeleteDonorP ActionListener End
}//DeleteDonorP Class End
