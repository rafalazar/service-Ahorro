package com.rafalazar.springboot.app.controllers;

import java.net.URI;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rafalazar.springboot.app.documents.AhorroAccount;
import com.rafalazar.springboot.app.service.AhorroAccountService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/ahorro-accounts")
public class AhorroAccountController {
	
	@Autowired
	private AhorroAccountService service;
	
	@GetMapping
	public Mono<ResponseEntity<Flux<AhorroAccount>>> list(){
		return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<AhorroAccount>> searchById(@PathVariable String id){
		return service.findById(id).map(ac -> ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(ac))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<ResponseEntity<AhorroAccount>> create(@RequestBody AhorroAccount ahorro){
		if(ahorro.getCreateAt() == null) {
			ahorro.setCreateAt(new Date());
		}
		
		if(ahorro.getUpdateAt() == null) {
			ahorro.setUpdateAt(new Date());
		}
		
		return service.save(ahorro).map(p -> ResponseEntity
				.created(URI.create("/api/ahorro-accounts/".concat(p.getId())))
				.contentType(MediaType.APPLICATION_JSON)
				.body(p)
				);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<AhorroAccount>> edit(@RequestBody AhorroAccount current, @PathVariable String id){
		return service.findById(id).flatMap(ac -> {
			ac.setNumAccount(current.getNumAccount());
			ac.setNameBank(current.getNameBank());
			ac.setTotal(current.getTotal());
			ac.setStatus(current.getStatus());
			ac.setCreateAt(current.getCreateAt());
			ac.setUpdateAt(current.getUpdateAt());
			return service.save(ac);
		}).map(ac -> ResponseEntity.created(URI.create("/api/ahorro-accounts/".concat(ac.getId())))
				.contentType(MediaType.APPLICATION_JSON).body(ac)).defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
		return service.findById(id).flatMap(ac -> {
			return service.delete(ac).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
		}).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
	}
	
	

}
