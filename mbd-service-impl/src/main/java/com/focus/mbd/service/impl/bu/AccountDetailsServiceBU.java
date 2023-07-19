/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bu;

import com.focus.mbd.dal.AccountRepository;
import com.focus.mbd.infra.entity.Account;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class AccountDetailsServiceBU implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Account> accountOptional = accountRepository.findByUserName(username);
        if (accountOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        Account account = accountOptional.get();
        return new User(account.getUserName(), account.getPwd(), List.of());
    }
}
