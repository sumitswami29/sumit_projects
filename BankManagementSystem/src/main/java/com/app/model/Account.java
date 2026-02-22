package com.app.model;

import java.util.ArrayList;
import java.util.List;

import com.app.model.Account;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long accountNumber;
    private String name;
    private String email;
    private String password;
    private double balance;
    
    
	@OneToMany(cascade = CascadeType.ALL)
    private List<AccTransaction> transaction = new ArrayList<>(); 
    

    public List<AccTransaction> getTransaction() {
		return transaction;
	}
	public void setTransaction(List<AccTransaction> transaction) {
		this.transaction = transaction;
	}
	public long getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
   
}