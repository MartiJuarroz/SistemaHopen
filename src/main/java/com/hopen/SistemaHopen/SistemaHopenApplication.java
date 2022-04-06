package com.hopen.SistemaHopen;

import com.hopen.SistemaHopen.UI.LoginForm;
import com.hopen.SistemaHopen.entities.Usuario;
import java.util.Arrays;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SistemaHopenApplication {

	public static void main(String[] args) {
		SpringApplication.run(SistemaHopenApplication.class, args);
     /*           ApplicationContext ctx = SpringApplication.run(SistemaHopenApplication.class, args);

        System.out.println("List of beans provided by Spring Boot:");
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.print(beanName);
            System.out.print(" ");
        }

        System.out.println("");*/
    }
	}


