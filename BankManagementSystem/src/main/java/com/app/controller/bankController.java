package com.app.controller;

import com.app.model.Account;
import com.app.service.bankService;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class bankController {

    @Autowired
    bankService bsi;

    @RequestMapping("/")
    public String openRegister() {
        return "register";
    }

    @RequestMapping("/login")
    public String login(@RequestParam("email") String email,@RequestParam("password") String password,Model m, HttpSession session) {

        Account acc = bsi.loginCheck(email, password);

        if (acc != null) {
        
            session.setAttribute("accountId", acc.getAccountNumber());

            m.addAttribute("account", acc);
            return "loginsuccess";
        }

        m.addAttribute("msg", "Invalid Email or Password");
        return "login";
    }

    @RequestMapping("/depositPage")
    public String depositPage(@RequestParam("accNo") int accNo, Model m) {
        Account ac = bsi.getSingleAcc(accNo);
        m.addAttribute("account", ac);
        return "deposit";
    }

    @RequestMapping("/withdrawPage")
    public String withdrawPage(@RequestParam("accNo") int accNo, Model m) {
        Account ac = bsi.getSingleAcc(accNo);
        m.addAttribute("account", ac);
        return "withdraw";
    }

    @RequestMapping("/register")
    public String Loginsuccess(@ModelAttribute Account account) {
        bsi.saveData(account);
        return "login";
    }

    @RequestMapping("/deposit")
    public String depositAmount(@RequestParam("accountNumber") long accountNumber,@RequestParam("amount") double amount,Model m) {

        bsi.depositAmount(accountNumber, amount);
        Account singleAcc = bsi.getSingleAcc(accountNumber);

        m.addAttribute("acc", singleAcc);
        return "history";
    }

    @RequestMapping("/withdraw")
    public String withdrawAmount(@RequestParam("accountNumber") long accountNumber, @RequestParam("amount") double amount,Model m) {

        bsi.withdrawAmount(accountNumber, amount);
        Account singleAcc = bsi.getSingleAcc(accountNumber);

        m.addAttribute("acc", singleAcc);
        return "history";
    }

    @RequestMapping("/loginPage")
    public String AlreadyLogin() {
        return "login";
    }

    @GetMapping("/history")
    public String transactionHistory(HttpSession session, Model model) {

        Long accountNumber = (Long) session.getAttribute("accountId");

        Account acc = bsi.getSingleAcc(accountNumber);

        model.addAttribute("acc", acc);

        return "history"; 
    }
    
    @RequestMapping("/logout")
    public String logoutAcc() {
    	return "login";
    }
    @RequestMapping("/registerpage")
    public String AlreadyRegister() {
        return "register";
    }
}
