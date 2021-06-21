package com.meritamerica.assignment6.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment6.MeritAmericaBankApp;
import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.exceptions.ExceedsFraudSuspicionLimitException;
import com.meritamerica.assignment6.exceptions.NegativeAmountException;
//import com.meritamerica.assignment5.exceptions.NoSuchResourceFoundException;
import com.meritamerica.assignment6.exceptions.NotFoundException;
import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.CDAccount;
import com.meritamerica.assignment6.models.CDOffering;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.models.MeritBank;
import com.meritamerica.assignment6.models.SavingsAccount;
import com.meritamerica.assignment6.repos.AccountHolderRepository;
import com.meritamerica.assignment6.repos.BankAccountRepository;
import com.meritamerica.assignment6.repos.CheckingAccountRepository;

@RestController
public class AccountHolderController {
	
	private final Logger log = LoggerFactory.getLogger(AccountHolderController.class);
	
	
	@Autowired AccountHolderRepository accHolderRepository;
	@Autowired BankAccountRepository bankAccountRepository;
	@Autowired CheckingAccountRepository checkingAccountRepository;
	
//	log.debug("Is rollbackOnly: " + TransactionAspectSupport.currentTransactionStatus().isRollbackOnly());
	
//	@GetMapping(value = "/accountHolders")
//	@ResponseStatus(HttpStatus.OK)
//	public List<AccountHolder> getAccountHolders(){
//		
//		return Arrays.asList(MeritBank.getAccountHolders());
//	}
	
	@PostMapping(value = "/accountHolders")
	@ResponseStatus(HttpStatus.CREATED)
	public AccountHolder addAccountHolder(@RequestBody @Valid AccountHolder accountHolder) {
		AccountHolder accHolder = accountHolder;
		System.out.println("accountHolder = " + accountHolder.toString());
//		MeritBank.addAccountHolder(accountHolder);
		accHolderRepository.save(accountHolder); 
		return accountHolder;
	}
	
//	@GetMapping(value = "/accountHolders")
//	@ResponseStatus(HttpStatus.OK) //Redundant but can do if your team prefers
//	public AccountHolder[] getAccountHolders(){
//		
////		return MeritBank.getAccountHolders();
//		return accHolderRepository.findAll();
//	}
	
	@GetMapping(value = "/accountHolders")
	@ResponseStatus(HttpStatus.OK) //Redundant but can do if your team prefers
	public List<AccountHolder> getAccountHolders(){
		
//		return MeritBank.getAccountHolders();
		return accHolderRepository.findAll();
	}
	
	@GetMapping(value = "/accountHolders/{customerId}")
	@ResponseStatus(HttpStatus.OK) //Redundant but can do if your team prefers
	public AccountHolder getAccountHolderById(@PathVariable long customerId) 
			throws NotFoundException {
		
		AccountHolder accountHolder = MeritBank.getAccountHolder(customerId);	
		if(accountHolder == null) { throw new NotFoundException("AccountHolder Not Found"); }
		return accountHolder;
	}
	
	@PostMapping(value = "/accountHolders/{customerId}/checkingAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CheckingAccount addCheckingAcc(@PathVariable long customerId, 
			@RequestBody @Valid CheckingAccount checkingAccount) 
			throws NotFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException	{
		AccountHolder accountHolder = this.getAccountHolderById(customerId);
		if(checkingAccount.getBalance() < 0) { throw new NegativeAmountException("Balance can't be negative"); }
		log.info(checkingAccount.toString());
		if(accountHolder.getCombinedBalance() + checkingAccount.getBalance() > 250000) {
			log.info("Here in Chk to throw an exception");
			throw new ExceedsCombinedBalanceLimitException("Combined Balance can't exceed $250K");
		}				
		accountHolder.addCheckingAccount(checkingAccount); 
		return checkingAccount;
		
	}
	
	@GetMapping(value = "/accountHolders/{customerId}/checkingAccounts")
	@ResponseStatus(HttpStatus.OK) 
	public CheckingAccount[] getAccHolderCheckingAccounts(@PathVariable long customerId) 
			throws NotFoundException {
			
		AccountHolder accountHolder = this.getAccountHolderById(customerId); 
		CheckingAccount[] checkingAccounts = accountHolder.getCheckingAccounts();
		if(checkingAccounts.length <= 0) { throw new NotFoundException("Checking Accounts Not Found"); }
		return checkingAccounts;
	}
	
	@PostMapping(value = "/accountHolders/{customerId}/savingsAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public SavingsAccount addSavingsAcc(@PathVariable long customerId, 
			@RequestBody @Valid SavingsAccount savingsAccount) 
			throws NotFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException {
			
		AccountHolder accountHolder = this.getAccountHolderById(customerId);
		if(savingsAccount.getBalance() < 0) { throw new NegativeAmountException("Balance can't be negative"); }
		if(accountHolder.getCombinedBalance() > 250000) {
			throw new ExceedsCombinedBalanceLimitException("Combined Balance can't be exceed $250K");
		}
		accountHolder.addSavingsAccount(savingsAccount);
		return savingsAccount;
	}
	
	@GetMapping(value = "/accountHolders/{customerId}/savingsAccounts")
	@ResponseStatus(HttpStatus.OK) 
	public SavingsAccount[] getAccHolderSavingsAccounts(@PathVariable long customerId) 
			throws NotFoundException {
			
		AccountHolder accountHolder = this.getAccountHolderById(customerId); 
		SavingsAccount[] savingsAccounts = accountHolder.getSavingsAccounts();
		if(savingsAccounts.length <= 0) { throw new NotFoundException("Savings Accounts Not Found"); }
		return savingsAccounts;
	}
	
//	@PostMapping(value = "/accountHolders/{customerId}/cdAccounts")
//	@ResponseStatus(HttpStatus.CREATED)
//	public CDAccount addCDAccount(@PathVariable long customerId, 
//			@RequestBody @Valid CDAccount cdAccount) 
//			throws NotFoundException, ExceedsFraudSuspicionLimitException {
//			
//		AccountHolder accountHolder = this.getAccountHolderById(customerId);
//		accountHolder.addCDAccount(cdAccount);
//		return cdAccount;
//	}
	
	@PostMapping(value = "/accountHolders/{customerId}/cdAccounts")
	@ResponseStatus(HttpStatus.CREATED)
	public CDAccount addCDAccount(@PathVariable long customerId, 
			@RequestBody @Valid CDAccount cdAccount) 
			throws NotFoundException, ExceedsFraudSuspicionLimitException {
			
		AccountHolder accountHolder = this.getAccountHolderById(customerId);
		int term = cdAccount.getCdOffering().getTerm();
		double interestRate = cdAccount.getCdOffering().getInterestRate();
		cdAccount.setTerm(term);
		cdAccount.setInterestRate(interestRate);
		accountHolder.addCDAccount(cdAccount);
		return cdAccount;
	}
		
	@GetMapping(value = "/accountHolders/{customerId}/cdAccounts")
	@ResponseStatus(HttpStatus.OK) 
	public CDAccount[] getAccHolderCDAccounts(@PathVariable long customerId) 
			throws NotFoundException {
			
		AccountHolder accountHolder = this.getAccountHolderById(customerId); 
		CDAccount[] cdAccounts = accountHolder.getCDAccounts();
		if(cdAccounts.length <= 0) { throw new NotFoundException("CD Accounts Not Found"); }
		return cdAccounts;
	}
}
