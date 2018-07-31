package br.com.uaijug.appex.appex.conf;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {

	/*@Bean
	public CaffeineCache clientsCache() {
		return new CaffeineCache("clientsInCache",
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}*/
}
