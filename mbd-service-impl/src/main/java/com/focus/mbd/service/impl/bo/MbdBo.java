/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bo;
import com.focus.mbd.service.impl.bu.ComponentServiceBU;
import com.focus.mbd.service.impl.bu.EmailServiceBU;
import com.focus.mbd.service.impl.bu.OdfServiceBU;
import com.focus.mbd.service.impl.bu.SwattLogServiceBU;
import java.io.IOException;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class MbdBo {
     private EmailServiceBU emailService;
     private ComponentServiceBU compoService;
     private OdfServiceBU odfService;
     private SwattLogServiceBU swattlogService;
     
     
     
    
    /*public void sendSamtr(String id0){
      emailService.sendEmailWithAttachments(id0, id0, id0, id0, id0, attachFiles);
       
    }*/
    
    public void fillSamtr(String filePath,String projectName,String baselineName,String reviewerName,String approverName)
    {
        odfService.modifyOdfDocument(filePath, projectName, baselineName, reviewerName, approverName);
    }
    
     public void testSwatt(String swattLogFilePath,String detailsFilePath) throws IOException
    {
       swattlogService.processSwattLog(swattLogFilePath, detailsFilePath);
        
    }
     
     public void createComponent(String folderName , String packagePath, String folderPath)
    {
        compoService.addComponent(folderName , packagePath, folderPath);
    }
     
    
    
            
    
}
