package com.tx0x.nocalleridbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.Locale;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@MapperScan(basePackages = "com.tx0x.nocalleridbackend.*")
//@SpringBootApplication
public class NoCallerIdBackendApplication {
    public static void main(String[] args) {
        Locale.setDefault(Locale.KOREA);
        SpringApplication.run(NoCallerIdBackendApplication.class, args);
    }

}
