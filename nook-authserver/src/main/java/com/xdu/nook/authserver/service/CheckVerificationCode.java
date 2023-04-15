package com.xdu.nook.authserver.service;

public interface CheckVerificationCode {
    public Boolean test(String email,String code);
}
