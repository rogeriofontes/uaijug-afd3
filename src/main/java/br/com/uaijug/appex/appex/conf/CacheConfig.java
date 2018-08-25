package br.com.uaijug.appex.appex.conf;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;

@EnableCaching
@Configuration
public class CacheConfig {

	@Bean
	public CaffeineCache clientsCache() {
		return new CaffeineCache("clientsInCache",
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}

	@Bean
	public CaffeineCache clientTypesCache() {
		return new CaffeineCache("clientTypesInCache",
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}

	@Bean
	public CaffeineCache ordersCache() {
		return new CaffeineCache("ordersInCache",
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}

	@Bean
	public CaffeineCache productsCache() {
		return new CaffeineCache("productsInCache",
				Caffeine.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES).maximumSize(100).build());
	}
}
