package com.company;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException
    {
        int portServer = 7788;
        int clientPort = 7787;
        
        Server server = new Server(portServer, clientPort);
        server.start();
        
        InetAddress address = InetAddress.getByName("localhost"); //address to
        
        //server.send(new byte[] {'1', '2', '3'}, address, port);
        
        //# Send 1
        //byte[] bytesDouble = Convert.toByteArray(13.0);
        //server.send(bytesDouble, address, portServer);
    
        //# Send 2
        byte[] bytesDouble2 = Convert.toByteArray(13.0, 11.0);
        server.send(bytesDouble2, address, portServer);
    }
}

//CLIENT