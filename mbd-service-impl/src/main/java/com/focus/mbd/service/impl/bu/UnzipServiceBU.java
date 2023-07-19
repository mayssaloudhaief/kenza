/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bu;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class UnzipServiceBU {
     public void unzip() {
           /* Context cn = new Context();
            //String s= cn.getMap();
            String s = cn.getDossierDestination();
            String fz = cn.getFichierZip();
            String fichierZip = fz;
            String dossierDestination = s;*/
            String fichierZip = "C:\\/Users/hp/Downloads/KenzaLadh.zip";
            String dossierDestination = "C:\\/Users/hp/Downloads/KenzaLadh";
            byte[] buffer = new byte[1024];
            try {
                // Création du dossier de destination s'il n'existe pas déjà
                File dossier = new File(dossierDestination);
                if (!dossier.exists()) {
                    dossier.mkdir();
                }
                // Ouverture de l'archive zip
                ZipInputStream zis = new ZipInputStream(new FileInputStream(fichierZip));
                // Parcours des entrées de l'archive
                ZipEntry ze = zis.getNextEntry();
                while (ze != null) {
                    String nomFichier = ze.getName();
                    if (nomFichier.charAt(nomFichier.length() - 1) != '/') {
                        File nouveauFichier = new File(dossierDestination + File.separator + nomFichier);
                        // Création des dossiers nécessaires pour le fichier
                        new File(nouveauFichier.getParent()).mkdirs();
                        // Écriture du fichier sur le disque
                        FileOutputStream fos = new FileOutputStream(nouveauFichier);
              
                        int longueur;
                        while ((longueur = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, longueur);
                        }
                        zis.closeEntry();
                        fos.close();
                    }
                    ze = zis.getNextEntry();
                }

                zis.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    

