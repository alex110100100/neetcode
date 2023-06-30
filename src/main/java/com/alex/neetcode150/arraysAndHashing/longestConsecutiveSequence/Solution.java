package com.alex.neetcode150.arraysAndHashing.longestConsecutiveSequence;

import java.util.*;

class Solution {
    public int longestConsecutive(int[] nums) {
        int longestSequence = 0;
        Set<Integer> set = new HashSet<>();

        for (int num: nums) {
            set.add(num);
        }

        for (int num : nums) {
            if (!set.contains(num - 1)) {  // check if it's the first in a sequence, by seeing if num - 1 exists
                int seqLength = 0;

                while (set.contains(num)) {
                    num++;
                    seqLength++;
                }

                longestSequence = Math.max(seqLength, longestSequence);
            }
        }

        return longestSequence;
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic example
        int[] nums1 = {100, 4, 200, 1, 3, 2, 5, 6, 7};
        int expected1 = 7;
        int result1 = solution.longestConsecutive(nums1);
        System.out.println(result1 == expected1 ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with an empty input array
        int[] nums2 = {};
        int expected2 = 0;
        int result2 = solution.longestConsecutive(nums2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with a single element in the input array
        int[] nums3 = {5};
        int expected3 = 1;
        int result3 = solution.longestConsecutive(nums3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");
    }
}







