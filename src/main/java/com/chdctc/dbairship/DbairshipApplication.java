package com.chdctc.dbairship;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DbairshipApplication {

	public static void main(String[] args) {

		for(String s: args){
			String[] jobArgument= s.split("=");

			System.setProperty(jobArgument[0], jobArgument[1]);
		}


		SpringApplication.run(DbairshipApplication.class, args);
	}

}
