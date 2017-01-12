package com.company;

import java.lang.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    
    public static void main(String[] args) throws UnknownHostException
    {
    
        int portServer = 7788;
        int clientPort = 7787;
        
        
        Client client = new Client(portServer, clientPort);
        
        client.start(); //Start server (start waiting answer)
              
        
        //Send example
        InetAddress address = InetAddress.getByName("localhost"); //address to
        
        byte[] bytesDouble2 = Convert.toByteArray(13.0, 11.0);
        client.send(bytesDouble2, address, portServer);
        
    
        new MainFrame(client);
    }
}
