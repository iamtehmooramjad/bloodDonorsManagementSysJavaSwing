package com.Bdms;
import java.sql.*;
import javax.swing.JOptionPane;

public class DbConnection {
	
	//Create Connection
	public static Connection createConnection()
	{
		Connection con=null;
		try 
		{
			String url="jdbc:mysql://localhost:3307/bdms"; 
			String uname="root";
			String pass="lenovo55";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			//Maintain Connection
			con=DriverManager.getConnection(url,uname,pass);
			
		} 
		catch (ClassNotFoundException e) 
		{
			JOptionPane.showMessageDialog(null, e);
		} 	
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return con;
	}
	
	//Create and Statement
	public static ResultSet createAndExecuteStatement(String query)
	{
		Statement st=null;
		ResultSet rs=null;
		try 
		{
	 		Connection con=createConnection();
			st=con.createStatement();
			rs=st.executeQuery(query);
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return rs;
	}
	
	//Create and Execute PreparedStatement
	public static PreparedStatement createPreparedStatement(String query)
	{
		PreparedStatement preSt=null;
		try 
		{
	 		Connection con=createConnection();
			preSt=con.prepareStatement(query);
		}
		catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return preSt;
	}
	//ExecuteUpdate Statement
	public static int executeUpdate(PreparedStatement p)
	{
		int count=0;
		try 
		{	count=p.executeUpdate();	}
		catch (SQLException e) 
		{	JOptionPane.showMessageDialog(null, e);		}
		return count;
	}
	
	//Close PreparedStatement
	public static void close(PreparedStatement p)
	{
		try 
		{	p.close();	}
		catch (SQLException e) 
		{	JOptionPane.showMessageDialog(null, e);		}
	}
	
	//Close Statement
	public static void close(Statement s)
	{
		try 
		{	s.close();	}
		catch (SQLException e) 
		{	JOptionPane.showMessageDialog(null, e);		}
	}
	
	
	//Call Stored Procedure updateAllStatus()
	public static void updateAllStatus()
	{
		try 
		{
	 		Connection con=createConnection();
	 		String procedure;
	 		procedure="call updateAllStatus()";  //updates the status of all donors
	 		//Calling Stored Procedure
	 		CallableStatement cs=con.prepareCall(procedure);
	 		cs.executeUpdate();   //executeUpdate is used because the callable statement updates the result
	 					  		  //cs.executeQuery will be used when the statement returns the result set.
	 		cs.close();
		}
		catch (SQLException e) 
		{	JOptionPane.showMessageDialog(null, e);		}

	}
	//Call setStatus
	public static void setStatus(String donorId)
	{	
		try 
		{
	 		Connection con=createConnection();
	 		//Now we will set the status of this donor to unavailable using Callable Statement
	 		String procedure;
	 		procedure="call setStatus('"+donorId+"')";  //updates the status of all donors
	 		//Calling Stored Procedure
	 		CallableStatement cs=con.prepareCall(procedure);
	 		cs.executeUpdate();   //executeUpdate is used because the callable statement updates the result
	 					  		  //cs.executeQuery will be used when the statement returns the result set.	 		
	 		cs.close();
		}
		catch (SQLException e) 
		{	JOptionPane.showMessageDialog(null, e);		}

}
}
