package com.fun.test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;

/**
 * 单元测试的Spring Boot配置类
 */
@SpringBootApplication
//@PropertySource(value = { "classpath:application.properties" })
@ImportResource("classpath:application-context.xml")
public class TestApplication {

}
