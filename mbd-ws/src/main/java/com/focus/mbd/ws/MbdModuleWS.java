/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.ws;

import com.focus.mbd.infra.entity.TestComponent;
import com.focus.mbd.infra.entity.Account;
import com.focus.mbd.infra.model.Samtr;
import com.focus.mbd.infra.model.SwattLog;
import com.focus.mbd.service.api.MbdModule;
import com.focus.mbd.service.impl.bu.AuthentificationServiceBU;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * V2M + CB + M2V
 * @author hp
 */
@RestController
public class MbdModuleWS {

    @Autowired
    private MbdModule module;
    @Autowired
    private AuthentificationServiceBU authService;
   @PostMapping(value="/component")
    public void newComponent(@RequestBody TestComponent testcomponent) {
        module.createComponent(testcomponent.getNameCompo(),testcomponent.getPackagePath(),testcomponent.getFolderPath());
    }

    @PostMapping(value="/remplissageSamtr")
    public void fillSamtr(@RequestBody Samtr samtr) {
        module.fillSamtr(samtr.getFilePath(), samtr.getProjectName(), samtr.getBaselineName(), samtr.getReviewerName(), samtr.getApproverName());
    }

    @PostMapping(value="/ResultSwatt")
    public void TestResult(@RequestBody SwattLog swattLog) {
        module.testSwatt(swattLog.getSwattLogFilePath(), swattLog.getDetailsFilePath());
    }
    
   @PostMapping(value="/registerUser")
    public void addUser(@RequestBody Account account) {
         module.registerUser(account.getUserName(), account.getPwd());
       
    }

    
    @PostMapping(value="/loginUser")
    public void login(@RequestBody Account account) {
         module.loginUser(account.getUserName(), account.getPwd());
         
    }
    
    @PutMapping("/{accountId}")
    public void updateAccount(@PathVariable Long accountId,@RequestBody Account account) {
        authService.updateAccount(account);
    }

    @DeleteMapping("/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        authService.deleteAccountById(accountId);
    }
}
