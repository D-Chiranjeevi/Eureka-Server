package com.sppl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sppl.model.AccountReg1;
import com.sppl.repository.AccountRepository;

@Service
public class AccountService {
	@Autowired
	public AccountRepository accountRepository;

	public AccountReg1 saveAccount(AccountReg1 accountReg1) {
		return accountRepository.save(accountReg1);

	}
	
	public AccountReg1 getById( String regUUID) {
		return accountRepository.findById(regUUID).get();
		
	}
}
