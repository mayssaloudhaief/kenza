/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bu;

import com.focus.mbd.dal.SwattLogRepository;
import com.focus.mbd.infra.model.SwattLog;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */
@Service
public class SwattLogServiceBU {
     @Autowired
    private SwattLogRepository swattlogRepository;
    
        public void processSwattLog(String swattLogFilePath, String detailsFilePath) throws IOException {
            FileReader file = new FileReader(detailsFilePath);
            String regexPointer = "makes\\s+pointer\\s+from\\s+integer\\s+without\\s+a\\s+cast";
            String regexWorkaround = "_S_OUT'$";
            String outputFilePath = "/Users/hp/Desktop/PFE-2023/ResultSwatt.json";
            String passedRegex = "TESTs\\s+PASSED\\s+:\\s+(\\d+)";
            String failedRegex = "TESTs\\s+FAILED\\s+:\\s+(\\d+)";
            String unexecutedRegex = "TESTs\\s+UNEXECUTED\\s+:\\s+(\\d+)";
            List<Integer> Numbers = new ArrayList<>();
            enum TestResult {
                PASSED,
                FAILED,
                UNEXECUTED
            }
            enum ErrorType {
                POINTER,
                WORKAROUND
            }
            List<ErrorType> errors = new ArrayList<>();
            JSONObject jsonResult = new JSONObject();
            TestResult state = null;

            try (BufferedReader reader = new BufferedReader(new FileReader(swattLogFilePath))) {
                String line;
                Pattern passedPattern = Pattern.compile(passedRegex);
                Pattern failedPattern = Pattern.compile(failedRegex);
                Pattern unexecutedPattern = Pattern.compile(unexecutedRegex);

                while ((line = reader.readLine()) != null) {
                    Matcher passedMatcher = passedPattern.matcher(line);
                    Matcher failedMatcher = failedPattern.matcher(line);
                    Matcher unexecutedMatcher = unexecutedPattern.matcher(line);

                    if (passedMatcher.find()) {
                        String numberString = passedMatcher.group(1);
                        int number = Integer.parseInt(numberString);
                        Numbers.add(number);
                    }

                    if (failedMatcher.find()) {
                        String numberString = failedMatcher.group(1);
                        int number = Integer.parseInt(numberString);
                        Numbers.add(number);

                    }

                    if (unexecutedMatcher.find()) {
                        String numberString = unexecutedMatcher.group(1);
                        int number = Integer.parseInt(numberString);
                        Numbers.add(number);

                    }

                }
                System.out.println(Numbers);

                if (Numbers.get(1) == 0 && Numbers.get(2) == 0) {
                    state = TestResult.PASSED;

                } else if (Numbers.get(1) != 0 && Numbers.get(2) == 0) {
                    state = TestResult.FAILED;

                } else {
                    state = TestResult.UNEXECUTED;

                }

                switch (state) {
                    case PASSED:
                        jsonResult.put("result", state);
                        break;
                    case FAILED:
                        jsonResult.put("result", state);
                        break;
                    case UNEXECUTED:
                        jsonResult.put("result", state);
                        try (BufferedReader reader2 = new BufferedReader(file)) {
                            String lines;
                            Pattern workaroundPattern = Pattern.compile(regexWorkaround);
                            Pattern pointerPattern = Pattern.compile(regexPointer);

                            while ((lines = reader2.readLine()) != null) {
                                Matcher pointerMatcher = pointerPattern.matcher(lines);
                                Matcher workaroundMatcher = workaroundPattern.matcher(lines);

                                if (pointerMatcher.find()) {
                                    errors.add(ErrorType.POINTER);

                                }

                                if (workaroundMatcher.find()) {
                                    errors.add(ErrorType.WORKAROUND);

                                }

                            }

                            reader.close();
                            for (int i = 0; i < errors.size(); i++) {
                                ErrorType error = errors.get(i);
                                if (error == ErrorType.POINTER) {
                                    jsonResult.put("error 1", "pointer_issue");
                                }
                                if (error == ErrorType.WORKAROUND) {
                                    jsonResult.put("error 2 ", "workaround_NVM_issue");
                                }
                                if (error != ErrorType.WORKAROUND && error != ErrorType.POINTER) {
                                    jsonResult.put("error", "Unknown_issue");
                                }
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Écrire les résultats dans un fichier JSON
            try (FileWriter fileWriter = new FileWriter(outputFilePath)) {
                fileWriter.write(jsonResult.toJSONString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
          public void saveSwattlog(SwattLog swattlog)
    {
        swattlogRepository.save(swattlog);
    }
    
    public SwattLog findSwattlogById(Long id)
    {
        return swattlogRepository.findById(id).orElse(null);
    }
    
    public void updateSwattlog(SwattLog swattlog)
    {
        swattlogRepository.save(swattlog);
       
    }
    public void deleteSwattlogById(Long id)
    {
        swattlogRepository.deleteById(id);
    }
    
    
}
