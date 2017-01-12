package com.company;

import java.nio.ByteBuffer;

public class Convert {
    
    public static byte[] toByteArray(double value) {
        
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(value);
        return bytes;
    }
    
    public static byte[] toByteArray(double value1, double value2) {
        
        byte[] bytes = new byte[2*8];
        ByteBuffer.wrap(bytes, 0, 8).putDouble(value1);
        ByteBuffer.wrap(bytes, 8, 8).putDouble(value2); // Ok ?!
        return bytes;
    }
    
    public static double toDouble(byte[] bytes) {
        
        return ByteBuffer.wrap(bytes).getDouble();
    }
    
    public static double toDouble1(byte[] bytes) {
        
        double d1 = ByteBuffer.wrap(bytes, 8, 8).getDouble();
        double d2 = ByteBuffer.wrap(bytes, 8, 8).getDouble();
        
        System.out.println("d1 = " + d1 + "; d2 = " + d2);
        
        return 0;
    }
}
