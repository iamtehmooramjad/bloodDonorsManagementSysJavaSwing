package com.Bdms;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.Border;

public class AddRequestP extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	
	//Add Request Panel Components
	JLabel [] addRequestL=new JLabel[7];
	JTextField []addRequestT=new JTextField[6];
	JComboBox<String> addRequestC;
	JButton[] addRequestB=new JButton[2];
	GridBagConstraints gbc;
	JPanel addReqTopP;
	JLabel addBloodReq;
	JPanel addReqCenP;
	Border border;
	String []bloodGroups=new String[] {"Select Below","A+","A-","B+","B-","O+","O-","AB+","AB-"};
	
	public AddRequestP()
	{
		initialize();
	}
	private void initialize()
	{

		//Add Blood Request start
		this.setLayout(new BorderLayout());
		this.addMouseListener(new MouseAdapter()
		{
			BloodReq r=new BloodReq();
			public void mouseEntered(MouseEvent e)
			{
				String query="SELECT reqId FROM request\r\n" + 
						"ORDER BY reqId DESC\r\n" + 
						"LIMIT 1";
				try 
				{
					ResultSet rs=null;
					rs=DbConnection.createAndExecuteStatement(query);
			
					r.setReqId("0");				
					
					if(rs.next())
					{	r.setReqId(Integer.toString(Integer.parseInt(rs.getString("reqId"))+1));	}
					else
					{	r.setReqId(Integer.toString(Integer.parseInt(r.getRequestId())+1));			}
				
					addRequestT[0].setText(r.getRequestId());
		
				} 
				catch (SQLException e1) 
				{	e1.printStackTrace();	}
			}
		});
		
		border=BorderFactory.createLoweredBevelBorder();
		//addd REQUEST top Panel
		addReqTopP = new JPanel();
		addReqTopP.setBorder(border);
		addReqTopP.setBackground(new Color(41,47,54));
		this.add(addReqTopP, BorderLayout.NORTH);
		
		//add request Top Label
		addBloodReq = new JLabel("Add Blood Request");
		addBloodReq.setFont(new Font("Yu Gothic", Font.PLAIN, 24));
		addReqTopP.add(addBloodReq);
		
		//Add Request Center Panel
		addReqCenP = new JPanel();
		addReqCenP.setBackground(Color.WHITE);
		this.add(addReqCenP, BorderLayout.CENTER);		
		addReqCenP.setLayout(new GridBagLayout());
		
		//Add Request form start
		gbc=new GridBagConstraints();
		addRequestC=new JComboBox<String>(bloodGroups);

		int x;
		int y=-1;
		for(int i=0;i<7;i++)
		{
			y++;  //increment in y for increment in y axis
			x=0;  //sets the gridx=0 everytime the loop executes to place the labels in gridx=0 position		
			
			//gbc for labels
			
			gbc.anchor = GridBagConstraints.EAST;
			gbc.insets = new Insets(0, 0, 20, 30); //(top,left,bottom,right)
			gbc.gridx=x;
			gbc.gridy=y;
			gbc.ipadx=0; // =0 because when next time loop executes gbc object will have ipadx=10 and ipady=80
			gbc.ipady=0; //			
			addRequestL[i]=new JLabel();
			addRequestL[i].setFont(new Font("Yu Gothic", Font.PLAIN, 14));
			addReqCenP.add(addRequestL[i],gbc);
			
			x=x+1;
			gbc.gridx=x;
			gbc.anchor = GridBagConstraints.WEST;
			
			if(i<6)
			{
				//creating addRequest textfields
				addRequestT[i]=new JTextField();
				addRequestT[i].setColumns(10);
				gbc.ipadx=60;
				gbc.ipady=10;
				if(i==0)
				{
					//setting the textFiled to uneditable
					addRequestT[i].setBackground(Color.white);
					addRequestT[i].setEditable(false);        //ReqId will be auto generated & un-editable 
				}
				
				addRequestT[i].addActionListener(this);       //Adding ActionListener to textFields
				addReqCenP.add(addRequestT[i],gbc);			  //Adding in the Panel with gbc 
			}
			if(i==6)
			{
				//Setting the font
				addRequestC.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
				addRequestC.setBackground(Color.WHITE);
				addRequestC.addActionListener(this);
				//Adding combobox in the panel
				addReqCenP.add(addRequestC,gbc);				
				
				//Decrement  in x and increment in y to place buttons
				x=x-1;
				gbc.gridx=x;  //x will be reset to 0
				gbc.gridy=y+1;  
	
				for(int k=0;k<2;k++)
				{
					addRequestB[k]=new JButton();
					addRequestB[k].setBackground(new Color(8,65,92)); //Background Color
					addRequestB[k].setForeground(Color.WHITE);        //Foreground Color
					addRequestB[k].setFont(new Font("Yu Gothic", Font.PLAIN, 14));//Setting Font style and size
				
					if(k==0)
						gbc.anchor = GridBagConstraints.EAST;  //for addRequestB Button
					else
						gbc.anchor = GridBagConstraints.WEST;  //for Cancel Button
					
					addRequestB[k].addActionListener(this);
					addReqCenP.add(addRequestB[k],gbc);
					
					if(k==0)
					{
						addRequestB[k].setText("Add Request");
						gbc.gridx=x+1;
					}
					else if(k==1)
					{
						addRequestB[k].setText("Cancel");
					}
										
				}
			}
				//Setting the names of labels
				 if(i==0)
				 { 					 addRequestL[i].setText("Request Id");						 }
				 else if(i==1)
				 {					 addRequestL[i].setText("Enter first name");				 }
				 else if(i==2)
				 {					 addRequestL[i].setText("Enter last name");					 }
				 else if(i==3)
				 {					 addRequestL[i].setText("Enter city");						 }
				 else if(i==4)
				 {					 addRequestL[i].setText("Enter phone number");				 }
				 else if(i==5)
				 {					 addRequestL[i].setText("Enter Cnic number");				 }
				 else if(i==6)
				 {					 addRequestL[i].setText("Select Blood Group");				 }
			
		}
		
	}	//Add Request Initialize method End

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String query;
		BloodReq bReq=new BloodReq();
		
		if(e.getSource()==addRequestB[0]) //addRequest "Add Button"
		{
			String fName="",lName="",city="",phoneNum="",cnic="",bloodGroup="";
			
			//first name
			boolean res=bReq.checkName(addRequestT[1].getText(),addRequestT[1]);
			if(res==true)
			{	fName=addRequestT[1].getText().toLowerCase();	}
			
			//last name
			boolean res1=bReq.checkName(addRequestT[2].getText(),addRequestT[2]);
			if(res1==true)
			{	lName=addRequestT[2].getText().toLowerCase();	}

			//City
			boolean res2=bReq.checkName(addRequestT[3].getText(),addRequestT[3]);
			if(res1==true)
			{		city=addRequestT[3].getText().toLowerCase();	}

			//Phone Number
			boolean res3=bReq.checkNum(addRequestT[4].getText(),addRequestT[4],11);
			if(res1==true)
			{		phoneNum=addRequestT[4].getText().toLowerCase();	}

			//CNIC
			boolean res4=bReq.checkNum(addRequestT[5].getText(),addRequestT[5],13);

			if(res4==true)
			{	cnic=addRequestT[5].getText().toLowerCase();	}

			//Blood Group
			boolean res5=bReq.checkComboBox(addRequestC);
			if(res5==true)
			{	bloodGroup=addRequestC.getSelectedItem().toString();	}

			if(res==true && res1==true && res2==true && res3==true && res4==true && res5==true)
			{
				PreparedStatement preSt=null;
				query="INSERT INTO request(fName,lName,city,phoneNum,cnic,bloodGroup) VALUES(?,?,?,?,?,?);";
				try 
				{
					//Prepared Statement
					preSt=DbConnection.createPreparedStatement(query);
					//Setting values to the Attributes of donor Table
					preSt.setString(1, fName);
					preSt.setString(2, lName);
					preSt.setString(3, city);
					preSt.setString(4, cnic);
					preSt.setString(5, phoneNum);
					preSt.setString(6, bloodGroup);
					int count =DbConnection.executeUpdate(preSt);
					//show how many rows are affected
					JOptionPane.showMessageDialog(null, count+" record is saved Successfully");	
					DbConnection.close(preSt);
					
					//Settings all fields to empty
					for(int i=1;i<6;i++)
					{
						addRequestT[i].setText("");
						if(i==5)
							addRequestC.setSelectedIndex(0);
					}
				}
				catch(Exception e2)
				{		JOptionPane.showMessageDialog(null, e2);	}
			}
			else 
			{	JOptionPane.showMessageDialog(null, "Please enter correct data");	}
		}
		else if(e.getSource()==addRequestB[1])  //AddRequest Cancel Button  
		{
			//Settings all fields to empty
			for(int i=1;i<6;i++)
			{
				addRequestT[i].setText("");
				addRequestT[i].setBackground(Color.WHITE);
				if(i==5)
				{
					addRequestC.setSelectedIndex(0);
					addRequestC.setBackground(Color.WHITE);
				}
			}
		}
	}//AddRequestP Class ActionListener End
}//AddRequestP Class End
