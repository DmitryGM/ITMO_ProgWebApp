package com.company.client;

import com.company.circle.Circle;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Client {
    
    private boolean running;
    private boolean answer;
    
    private Thread listenThread;
    private DatagramSocket socket;
    
    private InetAddress address;
    private int portFrom;
    private int portTo;
    
    {
        answer = false;
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
    
    public void stop()
    {
        running = false;
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
                answer = true;
                System.out.println("Answer: true"); // Debug
                
                this.stop();
            }
            else
            {
                answer = false;
                System.out.println("Answer: false"); // Debug
    
                this.stop();
            }
        }
        
        this.stop();
        System.out.println("this.stop() from listen()"); // Debug
    }
    
    public synchronized Circle.State send(byte[] data)
    {
        DatagramPacket packet = new DatagramPacket(data, data.length, this.address, this.portTo);
        
        try
        {
            long time_up = System.currentTimeMillis();
            
            socket.send(packet);
            this.start(); //wait answer
            System.out.println("Wait answer..."); // Debug
    
            long difference = 0;
            
            while (running && difference < 1000)
            {
                // Wait 1000 ms = 1 s
                difference = System.currentTimeMillis() - time_up;
                
                System.out.print(running); //the quantum physics
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        if (running == false)
        {
            if(answer)
                return Circle.State.InArea;
            else
                return Circle.State.OutArea;
        }
        
        System.out.println("this.stop() from send()"); // Debug
        
        this.stop();
        return Circle.State.Unknown;
    }
}