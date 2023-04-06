package com.xdu.nook.authserver.service.impl;

import com.xdu.nook.authserver.service.CodeFactory;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service("codeFactory")
public class CodeFactoryImpl implements CodeFactory {

    public String generateCode() {
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }
}
