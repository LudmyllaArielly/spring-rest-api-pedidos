package com.ludmylla.springloja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EntityScan(basePackages = "com.ludmylla.spring.loja.model")
@ComponentScan(basePackages = {"com.ludmylla.*"})
@EnableJpaRepositories(basePackages = {"com.ludmylla.spring.loja.repository"})
@EnableTransactionManagement
public class SpringLojaApplication {

    public static void main(String[] args) {
    	
        SpringApplication.run(SpringLojaApplication.class, args);
	
		/*BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String result = encoder.encode("123");
		System.out.println(result);*/
    }

}
