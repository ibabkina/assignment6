package com.meritamerica.assignment6.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meritamerica.assignment6.models.SavingsAccount;



public interface SavingsAccountRepository extends JpaRepository<SavingsAccount, Long> {
	
	SavingsAccount findById(long id);
}
