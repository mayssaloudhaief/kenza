/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.infra.entity;

import com.focus.mbd.infra.model.Samtr;
import com.focus.mbd.infra.model.SwattLog;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Data;

/**
 *
 * @author hp
 */
@Data
@Entity
public class TestComponent {
    @Id 
    @GeneratedValue
    private Long id;
    
      @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "message", column = @Column(name = "samtr_message")),
        @AttributeOverride(name = "objet", column = @Column(name = "samtr_objet")),
        @AttributeOverride(name = "filePath", column = @Column(name = "samtr_filePath")),
        @AttributeOverride(name = "projectName", column = @Column(name = "samtr_projectName")),
        @AttributeOverride(name = "baselineName", column = @Column(name = "samtr_baselineName")),
        @AttributeOverride(name = "reviewerName", column = @Column(name = "samtr_reviewerName")),
        @AttributeOverride(name = "approverName", column = @Column(name = "samtr_approverName"))
    })
    private Samtr samtr;
    
    @Embedded
    private SwattLog swattLog;
    
    private String nameCompo;
    private String packagePath;
    private String folderPath;
    @ManyToOne
    private Account owner;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Samtr getSamtr() {
        return samtr;
    }

    public void setSamtr(Samtr samtr) {
        this.samtr = samtr;
    }

    public SwattLog getSwattLog() {
        return swattLog;
    }

    public void setSwattLog(SwattLog swattLog) {
        this.swattLog = swattLog;
    }

   

    public String getNameCompo() {
        return nameCompo;
    }

    public void setNameCompo(String nameCompo) {
        this.nameCompo = nameCompo;
    }

    public String getPackagePath() {
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public Account getOwner() {
        return owner;
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }


    
    
}
