// 1.4.12 Write a program that, given two sorted arrays of N int values, prints all elements
//        that appear in both arrays, in sorted sorder. The running time of your program should be
//        proportional to N in the worst case.

package pkg;

public class CombineArrays {
	public static void combine (int[] a, int[] b) {
		int ai = 0;
		int bi = 0;
		while (true) {
			if (ai >= a.length || bi >= b.length)
				break;
			if (a[ai] < b[bi])
				ai++;
			else if (a[ai] > b[bi])
				bi++;
			else {
				System.out.printf("%d ", a[ai]);
				ai++;
				bi++;
			}
		}
	}
	
	public static void main (String[] args) {
		int[] a = new int[] {1, 2, 2, 3, 17, 29};
		int[] b = new int[] {2, 2, 29, 219};
		combine(a, b);
	}
}
