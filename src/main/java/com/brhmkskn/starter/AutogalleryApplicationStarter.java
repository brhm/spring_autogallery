package com.brhmkskn.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages = {"com.brhmkskn"})
@EntityScan(basePackages = {"com.brhmkskn"})
@EnableJpaRepositories(basePackages = {"com.brhmkskn"})
@SpringBootApplication
public class AutogalleryApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(AutogalleryApplicationStarter.class, args);
	}

}
