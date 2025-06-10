package com.alex.neetcode150.trees.subtreeOfAnotherTree;

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
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (subRoot == null)
            return true;

        if (root == null)
            return false;

        if (isSameTree(root, subRoot))
            return true;

        return (isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot));
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;

        if (p == null || q == null || p.val != q.val)
            return false;

        return (isSameTree(p.left, q.left) && isSameTree(p.right, q.right));
    }
}

class EfficientSolution {
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        return serialise(root).contains(serialise(subRoot));
    }

    public String serialise(TreeNode root) {
        if (root == null)
            return "null,";

        return root.val + ',' + serialise(root.left) + serialise(root.right);
    }
}


class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 10;

        // Test case 1: LeetCode Example 1 - Valid subtree
        System.out.println("Test 1: Valid subtree [3,4,5,1,2] with subtree [4,1,2]");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(4);
        root1.right = new TreeNode(5);
        root1.left.left = new TreeNode(1);
        root1.left.right = new TreeNode(2);

        TreeNode subRoot1 = new TreeNode(4);
        subRoot1.left = new TreeNode(1);
        subRoot1.right = new TreeNode(2);

        boolean expected1 = true;
        boolean result1 = solution.isSubtree(root1, subRoot1);
        if (result1 == expected1) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected1 + ", Got: " + result1);
        }
        System.out.println();

        // Test case 2: LeetCode Example 2 - Not a subtree (partial match)
        System.out.println("Test 2: Not a subtree [3,4,5,1,2,null,null,null,null,0] with [4,1,2]");
        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(4);
        root2.right = new TreeNode(5);
        root2.left.left = new TreeNode(1);
        root2.left.right = new TreeNode(2);
        root2.left.right.left = new TreeNode(0);

        TreeNode subRoot2 = new TreeNode(4);
        subRoot2.left = new TreeNode(1);
        subRoot2.right = new TreeNode(2);

        boolean expected2 = false;
        boolean result2 = solution.isSubtree(root2, subRoot2);
        if (result2 == expected2) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2 + ", Got: " + result2);
        }
        System.out.println();

        // Test case 3: Subtree is the entire tree
        System.out.println("Test 3: Subtree is the entire tree");
        TreeNode root3 = new TreeNode(1);
        root3.left = new TreeNode(2);
        root3.right = new TreeNode(3);

        TreeNode subRoot3 = new TreeNode(1);
        subRoot3.left = new TreeNode(2);
        subRoot3.right = new TreeNode(3);

        boolean expected3 = true;
        boolean result3 = solution.isSubtree(root3, subRoot3);
        if (result3 == expected3) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3 + ", Got: " + result3);
        }
        System.out.println();

        // Test case 4: Single node subtree exists
        System.out.println("Test 4: Single node subtree exists");
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.right = new TreeNode(3);

        TreeNode subRoot4 = new TreeNode(2);

        boolean expected4 = true;
        boolean result4 = solution.isSubtree(root4, subRoot4);
        if (result4 == expected4) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4 + ", Got: " + result4);
        }
        System.out.println();

        // Test case 5: Single node subtree doesn't exist
        System.out.println("Test 5: Single node subtree doesn't exist");
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);

        TreeNode subRoot5 = new TreeNode(4);

        boolean expected5 = false;
        boolean result5 = solution.isSubtree(root5, subRoot5);
        if (result5 == expected5) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5 + ", Got: " + result5);
        }
        System.out.println();

        // Test case 6: Empty subtree (null)
        System.out.println("Test 6: Null subtree");
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);

        TreeNode subRoot6 = null;

        boolean expected6 = true; // Null is considered a subtree of any tree
        boolean result6 = solution.isSubtree(root6, subRoot6);
        if (result6 == expected6) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6 + ", Got: " + result6);
        }
        System.out.println();

        // Test case 7: Root is null, subtree is not null
        System.out.println("Test 7: Root is null, subtree is not null");
        TreeNode root7 = null;
        TreeNode subRoot7 = new TreeNode(1);

        boolean expected7 = false;
        boolean result7 = solution.isSubtree(root7, subRoot7);
        if (result7 == expected7) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7 + ", Got: " + result7);
        }
        System.out.println();

        // Test case 8: Subtree found in right subtree
        System.out.println("Test 8: Subtree found in right subtree");
        TreeNode root8 = new TreeNode(1);
        root8.left = new TreeNode(2);
        root8.right = new TreeNode(3);
        root8.right.left = new TreeNode(4);
        root8.right.right = new TreeNode(5);

        TreeNode subRoot8 = new TreeNode(3);
        subRoot8.left = new TreeNode(4);
        subRoot8.right = new TreeNode(5);

        boolean expected8 = true;
        boolean result8 = solution.isSubtree(root8, subRoot8);
        if (result8 == expected8) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected8 + ", Got: " + result8);
        }
        System.out.println();

        // Test case 9: Same values but different structure
        System.out.println("Test 9: Same values but different structure");
        TreeNode root9 = new TreeNode(1);
        root9.left = new TreeNode(2);
        root9.right = new TreeNode(3);
        root9.left.right = new TreeNode(4);

        TreeNode subRoot9 = new TreeNode(2);
        subRoot9.left = new TreeNode(4);

        boolean expected9 = false;
        boolean result9 = solution.isSubtree(root9, subRoot9);
        if (result9 == expected9) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected9 + ", Got: " + result9);
        }
        System.out.println();

        // Test case 10: Deep nested subtree
        System.out.println("Test 10: Deep nested subtree");
        TreeNode root10 = new TreeNode(1);
        root10.left = new TreeNode(2);
        root10.right = new TreeNode(3);
        root10.left.left = new TreeNode(4);
        root10.left.right = new TreeNode(5);
        root10.left.left.left = new TreeNode(6);
        root10.left.left.right = new TreeNode(7);

        TreeNode subRoot10 = new TreeNode(4);
        subRoot10.left = new TreeNode(6);
        subRoot10.right = new TreeNode(7);

        boolean expected10 = true;
        boolean result10 = solution.isSubtree(root10, subRoot10);
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