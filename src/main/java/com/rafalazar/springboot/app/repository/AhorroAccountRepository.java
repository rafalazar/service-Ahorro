package com.rafalazar.springboot.app.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.rafalazar.springboot.app.documents.AhorroAccount;

public interface AhorroAccountRepository extends ReactiveMongoRepository<AhorroAccount, String>{

}
