package com.alex.neetcode150.backtracking.combinationSum;

import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        // we want to try all combinations, e.g. without each number, or with each number, or some numbers multiple times
        // we can imagine a tree representing all possibilities. We can perform a DFS and sum along the paths as we go
        // the leaf nodes will represent either where a valid combination was found, or else the sum overshot the target, so invalid

        // Create a List of Lists to store the results, we'll add to it as we find valid combinations
        List<List<Integer>> result = new ArrayList<>();

        // Create a List of Integers to represent the current combination we're trying (adding to it as we traverse down the tree)
        List<Integer> currCombo = new ArrayList<>();

        // We will use recursion to traverse the tree, so we'll create a separate method
        backtrack(result, currCombo, nums, target, 0);

        return result;
    }


    public void backtrack(List<List<Integer>> result, List<Integer> currCombo, int[] nums, int targetRemaining, int startIndex) { // Try each number from startIndex onwards (to avoid duplicates like [2,3] and [3,2])
        // Base case to stop recursing
        // If we have found a valid combination, or overshot the target, we need to stop recursing
        if (targetRemaining == 0) {
            result.add(new ArrayList<>(currCombo));
            return;
        }

        if (targetRemaining < 0)
            return;


        for (int i = startIndex; i < nums.length; i++) {

            // Make choice
            currCombo.add(nums[i]);

            // Recurse to continue down current path
            backtrack(result, currCombo, nums, targetRemaining - nums[i], i);

            // Undo choice
            currCombo.remove(currCombo.size() - 1);

        }

    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 10;

        // Test case 1: LeetCode Example 1
        System.out.println("Test 1: LeetCode Example 1 - candidates=[2,3,6,7], target=7");
        int[] nums1 = {2,3,6,7};
        int target1 = 7;
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(2,2,3),
                Arrays.asList(7)
        );
        List<List<Integer>> result1 = solution.combinationSum(nums1, target1);

        if (combinationsEqual(result1, expected1)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected1);
            System.out.println("Got: " + result1);
        }
        System.out.println();

        // Test case 2: LeetCode Example 2
        System.out.println("Test 2: LeetCode Example 2 - candidates=[2,3,5], target=8");
        int[] nums2 = {2,3,5};
        int target2 = 8;
        List<List<Integer>> expected2 = Arrays.asList(
                Arrays.asList(2,2,2,2),
                Arrays.asList(2,3,3),
                Arrays.asList(3,5)
        );
        List<List<Integer>> result2 = solution.combinationSum(nums2, target2);

        if (combinationsEqual(result2, expected2)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2);
            System.out.println("Got: " + result2);
        }
        System.out.println();

        // Test case 3: LeetCode Example 3 - No solution
        System.out.println("Test 3: LeetCode Example 3 - candidates=[2], target=1");
        int[] nums3 = {2};
        int target3 = 1;
        List<List<Integer>> expected3 = Arrays.asList(); // Empty list
        List<List<Integer>> result3 = solution.combinationSum(nums3, target3);

        if (combinationsEqual(result3, expected3)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3);
            System.out.println("Got: " + result3);
        }
        System.out.println();

        // Test case 4: Single element equals target
        System.out.println("Test 4: Single element equals target");
        int[] nums4 = {1,2,3};
        int target4 = 3;
        List<List<Integer>> expected4 = Arrays.asList(
                Arrays.asList(1,1,1),
                Arrays.asList(1,2),
                Arrays.asList(3)
        );
        List<List<Integer>> result4 = solution.combinationSum(nums4, target4);

        if (combinationsEqual(result4, expected4)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4);
            System.out.println("Got: " + result4);
        }
        System.out.println();

        // Test case 5: Target is 1
        System.out.println("Test 5: Small target=1");
        int[] nums5 = {1,2};
        int target5 = 1;
        List<List<Integer>> expected5 = Arrays.asList(
                Arrays.asList(1)
        );
        List<List<Integer>> result5 = solution.combinationSum(nums5, target5);

        if (combinationsEqual(result5, expected5)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5);
            System.out.println("Got: " + result5);
        }
        System.out.println();

        // Test case 6: Large target with small numbers
        System.out.println("Test 6: Larger target with small candidates");
        int[] nums6 = {1,2};
        int target6 = 4;
        List<List<Integer>> expected6 = Arrays.asList(
                Arrays.asList(1,1,1,1),
                Arrays.asList(1,1,2),
                Arrays.asList(2,2)
        );
        List<List<Integer>> result6 = solution.combinationSum(nums6, target6);

        if (combinationsEqual(result6, expected6)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6);
            System.out.println("Got: " + result6);
        }
        System.out.println();

        // Test case 7: All candidates larger than target
        System.out.println("Test 7: All candidates larger than target");
        int[] nums7 = {5,6,7};
        int target7 = 4;
        List<List<Integer>> expected7 = Arrays.asList(); // No solution
        List<List<Integer>> result7 = solution.combinationSum(nums7, target7);

        if (combinationsEqual(result7, expected7)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7);
            System.out.println("Got: " + result7);
        }
        System.out.println();

        // Test case 8: Duplicates in candidates (should still work)
        System.out.println("Test 8: Candidates with larger numbers");
        int[] nums8 = {4,5};
        int target8 = 9;
        List<List<Integer>> expected8 = Arrays.asList(
                Arrays.asList(4,5)
        );
        List<List<Integer>> result8 = solution.combinationSum(nums8, target8);

        if (combinationsEqual(result8, expected8)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected8);
            System.out.println("Got: " + result8);
        }
        System.out.println();

        // Test case 9: Multiple ways with same number repeated
        System.out.println("Test 9: Multiple repetitions");
        int[] nums9 = {3,4};
        int target9 = 6;
        List<List<Integer>> expected9 = Arrays.asList(
                Arrays.asList(3,3)
        );
        List<List<Integer>> result9 = solution.combinationSum(nums9, target9);

        if (combinationsEqual(result9, expected9)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected9);
            System.out.println("Got: " + result9);
        }
        System.out.println();

        // Test case 10: Complex case with multiple solutions
        System.out.println("Test 10: Complex case with multiple paths");
        int[] nums10 = {2,4,6,8};
        int target10 = 8;
        List<List<Integer>> expected10 = Arrays.asList(
                Arrays.asList(2,2,2,2),
                Arrays.asList(2,2,4),
                Arrays.asList(2,6),
                Arrays.asList(4,4),
                Arrays.asList(8)
        );
        List<List<Integer>> result10 = solution.combinationSum(nums10, target10);

        if (combinationsEqual(result10, expected10)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected10);
            System.out.println("Got: " + result10);
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

    // Helper method to check if two lists of combinations are equal (order doesn't matter)
    public static boolean combinationsEqual(List<List<Integer>> a, List<List<Integer>> b) {
        if (a.size() != b.size()) return false;

        Set<List<Integer>> setA = new HashSet<>();
        Set<List<Integer>> setB = new HashSet<>();

        for (List<Integer> combination : a) {
            List<Integer> sorted = new ArrayList<>(combination);
            Collections.sort(sorted);
            setA.add(sorted);
        }
        for (List<Integer> combination : b) {
            List<Integer> sorted = new ArrayList<>(combination);
            Collections.sort(sorted);
            setB.add(sorted);
        }

        return setA.equals(setB);
    }
}