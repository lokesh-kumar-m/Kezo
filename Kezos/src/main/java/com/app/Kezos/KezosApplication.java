package com.app.Kezos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class KezosApplication {

	public static void main(String[] args) {
		SpringApplication.run(KezosApplication.class, args);
	}

}
