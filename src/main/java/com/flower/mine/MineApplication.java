package com.flower.mine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class MineApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
		SpringApplication.run(MineApplication.class, args);
	}
}
