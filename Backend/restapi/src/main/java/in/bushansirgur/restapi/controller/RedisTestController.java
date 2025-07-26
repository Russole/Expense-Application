package in.bushansirgur.restapi.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class RedisTestController {

    // 自訂 RedisTemplate（例如指向不同節點）
    @Qualifier("customRedisTemplate")
    private final StringRedisTemplate customRedisTemplate;

    @GetMapping("/redis/ping")
    public String redisPing(@RequestParam(defaultValue = "Jack1@gmail.com") String email) {
        String value = customRedisTemplate.opsForValue().get("user:auth:" + email);
        return value != null ? value : "MISS";
    }
}


