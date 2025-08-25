package com.studentUnitTests.syudentest;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SyudentestApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
	@Test
	void testMain() {
		StudentApplication.main(new String[]{}); // Just invoke main method
	}

}
