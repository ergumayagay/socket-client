package org.emil.socketclient;

import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ch.qos.logback.classic.Logger;

@SpringBootApplication
public class SocketClientApplication {
	
	public static Logger logger = (Logger) LoggerFactory.getLogger(SocketClientApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SocketClientApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(SocketClientService socketClientService, SocketClientProperties socketClientProperties) {
		return args ->{
			
			
			String response = socketClientService.sendMessage("test");
			logger.info("Message is {}", response);
			socketClientService.stopConnection();
		};
	}
}
