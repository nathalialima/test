package br.com.concrete.teste.util;

//import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public final class Util {

//    private PasswordEncoder passwordEncoder;

    public String encrypt(String key){
        return key;
//        return passwordEncoder.encode(key);
    }
}
