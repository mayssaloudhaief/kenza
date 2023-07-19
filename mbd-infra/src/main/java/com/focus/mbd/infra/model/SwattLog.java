/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.infra.model;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
@Embeddable
public class SwattLog {
    
     private String swattLogFilePath;
     private String detailsFilePath;
     
    public String getSwattLogFilePath() {
        return swattLogFilePath;
    }

    public void setSwattLogFilePath(String swattLogFilePath) {
        this.swattLogFilePath = swattLogFilePath;
    }

    public String getDetailsFilePath() {
        return detailsFilePath;
    }

    public void setDetailsFilePath(String detailsFilePath) {
        this.detailsFilePath = detailsFilePath;
    }
    
}
