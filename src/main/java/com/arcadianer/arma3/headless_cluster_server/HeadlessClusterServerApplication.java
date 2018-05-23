package com.arcadianer.arma3.headless_cluster_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HeadlessClusterServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HeadlessClusterServerApplication.class, args);
	}
}
