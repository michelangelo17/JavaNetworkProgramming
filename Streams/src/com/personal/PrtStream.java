package com.personal;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class PrtStream {
    public static void main(String[] args) {
        try {
            PrintStream out = new PrintStream("example7.txt");
            int var1 = 10;
            out.println("The value of var1 is: " + var1);
            out.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.toString());
        }
    }
}
