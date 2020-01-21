package com.rafalazar.springboot.app;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

import com.rafalazar.springboot.app.documents.AhorroAccount;
import com.rafalazar.springboot.app.service.AhorroAccountService;

import reactor.core.publisher.Flux;

@EnableEurekaClient
@SpringBootApplication
public class ServiceAhorroApplication implements CommandLineRunner{
	
	@Autowired
	private AhorroAccountService service;
	
	@Autowired
	private ReactiveMongoTemplate mongoTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(ServiceAhorroApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ServiceAhorroApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		mongoTemplate.dropCollection("ahorro_account").subscribe();
		
		Flux.just(
				new AhorroAccount("899687","P1",1800.90,"inactivo"),
				new AhorroAccount("756677","P2",650.65,"activo"),
				new AhorroAccount("244324","P3",420.10,"inactivo")
			).flatMap(ca ->{
				ca.setCreateAt(new Date());
				ca.setUpdateAt(new Date());
				return service.save(ca);
			})
		.subscribe(ca -> log.info("Insert: " + ca.getNumAccount() + " -> " + ca.getNameBank()))
		;
		
	}

}
