package com.fun.starter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
//@ComponentScan({"com.fun"})
@ImportResource("classpath:application-context.xml")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


    /*@Bean
    public FilterRegistrationBean registerFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean();
        return bean;
    }*/

}
