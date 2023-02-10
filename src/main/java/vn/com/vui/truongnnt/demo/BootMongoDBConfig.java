package vn.com.vui.truongnnt.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
//@EnableMongoRepositories(basePackages = "com.journaldev.bootifulmongodb.dal")
public class BootMongoDBConfig extends AbstractMongoClientConfiguration {

	@Override
	protected String getDatabaseName() {
		// TODO Auto-generated method stub
		return "demo";
	}

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(new RedisStandaloneConfiguration("127.0.0.1", 6379));
	}

	@Bean
	@Primary
	public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
		// tạo ra một RedisTemplate
		// Với Key là Object
		// Value là Object
		// RedisTemplate giúp chúng ta thao tác với Redis
		RedisTemplate<Object, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory);
		return template;
	}
}
