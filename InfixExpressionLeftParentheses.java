// Write a program that takes an expression without right parentheses
//	 and prints the equivalent infix expression with the parentheses inserted.
//   e.g. " ( ( ( 1 + 2 * ( 3 - 4 * ( 5 - 6 "

package pkg;

import java.util.Stack;

public class InfixExpressionLeftParentheses {
    public static void printInfix(String input) {
        Stack<String> stack = new Stack<String>();
        for (int i = input.length() - 1; i >= 0; i--) {
            String value = input.substring(i, i + 1);
            if (value.equals(" "))
                continue;
            System.out.println(value);
            if (value.equals("("))
                stack.push("( " + stack.pop() + " " + stack.pop() + " " + stack.pop() + " )");
            else
                stack.push(value);
        }
        System.out.println(stack.pop());
    }
    
    static void main(String[] args) {
        printInfix(" ( ( ( 1 + 2 * ( 3 - 4 * ( 5 - 6 ");
    }
}
