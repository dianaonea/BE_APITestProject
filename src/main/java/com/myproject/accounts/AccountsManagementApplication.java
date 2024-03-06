package com.myproject.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication (scanBasePackages = {"com.myproject.accounts.controller", "com.myproject.accounts.service"})
public class AccountsManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsManagementApplication.class, args);
    }
}