package com.ftp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9090);
            boolean isStopped = false;
            while (!isStopped) {
                Socket clientSocket = serverSocket.accept();
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThread.start();
            }
        } catch (IOException ioe) {
            System.out.println("Port 9090 is already opened! Please use another port.");
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
