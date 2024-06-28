package com.sean.base.chapter33;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Security;

/**
 * @Auther: xionghaiyang
 * @Date: 2022-12-05 19:20
 * @Description: TODO
 */
public class Hash {

    private MessageDigest hash;

    public Hash(String algorithm) {
        try {
            hash = MessageDigest.getInstance(algorithm);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public String hashCode(String input) {
        return DatatypeConverter.printHexBinary(hash.digest(input.getBytes())).toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println("支持的算法");
        for (String str : Security.getAlgorithms("MessageDigest")) {
            System.out.println(str);
        }
        String algorithm = "MD5";
        Hash hash = new Hash(algorithm);
        String input1 = "hello1";
        String input2 = "hello2";
        String input3 = "hello3";
        String input4 = "hello4";
        String input5 = "hello5";
        System.out.println(hash.hashCode(input1));
        System.out.println(hash.hashCode(input2));
        System.out.println(hash.hashCode(input3));
        System.out.println(hash.hashCode(input4));
        System.out.println(hash.hashCode(input5));
    }

}
