package com.converter;

import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class CurrencyConverterServer extends UnicastRemoteObject implements CurrencyConverterInterface {
	private Double EURtoUSD = 1.34;
	private Double EURtoGBP = 0.83;
	private Double USDtoGBP = 0.62;
	
	private static final long serialVersionUID = 1L;
	
	public CurrencyConverterServer() throws RemoteException {
    }

	public Double convert(String from, String to, Double amount) throws RemoteException {
		if (from.equals("EUR")) {
			if (to.equals("USD"))
				return amount * EURtoUSD;
			else if (to.equals("GBP"))
				return amount * EURtoGBP;
		}
		else if (from.equals("USD")) {
			if (to.equals("EUR"))
				return amount * (1/EURtoUSD);
			else if (to.equals("GBP"))
				return amount * USDtoGBP;
		}
		else if (from.equals("GBP")) {
			if (to.equals("EUR"))
				return amount * 1/EURtoGBP;
			else if (to.equals("USD"))
				return amount * 1/USDtoGBP;
		}
		throw new RemoteException("Sorry, the currency converter from " + 
									from + " to " + to + " is not available");
	}
	
	public static void main(String args[]) throws RemoteException {
		try {
	        // install a security manager (uses a security policy)
	        if (System.getSecurityManager() == null) {
	            RMISecurityManager sm = new RMISecurityManager();
	            System.setSecurityManager(sm);
	        }
	 
	        // create rmi registry
	        LocateRegistry.createRegistry(8000); 
	  
	        // create remote object
	        CurrencyConverterServer obj = new CurrencyConverterServer();
	  
	        // Bind the object instance to the name "HelloRMI"
	        // 0.0.0.0 denotes the service will listen on all network interfaces
	        Naming.rebind("//0.0.0.0:8000/HelloRMI", obj);
	         
	        System.out.println("RMI server started, " + 
	            "listening to incoming requests...");
	    } catch (Exception e) {
	        System.out.println("Error occurred: " + e.getLocalizedMessage());
	    }
	}
}
