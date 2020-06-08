package com.bct.weeklystatus.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.bct.weeklystatus.entities.ProjectDetail;
import com.bct.weeklystatus.entities.ProjectStatusDetail;
import com.bct.weeklystatus.entities.TaskDetail;
import com.bct.weeklystatus.entities.Account;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepostiry
{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private EntityManager entityManager;
	

	
	public void insert(Account account) {
		
		entityManager.merge(account);
		
	}
	
	public void delete(String id) {
		Optional<Account> account =findById(id);
		entityManager.remove(account);
		
	}
	
	public void addProjectsForAccount(String accountID, List<ProjectDetail> projectDetails) {		
		Optional<Account> optionqlaccount = findById(accountID);
		if(optionqlaccount.isPresent()) {
		Account account = optionqlaccount.get();
		logger.info("account.getProjectDetails()-> {}", account.getAccountID());
		for(ProjectDetail projectDetail:projectDetails)
		{			
			account.getProjectDetails().add(projectDetail);
			projectDetail.setAccount(account);
			entityManager.persist(projectDetail);
		}
		}
	}
	
	
	public List<Account> findAllAccounts(){
	TypedQuery<Account>	 typedQuery=entityManager.createNamedQuery("find_all_accounts",Account.class);
	return typedQuery.getResultList();
	}
	public Account findAccount(Long projectId){
		TypedQuery<Account>	 typedQuery=entityManager.createQuery("select ac from ProjectDetail pd  , Account ac where pd.projectId="+projectId+" and pd.account = ac ",Account.class);
		return typedQuery.getResultList().get(0);
		}
	public List<ProjectDetail> findAllProjectDetails(String accountID){
		Optional<Account> optionqlaccount = findById(accountID);
		
		if(optionqlaccount.isPresent()) {
			Account account = optionqlaccount.get();
		
		logger.info("findAllProjectDetails ");
		
		return account.getProjectDetails();
		}else {
			return null;
		}
		
	}
	public ProjectDetail findProjectDetail(String accountID,Long projectId,String weekDuration){
		Optional<Account> optionqlaccount = findById(accountID);
		
		if(optionqlaccount.isPresent()) {
			Account account = optionqlaccount.get();
			
		
		logger.info("findAllProjectDetails "+projectId+ " weekduration "+weekDuration);
		 account.getProjectDetails().stream().forEach(pd->{
			 System.out.println(pd.getProjectId().longValue());
			 pd.getWeekDuration().forEach(e->System.out.println(e));
			 	 
		 System.out.println((pd.getProjectId().longValue()==projectId.longValue() && pd.getWeekDuration().stream().anyMatch(wd -> (weekDuration.equalsIgnoreCase(wd.getWeekduration()))) ));
		 });
		ProjectDetail projectDetail = account.getProjectDetails().stream().filter(pd->(pd.getProjectId().longValue()==projectId.longValue() && (pd.getWeekDuration().stream().anyMatch(wd -> weekDuration.equalsIgnoreCase(wd.getWeekduration()))))).findFirst().get();
		projectDetail.setAccount(account);
		return projectDetail;
		}else {
			return null;
		}
		
	}
	public List<ProjectDetail> findProjectDetail(String accountID,Long projectId){
		Optional<Account> optionqlaccount = findById(accountID);
		
		if(optionqlaccount.isPresent()) {
			Account account = optionqlaccount.get();
			
		
		logger.info("findAllProjectDetails "+projectId);
		
		 List<ProjectDetail> projectDetails = account.getProjectDetails().stream().filter(pd->(pd.getProjectId().longValue()==projectId.longValue())).collect(Collectors.toList());
		 projectDetails.forEach(projectDetail->projectDetail.setAccount(account));
		 
		 	 return projectDetails;
				}else {
			return null;
		}
		
	}
	public List<ProjectDetail> findAllProjectDetails(){
	
		logger.info("findAllProjectDetails ");
		
		List<Account> accounts = findAllAccounts();
		List<ProjectDetail> projectDetails = new ArrayList<ProjectDetail>();
		accounts.forEach(account ->
				projectDetails.addAll(account.getProjectDetails()));
		return projectDetails;
		
		
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Account> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Account> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Account> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteInBatch(Iterable<Account> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Account getOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Account> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Account> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Account> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Account> findById(String id) {
		// TODO Auto-generated method stub
		
		return Optional.of(entityManager.find(Account.class,id));
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Account entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Account> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <S extends Account> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Account> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <S extends Account> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <S extends Account> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}

	
}	
