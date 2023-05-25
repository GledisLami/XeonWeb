package com.Xeon.XeonWeb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@EnableCaching
public class XeonWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(XeonWebApplication.class, args);
	}

	@EventListener(ContextRefreshedEvent.class)
	public void onApplicationStartup(ContextRefreshedEvent event) {
		// Cache clearing logic
		event.getApplicationContext().getBean(CacheManager.class).getCacheNames().forEach(cacheName -> {
			event.getApplicationContext().getBean(CacheManager.class).getCache(cacheName).clear();
		});
	}

}
