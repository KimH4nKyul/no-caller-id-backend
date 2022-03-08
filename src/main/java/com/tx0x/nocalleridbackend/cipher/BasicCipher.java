package com.tx0x.nocalleridbackend.cipher;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class BasicCipher {

    public String encode(String value) throws NoSuchAlgorithmException {
        if (value == null) {
            throw new IllegalArgumentException("[BasicCipher] No value");
        }
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(value.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for(int i=0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }

        return sb.toString();
    }



}
