package com.globalite.dangdang.common;

/**
 * Created by zhu on 2016/9/5.
 */
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class Utils {
    private Utils() {
    }

    public static String getAllProperty(Class<?> clazz, Object target) {
        StringBuffer buffer = new StringBuffer("");
        Method[] methods = clazz.getDeclaredMethods();
        Method[] var7 = methods;
        int var6 = methods.length;

        for(int var5 = 0; var5 < var6; ++var5) {
            Method method = var7[var5];
            String prefix = method.getName().substring(0, 3);
            if("get".equals(prefix)) {
                String value = "";

                try {
                    Object var12 = method.invoke(target, new Object[0]);
                    buffer.append(method.getName().substring(3)).append(":").append(var12).append("\r\n");
                } catch (Exception var11) {
                    buffer.append("[proxy]").append("\r\n");
                }
            }
        }

        buffer.append("================================================").append("\r\n");
        return buffer.toString();
    }

    public static String toMD5(String data) {
        if(data == null) {
            return null;
        } else {
            try {
                MessageDigest e = MessageDigest.getInstance("MD5");
                e.update(data.getBytes());
                String result = (new BigInteger(1, e.digest())).toString(16);
                return result;
            } catch (NoSuchAlgorithmException var3) {
                return null;
            }
        }
    }
}
