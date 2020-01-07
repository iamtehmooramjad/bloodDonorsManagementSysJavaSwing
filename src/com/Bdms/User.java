package com.Bdms;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class User extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	static boolean loginSuccess=false;
	
	private JPanel loginP;
	private JPanel signupP;
	private JLabel uNameL;
	private JLabel passL;
	private JTextField uNameT;
	private JTextField passT;
	private JButton signupB;
	private JLabel signupTopL;
	private JLabel fullNameL;
	private JLabel cnicL;
	private JLabel phoneNumL;
	private JLabel passwordL;
	private JTextField fullNameT;
	private JTextField cnicT;
	private JTextField phoneNumT;
	private JButton sSignupB;
	private JButton sLoginB;
	private JTextField sPass;
	private JPanel loginTopLP;
	JButton loginB ;
	private JLabel usernameL;
	private JTextField usernameT;

	public User()
	{		initialize();		}
	
	private void initialize() 
	{
		
		this.getContentPane().setLayout(new CardLayout());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\logo.png"));
		this.setTitle("Blood Donors Management System");
		loginP = new JPanel();
		loginP.setBackground(Color.WHITE);
		this.getContentPane().add(loginP);
		loginP.setLayout(null);
				
		uNameL = new JLabel("Username");
		uNameL.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
		uNameL.setBounds(116, 103, 85, 24);
		loginP.add(uNameL);
		
		passL = new JLabel("Password");
		passL.setFont(new Font("Yu Gothic", Font.PLAIN, 16));
		passL.setBounds(116, 151, 98, 24);
		loginP.add(passL);
		
		uNameT = new JTextField();
		uNameT.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		uNameT.setBounds(243, 104, 140, 24);
		loginP.add(uNameT);
		uNameT.setColumns(10);
		
		passT = new JTextField();
		passT.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		passT.setColumns(10);
		passT.setBounds(243, 152, 140, 24);
		loginP.add(passT);
		
		loginB = new JButton("Log In");
		loginB.setBounds(116, 208, 89, 23);
		loginB.setBackground(new Color(245,50,64));
		loginB.setForeground(Color.WHITE);
		loginB.addActionListener(this);
		loginP.add(loginB);
		
		signupB = new JButton("Sign Up");
		signupB.addActionListener(this);
		signupB.setBounds(243, 208, 89, 23);
		signupB.setBackground(new Color(245,50,64));
		signupB.setForeground(Color.WHITE);
		loginP.add(signupB);
		
		loginTopLP = new JPanel();
		loginTopLP.setBounds(0, 0, 500, 50);
		loginTopLP.setBackground(new Color(8,65,92));
		loginP.add(loginTopLP);
		
		JLabel loginTopL = new JLabel("Log In Here");
		loginTopLP.add(loginTopL);
		loginTopL.setHorizontalAlignment(SwingConstants.CENTER);
		loginTopL.setForeground(Color.WHITE);
		loginTopL.setFont(new Font("Yu Gothic", Font.PLAIN, 18));
		
		signupP = new JPanel();
		signupP.setBackground(Color.WHITE);
		this.getContentPane().add(signupP);
		signupP.setLayout(null);
		
		fullNameL = new JLabel("Full name");
		fullNameL.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		fullNameL.setBounds(120, 100, 100, 20);
		signupP.add(fullNameL);
		
		cnicL = new JLabel("Cnic");
		cnicL.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		cnicL.setBounds(120, 130, 100, 20);
		signupP.add(cnicL);
		
		phoneNumL = new JLabel("Phone number");
		phoneNumL.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		phoneNumL.setBounds(120, 160, 100, 20);
		signupP.add(phoneNumL);
		
		passwordL = new JLabel("Password");
		passwordL.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		passwordL.setBounds(120, 190, 100, 20);
		signupP.add(passwordL);
		
		fullNameT = new JTextField();
		fullNameT.setBounds(250, 100, 130, 25);
		signupP.add(fullNameT);
		fullNameT.setColumns(10);
		
		cnicT = new JTextField();
		cnicT.setColumns(10);
		cnicT.setBounds(250, 130, 130, 25);
		signupP.add(cnicT);
		
		phoneNumT = new JTextField();
		phoneNumT.setColumns(10);
		phoneNumT.setBounds(250, 160, 130, 25);
		signupP.add(phoneNumT);
		
		sPass = new JTextField();
		sPass.setBounds(250, 190, 130, 25);
		signupP.add(sPass);
		
		sSignupB = new JButton("Sign Up");
		sSignupB.setBounds(120, 260, 90, 25);
		sSignupB.setBackground(new Color(245,50,64));
		sSignupB.setForeground(Color.WHITE);
		sSignupB.addActionListener(this);
		signupP.add(sSignupB);
		
		sLoginB = new JButton("Log In");
		sLoginB.setBounds(250, 260, 90, 25);
		sLoginB.setBackground(new Color(245,50,64));
		sLoginB.setForeground(Color.WHITE);
		sLoginB.addActionListener(this);
		signupP.add(sLoginB);
		
		usernameL = new JLabel("Username");
		usernameL.setFont(new Font("Yu Gothic", Font.PLAIN, 14));
		usernameL.setBounds(120, 220, 100, 20);
		signupP.add(usernameL);
		
		usernameT = new JTextField();
		usernameT.setColumns(10);
		usernameT.setBounds(250, 220, 130, 25);
		signupP.add(usernameT);
		
		JPanel signupTopLP = new JPanel();
		signupTopLP.setBounds(0, 0, 500, 50);
		signupTopLP.setBackground(new Color(8,65,92));
		signupP.add(signupTopLP);
		
		signupTopL = new JLabel("Sign Up Here");
		signupTopLP.add(signupTopL);
		signupTopL.setForeground(Color.WHITE);
		signupTopL.setHorizontalAlignment(SwingConstants.CENTER);
		signupTopL.setFont(new Font("Yu Gothic", Font.PLAIN, 18));

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==signupB)//Add Signup Panel
		{
			this.getContentPane().remove(loginP);
			this.getContentPane().add(signupP);
			this.repaint();
			this.revalidate();			
		}
		else if(e.getSource()==sLoginB)//Add Login Panel
		{
			this.getContentPane().remove(signupP);
			this.getContentPane().add(loginP);
			this.repaint();
			this.revalidate();
			
		}
		else if(e.getSource()==sSignupB) //Sign up button to signup
		{			
			try 
			{	
				String fullName="",cnic="",phoneNum="",passw="",userName="";
				fullName=fullNameT.getText();
				cnic=cnicT.getText();
				phoneNum=phoneNumT.getText();
				passw=sPass.getText();
				userName=usernameT.getText();	
				String query="INSERT INTO users(fullName,cnic,phoneNum,pass,userName) VALUES(?,?,?,?,?)";
			
				//Prepared Statement
				PreparedStatement preSt=null;
				preSt=DbConnection.createPreparedStatement(query);
			
				//Setting values to the Attributes of donor Table
				preSt.setString(1, fullName);
				preSt.setString(2, cnic);
				preSt.setString(3, phoneNum);
				preSt.setString(4, passw);
				preSt.setString(5, userName);
				int count =DbConnection.executeUpdate(preSt);
		
				fullNameT.setText("");
				cnicT.setText("");
				phoneNumT.setText("");
				sPass.setText("");
				usernameT.setText("");
				
				JOptionPane.showMessageDialog(null, count+" Account Created Successfully..!");
				DbConnection.close(preSt);
			} 
			catch (SQLException  e1) 
			{	JOptionPane.showMessageDialog(null, e1);	}


			
		}
		
		else if(e.getSource()==loginB)//Login Button to Login
		{
			String uName="",passw="";
			uName=uNameT.getText();
			passw=passT.getText();
			String query="SELECT * FROM users WHERE userName = '"+uName+"' AND pass = '"+passw+"'";
		
			try 
			{
				ResultSet rs=DbConnection.createAndExecuteStatement(query);
				if(rs.next())
				{
					this.setVisible(false);
					Frame g = new Frame();
					g.setTitle("Blood Donors Managements System"); 
					g.setIconImage(Toolkit.getDefaultToolkit().getImage(".\\src\\logo.png"));
					g.setMinimumSize(new Dimension(1050, 700));
			    	g.setLocationRelativeTo(null);
			    	g.setVisible(true);
					
				}
				else
				{	JOptionPane.showMessageDialog(null, "Invalid username or password...!");	}
			
				
				
			} 
			catch (SQLException e1) 
			{	JOptionPane.showMessageDialog(null, "Enter Correct details...!\n"+e1);			}
			
		}
	}
}
