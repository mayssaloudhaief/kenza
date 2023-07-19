/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bu;

import com.focus.mbd.dal.TestComponentRepository;
import com.focus.mbd.infra.entity.TestComponent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class ComponentServiceBU {
    
    @Autowired
    private TestComponentRepository testcomponenttRepository;
    
    public void addComponent(String folderName , String packagePath, String folderPath) {
            //String folderPath = "C:\\/Users/hp/Desktop/" + folderName; // Chemin du dossier à créer
            File folder = new File(folderPath);

            // Vérifier si le dossier existe déjà ou non
            if (!folder.exists()) {
                boolean success = folder.mkdir(); // Créer le dossier
               // String sourcePath = "C:\\/Users/hp/Downloads/" + folderName;
                String destinationPath = folderPath;

                File sourceDirectory = new File(packagePath);
                File[] files = sourceDirectory.listFiles();

                if (files != null && files.length > 0) {
                    // Copier les fichiers dans le répertoire de destination
                    for (File file : files) {
                        try {
                            Path sourceFilePath = file.toPath();
                            Path destinationFilePath = new File(destinationPath, file.getName()).toPath();
                            Files.copy(sourceFilePath, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
                            System.out.println("Le fichier a été copié avec succès !");
                        } catch (IOException e) {
                            System.out.println("Erreur lors de la copie du fichier : " + e.getMessage());
                        }
                    }
                }

                if (success) {
                    System.out.println("Le dossier a été créé avec succès !");
                } else {
                    System.out.println("Erreur lors de la création du dossier.");
                }
            } else {
                System.out.println("Le dossier existe déjà !");
            }
        }
    
      public void saveTestComponent(TestComponent testcomponent)
    {
        testcomponenttRepository.save(testcomponent);
    }
    
    public TestComponent findTestComponentById(Long id)
    {
        return testcomponenttRepository.findById(id).orElse(null);
    }
    
    public void updateTestComponent(TestComponent testcomponent)
    {
        testcomponenttRepository.save(testcomponent);
       
    }
    public void deleteTestComponentById(Long id)
    {
        testcomponenttRepository.deleteById(id);
    }


    
}
