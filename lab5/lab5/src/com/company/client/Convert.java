package com.company.client;

import java.nio.ByteBuffer;

public class Convert {

    public static byte[] toByteArray(double value1, double value2, double value3) {
        
        byte[] bytes = new byte[3*8]; // 2 -> 3
        ByteBuffer.wrap(bytes, 0, 8).putDouble(value1);
        ByteBuffer.wrap(bytes, 8, 8).putDouble(value2);
        ByteBuffer.wrap(bytes, 16, 8).putDouble(value3); //???
        return bytes;
    }
    
    public static Boolean toBoolean(byte[] data)
    {
        if(data[0] == 1)
            return true;
        else
            return false;
    }
}
