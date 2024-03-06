package com.sppl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sppl.model.AccountReg1;

public interface AccountRepository extends JpaRepository<AccountReg1, String> {

}
