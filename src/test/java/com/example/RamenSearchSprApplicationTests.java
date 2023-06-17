package com.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import pack.RamenSearchSprApplication;

//@SpringBootTest
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RamenSearchSprApplication.class)
@AutoConfigureMockMvc
class RamenSearchSprApplicationTests {

	@Test
	void contextLoads() {
	}

}
