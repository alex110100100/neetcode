package com.alex.neetcode150.stack.validParentheses;

import java.util.Stack;

class Solution {
    public boolean isValid(String s) {
        Stack<Character> charStack = new Stack<>();

        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                case '[':
                case '{':
                    charStack.push(c);
                    break;

                case ')':
                    if (charStack.isEmpty() || charStack.pop() != '(') return false;
                    break;
                case ']':
                    if (charStack.isEmpty() || charStack.pop() != '[') return false;
                    break;
                case '}':
                    if (charStack.isEmpty() || charStack.pop() != '{') return false;
                    break;
            }
        }
        return charStack.isEmpty();
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1: Valid balanced parentheses
        String s1 = "()[]{}";
        boolean expected1 = true;
        boolean result1 = solution.isValid(s1);
        System.out.println("Test Case 1: " + (result1 == expected1 ? "Passed" : "Failed"));
        System.out.println("Input: s = " + s1);
        System.out.println("Expected: " + expected1 + ", Got: " + result1 + "\n");

        // Test case 2: Invalid nested parentheses
        String s2 = "([)]";
        boolean expected2 = false;
        boolean result2 = solution.isValid(s2);
        System.out.println("Test Case 2: " + (result2 == expected2 ? "Passed" : "Failed"));
        System.out.println("Input: s = " + s2);
        System.out.println("Expected: " + expected2 + ", Got: " + result2 + "\n");

        // Test case 3: Single character
        String s3 = "[";
        boolean expected3 = false;
        boolean result3 = solution.isValid(s3);
        System.out.println("Test Case 3: " + (result3 == expected3 ? "Passed" : "Failed"));
        System.out.println("Input: s = " + s3);
        System.out.println("Expected: " + expected3 + ", Got: " + result3 + "\n");

        // Test case 4: Valid nested parentheses
        String s4 = "{[()]}";
        boolean expected4 = true;
        boolean result4 = solution.isValid(s4);
        System.out.println("Test Case 4: " + (result4 == expected4 ? "Passed" : "Failed"));
        System.out.println("Input: s = " + s4);
        System.out.println("Expected: " + expected4 + ", Got: " + result4 + "\n");
    }
}

