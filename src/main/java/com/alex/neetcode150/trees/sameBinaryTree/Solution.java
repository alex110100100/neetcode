package com.alex.neetcode150.trees.sameBinaryTree;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (p == null || q == null || p.val != q.val)
            return false;

        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 10;

        // Test case 1: LeetCode Example 1 - Same trees
        System.out.println("Test 1: Same trees [1,2,3] and [1,2,3]");
        TreeNode p1 = new TreeNode(1);
        p1.left = new TreeNode(2);
        p1.right = new TreeNode(3);

        TreeNode q1 = new TreeNode(1);
        q1.left = new TreeNode(2);
        q1.right = new TreeNode(3);

        boolean expected1 = true;
        boolean result1 = solution.isSameTree(p1, q1);
        if (result1 == expected1) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected1 + ", Got: " + result1);
        }
        System.out.println();

        // Test case 2: LeetCode Example 2 - Different structure
        System.out.println("Test 2: Different structure [1,2] and [1,null,2]");
        TreeNode p2 = new TreeNode(1);
        p2.left = new TreeNode(2);

        TreeNode q2 = new TreeNode(1);
        q2.right = new TreeNode(2);

        boolean expected2 = false;
        boolean result2 = solution.isSameTree(p2, q2);
        if (result2 == expected2) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2 + ", Got: " + result2);
        }
        System.out.println();

        // Test case 3: LeetCode Example 3 - Different values
        System.out.println("Test 3: Different values [1,2,1] and [1,1,2]");
        TreeNode p3 = new TreeNode(1);
        p3.left = new TreeNode(2);
        p3.right = new TreeNode(1);

        TreeNode q3 = new TreeNode(1);
        q3.left = new TreeNode(1);
        q3.right = new TreeNode(2);

        boolean expected3 = false;
        boolean result3 = solution.isSameTree(p3, q3);
        if (result3 == expected3) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3 + ", Got: " + result3);
        }
        System.out.println();

        // Test case 4: Both trees are null
        System.out.println("Test 4: Both trees are null");
        TreeNode p4 = null;
        TreeNode q4 = null;

        boolean expected4 = true;
        boolean result4 = solution.isSameTree(p4, q4);
        if (result4 == expected4) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4 + ", Got: " + result4);
        }
        System.out.println();

        // Test case 5: One tree is null, other is not
        System.out.println("Test 5: One null, one non-null");
        TreeNode p5 = new TreeNode(1);
        TreeNode q5 = null;

        boolean expected5 = false;
        boolean result5 = solution.isSameTree(p5, q5);
        if (result5 == expected5) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5 + ", Got: " + result5);
        }
        System.out.println();

        // Test case 6: Single nodes with same value
        System.out.println("Test 6: Single nodes with same value");
        TreeNode p6 = new TreeNode(5);
        TreeNode q6 = new TreeNode(5);

        boolean expected6 = true;
        boolean result6 = solution.isSameTree(p6, q6);
        if (result6 == expected6) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6 + ", Got: " + result6);
        }
        System.out.println();

        // Test case 7: Single nodes with different values
        System.out.println("Test 7: Single nodes with different values");
        TreeNode p7 = new TreeNode(5);
        TreeNode q7 = new TreeNode(3);

        boolean expected7 = false;
        boolean result7 = solution.isSameTree(p7, q7);
        if (result7 == expected7) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7 + ", Got: " + result7);
        }
        System.out.println();

        // Test case 8: Same structure and values, larger tree
        System.out.println("Test 8: Identical larger trees");
        TreeNode p8 = new TreeNode(1);
        p8.left = new TreeNode(2);
        p8.right = new TreeNode(3);
        p8.left.left = new TreeNode(4);
        p8.left.right = new TreeNode(5);
        p8.right.left = new TreeNode(6);

        TreeNode q8 = new TreeNode(1);
        q8.left = new TreeNode(2);
        q8.right = new TreeNode(3);
        q8.left.left = new TreeNode(4);
        q8.left.right = new TreeNode(5);
        q8.right.left = new TreeNode(6);

        boolean expected8 = true;
        boolean result8 = solution.isSameTree(p8, q8);
        if (result8 == expected8) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected8 + ", Got: " + result8);
        }
        System.out.println();

        // Test case 9: Same structure, one missing node
        System.out.println("Test 9: Same structure, one missing node");
        TreeNode p9 = new TreeNode(1);
        p9.left = new TreeNode(2);
        p9.right = new TreeNode(3);
        p9.left.left = new TreeNode(4);

        TreeNode q9 = new TreeNode(1);
        q9.left = new TreeNode(2);
        q9.right = new TreeNode(3);
        // Missing q9.left.left = new TreeNode(4);

        boolean expected9 = false;
        boolean result9 = solution.isSameTree(p9, q9);
        if (result9 == expected9) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected9 + ", Got: " + result9);
        }
        System.out.println();

        // Test case 10: Negative values (edge case)
        System.out.println("Test 10: Trees with negative values");
        TreeNode p10 = new TreeNode(-1);
        p10.left = new TreeNode(-2);
        p10.right = new TreeNode(-3);

        TreeNode q10 = new TreeNode(-1);
        q10.left = new TreeNode(-2);
        q10.right = new TreeNode(-3);

        boolean expected10 = true;
        boolean result10 = solution.isSameTree(p10, q10);
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