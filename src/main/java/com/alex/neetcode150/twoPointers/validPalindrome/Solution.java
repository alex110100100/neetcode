package com.alex.neetcode150.twoPointers.validPalindrome;


class Solution {
    public boolean isPalindrome(String s) {

        int i = 0;
        int j = s.length() - 1;
        while (i < j) {

            char left = s.charAt(i);
            char right = s.charAt(j);

            if (!Character.isLetterOrDigit(left)) {
                i++;
                continue;
            }

            if (!Character.isLetterOrDigit(right)) {
                j--;
                continue;
            }

            if (Character.toLowerCase(left) != Character.toLowerCase(right)) {
                return false;
            }

            i++;
            j--;
        }

        return true;
    }
}



class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic example
        String input1 = "A man, a plan, a canal: Panama";
        boolean expected1 = true;
        boolean result1 = solution.isPalindrome(input1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with an empty string
        String input2 = "";
        boolean expected2 = true;
        boolean result2 = solution.isPalindrome(input2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with a non-palindrome string
        String input3 = "hello";
        boolean expected3 = false;
        boolean result3 = solution.isPalindrome(input3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");

        // Test Case 4: Edge case with a single character
        String input4 = "a";
        boolean expected4 = true;
        boolean result4 = solution.isPalindrome(input4);
        System.out.println(result4 == expected4 ? "Test Case 4 Passed" : "Test Case 4 Failed");
    }
}

