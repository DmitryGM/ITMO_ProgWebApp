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
    
    private int port; // my server port
    private int clientPort;
    
    {
        running = false;
    }
    
    public Server(int port, int clientPort)
    {
        this.port = port;
        this.clientPort = clientPort;
        
        try
        {
            socket = new DatagramSocket(this.port);
        } catch (SocketException e)
        {
            e.printStackTrace();
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
        int pacSize = 2*8;
        byte data[] = new byte[pacSize];
        
        DatagramPacket packet = new DatagramPacket(data, data.length);
        
        while (running)
        {
            socket.receive(packet);
            
            double getDouble = Convert.toDouble1(data); //#2
            
            send(new byte[]{1,2,3,4,5,6,7,8}, InetAddress.getByName("localhost"), clientPort); //Answer
            
            System.out.println("Data: " + getDouble);
        }
    }
    
    private void send(byte[] data, InetAddress address, int portTo)
    {
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