package com.company;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException
    {
	    
        Server server = new Server(7787); //7787
        server.start();
        
        InetAddress address = InetAddress.getByName("localhost"); //address to
        int port = 7788; //port to
        
        //server.send(new byte[] {'1', '2', '3'}, address, port);
        
        byte[] bytesDouble = Convert.toByteArray(3.1415);
        server.send(bytesDouble, address, port);
    }
}