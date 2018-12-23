// 1.3.9 Write a program that takes an expression without left parentheses
//	 and prints the equivalent infix expression with the parentheses inserted.

package pkg;

import java.util.Stack;

public class InfixExpression {
	public static void printInfix(String input) {
		String[] values = input.split("\\s");
		Stack<String> operators = new Stack<String>();
		Stack<String> operands = new Stack<String>();
		for (int i = 0; i < values.length; i++) {
			String value = values[i];
			if (value.equals(")")) {
				String b = operands.pop();
				String a = operands.pop();
				String op = operators.pop();
				operands.push("( " + a + " " + op + " " + b + " )");
			} else if (value.equals("+") || value.equals("-") || value.equals("*") || value.equals("/")) {
				operators.push(value);
			} else {
				// value is an integer
				operands.push(value);
			}
		}
		System.out.println(operands.pop());
	}
	
	public static void main(String[] args) {
		printInfix("1 + 2 ) * 3 - 4 ) * 5 - 6 ) ) )");
	}
	
}
