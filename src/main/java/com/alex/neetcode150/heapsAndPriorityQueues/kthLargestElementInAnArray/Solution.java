package com.alex.neetcode150.heapsAndPriorityQueues.kthLargestElementInAnArray;

import java.util.PriorityQueue;

class Solution {
    public int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        for (int num : nums) {
            minHeap.offer(num);

            if (minHeap.size() > k)
                minHeap.poll();
        }

        return minHeap.poll();
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 10;

        // Test case 1: LeetCode Example 1
        System.out.println("Test 1: LeetCode Example 1");
        int[] nums1 = {3,2,1,5,6,4};
        int k1 = 2;
        int expected1 = 5; // 2nd largest in [6,5,4,3,2,1] is 5
        int result1 = solution.findKthLargest(nums1, k1);

        if (result1 == expected1) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected1 + ", Got: " + result1);
        }
        System.out.println();

        // Test case 2: LeetCode Example 2
        System.out.println("Test 2: LeetCode Example 2");
        int[] nums2 = {3,2,3,1,2,4,5,5,6};
        int k2 = 4;
        int expected2 = 4; // 4th largest in [6,5,5,4,3,3,2,2,1] is 4
        int result2 = solution.findKthLargest(nums2, k2);

        if (result2 == expected2) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2 + ", Got: " + result2);
        }
        System.out.println();

        // Test case 3: k = 1 (largest element)
        System.out.println("Test 3: Find largest element (k=1)");
        int[] nums3 = {7,1,3,9,2};
        int k3 = 1;
        int expected3 = 9;
        int result3 = solution.findKthLargest(nums3, k3);

        if (result3 == expected3) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3 + ", Got: " + result3);
        }
        System.out.println();

        // Test case 4: k = array length (smallest element)
        System.out.println("Test 4: Find smallest element (k=array.length)");
        int[] nums4 = {4,2,8,1,6};
        int k4 = 5;
        int expected4 = 1; // 5th largest = smallest
        int result4 = solution.findKthLargest(nums4, k4);

        if (result4 == expected4) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4 + ", Got: " + result4);
        }
        System.out.println();

        // Test case 5: Array with duplicates
        System.out.println("Test 5: Array with duplicates");
        int[] nums5 = {1,1,1,1,1};
        int k5 = 3;
        int expected5 = 1; // All elements are the same
        int result5 = solution.findKthLargest(nums5, k5);

        if (result5 == expected5) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5 + ", Got: " + result5);
        }
        System.out.println();

        // Test case 6: Single element
        System.out.println("Test 6: Single element array");
        int[] nums6 = {42};
        int k6 = 1;
        int expected6 = 42;
        int result6 = solution.findKthLargest(nums6, k6);

        if (result6 == expected6) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6 + ", Got: " + result6);
        }
        System.out.println();

        // Test case 7: Already sorted (ascending)
        System.out.println("Test 7: Already sorted ascending");
        int[] nums7 = {1,2,3,4,5,6};
        int k7 = 3;
        int expected7 = 4; // 3rd largest in [6,5,4,3,2,1] is 4
        int result7 = solution.findKthLargest(nums7, k7);

        if (result7 == expected7) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7 + ", Got: " + result7);
        }
        System.out.println();

        // Test case 8: Already sorted (descending)
        System.out.println("Test 8: Already sorted descending");
        int[] nums8 = {6,5,4,3,2,1};
        int k8 = 3;
        int expected8 = 4; // 3rd largest is 4
        int result8 = solution.findKthLargest(nums8, k8);

        if (result8 == expected8) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected8 + ", Got: " + result8);
        }
        System.out.println();

        // Test case 9: Negative numbers
        System.out.println("Test 9: Array with negative numbers");
        int[] nums9 = {-1,-3,2,0,-8,4};
        int k9 = 2;
        int expected9 = 2; // Sorted: [4,2,0,-1,-3,-8], 2nd largest is 2
        int result9 = solution.findKthLargest(nums9, k9);

        if (result9 == expected9) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected9 + ", Got: " + result9);
        }
        System.out.println();

        // Test case 10: Large numbers with duplicates in middle
        System.out.println("Test 10: Mixed duplicates");
        int[] nums10 = {2,1,3,3,3,1};
        int k10 = 4;
        int expected10 = 2; // Sorted: [3,3,3,2,1,1], 4th largest is 2
        int result10 = solution.findKthLargest(nums10, k10);

        if (result10 == expected10) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected10 + ", Got: " + result10);
        }
        System.out.println();

        // Summary
        System.out.println("=== TEST SUMMARY ===");
        System.out.println("Tests passed: " + testsPassed + "/" + totalTests);
        if (testsPassed == totalTests) {
            System.out.println("🎉 All tests passed!");
        } else {
            System.out.println("❌ Some tests failed. Check your implementation.");
        }
    }
}