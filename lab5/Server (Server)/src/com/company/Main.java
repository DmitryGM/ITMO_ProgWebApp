package com.company;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException
    {
        int portServer = 7788;
        int clientPort = 7787;
        InetAddress clientAdress = InetAddress.getByName("localhost"); //ITMO IP: 77.234.212.37

        Server server = new Server(clientAdress, portServer, clientPort);
        server.start();
                        
        System.out.println("Server start");
    }
}

// SERVER