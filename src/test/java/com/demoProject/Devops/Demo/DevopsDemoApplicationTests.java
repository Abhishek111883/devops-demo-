package com.demoProject.Devops.Demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DevopsDemoApplicationTests {

	@Test
	public void test1_addition() {
		int result = 2 + 2;
		assertEquals(4, result);
		System.out.println("Test1 - Addition test passed");
	}

	@Test
	public void test2_stringComparison() {
		String hello = "Hello";
		assertTrue(hello.startsWith("H"));
		System.out.println("Test2 - String test passed");
	}

	@Test
	public void test3_booleanCheck() {
		boolean flag = true;
		assertNotEquals(false, flag);
		System.out.println("Test3 - Boolean test passed");
	}

	@Test
	public void test4_objectNotNull() {
		Object obj = new Object();
		assertNotNull(obj);
		System.out.println("Test4 - Not null test passed");
	}

}
