package com.flower.mine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@SpringBootApplication
@EnableScheduling
public class MineApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("CTT"));
		SpringApplication.run(MineApplication.class, args);
	}
}
