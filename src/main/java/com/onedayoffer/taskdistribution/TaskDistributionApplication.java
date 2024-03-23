package com.onedayoffer.taskdistribution;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Sviridov_V_A
 * @version 1.0.0-SNAPSHOT
 * @since 2024-03-23
 */
@SpringBootApplication
@EnableJpaRepositories
public class TaskDistributionApplication {
	public static void main(String[] args) {
		SpringApplication.run(TaskDistributionApplication.class, args);
	}
}
