package com.xdu.nook.api.utils;

import java.util.UUID;

public class CallNumberUtils {

    private static UUID uuid;

    public static String getCallNumber() {
        return getCallNumber(true);
    }

    public static String getCallNumber(boolean withHyphen) {
        if(true == withHyphen) {
            return getUUID();
        }
        return removeHyphen(getUUID());
    }

    public static String removeHyphen(String uuid) {
        StringBuilder sb = new StringBuilder(uuid);
        sb.deleteCharAt(8);
        sb.deleteCharAt(12);
        sb.deleteCharAt(16);
        sb.deleteCharAt(20);
        return sb.toString();
    }
    private static String getUUID() {
        uuid = UUID.randomUUID();
        return uuid.toString();
    }


}
