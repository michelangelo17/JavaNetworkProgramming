package com.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    public static void main(String[] args) {
        try {
            InetAddress serverAddress = InetAddress.getByName("localhost");
            System.out.println("server ip address " + serverAddress.getHostAddress());
            Socket socket = new Socket(serverAddress, 8080);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println(in.readLine());
            out.println("Hello server!");
            in.close();
            out.close();
            socket.close();
        } catch (UnknownHostException e1) {
            System.out.println("Unknown Host Exception " + e1.toString());
        } catch (IOException e2) {
            System.out.println("IO Exception " + e2.toString());
        } catch (IllegalArgumentException e3) {
            System.out.println("Illegal Argument Exception" + e3.toString());
        } catch (Exception e4) {
            System.out.println("Other Exceptions: " + e4.toString());
        }
    }
}
