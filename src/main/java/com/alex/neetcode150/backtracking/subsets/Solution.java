package com.alex.neetcode150.backtracking.subsets;

import java.util.*;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentSubset = new ArrayList<>();
        backtrack(result, currentSubset, nums, 0);
        return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> currentSubset, int[] nums, int index) {
        result.add(new ArrayList<>(currentSubset));
        for (int i = index; i < nums.length; i++) {
            // Make a choice (add number to subset)
            currentSubset.add(nums[i]);

            // Recurse with current choice
            backtrack(result, currentSubset, nums, i + 1);

            // Backtrack: undo the current choice
            currentSubset.remove(currentSubset.size() - 1);
        }
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 8;

        // Test case 1: LeetCode Example 1
        System.out.println("Test 1: LeetCode Example 1 - [1,2,3]");
        int[] nums1 = {1,2,3};
        List<List<Integer>> expected1 = Arrays.asList(
                Arrays.asList(),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(1,2),
                Arrays.asList(3),
                Arrays.asList(1,3),
                Arrays.asList(2,3),
                Arrays.asList(1,2,3)
        );
        List<List<Integer>> result1 = solution.subsets(nums1);

        if (subsetsEqual(result1, expected1)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected size: " + expected1.size() + ", Got size: " + result1.size());
            System.out.println("Expected: " + expected1);
            System.out.println("Got: " + result1);
        }
        System.out.println();

        // Test case 2: LeetCode Example 2
        System.out.println("Test 2: LeetCode Example 2 - [0]");
        int[] nums2 = {0};
        List<List<Integer>> expected2 = Arrays.asList(
                Arrays.asList(),
                Arrays.asList(0)
        );
        List<List<Integer>> result2 = solution.subsets(nums2);

        if (subsetsEqual(result2, expected2)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2);
            System.out.println("Got: " + result2);
        }
        System.out.println();

        // Test case 3: Empty array
        System.out.println("Test 3: Empty array");
        int[] nums3 = {};
        List<List<Integer>> expected3 = Arrays.asList(
                Arrays.asList()
        );
        List<List<Integer>> result3 = solution.subsets(nums3);

        if (subsetsEqual(result3, expected3)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3);
            System.out.println("Got: " + result3);
        }
        System.out.println();

        // Test case 4: Two elements
        System.out.println("Test 4: Two elements [1,2]");
        int[] nums4 = {1,2};
        List<List<Integer>> expected4 = Arrays.asList(
                Arrays.asList(),
                Arrays.asList(1),
                Arrays.asList(2),
                Arrays.asList(1,2)
        );
        List<List<Integer>> result4 = solution.subsets(nums4);

        if (subsetsEqual(result4, expected4)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4);
            System.out.println("Got: " + result4);
        }
        System.out.println();

        // Test case 5: Negative numbers
        System.out.println("Test 5: With negative numbers [-1,0,1]");
        int[] nums5 = {-1,0,1};
        List<List<Integer>> expected5 = Arrays.asList(
                Arrays.asList(),
                Arrays.asList(-1),
                Arrays.asList(0),
                Arrays.asList(-1,0),
                Arrays.asList(1),
                Arrays.asList(-1,1),
                Arrays.asList(0,1),
                Arrays.asList(-1,0,1)
        );
        List<List<Integer>> result5 = solution.subsets(nums5);

        if (subsetsEqual(result5, expected5)) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected size: " + expected5.size() + ", Got size: " + result5.size());
            System.out.println("Expected: " + expected5);
            System.out.println("Got: " + result5);
        }
        System.out.println();

        // Test case 6: Four elements (larger set)
        System.out.println("Test 6: Four elements [1,2,3,4]");
        int[] nums6 = {1,2,3,4};
        // Should have 2^4 = 16 subsets
        List<List<Integer>> result6 = solution.subsets(nums6);

        if (result6.size() == 16 && hasEmptySet(result6) && hasFullSet(result6, nums6)) {
            System.out.println("✓ PASSED (size and key subsets check)");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected size: 16, Got size: " + result6.size());
            System.out.println("Has empty set: " + hasEmptySet(result6));
            System.out.println("Has full set: " + hasFullSet(result6, nums6));
        }
        System.out.println();

        // Test case 7: Larger numbers
        System.out.println("Test 7: Larger numbers [10,20,30]");
        int[] nums7 = {10,20,30};
        List<List<Integer>> result7 = solution.subsets(nums7);

        if (result7.size() == 8 && hasEmptySet(result7) && hasFullSet(result7, nums7)) {
            System.out.println("✓ PASSED (size and key subsets check)");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected size: 8, Got size: " + result7.size());
            System.out.println("Has empty set: " + hasEmptySet(result7));
            System.out.println("Has full set: " + hasFullSet(result7, nums7));
        }
        System.out.println();

        // Test case 8: Check specific subsets exist
        System.out.println("Test 8: Verify specific subsets for [1,2,3]");
        int[] nums8 = {1,2,3};
        List<List<Integer>> result8 = solution.subsets(nums8);

        boolean hasEmpty = containsSubset(result8, Arrays.asList());
        boolean hasSingle1 = containsSubset(result8, Arrays.asList(1));
        boolean hasPair12 = containsSubset(result8, Arrays.asList(1,2));
        boolean hasFull = containsSubset(result8, Arrays.asList(1,2,3));

        if (result8.size() == 8 && hasEmpty && hasSingle1 && hasPair12 && hasFull) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Size: " + result8.size() + "/8, Empty: " + hasEmpty +
                    ", [1]: " + hasSingle1 + ", [1,2]: " + hasPair12 + ", [1,2,3]: " + hasFull);
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

    // Helper method to check if two lists of subsets are equal (order doesn't matter)
    public static boolean subsetsEqual(List<List<Integer>> a, List<List<Integer>> b) {
        if (a.size() != b.size()) return false;

        Set<Set<Integer>> setA = new HashSet<>();
        Set<Set<Integer>> setB = new HashSet<>();

        for (List<Integer> subset : a) {
            setA.add(new HashSet<>(subset));
        }
        for (List<Integer> subset : b) {
            setB.add(new HashSet<>(subset));
        }

        return setA.equals(setB);
    }

    // Helper method to check if result contains empty set
    public static boolean hasEmptySet(List<List<Integer>> subsets) {
        return subsets.stream().anyMatch(List::isEmpty);
    }

    // Helper method to check if result contains the full set
    public static boolean hasFullSet(List<List<Integer>> subsets, int[] nums) {
        Set<Integer> fullSet = new HashSet<>();
        for (int num : nums) {
            fullSet.add(num);
        }

        return subsets.stream().anyMatch(subset -> new HashSet<>(subset).equals(fullSet));
    }

    // Helper method to check if a specific subset exists in the result
    public static boolean containsSubset(List<List<Integer>> subsets, List<Integer> target) {
        Set<Integer> targetSet = new HashSet<>(target);
        return subsets.stream().anyMatch(subset -> new HashSet<>(subset).equals(targetSet));
    }
}