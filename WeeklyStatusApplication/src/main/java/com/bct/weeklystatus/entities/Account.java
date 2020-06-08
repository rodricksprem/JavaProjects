package com.bct.weeklystatus.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="BCT_ACCOUNT")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@NamedQuery(name="find_all_accounts",query = "select pd from Account pd")

@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdBy", "lastUpdatedBy"},
        allowGetters = true
)

public class Account {
	@Column(name="Account_ID")
	@Id
	private String accountID;
	
	@Column(name="Account_Name")
	private String accountName;

	@JsonManagedReference(value="account_projectdetail")
	@OneToMany(mappedBy="account")
	private List<ProjectDetail> projectDetails;
	
	@Column(name="Account_Owner")
	private String accountOwner;
	
	@Column(name="Account_Status")
	private int accountStatus;
	@Column(name="Remarks")
	private String remarks;	
}
	