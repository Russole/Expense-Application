package in.bushansirgur.restapi.service;

import in.bushansirgur.restapi.entity.ProfileEntity;
import in.bushansirgur.restapi.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;

@RequiredArgsConstructor
@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;
    // 自訂 RedisTemplate（例如指向不同節點）
    @Qualifier("customRedisTemplate")
    private final StringRedisTemplate customRedisTemplate;

    private static final String REDIS_PREFIX = "user:auth:";

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String redisKey = REDIS_PREFIX + email;

        try {
            String encodedPassword = customRedisTemplate.opsForValue().get(redisKey);
            if (encodedPassword != null) {
                log.info("Redis 查詢 key={}, value={}", redisKey, encodedPassword);
                return new User(email, encodedPassword, new ArrayList<>());
            }
        } catch (Exception ex) {
            // 只要 catch 被觸發，你就會看到 log
            log.error("Redis 連線失敗：{}", ex.getMessage(), ex);
            // 可以選擇 fallback，或繼續向 DB 查詢
        }

        ProfileEntity profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Profile not found for the email " + email));

        customRedisTemplate.opsForValue().set(redisKey, profile.getPassword(), Duration.ofHours(1));
        return new User(profile.getEmail(), profile.getPassword(), new ArrayList<>());
    }
}
