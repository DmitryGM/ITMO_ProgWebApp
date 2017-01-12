package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    
    private boolean running;
    
    private Thread listenThread;
    private DatagramSocket socket;
    
    private int portFrom;
    //private int portTo;
    
    {
        running = false;
    }
    
    public Server(int portTo, int portFrom)
    {
        //this.portTo = portTo;
        this.portFrom = portFrom;
        
        try {
            socket = new DatagramSocket(portFrom);
        }
        catch (SocketException e) {
            e.printStackTrace();
            return;
        }
    }
    
    public void start()
    {
        listenThread = new Thread(() ->
        {
            try
            {
                listen();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        });
    
        listenThread.start();
        running = true;
    }
    
    private void listen() throws IOException
    {
        int pacSize = 8;
        byte data[] = new byte[pacSize];
        
        DatagramPacket packet = new DatagramPacket(data, data.length);
        
        while (running)
        {
            socket.receive(packet);
            
            double getDouble = Convert.toDouble(data);
            
            System.out.println("Data: " + getDouble);
        }
    }
    
    public void send(byte[] data, InetAddress address, int portTo)
    {
        //assert (socket.isClosed()); // ?
    
        DatagramPacket packet = new DatagramPacket(data, data.length, address, portTo);
        try
        {
            socket.send(packet);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}