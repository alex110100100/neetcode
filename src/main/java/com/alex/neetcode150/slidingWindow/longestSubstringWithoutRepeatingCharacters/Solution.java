package com.alex.neetcode150.slidingWindow.longestSubstringWithoutRepeatingCharacters;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution1 {
    public int lengthOfLongestSubstring(String s) {
        int subStart = 0, subEnd = 0, maxSubLength = 0;
        Set<Character> subChars = new HashSet<>();

        while (subEnd < s.length()) {
            char c = s.charAt(subEnd);

            if (!subChars.contains(c)) {
                subChars.add(c);
                maxSubLength = Math.max(maxSubLength, subChars.size());
                subEnd++;
            } else {
                subChars.remove(s.charAt(subStart));
                subStart++;
            }
        }

        return maxSubLength;
    }
}


class Solution2 {
    public int lengthOfLongestSubstring(String s) {
        int subEnd = 0, subStart = 0, max = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (subEnd < s.length()){
            char c = s.charAt(subEnd);

            if (map.containsKey(c)){
                subStart = Math.max(subStart, map.get(c) + 1);        // the Math.max is needed because we could have consecutive chars repeated. e.g. abba
            }

            map.put(c, subEnd);
            max = Math.max(max, subEnd - subStart + 1);
            subEnd++;
        }

        return max;
    }
}


class Test {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // Test Case 1: Basic example
        String s1 = "abcabcbb";
        int expected1 = 3;
        int result1 = solution.lengthOfLongestSubstring(s1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with empty string
        String s2 = "";
        int expected2 = 0;
        int result2 = solution.lengthOfLongestSubstring(s2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with single character
        String s3 = "a";
        int expected3 = 1;
        int result3 = solution.lengthOfLongestSubstring(s3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");

        // Test Case 4: Edge case with repeating characters
        String s4 = "bbbbbb";
        int expected4 = 1;
        int result4 = solution.lengthOfLongestSubstring(s4);
        System.out.println(result4 == expected4 ? "Test Case 4 Passed" : "Test Case 4 Failed");
    }
}
