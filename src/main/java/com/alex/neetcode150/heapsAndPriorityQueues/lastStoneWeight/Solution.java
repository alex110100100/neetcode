package com.alex.neetcode150.heapsAndPriorityQueues.lastStoneWeight;


import java.util.Collections;
import java.util.PriorityQueue;

class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());

        for (int stone : stones)
            maxHeap.offer(stone);

        while (maxHeap.size() > 1) {
            int stone1 = maxHeap.poll();
            int stone2 = maxHeap.poll();

            if (stone1 != stone2)
                maxHeap.offer(Math.abs(stone1 - stone2));
        }

        return maxHeap.isEmpty() ? 0 : maxHeap.poll();
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 10;

        // Test case 1: LeetCode Example 1
        System.out.println("Test 1: LeetCode Example 1");
        int[] stones1 = {2,7,4,1,8,1};
        int expected1 = 1;
        int result1 = solution.lastStoneWeight(stones1);

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
        int[] stones2 = {1};
        int expected2 = 1;
        int result2 = solution.lastStoneWeight(stones2);

        if (result2 == expected2) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2 + ", Got: " + result2);
        }
        System.out.println();

        // Test case 3: All stones destroyed (result is 0)
        System.out.println("Test 3: All stones destroyed");
        int[] stones3 = {2,2};
        int expected3 = 0;
        int result3 = solution.lastStoneWeight(stones3);

        if (result3 == expected3) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3 + ", Got: " + result3);
        }
        System.out.println();

        // Test case 4: Multiple equal weights
        System.out.println("Test 4: Multiple stones with equal weights");
        int[] stones4 = {3,3,3,3};
        int expected4 = 0; // All pairs destroy each other
        int result4 = solution.lastStoneWeight(stones4);

        if (result4 == expected4) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4 + ", Got: " + result4);
        }
        System.out.println();

        // Test case 5: Already sorted stones
        System.out.println("Test 5: Already sorted stones");
        int[] stones5 = {1,2,3,4,5};
        int expected5 = 1; // 5-4=1, 3-2=1, 1-1=0, result: 1
        int result5 = solution.lastStoneWeight(stones5);

        if (result5 == expected5) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5 + ", Got: " + result5);
        }
        System.out.println();

        // Test case 6: Reverse sorted stones
        System.out.println("Test 6: Reverse sorted stones");
        int[] stones6 = {5,4,3,2,1};
        int expected6 = 1; // Same result as test 5, order doesn't matter for final result
        int result6 = solution.lastStoneWeight(stones6);

        if (result6 == expected6) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6 + ", Got: " + result6);
        }
        System.out.println();

        // Test case 7: Large difference
        System.out.println("Test 7: One very heavy stone");
        int[] stones7 = {10,1,1,1,1};
        int expected7 = 6; // 10 destroys all others: 10-1=9, 9-1=8, 8-1=7, 7-1=6
        int result7 = solution.lastStoneWeight(stones7);

        if (result7 == expected7) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7 + ", Got: " + result7);
        }
        System.out.println();

        // Test case 8: Three stones
        System.out.println("Test 8: Three stones with different outcomes");
        int[] stones8 = {3,1,2};
        int expected8 = 0; // 3-2=1, 1-1=0
        int result8 = solution.lastStoneWeight(stones8);

        if (result8 == expected8) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected8 + ", Got: " + result8);
        }
        System.out.println();

        // Test case 9: Odd number of equal stones
        System.out.println("Test 9: Odd number of equal stones");
        int[] stones9 = {5,5,5};
        int expected9 = 5; // 5-5=0, then 5 remains
        int result9 = solution.lastStoneWeight(stones9);

        if (result9 == expected9) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected9 + ", Got: " + result9);
        }
        System.out.println();

        // Test case 10: Complex sequence
        System.out.println("Test 10: Complex sequence");
        int[] stones10 = {9,3,2,10};
        int expected10 = 8; // 10-9=1, 3-2=1, 1-1=0... wait, let me recalculate
        // Step by step: [9,3,2,10] -> [1,3,2] (10-9=1) -> [1,1] (3-2=1) -> [0] (1-1=0)
        // Actually: [9,3,2,10] -> max heap gives us 10,9 first -> 10-9=1 -> [3,2,1]
        // -> 3,2 -> 3-2=1 -> [1,1] -> 1-1=0 -> result: 0
        int expected10_corrected = 0;
        int result10 = solution.lastStoneWeight(stones10);

        if (result10 == expected10_corrected) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected10_corrected + ", Got: " + result10);
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