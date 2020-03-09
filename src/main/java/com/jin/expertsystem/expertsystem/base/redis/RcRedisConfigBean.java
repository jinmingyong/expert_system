package com.jin.expertsystem.expertsystem.base.redis;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author GaoLiwei
 * @date 2019/4/15
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RcRedisConfigBean {

    private String host;

    private int port;

    private String password;

    private int timeout;

    private int database;


    @Value("${spring.redis.jedis.pool.max-idle}")
    private int maxIdle;

    @Value("${spring.redis.jedis.pool.max-active}")
    private int maxActive;

    @Value("${spring.redis.jedis.pool.min-idle}")
    private int minIdle;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private int maxWait;



}
