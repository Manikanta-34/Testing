package junitTest;

import static org.junit.Assert.*;

import org.junit.Test;

public class StringASCIITest {

	@Test
	public void test() {
		StringASCII obj=new StringASCII();
		
		assertEquals("ABC", "ABCd");
		//fail("Not yet implemented");
	}

}
