package com.Bdms;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class RequestsP extends JPanel implements ActionListener{
	
	/*
	 *Requests Panel:
	 *requestP is added into rightPanel's card layout and set the layout of requestP to BorderLayout.
	 *requestL is add in the requestsP at North position using BorderLaout.NORTH.
	 *requestDataP is added in the requestsP at Center position using BorderLaout.CENTER.
	 *Layout of requestDataP is set GridLayout with 1 column and 1 row. and reqJs(Scroll pane) is added into it.
	 *requestsTable is added into reqJs 
	*/
	
	private static final long serialVersionUID = 1L;
	JPanel reqManageP2;
	private JTextField rReqIdT;
	private JTextField rDonorIdT;
	private JTextField dReqIdT;
	JButton rCancelB;
	JButton doneB;
	JButton reqTopB;
	JPanel reqCenterP;
	JPanel reqTopP; 
	JLabel reqTopL;
	JPanel reqManageP;
           JPanel reqP1;
           JLabel reqManageL;
           JPanel reqP2;
           JLabel rReqIdL;
	JLabel rDonorIdL;
	JPanel reqP3;
	JLabel reqDeleteL;
	JPanel reqP4;
	JLabel dReqIdL;
	JTable reqtable;
	DefaultTableModel model;
    Object[] columnsName;
	Object[] rowData;
    JPanel reqTableP;
    JScrollPane pane;
	JButton rDeleteB;
	JButton rCancelB1;

	public RequestsP()
	{
		initialize();
	}
	
	private void initialize()
	{
		this.setLayout(new BorderLayout());
		
		reqTopP = new JPanel();
		reqTopP.setBackground(new Color(41,47,54));
		this.add(reqTopP, BorderLayout.NORTH);
		reqTopP.setLayout(new FlowLayout(FlowLayout.CENTER,100,0));
		
		reqTopL = new JLabel("Blood Requests");
		reqTopL.setFont(new Font("Yu Gothic", Font.PLAIN, 24));
		reqTopL.setForeground(Color.WHITE);
		reqTopP.add(reqTopL);
		
		reqTopB=new JButton ("Refresh");
		reqTopB.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		reqTopB.setForeground(Color.WHITE);
		reqTopB.setBackground(new Color(8,65,92));
		reqTopB.addActionListener(this);
		reqTopP.add(reqTopB);

		reqCenterP = new JPanel();
		reqCenterP.setBackground(Color.WHITE);
		this.add(reqCenterP, BorderLayout.CENTER);
		
		//Now setting the two rows,1 for donors search and second for requests
		reqCenterP.setLayout(new GridLayout(2, 1));

			 reqtable = new JTable();
	         model = new DefaultTableModel();
	        //Column names
	          columnsName = new Object[8];  //Specifying the no of Columns
	        //Specifying column names
	        columnsName[0] = "reqId";
	        columnsName[1] = "fName";
	        columnsName[2] = "lName";
	        columnsName[3] = "city";
	        columnsName[4] = "phoneNum";
	        columnsName[5] = "cnic";
	        columnsName[6] = "bloodGroup";
	        columnsName[7] = "Donor Id";
	        model.setColumnIdentifiers(columnsName);
			 
	       rowData = new Object[8];   //Specifying the data columns
	       for(int i = 0; i < BloodReq.getRequests().size(); i++)
			{
	              rowData[0] = BloodReq.getRequests().get(i).getRequestId();
	              rowData[1] = BloodReq.getRequests().get(i).getFirstName();
	              rowData[2] = BloodReq.getRequests().get(i).getLastName();
	              rowData[3] = BloodReq.getRequests().get(i).getCity();
	              rowData[4] = BloodReq.getRequests().get(i).getPhoneNum();
		          rowData[5] = BloodReq.getRequests().get(i).getCnic();
	              rowData[6] = BloodReq.getRequests().get(i).getBloodGroup();
	              rowData[7] = BloodReq.getRequests().get(i).getDonorId();
	               model.addRow(rowData);
	        }
	        reqtable.setModel(model);
	        model.fireTableDataChanged();
	    //1:Creating panel in centerP
	        reqTableP=new JPanel();
	        pane = new JScrollPane(reqtable);

	        reqCenterP.add(reqTableP);
	        reqTableP.setLayout(new BorderLayout());
	        reqTableP.add(pane,BorderLayout.CENTER);

		//2:
	     reqManageP=new JPanel();
	     reqManageP.setBackground(Color.WHITE);
	     reqCenterP.add(reqManageP);
	     reqManageP.setLayout(new GridLayout(4,1));
			
			//Now adding components in the center panel
			
	     	reqP1 = new JPanel();
			reqP1.setBackground(Color.WHITE);
			reqManageP.add(reqP1);
			
			reqManageL = new JLabel("Manage Requests");
			reqManageL.setFont(new Font("Yu Gothic", Font.PLAIN, 18));
			reqP1.add(reqManageL);
			
			reqP2 = new JPanel();
			reqP2.setBackground(Color.WHITE);
			reqManageP.add(reqP2);
			
			rReqIdL = new JLabel("Enter Request Id");
			rReqIdL.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
			reqP2.add(rReqIdL);
			
			rReqIdT = new JTextField();
			reqP2.add(rReqIdT);
			rReqIdT.setColumns(10);
			
			rDonorIdL = new JLabel("Enter Donor Id");
			rDonorIdL.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
			reqP2.add(rDonorIdL);
			
			rDonorIdT = new JTextField();
			reqP2.add(rDonorIdT);
			rDonorIdT.setColumns(10);
			
			doneB = new JButton("Done");
			doneB.setFont(new Font("Yu Gothic", Font.PLAIN, 10));
			doneB.setBackground(new Color(8,65,92));
			doneB.setForeground(Color.WHITE);
			doneB.addActionListener(this);
			reqP2.add(doneB);
			
			rCancelB = new JButton("Cancel");
			rCancelB.setFont(new Font("Yu Gothic", Font.PLAIN, 10));
			rCancelB.setBackground(new Color(8,65,92));
			rCancelB.setForeground(Color.WHITE);
			rCancelB.addActionListener(this);
			reqP2.add(rCancelB);
						
			reqP3 = new JPanel();
			reqP3.setBackground(Color.WHITE);
			reqManageP.add(reqP3);
			
			reqDeleteL = new JLabel("Delete Requests");
			reqDeleteL.setFont(new Font("Yu Gothic", Font.PLAIN, 18));
			reqP3.add(reqDeleteL);
			
			reqP4 = new JPanel();
			reqP4.setBackground(Color.WHITE);
			reqManageP.add(reqP4);
			
			dReqIdL = new JLabel("Enter Request Id");
			dReqIdL.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
			reqP4.add(dReqIdL);
			
			dReqIdT = new JTextField();
			reqP4.add(dReqIdT);
			dReqIdT.setColumns(10);
			
			rDeleteB = new JButton("Delete ");
			rDeleteB.setFont(new Font("Yu Gothic", Font.PLAIN, 10));
			rDeleteB.setBackground(new Color(8,65,92));
			rDeleteB.setForeground(Color.WHITE);
			rDeleteB.addActionListener(this);
			reqP4.add(rDeleteB);
			
			rCancelB1 = new JButton("Cancel");
			rCancelB1.setFont(new Font("Yu Gothic", Font.PLAIN, 10));
			rCancelB1.setBackground(new Color(8,65,92));
			rCancelB1.setForeground(Color.WHITE);
			rCancelB1.addActionListener(this);
			reqP4.add(rCancelB1);

	}//RequestsP initialize End
	//RequestsP ActionListener Start	
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		String query;
		if(e.getSource()==doneB)  //Manage Requests Done Button
		{
			String reqId=rReqIdT.getText();
			String donId=rDonorIdT.getText();
			PreparedStatement preSt=null;
			query="UPDATE request SET donId = '"+donId+"' WHERE reqId = '"+reqId+"'";
			try 
			{
				//Prepared Statement
				preSt=DbConnection.createPreparedStatement(query);
				int count=DbConnection.executeUpdate(preSt);
				//show how many rows are affected
				JOptionPane.showMessageDialog(null, count+" Request is served Successfully");	
				DbConnection.close(preSt);
				
				//Now we will set the status of this donor to unavailable
				query="SELECT donId FROM request WHERE reqId = '"+reqId+"'";
				ResultSet rs=DbConnection.createAndExecuteStatement(query);
				rs.next();
				String donorId=rs.getString("donId");
				DbConnection.close(preSt);

				//Now we will call the setStatus Stored Procedure
				//of this donor to unavailable using setStatus FUnction
				DbConnection.setStatus(donorId);
				
				//Settings all fields to empty
				rDonorIdT.setText("");
				rReqIdT.setText("");
			}
			catch(Exception e2)
			{	JOptionPane.showMessageDialog(null, e2);	}

		}
		else if(e.getSource()==rCancelB) //Manage Request Cancel Button
		{
				//Settings all fields to empty
				rDonorIdT.setText("");
				rReqIdT.setText("");
		}
		else if(e.getSource()==reqTopB) //Blood Requests Refresh Button 
		{
			//Removing Table
			DefaultTableModel model =(DefaultTableModel)reqtable.getModel();
			model.setColumnCount(0);
			model.setRowCount(0);
	        //Specifying column names
	        columnsName[0] = "reqId";
	        columnsName[1] = "fName";
	        columnsName[2] = "lName";
	        columnsName[3] = "city";
	        columnsName[4] = "phoneNum";
	        columnsName[5] = "cnic";
	        columnsName[6] = "bloodGroup";
	        columnsName[7] = "Donor Id";
	        model.setColumnIdentifiers(columnsName);
	        	        
	        for(int i = 0; i < BloodReq.getRequests().size(); i++){
	              rowData[0] = BloodReq.getRequests().get(i).getRequestId();
	              rowData[1] = BloodReq.getRequests().get(i).getFirstName();
	              rowData[2] = BloodReq.getRequests().get(i).getLastName();
	              rowData[3] = BloodReq.getRequests().get(i).getCity();
	              rowData[4] = BloodReq.getRequests().get(i).getPhoneNum();
		          rowData[5] = BloodReq.getRequests().get(i).getCnic();
	              rowData[6] = BloodReq.getRequests().get(i).getBloodGroup();
	              rowData[7] = BloodReq.getRequests().get(i).getDonorId();
	               model.addRow(rowData);
	        }
	        reqtable.setModel(model);
	        model.fireTableDataChanged();
	        reqTableP.add(pane,BorderLayout.CENTER);
		}
		if(e.getSource()==rCancelB1) //Delete Request Cancel Button
		{	dReqIdT.setText("");	}
		
		else if(e.getSource()==rDeleteB) //Delete Request Button 
		{
			String reqId=dReqIdT.getText();
			PreparedStatement preSt=null;
			//Prepared Statement
			query="DELETE FROM request WHERE reqId = '"+reqId+"'";
		 	preSt=DbConnection.createPreparedStatement(query);
			int count =DbConnection.executeUpdate(preSt);
			DbConnection.close(preSt);
			//show how many rows are affected
			JOptionPane.showMessageDialog(null, count+" Request is Deleted Successfully");	
			//Settings all fields to empty
			dReqIdT.setText("");
		}
	}//RequestsP ActionListener End	
}//RequestsP Class End
