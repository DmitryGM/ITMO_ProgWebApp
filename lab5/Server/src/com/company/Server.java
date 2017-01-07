package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
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
    
    public void start()
    {
    
        try
        {
            socket = new DatagramSocket(port);
        } catch (SocketException e)
        {
            e.printStackTrace();
            return;
        }
        
        running = true;
    }
    
    private void listen()
    {
        
        while (running)
        {
            //TODO:....
            
        }
    }
    
    private void send(byte[] data, InetAddress address, int port)
    {
        
        assert (socket.isClosed());
    
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
        try
        {
            socket.send(packet);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}