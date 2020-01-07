package com.Bdms;
public class MainDriver 
{
	public static void main(String [] args)
	{
		//Creating OBJECT of splash screen
		Splash s=new Splash();
		//Creating thread of splash
		Thread t1=new Thread(s);
		t1.start(); 
	}

}
