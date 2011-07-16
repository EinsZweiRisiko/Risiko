package ausprobieren.Client;

import java.rmi.Naming;

import ausprobieren.Server.ServerInterface;

public class Client {

	public static void main(String[] args) {
	    try {
	        ServerInterface server = (ServerInterface)Naming.lookup("//127.0.0.1/Server");
	        System.out.println("L�sung: " +server.calc(1,1));
	      }
	      catch (Exception ex)
	      {
	    	  System.out.println(ex);
	      }
	}
}
