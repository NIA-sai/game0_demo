package online.zsgbp.demo.spring_boot.game_0_demo.config;
//now it's gibberish for me .w_w..

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.*;

import java.time.Duration;

@Configuration
@EnableCaching
public class RedisConfig
{
	@Bean
	public RedisTemplate < String, Object > redisTemplate ( RedisConnectionFactory factory )
	{
		RedisTemplate < String, Object > redisTemplate = new RedisTemplate <> ();
		redisTemplate.setConnectionFactory ( factory );
		redisTemplate.setKeySerializer ( new StringRedisSerializer () );
		redisTemplate.setValueSerializer ( new GenericJackson2JsonRedisSerializer () );
		redisTemplate.setHashKeySerializer ( new StringRedisSerializer () );
		redisTemplate.setHashValueSerializer ( new Jackson2JsonRedisSerializer < Object > ( Object.class ) );
		//todo change the ttl
		return redisTemplate;
	}
	
			@Bean
			public RedisCacheManager redisCacheManager ( RedisTemplate redisTemplate )
			{
				RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter ( redisTemplate.getConnectionFactory () );
				RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig ()
						.entryTtl ( Duration.ofMinutes ( 60 ) )
						.serializeValuesWith ( RedisSerializationContext.SerializationPair.fromSerializer ( redisTemplate.getValueSerializer () ) );
				return new RedisCacheManager ( redisCacheWriter , redisCacheConfiguration );
			}//o.0
}
