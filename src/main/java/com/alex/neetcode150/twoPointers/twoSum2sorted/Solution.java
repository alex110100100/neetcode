package com.alex.neetcode150.twoPointers.twoSum2sorted;


import java.util.Arrays;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum < target)
                left++;
            else if (sum > target)
                right--;
            else
                return new int[] {left + 1, right + 1};     // LC insists on 1-indexing in this Q for some weird reason
        }
        return null;
    }
}



class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();

        // Test Case 1: Basic example
        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        int[] expected1 = {1, 2};
        int[] result1 = solution.twoSum(nums1, target1);
        System.out.println(Arrays.equals(result1, expected1) ? "Test Case 1 Passed" : "Test Case 1 Failed");

        // Test Case 2: Edge case with empty array
        int[] nums2 = {};
        int target2 = 9;
        int[] expected2 = null;
        int[] result2 = solution.twoSum(nums2, target2);
        System.out.println(result2 == expected2 ? "Test Case 2 Passed" : "Test Case 2 Failed");

        // Test Case 3: Edge case with no solution
        int[] nums3 = {1, 2, 3, 4, 5};
        int target3 = 10;
        int[] expected3 = null;
        int[] result3 = solution.twoSum(nums3, target3);
        System.out.println(result3 == expected3 ? "Test Case 3 Passed" : "Test Case 3 Failed");

        // Test Case 4: Edge case with duplicate elements
        int[] nums4 = {3, 3, 4, 5};
        int target4 = 6;
        int[] expected4 = {1, 2};
        int[] result4 = solution.twoSum(nums4, target4);
        System.out.println(Arrays.equals(result4, expected4) ? "Test Case 4 Passed" : "Test Case 4 Failed");
    }
}


