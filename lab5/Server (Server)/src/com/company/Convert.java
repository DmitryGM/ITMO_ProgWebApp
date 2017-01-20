package com.company;

import java.nio.ByteBuffer;

public class Convert {
    
    public static void toDouble(byte[] bytes, Double[] doubles) {
        
        double d1 = ByteBuffer.wrap(bytes, 0, 8).getDouble();
        double d2 = ByteBuffer.wrap(bytes, 8, 8).getDouble();
        double d3 = ByteBuffer.wrap(bytes, 16, 8).getDouble();

        // Debug
        System.out.println("d1 = " + d1 + "; d2 = " + d2 + "; d3 = " + d3);
    
        doubles[0] = d1;
        doubles[1] = d2;
        doubles[2] = d3;
    }
}
