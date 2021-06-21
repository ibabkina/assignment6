package com.meritamerica.assignment6.models;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.Positive;

/**
 * This program creates a CD account for a client.
 * 
 * @author Irina Babkina 
 * 
 */

public class CDAccount extends BankAccount{
	
	private int term;
	
//	------- Added in assignment 5 -------
	private CDOffering cdOffering;
	
	
	public CDOffering getCdOffering() {
		return cdOffering;
	}

	public void setCdOffering(CDOffering cdOffering) {
		this.cdOffering = cdOffering;
	}
//  --------------------------------------	
	
	/**
	 * Default constructor 
	 */
	public CDAccount() { super(); }
	
	/**
	 * @param offering
	 * @param openingBalance 
	 */
	public CDAccount(CDOffering offering, double openingBalance) {
		super(openingBalance, offering.getInterestRate()); 
		this.term = offering.getTerm();
	}
	
	/**
	 * @param accNumber
	 * @param openingBalance
	 * @param interestRate
	 */
	public CDAccount(long accNumber, 
					double openingBalance, 
					double interestRate, 
					Date accountOpenedOn, 
					int term){
		super(accNumber, openingBalance, interestRate, accountOpenedOn);
		this.term = term;
	}
	
	public static CDAccount readFromString(String accountData) 
			throws NumberFormatException, ParseException {
		String[] args = accountData.split(",");
    	
		CDAccount acc = new CDAccount(Long.parseLong(args[0]), 
							Double.parseDouble(args[1]), 
							Double.parseDouble(args[2]), 
							new SimpleDateFormat("dd/MM/yyyy").parse(args[3]), 
							Integer.parseInt(args[4])); 
		return acc;
	}
	
//	@Override
//	public boolean deposit(double amount) throws ExceedsFraudSuspicionLimitException { 
//		throw new ExceedsFraudSuspicionLimitException("You cannot deposit to CD Account");
//		}
//	
//	@Override
//	public boolean withdraw(double amount) throws ExceedsFraudSuspicionLimitException { 
//		throw new ExceedsFraudSuspicionLimitException("You cannot withdraw from CD Account");
//	}
	
	/**
	 * @return the interestRate
	 */
	public double getInterestRate() { return this.interestRate; }
	
	/**
	 * @param cdOffering
	 */
	public void setInterestRate(CDOffering cdOffering) { 
		super.setInterestRate(cdOffering.getInterestRate());
	}
	
	/**
	 * @return the term
	 */
	public int getTerm() { return term; }
	
	/**
	 * @param cdOffering
	 */
	public void setTerm(CDOffering cdOffering) { this.term = cdOffering.getTerm(); }
	
	/**
	 * @param term the term to set
	 */
	public void setTerm(int term) { this.term = term; }

//	/**
//	 * @return the startDate 
//	 */
//	public java.util.Date getStartDate() { return startDate; }
		
	/**
	 * Calculates the future value of the account balance based on the interest 
	 * and number of years
	 * @param years: number of periods in years
	 * @return the future value
	 */
	double futureValue(){ return this.balance * Math.pow(1 + interestRate, term); }
	
	

	@Override
	public String toString() {
		return "\nCD Account Number: " + this.getAccountNumber()
			+ "\nCD Account Balance: $" + String.format("%.2f", this.getBalance())
			+ "\nCD Account Interest Rate: " + String.format("%.4f", this.getInterestRate())
			+ "\nCD Account Term: " + this.getTerm()
			+ "\nCD Account Balance in " + this.getTerm() + " years: " + String.format("%.2f", this.futureValue(this.getTerm()))
			+ "\nCD Account Opened Date " + this.getOpenedOn();
	}
	
	@Override
	public String writeToString() {
		return Long.toString(this.getAccountNumber()) + "," 
				+ String.format("%.0f", this.getBalance()) + ","
				+ String.format("%.3f", this.getInterestRate()) + ","
				+ new SimpleDateFormat("MM/dd/yyyy").format(this.accountOpenedOn) + ","
				+ Integer.toString(this.term);
	}
}