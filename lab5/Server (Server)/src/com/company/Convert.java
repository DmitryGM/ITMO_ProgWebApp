package com.company;

import java.nio.ByteBuffer;

public class Convert {
    
    public static byte[] toByteArray(Boolean bool) {
        
        byte[] bytes = new byte[1];
        
        if(bool == true)
            bytes[0] = 1;
        else
            bytes[0] = 0;
        
        return bytes;
    }
    
    public static byte[] toByteArray(double value1, double value2) {
        
        byte[] bytes = new byte[2 * 8];
        ByteBuffer.wrap(bytes).putDouble(value1);
        ByteBuffer.wrap(bytes, 8, 8).putDouble(value2); // Ok ?!
        return bytes;
    }
    
    public static void toDouble(byte[] bytes, Double[] doubles) {
        
        double d1 = ByteBuffer.wrap(bytes, 0, 8).getDouble();
        double d2 = ByteBuffer.wrap(bytes, 8, 8).getDouble();
        
        System.out.println("d1 = " + d1 + "; d2 = " + d2);
    
        doubles[0] = d1;
        doubles[1] = d2;
    }
}