package com.rafalazar.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rafalazar.springboot.app.documents.AhorroAccount;
import com.rafalazar.springboot.app.repository.AhorroAccountRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AhorroAccountServiceImpl implements AhorroAccountService{

	@Autowired
	AhorroAccountRepository repo;
	
	@Override
	public Flux<AhorroAccount> findAll() {
		return repo.findAll();
	}

	@Override
	public Mono<AhorroAccount> findById(String id) {
		return repo.findById(id);
	}

	@Override
	public Mono<AhorroAccount> save(AhorroAccount ahorro) {
		return repo.save(ahorro);
	}

	@Override
	public Mono<Void> delete(AhorroAccount ahorro) {
		return repo.delete(ahorro);
	}

}
