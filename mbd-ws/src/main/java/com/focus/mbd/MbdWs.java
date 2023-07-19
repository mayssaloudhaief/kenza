/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.focus.mbd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 *
 * @author hp
 */
@SpringBootApplication
@EnableJpaRepositories("com.focus.mbd.dal")
public class MbdWs {

    public static void main(String[] args) {
        SpringApplication.run(MbdWs.class, args);
    }
}
