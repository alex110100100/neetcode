package com.alex.neetcode150.stack.reversePolishNotation;


import java.util.Stack;

class Solution {
    public int evalRPN(String[] tokens) {

        // iterate through tokens
        // add each to a stack
        // unless its an operator, in which case perform the operation (by popping 2 from stack) and add result to stack
        // at the end there should be only 1 element left on the stack, return that

        Stack<Integer> operands = new Stack<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    operands.push(operands.pop() + operands.pop());
                    break;
                case "-":
                    operands.push(-operands.pop() + operands.pop());
                    break;
                case "*":
                    operands.push(operands.pop() * operands.pop());
                    break;
                case "/":
                    Integer a = operands.pop();
                    Integer b = operands.pop();
                    operands.push(b/a);
                    break;
                default:
                    operands.push(Integer.parseInt(token));
            }
        }

        return operands.pop();
    }
}



class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Basic arithmetic
        String[] tokens1 = {"2", "1", "+", "3", "*"};
        int expected1 = 9;  // ((2 + 1) * 3) = 9
        int result1 = solution.evalRPN(tokens1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "Passed" : "Failed"));
        System.out.println("Input: " + String.join(" ", tokens1));
        System.out.println("Expected: " + expected1 + ", Got: " + result1 + "\n");

        // Test case 2: Division and subtraction
        String[] tokens2 = {"4", "13", "5", "/", "+"};
        int expected2 = 6;  // (4 + (13 / 5)) = 6
        int result2 = solution.evalRPN(tokens2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "Passed" : "Failed"));
        System.out.println("Input: " + String.join(" ", tokens2));
        System.out.println("Expected: " + expected2 + ", Got: " + result2 + "\n");

        // Test case 3: Complex expression
        String[] tokens3 = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int expected3 = 22;  // (10 * (6 / ((9 + 3) * -11))) * 17 + 5
        int result3 = solution.evalRPN(tokens3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "Passed" : "Failed"));
        System.out.println("Input: " + String.join(" ", tokens3));
        System.out.println("Expected: " + expected3 + ", Got: " + result3 + "\n");

        // Test case 4: Single number
        String[] tokens4 = {"42"};
        int expected4 = 42;
        int result4 = solution.evalRPN(tokens4);
        System.out.println("Test Case 4: " + (result4 == expected4 ? "Passed" : "Failed"));
        System.out.println("Input: " + String.join(" ", tokens4));
        System.out.println("Expected: " + expected4 + ", Got: " + result4 + "\n");
    }
}

