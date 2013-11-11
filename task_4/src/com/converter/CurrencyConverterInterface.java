package com.converter;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CurrencyConverterInterface extends Remote {
	
	public Double convert(String from, String to, Double amount) throws RemoteException;
}
