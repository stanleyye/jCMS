package jcms.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.lang.reflect.Method;

@Configuration
@EnableCaching
public class RedisCacheConfig extends CachingConfigurerSupport {
	@Value("${redisHostName}")
	private String redisHostName;

	@Value("${redisPortNumber}")
	private Integer redisPortNumber;

	/**
	 * Create a factory that uses Jedis (a Redis java client) based connections.
	 *
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
	 *
	 * @return the redis interaction abstraction
	 */
	@Bean
	public RedisTemplate<Object, Object> redisTemplate() {
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.setExposeConnection(true);
		return redisTemplate;
	}

	/**
	 * The cache manager for Redis.
	 *
	 * @return the redis cache manager
	 */
	@Bean
	public RedisCacheManager redisCacheManager() {
		RedisCacheManager redisCacheManager = new RedisCacheManager(redisTemplate());
		redisCacheManager.setTransactionAware(true);
		redisCacheManager.setLoadRemoteCachesOnStartup(true);
		redisCacheManager.setUsePrefix(true);
		// Set default expiration to 6 hours (21600 seconds)
		redisCacheManager.setDefaultExpiration(21600);
		return redisCacheManager;
	}

	/**
	 * Overrides the default key generation method for Spring cache by
	 * appending the class name, method name and the parameters instead of the
	 * default method parameters.
	 *
	 * @return the key generator method
	 */
	@Bean
	public KeyGenerator keyGenerator() {
		// Lambda expression. Returns an anonymous inner KeyGenerator class
		return (Object o, Method method, Object... objects) -> {
			// Generate a unique key of the class name, the method name,
			// and all method parameters appended
			StringBuilder sb = new StringBuilder();
			sb.append(o.getClass().getName());
			sb.append(method.getName());
			for (Object obj : objects) {
				sb.append(obj.toString());
			}
			return sb.toString();
		};
	}
}
