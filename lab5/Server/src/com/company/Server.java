package com.company;

import java.net.DatagramSocket;
import java.net.SocketException;

public class Server {
    
    private int port;
    private boolean running;
    
    private DatagramSocket socket;
    
    {
        running = false;
    }
    public Server(int port)
    {
        
        this.port = port;
    }
    
    public void start() {
    
        try {
            socket = new DatagramSocket(port);
        }
        catch (SocketException e) {
            e.printStackTrace();
            return;
        }
        
        running = true;
    }
    
    
}

// DatagramSocket  и DatagramPacket