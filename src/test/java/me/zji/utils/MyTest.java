package me.zji.utils;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的测试类
 * Created by imyu on 2017/2/23.
 */
public class MyTest {
    @Test
    public void testHashMapGet() {
        Map map = new HashMap();
        map.put("xx","true");
        System.out.println("true".equals(map.get("xx")));
    }

}
