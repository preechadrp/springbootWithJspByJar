package com.example.springbootWithJsp.components;

import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class StartTask {

	static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(StartTask.class);
	
	@PostConstruct
	public void init() {
		//ทำงานตอนเริ่มระบบครั้งแรก
		log.info("Start Task...");
	}
}
