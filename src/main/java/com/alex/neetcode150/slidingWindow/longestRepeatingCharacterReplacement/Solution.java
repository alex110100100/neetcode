package com.alex.neetcode150.slidingWindow.longestRepeatingCharacterReplacement;

class Solution {
    public int characterReplacement(String s, int k) {
        int[] charCount = new int[26];
        int maxCount = 0; // The count of the most frequent character in the current window
        int maxLength = 0; // The length of the longest valid substring

        int start = 0, end = 0;

        while (end < s.length()) {
            char c = s.charAt(end);
            charCount[c - 'A']++;
            maxCount = Math.max(maxCount, charCount[c - 'A']);

            // Check if we need to shrink the window
            if (end - start + 1 - maxCount > k) {
                charCount[s.charAt(start) - 'A']--; // Shrink the window
                start++;
            }

            maxLength = Math.max(maxLength, end - start + 1);
            end++;
        }

        return maxLength;
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic example
        String s1 = "ABAB";
        int k1 = 2;
        int expected1 = 4;
        int result1 = solution.characterReplacement(s1, k1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with empty string
        String s2 = "";
        int k2 = 1;
        int expected2 = 0;
        int result2 = solution.characterReplacement(s2, k2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with single character
        String s3 = "A";
        int k3 = 0;
        int expected3 = 1;
        int result3 = solution.characterReplacement(s3, k3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");

        // Test Case 4: Random case
        String s4 = "AABABBA";
        int k4 = 1;
        int expected4 = 4;
        int result4 = solution.characterReplacement(s4, k4);
        System.out.println(result4 == expected4 ? "Test Case 4 Passed" : "Test Case 4 Failed");
    }
}