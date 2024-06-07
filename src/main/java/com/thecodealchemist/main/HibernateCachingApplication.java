package com.thecodealchemist.main;

import com.thecodealchemist.main.service.ReminderService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
/**
 * A session-scoped cache that stores entities loaded during the session.
 */
public class HibernateCachingApplication {

	public static void main(String[] args) {
		SpringApplication.run(HibernateCachingApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(ReminderService reminderService) {
		return args -> {
			reminderService.firstLevelCacheWithRepository();
		};
	}
}
