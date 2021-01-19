package junitTest;

import static org.junit.Assert.assertArrayEquals;

import java.util.Arrays;

import org.junit.Test;

public class ArrayExample {

	@Test
	public void testArraySort() {
		int[] numbers= {2,4,1,3};
		int[] expected= {1,2,3,4};
		
		Arrays.sort(numbers);
		
		assertArrayEquals(expected, numbers);
		
	}

}
