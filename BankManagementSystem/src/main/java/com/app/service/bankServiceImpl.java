package com.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.AccTransaction;
import com.app.model.Account;
import com.app.repository.bankRepository;

@Service
public class bankServiceImpl implements bankService {

    @Autowired
    bankRepository bri;

    @Override
    public void saveData(Account account) {
        bri.save(account);
    }

    public Account loginCheck(String email, String password) {
        return bri.findByEmailAndPassword(email, password);
    }

    public Account getSingleAcc(long accountNumber) {
        return bri.findById(accountNumber).get();
    }

 
    public void depositAmount(long accountNumber, double amount) {

        Account acc = bri.findById(accountNumber).get();

        acc.setBalance(acc.getBalance() + amount);

        AccTransaction tr = new AccTransaction();
        tr.setType("Deposit");
        tr.setAmount(amount);


        acc.getTransaction().add(tr);

        bri.save(acc);
    }

  
    public void withdrawAmount(long accountNumber, double amount) {

        Account acc = bri.findById(accountNumber).get();

        acc.setBalance(acc.getBalance() - amount);

        AccTransaction tr = new AccTransaction();
        tr.setType("Withdraw");
        tr.setAmount(amount);

       
        acc.getTransaction().add(tr);

        bri.save(acc);
    }
}
