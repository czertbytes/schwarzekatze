package de.czertbytes.schwarzekatze.core.config.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class CoreRepositoryRedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        final JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        final JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();

        jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
        jedisConnectionFactory.setHostName("localhost");
        jedisConnectionFactory.setPassword("");

        return jedisConnectionFactory;
    }


    @Bean
    public StringRedisTemplate redisTemplate() {
        final StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();

        stringRedisTemplate.setConnectionFactory(jedisConnectionFactory());

        return stringRedisTemplate;
    }

    @Bean
    public ValueOperations<String, String> redisValueOperations() {
        return redisTemplate().opsForValue();
    }
}
