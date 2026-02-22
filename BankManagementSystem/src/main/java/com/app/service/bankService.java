package com.app.service;

import com.app.model.Account;

public interface bankService {

    public void saveData(Account account);

    public Account loginCheck(String email, String password);

    public Account getSingleAcc(long accountNumber);

    public void depositAmount(long accountNumber, double amount);

   
    public void withdrawAmount(long accountNumber, double amount);
}
