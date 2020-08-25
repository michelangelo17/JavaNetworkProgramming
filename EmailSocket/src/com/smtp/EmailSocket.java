package com.smtp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class EmailSocket {
    private static Socket smtpSocket;
    private static PrintWriter out;
    private static BufferedReader in;

    public static void main(String[] args) {
	    try {
	        smtpSocket = new Socket("localhost", 25);
	        in = new BufferedReader(new InputStreamReader(smtpSocket.getInputStream()));
	        out = new PrintWriter(smtpSocket.getOutputStream(), true);
        } catch (UnknownHostException uhe) {
            System.out.println("Issue with hostname: " + uhe.toString());
        } catch (IOException ioe) {
            System.out.println("Issue with IO: " + ioe.toString());
        }

	    if (smtpSocket != null && out != null && in != null) {
	        try {
	            String responseLine;
	            while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("220") != -1) {
                        break;
                    }
                }

	            out.println("HELO " + InetAddress.getLocalHost().getHostAddress());
                System.out.println("HELO " + InetAddress.getLocalHost().getHostAddress());
                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("250") != -1) {
                        break;
                    }
                }

                out.println("MAIL From: mytest@test.com");
                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("250") != -1) {
                        break;
                    }
                }

                out.println("RCPT TO: michelangelo.markus@gmail.com");
                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("250") != -1) {
                        break;
                    }
                }
                out.println("DATA");
                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("354") != -1) {
                        break;
                    }
                }
                out.println("From: mytest@test.com");
                out.println("To: michelangelo.markus@gmail.com");
                out.println("Subject: TEST EMAIL");
                out.println();
                out.println("Subject: TEST EMAIL");
                out.println("This is a test message");
                out.println("Thanks,");
                out.println("-Me");
                out.println();
                out.println(".");

                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("250") != -1) {
                        break;
                    }
                }

                out.println("QUIT");

                while ((responseLine = in.readLine()) != null) {
                    System.out.println("Server: " + responseLine);
                    if (responseLine.indexOf("221") != -1) {
                        break;
                    }
                }

                System.out.println("Email successfully sent!");

                out.close();
                in.close();
                smtpSocket.close();

            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }
    }
}
