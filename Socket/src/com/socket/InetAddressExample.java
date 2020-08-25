package com.socket;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressExample {
    public static void main(String[] args) {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println(address.getHostAddress());
            System.out.println(address.getHostName());
            InetAddress address1 = InetAddress.getByName("scanme.nmap.org");
            System.out.println(address1.getHostAddress());
            System.out.println(address1.getHostName());
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}
