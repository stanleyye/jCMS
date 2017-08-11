package jcms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

// TODO: Set a timeout for caching
public class RedisCacheConfig extends CachingConfigurerSupport {
	@Value("${redisHostName}")
	private String redisHostName;

	@Value("${redisPortNumber}")
	private Integer redisPortNumber;

	/**
	 * Create a factory that uses Jedis (a Redis java client) based connections.
	 * @return a jedis connection factory.
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(redisHostName);
		jedisConnectionFactory.setPort(redisPortNumber);
		return jedisConnectionFactory;
	}

	/**
	 * Create an abstraction for Redis interactions. It takes care of connection
	 * management and serialization.
	 * @return the redis interaction abstraction
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setExposeConnection(true);
		return redisTemplate;
	}

	@Bean
	public RedisCacheManager redisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
		redisCacheManager.setTransactionAware(true);
		redisCacheManager.setLoadRemoteCachesOnStartup(true);
		redisCacheManager.setUsePrefix(true);
		return redisCacheManager;
	}
}
