package com.challenge.forohub;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ForohubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForohubApplication.class, args);
	}

	/*@Bean
	public CommandLineRunner printBeans(ApplicationContext ctx) {
		return args -> {
			for (String beanName : ctx.getBeanDefinitionNames()) {
				System.out.println(beanName);
			}
		};
	}*/

}
