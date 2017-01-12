package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    
    private boolean running;
    
    private Thread listenThread;
    private DatagramSocket socket;
    
    private InetAddress address;
    private int portFrom;
    private int portTo;
    
    {
        running = false;
    }
    
    public Client(int portTo, int portFrom, InetAddress address)
    {
        this.address = address;
        this.portTo = portTo;
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
        int pacSize = 1;
        byte data[] = new byte[pacSize];
        
        DatagramPacket packet = new DatagramPacket(data, data.length);
        
        while (running)
        {
            socket.receive(packet);
            
            Boolean bool = Convert.toBoolean(data);
            
            if (bool == true)
            {
                System.out.println("true");
            }
            else
            {
                System.out.println("false");
            }
        }
    }
    
    public void send(byte[] data)
    {
        //assert (socket.isClosed()); // ?
        
        DatagramPacket packet = new DatagramPacket(data, data.length, this.address, this.portTo);
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