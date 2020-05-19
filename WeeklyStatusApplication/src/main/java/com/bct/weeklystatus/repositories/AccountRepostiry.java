package com.bct.weeklystatus.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bct.weeklystatus.entities.Account;

public interface AccountRepostiry extends JpaRepository<Account, String> {

}
