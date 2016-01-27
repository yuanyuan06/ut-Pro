package com.utComm;

import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Administrator on 2016/1/12.
 */
public class TestMD5 {

    @Test
    public void test1() throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update("袁渊".getBytes());
        byte[] bytes = md.digest();
        StringBuffer sb = new StringBuffer();
        for(byte b: bytes){
            sb.append(Integer.toHexString(b&0xff));
        }
        System.out.println(sb);
//        e98289ac8e420f0eb7f0b6a872e0bc

//        b7c8fb53e2c95ec697dfc9be2ed10e9
//        ea1a45f5f48d5fe418ed723dc76c54d
    }


}
