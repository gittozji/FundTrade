package me.zji.utils;

import org.apache.shiro.crypto.hash.Hash;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Test;

import java.security.NoSuchAlgorithmException;

/**
 * Created by imyu on 2017/2/13.
 */
public class Md5UtilTest {
    @Test
    public void test() {
        try {
            String s = Md5Util.getMd5("1234");
            s = Md5Util.getMd5(s);
            s = Md5Util.getMd5(s);
            System.out.println(s);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test1() {
        Hash hash = new SimpleHash("md5","1234",null,3);
        String password_cipherText = hash.getBytes().toString();
        System.out.println(password_cipherText);
    }
}
