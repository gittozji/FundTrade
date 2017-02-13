package me.zji.utils;

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
}
