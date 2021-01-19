package junitTest;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class BeforeAfterAnnotationTest {

	
	/*
	 * @Before public void beforeAnnotation() {
	 * System.out.println("before executed"); }
	 */
	 
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("before class");
	}
	@Test
	public void test1() {
		System.out.println("test1 executed");
	}
	
	@Test
	public void test2() {
		System.out.println("test2 executed");
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("after class");
	}
	
	/*
	 * @After public void afterAnnotation() { System.out.println("after executed");
	 * }
	 */
	 
	

}
