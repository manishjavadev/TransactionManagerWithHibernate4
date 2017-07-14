package com.manish.javadev.spring.dao;

import com.manish.javadev.spring.model.Account;

/**
 * @author Deepak
 *
 */
public interface AccountDao {

	public void withdrawAmount(Long accountFrom,Double amount);

	public void depositAmount(Long accountTo, Double ammount);

	public Account createAccount(Account account);

}
