package com.company;

import java.lang.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    
    public static void main(String[] args) throws UnknownHostException
    {
        int portServer = 7788;
        int clientPort = 7787;
        InetAddress address = InetAddress.getByName("localhost"); //address to
        
        Client client = new Client(portServer, clientPort, address);
        
        client.start(); //Start server (start waiting answer)
        
        //Send example
        
        byte[] bytesDouble = Convert.toByteArray(13.0, 11.0);
        client.send(bytesDouble);
        
        
        new MainFrame(client);
    }
}
