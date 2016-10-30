package com.vardanian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
public class RestServiceSpringBootHelloAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceSpringBootHelloAppApplication.class,  args);
	}
}


