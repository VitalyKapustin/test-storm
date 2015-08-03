package com.insart.titanium.storm.util;

import com.insart.titanium.storm.entity.Transaction;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by v.kapustin on 8/3/15.
 */
public class CommonUtils {

    public static String getLogMessage(Class<?> clazz, Transaction transaction) {
        try {
            return new StringBuilder("{hostname=").append(InetAddress.getLocalHost().getHostName()).append(", ")
                    .append("module=").append(clazz.getSimpleName()).append(", ")
                    .append(transaction.toString()).append("}").toString();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
}
