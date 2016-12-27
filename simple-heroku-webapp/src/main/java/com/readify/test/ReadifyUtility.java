package com.readify.test;

import java.math.BigDecimal;

public class ReadifyUtility {

	public static double FibonacciRecursive(int n) {
		if (n == 0)
			return 0;
		if (n == 1)
			return 1;
		else {
			double result = FibonacciRecursive(n - 1)
					+ FibonacciRecursive(n - 2);
			return result;
		}

	}
	
	/**
	 * Calculates fibonacci with dynamic programming technique
	 * 
	 * a caching of values in this area as a key value pair would enhance performance but since there is a limit of 92 so the performance is good without caching
	 * 
	 * @return BigDecimal for precision
	 */
	public static BigDecimal fibonacciDynamicProgramming(int x) {
		BigDecimal a = new BigDecimal(0);
		BigDecimal b = new BigDecimal(1);
		for (int i = 0; i < x; i++) {
			BigDecimal c = a.add(b);
			a = b;
			b = c;
		}
		return a;
	}
	
	/**
	 * Validates if 3 sides of triangle is above zero
	 * 
	 * 
	 * @return Response
	 */
	public static boolean validateInputs(int a, int b, int c) {
		boolean result = true;

		if (a < 1 || b < 1 || c < 1) {
			result = false;
		}

		return result;

	}
	
	
	public static String calculateTriangle(long a, long b, long c) throws Exception {

		if ((a + b) <= c || (a + c) <= b || (b + c) <= a) { // validates that triangle can be drawn
			throw new Exception("Error"); // FIXME
		}
		if (a == b && b == c) {
			return "Equilateral";
		} else if ((a == b && b != c) || (a == c && c != b)
				|| (b == c && c != a)) {
			return "Isosceles";
		} else {
			return "Scalene";
		}
	}

}
