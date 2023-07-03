package com.alex.neetcode150.twoPointers.threeSum;


import java.util.*;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Set<List<Integer>> results = new HashSet<>();
        int num1, num2, num3, sum;

        for (int i = 0; i < nums.length - 2; i++) {
            num1 = nums[i];

            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                num2 = nums[left];
                num3 = nums[right];
                sum = num1 + num2 + num3;

                if (sum > 0) {
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    results.add(Arrays.asList(num1, num2, num3));
                    left++;
                    right--;
                }
            }
        }
        return new ArrayList<>(results);
    }
}



class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic example
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> expected1 = new ArrayList<>();
        expected1.add(Arrays.asList(-1, -1, 2));
        expected1.add(Arrays.asList(-1, 0, 1));
        List<List<Integer>> result1 = solution.threeSum(nums1);
        System.out.println(result1.equals(expected1) ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with empty array
        int[] nums2 = {};
        List<List<Integer>> expected2 = new ArrayList<>();
        List<List<Integer>> result2 = solution.threeSum(nums2);
        System.out.println(result2.equals(expected2) ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with no solution
        int[] nums3 = {1, 2, 3, 4, 5};
        List<List<Integer>> expected3 = new ArrayList<>();
        List<List<Integer>> result3 = solution.threeSum(nums3);
        System.out.println(result3.equals(expected3) ? "Test Case 3 Passed" : "Test Case 3 Failed");

        // Test Case 4: Edge case with duplicate elements
        int[] nums4 = {0, 0, 0};
        List<List<Integer>> expected4 = new ArrayList<>();
        expected4.add(Arrays.asList(0, 0, 0));
        List<List<Integer>> result4 = solution.threeSum(nums4);
        System.out.println(result4.equals(expected4) ? "Test Case 4 Passed" : "Test Case 4 Failed");
    }
}



