package com.rafalazar.springboot.app.service;

import com.rafalazar.springboot.app.documents.AhorroAccount;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AhorroAccountService {
	
	public Flux<AhorroAccount> findAll();
	
	public Mono<AhorroAccount> findById(String id);
	
	public Mono<AhorroAccount> save(AhorroAccount ahorro);
	
	public Mono<Void> delete(AhorroAccount ahorro);
}
