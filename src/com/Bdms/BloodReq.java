package com.Bdms;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.*;

public class BloodReq 
{
	//DATA MEMBERS
	private String reqId;	
	private String fName;
	private String lName;
	private String city;
	private String cnic;
	private String phoneNum;
	private String bloodGroup;
	private String donorId;
	
	//CONSTRUCTOR
	public BloodReq()
	{
		reqId="";
		fName="";
		lName="";
		city="";
		cnic="";
		phoneNum="";
		bloodGroup="";
		donorId="";
	}
	
	//Parametrized Constructor
	public BloodReq(String id,String fn,String ln,String cit,String cni,String phone,String bg,String did)
	{
		reqId=id;
		fName=fn;
		lName=ln;
		city=cit;
		cnic=cni;
		phoneNum=phone;
		bloodGroup=bg;
		donorId=did;
	}
	
	//Getting data from request table using ArrayList
	public static ArrayList<BloodReq> getRequests()
    {
        ArrayList<BloodReq> requests = new ArrayList<BloodReq>();
        Connection con=DbConnection.createConnection();			
        
		//Creating Statement and ResultSet object
		Statement st=null;
		ResultSet rs=null;
		//BloodReq object
        BloodReq u;    
        try {
            
            st = con.createStatement();
            rs = st.executeQuery("SELECT * FROM request");
            
            //Getting data from request while there is data in the table
            while(rs.next()){
                
                u = new BloodReq(
                        rs.getString("reqId"),
                        rs.getString("fName"),
                        rs.getString("lName"),
                        rs.getString("city"),
                        rs.getString("phoneNum"),
                        rs.getString("cnic"),
                        rs.getString("bloodGroup"),
                        rs.getString("donId")
                );
                
               //Adding row in the requests List    
                requests.add(u);
            }
             
        }
        catch (Exception ex) 
        {
            JOptionPane.showMessageDialog(null, ex);
        }
        //Returns the whole list that contains all the rows of data
        return requests;
  }

	//Setters
	public void setReqId(String rId)
	{		reqId=rId.toLowerCase();			}
	public void setFirstName(String fn)
	{		fName=fn.toLowerCase();				}
	public void setLastName(String ln)
	{		lName=ln.toLowerCase();				}
	public void setCity(String cityName)
	{		city=cityName.toLowerCase();		}
	public void setCnic(String cnicNum)
	{		cnic=cnicNum.toLowerCase();			}
	public void setPhoneNum(String phoneNumber)
	{		phoneNum=phoneNumber.toLowerCase();	}
	public void setBloodGroup(String bloodG)
	{		bloodGroup=bloodG.toLowerCase();	}
	public void setDonorId(String donId)
	{		donorId=donId;						}
	
	//Getters
	public	String getRequestId()
	{		return reqId;			}
	public	String getFirstName()
	{		return fName;			}
	public	String getLastName()
	{		return lName;			}
	public	String getCity()
	{		return city;			}
	public	String getCnic()
	{		return cnic;			}
	public	String getPhoneNum()
	{		return phoneNum;		}
	public	String getBloodGroup()
	{		return bloodGroup;		}
	public	String getDonorId()	
	{		return donorId;			}
		
	//Check name that the field is not a number
	public boolean checkName(String name)
	{
		boolean number=true;		//We are assuming that name will be a String 		
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

	//Check name that the field is not a number and set the fields to red if no right data entered
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
		if(number==true)
		{
			//it is a number
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
					{	result=true;	}
					else
					{	result=false;
						break;			}
				}
			}
			else if(name.length()<=2)
			{		result=false;		}	
		}
		return result;
}

	//Checks that the field contains the number only not string and alerts the user
	public boolean checkNum(String n,JTextField t,int len) 
	{
		boolean number=true;		//We are assuming that name will be a String 
		boolean result=false;
		try 
		{
			Double num=Double.parseDouble(n); //if s contains sting then it will not be parse and it will throw exception	
		}
		catch(NumberFormatException e)
		{		number=false;		}
		
		if(number==true) // true
		{
			//it is a number
			if(n.length()==len)
			{	result=true;	}
			else
			{	result=false;	}
		}
		else if(number==false) //it is a String
		{	result=false;	}
		return result;
}		
	//Validation for comboBox
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
