package com.helesto.util;

public class FixSeparator {

    public static String putFixSeparator(String message) {
        return message.replaceAll("", " | ");
    }

}
