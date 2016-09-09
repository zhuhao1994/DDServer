//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.globalite.dangdang.advice;

import java.lang.reflect.Method;
import org.apache.log4j.Logger;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class LogAdvice implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {
    private Logger logger = null;

    public LogAdvice() {
    }

    public void afterThrowing(Throwable error) {
        StringBuffer sb = new StringBuffer(error.toString());

        for(int i = 0; i < error.getStackTrace().length; ++i) {
            System.out.println(error.getStackTrace()[i].getClassName() + "." + error.getStackTrace()[i].getMethodName() + "(" + error.getStackTrace()[i].getFileName() + ":" + error.getStackTrace()[i].getLineNumber() + ")");
        }

        this.logger = Logger.getLogger(this.getClass());
        this.logger.error(sb.toString());
    }

    public void before(Method method, Object[] params, Object target) throws Throwable {
        String message = "[METHOD BEGIN]-->[Method:" + method.getName() + "] of [Class:" + target + "]";
        message = message + "current parameters are:[";
        Object[] var8 = params;
        int var7 = params.length;

        for(int var6 = 0; var6 < var7; ++var6) {
            Object param = var8[var6];
            message = message + param + ",";
        }

        message = message + "]";
        this.logger = Logger.getLogger(target.getClass());
        this.logger.info(message);
    }

    public void afterReturning(Object ret, Method method, Object[] params, Object target) throws Throwable {
        String message = "[METHOD END]-->[Method:" + method.getName() + "] of [Class:" + target + "]";
        message = message + "return value is:[" + ret + "] ";
        message = message + "current parameters are:[";
        Object[] var9 = params;
        int var8 = params.length;

        for(int var7 = 0; var7 < var8; ++var7) {
            Object param = var9[var7];
            message = message + param + ",";
        }

        message = message + "]";
        this.logger = Logger.getLogger(target.getClass());
        this.logger.info(message);
    }
}
