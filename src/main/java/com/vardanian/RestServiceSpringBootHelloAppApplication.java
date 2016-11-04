package com.vardanian;

import com.vardanian.entity.Contact;
import com.vardanian.service.ContactService;
import com.vardanian.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@ComponentScan
@EnableAutoConfiguration
@EnableTransactionManagement
@SpringBootApplication
public class RestServiceSpringBootHelloAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestServiceSpringBootHelloAppApplication.class,  args);
		Utils utils = Utils.getInstance();
		utils.recordDBContacts();
	}
}


