package com.alex.neetcode150.backtracking.combinationSum2;

import java.util.*;

class Solution1 {
    /* Solution 1 - almost the same as our "Combination Sum" solution, but using a HashSet to prevent duplicates in the result,
                    and i + 1 as the backtracking arg instead of i
     */

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        Arrays.sort(nums);
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> currCombo = new ArrayList<>();

        backtrack(result, currCombo, nums, target, 0);
        return new ArrayList<>(result);
    }


    public void backtrack(Set<List<Integer>> result, List<Integer> currCombo, int[] nums, int targetRemaining, int startIndex) {
        if (targetRemaining == 0) {
            result.add(new ArrayList<>(currCombo));
            return;
        }

        if (targetRemaining < 0)
            return;


        for (int i = startIndex; i < nums.length; i++) {
            currCombo.add(nums[i]);
            backtrack(result, currCombo, nums, targetRemaining - nums[i], i + 1);   // i + 1, so that we aren't trying to reuse the same item multiple times to reach the target
            currCombo.remove(currCombo.size() - 1);
        }
    }
}


class Solution2 {
    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        /* Solution 2 - again very similar to our "Combination Sum" solution,
                    but this time we check at each level whether the current value has been tried previously at this same level (if so, skip it).
                    and i + 1 as the backtracking arg instead of i.
                    With this solution, we are skipping duplicates before trying to add them, so there's no need for a HashSet.
                    So this solution is more efficient (avoids generating duplicates entirely).
     */


        Arrays.sort(nums); // MUST be sorted so duplicates are adjacent for easy skipping
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currCombo = new ArrayList<>();

        backtrack(result, currCombo, nums, target, 0);
        return result;
    }


    public void backtrack(List<List<Integer>> result, List<Integer> currCombo, int[] nums, int targetRemaining, int startIndex) {
        if (targetRemaining == 0) {
            result.add(new ArrayList<>(currCombo));
            return;
        }

        if (targetRemaining < 0)
            return;

        for (int i = startIndex; i < nums.length; i++) {
            // KEY INSIGHT: Skip duplicates at the same recursion level
            // If i > startIndex and candidates[i] == candidates[i-1],
            // we've already explored this value at this level
            if (i > startIndex && nums[i] == nums[i -1])
                continue; // Skip this duplicate

            currCombo.add(nums[i]);
            backtrack(result, currCombo, nums, targetRemaining - nums[i], i + 1);   // NB: i + 1
            currCombo.remove(currCombo.size() - 1);

        }
    }
}



class Test {
    public static void main(String[] args) {
        Solution2 solution = new Solution2();
        int testsPassed = 0;
        int totalTests = 10;

        // Test case 1: LeetCode Example 1
        System.out.println("Test 1: LeetCode Example 1 - candidates=[10,1,2,7,6,1,5], target=8");
        int[] candidates1 = {10,1,2,7,6,1,5};
        int target1 = 8;
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(1,1,6),
                Arrays.asList(1,2,5),
                Arrays.asList(1,7),
                Arrays.asList(2,6)
        );
        List<List<Integer>> result1 = solution.combinationSum2(candidates1, target1);

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
        System.out.println("Test 2: LeetCode Example 2 - candidates=[2,5,2,1,2], target=5");
        int[] candidates2 = {2,5,2,1,2};
        int target2 = 5;
        List<List<Integer>> expected2 = Arrays.asList(
                Arrays.asList(1,2,2),
                Arrays.asList(5)
        );
        List<List<Integer>> result2 = solution.combinationSum2(candidates2, target2);

        if (combinationsEqual(result2, expected2)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2);
            System.out.println("Got: " + result2);
        }
        System.out.println();

        // Test case 3: No solution
        System.out.println("Test 3: No solution - candidates=[1,2], target=4");
        int[] candidates3 = {1,2};
        int target3 = 4;
        List<List<Integer>> expected3 = Arrays.asList(); // No valid combination
        List<List<Integer>> result3 = solution.combinationSum2(candidates3, target3);

        if (combinationsEqual(result3, expected3)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3);
            System.out.println("Got: " + result3);
        }
        System.out.println();

        // Test case 4: Single element
        System.out.println("Test 4: Single element matches target");
        int[] candidates4 = {1,1,1,1,1};
        int target4 = 3;
        List<List<Integer>> expected4 = Arrays.asList(
                Arrays.asList(1,1,1)
        );
        List<List<Integer>> result4 = solution.combinationSum2(candidates4, target4);

        if (combinationsEqual(result4, expected4)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4);
            System.out.println("Got: " + result4);
        }
        System.out.println();

        // Test case 5: All duplicates, multiple solutions
        System.out.println("Test 5: Mixed duplicates");
        int[] candidates5 = {2,2,2,3,3};
        int target5 = 7;
        List<List<Integer>> expected5 = Arrays.asList(
                Arrays.asList(2,2,3)
        );
        List<List<Integer>> result5 = solution.combinationSum2(candidates5, target5);

        if (combinationsEqual(result5, expected5)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5);
            System.out.println("Got: " + result5);
        }
        System.out.println();

        // Test case 6: Single number equals target
        System.out.println("Test 6: Single number equals target");
        int[] candidates6 = {1,2,3,4,5};
        int target6 = 5;
        List<List<Integer>> expected6 = Arrays.asList(
                Arrays.asList(1,4),
                Arrays.asList(2,3),
                Arrays.asList(5)
        );
        List<List<Integer>> result6 = solution.combinationSum2(candidates6, target6);

        if (combinationsEqual(result6, expected6)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6);
            System.out.println("Got: " + result6);
        }
        System.out.println();

        // Test case 7: Large candidates, small target
        System.out.println("Test 7: All candidates larger than target");
        int[] candidates7 = {5,6,7,8};
        int target7 = 4;
        List<List<Integer>> expected7 = Arrays.asList(); // No solution
        List<List<Integer>> result7 = solution.combinationSum2(candidates7, target7);

        if (combinationsEqual(result7, expected7)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7);
            System.out.println("Got: " + result7);
        }
        System.out.println();

        // Test case 8: Many duplicates with different combinations
        System.out.println("Test 8: Many duplicates, avoid duplicate combinations");
        int[] candidates8 = {1,1,1,2,2};
        int target8 = 4;
        List<List<Integer>> expected8 = Arrays.asList(
                Arrays.asList(1,1,2),
                Arrays.asList(2,2)
        );
        List<List<Integer>> result8 = solution.combinationSum2(candidates8, target8);

        if (combinationsEqual(result8, expected8)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected8);
            System.out.println("Got: " + result8);
        }
        System.out.println();

        // Test case 9: Target equals one element
        System.out.println("Test 9: Target equals smallest element");
        int[] candidates9 = {1,3,4,5};
        int target9 = 1;
        List<List<Integer>> expected9 = Arrays.asList(
                Arrays.asList(1)
        );
        List<List<Integer>> result9 = solution.combinationSum2(candidates9, target9);

        if (combinationsEqual(result9, expected9)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected9);
            System.out.println("Got: " + result9);
        }
        System.out.println();

        // Test case 10: Complex case with multiple valid paths
        System.out.println("Test 10: Complex case - multiple solutions");
        int[] candidates10 = {1,2,3,4,5,6};
        int target10 = 6;
        List<List<Integer>> expected10 = Arrays.asList(
                Arrays.asList(1,2,3),
                Arrays.asList(1,5),
                Arrays.asList(2,4),
                Arrays.asList(6)
        );
        List<List<Integer>> result10 = solution.combinationSum2(candidates10, target10);

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