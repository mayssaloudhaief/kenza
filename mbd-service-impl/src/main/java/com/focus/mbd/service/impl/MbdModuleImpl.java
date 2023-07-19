/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl;

import com.focus.mbd.service.api.MbdModule;
import com.focus.mbd.service.impl.bo.CoreBO;
import com.focus.mbd.service.impl.bo.MbdBo;
import lombok.experimental.Delegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Facade
 * @author hp
 */
@Service
public class MbdModuleImpl implements MbdModule{
    @Autowired
    @Delegate
    private MbdBo mbdBo;
    
    @Autowired
    @Delegate
    private CoreBO coreBo;

  
    
 
    
   
    
}
