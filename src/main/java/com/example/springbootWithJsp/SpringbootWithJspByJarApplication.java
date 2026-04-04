package com.example.springbootWithJsp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan(basePackages = {"com.example.springbootWithJsp.servlet"})  //ให้ไป scan หาพวกที่ใช้ @WebServlet,@WebFilter,@WebListener
@SpringBootApplication
public class SpringbootWithJspByJarApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootWithJspByJarApplication.class, args);
	}

}
