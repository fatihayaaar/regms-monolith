package com.fayardev.membershipsystem.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class IDGenerator {

    private static IDGenerator idGenerator;

    private IDGenerator() {

    }

    public static IDGenerator getInstance() {
        if (idGenerator == null) {
            idGenerator = new IDGenerator();
        }
        return idGenerator;
    }

    public static String mediaNameIDGenerator(int i) {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyMMddhhmmssMs");
        Random rnd = new Random();
        return ft.format(dNow) + i + rnd.nextInt(9999);
    }

    public int generate() {
        String str = ((new Date().getTime() / 1000L) % Integer.MAX_VALUE) / 1000 + "" + new Random().nextInt(99);
        return Integer.parseInt(str);
    }
}