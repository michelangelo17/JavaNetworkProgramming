package com.server;

import java.io.*;
import java.net.Socket;
import java.util.Date;

public class ClientThread extends Thread {
    final static String CRLF = "\r\n";
    private Socket socket;
    private boolean isStop;
    private BufferedReader in;
    private PrintWriter out;
    private File file;

    public ClientThread(Socket socket) {
        this.socket = socket;
        this.isStop = false;
    }

    public void run() {
        try {
            while (!isStop) {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
                String line;
                String httpHeader = "";
                String htmlFile = "";

                while (true) {
                    line = in.readLine();
                    if (line.equals(CRLF) || line.equals("")) {
                        break;
                    }
                    httpHeader = httpHeader + line + "\n";
                    if (line.contains("GET")) {
                        int beginIndex = line.indexOf("/");
                        int endIndex = line.indexOf(" HTTP");
                        htmlFile = line.substring(beginIndex + 1, endIndex);
                    }
                }

                processRequest(htmlFile);
                closeConnection();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void processRequest(String htmlFile) throws Exception {
        File file = new File(htmlFile);
        if (file.exists()) {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            out.print("HTTP/1.0 200 OK" + CRLF);
            Date date = new Date();
            out.print("Date: " + date.toString() + CRLF);
            out.print("Server: java tiny web server" + CRLF);
            out.print("Content-Type: text/html" + CRLF);
            out.print("Content-Length: " + file.length() + CRLF);
            out.println("Content-Type: text/html; charset=iso-8859-1" + CRLF);

            String line = "";
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        } else {
            out.print("HTTP/1.1 404 Not Found" + CRLF);
            Date date = new Date();
            out.print("Date: " + date.toString() + CRLF);
            out.print("Server: java tiny web server" + CRLF);
            out.print("Connection: close" + CRLF);
            out.println("Content-Type: text/html; charset=iso-8859-1" + CRLF);

            out.println("<html><head>");
            out.println("<title>404 Not Found</title>");
            out.println("<h1>404 Not Found</h1>");
        }
    }

    private void closeConnection() {
        try {
            out.close();
            in.close();
            socket.close();
            isStop = true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
