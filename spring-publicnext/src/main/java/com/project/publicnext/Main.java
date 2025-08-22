package com.project.publicnext;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling// for scheduler
public class Main {
	public static void main(String[] args) {
		SpringApplication.run(Main.class,args);
	}
}