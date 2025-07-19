package in.bushansirgur.restapi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RedisStartupTester implements CommandLineRunner {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public void run(String... args) throws Exception {
        redisTemplate.opsForValue().set("startup:test", "ok", Duration.ofMinutes(5));
        String value = redisTemplate.opsForValue().get("startup:test");
        System.out.println("✅ Redis 測試值: " + value);
    }
}

