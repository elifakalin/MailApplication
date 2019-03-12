package com.send.mail.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication(scanBasePackages="com.send.mail.demo")
@EnableAsync
public class SendMailApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(SendMailApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SendMailApplication.class);
    }

    @Bean(value = "sendMailExecutor")
    public TaskExecutor taskExecutor() {
   
        return new ThreadPoolTaskExecutor();
    }
}
