package com.alex.neetcode150.arraysAndHashing.productOfArrayExceptSelf;

import java.util.Arrays;


class Solution1 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] leftProducts = new int[n];
        leftProducts[0] = 1;

        int[] rightProducts = new int[n];
        rightProducts[n - 1] = 1;

        for (int i = 1; i < n; i++) {
            leftProducts[i] = leftProducts[i - 1] * nums[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            rightProducts[i] = rightProducts[i + 1] * nums[i + 1];
        }

        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            result[i] = leftProducts[i] * rightProducts[i];
        }

        return result;
    }
}


class Solution2 {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;

        int[] result = new int[n];
        result[0] = 1;

        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int lastRightProduct = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            result[i] *= lastRightProduct;
            lastRightProduct *= nums[i];
        }

        return result;
    }
}

class Test {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();

        // Test Case 1: Basic example
        int[] nums1 = {1, 2, 3, 4};
        int[] expected1 = {24, 12, 8, 6};
        int[] result1 = solution.productExceptSelf(nums1);
        System.out.println(Arrays.equals(result1, expected1) ? "Test Case 1 Passed" : "Test Case 1 Failed");

    }
}
