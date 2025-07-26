package in.bushansirgur.restapi.config;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;

@Configuration
@Slf4j
public class RedisConfig {

    @Value("${spring.redis.host}")
    private String redisHost;

    @Value("${spring.redis.port}")
    private int redisPort;

    @Value("${spring.redis.password:}")
    private String redisPassword;

    @Bean(name = "customRedisTemplate")
    public StringRedisTemplate customRedisTemplate() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration();
        config.setHostName(redisHost);
        config.setPort(redisPort);
        if (redisPassword != null && !redisPassword.isEmpty()) {
            config.setPassword(redisPassword);
        }

        // ✅ 連線池設定
        GenericObjectPoolConfig<?> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(100);      // 最大總連線數（依你壓測併發調整）
        poolConfig.setMaxIdle(20);        // 最大 idle 數量
        poolConfig.setMinIdle(5);         // 最小 idle 數量
        poolConfig.setMaxWait(Duration.ofMillis(300));  // 最長等待取得連線時間

        // ✅ Lettuce client 設定 + 套用連線池
        LettuceClientConfiguration clientConfig = LettucePoolingClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(3))
                .poolConfig(poolConfig)
                .build();

        log.info("✅ Redis 正在建立連線...");
        log.info("🔗 Redis Host: {}", config.getHostName());
        log.info("🔗 Redis Port: {}", config.getPort());
        log.info("🔗 Redis Max Connections: {}", poolConfig.getMaxTotal());
        log.info("🔐 Redis 密碼是否有設定: {}", (redisPassword != null && !redisPassword.isEmpty()) ? "✅ 有" : "❌ 無");

        LettuceConnectionFactory factory = new LettuceConnectionFactory(config, clientConfig);
        factory.afterPropertiesSet(); // 初始化連線工廠

        return new StringRedisTemplate(factory);
    }
}
