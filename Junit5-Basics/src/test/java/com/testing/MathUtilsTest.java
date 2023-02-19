package com.testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestReporter;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MathUtilsTest {
	
	private MathUtils mathUtils;
    TestInfo testInfo;
	TestReporter testReporter;
	
	
//	this method shoud be static if you want to run before all others...
//	@BeforeAll
//	void beforeAlInit() {
//		System.out.println("this method will run before all other...");
//	}
	@BeforeAll
	static void beforeAlInit() {
		System.out.println("this method will run before all other...");
	}
	
	
	@BeforeEach
	void init(TestInfo testInfo,TestReporter testReporter) {
		this.testInfo=testInfo;
		this.testReporter=testReporter;
		mathUtils = new MathUtils();
		testReporter.publishEntry("running "+testInfo.getDisplayName()+" with tags "+testInfo.getTags());
	}
	
	@RepeatedTest(4)
	@DisplayName("Testing add method")
	void testAddition() {
		assertEquals(20, mathUtils.add(19, 1),"this method should add two integer number...");
	}
	@Test
	@DisplayName("Testing add method")
	void testAddition1() {
		int excepted =-20;
		int actual=mathUtils.add(-19, -1);
		assertEquals(excepted, actual,()-> "this method should add two integer number and actual should be "+ actual+" but it giving me "+excepted );
	}
	

	@Nested
	@DisplayName("substraction of integers")
	class SubClassForSubstraction{
		@Test
		void testSubstraction1() {
			int excepted =18;
			int actual=mathUtils.subtract(19, 1);
			assertEquals(excepted, actual,"this method should sub two +ve integer number...");
		}
		@Test
		void testSubstraction2() {
			assertEquals(20, mathUtils.subtract(19, -1),"this method should sub two -ve integer number...");
		}
	}
	
	
	@AfterEach
	void cleanUp() {
		System.out.println("clean after each test runs...");
	}

	
	
	
	
	@Test
	@Tag("Math")
	@DisplayName("Multiplication of two number")
	void testMultiplication() {
//		int excepted =19;
//		int actual=mathUtils.multiply(19, 1);
//		assertEquals(excepted, actual,"this method should devide two integer number...");
		
		assertAll(
				()->assertEquals(19, mathUtils.multiply(19, 1),"this method should devide two +ve integer number..."),
				()->assertEquals(190, mathUtils.multiply(-19, -10),"this method should devide two -ve integer number..."),
				()->assertEquals(-2, mathUtils.multiply(-2, 1),"this method should devide one +ve and one -ve integer number..."),
				()->assertEquals(0, mathUtils.multiply(-2, 0),"this method should devide one +ve/-ve and one 0 integer number...")
				);
	}
	@Test
	void testDevision() {
		assertThrows(ArithmeticException.class, ()->mathUtils.devide(1, 0),"this method should add two integer number...");
	}
	@Test
	void testcircleArea() {
		double excepted =Math.PI*10*10;
		double actual=mathUtils.computeCircleArea(10);
		assertEquals(excepted, actual,"this method should return area of circle...");
	}

}
