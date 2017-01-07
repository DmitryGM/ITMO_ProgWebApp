package com.company;

import java.io.Console;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.DoubleSummaryStatistics;

public class Server {
    
    private int port;
    private boolean running;
    
    private Thread listenThread;
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
    
        try {
            socket = new DatagramSocket(port);
        }
        catch (SocketException e) {
            e.printStackTrace();
            return;
        }
        
        running = true;
        
        listenThread = new Thread(() -> listen());
        listenThread.start();
    }
    
    private void listen() {
        try
        {
            acceptFile(7788, 8);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private void acceptFile(int port, int pacSize) throws IOException {
        
        byte data[] = new byte[pacSize];
    
        DatagramPacket pac = new DatagramPacket(data, data.length);
        DatagramSocket s = new DatagramSocket(port);
        
        s.setSoTimeout(60000);
        
        while (running)
        {
            s.receive(pac);
            
            double getDouble = Convert.toDouble(data);
            
            System.out.println("Data: " + getDouble);
        }
    }
    
    public void send(byte[] data, InetAddress address, int port) {
        
        //assert (socket.isClosed());
    
        DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
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