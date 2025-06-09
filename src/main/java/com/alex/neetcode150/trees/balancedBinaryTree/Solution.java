package com.alex.neetcode150.trees.balancedBinaryTree;

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
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;

        if (Math.abs(calcHeight(root.left) - calcHeight(root.right)) > 1)
            return false;

        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int calcHeight(TreeNode node) {
        if (node == null)
            return 0;
        return Math.max(calcHeight(node.left), calcHeight(node.right)) + 1;
    }
}

class EfficientSolution {
    private boolean isBalanced;

    public boolean isBalanced(TreeNode root) {
        isBalanced = true;
        calcHeightAndCheckIfBalanced(root);
        return isBalanced;
    }

    public int calcHeightAndCheckIfBalanced(TreeNode node) {
        if (node == null)
            return 0;

        if (isBalanced == false)   // If we've already found one imbalanced subtree, no point continuing
            return 0;

        int leftHeight = calcHeightAndCheckIfBalanced(node.left);
        int rightHeight = calcHeightAndCheckIfBalanced(node.right);

        if (Math.abs(leftHeight - rightHeight) > 1)
            isBalanced = false;

        return Math.max(leftHeight, rightHeight) + 1;
    }
}

class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int testsPassed = 0;
        int totalTests = 8;

        // Test case 1: LeetCode Example 1 - Balanced tree
        System.out.println("Test 1: Balanced tree [3,9,20,null,null,15,7]");
        TreeNode root1 = new TreeNode(3);
        root1.left = new TreeNode(9);
        root1.right = new TreeNode(20);
        root1.right.left = new TreeNode(15);
        root1.right.right = new TreeNode(7);

        boolean expected1 = true;
        boolean result1 = solution.isBalanced(root1);
        if (result1 == expected1) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected1 + ", Got: " + result1);
        }
        System.out.println();

        // Test case 2: LeetCode Example 2 - Unbalanced tree
        System.out.println("Test 2: Unbalanced tree [1,2,2,3,3,null,null,4,4]");
        TreeNode root2 = new TreeNode(1);
        root2.left = new TreeNode(2);
        root2.right = new TreeNode(2);
        root2.left.left = new TreeNode(3);
        root2.left.right = new TreeNode(3);
        root2.left.left.left = new TreeNode(4);
        root2.left.left.right = new TreeNode(4);

        boolean expected2 = false;
        boolean result2 = solution.isBalanced(root2);
        if (result2 == expected2) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected2 + ", Got: " + result2);
        }
        System.out.println();

        // Test case 3: Empty tree
        System.out.println("Test 3: Empty tree");
        TreeNode root3 = null;

        boolean expected3 = true;
        boolean result3 = solution.isBalanced(root3);
        if (result3 == expected3) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected3 + ", Got: " + result3);
        }
        System.out.println();

        // Test case 4: Single node
        System.out.println("Test 4: Single node");
        TreeNode root4 = new TreeNode(1);

        boolean expected4 = true;
        boolean result4 = solution.isBalanced(root4);
        if (result4 == expected4) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected4 + ", Got: " + result4);
        }
        System.out.println();

        // Test case 5: Perfect binary tree (balanced)
        System.out.println("Test 5: Perfect binary tree");
        TreeNode root5 = new TreeNode(1);
        root5.left = new TreeNode(2);
        root5.right = new TreeNode(3);
        root5.left.left = new TreeNode(4);
        root5.left.right = new TreeNode(5);
        root5.right.left = new TreeNode(6);
        root5.right.right = new TreeNode(7);

        boolean expected5 = true;
        boolean result5 = solution.isBalanced(root5);
        if (result5 == expected5) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected5 + ", Got: " + result5);
        }
        System.out.println();

        // Test case 6: Left-skewed tree (unbalanced)
        System.out.println("Test 6: Left-skewed tree (unbalanced)");
        TreeNode root6 = new TreeNode(1);
        root6.left = new TreeNode(2);
        root6.left.left = new TreeNode(3);
        root6.left.left.left = new TreeNode(4);

        boolean expected6 = false;
        boolean result6 = solution.isBalanced(root6);
        if (result6 == expected6) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected6 + ", Got: " + result6);
        }
        System.out.println();

        // Test case 7: Right-skewed tree (unbalanced)
        System.out.println("Test 7: Right-skewed tree (unbalanced)");
        TreeNode root7 = new TreeNode(1);
        root7.right = new TreeNode(2);
        root7.right.right = new TreeNode(3);
        root7.right.right.right = new TreeNode(4);

        boolean expected7 = false;
        boolean result7 = solution.isBalanced(root7);
        if (result7 == expected7) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected7 + ", Got: " + result7);
        }
        System.out.println();

        // Test case 8: Tree that's balanced at root but unbalanced in subtree
        System.out.println("Test 8: Balanced at root, unbalanced in subtree");
        TreeNode root8 = new TreeNode(1);
        root8.left = new TreeNode(2);
        root8.right = new TreeNode(3);
        root8.left.left = new TreeNode(4);
        root8.left.left.left = new TreeNode(5);
        root8.left.left.left.left = new TreeNode(6);

        boolean expected8 = false;
        boolean result8 = solution.isBalanced(root8);
        if (result8 == expected8) {
            System.out.println("✓ PASSED");
            testsPassed++;
        } else {
            System.out.println("✗ FAILED");
            System.out.println("Expected: " + expected8 + ", Got: " + result8);
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