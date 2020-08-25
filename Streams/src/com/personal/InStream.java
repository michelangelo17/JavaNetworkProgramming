package com.personal;

import java.io.FileInputStream;

public class InStream {

    public static void main(String[] args) {
        try {
            FileInputStream inputStream = new FileInputStream("example.txt");
            int data = inputStream.read();
            while (data != -1) {
                System.out.print((char) data);
                data = inputStream.read();
            }
            inputStream.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
