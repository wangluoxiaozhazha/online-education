package com.city.base.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Component
public class Encryption {

    public String encryptionMD5(String password){
        return DigestUtils.md5DigestAsHex(password.getBytes());
    }

    @Bean
    public Encryption getEncryption(){
        return new Encryption();
    }
}
