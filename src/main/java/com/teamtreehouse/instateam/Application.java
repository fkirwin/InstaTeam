package com.teamtreehouse.instateam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

import com.teamtreehouse.instateam.Application;
import com.teamtreehouse.instateam.bootstrap.Data;

@EnableAutoConfiguration
@ComponentScan
public class Application
{

	public static void main(String[] args)
	{
		//first populate DB
		
		SpringApplication.run(Application.class, args);
	}

}
