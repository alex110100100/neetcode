package com.alex.neetcode150.heapsAndPriorityQueues.kthLargestElementInAStream;


import java.util.PriorityQueue;

class KthLargest {
    PriorityQueue<Integer> minHeap;
    int maxSize;

    public KthLargest(int k, int[] nums) {
        minHeap = new PriorityQueue<>();

        maxSize = k;

        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        minHeap.offer(val);

        if (minHeap.size() > maxSize)
            minHeap.remove();

        return minHeap.peek();
    }
}

class Test {
    public static void main(String[] args) {
        int testsPassed = 0;
        int totalTests = 7;

        // Test case 1: LeetCode Example
        System.out.println("Test 1: LeetCode Example - k=3, nums=[4,5,8,2]");
        KthLargest kthLargest1 = new KthLargest(3, new int[]{4, 5, 8, 2});

        int[] addValues1 = {3, 5, 10, 9, 4};
        int[] expected1 = {4, 5, 5, 8, 8};
        boolean test1Passed = true;

        for (int i = 0; i < addValues1.length; i++) {
            int result = kthLargest1.add(addValues1[i]);
            if (result != expected1[i]) {
                System.out.println("✗ FAILED at add(" + addValues1[i] + ")");
                System.out.println("Expected: " + expected1[i] + ", Got: " + result);
                test1Passed = false;
                break;
            }
        }

        if (test1Passed) {
            System.out.println("✓ PASSED");
            testsPassed++;
        }
        System.out.println();

        // Test case 2: k=1 (largest element)
        System.out.println("Test 2: k=1 (always return largest)");
        KthLargest kthLargest2 = new KthLargest(1, new int[]{1});

        int[] addValues2 = {2, 3, 1, 4};
        int[] expected2 = {2, 3, 3, 4};
        boolean test2Passed = true;

        for (int i = 0; i < addValues2.length; i++) {
            int result = kthLargest2.add(addValues2[i]);
            if (result != expected2[i]) {
                System.out.println("✗ FAILED at add(" + addValues2[i] + ")");
                System.out.println("Expected: " + expected2[i] + ", Got: " + result);
                test2Passed = false;
                break;
            }
        }

        if (test2Passed) {
            System.out.println("✓ PASSED");
            testsPassed++;
        }
        System.out.println();

        // Test case 3: Empty initial array
        System.out.println("Test 3: Empty initial array");
        KthLargest kthLargest3 = new KthLargest(2, new int[]{});

        int[] addValues3 = {1, 2, 3, 4};
        int[] expected3 = {1, 1, 2, 3}; // For k=2: need at least 2 elements
        boolean test3Passed = true;

        for (int i = 0; i < addValues3.length; i++) {
            int result = kthLargest3.add(addValues3[i]);
            if (result != expected3[i]) {
                System.out.println("✗ FAILED at add(" + addValues3[i] + ")");
                System.out.println("Expected: " + expected3[i] + ", Got: " + result);
                test3Passed = false;
                break;
            }
        }

        if (test3Passed) {
            System.out.println("✓ PASSED");
            testsPassed++;
        }
        System.out.println();

        // Test case 4: k equals array length
        System.out.println("Test 4: k equals initial array length");
        KthLargest kthLargest4 = new KthLargest(3, new int[]{1, 2, 3});

        int[] addValues4 = {0, 4, 5};
        int[] expected4 = {1, 2, 3}; // 3rd largest: [1,2,3] -> [0,1,2,3] -> [0,1,2,3,4] -> [0,1,2,3,4,5]
        boolean test4Passed = true;

        for (int i = 0; i < addValues4.length; i++) {
            int result = kthLargest4.add(addValues4[i]);
            if (result != expected4[i]) {
                System.out.println("✗ FAILED at add(" + addValues4[i] + ")");
                System.out.println("Expected: " + expected4[i] + ", Got: " + result);
                test4Passed = false;
                break;
            }
        }

        if (test4Passed) {
            System.out.println("✓ PASSED");
            testsPassed++;
        }
        System.out.println();

        // Test case 5: Negative numbers
        System.out.println("Test 5: Negative numbers");
        KthLargest kthLargest5 = new KthLargest(2, new int[]{-1, -2});

        int[] addValues5 = {-3, 0, 1};
        int[] expected5 = {-2, -1, 0}; // 2nd largest: [-3,-2,-1] -> [-3,-2,-1,0] -> [-3,-2,-1,0,1]
        boolean test5Passed = true;

        for (int i = 0; i < addValues5.length; i++) {
            int result = kthLargest5.add(addValues5[i]);
            if (result != expected5[i]) {
                System.out.println("✗ FAILED at add(" + addValues5[i] + ")");
                System.out.println("Expected: " + expected5[i] + ", Got: " + result);
                test5Passed = false;
                break;
            }
        }

        if (test5Passed) {
            System.out.println("✓ PASSED");
            testsPassed++;
        }
        System.out.println();

        // Test case 6: Duplicate values
        System.out.println("Test 6: Duplicate values");
        KthLargest kthLargest6 = new KthLargest(3, new int[]{5, 5, 5});

        int[] addValues6 = {5, 6, 5};
        int[] expected6 = {5, 5, 5}; // 3rd largest with duplicates
        boolean test6Passed = true;

        for (int i = 0; i < addValues6.length; i++) {
            int result = kthLargest6.add(addValues6[i]);
            if (result != expected6[i]) {
                System.out.println("✗ FAILED at add(" + addValues6[i] + ")");
                System.out.println("Expected: " + expected6[i] + ", Got: " + result);
                test6Passed = false;
                break;
            }
        }

        if (test6Passed) {
            System.out.println("✓ PASSED");
            testsPassed++;
        }
        System.out.println();

        // Test case 7: Large k, small initial array
        System.out.println("Test 7: k larger than initial array");
        KthLargest kthLargest7 = new KthLargest(4, new int[]{1});

        int[] addValues7 = {2, 3, 4, 5};
        int[] expected7 = {1, 1, 1, 2}; // Need 4 elements to get 4th largest
        boolean test7Passed = true;

        for (int i = 0; i < addValues7.length; i++) {
            int result = kthLargest7.add(addValues7[i]);
            if (result != expected7[i]) {
                System.out.println("✗ FAILED at add(" + addValues7[i] + ")");
                System.out.println("Expected: " + expected7[i] + ", Got: " + result);
                test7Passed = false;
                break;
            }
        }

        if (test7Passed) {
            System.out.println("✓ PASSED");
            testsPassed++;
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


