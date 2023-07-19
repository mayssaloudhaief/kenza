/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.focus.mbd.service.impl.bu;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.stereotype.Service;

/**
 *
 * @author hp
 */

@Service
public class ProcessRunnerServiceBU {
   

        public String run(String[] command, String cwd) {
            StringBuilder sb = new StringBuilder();
            try {
                ProcessBuilder pb = new ProcessBuilder(command);
                pb.directory(new File(cwd));
                pb.redirectError();
                Process p = pb.start();
                final InputStream out = p.getInputStream();
                boolean[] finished = new boolean[1];
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            byte[] buffer = new byte[2048];
                            int r;
                            while (true) {
                                if (out.available() > 0) {
                                    if ((r = out.read(buffer)) > 0) {
                                        sb.append(new String(buffer, 0, r));
                                    }
                                } else if (finished[0]) {
                                    break;
                                } else {
                                    Thread.sleep(1000);
                                }
                            }
                        } catch (Exception ex) {
                            //
                        }
                    }
                }.start();
                int e = p.waitFor();
                finished[0] = true;
                if (e != 0) {
                    throw new RuntimeException(sb.toString());
                }
                return sb.toString();
            } catch (IOException | InterruptedException ex) {
                throw new RuntimeException(ex + " : " + sb.toString());
            }
        }
    
      }
