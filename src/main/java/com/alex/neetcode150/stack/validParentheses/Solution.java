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

        String s1 = "()";                  // Valid parentheses
        String s2 = "()[]{}";              // Valid parentheses
        String s3 = "(]";                  // Invalid parentheses
        String s4 = "([)]";                // Invalid parentheses
        String s5 = "{[]}";                // Valid parentheses
        String s6 = "((()))";              // Valid parentheses

        System.out.println(solution.isValid(s1));   // Expected output: true
        System.out.println(solution.isValid(s2));   // Expected output: true
        System.out.println(solution.isValid(s3));   // Expected output: false
        System.out.println(solution.isValid(s4));   // Expected output: false
        System.out.println(solution.isValid(s5));   // Expected output: true
        System.out.println(solution.isValid(s6));   // Expected output: true
    }
}

