/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bo;

import com.focus.mbd.service.impl.bu.AuthentificationServiceBU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class CoreBO {
    @Autowired
    private AuthentificationServiceBU authService;
     public void loginUser (String username, String pwd)
     {
        authService.login(username, pwd);
     }
     
     public void registerUser(String username,String pwd)
     {
         authService.register(username, pwd);
     }
    
}
