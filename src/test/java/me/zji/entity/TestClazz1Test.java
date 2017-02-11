package me.zji.entity;

import me.zji.config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by imyu on 2017/1/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= RootConfig.class)
public class TestClazz1Test {
    @Autowired
    TestClazz1 testClazz1;
    @Test
    public void test() {
        testClazz1.say();
    }
}