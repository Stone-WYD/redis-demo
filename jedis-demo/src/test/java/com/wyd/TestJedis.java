package com.wyd;

import com.wyd.jedis.util.JedisConnecitonFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

import java.util.Map;

public class TestJedis {

    private Jedis jedis;

    @BeforeEach
    void setUp(){
        // 建立连接
        //jedis = new Jedis("192.168.31.76", 6379);
        jedis = JedisConnecitonFactory.getJedis();
        // 设置密码
        jedis.auth("123456");
        // 设置库
        jedis.select(0);
    }

    @Test
    void testString(){
        // 存入数据
        String result = jedis.set("name", "testJedis");
        System.out.println("result=" +  result);
        // 获取数据
        String name = jedis.get("name");
        System.out.println("name=" + name);
    }

    @Test
    void testHahs(){
        jedis.hset("user:1", "name", "testHash");
        jedis.hset("user:1", "age", "21");

        // 获取
        Map<String, String> map = jedis.hgetAll("user:1");
        System.out.println(map);
    }

    @AfterEach
    void close(){
        if (jedis != null) {
            jedis.close();
        }
    }
}
