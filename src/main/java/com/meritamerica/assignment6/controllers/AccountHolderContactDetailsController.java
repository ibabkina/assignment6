package com.meritamerica.assignment6.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.meritamerica.assignment6.exceptions.ExceedsCombinedBalanceLimitException;
import com.meritamerica.assignment6.exceptions.NegativeAmountException;
import com.meritamerica.assignment6.exceptions.NotFoundException;
import com.meritamerica.assignment6.models.AccountHolder;
import com.meritamerica.assignment6.models.AccountHolderContactDetails;
import com.meritamerica.assignment6.models.CheckingAccount;
import com.meritamerica.assignment6.models.MeritBank;
import com.meritamerica.assignment6.repos.AccountHolderContactDetailsRepo;

@RestController
public class AccountHolderContactDetailsController {
	
//	@Autowired
//	private AccountHolderContactDetailsRepo accHolderContDelailsRepo;
//	
//	/* Constructor */
//	public AccountHolderContactDetailsController(AccountHolderContactDetailsRepo accHolderContDelailsRepo) {
//		this.accHolderContDelailsRepo = accHolderContDelailsRepo;
//	}
//	
//	@GetMapping(value = "/accountHolderContactDetails")
//	public List<AccountHolderContactDetails> getAccountHolderContactDetails() {
////			throws NotFoundException {
//			
//		return accHolderContDelailsRepo.findAll();
//	}
	
	
	
//	@PostMapping(value = "/accountHolders/{id}/accountHolderContactDetails")
//	@ResponseStatus(HttpStatus.CREATED)
//		public AccountHolderContactDetails getAccountHolderContactDetails(@PathVariable long id, 
//				@RequestBody @Valid AccountHolderContactDetails accHolderContactDetails) {
//			//throws NotFoundException {
//		
//		n, NegativeAmountException, ExceedsCombinedBalanceLimitException	{
////			 MeritBank.getAccountHolder(id).set	
//		return accHolderContDelailsRepo.findAll();
//			return null;
//	}
//	
//	@ResponseStatus(HttpStatus.CREATED)
//	public CheckingAccount addCheckingAcc(@PathVariable long customerId, 
//			@RequestBody @Valid CheckingAccount checkingAccount) 
//			throws NotFoundException, NegativeAmountException, ExceedsCombinedBalanceLimitException	{
//		AccountHolder accountHolder = this.getAccountHolderById(customerId);
//		if(checkingAccount.getBalance() < 0) { throw new NegativeAmountException("Balance can't be negative"); }
//		log.info(checkingAccount.toString());
//		if(accountHolder.getCombinedBalance() + checkingAccount.getBalance() > 250000) {
//			log.info("Here in Chk to throw an exception");
//			throw new ExceedsCombinedBalanceLimitException("Combined Balance can't exceed $250K");
//		}				
//		accountHolder.addCheckingAccount(checkingAccount); 
//		return checkingAccount;
		
//	}
}
