package com.globalite.dangdang.common;

/**
 * Created by zhu on 2016/9/5.
 */
import java.io.IOException;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {
    public Base64() {
    }

    public static String encode(byte[] bytes) {
        return (new BASE64Encoder()).encode(bytes);
    }

    public static byte[] decode(String string) {
        byte[] bytes = null;

        try {
            bytes = (new BASE64Decoder()).decodeBuffer(string);
        } catch (IOException var3) {
            var3.printStackTrace();
        }

        return bytes;
    }
}
