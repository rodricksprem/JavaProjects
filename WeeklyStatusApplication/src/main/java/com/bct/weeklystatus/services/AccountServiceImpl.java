package com.bct.weeklystatus.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bct.weeklystatus.entities.Account;
import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.repositories.AccountRepositoryImpl;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
    private AccountRepositoryImpl accountRepository;

	@Override
	public void saveAccount(Account account) {
		// TODO Auto-generated method stub
		accountRepository.insert(account);
	}

	@Override
	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		accountRepository.delete(account.getAccountID());
	}

	@Override
	public Account findByAccountId(String accountID) {
		// TODO Auto-generated method stub
		Optional<Account> account= accountRepository.findById(accountID);
		if(account.isPresent()) {
			return account.get();
		}
		return null;
	}

	@Override
	public List<Account> findAllAccounts() {
		// TODO Auto-generated method stub
		return accountRepository.findAllAccounts();
	}

	public List<String> getAllAccountNames() {
		// TODO Auto-generated method stub
		return accountRepository.findAllAccounts().stream().map(account->account.getAccountName()).collect(Collectors.toList());
	}
	
	@Override
	public List<ProjectDetail> getAllProjectDetails(String accountID) {
		// TODO Auto-generated method stub
		return accountRepository.findAllProjectDetails(accountID);
	}
	@Override
	public ProjectDetail getProjectDetails(String accountID,Long projectID,String weekDuration) {
		// TODO Auto-generated method stub
		return accountRepository.findProjectDetail(accountID,projectID,weekDuration);
	}
	@Override
	public List<ProjectDetail> getProjectDetails(String accountID,Long projectID) {
		// TODO Auto-generated method stub
		return accountRepository.findProjectDetail(accountID,projectID);
	}
	public List<ProjectDetail> getAllProjectDetails() {
		// TODO Auto-generated method stub
		return accountRepository.findAllProjectDetails();
	}
	public Account getAccountDetail(Long projectId) {
		// TODO Auto-generated method stub
		
		return accountRepository.findAccount(projectId);
	}

}
