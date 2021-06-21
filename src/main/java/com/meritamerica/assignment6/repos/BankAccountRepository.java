package com.meritamerica.assignment6.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meritamerica.assignment6.models.BankAccount;


public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

	BankAccount findById(long id); // if you don't specify (omit this line) it will do optional type


}
