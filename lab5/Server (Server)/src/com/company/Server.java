package com.company;

import java.awt.*;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Server {
    
    private boolean running;
    
    private Thread listenThread;
    private DatagramSocket socket;
    
    private int serverPort; // my server port
    private int clientPort;
    private InetAddress clientAdress;
    
    {
        running = false;
    }
    
    public Server(InetAddress clientAdress, int serverPort, int clientPort)
    {
        this.serverPort = serverPort;
        this.clientPort = clientPort;
        this.clientAdress = clientAdress;
        
        try
        {
            socket = new DatagramSocket(serverPort);
        } catch (SocketException e)
        {
            e.printStackTrace();
        }
    }
    
    public void start()
    {
        new Thread(new Runnable()
        {
            public void run()
            {
                try
                {
                    listen();
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }).start();
        
        running = true;
    }
    
    private void listen() throws IOException
    {
        int pacSize = 3*8; // 2 -> 3
        byte data[] = new byte[pacSize];
        
        DatagramPacket packet = new DatagramPacket(data, data.length);
        
        while (running)
        {
            socket.receive(packet);
    
            Double[] doubles = new Double[3]; // 2 -> 3
            Convert.toDouble(data, doubles);
            
            double d1 = doubles[0];
            double d2 = doubles[1];
            double d3 = doubles[2];
            
            if (BlueArea.isInArea(new Point((int)d1, (int)d2), (int)d3))
            {
                send(new byte[]{1}, clientAdress, clientPort); //Answer
            }
            else
            {
                send(new byte[]{0}, clientAdress, clientPort); //Answer
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