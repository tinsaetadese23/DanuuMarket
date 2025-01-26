package com.e_commerce.danuu_market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class DanuuMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(DanuuMarketApplication.class, args);
	}

}
