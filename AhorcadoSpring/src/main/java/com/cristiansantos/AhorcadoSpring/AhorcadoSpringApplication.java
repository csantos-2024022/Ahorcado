package com.cristiansantos.AhorcadoSpring;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AhorcadoSpringApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(AhorcadoSpringApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.printf("Api Funcionando");
    }
}
