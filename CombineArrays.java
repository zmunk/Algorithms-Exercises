// 1.4.12 Write a program that, given two sorted arrays of N int values, prints all elements
//        that appear in both arrays, in sorted sorder. The running time of your program should be
//        proportional to N in the worst case.

package pkg;

public class CombineArrays {
	public static int[] combine (int[] a, int[] b) {
		int[] combo = new int[a.length];
		int comboInd = 0;
		int ai = 0; // index of array a
		int bi = 0; // index of array b
		while (true) {
			if (ai >= a.length || bi >= b.length)
				break;  // out of bounds
			if (a[ai] < b[bi])  // if current value from array a is smaller, move to next value
				ai++;
			else if (a[ai] > b[bi])  // if current value from array b is smaller, move to next value
				bi++;
			else {
				// if the two values are equal, we have found a matching value
				combo[comboInd++] = a[ai];  // add to our array
				ai++;
				bi++;
			}
		}
		int[] finalArr = new int[comboInd];
		for (int i = 0; i < comboInd; i++)  // get rid of extra spaces in array
			finalArr[i] = combo[i];
		return finalArr;
	}
	
	public static void main (String[] args) {
		int[] a = new int[] {1, 2, 2, 3, 17, 29};
		int[] b = new int[] {2, 2, 29, 219};
		int[] c = combine(a, b);
		for (int i = 0; i < c.length; i++)
			System.out.printf("%d ", c[i]);
	}
}
