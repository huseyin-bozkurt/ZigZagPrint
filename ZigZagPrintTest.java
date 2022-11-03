import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
 * ZigZagPrintTest has a test which checks whether given input array traversed in zigzag, starting from right upper corner, order or not.
 * 1,  2, 3, 4
 * 5,  6, 7, 8
 * 9, 10,11,12
 * 13,14,15,16
 * traversed as 4, 3, 8, 12, 7, 2, 1, 6, 11, 16, 15, 10, 5, 9, 14, 13
 * 
 * @author hbozkurt
 * */
public class ZigZagPrintTest {

	@Test
	public void testSpiralPrinfOfArray() {
		// null check
		assertEquals(0, printInDifferentOrder(null).size());

		// zero checks
		int[][] nullArray = new int[0][5];
		assertEquals(0, printInDifferentOrder(nullArray).size());
		int[][] zeroArray = new int[5][0];
		assertEquals(0, printInDifferentOrder(zeroArray).size());

		// check n*n array
		int[][] input = { { 1 } };
		List<Integer> expectedResult = new ArrayList<>(Arrays.asList(1));
		List<Integer> result = printInDifferentOrder(input);
		assertEquals(1, result.size());
		assertEquals(expectedResult, result);

		int[][] input2 = { { 1, 2 }, { 3, 4 }, };
		expectedResult = new ArrayList<>(Arrays.asList(2, 1, 4, 3));
		result = printInDifferentOrder(input2);
		assertEquals(4, result.size());
		assertEquals(expectedResult, result);

		int[][] input3 = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		expectedResult = new ArrayList<>(Arrays.asList(3, 2, 6, 9, 5, 1, 4, 8, 7));
		result = printInDifferentOrder(input3);
		assertEquals(9, result.size());
		assertEquals(expectedResult, result);

		int[][] input4 = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };
		expectedResult = new ArrayList<>(Arrays.asList(4, 3, 8, 12, 7, 2, 1, 6, 11, 16, 15, 10, 5, 9, 14, 13));
		result = printInDifferentOrder(input4);
		assertEquals(16, result.size());
		assertEquals(expectedResult, result);

		// check 1*2 array
		int[][] input5 = { { 1 }, { 2 } };
		expectedResult = new ArrayList<>(Arrays.asList(1, 2));
		result = printInDifferentOrder(input5);
		assertEquals(2, result.size());
		assertEquals(expectedResult, result);

		// check 4*6 array
		int[][] input6 = { { 1, 2, 3, 4, 5, 6 }, { 16, 17, 18, 19, 20, 7 }, { 15, 24, 23, 22, 21, 8 },
				{ 14, 13, 12, 11, 10, 9 } };
		expectedResult = new ArrayList<>(
				Arrays.asList(6, 5, 7, 8, 20, 4, 3, 19, 21, 9, 10, 22, 18, 2, 1, 17, 23, 11, 12, 24, 16, 15, 13, 14));
		result = printInDifferentOrder(input6);
		assertEquals(24, result.size());
		assertEquals(expectedResult, result);

		// check 5*8 array
		int[][] input7 = { { 1, 2, 3, 4, 5 }, { 22, 23, 24, 25, 6 }, { 21, 36, 37, 26, 7 }, { 20, 35, 38, 27, 8 },
				{ 19, 34, 39, 28, 9 }, { 18, 33, 40, 29, 10 }, { 17, 32, 31, 30, 11 }, { 16, 15, 14, 13, 12 } };
		expectedResult = new ArrayList<>(Arrays.asList(5, 4, 6, 7, 25, 3, 2, 24, 26, 8, 9, 27, 37, 23, 1, 22, 36, 38,
				28, 10, 11, 29, 39, 35, 21, 20, 34, 40, 30, 12, 13, 31, 33, 19, 18, 32, 14, 15, 17, 16));
		result = printInDifferentOrder(input7);
		assertEquals(40, result.size());
		assertEquals(expectedResult, result);
	}

	/**
	 * Traverse given input array in zigzag order starting from right upper corner
	 * 
	 * @param input is array of int which will be traversed in zigzag order starting
	 *              from right upper corner.
	 * @return zigzag order of given array as List.
	 */
	public static List<Integer> printInDifferentOrder(int[][] input) {
		// prepare empty list to keep return value
		List<Integer> solution = new ArrayList<Integer>();
		if (input != null && input.length != 0 && input[0].length != 0) {
			// start from row 1 because next move will be calculated in first step
			int startRow = 1;
			// start from column input[0].length because next move will be calculated in
			// first step
			int startColumn = input[0].length;
			// used for direction, true means goes up, false means goes down
			boolean direction = true;
			// iterate until all elements are in the list
			while (solution.size() < (input.length * input[0].length)) {
				// according to direction, decision changes
				if (direction) { // when traversing upwards
					// overflows from upper row but not in column when traversing upwards
					if (startRow - 1 < 0 && startColumn - 1 >= 0) {
						startColumn--;
						direction = false;
					} else if (startColumn - 1 < 0 && startRow - 1 >= 0) { // overflows from left column but not from
																			// row
																			// when traversing upwards
						startRow++;
						direction = false;
					} else if (startRow - 1 < 0 && startColumn - 1 <= 0) { // overflows from upper column and row, it
																			// means end corner when traversing upwards

						startRow++;
						direction = false;
					} else { // no overflows from upper column and row when traversing upwards
						startColumn--;
						startRow--;
					}
				} else {// when traversing downwards
					// overflows from lower row but not from column when traversing downwards
					if (startRow + 1 >= input.length && startColumn + 1 < input[0].length) {
						startColumn--;
						direction = true;
					} else if (startColumn + 1 >= input[0].length && startRow + 1 < input.length) {// overflows from
																									// right column but
																									// not from row when
																									// traversing
																									// downwards
						startRow++;
						direction = true;
					} else if (startRow + 1 >= input.length && startColumn + 1 >= input[0].length) {// overflows from
																									// right column and
																									// lower row, it
																									// means end
																									// corner when
																									// traversing
						startColumn--;
						direction = true;
					} else { // no overflows from lower column and row when traversing upwards
						startColumn++;
						startRow++;
					}
				}
				solution.add(input[startRow][startColumn]);
			}
		}
		return solution;
	}

}
