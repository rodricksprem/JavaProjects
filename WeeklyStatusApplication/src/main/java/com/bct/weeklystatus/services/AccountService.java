package com.bct.weeklystatus.services;

import java.util.List;

import com.bct.weeklystatus.entities.Account;
import com.bct.weeklystatus.entities.ProjectDetail;

public interface AccountService {
	
	public void saveAccount(Account account);

	public void deleteAccount(Account account);
	public Account findByAccountId(String accountID);
	public List<Account> findAllAccounts();
	public List<String> getAllAccountNames() ;
	public List<ProjectDetail> getAllProjectDetails(String accountID);
	public List<ProjectDetail> getAllProjectDetails();
	public Account getAccountDetail(Long projectId);

	public ProjectDetail getProjectDetails(String accountID, Long projectID, String weekDuration);

	
}
