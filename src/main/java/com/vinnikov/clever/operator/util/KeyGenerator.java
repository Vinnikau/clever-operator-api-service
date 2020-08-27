package com.vinnikov.clever.operator.util;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

public class KeyGenerator {

    public static String generateKey(String user, Date date) {
        String generate = "Clever";
        generate += (int) (Math.random() * 1000);
        generate += user;
        generate += date;
        generate += "Clever";

        return generate(user, date, (int) (Math.random() * 1000));
    }

    public static String generate(String user, Date date, int random) {
        String generate = "Clever" + random + user + date + "Clever";
        return DigestUtils.md5Hex(generate).toUpperCase();
    }
}
