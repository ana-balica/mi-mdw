package com.converter;

import java.rmi.Naming;
 
public class Client { 
     
    public static void main(String args[]) throws Exception {
        // get a reference to the remote object
        // assuming the server is running on the localhost
    	CurrencyConverterInterface o = (CurrencyConverterInterface)
            Naming.lookup("//localhost/HelloRMI");
             
        // call the object method
        System.out.println(o.convert("USD", "EUR", 132.4));
        System.out.println(o.convert("EUR", "USD", 74.3));
        System.out.println(o.convert("EUR", "GBP", 998.66));
    }
}