package com.personal;

import java.io.FileOutputStream;

public class OutStream {
    public static void main(String[] args) {
        try {
            FileOutputStream fileOutput = new FileOutputStream("example1.txt", true);
            char H = 'H';
            fileOutput.write((char) H);
            String string = "Hello World!";
            fileOutput.write(string.getBytes());
            fileOutput.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
