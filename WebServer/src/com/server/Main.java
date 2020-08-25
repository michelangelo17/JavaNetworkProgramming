package com.server;

import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            boolean isStop = false;

            while (!isStop) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client " + clientSocket.getInetAddress().getHostAddress() + " is connected");
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
