package in.bushansirgur.restapi.service;

import in.bushansirgur.restapi.entity.ProfileEntity;
import in.bushansirgur.restapi.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;
    private final StringRedisTemplate redisTemplate;
    private static final String REDIS_PREFIX = "user:auth:";
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        String redisKey = REDIS_PREFIX + email;
        // 嘗試從 Redis 取得密碼
        String encodedPassword = redisTemplate.opsForValue().get(redisKey);

        if (encodedPassword != null) {
            // Redis 有快取，直接用
            return new User(email, encodedPassword, new ArrayList<>());
        }

        ProfileEntity profile = profileRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Profile not found for the email "+email));
        log.info("Inside loadUserByUsername()::: priting the profile details {}", profile);
        // 把查到的帳號密碼快取進 Redis，設定有效時間（例如 1 小時）
        redisTemplate.opsForValue().set(redisKey, profile.getPassword(), Duration.ofHours(1));
        return new User(profile.getEmail(), profile.getPassword(), new ArrayList<>());
    }
}
