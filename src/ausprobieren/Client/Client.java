package ausprobieren.client;

import java.rmi.Naming;

import ausprobieren.server.ServerInterface;


public class Client {

	public static void main(String[] args) {
	    try {
	        ServerInterface server = (ServerInterface)Naming.lookup("//127.0.0.0/Server");
	        System.out.println("L�sung: " +server.calc(5,12));
	      }
	      catch (Exception ex)
	      {
	    	  System.out.println(ex);
	      }
	}
}
