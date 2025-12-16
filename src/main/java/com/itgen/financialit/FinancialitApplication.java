package com.itgen.financialit;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FinancialitApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinancialitApplication.class, args);
	}

	@Bean
	ApplicationRunner runner() {
		return args -> {
			var welcome = "Welcome to Financtial IT _ " + " Development enviroment";
			System.out.println(welcome);
		};
	}

}
