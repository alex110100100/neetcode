package com.alex.neetcode150.slidingWindow.permutationInString;

class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() > s2.length()) return false;

        int[] s1CharCounts = new int[26];
        int[] windowCharCounts = new int[26];

        for (int i = 0; i < s1.length(); i++) {
            s1CharCounts[s1.charAt(i) - 'a']++;
            windowCharCounts[s2.charAt(i) - 'a']++;
        }

        for (int i = 0; i < (s2.length() - s1.length()); i++) {
            if (matches(s1CharCounts, windowCharCounts))
                return true;

            //move window along
            windowCharCounts[s2.charAt(i) - 'a']--;
            windowCharCounts[s2.charAt(i + s1.length()) - 'a']++;
        }

        if (matches(s1CharCounts, windowCharCounts))
            return true;

        return false;
    }


    private boolean matches(int[] array1, int[] array2) {
        for (int i = 0; i < array1.length; i++) {
            if (array1[i] != array2[i])
                return false;
        }
        return true;
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test case 1
        String s1_1 = "ab";
        String s2_1 = "lecaabee";
        boolean expected1 = true;
        boolean result1 = solution.checkInclusion(s1_1, s2_1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test case 2
        String s1_2 = "adc";
        String s2_2 = "dcda";
        boolean expected2 = true;
        boolean result2 = solution.checkInclusion(s1_2, s2_2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");
    }
}