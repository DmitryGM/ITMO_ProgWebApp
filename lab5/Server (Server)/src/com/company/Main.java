package com.company;

import java.net.UnknownHostException;

public class Main {

    public static void main(String[] args) throws UnknownHostException
    {
        int portServer = 7788;
        int clientPort = 7787;
        
        Server server = new Server(portServer, clientPort);
        server.start();
                        
        System.out.println("Server start");
    }
}

//SERVER