package com.url;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class URLExample {

    public static void main(String[] args) throws Exception {
	    URL url = new URL("https://finance.yahoo.com/quote/ORCL?ltr=1");
        URLConnection myURL = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(myURL.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            if (inputLine.contains("data-reactid=\"50\"")) {
                System.out.println(inputLine);
            }
        }
        in.close();
    }
}
