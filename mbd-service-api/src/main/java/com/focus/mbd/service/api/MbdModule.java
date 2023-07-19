/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.focus.mbd.service.api;

import org.springframework.stereotype.Service;


/**
 *
 * @author hp
 */
@Service
public interface MbdModule {

    /*void sendSamtr(String id0);*/
    void createComponent(String folderName,String packagePath, String folderPath);
    void fillSamtr(String filePath,String projectName,String baselineName,String reviewerName,String approverName);
    void testSwatt(String swattLogFilePath,String detailsFilePath) ;
    void loginUser (String userName, String pwd);
    void registerUser(String userName,String pwd);

}
