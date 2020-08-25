package com.socket;


import org.apache.commons.validator.routines.InetAddressValidator;

public class ValidateIP {
    public static void main(String[] args) {
        InetAddressValidator validator = new InetAddressValidator();
        String ipAddress = "192.168.0.321";
        boolean isValid = validator.isValid(ipAddress);
        if (isValid) {
            System.out.println(ipAddress + " is valid");
        } else {
            System.out.println(ipAddress + " is invalid");
        }
    }

}
