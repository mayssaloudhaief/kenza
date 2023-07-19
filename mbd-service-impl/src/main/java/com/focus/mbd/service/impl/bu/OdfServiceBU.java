/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bu;

import com.focus.mbd.dal.SamtrRepository;
import com.focus.mbd.infra.model.Samtr;
import java.io.FileOutputStream;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.dom.element.text.TextPElement;
import org.odftoolkit.odfdom.dom.element.text.TextSElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.NodeList;

/**
 *
 * @author hp
 */
@Service
public class OdfServiceBU {
    @Autowired
    private SamtrRepository samtrRepository;
     public void modifyOdfDocument(String filePath, String projectName, String baselineName, String reviewerName, String approverName) {
            try {

                OdfTextDocument textDocument = OdfTextDocument.loadDocument(filePath);

                NodeList paragraphNodes = textDocument.getContentRoot().getElementsByTagNameNS("urn:oasis:names:tc:opendocument:xmlns:text:1.0", "p");

                for (int i = 0; i < paragraphNodes.getLength(); i++) {
                    String paragraphText = paragraphNodes.item(i).getTextContent();

                    if (paragraphText != null && paragraphText.contains("Project name")) {
                        TextPElement paragraphElement = (TextPElement) paragraphNodes.item(i + 1);
                        TextSElement nextSpanElement = paragraphElement.newTextSElement();
                        nextSpanElement.setTextContent(projectName);
                        paragraphElement.appendChild(nextSpanElement);
                        String updatedParagraph = paragraphText.replace("Project name", projectName);
                        paragraphElement.setTextContent(updatedParagraph);
                    }
                    if (paragraphText != null && paragraphText.contains("Project baseline")) {
                        TextPElement paragraphElement = (TextPElement) paragraphNodes.item(i + 1);
                        TextSElement nextSpanElement = paragraphElement.newTextSElement();
                        nextSpanElement.setTextContent(baselineName);
                        paragraphElement.appendChild(nextSpanElement);
                        String updatedParagraph = paragraphText.replace("Project baseline", baselineName);
                        paragraphElement.setTextContent(updatedParagraph);
                    }
                    if (paragraphText != null && paragraphText.contains("Test report reviewer")) {
                        TextPElement paragraphElement = (TextPElement) paragraphNodes.item(i + 1);
                        TextSElement nextSpanElement = paragraphElement.newTextSElement();
                        nextSpanElement.setTextContent(reviewerName);
                        paragraphElement.appendChild(nextSpanElement);
                        String updatedParagraph = paragraphText.replace("Test report reviewer", reviewerName);
                        paragraphElement.setTextContent(updatedParagraph);
                    }
                    if (paragraphText != null && paragraphText.contains("Approver")) {
                        TextPElement paragraphElement = (TextPElement) paragraphNodes.item(i + 1);
                        TextSElement nextSpanElement = paragraphElement.newTextSElement();
                        nextSpanElement.setTextContent(approverName);
                        paragraphElement.appendChild(nextSpanElement);
                        String updatedParagraph = paragraphText.replace("Approver", approverName);
                        paragraphElement.setTextContent(updatedParagraph);
                    }
                }

                FileOutputStream outputStream = new FileOutputStream(filePath);
                textDocument.save(outputStream);
                outputStream.close();

                textDocument.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
     
      public void saveSamtr(Samtr samtr)
    {
        samtrRepository.save(samtr);
    }
    
    public Samtr findSamtrById(Long id)
    {
        return samtrRepository.findById(id).orElse(null);
    }
    
    public void updateSamtr(Samtr samtr)
    {
        samtrRepository.save(samtr);
       
    }
    public void deleteSamtrById(Long id)
    {
        samtrRepository.deleteById(id);
    }
    
    
}
