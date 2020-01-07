package com.Bdms;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SearchDonorP extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1L;
	//SearchDonor Panel Components
	JLabel[] searchDonorL=new JLabel[2];
	JComboBox <String>searchDonorC;
	JButton [] searchDonorB=new JButton[2];
	JTextField searchDonorT=new JTextField();
	JPanel [] sDonorP=new JPanel[3];
	JTable donorsTable;
	JPanel sDonorCenterP;
	JPanel sDonorTopP;
	JScrollPane sDonorJs;
	JScrollPane donorsJs;
	JPanel sDonCCenP;
	JPanel sDonCTopP;
	JPanel sDonorCenP;
	JTable searchTable;
	DefaultTableModel sModel;
	Object[] sColumnNames;
    Object[] sRowData;
    JScrollPane sDonorPane;
	JLabel sDonorTopL;

  public SearchDonorP()
  {
  	initialize();
  }
  private void initialize()
  {
  	String [] bloodGroups=new String[] {"Select Below","A+","A-","B+","B-","O+","O-","AB+","AB-"};

		//search donor start
		this.setBackground(Color.WHITE);		
		this.setLayout(new BorderLayout());

		//Top panel
		sDonorTopP = new JPanel();
		sDonorTopP.setLayout(new FlowLayout(FlowLayout.CENTER,0,10));
		sDonorTopP.setBackground(new Color(41,47,54));
		
		//label for top panel
		sDonorTopL = new JLabel("Search Blood Donors");
		sDonorTopL.setFont(new Font("Yu Gothic",Font.PLAIN,24));
		sDonorTopL.setForeground(Color.white);
		sDonorTopP.add(sDonorTopL);
		this.add(sDonorTopP,BorderLayout.NORTH);
		
		//Center Panel
		sDonorCenP=new JPanel();
		this.add(sDonorCenP,BorderLayout.CENTER);
		sDonorCenP.setLayout(new BorderLayout());
		//AddING two panels in it
		
		//Search donor center panel
		sDonCTopP= new JPanel();
		sDonorCenP.add(sDonCTopP,BorderLayout.NORTH);
		sDonCTopP.setLayout(new GridLayout(1,3,0,10));//grid of 1 row and 3 columns
		
		//Adding three panels in the sDonCTopP
		for(int i=0;i<3;i++)
		{
			sDonorP[i]=new JPanel();
			sDonorP[i].setBackground(Color.WHITE);
			
			sDonorP[i].setLayout(new FlowLayout(FlowLayout.CENTER,10,10));
			sDonCTopP.add(sDonorP[i]);
			
			if(i==0 || i==1)
			{
				//Adding labels
				searchDonorL[i]=new JLabel();
				searchDonorL[i].setFont(new Font("Yu Gothic",Font.PLAIN,14));
				if(i==0)
				{
					searchDonorL[i].setText("Select Blood Group");
					searchDonorC=new JComboBox<String>(bloodGroups);
					searchDonorC.setFont(new Font("Yu Gothic",Font.PLAIN,12));
					searchDonorC.setBackground(Color.WHITE);
					sDonorP[i].add(searchDonorL[i]);
					sDonorP[i].add(searchDonorC);
				}
				else if(i==1)
				{
					searchDonorL[i].setText("Enter City");
					searchDonorT.setColumns(10);
					searchDonorT.setFont(new Font("Yu Gothic",Font.PLAIN,12));
					sDonorP[i].add(searchDonorL[i]);
					sDonorP[i].add(searchDonorT);
				}
			}
			else
			{
				for(int j=0;j<2;j++)
				{
					//Adding Buttons
					searchDonorB[j]=new JButton();
					
					if(j==0)
						searchDonorB[j].setText("Search");
					else
						searchDonorB[j].setText("Refresh");
					
					searchDonorB[j].setFont(new Font("Yu Gothic",Font.PLAIN,12));
					searchDonorB[j].setBackground(new Color(8,65,92));
					searchDonorB[j].setForeground(Color.WHITE);
					searchDonorB[j].addActionListener(this);
					sDonorP[i].add(searchDonorB[j]);
				}
			}
		}

		//AddING table in the sDonCCenP		
		sDonCCenP= new JPanel();
		sDonorCenP.add(sDonCCenP,BorderLayout.CENTER);
		sDonCCenP.setBackground(Color.WHITE);
		sDonCCenP.setLayout(new GridLayout(1,1));
		
			//SEARCH DONOR tABLE START
			searchTable = new JTable();
			sModel = new DefaultTableModel();
			//Column names
	        sColumnNames = new Object[10];  //Specifying the no of Columns
	        
	        //Specifying column names
	        sColumnNames[0] = "donorId";
	        sColumnNames[1] = "fName";
	        sColumnNames[2] = "lName";
	        sColumnNames[3] = "city";
	        sColumnNames[4] = "cnic";
	        sColumnNames[5] = "phoneNum";
	        sColumnNames[6] = "bloodGroup";
	        sColumnNames[7] = "dob";
	        sColumnNames[8] = "lastDonationDate";
	        sColumnNames[9] = "status";
	        sModel.setColumnIdentifiers(sColumnNames);
  	        sRowData = new Object[10];   //Specifying the data columns
	        
	        for(int j = 0; j < Donor.getDonors().size(); j++)
	        {
	              sRowData[0] = Donor.getDonors().get(j).getDonorId();
	              sRowData[1] = Donor.getDonors().get(j).getFirstName();
	              sRowData[2] = Donor.getDonors().get(j).getLastName();
	              sRowData[3] = Donor.getDonors().get(j).getCity();
	              sRowData[4] = Donor.getDonors().get(j).getCnic();
		          sRowData[5] = Donor.getDonors().get(j).getPhoneNum();
	              sRowData[6] = Donor.getDonors().get(j).getBloodGroup();
	              sRowData[7] = Donor.getDonors().get(j).getDob();
	              sRowData[8] = Donor.getDonors().get(j).getLastDonationDate();
	              sRowData[9] = Donor.getDonors().get(j).getStatus();
	              sModel.addRow(sRowData);
	        }
	        searchTable.setModel(sModel);
	        sModel.fireTableDataChanged();
	        sDonorPane = new JScrollPane(searchTable);
	        sDonCCenP.add(sDonorPane);  
	      
	         //SEARCH DONOR TABLE END
	

}//SearchDonorP initialize End  
  //SearchDonorP ActionListener Start
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		
		if (e.getSource()==searchDonorB[0])  //Search Donor Search button
		{	
			String bloodG=searchDonorC.getSelectedItem().toString();
			String city=searchDonorT.getText().toLowerCase();
			//Removing Table
			DefaultTableModel sModel =(DefaultTableModel)searchTable.getModel();
			sModel.setColumnCount(0);
			sModel.setRowCount(0);
			
			//Adding Back the Table
	        
	        //Specifying column names
	   
	        sColumnNames[0] = "donorId";
	        sColumnNames[1] = "fName";
	        sColumnNames[2] = "lName";
	        sColumnNames[3] = "city";
	        sColumnNames[4] = "cnic";
	        sColumnNames[5] = "phoneNum";
	        sColumnNames[6] = "bloodGroup";
	        sColumnNames[7] = "dob";
	        sColumnNames[8] = "lastDonationDate";
	        sColumnNames[9] = "status";
	        sModel.setColumnIdentifiers(sColumnNames);
	        
	        for(int j = 0; j < Donor.getDonors().size(); j++)
	        {
	        	  sRowData[3] = Donor.getDonors().get(j).getCity();
	        	  sRowData[6] = Donor.getDonors().get(j).getBloodGroup();
	        	  String c=sRowData[3].toString().toLowerCase();
	        	  String bg=sRowData[6].toString();
	            
	        	  if(searchDonorC.getSelectedIndex()==0) //If JCombo is Unselected 
	        	  {
	        		  if(searchDonorT.getText()!="")    //And TextField is not empty
	        		  {
	        			  if(city.equals(c))
	        			  {
	        	        	  sRowData[0] = Donor.getDonors().get(j).getDonorId();
	        	              sRowData[1] = Donor.getDonors().get(j).getFirstName();
	        	              sRowData[2] = Donor.getDonors().get(j).getLastName();
	        	              sRowData[4] = Donor.getDonors().get(j).getCnic();
	        		          sRowData[5] = Donor.getDonors().get(j).getPhoneNum();
	        	              sRowData[7] = Donor.getDonors().get(j).getDob();
	        	              sRowData[8] = Donor.getDonors().get(j).getLastDonationDate();
	        	              sRowData[9] = Donor.getDonors().get(j).getStatus();
	        		          sModel.addRow(sRowData); 
	        			  }
	        		  }
	        	  }
	        	  else if(searchDonorC.getSelectedIndex()!=0 && searchDonorT.getText().length()==0)
	        	  {
	        		  if(bloodG.equals(bg))
	        		  {
	        			  JOptionPane.showMessageDialog(null, "len");
		            	  sRowData[0] = Donor.getDonors().get(j).getDonorId();
			              sRowData[1] = Donor.getDonors().get(j).getFirstName();
			              sRowData[2] = Donor.getDonors().get(j).getLastName();
			              sRowData[4] = Donor.getDonors().get(j).getCnic();
				          sRowData[5] = Donor.getDonors().get(j).getPhoneNum();
			              sRowData[7] = Donor.getDonors().get(j).getDob();
			              sRowData[8] = Donor.getDonors().get(j).getLastDonationDate();
			              sRowData[9] = Donor.getDonors().get(j).getStatus();
				          sModel.addRow(sRowData);
	        		  }
	        	  }
	        	  else if(city.equals(c) && bg.equals(bloodG))
	        	  {  
		        	  sRowData[0] = Donor.getDonors().get(j).getDonorId();
		              sRowData[1] = Donor.getDonors().get(j).getFirstName();
		              sRowData[2] = Donor.getDonors().get(j).getLastName();
		              sRowData[4] = Donor.getDonors().get(j).getCnic();
			          sRowData[5] = Donor.getDonors().get(j).getPhoneNum();
		              sRowData[7] = Donor.getDonors().get(j).getDob();
		              sRowData[8] = Donor.getDonors().get(j).getLastDonationDate();
		              sRowData[9] = Donor.getDonors().get(j).getStatus();
			          sModel.addRow(sRowData);
	        	  } 
	        }
	        searchTable.setModel(sModel);
	        sModel.fireTableDataChanged();
	        sDonCCenP.add(sDonorPane);  
		}
		else if(e.getSource()==searchDonorB[1]) //Search Donor Refresh Button
		{
			//Resetting the textfields and combo box
			searchDonorT.setText("");
			searchDonorC.setSelectedIndex(0);
			//Removing Table
			DefaultTableModel sModel =(DefaultTableModel)searchTable.getModel();
			sModel.setColumnCount(0);
			sModel.setRowCount(0);
	        
	        //Specifying column names
	        sColumnNames[0] = "donorId";
	        sColumnNames[1] = "fName";
	        sColumnNames[2] = "lName";
	        sColumnNames[3] = "city";
	        sColumnNames[4] = "cnic";
	        sColumnNames[5] = "phoneNum";
	        sColumnNames[6] = "bloodGroup";
	        sColumnNames[7] = "dob";
	        sColumnNames[8] = "lastDonationDate";
	        sColumnNames[9] = "status";
	        sModel.setColumnIdentifiers(sColumnNames);
	        for(int j = 0; j < Donor.getDonors().size(); j++)
	        {
	              sRowData[0] = Donor.getDonors().get(j).getDonorId();
	              sRowData[1] = Donor.getDonors().get(j).getFirstName();
	              sRowData[2] = Donor.getDonors().get(j).getLastName();
	              sRowData[3] = Donor.getDonors().get(j).getCity();
	              sRowData[4] = Donor.getDonors().get(j).getCnic();
		          sRowData[5] = Donor.getDonors().get(j).getPhoneNum();
	              sRowData[6] = Donor.getDonors().get(j).getBloodGroup();
	              sRowData[7] = Donor.getDonors().get(j).getDob();
	              sRowData[8] = Donor.getDonors().get(j).getLastDonationDate();
	              sRowData[9] = Donor.getDonors().get(j).getStatus();
	               sModel.addRow(sRowData);
	        }
	        searchTable.setModel(sModel);
	        sModel.fireTableDataChanged();
	        sDonCCenP.add(sDonorPane);  
		}	
	}//SearchDonorP  ActionListener End	
}
//SearchDonorP Class End
