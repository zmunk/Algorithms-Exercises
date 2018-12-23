// 1.4.8 Write a program to determine the number pairs of  values
// 	in an input ﬁle that are equal. If your ﬁrst try is quadratic,
//	think again and use Arrays.sort() to develop a linearithmic solution.

// quadratic solution
public static void main(String[] args) {
	int[] values = new In(args[0]).readAllInts();
	int count = 0;
	for (int i = 0; i < values.length; i++) {
		for (int j = i + 1; j < values.length(); j++) {
			if (values[i] == values[j])
				count++;
		}
	}
	System.out.println(count);
}

// linearithmic solution
public static void main(String[] args) {
	int[] values = new In(args[0]).readAllInts();
	int count = 0, ind;
	values.sort();
	for (int i = 0; i < values.length; i++) {
		ind = rank(values, values[i]);
		if (ind != -1 && ind != i)
			count++;
		// count is incremented twice for each pair found
	}
	System.out.println(count / 2) // compensate for double incrementation
}