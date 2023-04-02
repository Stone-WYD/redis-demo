package com.wyd;

import com.wyd.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class RedisDemoApplicationTests {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void testString() {
        // 写入一条String数据
        redisTemplate.opsForValue().set("name", "王玉东");
        // 获取String数据
        Object name = redisTemplate.opsForValue().get("name");
        System.out.println( "name = " +  name);
    }

    @Test
    void testSaveUser(){
        redisTemplate.opsForValue().set("user:100", new User("王玉东", 21));
        User user = (User) redisTemplate.opsForValue().get("user:100");
        System.out.println( "user = " + user);
    }

}
