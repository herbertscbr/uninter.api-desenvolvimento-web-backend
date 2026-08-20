package com.uninter.ads.back_end;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndApplication {

	public static void main(String[] args) {
		//System.out.println("server.servlet.context-path: " + System.getProperty("server.servlet.context-path"));
		System.out.println("API em Spring desenvolvida para discplina de 'DESENVOLVIMENTO WEB BACK-END', do curso de Análise e desenvolvimento de sistemas / Uninter");
		SpringApplication.run(BackEndApplication.class, args);
	}

}
