package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.DoubleSummaryStatistics;

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
        
        listenThread.start(); //new Thread !
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
    
            Double[] doubles = new Double[2];
            Convert.toDouble(data, doubles);
            
            double d1 = doubles[0];
            double d2 = doubles[1];
            
            if (d1*d1 + d2*d2 < 1)
            {
                send(new byte[]{1}, InetAddress.getByName("localhost"), clientPort); //Answer
            }
            else
            {
                send(new byte[]{0}, InetAddress.getByName("localhost"), clientPort); //Answer
            }
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