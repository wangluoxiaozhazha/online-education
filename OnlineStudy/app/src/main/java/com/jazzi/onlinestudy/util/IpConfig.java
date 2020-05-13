package com.jazzi.onlinestudy.util;

public class IpConfig {
//    private static final String ipAddress="http://192.168.43.101";
    private static String ipAddress="http://112.126.74.242:8082";
    public static String getIp(){
        return ipAddress;
    }
    public static void setIP(String ip){
        ipAddress="http://"+ip;
    }
}
