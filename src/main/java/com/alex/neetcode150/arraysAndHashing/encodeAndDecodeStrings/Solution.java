package com.alex.neetcode150.arraysAndHashing.encodeAndDecodeStrings;

import java.util.*;

public class Solution {
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();

        for (String str: strs) {
            sb.append(str.length()).append('#').append(str);
        }

        return sb.toString();
    }

    /*
     * @param str: A string
     * @return: decodes a single string to a list of strings
     */
    public List<String> decode(String str) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            // determine what the length of each original String was
            int strLen = 0;
            if (str.charAt(i) == '#') {
                for (int j = i - 1, place = 1; j >= 0 && Character.isDigit(str.charAt(j)); j--, place *= 10) {
                    strLen += Character.getNumericValue(str.charAt(j)) * place;
                }

                // add each string to result
                result.add(str.substring(i + 1, i + 1 + strLen));
            }
        }

        return result;
    }
}



class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic example
        List<String> input1 = Arrays.asList("abc", "def", "ghi");
        String encoded1 = solution.encode(input1);
        List<String> decoded1 = solution.decode(encoded1);
        System.out.println(decoded1.equals(input1) ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with empty list
        List<String> input2 = new ArrayList<>();
        String encoded2 = solution.encode(input2);
        List<String> decoded2 = solution.decode(encoded2);
        System.out.println(decoded2.equals(input2) ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with a single string
        List<String> input3 = Collections.singletonList("hello");
        String encoded3 = solution.encode(input3);
        List<String> decoded3 = solution.decode(encoded3);
        System.out.println(decoded3.equals(input3) ? "Test Case 3 Passed" : "Test Case 3 Failed");
    }
}








