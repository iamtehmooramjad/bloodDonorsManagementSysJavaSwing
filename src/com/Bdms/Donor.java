package com.Bdms;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Donor 
{
	//Data Members
	private String donorId;
	private String fName;
	private String lName;
	private String city;
	private String cnic;
	private String phoneNum;
	private String bloodGroup;
	private String dob;
	private String lastDonationD;	
	private String status;
	
	
	//Constructor
	public Donor()
	{
		fName="";
		lName="";
		donorId="";
		city="";
		cnic="";
		phoneNum="";
		bloodGroup="";
		dob="";
		lastDonationD="";
		status="";
	}
	//Parametrized Constructor
	public Donor(String did,String fn,String ln,String cit,String cni,String phone,String bG,String d1,String d2,String st)
	{       
		donorId=did;
		fName=fn;
		lName=ln;
		city=cit;
		cnic=cni;
		phoneNum=phone;
		bloodGroup=bG;
		dob=d1;
		lastDonationD=d2;
		status=st;
	}
	
	//Getting data from donor table of database using arrayList
	public static ArrayList<Donor> getDonors()
    {
        ArrayList<Donor> donors = new ArrayList<Donor>();
        Connection con=DbConnection.createConnection();	
		//Creates the Statement and ResultSet objects
		Statement st=null;
		ResultSet rs=null;	
        //Donor Object
        Donor u;
        try 
        {
        	//Creates the statements
            st = con.createStatement();
            //Query
            rs = st.executeQuery("SELECT * FROM donor");
            
            //Getting data from donor table of database
            while(rs.next()){
                
                u = new Donor(
                        rs.getString("donorId"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("city"),
                        rs.getString("cnic"),
                        rs.getString("phoneNum"),
                        rs.getString("bloodGroup"),
                        rs.getString("dob"), 
                        rs.getString("lastDonationDate"),
                        rs.getString("status")
                );
                
                //adding row in donors ArrayList
                donors.add(u);
            }
             
        }   
		catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
        //Returns the donors ArrayList
        return donors;
}
	//Setters
	public void setFirstName(String fn)
	{		fName=fn.toLowerCase();			}
	public void setLastName(String ln)
	{		lName=ln.toLowerCase();			}
	public void setDonorId(String donId)
	{		donorId=donId.toLowerCase();			}
	public void setCity(String cityName)
	{		city=cityName.toLowerCase();			}
	public void setCnic(String cnicNum)
	{		cnic=cnicNum.toLowerCase();			}
	public void setPhoneNum(String phoneNumber)
	{		phoneNum=phoneNumber.toLowerCase();		}
	public void setBloodGroup(String bloodG)
	{		bloodGroup=bloodG.toLowerCase();			}
	public void setDob(String Dob)
	{		dob=Dob;					}
	public void setLastD(String lDonat)
	{		lastDonationD=lDonat;				}
	public void setStatus(String s)
	{		status=s;					}

	//Getters
	String getFirstName()
	{		return fName;			}
	String getLastName()
	{		return lName;			}
	String getDonorId()
	{		return donorId;			}
	String getCity()
	{		return city;			}
	String getCnic()
	{		return cnic;			}
	String getPhoneNum()
	{		return phoneNum;			}
	String getBloodGroup()
	{		return bloodGroup;			}
	String getDob()
	{		return dob;			}
	String getLastDonationDate()
	{		return lastDonationD;			}
	String getStatus()
	{		return status;			}
	
	
	//Validation to  check name
	public boolean checkName(String name)
	{
		boolean number=true;		 
		//boolean result=true;
		try 
		{		
			Double num=Double.parseDouble(name); //if s contains sting then it will not be parse and it will throw exception	
		}
		catch(NumberFormatException e)
		{
			number=false;
			return number;
		}
			return number;
	}

	//Validation for checkname 
	public boolean checkName(String name,JTextField t)
	{
		boolean number=true;		
		boolean result=true;
		try 
		{
			Double num=Double.parseDouble(name); //if s contains sting then it will not be parse and it will throw exception	
		}
		catch(NumberFormatException e)
		{
			number=false;
		}
		
		if(number==true) // true
		{
			result=false;		
		}
		else if(number==false) //it is a String
		{
			if(name.length()>2)
			{
				char[] chars=name.toCharArray();
				for(char c : chars)
				{
					if(Character.isLetter(c))
					{		result=true;		}
					else
					{
						result=false;
						break;
					}
				}
			}
			else if(name.length()<=2)
			{		result=false;		}	
		}
		
		return result;
		//if result = false -> number
		//if result = true  -> String>2
	}

	//Validation for Numbers getting name,field  and length of the number
	public boolean checkNum(String n,JTextField t,int len)//JFrame f on 3rd
	{
		boolean number=true;		 
		boolean result=false;
		try 
		{
			Double num=Double.parseDouble(n); //if s contains sting then it will not be parse and it will throw exception	
		}
		catch(NumberFormatException e)
		{	number=false;		}
		
		if(number==true) // true
		{
			//it is a number
			if(n.length()==len)
			{	result=true;	}
			else
			{	result=false;	}
				
		}
		else if(number==false) //it is a String
		{	result=false;		}
		
		return result;
		//if result = false -> number
		//if result = true  -> String>2
	}
	

	//Validation for checkComboBox
	public boolean checkComboBox(JComboBox<String> c)
	{
		boolean result=false;
		if(c.getSelectedIndex()==0)
		{
			c.setBackground(new Color(245,50,64));
			c.setForeground(Color.WHITE);
		}
		else if(c.getSelectedIndex()!=0)
		{
			c.setBackground(Color.WHITE);
			c.setForeground(Color.BLACK);
			result=true;
		}
		return result;
	}

}
