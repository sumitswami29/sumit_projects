package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Account;

public interface bankRepository extends JpaRepository<Account, Long>{
	
	

	Account findByEmailAndPassword(String email, String password);

		
	
	

}
