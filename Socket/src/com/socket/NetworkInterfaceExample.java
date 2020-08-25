package com.socket;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.List;

public class NetworkInterfaceExample {
    public static void main(String[] args) {
        try {
//            InetAddress address = InetAddress.getByName("172.17.0.1");
//            NetworkInterface networkInterface = NetworkInterface.getByName("docker0");
//            NetworkInterface networkInterface = NetworkInterface.getByInetAddress(address);

            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            while (nets.hasMoreElements()) {
                NetworkInterface networkInterface = nets.nextElement();

                if (networkInterface != null) {
                    System.out.println("NIC Name: " + networkInterface.getName());
                    System.out.println("NIC Display Name: " + networkInterface.getDisplayName());
                    System.out.println("NIC get hardware address: " + convertByteToString(networkInterface.getHardwareAddress()));
                    System.out.println("MTU: " + networkInterface.getMTU());
                    NetworkInterface parentInterface = networkInterface.getParent();
                    if (parentInterface != null) {
                        System.out.println("Parent Interface: " + parentInterface.getDisplayName());
                    } else {
                        System.out.println("No Parent Interface!");
                    }
                    System.out.println("Is loopback? " + networkInterface.isLoopback());
                    System.out.println("Is up? " + networkInterface.isUp());
                    System.out.println("Is virtual? " + networkInterface.isVirtual());
                    System.out.println("Support multicast? " + networkInterface.supportsMulticast());
                    List<InterfaceAddress> list = networkInterface.getInterfaceAddresses();
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println("IP Address: " + (list.get(i).getAddress().getHostAddress()));
                    }
                    System.out.println();
                    System.out.println("________________________________________________");
                    System.out.println();
                } else {
                    System.out.println("interface not found");
                }
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public static String convertByteToString(byte[] mac) {
        if (mac == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder(18);
        for (byte b : mac) {
            if (sb.length() > 0) {
                sb.append(":");
            }
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
