package com.example.notificationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationserviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(com.example.notificationservice.NotificationserviceApplication.class, args);
	}
}
