package com.vinnikov.clever.operator.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

public class KeyGenerator {

    public static String generateKey(String user, Date date) {
        String generate = "Clever";
        generate += 0 + (int) (Math.random() * 1000);
        generate += user;
        generate += date;
        generate += "Clever";

        return DigestUtils.md5Hex(generate).toUpperCase();
    }
}
