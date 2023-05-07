package com.xdu.nook.api;

import com.xdu.nook.api.utils.CallNumberUtils;

public class TestDemo {
    public static void main(String[] args) {
        String callNumber = CallNumberUtils.getCallNumber();
        System.out.println(callNumber);
    }
}
