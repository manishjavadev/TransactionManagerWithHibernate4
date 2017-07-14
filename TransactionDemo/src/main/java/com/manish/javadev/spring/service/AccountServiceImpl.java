package com.manish.javadev.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.manish.javadev.spring.dao.AccountDao;
import com.manish.javadev.spring.model.Account;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
	@Autowired
	private AccountDao accountDao;

	public void setPersonDAO(AccountDao accountDao) {
		this.accountDao = accountDao;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public void createAccount(Long accountNumber, Double amount) {
		Account account = new Account(new Long(accountNumber), "Saving", "Manish", amount == null ? null : amount);
		account = accountDao.createAccount(account);
		System.out.println("Account = " + account);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public void fundTransfer(Long accountFrom, Long accountTo, Double amount) {
		accountDao.withdrawAmount(accountFrom, amount);
		accountDao.depositAmount(accountTo, amount);
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, readOnly = false, timeout = 100, rollbackFor = Exception.class)
	public void depositAmount(Long accountNumber, Double amount) {
		accountDao.depositAmount(accountNumber, amount);
	}
}
