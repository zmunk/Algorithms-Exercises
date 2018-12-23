package pkg;

import java.util.Stack;

public class BalancedParenth {
	
	public static boolean balanced(String input) {
		String[] values = input.split("");
		Stack<String> parentheses = new Stack<String>();
		for (int i = 0; i < values.length; i++) {
			String curr = values[i];
			if (curr.equals("(") || curr.equals("[") || curr.equals("{")) {
				parentheses.push(curr);
				continue;
			}
			String last = parentheses.pop();
			if (last.equals("(") && curr.equals(")") ||
				last.equals("[") && curr.equals("]") ||
				last.equals("{") && curr.equals("}")) {
				
				continue;
			} else {
				parentheses.push(last);
				parentheses.push(curr);
			}
		}
		if (parentheses.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(balanced("[()]{}{[()()]()}"));
		System.out.println(balanced("[(])"));
	}
	
}
