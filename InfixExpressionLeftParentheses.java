// Write a program that takes an expression without right parentheses
//	 and prints the equivalent infix expression with the parentheses inserted.
//   e.g. " ( ( ( 1 + 2  * ( 3 - 4  * ( 5 - 6 "

package pkg;

import java.util.Stack;

public class InfixExpressionLeftParentheses {
	public static void printInfix(String input) {
		String[] values = input.split("\\s");
		Stack<String> stack = new Stack<String>();
		for (int i = values.length - 1; i >= 0; i--) {
			String value = values[i];
			if (value.equals("(")) {
				String a = stack.pop();
        String op = stack.pop();
				String b = stack.pop();
				stack.push("( " + a + " " + op + " " + b + " )");
			} else {
				// value is an integer or an operator
				stack.push(value);
			}
		}
		System.out.println(stack.pop());
	}
	
	public static void main(String[] args) {
		printInfix(" ( ( ( 1 + 2  * ( 3 - 4  * ( 5 - 6 ");
	}
	
}
