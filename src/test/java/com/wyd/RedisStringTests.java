package com.wyd;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyd.redis.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Map;

@SpringBootTest
class RedisStringTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private static final ObjectMapper mapper = new ObjectMapper();

    @Test
    void testString() {
        // 写入一条String数据
        stringRedisTemplate.opsForValue().set("name", "123");
        // 获取String数据
        Object name = stringRedisTemplate.opsForValue().get("name");
        System.out.println( "name = " +  name);
    }

    @Test
    void testSaveUser() throws JsonProcessingException {
        // 创建对象
        User user = new User("wyd", 21);
        // 手动序列化对象
        String value = mapper.writeValueAsString(user);
        // 写入数据
        stringRedisTemplate.opsForValue().set("user:100", value);

        // 获取数据
        String joinUser = stringRedisTemplate.opsForValue().get("user:100");
        // 反序列化
        User readValue = mapper.readValue(joinUser, User.class);
        System.out.println( "user = " + readValue);
    }

    @Test
    void testHash(){
        stringRedisTemplate.opsForHash().put("user:4", "name", "wyd11");
        stringRedisTemplate.opsForHash().put("user:4", "age", "22");

        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("user:4");
        System.out.println("entries: " + entries);
    }
}
