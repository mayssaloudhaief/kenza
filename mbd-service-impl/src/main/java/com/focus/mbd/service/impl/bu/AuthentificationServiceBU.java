/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bu;

import com.focus.mbd.dal.AccountRepository;
import com.focus.mbd.infra.entity.Account;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class AuthentificationServiceBU {
    
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    
     public void saveAccount(Account account) {
        accountRepository.save(account);
    }

    public Account findAccountById(Long id) {
        return accountRepository.findById(id).orElse(null);
    }

    public void updateAccount(Account account) {
        accountRepository.save(account);
    }

    public void deleteAccountById(Long id) {
        accountRepository.deleteById(id);
    }
    
    public void login(String username, String pwd) {
       Account account = accountRepository.findByUserName(username).get();

            String mdpStocke = account.getPwd();
           if (!passwordEncoder.matches(pwd, mdpStocke))
            {
               throw new SecurityException("wrong username or password: "+username); 
            }
       
    }

   public void register(String username, String password) {
    Optional<Account> accountOptional = accountRepository.findByUserName(username);
    if (accountOptional.isEmpty()) {
        Account account = new Account();
        account.setUserName(username);
        account.setPwd(passwordEncoder.encode(password));
        accountRepository.save(account);
    } else {
        throw new SecurityException("User already registered: " + username);
    }
}
    
}
